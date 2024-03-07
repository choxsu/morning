package com.syc.modules.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.modules.admin.api.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {


    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);
}
