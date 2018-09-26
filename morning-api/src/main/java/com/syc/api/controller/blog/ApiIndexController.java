package com.syc.api.controller.blog;

import com.github.pagehelper.Page;
import com.syc.api.controller.common.BaseApiController;
import com.syc.api.service.common.RedisService;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.model.result.Result;
import com.syc.service.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/index/api")
public class ApiIndexController extends BaseApiController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisService redisService;

    /**
     * @api {get} /index/api/v1/list #1、首页列表数据带分页
     * @apiName 首页列表
     * @apiGroup 首页相关接口
     * @apiDescription 首页列表数据带分页, 返回了所有的相关博客列表数据
     * @apiParam {String} token 登录token信息
     * @apiParam {String} tagId 标签id
     * @apiParam {String} category 博客分类str
     * @apiParam {int} pageNumber 当前第几页 默认第一页
     * @apiParam {int} pageSize 每页显示的条数 默认十条
     * @apiSuccess {int} code   提示代码 -1->权限不足 0->失败 1->成功
     * @apiSuccess {String} msg 提示信息
     * @apiSuccess {Array} data 对象信息
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
    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    public Result index(String token,
                        @RequestParam(value = "tagId", required = false) Integer tagId,
                        @RequestParam(value = "category", required = false) String category,
                        @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Blog blog = new Blog();
        if (tagId != null) {
            blog.setTagId(tagId);
        }
        if (StringUtils.hasText(category)) {
            blog.setCategory(category);
        }
        Page list;
        try {
            list = blogService.findList(blog, pageNumber, pageSize);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.fail().setMsg("系统错误:500");
        }
        return Result.ok().setData(list);
    }
}
