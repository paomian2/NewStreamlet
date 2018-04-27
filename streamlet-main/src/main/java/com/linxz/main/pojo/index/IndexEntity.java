package com.linxz.main.pojo.index;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月09日21:31  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexEntity {

    private int itemType;
    private String title;
    private List<BannerEntity> banners;
    private List<UserEntity> users;
    private NewsEntity news;
    private DiscoverEntity discovers;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BannerEntity> getBannerEntity() {
        return banners;
    }

    public void setBannerEntity(List<BannerEntity> bannerEntity) {
        this.banners = bannerEntity;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public NewsEntity getNewsEntity() {
        return news;
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        this.news = newsEntity;
    }

    public DiscoverEntity getDiscoverEntity() {
        return discovers;
    }

    public void setDiscoverEntity(DiscoverEntity discoverEntity) {
        this.discovers = discoverEntity;
    }
}
