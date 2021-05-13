/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.api.service.article;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.syc.common.util.ResultModel;
import com.syc.model.request.ArticleRO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author choxsu
 * @date 2021/3/4
 * @describe
 */
@Service
public class ArticleApiService {


//    @Resource
//    private MoArticleMapper moArticleMapper;

    public ResultModel listByPage(Integer pageNumber, Integer pageSize, String title) {
//        IPage<MoArticle> iPage = moArticleMapper.selectPage(new Page<>(pageNumber, pageSize), Wrappers.lambdaQuery(MoArticle.class));
//        List<MoArticle> records = iPage.getRecords();
        String select  = "select *";
        String from = "from cms_article where 1 = 1 ";
        List<Object> params = new ArrayList<>();
        if(StrKit.notBlank(title)) {
            from += " and title like ? ";
            params.add("%" + title + "%");
        }
        from += " order by id desc";
        Page<Record> page = Db.paginate(pageNumber, pageSize, select, from, params.toArray());
        List<Record> list = page.getList();
        return ResultModel.success(ResultModel.SUCCESS_MSG, getBackPage(page.getTotalPage(), list));
    }

    public ResultModel save(ArticleRO articleRO) {
        Record article = new Record();
        article.set("account_id", 1);
        article.set("title", articleRO.getTitle());
        article.set("content_type", 1);
        article.set("content", articleRO.getContent());
        article.set("author", articleRO.getAuthor());
        article.set("status", articleRO.getStatus());
        article.set("category_id", articleRO.getCategoryId());
        article.set("create_at", LocalDateTime.now());
        Db.save("cms_article", article);
        return ResultModel.success();
    }

    public static Kv getBackPage(long total, List items) {
        List<Object> list = new ArrayList<>();
        for (Object item : items) {
            if (item instanceof Record) {
                Record record = (Record) item;
                Map<String, Object> columns = record.getColumns();
                list.add(columns);
            } else {
                list.add(item);
            }
        }
        return Kv.by("total", total).set("items", list);
    }
}
