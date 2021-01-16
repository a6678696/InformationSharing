package com.ledao.controller.admin;

import com.ledao.entity.DownloadMessage;
import com.ledao.entity.PageBean;
import com.ledao.service.DownloadMessageService;
import com.ledao.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台下载信息Controller层
 *
 * @author LeDao
 * @company
 * @create 2021-01-16 19:50
 */
@RestController
@RequestMapping("/admin/downloadMessage")
public class DownloadMessageAdminController {

    @Resource
    private DownloadMessageService downloadMessageService;

    @Resource
    private UserService userService;

    /**
     * 分页分条件查询下载信息
     *
     * @param downloadMessage
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(DownloadMessage downloadMessage, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("userId", downloadMessage.getUserId());
        List<DownloadMessage> downloadMessageList = downloadMessageService.list(map);
        for (DownloadMessage message : downloadMessageList) {
            message.setUser(userService.findById(message.getUserId()));
        }
        Long total = downloadMessageService.getTotal(map);
        resultMap.put("rows", downloadMessageList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除下载信息(可批量删除)
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
            key = downloadMessageService.deleteById(id);
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }
}
