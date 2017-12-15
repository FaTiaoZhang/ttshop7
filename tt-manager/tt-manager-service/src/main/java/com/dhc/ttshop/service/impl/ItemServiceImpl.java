package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.Order;
import com.dhc.ttshop.common.dto.Page;
import com.dhc.ttshop.common.dto.Result;
import com.dhc.ttshop.common.util.IDUtils;
import com.dhc.ttshop.dao.TbItemCustomMapper;
import com.dhc.ttshop.dao.TbItemDescMapper;
import com.dhc.ttshop.dao.TbItemMapper;
import com.dhc.ttshop.dao.TbItemParamItemMapper;
import com.dhc.ttshop.pojo.po.TbItem;
import com.dhc.ttshop.pojo.po.TbItemDesc;
import com.dhc.ttshop.pojo.po.TbItemExample;
import com.dhc.ttshop.pojo.po.TbItemParamItem;
import com.dhc.ttshop.pojo.vo.TbItemCustom;
import com.dhc.ttshop.pojo.vo.TbItemQuery;
import com.dhc.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:27
 * Version:V1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private TbItemDescMapper itemDescDao;
    @Autowired
    private TbItemParamItemMapper itemParamItemDao;
    @Override
    public TbItem getById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> tbItemList = null;
        try {
            //查询所有商品
            tbItemList = itemDao.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemList;
    }

    @Override
    public Result<TbItemCustom> listItems(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            //0 封装一个Map
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("page", page);
            map.put("order", order);
            map.put("query", query);
            //1 先查总记录数 int--Long
            long total = itemCustomDao.countItems(map);
            //2 查询指定页码的记录集合
            List<TbItemCustom> list = itemCustomDao.listItems(map);
            //3 存放result中
            result = new Result<TbItemCustom>();
            result.setTotal(total);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int itemsBatch(List<Long> ids) {
        int i = 0;
        try {
            TbItem record = new TbItem();
            record.setStatus((byte) 3);
            //创建模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行更新操作
            i = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    //添加了@Transactional这个注解方法叫做事务方法，事务方法并不是越多越好，凡是涉及查询的方法要求不加@Transactional
    //@Transactional尽量精细化，以后会经常调用一些第三方的接口，那么建议调用第三方接口的内容不要加到事务方法中，可以另外添加方法进行解耦操作
    @Transactional
    @Override
    public long saveItem(TbItem tbItem, String content, String paramData) {
        long itemId = 0;
        try {
            int i = 0;
            //先存tb_item
            itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)1);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            i = itemDao.insert(tbItem);
            //再存tb_item_desc
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setItemDesc(content);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            i += itemDescDao.insert(tbItemDesc);
            //再存tb_item_param_item
            TbItemParamItem itemParamItem = new TbItemParamItem();
            itemParamItem.setItemId(itemId);
            itemParamItem.setParamData(paramData);
            itemParamItem.setCreated(new Date());
            itemParamItem.setUpdated(new Date());
            i += itemParamItemDao.insert(itemParamItem);
            if (i == 3){
                return itemId;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return 0;
    }
}
