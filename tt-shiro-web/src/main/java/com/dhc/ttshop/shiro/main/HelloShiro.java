package com.dhc.ttshop.shiro.main;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: DHC
 * Date: 2017/12/13
 * Time: 17:19
 * Version:V1.0
 */
public class HelloShiro {
    private static final transient Logger log = LoggerFactory.getLogger(HelloShiro.class);
    public static void main(String[] args) {
        //读取类路径下的配置文件产生工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂安全管理器
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //通过安全工具类获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //本来想从前台页面获取到的用户名和密码，形成令牌
        UsernamePasswordToken token = new UsernamePasswordToken("dhc1", "12");
        //打开"记住我"的功能
        token.setRememberMe(true);
        try {
            //使用当前用户进行登录
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
