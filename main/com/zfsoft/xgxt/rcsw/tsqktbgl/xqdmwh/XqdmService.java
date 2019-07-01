/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 下午02:54:21 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.xqdmwh;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:982]
 * @时间： 2016-3-15 下午02:54:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqdmService extends SuperServiceImpl<XqdmForm, XqdmDao>{
	private XqdmDao dao = new XqdmDao();
	
	/** 
	 * @描述:新增_查询学情代码是否存在
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-15 下午02:26:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean checkExistForAdd(XqdmForm form){
		return dao.checkExistForAdd(form);
	}
	
	/** 
	 * @描述:修改_查询学情代码是否存在
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-15 下午02:31:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean checkExistForUpdate(XqdmForm form,String oldxqdm) {
		return dao.checkExistForUpdate(form, oldxqdm);
	}
	
	/** 
	 * @描述:判断是否在申请和结果中存在
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-15 下午02:52:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkExistForsqjg(XqdmForm form){		
		return dao.checkExistForsqjg(form);
	} 
	
	/** 
	 * @描述:获取学情代码最大值
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-15 下午05:12:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * String 返回类型 
	 * @throws 
	 */
	public String getMaxXqdm() throws SQLException{
		return String.valueOf(dao.getMaxXqdm());
	}
	
	public boolean update(XqdmForm form,String oldxqdm) throws Exception{
		return dao.update(form,oldxqdm);
	}
}
