package com.linxz.androidstudy.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.pojo.ArticleControllerEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日0:25  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ArticleControllerAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ArticleControllerEntity> list;

    public ArticleControllerAdapter(Context context, List<ArticleControllerEntity> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ArticleControllerEntity getItem(int position) {
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
            convertView = inflater.inflate(R.layout.study_item_article_dialog, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ArticleControllerEntity entity=getItem(position);
        viewHolder.iconItem.setText(entity.getIcon());
        viewHolder.tvName.setText(entity.getName());
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
