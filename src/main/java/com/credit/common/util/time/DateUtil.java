/*
 * Created on 2006-2-21
 *
 * @author xuji
 * 
 * Copyright (C) 2006, HC SOFTWARE.
 */
package com.credit.common.util.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 提供时间相关的辅助函数
 * 
 * @author xuji
 * @version 0.9
 * @since 0.9
 */
public class DateUtil
{

    private static Calendar calendar;

    private static DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取缺省的中国时间字符串
     */
    public static final String format(Date date)
    {
        if (dateFmt == null)
        {
            dateFmt = DateFormat.getInstance();

            // timeZone在系统初始化时设置
            // m_dateFmt.setTimeZone(TimeZone.getTimeZone("PRC"));
        }
        return dateFmt.format(date);
    }

    /**
     * 给一个日期增加几天，不影响输入的Date参数
     * 
     * @param date
     * @param days
     * @return
     */
    public static final Date addDay(Date date, int days)
    {
        if (calendar == null)
        {
            calendar = Calendar.getInstance();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 给定一个Timestamp类型和输出格式，返回给定格式化的日期输出
     * 
     * @param timeStamp
     * @param formatStr
     * @return
     */
    public static final String formatTime(Timestamp timeStamp, String formatStr)
    {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(timeStamp);
        return str;
    }

    /**
     * 减天数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date lowerDay(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
        return calendar.getTime();
    }

    /**
     * 添加月份
     * 
     * @param date
     * @param month
     * @return
     */
    public static final Date addMonth(Date date, int month)
    {
        if (calendar == null)
        {
            calendar = Calendar.getInstance();
        }
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static void main(String[] args)
    {
        System.out.println(DateUtil.format(new Date()));
    }
}
