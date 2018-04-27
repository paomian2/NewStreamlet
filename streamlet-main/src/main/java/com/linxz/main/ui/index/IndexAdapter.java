package com.linxz.main.ui.index;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.ui.recycle.SpaceItemDecoration;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.main.R;
import com.linxz.main.pojo.index.BannerEntity;
import com.linxz.main.pojo.index.DiscoverEntity;
import com.linxz.main.pojo.index.NewsEntity;
import com.linxz.main.pojo.index.UserEntity;
import com.linxz.main.ui.callback.ActiveUsersItemClickListener;
import com.linxz.main.ui.callback.BannerCallBack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月09日20:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexAdapter extends MultipleRecyclerAdapter {

    private  ArrayList<BannerEntity> bannerEntitys;

    private BannerCallBack mBannerCallBack;


    public IndexAdapter setBannerClickListener(BannerCallBack bannerCallBack){
        this.mBannerCallBack=bannerCallBack;
        return this;
    }


    public IndexAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexItemType.TITLE, R.layout.item_index_text);
        addItemType(IndexItemType.BANNER, com.linxz.core.R.layout.item_multiple_banner);
        addItemType(IndexItemType.ACTIVE_USERS, R.layout.item_index_users);
        addItemType(IndexItemType.NEWS_IT, R.layout.item_index_news);
        addItemType(IndexItemType.DISCOVER_TEXT, R.layout.item_index_discover_text);
        addItemType(IndexItemType.DISCOVER_ONE_IMAGE, R.layout.item_index_discover_one_img);
        addItemType(IndexItemType.DISCOVER_MORE_IMAGE, R.layout.item_index_discover_more_img);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            //标题
            case IndexItemType.TITLE:
                final String title=entity.getField(IndexItemFields.TITLE);
                final AppCompatTextView tvItemTitle=holder.getView(R.id.tv_title);
                tvItemTitle.setText(title);
                break;
            //banner
            case IndexItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerEntitys = entity.getField(IndexItemFields.BANNER);
                    final ConvenientBanner<BannerEntity> convenientBanner = holder.getView(com.linxz.core.R.id.banner_recycler_item);
                    IndexBannerCreator.setDefault(convenientBanner,bannerEntitys,bannerClickListener);
                    mIsInitBanner = true;
                }
                break;
            //活跃用户
            case IndexItemType.ACTIVE_USERS:
                final List<UserEntity> userEntities = entity.getField(IndexItemFields.USERS);
                String userJson = new Gson().toJson(userEntities);

                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                final RecyclerView rvUsers = holder.getView(R.id.rv_users);
                rvUsers.setLayoutManager(layoutManager);
                int decorationCount=rvUsers.getItemDecorationCount();
                if (decorationCount<=0){
                    rvUsers.addItemDecoration(new SpaceItemDecoration(30));
                }
                rvUsers.addOnItemTouchListener(ActiveUsersItemClickListener.create());

                final List<MultipleItemEntity> data = new IndexUserConverter().setJsonData(userJson).convert();
                final IndexUserAdapter adapter = new IndexUserAdapter(data);
                rvUsers.setAdapter(adapter);
                break;
            //IT咨询列表
            case IndexItemType.NEWS_IT:
                final NewsEntity newsEntity = entity.getField(IndexItemFields.NEWS);
                final AppCompatImageView imgNews = holder.getView(R.id.img_news);
                final AppCompatTextView tvNewsDesc = holder.getView(R.id.tv_news_desc);

                ImageHelper.imageNet(mContext, newsEntity.getImages(), imgNews);
                tvNewsDesc.setText(newsEntity.getTitle());
                break;
            //帖子:纯文本
            case IndexItemType.DISCOVER_TEXT:
                final DiscoverEntity discoverEntity = entity.getField(IndexItemFields.DISCOVER);

                final CircleImageView imgUser = holder.getView(R.id.img_user);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_discover_title);
                final AppCompatTextView tvlable = holder.getView(R.id.tvlable);
                final AppCompatTextView tvTime = holder.getView(R.id.tv_time);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_desc);
                final AppCompatTextView tvRead = holder.getView(R.id.tv_look);
                final AppCompatTextView tvUp = holder.getView(R.id.tv_up);

                ImageHelper.imageNet(mContext, discoverEntity.getHeadimg(), imgUser);
                tvTitle.setText(discoverEntity.getTitle());
                tvlable.setText(discoverEntity.getLable());
                tvTime.setText("1小时前");
                tvDesc.setText(discoverEntity.getContent());
                tvRead.setText(discoverEntity.getReadCount() + "阅读");
                tvUp.setText(discoverEntity.getUserId() + "赞");
                break;
            //帖子:一张图
            case IndexItemType.DISCOVER_ONE_IMAGE:
                final DiscoverEntity discoverOneImgEntity = entity.getField(IndexItemFields.DISCOVER);

                final AppCompatTextView tvTitleOne = holder.getView(R.id.tv_discover_title);
                final AppCompatTextView tvLableOme = holder.getView(R.id.tvLable);
                final AppCompatImageView imgDescOne = holder.getView(R.id.img_desc1);
                final AppCompatTextView tvDescOne = holder.getView(R.id.tv_desc);
                final CircleImageView imgUserOne = holder.getView(R.id.img_user);
                final AppCompatTextView tvUserNameOne = holder.getView(R.id.tv_user_name);
                final AppCompatTextView tvReadOne = holder.getView(R.id.tv_look);
                final AppCompatTextView tvUpOne = holder.getView(R.id.tv_up);

                tvTitleOne.setText(discoverOneImgEntity.getTitle());
                tvLableOme.setText(discoverOneImgEntity.getLable());
                ImageHelper.imageNet(mContext, discoverOneImgEntity.getImgs(), imgDescOne);
                tvDescOne.setText(discoverOneImgEntity.getContent());
                ImageHelper.imageNet(mContext, discoverOneImgEntity.getHeadimg(), imgUserOne);
                tvUserNameOne.setText(discoverOneImgEntity.getName());
                tvReadOne.setText(discoverOneImgEntity.getReadCount() + "阅读");
                tvUpOne.setText(discoverOneImgEntity.getUserId() + "赞");
                break;
            //帖子:多张图
            case IndexItemType.DISCOVER_MORE_IMAGE:
                final DiscoverEntity discoverMoreImgEntity = entity.getField(IndexItemFields.DISCOVER);

                final AppCompatTextView tvTitleMore = holder.getView(R.id.tv_discover_title);
                final AppCompatTextView tvTimeMore = holder.getView(R.id.tv_time);
                final AppCompatTextView tvLableMore = holder.getView(R.id.tvlable);
                final AppCompatTextView tvDescMore = holder.getView(R.id.tv_desc);
                final AppCompatTextView tvUserName = holder.getView(R.id.tv_user_name);
                final AppCompatTextView tvReadMore = holder.getView(R.id.tv_look);
                final AppCompatTextView tvUpMore = holder.getView(R.id.tv_up);
                final CircleImageView imgUserMore = holder.getView(R.id.img_user);
                final RecyclerView rvImgs = holder.getView(R.id.rv_imgs);

                tvTitleMore.setText(discoverMoreImgEntity.getTitle());
                tvTimeMore.setText("10分钟前");
                tvLableMore.setText(discoverMoreImgEntity.getLable());
                tvDescMore.setText(discoverMoreImgEntity.getContent());
                tvUserName.setText(discoverMoreImgEntity.getName());
                tvReadMore.setText(discoverMoreImgEntity.getReadCount() + "阅读");
                tvUpMore.setText(discoverMoreImgEntity.getUp() + "赞");
                ImageHelper.imageNet(mContext, discoverMoreImgEntity.getHeadimg(), imgUserMore);

                String[] arrayImgs = discoverMoreImgEntity.getImgs().split("`");
                ArrayList<String> list = new ArrayList<>();
                Collections.addAll(list, arrayImgs);
                String imgsJson = new Gson().toJson(list);

                LinearLayoutManager layoutManagerMore = new LinearLayoutManager(mContext);
                layoutManagerMore.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvImgs.setLayoutManager(layoutManagerMore);
                int decrorationCount=rvImgs.getItemDecorationCount();
                if (decrorationCount ==0) {
                    rvImgs.addItemDecoration(new SpaceItemDecoration(5));
                }
                List<MultipleItemEntity> imgData = new IndexDiscoverImgesConverter().setJsonData(imgsJson).convert();
                final IndexDiscoverImgesAdapter indexDiscoverImgesAdapter = new IndexDiscoverImgesAdapter(imgData);
                rvImgs.setAdapter(indexDiscoverImgesAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        super.onItemClick(position);
    }

    /**banner点击事件*/
    private com.bigkoo.convenientbanner.listener.OnItemClickListener bannerClickListener=new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
             if (mBannerCallBack!=null){
                 mBannerCallBack.onBannerClick(bannerEntitys.get(position));
             }
        }
    };

}
