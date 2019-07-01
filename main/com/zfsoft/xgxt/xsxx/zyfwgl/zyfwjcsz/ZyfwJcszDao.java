/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 上午10:24:58 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务基础设置Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 上午10:24:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJcszDao extends SuperDAOImpl<ZyfwJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(ZyfwJcszForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_zyfwgl_jcszb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJcszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJcszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * @描述:获取基础设置信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午2:03:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ZyfwJcszForm 返回类型 
	 * @throws Exception
	 */
	public ZyfwJcszForm getModel() throws Exception {
		String sql = "select * from xg_xsxx_zyfwgl_jcszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}

	/** 
	 * @描述:获取申请审核开关状态
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午5:15:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String [] 返回类型 
	 * @throws 
	 */
	public String[] getSqShKg() {
		// TODO 自动生成方法存根
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg");
		sql.append(" from xg_xsxx_zyfwgl_jcszb t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}

}
