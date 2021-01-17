package com.ledao.controller;

import com.ledao.entity.*;
import com.ledao.service.*;
import com.ledao.util.PageUtil;
import com.ledao.util.StringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Resource
    private CommentService commentService;

    @Resource
    private DownloadMessageService downloadMessageService;

    @Resource
    private InformationService informationService;

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "articleTypeId", required = false) String articleTypeId) {
        this.loadSomeData();
        ModelAndView mav = new ModelAndView();
        if (page == null) {
            page = 1;
        }
        int pageSize = 10;
        Map<String, Object> map = new HashMap<>(16);
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
        Map<String, Object> map = new HashMap<>(16);
        map.put("isHot", 1);
        map.put("state", 2);
        map.put("sortByPublishDate", 1);
        //热门资源列表
        List<Article> articleListHot = articleService.list(map);
        map.put("sortBySortNum", 1);
        List<ArticleType> articleTypeList = articleTypeService.list(map);
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
    public ModelAndView toLoginPage(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            ModelAndView mav = new ModelAndView("redirect:/");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("title", "用户登录");
            mav.addObject("mainPage", "page/login");
            mav.addObject("mainPageKey", "#b");
            mav.setViewName("index");
            return mav;
        }
    }

    /**
     * 跳转到用户注册页面
     *
     * @return
     */
    @RequestMapping("/toRegisterPage")
    public ModelAndView toRegisterPage(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            ModelAndView mav = new ModelAndView("redirect:/");
            return mav;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("title", "用户注册");
            mav.addObject("mainPage", "page/register");
            mav.addObject("mainPageKey", "#b");
            mav.setViewName("index");
            return mav;
        }
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
    public ModelAndView toUserBackstagePage() {
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
    public ModelAndView toPersonMessagePage() {
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
    public ModelAndView toPersonMessageUpdatePage() {
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
    public ModelAndView toWriteArticlePage() {
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
    public ModelAndView toArticleManagePage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "state", required = false) Integer state, HttpSession session) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 7;
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("userId", currentUser.getId());
        map.put("sortByPublishDate", 1);
        map.put("isUseful", 1);
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(name)) {
            param.append("&name=" + name);
            map.put("name", StringUtil.formatLike(name));
        }
        if (state != null) {
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
    public ModelAndView toUpdateArticlePage(Integer id) {
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
    public ModelAndView toArticleFailureManagePage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "state", required = false) Integer state, HttpSession session) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 7;
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("userId", currentUser.getId());
        map.put("sortByPublishDate", 1);
        map.put("isUseful", 0);
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(name)) {
            param.append("&name=" + name);
            map.put("name", StringUtil.formatLike(name));
        }
        if (state != null) {
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

    /**
     * 跳转到评论管理页面
     *
     * @return
     */
    @RequestMapping("/toCommentManagePage")
    public ModelAndView toCommentManagePage(@RequestParam(value = "page", required = false) Integer page, HttpSession session) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 10;
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>(16);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("articleAuthorId", currentUser.getId());
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        List<Comment> commentList = commentService.list(map);
        for (Comment comment : commentList) {
            comment.setArticle(articleService.findById(comment.getArticleId()));
            comment.setUser(userService.findById(comment.getUserId()));
        }
        Long total = commentService.getTotal(map);
        mav.addObject("pageCode", PageUtil.genPagination2("/toCommentManagePage", total, page, pageSize, "&"));
        mav.addObject("commentList", commentList);
        mav.addObject("title", "评论管理");
        mav.addObject("mainPage", "page/commentManage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/toDownloadLink")
    public ModelAndView toDownloadLink(Integer articleId, HttpSession session) {
        Article article = articleService.findById(articleId);
        User currentUser = (User) session.getAttribute("currentUser");
        User author = userService.findById(article.getUserId());
        String roleName = "普通用户";
        Map<String, Object> map = new HashMap<>(16);
        map.put("articleId", articleId);
        map.put("userId", currentUser.getId());
        Long total = downloadMessageService.getTotal(map);
        //用户没下载过这个资源
        if (total == 0) {
            if (currentUser.getRoleName().equals(roleName)) {
                //下载者积分充足
                if (currentUser.getPoints() >= article.getPoints()) {
                    //下载者减去积分
                    currentUser.setPoints(currentUser.getPoints() - article.getPoints());
                    userService.update(currentUser);
                    session.setAttribute("currentUser", userService.findById(currentUser.getId()));
                    //资源作者获得积分
                    author.setPoints(author.getPoints() + article.getPoints());
                    userService.update(author);
                    DownloadMessage downloadMessage = new DownloadMessage();
                    downloadMessage.setArticleId(articleId);
                    downloadMessage.setMessage("下载了：" + article.getName());
                    downloadMessage.setUserId(currentUser.getId());
                    downloadMessageService.add(downloadMessage);
                    ModelAndView mav = new ModelAndView();
                    mav.addObject("article", article);
                    mav.addObject("title", "资源分享链接(" + article.getName() + ")");
                    mav.addObject("mainPage", "page/downloadLink");
                    mav.addObject("mainPageKey", "#b");
                    mav.setViewName("index");
                    return mav;
                } else {
                    ModelAndView mav = new ModelAndView("redirect:/article/" + articleId);
                    return mav;
                }
            } else {
                //资源被VIP用户下载只能获得一半的积分
                author.setPoints(author.getPoints() + article.getPoints() / 2);
                userService.update(author);
                DownloadMessage downloadMessage = new DownloadMessage();
                downloadMessage.setArticleId(articleId);
                downloadMessage.setMessage("下载了：" + article.getName());
                downloadMessage.setUserId(currentUser.getId());
                downloadMessageService.add(downloadMessage);
                ModelAndView mav = new ModelAndView();
                mav.addObject("article", article);
                mav.addObject("title", "资源分享链接(" + article.getName() + ")");
                mav.addObject("mainPage", "page/downloadLink");
                mav.addObject("mainPageKey", "#b");
                mav.setViewName("index");
                return mav;
            }
        } else {
            DownloadMessage downloadMessage = new DownloadMessage();
            downloadMessage.setArticleId(articleId);
            downloadMessage.setMessage("下载了：" + article.getName());
            downloadMessage.setUserId(currentUser.getId());
            downloadMessageService.add(downloadMessage);
            ModelAndView mav = new ModelAndView();
            mav.addObject("article", article);
            mav.addObject("title", "资源分享链接(" + article.getName() + ")");
            mav.addObject("mainPage", "page/downloadLink");
            mav.addObject("mainPageKey", "#b");
            mav.setViewName("index");
            return mav;
        }
    }

    /**
     * 用户下载资源时判断积分是否足够
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkPoints")
    public Map<String, Object> checkPoints(Integer articleId, HttpSession session) {
        String roleNameVIP = "VIP用户";
        Article article = articleService.findById(articleId);
        User currentUser = (User) session.getAttribute("currentUser");
        Map<String, Object> resultMap = new HashMap<>(16);
        if (currentUser.getPoints() >= article.getPoints()) {
            resultMap.put("success", true);
        } else {
            if (currentUser.getRoleName().equals(roleNameVIP)) {
                resultMap.put("success", true);
            } else {
                resultMap.put("success", false);
            }
        }
        return resultMap;
    }

    /**
     * 用户是否下载过资源
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkIsDownload")
    public Map<String, Object> checkIsDownload(Integer articleId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        Map<String, Object> map = new HashMap<>(16);
        map.put("articleId", articleId);
        map.put("userId", currentUser.getId());
        Long total = downloadMessageService.getTotal(map);
        Map<String, Object> resultMap = new HashMap<>(16);
        if (total > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 跳转到查看我的下载页面
     *
     * @return
     */
    @RequestMapping("/toMyDownloadPage")
    public ModelAndView toMyDownloadPage(@RequestParam(value = "page", required = false) Integer page, HttpSession session) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 10;
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>(16);
        User currentUser = (User) session.getAttribute("currentUser");
        map.put("userId", currentUser.getId());
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        List<DownloadMessage> downloadMessageList = downloadMessageService.list(map);
        for (DownloadMessage downloadMessage : downloadMessageList) {
            downloadMessage.setArticle(articleService.findById(downloadMessage.getArticleId()));
        }
        Long total = downloadMessageService.getTotal(map);
        mav.addObject("pageCode", PageUtil.genPagination2("/toMyDownloadPage", total, page, pageSize, "&"));
        mav.addObject("downloadMessageList", downloadMessageList);
        mav.addObject("title", "已下载资源");
        mav.addObject("mainPage", "page/myDownload");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到系统消息页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/toInformationPage")
    public ModelAndView toInformationPage(@RequestParam(value = "page", required = false) Integer page, HttpSession session) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 10;
        User currentUser = (User) session.getAttribute("currentUser");
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", currentUser.getId());
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        List<Information> informationList = informationService.list(map);
        Long total = informationService.getTotal(map);
        ModelAndView mav = new ModelAndView();
        mav.addObject("informationList", informationList);
        mav.addObject("pageCode", PageUtil.genPagination2("/toInformationPage", total, page, pageSize, "&"));
        mav.addObject("title", "系统消息");
        mav.addObject("mainPage", "page/myInformation");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 登录请求
     *
     * @return
     */
    @RequestMapping("/login")
    public Object login(HttpSession session) {
        User user = userService.findByUserName("admin");
        session.setAttribute("currentUserNickName", user.getNickName());
        return "/login";
    }

    /**
     * 进入后台管理请求
     *
     * @return
     */
    @RequestMapping("/admin")
    public String toAdmin() {
        return "/admin/main";
    }

    /**
     * 验证码是否正确
     *
     * @param imageCode
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkCodeIsSuccess")
    public Map<String, Object> checkCodeIsSuccess(String imageCode, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>(16);
        String checkCode = (String) session.getAttribute("checkCode");
        resultMap.put("checkCode", checkCode);
        if (StringUtil.isNotEmpty(checkCode)) {
            if (imageCode.equals(checkCode)) {
                resultMap.put("success", true);
            } else {
                resultMap.put("success", false);
            }
        }
        return resultMap;
    }

    /**
     * 跳转到源码下载页面
     *
     * @return
     */
    @RequestMapping("/toDownloadCodePage")
    public ModelAndView toDownloadCodePage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "源码下载");
        mav.addObject("mainPage", "page/downloadCode");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }
}
