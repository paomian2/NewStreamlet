package com.linxz.core.utils.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.view.WheelView;
import com.linxz.core.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月02日21:45  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicPickerDialog {

    private Context mContext;
    private List<String> mOptionsItems=new ArrayList<>();
    private OnItemSelectedListener mOnItemSelectedListener;
    private int itemIndex;
    private String itemDesc;

    private BasicPickerDialog(Context context,List<String> optionsItems,OnItemSelectedListener onItemSelectedListener){
        this.mContext=context;
        this.mOptionsItems=optionsItems;
        this.mOnItemSelectedListener=onItemSelectedListener;
    }

    public static BasicPickerDialogBuiler builder(){
        return new BasicPickerDialogBuiler();
    }

    public static class BasicPickerDialogBuiler{
        private Context mContext;
        private List<String> mOptionsItems=new ArrayList<>();
        private OnItemSelectedListener mOnItemSelectedListener;

        public BasicPickerDialogBuiler withContext(Context context){
            this.mContext=context;
            return this;
        }

        public BasicPickerDialogBuiler withOptionsItems(List<String> optionsItems){
            if (optionsItems!=null && optionsItems.size()>0){
                this.mOptionsItems.addAll(optionsItems);
            }
            return this;
        }

        public BasicPickerDialogBuiler withsItemSelectdListener(OnItemSelectedListener onItemSelectedListener){
            this.mOnItemSelectedListener=onItemSelectedListener;
            return this;
        }

        public BasicPickerDialog build(){
           return new BasicPickerDialog(mContext,mOptionsItems,mOnItemSelectedListener);
        }

    }


    /**token失效Dialogs*/
    public void showPickerDialog() {
        try {
            final Dialog dialog = new Dialog(mContext, R.style.CustomDialog);
            @SuppressLint("InflateParams")
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_basic_picker, null);
            WheelView wheelView = contentView.findViewById(R.id.wheelview);

            wheelView.setCyclic(false);
            wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
            wheelView.setOnItemSelectedListener(new com.contrarywind.listener.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    itemIndex=index;
                    itemDesc=mOptionsItems.get(index);
                }
            });

            contentView.findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemSelectedListener!=null){
                        mOnItemSelectedListener.onItemSelected(itemIndex,itemDesc);
                    }
                    dialog.dismiss();
                }
            });

            contentView.findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            AppCompatTextView tv_left=contentView.findViewById(R.id.tv_left);
            AppCompatTextView tv_right=contentView.findViewById(R.id.tv_right);
            dialog.setContentView(contentView);
            dialog.show();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
