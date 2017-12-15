package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.Page;
import com.dhc.ttshop.common.dto.Result;
import com.dhc.ttshop.dao.TbItemParamCustomMapper;
import com.dhc.ttshop.dao.TbItemParamMapper;
import com.dhc.ttshop.pojo.po.TbItemParam;
import com.dhc.ttshop.pojo.po.TbItemParamExample;
import com.dhc.ttshop.pojo.vo.TbItemParamCustom;
import com.dhc.ttshop.service.ItemParamService;
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
 * Date: 2017/11/24
 * Time: 17:32
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemParamMapper itemParamDao;
    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;

    @Transactional
    @Override
    public int saveItemParam(Long cid, String paramData) {
        int i = 0;
        try {
            TbItemParam itemParam = new TbItemParam();
            itemParam.setItemCatId(cid);
            itemParam.setParamData(paramData);
            itemParam.setCreated(new Date());
            itemParam.setUpdated(new Date());
            i = itemParamDao.insertSelective(itemParam);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return i;
    }

    @Override
    public TbItemParam getByCid(Long cid) {
        TbItemParam tbItemParam = null;
        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            List<TbItemParam> list =  itemParamDao.selectByExampleWithBLOBs(example);
            if(list != null && list.size()>0){
                tbItemParam = list.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return tbItemParam;
    }

    @Override
    public Result<TbItemParamCustom> listItemParams(Page page) {
        Result<TbItemParamCustom> rs = new Result<TbItemParamCustom>();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("page", page);
            List<TbItemParamCustom> list = itemParamCustomDao.listItemParamsByPage(map);
            long counts = itemParamCustomDao.countItemParams(map);
            rs.setTotal(counts);
            rs.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return rs;
    }
}
