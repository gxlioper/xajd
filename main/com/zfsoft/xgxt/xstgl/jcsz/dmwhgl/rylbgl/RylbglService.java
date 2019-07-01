/**
 * @部门:学工产品事业部
 * @日期：2015-9-9 下午04:37:53 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-9-9 下午04:37:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RylbglService extends SuperServiceImpl<RylbglForm, RylbglDao> {
	//验证数据库中是否有同名的人员类别名称
	public boolean isExistsSameRylbmc(String rylbmc,String rylbdm){
		return dao.isExistsSameRylbmc(rylbmc, rylbdm);
	}
	
	//删除人员类别时判断在社团成员结果表和社团成员申请表中是否有用到该类别的数据
	public boolean isExistsRylbmc_user(String rylbdm){
		return dao.isExistsRylbmc_user(rylbdm);
	}
	
	//保存新增人员类别
	public boolean save(String rylbmc) throws Exception{
		return dao.save(rylbmc);
	}
	
	//获取人员类别名称
	public String getRylbmc(String rylbdm){
		return dao.getRylbmc(rylbdm);
	}
}
