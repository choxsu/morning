package com.syc.module.framework.mapper;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syc.module.common.core.BaseMapperX;
import com.syc.module.common.core.LambdaQueryWrapperX;
import com.syc.module.common.domain.PageResult;
import com.syc.module.common.domain.entity.SysDictTypeEntity;

import java.util.Map;

public interface SysDictTypeMapper extends BaseMapperX<SysDictTypeEntity> {
    default PageResult<SysDictTypeEntity> selectPage(SysDictTypeEntity dictType) {
        return selectPage(new LambdaQueryWrapperX<SysDictTypeEntity>()
                .likeIfPresent(SysDictTypeEntity::getDictName, dictType.getDictName())
                .likeIfPresent(SysDictTypeEntity::getDictType, dictType.getDictType())
                .eqIfPresent(SysDictTypeEntity::getStatus, dictType.getStatus())
                .betweenIfPresent(SysDictTypeEntity::getCreateTime, dictType.getParams()));
    }


    default LambdaQueryWrapper<SysDictTypeEntity> creatWrapper(SysDictTypeEntity dictType) {
        Map<String, Object> params = dictType.getParams();
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        return new LambdaQueryWrapperX<SysDictTypeEntity>()
                .ge(ObjectUtil.isNotEmpty(beginTime), SysDictTypeEntity::getCreateTime, beginTime)
                .le(ObjectUtil.isNotEmpty(endTime), SysDictTypeEntity::getCreateTime, endTime);

    }

}
