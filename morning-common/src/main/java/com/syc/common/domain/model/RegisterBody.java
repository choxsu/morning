package com.syc.common.domain.model;

import lombok.Data;

/**
 * 用户注册对象
 */
@Data
public class RegisterBody extends LoginBody {

    /**
     * 邮箱
     */
    private String email;

}
