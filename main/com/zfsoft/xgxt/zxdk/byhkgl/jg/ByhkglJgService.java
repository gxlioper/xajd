/**
 * @部门:学工产品事业部
 * @日期：2016-5-11 上午10:10:15 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款结果  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-11 上午10:10:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglJgService extends SuperServiceImpl<ByhkglJgForm, ByhkglJgDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @描述: 唯一性判断
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-12 下午01:28:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ByhkglJgForm form) {
		return dao.isHaveRecordForjg(form);		
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-12 下午01:28:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveByhkgljg(ByhkglJgForm model) throws Exception {
		
		boolean result = false;
		if ("save".equals(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 获取zqyymc
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-10 下午01:44:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		return dao.getZqyymc(xh);
	}
}
