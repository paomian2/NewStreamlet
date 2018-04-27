package com.linxz.users.mvp.appui.push;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.camera.CameraHandle;
import com.linxz.core.ui.loader.LatteLoader;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.utils.dialogs.DialogHelper;
import com.linxz.core.utils.dialogs.OnPositiveClickListener;
import com.linxz.core.utils.permission.OnPermissionDeniedWithNeverAsk;
import com.linxz.core.utils.permission.OnPermissionGranted;
import com.linxz.core.utils.permission.OnpermissionRationale;
import com.linxz.core.utils.permission.PermissionHandle;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.app.ActivityPathManager;
import com.linxz.users.mvp.presenter.PushPresenter;
import com.linxz.users.pojo.Cate;
import com.linxz.users.ui.push.CateSpinerAdapter;
import com.linxz.users.ui.push.PushMessageAdapter;
import com.linxz.users.ui.push.PushMessageConverter;
import com.linxz.users.ui.push.callback.PushImageAddClickCallBack;
import com.linxz.users.ui.push.callback.PushImageClickCallBack;
import com.linxz.users.ui.push.listener.PushImageAddClickListener;
import com.linxz.users.utils.UserSharePrefreshHanlder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日20:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushActivity extends BaseMvpActivity<PushPresenter> implements PushView {


    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView tvRight;
    @BindView(R2.id.spinner_cate)
    AppCompatSpinner spinnerCate;
    @BindView(R2.id.edt_title)
    AppCompatEditText edtTitle;
    @BindView(R2.id.edt_content)
    AppCompatEditText edtContent;
    @BindView(R2.id.edt_link_url)
    AppCompatEditText edtLinkUrl;

    @BindView(R2.id.rv_imgs)
    RecyclerView rvImgs;

    /**
     * 拍照请求码
     */
    private final int CAMERA_REQUEST_CODE = 188;
    /**添加成功返回码*/
    public static final int REQUEST_CODE=1086;
    private PushMessageConverter pushMessageConverter;
    private PushMessageAdapter pushMessageAdapter;
    private List<String> imagePathsList = new ArrayList<>();
    private List<String> imagesUrlList = new ArrayList<>();
    private Cate cateSelect;

    @Override
    protected PushPresenter createPresenter() {
        return new PushPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_push;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新建发布");
        tvRight.setText("发布");
        tvRight.setBackgroundColor(ContextCompat.getColor(activity,R.color.title_color));
        tvRight.setVisibility(View.VISIBLE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        rvImgs.setLayoutManager(gridLayoutManager);
        rvImgs.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity, R.color.white), 10));
        rvImgs.setHasFixedSize(true);
        rvImgs.setNestedScrollingEnabled(false);
        PushImageAddClickListener listener = PushImageAddClickListener.builder()
                .withPushImageAddClickCallBack(new PushImageAddClickCallBack() {
                    @Override
                    public void onImageAdd() {
                        imagePicker();
                    }
                })
                .withPushImageClickCallBack(new PushImageClickCallBack() {
                    @Override
                    public void onImageClick(String filePath) {
                        imageHandle(filePath);
                    }
                })
                .build();
        rvImgs.addOnItemTouchListener(listener);

        imagePathsList.add("");
        String dataJson = new Gson().toJson(imagePathsList);
        pushMessageConverter = new PushMessageConverter();
        List<MultipleItemEntity> data = pushMessageConverter.setJsonData(dataJson).convert();
        pushMessageAdapter = PushMessageAdapter.build(data);
        rvImgs.setAdapter(pushMessageAdapter);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        getCate();
    }

    @Override
    public BaseActivity getContext() {
        return null;
    }

    /**
     * 添加图片
     * */
    private void imagePicker() {
        if (imagePathsList.size() >= 10) {
            showToast("最多9张图");
            return;
        }
        final int maxNum = 10 - imagePathsList.size();
        PermissionHandle
                .builder()
                .withActivity(activity)
                .withPermissions(Manifest.permission.CAMERA)
                .withOnPermissionGranted(new OnPermissionGranted() {
                    @Override
                    public void onGranted() {
                        CameraHandle
                                .builder()
                                .withActivity(activity)
                                .withRequestCode(CAMERA_REQUEST_CODE)
                                .withMaxSelectNum(maxNum)
                                .build()
                                .pictureSelector();
                    }
                })
                .withOnpermissionRationale(new OnpermissionRationale() {
                    @Override
                    public void showRequestPermissionRationale() {

                    }
                })
                .withOnPermissionDeniedWithNeverAsk(new OnPermissionDeniedWithNeverAsk() {
                    @Override
                    public void deniedWithNeverAsk() {

                    }
                })
                .build()
                .permissionCheck();

    }

    /**
     * 图片操作(移除图片)
     * */
    private void imageHandle(final String filePath) {
        DialogHelper.builder()
                .withContext(activity)
                .withTitle("温馨提示")
                .withMessage("是否移除该照片")
                .withPositiveClick(new OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick(Object object) {
                        for (String str : imagePathsList) {
                            if (filePath.equals(str)) {
                                imagePathsList.remove(filePath);
                                break;
                            }
                        }
                        pushMessageConverter.clearData();
                        String dataJson = new Gson().toJson(imagePathsList);
                        List<MultipleItemEntity> data = pushMessageConverter.setJsonData(dataJson).convert();
                        pushMessageAdapter.setNewData(data);
                    }
                })
                .build()
                .showBasicDialog();
    }

    /**
     * 表单校验
     */
    private boolean checkForm() {
        if (cateSelect == null) {
            showToast("分类加载失败!");
            return false;
        }
        if (edtContent.getText().toString().trim().isEmpty()) {
            showToast("请输入信息内容！");
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    List<String> picPaths = CameraHandle.pickerResult(data);
                    if (picPaths != null && picPaths.size() > 0) {
                        imagePathsList.addAll(0, picPaths);
                        pushMessageConverter.clearData();
                        String dataJson = new Gson().toJson(imagePathsList);
                        List<MultipleItemEntity> dataList = pushMessageConverter.setJsonData(dataJson).convert();
                        pushMessageAdapter.setNewData(dataList);
                    } else {
                        showToast("图片操作异常!");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void getCate() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("parentId", "c10048");
        mvpPresenter.getCate(params);
    }

    @Override
    public void getCateSuccess(List<Cate> cateList) {
        cateSelect=cateList.get(0);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(activity, R.layout.user_item_spiner);
        final CateSpinerAdapter cateSpinerAdapter = CateSpinerAdapter.build(activity, cateList);
        spinnerCate.setAdapter(cateSpinerAdapter);
        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cateSelect = cateSpinerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void getCateFailuer(String msg) {

    }

    @Override
    public void upload(String filePath) {
        mvpPresenter.upload(filePath);
        LatteLoader.showLoading(activity);
    }

    @Override
    public void uploadResult(boolean success, String msg, String path) {
        showToast(msg);
        if (success) {
            imagesUrlList.add(path);
            if ((imagesUrlList.size() + 1 == imagePathsList.size())) {
                //上传完毕
                pushMessage();
            }
        }else{
            showToast(msg);
            LatteLoader.stopLoading();
        }
    }

    @Override
    public void pushMessage() {
        if (cateSelect == null) {
            showToast("数据加载失败！");
            finish();
        }
        StringBuilder imgs = new StringBuilder();
        for (String str : imagesUrlList) {
            if (imgs.length() > 0) {
                imgs.append("`").append(str);
            }else{
                imgs.append(str);
            }
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", UserSharePrefreshHanlder.getToken());
        params.put("cateId", cateSelect.getTypeId());
        params.put("title", edtTitle.getText().toString().trim());
        params.put("content", edtContent.getText().toString().trim());
        params.put("linkUrl", edtLinkUrl.getText().toString().trim());
        params.put("imgs", imgs.toString());
        mvpPresenter.pushMessage(params);
    }

    @Override
    public void pushMessageResult(boolean success, int code, String msg) {
        LatteLoader.stopLoading();
        if (success) {
            showToast(msg);
            setResult(RESULT_OK);
            finish();
        } else if (code == 202) {
            DialogHelper
                    .builder()
                    .withContext(activity)
                    .withPositiveClick(new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(Object object) {
                            ARouter.getInstance()
                                    .build(ActivityPathManager.SIGN_IN)
                                    .navigation();
                        }
                    }).build()
                    .showAuthDialog();

        } else {
            showToast(msg);
        }
    }

    @OnClick(R2.id.tv_reback)
    public void reback() {
        finish();
    }

    @OnClick(R2.id.tv_right)
    public void pushMessageClick() {
        if (checkForm()) {
            LatteLoader.showLoading(activity);
            //上传图片
            if (imagePathsList.size() > 1) {
                for (String filePath : imagePathsList) {
                    if (filePath != null && !filePath.isEmpty()) {
                        upload(filePath);
                    }
                }
            } else {
                //无图发布
                pushMessage();
            }
        }
    }
}
