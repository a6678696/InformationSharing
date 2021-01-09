package com.ledao.util;

/**
 * 分页工具类
 *
 * @author LeDao
 * @company
 * @create 2021-01-09 14:16
 */
public class PageUtil {

    /**
     * 生成分页代码(首页展示时使用)
     *
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return
     */
    public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {
        //当前页前后页的显示数量
        int pageTotal = 2;
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append("<a href='" + targetUrl + "?page=1" + param + "'>首页</a>");
            if (currentPage > 1) {
                pageCode.append("<a href='" + targetUrl + "?page=" + (currentPage - 1) + param + "'>«</a>");
            } else {
                pageCode.append("<a>«</a>");
            }
            for (int i = currentPage - pageTotal; i <= currentPage + pageTotal; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<span class='layui-laypage-curr' style='background-color: #1e9fff;color: white'>" + i + "</span>");
                } else {
                    pageCode.append("<a href='" + targetUrl + "?page=" + i + param + "'>" + i + "</a>");
                }
            }
            if (currentPage < totalPage) {
                pageCode.append("<a href='" + targetUrl + "?page=" + (currentPage + 1) + param + "'>»</a>");
            } else {
                pageCode.append("<a>»</a>");
            }
            pageCode.append("<a href='" + targetUrl + "?page=" + totalPage + param + "'>尾页</a>");
            return pageCode.toString();
        }
    }

    /**
     * 生成分页代码(资源管理页面)
     *
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return
     */
    public static String genPagination2(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {
        //当前页前后页的显示数量
        int pageTotal = 2;
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append("<a href='" + targetUrl + "?page=1" + param + "'>首页</a>");
            if (currentPage > 1) {
                pageCode.append("<a href='" + targetUrl + "?page=" + (currentPage - 1) + param + "'>«</a>");
            } else {
                pageCode.append("<a>«</a>");
            }
            for (int i = currentPage - pageTotal; i <= currentPage + pageTotal; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<span class='layui-laypage-curr' style='background-color: #1e9fff;color: white'>" + i + "</span>");
                } else {
                    pageCode.append("<a href='" + targetUrl + "?page=" + i + param + "'>" + i + "</a>");
                }
            }
            if (currentPage < totalPage) {
                pageCode.append("<a href='" + targetUrl + "?page=" + (currentPage + 1) + param + "'>»</a>");
            } else {
                pageCode.append("<a>»</a>");
            }
            pageCode.append("<a href='" + targetUrl + "?page=" + totalPage + param + "'>尾页</a>");
            return pageCode.toString();
        }
    }
}
