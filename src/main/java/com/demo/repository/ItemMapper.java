package com.demo.repository;

import com.demo.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ItemMapper {

    /** 登録 */
    void insertItem(Item item);

    /** 対象月内の入力情報を取得 */
    List<Item> retrieveItemInTargetMonth(Date startDate, Date endDate);

}
