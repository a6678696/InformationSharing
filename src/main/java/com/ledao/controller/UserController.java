package com.ledao.controller;

import com.ledao.entity.User;
import com.ledao.service.UserService;
import com.ledao.util.StringUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private UserService userService;

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
            resultMap.put("errorInfo", "邮件不不能为空！");
            return resultMap;
        }
        User u = userService.findByEmail(email);
        if (u == null) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "这个邮件不存在！");
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
