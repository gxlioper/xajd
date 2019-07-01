/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-12 ����04:16:43 
 */  
package xgxt.base;

import xgxt.utils.date.DateUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������ӡ
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-3-12 ����04:16:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TestDemo {
	
	public int getMDay(int y, int m) {

	    int[] mday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	    if ((y % 40 == 0 && y % 100 != 0) || y % 400 == 0)//�ж��Ƿ�������

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

	    System.out.print("������\t����һ\t���ڶ�\t������\t������\t������\t������\n"); 

	    for (int i = 1; i < firstnumber; i++)//��һ������ 

	    { 

	    	System.out.print("\t"); 

	    } 

	    for (int i = firstnumber; i <= 7; i++) { 

	    	System.out.print(day+"\t");

	        day++; 

	    } 
	    System.out.print("\n");
	    int number = 0;
	    for (int i = 0; i < weeknumber; i++)//�������� 

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
