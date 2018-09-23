package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * (SensitiveWords)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class SensitiveWords implements Serializable {
    private static final long serialVersionUID = 179856076235399127L;
    
    private Integer id;
    
    private String word;
    
    private Object status;
    
    private String wordPinyin;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getWordPinyin() {
        return wordPinyin;
    }

    public void setWordPinyin(String wordPinyin) {
        this.wordPinyin = wordPinyin;
    }

}