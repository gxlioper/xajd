/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JspjsqDao extends SuperDAOImpl<JspjsqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(JspjsqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JspjsqForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String pjlx = t.getPjlx();
		if(pjlx.equals("dpj")){
			sql.append("select * from ");
			sql.append(" (select * from ( select a.*,b.xssfyy,b.jssfty, ");
			sql.append(" (case when a.zzshen is null or a.zzshen = '0' then '否' ");
			sql.append(" when a.zzshen='1'and xssfyy is null then '是(未预约)' ");
			sql.append(" when a.zzshen='1'and xssfyy is not null and jssfty is null then '是(已预约)' ");
			sql.append(" when a.zzshen='1'and xssfyy is not null and jssfty='1' then '是(已同意)' ");
			sql.append(" when a.zzshen='1'and xssfyy is not null and jssfty='0' then '是(未同意)' ");
			sql.append(" else '否' end) yyzt ");
			sql.append(" from (select * from view_fdyxx where sfbl='1' and zgh not in (select pjjszgh from xg_dekt_jspjglsqb where xh='"+user.getUserName()+"' )) a ");
			sql.append("  left join XG_XAJD_DEKT_JXYY b on b.zgh = a.zgh and xh = '"+user.getUserName()+"') where yyzt in ('是(已同意)','否'))");
			
		}else if (pjlx.equals("ypj")){
			sql.append(" select a.*,(select splc from xg_dekt_shlcszb where lx='js') splcidnew");
			sql.append(" from ( select t1.*,t1.pjsj xpjsj,t1.pjjszgh zgh,");
			sql.append(" t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
			sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.xymc,t2.zymc,t2.nj,t3.xqmc,t4.xm pjjsxm,t4.bmmc, t4.jslbdm , t4.jslbmc ,");
			sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
			sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_dekt_jspjglsqb ");
			sql.append(" t1  left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm left join view_fdyxx t4 on t4.zgh=t1.pjjszgh where t1.xh = '"+user.getUserName()+"') a  ");
		}else if (pjlx.equals("dyy")){
			sql.append("select * from (");
			sql.append(" select * from ( select a.*,b.xssfyy,b.jssfty, " +
					" case when xssfyy is null then '是(未预约)' " +
					" when xssfyy is not null and jssfty is null then '是(已预约)'"+
					" when xssfyy is not null and jssfty='1' then '是(已同意)'"+
					" when xssfyy is not null and jssfty='0' then '是(未同意)'"+
					" else '否'"+
					" end yyzt "+
					" from (select* from view_fdyxx where sfbl='1' and zgh not in (select pjjszgh from xg_dekt_jspjglsqb "
					+ "where xh='"+user.getUserName()+"' )) a ");
			sql.append("  left join XG_XAJD_DEKT_JXYY b on b.zgh = a.zgh and xh = '"+user.getUserName()+"' where  (a.zzshen = '1'))");
			sql.append("  where yyzt in ('是(未预约)','是(已预约)','是(未同意)'))");
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

	public boolean isExist(JspjsqForm model) {
		String sql = "select count(1) num from xg_dekt_jspjglsqb where xn = ? and xq =? and xh=? and pjjszgh=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getPjjszgh()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(JspjsqForm model) {
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

	public boolean updateJspj(JspjsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_dekt_jspjglsqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String, String> getJspjInfo(JspjsqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t3.xqmc,t4.xm pjjsxm , t5.rstjmc ");
		sql.append(" from xg_dekt_jspjglsqb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm left join fdyxxb t4 on t1.pjjszgh=t4.zgh left join XG_DEKT_RSTJ t5 on t1.ylzd1 = t5.rstjdm ");
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
	public boolean jspjSzSave(String zgh, String xh, String zzshen, String xsyyxx) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_XAJD_DEKT_JXYY(zgh,xh,zzshen,xssfyy,jssfty,xsyyxx,jshfxx) values(? ");
		params.add(zgh);
		sql.append(" ,?");
		params.add(xh);
		sql.append(" ,?");
		params.add(zzshen);
		sql.append(" ,1 ,'', ?");
		params.add(xsyyxx);
		sql.append(" ,'')");
		DAO dao = DAO.getInstance();
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}
	public List<HashMap<String, String>> getRstjList(){
		String sql = "select * from XG_DEKT_RSTJ";
		return dao.getListNotOut(sql, new String[]{});
	}
}
