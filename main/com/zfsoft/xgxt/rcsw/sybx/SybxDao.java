/**
 * @部门:学工产品事业部
 * @日期：2013-5-9 上午08:45:05 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO 商业保险管理
 * @作者： honglin 
 * @时间： 2013-5-8 下午05:22:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SybxDao extends SuperDAOImpl<SybxForm>{


	@Override
	public List<HashMap<String, String>> getPageList(SybxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SybxForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from view_xg_rcsw_sybxxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_sybxxxb");
		super.setKey("guid");
		super.setClass(SybxForm.class);
	}
	
	/**
	 * 
	 * @描述:增加操作唯一性判断（学号，学年）
	 * @作者：honglin
	 * @日期：2013-5-9 下午02:09:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForSave(SybxForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rcsw_sybxxxb where xh=? and xn=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @描述:修改操作唯一性判断（学号，学年）
	 * @作者：honglin
	 * @日期：2013-5-9 下午02:09:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(SybxForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rcsw_sybxxxb where xh=? and xn=? and guid<>?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getGuid()}, "num");
		return num;
		
	}

	/**
	  * 
	  * @描述:获得单个学生商业保险信息
	  * @作者：HongLin
	  * @日期：2013-5-9 下午02:09:02
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xh
	  * @param request
	  * @return
	  * List<String[]> 返回类型 
	  * @throws
	  */
	public HashMap<String, String> getOneSybxList(String  guid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,bxje,jhrxm,jhrsfzh,txdz,bxje,bz,czjmylbxje,sybxje,zjyy,czjmylbxcbqsrq,czjmylbxcbjsrq,sybxcbqsrq,sybxcbjsrq,cbrylb,jfrylb,sfzqfjg,sfzyxqxqsrq,sfzyxqxjzrq ");
		sql.append(",zjyymc,cbrylbmc,jfrylbmc ");
		sql.append("from view_xg_rcsw_sybxxxb a where guid=? ");
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	}
	
	/**
	 * @描述:获取增加原因
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllZjyyList() {
		String sql = "select dm,mc from WFXY_ZJYYB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @描述:获取参保人员类别
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllCbrylbList() {
		String sql = "select dm,mc from WFXY_CBRYLBB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @描述:获取缴费人员类别
	 * @作者：江水才[工号:1150]
	 * @日期：2014-10-11 下午03:00:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getAllJfrylbList() {
		String sql = "select dm,mc from WFXY_JFRYLBB";
		return dao.getListNotOut(sql, new String[]{});
	}
}
