/**
 * @部门:学工产品事业部
 * @日期：2013-5-9 上午08:44:51 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO 商业保险管理
 * @作者： honglin 
 * @时间： 2013-5-8 下午05:22:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SybxService extends SuperServiceImpl<SybxForm, SybxDao>{
	private SybxDao dao = new SybxDao();
	
	public SybxService(){
		super.setDao(dao);
	}
	

	/**
	 * 
	 * @描述:唯一性判断（学号，学年）
	 * @作者：HongLin
	 * @日期：2013-5-9 下午02:09:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	 public boolean isExistBySybz(SybxForm model, String type)throws Exception{
		    boolean flag = false;
			if("save".equalsIgnoreCase(type)){
				String num=dao.checkExistForSave(model);
				flag = !"0".equalsIgnoreCase(num);
			}else if("update".equalsIgnoreCase(type)){
				String num=dao.checkExistForUpdate(model);
				flag = !"0".equalsIgnoreCase(num);	
			}
	    	return  flag;
	}
	
	 /**
	  * @throws Exception 
	  * 
	  * @描述:获得单个学生商业保险信息
	  * @作者：honglin
	  * @日期：2013-5-9 下午02:09:02
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xh
	  * @param request
	  * @return
	  * List<String[]> 返回类型 
	  * @throws
	  */
	public HashMap<String, String> getOneSybxList(String  guid) throws Exception {
		 
		return dao.getOneSybxList(guid);
	}
	

	/**
	 * @描述:获取增加原因
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllZjyyList() {
		return dao.getAllZjyyList();
	}
	
	/**
	 * @描述:获取参保人员类别
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllCbrylbList() {
		return dao.getAllCbrylbList();
	}
	
	/**
	 * @描述:获取缴费人员类别
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllJfrylbList() {
		return dao.getAllJfrylbList();
	}
	 
}
