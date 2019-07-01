/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午07:36:54 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;

import xgxt.comm.CommService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午07:36:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxCsSzService extends CommService{
	BysXxCsSzDao dao = new BysXxCsSzDao();
	
	public boolean csSz(BysXxCsSzForm model) throws Exception {
		boolean result=true;
		result=dao.csSzDel(model);
		result= dao.csSzAdd(model);
		return result;
	}
	
	/**
	 * 获取参数设置数据
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> getCssz() throws Exception{
		return dao.getCssz();
	}
	
	/**
	 * 获取审批流数据
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> splCx() throws Exception{
		return dao.splCx();
	}
	

}
