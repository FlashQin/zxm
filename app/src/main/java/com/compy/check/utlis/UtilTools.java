package com.compy.check.utlis;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class UtilTools {

    private static String HH_MM = "HH:mm";
    private static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static void hideKeyboard(Context mcontext, ViewGroup view) {
        view.requestFocus();
        InputMethodManager im = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Context mcontext, View view) {
        InputMethodManager im = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
    }

    public static void toast(Context mcontext, String text) {
        Toast.makeText(mcontext, text, Toast.LENGTH_SHORT).show();
    }

    public static String gettimenow() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sim = dateFormat.format(date);

        return sim;
    }

    public static String gettimenowZheng() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        String sim = dateFormat.format(date);

        return sim;
    }

    public static String gettimenowChinese() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        String sim = dateFormat.format(date);


        return sim;
    }
    public static String gettimenowYear() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sim = dateFormat.format(date);


        return sim;
    }
    public static String getdWeek() {
        String mWay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        return "星期" + mWay;
    }

    public static String getTomrrow(String time) {
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, 1);// 日期加1
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timee;
    }

    public static String getSpeclDay(String time, int day) {
        if (time.equals(gettimenowChinese() + " 00:00:00") && day == -1) {
            return gettimenowChinese();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, day);// 日期加1年
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
            //timee=timee.substring(0,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timee;
    }
    public static String addDayEgls(String time, int day) {
        if (time.equals("0")){
            time=gettimenowYear();
        }
        if (time.equals(gettimenowYear() ) && day == -1) {
            return gettimenowYear();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, day);// 日期加几天
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
            //timee=timee.substring(0,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timee;
    }
    public static String getSpeclDayEgls(String time, int day) {
        if (time.equals("0")){
            time=gettimenowChinese() + " 00:00:00";
        }
        if (time.equals(gettimenowChinese() + " 00:00:00") && day == -1) {
            return gettimenowChinese();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, day);// 日期加几天
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
            //timee=timee.substring(0,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timee;
    }
    public static String addmouth(String time, int mouth) {
        if (time.equals("0")){
            time=gettimenowChinese();
        }
        if (time.equals(gettimenowChinese()) && mouth == -1) {
            return gettimenowChinese();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, mouth);// 日期加几个月
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
            //timee=timee.substring(0,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timee;
    }
    public static String getSpeclMouthEgls(String time, int mouth) {
        if (time.equals("0")){
            time=gettimenowChinese() + " 00:00:00";
        }
        if (time.equals(gettimenowChinese() + " 00:00:00") && mouth == -1) {
            return gettimenowChinese();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, mouth);// 日期加几个月
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
            //timee=timee.substring(0,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return timee;
    }
    public static String getTomrrowChinese() {
        String timee = gettimenowChinese();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date dt = sdf.parse(timee);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, 1);// 日期加1年
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timee;
    }

    public static String getTomrrowDay(String time) {
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 ");
        try {
            Date dt = sdf.parse(time);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_MONTH, 1);// 日期加1年
            Date dt1 = rightNow.getTime();
            timee = sdf.format(dt1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timee;
    }

    public static int[] getTime2(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] str = new int[4];
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);


            //   hours += days * 24;

            if (hours > 0) {
                str[0] = Integer.parseInt(String.valueOf(days));
                str[1] = Integer.parseInt(String.valueOf(hours > 6 ? 6 : hours));
                str[2] = Integer.parseInt(String.valueOf(minutes));
                str[3] = Integer.parseInt(String.valueOf(second));
            } else {
                str[0] = 00;
                str[1] = 00;
                str[2] = 00;
                str[3] = 00;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
    public static boolean getIsstart(String start, String endTime) {//当前时间-指定开市时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nums = 0;
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);
            if (hours > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
    public static boolean getCloseDay(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long nums = 0;
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            //   hours += days * 24;
            if (days > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
    public static long getDayNumsNew(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long nums = 0;
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            //   hours += days * 24;
            if (days > 0) {
                nums = days;
            } else {
                nums = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nums;
    }
    public static long getDayNums(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        long nums = 0;
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            //   hours += days * 24;
            if (days > 0) {
                nums = days;
            } else {
                nums = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nums;
    }

    public static boolean getTimeRight(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] str = new int[4];
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);

            //   hours += days * 24;

            if (days >= 0 && hours >= 0 && minutes >= 0 && second >= 0) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean getTimeStart(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);

            //   hours += days * 24;
            if (days < 0) {
                return true;
            }
            if (hours < 0) {
                return true;
            }
            if (hours == 0 && minutes < 0) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean getTimeEnd(String start, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(endTime);
            long diff = d2.getTime() - d1.getTime(); //
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);

            //   hours += days * 24;

            if (days > 0) {
                return true;
            }
            if (hours > 0) {
                return true;
            }
            if (hours == 0 && minutes > 0) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean dateInTimeBetweenHour(String targetTime, String startHour, String endHour) {
        SimpleDateFormat sdf = new SimpleDateFormat(HH_MM);
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            //转换为对应时区时间
            Date date = sdf.parse(sdf.format(df.parse(targetTime)));
            Date start = sdf.parse(startHour);
            Date end = sdf.parse(endHour);
            if (start.after(end) || start.equals(end)) {
                return date.getTime() >= start.getTime() || date.getTime() <= end.getTime();
            } else {
                return date.getTime() >= start.getTime() && date.getTime() <= end.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isEffectiveDate(String nowTime1, String startTime1, String endTime1) {
        Date nowTime = null, startTime= null, endTime= null;
        try {
            nowTime = new SimpleDateFormat("yyyy-MM-dd").parse(nowTime1);
            startTime = new SimpleDateFormat("yyyy-MM-dd").parse(startTime1);
            endTime = new SimpleDateFormat("yyyy-MM-dd").parse(endTime1);
        } catch (ParseException e) {

        }
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}