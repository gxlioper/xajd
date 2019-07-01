/**
 * @部门:学工产品事业部
 * @日期：2015-12-21 下午01:58:37 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-专业奖管理-代码维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2015-12-21 下午01:58:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */


public class ZyjrddjwhService extends SuperServiceImpl<ZyjrddjwhForm, ZyjrddjwhDao>{
	private ZyjrddjwhDao dao = new ZyjrddjwhDao();

	public ZyjrddjwhService() {
		this.setDao(dao);
	}
	
	public boolean checkDjdmExist(String[] ids) {
		return dao.checkDjdmExist(ids);
	}
	
	
	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: XXXX管理模块
	 * @类功能描述: 判断等级名称是否存在 
	 * @作者： 柳俊[工号:1282]
	 * @时间： 2015-12-21 下午02:04:34 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public boolean checkIsExist(ZyjrddjwhForm myForm) {
		// TODO 自动生成方法存根
		return dao.checkIsExist(myForm);
	} 
	
	
	
}
