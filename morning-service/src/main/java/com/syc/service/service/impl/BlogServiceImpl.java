package com.syc.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.syc.common.aop.JFinalTxAop;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.service.common.CommonService;
import com.syc.service.dao.BlogDao;
import com.syc.service.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service("blogService")
public class BlogServiceImpl extends CommonService implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public Page findList(Blog blog, String tagId, int pageNumber, int paeSize) {
        if (blog == null) {
            blog = new Blog();
        }
        List list = new ArrayList();
        if (StringUtils.hasLength(tagId)) {
            list = Arrays.asList(tagId.split(","));
        }
        blog.setIsdelete(0);
        Page<Blog> page = PageHelper.startPage(pageNumber, paeSize);

        blogDao.queryAll(blog, list);

        return page;
    }

    @Override
    public List findList(Blog blog, int limit) {
        return null;
    }

    @Override
    public Blog findById(Integer id) {
        Blog blog = blogDao.queryById(id);
        if (blog != null) {
            blog.setClickcount(blog.getClickcount() + 1);
            blogDao.updateClick(blog.getId());
        }
        return blog;
    }

    @Override
    public boolean save(Blog blog) {
        return false;
    }

    @Override
    public boolean update(Blog blog) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }


    @Transactional
    @Override
    public Ret txTest() {
        Date date = new Date();
        Record record = new Record();
        record.set("accountId", 1);
        record.set("title", "测试JFinal");
        record.set("content", "内容测试");
        record.set("createAt", date);
        record.set("updateAt", date);
        record.set("category", "note");
        Db.save("blog", record);

        Blog blog = new Blog();
        blog.setAccountid(1);
        blog.setTitle("测试Mybatis");
        blog.setContent("内容测试");
        blog.setCreateat(date);
        blog.setUpdateat(date);
        blog.setClickcount(0);
        blog.setLikecount(0);
        blog.setFavoritecount(0);
        blog.setIsdelete(0);
        blog.setCategory("note");
        blogDao.insert(blog);
        //手动回滚DEMO,这种回滚是无需抛出异常，就可以回滚
        if (JFinalTxAop.setRollbackOnly()){
            return Ret.fail("msg", "保存失败，数据被手动回滚！");
        }
        return Ret.ok("msg", "保存成功");
    }
}
