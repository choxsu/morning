package com.syc.service.mapper;

import com.syc.model.entity.BlogCategory;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.BlogCategory
 */
@Repository
public interface BlogCategoryMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(BlogCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(BlogCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    BlogCategory selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKeySelective(BlogCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKey(BlogCategory record);
}