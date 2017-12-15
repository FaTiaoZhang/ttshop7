package com.dhc.ttshop.item.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/12/13
 * Time: 13:58
 * Version:V1.0
 */
@Controller
public class GenerateHtmlAction {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @RequestMapping(value = "/generateHtml",method = RequestMethod.GET)
    @ResponseBody
    public String genHTML(){
        try {
            //第一步获取配置对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            //第二步获取模板对象
            Template template = configuration.getTemplate("01.ftl");
            //第三步创建数据集合
            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("name", "hhhh");
            //第四步创建输出流
            Writer out = new FileWriter("d:/ftl/001.html");
            //第五步生成静态页面
            template.process(dataModel,out);
            //第六步关闭资源
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
