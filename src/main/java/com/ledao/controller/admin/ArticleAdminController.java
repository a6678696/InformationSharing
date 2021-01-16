package com.ledao.controller.admin;

import com.ledao.entity.Article;
import com.ledao.entity.ArticleType;
import com.ledao.entity.PageBean;
import com.ledao.entity.User;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.service.UserService;
import com.ledao.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 后台资源Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 22:03
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleAdminController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private UserService userService;

    /**
     * 分页分条件查询文章
     *
     * @param article
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Article article, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("sortByPublishDate", 1);
        map.put("name", StringUtil.formatLike(article.getName()));
        map.put("userId", article.getUserId());
        map.put("articleTypeId", article.getArticleTypeId());
        map.put("state", article.getState());
        map.put("isUseful", article.getIsUseful());
        List<Article> articleList = articleService.list(map);
        for (Article article1 : articleList) {
            article1.setArticleType(articleTypeService.findById(article1.getArticleTypeId()));
            article1.setUser(userService.findById(article1.getUserId()));
        }
        Long total = articleService.getTotal(map);
        resultMap.put("rows", articleList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 审核通过资源
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/pass")
    public Map<String, Object> pass(Integer articleId) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Article article = articleService.findById(articleId);
        //资源本来已经审核通过
        if (article.getState() == 2) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "这个资源本来已经审核通过,无需操作!");
        } else {
            article.setState(2);
            article.setCheckDate(new Date());
            article.setReason("无");
            articleService.update(article);
            resultMap.put("success", true);
        }
        return resultMap;
    }

    /**
     * 审核通过资源
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/fail")
    public Map<String, Object> fail(Integer articleId,String reason) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Article article = articleService.findById(articleId);
        //资源本来已经是审核不通过
        if (article.getState() == 3) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "这个资源本来是审核不通过,无需操作!");
        } else {
            article.setState(3);
            article.setCheckDate(new Date());
            article.setReason(reason);
            articleService.update(article);
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
