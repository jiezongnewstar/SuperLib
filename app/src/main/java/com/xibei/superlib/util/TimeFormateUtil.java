package com.ubus.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Xibei on 2020/5/13.
 * Github: https://github.com/jiezongnewstar
 * Email: ibossjia@gmail.com
 * Deeclaration:  时间格式转换类
 */
public class TimeFormateUtil {

    public static final String YYYYMM1 = "yyyy-MM";
    public static final String YYYYMMDD_HHMMSS1 = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD_HHMMSS2 = "yyyy.MM.dd HH:mm:ss";
    public static final String YYYYMMDD_HHMM1 = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD_HHMM2 = "yyyy.MM.dd HH:mm";
    public static final String YYYYMMDD1 = "yyyy-MM-dd";
    public static final String YYYYMMDD2 = "yyyy.MM.dd";
    public static final String YYYYMMDDHHSS1 = "yyyy-MM-dd HH:ss";
    public static final String YYYYMMDDHHSS2 = "yyyy.MM.dd HH:ss";
    public static final String HHMM = "HH:mm";
    public static final String YYYYNMMY = "yyyy年MM月";
    public static final String YYYYNMMYDDR = "yyyy年MM月dd日";


    //系统启动到现在的毫秒差值
    public static long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }


    /**
     * 返回校验后的当前时间s
     *
     * @return
     */
    public static long getNowTimeMillSeconds() {
        return CommonUtil.getServerTime() + (getElapsedRealtime() - CommonUtil.getDiffTime());
    }


    /**
     * 接口返回字段时间格式化
     *
     * @param dateString
     * @param patternDst
     * @return
     */
    public static String dateFormateFromServer(String dateString, String patternDst) {
        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS1);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            return "";
        }
        sdf = new SimpleDateFormat(patternDst);
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param millSeconds 毫秒级时间戳
     * @param pattern     日期格式
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateformate(long millSeconds, String pattern) {
        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(millSeconds);
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param millSeconds 毫秒级时间戳
     * @param pattern     日期格式
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateformate2Seconds(long millSeconds, String pattern) {
        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(millSeconds * 1000);
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param dateString 传入具体时间字符串
     * @param patternSrc 具体时间字符串格式
     * @param patternDst 输出自定义时间字符串
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateformate(String dateString, String patternSrc, String patternDst) {
        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(patternSrc);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat(patternDst);
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param date 传入date类
     * @return 秒级时间戳
     */
    public static long getSecondTimestamp(Date date) {
        long timestamp = 0;
        if (date != null) {
            timestamp = date.getTime() / 1000;
        }

        return timestamp;
    }

    /**
     * @param dateString 时间字符串
     * @param pattern    时间格式
     * @return 毫秒级别时间戳
     */
    @SuppressLint("SimpleDateFormat")
    public static long getMillSecondTimestamp(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert date != null;
        return date.getTime();
    }

    /**
     * @param dateString 时间字符串
     * @param pattern    时间格式
     * @return 秒级别时间戳
     */
    @SuppressLint("SimpleDateFormat")
    public static long getSecondTimestamp(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert date != null;
        return date.getTime() / 1000;
    }

    /**
     * @param millSeconds 需要倒计时的总时间毫秒数
     * @return 具体时间 时：分 秒
     */
    public static String getCountDowTime(long millSeconds) {
        long hour = millSeconds / 3600000;
        long minute = (millSeconds - hour * 3600000) / 60000;
        long second = (millSeconds - hour * 3600000 - minute * 60000) / 1000;

        StringBuffer stringBuffer = new StringBuffer();
        if (hour != 0) {
            if (hour < 10) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hour);
            stringBuffer.append(":");
        }

        if (minute < 10) {
            stringBuffer.append(0);
        }
        stringBuffer.append(minute);
        stringBuffer.append(":");

        if (second < 10) {
            stringBuffer.append(0);
        }
        stringBuffer.append(second);

        return stringBuffer.toString();
    }

    /**
     * 计算相对0点的分钟数
     *
     * @param dateString
     * @param pattern
     * @return 相对0点分钟数
     */
    @SuppressLint("SimpleDateFormat")
    public static int getMiniuteWithZero(String dateString, String pattern) {
        SimpleDateFormat formate = new SimpleDateFormat(pattern);
        try {
            Date dateSrc = formate.parse(dateString);
            long millSecondsSrc = Objects.requireNonNull(dateSrc).getTime();
            String dateformate = dateformate(dateString, pattern, "yyyy-MM-dd");
            Date dateZero = new Date();
            dateZero.setTime(getMillSecondTimestamp(dateformate, "yyyy-MM-dd"));
            long zeroMillSeconds = dateZero.getTime();
            if (millSecondsSrc > zeroMillSeconds) {
                return (int) ((millSecondsSrc - zeroMillSeconds) / 1000 / 60);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取第几周
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static int getWeek(String dateString, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillSecondTimestamp(dateString, pattern));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week;
    }

    /**
     * 获取某月第一天
     *
     * @param date
     * @return
     */
    public static long getFirstDayofMonth(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String dayStr = year + "-" + (month + 1) + "-1";
        return getMillSecondTimestamp(dayStr, YYYYMMDD1);
    }


    /**
     * 获取某月最后一天
     *
     * @param date
     * @return
     */
    public static long getLastDayofMonth(long date) {

        List<Integer> day31List = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> day30List = Arrays.asList(4, 6, 9, 11);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        if (day31List.contains(month + 1)) {
            return getMillSecondTimestamp(year + "-" + (month + 1) + "-31", YYYYMMDD1);
        } else if (day30List.contains(month + 1)) {
            return getMillSecondTimestamp(year + "-" + (month + 1) + "-30", YYYYMMDD1);
        } else {
            if (year % 400 == 0 || year % 4 == 0) {
                return getMillSecondTimestamp(year + "-" + (month + 1) + "-29", YYYYMMDD1);
            } else {
                return getMillSecondTimestamp(year + "-" + (month + 1) + "-28", YYYYMMDD1);
            }
        }
    }

    /**
     * 获取某周（自然周）第一天日期
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getFirstDayOfWeek(String dateString, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillSecondTimestamp(dateString, pattern));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (calendar.get(Calendar.DAY_OF_MONTH) >= 7) {
            calendar.add(Calendar.DAY_OF_WEEK, -getWeek(calendar.get(Calendar.DAY_OF_WEEK)) + 1);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某周（自然周）最后一天日期
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getLastDayOfWeek(String dateString, String pattern) {

        List<Integer> day31List = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> day30List = Arrays.asList(4, 6, 9, 11);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillSecondTimestamp(dateString, pattern));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        if (day31List.contains(month + 1)) {
            if (calendar.get(Calendar.DAY_OF_MONTH) >= 24) {
                calendar.set(Calendar.DAY_OF_MONTH, 31);
            } else {
                calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + (7 - getWeek(calendar.get(Calendar.DAY_OF_WEEK))));
            }

        } else if (day30List.contains(month + 1)) {
            if (calendar.get(Calendar.DAY_OF_MONTH) >= 23) {
                calendar.set(Calendar.DAY_OF_MONTH, 30);
            } else {
                calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + (7 - getWeek(calendar.get(Calendar.DAY_OF_WEEK))));
            }
        } else {
            if (year % 400 == 0 || year % 4 == 0) {
                if (calendar.get(Calendar.DAY_OF_MONTH) >= 22) {
                    calendar.set(Calendar.DAY_OF_MONTH, 29);
                } else {
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + (7 - getWeek(calendar.get(Calendar.DAY_OF_WEEK))));
                }
            } else {
                if (calendar.get(Calendar.DAY_OF_MONTH) >= 21) {
                    calendar.set(Calendar.DAY_OF_MONTH, 28);
                } else {
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + (7 - getWeek(calendar.get(Calendar.DAY_OF_WEEK))));
                }
            }
        }
        return calendar.getTimeInMillis();
    }


    /**
     * 获取某月中某周第一天
     *
     * @param week    第几周
     * @param dateStr
     * @param pattern
     * @return
     */
    public static long getFirstDayOfWeek(int week, String dateStr, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillSecondTimestamp(dateStr, pattern));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        final int monthSrc = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        final int monthDst = calendar.get(Calendar.MONTH);
        if (monthSrc != monthDst) {
            calendar.set(Calendar.MONTH, monthSrc);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某月中某周的最后一天
     *
     * @param week    第几周
     * @param dateStr
     * @param pattern
     * @return
     */
    public static long getLastDayOfWeek(int week, String dateStr, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillSecondTimestamp(dateStr, pattern));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        final int monthSrc = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        final int monthDst = calendar.get(Calendar.MONTH);
        if (monthSrc != monthDst) {
            List<Integer> day31List = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
            List<Integer> day30List = Arrays.asList(4, 6, 9, 11);
            calendar.set(Calendar.MONTH, monthSrc);
            if (day31List.contains(monthSrc + 1)) {
                calendar.set(Calendar.DAY_OF_MONTH, 31);
            } else if (day30List.contains(monthSrc + 1)) {
                calendar.set(Calendar.DAY_OF_MONTH, 30);
            } else {
                if (year % 400 == 0 || year % 4 == 0) {
                    calendar.set(Calendar.DAY_OF_MONTH, 29);
                } else {
                    calendar.set(Calendar.DAY_OF_MONTH, 28);
                }
            }
        }
        return calendar.getTimeInMillis();
    }


    public static int getWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return 7;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
        }
        return 0;
    }
}
