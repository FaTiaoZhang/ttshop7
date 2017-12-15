package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.dao.TbItemCustomMapper;
import com.dhc.ttshop.pojo.vo.TbSearchItemCustom;
import com.dhc.ttshop.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/6
 * Time: 13:59
 * Version:V1.0
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SolrServer solrServer;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Override
    public MessageResult importAllItemsIndex() {
        MessageResult mr = null;
        try {
            //总体步骤：创建索引库（采集数据、插入索引库）
            //1 采集数据（VO  TbSearchItemCustom：id,title,sellPoint,price,image,catName）
            List<TbSearchItemCustom> list = itemCustomDao.listSearchImportItems();
            //2 遍历上一步出现list
            for(TbSearchItemCustom item : list){
                //创建文档对象
                SolrInputDocument document = new SolrInputDocument();
                //将field添加到文档对象中，addField中的key一定要与schema.xml中field name保持一致
                document.addField("id",item.getId());
                document.addField("item_title",item.getTitle());
                document.addField("item_sell_point",item.getSellPoint());
                document.addField("item_price",item.getPrice());
                document.addField("item_image",item.getImage());
                document.addField("item_category_name",item.getCatName());
                //写入索引库操作
                solrServer.add(document);
            }
            //提交
            solrServer.commit();
            mr = new MessageResult();
            mr.setSuccess(true);
            mr.setMessage("恭喜！一键导入成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }
}
