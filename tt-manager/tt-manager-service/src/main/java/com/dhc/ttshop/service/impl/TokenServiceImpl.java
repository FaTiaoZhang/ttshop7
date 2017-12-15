package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.common.jedis.JedisClient;
import com.dhc.ttshop.common.util.JsonUtils;
import com.dhc.ttshop.common.util.StrKit;
import com.dhc.ttshop.pojo.po.TbUser;
import com.dhc.ttshop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 9:43
 * Version:V1.0
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult getUserByToken(String token) {
        //初始化结果集对象
        MessageResult mr = new MessageResult();
        //从redis集群中获取token信息
        String userJson = jedisClient.get("TT_TOKEN:" + token);
        if (StrKit.isBlank(userJson)){
            //JSON中没有token登录信息
            mr.setSuccess(false);
            mr.setMessage("用户登录已过期！");
            //返回校验失败信息
            return mr;
        }
        //JSON中有登录信息的
        //user的password属性为null
        TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
        mr.setSuccess(true);
        mr.setMessage("登录成功");
        mr.setData(user);
        return mr;
    }
}
