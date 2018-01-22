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

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.duer.dcs.R;
import com.baidu.duer.dcs.androidsystemimpl.PlatformFactoryImpl;
import com.baidu.duer.dcs.androidsystemimpl.webview.BaseWebView;
import com.baidu.duer.dcs.devicemodule.screen.ApiConstants;
import com.baidu.duer.dcs.devicemodule.screen.ScreenDeviceModule;
import com.baidu.duer.dcs.devicemodule.screen.extend.card.ScreenExtendDeviceModule;
import com.baidu.duer.dcs.devicemodule.screen.extend.card.message.RenderPlayerInfoPayload;
import com.baidu.duer.dcs.devicemodule.screen.extend.card.message.RenderWeatherPayload;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderCardPayload;
import com.baidu.duer.dcs.devicemodule.screen.message.RenderVoiceInputTextPayload;
import com.baidu.duer.dcs.devicemodule.voiceinput.VoiceInputDeviceModule;
import com.baidu.duer.dcs.framework.DcsFramework;
import com.baidu.duer.dcs.framework.DeviceModuleFactory;
import com.baidu.duer.dcs.framework.IResponseListener;
import com.baidu.duer.dcs.framework.message.Directive;
import com.baidu.duer.dcs.http.HttpConfig;
import com.baidu.duer.dcs.bean.MusicLrcBean;
import com.baidu.duer.dcs.oauth.api.IOauth;
import com.baidu.duer.dcs.oauth.api.OauthImpl;
import com.baidu.duer.dcs.pccmodel.WeatherLogic;
import com.baidu.duer.dcs.pccmodel.model.City;
import com.baidu.duer.dcs.pccmodel.model.County;
import com.baidu.duer.dcs.pccmodel.model.Province;
import com.baidu.duer.dcs.pccmodel.model.WeatherBean;
import com.baidu.duer.dcs.pccmodel.util.HttpCallbackListener;
import com.baidu.duer.dcs.pccmodel.util.HttpUtil;
import com.baidu.duer.dcs.systeminterface.IPlatformFactory;
import com.baidu.duer.dcs.systeminterface.IWakeUp;
import com.baidu.duer.dcs.util.CommonUtil;
import com.baidu.duer.dcs.util.DateUtils;
import com.baidu.duer.dcs.util.JsonUtil;
import com.baidu.duer.dcs.util.LocationUtil;
import com.baidu.duer.dcs.util.LogUtil;
import com.baidu.duer.dcs.util.LogUtils;
import com.baidu.duer.dcs.util.NetWorkUtil;
import com.baidu.duer.dcs.util.ToastUtils;
import com.baidu.duer.dcs.util.Util;
import com.baidu.duer.dcs.wakeup.WakeUp;
import com.baidu.duer.dcs.widget.PlayMusicUI;
import com.baidu.duer.dcs.widget.RenderCardStandadrUI;
import com.baidu.duer.dcs.widget.RenderCardTextUI;
import com.baidu.duer.dcs.widget.WeatherUI;

import java.io.File;

/**
 * 主界面 activity
 * <p>
 * Created by zhangyan42@baidu.com on 2017/5/18.
 */
public class DcsSampleMainActivity extends DcsSampleBaseActivity implements View.OnClickListener {
    public static final String TAG = "DcsDemoActivity";
    private Button voiceButton;
    private TextView textViewTimeStopListen;
    private TextView textViewRenderVoiceInputText;
    private BaseWebView webView;
    private LinearLayout mTopLinearLayout;

    private LinearLayout layout_wakeup_after;
    private DcsFramework dcsFramework;
    private DeviceModuleFactory deviceModuleFactory;
    private IPlatformFactory platformFactory;
    private long startTimeStopListen;
    private boolean isStopListenReceiving;
    private String mHtmlUrl;
    // 唤醒
    private WakeUp wakeUp;

    //唤醒之前的界面
    private RelativeLayout layout_wakeup_before;

    private TextView tv_time;
    private TextView tv_date;

    private TextView tv_addr;
    private TextView tv_weahter;

    private static final String NO_LRC = "没有找到歌词";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dcs_sample_activity_main);
        initView();
        initOauth();
        initFramework();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initOauth();
    }

    private void initView() {


        layout_wakeup_before = (RelativeLayout) findViewById(R.id.wakeup_before_layout);
        tv_time = (TextView) findViewById(R.id.wakeup_before_tv_time);
        tv_date = (TextView) findViewById(R.id.wakeup_before_tv_date);
        initDateTime();
        //获取地理位置
        tv_addr = (TextView) findViewById(R.id.wakeup_before_weather_tv_address);
        tv_weahter = (TextView) findViewById(R.id.wakeup_before_weather_tv_weather);
        LocationUtil.getLocationString(this, new LocationUtil.LocationCallback() {
            @Override
            public void onSuccess(Province province, City city, County county) {
                LogUtils.e(province.toString() + "," + city.toString() + "," + county.toString());

                WeatherLogic.getWeahter(DcsSampleMainActivity.this, county.getCountyCode(), new WeatherLogic.WeatherCallback() {
                    @Override
                    public void onSuccess(final WeatherBean bean) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_addr.setText(bean.getCity());

                                tv_weahter.setText(bean.getTemp1() + "° ~ " + bean.getTemp2() + "  " + bean.getWeather());
                            }
                        });


                    }

                    @Override
                    public void onFailed(String failMsg) {

                    }
                });
            }

            @Override
            public void onFailed(String failMsg) {

                tv_weahter.setText("无法获取当前位置");
            }
        });
        //唤醒之后

        layout_wakeup_after = (LinearLayout) findViewById(R.id.wakeup_after_layout);
        voiceButton = (Button) findViewById(R.id.voiceBtn);
        voiceButton.setOnClickListener(this);

        textViewTimeStopListen = (TextView) findViewById(R.id.id_tv_time_0);
        textViewRenderVoiceInputText = (TextView) findViewById(R.id.id_tv_RenderVoiceInputText);
        mTopLinearLayout = (LinearLayout) findViewById(R.id.topLinearLayout);

        webView = new BaseWebView(DcsSampleMainActivity.this.getApplicationContext());
        webView.setWebViewClientListen(new BaseWebView.WebViewClientListener() {
            @Override
            public BaseWebView.LoadingWebStatus shouldOverrideUrlLoading(WebView view, String url) {
                // 拦截处理不让其点击
                return BaseWebView.LoadingWebStatus.STATUS_FALSE;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!url.equals(mHtmlUrl) && !"about:blank".equals(mHtmlUrl)) {

                    //这里可以使用独立浏览器,显示页面
                    platformFactory.getWebView().linkClicked(url);
                }
                mHtmlUrl = url;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
        });
//        mTopLinearLayout.addView(webView);


    }


    private void initFramework() {
        platformFactory = new PlatformFactoryImpl(this);
        platformFactory.setWebView(webView);
        dcsFramework = new DcsFramework(platformFactory);
        deviceModuleFactory = dcsFramework.getDeviceModuleFactory();

        deviceModuleFactory.createVoiceOutputDeviceModule();
        deviceModuleFactory.createVoiceInputDeviceModule();
        deviceModuleFactory.getVoiceInputDeviceModule().addVoiceInputListener(
                new VoiceInputDeviceModule.IVoiceInputListener() {
                    @Override
                    public void onStartRecord() {
                        LogUtil.d(TAG, "onStartRecord");
                        startRecording();
                    }

                    @Override
                    public void onFinishRecord() {
                        LogUtil.d(TAG, "onFinishRecord");
                        stopRecording();
                    }

                    public void onSucceed(int statusCode) {
                        LogUtil.d(TAG, "onSucceed-statusCode:" + statusCode);
                        if (statusCode != 200) {
                            stopRecording();
                            Toast.makeText(DcsSampleMainActivity.this,
                                    getResources().getString(R.string.voice_err_msg),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        LogUtil.d(TAG, "onFailed-errorMessage:" + errorMessage);
                        stopRecording();
                        Toast.makeText(DcsSampleMainActivity.this,
                                getResources().getString(R.string.voice_err_msg),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        deviceModuleFactory.createAlertsDeviceModule();

        deviceModuleFactory.createAudioPlayerDeviceModule();

        deviceModuleFactory.createSystemDeviceModule();
        deviceModuleFactory.createSpeakControllerDeviceModule();
        deviceModuleFactory.createPlaybackControllerDeviceModule();
        deviceModuleFactory.createScreenDeviceModule();
        deviceModuleFactory.createScreenExtendDeviceModule();

        deviceModuleFactory.getScreenDeviceModule()
                .addRenderVoiceInputTextListener(new ScreenDeviceModule.IRenderVoiceInputTextListener() {
                    @Override
                    public void onRenderVoiceInputText(RenderVoiceInputTextPayload payload) {
                        textViewRenderVoiceInputText.setText(payload.text);


                    }

                });

        deviceModuleFactory.getScreenExtendDeviceModule().addListener(new ScreenExtendDeviceModule.IRenderExtendListener() {
            @Override
            public void onRenderDirective(Directive directive) {
                mTopLinearLayout.removeAllViews();
                if (directive.getHeader().getName().equals(com.baidu.duer.dcs.devicemodule.screen.extend.card.ApiConstants.Directives.RenderWeather.NAME)) {
                    //查询天气时返回json
                    handleRenderWeather(directive);
                } else if (directive.getHeader().getName().equals(com.baidu.duer.dcs.devicemodule.screen.extend.card.ApiConstants.Directives.RenderPlayerInfo.NAME)) {
                    handlePlayMusicLrc(directive);
                    //播放音乐
                }

            }
        });

        //设置RenderCard


        deviceModuleFactory.getScreenDeviceModule().addRenderListener(new ScreenDeviceModule.IRenderListener() {
            @Override
            public void onRenderDirective(Directive directive) {
                //从directive 中获取playload
                mTopLinearLayout.removeAllViews();

//{header=
// Header{namespace='ai.dueros.device_interface.screen', name='RenderCard'} id:NWE1ZjFiYzFlZTc0OA== dialogRequestId:742be235-8c8f-4af2-ba28-14889f54da1e,
// payload=com.baidu.duer.dcs.framework.message.Payload@d30e4c2,
// payLoad='{"type":"TextCard","content":"你好，很高兴见到你","token":"eyJib3RfaWQiOiJ1cyIsInJlc3VsdF90b2tlbiI6IjVlNGU5NWQxLTBlNDktNGMxNi1iNDI1LTBiYzUyYmU5ODk1YyIsImJvdF90b2tlbiI6Im51bGwifQ=="}',
// rawMessage='{"header":{"namespace":"ai.dueros.device_interface.screen","name":"RenderCard","messageId":"NWE1ZjFiYzFlZTc0OA==","dialogRequestId":"742be235-8c8f-4af2-ba28-14889f54da1e"},"payload":{"type":"TextCard","content":"你好，很高兴见到你","token":"eyJib3RfaWQiOiJ1cyIsInJlc3VsdF90b2tlbiI6IjVlNGU5NWQxLTBlNDktNGMxNi1iNDI1LTBiYzUyYmU5ODk1YyIsImJvdF90b2tlbiI6Im51bGwifQ=="}}'}


                LogUtils.e("获取到的RenderCardPayload数据===" + directive.getRawMessage());

                if (directive.getHeader().getName().equals(ApiConstants.Directives.RenderCard.NAME)) {

                    LogUtils.e("待处理数据");
                    RenderCardPayload renderCardPayload = JsonUtil.jsonToBean(directive.getPayloadStr(), RenderCardPayload.class);
                    handleRenderCardPayLoad(renderCardPayload);
                } else if (directive.getHeader().getName().equals(ApiConstants.Directives.RenderHint.NAME)) {

                }

            }
        });

        // init唤醒
        wakeUp = new WakeUp(platformFactory.getWakeUp(),
                platformFactory.getAudioRecord());
        wakeUp.addWakeUpListener(wakeUpListener);
        // 开始录音，监听是否说了唤醒词
        wakeUp.startWakeUp();
    }

    /**
     * 处理天气
     *
     * @param directive
     */
    private void handleRenderWeather(Directive directive) {


        if (Util.isNull(directive) || Util.isNullOrBlank(directive.getPayloadStr())) {
            //没有找歌词
            return;
        }
        RenderWeatherPayload renderWeatherPayload = JsonUtil.jsonToBean(directive.getPayloadStr(), RenderWeatherPayload.class);

        WeatherUI weatherUI = new WeatherUI(DcsSampleMainActivity.this);
        weatherUI.setData(renderWeatherPayload);
        mTopLinearLayout.addView(weatherUI);
    }

    /**
     * 处理音乐
     *
     * @param directive
     */
    private void handlePlayMusicLrc(Directive directive) {
        LogUtils.e("获取到的RenderCardPayload数据===" + directive.getPayloadStr());

        if (Util.isNull(directive) || Util.isNullOrBlank(directive.getPayloadStr())) {
            //没有找歌词
            PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
            playMusicUI.getTextViewLrc().setText(NO_LRC);
            mTopLinearLayout.addView(playMusicUI);
            return;
        }

        RenderPlayerInfoPayload playerInfoPayload = JsonUtil.jsonToBean(directive.getPayloadStr(), RenderPlayerInfoPayload.class);

        if (Util.isNull(playerInfoPayload)) {
            //没有获取找到相关歌词
            PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
            playMusicUI.getTextViewLrc().setText(NO_LRC);
            mTopLinearLayout.addView(playMusicUI);
            return;
        }

        String lrcUrl = playerInfoPayload.getContent().getLyric().getUrl();
        if (Util.isNullOrBlank(lrcUrl)) {
            //没有获取找到相关歌词
            PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
            playMusicUI.getTextViewLrc().setText(NO_LRC);
            mTopLinearLayout.addView(playMusicUI);
            return;
        }

        //查询歌词
        HttpUtil.sendHttpRequest(playerInfoPayload.getContent().getLyric().getUrl(), new HttpCallbackListener() {
            @Override
            public void finish(String response) {

                if (Util.isNullOrBlank(response)) {
                    PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
                    playMusicUI.getTextViewLrc().setText(NO_LRC);
                    mTopLinearLayout.addView(playMusicUI);
                    //没有获取找到相关歌词
                } else {
                    final MusicLrcBean bean = JsonUtil.jsonToBean(response, MusicLrcBean.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
                            playMusicUI.getTextViewLrc().setText(bean.getData().getLycContent());
                            mTopLinearLayout.addView(playMusicUI);
                        }
                    });


                }

            }

            @Override
            public void error(String msg) {
                //没有获取找到相关歌词

                PlayMusicUI playMusicUI = new PlayMusicUI(DcsSampleMainActivity.this);
                playMusicUI.getTextViewLrc().setText(NO_LRC);
                mTopLinearLayout.addView(playMusicUI);
            }
        });


    }

    //处理RenderCardPlayLoad
    private void handleRenderCardPayLoad(RenderCardPayload payLoad) {
        switch (payLoad.getType()) {
            case RenderCardPayload.TYPE.TEXTCARD:
                handleRenderCardText(payLoad);
                break;
            case RenderCardPayload.TYPE.STANDARDCARD:
                handleRenderCardStandard(payLoad);
                break;
            case RenderCardPayload.TYPE.IMAGELISTCARD:
                break;
            case RenderCardPayload.TYPE.LISTCARD:
                break;
            default:
                break;
        }

    }


    private void handleRenderCardText(RenderCardPayload payLoad) {
        RenderCardTextUI renderCardTextUI = new RenderCardTextUI(this);
        renderCardTextUI.setData(payLoad);
        mTopLinearLayout.addView(renderCardTextUI);
    }

    private void handleRenderCardStandard(RenderCardPayload payLoad) {

        LogUtils.e("处理standard");
        RenderCardStandadrUI renderCardStandard = new RenderCardStandadrUI(this);
        renderCardStandard.setData(payLoad);
        mTopLinearLayout.addView(renderCardStandard);
    }

    private IWakeUp.IWakeUpListener wakeUpListener = new IWakeUp.IWakeUpListener() {
        @Override
        public void onWakeUpSucceed() {
            ToastUtils.showForumToast(DcsSampleMainActivity.this, getResources().getString(R.string.wakeup_succeed), R.drawable.pop_icon_toast_success);
            //唤醒之后该界面消失
            layout_wakeup_after.setVisibility(View.VISIBLE);
            layout_wakeup_before.setVisibility(View.GONE);
            //自动触发点击事件
//            voiceButton.performClick();
        }
    };

    private void doUserActivity() {
        deviceModuleFactory.getSystemProvider().userActivity();
    }

    private void initOauth() {
        IOauth baiduOauth = new OauthImpl();
        if (baiduOauth.isSessionValid()) {
            HttpConfig.setAccessToken(baiduOauth.getAccessToken());
        } else {
            baiduOauth.authorize();
        }
    }

    private void stopRecording() {
        wakeUp.startWakeUp();
        isStopListenReceiving = false;
        voiceButton.setText(getResources().getString(R.string.stop_record));
        long t = System.currentTimeMillis() - startTimeStopListen;
        textViewTimeStopListen.setText(getResources().getString(R.string.time_record, t));
    }

    private void startRecording() {
        wakeUp.stopWakeUp();
        isStopListenReceiving = true;
        deviceModuleFactory.getSystemProvider().userActivity();
        voiceButton.setText(getResources().getString(R.string.start_record));
        textViewTimeStopListen.setText("");
        textViewRenderVoiceInputText.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voiceBtn:
                if (!NetWorkUtil.isNetworkConnected(this)) {
                    Toast.makeText(this,
                            getResources().getString(R.string.err_net_msg),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (CommonUtil.isFastDoubleClick()) {
                    //判断是不是双击
                    return;
                }
                if (isStopListenReceiving) {
                    platformFactory.getVoiceInput().stopRecord();
                    isStopListenReceiving = false;
                    return;
                }
                isStopListenReceiving = true;
                startTimeStopListen = System.currentTimeMillis();
                platformFactory.getVoiceInput().startRecord();
                doUserActivity();
                break;
            default:
                break;
        }
    }

    //播放音乐时的接口
    private IResponseListener playPauseResponseListener = new IResponseListener() {
        @Override
        public void onSucceed(int statusCode) {
            if (statusCode == 204) {
                Toast.makeText(DcsSampleMainActivity.this,
                        getResources().getString(R.string.no_directive),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onFailed(String errorMessage) {
            Toast.makeText(DcsSampleMainActivity.this,
                    getResources().getString(R.string.request_error),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    };

    private IResponseListener nextPreResponseListener = new IResponseListener() {
        @Override
        public void onSucceed(int statusCode) {
            if (statusCode == 204) {
                Toast.makeText(DcsSampleMainActivity.this,
                        getResources().getString(R.string.no_audio),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

        @Override
        public void onFailed(String errorMessage) {
            Toast.makeText(DcsSampleMainActivity.this,
                    getResources().getString(R.string.request_error),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    };

    /**
     * 打开日志
     *
     * @param path 文件的绝对路径
     */
    private void openAssignFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(DcsSampleMainActivity.this,
                    getResources().getString(R.string.no_log),
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "text/plain");
        try {
            startActivity(Intent.createChooser(intent,
                    getResources().getString(R.string.open_file_title)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 先remove listener  停止唤醒,释放资源
        wakeUp.removeWakeUpListener(wakeUpListener);
        wakeUp.stopWakeUp();
        wakeUp.releaseWakeUp();

        if (dcsFramework != null) {
            dcsFramework.release();
        }
        webView.setWebViewClientListen(null);
        mTopLinearLayout.removeView(webView);
        webView.removeAllViews();
        webView.destroy();
    }


    private void initDateTime() {

        tv_time.setText(DateUtils.getDateHMS().substring(0, 5));
        tv_date.setText(DateUtils.getDateFormat("yy/MM/dd", System.currentTimeMillis()));

        new TimeThread().start();
    }

    private static final int msgKey = 1;


    public class TimeThread extends Thread {


        @Override
        public void run() {
            super.run();
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case msgKey:
                    tv_time.setText(DateUtils.getDateHMS());
                    tv_date.setText(DateUtils.getDateFormat("yy/MM/dd", System.currentTimeMillis()));
                    break;
                default:
                    break;
            }
        }
    };
}