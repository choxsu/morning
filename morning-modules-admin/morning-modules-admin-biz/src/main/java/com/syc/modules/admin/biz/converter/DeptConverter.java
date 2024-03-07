package com.syc.modules.admin.biz.converter;


import com.syc.modules.admin.api.model.entity.SysDept;
import com.syc.modules.admin.api.model.form.DeptForm;
import com.syc.modules.admin.api.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author haoxr
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm entity2Form(SysDept entity);
    DeptVO entity2Vo(SysDept entity);

    SysDept form2Entity(DeptForm deptForm);

}