package org.merchant.db.util;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 获取当天的开始时间
     * @param date 当前时间
     * @return 当天开始时间
     */
    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 获取当天的结束时间
     * @param date 当前时间
     * @return 当天结束时间
     */
    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

    /**
     * 获取当前所在一周开始时间
     * @param date 当前时间
     * @return 当前所在一周结束时间
     */
    public static Date getBeginDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        // Monday
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        LocalDate toDay = dateToLocalData(c.getTime());
        LocalDateTime localDateTime = toDay.atTime(0, 0, 0);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当前所在一周结束时间
     * @param date 当前时间
     * @return 当前所在一周结束时间
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        // Sunday
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        LocalDate toDay = dateToLocalData(c.getTime());
        LocalDateTime localDateTime = toDay.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当前所在一月开始时间
     * @param date 当前时间
     * @return 当前所在一月开始时间
     */
    public static Date getBeginTimeOfMonth(Date date) {
        LocalDate ld = dateToLocalData(date);
        YearMonth yearMonth = YearMonth.of(ld.getYear(), ld.getMonth());
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当前所在一月结束时间
     * @param date 当前时间
     * @return 当前所在一月结束时间
     */
    public static Date getEndTimeOfMonth(Date date) {
        LocalDate ld = dateToLocalData(date);
        YearMonth yearMonth = YearMonth.of(ld.getYear(), ld.getMonth());
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 将 LocalDateTime 转为 Date
     *
     * @param localDateTime
     * @return java.util.Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将 Date 转为 LocalDateTime
     *
     * @param date
     * @return java.time.LocalDateTime;
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将 Date 转为 LocalDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalData(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将 LocalDate 转为 Date
     *
     * @param localDate
     * @return java.util.Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**

     * 计算两个时间点的天数差

     * @param dt1 第一个时间点

     * @param dt2 第二个时间点

     * @return int，即要计算的天数差

     */

    public static int dateDiff(LocalDateTime dt1,LocalDateTime dt2){

        //获取第一个时间点的时间戳对应的秒数

        long t1 = dt1.toEpochSecond(ZoneOffset.ofHours(0));

        //获取第一个时间点在是1970年1月1日后的第几天

        long day1 = t1 /(60*60*24);

        //获取第二个时间点的时间戳对应的秒数

        long t2 = dt2.toEpochSecond(ZoneOffset.ofHours(0));

        //获取第二个时间点在是1970年1月1日后的第几天

        long day2 = t2/(60*60*24);

        //返回两个时间点的天数差

        return (int)(day2 - day1);

    }
}
