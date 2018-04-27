package com.linxz.core.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.R;

import java.util.List;


/**
 * @author linxz
 */
public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {
	/**一个tab页面对应一个Fragment*/
	private List<Fragment> fragments;
	/**用于切换tab*/
	private RadioGroup rgs;
	/**Fragment所属的Activity*/
	private FragmentActivity fragmentActivity;
	/**Activity中所要被替换的区域的id*/
	private int fragmentContentId;
	/**当前Tab页面索引*/
	private int currentTab;
	/**用于让调用者在切换tab时候增加新的功能*/
	private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener;

	public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
		this.fragments = fragments;
		this.rgs = rgs;
		this.fragmentActivity = fragmentActivity;
		this.fragmentContentId = fragmentContentId;
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();

		// 默认显示第一页
		if(fragments.size()>0){
			String tempTag="0";
			if (fragmentActivity.getSupportFragmentManager().findFragmentByTag(tempTag)==null) {
				ft.add(fragmentContentId, fragments.get(0), "0");
			}else{
				ft.remove(fragmentActivity.getSupportFragmentManager().findFragmentByTag(tempTag));
				ft.add(fragmentContentId, fragments.get(0), "0");
			}
			ft.commitAllowingStateLoss();

			rgs.setOnCheckedChangeListener(this);
		}


	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		if (rgs.getChildCount() == fragments.size()) {
			for (int i = 0; i < rgs.getChildCount(); i++) {
				if (rgs.getChildAt(i).getId() == checkedId) {
					Fragment fragment = fragments.get(i);
					FragmentTransaction ft = obtainFragmentTransaction(i);

					getCurrentFragment().onPause(); // 暂停当前tab
					// getCurrentFragment().onStop(); // 暂停当前tab

					if (fragment.isAdded()) {
						// fragment.onStart(); // 启动目标tab的onStart()
						fragment.onResume(); // 启动目标tab的onResume()
					} else {
						if(fragmentActivity.getSupportFragmentManager().findFragmentByTag(i+"")==null){
							ft.add(fragmentContentId, fragment, i + "");
							ft.commitAllowingStateLoss();
						}else{
							ft.remove(fragmentActivity.getSupportFragmentManager().findFragmentByTag(i+""));
							ft.add(fragmentContentId, fragment, i + "");
							ft.commitAllowingStateLoss();
						}
					}
					// 显示目标tab
					showTab(i);
					// 如果设置了切换tab额外功能功能接口
					if (null != onRgsExtraCheckedChangedListener) {
						onRgsExtraCheckedChangedListener.onRgsExtraCheckedChanged(radioGroup, checkedId, i);
					}

				}
			}

		}else{
			//长度不一样就直接退出应用
			AppActivityManager.getInstance().exit();
		}

	}

	/**
	 * 切换tab
	 * 
	 * @param idx
	 */
	private void showTab(int idx) {
		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(idx);

			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commitAllowingStateLoss();
		}
		// 更新目标tab为当前tab
		currentTab = idx;
	}

	/**
	 * 获取一个带动画的FragmentTransaction
	 * 
	 * @param index
	 * @return
	 */
	private FragmentTransaction obtainFragmentTransaction(int index) {
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
		// 设置切换动画
		if (index > currentTab) {
			ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
		} else {
			ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
		}
		return ft;
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentTab);
	}

	public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
		return onRgsExtraCheckedChangedListener;
	}

	public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
		this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
	}

	/**
	 * 切换tab额外功能功能接口
	 */
	public static class OnRgsExtraCheckedChangedListener {
		public void onRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {

		}
	}

}
