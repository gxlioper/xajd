/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class StglshDao extends SuperDAOImpl<StglshForm>{

	@Override
	public List<HashMap<String, String>> getPageList(StglshForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StglshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.*,'0' shlx,t2.xm, (case t2.xb when '1' then '男' when '2' then '女' else t2.xb end) xb, t2.bjmc,t2.sfzh, ");
		sql.append(" t2.lxdh,t2.sjhm, t2.xydm, t2.zydm,t2.xymc,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc, t4.guid shid,t4.gwid,t4.shr, t4.shyj,'[新社团]'||t6.mc || '[' || decode(t4.shzt, ");
		sql.append(" '0', '待审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审', '5','审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_ttgl_stglsqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh  left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.sqid = t4.ywid ");
		if(!"dsh".equals(t.getShzt())){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		
		sql.append(" union all ");
		
		sql.append(" select t1.*,'1' shlx,t2.xm, (case t2.xb when '1' then '男' when '2' then '女' else t2.xb end) xb, t2.bjmc,t2.sfzh, ");
		sql.append(" t2.lxdh,t2.sjhm, t2.xydm, t2.zydm,t2.xymc,t2.zymc,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc, t4.guid shid,t4.gwid,t4.shr, t4.shyj, '[社团转正]'||t6.mc || '[' || decode(t4.shzt, ");
		sql.append(" '0', '待审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审', '5','审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_ttgl_stglsqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh  left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.zzywid = t4.ywid ");
		if(!"dsh".equals(t.getShzt())){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
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
		super.setTableName("xg_ttgl_stglsqb");
	}

	public HashMap<String, String> getStxxInfo(StglshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm zdlsxm,t3.bmmc,t4.mc as zzlbmc,t5.mc as ndzzztmc");
		sql.append(" from xg_ttgl_stglsqb t1 left join fdyxxb t2 on t1.stzdls = t2.zgh");
		sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
		sql.append(" left join XG_TTGL_ZZLBDMB t4 on t1.zzlb = t4.dm");
		sql.append(" left join XG_TTGL_NDZZZTDMB t5 on t1.ndzzzt = t5.dm");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public boolean updateStsq(String sqid, String shzt) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stglsqb set shzt = ?  where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean deleteStxxjg(String sqid) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_ttgl_stgljgb ");
		sql.append(" where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean updateStcyb(String sqid, String shsj) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stcyb set shzt = '1',shsj=?  where jgid = ?");
		inputV[0] = shsj;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean updateZzshzt(StglshForm upForm) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stglsqb set zzshzt = ? where zzywid = ?");
		inputV[0] = upForm.getZzshzt();
		inputV[1] = upForm.getZzywid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean updateJgbStzt(StglshForm form) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stgljgb set stzt ='1' where sqid = ?");
		inputV[0] = form.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean updateSqbZzshzt(String zzywid, String ywShz) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stglsqb set zzshzt = ?  where zzywid = ?");
		inputV[0] = ywShz;
		inputV[1] = zzywid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean updateStzt(String sqid) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stgljgb set stzt ='0'  where sqid = ?");
		inputV[0] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

}
