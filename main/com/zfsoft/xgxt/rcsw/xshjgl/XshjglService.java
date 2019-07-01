/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午04:59:02 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.fyff.ffjg.FyffjgForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2015-9-14 上午09:35:12 
 * @版本： V5.17
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XshjglService extends SuperServiceImpl<XshjglForm, XshjglDao> {
	private XshjglDao rd = new XshjglDao();

	public XshjglService() {
		setDao(rd);
	}
	public HashMap<String, String> getOneInfo(XshjglForm t) throws Exception {
		
		return dao.getOneInfo(t);
	}
	
	
	public boolean isExistQysj(XshjglForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		
	}
	/**
	 * 导出功能
	 * @作者：孟威[工号：1186]
	 * @日期：2015-10-13 下午04:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
public List<HashMap<String, String>> getXshjgldcList(XshjglForm t, User user) throws Exception{
		
		return dao.getXshjgldcList(t, user);
	}
}
