package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Blog)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = 551312832876964002L;
    //主键id
    private Integer id;
    //博客主id
    private Integer accountid;
    //标题
    private String title;
    //内容
    private Object content;
    //创建时间
    private Date createat;
    //修改时间
    private Date updateat;
    //点击次数
    private Integer clickcount;
    //喜欢次数
    private Integer likecount;
    //收藏次数
    private Integer favoritecount;
    //类型 note（笔记）favorite(收藏）code(代码）about(关于）
    private String category;
    //是否删除 0否1是
    private Integer isdelete;
    //tag_id
    private Integer tagId;
    //代码分类id，如果category为code时候，这个值才会生效
    private Integer categoryId;
    
    private Object markedcontent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getFavoritecount() {
        return favoritecount;
    }

    public void setFavoritecount(Integer favoritecount) {
        this.favoritecount = favoritecount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getMarkedcontent() {
        return markedcontent;
    }

    public void setMarkedcontent(Object markedcontent) {
        this.markedcontent = markedcontent;
    }

}