package com.ledao.controller;

import com.ledao.entity.Article;
import com.ledao.entity.ArticleType;
import com.ledao.entity.Link;
import com.ledao.entity.User;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.service.LinkService;
import com.ledao.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-03 21:50
 */
@Controller
public class IndexController {

    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private LinkService linkService;

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView mav = new ModelAndView();
        //文章类型列表
        List<ArticleType> articleTypeList = articleTypeService.list(null);
        Map<String, Object> map = new HashMap<>(16);
        map.put("sortByPublishDate", 1);
        //资源列表
        List<Article> articleList = articleService.list(map);
        for (Article article : articleList) {
            article.setUser(userService.findById(article.getUserId()));
        }
        map.put("isHot", 1);
        //热门资源列表
        List<Article> articleListHot = articleService.list(map);
        map.put("sortBySortNum", 1);
        //友情链接列表
        List<Link> linkList = linkService.list(map);
        mav.addObject("articleTypeList", articleTypeList);
        mav.addObject("articleList", articleList);
        mav.addObject("articleListHot", articleListHot);
        mav.addObject("linkList", linkList);
        mav.addObject("title", "首页");
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到用户登录页面
     *
     * @return
     */
    @RequestMapping("/toLoginPage")
    public ModelAndView toLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "用户登录");
        mav.addObject("mainPage", "page/login");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到用户注册页面
     *
     * @return
     */
    @RequestMapping("/toRegisterPage")
    public ModelAndView toRegisterPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "用户注册");
        mav.addObject("mainPage", "page/register");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }
}
