package com.linxz.androidstudy.ui;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.linxz.androidstudy.R;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日14:02  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ArticleListAdapter extends MultipleRecyclerAdapter{

    private OnArticleItemClickListener onItemClickListener;

    public void setOnArticleItemClickListener(OnArticleItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public ArticleListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.DISCOVER_TEXT, R.layout.study_item_study_articlelist);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case ItemType.DISCOVER_TEXT:
                final ArticleEntity articleEntity=entity.getField(MultipleFields.TITLE);
                final AppCompatTextView tvTitle=holder.getView(R.id.tv_title);
                tvTitle.setText(articleEntity.getTitle());
                tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener!=null){
                            onItemClickListener.onClick(articleEntity.getBasicKnowledgeId());
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public interface OnArticleItemClickListener{
        /**
         * @param id:文章Id
         * */
        void onClick(String id);
    }
}
