package com.syc.modules.admin.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.syc.modules.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xq.su
 */
@EqualsAndHashCode(callSuper = true)
@TableName("file_info")
@Data
public class SysFileInfo extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 是否删除 0否1是
     */
    private Boolean isDelete;

}
