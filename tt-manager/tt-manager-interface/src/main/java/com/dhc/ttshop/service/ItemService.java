package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.Order;
import com.dhc.ttshop.common.dto.Page;
import com.dhc.ttshop.common.dto.Result;
import com.dhc.ttshop.pojo.po.TbItem;
import com.dhc.ttshop.pojo.vo.TbItemCustom;
import com.dhc.ttshop.pojo.vo.TbItemQuery;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/17
 * Time: 15:26
 * Version:V1.0
 */
public interface ItemService {
    /**
     * 通过商品主键查询单个商品
     * @param itemId
     * @return
     */
    TbItem getById(Long itemId);

    /**
     * 不带分页的查询所有商品
     * @return
     */
    List<TbItem> listItems();

    /**
     * 带分页的查询所有商品
     * @return
     */
    Result<TbItemCustom> listItems(Page page, Order order, TbItemQuery query);

    /**
     * 批量修改商品的状态
     * @param ids
     * @return
     */
    int itemsBatch(List<Long> ids);

    /**
     * 发布商品
     * @param tbItem
     * @param content
     * @return
     */
    long saveItem(TbItem tbItem, String content, String paramData);
}
