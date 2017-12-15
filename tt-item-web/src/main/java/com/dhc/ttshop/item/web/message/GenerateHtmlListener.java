package com.dhc.ttshop.item.web.message;

import com.dhc.ttshop.pojo.po.TbItem;
import com.dhc.ttshop.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/12/13
 * Time: 14:13
 * Version:V1.0
 */
public class GenerateHtmlListener implements MessageListener{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ItemService itemService;
//    @Autowired
//    private ItemParamItemService itemParamItemService;

    @Override
    public void onMessage(Message message) {
        try {
            //1 接收消息 获取商品ID
            TextMessage textMessage = (TextMessage)message;
            String itemIdStr = textMessage.getText();
            long itemId = Long.parseLong(itemIdStr);
            //2 根据商品ID查询出该商品
            TbItem item = itemService.getById(itemId);
//            TbItemParamItem itemParamItem = itemParamItemService.getByItemId(itemId);
            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("item", item);
//            dataModel.put("itemParamItem",itemParamItem);
            //3 生成静态页面
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Writer out = new FileWriter(new File("d:/ftl/" + itemId + ".html"));
            template.process(dataModel,out);
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
