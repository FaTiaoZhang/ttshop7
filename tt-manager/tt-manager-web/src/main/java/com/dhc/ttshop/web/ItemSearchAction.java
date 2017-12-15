package com.dhc.ttshop.web;

import com.dhc.ttshop.common.dto.MessageResult;
import com.dhc.ttshop.service.ItemSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: DHC
 * Date: 2017/12/6
 * Time: 13:54
 * Version:V1.0
 */
@Controller
public class ItemSearchAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping(value = "/item/search/import",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult importAllItemsIndex(){
        MessageResult mr = null;
        try {
            mr = itemSearchService.importAllItemsIndex();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mr;
    }
}
