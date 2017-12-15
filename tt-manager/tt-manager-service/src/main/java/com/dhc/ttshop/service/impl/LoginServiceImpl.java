package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.common.jedis.JedisClient;
import com.dhc.ttshop.common.util.JsonUtils;
import com.dhc.ttshop.common.util.StrKit;
import com.dhc.ttshop.dao.TbUserMapper;
import com.dhc.ttshop.pojo.po.TbUser;
import com.dhc.ttshop.pojo.po.TbUserExample;
import com.dhc.ttshop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/11
 * Time: 15:25
 * Version:V1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbUserMapper userDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult userLogin(String username, String password) {
        MessageResult mr = null;
        try {
            //1 使用模板进行查询
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<TbUser> userList = userDao.selectByExample(example);
            //2 判断集合中是否有当前用户
            if (userList == null || userList.size() == 0) {
                //用户名不存在
                mr = new MessageResult();
                mr.setSuccess(false);
                mr.setMessage("用户名不存在");
                return mr;
            }
            //
            TbUser user = userList.get(0);
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
            if (! md5DigestAsHex.equals(user.getPassword())){
                //用户名正确但是密码错误
                mr = new MessageResult();
                mr.setSuccess(false);
                mr.setMessage("用户名或密码错误");
                return mr;
            }
            //用户名和密码均正确
            String token = StrKit.uuid();
            user.setPassword(null);
            //将登录成功的信息存放缓存服务器上
            jedisClient.set("TT_TOKEN:" + token, JsonUtils.objectToJson(user));
            //设置过期时间
            jedisClient.expire("TT_TOKEN:" + token, 1800);
            //返回正确的result
            mr = new MessageResult();
            mr.setSuccess(true);
            mr.setMessage("登录成功");
            mr.setData(token);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }
}
