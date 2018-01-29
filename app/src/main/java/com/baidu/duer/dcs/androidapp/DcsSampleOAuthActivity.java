/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baidu.duer.dcs.androidapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.nim.acc.AccountManager;
import com.baidu.duer.dcs.nim.model.LoginNimLogic;
import com.baidu.duer.dcs.nim.model.OnModelCallback;
import com.baidu.duer.dcs.nim.model.RegisterNimLogic;
import com.baidu.duer.dcs.oauth.api.BaiduDialog;
import com.baidu.duer.dcs.oauth.api.BaiduDialogError;
import com.baidu.duer.dcs.oauth.api.BaiduException;
import com.baidu.duer.dcs.oauth.api.BaiduOauthImplicitGrant;
import com.baidu.duer.dcs.util.LogUtil;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.SPUtils;
import com.baidu.duer.dcs.util.Util;
import com.netease.nimlib.sdk.auth.LoginInfo;

/**
 * 用户认证界面
 * <p>
 * Created by zhangyan42@baidu.com on 2017/5/18.
 */
public class DcsSampleOAuthActivity extends DcsSampleBaseActivity implements View.OnClickListener {
    // 需要开发者自己申请client_id
    // client_id，就是oauth的client_id
    private static final String CLIENT_ID = "MOHcVMmvT0QxrKWkz2eWSeUV5GojGE3G";
    // 是否每次授权都强制登陆
    private boolean isForceLogin = false;
    // 是否每次都确认登陆
    private boolean isConfirmLogin = true;
    private EditText editTextClientId;
    private Button oauthLoginButton;
    private BaiduOauthImplicitGrant baiduOauthImplicitGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dcs_sample_activity_oauth);
        initView();
        setOnClickListener();


    }

    private void setOnClickListener() {
        oauthLoginButton.setOnClickListener(this);
    }

    private void initView() {
        editTextClientId = (EditText) findViewById(R.id.edit_client_id);
        oauthLoginButton = (Button) findViewById(R.id.btn_login);

        editTextClientId.setText(CLIENT_ID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String clientId = editTextClientId.getText().toString();
                if (!TextUtils.isEmpty(clientId) && !TextUtils.isEmpty(clientId)) {
                    baiduOauthImplicitGrant = new BaiduOauthImplicitGrant(clientId, DcsSampleOAuthActivity.this.getApplication());
                    baiduOauthImplicitGrant.authorize(DcsSampleOAuthActivity.this, isForceLogin, isConfirmLogin, new BaiduDialog
                            .BaiduDialogListener() {
                        @Override
                        public void onComplete(Bundle values) {
                            Toast.makeText(DcsSampleOAuthActivity.this.getApplicationContext(),
                                    getResources().getString(R.string.login_succeed),
                                    Toast.LENGTH_SHORT).show();
                            startMainActivity();
                        }

                        @Override
                        public void onBaiduException(BaiduException e) {

                        }

                        @Override
                        public void onError(BaiduDialogError e) {
                            if (null != e) {
                                String toastString = TextUtils.isEmpty(e.getMessage())
                                        ? DcsSampleOAuthActivity.this.getResources()
                                        .getString(R.string.err_net_msg) : e.getMessage();
                                Toast.makeText(DcsSampleOAuthActivity.this.getApplicationContext(), toastString,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancel() {
                            LogUtil.d("cancle", "I am back");
                        }
                    });
                } else {
                    Toast.makeText(DcsSampleOAuthActivity.this.getApplicationContext(),
                            getResources().getString(R.string.client_id_empty),
                            Toast.LENGTH_SHORT).show();
                }

                dologinNim();
                break;
            default:
                break;
        }
    }

    private void dologinNim() {


        String account;
        String token;
/**
 * {"code":200,"info":{"token":"ca627e6eecb9d8dcc7daa6df16f6daca","accid":"han","name":""}}
 * {"code":200,"info":{"token":"fd2eecce7bbb4830f97bfdc3eb511ca4","accid":"xiaokai","name":""}}
 */
        if (DcsSampleApplication.loginFirst) {
            account = "han";
            token = "ca627e6eecb9d8dcc7daa6df16f6daca";
        } else {
            account = "xiaokai";
            token = "fd2eecce7bbb4830f97bfdc3eb511ca4";
        }


        LoginNimLogic.doLogin(account, token, new OnModelCallback<LoginInfo>() {
            @Override
            public void onModelSuccessed(LoginInfo loginInfo) {
                LogUtils.e("登录成功,account="+loginInfo.getAccount());
                AccountManager.getInstance().save(loginInfo.getAccount(), loginInfo.getToken(), RegisterNimLogic.readAppKey(DcsSampleOAuthActivity.this));

            }

            @Override
            public void onModelFailed(String failedMsg) {
                LogUtils.e("登录成功,account="+failedMsg);
            }
        });
    }

    private void startMainActivity() {

        LogUtils.e("开始启动Main");
        Intent intent = new Intent(DcsSampleOAuthActivity.this, DcsSampleMainActivity.class);
        intent.putExtra("baidu", baiduOauthImplicitGrant);
        startActivity(intent);
        finish();
    }
}