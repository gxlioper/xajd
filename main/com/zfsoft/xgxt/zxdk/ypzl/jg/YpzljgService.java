/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 上午11:41:41 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-24 上午11:41:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzljgService extends SuperServiceImpl<YpzljgForm, YpzljgDao>{
	private YpzljgDao dao = new YpzljgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/**
	 * 
	 * @描述:判断是否存在
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 上午10:17:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjg(YpzljgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-25 上午10:20:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveYpzljg(YpzljgForm model) throws Exception {
		boolean result = false;
		if ("save".equals(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
}
