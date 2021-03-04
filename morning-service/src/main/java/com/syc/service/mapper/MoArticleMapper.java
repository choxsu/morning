package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoArticle;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoArticle
 */
@Mapper
public interface MoArticleMapper extends BaseMapper<MoArticle> {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoArticle record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoArticle record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    MoArticle selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKeySelective(MoArticle record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKey(MoArticle record);
}