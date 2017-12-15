package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.jedis.JedisClient;
import com.dhc.ttshop.common.util.JsonUtils;
import com.dhc.ttshop.common.util.StrKit;
import com.dhc.ttshop.dao.TbContentMapper;
import com.dhc.ttshop.pojo.po.TbContent;
import com.dhc.ttshop.pojo.po.TbContentExample;
import com.dhc.ttshop.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/4
 * Time: 13:58
 * Version:V1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentMapper contentDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> listContentsByCid(Long cid) {
        List<TbContent> contentList = null;
        //1 判断缓存，如果缓存中有数据直接返回
        try {
            String json = jedisClient.hget("CONTENT_LIST", cid + "");
            if (StrKit.notBlank(json)) {
                Integer.parseInt("a");
                contentList = JsonUtils.jsonToList(json, TbContent.class);
                return contentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        //2 主业务：去数据库查询(如果没有的话，去数据库查询)
        //创建查询模板
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        contentList = contentDao.selectByExampleWithBLOBs(example);
        //3 从数据库中查询到以后，要添加的缓存服务器中
        try {
            //存入缓存的hash中
            jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(contentList));
            return contentList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
