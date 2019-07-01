/**
 * @部门:学工产品事业部
 * @日期：2017-6-27 下午02:57:14 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-6-27 下午02:57:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zhpxfDao extends SuperDAOImpl<zhpxfForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(zhpxfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(zhpxfForm t, User user)
			throws Exception {
		SearchModel searchmodel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from( select a.zcfid,a.xn, a.xh,a.xm, a.zhpxf,");
		sql.append(" case when instr(to_char(a.pjf), '.') < 1 then to_char(a.pjf) || '.00'when instr(to_char(a.pjf), '.') + 1 = length(to_char(a.pjf)) then to_char(a.pjf) || '0' else to_char(round(a.pjf, 2)) end pjf,");
		sql.append(" case when instr(to_char(a.zhszf), '.') < 1 then to_char(a.zhszf) || '.00'when instr(to_char(a.zhszf), '.') + 1 = length(to_char(a.zhszf)) then to_char(a.zhszf) || '0' else to_char(round(a.zhszf, 2)) end zhszf,");
		sql.append(" b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,b.bjdm,b.xb,b.nj, rank() over(partition by b.bjdm,a.xn order by a.zhszf desc) || '/' || c.bjrs zhszfpm from (");
		sql.append(" select a.zcfid,a.xn,a.xh,a.xm,case when instr(to_char(a.zhpxf), '.') < 1 then to_char(a.zhpxf) || '.00' when instr(to_char(a.zhpxf), '.') + 1 = length(to_char(a.zhpxf)) then to_char(a.zhpxf) || '0' else to_char(round(a.zhpxf, 2)) end zhpxf, CAST((sum(b.xf * b.cj) / sum(b.xf)) AS NUMBER (10, 2) ) pjf,");
		sql.append(" CAST((a.zhpxf*0.3+(sum(b.xf * b.cj)/sum(b.xf))*0.7) AS NUMBER(10, 2)) zhszf ");
		sql.append(" from xg_pjpy_zhpxf a left join cjb b on a.xh=b.xh and a.xn=b.xn group by a.zcfid,a.xn,a.xh,a.xm,a.zhpxf)a left join view_xsjbxx b on a.xh=b.xh ");
		sql.append(" left join (select bjdm, count(xh) bjrs from view_xsjbxx group by bjdm)c on b.bjdm = c.bjdm ");
		sql.append("  ) t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_PJPY_ZHPXF");
		super.setKey("zcfid");
		super.setClass(zhpxfForm.class);
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-6-28 下午05:12:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXhQjsjIsExist(String xn, String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_pjpy_zhpxf where xn = ? and xh = ? ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{xn,xh}, "num"))>=1 ? true : false;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-6-28 下午05:13:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkJsDrSjfw(String xh, zhpxfForm model) {
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(model.getUser(), "t", "xydm", "bjdm");
		sql.append(" select count(1) num from view_xsxxb t where xh = ?  ");
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num").equals("1") ? true : false;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-6-29 上午09:07:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
		public int[] saveDrDataIntoDb(List<String[]> paralist) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_zhpxf( ");
		sql.append(" xn,");
		sql.append(" xh,");
		sql.append(" xm,");
		sql.append(" zhpxf");
		sql.append(" )values(?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}

	/**
	 * @param myform  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-7-31 上午10:06:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * zhpxfForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getAllData(zhpxfForm myform) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from( select a.zcfid,a.xn, a.xh,a.xm, a.zhpxf,");
		sql.append("case when instr(to_char(a.pjf), '.') < 1 then to_char(a.pjf) || '.00'when instr(to_char(a.pjf), '.') + 1 = length(to_char(a.pjf)) then to_char(a.pjf) || '0' else to_char(round(a.pjf, 2)) end pjf,");
		sql.append("case when instr(to_char(a.zhszf), '.') < 1 then to_char(a.zhszf) || '.00'when instr(to_char(a.zhszf), '.') + 1 = length(to_char(a.zhszf)) then to_char(a.zhszf) || '0' else to_char(round(a.zhszf, 2)) end zhszf,");
		sql.append("b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,b.bjdm,b.xb,b.nj, rank() over(partition by b.bjdm,a.xn order by a.zhszf desc) zhszfpm from (");
		sql.append(" select a.zcfid,a.xn,a.xh,a.xm,case when instr(to_char(a.zhpxf), '.') < 1 then to_char(a.zhpxf) || '.00' when instr(to_char(a.zhpxf), '.') + 1 = length(to_char(a.zhpxf)) then to_char(a.zhpxf) || '0' else to_char(round(a.zhpxf, 2)) end zhpxf, CAST((sum(b.xf * b.cj) / sum(b.xf)) AS NUMBER (10, 2) ) pjf,");
		sql.append(" CAST((a.zhpxf*0.3+(sum(b.xf * b.cj)/sum(b.xf))*0.7) AS NUMBER(10, 2)) zhszf ");
		sql.append(" from xg_pjpy_zhpxf a left join cjb b on a.xh=b.xh and a.xn=b.xn group by a.zcfid,a.xn,a.xh,a.xm,a.zhpxf)a left join view_xsjbxx b on a.xh=b.xh  ) t");
		sql.append(" where t.zcfid=?  ");
		return dao.getMapNotOut(sql.toString(), new String[]{myform.getZcfid()});
	}

	/**
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-9-21 上午10:40:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gxlist
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public int[] saveGxDataIntoDb(List<String[]> gxlist) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zhpxf set xm=?,zhpxf=? where xn=? and xh=?");
 		return dao.runBatch(sql.toString(), gxlist);
	}

}
