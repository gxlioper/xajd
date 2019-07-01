/**
 * @部门:学工产品事业部
 * @日期：2016-7-6 上午11:17:20 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 代码维护-奖项类别  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-6 上午11:17:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxlbService extends SuperServiceImpl<DmwhJxlbForm, DmwhJxlbDao> implements Constants {
	
	DmwhJxlbDao dao = new DmwhJxlbDao();
	
	public DmwhJxlbService() {
		this.setDao(dao);
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午02:05:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(DmwhJxlbForm model) throws Exception {
		model.setJxlbdm(dao.getNextId());	
		return this.runInsert(model);
	}
	
	/**
	 * 
	 * @描述: 重复验证 
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午03:13:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExist(DmwhJxlbForm model, String type) {	
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {		
			String num=dao.checkExistForSave(model);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		} else if("update".equalsIgnoreCase(type)) {
			String num=dao.checkExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}		
		}
		
		return  flag;	
	}
	
	/**
	 * 
	 * @描述: 申请判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午05:28:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkSq(String value) throws Exception {
		String resultLxmc="";
    	String[] lxmc=dao.checkSq(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * 
	 * @描述: 结果判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 下午05:28:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkJg(String value)throws Exception{
		String resultLxmc="";
		String [] lxmc=dao.checkJg(value);
		for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * 
	 * @描述:奖项等级判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-19 上午09:06:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkJxdj(String value)throws Exception{
		String resultLxmc="";
		String [] lxmc=dao.checkJxdj(value);
		for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * 
	 * @描述:奖项名次判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-19 上午09:06:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkJxmc(String value)throws Exception{
		String resultLxmc="";
		String [] lxmc=dao.checkJxmc(value);
		for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
}
