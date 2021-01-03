package com.ledao.entity;

/**
 * 友情链接实体类
 *
 * @author LeDao
 * @company
 * @create 2021-01-04 1:28
 */
public class Link {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String url;
    /**
     * 排列序号
     */
    private Integer sortNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sortNum=" + sortNum +
                '}';
    }
}
