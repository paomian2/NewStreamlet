package com.linxz.androidstudy.pojo;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日14:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ArticleEntity {


    private int id;
    private String basicKnowledgeId;
    private String categoryId;
    private String title;
    private String content;
    private Object imgsDesc;
    private Object author;
    private Object authorId;
    private String linkUrl;
    private Object linkIds;
    private Object type;
    private Object source;
    private int status;
    private int up;
    private int down;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBasicKnowledgeId() {
        return basicKnowledgeId;
    }

    public void setBasicKnowledgeId(String basicKnowledgeId) {
        this.basicKnowledgeId = basicKnowledgeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getImgsDesc() {
        return imgsDesc;
    }

    public void setImgsDesc(Object imgsDesc) {
        this.imgsDesc = imgsDesc;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public Object getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Object authorId) {
        this.authorId = authorId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Object getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(Object linkIds) {
        this.linkIds = linkIds;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
