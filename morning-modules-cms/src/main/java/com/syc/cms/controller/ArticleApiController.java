/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.cms.controller;


import com.syc.cms.domain.entity.CmsArticle;
import com.syc.cms.service.ArticleApiService;
import com.syc.modules.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xq.su
 */
@RestController
@RequestMapping("/cms/article")
public class ArticleApiController {

    @Autowired
    private ArticleApiService articleApiService;



    @GetMapping("/listByPage")
    public Result listByPage(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                             String title) {

        return articleApiService.listByPage(pageNumber, pageSize, title);
    }

    @PutMapping("/save")
    public Result save(@RequestBody @Validated CmsArticle article) {

        return articleApiService.save(article);
    }



}
