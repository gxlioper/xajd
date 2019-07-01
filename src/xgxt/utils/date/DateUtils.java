package xgxt.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class DateUtils {
	private static long nd = 1000*24*60*60;//一天的毫秒数
	private static long nh = 1000*60*60;//一小时的毫秒数
	private static long nm = 1000 * 60;//一分钟的毫秒数

	public static String getYear() {
		return Calendar.getInstance().get(Calendar.YEAR) + "";
	}

	public static String getMonth() {
		String result = Calendar.getInstance().get(Calendar.MONTH) + "";
		if (!StringUtils.isNull(result)) {
			result = (Integer.parseInt(result.trim()) + 1) + "";
		}
		if (result.length() != 2)
			result = "0" + result;
		return result;
	}

	public static String getDayOfMonth() {
		String result = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
		if (result.length() != 2)
			result = "0" + result;
		return result;
	}

	public static String getHour() {
		String result = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "";
		if (result.length() != 2)
			result = "0" + result;
		return result;
	}

	public static String getMinute() {
		String result = Calendar.getInstance().get(Calendar.MINUTE) + "";
		if (result.length() != 2)
			result = "0" + result;
		return result;
	}

	public static String getSecond() {
		String result = Calendar.getInstance().get(Calendar.SECOND) + "";
		if (result.length() != 2)
			result = "0" + result;
		return result;
	}

	public static String getTime() {
		return getYear() + getMonth() + getDayOfMonth() + getHour()
				+ getMinute() + getSecond();
	}

	/**
	 * 
	 * @描述:TODO(计算当前到日期的差)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-29 下午02:20:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param from
	 * @param to
	 * @param formate 例如yyyy-MM-dd yyyyMMdd
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public static String[] calYears(String from){
		String curYear =  DateUtils.getYear();
		String curMonth = DateUtils.getMonth();
		if(!StringUtils.isNotNull(from))
			return new String[]{null , null};
		String formateFrom = org.apache.commons.lang.StringUtils.remove(from, "-");
		int yearInt = Integer.valueOf(org.apache.commons.lang.StringUtils.substring(formateFrom, 0, 4));
		int monthInt = Integer.valueOf(org.apache.commons.lang.StringUtils.substring(formateFrom, 4, 6));
		int y = Integer.valueOf(curYear) - yearInt;
		int m = 0;
		if((Integer.valueOf(curMonth) - monthInt) < 0){
			y--;
			m = (Integer.valueOf(curMonth) + 12 - monthInt);
		}else{
			m = (Integer.valueOf(curMonth) - monthInt);
		}
		
		return new String[]{y+"" , m+""};
	}
	
	// date格式20001212，返回一周的第几天
	@SuppressWarnings("deprecation")
	public static int getDayOfWeek_num(String date) {
		if (date != null && date.length() == 8) {
			date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
					+ date.substring(6);
		} else {
			return 0;
		}
		Date dt = new Date(date);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}

	// 获得中文星期几date格式20001212
	public static String getDayOfWeek_CN(String date) {
		int num = getDayOfWeek_num(date);
		String[] week = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四",
				"星期五", "星期六" };
		if (num != 0) {
			return week[num - 1];
		} else {
			return null;
		}
	}

	// 获得中文日期 date如20100730 转换为2010年07月30日
	public static String getDayOfCn(String date) {
		StringBuilder builder = new StringBuilder();
		if (date.contains("-")) {
			date=date.replace("-", "");
		}
		if (date != null && date.length() == 8) {
			builder.append(date.substring(0, 4)).append("年").append(
					date.substring(4, 6)).append("月").append(date.substring(6))
					.append("日");
		}
		
		return  builder.toString();
	}
	// 格式如: YYYY-MM-DD HH:MI:SS转换为YYYYMMDD
	public static String getDayOfLongt(String date) {
		StringBuilder builder = new StringBuilder();
		if (date != null && date.length() >= 10) {
			builder.append(date.substring(0, 4)).append(
					date.substring(5, 7)).append(date.substring(8,10))
					;
		}
		
		return  builder.toString();
	}
	// 返回当前年，月，日 格式如：2009年12月18日
	public static String getLyr() {
		return getYear() + "年" + getMonth() + "月" + getDayOfMonth() + "日";
	}
	
	//格式如: YYYY-MM-DD HH:MI:SS
	public static String getCurrTime() {
		return getYear()+"-" + getMonth() +"-"+ getDayOfMonth() + " "+ getHour() + ":"
				+ getMinute()+ ":" + getSecond();
	}
	
	//yyyy-mm-dd
	public static String getCurrDate(){
		return getYear()+"-" + getMonth() +"-"+ getDayOfMonth();
	}
	//yyyymm
	public static String getCurrYearAndMonth(){
		return getYear() + getMonth();
	}
	
	//yyyymm
	public static String getCurrYearAndMonth2(){
		return getYear() +"-"+ getMonth();
	}
	
	
	
	/**
	 * 
	 * @描述:比较时间 例如 比较 08:10 和 09:50 的大小
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午11:06:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param time1
	 * @param time2
	 * void 返回类型 
	 * @throws
	 */
	public static int compareTimes(String time1 , String time2){
		if(time1 == null || time2 == null)
			return 0;
		int time1Long = Integer.valueOf(time1.split(":")[0]) * 3600 + Integer.valueOf(time1.split(":")[1]) * 60;
		int time2Long = Integer.valueOf(time2.split(":")[0]) * 3600 + Integer.valueOf(time2.split(":")[1]) * 60;
		
		return time1Long - time2Long;
	}
	
	/**
	 * @throws ParseException 
	 * 
	 * @描述:比较两个时间段时候存在重叠
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-25 上午11:31:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param startDate
	 * @param endDate
	 * @param compStartDate
	 * @param compEndDate
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public static boolean checkTimepriodDuplicate(String startDate , String endDate , String compStartDate , String compEndDate) throws ParseException{
		if(startDate == null || endDate == null || compStartDate == null || compEndDate == null )
			return false;
		
		Date startDateObj = org.apache.commons.lang.time.DateUtils.parseDate(startDate, new String[]{"yyyy-MM-dd HH:mm"});
		Date endDateObj = org.apache.commons.lang.time.DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd HH:mm"});
		Date compStartDateObj = org.apache.commons.lang.time.DateUtils.parseDate(compStartDate, new String[]{"yyyy-MM-dd HH:mm"});
		Date compEndDateObj = org.apache.commons.lang.time.DateUtils.parseDate(compEndDate, new String[]{"yyyy-MM-dd HH:mm"});
		if(!(endDateObj.compareTo(compStartDateObj) <= 0 || startDateObj.compareTo(compEndDateObj) >= 0)){
			return true;
		}
		return false;
	}
	
	/**
     * 数字转中文
     * */
    public static String numToZh(String num) {
        StringBuffer rs = new StringBuffer();
        for (int i = 0; i < num.length(); i++) {
        	switch(num.charAt(i)){
        		case '0':
        			rs.append("〇");
        			break;
        		case '1':
        			rs.append("一");
        			break;
        		case '2':
        			rs.append("二");
        			break;
        		case '3':
        			rs.append("三");
        			break;
        		case '4':
        			rs.append("四");
        			break;
        		case '5':
        			rs.append("五");
        			break;
        		case '6':
        			rs.append("六");
        			break;
        		case '7':
        			rs.append("七");
        			break;
        		case '8':
        			rs.append("八");
        			break;
        		case '9':
        			rs.append("九");
        			break;
        		default:
        			break;
        	}
		}
        return rs.toString();
    }
    /**
     * @描述: 获取月份天数
     * @作者：孟威[工号：1186]
     * @日期：2016-6-27 下午08:15:40
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param y
     * @param m
     * @return
     * int 返回类型 
     * @throws
     */
    public static int getMDay(int y, int m) {
	    int[] mday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    if ((y % 40 == 0 && y % 100 != 0) || y % 400 == 0)//判断是否是闰月
	        mday[1] = 29;
	    return mday[m - 1];
	}
    
    
    /**
     * 
     * @描述: 取当前日期的下一天
     * @作者：屈朋辉[工号：445]
     * @日期：2016年10月15日 下午3:42:26
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * Date 返回类型 
     * @throws
     */
    public static Date getNextDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime();
    }
    
    /**
     * @描述：判断当前时间是否在日期段内
     * @作者：卓耐[工号:1391]
     * @日期：2016年10月20日
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param starTime
     * @param endTime
     * @return
     * boolean 返回类型
     * @throws Exception 
     */
    public static boolean betweenTime(String starTime,String endTime) throws Exception{
    	Pattern p=Pattern.compile("^\\d{8}$");
    	SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
    	Date date1=null;
    	Date date2=null;
    	if(StringUtils.isNotNull(starTime)){
    		if(p.matcher(starTime).matches()){
    			date1=format.parse(starTime);
    		}else{
    			date1=com.zfsoft.xgxt.base.export.util.DateUtils.parse(starTime);
    		}
    	}
    	if(StringUtils.isNotNull(endTime)){
    		if(p.matcher(endTime).matches()){
    			date2=format.parse(endTime);
    		}else{
    			date2=com.zfsoft.xgxt.base.export.util.DateUtils.parse(endTime);
    		}
    	}
    	
    	return betweenDate(date1,date2);
    }
    
    /**
     * @描述：判断当前时间是否在日期段(DATE)内
     * @作者：卓耐[工号:1391]
     * @日期：2016年10月20日
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param starTime
     * @param endTime
     * @return
     * boolean 返回类型
     */
    public static boolean betweenDate(Date starDate,Date endDate){
    	Date now=new Date();
    	boolean start=true;
    	boolean end=true;
    	if(null!=starDate){
    		start=now.after(starDate);
    	}
    	if(null!=endDate){
    		endDate.setDate(endDate.getDate()+1); 
    		end=now.before(endDate);
    	}
    	return start&&end;
    }
    
//=========================================纯中文日期转换============================================
    /**
	 * 中文数字List
	 */
	private static List<Character> zhNumberList = Arrays.asList('〇','一','二','三','四','五','六','七','八','九','十');
	
	/**
	 * 阿拉伯数字为key，中文数字为value的数字Map
	 */
	private static Map<Integer,Character> zhNumberMap = new HashMap<Integer,Character>();
	
	/**
	 * 初始化数字Map
	 */
	static{
		for(int i=0;i<zhNumberList.size();i++){
			zhNumberMap.put(i, zhNumberList.get(i));
		}
	}
	
	/**
	 * 
	 * @描述:返回中文数字List [〇~十]
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:13:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<Character> 返回类型 
	 * @throws
	 */
	public static List<Character> getZHNumberList(){
		return zhNumberList;
	}
	
	/**
	 * 
	 * @描述:返回中文数字Map，key:0-10，value:〇-十
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:13:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Map<Integer,Character> 返回类型 
	 * @throws
	 */
	public static Map<Integer,Character> getZHNumberMap(){
		return zhNumberMap;
	}
	
	/**
	 * @描述:获取当前日期的中文年
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:15:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHYear(){
		Calendar calendar = Calendar.getInstance();
		Integer year = calendar.get(Calendar.YEAR);
		String zhYear = getZHYear(year.toString());
		return zhYear;
	}
	
	/**
	 * @描述:获取指定日期的中文年
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dateStr
	 * 指定日期格式为字符串如：2017-12-12，也可直接传入年字符串如：2017
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHYear(String dateStr){
		String year = null;
		StringBuilder zhYear = new StringBuilder();
		if(dateStr.contains("-")){
			year = dateStr.split("-")[0];
		}else{
			year = dateStr;
		}
		for(int i=0;i<year.length();i++){
			Integer key = Integer.parseInt(dateStr.charAt(i)+"");
			zhYear.append(zhNumberMap.get(key));
		}
		return zhYear.toString();
	}
	
	/**
	 * @描述:获取当前日期的中文月
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:16:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHMonth(){
		Calendar calendar = Calendar.getInstance();
		Integer month = calendar.get(Calendar.MONTH)+1;
		String zhMonth = getZHMonth(month.toString());
		return zhMonth;
	}
	
	/**
	 * @描述:获取指定日期的中文月
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dateStr
	 * 指定日期格式为字符串如：2017-12-12，也可直接传入月字符串如：12，05或5
	 * 注意：月值范围[1,12]，这里的处理是小于1按1算，大于12按12算
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHMonth(String dateStr){
		String month = null;
		StringBuilder zhMonth = new StringBuilder();
		if(dateStr.contains("-")){
			month = dateStr.split("-")[1];
		}else{
			month = dateStr;
		}
		
		//获取keyList
		Integer keyInteger = Integer.parseInt(month);
		//不合法值处理
		keyInteger = keyInteger<1?1:(keyInteger>12?12:keyInteger);
		
		List<Integer> keyList = getKeyList(keyInteger);
		for(int i=0;i<keyList.size();i++){
			Integer key = keyList.get(i);
			zhMonth.append(zhNumberMap.get(key));
		}
		return zhMonth.toString();
	}
	
	/**
	 * @描述:获取当期日期的中文日
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:33:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHDay(){
		Calendar calendar = Calendar.getInstance();
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		String zhDay = getZHDay(day.toString());
		return zhDay;
	}
	
	/**
	 * @描述:获取指定日期的中文日
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dateStr
	 * 指定日期格式为字符串如：2017-12-12，也可直接传入日字符串如：12，31,29，05或5
	 * 注意：日值范围(不关联月份)[1-31]，这里的处理是小于1按1算，大于31按31算
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHDay(String dateStr){
		String day = null;
		StringBuilder zhMonth = new StringBuilder();
		if(dateStr.contains("-")){
			day = dateStr.split("-")[2];
		}else{
			day = dateStr;
		}
		//获取keyList
		Integer keyInteger = Integer.parseInt(day);
		//不合法值处理
		keyInteger = keyInteger<1?1:(keyInteger>31?31:keyInteger);
		
		List<Integer> keyList = getKeyList(keyInteger);
		for(int i=0;i<keyList.size();i++){
			Integer key = keyList.get(i);
			zhMonth.append(zhNumberMap.get(key));
		}
		return zhMonth.toString();
	}
	
	/**
	 * @描述:获取当前日期的中文年月日
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:34:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHDate(){
		String zhDate = getZHYear()+"年"+getZHMonth()+"月"+getZHDay()+"日";
		return zhDate;
	}
	
	/**
	 * @描述:获取指定日期的中文年月日
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:35:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dateStr
	 * 指定日期格式为字符串如：2017-12-12
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getZHDate(String dateStr){
		String zhDate = getZHYear(dateStr)+"年"+getZHMonth(dateStr)+"月"+getZHDay(dateStr)+"日";
		return zhDate;
	}
	
	/**
	 * @描述:获取月和日的keyList，通过遍历keyList中的key找到对应的中文数字
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月6日 上午10:35:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * List<Integer> 返回类型 
	 * @throws
	 */
	private static List<Integer> getKeyList(Integer key){
		List<Integer> keyList = new ArrayList<Integer>();
		//个位的值
		Integer keySingle = key%10;
		//十位的值
		Integer keyTens = key/10;
		
		if(keyTens==1){
			keyList.add(10);
		}else if(keyTens>1){
			keyList.add(keyTens);
			keyList.add(10);
		}
		
		if(keySingle>0){
			keyList.add(keySingle);
		}
				
		return keyList;
	}
    
//=========================================纯中文日期转换：end========================================
	
	
	public static String getYea(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date dt = sdf.parse(date);
		cal.setTime(dt);
		return Integer.toString(cal.get(Calendar.YEAR));
	}
	
	public static String getMonth(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date dt = sdf.parse(date);
		cal.setTime(dt);
		return Integer.toString(cal.get(Calendar.MONTH) + 1);
	}
	
	/**
	 * 
	 * @描述:取给的参数的日期，参数格式yyyy-mm-dd
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-24 下午05:26:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @return
	 * @throws ParseException
	 * String 返回类型 
	 * @throws
	 */
	public static String getDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date dt = sdf.parse(date);
		cal.setTime(dt);
		return Integer.toString(cal.get(Calendar.DAY_OF_MONTH) + 1);
	}
	
	
	/**
	 * @描述:获取某年某月的最后一天日期,指定输出格式
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月13日 下午2:39:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param year
	 * @param month
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month,String format){  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR,year);  
        //设置月份  
        cal.set(Calendar.MONTH, month-1);  
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        String lastDayOfMonth = sdf.format(cal.getTime());  
          
        return lastDayOfMonth;  
    }
	
	/**
	 * @描述:获取某年某月的最后一天
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月13日 下午2:39:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param year
	 * @param month
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month){  
        return getLastDayOfMonth(year, month,"yyyy-MM-dd");
    }  
	
	/**
	 * @描述:获取某年某月的最后一天，根据日期字符串
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月13日 下午2:49:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @param format
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getLastDayOfMonth(String date){ 
		if(date.contains("-")){
			int year = Integer.parseInt(date.split("-")[0]);
			int month = Integer.parseInt(date.split("-")[1]);
	        date = getLastDayOfMonth(year, month);
		}
		return date;
    }  
    
	/**
	 * 
	 * @描述: 所给参数date的前几(num)天或后几(num)天,num正整数表示后几天,num负整数表示前几天
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-27 上午08:57:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @param num
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getLastDayOrNextDay(String date,int num){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char((to_date(?,'yyyy-mm-dd hh24:mi:ss')+(?)),'yyyy-mm-dd') rnday from dual");
		return DAO.getInstance().getOneRs(sql.toString(),new String[]{date,num+""}, "rnday");
	}

	/**
	 * 计算两个日期相差多少分钟
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static long getDiffMin(Date beforeDate,Date afterDate){
		long diff = afterDate.getTime() - beforeDate.getTime();
		long min = diff % nd % nh / nm;
		return min;
	}
}





