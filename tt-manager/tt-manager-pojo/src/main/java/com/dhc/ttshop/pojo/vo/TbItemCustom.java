package com.dhc.ttshop.pojo.vo;

import com.dhc.ttshop.pojo.po.TbItem;

/**
 * 商品表的VO类
 * User: DHC
 * Date: 2017/11/21
 * Time: 11:13
 * Version:V1.0
 */
public class TbItemCustom extends TbItem {
    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
