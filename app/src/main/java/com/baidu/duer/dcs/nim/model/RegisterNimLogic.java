package com.baidu.duer.dcs.nim.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.duer.dcs.nim.checksun.CheckSumBuilder;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kenway on 18/1/23 14:22
 * Email : xiaokai090704@126.com
 */

public class RegisterNimLogic {


    /**
     * 注册网易云信的接口
     */
    public static final String url = "https://api.netease.im/nimserver/user/create.action";

    /**
     * 注册网易云信
     *{"code":200,"info":{"token":"fd2eecce7bbb4830f97bfdc3eb511ca4","accid":"xiaokai","name":""}}
     * @throws IOException
     */

    public static void registerLogic(final Context context, final String acc, final String nonce, final OnModelCallback<String> callback)  {

      new Thread(new Runnable() {
          @Override
          public void run() {
              DefaultHttpClient httpClient = new DefaultHttpClient();
              HttpPost httpPost = new HttpPost(url);
              String appKey = readAppKey(context);
              String appSecrent = "b66467ef3f95";
              String curTime = String.valueOf((new Date()).getTime() / 1000L);
              String checkSum = CheckSumBuilder.getCheckSum(appSecrent, nonce, curTime);//参考 计算CheckSum的java代码

              // 设置请求的header
              httpPost.addHeader("AppKey", appKey);
              httpPost.addHeader("Nonce", nonce);
              httpPost.addHeader("CurTime", curTime);
              httpPost.addHeader("CheckSum", checkSum);
              httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

              try {
                  // 设置请求的参数
                  List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                  nvps.add(new BasicNameValuePair("accid", acc));
                  httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
                  // 执行请求
                  HttpResponse response = null;
                  response = httpClient.execute(httpPost);
                  //{"code":200,"info":{"token":"86222bfe03ddcb11883cceb31ff11e5f","accid":"helloworl","name":""}}
                  String bodyStr = null;
                  bodyStr = EntityUtils.toString(response.getEntity(), "utf-8");
                  if (!Util.isNull(callback)) callback.onModelSuccessed(bodyStr);

              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }).start();


    }


    public static String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
