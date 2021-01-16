package com.ledao.mapper;

import com.ledao.entity.Information;

import java.util.List;
import java.util.Map;

/**
 * 消息Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2021-01-13 2:24
 */
public interface InformationMapper {

    /**
     * 根据条件查询
     *
     * @param map
     * @return
     */
    List<Information> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 添加消息
     *
     * @param information
     * @return
     */
    Integer add(Information information);

    /**
     * 修改消息
     *
     * @param information
     * @return
     */
    Integer update(Information information);

    /**
     * 根据id删除消息
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
