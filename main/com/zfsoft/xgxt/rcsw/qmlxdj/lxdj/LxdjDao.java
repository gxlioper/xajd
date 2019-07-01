/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午08:57:49 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午08:57:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public final class LxdjDao extends SuperDAOImpl<LxdjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxdjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxdjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.* from (");
		sql.append(" select t.*,");
		sql.append(" rownum rnn,");
		sql.append(" t1.XM,");
		sql.append(" t1.XB,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.ZYMC,t1.mzmc,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.BJDM,t1.zybj,t1.zybjmc,t5.sydm,t6.symc,");
		sql.append(" t2.xqmc,");
		sql.append(" t3.mc lxgj,");
		sql.append(" t4.mc fxgj,");
		sql.append(" ssx.shengmc,");
		sql.append(" ssx.shimc,");
		sql.append(" ssx.xianmc,");
		sql.append(" ssx.shengmc || ssx.shimc || ssx.xianmc ssxdz,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" '未提交',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append("  '5',");
		sql.append(" '审核中',");
		sql.append(" t.shzt) shztmc");
		sql.append(" from xg_rcsw_lxqxdj_lxqxdjsqb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join XG_XTWH_SYBJGLB t5 on t1.bjdm = t5.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t6 on t5.sydm = t6.sydm ");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.lxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t4");
		sql.append(" on t.fxjtgjdm = t4.dm");
		sql.append(" left join xg_view_dmk_qx ssx");
		sql.append(" on ssx.qxdm = t.mddssx");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LxdjForm.class);
		this.setKey("sqid");
		this.setTableName("xg_rcsw_lxqxdj_lxqxdjsqb");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午02:28:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotExist(String xh,String xn,String xq,String flag){
		StringBuffer sql = new StringBuffer();
		if("sq".equals(flag)){
			sql.append(" select count(1) num");
			sql.append(" from (select xh, xn, xq");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjsqb");
			sql.append(" union all");
			sql.append(" select xh, xn, xq");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjjgb)");
			sql.append(" where xh = ?");
			sql.append(" and xn = ?");
			sql.append(" and xq = ?");
			sql.append(" ");
		}else{
			sql.append(" select count(1) num");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjjgb");
			sql.append(" where xh = ?");
			sql.append(" and xn = ?");
			sql.append(" and xq = ?");
			sql.append(" ");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "num");	
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @描述: dmList
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmList(){
		StringBuffer sql = new StringBuffer();
		sql.append("   select * from xg_rcsw_lxqxdj_dmwhb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	//获取离校类型
	public List<HashMap<String, String>> getLxlxList(){
		StringBuffer sql = new StringBuffer();
		sql.append("   select * from xg_rcsw_lxlx_dmwhb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @描述: 获取查看信息map
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:45:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,");
		sql.append(" t1.xqmc,");
		sql.append(" t2.mc lxgj,");
		sql.append(" t3.mc fxgj,t4.mc lxlxmc");
		sql.append(" from");
		sql.append(" xg_rcsw_lxqxdj_lxqxdjsqb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.xq = t1.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t2");
		sql.append(" on t.lxjtgjdm = t2.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.fxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxlx_dmwhb t4 on t.lxlx = t4.dm ");
		sql.append(" where t.sqid = ?");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
}
