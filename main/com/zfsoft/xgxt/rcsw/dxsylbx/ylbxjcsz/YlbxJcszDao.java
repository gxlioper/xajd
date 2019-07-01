package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 大学生医疗保险参数设置信息模块
 * @类功能描述: TODO(学生证补办基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:55:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxJcszDao extends SuperDAOImpl<YlbxJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxJcszForm t, User user)
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
		super.setTableName("xg_rcsw_ylbx_jcszb");
		super.setClass(YlbxJcszForm.class);
	}

	/**
	 * 
	 * @描述:TODO(查询基础设置信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 上午10:15:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * YlbxJcszForm 返回类型 
	 * @throws
	 */
	public YlbxJcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_ylbx_jcszb a ");
		
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:TODO(删除参数设置信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 上午10:16:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(YlbxJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_ylbx_jcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
