/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class SspyshDao extends SuperDAOImpl<SspyshForm>{

	@Override
	public List<HashMap<String, String>> getPageList(SspyshForm t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(SspyshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.*,t2.xydm,t2.xm,t2.zydm,t9.ldmc,t8.pyxmmc,t2.bjdm,t4.guid shid, t4.gwid,t4.shr, t4.shyj, t6.mc || '[' || decode(t4.shzt, "); 
		sql.append(" '0', '待审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审',  '5','审核中') || ']' shztmc,t6.gwz,t7.xqmc, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_gygl_sspysqb t1 left join xsxxb t2 on t1.xh = t2.xh  left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.sqid = t4.ywid ");
		String shlx = t.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm  left join xg_gygl_new_sspyxmdmb t8 on t1.pyxmdm = t8.pyxmdm ");
		sql.append("  left join xg_gygl_new_ldxxb t9 on t1.lddm = t9.lddm  where t5.spyh = '"+user.getUserName()+"' ");
		sql.append("  ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_sspysqb");
		super.setKey("sqid");
	}
	public HashMap<String, String> getSspyInfo(SspyshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t4.ldmc,t5.pyxmmc,t2.xm,t3.xqmc");
		sql.append(" from xg_gygl_sspysqb t1 left join xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm left join xg_gygl_new_ldxxb t4 on t1.lddm=t4.lddm ");
		sql.append(" left join xg_gygl_new_sspyxmdmb t5 on t1.pyxmdm=t5.pyxmdm where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	public boolean updateSspy(String sqid, String shzt) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_gygl_sspysqb set shzt = ?  where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	//如果是最后一级审核撤销，删除结果表记录
	public boolean deletesspyjg(String sqid) throws Exception {
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_gygl_sspyjgb ");
		sql.append(" where ywid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}

	
	
	
	
	
}
