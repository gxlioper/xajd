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
	private static long nd = 1000*24*60*60;//һ��ĺ�����
	private static long nh = 1000*60*60;//һСʱ�ĺ�����
	private static long nm = 1000 * 60;//һ���ӵĺ�����

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
	 * @����:TODO(���㵱ǰ�����ڵĲ�)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-29 ����02:20:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param from
	 * @param to
	 * @param formate ����yyyy-MM-dd yyyyMMdd
	 * @return
	 * String[] �������� 
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
	
	// date��ʽ20001212������һ�ܵĵڼ���
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

	// ����������ڼ�date��ʽ20001212
	public static String getDayOfWeek_CN(String date) {
		int num = getDayOfWeek_num(date);
		String[] week = new String[] { "������", "����һ", "���ڶ�", "������", "������",
				"������", "������" };
		if (num != 0) {
			return week[num - 1];
		} else {
			return null;
		}
	}

	// ����������� date��20100730 ת��Ϊ2010��07��30��
	public static String getDayOfCn(String date) {
		StringBuilder builder = new StringBuilder();
		if (date.contains("-")) {
			date=date.replace("-", "");
		}
		if (date != null && date.length() == 8) {
			builder.append(date.substring(0, 4)).append("��").append(
					date.substring(4, 6)).append("��").append(date.substring(6))
					.append("��");
		}
		
		return  builder.toString();
	}
	// ��ʽ��: YYYY-MM-DD HH:MI:SSת��ΪYYYYMMDD
	public static String getDayOfLongt(String date) {
		StringBuilder builder = new StringBuilder();
		if (date != null && date.length() >= 10) {
			builder.append(date.substring(0, 4)).append(
					date.substring(5, 7)).append(date.substring(8,10))
					;
		}
		
		return  builder.toString();
	}
	// ���ص�ǰ�꣬�£��� ��ʽ�磺2009��12��18��
	public static String getLyr() {
		return getYear() + "��" + getMonth() + "��" + getDayOfMonth() + "��";
	}
	
	//��ʽ��: YYYY-MM-DD HH:MI:SS
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
	 * @����:�Ƚ�ʱ�� ���� �Ƚ� 08:10 �� 09:50 �Ĵ�С
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����11:06:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param time1
	 * @param time2
	 * void �������� 
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
	 * @����:�Ƚ�����ʱ���ʱ������ص�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����11:31:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param startDate
	 * @param endDate
	 * @param compStartDate
	 * @param compEndDate
	 * @return
	 * boolean �������� 
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
     * ����ת����
     * */
    public static String numToZh(String num) {
        StringBuffer rs = new StringBuffer();
        for (int i = 0; i < num.length(); i++) {
        	switch(num.charAt(i)){
        		case '0':
        			rs.append("��");
        			break;
        		case '1':
        			rs.append("һ");
        			break;
        		case '2':
        			rs.append("��");
        			break;
        		case '3':
        			rs.append("��");
        			break;
        		case '4':
        			rs.append("��");
        			break;
        		case '5':
        			rs.append("��");
        			break;
        		case '6':
        			rs.append("��");
        			break;
        		case '7':
        			rs.append("��");
        			break;
        		case '8':
        			rs.append("��");
        			break;
        		case '9':
        			rs.append("��");
        			break;
        		default:
        			break;
        	}
		}
        return rs.toString();
    }
    /**
     * @����: ��ȡ�·�����
     * @���ߣ�����[���ţ�1186]
     * @���ڣ�2016-6-27 ����08:15:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param y
     * @param m
     * @return
     * int �������� 
     * @throws
     */
    public static int getMDay(int y, int m) {
	    int[] mday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    if ((y % 40 == 0 && y % 100 != 0) || y % 400 == 0)//�ж��Ƿ�������
	        mday[1] = 29;
	    return mday[m - 1];
	}
    
    
    /**
     * 
     * @����: ȡ��ǰ���ڵ���һ��
     * @���ߣ������[���ţ�445]
     * @���ڣ�2016��10��15�� ����3:42:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * Date �������� 
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
     * @�������жϵ�ǰʱ���Ƿ������ڶ���
     * @���ߣ�׿��[����:1391]
     * @���ڣ�2016��10��20��
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param starTime
     * @param endTime
     * @return
     * boolean ��������
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
     * @�������жϵ�ǰʱ���Ƿ������ڶ�(DATE)��
     * @���ߣ�׿��[����:1391]
     * @���ڣ�2016��10��20��
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param starTime
     * @param endTime
     * @return
     * boolean ��������
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
    
//=========================================����������ת��============================================
    /**
	 * ��������List
	 */
	private static List<Character> zhNumberList = Arrays.asList('��','һ','��','��','��','��','��','��','��','��','ʮ');
	
	/**
	 * ����������Ϊkey����������Ϊvalue������Map
	 */
	private static Map<Integer,Character> zhNumberMap = new HashMap<Integer,Character>();
	
	/**
	 * ��ʼ������Map
	 */
	static{
		for(int i=0;i<zhNumberList.size();i++){
			zhNumberMap.put(i, zhNumberList.get(i));
		}
	}
	
	/**
	 * 
	 * @����:������������List [��~ʮ]
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:13:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<Character> �������� 
	 * @throws
	 */
	public static List<Character> getZHNumberList(){
		return zhNumberList;
	}
	
	/**
	 * 
	 * @����:������������Map��key:0-10��value:��-ʮ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:13:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Map<Integer,Character> �������� 
	 * @throws
	 */
	public static Map<Integer,Character> getZHNumberMap(){
		return zhNumberMap;
	}
	
	/**
	 * @����:��ȡ��ǰ���ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:15:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getZHYear(){
		Calendar calendar = Calendar.getInstance();
		Integer year = calendar.get(Calendar.YEAR);
		String zhYear = getZHYear(year.toString());
		return zhYear;
	}
	
	/**
	 * @����:��ȡָ�����ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:15:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateStr
	 * ָ�����ڸ�ʽΪ�ַ����磺2017-12-12��Ҳ��ֱ�Ӵ������ַ����磺2017
	 * @return
	 * String �������� 
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
	 * @����:��ȡ��ǰ���ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:16:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getZHMonth(){
		Calendar calendar = Calendar.getInstance();
		Integer month = calendar.get(Calendar.MONTH)+1;
		String zhMonth = getZHMonth(month.toString());
		return zhMonth;
	}
	
	/**
	 * @����:��ȡָ�����ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateStr
	 * ָ�����ڸ�ʽΪ�ַ����磺2017-12-12��Ҳ��ֱ�Ӵ������ַ����磺12��05��5
	 * ע�⣺��ֵ��Χ[1,12]������Ĵ�����С��1��1�㣬����12��12��
	 * @return
	 * String �������� 
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
		
		//��ȡkeyList
		Integer keyInteger = Integer.parseInt(month);
		//���Ϸ�ֵ����
		keyInteger = keyInteger<1?1:(keyInteger>12?12:keyInteger);
		
		List<Integer> keyList = getKeyList(keyInteger);
		for(int i=0;i<keyList.size();i++){
			Integer key = keyList.get(i);
			zhMonth.append(zhNumberMap.get(key));
		}
		return zhMonth.toString();
	}
	
	/**
	 * @����:��ȡ�������ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:33:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getZHDay(){
		Calendar calendar = Calendar.getInstance();
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		String zhDay = getZHDay(day.toString());
		return zhDay;
	}
	
	/**
	 * @����:��ȡָ�����ڵ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateStr
	 * ָ�����ڸ�ʽΪ�ַ����磺2017-12-12��Ҳ��ֱ�Ӵ������ַ����磺12��31,29��05��5
	 * ע�⣺��ֵ��Χ(�������·�)[1-31]������Ĵ�����С��1��1�㣬����31��31��
	 * @return
	 * String �������� 
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
		//��ȡkeyList
		Integer keyInteger = Integer.parseInt(day);
		//���Ϸ�ֵ����
		keyInteger = keyInteger<1?1:(keyInteger>31?31:keyInteger);
		
		List<Integer> keyList = getKeyList(keyInteger);
		for(int i=0;i<keyList.size();i++){
			Integer key = keyList.get(i);
			zhMonth.append(zhNumberMap.get(key));
		}
		return zhMonth.toString();
	}
	
	/**
	 * @����:��ȡ��ǰ���ڵ�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:34:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getZHDate(){
		String zhDate = getZHYear()+"��"+getZHMonth()+"��"+getZHDay()+"��";
		return zhDate;
	}
	
	/**
	 * @����:��ȡָ�����ڵ�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:35:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateStr
	 * ָ�����ڸ�ʽΪ�ַ����磺2017-12-12
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getZHDate(String dateStr){
		String zhDate = getZHYear(dateStr)+"��"+getZHMonth(dateStr)+"��"+getZHDay(dateStr)+"��";
		return zhDate;
	}
	
	/**
	 * @����:��ȡ�º��յ�keyList��ͨ������keyList�е�key�ҵ���Ӧ����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��6�� ����10:35:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * List<Integer> �������� 
	 * @throws
	 */
	private static List<Integer> getKeyList(Integer key){
		List<Integer> keyList = new ArrayList<Integer>();
		//��λ��ֵ
		Integer keySingle = key%10;
		//ʮλ��ֵ
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
    
//=========================================����������ת����end========================================
	
	
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
	 * @����:ȡ���Ĳ��������ڣ�������ʽyyyy-mm-dd
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-24 ����05:26:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @return
	 * @throws ParseException
	 * String �������� 
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
	 * @����:��ȡĳ��ĳ�µ����һ������,ָ�������ʽ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��13�� ����2:39:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param year
	 * @param month
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month,String format){  
        Calendar cal = Calendar.getInstance();  
        //�������  
        cal.set(Calendar.YEAR,year);  
        //�����·�  
        cal.set(Calendar.MONTH, month-1);  
        //��ȡĳ���������  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //�����������·ݵ��������  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //��ʽ������  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        String lastDayOfMonth = sdf.format(cal.getTime());  
          
        return lastDayOfMonth;  
    }
	
	/**
	 * @����:��ȡĳ��ĳ�µ����һ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��13�� ����2:39:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param year
	 * @param month
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month){  
        return getLastDayOfMonth(year, month,"yyyy-MM-dd");
    }  
	
	/**
	 * @����:��ȡĳ��ĳ�µ����һ�죬���������ַ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��13�� ����2:49:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @param format
	 * @return
	 * String �������� 
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
	 * @����: ��������date��ǰ��(num)����(num)��,num��������ʾ����,num��������ʾǰ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-27 ����08:57:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @param num
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getLastDayOrNextDay(String date,int num){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char((to_date(?,'yyyy-mm-dd hh24:mi:ss')+(?)),'yyyy-mm-dd') rnday from dual");
		return DAO.getInstance().getOneRs(sql.toString(),new String[]{date,num+""}, "rnday");
	}

	/**
	 * �����������������ٷ���
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





