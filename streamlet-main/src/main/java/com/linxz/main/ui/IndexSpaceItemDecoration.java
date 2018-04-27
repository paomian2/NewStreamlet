package com.linxz.main.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日2:22  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public IndexSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildAdapterPosition(view) != 0 && parent.getChildAdapterPosition(view) != 1) {
            outRect.bottom = space;
        }
    }
}
