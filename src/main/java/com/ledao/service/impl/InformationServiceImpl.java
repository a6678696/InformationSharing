package com.ledao.service.impl;

import com.ledao.entity.Information;
import com.ledao.mapper.InformationMapper;
import com.ledao.service.InformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 消息Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2021-01-13 2:34
 */
@Service("informationService")
public class InformationServiceImpl implements InformationService {

    @Resource
    private InformationMapper informationMapper;

    @Override
    public List<Information> list(Map<String, Object> map) {
        return informationMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return informationMapper.getTotal(map);
    }

    @Override
    public Integer add(Information information) {
        return informationMapper.add(information);
    }

    @Override
    public Integer update(Information information) {
        return informationMapper.update(information);
    }
}
