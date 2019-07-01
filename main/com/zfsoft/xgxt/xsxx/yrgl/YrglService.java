/**
 * @部门:学工产品事业部
 * @日期：2015-12-31 上午09:36:52 
 */  
package com.zfsoft.xgxt.xsxx.yrgl;



import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
	/**
	 * @系统名称: 学生工作管理系统
	 * @模块名称: XXXX管理模块
	 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
	 * @作者： 孟威[工号:1186]
	 * @时间： 2016-1-5 上午09:46:24 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	public class YrglService extends SuperServiceImpl<YrglForm, YrglDao> {
		private YrglDao rd = new YrglDao();
	public YrglService() {
		setDao(rd);
	}
	//单个查询
	public HashMap<String, String> getOneInfo(YrglForm t) throws Exception {
		return dao.getOneInfo(t);
	}
	public boolean isExistQysj(YrglForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;	
	}
	/**
	 * @描述: 导出
	 * @作者：孟威[工号：1186]
	 * @日期：2016-1-5 上午10:00:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXshjgldcList(YrglForm t, User user) throws Exception{	
			return dao.getXshjgldcList(t, user);
		}
	public HashMap<String, String> getXn(String xh) throws Exception {
		return dao.getXn(xh);
	}

}
