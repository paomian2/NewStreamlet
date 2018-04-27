package com.linxz.androidstudy.mvp.appui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.pojo.ArticleControllerEntity;
import com.linxz.androidstudy.ui.ArticleControllerAdapter;
import com.linxz.androidstudy.ui.ArticleDetailDialog;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.activitys.web.WebDelegateImpl;
import com.linxz.core.utils.sharesdk.MobSDKBuild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： 博客页面
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日15:18  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/study/blog")
public class ArticleDetailActivity extends BaseActivity {

    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    private View contentView;
    private ArticleDetailDialog detailDialog;
    private String blogId;
    private String title;
    private String url;

    /**基础知识*/
    private final int TYPE_BASIC_KNOWLEDGE=1001;
    /**安卓开源库*/
    private final int YTPE_ANDROID_LIB=1002;
    /**IT资讯*/
    private final int TYPE_IT_NEWS=1003;

    @OnClick(R2.id.tv_reback)
    public void reBack() {
        finish();
    }

    @OnClick(R2.id.itv_add)
    public void itvAdd() {
       /* if (detailDialog == null) {
            detailDialog = ArticleDetailDialog
                    .build(activity)
                    .withUI(contentView);
        } else {
            detailDialog.show();
        }*/
        MobSDKBuild.create()
                .showShare(activity);
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        Intent intent = getIntent();
        blogId = intent.getStringExtra("blogId");
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("entry " + this.getClass().getSimpleName() + " must with url");
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.study_article_detail;
    }

    @SuppressLint("InflateParams")
    @Override
    public void initUI() {
        tvTitle.setText(title == null ? "" : title);

        final WebDelegateImpl delegate = WebDelegateImpl.create(url);
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);

        contentView = getLayoutInflater().inflate(R.layout.study_dialog_artircle, null);
        GridView gridView = contentView.findViewById(R.id.gv_icon);
        final List<ArticleControllerEntity> list = new ArrayList<>();
        addItems(list);
        ArticleControllerAdapter adapter = new ArticleControllerAdapter(activity, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticleControllerEntity entity = list.get(position);
                showToast(entity.getName());
            }
        });
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }

    private void addItems(List<ArticleControllerEntity> list) {
        ArticleControllerEntity read = new ArticleControllerEntity("{studyIcon-read}", "已阅读");
        ArticleControllerEntity know = new ArticleControllerEntity("{studyIcon-knowd}", "已研究");
        ArticleControllerEntity discuss = new ArticleControllerEntity("{studyIcon-discuss}", "去讨论");
        ArticleControllerEntity collection = new ArticleControllerEntity("{studyIcon-colleage}", "收藏");
        ArticleControllerEntity share = new ArticleControllerEntity("{studyIcon-share}", "分享");

        list.add(read);
        list.add(know);
        list.add(discuss);
        list.add(collection);
        list.add(share);
    }

}
