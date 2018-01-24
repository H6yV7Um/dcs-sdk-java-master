package com.baidu.duer.dcs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.devicemodule.screen.extend.card.message.RenderWeatherPayload;
import com.baidu.duer.dcs.util.ImageLoader;
import com.baidu.duer.dcs.util.LogUtil;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;
import com.bumptech.glide.Glide;

import java.util.HashMap;

/**
 * Created by kenway on 18/1/19 14:59
 * Email : xiaokai090704@126.com
 * <p>
 * 天气的界面现实
 */

public class WeatherUI extends LinearLayout {

    private Context context;

    private TextView tv_city;

    private TextView tv_date;
    private ImageView iv_today_status;

    private TextView tv_today_status;

    private TextView tv_today_temp;
    private TextView tv_today_decs;
    private TextView tv_today_quality;

    private WeatherItemUI time_1;
    private WeatherItemUI time_2;
    private WeatherItemUI time_3;
    private WeatherItemUI time_4;
    private WeatherItemUI time_5;

    public WeatherUI(Context context) {
        super(context);

        this.context=context;
        initViews();
    }


    public WeatherUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initViews();
    }

    public WeatherUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initViews();
    }

    private void initViews() {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_weather, this);
        tv_city = (TextView) view.findViewById(R.id.widget_weather_tv_city);
        tv_date = (TextView) view.findViewById(R.id.widget_weather_tv_date);
        iv_today_status = (ImageView) view.findViewById(R.id.widget_weather_iv_today_status);
        tv_today_status = (TextView) view.findViewById(R.id.widget_weather_tv_today_status);
        tv_today_temp = (TextView) view.findViewById(R.id.widget_weather_tv_today_temp);
        tv_today_decs = (TextView) view.findViewById(R.id.widget_weather_tv_today_decs);
        tv_today_quality = (TextView) view.findViewById(R.id.widget_weather_tv_today_quality);

        time_1 = (WeatherItemUI) view.findViewById(R.id.widget_weather_tv_itme_1);
        time_2 = (WeatherItemUI) view.findViewById(R.id.widget_weather_tv_itme_2);
        time_3 = (WeatherItemUI) view.findViewById(R.id.widget_weather_tv_itme_3);
        time_4 = (WeatherItemUI) view.findViewById(R.id.widget_weather_tv_itme_4);
        time_5 = (WeatherItemUI) view.findViewById(R.id.widget_weather_tv_itme_5);
        hashMapDate.put(Date.MON, "周一");
        hashMapDate.put(Date.TUE, "周二");
        hashMapDate.put(Date.WED, "周三");
        hashMapDate.put(Date.THUR, "周四");
        hashMapDate.put(Date.THU, "周四");
        hashMapDate.put(Date.FRI, "周五");
        hashMapDate.put(Date.SAT, "周六");
        hashMapDate.put(Date.SUN, "周日");

    }


    public void setData(RenderWeatherPayload data) {


        RenderWeatherPayload.WeatherForecastBean bean_first = data.getWeatherForecast().get(0);

        tv_city.setText(data.getCity());


        tv_date.setText(bean_first.getDate());

        if (!Util.isNullOrBlank(bean_first.getWeatherIcon().getSrc())) {
            ImageLoader.load(context, bean_first.getWeatherIcon().getSrc(), iv_today_status);


        }



        tv_today_status.setText(bean_first.getWeatherCondition());



        tv_today_temp.setText(bean_first.getLowTemperature() + " ~ " + bean_first.getHighTemperature());




        tv_today_decs.setText("今日 " + bean_first.getDate() + " " + bean_first.getWindCondition() + " 空气质量 " + bean_first.getCurrentPM25());

        tv_today_quality.setText(bean_first.getCurrentAirQuality());

        for (int i = 0; i < data.getWeatherForecast().size(); i++) {
            String url = data.getWeatherForecast().get(i).getWeatherIcon().getSrc();
            String str_temp = data.getWeatherForecast().get(i).getLowTemperature() + " ~ " + data.getWeatherForecast().get(i).getHighTemperature();
            String wind = data.getWeatherForecast().get(i).getWeatherCondition();
            String date = data.getWeatherForecast().get(i).getDay();
            if (i == 0) {
                time_1.setData("今日", url, str_temp, wind);
            } else if (i == 1) {
                time_2.setData("明日", url, str_temp, wind);
            } else if (i == 2) {

                time_3.setData(hashMapDate.get(date), url, str_temp, wind);
            } else if (i == 3) {
                time_4.setData(hashMapDate.get(date), url, str_temp, wind);
            } else if (i == 4) {
                time_5.setData(hashMapDate.get(date), url, str_temp, wind);
            }
        }
        LogUtils.e("data="+data.getCity());
    }

    public interface Date {
        String FRI = "FRI";
        String SAT = "SAT";
        String SUN = "SUN";
        String MON = "MON";
        String TUE = "TUE";
        String WED = "WED";
        String THUR = "THUR";
        String THU = "THU";
    }

    public HashMap<String, String> hashMapDate = new HashMap<>();
}
