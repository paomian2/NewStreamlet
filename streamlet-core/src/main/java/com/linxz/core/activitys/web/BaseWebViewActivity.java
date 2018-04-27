package com.linxz.core.activitys.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linxz.core.R;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.utils.StringUtils;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月20日12:21  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@SuppressWarnings("ALL")
public class BaseWebViewActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String WEB_URL = "web_url";
    private static final String TITTLE="tittle";
    private WebView strategyWebview;
    private ProgressBar strategypb;
    private TextView tvTitle;
    private String tittle="";
    private String webUrl;
    private SwipeRefreshLayout mSwipeLayout;
    private String HTML="";
    /**加载HTML数据模式*/
    private boolean loadHTML=false;

    /**协议入口*/
    public static void launcher(Context context, String tittle, String webUrl) {
        Intent intent = new Intent();
        intent.setClass(context, BaseWebViewActivity.class);
        intent.putExtra(WEB_URL, webUrl);
        intent.putExtra(TITTLE,tittle);
        context.startActivity(intent);
    }

    /**其他入口*/
    public static void launcher(BaseActivity activity,String webUrl) {
        Intent intent = new Intent();
        intent.setClass(activity, BaseWebViewActivity.class);
        intent.putExtra(WEB_URL, webUrl);
        activity.startActivityForResult(intent,REQUEST_PAY_CODE);
    }

    /**订单详情页面支付入口*/
    public static void launcher(BaseActivity activity,String webUrl,int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, BaseWebViewActivity.class);
        intent.putExtra(WEB_URL, webUrl);
        activity.startActivityForResult(intent,requestCode);
    }

    /**商品详情入口*/
    public static void launcher(Context context,String tittle,String html,boolean loadHTML) {
        Intent intent = new Intent();
        intent.setClass(context, BaseWebViewActivity.class);
        intent.putExtra("html", html);
        intent.putExtra(TITTLE,tittle);
        intent.putExtra("loadHTML",loadHTML);
        context.startActivity(intent);
    }


    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        tittle = getIntent().getStringExtra(TITTLE);
        webUrl = getIntent().getStringExtra(WEB_URL);
        HTML=getIntent().getStringExtra("html");
        loadHTML=getIntent().getBooleanExtra("loadHTML",false);
    }

    @Override
    public Object setLayout() {
        return R.layout.base_act_web;
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initUI() {
        findViewById(R.id.leftButton).setOnClickListener(this);
        mSwipeLayout =  findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.top_bg);
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeLayout.setEnabled(false);
        strategyWebview = findViewById(R.id.ac_search_strategy_webview);
        WebSettings webSettings = strategyWebview.getSettings();
        if (webSettings != null) {
            webSettings.setJavaScriptEnabled(true);
        }
        strategyWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        strategyWebview.setHorizontalScrollbarOverlay(true);
        strategyWebview.setHorizontalScrollBarEnabled(true);
        strategyWebview.requestFocus();
        strategyWebview.setWebViewClient(new strategyWebViewClient());
        strategyWebview.setWebChromeClient(new strategyWebChromeClient());
        strategyWebview.setDownloadListener(new MyWebViewDownLoadListener());
        strategypb = findViewById(R.id.ac_search_strategy_progress);
        tvTitle =  findViewById(R.id.centerTitle);
        if (!StringUtils.isEmpty(tittle)) {
            tvTitle.setText(tittle);
        }

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        if (loadHTML){
            strategyWebview.loadData(HTML, "text/html; charset=UTF-8", null);
        }else{
            strategyWebview.loadUrl(webUrl);
        }

    }

    private class strategyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("platformapi/startapp")) {
                startAlipayActivity(url);
                // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
            } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                    && (url.contains("platformapi") && url.contains("startapp"))) {
                startAlipayActivity(url);
            } else {
                strategyWebview.loadUrl(url);
            }
            return true;
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            mSwipeLayout.setEnabled(true);
            mSwipeLayout.setRefreshing(false);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
            // 接受所有网站的证书，忽略SSL错误，执行访问网页
            handler.proceed();
        }
    }

    private class strategyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            strategypb.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (title.length() > 8) {
                title = title.substring(0, 8) + "...";
                tvTitle.setText(title);
            }
            //tvTitle.setText("365名酒汇科技股份有限公司");
        }


    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onPause() {
        super.onPause();
        strategyWebview.onPause();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onResume() {
        super.onResume();
        strategyWebview.onResume();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        strategyWebview.removeAllViews();
        strategyWebview.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(strategyWebview.canGoBack()){
                strategyWebview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**调起支付宝并跳转到指定页面*/
    private void startAlipayActivity(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url,
                    Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            if ("apk".equals(url.substring(url.lastIndexOf(".") + 1))) {
            } else {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    activity.startActivity(intent);
                } catch (Exception e) {
                    showToast("附件错误");
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.leftButton) {
            finish();
        }
    }


}