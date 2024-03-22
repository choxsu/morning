package com.syc.modules.admin.biz.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syc.framework.security.util.SecurityUtils;
import com.syc.modules.common.constant.SystemConstants;
import com.syc.modules.common.domain.model.Option;
import com.syc.modules.admin.api.model.entity.SysRole;
import com.syc.modules.admin.api.model.entity.SysRoleMenu;
import com.syc.modules.admin.api.model.form.RoleForm;
import com.syc.modules.admin.api.model.query.RolePageQuery;
import com.syc.modules.admin.api.model.vo.RolePageVO;
import com.syc.modules.admin.biz.converter.RoleConverter;
import com.syc.modules.admin.biz.mapper.SysRoleMapper;
import com.syc.modules.admin.biz.services.SysRoleMenuService;
import com.syc.modules.admin.biz.services.SysRoleService;
import com.syc.modules.admin.biz.services.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 角色业务实现类
 *
 * @author xq.su
 * @since 2022/6/3
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService roleMenuService;
    private final SysUserRoleService userRoleService;
    private final RoleConverter roleConverter;

    /**
     * 角色分页列表
     *
     * @param queryParams 角色查询参数
     * @return {@link Page<RolePageVO>} – 角色分页列表
     */
    @Override
    public Page<RolePageVO> getRolePage(RolePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<SysRole> rolePage = this.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysRole>()
                        .and(StrUtil.isNotBlank(keywords),
                                wrapper ->
                                        wrapper.like(StrUtil.isNotBlank(keywords), SysRole::getName, keywords)
                                                .or()
                                                .like(StrUtil.isNotBlank(keywords), SysRole::getCode, keywords)
                        )
                        // 非超级管理员不显示超级管理员角色
                        .ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE)
        );

        // 实体转换
        return roleConverter.entity2Page(rolePage);
    }

    /**
     * 角色下拉列表
     *
     * @return {@link List< Option >} – 角色下拉列表
     */
    @Override
    public List<Option> listRoleOptions() {
        // 查询数据
        List<SysRole> roleList = this.list(new LambdaQueryWrapper<SysRole>()
                .ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE)
                .select(SysRole::getId, SysRole::getName)
                .orderByAsc(SysRole::getSort)
        );

        // 实体转换
        return roleConverter.entities2Options(roleList);
    }

    /**
     * 保存角色
     *
     * @param roleForm 角色表单数据
     * @return {@link Boolean}
     */
    @Override
    public boolean saveRole(RoleForm roleForm) {

        Long roleId = roleForm.getId();

        // 编辑角色时，判断角色是否存在
        SysRole oldRole = null;
        if (roleId != null) {
            oldRole = this.getById(roleId);
            Assert.isTrue(oldRole != null, "角色不存在");
        }

        String roleCode = roleForm.getCode();
        long count = this.count(new LambdaQueryWrapper<SysRole>()
                .ne(roleId != null, SysRole::getId, roleId)
                .and(wrapper ->
                        wrapper.eq(SysRole::getCode, roleCode).or().eq(SysRole::getName, roleForm.getName())
                ));
        Assert.isTrue(count == 0, "角色名称或角色编码已存在，请修改后重试！");

        // 实体转换
        SysRole role = roleConverter.form2Entity(roleForm);

        boolean result = this.saveOrUpdate(role);
        if (result) {
            // 判断角色编码或状态是否修改，修改了则刷新权限缓存
            if (oldRole != null
                    && (
                    !StrUtil.equals(oldRole.getCode(), roleCode) ||
                            !ObjectUtil.equals(oldRole.getStatus(), roleForm.getStatus())
            )) {
                roleMenuService.refreshRolePermsCache(oldRole.getCode(), roleCode);
            }
        }
        return result;
    }

    /**
     * 获取角色表单数据
     *
     * @param roleId 角色ID
     * @return {@link RoleForm} – 角色表单数据
     */
    @Override
    public RoleForm getRoleForm(Long roleId) {
        SysRole entity = this.getById(roleId);
        return roleConverter.entity2Form(entity);
    }

    /**
     * 修改角色状态
     *
     * @param roleId 角色ID
     * @param status 角色状态(1:启用；0:禁用)
     * @return {@link Boolean}
     */
    @Override
    public boolean updateRoleStatus(Long roleId, Integer status) {

        SysRole role = this.getById(roleId);
        Assert.isTrue(role != null, "角色不存在");

        role.setStatus(status);
        boolean result = this.updateById(role);
        if (result) {
            // 刷新角色的权限缓存
            roleMenuService.refreshRolePermsCache(role.getCode());
        }
        return result;
    }

    /**
     * 批量删除角色
     *
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return {@link Boolean}
     */
    @Override
    public boolean deleteRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的角色ID不能为空");
        List<Long> roleIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long roleId : roleIds) {
            SysRole role = this.getById(roleId);
            Assert.isTrue(role != null, "角色不存在");

            // 判断角色是否被用户关联
            boolean isRoleAssigned = userRoleService.hasAssignedUsers(roleId);
            Assert.isTrue(!isRoleAssigned, "角色【{}】已分配用户，请先解除关联后删除", role.getName());

            boolean deleteResult = this.removeById(roleId);
            if (deleteResult) {
                // 删除成功，刷新权限缓存
                roleMenuService.refreshRolePermsCache(role.getCode());
            }
        }
        return true;
    }

    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */
    @Override
    public List<Long> getRoleMenuIds(Long roleId) {
        return roleMenuService.listMenuIdsByRoleId(roleId);
    }

    /**
     * 修改角色的资源权限
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "menu", key = "'routes'")
    public boolean assignMenusToRole(Long roleId, List<Long> menuIds) {
        SysRole role = this.getById(roleId);
        Assert.isTrue(role != null, "角色不存在");

        // 删除角色菜单
        roleMenuService.remove(
                new LambdaQueryWrapper<SysRoleMenu>()
                        .eq(SysRoleMenu::getRoleId, roleId)
        );
        // 新增角色菜单
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<SysRoleMenu> roleMenus = menuIds
                    .stream()
                    .map(menuId -> new SysRoleMenu(roleId, menuId))
                    .toList();
            roleMenuService.saveBatch(roleMenus);
        }

        // 刷新角色的权限缓存
        roleMenuService.refreshRolePermsCache(role.getCode());

        return true;
    }

    /**
     * 获取最大范围的数据权限
     *
     * @param roles 角色编码集合
     * @return {@link Integer} – 数据权限范围
     */
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        return this.baseMapper.getMaximumDataScope(roles);
    }

}
