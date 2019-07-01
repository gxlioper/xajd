/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpyjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;


public class RcpyjgDao extends SuperDAOImpl<RcpyjgForm>{

	@Override
	public List<HashMap<String, String>> getPageList(RcpyjgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RcpyjgForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*");
		sql.append(" from ( select t1.*,t7.pylbmc,t8.khzbmc,t9.xztjmc,decode(t1.sjly,1,'流程数据',0,'结果数据') sjlymc,");
		sql.append("t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t6.bjmc,t2.lxdh,t6.xydm,t6.zydm,t2.bjdm,t6.xymc,t6.zymc,t2.nj from xg_rcpy_rcpyjgb ");
		sql.append(" t1  left join view_xsbfxx t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t7 on t1.pylb = t7.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t8 on t1.khzb = t8.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t9 on t1.xztj = t9.xztjdm ");
		sql.append("  ) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("jgid");
		super.setTableName("xg_rcpy_rcpyjgb");
	}

	public boolean isExist(RcpyjgForm model) {
		String sql = "select count(1) num from xg_rcpy_rcpyjgb where xh = ? and xmmc =? and pylb=? and khzb=? and xztj=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getXmmc(),model.getPylb(),model.getKhzb(),model.getXztj()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(RcpyjgForm model) {
		String sql = "select count(1) num from xg_rcpy_rcpyjgb where xh = ? and xmmc =? and pylb=? and khzb=? and xztj=? and jgid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getXmmc(),model.getPylb(),model.getKhzb(),model.getXztj(),model.getJgid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isCanDel(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcpy_rcpyjgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}

	public HashMap<String, String> getRcpyXhXm(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_rcpy_rcpyjgb a");
		sb.append(",xsxxb b where a.xh=b.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	public HashMap<String, String> getRcpyjgInfo(RcpyjgForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.pylbmc,t3.khzbmc,t4.xztjmc from xg_rcpy_rcpyjgb t1 ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t2 on t1.pylb = t2.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t3 on t1.khzb = t3.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t4 on t1.xztj = t4.xztjdm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJgid()});
	}

	public boolean deleteExist(RcpyjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		"delete from xg_rcpy_rcpyjgb where xh = ? and xmmc =? and pylb=? and khzb=? and xztj=?");
		return dao.runUpdate(sql.toString(), new String[] {model.getXh(),model.getXmmc(),model.getPylb(),model.getKhzb(),model.getXztj()});
	}

}
