/**
 * @部门:学工产品事业部
 * @日期：2016-8-2 下午04:09:55 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 基本分管理
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-2 下午04:09:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JbfglService extends SuperServiceImpl <JbfglForm,JbfglDao> {
	
	private JbfglDao rd = new JbfglDao();

	public JbfglService() {
		setDao(rd);
	}

	
public boolean isExistQysj(JbfglForm myForm) 
	throws Exception {
			String num = dao.checkExistForSave(myForm);
			return Integer.valueOf(num) > 0;
		
	}


	
}
