package com.afeng.utils.javaBasis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    
    public static void main(String[] args) {
    		System.out.println(compareDate("2017-09-09 12:10:23.213 EST", "2017-09-09 12:20:23.213 EST"));
	}

    /**
     * 比较日期，格式：2017-09-09 12:10:23.213 EST
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static int compareDate(String dateStr1, String dateStr2) {
        Date date1;
        Date date2;
        try {
            date1 = DateUtils.parseDate(dateStr1, "yyyy-MM-dd HH:mm:ss.SSS Z");
            date2 = DateUtils.parseDate(dateStr2, "yyyy-MM-dd HH:mm:ss.SSS Z");
            if (date1.after(date2)) {
                return -1;
            } else if (date1.before(date2)) {
                return 1;
            } else {
                return 1;
            }
        } catch (ParseException e) {
            String errmsg = "读取数据日期格式解析异常" + e;
            LOGGER.error(errmsg,e);
            return 0;
        }
    }

    /**
     * 比较日期，指定格式
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public int compareDate(String dateStr1, String dateStr2, String pattern) {
        Date date1;
        Date date2;
        try {
            date1 = DateUtils.parseDate(dateStr1, pattern);
            date2 = DateUtils.parseDate(dateStr2, pattern);
            if (date1.after(date2)) {
                return -1;
            } else if (date1.before(date2)) {
                return 1;
            } else {
                return 1;
            }
        } catch (ParseException e) {
            String errmsg = "读取es数据日期格式解析异常" + e;
            return 0;
        }
    }

    public long timeStrToTimeStampMS(String timeStr, String pattern) throws Exception {
        if (StringUtils.isBlank(timeStr)) {
            return 0;
        }
        Date date = DateUtils.parseDate(timeStr, pattern);
        return date.getTime();
    }

    public long timeStrToTimeStampS(String timeStr, String pattern) throws Exception {
        return timeStrToTimeStampMS(timeStr, pattern) / 1000;
    }

    public String timeStampToTimeStrMS(String timeStamp, String pattern) {
        Date date = new Date(Long.parseLong(timeStamp));
        String timeStr = DateFormatUtils.format(date, pattern);
        return timeStr;
    }

    public String timeStampToTimeStrS(String timeStamp, String pattern) {
        long ts = Long.parseLong(timeStamp);
        timeStamp = String.valueOf(ts * 1000);
        return timeStampToTimeStrMS(timeStamp, pattern);
    }

    public void handleResponse(HttpServletResponse response, String resultStr) throws Exception {
        response.getOutputStream().write(resultStr.getBytes("utf8"));
        response.setCharacterEncoding("utf8");
        response.setContentType("application/json");
        response.getOutputStream().flush();
    }


    /*
     * 时间戳转换成指定格式的字符串
     */
    public String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public Date getDefaultBeginDate() {
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.HOUR_OF_DAY, 0);
        currentTime.set(Calendar.MINUTE, 0);
        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);
        Date beginTime = currentTime.getTime();
        return beginTime;
    }

    public Date getDefaultBeginDate(Date date) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(date);
        currentTime.set(Calendar.HOUR_OF_DAY, 0);
        currentTime.set(Calendar.MINUTE, 0);
        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);
        Date beginTime = currentTime.getTime();
        return beginTime;
    }

    public Date getDefaultBeginDate(String beginTimeStr) {
        Date beginTime = null;
        if (StringUtils.isNotBlank(beginTimeStr)) {
            try {
                beginTime = DateUtils.parseDate(beginTimeStr, "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                String errmsg = "传入的日期格式解析失败";
                beginTime = getDefaultBeginDate();
            }
        } else {
            beginTime = getDefaultBeginDate();
        }
        return beginTime;
    }

    public Date getDefaultBeginDate(String beginTimeStr, String pattern) {
        Date beginTime = null;
        if (StringUtils.isNotBlank(beginTimeStr)) {
            try {
                beginTime = DateUtils.parseDate(beginTimeStr, pattern);
            } catch (ParseException e) {
                String errmsg = "传入的日期格式解析失败";
                beginTime = getDefaultBeginDate();
            }
        } else {
            beginTime = getDefaultBeginDate();
        }
        return beginTime;
    }

    public Date getDefaultEndDate(String endTimeStr) {
        Date endTime = null;
        if (StringUtils.isNotBlank(endTimeStr)) {
            try {
                endTime = DateUtils.parseDate(endTimeStr, "yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                String errmsg = "传入的日期格式解析失败";
                endTime = new Date();
            }
        } else {
            endTime = new Date();
        }
        return endTime;
    }

    public Date addOneday(Date time) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            cal.add(Calendar.DATE, 1);
            Date date = cal.getTime();
            return date;
        } catch (Exception ex) {
            String errmsg = "输入格式错误";
            return null;
        }
    }

    public String timeFormatTransfer(String timeStr, String formatFrom, String formatTo) throws ParseException {
        Date date = DateUtils.parseDate(timeStr, formatFrom);
        String logTime = DateFormatUtils.format(date, formatTo);
        return logTime;
    }

    public String[] getCurrentAndLastMonthTime() {
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.DAY_OF_MONTH, 1);
        Date currentMonth = currentTime.getTime();
        currentTime.set(Calendar.MONTH, currentTime.get(Calendar.MONTH) - 1);
        Date lastMonth = currentTime.getTime();
        System.out.println(currentMonth);
        System.out.println(lastMonth);
        String currentMonthStamp = Long.toString(currentMonth.getTime() / 1000);
        String lastMonthStamp = Long.toString(lastMonth.getTime() / 1000);
        String[] times = { lastMonthStamp, currentMonthStamp };
        return times;
    }

    public Date getDefaultBeginDateByTimestamp(String beginTimeStr) {
        Date beginTime = null;
        if (StringUtils.isNotBlank(beginTimeStr)) {
            try {
                long bTime = Long.parseLong(beginTimeStr);
                beginTime = new Date(bTime * 1000);
            } catch (Exception e) {
                String errmsg = "传入的日期格式解析失败";
                beginTime = getDefaultBeginDate();
            }
        } else {
            beginTime = getDefaultBeginDate();
        }
        return beginTime;
    }

    public Date getDefaultEndDateByTimestamp(String endTimeStr) {
        Date beginTime = null;
        if (StringUtils.isNotBlank(endTimeStr)) {
            try {
                long bTime = Long.parseLong(endTimeStr);
                beginTime = new Date(bTime * 1000);
            } catch (Exception e) {
                String errmsg = "传入的日期格式解析失败";
                beginTime = getDefaultBeginDate();
            }
        } else {
            beginTime = new Date();
        }
        return beginTime;
    }

    

}
