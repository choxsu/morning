package com.syc.modules.admin.biz.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syc.modules.common.constant.SystemConstants;
import com.syc.modules.common.domain.model.Option;
import com.syc.modules.common.enums.StatusEnum;
import com.syc.modules.admin.api.model.entity.SysDept;
import com.syc.modules.admin.api.model.form.DeptForm;
import com.syc.modules.admin.api.model.query.DeptQuery;
import com.syc.modules.admin.api.model.vo.DeptVO;
import com.syc.modules.admin.biz.converter.DeptConverter;
import com.syc.modules.admin.biz.mapper.SysDeptMapper;
import com.syc.modules.admin.biz.services.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 部门业务实现类
 *
 * @author haoxr
 * @since 2021-08-22
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    private final DeptConverter deptConverter;

    /**
     * 获取部门列表
     */
    @Override
    public List<DeptVO> listDepartments(DeptQuery queryParams) {
        // 查询参数
        String keywords = queryParams.getKeywords();
        Integer status = queryParams.getStatus();

        // 查询数据
        List<SysDept> deptList = this.list(
                new LambdaQueryWrapper<SysDept>()
                        .like(StrUtil.isNotBlank(keywords), SysDept::getName, keywords)
                        .eq(status != null, SysDept::getStatus, status)
                        .orderByAsc(SysDept::getSort)
        );

        if (CollectionUtil.isEmpty(deptList)) {
            return Collections.EMPTY_LIST;
        }

        // 获取所有部门ID
        Set<Long> deptIds = deptList.stream()
                .map(SysDept::getId)
                .collect(Collectors.toSet());
        // 获取父节点ID
        Set<Long> parentIds = deptList.stream()
                .map(SysDept::getParentId)
                .collect(Collectors.toSet());
        // 获取根节点ID（递归的起点），即父节点ID中不包含在部门ID中的节点，注意这里不能拿顶级部门 O 作为根节点，因为部门筛选的时候 O 会被过滤掉
        List<Long> rootIds = CollectionUtil.subtractToList(parentIds, deptIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurDeptList(rootId, deptList).stream())
                .toList();
    }

    /**
     * 递归生成部门树形列表
     *
     * @param parentId 父ID
     * @param deptList 部门列表
     * @return 部门树形列表
     */
    public List<DeptVO> recurDeptList(Long parentId, List<SysDept> deptList) {
        return deptList.stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    DeptVO deptVO = deptConverter.entity2Vo(dept);
                    List<DeptVO> children = recurDeptList(dept.getId(), deptList);
                    deptVO.setChildren(children);
                    return deptVO;
                }).collect(Collectors.toList());
    }

    /**
     * 部门下拉选项
     *
     * @return 部门下拉List集合
     */
    @Override
    public List<Option> listDeptOptions() {

        List<SysDept> deptList = this.list(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getStatus, StatusEnum.ENABLE.getValue())
                .select(SysDept::getId, SysDept::getParentId, SysDept::getName)
                .orderByAsc(SysDept::getSort)
        );
        if (CollectionUtil.isEmpty(deptList)) {
            return Collections.EMPTY_LIST;
        }

        Set<Long> deptIds = deptList.stream()
                .map(SysDept::getId)
                .collect(Collectors.toSet());

        Set<Long> parentIds = deptList.stream()
                .map(SysDept::getParentId)
                .collect(Collectors.toSet());

        List<Long> rootIds = CollectionUtil.subtractToList(parentIds, deptIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurDeptTreeOptions(rootId, deptList).stream())
                .toList();
    }

    /**
     * 新增部门
     *
     * @param formData 部门表单
     * @return 部门ID
     */
    @Override
    public Long saveDept(DeptForm formData) {
        // 校验部门名称是否存在
        String name = formData.getName();
        long count = this.count(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getName, name)
        );
        Assert.isTrue(count == 0, "部门名称已存在");

        // form->entity
        SysDept entity = deptConverter.form2Entity(formData);

        // 生成部门路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除部门时级联删除子部门
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        // 保存部门并返回部门ID
        boolean result = this.save(entity);
        Assert.isTrue(result, "部门保存失败");

        return entity.getId();
    }

    /**
     * 更新部门
     *
     * @param deptId   部门ID
     * @param formData 部门表单
     * @return 部门ID
     */
    @Override
    public Long updateDept(Long deptId, DeptForm formData) {
        // 校验部门名称是否存在
        String name = formData.getName();
        long count = this.count(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getName, name)
                .ne(SysDept::getId, deptId)
        );
        Assert.isTrue(count == 0, "部门名称已存在");

        // form->entity
        SysDept entity = deptConverter.form2Entity(formData);
        entity.setId(deptId);

        // 生成部门路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除部门时级联删除子部门
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        // 保存部门并返回部门ID
        boolean result = this.updateById(entity);
        Assert.isTrue(result, "部门更新失败");

        return entity.getId();
    }

    /**
     * 递归生成部门表格层级列表
     *
     * @param parentId 父ID
     * @param deptList 部门列表
     * @return 部门表格层级列表
     */
    public static List<Option> recurDeptTreeOptions(long parentId, List<SysDept> deptList) {
        List<Option> list = CollectionUtil.emptyIfNull(deptList).stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    Option option = new Option(dept.getId(), dept.getName());
                    List<Option> children = recurDeptTreeOptions(dept.getId(), deptList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        option.setChildren(children);
                    }
                    return option;
                })
                .collect(Collectors.toList());
        return list;
    }


    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return 是否删除成功
     */
    @Override
    public boolean deleteByIds(String ids) {
        // 删除部门及子部门
        if (StrUtil.isNotBlank(ids)) {
            String[] menuIds = ids.split(",");
            for (String deptId : menuIds) {
                this.remove(new LambdaQueryWrapper<SysDept>()
                        .eq(SysDept::getId, deptId)
                        .or()
                        .apply("CONCAT (',',tree_path,',') LIKE CONCAT('%,',{0},',%')", deptId));
            }
        }
        return true;
    }

    /**
     * 获取部门详情
     *
     * @param deptId 部门ID
     * @return 部门表单对象
     */
    @Override
    public DeptForm getDeptForm(Long deptId) {

        SysDept entity = this.getOne(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getId, deptId)
                .select(
                        SysDept::getId,
                        SysDept::getName,
                        SysDept::getParentId,
                        SysDept::getStatus,
                        SysDept::getSort
                ));

        return deptConverter.entity2Form(entity);
    }


    /**
     * 部门路径生成
     *
     * @param parentId 父ID
     * @return 父节点路径以英文逗号(, )分割，eg: 1,2,3
     */
    private String generateDeptTreePath(Long parentId) {
        String treePath = null;
        if (SystemConstants.ROOT_NODE_ID.equals(parentId)) {
            treePath = String.valueOf(parentId);
        } else {
            SysDept parent = this.getById(parentId);
            if (parent != null) {
                treePath = parent.getTreePath() + "," + parent.getId();
            }
        }
        return treePath;
    }
}
