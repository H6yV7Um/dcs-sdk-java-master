package com.baidu.duer.dcs.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.baidu.duer.dcs.pccmodel.db.CoolWeatherDB;
import com.baidu.duer.dcs.pccmodel.model.City;
import com.baidu.duer.dcs.pccmodel.model.County;
import com.baidu.duer.dcs.pccmodel.model.Province;
import com.baidu.duer.dcs.pccmodel.util.HttpCallbackListener;
import com.baidu.duer.dcs.pccmodel.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by kenway on 18/1/16 15:35
 * Email : xiaokai090704@126.com
 */

public class LocationUtil {


    public static void getLocationString(Context context, LocationCallback call) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);//位置服务
        String provider = judgeProvider(context, locationManager);//位置提供器

        Location location;


        if (provider != null) {
            //为了压制getLast

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (null != call) call.onFailed("没有相对应的权限");
                return;
            }


            location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                getLocation(context, location, call);//得到当前经纬度并开启线程去反向地理编码
            } else {
                if (null != call) call.onFailed("暂时无法获得当前位置");
                LogUtils.e("location为null");
                Toast.makeText(context, "暂时无法获得当前位置", Toast.LENGTH_SHORT).show();

            }
        } else {//不存在位置提供器的情况

        }


    }

    private static void getLocation(final Context context, Location location, final LocationCallback call) {

        String latitude = location.getLatitude() + "";
        String longitude = location.getLongitude() + "";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=CPCdGvxtN09XG6G6clQ7QcqME8lH2p8c&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=0";




        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void finish(String response) {


                //renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":113.31957999999999,"lat":23.12608501102551},"formatted_address":"广东省广州市越秀区寺右新马路113","business":"五羊新城,岭南,珠江新城","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"广东省","city":"广州市","city_level":2,"district":"越秀区","town":"","adcode":"440104","street":"寺右新马路","street_number":"113","direction":"附近","distance":"2"},"pois":[],"roads":[],"poiRegions":[],"sematic_description":"五羊新城广场写字楼内0米","cityCode":257}})
                try {
                    String str = response;
                    str = str.replace("renderReverse&&renderReverse", "");
                    str = str.replace("(", "");
                    str = str.replace(")", "");
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(str);
                        JSONObject address = jsonObject.getJSONObject("result");
                        String city = address.getString("formatted_address");
                        String district = address.getString("sematic_description");

                        if (null != city && city.length() != 0) {
                            // "广东省广州市越秀区寺右新马路113",

                            CoolWeatherDB db = CoolWeatherDB.getInstance(context);

                            List<Province> provicesList = db.loadProvinces();
                            for (int i = 0; i < provicesList.size(); i++) {
                                if (city.contains(provicesList.get(i).getProvinceName())) {
                                    List<City> citysList = db.loadCitys(provicesList.get(i).getId());
                                    for (int j = 0; j < citysList.size(); j++) {
                                        if (city.contains(citysList.get(j).getCityName())) {
                                            List<County> countysList = db.loadCounty(citysList.get(j).getId());

                                            for (int c = 0; c < countysList.size(); c++) {
                                                if (city.contains(countysList.get(c).getCountyName())) {
                                                    if (null != call)
                                                        call.onSuccess(provicesList.get(i), citysList.get(j), countysList.get(c));
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (null != call) call.onFailed("不包含任何地方");
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                if (null != call) call.onFailed(msg);
            }
        });
    }

    private static String judgeProvider(Context context, LocationManager locationManager) {

        List<String> providerlist = locationManager.getProviders(true);
        LogUtils.e("provider.list===" + providerlist.toString());
        if (providerlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else if (providerlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else if (providerlist.contains(LocationManager.PASSIVE_PROVIDER)) {
            return LocationManager.PASSIVE_PROVIDER;
        } else {
            Toast.makeText(context, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }

        return null;

    }

    public interface LocationCallback {
        void onSuccess(Province province, City city, County county);

        void onFailed(String failMsg);
    }
}
