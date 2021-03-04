/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.api.controller.article;

import com.jfinal.ext.cors.EnableCORS;
import com.syc.api.controller.common.BaseController;
import com.syc.api.service.article.ArticleApiService;
import com.syc.common.util.ResultModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author choxsu
 * @date 2021/3/4
 * @describe
 */
@RestController
@RequestMapping("/api/article")
public class ArticleApiController extends BaseController {

    @Resource
    private ArticleApiService articleApiService;


    @GetMapping("/listByPage")
    public ResultModel listByPage(Integer pageNumber, Integer pageSize) {

        return articleApiService.listByPage(pageNumber, pageSize);
    }



}
