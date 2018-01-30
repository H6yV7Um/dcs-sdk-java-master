package com.baidu.duer.dcs.banner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.Util;

import java.util.List;

/**
 * Created by kenway on 18/1/30 11:48
 * Email : xiaokai090704@126.com
 */

public class BannerMeishiAdapter extends RecyclerView.Adapter<BannerMeishiAdapter.MyViewHolder> {


    private List<Integer> list;
    private Context context;
    private String wap_url;

    public BannerMeishiAdapter(List<Integer> list, Context context, String wap_url) {
        this.list = list;
        this.context = context;
        this.wap_url=wap_url;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_banner, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageLoader.load(context, list.get(position), holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Util.isNullOrBlank(wap_url)) {
                    //如果没有tid ,调用默认的浏览器打开该链接
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(wap_url);
                    intent.setData(content_url);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.adapter_banner_iv);
        }
    }
}
