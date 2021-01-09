package com.ledao.controller;

import com.ledao.entity.Article;
import com.ledao.entity.ArticleType;
import com.ledao.entity.Link;
import com.ledao.entity.User;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.service.LinkService;
import com.ledao.service.UserService;
import com.ledao.util.PageUtil;
import com.ledao.util.StringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
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
public class IndexController implements CommandLineRunner, ServletContextListener {

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private ServletContext application;
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
    public ModelAndView root(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "articleTypeId",required = false)String articleTypeId) {
        this.loadSomeData();
        ModelAndView mav = new ModelAndView();
        if (page == null) {
            page=1;
        }
        int pageSize=10;
        Map<String, Object> map=new HashMap<>(16);
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        map.put("state", 2);
        map.put("sortByPublishDate", 1);
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(articleTypeId)) {
            param.append("&articleTypeId=" + articleTypeId);
            map.put("articleTypeId", articleTypeId);
            mav.addObject("articleTypeId", articleTypeId);
        }
        List<Article> articleList = articleService.list(map);
        for (Article article : articleList) {
            article.setUser(userService.findById(article.getUserId()));
        }
        Long total = articleService.getTotal(map);
        mav.addObject("total", total);
        mav.addObject("pageCode", PageUtil.genPagination("/", total, page, pageSize, param.toString()));
        mav.addObject("articleList", articleList);
        mav.addObject("title", "首页");
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 加载首页数据
     */
    public void loadSomeData() {
        //文章类型列表
        List<ArticleType> articleTypeList = articleTypeService.list(null);
        Map<String, Object> map = new HashMap<>(16);
        map.put("isHot", 1);
        map.put("state", 2);
        map.put("sortByPublishDate", 1);
        //热门资源列表
        List<Article> articleListHot = articleService.list(map);
        map.put("sortBySortNum", 1);
        //友情链接列表
        List<Link> linkList = linkService.list(map);
        application.setAttribute("articleTypeList", articleTypeList);
        application.setAttribute("articleListHot", articleListHot);
        application.setAttribute("linkList", linkList);
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

    /**
     * 跳转到找回密码页面
     *
     * @return
     */
    @RequestMapping("/toSearchPasswordPage")
    public ModelAndView toSearchPasswordPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "找回密码");
        mav.addObject("mainPage", "page/searchPassword");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到用户后台页面
     *
     * @return
     */
    @RequestMapping("/toUserBackstagePage")
    public ModelAndView toUserBackstagePage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "用户后台");
        mav.addObject("mainPage", "page/userBackstage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到个人信息页面
     *
     * @return
     */
    @RequestMapping("/toPersonMessagePage")
    public ModelAndView toPersonMessagePage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "个人信息");
        mav.addObject("mainPage", "page/personMessage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到修改个人信息页面
     *
     * @return
     */
    @RequestMapping("/toPersonMessageUpdatePage")
    public ModelAndView toPersonMessageUpdatePage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "个人信息修改");
        mav.addObject("mainPage", "page/updatePersonMessage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到发布资源页面
     *
     * @return
     */
    @RequestMapping("/toWriteArticlePage")
    public ModelAndView toWriteArticlePage(){
        List<ArticleType> articleTypeList = articleTypeService.list(null);
        ModelAndView mav = new ModelAndView();
        mav.addObject("articleTypeList", articleTypeList);
        mav.addObject("title", "发布资源");
        mav.addObject("mainPage", "page/writeArticle");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到资源管理页面
     *
     * @return
     */
    @RequestMapping("/toArticleManagePage")
    public ModelAndView toArticleManagePage(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "state",required = false)Integer state,HttpSession session){
        if (page == null) {
            page=1;
        }
        int pageSize=7;
        Map<String, Object> map=new HashMap<>(16);
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("userId",currentUser.getId());
        map.put("sortByPublishDate",1);
        map.put("isUseful",1);
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(name)) {
            param.append("&name=" + name);
            map.put("name", StringUtil.formatLike(name));
        }
        if (state!=null) {
            param.append("&state=" + state);
            map.put("state", state);
        }
        Long total = articleService.getTotal(map);
        List<Article> articleList = articleService.list(map);
        for (Article article : articleList) {
            article.setArticleType(articleTypeService.findById(article.getArticleTypeId()));
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("articleSearchState", state);
        mav.addObject("articleList", articleList);
        mav.addObject("articleSearch", new Article(name, state));
        mav.addObject("pageCode", PageUtil.genPagination2("/toArticleManagePage", total, page, pageSize, param.toString()));
        mav.addObject("title", "资源管理");
        mav.addObject("mainPage", "page/articleManage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到修改资源页面
     *
     * @return
     */
    @RequestMapping("/toUpdateArticlePage")
    public ModelAndView toUpdateArticlePage(Integer id){
        List<ArticleType> articleTypeList = articleTypeService.list(null);
        ModelAndView mav = new ModelAndView();
        Article article = articleService.findById(id);
        mav.addObject("articleTypeIdUpdate", article.getArticleTypeId());
        mav.addObject("articleUpdatePoints", article.getPoints());
        mav.addObject("contentUpdate", article.getContent());
        mav.addObject("article", article);
        mav.addObject("articleTypeList", articleTypeList);
        mav.addObject("title", "修改资源");
        mav.addObject("mainPage", "page/updateArticle");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到失效资源管理页面
     *
     * @return
     */
    @RequestMapping("/toArticleFailureManagePage")
    public ModelAndView toArticleFailureManagePage(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "state",required = false)Integer state,HttpSession session){
        if (page == null) {
            page=1;
        }
        int pageSize=7;
        Map<String, Object> map=new HashMap<>(16);
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("userId",currentUser.getId());
        map.put("sortByPublishDate",1);
        map.put("isUseful",0);
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(name)) {
            param.append("&name=" + name);
            map.put("name", StringUtil.formatLike(name));
        }
        if (state!=null) {
            param.append("&state=" + state);
            map.put("state", state);
        }
        Long total = articleService.getTotal(map);
        List<Article> articleList = articleService.list(map);
        for (Article article : articleList) {
            article.setArticleType(articleTypeService.findById(article.getArticleTypeId()));
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("articleSearchState", state);
        mav.addObject("articleList", articleList);
        mav.addObject("articleSearch", new Article(name, state));
        mav.addObject("pageCode", PageUtil.genPagination2("/toArticleFailureManagePage", total, page, pageSize, param.toString()));
        mav.addObject("title", "失效资源管理");
        mav.addObject("mainPage", "page/FailureResourceManage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }
}
