package com.syc.module.admin.controller.system;


import cn.hutool.core.bean.BeanUtil;
import com.syc.module.admin.controller.system.vo.dict.DictDataSimpleRespVO;
import com.syc.module.common.annotation.Anonymous;
import com.syc.module.common.annotation.ApiResource;
import com.syc.module.common.domain.PageResult;
import com.syc.module.common.domain.R;
import com.syc.module.common.domain.entity.SysDictDataEntity;
import com.syc.module.common.enums.CommonStatusEnum;
import com.syc.module.common.enums.ResBizTypeEnum;
import com.syc.module.framework.service.SysDictDataService;
import com.syc.module.framework.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/dict-data")
@ApiResource(name = "字典数据管理", resBizType = ResBizTypeEnum.SYSTEM)
public class SysDictDataController {
    @Autowired
    private SysDictDataService dictDataService;
    @Autowired
    private SysDictTypeService dictTypeService;


    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping(value = "/list", name = "字典数据管理-分页")
    public R page(SysDictDataEntity dictData) {
        PageResult<SysDictDataEntity> page = dictDataService.page(dictData);
        return R.ok().put(page);
    }

    @GetMapping(value = {"/list-all-simple", "simple-list"})
    @Operation(summary = "获得全部字典数据列表", description = "一般用于管理后台缓存字典数据在本地")
    public R<List<DictDataSimpleRespVO>> getSimpleDictDataList() {
        List<SysDictDataEntity> list = dictDataService.getDictDataList(CommonStatusEnum.ENABLE.getStatus(), null);
        return R.ok(list.stream().map(r-> BeanUtil.toBean(r, DictDataSimpleRespVO.class)).collect(Collectors.toList()));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}", name = "字典数据管理-根据字典类型查询字典数据信息")
    @Anonymous
    public R dictType(@PathVariable String dictType) {

        List<SysDictDataEntity> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isEmpty(data)) {
            data = new ArrayList<SysDictDataEntity>();
        }
        return R.ok().put(data);
    }


    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}", name = "字典数据管理-查询")
    public R getInfo(@PathVariable Long dictCode) {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }


    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @PostMapping(name = "字典数据管理-新增")
    public R add(@Validated @RequestBody SysDictDataEntity dict) {
        return R.ok(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @PutMapping(name = "字典数据管理-修改")
    public R edit(@Validated @RequestBody SysDictDataEntity dict) {
        return R.ok(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @DeleteMapping(value = "/{dictCodes}", name = "字典数据管理-删除")
    public R remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return R.ok();
    }
}
