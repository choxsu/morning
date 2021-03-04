package com.syc.service.mapper;

import com.syc.model.entity.BlogTag;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.BlogTag
 */
@Repository
public interface BlogTagMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(BlogTag record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(BlogTag record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    BlogTag selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKeySelective(BlogTag record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKey(BlogTag record);
}