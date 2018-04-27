package com.linxz.users.mvp.appui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.ui.push.MyPushAdapter;
import com.linxz.users.ui.push.MyPushConverter;

import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月13日6:21  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/user/home2")
public class UserHomeActivity2 extends BaseActivity{

    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R2.id.recycle_view)
    RecyclerView recyclerView;

    private String userId;

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        userId=getIntent().getStringExtra("userId");
        if (userId==null || userId.isEmpty()){
            throw new RuntimeException("entry "+this.getClass().getSimpleName()+" must has userId");
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.user_base_recycleview;
    }

    @Override
    public void initUI() {

        showToast(""+userId);

        String response="{\n" +
                "  \"userInfo\":{\n" +
                "               \"userId\":\"1001\",\n" +
                "\t\t\t   \"name\":\"刘亦菲\",\n" +
                "\t\t\t   \"headimg\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101289799,2486141640&fm=27&gp=0.jpg\",\n" +
                "\t\t\t   \"rank\":1\n" +
                "             },\n" +
                " \"pushList\":[\n" +
                "              {\n" +
                "\t\t\t    \"id\":\"1221\",\n" +
                "\t\t\t\t\"title\":\"现在的Android行情怎么样\",\n" +
                "\t\t\t\t\"lable\":\"来解答\",\n" +
                "\t\t\t\t\"time\":10000,\n" +
                "\t\t\t\t\"content\":\"2018年的Android行情怎么样呢，找工作好找么\",\n" +
                "\t\t\t\t\"images\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101289799,2486141640&fm=27&gp=0.jpg\",\n" +
                "\t\t\t\t\"up\":13,\n" +
                "\t\t\t\t\"down\":4,\n" +
                "\t\t\t\t\"read\":111,\n" +
                "\t\t\t\t\"discuss\":[\n" +
                "\t\t\t\t    {\n" +
                "\t\t\t\t\t  \"id\":\"1001\",\n" +
                "\t\t\t\t\t  \"linkId\":\"1111\",\n" +
                "\t\t\t\t\t  \"type\":\"1\",\n" +
                "\t\t\t\t\t  \"userId\":\"123\",\n" +
                "\t\t\t\t\t  \"userName\":\"吴莫愁\",\n" +
                "\t\t\t\t\t  \"userImg\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101289799,2486141640&fm=27&gp=0.jpg\",\n" +
                "\t\t\t\t\t  \"discussContent\":\"又到了装逼的季节了么？\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t  \"id\":\"1001\",\n" +
                "\t\t\t\t\t  \"linkId\":\"1111\",\n" +
                "\t\t\t\t\t  \"type\":\"1\",\n" +
                "\t\t\t\t\t  \"userId\":\"123\",\n" +
                "\t\t\t\t\t  \"userName\":\"张一明\",\n" +
                "\t\t\t\t\t  \"userImg\":\"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3101289799,2486141640&fm=27&gp=0.jpg\",\n" +
                "\t\t\t\t\t  \"discussContent\":\"又到了装逼的季节了么？\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t]\n" +
                "\t\t\t  }\n" +
                "            \n" +
                "\t\t\t \n" +
                "\t\t\t ]\n" +
                "\n" +
                "}";

        LinearLayoutManager layoutManager=new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity,R.color.page_bg),60));

        final List<MultipleItemEntity> data=new MyPushConverter().setJsonData(response).convert();
        final MyPushAdapter adapter=new MyPushAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initEnvent() {
    }

    @Override
    public void initData() {
    }
}
