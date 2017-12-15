package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.MessageResult;

/**
 * User: DHC
 * Date: 2017/12/6
 * Time: 13:58
 * Version:V1.0
 */
public interface ItemSearchService {
    /**
     * 一键导入索引库，并且返回MessageResult
     * @return
     */
    MessageResult importAllItemsIndex();
}
