package com.linxz.main.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.app.Linxz;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.core.utils.log.LinxzLogger;
import com.linxz.main.mvp.appui.frags.IndexI;
import com.linxz.main.mvp.module.IndexModule;
import com.linxz.main.pojo.index.BannerEntity;
import com.linxz.main.pojo.index.DiscoverEntity;
import com.linxz.main.pojo.index.NewsEntity;
import com.linxz.main.pojo.index.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日20:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexPresenter extends BasePresenter<IndexI, IndexModule> {

    public IndexPresenter(IndexI mView) {
        super(mView);
    }

    @Override
    public IndexModule onCreateModel() {
        return new IndexModule();
    }

    /**
     * 获取Banner
     */
    public void getBanners() {
        mModel.getBanners(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<BannerEntity> banners = new Gson().fromJson(response, new TypeToken<List<BannerEntity>>() {
                }.getType());
                mView.getBannersSuccess(banners);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getBannerFailer(msg);
            }
        });
    }

    /**
     * 获取活跃用户
     */
    public void getActiveUsers() {
        mModel.getActiveUsers(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<UserEntity> userEntities = new Gson().fromJson(response, new TypeToken<List<UserEntity>>() {
                }.getType());
                mView.getActiveUsersSuccess(userEntities);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getActiveUsersFailuer();
            }
        });
    }

    /**
     * 获取最新IT资讯
     */
    public void getITNews() {
        LinxzLogger.d("","");
        mModel.getITNews(new ISuccess() {
                             @Override
                             public void onSuccess(String response) {
                                 List<NewsEntity> newsEntities = new Gson().fromJson(response, new TypeToken<List<NewsEntity>>() {
                                 }.getType());
                                 mView.getITNewsSuccess(newsEntities);
                             }
                         },
                new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mView.getActiveUsersFailuer();
                    }
                });
    }

    /**
     * 获取最新IT资讯
     */
    public void getDicovers(HashMap<String,Object> params) {
        mModel.getDiscovers(params,new ISuccess() {
                             @Override
                             public void onSuccess(String response) {
                                 List<DiscoverEntity> discoverEntities = new Gson().fromJson(response, new TypeToken<List<DiscoverEntity>>() {
                                 }.getType());
                                 mView.getPushSuccess(discoverEntities);
                             }
                         },
                new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mView.getPushsFailuer();
                    }
                });
    }
}
