package com.demo.item.model;

import com.demo.category.model.Category;

import java.util.Date;
import java.util.List;

public class Item {
    /** ユーザID */
    private String userId;
    /** itemId */
    private Integer itemId;
    /** 名前 */
    private String name;
    /** 価格 */
    private Integer price;
    /** 対象日 */
    private Date targetDate;
    /** カテゴリーID */
    private Integer categoryId;
    /** 作成者 */
    private String createdBy;
    /** 作成日 */
    private Date createdDate;
    /** 更新者 */
    private String updatedBy;
    /** 更新日 */
    private Date updatedDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /** カテゴリIDをカテゴリに変換する */
    public String convertCategoryIdToCategory(List<Category> categories) {
        return categories.stream()
                .filter(e -> e.getCategoryId().equals(getCategoryId()))
                .map(Category::getName)
                .findFirst()
                .orElse("1");
    }

}
