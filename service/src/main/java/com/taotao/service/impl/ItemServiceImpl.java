package com.taotao.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.DataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public Page<TbItem> findPage() {
        Page<TbItem> p = new Page<TbItem>(0, 1);
        PageHelper.setPagination(p);
        p.setRecords(itemMapper.findAllByPage(p));
        return p;
    }

    @Override
    public DataGridResult getItemList(int page, int rows) {

        return null;
    }

    @Override
    public TbItem getItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
