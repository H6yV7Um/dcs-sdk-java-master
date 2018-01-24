package com.baidu.duer.dcs.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


import com.baidu.duer.dcs.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv) {
//        Glide.with(context)
//                .load(url)
//                .into(iv);

        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).into(iv);
    }



    public static void load(Context context, int resId, ImageView iv) {
        Glide.with(context)
                .load(resId)
                .into(iv);
    }




}
