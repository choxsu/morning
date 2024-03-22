package com.syc.modules.admin.api.model.query;

import com.syc.modules.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限分页查询对象
 *
 * @author xq.su
 * @since 2022/1/14 22:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema 
public class PermPageQuery extends BasePageQuery {

    @Schema(description="权限名称")
    private String name;

    @Schema(description="菜单ID")
    private Long menuId;

}
