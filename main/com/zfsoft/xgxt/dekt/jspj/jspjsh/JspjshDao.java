/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JspjshDao extends SuperDAOImpl<JspjshForm>{

	

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_dekt_jspjglsqb");
	}

	@Override
	public List<HashMap<String, String>> getPageList(JspjshForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JspjshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.*,t1.pjsj xpjsj,t8.xm pjjsxm,t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb, t2.bjmc,t2.sfzh, ");
		sql.append(" t2.lxdh,t2.sjhm, t2.xydm, t2.zydm,t2.xymc,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc, t4.guid shid,t4.gwid,t4.shr, t4.shyj, t6.mc || '[' || decode(t4.shzt, ");
		sql.append(" '0', '待审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审',  '5','审核中') || ']' shztmc,t6.gwz,t7.xqmc,t10.sydm,t10.symc,t11.rstjmc ,");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_dekt_jspjglsqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh  left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm left join view_fdyxx t8 on t8.zgh=t1.pjjszgh");
		sql.append(" left join xg_xtwh_sybjglb t9 on t2.bjdm=t9.bjdm ");
		sql.append(" left join xg_xtwh_sydmb t10 on t9.sydm =t10.sydm ");
		sql.append(" left join XG_DEKT_RSTJ t11 on t1.ylzd1 =t11.rstjdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append("  ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String, String> getJspjxxInfo(JspjshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t3.xqmc,t4.xm pjjsxm, t5.rstjmc");
		sql.append(" from xg_dekt_jspjglsqb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm left join view_fdyxx t4 on t4.zgh=t1.pjjszgh left join XG_DEKT_RSTJ t5 on t1.ylzd1 = t5.rstjdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public boolean updateJspjxx(String sqid, String shzt) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_dekt_jspjglsqb set shzt = ?  where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean deleteJspjxxjg(String sqid) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_dekt_jspjglb ");
		sql.append(" where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}

}
