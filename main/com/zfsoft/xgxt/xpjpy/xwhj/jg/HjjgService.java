/**
 * @部门:学工产品事业部
 * @日期：2016-7-27 下午01:19:59 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖结果  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-27 下午01:19:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjgService extends SuperServiceImpl<HjjgForm, HjjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @描述: 重复验证（增加）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午11:13:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjg(HjjgForm form) {
		return dao.isHaveRecordForjg(form);	
	}
	
	/**
	 * 
	 * @描述: 重复验证（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午11:14:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(HjjgForm form) {
		return dao.isHaveRecordForjgU(form);	
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-28 上午11:14:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveHjjg(HjjgForm model) throws Exception {
		
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
