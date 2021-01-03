package com.ledao.service;

import com.ledao.entity.ArticleType;

import java.util.List;
import java.util.Map;

/**
 * 文章类型Service接口
 *
 * @author LeDao
 * @company
 * @create 2021-01-03 22:12
 */
public interface ArticleTypeService {

    /**
     * 条件查询文章类型
     *
     * @param map
     * @return
     */
    List<ArticleType> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 添加文章类型
     *
     * @param articleType
     * @return
     */
    Integer add(ArticleType articleType);

    /**
     * 修改文章类型
     *
     * @param articleType
     * @return
     */
    Integer update(ArticleType articleType);

    /**
     * 根据id删除文章类型
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
