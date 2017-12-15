package com.dhc.ttshop.dubbo.service.main;

import com.dhc.ttshop.dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 15:34
 * Version:V1.0
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring-dubbo-consumer.xml"});
        context.start();
        DemoService service = (DemoService) context.getBean("demoService");//获取到了代理对象（RPC）
        System.out.println(service.sayHello("xdd"));
    }
}
