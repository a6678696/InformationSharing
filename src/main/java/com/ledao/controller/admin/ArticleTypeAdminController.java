package com.ledao.controller.admin;

import com.ledao.entity.ArticleType;
import com.ledao.entity.PageBean;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台资源类型Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 21:15
 */
@RestController
@RequestMapping("/admin/articleType")
public class ArticleTypeAdminController {

    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private ArticleService articleService;

    /**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     */
    @RequestMapping("/comboList")
    public List<ArticleType> comboList(String q) {
        if (q == null) {
            q = "";
        }
        List<ArticleType> articleTypeList = articleTypeService.findByName(StringUtil.formatLike(q));
        return articleTypeList;
    }

    /**
     * 分页分条件查询资源类型
     *
     * @param articleType
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(ArticleType articleType, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("sortBySortNum", 1);
        map.put("name", StringUtil.formatLike(articleType.getName()));
        List<ArticleType> articleTypeList = articleTypeService.list(map);
        for (ArticleType type : articleTypeList) {
            Map<String, Object> map2 = new HashMap<>(16);
            map2.put("articleTypeId", type.getId());
            type.setNum(articleService.list(map2).size());
        }
        Long total = articleTypeService.getTotal(map);
        resultMap.put("rows", articleTypeList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或修改资源类型
     *
     * @param articleType
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(ArticleType articleType) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key;
        if (articleType.getId() == null) {
            key = articleTypeService.add(articleType);
        } else {
            key = articleTypeService.update(articleType);
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 删除资源类型(可批量删除)
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key = 0;
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            Map<String, Object> map = new HashMap<>(16);
            map.put("articleTypeId", id);
            if (articleService.list(map).size() > 0) {
                resultMap.put("success", false);
                resultMap.put("errorInfo", "该资源类别下有资源,不能删除,删除前请先删除它的资源!");
            } else {
                key = articleTypeService.deleteById(id);
                resultMap.put("success", true);
            }
        }
        if (key > 0) {
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
