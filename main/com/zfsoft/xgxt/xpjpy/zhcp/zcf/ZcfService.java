/**
 * @部门:学工产品事业部
 * @日期：2016-4-11 下午03:31:57 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcf;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-11 下午03:31:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfService extends SuperServiceImpl<ZcfForm, ZcfDao> {
	public HashMap<String, String> getZcfInfo(String xn,String xh){
		return dao.getZcfInfo(xn, xh);
	}
	
	public HashMap<String, String> getJkfInfo(String xn,String xh){
		return dao.getJkfInfo(xn, xh);
	}
	
	public List<HashMap<String, String>> getZcfList(String xn,String xh){
		return dao.getZcfList(xn, xh);
	}
}
