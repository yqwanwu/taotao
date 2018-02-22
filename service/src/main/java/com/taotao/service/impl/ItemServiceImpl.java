package com.taotao.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.DataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TaotaoResult createItem(TbItem item, String desc) throws Exception {
        Long id = System.currentTimeMillis() * 100 + new Random().nextInt(100);
        item.setId(id);
        //1,正常。。 2 下架， 3 删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemMapper.insert(item);

        if (createItemDesc(id, desc).getStatus() != 200) {
            throw new RuntimeException("保存商品描述信息失败");
        }

        return TaotaoResult.ok();
    }

    private TaotaoResult createItemDesc(Long id, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    @Override
    public Page<TbItem> findPage() {
        Page<TbItem> p = new Page<TbItem>(0, 3);
        PageHelper.setPagination(p);
        p.setRecords(itemMapper.findAllByPage(p));
        return p;
    }

    @Override
    public DataGridResult getItemList(int page, int rows) {
        Page<TbItem> p = new Page<>(page, rows);
        p.setRecords(itemMapper.findAllByPage(p));
        p.setTotal(itemMapper.selectAll().size());
        DataGridResult result = new DataGridResult();
        result.setRows(p.getRecords());
        result.setTotal(p.getTotal());

        return result;
    }

    @Override
    public TbItem getItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }// img_yuyin   img_biaoq   img_add
}
