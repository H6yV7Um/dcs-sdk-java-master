package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderCardPayload;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.LogUtil;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;

/**
 * Created by kenway on 18/1/22 12:16
 * Email : xiaokai090704@126.com
 */

public class RenderCardStandadrUI extends LinearLayout {

    private Context context;
    private ImageView iv;
    private TextView tv_title;
    private TextView tv_content;

    private TextView tv_more;
    private View view;

    public RenderCardStandadrUI(Context context) {
        super(context);
        this.context = context;
        initViews();
    }


    public RenderCardStandadrUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public RenderCardStandadrUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    private void initViews() {

        view = LayoutInflater.from(context).inflate(R.layout.widget_render_standard, this);
        iv = (ImageView) view.findViewById(R.id.widget_render_standard_iv);
        tv_title = (TextView) view.findViewById(R.id.widget_render_standard_tv_title);
        tv_content = (TextView) view.findViewById(R.id.widget_render_standard_tv_content);

        tv_more= (TextView) view.findViewById(R.id.widget_render_standard_tv_more);

    }

    public void setData(final RenderCardPayload payload) {

        LogUtils.e("设置数据");

        if (!Util.isNullOrBlank(payload.getImage().getSrc())) {
            ImageLoader.load(context, payload.getImage().getSrc(), iv);
        } else {
            iv.setVisibility(GONE);
        }
        if (!Util.isNullOrBlank(payload.getTitle())) {
            tv_title.setText(payload.getTitle());
        } else {
            tv_title.setVisibility(GONE);
        }

        if (!Util.isNullOrBlank(payload.getContent())) {
            tv_content.setText(payload.getContent());
        } else {
            tv_content.setVisibility(GONE);
        }


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
