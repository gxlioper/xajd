/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsglsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DsglsqDao extends SuperDAOImpl<DsglsqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(DsglsqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DsglsqForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String ydzt = t.getYdzt();
		if ("wdsm".equals(ydzt)) {
			sql.append(" select * from (select t1.* from xg_dekt_smwhb t1 where t1.ssm not in ( select ssm from xg_dekt_dsglsqb where xh='"+user.getUserName()+"'))a where 1 = 1");
		}else {
			sql.append(" select a.*,(select splc from xg_dekt_shlcszb where lx='ds') splcidnew");
			sql.append(" from ( select t1.*,");
			sql.append(" t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
			sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.xymc,t2.zymc,t2.nj,t3.xqmc, ");
			sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
			sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_dekt_dsglsqb ");
			sql.append(" t1  left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a  ");
			sql.append(" where 1 = 1");
			sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_dekt_dsglsqb");
	}

	
	public boolean isExist(DsglsqForm model) {
		String sql = "select count(1) num from xg_dekt_dsglsqb where xn = ? and xq =? and xh=? and ssm=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getSsm()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_dekt_shlcszb where lx='ds' ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isExistforUpdate(DsglsqForm model) {
		String sql = "select count(1) num from xg_dekt_dsglsqb where xn = ? and xq =? and xh=? and ssm=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getSsm(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_dekt_dsglsqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}

	public HashMap<String, String> getDssqxx(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_dekt_dsglsqb a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}

	public boolean updateDssq(DsglsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_dekt_dsglsqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String,String> getDssqInfo(DsglsqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t3.xqmc");
		sql.append(" from xg_dekt_dsglsqb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public String getdsCount(String xh) {
		String sql = " select count(1) rn from xg_dekt_dsglb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}
	public String getjsCount(String xh) {
		String sql = " select count(1) rn from xg_dekt_jspjglb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}

	public String getjzCount(String xh) {
		String sql = " select count(1) rn from view_xg_hdgl_xshdxx a left join xg_hdgl_hdbqglb b on a.hdid = b.jgid left join xg_hdgl_hdbqdmb c on b.hdbq = c.hdbqdm where c.hdbqmc like '%讲座%' and a.xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}

	public String gethdCount(String xh) {
		String sql = " select count(1) rn from view_xg_hdgl_xshdxx a left join xg_hdgl_hdbqglb b on a.hdid = b.jgid left join xg_hdgl_hdbqdmb c on b.hdbq = c.hdbqdm where c.hdbqmc like '%活动%' and a.xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "rn");
	}
}
