package com.linxz.users.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.pojo.HomeItemEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日16:36  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class HomeItemAdapter extends BaseAdapter {

    private List<HomeItemEntity> list;
    private LayoutInflater inflater;

    public HomeItemAdapter(Context context, List<HomeItemEntity> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeItemEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.user_item_home, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        HomeItemEntity homeItemEntity=getItem(position);
        viewHolder.iconItem.setText(homeItemEntity.getIcon());
        viewHolder.tvName.setText(homeItemEntity.getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R2.id.icon_item)
        IconTextView iconItem;
        @BindView(R2.id.tv_name)
        AppCompatTextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
