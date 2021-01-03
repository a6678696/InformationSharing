package com.ledao.mapper;

import com.ledao.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2021-01-03 23:38
 */
public interface ArticleMapper {

    /**
     * 条件查询
     *
     * @param map
     * @return
     */
    List<Article> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    Integer add(Article article);

    /**
     * 修改文章
     *
     * @param article
     * @return
     */
    Integer update(Article article);

    /**
     * 根据id删除文章
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
