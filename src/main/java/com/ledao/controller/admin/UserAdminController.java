package com.ledao.controller.admin;

import com.ledao.entity.PageBean;
import com.ledao.entity.User;
import com.ledao.service.UserService;
import com.ledao.util.MyEncryption;
import com.ledao.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 后台用户controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-02 2:02
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    /**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     */
    @RequestMapping("/comboList")
    public List<User> comboList(String q) {
        if (q == null) {
            q = "";
        }
        String removeUserName = "admin";
        List<User> userList = userService.findByName(StringUtil.formatLike(q));
        Iterator iterator = userList.iterator();
        if (iterator.hasNext()) {
            User user = (User) iterator.next();
            if (removeUserName.equals(user.getUserName())) {
                iterator.remove();
            }
        }
        return userList;
    }

    /**
     * 根据条件分页条件查询用户
     *
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(User user, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        PageBean pageBean = new PageBean(page, rows);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("userName", StringUtil.formatLike(user.getUserName()));
        map.put("email", StringUtil.formatLike(user.getEmail()));
        map.put("roleName", user.getRoleName());
        map.put("isOff", user.getIsOff());
        map.put("key", 1);
        List<User> userList = userService.list(map);
        for (User user1 : userList) {
            user1.setPassword(MyEncryption.jiemi(user1.getPassword()));
        }
        Long total = userService.getTotal(map);
        resultMap.put("rows", userList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(User user) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user1 = userService.findByEmail(user.getEmail());
        //当id为空时,添加用户
        if (user.getId() == null) {
            //邮箱被注册
            if (user1 != null) {
                resultMap.put("success", false);
                resultMap.put("errorInfo", "该邮箱已被注册,请重新输入!!");
            } else {
                //密码和确认密码不一致
                if (!user.getPassword().equals(user.getPassword2())) {
                    resultMap.put("success", false);
                    resultMap.put("errorInfo", "密码和确认密码不一样,请重新输入!!");
                } else {
                    User user2 = userService.findByUserName(user.getUserName());
                    //用户名被注册
                    if (user2 != null) {
                        resultMap.put("success", false);
                        resultMap.put("errorInfo", "该用户名已被注册,请重新输入!!");
                    } else {
                        resultMap.put("success", true);
                        user.setPassword(MyEncryption.jiami(user.getPassword()));
                        userService.add(user);
                    }
                }
            }
        } else {
            //要修改的用户原来的信息
            User user3 = userService.findById(user.getId());
            //邮箱被注册并且修改后的邮箱和之前的邮箱不相同
            if (user1 != null && (!user.getEmail().equals(user3.getEmail()))) {
                resultMap.put("success", false);
                resultMap.put("errorInfo", "该邮箱已被注册,请重新输入!!");
            } else {
                //密码和确认密码不一致
                if (!user.getPassword().equals(user.getPassword2())) {
                    resultMap.put("success", false);
                    resultMap.put("errorInfo", "密码和确认密码不一样,请重新输入!!");
                } else {
                    resultMap.put("success", true);
                    user.setPassword(MyEncryption.jiami(user.getPassword()));
                    userService.update(user);
                }
            }
        }
        return resultMap;
    }

    /**
     * 根据id删除用户,可批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<>(16);
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            userService.deleteById(id);
        }
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 修改账号状态(封禁或解封)
     *
     * @param id
     * @param isOff
     * @return
     */
    @RequestMapping("/modifyOff")
    public Map<String, Object> modifyOff(Integer id, Integer isOff) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findById(id);
        user.setIsOff(isOff);
        userService.update(user);
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 修改用户类型(普通用户或VIP用户)
     *
     * @param id
     * @param roleName
     * @return
     */
    @RequestMapping("/modifyRoleType")
    public Map<String, Object> modifyRoleType(Integer id, String roleName) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findById(id);
        user.setRoleName(roleName);
        userService.update(user);
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 给用户加或减积分
     *
     * @param id
     * @param status 1代表加积分,2代表减积分
     * @param points
     * @return
     */
    @RequestMapping("/addOrReducePoints")
    public Map<String, Object> addOrReducePoints(Integer id, Integer status, Integer points) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findById(id);
        //加积分
        if (status == 1) {
            user.setPoints(user.getPoints() + points);
            resultMap.put("success", true);
        } else {
            //用户当前剩余积分大于要扣除的积分
            if (user.getPoints() >= points) {
                user.setPoints(user.getPoints() - points);
                resultMap.put("success", true);
            } else {
                resultMap.put("success", false);
                resultMap.put("errorInfo", "用户当前剩余积分少于要扣除的积分,不能扣除积分!");
            }
        }
        userService.update(user);
        return resultMap;
    }
}
