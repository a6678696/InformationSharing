package com.ledao.controller.admin;

import com.ledao.entity.Link;
import com.ledao.entity.PageBean;
import com.ledao.service.LinkService;
import com.ledao.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理友情链接controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 12:40
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    /**
     * 根据条件分页查询友情链接
     *
     * @param link
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Link link, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("name", StringUtil.formatLike(link.getName()));
        map.put("sortBySortNum", 1);
        List<Link> linkList = linkService.list(map);
        Long total = linkService.getTotal(map);
        resultMap.put("rows", linkList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或修改友情链接
     *
     * @param link
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(Link link) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key;
        //id为空时,添加
        if (link.getId() == null) {
            key = linkService.add(link);
        } else {
            key = linkService.update(link);
        }
        //操作成功
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 删除友情链接(可批量删除)
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<>(16);
        String[] idsStr = ids.split(",");
        int deleteKey=0;
        for (int i = 0; i < idsStr.length; i++) {
            int id= Integer.parseInt(idsStr[i]);
            deleteKey = linkService.deleteById(id);
        }
        if (deleteKey>0) {
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
