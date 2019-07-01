/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 下午04:44:23 
 */  
package com.zfsoft.xgxt.jjgl.xsxqsq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-28 下午04:44:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxqsqDao extends SuperDAOImpl<XsxqsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxqsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxqsqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:更具需求id和学号获取Model
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午08:05:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * XsxqsqForm 返回类型 
	 * @throws
	 */
	public XsxqsqForm getModelByXqidAndXh(String xqid , String xh) throws Exception{
		String sql = "select * from XSGGFW_JJFW_XSJJXQSQB a where a.xqid = ? and a.xh = ? and a.zt = '1'";
		Map<String , String> map = dao.getMapNotOut(sql, new String[]{xqid , xh});
		XsxqsqForm model = new XsxqsqForm();
		BeanUtils.populate(model, map);
		
		return model;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XsxqsqForm.class);
		super.setKey("sqid");
		super.setTableName("XSGGFW_JJFW_XSJJXQSQB");
	}

}
