package com.syc.api.kit;

import java.util.Calendar;
import java.util.Date;

public class DateKit {

    /**
     * 日期相减(返回秒值)
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static Long diffDateTime(Date date, Date date1) {
        return ((getMillis(date) - getMillis(date1)) / 1000);
    }

    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 获取 指定日期 后 指定毫秒后的 Date
     *
     * @param date
     * @param millSecond
     * @return
     */
    public static Date getDateAddMillSecond(Date date, int millSecond) {
        Calendar cal = Calendar.getInstance();
        // 没有 就取当前时间
        if (null != date) {
            cal.setTime(date);
        }
        cal.add(Calendar.MILLISECOND, millSecond);
        return cal.getTime();
    }

    /**
     * 取将来时间
     *
     * @param date
     * @param expire
     * @param idate
     * @return
     */
    public static Date getDateAdd(Date date, int expire, int idate) {
        Calendar calendar = Calendar.getInstance();
        //默认当前时间
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(idate, expire);
        return calendar.getTime();
    }
}