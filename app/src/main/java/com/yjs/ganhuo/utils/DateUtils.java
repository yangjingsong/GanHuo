package com.yjs.ganhuo.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class DateUtils {
    public static String getDateTitle(int pageNum) {
        String dayOfWeek = "";
        DateTime dateTime = new DateTime();
        switch (dateTime.minusDays(pageNum).getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "星期六";
                break;
        }
        return dateTime.minusDays(pageNum).toString("MM月dd日 " + dayOfWeek);

    }

    public static String getDate(int pageNum) {
        DateTime dateTime = new DateTime();
        return dateTime.plusDays(1).minusDays(pageNum).toString("yyyyMMdd");
    }
}
