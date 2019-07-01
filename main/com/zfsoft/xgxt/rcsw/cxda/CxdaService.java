/**
 * @部门:学工产品事业部
 * @日期：2015-6-17 上午10:07:13 
 */  
package com.zfsoft.xgxt.rcsw.cxda;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.rzkh.rzkhjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-6-17 上午10:07:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxdaService extends SuperServiceImpl<CxdaForm, CxdaDao> {
	//判断在本学年本学期在结果表中是否有诚信档案记录
	public boolean checkExistForSave(CxdaForm model){
		return dao.checkExistForSave(model);
	}
	
	//获取学期对照表中的学期
	public HashMap<String, String> getxqdz(String xqdm){
		return dao.getxqdz(xqdm);
	}
}

