package com.ledao.controller;

import com.ledao.entity.User;
import com.ledao.service.UserService;
import com.ledao.util.DateUtil;
import com.ledao.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-02 2:02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${userImageFilePath}")
    private String userImageFilePath;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * successLogin:true代表成功登录,false代表登录失败
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(User user, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User currentUser = userService.findByUserName(user.getUserName());
        //用户名不正确时
        if (currentUser == null) {
            mav.addObject("user", user);
            mav.addObject("successLogin", false);
            mav.addObject("title", "用户登录");
            mav.addObject("mainPage", "page/login");
            mav.addObject("mainPageKey", "#b");
            mav.setViewName("index");
            return mav;
        } else {
            //密码正确时
            if (currentUser.getPassword().equals(user.getPassword())) {
                session.setAttribute("currentUser", currentUser);
                mav.addObject("successLogin", true);
                mav.addObject("title", "首页");
                mav.addObject("mainPage", "page/indexFirst");
                mav.addObject("mainPageKey", "#b");
                mav.setViewName("index");
                return mav;
            } else {
                mav.addObject("user", user);
                mav.addObject("successLogin", false);
                mav.addObject("title", "用户登录");
                mav.addObject("mainPage", "page/login");
                mav.addObject("mainPageKey", "#b");
                mav.setViewName("index");
                return mav;
            }
        }
    }

    /**
     * 添加或修改用户信息
     *
     * @param user
     * @param file
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(User user, @RequestParam("userImage") MultipartFile file, HttpSession session) throws Exception {
        if (!file.isEmpty()) {
            if (user.getId() != null) {
                FileUtils.deleteQuietly(new File(userImageFilePath + userService.findById(user.getId()).getImageName()));
            }
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr2() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(userImageFilePath + newFileName));
            user.setImageName(newFileName);
        }
        if (user.getId() == null) {
            userService.add(user);
            ModelAndView mav = new ModelAndView("redirect:/toLoginPage");
            return mav;
        } else {
            userService.update(user);
            ModelAndView mav = new ModelAndView();
            mav.addObject("successModify", true);
            mav.addObject("title", "个人中心");
            mav.addObject("mainPage", "page/personMessage");
            user.setImageName(userService.findById(user.getId()).getImageName());
            session.setAttribute("currentUser", user);
            mav.addObject("mainPageKey", "#b");
            return mav;
        }
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/searchPassword")
    public ModelAndView searchPassword(User user,HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String mailCode= (String) session.getAttribute("mailCode");
        if (mailCode.equals(user.getCheckCode())) {
            //未修改密码前
            User oldUser = userService.findByEmail(user.getEmail());
            //获取新的密码
            oldUser.setPassword(user.getPassword());
            userService.update(oldUser);
            mav.addObject("title", "用户登录");
            mav.addObject("searchPasswordSuccess", true);
            mav.addObject("mainPage", "page/login");
        } else {
            mav.addObject("user", user);
            mav.addObject("title", "找回密码");
            mav.addObject("checkCodeFail", true);
            mav.addObject("mainPage", "page/searchPassword");
        }
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 用户注册时判断用户名是否已经存在
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/existUserWithUserName")
    public Map<String, Object> existUserWithUserName(String userName) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findByUserName(userName);
        if (user != null) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 判断数据库中邮箱是否存在
     *
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/existEmail")
    public Map<String, Object> existEmail(String email) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findByEmail(email);
        if (user != null) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 退出登录返回的页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "首页");
        mav.addObject("successLogout", true);
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 发送邮件
     *
     * @param email
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/sendEmail")
    public Map<String, Object> sendEmail(String email, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new HashMap<>(16);
        if (StringUtil.isEmpty(email)) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "邮箱不能为空！");
            return resultMap;
        }
        User u = userService.findByEmail(email);
        if (u == null) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "数据库中这个邮箱不存在！");
            return resultMap;
        }
        String mailCode = StringUtil.genSixRandomNum();
        System.out.println("mailCode:" + mailCode);
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人
        message.setFrom("1203007469@qq.com");
        //收件人
        message.setTo(email);
        // 主题
        message.setSubject("LeDao资源分享平台");
        //内容
        message.setText("您的验证码为：" + mailCode);
        javaMailSender.send(message);

        // 验证码存到session中
        session.setAttribute("mailCode", mailCode);
        session.setAttribute("userId", u.getId());
        resultMap.put("success", true);
        return resultMap;
    }
}
