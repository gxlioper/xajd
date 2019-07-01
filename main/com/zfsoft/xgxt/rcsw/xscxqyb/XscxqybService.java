/**
 * @部门:学工产品事业部
 * @日期：2016-3-17 上午11:04:45 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-3-25 上午10:19:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
	public class XscxqybService extends SuperServiceImpl<XscxqybForm,XscxqybDao>{
		private XscxqybDao rd = new XscxqybDao();
		public XscxqybService() {
			setDao(rd);
	}
/**
 * @描述:TODO(获得当前学期名称)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:18:48
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param model
 * @return
 * @throws Exception
 * String 返回类型 
 * @throws
 */
	public String getCurrentXqmc(XscxqybForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
/**
 * @描述:TODO(同一学年学期，相同的操作人是否存在同样月份的记录)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:20:27
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param form
 * @return
 * @throws Exception
 * boolean 返回类型 
 * @throws
 */
	public boolean isExistYf(XscxqybForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;	
	}
/**
 * @描述:TODO(查看页面所需SQL)
 * @作者：孟威[工号：1186]
 * @日期：2016-3-25 上午10:24:50
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param jgid
 * @return
 * HashMap<String,String> 返回类型 
 * @throws
 */
	public HashMap<String,String> getXxck (String jgid){
		return dao.getXxck(jgid);
	}
/**
 * 
 * @描述:导出
 * @作者：孟威[工号：982]
 * @日期：2016-3-24 下午02:05:25
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> 返回类型 
 * @throws
 */
	public List<HashMap<String, String>> getXscxqybdcList(XscxqybForm t, User user) throws Exception{
		return dao.getXscxqybdcList(t, user);
	}
}
