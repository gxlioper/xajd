/**
 * @部门:学工产品事业部
 * @日期：2018-1-19 下午02:04:06 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.wmqs;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-1-19 下午02:04:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WmqsService  extends SuperServiceImpl<WmqsForm, WmqsDao>{
	private WmqsDao dao = new WmqsDao();

	public WmqsService() {
		super.setDao(dao);
	}
	
	/** 
	 * @描述:验证同一学年下班级是否重复(这里用一句话描述这个方法的作用)
	 * @作者：林国厦[工号：1553]
	 * @日期：2018-1-15 下午04:33:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistByWmqs(WmqsForm model, String type)
		throws Exception {
			if ("add".equalsIgnoreCase(type)) {
				String num = dao.checkExistForAddSave(model);
				return Integer.valueOf(num) > 0;
			} else {
				String num = dao.checkExistForUpdate(model);
				return Integer.valueOf(num) > 0;
			}
		
	}
	/** 
	 * @描述:获取校区(这里用一句话描述这个方法的作用)
	 * @作者：林国厦[工号：1553]
	 * @日期：2018-1-15 下午04:33:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXqmcList()throws Exception {
			
			return dao.getXqmcList();
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-1-22 上午10:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getLdSum(WmqsForm model) {
		// TODO 自动生成方法存根
		return dao.getLdSum(model);
	}

	/** 
	 * @描述:校区代码(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-22 下午02:27:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqmc
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXqdm(String xqmc) {
		
		return dao.getXqdm(xqmc);
	}
	
}
