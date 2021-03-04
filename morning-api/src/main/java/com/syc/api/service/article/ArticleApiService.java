/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.api.service.article;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.common.util.ResultModel;
import com.syc.model.entity.MoArticle;
import com.syc.service.mapper.MoArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author choxsu
 * @date 2021/3/4
 * @describe
 */
@Service
public class ArticleApiService {


    @Resource
    private MoArticleMapper moArticleMapper;

    public ResultModel listByPage(Integer pageNumber, Integer pageSize) {
        IPage<MoArticle> iPage = moArticleMapper.selectPage(new Page<>(pageNumber, pageSize), Wrappers.lambdaQuery(MoArticle.class));

        List<MoArticle> records = iPage.getRecords();

        return ResultModel.success(ResultModel.SUCCESS_MSG, records);
    }
}
