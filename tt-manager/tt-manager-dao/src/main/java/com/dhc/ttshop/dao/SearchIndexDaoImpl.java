package com.dhc.ttshop.dao;

import com.dhc.ttshop.pojo.dto.TbSearchItemCustomResult;
import com.dhc.ttshop.pojo.vo.TbSearchItemCustom;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/12/7
 * Time: 14:00
 * Version:V1.0
 */
@Repository
public class SearchIndexDaoImpl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SolrServer solrServer;

    public TbSearchItemCustomResult searchIndex(SolrQuery query, Integer pageSize) {
        TbSearchItemCustomResult result = new TbSearchItemCustomResult();
        try {
            //获取查询响应
            QueryResponse response = solrServer.query(query);
            //获取文档域
            SolrDocumentList documentList = response.getResults();
            //获取numFound
            long numFound = documentList.getNumFound();
            List<TbSearchItemCustom> itemList = new ArrayList<TbSearchItemCustom>();
            //documentList-->itemList
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument document : documentList) {
                TbSearchItemCustom item = new TbSearchItemCustom();
                item.setId((String) document.get("id"));
                item.setCatName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((long) (document.get("item_price")));
                item.setSellPoint((String) document.get("item_sell_point"));
                //缺一个title
                List<String> stringList = highlighting.get(document.get("id")).get("item_title");
                String title = "";
                if (stringList != null && stringList.size() > 0) {
                    title = stringList.get(0);
                } else {
                    title = (String)document.get("item_title");
                }
                item.setTitle(title);
                itemList.add(item);
            }
            result.setRecordCount(numFound);
            //退一进一
            result.setTotalPages(((int) numFound + pageSize - 1) / pageSize);
            result.setItemList(itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
