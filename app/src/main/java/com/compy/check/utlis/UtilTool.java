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


public class UtilTool {

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

    public static String gettimeYear() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sim = dateFormat.format(date);

        return sim;
    }

    public static String gettimenowNumber() {
        Date date = new Date();

        String time = date.toLocaleString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");

        String sim = dateFormat.format(date);

        return sim;
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

    public static String getSpeclDayEgls(String time, int day) {
        if (time.equals(gettimenowChinese() + " 00:00:00") && day == -1) {
            return gettimenowChinese();
        }
        String timee = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

            if (days < 2) {
                if ((hours * 24 * 60 + minutes * 60 + second) < (24 * 24 + minutes * 60 + 60)) {
                    return true;
                }
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

    public static boolean getnotice(String start, String endTime) {
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

            if (days >= 0 && hours >= 0 && hours >= 0 && minutes >= 0) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static long getTimeMISO(String start, String endTime) {
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
            System.out.println("-----+++++" + start + "/" + endTime + "  " + minutes + "/" + second);
            if (days == 0 && hours == 0 && (minutes * 60 + second) < 150) {
                if ((minutes * 60 + second) > 0) {
                    return 150 - (minutes * 60 + second);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}