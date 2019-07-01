/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.jzbg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JzbgDao extends SuperDAOImpl<JzbgForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JzbgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JzbgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.xm fbrxm from xg_zjbg_jzbgb a left join fdyxxb b on a.fbr=b.zgh "); 
		sql.append(" where 1 = 1"); 
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
	super.setTableName("xg_zjbg_jzbgb");
	super.setKey("jzid");}

	public boolean isExist(JzbgForm model) {
		String sql = "select count(1) num from xg_zjbg_jzbgb where mc = ? and jzid !=?" ;
		String num = dao.getOneRs(sql, new String[]{model.getMc(),model.getJzid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public List<HashMap<String, String>> getPjxxPageList(JzbgForm t,
			User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (select * from xg_zjbg_jzbgb t1 left join (select b.* from xg_zjbg_jzbgb a left join xg_zjbg_bgcyryxxb b on a.jzid=b.jzid where b.xh='"+user.getUserName()+"' ) t2 on t1.jzid= t2.jzid) t"); 
		sql.append(" where 1=1 "); 
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean insertPjxx(JzbgForm t, User u) throws Exception {
		String sql = "insert into xg_zjbg_bgcyryxxb (jzid,xh) values (?,?)";
		return dao.insert(sql, new String[]{t.getJzid(),u.getUserName()});
	}

	public boolean updatePjxx(JzbgForm t) throws Exception {
		String sql = "update xg_zjbg_bgcyryxxb set pj=?,pjbz=? where pjid=?";
		return dao.runUpdate(sql,new String[]{t.getPj(),t.getPjbz(),t.getPjid()});
	}

	public boolean JzbgDelete(String values) throws Exception {
		String sql = "delete from xg_zjbg_jzbgb where jzid=?";
		return  dao.runUpdate(sql, new String[]{values});
	}

	public boolean isExistPjxx(String jzid) {
		String sql = "select count(1) num from xg_zjbg_bgcyryxxb where jzid = ?" ;
		String num = dao.getOneRs(sql, new String[]{jzid}, "num");
		return Integer.valueOf(num)>0;
	}

	public HashMap<String, String> getJzbgXX(JzbgForm myForm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xh,a.pj,a.pjbz,b.jzid,b.mc,b.sj,b.dd,b.zbdw,b.zjr,b.cyrs,c.xm fbr,b.fbsj,b.zt from xg_zjbg_bgcyryxxb a left join xg_zjbg_jzbgb b on a.jzid=b.jzid  left join fdyxxb c on b.fbr=c.zgh");
		sql.append(" where a.pjid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getPjid()});
		
	}

	public HashMap<String, String> getModelXX(JzbgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.jzid,a.mc,a.sj,a.dd,a.zbdw,a.zjr,a.cyrs,b.xm fbr,a.fbsj,a.zt from xg_zjbg_jzbgb a left join fdyxxb b on a.fbr=b.zgh where a.jzid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJzid()});
	}

}
