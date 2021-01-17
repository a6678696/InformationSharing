package com.ledao.quartz;

import com.ledao.entity.Article;
import com.ledao.service.ArticleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * 每天23点自动删除已被删除文章中的图片
 *
 * @author LeDao
 * @company
 * @create 2020-09-23 11:27
 */
@Configuration
@EnableScheduling
public class DeleteInvalidImage {

    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "0 0 23 * * ?")
    public void work() {
        List<Article> articleList = articleService.list(null);
        //博客中引用的图片名称集合
        List<String> articleImageList = new ArrayList<>();
        for (Article article : articleList) {
            //博客里的内容
            String articleInfo = article.getContent();
            //抓取出博客里的内容
            Document document = Jsoup.parse(articleInfo);
            //提出.jpg图片
            Elements jpgs = document.select("img[src$=.jpg]");
            if (jpgs.size() > 0) {
                for (int i = 0; i < jpgs.size(); i++) {
                    Element jpg = jpgs.get(i);
                    int begin = jpg.toString().indexOf("/static/images/articleImage/");
                    int last = jpg.toString().indexOf(".jpg");
                    articleImageList.add((jpg.toString().substring(begin, last) + ".jpg").substring(28));
                }
            }
        }
        File file = new File("C:\\Java\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\webapps\\InformationSharing\\static\\images\\articleImage");
        //文件夹内的图片名称集合
        List<String> dirImageList = new ArrayList<>();
        //仅为了获取文件名
        for (String s : file.list()) {
            dirImageList.add(s);
        }
        if (dirImageList.size() > articleImageList.size()) {
            for (String s : articleImageList) {
                Iterator iterator = dirImageList.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().equals(s)) {
                        iterator.remove();
                    }
                }
            }
            List<String> invalidImageList = dirImageList;
            for (String s : invalidImageList) {
                File file1 = new File("C:\\Java\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\webapps\\InformationSharing\\static\\images\\articleImage\\" + s);
                file1.delete();
            }
        }
    }
}
