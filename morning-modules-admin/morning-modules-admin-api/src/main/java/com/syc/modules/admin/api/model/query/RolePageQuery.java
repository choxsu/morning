package com.syc.modules.admin.api.model.query;

import com.syc.modules.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页查询实体
 *
 * @author xq.su
 * @since 2022/6/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePageQuery extends BasePageQuery {

    @Schema(description = "关键字(角色名称/角色编码)")
    private String keywords;
}
