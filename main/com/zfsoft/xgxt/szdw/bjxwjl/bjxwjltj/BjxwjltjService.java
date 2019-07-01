/**
 * @部门:学工产品事业部
 * @日期：2014-5-19 下午01:54:07 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-19 下午01:54:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxwjltjService extends SuperServiceImpl<BjxwjltjForm, BjxwjltjDao> {

	
	public BjxwjltjService(){
		setDao(new BjxwjltjDao());
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-19 下午04:31:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getbjxx(String bjdm) {
		
		return dao.getbjxx(bjdm);
	}
	
	/**
	 * 
	 * @描述:获取班级行为信息列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-19 下午04:49:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param type
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwxx(String bjdm , String type) {
		
		return dao.getXwxx(bjdm , type);
	}
	
}
