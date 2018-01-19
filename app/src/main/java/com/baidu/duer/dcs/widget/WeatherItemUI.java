package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.Util;

/**
 * Created by kenway on 18/1/19 14:59
 * Email : xiaokai090704@126.com
 * <p>
 * 天气的界面现实
 */

public class WeatherItemUI extends LinearLayout {

    private Context context;

    private TextView tv_date;
    private ImageView iv_status;
    private TextView tv_status;
    private TextView tv_window;

    public WeatherItemUI(Context context) {
        super(context);
        this.context = context;
        initViews();
    }


    public WeatherItemUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public WeatherItemUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    private void initViews() {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_weather_item, this);
        tv_date = (TextView) view.findViewById(R.id.widget_w_item_tv_date);
        iv_status = (ImageView) view.findViewById(R.id.widget_w_item_iv_status);
        tv_status = (TextView) view.findViewById(R.id.widget_w_item_tv_status);
        tv_window = (TextView) view.findViewById(R.id.widget_w_item_tv_window);
    }


    public void setData(String date, String urlStatus, String tvStatus, String window) {



        tv_date.setText(date);
        //加载图片
        if (!Util.isNullOrBlank(urlStatus))
            ImageLoader.load(context, urlStatus, iv_status);
        tv_status.setText(tvStatus);

        tv_window.setText(window);
    }

    public  interface  Date{
        String FRI="FRI";
        String SAT="SAT";
        String SUN="SUN";
        String MON="MON";
        String TUE="TUE";
        String WED="WED";
        String THUR="THUR";
    }


}
