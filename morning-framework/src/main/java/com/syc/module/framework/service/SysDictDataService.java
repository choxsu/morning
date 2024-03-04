package com.syc.module.framework.service;


import com.syc.module.common.domain.PageResult;
import com.syc.module.common.domain.entity.SysDictDataEntity;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SysDictDataService {

    PageResult<SysDictDataEntity> page(SysDictDataEntity dictDataEntity);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictDataEntity dictData);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictDataEntity selectDictDataById(Long dictCode);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
     int updateDictData(SysDictDataEntity dictData);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
     void deleteDictDataByIds(Long[] dictCodes);


    /**
     * 获得字典数据列表
     *
     * @param status   状态
     * @param dictType 字典类型
     * @return 字典数据全列表
     */
    List<SysDictDataEntity> getDictDataList(@Nullable Integer status, @Nullable String dictType);
}
