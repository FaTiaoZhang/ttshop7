package com.dhc.ttshop.search.web;

import com.dhc.ttshop.common.util.PropKit;
import com.dhc.ttshop.common.util.StrKit;
import com.dhc.ttshop.pojo.dto.TbSearchItemCustomResult;
import com.dhc.ttshop.service.SearchIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: DHC
 * Date: 2017/12/7
 * Time: 9:21
 * Version:V1.0
 */
@Controller
public class SearchIndexAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchIndexService searchIndexService;

    @RequestMapping(value = "/")
    public String searchIndex(String keyword,
                              @RequestParam(defaultValue = "1") Integer page, Model model){
        try {
            if (StrKit.notBlank(keyword)){
                Integer pageSize = PropKit.use("page.properties").getInt("page.pageSize");
                //通过业务逻辑层从索引库中查询结果集
                TbSearchItemCustomResult result = searchIndexService.searchIndex(keyword,page,pageSize);
                //回显到页面的内容
                model.addAttribute("query", keyword);
                model.addAttribute("page", page);
                model.addAttribute("recordCount", result.getRecordCount());
                model.addAttribute("itemList", result.getItemList());
                model.addAttribute("totalPages", result.getTotalPages());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return "search";
    }
}
