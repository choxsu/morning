package com.syc.api.controller.blog;

import com.syc.api.controller.common.BaseApiController;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.model.result.Result;
import com.syc.service.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/blog/api")
public class ApiBlogController extends BaseApiController {


    private final BlogService blogService;

    @Autowired
    public ApiBlogController(@Qualifier("blogService") BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * @api {get} /blog/api/v1/detail/:id #1、博客详情
     * @apiName 博客详情
     * @apiGroup 博客相关接口
     * @apiDescription 博客相关接口
     * @apiParam {String} token 登录token信息
     * @apiParam {int} id 列表id
     *
     * @apiSuccess {int} code   提示代码 -1->权限不足 0->失败 1->成功
     * @apiSuccess {String} msg 提示信息
     * @apiSuccess {Object} data 对象信息
     * @apiSuccess {int} data.id 主键id
     * @apiSuccess {int} data.accountid 博主id
     * @apiSuccess {String} data.title 标题
     * @apiSuccess {String} data.content html内容
     * @apiSuccess {String} data.createat 创建时间
     * @apiSuccess {String} data.updateat 更新时间
     * @apiSuccess {int} data.clickcount 点击数
     * @apiSuccess {int} data.likecount 收藏数
     * @apiSuccess {int} data.favoritecount 收藏数
     * @apiSuccess {String} data.category 分类名称
     * @apiSuccess {int} data.isdelete 是否删除 0否 1是
     * @apiSuccess {int} data.tagId 标签id
     * @apiSuccess {int} data.categoryId 分类id，暂时弃用
     * @apiSuccess {String} data.markedcontent markdown内容
     */
    @RequestMapping(value = "/v1/detail/{id}", method = RequestMethod.GET)
    public Result detail(String token, @PathVariable("id") Integer id) {
        Blog blog;
        try {
            blog = blogService.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.fail().setMsg("系统错误:500");
        }
        return Result.ok().setData(blog);
    }




}
