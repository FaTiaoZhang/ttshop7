package com.dhc.ttshop.search.message;

import com.dhc.ttshop.dao.TbItemCustomMapper;
import com.dhc.ttshop.pojo.vo.TbSearchItemCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听器：消息队列中有新入队的消息，立即消费
 * 添加到索引库
 * User: DHC
 * Date: 2017/12/8
 * Time: 15:43
 * Version:V1.0
 */
public class ItemAddMessageListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void onMessage(Message message) {
        try {
            //接收消息，获取到商品ID
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);
            //采集数据
            TbSearchItemCustom item = itemCustomDao.getSearchItemByItemId(itemId);
            //把field添加document中
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCatName());
            //写入索引库
            solrServer.add(document);
            //提交
            solrServer.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
