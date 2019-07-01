/**
 * @部门:学工产品事业部
 * @日期：2016-7-20 下午04:45:47 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 代码维护-奖项名次
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-20 下午04:45:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxmcService extends SuperServiceImpl<DmwhJxmcForm, DmwhJxmcDao> implements Constants {
	
	DmwhJxmcDao dao = new DmwhJxmcDao();
	
	public DmwhJxmcService() {
		this.setDao(dao);
	}
	
	/**
	 * 
	 * @描述: 奖项等级LIST
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-21 上午10:14:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxdjList(String jxlbdm, String jsfs){
		return dao.getJxdjList(jxlbdm, jsfs);
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
	public boolean save(DmwhJxmcForm model) throws Exception {
		model.setJxmcdm(dao.getNextId());
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
	public boolean isExist(DmwhJxmcForm model, String type) {	
		
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
}
