package com.linxz.main.pojo.index;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月09日17:34  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class NewsEntity {

    private String itmsgId;
    private String title;
    private String imgdesc;
    private String desc;
    private String linkUrl;

    public String getId() {
        return itmsgId;
    }

    public void setId(String id) {
        this.itmsgId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return imgdesc;
    }

    public void setImages(String images) {
        this.imgdesc = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
