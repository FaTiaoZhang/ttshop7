package com.dhc.ttshop.pojo.vo;

/**
 * 模糊查询的简单实体类
 * User: DHC
 * Date: 2017/11/22
 * Time: 16:50
 * Version:V1.0
 */
public class TbItemQuery {
    private String title;
    private Byte status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
