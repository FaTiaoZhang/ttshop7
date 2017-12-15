package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.MessageResult;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 9:42
 * Version:V1.0
 */
public interface TokenService {
    /**
     * 通过令牌查询用户
     * @param token
     * @return
     */
    MessageResult getUserByToken(String token);
}
