package com.baidu.duer.dcs.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenway on 18/1/19 11:59
 * Email : xiaokai090704@126.com
 *
 * 获取返回歌词的json
 */

public class MusicLrcBean {

    private int status;
    private String code;
    private DataBean data;
    private String message;

    public static MusicLrcBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), MusicLrcBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<MusicLrcBean> arrayMusicLrcBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<MusicLrcBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String lycUrl;
        private String lycContent;
        private String wap_lycUrl;
        private String lycDownload;

        @Override
        public String toString() {
            return "DataBean{" +
                    "lycUrl='" + lycUrl + '\'' +
                    ", lycContent='" + lycContent + '\'' +
                    ", wap_lycUrl='" + wap_lycUrl + '\'' +
                    ", lycDownload='" + lycDownload + '\'' +
                    '}';
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getLycUrl() {
            return lycUrl;
        }

        public void setLycUrl(String lycUrl) {
            this.lycUrl = lycUrl;
        }

        public String getLycContent() {
            return lycContent;
        }

        public void setLycContent(String lycContent) {
            this.lycContent = lycContent;
        }

        public String getWap_lycUrl() {
            return wap_lycUrl;
        }

        public void setWap_lycUrl(String wap_lycUrl) {
            this.wap_lycUrl = wap_lycUrl;
        }

        public String getLycDownload() {
            return lycDownload;
        }

        public void setLycDownload(String lycDownload) {
            this.lycDownload = lycDownload;
        }
    }

    @Override
    public String toString() {
        return "MusicLrcBean{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
