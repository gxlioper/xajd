/**
 * @部门:学工产品事业部
 * @日期：2016-3-3 下午03:47:11 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-3 下午03:47:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsqjcxService extends SuperServiceImpl<XsqjcxForm, XsqjcxDao> {
	XsqjcxDao dao = new XsqjcxDao();
	public HashMap<String, String> Qjsqck(XsqjcxForm para){
		return dao.Qjsqck(para);
	}
	/** 
	 * @描述:审批历史
	 * @作者：CP[工号：982]
	 * @日期：2017-4-11 上午09:55:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplsList(XsqjcxForm model) {
		// TODO 自动生成方法存根
		return dao.getSplsList(model);
	}
}
