package com.linxz.core.activitys.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.linxz.core.activitys.web.route.RouteKeys;
import com.linxz.core.app.ConfigKeys;
import com.linxz.core.app.Linxz;
import com.linxz.core.fragments.BaseFragment;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author linxz
 */

public abstract class WebDelegate extends BaseFragment implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private BaseFragment mTopDelegate = null;

    public WebDelegate() {
    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                final String name = Linxz.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public void setTopDelegate(BaseFragment delegate) {
        mTopDelegate = delegate;
    }

    public BaseFragment getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
