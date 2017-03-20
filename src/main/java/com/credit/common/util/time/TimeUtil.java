package com.credit.common.util.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zhanggs
 */
public class TimeUtil
{

    /**
     * yyyy-MM-dd
     */
    static public final String FORMAT_DATE_ONLY = "yyyy-MM-dd";

    /**
     * yyyy/MM/dd
     */
    static public final String FORMAT_DATE_ONLY_SLASH = "yyyy/MM/dd";

    static public final String FORMAT_DATE_ONLY_ZH = "yyyy年MM月dd日";

    static public final String FORMAT_TIME_ONLY = "HH:mm:ss";

    static public final String FORMAT_COMPACT = "yyyyMMddHHmmss";

    static public final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

    static public final String FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";

    static public final String FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";

    static public final String FORMAT_DATE_ONLY_DOT = "yyyy.MM.dd";
    
    static public final String FORMAT_DATE_ONLY_ONE_DOT = "yyyy.MM";
    
    static public final String FORMAT_DATE_ONLY_TWO_DOT = "yyyy-MM";

    /**
     * yyyyMMddHH
     */
    static public final String FORMAT_YYYYMMDDHH = "yyyyMMddHH";

    static public final String FORMAT_YYYYMMDD = "yyyyMMdd";

    static public final long DATE_SECOND = 86400;// 一天有86400秒

    static public final long DATE_MINUTE = 1440;// 一天有1440分

    static public final long MINUTE_SECOND = 60;// 一天有60分

	static public final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static Date parse(String str, String format)
    {
        try
        {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return sf.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * 判断是否为工作日，
	 * @param date
	 * @return true工作日，false，休息日
	 */
    public static boolean isWeekday(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w > 0 && w < 6;
    }
	/**
	 * 判断是否为工作日，
	 * @param dateStr
	 * @return true工作日，false，休息日
	 */
    public static boolean isWeekday(String dateStr, String format)
    {
        Date date = parse(dateStr, format);
        return date != null && isWeekday(date);
    }
	/**
	 * 判断是否为工作日，
	 * @param dateStr
	 * @return true工作日，false，休息日
	 */
	public static boolean isWeekday(String dateStr)
	{
		return isWeekday(dateStr,FORMAT_NORMAL);
	}
	public static long getDateTime(String str)
	{
		Date parse = parse(str, FORMAT_NORMAL);
		if (parse == null)
		{
			return 0;
		}
		return parse.getTime();
	}
    public static long getDateTime(String str, String format)
    {
        Date parse = parse(str, format);
        if (parse == null)
        {
            return 0;
        }
        return parse.getTime();
    }
    public static String format(Date date, String format)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sf.format(date);
    }

    public static String format(Timestamp date, String format)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sf.format(date);
    }

    public static boolean isExpire(String strTime, String strExpiredTime)
    {
        Date time = parse(strTime, FORMAT_NORMAL);
        Date expiredTime = parse(strExpiredTime, FORMAT_NORMAL);

        return (time.compareTo(expiredTime) >= 0);
    }

    /**
     * 生成制定日期的Date对象，从0点开始
     *
     * @param year
     * @param month
     * @param days
     * @return
     */
    public static Date createDate(int year, int month, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, days, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 计算时间差
     *
     * @param beginTime
     *            开始时间，格式：yyyy-MM-dd HH:mm:ss
     * @param endTime
     *            结束时间，格式：yyyy-MM-dd HH:mm:ss
     * @return 从开始时间到结束时间之间的时间差（秒）
     */
    public static long getTimeDifference(String beginTime, String endTime)
    {
        long between = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_NORMAL);

        Date end = null;
        Date begin = null;
        try
        {
            // 将截取到的时间字符串转化为时间格式的字符串
            end = sdf.parse(endTime);
            begin = sdf.parse(beginTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

        return between;
    }

    public static String getTimeDifference(Date beginTime, Date endTime)
    {

        long between = endTime.getTime() - beginTime.getTime();
        if (between <= 0)
        {
            return "已过期";
        }
        between = between / 1000;// 除以1000是为了转换成秒
		return getTimeInterval(between);
    }

	/**
	 *
	 * @param interval 单位：秒
	 * @return
	 */
	public static String getTimeInterval(long interval)
	{

		if (interval <= 0)
		{
			return "0秒";
		}
		long date = interval / DATE_SECOND;
		long hour = (interval - date * DATE_SECOND) / 3600;
		long minute = (interval - date * DATE_SECOND - hour * 3600) / 60;
		long sec = (interval - date * DATE_SECOND - hour * 3600 - minute * 60);
		StringBuilder stringBuilder = new StringBuilder();
        if (date != 0)
        {
            stringBuilder.append(date);
            stringBuilder.append("天");
        }
        if (hour != 0)
        {
            stringBuilder.append(hour);
            stringBuilder.append("小时");
        }
        if (minute != 0)
        {
            stringBuilder.append(minute);
            stringBuilder.append("分钟");
        }
        if (sec != 0)
        {
            stringBuilder.append(sec);
            stringBuilder.append("秒");
        }
		return stringBuilder.toString();
	}
    // 得到X天X小时时间
    public static String getDateExplain(long second)
    {
        String time = "";
        long hourCount_ = second / 3600;
        long dayCount_ = hourCount_ / 24;
        long remnantHour = hourCount_ % 24;
        if (dayCount_ != 0)
        {
            time = dayCount_ + "天";
        }
        if (remnantHour != 0)
        {
            time += remnantHour + "小时";
        }
        return time;
    }

    // 得到当前时间后x天的日期
    public static Date getFutrueDate(Date oldDate, int addDay)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(oldDate);
        c.add(Calendar.DATE, addDay);

        return c.getTime();
    }

    // 得到当前时间后x天的日期
    public static Date getFutrueDate(String oldDate, int addDay)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(parse(oldDate, FORMAT_DATE_ONLY));
        c.add(Calendar.DATE, addDay);

        return c.getTime();
    }

    // 得到当天前x年x月x日的时间
    public static Date getDateBefore(int year, int month, int day)
    {
        Date result = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(result);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        result = calendar.getTime();
        return result;
    }
	public static Date getDateBefore(Date date, int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		date = calendar.getTime();
		return date;
	}
    // 比较两日期大小.
    public static String compare(Date d1, Date d2)
    {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        if (c1.after(c2))
        {
            return ">";
        }
        else
            if (c1.before(c2))
            {
                return "<";
            }
            else
            {
                return "=";
            }
    }

    // 比较两日期大小
    public static String compare(String s1, String s2)
    {
        Date d1 = parse(s1, FORMAT_DATE_ONLY);
        Date d2 = parse(s2, FORMAT_DATE_ONLY);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.after(c2))
        {
            return ">";
        }
        else
            if (c1.before(c2))
            {
                return "<";
            }
            else
            {
                return "=";
            }
    }

    public static void main(String[] args) throws ParseException
    {
        Date startTime = parse("2009-04-30 16:58:20.093", FORMAT_DETAIL);
        Date endTime = new Date();
        // System.out.println(getMonthsDifference(startTime, endTime));

        /*
         * System.out.println(getLastDayOfPreviousMonth(new Date(),true));
         * System.out.println(getFirstDayOfNextMonth(new Date(),true));
         * System.out.println(format(getFirstDayOfMonth(new
         * Date()),FORMAT_NORMAL));
         * System.out.println(format(getLastDayOfMonth(new
         * Date()),FORMAT_NORMAL)); System.out.println(format(format(new
         * Date()),FORMAT_NORMAL));
         */
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(createDate(0, 0, 0));
        System.out.println(sf.format(getDateBefore(-1, 0, 0)));
    }

    /**
     * getMonthsDifference：俩个时间的年份之差
     *
     * @param startTime
     * @param endTime
     * @return
     * @see <参见的内容>
     */
    public static int getYearsDifference(Date startTime, Date endTime)
    {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(startTime);
        calendarEnd.setTime(endTime);
        return (calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR)) + (calendarStart.get(Calendar.MONTH) == 0 ? 0 : 1);
    }

    /**
     * getMonthsDifference：俩个时间的月份之差
     *
     * @param startTime
     * @param endTime
     * @return
     * @see <参见的内容>
     */
    public static int getMonthsDifference(Date startTime, Date endTime)
    {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(startTime);
        calendarEnd.setTime(endTime);
        return (calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR)) * 12 + (calendarEnd.get(Calendar.MONTH) - calendarStart.get(Calendar.MONTH));
    }

    /**
     * getMonthsDifference：俩个时间的月份之差
     *
     * @param startTime
     * @return
     * @see <参见的内容>
     */
    public static int getMonthsDifference(Date startTime)
    {
        return getMonthsDifference(startTime, new Date());
    }

    /**
     * getMonthsDifference：俩个时间的年份之差
     *
     * @param startTime
     * @return
     * @see <参见的内容>
     */
    public static int getYearsDifference(Date startTime)
    {
        return getYearsDifference(startTime, new Date());
    }

    /**
     * 得到上个月最后一天
     * 
     * @return isFormatDate is true return string else return date
     */
    public static Object getLastDayOfPreviousMonth(Date date, boolean isFormatDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        if (isFormatDate)
            return formatDate(getLastDayOfMonth(calendar.getTime()));
        return getLastDayOfMonth(calendar.getTime());
    }

    /**
     * 得到下个月第一天
     * 
     * @return isFormatDate is true return string else return date
     */
    public static Object getFirstDayOfNextMonth(Date date, boolean isFormatDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        if (isFormatDate)
            return formatDate(getFirstDayOfMonth(calendar.getTime()));
        return getFirstDayOfMonth(calendar.getTime());
    }

    public static String formatDate(Date date)
    {
        return format(date, FORMAT_NORMAL);
    }

    /**
     * 得到当前月最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date)
    {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.roll(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 得到当前月的第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 得到当年最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.MONTH, 11);
        ca.set(Calendar.DATE, 1);
        ca.roll(Calendar.DAY_OF_MONTH, -1);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MINUTE, 59);
        return ca.getTime();
    }

    /**
     * 得到当年的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }
    /***
     * format：格式当前时间为00:00:00
     * 
     * @param date
     * @return
     * @see <参见的内容>
     */
    public static Date format(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /***
     * format：格式当前时间为59:59:59
     * 
     * @param date
     * @return
     * @see <参见的内容>
     */
    public static Date formatTimeEnd(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
    
    /**
     * 获取当前月的前X个月时间
     * @param data
     * @return
     */
    public static Date formatTimeBeforFive(int month){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        return TimeUtil.getFirstDayOfMonth(c.getTime());
    }
}
