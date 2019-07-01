/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:28:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务类型DAO
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:28:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwlxDAO extends SuperDAOImpl<ZwlxForm> {

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("lxdm");
		super.setTableName("xg_szdw_xsgb_zwlxb");
		super.setClass(ZwlxForm.class);
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwlxForm model)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select t.lxdm,t.lxmc,t.bz,t1.lcxx splc from xg_szdw_xsgb_zwlxb t   ");
		
		sql.append(" left join ");
		sql.append(" (select splc,mc||'：'||replace(max(lcxx),';','->')lcxx from( ");
		sql.append("select splc,a.mc,to_char(WM_CONCAT(c.mc)over(  ");
		sql.append("partition by splc  order by xh))lcxx from xg_xtwh_splc a, ");
		sql.append("xg_xtwh_spbz b, ");
		sql.append("xg_xtwh_spgw c ");
		sql.append("where  a.id=b.splc and b.spgw=c.id) group by splc,mc)t1 on t.splc = t1.splc ");
		sql.append("where 1 =1 ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwlxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * @描述:获取类型列表
	 * @作者：zhangjw
	 * @日期：2013-8-8 上午9:52:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getList(){
		String sql = "select  * from xg_szdw_xsgb_zwlxb ";
		return dao.getArrayList(sql, new String[]{}, new String[]{"lxdm","lxmc"});
	}
	/**
	 * @描述:根据类型名称查询数量
	 * @作者：zhangjw
	 * @日期：2013-8-28 下午6:00:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxname
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getCountBylxName(String lxname) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from xg_szdw_xsgb_zwlxb where lxmc='"+lxname+"'");
		return dao.getOneRsint(sql.toString());
	}
	
	/**
	 * 
	 * @描述: 审批流程名称查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-26 上午10:21:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getSplcMc(String splc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc,mc||'：'||replace(max(lcxx),';','->')lcxx from( ");
		sql.append("select splc,a.mc,to_char(WM_CONCAT(c.mc)over(  ");
		sql.append("partition by splc  order by xh))lcxx from xg_xtwh_splc a, ");
		sql.append("xg_xtwh_spbz b, ");
		sql.append("xg_xtwh_spgw c ");
		sql.append("where  splc=? and a.id=b.splc and b.spgw=c.id) group by splc,mc ");
		return dao.getMapNotOut(sql.toString(), new String[]{splc});
	}

}
