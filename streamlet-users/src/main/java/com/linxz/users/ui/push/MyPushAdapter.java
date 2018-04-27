package com.linxz.users.ui.push;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.ui.recycle.SpaceItemDecoration;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.users.R;
import com.linxz.users.pojo.push.DynamicEntity;
import com.linxz.users.pojo.push.PushItemType;
import com.linxz.users.pojo.push.PushMultipleFields;
import com.linxz.users.pojo.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月13日7:17  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public final class MyPushAdapter extends MultipleRecyclerAdapter {


    public MyPushAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EMPTY_DATA,R.layout.core_item_empty_data);
        addItemType(PushItemType.HEANDER, R.layout.user_item_push_header);
        addItemType(PushItemType.TEXT, R.layout.user_item_push);
        addItemType(PushItemType.ON_IMG, R.layout.user_item_push_one_img);
        addItemType(PushItemType.MOREN_IMG, R.layout.user_item_push_more_img);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.EMPTY_DATA:
                break;
            case PushItemType.HEANDER:
                final AppCompatTextView tv_img_user=holder.getView(R.id.tv_img_user);
                final CircleImageView img_user=holder.getView(R.id.img_user);
                final AppCompatTextView tv_user_name=holder.getView(R.id.tv_user_name);
                final AppCompatTextView tv_user_v=holder.getView(R.id.tv_user_v);

                final UserEntity userEntity=entity.getField(PushMultipleFields.HEADER);
                if (userEntity.getPortrait()==null || userEntity.getPortrait().isEmpty()){
                    tv_img_user.setVisibility(View.VISIBLE);
                    tv_img_user.setText(userEntity.getNickName().substring(0,1));
                    img_user.setVisibility(View.GONE);
                }else{
                    tv_img_user.setVisibility(View.GONE);
                    img_user.setVisibility(View.VISIBLE);
                    ImageHelper.imageNet(mContext,userEntity.getPortrait(),img_user);
                }

                tv_user_name.setText(userEntity.getNickName());
                tv_user_v.setText("Android小菜鸟");

                break;
            case PushItemType.TEXT:
                final AppCompatTextView tv_title=holder.getView(R.id.tv_title);
                final AppCompatTextView tv_time=holder.getView(R.id.tv_time);
                final AppCompatTextView tv_lable=holder.getView(R.id.tv_lable);
                final AppCompatTextView tv_content=holder.getView(R.id.tv_content);

                final IconTextView icon_up=holder.getView(R.id.icon_up);
                final AppCompatTextView tv_up=holder.getView(R.id.tv_up);
                final IconTextView icon_down=holder.getView(R.id.icon_down);
                final AppCompatTextView tv_down=holder.getView(R.id.tv_down);
                final IconTextView icon_push_read=holder.getView(R.id.icon_push_read);
                final AppCompatTextView tv_push_read=holder.getView(R.id.tv_push_read);

                final RecyclerView layout_discuss=holder.getView(R.id.layout_discuss);

                final DynamicEntity pushListEntity=entity.getField(PushMultipleFields.MORE_IMG);

                tv_title.setText(pushListEntity.getTitle()+"");
                tv_time.setText("1分钟前");
                tv_lable.setText(pushListEntity.getTitle()+"");
                tv_content.setText(pushListEntity.getContent());

                tv_up.setText("12");
                tv_down.setText("112");
                tv_push_read.setText("1.2万");
                layout_discuss.removeAllViews();
                break;
            case PushItemType.ON_IMG:
                final AppCompatTextView tv_title_one=holder.getView(R.id.tv_title);
                final AppCompatTextView tv_time_one=holder.getView(R.id.tv_time);
                final AppCompatTextView tv_lable_one=holder.getView(R.id.tv_lable);
                final AppCompatTextView tv_content_one=holder.getView(R.id.tv_content);

                final AppCompatImageView img_one=holder.getView(R.id.img_one);

                final IconTextView icon_up_one=holder.getView(R.id.icon_up);
                final AppCompatTextView tv_up_one=holder.getView(R.id.tv_up);
                final IconTextView icon_down_one=holder.getView(R.id.icon_down);
                final AppCompatTextView tv_down_one=holder.getView(R.id.tv_down);
                final IconTextView icon_push_read_one=holder.getView(R.id.icon_push_read);
                final AppCompatTextView tv_push_read_one=holder.getView(R.id.tv_push_read);

                final RecyclerView layout_discuss_one=holder.getView(R.id.layout_discuss);

                final DynamicEntity pushListEntity_one=entity.getField(PushMultipleFields.MORE_IMG);

                tv_title_one.setText(pushListEntity_one.getTitle()+"");
                tv_time_one.setText("1分钟前");
                tv_lable_one.setText(pushListEntity_one.getTitle()+"");
                tv_content_one.setText(pushListEntity_one.getContent());

                ImageHelper.imageNet(mContext,pushListEntity_one.getImgs(),img_one);

                tv_up_one.setText("12");
                tv_down_one.setText("112");
                tv_push_read_one.setText("1.2万");

                LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layout_discuss_one.setLayoutManager(layoutManager);

               /* final String discussJson=new Gson().toJson(pushListEntity_one.getDiscuss());
                final List<MultipleItemEntity> data=new DiscussConverter().setJsonData(discussJson).convert();
                final DiscussAdapter discussAdapter=new DiscussAdapter(data);
                layout_discuss_one.setAdapter(discussAdapter);*/
                break;
            case PushItemType.MOREN_IMG:
                final AppCompatTextView tv_title_more=holder.getView(R.id.tv_title);
                final AppCompatTextView tv_time_more=holder.getView(R.id.tv_time);
                final AppCompatTextView tv_lable_more=holder.getView(R.id.tv_lable);
                final AppCompatTextView tv_content_more=holder.getView(R.id.tv_content);
                final RecyclerView recycle_view_img_more=holder.getView(R.id.recycle_view_img_more);

                final DynamicEntity pushListEntity_more=entity.getField(PushMultipleFields.MORE_IMG);

                tv_title_more.setText(pushListEntity_more.getTitle()+"");
                tv_time_more.setText("1分钟前");
                tv_lable_more.setText(pushListEntity_more.getTitle()+"");
                tv_content_more.setText(pushListEntity_more.getContent());

                LinearLayoutManager layoutManagerMore = new LinearLayoutManager(mContext);
                layoutManagerMore.setOrientation(LinearLayoutManager.HORIZONTAL);
                recycle_view_img_more.setLayoutManager(layoutManagerMore);
                int decorationCount=recycle_view_img_more.getItemDecorationCount();
                if (decorationCount<=0){
                    recycle_view_img_more.addItemDecoration(new SpaceItemDecoration(8));
                }
                List<String> imagesUrl=new ArrayList<>();
                String[] imagesArry=pushListEntity_more.getImgs().split("`");
                Collections.addAll(imagesUrl,imagesArry);
                String imgsJson=new Gson().toJson(imagesUrl);

                List<MultipleItemEntity> imgData = new PushListImageConverter().setJsonData(imgsJson).convert();
                final PushListrImgesAdapter indexDiscoverImgesAdapter = new PushListrImgesAdapter(imgData);
                recycle_view_img_more.setAdapter(indexDiscoverImgesAdapter);
                break;
            default:
                break;
        }
    }



}
