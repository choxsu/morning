package com.syc.modules.admin.biz.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syc.modules.common.domain.model.Option;
import com.syc.modules.admin.api.model.entity.SysDept;
import com.syc.modules.admin.api.model.form.DeptForm;
import com.syc.modules.admin.api.model.query.DeptQuery;
import com.syc.modules.admin.api.model.vo.DeptVO;


import java.util.List;

/**
 * 部门业务接口
 *
 * @author xq.su
 * @since 2021/8/22
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * 部门列表
     *
     * @return
     */
    List<DeptVO> listDepartments(DeptQuery queryParams);

    /**
     * 部门树形下拉选项
     *
     * @return
     */
    List<Option> listDeptOptions();

    /**
     * 新增部门
     *
     * @param formData
     * @return
     */
    Long saveDept(DeptForm formData);

    /**
     * 修改部门
     *
     * @param deptId
     * @param formData
     * @return
     */
    Long updateDept(Long deptId, DeptForm formData);

    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return
     */
    boolean deleteByIds(String ids);

    /**
     * 获取部门详情
     *
     * @param deptId
     * @return
     */
    DeptForm getDeptForm(Long deptId);
}
