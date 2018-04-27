package com.linxz.interviews.mvp.appui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.activitys.web.BaseWebViewActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.loader.LatteLoader;
import com.linxz.core.ui.loader.LoaderStyle;
import com.linxz.core.widget.AlignTextView;
import com.linxz.interviews.R;
import com.linxz.interviews.R2;
import com.linxz.interviews.mvp.presenter.InterviewsPresenter;
import com.linxz.interviews.pojo.InterviewsEntity;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日1:00  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/interviews/home")
public class InterviewsActivity extends BaseMvpActivity<InterviewsPresenter> implements InterviewsView {

    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;

    @BindView(R2.id.layoutShortAnswer)
    LinearLayoutCompat layoutShortAnswer;
    @BindView(R2.id.tv_article_title)
    AppCompatTextView tvArticleTitle;
    @BindView(R2.id.tv_content)
    AlignTextView tvContent;

    @BindView(R2.id.layoutScrollView)
    NestedScrollView layoutScrollView;
    @BindView(R2.id.layoutChooseAnswer)
    LinearLayoutCompat layoutChooseAnswer;
    @BindView(R2.id.tv_subject)
    AppCompatTextView tv_subject;
    @BindView(R2.id.tv_questionA)
    AppCompatTextView tv_questionA;
    @BindView(R2.id.tv_questionB)
    AppCompatTextView tv_questionB;
    @BindView(R2.id.tv_questionC)
    AppCompatTextView tv_questionC;
    @BindView(R2.id.tv_questionD)
    AppCompatTextView tv_questionD;

    @BindView(R2.id.tv_link)
    AppCompatTextView tvLink;


    private String interviewId;

    @OnClick(R2.id.tv_reback)
    public void reback() {
        finish();
    }

    @OnClick(R2.id.tv_link)
    public void tvLink() {
        BaseWebViewActivity.launcher(activity,tvLink.getText().toString().trim());
    }

    @Override
    protected InterviewsPresenter createPresenter() {
        return new InterviewsPresenter(this);
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        interviewId = getIntent().getStringExtra("interviewId");
    }

    @Override
    public Object setLayout() {
        return R.layout.interviews_act_interviews;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        LatteLoader.showLoading(activity, LoaderStyle.BallBeatIndicator);
        getInterview();
    }

    @Override
    public BaseActivity getContext() {
        return null;
    }


    @Override
    public void getInterview() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("interviewId", interviewId);
        mvpPresenter.getInterview(params);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getInterviewSuccess(InterviewsEntity interviewsEntity) {
        LatteLoader.stopLoading();
        layoutScrollView.setVisibility(View.VISIBLE);
        final String SHORT_ANSWER = "1";
        if (SHORT_ANSWER.equals(interviewsEntity.getType())) {
            //简答题
            layoutChooseAnswer.setVisibility(View.GONE);
            layoutShortAnswer.setVisibility(View.VISIBLE);
            tvTitle.setText(interviewsEntity.getQuestion());
            tvArticleTitle.setText(interviewsEntity.getQuestion());
            tvContent.setText(interviewsEntity.getAnswer());
        } else {
            //选择题
            layoutShortAnswer.setVisibility(View.GONE);
            layoutChooseAnswer.setVisibility(View.VISIBLE);
            tv_subject.setText(interviewsEntity.getQuestion());
            tv_questionA.setText("A." + interviewsEntity.getAnswerA());
            tv_questionB.setText("B." + interviewsEntity.getAnswerB());
            tv_questionC.setText("C." + interviewsEntity.getAnswerC());
            tv_questionD.setText("D." + interviewsEntity.getAnswerD());
            switch (interviewsEntity.getAnswer()) {
                case "A":
                    tv_questionA.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    break;
                case "B":
                    tv_questionB.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    break;
                case "C":
                    tv_questionC.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    break;
                case "D":
                    tv_questionD.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    break;
                default:
                    break;
            }
        }
        tvLink.setText(interviewsEntity.getUrl());
    }

    @Override
    public void getInterviewFailuer() {
        LatteLoader.stopLoading();
        AppActivityManager.getInstance().goToFailed(activity,"面试题");
    }
}
