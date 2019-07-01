/**
 * @部门:学工产品事业部
 * @日期：2013-7-3 上午10:42:27 
 */
package com.zfsoft.xgxt.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:
 * @作者： zhangjw
 * @时间： 2013-7-3 上午10:42:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DateTranCnDate {
	public DateTranCnDate() {

	}

	/**
	 * @描述:字符串日期转换成中文格式日期
	 * @作者：zhangjw
	 * @日期：2013-7-3 上午10:43:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date yyyy-MM-dd
	 * @return yyyy年MM月dd日
	 * String 返回类型
	 */
	public static String dateToCnDate(String date) {
		String result = "";
		String[] cnDate = new String[] { "〇", "一", "二", "三", "四", "五", "六",
				"七", "八", "九" };
		String ten = "十";
		String[] dateStr = date.split("-");
		for (int i = 0; i < dateStr.length; i++) {
			for (int j = 0; j < dateStr[i].length(); j++) {
				String charStr = dateStr[i];
				String str = String.valueOf(charStr.charAt(j));
				if (charStr.length() == 2) {
					if (charStr.equals("10")) {
						result += ten;
						break;
					} else {
						if (j == 0) {
							if (charStr.charAt(j) == '1')
								result += ten;
							else if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)] + ten;
						}
						if (j == 1) {
							if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)];
						}
					}
				} else {
					result += cnDate[Integer.parseInt(str)];
				}
			}
			if (i == 0) {
				result += "年";
				continue;
			}
			if (i == 1) {
				result += "月";
				continue;
			}
			if (i == 2) {
				result += "日";
				continue;
			}
		}
		return result;
	}
	/**
	 * @描述:字符串日期转换成中文格式日期 获取月份
	 * @作者：zhangjw
	 * @日期：2013-7-3 上午10:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date yyyy-MM-dd
	 * @return yyyy年MM月dd日
	 * String 返回类型
	 */
	public static String dateToCnDateSubMonth(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(0,oldDate.indexOf("月")+1);
	}
	
	/**
	 * 
	 * @描述: 东北石油大学-只截取大写月份
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-19 上午10:41:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String dateToCnDateSubOnlyMonth(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(5,oldDate.indexOf("月"));
	}
	
	/**
	 * 
	 * @描述: 东北石油大学-只截取大写日期
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-19 上午10:47:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String dateToCnDateSubOnlyDay(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(oldDate.lastIndexOf("月")+1,oldDate.indexOf("日"));
	} 

	/**
	 * @描述:字符串日期转换成中文格式日期
	 * @作者：zhangjw
	 * @日期：2013-7-3 上午10:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date yyyy-MM-dd
	 * @return yyyy年MM月dd日
	 * String 返回类型
	 */
	public static String dateToCnDateSubYear(String date) {
		String result = "";
		String[] cnDate = new String[] { "〇", "一", "二", "三", "四", "五", "六",
				"七", "八", "九" };
		String ten = "十";
		String[] dateStr = date.split("-");
		for (int i = 0; i < dateStr.length; i++) {
			for (int j = 0; j < dateStr[i].length(); j++) {
				String charStr = dateStr[i];
				String str = String.valueOf(charStr.charAt(j));
				if (charStr.length() == 2) {
					if (charStr.equals("10")) {
						result += ten;
						break;
					} else {
						if (j == 0) {
							if (charStr.charAt(j) == '1')
								result += ten;
							else if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)] + ten;
						}
						if (j == 1) {
							if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)];
						}
					}
				} else {
					result += cnDate[Integer.parseInt(str)];
				}
			}
		}
		result = result.substring(0, 4);
		return result;
	}
	//格式化类型
	public enum FomartDateType{year,month,day};
	/**
	 * 
	 * @描述:格式化时间，根据给定类型返回固定格式
	 *       （获取到年 月 日）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-22 上午10:46:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @param type
	 * @return
	 * String 返回类型 
	 */
	public static String fomartDate(String date,FomartDateType type){
		//默认到日
		if(null==type){
			type=FomartDateType.day;
		}
		int i=-1;
		switch (type) {
		case year:
			i=date.indexOf("年");
			break;
		case month:
			i=date.indexOf("月");
			break;
		case day:
			i=date.indexOf("日");
			break;
		default:
			break;
		}
		return date=date.substring(0,i+1);
	}
	/**
	 * @描述:转换字符串日期为‘中文常用’日期
	 * 		（如：20130809或 2013-09-08  转为2013年8月9日 ）
	 * 		 (暂时支持一位智能分割)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-21 下午04:58:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * String 返回类型 
	 */
	public static String fomartDateToCn(String data){
		return fomartDateToCn(data,FomartDateType.day);
	}
	/**
	 * @描述:转换字符串日期为‘中文常用’日期
	 * 		（如：20130809或 2013-09-08  转为2013年8月9日 ）
	 * 		 (暂时支持一位智能分割)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-21 下午04:58:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * String 返回类型 
	 */
	public static String fomartDateToCn(String data ,FomartDateType type){
		if(null==data){
			return data;
		}
		String chData=data;
		if(data.length()==8){
			chData=fomartDateToCn(data, null,type);
		}else if(data.length()>8){//智能尝试获取分隔符
			//暂时只支持一位分隔符，后续可以添加其他，例如如果字符不是数字则认为为分隔符部分。
			String splitA=data.substring(4,5);
			String splitB=data.substring(7,8);
			if(splitA.equals(splitB)){
				int lastIndex=data.lastIndexOf(splitB);
				data=data.substring(0,lastIndex+3);
				chData=fomartDateToCn(data, splitA,type);
			}
		}
		return chData;
	}
	/**
	 * @描述:转换字符串日期为‘中文常用’日期
	 * 		（如：20130809或 2013-09-08  转为2013年8月9日 ）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-21 下午04:33:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data 字符串日期
	 * @param splitStr 日期分隔符（2013-09-08的分隔符为 "-"）
	 * @return 
	 * String 返回类型 
	 */
	public static String fomartDateToCn(String date,String splitStr){
		return fomartDateToCn(date,null,FomartDateType.day);
	}
	/**
	 * @描述:转换字符串日期为‘中文常用’日期
	 * 		（如：20130809或 2013-09-08  转为2013年8月9日 ）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-21 下午04:33:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data 字符串日期
	 * @param splitStr 日期分隔符（2013-09-08的分隔符为 "-"）
	 * @return 
	 * String 返回类型 
	 */
	public static String fomartDateToCn(String date,String splitStr,FomartDateType type){
		String year;
		String month;
		String day;
		if(date==null||date.length()<8){
			return date;
		}else if(null==splitStr){
			year=date.substring(0,4);
			month=date.substring(4,6);
			day=date.substring(6,8);
		}else{
			String datas[]=date.split(splitStr);
			year=datas[0];
			month=datas[1];
			day=datas[2];
		}
		
		date=year+"年"+month+"月"+day+"日";
		return fomartDate(date, type);
	}

	/**
	 * 
	 * @描述:比较时间大小（less是否比more小）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 下午04:06:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param less 小的时间
	 * @param more 大的时间
	 * @return boolean
	 * @throws ParseException boolean 返回类型
	 */
	public static boolean compareDate(String less, String more) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
		Date first = sdf.parse(more);
		Date second = sdf.parse(more);
		return first.getTime() < second.getTime();
	}
	
	/**
	 * 
	 * @描述: 取星期几（周末为1周六为7依次）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-22 上午08:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static void main(String[] args) throws ParseException {
//		String year =fomartDateToCn("20130809",FomartDateType.day);
			/*dateToCnDate(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));*/
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = sdf.parse("2016-02-20");
		int a = getDay(d);
		
//		String m = year.substring(5,6);
		System.out.println(a);
	}
}
