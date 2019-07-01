/**
 * @部门:学工产品事业部
 * @日期：2018-5-16 下午04:32:43 
 */  
package com.zfsoft.xgxt.zxdk.xsdkqf;

import xgxt.form.User;
import xgxt.xsxx.comm.sjy.jcsjcsh.SjyJcsjcshForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2018-5-16 下午04:32:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsdkqfService extends SuperServiceImpl<XsdkqfForm,XsdkqfDao>{
	XsdkqfDao dao = new XsdkqfDao();
	
	public boolean allSubmit(SjyJcsjcshForm model, User user) throws Exception {
		
		boolean flag = false;
		flag = dao.Fsznx();
		
		return flag;
	}
	public boolean Fsznx() throws Exception {

		return dao.Fsznx();
	}
}
