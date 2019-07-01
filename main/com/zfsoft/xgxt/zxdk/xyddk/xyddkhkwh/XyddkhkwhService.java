/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午08:59:54 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.xyddkhkwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-校园地（国家）贷款-还款维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-22 上午08:59:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkhkwhService extends SuperServiceImpl<XyddkhkwhForm,XyddkhkwhDao>{
	/**
	 * @描述: 获取当前学期
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-22 上午09:34:57
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
	 * @描述: 唯一性判断
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-22 上午09:35:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
		public boolean isHaveRecord(XyddkhkwhForm form){
			return dao.isHaveRecord(form);
		}
	}