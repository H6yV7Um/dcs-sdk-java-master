package com.baidu.duer.dcs.devicemodule.screen.message;

import com.baidu.duer.dcs.framework.message.Payload;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RenderCardPayload extends Payload implements Serializable {


    private String token;
    private String type;
    private String title;
    private String content;

    private ImageBean image;


    private LinkBean link;

    private List<String> imageList;
    private List<ListBean> list;

    public static RenderCardPayload objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), RenderCardPayload.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<RenderCardPayload> arrayRenderCardPayloadFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new com.google.gson.reflect.TypeToken<ArrayList<RenderCardPayload>>() {
            }.getType();

            return new com.google.gson.Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageBean getImage() {
        return image;
    }

    public void setImage(ImageBean image) {
        this.image = image;
    }



    public LinkBean getLink() {
        return link;
    }

    public void setLink(LinkBean link) {
        this.link = link;
    }



    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }
    public static class ListBean {
        private String title;
        private String content;
        private String url;
        private ImageBean image;

        public static ListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ListBean> arrayListBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ListBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public static class ImageBean {
            private String src;

            public static ImageBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ImageBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ImageBean> arrayImageBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ImageBean>>() {
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
    }

    public static class ImageBean {
        private String src;

        public static ImageBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), ImageBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ImageBean> arrayImageBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ImageBean>>() {
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

    public static class LinkBean {
        private String url;
        private String anchorText;

        public static LinkBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), LinkBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<LinkBean> arrayLinkBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<LinkBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAnchorText() {
            return anchorText;
        }

        public void setAnchorText(String anchorText) {
            this.anchorText = anchorText;
        }
    }
    public  interface  TYPE{
        String TEXTCARD="TextCard";
        String STANDARDCARD="StandardCard";
        String LISTCARD="ListCard";
        String IMAGELISTCARD="ImageListCard";
    }
}
