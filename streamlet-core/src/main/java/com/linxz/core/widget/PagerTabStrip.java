/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linxz.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linxz.core.R;
import com.linxz.core.utils.dimen.DimenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 配合ViewPager实现tab效果
 */
public class PagerTabStrip extends HorizontalScrollView {

	public interface IconTabProvider {
		public int getPageIconResId(int position);
	}

	private static final int[] ATTRS = new int[] { android.R.attr.textSize, android.R.attr.textColor };

	private LinearLayout.LayoutParams defaultTabLayoutParams;
	private LinearLayout.LayoutParams expandedTabLayoutParams;

	private final PageListener pageListener = new PageListener();
	public OnPageChangeListener delegatePageListener;

	private LinearLayout tabsContainer;
	private ViewPager pager;

	private int tabCount;

	private int currentPosition = 0;
	private int selectedPosition = 0;
	private float currentPositionOffset = 0f;

	private Paint rectPaint;
	private Paint dividerPaint;

	private int indicatorColor = 0x00BAA2;
	private int underlineColor = 0x1A000000;
	private int dividerColor = 0x00000000;

	private boolean shouldExpand = false;
	private boolean textAllCaps = true;

	private int scrollOffset = 52;
	private int indicatorHeight = 8;
	private int underlineHeight = 2;
	/** 浮标左右边距，默认是没有 */
	private int indicatorMargin = 0;
	private int dividerPadding = 12;
//	private int tabPadding = 18;
	/** 每个tab的边距。  */
	private int tabPadding = 12;
	private int dividerWidth = 1;

	private int tabTextSize = 12;
	private int tabTextColor = 0xFF666666;
	private int selectedTabTextColor = 0xFF666666;
	private Typeface tabTypeface = null;
	private int tabTypefaceStyle = Typeface.NORMAL;

	private int lastScrollX = 0;

	private int tabBackgroundResId = R.drawable.bg_transparent;
	/** 设置是否平均标签大小,默认为小于等于5个标签为平均标签大小。*/
	private boolean average = true;

	/**
	 * 需要显示红点的item
	 */
	private List<Integer> redDot =new ArrayList<Integer>();
	private Locale locale;

	/**需要统计每个I列表的总数量*/
	private boolean hasCount=false;
	private List<String> itemsCount=new ArrayList<>();

	public PagerTabStrip(Context context) {
		this(context, null);
	}

	public PagerTabStrip(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PagerTabStrip(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setFillViewport(true);
		setWillNotDraw(false);

		tabsContainer = new LinearLayout(context);
		tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
		tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(tabsContainer);

		DisplayMetrics dm = getResources().getDisplayMetrics();

		scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
		indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
		underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
		dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
		tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
		dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
		tabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);

		// get system attrs (android:textSize and android:textColor)

		TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

		tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
		tabTextColor = a.getColor(R.styleable.PagerTabStrip_pststabTextColor, tabTextColor);

		a.recycle();

		// get custom attrs

		a = context.obtainStyledAttributes(attrs, R.styleable.PagerTabStrip);

		indicatorColor = a.getColor(R.styleable.PagerTabStrip_pstsIndicatorColor, indicatorColor);
		underlineColor = a.getColor(R.styleable.PagerTabStrip_pstsUnderlineColor, underlineColor);
		dividerColor = a.getColor(R.styleable.PagerTabStrip_pstsDividerColor, dividerColor);
		indicatorHeight = a.getDimensionPixelSize(R.styleable.PagerTabStrip_pstsIndicatorHeight,
				indicatorHeight);
		underlineHeight = a.getDimensionPixelSize(R.styleable.PagerTabStrip_pstsUnderlineHeight,
				underlineHeight);
		dividerPadding = a.getDimensionPixelSize(R.styleable.PagerTabStrip_pstsDividerPadding,
				dividerPadding);
		tabPadding = a.getDimensionPixelSize(R.styleable.PagerTabStrip_pstsTabPaddingLeftRight,
				tabPadding);
		tabBackgroundResId = a.getResourceId(R.styleable.PagerTabStrip_pstsTabBackground,
				tabBackgroundResId);
		shouldExpand = a.getBoolean(R.styleable.PagerTabStrip_pstsShouldExpand, shouldExpand);
		scrollOffset = a.getDimensionPixelSize(R.styleable.PagerTabStrip_pstsScrollOffset,
				scrollOffset);
		textAllCaps = a.getBoolean(R.styleable.PagerTabStrip_pstsTextAllCaps, textAllCaps);
		a.recycle();

		rectPaint = new Paint();
		rectPaint.setAntiAlias(true);
		rectPaint.setStyle(Style.FILL);

		dividerPaint = new Paint();
		dividerPaint.setAntiAlias(true);
		dividerPaint.setStrokeWidth(dividerWidth);

		defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT);
		expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

		if (locale == null) {
			locale = getResources().getConfiguration().locale;
		}
	}

	public void setViewPager(ViewPager pager) {
		this.pager = pager;

		if (pager.getAdapter() == null) {
			throw new IllegalStateException("ViewPager does not have adapter instance.");
		}

		pager.addOnPageChangeListener(pageListener);

		notifyDataSetChanged();
	}

	/**
	 * TODO:设置viewPager，同时配置标签大小为自适应不平均分配。<br/><br/>
	 */
	public void setViewPagerNotAverage(ViewPager pager) {
		this.pager = pager;
		setAverage(false);

		if (pager.getAdapter() == null) {
			throw new IllegalStateException("ViewPager does not have adapter instance.");
		}

		pager.addOnPageChangeListener(pageListener);

		notifyDataSetChanged();
	}

	public void setOnPageChangeListener(OnPageChangeListener listener) {
		this.delegatePageListener = listener;
	}

	public void notifyDataSetChanged() {

		tabsContainer.removeAllViews();

		tabCount = pager.getAdapter().getCount();

		for (int i = 0; i < tabCount; i++) {

			if (pager.getAdapter() instanceof IconTabProvider) {
				addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
			} else {
				addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
			}

		}

		updateTabStyles();

		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
				currentPosition = pager.getCurrentItem();
				scrollToChild(currentPosition, 0);
			}
		});

	}

	/**设置Tab的样式：是否需要统计tab对应得item的数量*/
	public void setTabsCount(boolean hasCount,List<String> itemsCount){
		this.hasCount=hasCount;
		this.itemsCount.clear();
		this.itemsCount.addAll(itemsCount);
		notifyDataSetChanged();
	}

	private void addTextTab(final int position, String title) {
		RelativeLayout tab= (RelativeLayout) View.inflate(getContext(),R.layout.view_personal_tab,null);
		TextView tv= (TextView) tab.findViewById(R.id.personal_item_title);
		tv.setText(title);
		TextView tvCount= (TextView) tab.findViewById(R.id.tvCount);
		if (hasCount&&itemsCount.size()>=position){
			tvCount.setText(itemsCount.get(position));
			tvCount.setVisibility(View.VISIBLE);
		}else{
			tvCount.setVisibility(View.GONE);
		}
		addTab(position, tab);
	}

	public interface ClickTab{
		void onClick(int position);
	}

	/** 点击事件接口 */
	ClickTab clickTab = null;

	/**
	 * @Description tab栏点击触发点击事件，一般用于刷新页面数据；
	 */
	public void addClickTab(ClickTab clickTab) {
		this.clickTab = clickTab;
		notifyDataSetChanged();
	}


	private void addIconTab(final int position, int resId) {

		ImageButton tab = new ImageButton(getContext());
		tab.setImageResource(resId);

		addTab(position, tab);

	}

	private void addTab(final int position, View tab) {
		tab.setFocusable(true);
		tab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pager != null) {
					pager.setCurrentItem(position,false);
				}
				if (clickTab != null) {
					clickTab.onClick(position);
				}
			}
		});

		tab.setPadding(tabPadding, 0, tabPadding, 0);

		if (tabCount <= 5 && isAverage()) {
			defaultTabLayoutParams.width = DimenUtil.getScreenWidth() / tabCount;
		}

		tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
	}

	private void updateTabStyles() {

		for (int i = 0; i < tabCount; i++) {

			View v = tabsContainer.getChildAt(i);

			v.setBackgroundResource(tabBackgroundResId);

			if (v instanceof RelativeLayout) {

				RelativeLayout tab = (RelativeLayout) v;
				TextView title= (TextView) tab.findViewById(R.id.personal_item_title);
				title.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
				title.setTypeface(tabTypeface, tabTypefaceStyle);
				title.setTextColor(tabTextColor);
				TextView tvCount= (TextView) tab.findViewById(R.id.tvCount);
				tvCount.setTextColor(tabTextColor);

				ImageView news= (ImageView) tab.findViewById(R.id.personal_item_new);
				if(redDot.contains(i)){
					news.setVisibility(VISIBLE);
				}else{
					news.setVisibility(GONE);
				}
				if (i == selectedPosition) {
					title.setTextColor(selectedTabTextColor);
					tvCount.setTextColor(selectedTabTextColor);
				}
			}
		}

	}

	private void scrollToChild(int position, int offset) {

		if (tabCount == 0) {
			return;
		}

		int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

		if (position > 0 || offset > 0) {
			newScrollX -= scrollOffset;
		}

		if (newScrollX != lastScrollX) {
			lastScrollX = newScrollX;
			scrollTo(newScrollX, 0);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (isInEditMode() || tabCount == 0) {
			return;
		}

		final int height = getHeight();

		// draw underline
		rectPaint.setColor(underlineColor);
		canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);

		// draw indicator line
		rectPaint.setColor(indicatorColor);

		// default: line below current tab
		View currentTab = tabsContainer.getChildAt(currentPosition);
		float lineLeft = currentTab.getLeft();
		float lineRight = currentTab.getRight();

		// if there is an offset, start interpolating left and right coordinates
		// between current and next tab
		if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

			View nextTab = tabsContainer.getChildAt(currentPosition + 1);
			final float nextTabLeft = nextTab.getLeft();
			final float nextTabRight = nextTab.getRight();

			lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
			lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
		}

		canvas.drawRect(lineLeft + indicatorMargin, height - indicatorHeight, lineRight - indicatorMargin, height, rectPaint);
		// draw divider

		dividerPaint.setColor(dividerColor);
		for (int i = 0; i < tabCount - 1; i++) {
			View tab = tabsContainer.getChildAt(i);
			canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding,
					dividerPaint);
		}
	}

	/**
	 * @description 设置游标边距
	 */
	public void setIndicatorMargin(int margin) {
		this.indicatorMargin = margin;
		invalidate();
	}

	public void setRedDotIndex(int... index){
		for (int i:index){
			if(!redDot.contains(index)){
				redDot.add(i);
			}
		}
		updateTabStyles();
	}
	/**
	 * 设置需要显示红点的item
	 * @param index
	 */
	public void setRedDotIndex(int index){
		if(!redDot.contains(index)){
			redDot.add(index);
			updateTabStyles();
		}
	}

	public void clearRedDotIndex(int index){
		if(redDot.contains(index)){
			redDot.remove((Object)index);
			updateTabStyles();
		}
	}

	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			currentPosition = position;
			currentPositionOffset = positionOffset;

			scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

			invalidate();

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE) {
				scrollToChild(pager.getCurrentItem(), 0);
			}

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrollStateChanged(state);
			}
		}

		@Override
		public void onPageSelected(int position) {
			selectedPosition = position;
			updateTabStyles();
			if (delegatePageListener != null) {
				delegatePageListener.onPageSelected(position);
			}
		}

	}

	/**
	 * Description：注释详见索引(See Also)。<br/><br/>
	 * @see #average
	 */
	public boolean isAverage() {
		return average;
	}

	/**
	 * Description：注释详见索引(See Also)。<br/><br/>
	 * @see #average
	 */
	public void setAverage(boolean average) {
		this.average = average;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public void setIndicatorColor(int indicatorColor) {
		this.indicatorColor = indicatorColor;
		invalidate();
	}

	public void setIndicatorColorResource(int resId) {
		this.indicatorColor = getResources().getColor(resId);
		invalidate();
	}

	public int getIndicatorColor() {
		return this.indicatorColor;
	}

	public void setIndicatorHeight(int indicatorLineHeightPx) {
		this.indicatorHeight = indicatorLineHeightPx;
		invalidate();
	}

	public int getIndicatorHeight() {
		return indicatorHeight;
	}

	public void setUnderlineColor(int underlineColor) {
		this.underlineColor = underlineColor;
		invalidate();
	}

	public void setUnderlineColorResource(int resId) {
		this.underlineColor = getResources().getColor(resId);
		invalidate();
	}

	public int getUnderlineColor() {
		return underlineColor;
	}

	public void setDividerColor(int dividerColor) {
		this.dividerColor = dividerColor;
		invalidate();
	}

	public void setDividerColorResource(int resId) {
		this.dividerColor = getResources().getColor(resId);
		invalidate();
	}

	public int getDividerColor() {
		return dividerColor;
	}

	public void setUnderlineHeight(int underlineHeightPx) {
		this.underlineHeight = underlineHeightPx;
		invalidate();
	}

	public int getUnderlineHeight() {
		return underlineHeight;
	}

	public void setDividerPadding(int dividerPaddingPx) {
		this.dividerPadding = dividerPaddingPx;
		invalidate();
	}

	public int getDividerPadding() {
		return dividerPadding;
	}

	public void setScrollOffset(int scrollOffsetPx) {
		this.scrollOffset = scrollOffsetPx;
		invalidate();
	}

	public int getScrollOffset() {
		return scrollOffset;
	}

	public void setShouldExpand(boolean shouldExpand) {
		this.shouldExpand = shouldExpand;
		notifyDataSetChanged();
	}

	public boolean getShouldExpand() {
		return shouldExpand;
	}

	public boolean isTextAllCaps() {
		return textAllCaps;
	}

	public void setAllCaps(boolean textAllCaps) {
		this.textAllCaps = textAllCaps;
	}

	public void setTextSize(int textSizePx) {
		this.tabTextSize = textSizePx;
		updateTabStyles();
	}

	public int getTextSize() {
		return tabTextSize;
	}

	public void setTextColor(int textColor) {
		this.tabTextColor = textColor;
		updateTabStyles();
	}

	public void setTextColorResource(int resId) {
		this.tabTextColor = getResources().getColor(resId);
		updateTabStyles();
	}

	public int getTextColor() {
		return tabTextColor;
	}

	public void setSelectedTextColor(int textColor) {
		setSelectedTextColor(textColor,selectedPosition);
	}
	public void setSelectedTextColor(int textColor,int selectedPosition) {
		this.selectedTabTextColor = textColor;
		this.selectedPosition=selectedPosition;
		updateTabStyles();
	}

	public void setSelectedTextColorResource(int resId) {
		this.selectedTabTextColor = getResources().getColor(resId);
		updateTabStyles();
	}

	public int getSelectedTextColor() {
		return selectedTabTextColor;
	}

	public void setTypeface(Typeface typeface, int style) {
		this.tabTypeface = typeface;
		this.tabTypefaceStyle = style;
		updateTabStyles();
	}

	public void setTabBackground(int resId) {
		this.tabBackgroundResId = resId;
		updateTabStyles();
	}

	public int getTabBackground() {
		return tabBackgroundResId;
	}

	public void setTabPaddingLeftRight(int paddingPx) {
		this.tabPadding = paddingPx;
		updateTabStyles();
	}

	public int getTabPaddingLeftRight() {
		return tabPadding;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState savedState = (SavedState) state;
		super.onRestoreInstanceState(savedState.getSuperState());
		currentPosition = savedState.currentPosition;
		requestLayout();
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		SavedState savedState = new SavedState(superState);
		savedState.currentPosition = currentPosition;
		return savedState;
	}

	static class SavedState extends BaseSavedState {
		int currentPosition;

		public SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			currentPosition = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(currentPosition);
		}

		public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
			@Override
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

}
