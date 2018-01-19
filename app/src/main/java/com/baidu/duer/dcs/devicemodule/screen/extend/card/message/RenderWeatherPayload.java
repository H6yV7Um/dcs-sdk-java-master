package com.baidu.duer.dcs.devicemodule.screen.extend.card.message;

import com.baidu.duer.dcs.framework.message.Payload;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询天气时返回的内容
 */
public class RenderWeatherPayload extends Payload implements Serializable {


    /**
     * {"header":{"namespace":"ai.dueros.device_interface.screen_extended_card","name":"RenderWeather","messageId":"ZHVlcl93ZWF0aGVyKzE1MTYzNDM4NzE=","dialogRequestId":"91fc503f-03be-4257-8f1d-4fa39a01dd2b"},
     * "payload":{"city":"广州","county":"广州","askingDay":"FRI","askingDate":"2018-01-19","weatherForecast":[{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"},"highTemperature":"24℃","lowTemperature":"16℃","day":"FRI","date":"2018-01-19","weatherCondition":"多云","windCondition":"无持续风向微风","currentTemperature":"24℃","currentPM25":"109","currentAirQuality":"轻度污染","indexes":[{"type":"CLOTHES","level":"舒适","suggestion":"温度舒适，可以穿T恤衫、衬衫和薄外套，注意早晚温差"},{"type":"CAR_WASH","level":"较适宜","suggestion":"比较适合洗车，当日未来24小时内没有雨，风力较小，擦洗干净的汽车至少能保持一天"},{"type":"TRIP","level":"适宜","suggestion":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},{"type":"INFLUENZA","level":"较易发","suggestion":"比较容易感冒，请注意衣服增减，如果你体质较弱，请适当防护"},{"type":"EXERCISE","level":"较适宜","suggestion":"天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。"},{"type":"ULTRAVIOLET","level":"弱","suggestion":"紫外线强度较弱，建议出门前涂抹防晒指数在12-15之间的防晒霜"}],"pm25":"109","airQuality":"轻度污染"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"},"highTemperature":"23℃","lowTemperature":"15℃","day":"SAT","date":"2018-01-20","weatherCondition":"多云","windCondition":"无持续风向微风"},{"weatherIcon":{"src":"http://d.hiphotos.baidu.com/xiaodu/pic/item/023b5bb5c9ea15ce45b40f3ebd003af33a87b2ab.jpg"},"highTemperature":"21℃","lowTemperature":"15℃","day":"SUN","date":"2018-01-21","weatherCondition":"多云转阴","windCondition":"无持续风向微风"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/a5c27d1ed21b0ef4f4bd7769d6c451da81cb3eb4.jpg"},"highTemperature":"19℃","lowTemperature":"14℃","day":"MON","date":"2018-01-22","weatherCondition":"小雨转阴","windCondition":"无持续风向微风"},{"weatherIcon":{"src":"http://h.hiphotos.baidu.com/xiaodu/pic/item/4e4a20a4462309f7c70a4225790e0cf3d7cad6a4.jpg"},"highTemperature":"21℃","lowTemperature":"14℃","day":"TUE","date":"2018-01-23","weatherCondition":"多云","windCondition":"无持续风向微风"}],"askingType":"WEATHER","description":"广州今天多云，16℃～24℃，比昨天冷一些，空气质量指数109，轻度污染，请注意防护。","timeZone":"8","askingDateDescription":"今天"}}
     */
    private String city;
    private String county;
    private String askingDay;
    private String askingDate;
    private String askingType;
    private String description;
    private String timeZone;
    private String askingDateDescription;
    private List<WeatherForecastBean> weatherForecast;

    public static RenderWeatherPayload objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), RenderWeatherPayload.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<RenderWeatherPayload> arrayRenderWeatherPayloadFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<RenderWeatherPayload>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAskingDay() {
        return askingDay;
    }

    public void setAskingDay(String askingDay) {
        this.askingDay = askingDay;
    }

    public String getAskingDate() {
        return askingDate;
    }

    public void setAskingDate(String askingDate) {
        this.askingDate = askingDate;
    }

    public String getAskingType() {
        return askingType;
    }

    public void setAskingType(String askingType) {
        this.askingType = askingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAskingDateDescription() {
        return askingDateDescription;
    }

    public void setAskingDateDescription(String askingDateDescription) {
        this.askingDateDescription = askingDateDescription;
    }

    public List<WeatherForecastBean> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(List<WeatherForecastBean> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public static class WeatherForecastBean {
        private WeatherIconBean weatherIcon;
        private String highTemperature;
        private String lowTemperature;
        private String day;
        private String date;
        private String weatherCondition;
        private String windCondition;
        private String currentTemperature;
        private String currentPM25;
        private String currentAirQuality;
        private String pm25;
        private String airQuality;
        private List<IndexesBean> indexes;

        @Override
        public String toString() {
            return "WeatherForecastBean{" +
                    "weatherIcon=" + weatherIcon +
                    ", highTemperature='" + highTemperature + '\'' +
                    ", lowTemperature='" + lowTemperature + '\'' +
                    ", day='" + day + '\'' +
                    ", date='" + date + '\'' +
                    ", weatherCondition='" + weatherCondition + '\'' +
                    ", windCondition='" + windCondition + '\'' +
                    ", currentTemperature='" + currentTemperature + '\'' +
                    ", currentPM25='" + currentPM25 + '\'' +
                    ", currentAirQuality='" + currentAirQuality + '\'' +
                    ", pm25='" + pm25 + '\'' +
                    ", airQuality='" + airQuality + '\'' +
                    ", indexes=" + indexes +
                    '}';
        }

        public static WeatherForecastBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), WeatherForecastBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<WeatherForecastBean> arrayWeatherForecastBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<WeatherForecastBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public WeatherIconBean getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(WeatherIconBean weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        public String getHighTemperature() {
            return highTemperature;
        }

        public void setHighTemperature(String highTemperature) {
            this.highTemperature = highTemperature;
        }

        public String getLowTemperature() {
            return lowTemperature;
        }

        public void setLowTemperature(String lowTemperature) {
            this.lowTemperature = lowTemperature;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeatherCondition() {
            return weatherCondition;
        }

        public void setWeatherCondition(String weatherCondition) {
            this.weatherCondition = weatherCondition;
        }

        public String getWindCondition() {
            return windCondition;
        }

        public void setWindCondition(String windCondition) {
            this.windCondition = windCondition;
        }

        public String getCurrentTemperature() {
            return currentTemperature;
        }

        public void setCurrentTemperature(String currentTemperature) {
            this.currentTemperature = currentTemperature;
        }

        public String getCurrentPM25() {
            return currentPM25;
        }

        public void setCurrentPM25(String currentPM25) {
            this.currentPM25 = currentPM25;
        }

        public String getCurrentAirQuality() {
            return currentAirQuality;
        }

        public void setCurrentAirQuality(String currentAirQuality) {
            this.currentAirQuality = currentAirQuality;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(String airQuality) {
            this.airQuality = airQuality;
        }

        public List<IndexesBean> getIndexes() {
            return indexes;
        }

        public void setIndexes(List<IndexesBean> indexes) {
            this.indexes = indexes;
        }

        public static class WeatherIconBean {
            private String src;

            public static WeatherIconBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), WeatherIconBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<WeatherIconBean> arrayWeatherIconBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<WeatherIconBean>>() {
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

        public static class IndexesBean {
            private String type;
            private String level;
            private String suggestion;

            public static IndexesBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), IndexesBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<IndexesBean> arrayIndexesBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<IndexesBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(String suggestion) {
                this.suggestion = suggestion;
            }
        }
    }

    @Override
    public String toString() {
        return "RenderWeatherPayload{" +
                "city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", askingDay='" + askingDay + '\'' +
                ", askingDate='" + askingDate + '\'' +
                ", askingType='" + askingType + '\'' +
                ", description='" + description + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", askingDateDescription='" + askingDateDescription + '\'' +
                ", weatherForecast=" + weatherForecast +
                '}';
    }
}
