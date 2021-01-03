package com.ledao.controller;

import com.ledao.entity.ArticleType;
import com.ledao.service.ArticleTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView mav = new ModelAndView();
        List<ArticleType> articleTypeList = articleTypeService.list(null);
        mav.addObject("articleTypeList", articleTypeList);
        mav.addObject("title", "首页");
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }
}
