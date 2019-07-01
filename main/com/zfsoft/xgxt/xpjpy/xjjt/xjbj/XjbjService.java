/**
 * @部门:学工产品事业部
 * @日期：2018-1-15 上午09:40:15 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.xjbj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-1-15 上午09:40:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XjbjService  extends SuperServiceImpl<XjbjForm, XjbjDao> {

	private XjbjDao dao = new XjbjDao();

	public XjbjService() {
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
	public boolean isExistByXjbj(XjbjForm model, String type)
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjdm(String bjmc) {
		
		return dao.getBjdm(bjmc);
	}
	public String getBjmc(String bjdm) {
		
		return dao.getBjmc(bjdm);
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm) {
		
		return dao.getBjxx(bjdm);
	}
}
