package com.dhc.ttshop.dao;

import com.dhc.ttshop.pojo.vo.TbItemCustom;
import com.dhc.ttshop.pojo.vo.TbSearchItemCustom;

import java.util.List;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/11/20
 * Time: 18:03
 * Version:V1.0
 */
public interface TbItemCustomMapper {
    /**
     * 符合条件的总记录数
     * @return
     */
    long countItems(Map<String,Object> map);

    /**
     * 指定页码的记录集合
     * @return
     */
//    List<TbItemCustom> listItems(@Param("page") Page page, @Param("order") Order order);
    List<TbItemCustom> listItems(Map<String,Object> map);

    /**
     * 去数据库中查询数据产生一个集合
     * @return
     */
    List<TbSearchItemCustom> listSearchImportItems();

    /**
     * 通过商品ID两表联查出TbSearchItemCustom
     * @param itemId
     * @return
     */
    TbSearchItemCustom getSearchItemByItemId(long itemId);
}
