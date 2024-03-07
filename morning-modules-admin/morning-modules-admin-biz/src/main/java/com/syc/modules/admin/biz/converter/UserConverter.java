package com.syc.modules.admin.biz.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.syc.modules.admin.api.model.bo.UserBO;
import com.syc.modules.admin.api.model.bo.UserFormBO;
import com.syc.modules.admin.api.model.entity.SysUser;
import com.syc.modules.admin.api.model.form.UserForm;
import com.syc.modules.admin.api.model.vo.UserImportVO;
import com.syc.modules.admin.api.model.vo.UserInfoVO;
import com.syc.modules.admin.api.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.syc.modules.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.syc.modules.common.enums.GenderEnum.class))")
    })
    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm bo2Form(UserFormBO bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

    SysUser importVo2Entity(UserImportVO vo);

}
