package com.ledao.mapper;

import com.ledao.entity.ArticleType;

import java.util.List;
import java.util.Map;

/**
 * 文章类型Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2021-01-03 21:59
 */
public interface ArticleTypeMapper {

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
     * 根据id查询文章类型
     *
     * @param id
     * @return
     */
    ArticleType findById(Integer id);

    /**
     * 根据id删除文章类型
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
