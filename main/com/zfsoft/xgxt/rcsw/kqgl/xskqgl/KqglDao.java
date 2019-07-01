/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 下午04:24:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 学生考勤管理
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 下午04:24:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqglDao extends SuperDAOImpl<KqglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KqglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 学生考勤登记结果查询
	 */
	public List<HashMap<String, String>> getPageList(KqglForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_new_dc_rcsw_kqgl t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	  
	
	protected void setTableInfo() {
		super.setTableName("xg_kqgl_kqdj");
		super.setKey("kqdjid");
	}
	
	
	/**
	 * 
	 * @描述:新增保存重复性判断（学号，考勤时间，考勤类型）
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午05:32:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(KqglForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_kqgl_kqdj ");
		sql.append(" where xh = ? and kqsj = ? and kqlxdm = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getKqsj(), form.getKqlxdm()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @描述:修改保存重复性判断（学号，考勤时间，考勤类型）
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午05:34:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(KqglForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_kqgl_kqdj ");
		sql.append(" where xh = ? and kqsj = ? and kqlxdm = ? and kqdjid <> ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getKqsj(), form.getKqlxdm() ,form.getKqdjid()}, "num");
		
		return num;
		
	}
	
	
	/**
	 * 
	 * @描述:考勤登记结果单个查看
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午05:45:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kqdjid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKqdjList(String kqdjid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_new_dc_rcsw_kqgl where kqdjid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[] { kqdjid });
		
	}
	
}
