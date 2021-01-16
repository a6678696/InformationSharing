package com.ledao.service.impl;

import com.ledao.entity.DownloadMessage;
import com.ledao.mapper.DownloadMessageMapper;
import com.ledao.service.DownloadMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 下载信息Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2021-01-10 20:39
 */
@Service("downloadMessageService")
public class DownloadMessageServiceImpl implements DownloadMessageService {

    @Resource
    private DownloadMessageMapper downloadMessageMapper;

    @Override
    public List<DownloadMessage> list(Map<String, Object> map) {
        return downloadMessageMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return downloadMessageMapper.getTotal(map);
    }

    @Override
    public Integer add(DownloadMessage downloadMessage) {
        return downloadMessageMapper.add(downloadMessage);
    }

    @Override
    public Integer deleteById(Integer id) {
        return downloadMessageMapper.deleteById(id);
    }
}
