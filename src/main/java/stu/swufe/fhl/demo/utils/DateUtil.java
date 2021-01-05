package stu.swufe.fhl.demo.utils;


import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间格式化工具
 */
public class DateUtil {

    public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";

    // String类型转化为Date
    public static Date StringToDate(String  strDate){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormat.parseDateTime(strDate);
        return dateTime.toDate();
    }

    public static Date StringToDate(String  strDate, String format){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormat.parseDateTime(strDate);
        return dateTime.toDate();
    }

    // Date类型转为String
    public static String DateToString(Date date){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static String DateToString(Date date, String format){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

}
