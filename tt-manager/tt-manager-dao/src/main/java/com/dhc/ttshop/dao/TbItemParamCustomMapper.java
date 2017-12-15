package com.dhc.ttshop.dao;

import com.dhc.ttshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

/**
 * User: DHC
 * Date: 2017/11/26
 * Time: 21:19
 * Version:V1.0
 */
public interface TbItemParamCustomMapper {

    List<TbItemParamCustom> listItemParamsByPage(Map<String, Object> map);

    long countItemParams(Map<String, Object> map);
}
