package com.linxz.users.ui.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.linxz.users.R;
import com.linxz.users.pojo.Cate;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月04日20:54  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class CateSpinerAdapter extends BaseAdapter{

    private List<Cate> cateList;
    private Context mContext;
    private LayoutInflater inflater;

    public static CateSpinerAdapter build(Context mContext,List<Cate> cateList){
        return new CateSpinerAdapter(mContext,cateList);
    }

    private CateSpinerAdapter(Context mContext,List<Cate> cateList){
        this.mContext=mContext;
        this.cateList=cateList;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return cateList.size();
    }

    @Override
    public Cate getItem(int position) {
        return cateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.user_item_spiner,null);
        }
        final AppCompatTextView tvCate=convertView.findViewById(R.id.tv_cate);
        final Cate cate=getItem(position);
        tvCate.setText(cate.getTypeName());
        tvCate.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        return convertView;
    }


}
