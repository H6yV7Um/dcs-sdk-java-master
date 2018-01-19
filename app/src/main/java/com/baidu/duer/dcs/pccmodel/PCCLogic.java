package com.baidu.duer.dcs.pccmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.baidu.duer.dcs.pccmodel.db.CoolWeatherDB;
import com.baidu.duer.dcs.pccmodel.model.City;
import com.baidu.duer.dcs.pccmodel.model.County;
import com.baidu.duer.dcs.pccmodel.model.Province;
import com.baidu.duer.dcs.pccmodel.util.HttpCallbackListener;
import com.baidu.duer.dcs.pccmodel.util.HttpUtil;
import com.baidu.duer.dcs.pccmodel.util.Utility;
import com.baidu.duer.dcs.util.LogUtil;
import com.baidu.duer.dcs.util.LogUtils;

import java.util.List;

/**
 * Created by kenway on 18/1/16 17:40
 * Email : xiaokai090704@126.com
 */

public class PCCLogic {
    public static CoolWeatherDB coolWeatherDB;


    public static void getPCC(Context context) {
        queryProvinces(context);
    }

    /**
     * 查询省列表
     */
    private static void queryProvinces(final Context context) {

        if (null == coolWeatherDB) {
            coolWeatherDB = CoolWeatherDB.getInstance(context);
        }
        List<Province> provinceList = coolWeatherDB.loadProvinces();
        if (provinceList.size() != 0) {
            LogUtils.e("不用加载");
        } else {
            String adress = "http://www.weather.com.cn/data/list3/city.xml";
            HttpCallbackListener listener = new HttpCallbackListener() {
                @Override
                public void finish(String response) {
                    boolean success = Utility.handleProvincesResponse(coolWeatherDB, response);

                    if (success) {
                        LogUtils.e("获取省成功,开始获取市");
                        List<Province> provinceList = coolWeatherDB.loadProvinces();
                        for (int i = 0; i < provinceList.size(); i++) {
                            queryCitys(context, provinceList.get(i).getProvinceCode(), provinceList.get(i).getId());
                        }

                    }
                }

                @Override
                public void error(String msg) {
                    LogUtils.e("msg");
                }
            };
            HttpUtil.sendHttpRequest(adress, listener);

        }


    }

    /**
     * 查询市列表
     */
    private static void queryCitys(final Context context, String code, final int provinceId) {

        if (null == coolWeatherDB) {
            coolWeatherDB = CoolWeatherDB.getInstance(context);
        }
        List<City> cityList = coolWeatherDB.loadCitys(provinceId);
        if (cityList.size() != 0) {
            LogUtils.e("不用加载");
        } else {
            String adress = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
            HttpCallbackListener listener = new HttpCallbackListener() {
                @Override
                public void finish(String response) {
                    boolean success = Utility.hanleCityResponse(coolWeatherDB, response, provinceId);

                    if (success) {
                        LogUtils.e("获取市成功,开始获取县");
                        List<City> cityList1 = coolWeatherDB.loadCitys(provinceId);
                        for (int i = 0; i < cityList1.size(); i++) {
                            queryCountys(context, cityList1.get(i).getCityCode(), cityList1.get(i).getId());
                        }

                    }
                }

                @Override
                public void error(String msg) {
                    LogUtils.e("msg");
                }
            };
            HttpUtil.sendHttpRequest(adress, listener);

        }


    }


    /**
     * 查询市列表
     */
    private static void queryCountys(final Context context, String code, final int cityId) {

        if (null == coolWeatherDB) {
            coolWeatherDB = CoolWeatherDB.getInstance(context);
        }
        List<County> cityList = coolWeatherDB.loadCounty(cityId);
        if (cityList.size() != 0) {
            LogUtils.e("不用加载");
        } else {
            String adress = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
            HttpCallbackListener listener = new HttpCallbackListener() {
                @Override
                public void finish(String response) {
                    boolean success = Utility.handleCountyResponse(coolWeatherDB, response, cityId);

                    if (success) {
                        LogUtils.e("获取县成功");

                    }
                }

                @Override
                public void error(String msg) {
                    LogUtils.e("msg");
                }
            };
            HttpUtil.sendHttpRequest(adress, listener);

        }


    }

}
