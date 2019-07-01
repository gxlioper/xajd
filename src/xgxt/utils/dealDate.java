package xgxt.utils;

import java.util.*;
import java.text.*;

//����ʱ�����
public class dealDate {

	// ���������ʱ���֮����������죬���������ʼʱ�䣬��ʽ��yy-mm-hh(1985-09-06)��ʽ���ַ��������ص���long ����
	@SuppressWarnings("deprecation")
	public long cont2(String fromRq, String toRq) {
		long l = 0;
		String s1 = toRq;
		String s2 = fromRq;
		String t1 = s1.replace('-', '/');
		String t2 = s2.replace('-', '/');
		try {
			Date dt1 = new Date(t1);
			Date dt2 = new Date(t2);
			// System.out.println("dt1="+dt1.getTime());
			// System.out.println("dt2="+dt2.getTime());
			l = dt1.getTime() - dt2.getTime();
			// System.out.println("---------->;"+l/60/60/1000/24);

		} catch (Exception e) {
			System.out.println("exception" + e.toString());
		}
		return l / 60 / 60 / 1000 / 24;
	}

	public Date getDate(int days) {
		Date dateresult = new Date();
		try {
			// DateFormat dateFormat =
			// DateFormat.getDateInstance(DateFormat.FULL);
			// Create our Gregorian Calendar.
			GregorianCalendar cal = new GregorianCalendar();
			// Set the date and time of our calendar
			// to the system&s date and time
			cal.setTime(new Date());
			cal.add(GregorianCalendar.DAY_OF_MONTH, days);
			dateresult = cal.getTime();
			// String dateresu=dateresult.toString();
		} catch (Exception e) {
			System.out.println("exception" + e.toString());
		}
		/* System.out.println("asdfsadf"+dateresult.toLocaleString()); */

		return dateresult;
	}

	// �������ĳ�쿪ʼ����days֮������ڣ����ص����ַ������͵�ʱ��
	@SuppressWarnings("deprecation")
	public String getDate2(int days, String date) {
		Date dateresult = new Date();
		String t1 = date.replace('-', '/');
		Date dt1 = new Date(t1);
		try {
			// DateFormat dateFormat =
			// DateFormat.getDateInstance(DateFormat.FULL);
			// Create our Gregorian Calendar.
			GregorianCalendar cal = new GregorianCalendar();
			// Set the date and time of our calendar
			// to the system&s date and time
			cal.setTime(dt1);
			cal.add(GregorianCalendar.DAY_OF_MONTH, days);
			dateresult = cal.getTime();
			// String dateresu=dateresult.toString();
		} catch (Exception e) {
			System.out.println("exception" + e.toString());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateresult);
		String result = sdf.format(dateresult);
		return result;
	}

	public String getToday() {
		// Date dateresult=new Date();
		Date dd = new Date();
		// Calendar calendar = Calendar.getInstance();
		// getTime()������ȡ�õ�ǰ�����ڣ��䷵��ֵ��һ��java.util.Date��Ķ���

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(dd);
		return result;
	}

	public String getTime() {
		// Date dateresult=new Date();
		// Date dd = new Date();
		Calendar calendar = Calendar.getInstance();
		String hh = "";
		if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
			hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			hh = "0" + hh;
		} else {
			hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		}
		String ss = String.valueOf(calendar.get(Calendar.MINUTE));
		String times = hh + ":" + ss;
		return times;
	}

	public String getDateBounds(java.util.Date start, java.util.Date end) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		start.setTime(86400000 * (long) (start.getTime() / 86400000));
		end.setTime(86400000 * (long) (end.getTime() / 86400000));
		int length = (int) (end.getTime() - start.getTime()) / 86400000;
		String value = "";
		for (int i = 0; i < length; i++)
			value += sdf.format(new java.util.Date(start.getTime() + i
					* 86400000))
					+ (i == length - 1 ? "" : ",");
		value = "{" + value + "}";
		return value;
	}

	// ������ӿ�ʼ������֮�������ʱ�䣬���ص�������
	@SuppressWarnings("deprecation")
	public String[] getDateBounds2(String start, String end) {
		String t1 = start.replace('-', '/');
		String t2 = end.replace('-', '/');
		Date dt1 = new Date(t1);
		Date dt2 = new Date(t2);
		// java.text.SimpleDateFormat sdf = new
		// java.text.SimpleDateFormat("yyyy-MM-dd");
		dt1.setTime(86400000 * (long) (dt1.getTime() / 86400000));
		dt2.setTime(86400000 * (long) (dt2.getTime() / 86400000));
		long len = this.cont2(start, end);
		if (len == 0) {
			String[] date = { start };
			return date;
		} else {
			// int length=(int)(dt2.getTime() - dt1.getTime())/86400000;
			String value = "";
			StringBuffer stb = new StringBuffer();
			// System.out.println(this.getDate2(1,start));

			for (int i = 0; i <= len; i++) {
				stb.append(this.getDate2(i, start) + (i == len ? "" : ","));
				// value += sdf.format(new
				// java.util.Date(dt1.getTime()+i*86400000))+(i==len-1?"":",");
			}
			value = stb.toString();
			String[] date = value.split(",");
			return date;
		}

	}

	// ������ӿ�ʼ������֮�������ѡ�����ڼ���ʱ�䣬���ص�������
	@SuppressWarnings("deprecation")
	public String[] getDateBounds3(String start, String end,String day)   { 
		String t1 = start.replace('-','/');
		String t2 = end.replace('-','/');
		Date dt1= new Date(t1);
		Date dt2= new Date(t2);
//		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");   
		dt1.setTime(86400000*(long)(dt1.getTime()/86400000));   
		dt2.setTime(86400000*(long)(dt2.getTime()/86400000)); 
		long len=this.cont2(start, end);
		if(len==0)
		{
			String []date={start};
			return date; 
		}
		else
		{
//			int length=(int)(dt2.getTime() - dt1.getTime())/86400000;   
			String value = ""; 
			StringBuffer stb=new StringBuffer();
			//   System.out.println(this.getDate2(1,start));

			for (int i=0;i<=len;i++) 
			{	
				if(!("".equals(day))){
					String monthday = this.getDate2(i,start);
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					Date d;
					int y;
					try {
						d = f.parse(monthday);
						rightNow.setTime(d);
						y = rightNow.get(Calendar.DAY_OF_WEEK);
						int day1 = Integer.parseInt(day);
						if(y == day1){
							stb.append(this.getDate2(i,start)+(i==len?"":","));
						}
//						System.out.println(y);
					} catch (ParseException e) {
						e.printStackTrace();
						}
				}else{
				stb.append(this.getDate2(i,start)+(i==len?"":","));
				}
			}
			value=stb.toString();
			String []date=value.split(",") ;
			return date; 
		}

	}
	
	
	/**
	 * ��������ʱ��֮����������
	 * @param str1
	 * @param str2
	 * @return
	 * @author �����
	 */
	public static int getMonths(String str1, String str2){      
	       int iMonth = 0;      
	       int flag = 0;      
	       try{      
	           Calendar objCalendarDate1 = Calendar.getInstance();      
	           objCalendarDate1.setTime(DateFormat.getDateInstance().parse(str1));      
	     
	           Calendar objCalendarDate2 = Calendar.getInstance();      
	           objCalendarDate2.setTime(DateFormat.getDateInstance().parse(str2));      
	     
	           if (objCalendarDate2.equals(objCalendarDate1))      
	               return 0;      
	           if (objCalendarDate1.after(objCalendarDate2)){      
	               Calendar temp = objCalendarDate1;      
	               objCalendarDate1 = objCalendarDate2;      
	               objCalendarDate2 = temp;      
	           }      
	           if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))      
	               flag = 1;      
	     
	           if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))      
	               iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))      
	                       * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)      
	                       - objCalendarDate1.get(Calendar.MONTH);      
	           else     
	               iMonth = objCalendarDate2.get(Calendar.MONTH)      
	                       - objCalendarDate1.get(Calendar.MONTH) - flag;      
	     
	       } catch (Exception e){      
	        e.printStackTrace();      
	       }      
	       return iMonth;      
	   }  
	
}
