package com.baidu.duer.dcs.pccmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.duer.dcs.pccmodel.model.WeatherBean;
import com.baidu.duer.dcs.pccmodel.util.HttpCallbackListener;
import com.baidu.duer.dcs.pccmodel.util.HttpUtil;
import com.baidu.duer.dcs.pccmodel.util.Utility;
import com.baidu.duer.dcs.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kenway on 18/1/17 12:04
 * Email : xiaokai090704@126.com
 */

public class WeatherLogic {


    public static void getWeahter(Context context, String weatherCode, WeatherCallback callback) {
        queryWeatheCode(context, weatherCode, callback);
    }

    private static void queryFromServer(final Context context, final String address, final String type, final WeatherCallback callback) {
        HttpCallbackListener listener = new HttpCallbackListener() {
            @Override
            public void finish(String response) {
                if ("countyCode".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        //从服务器返回的数据中简析出天气代号
                        String[] array = response.split("\\|");
                        if (array != null && array.length == 2) {
                            String weatherCode = array[1];
                            queryWeatherInfo(context, weatherCode, callback);
                        }
                    }
                } else if ("weatherCode".equals(type)) {

                    LogUtils.e("天气信息==" + response);

                    WeatherBean bean = new WeatherBean();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
                        String cityName = weatherInfo.getString("city");
                        String weatherCode = weatherInfo.getString("cityid");
                        String temp1 = weatherInfo.getString("temp1");
                        String temp2 = weatherInfo.getString("temp2");
                        String weatherDesp = weatherInfo.getString("weather");
                        String publishTime = weatherInfo.getString("ptime");

                        bean.setCity(cityName);
                        bean.setTemp1(temp1);
                        bean.setTemp2(temp2);
                        bean.setWeather(weatherDesp);


                        if (null != callback) callback.onSuccess(bean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void error(String msg) {

                if (null != callback) callback.onFailed(msg);
            }
        };
        HttpUtil.sendHttpRequest(address, listener);
    }

    /**
     * 查询县级代号所对应的天气代号
     *
     * @param countyCode
     */
    private static void queryWeatheCode(Context context, String countyCode, WeatherCallback callback) {
        String address = "http://www.weather.com.cn/data/list3/city" + countyCode + ".xml";
        queryFromServer(context, address, "countyCode", callback);

    }

    /**
     * 查询代号对应的天气
     *
     * @param weatherCode
     */
    private static void queryWeatherInfo(Context context, String weatherCode, WeatherCallback callback) {
        String address = "http://www.weather.com.cn/data/cityinfo/" + weatherCode + ".html";
        queryFromServer(context, address, "weatherCode", callback);

    }

    public interface WeatherCallback {
        void onSuccess(WeatherBean bean);

        void onFailed(String failMsg);
    }

}
