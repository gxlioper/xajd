/**
 * @部门:学工产品事业部
 * @日期：2015-3-12 下午04:16:43 
 */  
package xgxt.base;

import xgxt.utils.date.DateUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 日历打印
 * @作者： 张昌路[工号:982]
 * @时间： 2015-3-12 下午04:16:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TestDemo {
	
	public int getMDay(int y, int m) {

	    int[] mday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	    if ((y % 40 == 0 && y % 100 != 0) || y % 400 == 0)//判断是否是闰月

	        mday[1] = 29;

	    return mday[m - 1];

	}
	
	public void setCalendar(int year, int month) { 

		  

	    int daynumber = getMDay(year, month);
	    
	    String strMonth = String.valueOf(month);
	    if(month < 10){
	    	strMonth = "0" + strMonth;
	    }
	    int firstnumber = DateUtils.getDayOfWeek_num(year + strMonth + "01");

	    int lastnumber = DateUtils.getDayOfWeek_num(year + strMonth + daynumber);
	    
	    int weeknumber = (daynumber - (7 - firstnumber) - (lastnumber + 1)) / 7; 

	    int day = 1; 

	    System.out.print("星期天\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六\n"); 

	    for (int i = 1; i < firstnumber; i++)//第一个星期 

	    { 

	    	System.out.print("\t"); 

	    } 

	    for (int i = firstnumber; i <= 7; i++) { 

	    	System.out.print(day+"\t");

	        day++; 

	    } 
	    System.out.print("\n");
	    int number = 0;
	    for (int i = 0; i < weeknumber; i++)//其他星期 

	    { 
	    	int othWeek = daynumber - (7 - firstnumber) - (weeknumber - 1) * 7;
	        for (int k = othWeek; k < othWeek + 7; k++) { 

	        	System.out.print(day+"\t");
		        day++; 

	        } 

	        System.out.print("\n");
	        number++;

	    }
	    for (int i = 1; i < lastnumber + 1; i++)

	    { 

        	System.out.print(day+"\t");

	        day++; 
	    } 

	    System.out.print("\n"); 
	}
	
	public static void main(String[] args) {

		TestDemo aa = new TestDemo();
		aa.setCalendar(2016, 7);
	}
}
