package com.baidu.duer.dcs.widget.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderCardPayload;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;

import java.util.List;

/**
 * Created by kenway on 18/1/22 16:17
 * Email : xiaokai090704@126.com
 */

public class RenderCardListAdapter extends RecyclerView.Adapter<RenderCardListAdapter.MyViewHolder> {


    private Context context;

    private List<RenderCardPayload.ListBean> list;

    private RenderCardPayload.LinkBean linkBean;



    public RenderCardListAdapter(Context context, List<RenderCardPayload.ListBean> list,RenderCardPayload.LinkBean linkBean) {
        this.context = context;
        this.list = list;

        this.linkBean =linkBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_render_list, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final RenderCardPayload.ListBean listBean = list.get(position);

        if (!Util.isNull(list)) {


            if (!Util.isNull(listBean.getImage())) {
                ImageLoader.load(context, listBean.getImage().getSrc(), holder.iv);
            }

            holder.tv_title.setText(listBean.getTitle());
            holder.tv_content.setText(listBean.getContent());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Util.isNullOrBlank(linkBean.getUrl())) {
                        //如果没有tid ,调用默认的浏览器打开该链接
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(linkBean.getUrl());
                        intent.setData(content_url);
                        context.startActivity(intent);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return Util.isNull(list) ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView tv_title;
        public TextView tv_content;
        public ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.adapter_render_list_iv);
            tv_content = (TextView) itemView.findViewById(R.id.adapter_render_list_tv_content);
            tv_title = (TextView) itemView.findViewById(R.id.adapter_render_list_tv_title);

        }
    }
}
