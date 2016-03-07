package com.alex.onlinetest.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tools {
	
	/**
	 * 根据系统时间，生成一个格式为yyyyMMddHHmmss格式的字符串
	 * @return 根据系统时间，生成一个格式为yyyyMMddHHmmss格式的字符串
	 */
	public static String getCurrentDateTime(){
		
		SimpleDateFormat smpDF = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return smpDF.format(new Date());
	}
	
	public static Date formatStringToDate(String strdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = sdf.parse(strdate); 
		return date;
	}
	
	public static String formatDateToString(Date mydate) throws Exception {
		SimpleDateFormat smpDF = new SimpleDateFormat("yyyy-MM-dd");
		return smpDF.format(mydate);
	}
	
	/**
	 * 计算两个Date之间的时间差
	 * @param date1	时间1
	 * @param date2	时间2
	 * @return 时间2-时间1之间的差值，单位为秒
	 */
	public static Integer differenceOfDate(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date1);
		cal2.setTime(date2);
		
		return (cal2.get(Calendar.HOUR)-cal1.get(Calendar.HOUR))*3600 + (cal2.get(Calendar.MINUTE)-cal1.get(Calendar.MINUTE))*60 + (cal2.get(Calendar.SECOND)-cal1.get(Calendar.SECOND));
	}
	
	/**
	 * 将分钟转为秒数
	 * @param mins
	 * @return double类型分钟数所对应的秒数
	 */
	public static int transferMin2Sec(float mins) {
		return (int)mins*60;
	}
	
	public static int transferMin2Sec(int mins) {
		return mins*60;
	}
	
	/**
	 * 将一个List<String>转换为1,2,3的格式
	 * @param stl
	 * @return
	 */
	public static String transferListStringToString(List<String> stl) {
		
		String result="";
		
		for (int i=0; i<stl.size(); i++) {
			if (i==(stl.size()-1)) {
				result = result + stl.get(i);
			} else {
				result = result + stl.get(i) + ",";
			}
		}
		
		return result;
	}
	
	public static List<Integer> splitQuestionID(String questionids) {
		List<Integer> question_ids = new ArrayList<Integer>();
		String[] strarray = questionids.split(",");
		for (int i=0; i<strarray.length; i++) {
			question_ids.add(Integer.parseInt(strarray[i]));
		}
		return question_ids;
	}

}
