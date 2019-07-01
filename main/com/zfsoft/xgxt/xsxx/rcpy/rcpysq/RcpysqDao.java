/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpysq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class RcpysqDao extends SuperDAOImpl<RcpysqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(RcpysqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RcpysqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew");
		sql.append(" from ( select t1.*,t2.xm,t3.pylbmc,t4.khzbmc,t5.xztjmc, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.xymc,t2.zymc,t2.nj, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
		sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_rcpy_rcpysqb t1");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t3 on t1.pylb = t3.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t4 on t1.khzb = t4.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t5 on t1.xztj = t5.xztjdm ");
		sql.append(" ) a left join xg_rcpy_csszb b on 1=1 ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_rcpy_rcpysqb");
	}

	public boolean isExist(RcpysqForm model) {
		String sql = "select count(1) num from xg_rcpy_rcpysqb where xh = ? and xmmc =? and pylb=? and khzb=? and xztj=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getXmmc(),model.getPylb(),model.getKhzb(),model.getXztj()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(RcpysqForm model) {
		String sql = "select count(1) num from xg_rcpy_rcpysqb where xh = ? and xmmc =? and pylb=? and khzb=? and xztj=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getXmmc(),model.getPylb(),model.getKhzb(),model.getXztj(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcpy_csszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_rcpy_rcpysqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}

	public HashMap<String, String> getRcpyXhXm(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_rcpy_rcpysqb a");
		sb.append(",xsxxb b where a.xh=b.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}

	public boolean updateRcpysq(RcpysqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_rcpy_rcpysqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String, String> getRcpysqInfo(RcpysqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.pylbmc,t3.khzbmc,t4.xztjmc from xg_rcpy_rcpysqb t1 ");
		sql.append(" left join xg_xsxx_rcpy_pylbdmb t2 on t1.pylb = t2.pylbdm ");
		sql.append(" left join xg_xsxx_rcpy_khzbdmb t3 on t1.khzb = t3.khzbdm ");
		sql.append(" left join xg_xsxx_rcpy_xztjdmb t4 on t1.xztj = t4.xztjdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public List<HashMap<String, String>> getPylbList() {
		StringBuilder sql=new StringBuilder();
		sql.append(" select pylbdm pylb,pylbmc from xg_xsxx_rcpy_pylbdmb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public List<HashMap<String, String>> getKhzbList() {
		StringBuilder sql=new StringBuilder();
		sql.append(" select khzbdm khzb,khzbmc from xg_xsxx_rcpy_khzbdmb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public List<HashMap<String, String>> getXztjList() {
		StringBuilder sql=new StringBuilder();
		sql.append(" select xztjdm xztj,xztjmc from xg_xsxx_rcpy_xztjdmb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}


}
