package com.ledao.mapper;

import com.ledao.entity.DownloadMessage;

import java.util.List;
import java.util.Map;

/**
 * 下载信息Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2021-01-10 20:32
 */
public interface DownloadMessageMapper {

    /**
     * 根据条件查询
     *
     * @param map
     * @return
     */
    List<DownloadMessage> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 添加下载信息
     *
     * @param downloadMessage
     * @return
     */
    Integer add(DownloadMessage downloadMessage);
}
