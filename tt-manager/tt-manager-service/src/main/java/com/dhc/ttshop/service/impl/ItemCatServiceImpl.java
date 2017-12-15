package com.dhc.ttshop.service.impl;

import com.dhc.ttshop.common.dto.TreeNode;
import com.dhc.ttshop.dao.TbItemCatMapper;
import com.dhc.ttshop.pojo.po.TbItemCat;
import com.dhc.ttshop.pojo.po.TbItemCatExample;
import com.dhc.ttshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/23
 * Time: 16:26
 * Version:V1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCatMapper itemCatDao;


    @Override
    public List<TreeNode> listItemCatsForTree(Long parentId) {
        List<TreeNode> nodeList = null;
        try {
            //创建模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> itemCatList = itemCatDao.selectByExample(example);
            nodeList = new ArrayList<TreeNode>();
            for (TbItemCat itemCat : itemCatList){
                TreeNode treeNode = new TreeNode();
                treeNode.setId(itemCat.getId());
                treeNode.setText(itemCat.getName());
                treeNode.setState(itemCat.getIsParent()?"closed":"open");
                nodeList.add(treeNode);
            }
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return nodeList;
    }
}
