/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午06:12:27 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.ArrayList;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行评语管理) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午06:12:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxdjService extends SuperServiceImpl<CxdjForm, CxdjDao> {
private CxdjDao dao = new CxdjDao();
	
	public CxdjService() {
		super.setDao(dao);
	}

	/** 
	 * @描述:TODO(操行等级代码是否存在)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-30 下午06:47:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(CxdjForm model) {
		// TODO 自动生成方法存根
		return dao.isExist(model);
	}
	
	//浙江旅游个性化判断
	public boolean isNowUsing(String[] pj){
		return dao.isNowUsing(pj);
	}
	
	public boolean checkSameNameIsNotExists(String cxdjdm,String cxdjmc){
		return dao.checkSameNameIsNotExists(cxdjdm, cxdjmc);
	}
}
