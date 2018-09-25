package com.syc.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.model.entity.mybatis.dao.BlogDao;
import com.syc.service.common.CommonService;
import com.syc.service.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl extends CommonService implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public Page findList(Blog blog, int pageNumber, int paeSize) {

        Page page = PageHelper.startPage(pageNumber, paeSize);
        if (blog == null){
            blog = new Blog();
        }
        blog.setIsdelete(0);
        blogDao.queryAll(blog);
        return page;
    }

    @Override
    public List findList(Blog blog, int limit) {
        return null;
    }

    @Override
    public Blog findById(Integer id) {
        return blogDao.queryById(id);
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
}
