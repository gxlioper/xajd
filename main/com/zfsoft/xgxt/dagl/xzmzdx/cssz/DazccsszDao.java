/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:37:18 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理管理模块
 * @类功能描述: 档案转出参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:37:31 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazccsszDao extends SuperDAOImpl<DazccsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazccsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazccsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_dazccsszb");
		super.setClass(DazccsszForm.class);
	}
	
	/**
	 * @描述: 查询基础设置信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-27 下午06:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * DazccsszForm 返回类型 
	 * @throws
	 */
	public DazccsszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.fid uploadid,a.*, case when sqkg = 1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_xsxx_dagl_dazccsszb a ");
		sql.append("left join xg_comm_fileupload_data b on a.fjid = b.gid ");
		sql.append("");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 参数设置保存按钮触发删表操作
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-7 下午04:11:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteTableName() throws Exception{
		String sql = "delete from xg_xsxx_dagl_dazccsszb";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * @描述: 取参数设置信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-8 上午11:35:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getSplc(){
		String sql = "select * from xg_xsxx_dagl_dazccsszb where rownum = 1";
		return dao.getMapNotOut(sql, new String[]{});
	}

}
