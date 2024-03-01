package com.syc.cms.service;

import com.syc.cms.domain.entity.CmsArticle;
import com.syc.module.common.domain.R;

/**
 * @author xq.su
 * created on 2024/2/22
 */
public interface ArticleApiService {

    /**
     * 列表
     * @param pageNumber
     * @param pageSize
     * @param title
     * @return
     */
    R listByPage(Integer pageNumber, Integer pageSize, String title);

    /**
     * 保存
     * @param article
     * @return
     */
    R save(CmsArticle article);
}
