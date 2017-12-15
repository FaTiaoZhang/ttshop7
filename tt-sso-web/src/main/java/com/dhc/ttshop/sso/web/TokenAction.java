package com.dhc.ttshop.sso.web;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 9:38
 * Version:V1.0
 */
@Controller
public class TokenAction {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/user/{token}", method = RequestMethod.GET)
    @ResponseBody
    public MessageResult getUserByToken(@PathVariable String token) {
        MessageResult mr = tokenService.getUserByToken(token);
        return mr;
    }
}
