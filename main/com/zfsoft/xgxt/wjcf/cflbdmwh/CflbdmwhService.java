/**
 * @部门:学工产品事业部
 * @日期：2013-10-24 上午10:57:50 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分类别代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-24 上午10:52:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CflbdmwhService extends SuperServiceImpl<CflbdmwhForm, CflbdmwhDao> {
	
	private CflbdmwhDao dao=new CflbdmwhDao();
	
	public CflbdmwhService(){
		this.setDao(dao);
	}

	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (处分类别名称是否存在) 
	 * @作者： 陈敏杰[工号:913]
	 * @时间： 2013-10-24 上午10:52:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public boolean checkIsExist(CflbdmwhForm myForm) {
		// TODO 自动生成方法存根
		return dao.checkIsExist(myForm);
	}

	/** 
	 * @throws ParseException 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (该处分类别是否可终止)  
	 * @作者： 王洪锦[工号:1004]
	 * @时间： 2013-11-24 上午4:15:22 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
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
			//前台已经处理过了，cfqx（缺位的用0补了）
			String year = cfqx.split("-")[0];
			String month = cfqx.split("-")[1];
			String day = cfqx.split("-")[2];
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   //处分开始日期
			Date cfksDate = sdf.parse(cfsj);
		    Calendar   cfsjCalendar   =   new   GregorianCalendar(); 
		    cfsjCalendar.setTime(cfksDate);
		 
		    int years =Integer.parseInt(year);
		    int months = Integer.parseInt(month);
		    cfsjCalendar.add(cfsjCalendar.YEAR, years);
		    cfsjCalendar.add(cfsjCalendar.MONTH, months);
		    int days = Integer.parseInt(day);
		    cfsjCalendar.add(cfsjCalendar.DATE, days);
		    //处分截止日期
		    Date cfjzDate=cfsjCalendar.getTime();
		    
		    Calendar   sysCalendar   =   new   GregorianCalendar(); 
		    sysCalendar.setTime(sdf.parse(DateUtils.getCurrDate()));
		    //当前系统日期
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
						year = year+"年";
					}
					if("0".equals(month)){
						month = "";
					}else{
						month = month+"月";
					}
					if("0".equals(day)){
						day = "";
					}else{
						day = day+"天";
					}
					result = "处分期限："+year + month + day;
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
						year = year+"年";
					}
					if("".equals(month) || "0".equals(month)){
						month = "";
					}else{
						month = month+"月";
					}
					if("".equals(day) || "0".equals(day)){
						day = "";
					}else{
						day = day+"天";
					}
					result = "处分期限："+year + month + day;
				}
			}
		}
		return result;
	}
	
	/**
	 * @描述:根据处分类别代码获取处分发文权限
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月15日 下午2:18:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cflbdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFwqxByLbdm(String cflbdm){
		return dao.getFwqxByLbdm(cflbdm);
	}
}
