package com.linxz.core.activitys.bottom;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.R;
import com.linxz.core.R2;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author linxz
 */
public abstract class BaseBottomActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R2.id.bottom_bar_container)
    NoScrollViewPager bottomBarContainer;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<Fragment> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean,Fragment> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, Fragment> setItems(ItemBuilder builder);

    /**
     * 设置当前页面
     * */
    public abstract int setIndexDelegate();

    /**设置缓存数量*/
    public abstract int setOffscreenPageLimit();

    /**
     * 设置选中颜色
     * */
    @ColorInt
    public abstract int setClickedColor();


    @Override
    public Object setLayout() {
        return R.layout.act_base_bottom;
    }

    @Override
    public void initUI() {
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, Fragment> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, Fragment> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final Fragment value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

        BottomPagerAdapter homePagerAdapter = new BottomPagerAdapter(getSupportFragmentManager(), ITEM_DELEGATES);
        bottomBarContainer.setAdapter(homePagerAdapter);
        mCurrentDelegate=setIndexDelegate();
        bottomBarContainer.setCurrentItem(mCurrentDelegate);
        bottomBarContainer.setOffscreenPageLimit(setOffscreenPageLimit());
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(activity).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            itemIcon.setTextColor(Color.BLACK);
            itemTitle.setTextColor(Color.BLACK);
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }
    }



    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.BLACK);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.BLACK);
        }
    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        bottomBarContainer.setCurrentItem(tag);
        //注意先后顺序
        mCurrentDelegate = tag;
    }
}
