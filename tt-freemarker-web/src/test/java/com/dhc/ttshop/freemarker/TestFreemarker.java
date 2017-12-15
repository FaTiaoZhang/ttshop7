package com.dhc.ttshop.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/12/13
 * Time: 11:10
 * Version:V1.0
 */
public class TestFreemarker {

    @Test
    public void testFreemarker1() throws Exception{
        //1 获取配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2 设置字符集
        configuration.setDefaultEncoding("UTF-8");
        //3 设置模板所在路径
        configuration.setClassForTemplateLoading(this.getClass(),"/ftl");
        //4 获取模板
        Template template = configuration.getTemplate("01.ftl");
        //5 创建数据集并且设值
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("name", "小叮当");
        //6 输出流
        Writer out = new FileWriter("d:/ftl/01.html");
        //7 生成静态页面
        template.process(dataModel,out);
        //8 释放资源
        out.close();
    }
}
