package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TreeNode> getCatList(long parentId) {
        List<TbItemCat> itemCatList = itemCatMapper.findByParentId(parentId);

        List<TreeNode> list = new ArrayList<>();
        for (TbItemCat cat : itemCatList) {
            TreeNode node = new TreeNode();
            node.setId(cat.getId());
            node.setState(cat.getIsParent() ? "closed" : "open");
            node.setText(cat.getName());
            list.add(node);
        }

        return list;
    }
}
