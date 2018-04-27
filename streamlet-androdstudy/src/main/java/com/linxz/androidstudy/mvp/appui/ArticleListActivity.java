package com.linxz.androidstudy.mvp.appui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.mvp.presenter.ArticleListPresenter;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.androidstudy.ui.ArticleListAdapter;
import com.linxz.androidstudy.ui.ArticleListConverter;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日2:24  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/study/list")
public class ArticleListActivity extends BaseMvpActivity<ArticleListPresenter> implements ArticleListView{

    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R2.id.recycle_view)
    RecyclerView recycle_view;

    private ArticleListConverter mArticleListConverter;
    private ArticleListAdapter mArticleListAdapter;

    private String categoryId;

    @OnClick(R2.id.tv_reback)
    public void reback(){
        finish();
    }

    @Override
    protected ArticleListPresenter createPresenter() {
        return new ArticleListPresenter(this);
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        categoryId=getIntent().getStringExtra("categoryId");
    }

    @Override
    public Object setLayout() {
        return R.layout.study_act_study_list;
    }

    @Override
    public void initUI() {
         hideToolBar(true);


        String response="[\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"Activity生命周期\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"Service生命周期\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"广播的使用\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"Retrofit+MVP+OKhttp的使用\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"即时通讯进阶篇\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"第三方SDK细讲\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"Java基础篇\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":100,\n" +
                "\t\"title\":\"AndroidStudio的使用\"\n" +
                "  }\n" +
                "\n" +
                "]";

        LinearLayoutManager layoutManager=new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity,R.color.page_bg),60));

        mArticleListConverter=new ArticleListConverter();
        mArticleListAdapter=new ArticleListAdapter(new ArrayList<MultipleItemEntity>());

        final List<MultipleItemEntity> data=new ArticleListConverter().setJsonData(response).convert();
        final ArticleListAdapter adapter=new ArticleListAdapter(data);
        recycle_view.setAdapter(adapter);
        recycle_view.addOnItemTouchListener(null);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        getCateygory();
    }

    @Override
    public BaseActivity getContext() {
        return null;
    }


    @Override
    public void getCateygory() {

    }

    @Override
    public void getCateygorySuccess(List<Cate> cateList) {

    }

    @Override
    public void getCateygoryFailuer() {

    }
}
