/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:03:02 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生保险报销 
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-26 下午02:03:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsbxbxService extends SuperServiceImpl<XsbxbxForm, XsbxbxDao> {
	
	public XsbxbxService(){
		setDao(new XsbxbxDao());
	}
	
	//查看每条下拉框的名称信息
	public HashMap<String,String> getXsbxbx(String bxid) {
		
		return dao.getXsbxbx(bxid); 
	}
	
	//保险证明打印
	public HashMap<String, String> bxbxZm(String bxid) throws Exception{
		
		return dao.bxbxZm(bxid);
	}
}
