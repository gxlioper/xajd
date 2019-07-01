/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 上午10:20:55 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 考勤类型代码维护
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 上午10:20:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqlxService extends SuperServiceImpl<KqlxForm, KqlxDao> {

	private KqlxDao dao = new KqlxDao();
	
	public KqlxService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述:获取下一级考勤类型代码
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 上午10:24:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getNextKqlxdm() throws SQLException{
		
		int maxKqlxdm=0;
		maxKqlxdm = dao.getMaxKqlxdm()+1;
		return maxKqlxdm;
	}
	
	
	 /**
	  * 
	  * @描述:查询考勤登记表中是否存在
	  * @作者：陶钢军[工号：1075]
	  * @日期：2014-6-6 上午10:26:59
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param value
	  * @return
	  * @throws Exception
	  * String 返回类型 
	  * @throws
	  */
	 public String checkKqlxdmForKqdj(String value) throws Exception{
		 
		 String resKqlxmc = "";
		 String[] kqlxmc = dao.kqlxdmCheckExistForKqdj(value);
		 for(int i=0; i<kqlxmc.length; i++){
			 if(i==kqlxmc.length-1){
				 resKqlxmc+=kqlxmc[i];
			 }else{
				 resKqlxmc+=kqlxmc[i];
			 }
		 }
		 return resKqlxmc;
	 }
	 
	 
	 /**
	  * 
	  * @描述:查询考勤登记表中是否存在
	  * @作者：陶钢军[工号：1075]
	  * @日期：2014-6-6 上午10:30:25
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param form
	  * @return
	  * boolean 返回类型 
	  * @throws
	  */
	 public boolean isExistByKqlxmc(KqlxForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.kqlxmcCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	 
	 /**
	  * 
	  * @描述:获取考勤类型列表
	  * @作者：陶钢军[工号：1075]
	  * @日期：2014-6-6 上午10:32:23
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> 返回类型 
	  * @throws
	  */
	 public List<HashMap<String, String>> getKqlxList() throws Exception {
		return dao.getKqlxList();
	 }	
}
