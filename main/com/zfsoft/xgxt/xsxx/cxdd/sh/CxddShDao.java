/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:22:06 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-28 下午05:22:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddShDao extends SuperDAOImpl<CxddShForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxddShForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm",
				"bjdm");
	    sql.append(" select * from (");
        sql.append(" select t.bjdm,");
        sql.append(" t.bjid,");
        sql.append(" t.splc,");
        sql.append(" t.xn,");
        sql.append(" t.xq,");
        sql.append(" t1.nj,");
        sql.append(" t1.xydm,");
        sql.append(" t1.xymc,");
        sql.append(" t1.bjmc,");
        sql.append(" t1.zydm,");
        sql.append(" t1.zymc,");
        sql.append(" t.tjsj,");
        sql.append(" t3.xm tjrxm,");
        sql.append(" t2.bjrs,");
        sql.append(" t4.shzt,");
        sql.append(" t4.guid shid,");
        sql.append(" t4.gwid,");
        sql.append(" t4.shr,");
        sql.append(" t4.shyj,");
        sql.append(" t6.mc || '[' ||");
        sql.append(" decode(t4.shzt,");
        sql.append(" '0',");
        sql.append(" '未审核',");
        sql.append(" '1',");
        sql.append(" '通过',");
        sql.append(" '2',");
        sql.append(" '不通过',");
        sql.append(" '3',");
        sql.append(" '退回',");
        sql.append(" '4',");
        sql.append(" '需重审',");
        sql.append(" '5',");
        sql.append(" '审核中') || ']' shztmc,");
        sql.append(" t6.gwz,");
        sql.append(" row_number() over(partition by t.bjid order by t4.shsj desc) rn");
        sql.append(" from xg_xsxx_cxpy_pysb_bj t");
        sql.append(" left join view_njxyzybj t1");
        sql.append(" on t.bjdm = t1.bjdm");
        sql.append(" left join (select count(1) bjrs, bjdm");
        sql.append(" from xg_xsxx_cxpy_pysb_xs");
        sql.append(" where xn = '"+Base.currXn+"'");
        sql.append(" and xq = '"+Base.currXq+"' group by bjdm) t2");
        sql.append(" on t1.bjdm = t2.bjdm");
        sql.append(" left join yhb t3");
        sql.append(" on t.tjr = t3.yhm");
        sql.append(" left join xg_xtwh_shztb t4");
        sql.append(" on t.bjid = t4.ywid");
        sql.append(" left join xg_xtwh_spgwyh t5");
        sql.append(" on t4.gwid = t5.spgw");
        sql.append(" left join xg_xtwh_spgw t6");
        sql.append(" on t4.gwid = t6.id");
        sql.append(" where t5.spyh = '"+user.getUserName()+"'");
        String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		}else{
			sql.append(" and (t4.shzt=0 or  t4.shzt=4)");
		}
        sql.append(" and xn = '"+Base.currXn+"'");
        sql.append(" and xq = '"+Base.currXq+"'");
        sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
		// sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(CxddShForm.class);
		this.setKey("bjid");
		this.setTableName("xg_xsxx_cxpy_pysb_bj");
	}
	
	public List<HashMap<String, String>> getViewList(CxddShForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.xh, t.py, t2.cxdjmc, t1.bjmc, t1.xymc, t1.zymc,t1.xm");
		sql.append(" from xg_xsxx_cxpy_pysb_xs t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join xsxx_cxdjdmb t2");
		sql.append(" on t.pj = t2.cxdjdm");
		if("nopage".equalsIgnoreCase(t.getFlag())){
			t.getPages().setPageSize(Integer.MAX_VALUE);
		}
		sql.append("  where t.xn = ? and t.xq = ? and t.bjdm = ?");
		paralist.add(Base.currXn);
		paralist.add(Base.currXq);
		paralist.add(t.getBjdm());
		sql.append(" ) t");
		sql.append(" where 1=1 ");
		if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and (xh like %?% or xm like %?%) ");
			paralist.add(t.getXh());
			paralist.add(t.getXh());
		}
		return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}
	
	public boolean insertIntoJg(CxddShForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" insert into xg_xsxx_cxpy_pysb_jg");
		sql.append(" select t.*,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') tjsj,'1'sjly,''lylcywid from xg_xsxx_cxpy_pysb_xs t");
		sql.append(" where t.bjdm = ? ");
		sql.append(" and t.xn = ? and t.xq = ?");
		paralist.add(t.getBjdm());
		paralist.add(Base.currXn);
		paralist.add(Base.currXq);
		return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	public boolean delJg(CxddShForm t)throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("  delete from xg_xsxx_cxpy_pysb_jg");
		sql.append("  where  bjdm = ?");
		sql.append("  and xn = ?");
		sql.append("  and xq = ?");
		paralist.add(t.getBjdm());
		paralist.add(Base.currXn);
		paralist.add(Base.currXq);
		return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception {
		String sql = "update xg_xsxx_cxpy_pysb_bj set shzt=?  where bjid = ?";

		return dao.runUpdate(sql, new String[] { shzt, ywid });

	}
	

}
