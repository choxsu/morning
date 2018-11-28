package com.syc.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.syc.service.dao.BlogDao;
import com.syc.model.entity.mybatis.entity.Blog;
import com.syc.service.common.CommonService;
import com.syc.service.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
}
