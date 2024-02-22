package com.syc.cms.service.impl;

import com.syc.cms.domain.entity.CmsArticle;
import com.syc.cms.service.ArticleApiService;
import com.syc.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xq.su
 * created on 2024/2/22
 */
@Service
@Slf4j
public class ArticleApiServiceImpl implements ArticleApiService {


    /**
     * 列表
     *
     * @param pageNumber
     * @param pageSize
     * @param title
     * @return
     */
    @Override
    public R listByPage(Integer pageNumber, Integer pageSize, String title) {
        return null;
    }

    /**
     * 保存
     *
     * @param article
     * @return
     */
    @Override
    public R save(CmsArticle article) {
        return null;
    }
}
