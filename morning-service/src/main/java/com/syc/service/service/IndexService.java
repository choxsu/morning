package com.syc.service.service;

import com.github.pagehelper.Page;
import com.syc.model.entity.jf.Blog;

import java.util.List;

public interface IndexService {

    /**
     * @param blog       用对象字段做筛选条件
     * @param pageNumber 当前页 默认第一页
     * @param paeSize    每页显示的条数
     * @return
     */
    Page findList(Blog blog, int pageNumber, int paeSize);

    /**
     * 获取制定条数博客
     *
     * @param blog  用对象字段做筛选条件
     * @param limit 限制的条数，最大1000条，默认100条
     * @return
     */
    List findList(Blog blog, int limit);

    /**
     * 查询一条博客数据
     *
     * @param id
     * @return
     */
    Blog findById(Integer id);

    /**
     * 保存
     *
     * @param blog 保存实体对象
     * @return
     */
    boolean save(Blog blog);


    /**
     * 更新
     *
     * @param blog 更新的实体对象
     * @return
     */
    boolean update(Blog blog);


    /**
     * 删除，这里只做物理删除
     * @param id
     * @return
     */
    boolean delete(Integer id);


}
