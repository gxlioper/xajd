/**
 * @部门:学工产品事业部
 * @日期：2014-6-26 下午01:58:05 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-6-26 下午01:58:05
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmShDao extends SuperDAOImplExtend<TxhdXmShForm> {
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmShForm t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * ");
		sql.append(" from (select t2.xm,t2.xymc,t2.xydm,t2.bjmc,t2.bjdm,t2.xb,t1.sqid,t1.xh,t1.sqr,t1.sqsj,t1.sqly,t1.splc,t1.xmdm,t1.xn,t1.xq,f.*,d.shzt,");
		sql.append(" t3.xmmc,");
		sql.append(" d.gwid, ");
		sql.append("  d.shr, ");
		sql.append("  d.shyj, ");
		sql.append(" d.guid shid, ");
		sql
				.append(" row_number() over(partition by t1.sqid order by d.shsj desc) rn ");
		sql.append(" from xg_rcsw_txhd_xmsq t1 ");
		sql.append("  left join xg_xtwh_shztb d ");
		sql.append("  on t1.sqid = d.ywid ");
		//学生信息
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		
		sql.append("  left join xg_xtwh_spgwyh e ");
		sql.append("   on d.gwid = e.spgw ");
		sql.append(" left join xg_xtwh_spgw f ");
		sql.append("    on d.gwid = f.id  left join xg_rcsw_txhd_xmwh t3 on t1.xmdm = t3.xmdm ");
		sql.append(" where e.spyh = '" + user.getUserName()
				+ "' and t1.shzt<>9 and d.shzt<>9 ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" and t3.shkg = '1' and (sysdate between to_date(nvl(shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
		sql.append(" to_date(nvl(shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) ");
		sql.append(" ) a where rn = 1 )a where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取团学项目信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-6-26 下午04:58:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTxXmxx(TxhdXmShForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_rcsw_txhd_xmwh t where t.xmdm=?");
		return dao.getListNotOut(sql.toString(),new String[]{t.getXmdm()});
	}

	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_rcsw_txhd_xmsq");
		this.setClass(TxhdXmShForm.class);
	}
	
	
	/**
	 * 
	 * @描述: 根据项目代码、岗位ID获取通过人数
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午06:00:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param gwid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByXmdm(String xmdm, String gwid)throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from ( ");
		sql.append("select t1.xmdm,t2.gwid,t2.shzt, ");
		sql.append("row_number() over(partition by t1.sqid, t2.gwid order by t2.shsj desc) lvl from xg_rcsw_txhd_xmsq t1 ");
		sql.append(" left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ) where lvl = 1 and shzt = '1' and xmdm =? and gwid = ?");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm,gwid}, "count");
	}
	
}
