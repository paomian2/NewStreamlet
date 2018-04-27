package com.linxz.core.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.linxz.core.R;
import com.linxz.core.app.Linxz;
import com.linxz.core.base.ViewManager;
import com.linxz.core.utils.LinxzSharedPreference;

import java.lang.Thread.UncaughtExceptionHandler;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 所有activity的基类
 *
 * @author lstreamlet
 */
public abstract class BaseActivity extends AppCompatActivity implements UncaughtExceptionHandler, ISupportActivity {
    protected BaseActivity activity;
    /**
     * 默认的ActionBar
     */
    protected ActionBar mActionBar;
    /**
     * 黄油刀
     */
    private Unbinder mUnbinder = null;
    public static final int RESULT_PAY_SUCCESS = 1008;
    public static final int REQUEST_PAY_CODE = 666;
    public static final int REQUEST_REFRESH_CODE = 666;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        Looper looper;
                Handler l;
        AppActivityManager.getInstance().addActivity(this);
        ViewManager.getInstance().addActivity(this);
        //标题栏设置
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.drawable.arrow);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
            if (setTitleId() != 0) {
                mActionBar.setTitle(setTitleId());
            } else {
                mActionBar.setTitle(R.string.app_name);
            }
            mActionBar.hide();
        }
        //用于传递数据
        Bundle b = getIntent().getBundleExtra("Bundle");
        onGetBundle(b);
        //设置布局页面
        final View rootView;
        final Object targetView = setLayout();
        if (targetView instanceof Integer) {
            setContentView((Integer) targetView);
        } else if (targetView instanceof View) {
            setContentView((View) targetView);
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mUnbinder = ButterKnife.bind(this);
        initContainer(savedInstanceState);
        //初始化控件
        initUI();
        //初始化事件
        initEnvent();
        //传时候数据
        initData();
    }

    public void onGetBundle(Bundle bundle) {
    }

    /**
     * 初始化页面布局
     * @return  Object:返回int或view
     */
    public abstract Object setLayout();

    /**
     * 初始化控件
     */
    public abstract void initUI();

    /**
     * 初始化事件
     */
    public abstract void initEnvent();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DELEGATE.onDestroy();
        AppActivityManager.getInstance().removeActivity(this);
        ViewManager.getInstance().finishActivity(this);
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        System.gc();
        System.runFinalization();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        AppActivityManager.getInstance().cleanActivity();
    }

    /**
     * 默认时间LENGTH_LONG
     */
    public void showToast(String msg) {
        Toast.makeText(Linxz.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * Setup the toolbar.
     *
     * @param toolbar   toolbar
     * @param hideTitle 是否隐藏Title
     */
    public void setupToolBar(Toolbar toolbar, boolean hideTitle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.arrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            if (hideTitle) {
                //隐藏Title
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    /**
     * 隐藏Title
     */
    public void setupToolBar(boolean hideTitle) {
        if (mActionBar != null && hideTitle) {
            mActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 隐藏ActionBar
     */
    public void hideToolBar(boolean hideActionBar) {
        if (mActionBar != null && hideActionBar) {
            mActionBar.hide();
        }
    }


    /**
     * 设置默认标题id
     *
     * @return 标题id
     */
    @StringRes
    public int setTitleId() {
        return 0;
    }

    /**
     * 更新标题
     *
     * @param title String标题
     */
    public void setTitle(String title) {
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

   public boolean checkSign(){
        String user= LinxzSharedPreference.getCustomAppProfile("user");
        if (user.isEmpty()){
            ARouter.getInstance().build("/user/signIn").navigation();
            return false;
        }
        return true;
   }


    private void initContainer(@Nullable Bundle savedInstanceState) {
        DELEGATE.onCreate(savedInstanceState);
    }

    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        DELEGATE.onBackPressed();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
