package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;

/**
 * Created by kenway on 18/1/18 18:19
 * Email : xiaokai090704@126.com
 */

public class PlayMusicUI extends LinearLayout {
    private Context context;

    private TextView tv_lrc;

    public PlayMusicUI(Context context) {
        super(context);
        this.context = context;
        initViews();
    }


    public PlayMusicUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }


    public PlayMusicUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    private void initViews() {

        View layout = LayoutInflater.from(context).inflate(R.layout.widget_play_music, this);

        tv_lrc = (TextView) layout.findViewById(R.id.tv_lrc);


    }

    public TextView getTextViewLrc() {
        return tv_lrc;
    }
}
