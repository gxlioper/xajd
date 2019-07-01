/**
 * @部门:学工产品事业部
 * @日期：2015-7-21 上午11:49:08 
 */  
package com.zfsoft.xgxt.xsztz.xwtzxmjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-21 上午11:49:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XwTzXmJgService extends SuperServiceImpl<XwTzXmJgForm, XwTzXmJgDao> {
	public HashMap<String, String> getHdMap(XwTzXmJgForm model){
		return dao.getHdMap(model);
	}
	
	public List<HashMap<String, String>> getSsKmList(){
		return dao.getSsKmList();
	}
	
	public List<HashMap<String, String>> getXmJbList(){
		return dao.getXmJbList();
	}
	
	public boolean checkExistForSave(XwTzXmJgForm model) {
		return dao.checkExistForSave(model);
	}
}
