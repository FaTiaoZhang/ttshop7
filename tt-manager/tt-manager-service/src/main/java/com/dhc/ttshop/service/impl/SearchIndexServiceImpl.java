package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.dao.SearchIndexDaoImpl;
import com.dhc.ttshop.pojo.dto.TbSearchItemCustomResult;
import com.dhc.ttshop.service.SearchIndexService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: DHC
 * Date: 2017/12/7
 * Time: 10:00
 * Version:V1.0
 */
@Service
public class SearchIndexServiceImpl implements SearchIndexService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchIndexDaoImpl searchIndexDao;

    @Override
    public TbSearchItemCustomResult searchIndex(String keyword, Integer page, Integer pageSize) {
        TbSearchItemCustomResult result = null;
        try {
            //创建一个solr查询对象
            SolrQuery query = new SolrQuery();
            //添加查询条件
            query.setQuery(keyword);
            if (page <= 0) page = 1;
            //通过当前页页码和每页显示条数计算出某页第一条记录的索引号
            query.setStart((page - 1) * pageSize);
            query.setRows(pageSize);
            //添加查询的字段
            query.set("df", "item_keywords");
            //查询中开启高亮查询
            query.setHighlight(true);
            query.addHighlightField("item_title");
            //高亮的前缀
            query.setHighlightSimplePre("<em style='color:red'>");
            //高亮的后缀
            query.setHighlightSimplePost("</em>");
            //调用DAO实现类进行查询（query对象传递下去）
            result = searchIndexDao.searchIndex(query,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
