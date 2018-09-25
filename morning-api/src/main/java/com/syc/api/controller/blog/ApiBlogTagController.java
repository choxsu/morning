package com.syc.api.controller.blog;

import com.syc.api.controller.common.BaseApiController;
import com.syc.api.service.common.RedisService;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.model.entity.mybatis.entity.BlogTag;
import com.syc.model.result.Result;
import com.syc.service.service.BlogService;
import com.syc.service.service.BlogTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/blog/tag/api")
public class ApiBlogTagController extends BaseApiController {


    @Autowired
    private RedisService redisService;

    private final BlogTagService blogTagService;

    @Autowired
    public ApiBlogTagController(@Qualifier("blogTagService") BlogTagService blogTagService) {
        this.blogTagService = blogTagService;
    }

    /**
     * @api {get} /blog/tag/api/v1/list #2、博客标签列表
     * @apiName 博客标签列表
     * @apiGroup 博客相关接口
     * @apiDescription 博客相关接口， 博客标签列表
     * @apiParam {String} token 登录token信息
     * @apiSuccess {int} code   提示代码 -1->权限不足 0->失败 1->成功
     * @apiSuccess {String} msg 提示信息
     * @apiSuccess {Object} data 对象信息
     * @apiSuccess {int} data.id 主键id
     * @apiSuccess {String} data.name 标签名称
     */
    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    public Result detail(String token) {
        List<BlogTag> blogTags;
        final String tagListKey = "tag:tagList";
        try {
            Object o = redisService.get(tagListKey);
            if (o != null) {
                blogTags = (List<BlogTag>) o;
            } else {
                blogTags = blogTagService.queryAllByLimit(0, 100);
                redisService.set(tagListKey, blogTags);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.fail().setMsg("系统错误:500");
        }
        return Result.ok().setData(blogTags);
    }


}
