package xgxt.jygl.comman;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 就业管理DAO</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-13</p>
 */
public class JyglDAO extends DAO{

	DAO dao = DAO.getInstance();
	
	
	/**
	 * 就业协议表学生信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getJyxyStuInfo(String xh) {
		String sql = "select * from jygl_xsjbxxb where xsxh = ?";
		String[] outCol = new String[] { "zymc", "name", "xsxh", "xymc",
				"xslb", "nd", "bynd", "xysbh", "xxmc","dwmc","id","xb","bjmc" };
		
		return dao.getMap(sql, new String[] {xh}, outCol);
	}
	
	
	/**
	 * 查询某学生所在班的辅导员
	 * @param xh
	 * @return
	 * @throws SQLException 
	 */
	public String[] getFdy(String xh) throws SQLException {
		
		String sql = "select * from fdybjb where bjdm = (select bjdm from view_xsjbxx where xh = ?)";
		
		return dao.getArray(sql, new String[] {xh}, "zgh");
	}
	
	
	/**
	 * 下拉列表
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		
		if ("bbyy".equalsIgnoreCase(lx)) {
			dm = new String[] { "遗失", "更换" };
		} else if ("sxcl".equalsIgnoreCase(lx)) {
			dm = new String[] { "刊登遗失启事的报纸", "原推荐表" };
		} else if ("shzt".equals(lx)) {
			dm = new String[] { "未审核", "通过","不通过","需重审","退回"};
		} else if ("shjg".equals(lx)) {
			dm = new String[] { "通过", "不通过"};
		} else if ("isNot".equals(lx)) {
			dm = new String[] { "是", "否" };
		} else if ("yxbys".equals(lx)) {
			dm = new String[] { "省级", "校级" };
		} else if ("jyweb".equals(lx)) {
			dm = new String[] { "就业新闻", "通知公告","就业政策","生源信息","就业创业技巧指导" };
		} else if("xb".equals(lx)){
			dm = new String[] { "男女不限", "男", "女"};
		} else if ("gzxz".equals(lx)) {
			dm = new String[] {"全职","兼职"};
		} else if ("jyxys".equals(lx)) {
			dm = new String[] {"已领取","未领取"};
		}
		return dao.arrayToList(dm, dm);
	}
	
	
	/***
	 * 当前时间
	 * @return
	 */
	public String getNow() {
		
		String sql = "select to_char(sysdate,'yyyymmdd') now from dual";
		
		return dao.getOneRs(sql, new String[] {}, "now");
	}
	
	
	/**
	 * 毕业生批量上报
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	public boolean byssb(String[] xh,String sbr,String sbnd) throws SQLException {
		
		String[] sqlArr = new String[xh.length];
		
		for ( int i = 0 ; i < xh.length ; i ++) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("insert into jy_bysxxb ");
			sb.append("(xxmc,xxdm,xh,xm,xymc,zymc,bjmc,xydm,zydm,bjdm,sfzh,xbdm,");
			sb.append("csrq,mzdm,zzmm,qq,lxdh,rxnf,bynf,sjhm,yzbh,gbzy,gbzydm,sfzz,");
			sb.append("sfsf,sfdl,xz,zyfx,ksh,nj,sbrZgh,sbnd,sydshen,sydshi,sydxian)");
			sb.append("(select (select xxmc from xtszb) xxmc,");
			sb.append("(select xxdm from xtszb) xxdm,");
			sb.append("a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,");
			sb.append("case when a.xb='男' then '1' when a.xb='女' then '2' else a.xb end xb,");
			sb.append("a.csrq,a.mz,a.zzmm,a.qqhm,a.lxdh,replace(a.RXRQ,'-'),replace(a.byny,'-'),a.sjhm,a.jtyb,");
			sb.append("(select jygl_zymc from dmk_zydmdzb where jwc_zydm = a.zydm) jygl_zymc,");
			sb.append("(select jygl_zydm from dmk_zydmdzb where jwc_zydm = a.zydm) jygl_zydm,");
			sb.append("nvl(sfzz,'否'),nvl(sfsf,'否'),nvl(sfdl,'否'),xz,zyfx,ksh,nj,'");
			sb.append(sbr);
			sb.append("','");
			sb.append(sbnd);
			sb.append("',substr(syd,0,instr(syd,'/')-1),substr(syd,INSTR(syd,'/',1,1)+1,INSTR(syd,'/',1,1)-1) ");
			sb.append(",substr(syd,INSTR(syd,'/',1,2)+1,INSTR(syd,'/',1,2))");
			sb.append(" from ( ");
			sb.append(" select a.*,b.xymc,b.zymc,b.bjmc from(  ");
			sb.append(" select xh,xm,xydm,zydm,bjdm,sfzh,xb,csrq, ");
			sb.append(" mz,zzmm,qqhm,lxdh,rxrq,byny,sjhm,'' jtyb,'' sfzz,'' sfsf,'' sfdl, ");
			sb.append(" to_char(xz) xz,zyfx,ksh,to_char(nj) nj,syd from bks_xsjbxx a ");
			sb.append("  where not exists (select 1 from xsxxb b where a.xh = b.xh) ");
			sb.append("  union all ");
			sb.append("  select xh,xm,xydm,zydm,bjdm,sfzh,xb,csrq,mz,zzmm,qqhm, ");
			sb.append("  lxdh,rxrq,byny,sjhm,jtyb,sfzz,sfsf,sfdl,xz,zyfx,ksh,nj,syd ");
			sb.append("  from xsxxb) a left join view_njxyzybj b on a.bjdm=b.bjdm ");
			
			sb.append(") a where a.xh = '");
			sb.append(xh[i]);
			sb.append("')");
			
			sqlArr[i] = sb.toString();
			
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	
	/**
	 * 浙江省毕业生人数、浙江省各市毕业生人数、外省毕业生人数
	 * @param nj
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String[]> getBysrs(String nj) throws SQLException {
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		
		StringBuilder sb = new StringBuilder();
		//浙江省毕业生人数
		sb.append("select sum(bysrs) bysrs from (select shidm,shimc,nvl(rs,0) bysrs from");
		sb.append(" (select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc from dmk_qx ");
		sb.append(" where qxdm like'33__00' and qxdm not like '330000' order by qxdm ) a left join ");
		sb.append("(select sydshi,count(*) rs from view_jy_bysxxb where shzt='通过' and nj = '");
		sb.append(nj);
		sb.append("' group by sydshi) b");
		sb.append(" on a.shidm=b.sydshi ) order by shidm");
		sb.append("");
		String[] zjsrs = dao.getArray(sb.toString(), new String[] {}, "bysrs");
		
		sb = new StringBuilder();
		//各生源地市毕业生人数
		sb.append("select * from (select shidm,shimc,nvl(rs,0) bysrs from");
		sb.append(" (select distinct qxdm shidm,ltrim(rtrim(qxmc,'　'),'　') shimc from dmk_qx ");
		sb.append("where qxdm like'33__00' and qxdm not like '330000' order by qxdm ) a left join ");
		sb.append("(select sydshi,count(*) rs from view_jy_bysxxb where shzt='通过' ");
		sb.append("and nj = '");
		sb.append(nj);
		sb.append("' group by sydshi) b  on a.shidm=b.sydshi ) order by shidm");
		String[] bysrs = dao.getArray(sb.toString(), new String[] {}, "bysrs");
		
		
		sb = new StringBuilder();
		//非浙江省毕业生人数
		sb.append("select nvl(sum(rs),0)  bysrs from(select b.sydshi,count(*) rs ");
		sb.append("from view_jy_bysxxb b where shzt='通过' and nj = '");
		sb.append(nj);
		sb.append("' and sydshen not like'330000' group by b.sydshi)");
		String[] wsrs = dao.getArray(sb.toString(), new String[] {}, "bysrs");
		
		//毕业生总人数
		String sql = "select count(*) rs from view_jy_bysxxb where shzt='通过' and nj = '"+nj+"' and sydshen is not null";
		String[] zrs = dao.getArray(sql, new String[] {}, "rs");
		
		rs.put("zjsrs", zjsrs);
		rs.put("bysrs", bysrs);
		rs.put("wsrs", wsrs);
		rs.put("zrs", zrs);
		return rs;
	}
	
	
	/**
	 * 学院、专业
	 * @return
	 */
	public List<String[]> getXyZy(String nj) {
		String sql = "select distinct xymc,zymc,zydm from view_njxyzybj where nj=? order by xymc,zymc";
		return dao.rsToVator(sql, new String[] {nj}, new String[] {"xymc","zymc","zydm"});
	}

	
	/**
	 * 根据年级取毕业生人数
	 * @param nj
	 * @return
	 */
	public List<String[]> getBysByZy(String nj) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select a.xymc,a.zymc,a.zydm,a.bysrs,nvl(b.zjsrs,0) zjsrs from ");
		sb.append("( select a.xymc,a.zymc,a.zydm,nvl(b.bysrs,0) bysrs from ");
		sb.append("(select distinct xymc,zymc,zydm from view_njxyzybj where nj='");
		sb.append(nj);
		sb.append("') a left join ");
		sb.append("(select zydm,count(*) bysrs from view_jy_bysxxb where shzt='通过' ");
		sb.append("and nj = '");
		sb.append(nj);
		sb.append("' and sydshen is not null group by zydm) b on a.zydm=b.zydm  ) a left join ");
		sb.append(" (select zydm,count(*) zjsrs from view_jy_bysxxb where shzt='通过' ");
		sb.append(" and nj = '");
		sb.append(nj);
		sb.append("' and sydshen = '330000' group by zydm) b ");
		sb.append(" on a.zydm=b.zydm order by xymc,zymc");
		
		return dao.rsToVator(sb.toString(), new String[] {},new String[] {"bysrs","zjsrs"});
	}
	
	
	/**
	 * 按专业、生源市取毕业生人数
	 * @param zydm
	 * @param sydshi
	 * @param nj
	 * @return
	 */
	public String getBys(String zydm,String sydshi,String nj) {
		String sql = "select zydm,sydshi,count(*) rs from view_jy_bysxxb " +
				"where nj = '"+nj+"' and zydm=? and sydshi=? and shzt='通过' group by zydm,sydshi";
		String[] temp = dao.getOneRs(sql, new String[] {zydm,sydshi}, new String[] {"rs"});
		
		if (null != temp && temp.length>0) {
			return temp[0];
		} else {
			return "0";
		}
	}


	/**
	 * 获取系统设置
	 * @return
	 */
	public HashMap<String, String> getXxmc() {
		String sql = "select * from xtszb ";
		return dao.getMap(sql, new String[] {}, new String[] {"xxmc"});
	}
	
	
	/**
	 * 专业、班级个数
	 * @return
	 */
	public List<HashMap<String, String>> getXyBj(String nj) {
		return getXyBj(new String[]{nj});
	}
	
	/**
	 * 专业、班级个数
	 * @return
	 */
	public List<HashMap<String, String>> getXyBj(String[] nj) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xymc,xydm,count(*) bjs from view_njxyzybj where ");
		
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj=? ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" group by xymc,xydm order by xymc");
		
		
		return dao.getList(sql.toString(), nj, new String[] {"xymc","xydm","bjs"});
	}
	/**
	 * 获取学校中的学院
	 * 
	 * @author honglin
	 * @param nj
	 * @return
	 */
	public List<HashMap<String, String>> getXxXy(String[] nj) {
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc,xydm from ");
		sql.append("(select xymc,xydm,xxdm from jy_bysxxb where ");
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj=? ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" group by xymc,xydm,xxdm) ");
		sql.append("where xxdm='");
		sql.append(Base.xxdm);
		sql.append("'");
		return dao.getList(sql.toString(), nj, new String[] {"xymc","xydm"});
	}
	/**
	 * 获取学院中的班级 by 高职
	 * 
	 * @author honglin
	 * @param nj
	 * @return
	 */
	public List<HashMap<String, String>> getBjByGz(String[] nj,String xydm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bjmc,bjdm from ");
		sql.append("(select bjmc,bjdm,xydm from ");
		sql.append("(select * from jy_bysxxb where ");
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj=? ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		sql.append(") where (to_number(substr(bynf,1,4))-to_number(nj))<=3 ");
		sql.append("and xxdm='");
		sql.append(Base.xxdm);
		sql.append("') ");
		sql.append("where xydm='");
		sql.append(xydm);
		sql.append("' ");
		sql.append(" group by bjmc,bjdm");
		return dao.getList(sql.toString(), nj, new String[] {"bjmc","bjdm"});
	}
	
	/**
	 * 获取学院中的班级 by 本科
	 * 
	 * @author honglin
	 * @param nj
	 * @return
	 */
	public List<HashMap<String, String>> getBjByBk(String[] nj,String xydm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bjmc,bjdm from ");
		sql.append("(select bjmc,bjdm,xydm from ");
		sql.append("(select * from jy_bysxxb where ");
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj=? ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		sql.append(") where (to_number(substr(bynf,1,4))-to_number(nj))>3 ");
		sql.append("and xxdm='");
		sql.append(Base.xxdm);
		sql.append("') ");
		sql.append("where xydm='");
		sql.append(xydm);
		sql.append("' ");
		
		sql.append(" group by bjmc,bjdm");
		return dao.getList(sql.toString(), nj, new String[] {"bjmc","bjdm"});
	}
	
	/**
	 * 各班级就业率统计(浙江省版本)
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String, String>> getJyl(String xydm,String[] nj) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_jyltj where xydm=? and (");
		
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj=? ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") order by xymc,zymc,bjmc");
		
		return dao.getList(sql.toString(), StringUtils.joinStrArr(new String[]{xydm},nj), new String[] { "zymc",
				"bjmc", "zydm", "bjdm", "bysrs", "qys", "lhjys", "yps", "djys",
				"zbjys", "sxrs", "cgrs", "jgrs", "xbrs", "qxjrs", "wjys",
				"qyl", "lhjyl", "ypl", "jyl", "djyl" });
	}
	
	
	/**
	 * 各班级就业率统计(地大版本)
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String, String>> getZgddJyl(String xydm,String nj) {
		String sql = "select * from view_zgdd_jyltj where xydm=? and nj=? order by xymc,zymc,bjmc";
		return dao.getList(sql, new String[] { xydm,nj }, new String[] { "zymc",
				"bjmc", "zydm", "bjdm", "bysrs", "qyzs", "sxrs", "cgrs", "zyzy",
				"zzcy", "djys", "qyl", "sxl", "djyl", "jyl"});
	}
	
	
	/**
	 * 就业率合计(浙版)
	 * @return
	 */
	public HashMap<String, String> getJylHj(String[] nj) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select a.*, case when a.bysrs !=0 then TRUNC((a.qys/a.bysrs)*100,2)||'%' else '0.00%' end qyl, ");
		sb.append("case when a.bysrs !=0 then TRUNC((a.lhjys/a.bysrs)*100,2)||'%' else '0.00%' end lhjyl, ");
		sb.append("case when a.bysrs !=0 then TRUNC((a.yps/a.bysrs)*100,2)||'%' else '0.00%' end ypl, ");
		sb.append("case when a.bysrs !=0 then TRUNC(((a.qys+a.yps+a.lhjys)/a.bysrs)*100,2)||'%' else '0.00%' end jyl, ");
		sb.append("case when a.bysrs !=0 then TRUNC((a.djys/a.bysrs)*100,2)||'%' else '0.00%' end djyl  from ");
		sb.append("(select sum(bysrs) bysrs, sum(qys) qys, sum(qxjrs) qxjrs, sum(sxrs) sxrs,");
		sb.append("sum(cgrs)cgrs,sum(jgrs) jgrs, sum(xbrs) xbrs,sum(lhjys) lhjys,sum(yps) yps, ");
		sb.append("sum(wjys) wjys, sum(djys) djys, sum(zbjys) zbjys  from view_jyltj where ");
		
		for (int i = 0 ; i < nj.length ; i++){
			sb.append(" nj=? ");
			if (i != nj.length-1){
				sb.append(" or ");
			}
		}
		
		sb.append(") a ");
		
		return dao.getMap(sb.toString(), nj, new String[] { "bysrs", "qys",
						"qxjrs", "sxrs", "cgrs", "jgrs", "xbrs", "lhjys",
						"yps", "wjys", "djys", "zbjys", "qyl", "lhjyl", "ypl",
						"jyl", "djyl" });
	}

	
	
	/**
	 * 获取开关状态
	 * @return
	 */
	public String getKgzt() {
		String sql = "select * from jygl_csszb where xxdm=(select xxdm from xtszb) and sysdate between to_date(byssbkssj,'yyyymmdd') and to_date(byssbjssj,'yyyymmdd')+1";
		return dao.getOneRs(sql, new String[] {}, "xxdm");
	}
	
	
	/**
	 * 地大版就业率合计
	 * @param nj
	 * @return
	 */
	public HashMap<String,String> getZgddJylhj(String nj) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,");
		sql.append("case when a.bysrs !=0 then TRUNC((a.qyzs/a.bysrs)*100,2)||'%' else '0.00%' end qyl,");
		sql.append("case when a.bysrs !=0 then TRUNC((a.sxrs/a.bysrs)*100,2)||'%' else '0.00%' end sxl,");
		sql.append("case when a.bysrs !=0 then TRUNC(((a.qyzs+a.sxrs+a.cgrs+a.zyzy+a.zzcy+a.lhjys)/a.bysrs)*100,2)||'%' else '0.00%' end jyl");
		sql.append(" from (");
		sql.append("select sum(bysrs) bysrs,sum(qyzs) qyzs,sum(sxrs) sxrs,sum(cgrs) cgrs,sum(zyzy) zyzy,sum(zzcy) zzcy,sum(djys) djys,sum(lhjys) lhjys");
		sql.append(" from view_zgdd_jyltj where nj=? ) a");
	
		return dao.getMap(sql.toString(), new String[] {nj}, new String[] {"bysrs","qyzs","sxrs","cgrs","zyzy","zzcy","djys","qyl","sxl","jyl"});
	}
	
	
	/**
	 * 获取单位信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String,String> getDwInfo(String pkValue) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(mc,' ') mc,nvl(zgdw, '') zgdw,");
		sql.append("nvl(dwszd,' ') dwszd,nvl(dwdh,' ') dwdh,nvl(dwxzdm,' ') dwxzdm,");
		sql.append("nvl(hyfldm,' ') hyfldm,nvl(dwlxr,' ') dwlxr,nvl(dwyb,' ') dwyb ");
		sql.append("from dmk_yrdw where dm=?");
		sql.append("");
		sql.append("");
		sql.append("");
		return dao.getMap(sql.toString(), new String[] {pkValue}, 
				new String[] {"mc","zgdw","dwszd","dwdh","dwxzdm","hyfldm","dwlxr","dwyb"});
	}
	
	
	/**
	 * 福建工程就业学院就业率统计--本科、专科
	 * @param flg
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylForXy(String flg,String xydm,String nf) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select rownum r,a.xymc,nvl(b.xl,?) xl,nvl(b.bysrs,0) bysrs,");
		
		sb.append("nvl(b.yqyrs,0) yqyrs,");
		sb.append("nvl(b.cgrs,0) cgrs,");
		sb.append("nvl(b.sxrs,0) sxrs,");
		sb.append("nvl(b.yxjy,0) yxjy,nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,");
		sb.append("nvl(b.dfxm,0) dfxm,nvl(b.jyzs,0) jyzs,nvl(b.jyl,'0.00%') jyl,");
		sb.append("nvl(b.qyzs,0) qyzs,nvl(b.qyl,'0.00%') qyl,nvl(b.djys,0) djys,");
		sb.append("nvl(b.djyl,'0.00%')djyl,nvl(b.jyrs,0) jyrs,nvl(b.jieyl,'0.00%') jieyl,");
		sb.append("nvl(b.lxdks,0) lxdks ");
		sb.append("from (select distinct xydm,xymc from view_njxyzybj");
		if (!Base.isNull(xydm)) {
			sb.append(" where xydm='");
			sb.append(xydm);
			sb.append("' ");
		}
		
		sb.append(") a left join ");
		sb.append("(select xydm,xl,bysrs,yqyrs,cgrs,sxrs,yxjy,lhjy,yjsh,dfxm,jyzs,qyzs,djys,jyrs,");
		sb.append("to_char(case when bysrs <> 0 then round(jyzs/bysrs*100,2) else 0.00 end)||'%' jyl,");
		sb.append("to_char(case when bysrs <> 0 then round(qyzs/bysrs*100,2) else 0.00 end)||'%' qyl,");
		sb.append("to_char(case when bysrs <> 0 then round(djys/bysrs*100,2) else 0.00 end)||'%' djyl,");
		sb.append("to_char(case when bysrs <> 0 then round(jyrs/bysrs*100,2) else 0.00 end)||'%' jieyl,");
		sb.append("lxdks from (select xydm,xl,sum(bysrs) bysrs,sum(yqys) yqyrs,sum(cgrs) cgrs,sum(sxrs) sxrs,");
		sb.append("sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,");
		sb.append("nvl(sum(bysrs),0)-nvl(sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm),0) djys,");
		sb.append("sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm) jyzs,sum(yqys+sxrs+dfxm) qyzs,");
		sb.append("sum(jyrs) jyrs,sum(lxdks)lxdks  from (");
		sb.append("select xydm,xl,bysrs,");
		sb.append("nvl(b.yqys,0) yqys,nvl(b.cgrs,0) cgrs,nvl(b.sxrs,0) sxrs,");
		sb.append("nvl(b.yxjy,0) yxjy,nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,");
		sb.append("nvl(b.dfxm,0) dfxm,nvl(b.djys,0) djys,");
		sb.append("(select count(*) from view_jy_jyxy where xydm=b.xydm and xl like b.xl||'%' and xxsh='通过' and sfjy='是'  and bynf=b.bynf) jyrs,");
		sb.append("(select count(*) from view_jy_bysxxb where xydm=b.xydm and xl like b.xl||'%' and shzt='通过' and sflxdk='是' and bynf=b.bynf) lxdks ");
		sb.append("from (");
		sb.append(" select a.bysrs,a.xydm,a.bynf,a.xl,b.djys,b.yqys,b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,b.dfxm,b.dxwp,b.zbjy from ");
		sb.append("(select count(*) bysrs,xydm,xl,");
		sb.append("bynf from (select xydm,zydm,bjdm,shzt,bynf,");
		sb.append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科' else xl end xl from view_jy_bysxxb) where shzt='通过' and bynf=? ");
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
		}
		
		sb.append("group by xydm,xl,bynf) a");
		sb.append(" left join  (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,");
		sb.append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xl,xydm,bynf from view_fjgc_jytj");
		sb.append(" group by xydm, xl, bynf) b on a.xydm=b.xydm and a.xl=b.xl and a.bynf=b.bynf");
		sb.append(") b");
		sb.append(")  group by xydm,xl ");
		sb.append(") where xl=?) b on a.xydm=b.xydm");
		
		return dao.rsToVator(sb.toString(), new String[] { flg, nf, flg },
				new String[] { "r","xymc", "xl", "bysrs","yqyrs","cgrs","sxrs", "yxjy", "lhjy",
						"yjsh", "dfxm", "jyzs", "jyl", "qyzs", "qyl", "djys",
						"djyl", "jyrs", "jieyl", "lxdks" });
	}
	
	
	/**
	 * 福建工程就业学院就业率统计--小计
	 * @param flg
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylForXy(String xydm,String nf) {
		StringBuilder sb = new StringBuilder();
		StringBuilder query = new StringBuilder();
		if (!Base.isNull(xydm)) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("' ");
		}
		
		sb.append("select rownum r,a.*,");
		sb.append("case when a.bysrs > 0 then to_char(round(a.jyzs/a.bysrs*100,2))||'%' else '0.00%' end jyl,");
		sb.append("case when a.bysrs > 0 then to_char(round(a.djys/a.bysrs*100,2))||'%' else '0.00%' end djyl,");
		sb.append("case when a.bysrs > 0 then to_char(round(a.qyzs/a.bysrs*100,2))||'%' else '0.00%' end qyl,");
		sb.append("case when a.bysrs > 0 then to_char(round(a.jyrs/a.bysrs*100,2))||'%' else '0.00%' end jieyl ");
		sb.append("from (select a.xymc,'小计' xl,nvl(b.bysrs,0) bysrs, ");
		
		sb.append("nvl(b.yqys,0) yqyrs,nvl(b.cgrs,0) cgrs,");
		
		sb.append("nvl(b.sxrs,0) sxrs, nvl(b.yxjy,0) yxjy,nvl(b.lhjy,0) lhjy,");
		sb.append("nvl(b.yjsh,0) yjsh,nvl(b.dfxm,0) dfxm,nvl(b.jyzs,0) jyzs,");
		sb.append("nvl(b.qyzs,0) qyzs,nvl(b.djys,0) djys,nvl(b.jyrs,0) jyrs,");
		sb.append("nvl(b.lxdks,0) lxdks ");
		sb.append("from (select distinct xydm,xymc from view_njxyzybj where 1=1");
		sb.append(query);
		sb.append(") a left join ( select xydm,");
		sb.append("sum(bysrs) bysrs,");
		sb.append("(select count(*) from view_jy_jyxy where xydm=a.xydm ");
		sb.append(" and (xl like '本科%' or xl like '专科%') and xxsh='通过' and sfjy='是'  and bynf=?) jyrs,");
		sb.append("(select count(*) from view_jy_bysxxb where xydm=a.xydm ");
		sb.append(" and (xl like '本科%' or xl like '专科%') and shzt='通过' and sflxdk='是'  and bynf=?) lxdks,");
		sb.append("nvl(sum(bysrs),0)-nvl(sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm),0) djys,");
		sb.append("sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,");
		sb.append("sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm, ");
		sb.append("sum(dxwp) dxwp,sum(zbjy) zbjy,sum(sxrs+dfxm+yqys) qyzs,");
		sb.append("sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm) jyzs from (");
		sb.append("select a.bysrs,a.xydm,a.bynf,a.xl,b.djys,b.yqys,b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,b.dfxm,b.dxwp,b.zbjy from ");
		sb.append("(select count(*) bysrs,xydm,xl,bynf from (select xydm,zydm,bjdm,shzt,bynf,");
		sb.append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科' else xl end xl from view_jy_bysxxb) where shzt='通过' ");
		sb.append(query);
		sb.append(" and bynf=? and (xl like '本科%' or xl like '专科%') group by xydm,xl,bynf) a ");
		sb.append(" left join (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,");
		sb.append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,");
		sb.append("sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xl,xydm,bynf  ");
		sb.append("from view_fjgc_jytj ");
		sb.append("group by xl, xydm, bynf) b on a.xydm=b.xydm and a.xl=b.xl and a.bynf=b.bynf ) a");
		sb.append(" group by xydm ) b on a.xydm=b.xydm ) a");
		
		return dao.rsToVator(sb.toString(), new String[] { nf,nf,nf }, new String[] {
				"r", "xymc", "xl", "bysrs","yqyrs","cgrs", "sxrs", "yxjy", "lhjy", "yjsh",
				"dfxm", "jyzs", "jyl", "qyzs", "qyl", "djys", "djyl", "jyrs",
				"jieyl", "lxdks" });
	}
	
	
	/**
	 * 福建工程就业学院就业率统计--总计
	 * @param flg
	 * @param nf
	 * @return
	 */
	public String[]  getJylTjForXy(String xydm,String nf) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select a.*,");
		sb.append(" case when a.bysrs > 0 then to_char(round(a.jyzs/a.bysrs*100,2))||'%' else '0.00%' end jyl,");
		sb.append(" case when a.bysrs > 0 then to_char(round(a.djys/a.bysrs*100,2))||'%' else '0.00%' end djyl,");
		sb.append(" case when a.bysrs > 0 then to_char(round(a.qyzs/a.bysrs*100,2))||'%' else '0.00%' end qyl,");
		sb.append(" case when a.bysrs > 0 then to_char(round(a.jyrs/a.bysrs*100,2))||'%' else '0.00%' end jieyl ");
		sb.append("from (select nvl(sum(bysrs),0) bysrs,nvl(sum(jyrs),0) jyrs,nvl(sum(lxdks),0) lxdks,");
		sb.append("nvl(sum(djys),0) djys,nvl(sum(yqys),0) yqys,nvl(sum(cgrs),0) cgrs,nvl(sum(sxrs),0) sxrs,");
		sb.append("nvl(sum(yxjy),0) yxjy, nvl(sum(lhjy),0) lhjy,nvl(sum(dfxm),0) dfxm,nvl(sum(dxwp),0) dxwp,");
		sb.append("nvl(sum(zbjy),0) zbjy,nvl(sum(qyzs),0) qyzs,nvl(sum(jyzs),0) jyzs,nvl(sum(yjsh),0) yjsh from (select xydm,");
		sb.append(" sum(bysrs) bysrs,");
		sb.append("(select count(*) from view_jy_jyxy where xydm=a.xydm and ");
		sb.append("(xl like '本科%' or xl like '专科%') and xxsh='通过' and sfjy='是'  and bynf=?) jyrs,");
		sb.append("(select count(*) from view_jy_bysxxb where xydm=a.xydm and ");
		sb.append("(xl like '本科%' or xl like '专科%') and shzt='通过' and sflxdk='是'  and bynf=?) lxdks,");
		sb.append("nvl(sum(bysrs),0)-nvl(sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm),0) djys,sum(yqys) yqys,");
		sb.append("sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,");
		sb.append("sum(dxwp) dxwp,sum(zbjy) zbjy,sum(sxrs+dfxm+yqys) qyzs,");
		sb.append("sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm) jyzs ");
		sb.append("from (select a.bysrs,a.xydm,a.bynf,a.xl,b.djys,b.yqys,");
		sb.append("b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,b.dfxm,b.dxwp,b.zbjy from  ");
		sb.append("(select count(*) bysrs,xydm,xl,bynf from (select xydm,zydm,bjdm,shzt,bynf,");
		sb.append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科' else xl end xl from view_jy_bysxxb) where shzt='通过' ");
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
		}
		sb.append(" and bynf=? and (xl like '本科%' or xl like '专科%') group by xydm,xl,bynf) a ");
		sb.append(" left join (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,");
		sb.append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,");
		sb.append("sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xl,xydm,bynf ");
		sb.append("from view_fjgc_jytj ");
		sb.append("group by xl, xydm, bynf) b on a.xydm=b.xydm and a.xl=b.xl and a.bynf=b.bynf ) a");
		sb.append(" group by xydm) ) a");
		
		return dao.getOneRs(sb.toString(), new String[] { nf,nf,nf }, new String[] {
				"bysrs","yqys","cgrs", "sxrs", "yxjy", "lhjy", "yjsh", "dfxm", "jyzs", "jyl",
				"qyzs", "qyl", "djys", "djyl", "jyrs", "jieyl", "lxdks" });
	}
	
	
	/**
	 * 福建工程分院就业率统计本、专科小计
	 * @param xydm
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylTjForXyByXl(String xydm, String nf) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select b.xl,nvl(b.yqyrs,0) yqyrs,nvl(b.cgrs,0) cgrs,");
		sb.append("nvl(b.sxrs,0) sxrs,nvl(b.yxjy,0) yxjy,");
		sb.append("nvl(b.bysrs,0) bysrs,nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,");
		sb.append("nvl(b.dfxm,0) dfxm,nvl(b.jyzs,0) jyzs,nvl(b.jyl,'0.00%') jyl,");
		sb.append("nvl(b.qyzs,0) qyzs,nvl(b.qyl,'0.00%') qyl,nvl(b.djys,0) djys,");
		sb.append("nvl(b.djyl,'0.00%')djyl,nvl(b.jyrs,0) jyrs,nvl(b.jieyl,'0.00%') jieyl,");
		sb.append("nvl(b.lxdks,0) lxdks from (select xl,bysrs,yqyrs, cgrs,sxrs,yxjy,lhjy,yjsh,");
		sb.append("dfxm,jyzs,qyzs,djys,jyrs,lxdks,");
		sb.append("to_char(case when bysrs <> 0 then round(jyzs/bysrs*100,2) else 0.00 end)||'%' jyl,");
		sb.append("to_char(case when bysrs <> 0 then round(qyzs/bysrs*100,2) else 0.00 end)||'%' qyl,");
		sb.append("to_char(case when bysrs <> 0 then round(djys/bysrs*100,2) else 0.00 end)||'%' djyl,");
		sb.append("to_char(case when bysrs <> 0 then round(jyrs/bysrs*100,2) else 0.00 end)||'%' jieyl ");
		sb.append("from (select xl, sum(bysrs) bysrs,sum(yqys) yqyrs,sum(cgrs) cgrs,");
		sb.append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,");
		sb.append("nvl(sum(bysrs),0)-nvl(sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm),0) djys,");
		sb.append("sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm) jyzs,");
		sb.append("sum(yqys+sxrs+dfxm) qyzs,sum(jyrs) jyrs,sum(lxdks)lxdks  ");
		sb.append("from (select xydm,xl,bysrs, ");
		sb.append("nvl(b.yqys,0) yqys,nvl(b.cgrs,0) cgrs,nvl(b.sxrs,0) sxrs,nvl(b.yxjy,0) yxjy,");
		sb.append("nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,nvl(b.dfxm,0) dfxm,nvl(b.djys,0) djys,");
		sb.append("(select count(*) from view_jy_jyxy where xydm=b.xydm and xl like b.xl||'%' and xxsh='通过' and sfjy='是' and bynf=?) jyrs,");
		sb.append("(select count(*) from view_jy_bysxxb where xydm=b.xydm and xl like b.xl||'%' and shzt='通过' and sflxdk='是' and bynf=?) lxdks ");	
		sb.append("from (");
		sb.append("select a.bysrs,a.xydm,a.bynf,a.xl,b.djys,b.yqys,b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,b.dfxm,b.dxwp,b.zbjy from ");
		sb.append("(select count(*) bysrs,xydm,xl,");
		sb.append("bynf from (select xydm,zydm,bjdm,shzt,bynf,case when xl like '本科%' then '本科' when xl like '专科%' then '专科' else xl end xl from view_jy_bysxxb) where shzt='通过' and bynf=? ");
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("' ");
		}
		sb.append(" group by xydm,xl,bynf) a");
		sb.append(" left join (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,");
		sb.append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xl,xydm,bynf ");
		sb.append("from view_fjgc_jytj where bynf = ? ");
		sb.append(" group by xydm, xl, bynf) b on a.xydm=b.xydm and a.xl=b.xl and a.bynf=b.bynf");
		sb.append(" ) b");
		sb.append(" )  group by xl ) where xl='本科' or xl='专科') b");
		
		return dao.rsToVator(sb.toString(), new String[] { nf,nf, nf, nf },
				new String[] { "xl", "bysrs", "yqyrs","cgrs","sxrs", "yxjy", "lhjy", "yjsh",
						"dfxm", "jyzs", "jyl", "qyzs", "qyl", "djys", "djyl",
						"jyrs", "jieyl", "lxdks" });

	}
	
	
	/**
	 * 福建工程就业专业就业率统计--本科、专科
	 * @param flg
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylForZy(String flg,String bzk,JyglForm model) {
		
		StringBuilder query = new StringBuilder();
		if (!Base.isNull(model.getXydm())) {
			query.append(" and xydm='");
			query.append(model.getXydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getZydm())) {
			query.append(" and zydm='");
			query.append(model.getZydm());
			query.append("' ");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from (select a.xymc,a.zymc,b.xl,nvl(b.bysrs, 0) bysrs,")
		  .append("nvl(b.sxrs, 0) sxrs,nvl(b.yxjy, 0) yxjy,nvl(b.lhjy, 0) lhjy,")
		  .append("nvl(b.yjsh, 0) yjsh,nvl(b.yqys, 0) yqys,nvl(b.dfxm, 0) dfxm,")
		  .append("nvl(b.jyzs, 0) jyzs,nvl(b.jyl, '0.00%') jyl,nvl(b.qyzs, 0) qyzs,")
		  .append("nvl(b.qyl, '0.00%') qyl,nvl(b.djys, 0) djys,nvl(b.djyl, '0.00%') djyl,")
		  .append("nvl(b.jyrs, 0) jyrs,nvl(b.jieyl, '0.00%') jieyl,nvl(b.cgrs, 0) cgrs,")
		  .append("nvl(b.lxdks, 0) lxdks from (select distinct xydm, xymc, zydm, zymc ")
		  .append("from view_njxyzybj where 1 = 1 ")
		  .append(query)
		  .append(") a left join (select zydm,xl,bysrs,sxrs,yxjy,lhjy,yjsh,dfxm,")
		  .append("jyzs,qyzs,djys,jyrs,yqys,cgrs,")
		  .append("to_char(case when bysrs <> 0 then round(jyzs / bysrs * 100, 2) else 0.00 end) || '%' jyl,")
		  .append("to_char(case when bysrs <> 0 then round(qyzs / bysrs * 100, 2) else 0.00 end) || '%' qyl,")
		  .append("to_char(case when bysrs <> 0 then round(djys / bysrs * 100, 2) else 0.00 end) || '%' djyl,")
		  .append("to_char(case when bysrs <> 0 then round(jyrs / bysrs * 100, 2) else 0.00 end) || '%' jieyl,")
		  .append("lxdks from (select zydm,xl,sum(bysrs) bysrs,sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,")
		  .append("sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,")
		  .append("nvl(sum(bysrs),0)-nvl(sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm),0) djys,")
		  .append("sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm) jyzs,")
		  .append("sum(yqys + sxrs + dfxm) qyzs,sum(jyrs) jyrs,sum(lxdks) lxdks ")
		  .append("from (select  a.zydm,a.xl,a.bysrs,nvl(b.yqys, 0) yqys,nvl(b.cgrs, 0) cgrs,")
		  .append("nvl(b.sxrs, 0) sxrs,nvl(b.yxjy, 0) yxjy,nvl(b.lhjy, 0) lhjy,")
		  .append("nvl(b.yjsh, 0) yjsh,nvl(b.dfxm, 0) dfxm,nvl(b.djys, 0) djys,")
		  .append("(select count(*) from view_jy_jyxy where zydm = a.zydm ")
		  .append("and xl like a.xl||'%' and xxsh = '通过' and sfwy = '是' ")
		  .append("and bynf = a.bynf) jyrs,(select count(*) from view_jy_bysxxb ")
		  .append("where zydm = a.zydm and xl like a.xl||'%' and shzt = '通过' ")
		  .append("and sflxdk = '是' and bynf = a.bynf) lxdks from (select count(*) bysrs,")
		  .append("xydm,zydm,xl,bynf from ")
		  //view_jy_bysxxb 毕业生信息表，数据源
		  .append("(select xydm,xymc,zydm,bjdm,shzt,bynf,")
		  .append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科' when xl like '专升本%' then '专升本' else xl end xl from view_jy_bysxxb) where shzt = '通过' and bynf = ? ")
		  .append("and (xl like '本科%' or xl like '专科%' or xl like '专升本%') ")
	      .append("group by xydm,zydm, xl, bynf) a  left join (select sum(djys) djys,")
	      .append("sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,")
	      .append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,")
	      //view_fjgc_jytj 基础统计源头
	      .append("sum(zbjy) zbjy,xl,xydm,zydm,bynf from view_fjgc_jytj  ")
	      .append("group by xl, xydm,zydm, bynf)b on a.xydm=b.xydm and a.zydm=b.zydm and a.bynf=b.bynf and a.xl=b.xl ")
	      .append(") b group by zydm, xl) ) b on a.zydm = b.zydm ")
	      .append("order by xymc, zymc) where xl >' ' ");
	      
		//软件学院和校本部区分
		if ("rjxy".equals(flg)) {
			sb.append(" and xymc='软件学院' ");
		} else {
			sb.append(" and xymc<>'软件学院' ");
		}
		
		//本专科区分
		if ("bks".equals(bzk)) {
			sb.append(" and (xl like '本科%' or xl like '专升本%') ");
		} else if ("zks".equals(bzk)) {
			sb.append(" and xl like '专科%'");
		}
		
		sb.append(" order by xymc,zymc,xl");
		
		
		return dao.rsToVator(sb.toString(), new String[] { model.getNf()},
						new String[] { "xymc", "zymc", "xl", "bysrs", "yqys",
								"cgrs", "sxrs", "yxjy", "lhjy", "yjsh", "dfxm",
								"jyzs", "jyl", "qyzs", "qyl", "djys", "djyl",
								"jyrs", "jieyl", "lxdks" });
	}
	
	
	/**
	 * 福建工程就业专业就业率统计--小计
	 * @param flg
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylForZy(JyglForm model,String flg,String bzk) {
		StringBuilder sb = new StringBuilder();
		StringBuilder query = new StringBuilder();
		String nf = model.getNf();

		if (!Base.isNull(model.getXydm())) {
			query.append(" and xydm='");
			query.append(model.getXydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getZydm())) {
			query.append(" and zydm='");
			query.append(model.getZydm());
			query.append("' ");
		}
		
		
		sb.append("select a.*,")
		  .append("case when a.bysrs > 0 then to_char(round(a.jyzs / a.bysrs * 100, 2)) || '%' else '0.00%' end jyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.djys / a.bysrs * 100, 2)) || '%' else '0.00%' end djyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.qyzs / a.bysrs * 100, 2)) || '%' else '0.00%' end qyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.jyrs / a.bysrs * 100, 2)) || '%' else '0.00%' end jieyl ")
		  .append("from (select xymc,'小计' zymc,'' xl,sum(bysrs) bysrs,")
		  .append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yqys) yqys,")
		  .append("sum(yjsh) yjsh,sum(dfxm) dfxm,sum(jyzs) jyzs,sum(cgrs) cgrs,")
		  .append("sum(qyzs) qyzs,sum(djys) djys,sum(jyrs) jyrs,sum(lxdks) lxdks ")
		  .append("from (select  a.* from (select a.xymc,b.xl,nvl(b.bysrs, 0) bysrs,")
		  .append("nvl(b.sxrs, 0) sxrs,nvl(b.yxjy, 0) yxjy,nvl(b.lhjy, 0) lhjy,")
		  .append("nvl(b.yqys, 0) yqys,nvl(b.yjsh, 0) yjsh,nvl(b.dfxm, 0) dfxm,")
		  .append("nvl(b.jyzs, 0) jyzs,nvl(b.cgrs, 0) cgrs,nvl(b.qyzs, 0) qyzs,")
		  .append("nvl(b.djys, 0) djys,nvl(b.jyrs, 0) jyrs,nvl(b.lxdks, 0) lxdks ")
		  .append(" from (select distinct xydm, xymc from view_njxyzybj where 1 = 1 ")
		  .append(query)
		  .append(") a left join (select xydm,xl,(select count(*) from view_jy_jyxy ")
		  .append("where xydm = a.xydm and xl like a.xl||'%' ")
		  .append("and xxsh = '通过' and sfwy = '是' and bynf = ? ) jyrs,")
		  .append("(select count(*) from view_jy_bysxxb  where xydm = a.xydm ")
		  .append("and xl like a.xl||'%'")
		  .append("and shzt = '通过' and sflxdk = '是'  and bynf = ? ) lxdks,sum(bysrs) bysrs,")
		  .append("nvl(sum(bysrs),0)-nvl(sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm),0) djys,")
		  .append("sum(yqys) yqys,sum(cgrs) cgrs,")
		  .append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,")
		  .append("sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,")
		  .append("sum(sxrs + dfxm + yqys) qyzs,")
		  .append("sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm) jyzs ")
		  .append("from (select a.bysrs,a.xydm,a.zydm,a.bynf,a.xl,b.djys,b.yqys,")
		  .append("b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,b.dfxm,b.dxwp,b.zbjy ")
		  .append("from ( select count(*) bysrs, xydm, zydm,")
		  .append("xl, bynf ")
		  //view_jy_bysxxb 数据源
		  .append("from (select xydm,xymc,zydm,bjdm,shzt,bynf,")
		  .append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科' when xl like '专升本%' then '专升本' else xl end xl from view_jy_bysxxb) where shzt = '通过' and bynf = ? ");
		  
			//本、专科区分
			if ("bks".equals(bzk)) {
				sb.append(" and (xl like '本科%' or xl like '专升本%')");
			} else if ("zks".equals(bzk)) {
				sb.append(" and xl like '专科%'");
			} else {
				sb.append(" and (xl like '本科%' or xl like '专科%' or xl like '专升本%') ");
			}
		  
		  sb.append(" group by xydm,zydm, xl, bynf) a left join ")
		  .append("(select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,")
		  .append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,")
		  .append("sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,")
		  //统计源头 view_fjgc_jytj
		  .append("sum(zbjy) zbjy,xl,xydm,zydm,bynf from view_fjgc_jytj ")
		  .append("where 1 = 1 ")
		  .append(query)
		  .append(" group by xl, xydm, zydm, bynf ")
		  .append(") b on a.xydm=b.xydm and a.zydm=b.zydm and a.xl=b.xl and a.bynf=b.bynf) a ")
		  .append(" group by xydm,xl) b on a.xydm = b.xydm) a ")
		  .append("order by xymc ) where xl>' ' group by xymc) a");
		
		
		//软件学院和校本部区分
		if ("rjxy".equals(flg)) {
			sb.append(" where xymc='软件学院' ");
		} else {
			sb.append(" where xymc<>'软件学院' ");
		}
		
		
		
		sb.append(" order by xymc,zymc,xl");
		
		return dao.rsToVator(sb.toString(), new String[] { nf,nf, nf },
						new String[] { "xymc", "zymc", "xl", "bysrs", "yqys",
								"cgrs", "sxrs", "yxjy", "lhjy", "yjsh", "dfxm",
								"jyzs", "jyl", "qyzs", "qyl", "djys", "djyl",
								"jyrs", "jieyl", "lxdks" });
	}
	
	
	/**
	 * 福建工程就业专业就业率统计--总计
	 * @param flg
	 * @param nf
	 * @return
	 */
	public String[]  getJylTjForZy(JyglForm model,String flg,String bzk) {
		String nf = model.getNf();
		StringBuilder sb = new StringBuilder();
		StringBuilder query = new StringBuilder();
		
		if (!Base.isNull(model.getXydm())) {
			query.append(" and xydm='");
			query.append(model.getXydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getZydm())) {
			query.append(" and zydm='");
			query.append(model.getZydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getBjdm())) {
			query.append(" and bjdm='");
			query.append(model.getBjdm());
			query.append("' ");
		}
		
		sb.append("select a.*,")
		  .append("case when a.bysrs > 0 then to_char(round(a.jyzs / a.bysrs * 100, 2)) || '%' else '0.00%' end jyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.djys / a.bysrs * 100, 2)) || '%' else '0.00%' end djyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.qyzs / a.bysrs * 100, 2)) || '%' else '0.00%' end qyl,")
		  .append("case when a.bysrs > 0 then to_char(round(a.jyrs / a.bysrs * 100, 2)) || '%' else '0.00%' end jieyl ")
		  .append("from (select sum(bysrs) bysrs,sum(jyrs) jyrs,sum(lxdks) lxdks,sum(djys) djys,sum(yqys) yqys,")
		  .append("sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(dfxm) dfxm,sum(dxwp) dxwp,")
		  .append("sum(zbjy) zbjy,sum(qyzs) qyzs,sum(jyzs) jyzs,sum(yjsh) yjsh from (select bjdm,")
		  .append("(select count(*) from view_jy_jyxy where bjdm = a.bjdm  and ");

		 if ("bks".equals(bzk)) {
			 sb.append("xl like '本科%' ");
		 } else if("zks".equals(bzk)) {
			 sb.append("xl like '专科%'");
		 } else {
			 sb.append("(xl like '专科%' or xl like '本科%' or xl like '专升本%')");
		 }
		
		sb.append(" and xxsh = '通过' and sfwy = '是' and bynf = ? ")
		  .append(query)
		  .append(") jyrs,")
		  .append("(select count(*) from view_jy_bysxxb where bjdm = a.bjdm and ");
		
		 if ("bks".equals(bzk)) {
			 sb.append("xl like '本科%' ");
		 } else if("zks".equals(bzk)) {
			 sb.append("xl like '专科%'");
		 } else {
			 sb.append("(xl like '专科%' or xl like '本科%' or xl like '专升本%')");
		 }
		 
		sb.append(" and shzt = '通过' and sflxdk = '是' and bynf = ? ")
		  .append(query)
		  .append(") lxdks,sum(bysrs) bysrs,")
		  .append("nvl(sum(bysrs),0)-nvl(sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm),0) djys,")
		  .append("sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,")
		  .append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,")
		  .append(" sum(sxrs + dfxm + yqys) qyzs,sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm) jyzs ")
		  .append("from (select a.bysrs,a.xydm,a.zydm,a.bjdm,a.xl,a.bynf,b.djys,b.yqys,b.cgrs,b.sxrs,b.yxjy,b.lhjy,b.yjsh,")
		  .append("b.dfxm,b.dxwp,b.zbjy from (select count(*) bysrs,xydm,zydm,bjdm,")
		  .append("xl,bynf from ")
		  //数据源 view_jy_bysxxb
		  .append("(select xydm,zydm,xymc,bjdm,shzt,bynf,case when xl like '本科%' then '本科' when xl like '专科%' then '专科' when xl like '专升本%' then '专升本' else xl end xl from view_jy_bysxxb) where shzt = '通过' ");
		  
		if ("xbb".equals(flg)) {
			sb.append("and xymc <> '软件学院' ");
		}  
		  
		//本、专科区分
		if ("bks".equals(bzk)) {
			sb.append("and (xl like '本科%' or xl like '专升本%') ");
		} else if ("zks".equals(bzk)) {
			sb.append("and xl like '专科%' ");
		} else {
			sb.append("and (xl like '本科%' or xl like '专科%' or xl like '专升本%') ");
		}
		
		sb.append(" group by xydm,zydm,bjdm, xl, bynf) a ")
		  .append("left join (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs, sum(yxjy) yxjy,")
		  .append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xl,xydm,zydm,bjdm,bynf ")
		  //view_fjgc_jytj 统计源头
		  .append("from view_fjgc_jytj where 1 = 1 ")
		  .append(query)
		  .append("group by xl, xydm,zydm,bjdm, bynf )b on a.xydm=b.xydm and a.zydm=b.zydm and a.bjdm=b.bjdm and a.xl=b.xl and a.bynf=b.bynf ) a where bynf = ? ")
		  .append("and (xl='本科' or xl='专科' or xl='专升本') group by bjdm)) a  where 1=1 ");
		
		
		
		return dao.getOneRs(sb.toString(), new String[] { nf,nf,nf }, new String[] {
				"bysrs", "yqys","cgrs","sxrs", "yxjy", "lhjy", "yjsh", "dfxm", "jyzs", "jyl",
				"qyzs", "qyl", "djys", "djyl", "jyrs", "jieyl", "lxdks" });
	}

	
	/**
	 * 福建工程就业班级就业率统计--本科、专科
	 * @param flg
	 * @param nf
	 * @return
	 */
	public List<String[]> getJylForBj(String flg,JyglForm model) {
		
		StringBuilder query = new StringBuilder();
		if (!Base.isNull(model.getXydm())) {
			query.append(" and xydm='");
			query.append(model.getXydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getZydm())) {
			query.append(" and zydm='");
			query.append(model.getZydm());
			query.append("' ");
		}
		
		if (!Base.isNull(model.getBjdm())) {
			query.append(" and bjdm='");
			query.append(model.getBjdm());
			query.append("' ");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from (select a.xymc,a.zymc,a.bjmc,b.xl,nvl(b.bysrs, 0) bysrs,")
		  .append("nvl(b.sxrs, 0) sxrs,nvl(b.yxjy, 0) yxjy,nvl(b.lhjy, 0) lhjy,nvl(b.yjsh, 0) yjsh,")
		  .append("nvl(b.yqys, 0) yqys,nvl(b.dfxm, 0) dfxm,nvl(b.jyzs, 0) jyzs,nvl(b.jyl, '0.00%') jyl,")
		  .append("nvl(b.qyzs, 0) qyzs,nvl(b.qyl, '0.00%') qyl,nvl(b.djys, 0) djys,nvl(b.djyl, '0.00%') djyl,")
		  .append("nvl(b.jyrs, 0) jyrs,nvl(b.jieyl, '0.00%') jieyl,nvl(b.cgrs, 0) cgrs,nvl(b.lxdks, 0) lxdks ")
		  .append("from (select distinct xydm, xymc, zydm, zymc,bjdm,bjmc from view_njxyzybj where 1 = 1 ")
		  .append(query)
		  .append(") a left join (select bjdm,xl,bysrs,sxrs,yxjy,lhjy,yjsh,dfxm,jyzs,qyzs,djys,jyrs,yqys,cgrs,")
		  .append("to_char(case when bysrs <> 0 then round(jyzs / bysrs * 100, 2) else 0.00 end) || '%' jyl,")
		  .append("to_char(case when bysrs <> 0 then round(qyzs / bysrs * 100, 2) else 0.00 end) || '%' qyl,")
		  .append("to_char(case when bysrs <> 0 then round(djys / bysrs * 100, 2) else 0.00 end) || '%' djyl,")
		  .append("to_char(case when bysrs <> 0 then round(jyrs / bysrs * 100, 2) else 0.00 end) || '%' jieyl,")
		  .append("lxdks from (select bjdm,xl,sum(bysrs) bysrs,sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,")
		  .append("sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,")
		  .append("nvl(sum(bysrs),0)-nvl(sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm),0) djys,")
		  .append("sum(yqys + cgrs + sxrs + yxjy + lhjy + yjsh + dfxm) jyzs,sum(yqys + sxrs + dfxm) qyzs,")
		  .append("sum(jyrs) jyrs,sum(lxdks) lxdks from (select a.bjdm,a.xl,a.bysrs,nvl(b.yqys, 0) yqys,")
		  .append("nvl(b.cgrs, 0) cgrs,nvl(b.sxrs, 0) sxrs,nvl(b.yxjy, 0) yxjy,nvl(b.lhjy, 0) lhjy,")
		  .append("nvl(b.yjsh, 0) yjsh,nvl(b.dfxm, 0) dfxm,nvl(b.djys, 0) djys,(select count(*) from view_jy_jyxy ")
		  .append("where bjdm = a.bjdm and xl like a.xl || '%' and xxsh = '通过' and sfwy = '是' ")
		  .append("and bynf = a.bynf) jyrs,(select count(*) from view_jy_bysxxb where bjdm = a.bjdm  ")
		  .append("and xl like a.xl || '%' and shzt = '通过' and sflxdk = '是' and bynf = a.bynf) lxdks ")
		  .append(" from (select count(*) bysrs,xydm,zydm,bjdm,xl,")
		  .append("bynf from (select xydm,zydm,xymc,bjdm,shzt,bynf,")
		  .append("case when xl like '本科%' then '本科' when xl like '专科%' then '专科'")
		  .append(" when xl like '专升本%' then '专升本' else xl end xl from view_jy_bysxxb) where shzt = '通过' and bynf = ?  ")
		  .append("and (xl like '本科%' or xl like '专科%' or xl like '专升本%') ")
		  .append("group by xydm, zydm,bjdm, xl, bynf) a left join (select sum(djys) djys,")
		  .append("sum(yqys) yqys,sum(cgrs) cgrs,sum(sxrs) sxrs,sum(yxjy) yxjy,")
		  .append("sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(dxwp) dxwp,")
		  .append("sum(zbjy) zbjy,xl,xydm,zydm,bjdm,bynf from view_fjgc_jytj ")
		  .append("group by xl, xydm, zydm, bjdm,bynf) b on a.xydm = b.xydm and a.zydm = b.zydm ")
		  .append("and a.bjdm = b.bjdm and a.bynf = b.bynf and a.xl = b.xl) b ")
		  .append("group by bjdm, xl)) b on a.bjdm = b.bjdm order by xymc, zymc) ")
		  .append("where xl > ' '");
		 
		//软件学院和校本部区分
		if ("rjxy".equals(flg)) {
			sb.append(" and xymc='软件学院' ");
		} else {
			sb.append(" and xymc<>'软件学院' ");
		}
		sb.append(" order by xymc,zymc,xl");
		
		
		
		/*sb.append("select rownum r,a.xymc,a.zymc,a.bjmc,nvl(b.xl,?) xl,nvl(b.bysrs,0) bysrs,nvl(b.sxrs,0) sxrs,");
		sb.append("nvl(b.yxjy,0) yxjy,nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,nvl(b.yqys,0) yqys,");
		sb.append("nvl(b.dfxm,0) dfxm,nvl(b.jyzs,0) jyzs,nvl(b.jyl,'0.00%') jyl,");
		sb.append("nvl(b.qyzs,0) qyzs,nvl(b.qyl,'0.00%') qyl,nvl(b.djys,0) djys,");
		sb.append("nvl(b.djyl,'0.00%')djyl,nvl(b.jyrs,0) jyrs,nvl(b.jieyl,'0.00%') jieyl,");
		sb.append("nvl(b.cgrs,0) cgrs,nvl(b.lxdks,0) lxdks ");
		sb.append("from (select distinct xydm,xymc,zydm,zymc,bjdm,bjmc from view_njxyzybj where 1=1 ");
		sb.append(query);
		sb.append(") a left join ");
		sb.append("(select bjdm,xl,bysrs,sxrs,yxjy,lhjy,yjsh,dfxm,jyzs,qyzs,djys,jyrs,yqys,cgrs,");
		sb.append("to_char(case when bysrs !=0 then round(jyzs/bysrs*100,2) else 0.00 end)||'%' jyl,");
		sb.append("to_char(case when bysrs !=0 then round(qyzs/bysrs*100,2) else 0.00 end)||'%' qyl,");
		sb.append("to_char(case when bysrs !=0 then round(djys/bysrs*100,2) else 0.00 end)||'%' djyl,");
		sb.append("to_char(case when bysrs !=0 then round(jyrs/bysrs*100,2) else 0.00 end)||'%' jieyl,");
		sb.append("lxdks from (select bjdm,xl,sum(bysrs) bysrs,sum(sxrs) sxrs,sum(yqys) yqys,");
		sb.append("sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,sum(dfxm) dfxm,sum(djys) djys,");
		sb.append("sum(yqys+cgrs+sxrs+yxjy+lhjy+yjsh+dfxm) jyzs,sum(yqys+sxrs+dfxm) qyzs,");
		sb.append("sum(jyrs) jyrs,sum(cgrs) cgrs,sum(lxdks)lxdks  from (");
		sb.append("select bjdm,case when xz=4 then '本科' ");
		sb.append("when xz=3 then '专科' when xz=2 then '专升本' end xl,");
		sb.append("(select count(*) from view_jy_bysxxb where bjdm=b.bjdm and xz=b.xz and shzt='通过' and bynf=b.bynf) bysrs,");
		sb.append("nvl(b.yqys,0) yqys,nvl(b.cgrs,0) cgrs,nvl(b.sxrs,0) sxrs,");
		sb.append("nvl(b.yxjy,0) yxjy,nvl(b.lhjy,0) lhjy,nvl(b.yjsh,0) yjsh,");
		sb.append("nvl(b.dfxm,0) dfxm,nvl(b.djys,0) djys,");
		sb.append("(select count(*) from view_jy_jyxy where bjdm=b.bjdm and xz=b.xz and xxsh='通过' and sfwy='是'  and bynf=b.bynf) jyrs,");
		sb.append("(select count(*) from view_jy_bysxxb where bjdm=b.bjdm and xz=b.xz and shzt='通过' and sflxdk='是' and bynf=b.bynf) lxdks ");
		sb.append("from (select sum(djys) djys,sum(yqys) yqys,sum(cgrs) cgrs, ");
		sb.append("sum(sxrs) sxrs,sum(yxjy) yxjy,sum(lhjy) lhjy,sum(yjsh) yjsh,");
		sb.append("sum(dfxm) dfxm,sum(dxwp) dxwp,sum(zbjy) zbjy,xz,bjdm,bynf ");		
		sb.append(" from view_fjgc_jytj where bynf=? ");
		sb.append(query);
		sb.append(" group by xz,bjdm,bynf) b");
		sb.append(")  group by bjdm,xl ");
		sb.append(") where xl=?) b on a.bjdm=b.bjdm order by xymc,zymc,bjmc");*/
		
		return dao.rsToVator(sb.toString(), new String[] { model.getNf()},
						new String[] { "xymc", "zymc","bjmc", "xl", "bysrs", "yqys",
								"cgrs", "sxrs", "yxjy", "lhjy", "yjsh", "dfxm",
								"jyzs", "jyl", "qyzs", "qyl", "djys", "djyl",
								"jyrs", "jieyl", "lxdks" });
	}
	
	
	/**
	 * 查询含自定义字段数据
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getZdyQueryList(String tableName,
			JyglForm myModel, String[] colList, String[] zdyCol,String realTable,
			String[] pkKey) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm","ydw","gpdw","ecfpdw"};
		String[] queryLikeList = new String[]{"xh","xm"};
		String[] inputList = new String[] {};
		String query = "";
		if (queryList != null) {
			MakeQuery makeQuery = new MakeQuery();
			makeQuery.makeQuery(queryList, queryLikeList, myModel);
			inputList = makeQuery.getInputList();
			query = makeQuery.getQueryString();
			
		}
		int size = colList.length - 1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for (int i = 0; i < (size); i++) {
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(" rownum r,a.");
		sqlBuffer.append(colList[size]);

		for (int i = 0; i < zdyCol.length; i++) {
			sqlBuffer.append(",(select bcnr from ty_bdsz_bcnr where tabname = '");
			sqlBuffer.append(realTable);
			sqlBuffer.append("' and zdid = '");
			sqlBuffer.append(zdyCol[i]);
			sqlBuffer.append("' and zbid = ");
			for (int j = 0; j < pkKey.length; j++) {
				sqlBuffer.append("a.");
				sqlBuffer.append(pkKey[j]);
				if (j != pkKey.length - 1) {
					sqlBuffer.append("||");
				}
			}
			sqlBuffer.append(") ");
			sqlBuffer.append(zdyCol[i]);
		}
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" a ");
		String[] zcolList = new String[colList.length + zdyCol.length];
		for (int i = 0; i < colList.length; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colList.length + i] = zdyCol[i];
		}
		return CommonQueryDAO.commonQuery(sqlBuffer.toString(), query,
				inputList, zcolList, myModel);
	}


	
	/**
	 * 档案户口批量上报
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected boolean dahkPlsb(String[] pkValues) throws SQLException {
		
		String[] sqlArr = new String[pkValues.length];
		
		for (int i = 0 ; i < pkValues.length ; i++ ) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into jygl_dahkb (xh,datddw,hkqydz) select ");
			sql.append(" a.xh,");
			sql.append("case when exists (select 1 from jy_jyxy where xh=a.xh) ");
			sql.append("then (select datddw from jy_jyxy where xh=a.xh) else '' end datddw,");
			sql.append("case when exists (select 1 from jy_jyxy where xh=a.xh) then ");
			sql.append("(select hkqydz from jy_jyxy where xh=a.xh) else '' end hkqydz ");
			sql.append(" from jy_bysxxb a ");
			sql.append(" where xh='");
			sql.append(pkValues[i]);
			sql.append("'");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}


	/**
	 * 综测总分及排名
	 * @param xh
	 * @param xn
	 * @return
	 */
	protected HashMap<String, String> getZcfPm(String xh, String xn,String xq){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(fs,0) fs,pm,'总分' mc from pjpy_zhszcpb where ");
		sql.append("jb='1' and xh=? and xn=? ");
		
		if (!Base.isNull(xq)) {
			sql.append(" and xq='");
			sql.append(xq);
			sql.append("'");
		}
		
		return dao.getMap(sql.toString(), new String[] {xh,xn}, new String[] {"fs","pm"});
	}


	/**
	 * 优秀毕业生统计数据
	 * @param xydm
	 * @param sqlb
	 * @return
	 */
	protected List<String[]> getYxbysData(String xydm, String sqlb){
		String sql = "select zymc,xm,xb,xl,sydq,zzmmmc,sfzh from view_jygl_yxbys where xxsh='通过' and xydm=? and sqlb=?";
		
		return dao.rsToVator(sql, new String[] {xydm,sqlb}, new String[] {"zymc","xm","xb","xl","sydq","zzmmmc","sfzh"});
	}

	
	/**
	 * 获取导出的全部列
	 * @param tableName
	 * @return
	 */
	protected String[] getColumn(String tableName) {
		String sql = "select * from "+tableName;
		
		return dao.getColumnName(sql);
	}
	
	
	
	/**
	 * 福建工程-辅导员带班就业情况明细
	 * @param model
	 * @return
	 */
	protected List<String[]> getFdyJymx(JyglForm model,String flg){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select zgh,(select xm from yhb where yhm=a.zgh) xm,count(*) dbrs,")
		   .append("sum(yqy) yqyrs,sum(cg) cgrs,sum(sx) sxrs,sum(yxjy) yxjyrs,")
		   .append("sum(lhjy) lhjyrs,sum(jsh) jshrs,sum(gjdfxm) dfxmrs,")
		   .append("sum(yqy+cg+sx+yxjy+lhjy+jsh+gjdfxm) jyzs,sum(lxdk) lxdks,sum(yqy+sx+gjdfxm) qyzs,")
		   .append("count(*)-nvl(sum(yqy+cg+sx+yxjy+lhjy+jsh+gjdfxm),0) djyrs,sum(jy) jyrs,")
		   .append("round(sum(yqy+sx+gjdfxm)/count(*)*100,2)||'%' qyl,")
		   .append("round((count(*)-nvl(sum(yqy+cg+sx+yxjy+lhjy+jsh+gjdfxm),0))/count(*)*100,2)||'%' djyl,")
		   .append("round(sum(lxdk)/count(*)*100,2)||'%' lxdkl, ")
		   .append(" round(sum(jy)/count(*)*100,2)||'%' jieyl,")
		   .append("round(sum(yqy+cg+sx+yxjy+lhjy+jsh+gjdfxm)/count(*)*100,2)||'%' jyl ")
		   .append(" from(")
		   .append("select a.zgh,b.xh, d.jybz,d.jybzmc,")
		   .append("case when d.jybzmc='已签约' then 1 else 0 end yqy,")
		   .append("case when d.jybzmc='出国' then 1 else 0 end cg,")
		   .append("case when d.jybzmc='升学' then 1 else 0 end sx,")
		   .append("case when d.jybzmc='意向就业' then 1 else 0 end yxjy,")
		   .append("case when d.jybzmc='灵活就业' then 1 else 0 end lhjy,")
		   .append("case when d.jybzmc='接收函' then 1 else 0 end jsh,")
		   .append("case when d.jybzmc='国家地方项目' then 1 else 0 end gjdfxm,")
		   .append("case when d.jybzmc='待就业' then 1 else 0 end djy,")
		   .append("case when d.sfjy='是' then 1 else 0 end jy,")
		   .append("case when d.sflxdk='是' then 1 else 0 end lxdk ")
		   .append(" from (")
		   .append("select * from (select zgh,bjdm,")
		   .append("case when exists (select 1 from view_jy_bysxxb where bynf=? and shzt='通过'  ");
		
		if ("xbb".equals(flg)) {
			sql.append(" and xymc <> '软件学院' ");
		} else {
			sql.append(" and xymc = '软件学院' ");
		}
		
		sql.append("and bjdm = a.bjdm ) then '是' else '否' end sfbyb from fdybjb a) where sfbyb = '是') a ")
		   .append("left join view_xsbfxx b on a.bjdm = b.bjdm ")
		   .append("left join view_jy_jyxy d on b.xh=d.xh) a group by zgh ");
		
		
		return dao.rsToVator(sql.toString(), new String[] {model.getNf()}, 
				new String[] {"xm","dbrs","yqyrs","cgrs","sxrs","yxjyrs",
							  "lhjyrs","jshrs","dfxmrs","jyzs","jyl","qyzs",
							  "qyl","djyrs","djyl","jyrs","jieyl","lxdks"});
	}
	
	
	
	/**
	 * 根据统计项目的代码取相应备注信息
	 * 供DWR调用
	 * @param xmdm
	 * @return
	 */
	public String getXmbz(String xmdm) {
		String sql = "select * from jygl_jyxgtj where tjdm=?";
		
		return dao.getOneRs(sql, new String[] {xmdm}, "xmbz");
	}
	
	
	/**
	 * 修改项目备注
	 * @param xmdm
	 * @return
	 * @throws Exception 
	 */
	protected boolean updateXmbz(String xmdm,String xmbz) throws Exception {
		
		String sql = "update jygl_jyxgtj set xmbz=? where tjdm=?";
		
		return dao.runUpdate(sql, new String[] {xmbz,xmdm});
	}
	
	
	
	/**
	 *单位性质统计 
	 *循环列输出
	 * @param xl
	 * @return
	 */
	protected String[] getDwxzTj(String xl,List<HashMap<String, String>> dwxzList){
		
		if (null != dwxzList && dwxzList.size() > 0) {
			
			String[] output = new String[dwxzList.size()];
			StringBuilder sb = new StringBuilder();
			
			sb.append("select ");
			
			for (int i = 0 ; i < dwxzList.size() ; i++) {
				sb.append("case when a.dwxzdm='")
				  .append(dwxzList.get(i).get("dwxzdm"))
				  .append("' then sum(b.rs) else 0 end dwxz")
				  .append(dwxzList.get(i).get("dwxzdm"))
				  .append(",");
				
				output[i] = "dwxz"+dwxzList.get(i).get("dwxzdm");
			}
			sb.deleteCharAt(sb.length()-1);
			
			sb.append(" from dmk_dwxz a left join ")
			  .append("(select count(*) rs,dwxz from view_jy_jyxy ")
			  .append("where xxsh='通过' and xl like'")
			  .append(xl)
			  .append("%' ")
			  .append("group by dwxz) ")
			  .append("b on a.dwxzdm=b.dwxz group by a.dwxzdm ")
			  .append("");
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select ");
			
			for (int i = 0 ; i < output.length ; i++) {
				sql.append("sum(")
				   .append(output[i])
				   .append(") ")
				   .append(output[i])
				   .append(",");
			}
			
			sql.deleteCharAt(sql.length()-1);
			sql.append(" from (")
			   .append(sb)
			   .append(")");
			
			return dao.getOneRs(sql.toString(), new String[] {}, output);
		} else {
			return new String[] {};
		}
		
	}


	/**
	 * 单位性质
	 * @return
	 */
	protected List<HashMap<String, String>> getDwxzList() {
		String sql = "select * from dmk_dwxz";
		List<HashMap<String,String>> dwxzList = dao.getList(sql, new String[] {}, new String[] {"dwxzdm","dwxz"});
		return dwxzList;
	}
	
	
	/**
	 * 根据学历获取毕业生人数
	 * @param xl
	 * @return
	 */
	protected String getBysRs(String xl) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(*) rs from view_jy_bysxxb where shzt='通过' ");
		
		if (!Base.isNull(xl)) {
			sql.append(" and xl like '")
			   .append(xl)
			   .append("%'");
		}
		
		
		return dao.getOneRs(sql.toString(), new String[] {}, "rs");
	}
	
	
	/**
	 * 行业分类
	 * @return
	 */
	protected List<HashMap<String, String>> getHyflList() {
		String sql = "select * from dmk_hyfl";
		List<HashMap<String,String>> dwxzList = dao.getList(sql, new String[] {}, new String[] {"hyfldm","hyfl"});
		return dwxzList;
	}
	
	
	/**
	 *行业分类统计 
	 *循环列输出
	 * @param xl
	 * @return
	 */
	protected String[] getHyflTj(List<HashMap<String, String>> hyflList){
		
		if (null != hyflList && hyflList.size() > 0) {
			
			String[] output = new String[hyflList.size()];
			StringBuilder sb = new StringBuilder();
			
			sb.append("select ");
			
			for (int i = 0 ; i < hyflList.size() ; i++) {
				sb.append("case when a.hyfldm='")
				  .append(hyflList.get(i).get("hyfldm"))
				  .append("' then sum(b.rs) else 0 end hyfl")
				  .append(hyflList.get(i).get("hyfldm"))
				  .append(",");
				
				output[i] = "hyfl"+hyflList.get(i).get("hyfldm");
			}
			sb.deleteCharAt(sb.length()-1);
			
			sb.append(" from dmk_hyfl a left join ")
			  .append("(select count(*) rs,hyfldm from view_jy_jyxy ")
			  .append("where xxsh='通过' ")
			  .append("group by hyfldm) ")
			  .append("b on a.hyfldm=b.hyfldm group by a.hyfldm ")
			  .append("");
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select ");
			
			for (int i = 0 ; i < output.length ; i++) {
				sql.append("sum(")
				   .append(output[i])
				   .append(") ")
				   .append(output[i])
				   .append(",");
			}
			
			sql.deleteCharAt(sql.length()-1);
			sql.append(" from (")
			   .append(sb)
			   .append(")");
			
			return dao.getOneRs(sql.toString(), new String[] {}, output);
		} else {
			return new String[] {};
		}
		
	}
	
	
	
	/**
	 * 就业协议书领取保存
	 * @return
	 * @throws SQLException 
	 */
	protected boolean saveJyxylq(String[] xh,String[] lqqk,String[] xysbh,String userName) throws SQLException {
		
		String[] sqlArr = new String[xh.length*2];
		int n = 0;
		
		for (int i = 0 ; i < xh.length ; i++) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from jygl_jyxyslqdjb where xh='")
			   .append(xh[i])
			   .append("' ");
			sqlArr[n] = sql.toString();
			n++;
			
			sql = new StringBuilder();
			sql.append("insert into jygl_jyxyslqdjb (xh,lqqk,jyxybh) values ('")
			   .append(xh[i])
			   .append("','")
			   .append(lqqk[i])
			   .append("','")
			   .append(xysbh[i])
			   .append("')");
			sqlArr[n++] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	
	
	/**
	 *获取就业管理模块参数设置信息 
	 * @return
	 */
	protected HashMap<String,String> getCssz(){
		
		String sql = "select * from jygl_csszb where xxdm=(select xxdm from xtszb)";
		
		return dao.getMapNotOut(sql, new String[] {});
	}
	
	
	
	/**
	 * 批量生成就业协议编号
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public boolean saveXybh(int count) throws SQLException {
		
		String[] sqlArr = new String[count];
		
		for (int i = 0 ; i < count ; i++) {
			sqlArr[i] = "insert into xg_jygl_jyxybhb (jyxybh) values ('"+StringUtils.getGuid()+"')";
		}
		
		int[] result = dao.runBatch(sqlArr);
		
		return dao.checkBatch(result);
	}
	
	
	
	/**
	 * 未使用的就业协议编号
	 * @return
	 * @throws SQLException 
	 */
	public String getWsybh() throws SQLException {
		
		String sql = "select jyxybh from xg_view_jygl_jyxybhb where sfsy='否'";
		String[] temp = dao.getArray(sql, new String[] {}, "jyxybh");
		
		return StringUtils.joinStrByArray(temp, "@");
	}
	
	
	/**
	 * 剩余未使用编号的个数
	 * @return
	 */
	public String getSybh() {
		
		String sql = "select count(*) count from xg_view_jygl_jyxybhb where sfsy='否'";
		
		return dao.getOneRs(sql, new String[] {}, "count");
	}
	
	
	
	/**
	 * 协议书补领审核通过
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	protected boolean xysblsh(String[] pkValue,HashMap<String,String> valueMap) throws SQLException {
		
		String[] wsybh = getWsybh().split("@");
		String[] sqlArr = new String[pkValue.length*2];
		
		int n=0;
		
		for (int i = 0 ; i < pkValue.length && i < wsybh.length ; i++) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("update jygl_jyxyslqdjb set jyxybh='")
			   .append(wsybh[i])
			   .append("' where jyxybh=(select yjyxybh from jygl_jyxyblsqb where xh||sqsj='")
			   .append(pkValue[i])
			   .append("')");
			
			sqlArr[n] = sql.toString();
			n++;
			sql = new StringBuilder();
			
			sql.append("update jygl_jyxyblsqb set xxsh='通过',")
			   .append("xxshr='")
			   .append(valueMap.get("xxshr"))
			   .append("',xxshsj='")
			   .append(valueMap.get("xxshsj"))
			   .append("',xxshyj='")
			   .append(valueMap.get("xxshyj"))
			   .append("',")
			   .append("newjyxybh='")
			   .append(wsybh[i])
			   .append("' where xh||sqsj='")
			   .append(pkValue[i])
			   .append("'");
			
			sqlArr[n] = sql.toString();
			n++;
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	
	
	/**
	 * 协议书补领取消审核
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	protected boolean xysblQxsh(String[] pkValue) throws SQLException {
		
		String[] sqlArr = new String[pkValue.length*2];
		
		int n=0;
		
		for (int i = 0 ; i < pkValue.length ; i++) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("update jygl_jyxyslqdjb set jyxybh=(select yjyxybh from jygl_jyxyblsqb where xh||sqsj='")
			   .append(pkValue[i])
			   .append("') where jyxybh=(select newjyxybh from jygl_jyxyblsqb where xh||sqsj='")
			   .append(pkValue[i])
			   .append("')");
			
			sqlArr[n] = sql.toString();
			n++;
			sql = new StringBuilder();
			
			sql.append("update jygl_jyxyblsqb set xxsh='未审核',xxshr='',")
			   .append("xxshsj='',xxshyj='',newjyxybh='' where xh||sqsj='")
			   .append(pkValue[i])
			   .append("'");
			
			sqlArr[n] = sql.toString();
			n++;
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	
	
	/**
	 * 协议书补领单个审核后修改协议书领取处编号
	 * @param xh
	 * @param xysbh
	 * @return
	 * @throws Exception
	 */
	public boolean updateXysbhOne(String xh, String xysbh) throws Exception {
		
		String sql = "update jygl_jyxyslqdjb set jyxybh=? where xh=?";
		
		return dao.runUpdate(sql, new String[] {xysbh,xh});
	}
	
	
	
	/**
	 * 获取已上报毕业生数
	 * @param yhm
	 * @param nd
	 * @return
	 */
	public String getYsbrsByNd(String yhm,String nd) {
		
		String sql = "select count(*) count from jy_bysxxb where sbrZgh=? and sbnd=?";
		
		return dao.getOneRs(sql, new String[] {yhm,nd}, "count");
	}
	
	
	/**
	 * 参数设置初始化
	 *
	 */
	public void initCssz() {
		String sql = "insert into jygl_csszb(xxdm,lcbh,xysbbshjb,xysbbshr) values ((select xxdm from xtszb),'1','1',',xy')";
		try {
			dao.runUpdate(sql, new String[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 设置批量审核时学校审核状态为“退回”的记录为“需重审”
	 * @param realTable
	 * @param pkKey
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean resetXxsh(String realTable,String pkKey,String[] pkValue) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update ")
		   .append(realTable)
		   .append(" set xxsh='需重审' where xxsh='退回' and ")
		   .append(pkKey)
		   .append(" in (");
			
		for (int i = 0 ; i < pkValue.length ; i++) {
			sql.append("'")
			   .append(pkValue[i])
			   .append("'");
			
			if (i==pkValue.length-1) {
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		
		return runUpdate(sql.toString(), new String[] {});
	}
	
	
	
	/**
	 * 根据用人单位代码取相关信息
	 * @param yrdwdm
	 * @return
	 */
	public HashMap<String, String> getYrdw(String yrdwdm) {
		String sql = "select * from view_dmk_yrdw a where dm=?" ;
		
		return dao.getMap(sql, new String[] {yrdwdm}, 
					new String[] {"dm","mc","dwxzdm","hyfldm","dwlxr","dwdh","dwszd","dwszdmc","dwyb"});
	}
	
	/**
	 * 根据主管部门名称模糊查找主管部门 
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getLsbmOption(String zgdwmc){

		String sql = "select zgbmdm dm,zgbm mc from dmk_zgbm where zgbm like ?||'%' and rownum<=20" ;
		
		return dao.getList(sql, new String[]{zgdwmc}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * 查找主管部门 (只取前20条)
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getLsbmOption(){

		String sql = "select zgbmdm dm,zgbm mc from dmk_zgbm where rownum<=20" ;
		
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * 根据主管单位名称模糊查找主管单位 
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getZgdwOption(String zgdwmc){

		String sql = "select * from dmk_zgdw where mc like ?||'%' and rownum<=20" ;
		
		return dao.getList(sql, new String[]{zgdwmc}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * 查找主管单位 (只取前20条)
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getZgdwOption(){

		String sql = "select * from dmk_zgdw where rownum<=20" ;
		
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 毕业生去向
	 * @param zgdwmc
	 * @return
	 */
	public HashMap<String, String> getBysqx(String xh){

		String sql = "select jydw from jygl_byqx where xsxh=? " ;
		
		return dao.getMap(sql, new String[]{xh}, new String[]{"jydw"});
	}
	
	/**
	 * 根据学号获取扩展项信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJyxyKzx(String xh){
		
		String sql=" select * from view_jy_jyxy where xh=? ";
		return dao.getMap(sql, new String[]{xh}, new String[]{"kzx1","kzx2","kzx3","kzx4","kzx5","kzx6","kzx7","kzx8","kzx9"});
		
	}
	
	/**
	 * 毕业生信息 结果查询 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getXsxxList(JyglForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		MakeQuery mQuery=new MakeQuery();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=model.getUser();
		sql.append(" select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xz, ");
		sql.append(" a.xymc,a.zymc,a.bjmc,a.pkValue,rownum r from( ");
		sql.append(" select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xz, ");
		sql.append("  b.xymc, b.zymc, b.bjmc,xh pkValue from (  ");
		sql.append(" select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm,to_char(xz)xz ");
		sql.append(" from bks_xsjbxx a where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh ) ");
		sql.append(" union all select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,xz from xsxxb a ");  
		sql.append(" where sfbys = '是')a left join view_njxyzybj b on a.bjdm=b.bjdm "); 
		sql.append(" )a ");

//		权限控制 
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		if ("xy".equals(userType)) {
			query.append("and xydm='");
			query.append(userDep);
			query.append("'  and not exists"); 
			query.append("(select 1 from jy_bysxxb b where a.xh=b.xh) ");
		}  else {
			query.append("  and not exists(select 1 from jy_bysxxb b where a.xh=b.xh) ");
		}
		
		String []colLikeList={"xh","xm"};
		String []colList={"xydm","zydm","bjdm","nj"};
		mQuery.makeQuery(colList, colLikeList, model);
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, 
				mQuery.getInputList(), new String[]{ "pkValue", "xh", "xm", "xb",
			"xz", "nj", "xymc", "zymc", "bjmc"}, model);
	}
	
	/**
	 * 上报毕业生信息  结果查询 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByssbList(JyglForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		MakeQuery mQuery=new MakeQuery();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=model.getUser();
		
		sql.append(" select rownum r,xh,xm,xz,nj,xymc,bjmc,sbr,sbsj,sbnd,xb,je from (");
		sql.append(" select a.xh,a.xm,a.xz,a.nj,a.xymc,a.bjmc,a.sbrzgh sbr,a.sbsj,a.sbnd,");
		sql.append(" xydm,zydm,bjdm,substr(bynf,0,4)je, ");
		sql.append(" (select xb from dmk_xb where xbdm=a.xbdm) xb from jy_bysxxb a)a ");
		
		
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		if ("xy".equals(userType)) {
			query.append("and xydm='");
			query.append(userDep);
			query.append("' "); 
		}  
		
		String []colLikeList={"xh","xm","sbr"};
		String []colList={"xydm","zydm","bjdm","nj","sbnd","je"};
		mQuery.makeQuery(colList, colLikeList, model);
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, 
				mQuery.getInputList(), new String[]{"xh", "xm", "xb",
			"xz", "nj", "xymc","bjmc","sbr","sbsj"}, model);
	}
	
	/**
	 * 毕业生信息确认  2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByqrList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		MakeQuery mQuery=new MakeQuery();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=model.getUser();
		
		sql.append(" select rownum r,pkValue,xh,xm,xymc,bjmc,nj,xydm,zydm,bjdm, ");
		sql.append(" xlccdm,pyfsdm,zslbdm,sydshen,sydshi,sydxian,sfzz,sfsf,sfdl, ");
		sql.append(" sfqr,sfxg,lxdk,bynf,disabled,xl,sflxdk,xsgb,isjys,je,sydq,syds,sydx,zzmmmc, ");
		sql.append(" sjhm,xyshzt,shzt from (");
		sql.append(" select rownum r,xh pkValue,xh, xm, xymc, bjmc, nj,xydm,zydm,bjdm, ");
		sql.append(" xlccdm,pyfsdm,zslbdm,sydshen,sydshi,sydxian,sfzz,sfsf,sfdl, ");
		sql.append(" sfqr,sfxg,lxdk,bynf,");
		sql.append(model.getClientColumns());
		sql.append("(select xl from dmk_xl where xldm=a.xlccdm) xl, sflxdk, xsgb,");
		sql.append("isjys, shzt,substr(a.bynf,0,4) je,");
		sql.append("(select qxmc from dmk_qx where qxdm=a.sydshen) sydq,");
		sql.append("(select qxmc from dmk_qx where qxdm=a.sydshi) syds,");
		sql.append("(select qxmc from dmk_qx where qxdm=a.sydxian) sydx,");
        sql.append("(select zzmm from dmk_zzmm where zzmmdm=a.zzmm) zzmmmc,sjhm,xyshzt  from jy_bysxxb a");
        sql.append(" )a ");

		String userType=user.getUserType();
		String userDep=user.getUserDep();
		if ("xy".equals(userType)) {
			query.append("and xydm='");
			query.append(userDep);
			query.append("' "); 
		}  
		
		String []colLikeList={"xh","xm","lxdk","xsgb"};
		String []colList={"je","xydm","zydm","bjdm","xlccdm","pyfsdm",
				"zslbdm","sydshen","sydshi","sydxian","sfzz","sfsf",
				"sfdl","sfqr","sfxg","shzt","bynf","isjys","sybdzt","xyshzt"};
		mQuery.makeQuery(colList, colLikeList, model);
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, 
				mQuery.getInputList(), colArr, model);
	}
	
	/**
	 * 就业协议审核  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxyShList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		MakeQuery mQuery=new MakeQuery();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=model.getUser();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		String flg=userType+"sh";
		sql.append(" select a.*,rownum r, ");
		if ("xysh".equals(flg)) {
			sql.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' else '' end) disabled, ");
			sql.append(" (case when xysh='通过' or xysh='不通过' then 'disabled'  when xysh='退回' then 'th' else '' end) isdis, ");
		} else if ("xxsh".equals(flg) || "adminsh".equals(flg) || "stush".equals(flg)) {
			sql.append("'' disabled,");
			sql.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' when xxsh='退回' then 'th' else '' end) isdis, ");
		}
		
		sql.append(" xh pkValue ");
		sql.append(" from(select b.xh,xm,xymc,bjmc,xz,substr(bynf,0,4)je, ");
		sql.append(" nj,xydm,zydm,bjdm,jybz,dwxz,sfdk,jyhy, ");
		sql.append(" (select nvl(mzmc,'未确定') from mzdmb where mzdm=b.mzdm) mzmc, ");
		sql.append(" (select zzmm from dmk_zzmm where zzmmdm=b.zzmm) zzmmmc,  ");
		sql.append(" (select mc from dmk_jybz where dm=jybz)jybzmc, ");
		sql.append(" (select dwxz from dmk_dwxz where dwxzdm=a.dwxz) dwxzmc,  ");
		sql.append(" (select hyfl from dmk_hyfl where hyfldm=a.jyhy) jyhymc, ");
		sql.append(" xysh,a.xyshr,xxsh,xxshr from jy_jyxy a  ");
		sql.append(" left join jy_bysxxb b on a.xh=b.xh)a ");

		if ("xy".equals(userType)) {
			query.append("and xydm='");
			query.append(userDep);
			query.append("' "); 
		}  
		
		String []colLikeList={"xh","xm","xyshr","xxshr"};
		String []colList={"nj","xydm","zydm","bjdm","jybz","dwxz","sfdk","jyhy","xysh","xxsh","je"};
		mQuery.makeQuery(colList, colLikeList, model);
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, 
				mQuery.getInputList(), colArr, model);
	}
	
	/**
	 * 就业协议审核  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxycxList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		MakeQuery mQuery=new MakeQuery();
		StringBuilder sql= new StringBuilder();
		StringBuilder query=new StringBuilder();
		User user=model.getUser();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		sql.append(" select a.*,rownum r, ");
		sql.append(" (case when xysh='通过' or xysh='不通过' then 'disabled' when xysh='退回' then 'th' else '' end) disabled, ");
		
		sql.append(" xh pkValue ");
		
		sql.append("from(select b.xh,xm,xymc,bjmc,xz,substr(bynf,0,4)je, ");
		sql.append(" nj,xydm,zydm,bjdm,jybz,dwxz,sfdk,jyhy, ");
		sql.append(" (select nvl(mzmc,'未确定') from mzdmb where mzdm=b.mzdm) mzmc, ");
		sql.append(" (select zzmm from dmk_zzmm where zzmmdm=b.zzmm) zzmmmc,  ");
		sql.append(" (select mc from dmk_jybz where dm=jybz)jybzmc, ");
		sql.append(" (select dwxz from dmk_dwxz where dwxzdm=a.dwxz) dwxzmc,  ");
		sql.append(" (select hyfl from dmk_hyfl where hyfldm=a.jyhy) jyhymc, ");
		sql.append(" xysh,a.xyshr,xxsh,xxshr from jy_jyxy a  ");
		sql.append(" left join jy_bysxxb b on a.xh=b.xh)a ");

		if ("xy".equals(userType)) {
			query.append("and xydm='");
			query.append(userDep);
			query.append("' "); 
		}  
		
		String []colLikeList={"xh","xm","xyshr","xxshr"};
		String []colList={"nj","xydm","zydm","bjdm","jybz","dwxz","sfdk","jyhy","xysh","xxsh","je"};
		mQuery.makeQuery(colList, colLikeList, model);
		return CommonQueryDAO.commonQuery(sql.toString(), mQuery.getQueryString()+query, 
				mQuery.getInputList(), colArr, model);
	}
	
	/**
	 * 获取班级总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjZrs(String bjdm){
		String sql ="select count(*) zrs from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"zrs"});		
	}
	/**
	 * 获取班级男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjBrs(String bjdm){
		String sql ="select count(*) brs from jy_bysxxb where bjdm=? and xbdm='1' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"brs"});		
	}
	/**
	 * 获取班级女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjGrs(String bjdm){
		String sql ="select count(*) grs from jy_bysxxb where bjdm=? and xbdm='2' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"grs"});		
	}
	/**
	 * 获取班级领取协议人总数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjLqxyrs(String bjdm){
		String sql ="select count(*) rs from view_jygl_jyxyslqdjb where xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	/**
	 * 获取班级签约总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjQyrs(String bjdm){
		String sql ="select count(*) rs from jy_jyxy where jybz='2' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	/**
	 * 获取班级出国总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjCgrs(String bjdm){
		String sql ="select count(*) rs from jy_jyxy where jybz='3' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	/**
	 * 获取班级深造总人数
	 * 
	 * @author honglin
	 * @date 2012-4-18
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjSzrs(String bjdm){
		String sql ="select count(*) rs from jy_jyxy where jybz='4' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	/**
	 * 获取班主任姓名
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjBzr(String bjdm){
		String sql ="select b.xm from bzrbbb a,yhb b where a.zgh=b.yhm and bjdm=? and rownum = 1";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"xm"});		
	}
	/**
	 * 获取班级特困生人数
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjTksrs(String bjdm){
		String sql ="select count(*) rs from xszz_knsb where xmdm='5002' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	/**
	 * 获取班级外省人数(非北京人)
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBjWsrs(String bjdm){
		String sql ="select count(*) rs from jy_bysxxb where sydshen<>'110000' and bjdm=? and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});		
	}
	
	/**
	 * 获取班级领取协议的学生学号
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjLqxy(String bjdm){
		String sql ="select xh rs from view_jygl_jyxyslqdjb where xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh"});		
	}
	/**
	 * 获取班级签约的学生学号
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjQy(String bjdm){
		String sql ="select xh rs from jy_jyxy where jybz='2' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh"});		
	}
	/**
	 * 获取班级出国的学生学号
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjCg(String bjdm){
		String sql ="select xh rs from jy_jyxy where jybz='3' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh"});		
	}
	/**
	 * 获取班级深造的学生学号
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjSz(String bjdm){
		String sql ="select xh rs from jy_jyxy where jybz='4' and xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getList(sql, new String[]{bjdm}, new String[]{"xh"});		
	}
	
	/**
	 * 获取签约人总数（签约数+出国+深造   去除重复项的）
	 * 
	 * @author honglin
	 * @date 2012-4-19
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getQyl(String bjdm){
		String sql ="select count(*) rs from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from jy_bysxxb where bjdm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{bjdm}, new String[]{"rs"});
	}
	/**
	 * 获取学院中班主任总人数
	 * 
	 * @author honglin
	 * @date 2012-4-20
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getBzrzrs(String xydm){
		String sql ="select count(*) rs from (select distinct zgh from (select a.zgh,a.bjdm from bzrbbb a,yhb b where a.zgh=b.yhm and a.bjdm in (select c.bjdm from jy_bysxxb c where c.xydm=? and xxdm='"+Base.xxdm+"' group by c.bjdm)))";
		return dao.getMap(sql, new String[]{xydm}, new String[]{"rs"});
	}
	/**
	 * 获取学院中专业数量
	 * 
	 * @author honglin
	 * @date 2012-4-20
	 * @param bjdm
	 * @return
	 */
	public int getZygs(String[] nj,String xydm){
		String sql ="select zymc,zydm from (select zymc,zydm,xydm from (select * from jy_bysxxb where ";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj=? ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3 and xxdm='"+Base.xxdm+"')  where xydm="+xydm+" group by zymc,zydm";
		List<HashMap<String, String>> dlist=dao.getList(sql, nj, new String[]{"zymc","zydm"});
		int d = dlist.size();
		String sql2 ="select zymc,zydm from (select zymc,zydm,xydm from (select * from jy_bysxxb where ";
		for (int i = 0 ; i < nj.length ; i++){
			sql2+=" nj=? ";
			if (i != nj.length-1){
				sql2+=" or ";
			}
		}
		sql2+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3 and xxdm='"+Base.xxdm+"')  where xydm="+xydm+" group by zymc,zydm";
		List<HashMap<String, String>> xlist=dao.getList(sql2, nj, new String[]{"zymc","zydm"});
		int x = xlist.size();
		int zh=d+x;
		return zh;
	}
	
	/**
	 * 获取毕业表中学院中的专业
	 * 
	 * @author honglin
	 * @date 2012-4-23
	 * @param nj
	 * @param jyxy
	 * @return list
	 */
	public List<HashMap<String , String>> getXyByZy(String[] nj,String[] jyxy){
		StringBuilder sql= new StringBuilder();
		sql.append(" select zydm,zymc from ");
		sql.append("(select * from (select * from jy_bysxxb where ");
		for (int i = 0 ; i < nj.length ; i++){
			sql.append(" nj='");
			sql.append(nj[i]);
			sql.append("' ");
			if (i != nj.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" ) where xxdm='");
		sql.append(Base.xxdm);
		sql.append("') where ");
		for (int i = 0 ; i < jyxy.length ; i++){
			sql.append(" xydm=? ");
			if (i != jyxy.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" group by zydm,zymc");
		return dao.getList(sql.toString(), jyxy, new String []{"zydm","zymc"});
	}
	/**
	 * 获取专业总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyZrs(String[] nj,String zydm){
		//String sql ="select count(*) zrs from jy_bysxxb where zydm=? and xxdm='"+Base.xxdm+"'";
		String sql="select count(*) zrs from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where zydm=?  and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"zrs"});		
	}
	/**
	 * 获取专业男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyBrs(String[] nj,String zydm){
		//String sql ="select count(*) brs from jy_bysxxb where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"'";
		String sql="select count(*) brs from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"brs"});		
	}
	/**
	 * 获取专业女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyGrs(String[] nj,String zydm){
		//String sql ="select count(*) grs from jy_bysxxb where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"'";
		String sql="select count(*) grs from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"grs"});		
	}
	
	/**
	 * 获取专业本科总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyBkZrs(String[] nj,String zydm){
		//String sql ="select count(*) rs from (select * from jy_bysxxb where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xxdm='"+Base.xxdm+"'";
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业本科男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyBkBrs(String[] nj,String zydm){
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业本科女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyBkGrs(String[] nj,String zydm){
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	
	/**
	 * 获取专业高职总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyGzZrs(String[] nj,String zydm){
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业高职男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyGzBrs(String[] nj,String zydm){
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业高职女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyGzGrs(String[] nj,String zydm){
		String sql ="select count(*) rs from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"'";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业就业率本科总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyJylBkZrs(String[] nj,String zydm){
		String sql ="select count(*) rs from view_jygl_jyxyslqdjb where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业就业率高职总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyJylGzZrs(String[] nj,String zydm){
		String sql ="select count(*) rs from view_jygl_jyxyslqdjb where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业签约率本科总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylBkZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业签约率本科男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylBkBZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业签约率本科女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylBkGZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))>3) where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	
	/**
	 * 获取专业签约率高职总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylGzZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业签约率高职男生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylGzBZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xbdm='1' and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
	/**
	 * 获取专业签约率高职女生总人数
	 * 
	 * @author honglin
	 * @date 2012-4-24
	 * @param zydm
	 * @return
	 */
	public HashMap<String, String> getZyQylGzGZrs(String[] nj,String zydm){
		String sql ="select count(*) rs  from (select distinct xh from jy_jyxy where jybz='2' or jybz='3' or jybz='4') where xh in (select xh from (select * from (select * from jy_bysxxb where";
		for (int i = 0 ; i < nj.length ; i++){
			sql+=" nj='"+nj[i]+"' ";
			if (i != nj.length-1){
				sql+=" or ";
			}
		}
		sql+=") where (to_number(substr(bynf,1,4))-to_number(nj))<=3) where zydm=? and xbdm='2' and xxdm='"+Base.xxdm+"')";
		return dao.getMap(sql, new String[]{zydm}, new String[]{"rs"});		
	}
}
