package com.ledao.controller.admin;

import com.ledao.entity.Information;
import com.ledao.entity.PageBean;
import com.ledao.entity.User;
import com.ledao.service.InformationService;
import com.ledao.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台消息controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 14:09
 */
@RestController
@RequestMapping("/admin/information")
public class InformationAdminController {

    @Resource
    private InformationService informationService;

    @Resource
    private UserService userService;

    /**
     * 分条件分页查询消息
     *
     * @param information
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Information information, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("userId", information.getUserId());
        List<Information> informationList = informationService.list(map);
        for (Information information1 : informationList) {
            information1.setUser(userService.findById(information1.getUserId()));
        }
        Long total = informationService.getTotal(map);
        resultMap.put("rows", informationList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加消息
     *
     * @param information
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(Information information) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key = informationService.add(information);
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 删除消息(可批量删除)
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<>(16);
        String[] idsStr = ids.split(",");
        int key = 0;
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            key = informationService.deleteById(id);
        }
        if (key > 0) {
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
