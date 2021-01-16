package com.ledao.controller;

import com.ledao.entity.Article;
import com.ledao.entity.Comment;
import com.ledao.entity.User;
import com.ledao.service.ArticleService;
import com.ledao.service.ArticleTypeService;
import com.ledao.service.CommentService;
import com.ledao.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-10 15:31
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private UserService userService;

    /**
     * 添加评论
     *
     * @param content
     * @param articleId
     * @param session
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView add(String content, Integer articleId, HttpSession session) {
        Comment comment = new Comment();
        User currentUser = (User) session.getAttribute("currentUser");
        comment.setUserId(currentUser.getId());
        comment.setArticleId(articleId);
        comment.setCommentContent(content);
        comment.setArticleAuthorId(articleService.findById(articleId).getUserId());
        commentService.add(comment);
        ModelAndView mav = new ModelAndView("redirect:/article/" + articleId);
        return mav;
    }

    /**
     * 根据id删除评论
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(Integer id) {
        commentService.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/toCommentManagePage");
        return mav;
    }
}
