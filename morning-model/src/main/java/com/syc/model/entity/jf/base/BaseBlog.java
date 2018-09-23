package com.syc.model.entity.jf.base;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: blog
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseBlog<M extends Model<M>> extends Model<M> {

    public static final String tableName = "blog";

    /**
     * 主键id
     */
    private String id = "id";
    /**
     * 博客主id
     */
    private String accountId = "accountId";
    /**
     * 标题
     */
    private String title = "title";
    /**
     * 内容
     */
    private String content = "content";

	/**
	 * 待解析内容
	 */
	private String markedContent = "markedContent";
    /**
     * 创建时间
     */
    private String createAt = "createAt";
    /**
     * 修改时间
     */
    private String updateAt = "updateAt";
    /**
     * 点击次数
     */
    private String clickCount = "clickCount";
    /**
     * 喜欢次数
     */
    private String likeCount = "likeCount";
    /**
     * 收藏次数
     */
    private String favoriteCount = "favoriteCount";
    /**
     * 类型 note（笔记）favorite(收藏）code(代码）about(关于）
     */
    private String category = "category";
    /**
     * 是否删除 0否1是
     */
    private String isDelete = "isDelete";
    /**
     * tag_id
     */
    private String tagId = "tag_id";
    /**
     * 代码分类id，如果category为code时候，这个值才会生效
     */
    private String categoryId = "category_id";


    /**
     * set主键id
     */
	public void setId(Integer id) {
		set(this.id, id);
	}

    /**
     * get主键id
     */
	public Integer getId() {
		return getInt(id);
	}


    /**
     * set博客主id
     */
	public void setAccountId(Integer accountId) {
		set(this.accountId, accountId);
	}

    /**
     * get博客主id
     */
	public Integer getAccountId() {
		return getInt(accountId);
	}


    /**
     * set标题
     */
	public void setTitle(String title) {
		set(this.title, title);
	}

    /**
     * get标题
     */
	public String getTitle() {
		return getStr(title);
	}


    /**
     * set内容
     */
	public void setContent(String content) {
		set(this.content, content);
	}

    /**
     * get内容
     */
	public String getContent() {
		return getStr(content);
	}


    /**
     * set创建时间
     */
	public void setCreateAt(java.util.Date createAt) {
		set(this.createAt, createAt);
	}

    /**
     * get创建时间
     */
	public java.util.Date getCreateAt() {
		return get(createAt);
	}


    /**
     * set修改时间
     */
	public void setUpdateAt(java.util.Date updateAt) {
		set(this.updateAt, updateAt);
	}

    /**
     * get修改时间
     */
	public java.util.Date getUpdateAt() {
		return get(updateAt);
	}


    /**
     * set点击次数
     */
	public void setClickCount(Integer clickCount) {
		set(this.clickCount, clickCount);
	}

    /**
     * get点击次数
     */
	public Integer getClickCount() {
		return getInt(clickCount);
	}


    /**
     * set喜欢次数
     */
	public void setLikeCount(Integer likeCount) {
		set(this.likeCount, likeCount);
	}

    /**
     * get喜欢次数
     */
	public Integer getLikeCount() {
		return getInt(likeCount);
	}


    /**
     * set收藏次数
     */
	public void setFavoriteCount(Integer favoriteCount) {
		set(this.favoriteCount, favoriteCount);
	}

    /**
     * get收藏次数
     */
	public Integer getFavoriteCount() {
		return getInt(favoriteCount);
	}


    /**
     * set类型 note（笔记）favorite(收藏）code(代码）about(关于）
     */
	public void setCategory(String category) {
		set(this.category, category);
	}

    /**
     * get类型 note（笔记）favorite(收藏）code(代码）about(关于）
     */
	public String getCategory() {
		return getStr(category);
	}


    /**
     * set是否删除 0否1是
     */
	public void setIsDelete(Integer isDelete) {
		set(this.isDelete, isDelete);
	}

    /**
     * get是否删除 0否1是
     */
	public Integer getIsDelete() {
		return getInt(isDelete);
	}


    /**
     * settag_id
     */
	public void setTagId(Integer tagId) {
		set(this.tagId, tagId);
	}

    /**
     * gettag_id
     */
	public Integer getTagId() {
		return getInt(tagId);
	}


    /**
     * set代码分类id，如果category为code时候，这个值才会生效
     */
	public void setCategoryId(Integer categoryId) {
		set(this.categoryId, categoryId);
	}

    /**
     * get代码分类id，如果category为code时候，这个值才会生效
     */
	public Integer getCategoryId() {
		return getInt(categoryId);
	}

	/**
	 * get待解析内容
	 */
	public String getMarkedContent() {
		return getStr(markedContent);
	}

	/**
	 * set待解析内容
	 */
	public void setMarkedContent(String markedContent) {
		set(this.markedContent, markedContent);
	}
}
