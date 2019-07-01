/**
 * @部门:学工产品事业部
 * @日期：2013-10-25 上午09:14:32 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分原因代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-25 上午09:07:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfyydmService extends SuperServiceImpl<CfyydmForm, CfyydmDao> {
	
	private CfyydmDao dao=new CfyydmDao();
	public CfyydmService(){
		this.setDao(dao);
	}
	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (处分原因名称是否存在) 
	 * @作者： 陈敏杰[工号:913]
	 * @时间： 2013-10-24 上午10:52:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public boolean checkIsExist(CfyydmForm myForm) {
		// TODO 自动生成方法存根
		return dao.checkIsExist(myForm);
	}

}
