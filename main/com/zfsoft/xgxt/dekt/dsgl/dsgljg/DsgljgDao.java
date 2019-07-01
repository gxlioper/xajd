/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsgljg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DsgljgDao extends SuperDAOImpl<DsgljgForm>{

	@Override
	public List<HashMap<String, String>> getPageList(DsgljgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DsgljgForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*");
		sql.append(" from ( select t1.*,");
		sql.append("t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.zybj,t2.zybjmc,t2.xymc,t2.zymc,t2.nj,t4.sydm,t4.symc from (select xh,count(1) ydbs from xg_dekt_dsglb group by xh) t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xtwh_sybjglb t3 on t2.bjdm=t3.bjdm ");
		sql.append(" left join xg_xtwh_sydmb t4 on t3.sydm=t4.sydm ");
		sql.append(" ) a  ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("jgid");
		super.setTableName("xg_dekt_dsglb");
	}

	public boolean deleteExist(DsgljgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_dekt_dsglb where xh=? and xn = ? and xq = ? and ssm=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getSsm()});
	}

	public boolean isExist(DsgljgForm model) {
		String sql = "select count(1) num from xg_dekt_dsglb where xn = ? and xq =? and xh=? and ssm=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getSsm()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(DsgljgForm model) {
		String sql = "select count(1) num from xg_dekt_dsglb where xn = ? and xq =? and xh=? and ssm=?  and jgid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getSsm(),model.getJgid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isCanDel(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_dekt_dsglb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}

	public HashMap<String, String> getDsxxjg(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_dekt_dsglb a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	public HashMap<String, String> getDsxxInfo(DsgljgForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t3.xqmc");
		sql.append(" from xg_dekt_dsglb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJgid()});
	}

	public List<String[]> getYdxqInfo(String[] inputValue, String[] outputValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc from xg_dekt_dsglb a left join xqdzb b on a.xq=b.xqdm where a.xh=?");
		return dao.rsToVator(sql.toString(), inputValue, outputValue);
	}

}
