package com.dhc.ttshop.service;

import com.dhc.ttshop.common.dto.TreeNode;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/11/23
 * Time: 16:25
 * Version:V1.0
 */
public interface ItemCatService {
    /**
     * 通过父节点的编号来获取子分类
     * @param parentId
     * @return
     */
    List<TreeNode> listItemCatsForTree(Long parentId);
}
