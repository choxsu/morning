package com.syc.modules.admin.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户和角色关联表
 *
 * @author xq.su
 * @since 2022/12/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}