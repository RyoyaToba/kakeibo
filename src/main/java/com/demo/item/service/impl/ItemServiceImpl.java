package com.demo.item.service.impl;

import com.demo.item.model.ItemUI;
import com.demo.category.entity.Category;
import com.demo.item.entity.Item;
import com.demo.item.repository.ItemMapper;
import com.demo.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional()
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    public ItemMapper itemMapper;

    /** 新規レコードの追加 */
    @Override
    public void insertItem(Item item) {
        itemMapper.insertItem(item);
    }

    /** 対象月内の入力済み情報を取得 */
    @Override
    public List<Item> retrieveItemInTargetMonth(Date startDate, Date endDate) {
        return itemMapper.retrieveItemInTargetMonth(startDate, endDate);
    }

    /** ItemのテーブルデータをUI状態に置き換える */
    @Override
    public List<ItemUI> convertItemToItemUI(List<Item> items, List<Category> categories){
        return items.stream().map(item -> {
            ItemUI itemUI = new ItemUI();
            itemUI.setId(item.getId());
            itemUI.setName(item.getName());
            itemUI.setPrice(item.getPrice());
            itemUI.setCategory(item.convertCategoryIdToCategory(categories));
            return itemUI;
        }).collect(Collectors.toList());
    }

    /** カテゴリー毎に金額を集計する */
    @Override
    public Map<Integer, Integer> totalAmountPerCategory(List<Item> items) {
        // itemsをカテゴリーIDごとに集計する。(key:categoryId | value:totalPrice)
        return items.stream().collect(Collectors.groupingBy(
                        Item::getCategoryId,
                Collectors.summingInt(Item::getPrice)
        ));
    }

    /** itemを削除する */
    @Override
    public void deleteItem(Integer id) {
        itemMapper.deleteItem(id);
    }

}
