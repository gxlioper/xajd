/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:39:19 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.cxdj.CxdjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-心理问题类型
 * @类功能描述: 
 * @作者： 王志刚[工号:982]
 * @时间： 2014-4-23 下午03:39:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwtlxwhDao extends SuperDAOImpl<XlwtlxwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLJK_XLWJYJ_XLWTLX");
		setKey("lxdm");
		setClass(XlwtlxwhForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwtlxwhForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc ")
		   .append("from XG_XLJK_XLWJYJ_XLWTLX t ")
		   .append("where 1=1 order by t.lxdm");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwtlxwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/** 
	 * @描述:(心理问题类型代码是否存在)
	 * @作者：王志刚 [工号：1060]
	 * @日期：2014-4-24 下午06:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 */
	public boolean xlwtlxIsExist(XlwtlxwhForm model) {
		String sql="select count(t.lxdm) num from XG_XLJK_XLWJYJ_XLWTLX t where t.lxdm=?";
		String num = dao.getOneRs(sql.toString(), new String[]{model.getLxdm()}, "num");
		return !num.equals("0");
	}


}
