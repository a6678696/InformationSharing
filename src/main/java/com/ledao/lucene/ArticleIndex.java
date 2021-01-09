package com.ledao.lucene;

import com.ledao.entity.Article;
import com.ledao.util.DateUtil;
import com.ledao.util.StringUtil;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 文章索引类
 *
 * @author LeDao
 * @company
 * @create 2021-01-09 15:05
 */
public class ArticleIndex {

    private Directory dir;

    /**
     * 获取IndexWriter实例
     *
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        dir = FSDirectory.open(Paths.get("C://lucene/MyArticle"));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 添加博客索引
     *
     * @param article
     * @throws Exception
     */
    public void addIndex(Article article) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(article.getId()), Field.Store.YES));
        doc.add(new TextField("title", article.getName(), Field.Store.YES));
        doc.add(new StringField("publishDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", article.getSummary(), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 删除指定博客的索引
     *
     * @param articleId
     * @throws Exception
     */
    public void deleteIndex(String articleId) throws Exception {
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term("id", articleId));
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }

    /**
     * 更新博客索引
     *
     * @param article
     * @throws Exception
     */
    public void updateIndex(Article article) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(article.getId()), Field.Store.YES));
        doc.add(new TextField("title", article.getName(), Field.Store.YES));
        doc.add(new StringField("publishDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", article.getSummary(), Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(article.getId())), doc);
        writer.close();
    }

    /**
     * 查询博客信息
     *
     * @param q
     * @return
     * @throws Exception
     */
    public List<Article> searchArticle(String q) throws Exception {
        dir = FSDirectory.open(Paths.get("C://lucene/MyArticle"));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        QueryParser parser = new QueryParser("title", analyzer);
        Query query = parser.parse(q);

        QueryParser parser2 = new QueryParser("content", analyzer);
        Query query2 = parser2.parse(q);

        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

        TopDocs hits = is.search(booleanQuery.build(), 100);
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<strong><span style='color:red'>", "</span></strong>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);

        List<Article> articleList = new LinkedList<>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            Article article = new Article();
            article.setId(Integer.parseInt(doc.get("id")));
            article.setPublishDateStr(doc.get("publishDate"));
            String title = doc.get("title");
            String content = doc.get("content");
            if (title != null) {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isEmpty(hTitle)) {
                    article.setName(title);
                } else {
                    article.setName(hTitle);
                }
            }
            if (content != null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isEmpty(hContent)) {
                    if (content.length() <= 200) {
                        article.setContent(content);
                    } else {
                        article.setContent(content.substring(0, 200));
                    }
                } else {
                    article.setContent(hContent);
                }
            }
            articleList.add(article);
        }
        return articleList;
    }
}
