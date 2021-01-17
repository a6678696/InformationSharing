package com.ledao.controller.admin;

import com.ledao.entity.Comment;
import com.ledao.entity.PageBean;
import com.ledao.service.ArticleService;
import com.ledao.service.CommentService;
import com.ledao.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台评论Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 20:27
 */
@RestController
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    /**
     * 分页分条件查询评论
     *
     * @param comment
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Comment comment, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("userId", comment.getUserId());
        map.put("state", comment.getState());
        List<Comment> commentList = commentService.list(map);
        for (Comment comment1 : commentList) {
            comment1.setUser(userService.findById(comment1.getUserId()));
            comment1.setArticle(articleService.findById(comment1.getArticleId()));
        }
        Long total = commentService.getTotal(map);
        resultMap.put("rows", commentList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除评论(可批量删除)
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
            key = commentService.deleteById(id);
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 审核通过评论
     *
     * @param id
     * @return
     */
    @RequestMapping("/passComment")
    public Map<String, Object> passComment(Integer id) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Comment comment = commentService.findById(id);
        if (comment.getState() == 1) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "该评论本来已经是审核通过了,无需操作!");
        } else {
            comment.setState(1);
            commentService.update(comment);
            resultMap.put("success", true);
        }
        return resultMap;
    }

    /**
     * 审核通过评论
     *
     * @param id
     * @return
     */
    @RequestMapping("/failComment")
    public Map<String, Object> failComment(Integer id) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Comment comment = commentService.findById(id);
        if (comment.getState() == 2) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "该评论本来已经是审核不通过了,无需操作!");
        } else {
            comment.setState(2);
            commentService.update(comment);
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
