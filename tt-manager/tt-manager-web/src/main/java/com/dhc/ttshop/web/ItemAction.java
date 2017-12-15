package com.dhc.ttshop.web;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.common.dto.Order;
import com.dhc.ttshop.common.dto.Page;
import com.dhc.ttshop.common.dto.Result;
import com.dhc.ttshop.common.jedis.JedisClient;
import com.dhc.ttshop.pojo.po.TbItem;
import com.dhc.ttshop.pojo.vo.TbItemCustom;
import com.dhc.ttshop.pojo.vo.TbItemQuery;
import com.dhc.ttshop.service.ItemService;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:19
 * Version:V1.0
 */
@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private ItemService itemService;
    // ItemService itemService = new ItemServiceImpl();

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource
    private ActiveMQTopic topicDestination;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem printJsonById(@PathVariable("itemId") Long itemId) {
        return itemService.getById(itemId);
    }

    //    @ResponseBody
//    @RequestMapping(value = "/items", method = RequestMethod.GET)
//    public List<TbItem> listItems() {
//        List<TbItem> tbItemList = null;
//        try {
//            tbItemList = itemService.listItems();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return tbItemList;
//    }
    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Result<TbItemCustom> listItems(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItems(page, order, query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/items/batch", method = RequestMethod.POST)
    public int itemsBatch(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        try {
            i = itemService.itemsBatch(ids);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public MessageResult saveItem(TbItem tbItem, String content, String paramData) {
        MessageResult mr = new MessageResult();
        try {
            //1 a 保存商品成功  b 返回商品ID
            final long itemId = itemService.saveItem(tbItem, content, paramData);
            //2  发送消息 JmsTemplate.send
            if (itemId > 0){
                //send方法中的两个参数
                //第一个参数：目标对象itemAddTopic
                //第二个参数：TextMessage 商品ID发送出去
                jmsTemplate.send(topicDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage textMessage = session.createTextMessage(itemId + "");
                        return textMessage;
                    }
                });

                //3 封装messageresult
                mr.setSuccess(true);
                mr.setMessage("新增商品成功");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }


}
