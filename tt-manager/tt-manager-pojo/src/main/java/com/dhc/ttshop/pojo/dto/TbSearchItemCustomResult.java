package com.dhc.ttshop.pojo.dto;

import com.dhc.ttshop.pojo.vo.TbSearchItemCustom;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/7
 * Time: 9:49
 * Version:V1.0
 */
public class TbSearchItemCustomResult {
    private long recordCount;
    private int totalPages;
    private List<TbSearchItemCustom> itemList;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbSearchItemCustom> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbSearchItemCustom> itemList) {
        this.itemList = itemList;
    }
}
