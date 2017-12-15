package com.dhc.ttshop.dubbo.service.impl;

import com.dhc.ttshop.dubbo.service.DemoService;

/**
 * User: DHC
 * Date: 2017/12/12
 * Time: 14:38
 * Version:V1.0
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
