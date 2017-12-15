package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.Page;
import com.dhc.ttshop.common.dto.Result;
import com.dhc.ttshop.pojo.po.TbItemParam;
import com.dhc.ttshop.pojo.vo.TbItemParamCustom;

/**
 * User: DHC
 * Date: 2017/11/24
 * Time: 17:31
 * Version:V1.0
 */
public interface ItemParamService {
    /**
     *
     * @param cid
     * @param paramData
     * @return
     */
    int saveItemParam(Long cid, String paramData);

    /**
     *
     * @param cid
     * @return
     */
    TbItemParam getByCid(Long cid);

    /**
     *
     * @param page
     * @return
     */
    Result<TbItemParamCustom> listItemParams(Page page);
}
