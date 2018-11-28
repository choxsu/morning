package com.syc.service.service.impl;

import com.syc.service.dao.SensitiveWordsDao;
import com.syc.model.entity.mybatis.entity.SensitiveWords;
import com.syc.service.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SensitiveWords)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
@Service("sensitiveWordsService")
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
    @Resource
    private SensitiveWordsDao sensitiveWordsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SensitiveWords queryById(Integer id) {
        return this.sensitiveWordsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SensitiveWords> queryAllByLimit(int offset, int limit) {
        return this.sensitiveWordsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    @Override
    public SensitiveWords insert(SensitiveWords sensitiveWords) {
        this.sensitiveWordsDao.insert(sensitiveWords);
        return sensitiveWords;
    }

    /**
     * 修改数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    @Override
    public SensitiveWords update(SensitiveWords sensitiveWords) {
        this.sensitiveWordsDao.update(sensitiveWords);
        return this.queryById(sensitiveWords.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sensitiveWordsDao.deleteById(id) > 0;
    }
}