package com.syc.service.mapper;

import com.syc.model.entity.Blog;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.Blog
 */
@Repository
public interface BlogMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(Blog record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(Blog record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    Blog selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKeySelective(Blog record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKey(Blog record);
}