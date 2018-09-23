package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: sensitive_words
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseSensitiveWords<M extends BaseSensitiveWords<M>> extends Model<M> {

    public static final String tableName = "sensitive_words";

    /**
     * 该字段暂无注释
     */
    private String id = "id";
    /**
     * 该字段暂无注释
     */
    private String word = "word";
    /**
     * 该字段暂无注释
     */
    private String status = "status";
    /**
     * 该字段暂无注释
     */
    private String wordPinyin = "word_pinyin";


    /**
     * 该字段暂无注释
     */
	public void setId(Integer id) {
		set(this.id, id);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getId() {
		return getInt(id);
	}


    /**
     * 该字段暂无注释
     */
	public void setWord(String word) {
		set(this.word, word);
	}

    /**
     * 该字段暂无注释
     */
	public String getWord() {
		return getStr(word);
	}


    /**
     * 该字段暂无注释
     */
	public void setStatus(Integer status) {
		set(this.status, status);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getStatus() {
		return getInt(status);
	}


    /**
     * 该字段暂无注释
     */
	public void setWordPinyin(String wordPinyin) {
		set(this.wordPinyin, wordPinyin);
	}

    /**
     * 该字段暂无注释
     */
	public String getWordPinyin() {
		return getStr(wordPinyin);
	}

}
