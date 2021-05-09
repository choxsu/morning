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
import com.syc.model.request.ArticleRO;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResultModel listByPage(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                  String title) {

        return articleApiService.listByPage(pageNumber, pageSize, title);
    }

    @PutMapping("/save")
    public ResultModel save(@RequestBody @Validated ArticleRO articleRO) {

        return articleApiService.save(articleRO);
    }



}
