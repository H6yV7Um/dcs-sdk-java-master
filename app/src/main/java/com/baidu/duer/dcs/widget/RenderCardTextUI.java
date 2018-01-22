package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderCardPayload;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;

/**
 * Created by kenway on 18/1/22 12:16
 * Email : xiaokai090704@126.com
 */

public class RenderCardTextUI extends LinearLayout {

    private Context context;
    private TextView tv_render;

    public RenderCardTextUI(Context context) {
        super(context);
this.context=context;
        initViews();
    }


    public RenderCardTextUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initViews();
    }

    public RenderCardTextUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initViews();
    }

    private void initViews() {

        View view = LayoutInflater.from(context).inflate(R.layout.widget_render_text, this);
        tv_render = (TextView) view.findViewById(R.id.widget_render_text);

    }

    public void setData(final RenderCardPayload payload) {

        LogUtils.e("TextCard==" + payload.getContent());
        tv_render.setText(payload.getContent());

        tv_render.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.isNull(payload.getLink())) {
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
