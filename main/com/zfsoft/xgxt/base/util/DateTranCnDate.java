/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-3 ����10:42:27 
 */
package com.zfsoft.xgxt.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-3 ����10:42:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DateTranCnDate {
	public DateTranCnDate() {

	}

	/**
	 * @����:�ַ�������ת�������ĸ�ʽ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-3 ����10:43:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date yyyy-MM-dd
	 * @return yyyy��MM��dd��
	 * String ��������
	 */
	public static String dateToCnDate(String date) {
		String result = "";
		String[] cnDate = new String[] { "��", "һ", "��", "��", "��", "��", "��",
				"��", "��", "��" };
		String ten = "ʮ";
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
				result += "��";
				continue;
			}
			if (i == 1) {
				result += "��";
				continue;
			}
			if (i == 2) {
				result += "��";
				continue;
			}
		}
		return result;
	}
	/**
	 * @����:�ַ�������ת�������ĸ�ʽ���� ��ȡ�·�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-3 ����10:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date yyyy-MM-dd
	 * @return yyyy��MM��dd��
	 * String ��������
	 */
	public static String dateToCnDateSubMonth(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(0,oldDate.indexOf("��")+1);
	}
	
	/**
	 * 
	 * @����: ����ʯ�ʹ�ѧ-ֻ��ȡ��д�·�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-19 ����10:41:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String dateToCnDateSubOnlyMonth(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(5,oldDate.indexOf("��"));
	}
	
	/**
	 * 
	 * @����: ����ʯ�ʹ�ѧ-ֻ��ȡ��д����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-19 ����10:47:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String dateToCnDateSubOnlyDay(String date) {
		String oldDate = dateToCnDate(date);
		return oldDate.substring(oldDate.lastIndexOf("��")+1,oldDate.indexOf("��"));
	} 

	/**
	 * @����:�ַ�������ת�������ĸ�ʽ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-3 ����10:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date yyyy-MM-dd
	 * @return yyyy��MM��dd��
	 * String ��������
	 */
	public static String dateToCnDateSubYear(String date) {
		String result = "";
		String[] cnDate = new String[] { "��", "һ", "��", "��", "��", "��", "��",
				"��", "��", "��" };
		String ten = "ʮ";
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
	//��ʽ������
	public enum FomartDateType{year,month,day};
	/**
	 * 
	 * @����:��ʽ��ʱ�䣬���ݸ������ͷ��ع̶���ʽ
	 *       ����ȡ���� �� �գ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-22 ����10:46:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @param type
	 * @return
	 * String �������� 
	 */
	public static String fomartDate(String date,FomartDateType type){
		//Ĭ�ϵ���
		if(null==type){
			type=FomartDateType.day;
		}
		int i=-1;
		switch (type) {
		case year:
			i=date.indexOf("��");
			break;
		case month:
			i=date.indexOf("��");
			break;
		case day:
			i=date.indexOf("��");
			break;
		default:
			break;
		}
		return date=date.substring(0,i+1);
	}
	/**
	 * @����:ת���ַ�������Ϊ�����ĳ��á�����
	 * 		���磺20130809�� 2013-09-08  תΪ2013��8��9�� ��
	 * 		 (��ʱ֧��һλ���ָܷ�)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-21 ����04:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * String �������� 
	 */
	public static String fomartDateToCn(String data){
		return fomartDateToCn(data,FomartDateType.day);
	}
	/**
	 * @����:ת���ַ�������Ϊ�����ĳ��á�����
	 * 		���磺20130809�� 2013-09-08  תΪ2013��8��9�� ��
	 * 		 (��ʱ֧��һλ���ָܷ�)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-21 ����04:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * String �������� 
	 */
	public static String fomartDateToCn(String data ,FomartDateType type){
		if(null==data){
			return data;
		}
		String chData=data;
		if(data.length()==8){
			chData=fomartDateToCn(data, null,type);
		}else if(data.length()>8){//���ܳ��Ի�ȡ�ָ���
			//��ʱֻ֧��һλ�ָ������������������������������ַ�������������ΪΪ�ָ������֡�
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
	 * @����:ת���ַ�������Ϊ�����ĳ��á�����
	 * 		���磺20130809�� 2013-09-08  תΪ2013��8��9�� ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-21 ����04:33:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data �ַ�������
	 * @param splitStr ���ڷָ�����2013-09-08�ķָ���Ϊ "-"��
	 * @return 
	 * String �������� 
	 */
	public static String fomartDateToCn(String date,String splitStr){
		return fomartDateToCn(date,null,FomartDateType.day);
	}
	/**
	 * @����:ת���ַ�������Ϊ�����ĳ��á�����
	 * 		���磺20130809�� 2013-09-08  תΪ2013��8��9�� ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-21 ����04:33:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data �ַ�������
	 * @param splitStr ���ڷָ�����2013-09-08�ķָ���Ϊ "-"��
	 * @return 
	 * String �������� 
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
		
		date=year+"��"+month+"��"+day+"��";
		return fomartDate(date, type);
	}

	/**
	 * 
	 * @����:�Ƚ�ʱ���С��less�Ƿ��moreС��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����04:06:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param less С��ʱ��
	 * @param more ���ʱ��
	 * @return boolean
	 * @throws ParseException boolean ��������
	 */
	public static boolean compareDate(String less, String more) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
		Date first = sdf.parse(more);
		Date second = sdf.parse(more);
		return first.getTime() < second.getTime();
	}
	
	/**
	 * 
	 * @����: ȡ���ڼ�����ĩΪ1����Ϊ7���Σ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-22 ����08:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param date
	 * @return
	 * int �������� 
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
