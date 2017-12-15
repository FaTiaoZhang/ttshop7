package com.dhc.ttshop.service;

import com.dhc.ttshop.pojo.dto.TbSearchItemCustomResult;

/**
 * User: DHC
 * Date: 2017/12/7
 * Time: 9:59
 * Version:V1.0
 */
public interface SearchIndexService {
    /**
     * 通过查询条件查询索引库返回结果集
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    TbSearchItemCustomResult searchIndex(String keyword, Integer page, Integer pageSize);
}
