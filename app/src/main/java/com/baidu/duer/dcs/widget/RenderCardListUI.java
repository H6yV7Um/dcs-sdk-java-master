package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderCardPayload;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;
import com.baidu.duer.dcs.widget.adapter.RenderCardListAdapter;

import java.util.List;

/**
 * Created by kenway on 18/1/22 12:16
 * Email : xiaokai090704@126.com
 */

public class RenderCardListUI extends LinearLayout {

    private Context context;
    private RecyclerView rv;


    private TextView tv_more;
    private View view;

    public RenderCardListUI(Context context) {
        super(context);
        this.context = context;
        initViews();
    }


    public RenderCardListUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public RenderCardListUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    private void initViews() {

        view = LayoutInflater.from(context).inflate(R.layout.widget_render_list, this);
        rv = (RecyclerView) view.findViewById(R.id.widget_render_list_rv);

        rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        tv_more= (TextView) view.findViewById(R.id.widget_render_list_tv_more);

    }

    public void setData(final RenderCardPayload payload) {

        LogUtils.e("设置数据");
        List<RenderCardPayload.ListBean> list=payload.getList();
        RenderCardListAdapter adapter=new RenderCardListAdapter(context,list,payload.getLink());
        rv.setAdapter(adapter);

        tv_more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.isNullOrBlank(payload.getLink().getUrl())) {
                    //如果没有tid ,调用默认的浏览器打开该链接
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(payload.getLink().getUrl());
                    intent.setData(content_url);
                    context.startActivity(intent);
                }
            }
        });
    }

}
