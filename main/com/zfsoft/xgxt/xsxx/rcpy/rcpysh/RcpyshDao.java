/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpysh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class RcpyshDao extends SuperDAOImpl<RcpyshForm>{

	@Override
	public List<HashMap<String, String>> getPageList(RcpyshForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RcpyshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.*,t7.pylbmc,t8.khzbmc,t9.xztjmc,t2.xydm,t2.zymc,t2.xy xymc,t2.bjmc,(case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.nj,t2.xm,t2.zydm,t2.bjdm,t4.guid shid, t4.gwid,t4.shr, t4.shyj, t6.mc || '[' || decode(t4.shzt, "); 
		sql.append(" '0', '待审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审',  '5','审核中') || ']' shztmc,t6.gwz,");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcpy_rcpysqb t1 left join xsxxb t2 on t1.xh = t2.xh  left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.sqid = t4.ywid ");
		String shlx = t.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t7 on t1.pylb = t7.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t8 on t1.khzb = t8.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t9 on t1.xztj = t9.xztjdm ");
		sql.append("  where t5.spyh = '"+user.getUserName()+"' ");
		sql.append("  ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_rcpy_rcpysqb");
	}

	public HashMap<String, String> getRcpysqInfo(RcpyshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.pylbmc,t3.khzbmc,t4.xztjmc from xg_rcpy_rcpysqb t1 ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t2 on t1.pylb = t2.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t3 on t1.khzb = t3.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t4 on t1.xztj = t4.xztjdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public boolean updateRcpysq(String sqid, String shzt) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_rcpy_rcpysqb set shzt = ?  where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean deleteRcpyjg(String sqid) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcpy_rcpyjgb where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}

}
