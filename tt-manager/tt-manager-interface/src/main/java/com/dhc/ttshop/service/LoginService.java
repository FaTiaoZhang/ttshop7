package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.MessageResult;

/**
 * User: DHC
 * Date: 2017/12/11
 * Time: 15:24
 * Version:V1.0
 */
public interface LoginService {
    MessageResult userLogin(String username, String password);
}
