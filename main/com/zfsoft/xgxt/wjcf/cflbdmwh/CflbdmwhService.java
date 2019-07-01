/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-24 ����10:57:50 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����������ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-24 ����10:52:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CflbdmwhService extends SuperServiceImpl<CflbdmwhForm, CflbdmwhDao> {
	
	private CflbdmwhDao dao=new CflbdmwhDao();
	
	public CflbdmwhService(){
		this.setDao(dao);
	}

	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (������������Ƿ����) 
	 * @���ߣ� ������[����:913]
	 * @ʱ�䣺 2013-10-24 ����10:52:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean checkIsExist(CflbdmwhForm myForm) {
		// TODO �Զ����ɷ������
		return dao.checkIsExist(myForm);
	}

	/** 
	 * @throws ParseException 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (�ô�������Ƿ����ֹ)  
	 * @���ߣ� �����[����:1004]
	 * @ʱ�䣺 2013-11-24 ����4:15:22 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean zzwjcfFlag(String cflbmc,String cfsj) throws ParseException {
		boolean flag = false;
		HashMap<String, String>  cflbInfo = dao.cflbInfoByMc(cflbmc);
		if(cflbInfo!=null && cflbInfo.size()>0){
			if("1".equals(cflbInfo.get("sfszcfqx")) && "1".equals(cflbInfo.get("qxnsfkzz"))){
				flag = true;
			}
			if("1".equals(cflbInfo.get("sfszcfqx")) && "0".equals(cflbInfo.get("qxnsfkzz"))){
				String cfqx = cflbInfo.get("cfqx");
				flag = outCfqxflag(cfqx,cfsj);
			}
		}
		return flag;
	}
	
	public boolean  outCfqxflag(String cfqx,String cfsj) throws ParseException{
			//ǰ̨�Ѿ�������ˣ�cfqx��ȱλ����0���ˣ�
			String year = cfqx.split("-")[0];
			String month = cfqx.split("-")[1];
			String day = cfqx.split("-")[2];
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   //���ֿ�ʼ����
			Date cfksDate = sdf.parse(cfsj);
		    Calendar   cfsjCalendar   =   new   GregorianCalendar(); 
		    cfsjCalendar.setTime(cfksDate);
		 
		    int years =Integer.parseInt(year);
		    int months = Integer.parseInt(month);
		    cfsjCalendar.add(cfsjCalendar.YEAR, years);
		    cfsjCalendar.add(cfsjCalendar.MONTH, months);
		    int days = Integer.parseInt(day);
		    cfsjCalendar.add(cfsjCalendar.DATE, days);
		    //���ֽ�ֹ����
		    Date cfjzDate=cfsjCalendar.getTime();
		    
		    Calendar   sysCalendar   =   new   GregorianCalendar(); 
		    sysCalendar.setTime(sdf.parse(DateUtils.getCurrDate()));
		    //��ǰϵͳ����
		    Date sysDate = sysCalendar.getTime();
		    
		   if(sysDate.getTime()<cfksDate.getTime() || sysDate.getTime()>cfjzDate.getTime()){
			   return true;
		   }
			return false;
	}
	
	
	public String getCfqx(String cflbdm){
		String result= "";
		HashMap<String, String>  cflbInfo = dao.cflbInfoById(cflbdm);
		if(cflbInfo!=null && cflbInfo.size()>0){
			if("1".equals(cflbInfo.get("sfszcfqx"))){
				String cfqx = cflbInfo.get("cfqx");
				if(StringUtils.isEmpty(cfqx)){
					result ="";
				}else{
					String year = cfqx.split("-")[0];
					String month = cfqx.split("-")[1];
					String day = cfqx.split("-")[2];
					if("0".equals(year)){
						year = "";
					}else{
						year = year+"��";
					}
					if("0".equals(month)){
						month = "";
					}else{
						month = month+"��";
					}
					if("0".equals(day)){
						day = "";
					}else{
						day = day+"��";
					}
					result = "�������ޣ�"+year + month + day;
				}
			}
		}
		return result;
	}
	
	public String getCfqxByMc(String cflbmc){
		String result= "";
		HashMap<String, String>  cflbInfo = dao.cflbInfoByMc(cflbmc);
		if(cflbInfo!=null && cflbInfo.size()>0){
			if("1".equals(cflbInfo.get("sfszcfqx"))){
				String cfqx = cflbInfo.get("cfqx");
				if(StringUtils.isEmpty(cfqx)){
					result ="";
				}else{
					String year = cfqx.split("-")[0];
					String month = cfqx.split("-")[1];
					String day = cfqx.split("-")[2];
					if("".equals(year) || "0".equals(year)){
						year = "";
					}else{
						year = year+"��";
					}
					if("".equals(month) || "0".equals(month)){
						month = "";
					}else{
						month = month+"��";
					}
					if("".equals(day) || "0".equals(day)){
						day = "";
					}else{
						day = day+"��";
					}
					result = "�������ޣ�"+year + month + day;
				}
			}
		}
		return result;
	}
	
	/**
	 * @����:���ݴ����������ȡ���ַ���Ȩ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��15�� ����2:18:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cflbdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFwqxByLbdm(String cflbdm){
		return dao.getFwqxByLbdm(cflbdm);
	}
}
