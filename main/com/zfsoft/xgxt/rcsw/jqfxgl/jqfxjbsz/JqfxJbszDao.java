/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午03:41:05 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：lgx[工号:1553]
 * @时间： 2018-4-20 下午03:41:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JqfxJbszDao  extends SuperDAOImpl<JqfxJbszForm> {
		
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxJbszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxJbszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_RCSW_JQFXGL_JBSZB");
		super.setClass(JqfxJbszForm.class);
	}

	/**
	 * 
	 * @描述:TODO(查询假期返校基础设置)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:41:05 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * JqfxJbszForm 返回类型 
	 * @throws
	 */
	public JqfxJbszForm getModel() throws Exception {

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*, case when fxkg=1 and sysdate between to_date(nvl(fxtxkssj,'1990-01-01 00:00'),'yyyy-MM-dd hh24:mi') ");
		sql
				.append("and to_date(nvl(fxtxzzsj,'2030-01-01 23:59'),'yyyy-MM-dd hh24:mi') then 'true' else 'false' end isopen from XG_RCSW_JQFXGL_JBSZB a ");

		return super.getModel(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述:TODO(删除假期返校基础设置表)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:41:05 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(JqfxJbszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from XG_RCSW_JQFXGL_JBSZB ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(获取返校代码和返校名称)
	 * @作者：lgx[工号:1553]
	 * @时间： 2018-4-20 下午03:41:05 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getfxmcList() {
		String sql = "select fxdm,fxmc from XG_RCSW_JQFXGL_FXLBB order by fxdm";
		return dao.getList(sql, new String[] {}, new String[] { "fxdm", "fxmc" });
	}

}

