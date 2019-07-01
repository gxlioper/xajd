package com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办模块
 * @类功能描述: TODO(学生证补办基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:55:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbJcszDao extends SuperDAOImpl<XszbbJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XszbbJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XszbbJcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_xszbb_jcszb");
		super.setClass(XszbbJcszForm.class);
	}

	/**
	 * 
	 * @描述:TODO(查询基础设置信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:21:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * XszbbJcszForm 返回类型 
	 * @throws
	 */
	public XszbbJcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_xszbb_jcszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:TODO(删除参数设置信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:21:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(XszbbJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_xszbb_jcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
