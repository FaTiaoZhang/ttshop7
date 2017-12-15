package com.dhc.ttshop.dubbo.service.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 14:52
 * Version:V1.0
 */
public class Provider {
    public static void main(String[] args) throws  Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-dubbo-provider.xml"});
        context.start();
        System.in.read();
    }
}
