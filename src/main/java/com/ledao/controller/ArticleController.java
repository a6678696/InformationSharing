package com.ledao.controller;

import com.ledao.entity.Article;
import com.ledao.entity.ArticleType;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台文章Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-07 20:11
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Value("${articleImageFilePath}")
    private String articleImageFilePath;

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTypeService articleTypeService;

    /**
     * 添加或修改资源
     *
     * @param article
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(Article article) {
        if (article.getId() == null) {
            ModelAndView mav = new ModelAndView();
            articleService.add(article);
            List<ArticleType> articleTypeList = articleTypeService.list(null);
            mav.addObject("writeArticleSuccess", true);
            mav.addObject("articleTypeList", articleTypeList);
            mav.addObject("title", "发布资源");
            mav.addObject("mainPage", "page/writeArticle");
            mav.addObject("mainPageKey", "#b");
            mav.setViewName("index");
            return mav;
        } else {
            article.setState(1);
            articleService.update(article);
            ModelAndView mav = new ModelAndView("redirect:/toArticleManagePage");
            return mav;
        }
    }

    /**
     * 根据id删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(Integer id) {
        articleService.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/toArticleManagePage");
        return mav;
    }

    /**
     * ckeditor上传图片
     *
     * @param file
     * @param CKEditorFuncNum
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = DateUtil.getCurrentDateStr2() + suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(articleImageFilePath + newFileName));

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/static/images/articleImage/" + newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }
}
