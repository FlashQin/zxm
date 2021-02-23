package com.compy.check.utlis;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.compy.check.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {

    private int Round_dp = 10;
    private boolean isCheckUrl = false;

    public GlideImageLoader(int round_dp, boolean isCheckUrl) {
        Round_dp = round_dp;
        this.isCheckUrl = isCheckUrl;
    }

    public GlideImageLoader(int round_dp) {
        Round_dp = round_dp;
    }

    public GlideImageLoader() {
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (!isCheckUrl) {
            Glide.with(context)
                    .load(path)
                    .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(context, Round_dp))).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.test)
                    .skipMemoryCache(true)
                    .into(imageView);
        } else {
            try {
                Glide.with(context)
                        .load(CommonUtils.checkImgUrl(String.valueOf(path)))
                        .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(context, Round_dp))).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.test)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
