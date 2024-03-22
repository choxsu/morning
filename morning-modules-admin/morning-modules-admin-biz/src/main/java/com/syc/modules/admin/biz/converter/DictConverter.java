package com.syc.modules.admin.biz.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.syc.modules.admin.api.model.entity.SysDict;
import com.syc.modules.admin.api.model.form.DictForm;
import com.syc.modules.admin.api.model.vo.DictPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @author xq.su
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> entity2Page(Page<SysDict> page);

    DictForm entity2Form(SysDict entity);

    @InheritInverseConfiguration(name="entity2Form")
    SysDict form2Entity(DictForm entity);
}
