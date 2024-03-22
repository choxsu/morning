package com.syc.modules.admin.api.model.query;

import com.syc.modules.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户分页查询对象
 *
 * @author xq.su
 * @since 2022/1/14
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description ="用户分页查询对象")
@Data
public class UserPageQuery extends BasePageQuery {

    @Schema(description="关键字(用户名/昵称/手机号)")
    private String keywords;

    @Schema(description="用户状态")
    private Integer status;

    @Schema(description="部门ID")
    private Long deptId;

    @Schema(description="创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description="创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;
}
