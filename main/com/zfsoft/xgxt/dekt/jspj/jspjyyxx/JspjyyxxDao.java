/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjyyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JspjyyxxDao extends SuperDAOImpl<JspjyyxxForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JspjyyxxForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JspjyyxxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String pjlx = t.getPjlx();
		if(pjlx.equals("dpj")){
			sql.append(" select * from ( select a.*,b.XM ,b.XB,b.NJ ,b.XYDM,b.XYMC,b.ZYDM,b.ZYBJ,b.ZYBJMC,b.ZYMC,b.BJDM,b.BJMC,b.SFZH,b.ZZMM,b.ZZMMMC,k.sydm,k.symc,b.MZ,b.MZMC,b.XZ from XG_XAJD_DEKT_JXYY a ");
			sql.append(" left join view_xsxxb b on a.xh = b.xh");
			sql.append(" left join xg_xtwh_sybjglb j on b.bjdm=j.bjdm");
			sql.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm ");
			sql.append("  where (jssfty = '' or jssfty is null ) and a.zgh = '"+user.getUserName()+"')");
		}else{
			sql.append(" select * from (select a.*,b.XM ,b.XB,b.NJ ,b.XYDM,b.XYMC,b.ZYDM,b.ZYBJ,b.ZYBJMC,b.ZYMC,b.BJDM,b.BJMC,b.SFZH,b.ZZMM,b.ZZMMMC,k.sydm,k.symc,b.MZ,b.MZMC,b.XZ from XG_XAJD_DEKT_JXYY a ");
			sql.append(" left join view_xsxxb b on a.xh = b.xh");
			sql.append(" left join xg_xtwh_sybjglb j on b.bjdm=j.bjdm");
			sql.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm ");
			sql.append("  where  jssfty is not null  and a.zgh = '"+user.getUserName()+"')");
		}
		
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_dekt_jspjglsqb");
	}

	public boolean isExist(JspjyyxxForm model) {
		String sql = "select count(1) num from xg_dekt_jspjglsqb where xn = ? and xq =? and xh=? and pjjszgh=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getPjjszgh()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(JspjyyxxForm model) {
		String sql = "select count(1) num from xg_dekt_jspjglsqb where xn = ? and xq =? and xh=? and pjjszgh=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getPjjszgh(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_dekt_shlcszb where lx='js' ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_dekt_jspjglsqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}

	public HashMap<String, String> getJspjxx(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_dekt_jspjglsqb a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}

	public boolean updateJspj(JspjyyxxForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_dekt_jspjglsqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String, String> getJspjInfo(JspjyyxxForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t3.xqmc,t4.xm pjjsxm");
		sql.append(" from xg_dekt_jspjglsqb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm left join fdyxxb t4 on t1.pjjszgh=t4.zgh ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public Map<String, String> getFdyInfo(String zgh) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.zgh,b.xm,b.xb,b.csrq, trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(b.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age, b.bmdm, b.bmmc,b.lxdh" );
		sql.append(" from view_fdyxx b" ); 
		sql.append(" where b.zgh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}

	public String getfdyxm(String pjjszgh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select xm from fdyxxb where zgh=?");
		return dao.getOneRs(sql.toString(), new String[] {pjjszgh}, "xm");
	}
	
	public boolean xssqyy(String zgh, String xh, String zzshen) throws Exception{
		String insertSql = " insert into XG_XAJD_DEKT_JXYY(zgh,xh,zzshen,xssfyy,jssfty) values(?,?,?,1,'')";
		return dao.runUpdate(insertSql, new String[]{zgh, xh, zzshen});
	}

	public boolean deladd(String zgh, String xh) throws Exception{
		String insertSql = " delete from XG_XAJD_DEKT_JXYY where zgh = ? and xh = ?";
		return dao.runUpdate(insertSql, new String[]{zgh, xh});
	}
	
	public HashMap<String, String> getJspjyyxx(JspjyyxxForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_XAJD_DEKT_JXYY where sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	public boolean szdwSzSave(String jssfty, String jshfxx, String sqid) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_XAJD_DEKT_JXYY set jssfty=?  ");
		params.add(jssfty);
		sql.append(" , jshfxx = ? ");
		params.add(jshfxx);
		sql.append(" where sqid = ? ");
		params.add(sqid);
		DAO dao = DAO.getInstance();
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}
}
