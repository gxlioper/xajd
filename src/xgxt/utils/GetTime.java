package xgxt.utils;

import java.text.SimpleDateFormat;
import java.util.*;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GetTime {

	public static List<HashMap<String, String>> getYearList (int lenth){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy') nowYear from dual";
		String nowYear = dao.getOneRs(sql, new String[]{}, new String[]{"nowYear"})[0];
		Integer Year = Integer.parseInt(nowYear);
		String [] yearList = new String[lenth];
		for(int i = 0; i<lenth;i++){
			yearList[i] =  ((Integer)(Year-i)).toString();
		}
		return dao.arrayToList(yearList, yearList); 
	}

	public static String getTime (String year,String month){
		//�Լ�������ݵķ������ԡ�*��*������ʽ��������� �������ݿ��ѯ 
		//�����ֵ��Ϊ��Ҫ,����Action
		String temp = "��";
		if(("").equalsIgnoreCase(month)&&("").equalsIgnoreCase(year)){
			temp = "";
		}
		if(month == null){
			month = "";       
		}
		if(month.length()==1){
			month = "0"+month;
			//�õ���ֵΪ??��??�µ���ʽ
		}
		String time = "%"+year + temp + month+"%";
		return time ;
	}

	public static String getTime2 (String year,String month){
		//�Լ�������ݵķ������ԡ�*��*������ʽ��������� �������ݿ��ѯ 
		//�����ֵ��Ϊ��Ҫ,����Action
		String temp = "-";
		if(("").equalsIgnoreCase(month)&&("").equalsIgnoreCase(year)){
			temp = "";
		}
		if(month == null){
			month = "";
		}
		if(month.length()==1){
			month = "0"+month;
			//�õ���ֵΪ??��??�µ���ʽ
		}
		String time = "%"+year + temp + month+"%";
		return time ;
	}

	public String[] getMonthAwokeTimeByTj(String txrq,String tjsjd) {
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy-mm-dd') data from dual";
		String time = dao.getOneRs(sql, new String[]{}, new String[]{"data"})[0];
		String [] times = time.split("-") ;
		String endTime ="";
		String startTime ="";
		Integer endYear = 0;
		Integer endMonth = 0;
		Integer year = Integer.parseInt(times[0]);
		Integer month =Integer.parseInt(times[1]);
		Integer data = Integer.parseInt(times[2]);
		String tmp2 = "";
		String tmp3 = "";
		int tmp = 0;
		int i = 0;
		int n = 0;
		if(data<Integer.parseInt(txrq)){
			tmp = 1;
		}
		if(tjsjd.equalsIgnoreCase("��������")){
			year = 9999;
		}else if (tjsjd.equalsIgnoreCase("1����֮��")){
			i = 1;
		}else if (tjsjd.equalsIgnoreCase("2����֮��")){
			i = 2;
		}else if (tjsjd.equalsIgnoreCase("3����֮��")){
			i = 3;
		}else if (tjsjd.equalsIgnoreCase("����֮��")){
			i = 6;
		}else if (tjsjd.equalsIgnoreCase("һ��֮��")){
			n = 1;
		}else if (tjsjd.equalsIgnoreCase("����֮��")){
			n = 3;
		}
		if((month-tmp-1)<0){
			endYear = year-1;
			endMonth = 12+month-tmp-1;
		}else{
			endYear = year;
			endMonth = month-tmp-1;
		}
		if((endMonth.toString()).length()==1){
			tmp3 = "0";
		}
		endTime = endYear.toString()+"-"+tmp3+endMonth.toString();
		if((month-i-tmp-1)<0){
			year = year-1;
			month = 12+month-i-tmp-1;
		}else{
			month = month - i-tmp-1;
		}	
		year = year-n;
		if((month.toString()).length()==1){
			tmp2 = "0";
			//�õ���ֵΪ??��??�µ���ʽ
		}
		startTime = year.toString()+"-"+tmp2+month.toString();
		return new String [] {startTime,endTime};
	}

	public String[] getYearAwokeTimeByTj(String tjsjd, String sbntxrq,String xbntxrq) {
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy-mm') data from dual";
		String time = dao.getOneRs(sql, new String[]{}, new String[]{"data"})[0];
		String [] times = time.split("-") ;
		String endTime ="";
		String startTime ="";
		String tmp2 = "";
		String tmp3 = "";
		Integer endYear = 0;
		Integer endMonth = 0;
		Integer tmp = 0;
		Integer year = Integer.parseInt(times[0]);
		Integer month =Integer.parseInt(times[1]);
		if(2<month&&month<9){
			tmp = Integer.parseInt(sbntxrq);
		}else{
			tmp = Integer.parseInt(xbntxrq);
		}
		if(tmp>=month){
			month = month - 7;
		}else{
			month = month - 1;
		}
		int i = 0;
		int n = 0;
		if(tjsjd.equalsIgnoreCase("��������")){
			year = 9999;
		}else if (tjsjd.equalsIgnoreCase("1����֮��")){
			i = 1;
		}else if (tjsjd.equalsIgnoreCase("2����֮��")){
			i = 2;
		}else if (tjsjd.equalsIgnoreCase("3����֮��")){
			i = 3;
		}else if (tjsjd.equalsIgnoreCase("����֮��")){
			i = 6;
		}else if (tjsjd.equalsIgnoreCase("һ��֮��")){
			n = 1;
		}else if (tjsjd.equalsIgnoreCase("����֮��")){
			n = 3;
		}
		if(month<0){
			endYear = year-1;
			endMonth = 12+month;
		}else{
			endYear = year;
			endMonth = month;
		}
		if((endMonth.toString()).length()==1){
			tmp3 = "0";
		}
		endTime = endYear.toString()+"-"+tmp3+endMonth.toString();
		if((month-i)<0&&(month-i)>-12){
			year = year-1;
			month = 12+month-i;
		}else if((month-i)==12){
			year = year-2;
			month = 12;
		}else{
			month = month -i;
		}
		if((month.toString()).length()==1){
			tmp2 = "0";
		}
		year = year-n;
		startTime = year.toString()+"-"+tmp2+month.toString();
		return new String [] {startTime,endTime};
	}

	public static String getNowYear (){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy') nowYear from dual";
		String nowYear = dao.getOneRs(sql, new String[]{}, new String[]{"nowYear"})[0];
		return nowYear; 
	}
	
	/**
	 * XXXX��XX��XX��
	 * */
	public static String getNowTime(){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'YYYY')||'��'||to_char(sysdate,'MM')||'��'||to_char(sysdate,'DD')||'��' nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		return nowTime; 
	}
	
	/**
	 * XXXX-XX-XX
	 * */
	public static String getSystemTime(){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy-mm-dd') nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		return nowTime; 
	}
	
	public static String getNowTime3(){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'YYYY')||'-'||to_char(sysdate,'MM')||'-'||to_char(sysdate,'DD')||'��' nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		return nowTime; 
	}
	
	public static String getNowTime4(){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy-mm-dd HH24:MI:SS') time from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, "time");
		return nowTime; 
	}
	
	public static String getNowTime2(){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'YYYY')||to_char(sysdate,'MM')||to_char(sysdate,'DD') nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		return nowTime; 
	}
	
	public static String getNowDay(){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'DD') nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		if(nowTime.length()==2){
			if((nowTime.substring(0, 1).equalsIgnoreCase("0"))){
				nowTime=nowTime.substring(1, 2);
			}
		}
		
		return nowTime; 
	}
	
	public static String getNowMonth(){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'mm') nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		if(nowTime.length()==2){
			if((nowTime.substring(0, 1).equalsIgnoreCase("0"))){
				nowTime=nowTime.substring(1, 2);
			}
		}
		
		return nowTime; 
	}	
	
	public static String getXskhrq (){
		//�����ڹ���ѧѧ������������д���·� 
		//�����ֵ��Ϊ��Ҫ,����Action
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy-mm-dd') nowTime from dual";
		String timeTmp = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		String timeList [] = timeTmp.split("-");
		Integer yearNum = Integer.parseInt(timeList[0]);
		Integer monthNum =Integer.parseInt(timeList[1]);
		Integer data =Integer.parseInt(timeList[2]);
		if(data>26){
			monthNum+=1;
		}
		if(monthNum>12){
			yearNum+=1;
			monthNum-=12;
		}
		String time =yearNum.toString()+ monthNum.toString();
		return time ;
	}
	
	
	
	
	//��������֮����������
	/**
	 * @param time1  format:("YYYYMMDDHH24MISS")
	 * @param time2  format:("YYYYMMDDHH24MISS")
	 * @return  ��������֮����������
	 */
	public static String getDaysOfTowDateBySec(String time1,String time2){
		DAO dao = DAO.getInstance();
		String sql = "select round(to_date('"+time1+"','YYYYMMDDHH24MISS') - to_date('"+time2+"','YYYYMMDDHH24MISS')) days from dual";
		String days = dao.getOneRs(sql, new String[]{}, "days"); 		
		return days;
	}
	
//	��������֮����������
	/**
	 * @param time1  format:("yyyy-mm-dd")
	 * @param time2  format:("yyyy-mm-dd")
	 * @return  ��������֮����������
	 */

	public static String getDaysOfTowDate(String time1,String time2){
		
		String days = "��������";
		long quot = 0; 
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); 
		
		try {
			if(!Base.isNull(time1) && !Base.isNull(time1)){
				Date date1 = ft.parse( time1 ); 
				Date date2 = ft.parse( time2 ); 
				quot = date1.getTime() - date2.getTime(); 
				quot = quot / 1000 / 60 / 60 / 24; 	
				days =String.valueOf(Math.abs(quot));
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("��������ʱ���������ʽ����...");
		}
		return days;
	}
	
	public static String getNowTime5(){
		//�õ�����б�lenthΪ��ǰ��ݵ���ǰ����
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'YYYY')||'��'||to_char(sysdate,'MM')||'��' nowTime from dual";
		String nowTime = dao.getOneRs(sql, new String[]{}, new String[]{"nowTime"})[0];
		return nowTime; 
	}
	//��õ�ǰ�ܴ�
	/**
	 * @param date  format:("yyyymmdd")
	 */
	public static int getDqzs(String date) throws Exception{
		DAO dao = DAO.getInstance();
		int zs = 0;
		String sql = "select xqzs,qsrq from xtszb";
		String[] qsrq = dao.getOneRs(sql, new String[]{}, new String[]{"xqzs","qsrq"});
		if(qsrq != null && qsrq.length > 1 && !Base.isNull(qsrq[1]) && !Base.isNull(qsrq[0])){
			date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
			String kssj = qsrq[1].substring(0,4)+"-"+qsrq[1].substring(4,6)+"-"+qsrq[1].substring(6);
			String daynum = getDaysOfTowDate(date,kssj);
			int days = Integer.parseInt(daynum)+1;
			if (kssj.compareTo(date) <= 0 && days <= 7 * Integer.parseInt(qsrq[0])) {
				if (days % 7 > 0) {
					zs = days / 7 + 1;
				} else {
					zs = days / 7;
				}
			}
		}			
		return zs;	
	}
	
	
	/**
	 * ��ȡָ����ʽ�ĵ�ǰʱ��
	 * @param format
	 * @return
	 */
	public static String getTimeByFormat(String format){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,?) nowTime from dual";
		
		return dao.getOneRs(sql, new String[]{format}, "nowTime");
	}
}
