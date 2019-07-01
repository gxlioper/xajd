/**
 * @部门:学工产品事业部
 * @日期：2016-5-3 上午11:07:57 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.ypzldxjhk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 永平自立贷学金还款信息维护
 * @类功能描述: 永平自立贷学金还款信息维护 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-3 上午11:07:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzldxjhkService extends SuperServiceImpl<YpzldxjhkForm, YpzldxjhkDao>{
	
	/**
	 * 
	 * @描述: 获取当前学期
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-4 上午08:30:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/**
	 * 
	 * @描述: 唯一性判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-4 上午08:59:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecord(YpzldxjhkForm form){
		return dao.isHaveRecord(form);
	}

}
