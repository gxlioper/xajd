/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.szdw.sygl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class SyglDao extends SuperDAOImpl<SyglForm>{

	@Override
	public List<HashMap<String, String>> getPageList(SyglForm t)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.*,nvl(b.xxbjs,0) xxbjs from xg_xtwh_sydmb a left join (select sydm,count(1) xxbjs  from xg_xtwh_sybjglb group by sydm) b on a.sydm=b.sydm where 1 = 1");
		String[] symc = null;
		if(StringUtils.isNotNull(t.getSymc())){
			sb.append(" and a.symc like '%' || ? || '%'");
			symc = new String[]{t.getSymc()};
		}
		return getPageList(t, sb.toString(), StringUtils.joinStrArr(symc));
	}

	@Override
	public List<HashMap<String, String>> getPageList(SyglForm t, User user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sydm");
		super.setTableName("xg_xtwh_sydmb");
	}

	public boolean isExist(SyglForm model) {
		String sql = "select count(1) num from xg_xtwh_sydmb where symc = ? " ;
		String num = dao.getOneRs(sql, new String[]{model.getSymc()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(SyglForm model) {
		String sql = "select count(1) num from xg_xtwh_sydmb where symc = ?  and sydm <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getSymc(),model.getSydm()}, "num");
		return Integer.valueOf(num)>0;
	}

	public List<HashMap<String, String>> getBjList(SyglForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.bjrs,a.bjdm,b.bjmc,c.dbfdy from ( ");
		sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" left join (select bjdm,wm_concat(t2.xm) dbfdy from fdybjb t1 left join fdyxxb t2 on t1.zgh = t2.zgh  group by bjdm) c on a.bjdm = c.bjdm ");
		if ("dfp".equals(t.getFpzt())) {
			sql.append(" where not exists (select 1 from xg_xtwh_sybjglb b where a.bjdm = b.bjdm ) ");
		}else {
			sql.append(" where exists (select 1 from xg_xtwh_sybjglb b where a.bjdm = b.bjdm and b.sydm='"+t.getSydm()+"') ");
		}
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	public boolean bjFp(String sydm, String[] bjdmarr) throws Exception {
	
		String[] sqlArr = new String[bjdmarr.length];
		for(int i = 0;i<bjdmarr.length;i++){
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into xg_xtwh_sybjglb (sydm, bjdm) values ('"+sydm+"','"+bjdmarr[i]+"')");
			sqlArr[i]=sql.toString();
		}
		int[] num =dao.runBatch(sqlArr);
		return dao.checkBatch(num);
	}

	public boolean bjQxfp(String sydm, String[] bjdmarr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_sybjglb where sydm=? and bjdm in (");
		for(int i = 0;i<bjdmarr.length;i++){
			if(i==bjdmarr.length-1){
				sql.append("'"+bjdmarr[i]+"'");
			}else{
				sql.append("'"+bjdmarr[i]+"',");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{sydm});
	}

	public boolean isHaveBj(String sydm) {
		String sql = "select count(1) num from xg_xtwh_sybjglb where sydm = ?" ;
		String num = dao.getOneRs(sql, new String[]{sydm}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean syDelete(String sydm) throws Exception {
		String sql = "delete from xg_xtwh_sydmb where sydm=?";
		return  dao.runUpdate(sql, new String[]{sydm});
	}

}
