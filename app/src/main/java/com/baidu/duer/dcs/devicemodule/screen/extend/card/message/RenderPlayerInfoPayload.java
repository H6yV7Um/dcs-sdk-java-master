package com.baidu.duer.dcs.devicemodule.screen.extend.card.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenway on 18/1/18 14:42
 * Email : xiaokai090704@126.com
 *
 * 播放音乐飞Payload
 */

public class RenderPlayerInfoPayload {

    private String audioItemId;
    private ContentBean content;
    private String token;
    private List<ControlsBean> controls;

    public static RenderPlayerInfoPayload objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), RenderPlayerInfoPayload.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<RenderPlayerInfoPayload> arrayRenderPlayerInfoPayloadFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<RenderPlayerInfoPayload>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getAudioItemId() {
        return audioItemId;
    }

    public void setAudioItemId(String audioItemId) {
        this.audioItemId = audioItemId;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ControlsBean> getControls() {
        return controls;
    }

    public void setControls(List<ControlsBean> controls) {
        this.controls = controls;
    }

    public static class ContentBean {
        private ArtBean art;
        private String audioItemType;
        private LyricBean lyric;
        private int mediaLengthInMilliseconds;
        private ProviderBean provider;
        private String title;
        private String titleSubtext1;
        private String titleSubtext2;

        @Override
        public String toString() {
            return "ContentBean{" +
                    "art=" + art +
                    ", audioItemType='" + audioItemType + '\'' +
                    ", lyric=" + lyric +
                    ", mediaLengthInMilliseconds=" + mediaLengthInMilliseconds +
                    ", provider=" + provider +
                    ", title='" + title + '\'' +
                    ", titleSubtext1='" + titleSubtext1 + '\'' +
                    ", titleSubtext2='" + titleSubtext2 + '\'' +
                    '}';
        }

        public static ContentBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ContentBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ContentBean> arrayContentBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ContentBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public ArtBean getArt() {
            return art;
        }

        public void setArt(ArtBean art) {
            this.art = art;
        }

        public String getAudioItemType() {
            return audioItemType;
        }

        public void setAudioItemType(String audioItemType) {
            this.audioItemType = audioItemType;
        }

        public LyricBean getLyric() {
            return lyric;
        }

        public void setLyric(LyricBean lyric) {
            this.lyric = lyric;
        }

        public int getMediaLengthInMilliseconds() {
            return mediaLengthInMilliseconds;
        }

        public void setMediaLengthInMilliseconds(int mediaLengthInMilliseconds) {
            this.mediaLengthInMilliseconds = mediaLengthInMilliseconds;
        }

        public ProviderBean getProvider() {
            return provider;
        }

        public void setProvider(ProviderBean provider) {
            this.provider = provider;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleSubtext1() {
            return titleSubtext1;
        }

        public void setTitleSubtext1(String titleSubtext1) {
            this.titleSubtext1 = titleSubtext1;
        }

        public String getTitleSubtext2() {
            return titleSubtext2;
        }

        public void setTitleSubtext2(String titleSubtext2) {
            this.titleSubtext2 = titleSubtext2;
        }

        public static class ArtBean {
            private String src;

            public static ArtBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ArtBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ArtBean> arrayArtBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ArtBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class LyricBean {
            private String format;
            private String url;

            public static LyricBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), LyricBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<LyricBean> arrayLyricBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<LyricBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ProviderBean {
            private ArtBean logo;
            private String name;

            public static ProviderBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ProviderBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ProviderBean> arrayProviderBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ProviderBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public ArtBean getLogo() {
                return logo;
            }

            public void setLogo(ArtBean logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class ControlsBean {
        private boolean enabled;
        private String name;
        private boolean selected;
        private String type;
        private String selectedValue;

        public static ControlsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ControlsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ControlsBean> arrayControlsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ControlsBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSelectedValue() {
            return selectedValue;
        }

        public void setSelectedValue(String selectedValue) {
            this.selectedValue = selectedValue;
        }
    }

    @Override
    public String toString() {
        return "RenderPlayerInfoPayload{" +
                "audioItemId='" + audioItemId + '\'' +
                ", content=" + content +
                ", token='" + token + '\'' +
                ", controls=" + controls +
                '}';
    }
}
