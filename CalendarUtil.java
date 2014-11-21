package com.yunyou.tounahao.common.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class CalendarUtil {

	public static String fm = "yyyy年MM月dd日";
	public static String fm2 = "MM月dd日";
	public static String fm3 = "yyyy年MM月";
	public static String fm4 = "dd";
	public static String fm5 = "yyyy-MM-dd";
	/**
	 * 得到本周周一
	 * 
	 * 
	 */
	public static String getMondayOfThisWeek(int i) {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		}
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周日
	 * 
	 * 
	 */
	public static String getSundayOfThisWeek(int i) {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		}
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfMonth(int i) {
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		} else if(i==3){
			sdf = new SimpleDateFormat(fm3);
		} else if(i==4){
			sdf = new SimpleDateFormat(fm4);
		}
		Date dt = new Date();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = sdf.format(cal.getTime());
		return result;
	}
	/**
	 * 得到某年某月最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfMonth(int year ,int month,int i) {
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		} else if(i==3){
			sdf = new SimpleDateFormat(fm3);
		} else if(i==4){
			sdf = new SimpleDateFormat(fm4);
		}
		Date dt = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = sdf.format(cal.getTime());
		return result;
	}

	/**
	 * 得到本月第一天
	 * 
	 * @return
	 */
	public static String getFristDateOfMonth(int i) {
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		}
		Date dt = new Date();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = sdf.format(cal.getTime());
		return result;
	}
	
	/**
	 * 得到今天
	 * 
	 * @return
	 */
	public static String getToday() {
		 Calendar cal=Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 得到今天日期或月份
	 * 
	 * @return
	 */
	public static String getToday(int i) {
		SimpleDateFormat sdf = null;
		if (i == 1) {
			sdf = new SimpleDateFormat(fm);
		} else if (i == 2) {
			sdf = new SimpleDateFormat(fm2);
		}else if(i == 3){
			sdf = new SimpleDateFormat(fm3);
		}
		Date dt = new Date();
		if (dt == null)
			return null;
		String result = sdf.format(dt);
		return result;
	}
	
	/**
	 * 获取当月大写月份
	 * @return
	 */
	public static String getCapitalMonth(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + 1;
	    String temp="";
	    switch (month) {
		case 1:
			temp = "一月";
			break;
		case 2:
			temp = "二月";
			break;
		case 3:
			temp = "三月";
			break;
		case 4:
			temp = "四月";
			break;
		case 5:
			temp = "五月";
			break;
		case 6:
			temp = "六月";
			break;
		case 7:
			temp = "七月";
			break;
		case 8:
			temp = "八月";
			break;
		case 9:
			temp = "九月";
			break;
		case 10:
			temp = "十月";
			break;
		case 11:
			temp = "十一月";
			break;
		case 12:
			temp = "十二月";
			break;

		default:
			break;
		}
	    return temp;
	}
	
	/**
	 * 获取月份
	 * @return
	 */
	public static String getMonth(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + 1;
	    return String.valueOf(month);
	}
	/**
	 * 获取年份
	 * @return
	 */
	public static String getYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}
	
	/**
	 * date加上addDay的日期
	 * @param day
	 * @param addDay
	 * @return
	 * @throws ParseException 
	 */
	public static String getAddDay(String date,int addDay){
		  SimpleDateFormat formatDate = new SimpleDateFormat(fm5); 
		  Date d;
		  Calendar c = Calendar.getInstance();  
		try {
			d = formatDate.parse(date);
			c.setTime(d);
			c.add(Calendar.DATE, addDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return formatDate.format(c.getTime());
	}
	
	/**
	 * 字符串转日期
	 * @param dateStr
	 * @return
	 */
	public static Date StringToDate(String dateStr){
		SimpleDateFormat dd=new SimpleDateFormat(fm5);
		Date date=null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
