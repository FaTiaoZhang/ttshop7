package com.dhc.ttshop.portal.web;

import com.dhc.ttshop.common.util.PropKit;
import com.dhc.ttshop.pojo.po.TbContent;
import com.dhc.ttshop.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/4
 * Time: 10:19
 * Version:V1.0
 */
@Controller
public class ContentAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String listContentsByCid(Model model){
        try {
            //1 查询出结果
            Long cid = PropKit.use("ftp.properties").getLong("ftp.lunboId");
            //2 放入到model中
            List<TbContent> ad1List = contentService.listContentsByCid(cid);
            model.addAttribute("ad1List", ad1List);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        //3 返回
        return "index";
    }
}
