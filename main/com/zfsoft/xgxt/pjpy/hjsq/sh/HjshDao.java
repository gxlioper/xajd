package com.zfsoft.xgxt.pjpy.hjsq.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class HjshDao extends SuperDAOImpl<HjshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(HjshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(HjshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (select t.*,t1.XM,t1.XB,t1.NJ, t1.XYDM,t1.XYMC,t1.ZYDM, ");
		sql.append(" t2.xqmc,t4.shzt shztx,t4.guid shid,t4.gwid,t4.shr,t4.shyj,");
		sql.append(" t1.ZYMC,t1.BJDM,t1.BJMC,t6.mc || '[' || decode(t4.shzt,'0', '未审核', '1','通过','2','不通过', '3','退回', '4','需重审','5','审核中') || ']' shztmc,");
		sql.append(" t6.gwz, row_number() over(partition by t.id order by t4.shsj desc) rn");
		sql.append(" from xg_xsxx_new_hjqk_sqb t  left join view_xsbfxx t1 on t.xh = t1.XH");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm");
		sql.append(" left join xg_xtwh_shztb t4 on t.id = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id");
		sql.append(" where t5.spyh = '"+user.getUserName()+"'  ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t  where 1 = 1");
		sql.append(" and  rn = 1 ");
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(HjshForm.class);
		this.setKey("id");
		this.setTableName("xg_xsxx_new_hjqk_sqb");
	}
	
	public boolean deleteJg(String xn,String xq,String xh,String hjmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(xn);
		paraList.add(xh);
		paraList.add(xq);
		paraList.add(hjmc);
		sql.append(" delete from xg_xsxx_new_hjqk_jgb where xn = ?  and xh = ?");
		sql.append(" and xq = ? and hjmc = ?");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
}
