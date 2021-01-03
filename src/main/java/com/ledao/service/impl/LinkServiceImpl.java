package com.ledao.service.impl;

import com.ledao.entity.Link;
import com.ledao.mapper.LinkMapper;
import com.ledao.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2021-01-04 1:43
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public List<Link> list(Map<String, Object> map) {
        return linkMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return linkMapper.getTotal(map);
    }

    @Override
    public Integer add(Link link) {
        return linkMapper.add(link);
    }

    @Override
    public Integer update(Link link) {
        return linkMapper.update(link);
    }

    @Override
    public Integer deleteById(Integer id) {
        return linkMapper.deleteById(id);
    }
}
