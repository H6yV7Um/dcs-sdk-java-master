package com.baidu.duer.dcs.nim.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.Util;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * Created by kenway on 18/1/23 14:22
 * Email : xiaokai090704@126.com
 */

public class LoginNimLogic {

    /**
     * 登录网易云信
     *
     * //{"code":200,"info":{"token":"86222bfe03ddcb11883cceb31ff11e5f","accid":"helloworl","name":""}}
     * //{"code":200,"info":{"token":"fd2eecce7bbb4830f97bfdc3eb511ca4","accid":"xiaokai","name":""}}
     * @param acc
     * @param call
     */
    public static void doLogin(String acc, String token, final OnModelCallback<LoginInfo> call) {



        LoginInfo info = new LoginInfo(acc, token); // config...

        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {

                        LogUtils.e("param=="+param.getAccount());
                        if(!Util.isNull(call)){
                            call.onModelSuccessed(param);
                        }
//


                    }

                    @Override
                    public void onFailed(int code) {
                        LogUtils.e("code=="+code);
                        if(Util.isNull(call)){
                            return;

                        }
                        if (code == 302 || code == 404) {

                            call.onModelFailed("账号和密码错误");

                        } else {

                            call.onModelFailed("登录失败");

                        }
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Log.e("LoginActivity", "exception==" + exception.getMessage());
                    }

                };

        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);


    }
}
