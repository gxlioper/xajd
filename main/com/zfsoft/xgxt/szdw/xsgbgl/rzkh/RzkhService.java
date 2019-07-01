/**
 * @部门:学工产品事业部
 * @日期：2015-6-10 下午05:29:14 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-6-10 下午05:29:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RzkhService extends SuperServiceImpl<rzkhjgForm, RzkhDao> {
	
	//判断当前学生干部在本学年本学期在结果表中是否有考核记录
	public boolean checkExistForSave(rzkhjgForm model){
		return dao.checkExistForSave(model);
	}
	
	//获取职位名称
	public HashMap<String, String> getZwmc(String zwdm){
		return dao.getZwmc(zwdm);
	}
	
	public HashMap<String, String> getKhjgxxMap(rzkhjgForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getKhjgxxMap(model, user);
	
		return xsxxmap;
	}
	
	public List<HashMap<String, String>> getKhjgxxList(rzkhjgForm model ) throws Exception{
		return dao.getKhjgxxList(model);
	}
	
	//获取学期名称
	public HashMap<String, String> getxqdz(String xqdm){
		return dao.getxqdz(xqdm);
	}
}
