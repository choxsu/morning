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
        //手动回滚DEMO,这种回滚是无需抛出异常，就可以回滚,调用了setRollbackOnly方法 记得return,不return的话 后面的业务也会被回滚
        if (JFinalTxAop.setRollbackOnly()) {
            return Ret.fail("msg", "保存失败，数据被手动回滚！");
        }
        return Ret.ok("msg", "保存成功");
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 3, 4, 1};
        int swap;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                //System.out.println("a[" + j + "]:" + a[j] + "|a[" + i + "]:" + a[i]);
                if (a[j] > a[i]) {
                    swap = a[i];
                    a[i] = a[j];
                    a[j] = swap;
                }
                //System.out.println(Arrays.toString(a));
            }
            //System.out.println("-----------------------------");
        }
        System.out.println(Arrays.toString(a));

        int[] b = {5, 2, 3, 4, 1};
        System.out.println(ef(b, 2));
    }

    /**
     * 二分查找
     java中有三种移位运算符
     <<      :     左移运算符，num << 1,相当于num乘以2
     >>      :     右移运算符，num >> 1,相当于num除以2
     >>>    :     无符号右移，忽略符号位，空位都以0补齐,可以防止溢出
     * @param a
     * @param des
     * @return
     */
    public static int ef(int[] a, int des) {
        //定义初始最小、最大索引
        int low = 0;
        int high = a.length - 1;
        //确保不会出现重复查找，越界
        while (low <= high) {
            //计算出中间索引值
            int middle = (high + low) >>> 1;//防止溢出
            if (des == a[middle]) {
                return middle;
                //判断下限
            } else if (des < a[middle]) {
                high = middle - 1;
                //判断上限
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
