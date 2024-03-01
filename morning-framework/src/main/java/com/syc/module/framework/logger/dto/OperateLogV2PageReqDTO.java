package com.syc.module.framework.logger.dto;

import com.syc.module.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志分页 Request DTO
 *
 * @author HUIHUI
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperateLogV2PageReqDTO extends PageParam {

    /**
     * 模块类型
     */
    private String bizType;
    /**
     * 模块数据编号
     */
    private Long bizId;

    /**
     * 用户编号
     */
    private Long userId;

}
