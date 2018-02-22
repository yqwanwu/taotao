package com.taotao.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.taotao.pojo.DataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    public DataGridResult getItemList(int page, int rows);

    public TbItem getItemById(Long id);

    public Page<TbItem> findPage();

    TaotaoResult createItem(TbItem item, String desc) throws Exception;
}
