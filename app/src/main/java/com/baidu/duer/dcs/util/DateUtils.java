package com.baidu.duer.dcs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kenway on 17/5/22 16:15
 * Email : xiaokai090704@126.com
 * 日期工具类
 */

public class DateUtils {

    private static final String TAG = "tool.Util";

    /**
     * it will return a format time string as "mm:ss"
     *
     * @param time
     * @return
     */
    public static String getFormatTime(int time) {
        return getFormatTime("mm:ss", time);
    }

    /**
     * 获取mm:ss格式的时间
     *
     * @param format
     * @param time
     * @return
     */
    public static String getFormatTime(String format, int time) {
        if (format == null || format.equals("")) {
            throw new IllegalArgumentException("format is null");
        }
        if (format.equalsIgnoreCase("mm:ss")) {
            int minute = ((int) time) / 60;
            int second = ((int) time) % 60;
            String timeMsg;
            if (minute < 10)
                timeMsg = "0" + minute + ":";
            else
                timeMsg = minute + ":";
            if (second < 10)
                timeMsg += "0" + second;
            else
                timeMsg += second;
            return timeMsg;
        }
        return "00:00";
    }

    /**
     * <pre>
     *                     yyyy-MM-dd 1969-12-31
     *                     yyyy-MM-dd 1970-01-01
     *               yyyy-MM-dd HH:mm 1969-12-31 16:00
     *               yyyy-MM-dd HH:mm 1970-01-01 00:00
     *              yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
     *              yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
     *       yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
     *       yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000
     * </pre>
     *
     * @param format
     * @param msec
     * @return
     */
    public static String getDateFormat(String format, long msec) {
        return (new SimpleDateFormat(format)).format(new Date(msec));
    }

    /**
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 从日期获取时间
     *
     * @param formatDate
     * @return
     */
    public static long getDateMsec(String formatDate) {
        long msec = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
        try {
            Date date = simpleDateFormat.parse(formatDate);
            msec = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return msec;
    }

    /**
     * 通过format,和日期的字符串获取Date
     *
     * @param format
     * @param formatDate
     * @return
     */
    public static Date getDate(String format, String formatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 输入年月日,获取yyyy-mm-dd
     */

    public static String getStringformYMD(int year, int month, int day) {
        StringBuilder builder = new StringBuilder();
        builder.append(year + "-");
        if (month < 10) {
            builder.append("0" + month + "-");
        } else {
            builder.append(month + "-");
        }

        if (day < 10) {
            builder.append("0" + day);
        } else {
            builder.append(day);
        }

        return builder.toString();
    }

    /**
     * 获取日期从某月某日--某月某日
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String getMonthAndDayDuration(String beginDate, String endDate) {
        if ((beginDate == null || beginDate.length() == 0) || (endDate == null || endDate.length() == 0)) {
            LogUtils.e(TAG, "beginDate and endDate should not be null");
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(getDate("yyyy-MM-dd hh:mm:ss", beginDate));
        stringBuilder.append(beginCalendar.get(Calendar.MONTH) + 1);
        stringBuilder.append("月");
        stringBuilder.append(beginCalendar.get(Calendar.DAY_OF_MONTH));
        stringBuilder.append("日");

        stringBuilder.append("-");

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(getDate("yyyy-MM-dd hh:mm:ss", endDate));
        stringBuilder.append(endCalendar.get(Calendar.MONTH) + 1);
        stringBuilder.append("月");
        stringBuilder.append(endCalendar.get(Calendar.DAY_OF_MONTH));
        stringBuilder.append("日");
        return stringBuilder.toString();
    }

    /**
     * 获取上一个月
     * int[] now =new int[3];
     * <p>now[0]==year</p>
     * <p>now[1]==month</p>
     * <p>now[2]==day</p>
     *
     * @param now
     * @return
     */
    public static int[] getBeforeMonth(int[] now) {
        if (now[1] == 1) {
            now[0] = now[0] - 1;
            now[1] = 12;
        } else {
            now[1] = now[1] - 1;
        }

        now[2] = 1;
        return now;

    }

    /**
     * 获取下一个月
     * int[] now =new int[3];
     * <p>now[0]==year</p>
     * <p>now[1]==month</p>
     * <p>now[2]==day</p>
     *
     * @param now
     * @return
     */
    public static int[] getNextMonth(int[] now) {
        if (now[1] == 12) {
            now[0] = now[0] + 1;
            now[1] = 1;
        } else {
            now[1] = now[1] + 1;
        }
        now[2] = 1;
        return now;
    }

    /**
     * 将年月日数字转化为字符串  2017-06-01
     *
     * @return
     */
    public static String doArrToString(int[] arr) {
        StringBuilder builder = new StringBuilder();
        builder.append(arr[0] + "-");
        if (arr[1] < 10) {
            builder.append("0" + arr[1] + "-");
        } else {
            builder.append(arr[1] + "-");
        }
        if (arr[2] < 10) {
            builder.append("0" + arr[2]);
        } else {
            builder.append(arr[2]);
        }
        return builder.toString();
    }

    /**
     * 获取当前年月日数据
     *
     * @return
     */
    public static int[] getNowDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int[] selectDate = new int[3];
        selectDate[0] = year;
        selectDate[1] = month;
        selectDate[2] = day;
        return selectDate;
    }

    /**
     * 获取某一个月有多少天
     *
     * @return
     */
    public static int getDaysOfMonth(int[] ymd) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            LogUtils.e(TAG, "doArrToString(ymd)===" + doArrToString(ymd));
            Date date = sdf.parse(doArrToString(ymd));
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某一个月有多少天
     *
     * @return
     */
    public static int getDaysOfMonth(String date2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(date2);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }





    public static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }

    public static String getDateHMS() {
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }

    /**
     * 获取该时间的 "yyyy-MM-dd"
     *
     * @param time
     * @return "yyyy-MM-dd"
     */
    public static String getTimeStr(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 获取几天前的日期
     * 获取几天前的日期
     * @param day --几天
     * @return yyyy-MM-dd
     */
    public static String getBefomeSomeDay(int day) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取当前年
        int month = cal.get(Calendar.MONTH) + 1;//获取当前月
        int nowday = cal.get(Calendar.DAY_OF_MONTH);//获取当前日
        cal.set(Calendar.DAY_OF_YEAR, nowday - day);
        int beforeDay = cal.get(Calendar.DAY_OF_MONTH);
        int beforeMonth = month;
        int beforeYear = year;
        //判断前面的日期如果大于当前日期,那么月分减一
        if (beforeDay > nowday) {
            beforeMonth -= 1;
        }
        //判断前面的月如果大于当前月,那么年份分减一
        if (beforeMonth > month) {
            beforeYear -= 1;
        }
        cal.set(beforeYear, beforeMonth - 1, beforeDay);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateBefore = format.format(date);
        return dateBefore;
    }

}
