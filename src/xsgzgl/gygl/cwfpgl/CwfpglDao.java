package xsgzgl.gygl.cwfpgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewDAO;

public class CwfpglDao extends GyglNewDAO {

	/**
	 * 获取床位分配统计数据
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList(CwfpglForm myForm, HttpServletRequest request) throws Exception {

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 权限控制
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");

		// 学院或年级学院人数统计数据集
		// 楼栋统计【学院】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,b.qsxb ");

		// 寝室统计【学院】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,b.qsxb ");

		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum r,a.* from (select xydm || '_' || nj || '_' || xb pk, nj, xydm, xb, zrs as num,  yrz as rzrs, zrs - yrz as wrzrs, ");
		sql.append(" zcws as cws, yycws, zcws - yycws as sycws, ");
		sql.append(" xymc , sumlddm ,sumqsh from ( select a.*, nvl(b.num, 0) as zrs, nvl(c.num, 0) as yrz, ");
		sql.append(" nvl(d.num, 0) as zcws, nvl(d.yycws, 0) as yycws ");
		sql.append(" , nvl(e.sumlddm,0) as sumlddm ");
		sql.append(" , nvl(f.sumqsh,0) as sumqsh ");
		sql.append(" from (select *  from (select nj, xydm, xymc from view_njxyzybj_all t ");
		sql.append("         group by nj, xydm, xymc) a  left join dmk_xb on 1 = 1) a  ");
		sql.append(" left join (select nj, xydm, xb, count(*) num  from view_xsjbxx a ");
		sql.append("     group by nj, xydm, xb) b ");
		sql.append("     on a.nj = b.nj   and a.xb = b.xb and a.xydm = b.xydm ");
		sql.append(" left join (select nj, xydm, xb, count(*) num  from (select a.* from view_xsjbxx a, xg_gygl_new_cwxxb b where a.xh = b.xh) ");
		sql.append("            group by nj, xydm, xb) c ");
		sql.append("     on b.nj = c.nj  and b.xb = c.xb and b.xydm = c.xydm ");
		sql.append(" left join (select nj,  xydm, qsxb,  count(*) num, sum(case when xh is not null then 1  end) yycws ");
		sql.append("             from (select a.*, b.qsxb  from xg_gygl_new_cwxxb a  left join xg_gygl_new_qsxxb b on a.lddm = b.lddm  and a.qsh = b.qsh) ");
		sql.append("             group by nj, xydm, qsxb) d ");
		sql.append("     on a.xydm = d.xydm  and a.nj = d.nj and a.xb = d.qsxb ");
		// 楼栋统计
		sql.append("  left join ( " + sql_ldsum.toString() + ") e");
		sql.append("     on a.xydm = e.xydm  and a.nj = e.nj and a.xb = e.qsxb ");

		// 寝室统计
		sql.append("  left join ( " + sql_qssum.toString() + ") f");
		sql.append("     on a.xydm = f.xydm  and a.nj = f.nj and a.xb = f.qsxb ");

		sql.append(" )  order by nj desc, xydm, xb) a ");
		sql.append(" where 1=1");

		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "cws", "sumqsh", "sumlddm" };

		return commonQuery(sql.toString(), searchTj + searchTjByUser, inputV, colList, myForm);
	}

	/**
	 * 统计楼栋分配信息
	 * 
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --查询楼栋基本信息
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		sql = "select count(1) qss,count(case when xydm is not null or nj is not null then 1 end) yfp_qss, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss "
				+ "from xg_gygl_new_qsxxb where lddm=?";// --楼栋寝室统计信息
		HashMap<String, String> ldqstjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldqstjxx);

		sql = "select count(1) cws,count(case when xydm is not null or nj is not null then 1 end) yfp_cws, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_cws "
				+ "from xg_gygl_new_cwxxb where lddm=?";// --楼栋床位统计信息
		HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldcwtjxx);

		request.setAttribute("ldjbxx", ldjbxx);

		sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bxy_cws,b.qss,b.yfp_qss,b.wfp_qss from " + "( " + "select ch,chmc,count(1) cws, "
				+ "count(case when xydm is not null or nj is not null then 1 end) yfp_cws, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_cws, "
				+ "count(case when xydm=? and nj=? and qsxb=? then 1 end) bxy_cws " + "from view_xg_gygl_new_cwxx where sfbl='否' and lddm=? and qsxb=? " + " group by ch,chmc order by to_number(ch) "
				+ // --楼层床位统计信息
				") a, " + "( " + "select ch,count(1) qss, " + "count(case when xydm is not null or nj is not null then 1 end) yfp_qss, "
				+ "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss " + "from xg_gygl_new_qsxxb where lddm=? group by ch order by to_number(ch) " + // --楼层统计信息
				") b where a.ch=b.ch(+) ";
		List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { myForm.getXydm(), myForm.getNj(), myForm.getXb(), lddm, xb, lddm });
		request.setAttribute("ldlcxx", ldlcxx);

		String cwzt_sql = "";
		if ("未分配".equals(cwzt)) {
			cwzt_sql = " and a.xydm is null ";
		} else if ("已分配".equals(cwzt)) {
			cwzt_sql = " and a.xydm is not null ";
		}
		//辽宁机电职业技术学院 床位号可以有汉字出现_个性化
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "order by to_number(ch),qsh,cwh";
		}else{
			sb = "order by to_number(ch),qsh,to_number(cwh)";
		}
		// sql="select * from view_xg_gygl_new_cwxx where lddm=? order by to_number(ch),qsh,cwh";//--楼栋楼层寝室床位信息
		sql = "select a.*,b.xm from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where a.sfbl='否' and lddm=? and qsxb=? " + cwzt_sql
				+ " "+sb+"";// --楼栋楼层寝室床位信息
		List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xb });
		request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);
	}

	/**
	 * 获取楼栋列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdList(String ldxb) {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc " + "from xg_gygl_new_ldxxb where ldxb=? or ldxb='混住'";
		return dao.getListNotOut(sql, new String[] { ldxb });
	}

	/**
	 * 获取楼栋列表(用户如果是公寓管理员，公寓管理员数据范围控制)
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdListByGyfdy(HttpServletRequest request, String ldxb,String cwzt,String xydm) {
		String gyglyQx = (String) request.getSession().getAttribute("gyglyQx");
		String username = (String) request.getSession().getAttribute("userName");
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct(lddm),ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc ");
		sql.append("from view_xg_gygl_new_qsxx where (ldxb=? or ldxb='混住')");
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql.append(" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '" + username + "')");
		}
		if("已分配".equals(cwzt)){
			sql.append(" and xydm =?");
			return dao.getListNotOut(sql.toString(), new String[] { ldxb,xydm });
		}
		else{
			return dao.getListNotOut(sql.toString(), new String[] { ldxb});
		}
		
		
		
	}

	/**
	 * 获取楼栋列表(用户如果是公寓管理员，公寓管理员数据范围控制)
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getCwfpLdListByGyfdy(HttpServletRequest request, String ldxb, String xy) {
		String gyglyQx = (String) request.getSession().getAttribute("gyglyQx");
		String username = (String) request.getSession().getAttribute("userName");

		DAO dao = DAO.getInstance();

		String sql = "select distinct(lddm),ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc " + "from view_xg_gygl_new_qsxx where (ldxb=? or ldxb='混住') and xydm =?";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '" + username + "')";
		}
		return dao.getListNotOut(sql, new String[] { ldxb, xy });
	}

	/**
	 * 保存寝室床位分配信息
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public boolean saveQscwfpxx(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] qshs = request.getParameterValues("checkbox_qsh");// 寝室号
		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号
		// String
		// primarykey_checkVal=request.getParameter("primarykey_checkVal");//主键"学院_年级_性别"
		String xydm = myForm.getXydm();// 学院
		String nj = myForm.getNj();// 年级
		String xb = myForm.getXb();// 性别

		// 保存寝室分配信息
		if (qshs != null && qshs.length > 0) {
			for (int i = 0; i < qshs.length; i++) {
				sqls.add("update xg_gygl_new_qsxxb set xydm='" + xydm + "',nj='" + nj + "' where lddm='" + lddm + "' and qsh='" + qshs[i] + "' and qsxb='" + xb + "' and xydm is null");
			}
		}
		// 保存床位分配信息（xyfp_flg：[1：学院已分配]）
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set xydm='" + xydm + "',nj='" + nj + "',xyfp_flg='1' where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='"
						+ cwhs[i].split("_")[1] + "' and xydm is null");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 取消寝室床位分配信息
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public synchronized boolean qxQscwfpxx(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] qshs = request.getParameterValues("checkbox_qsh");// 寝室号
		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号

		// 保存寝室分配信息
		if (qshs != null && qshs.length > 0) {
			for (int i = 0; i < qshs.length; i++) {
				sqls.add("update xg_gygl_new_qsxxb set xydm='',nj='' where lddm='" + lddm + "' and qsh='" + qshs[i] + "'");
			}
		}
		// 清空學院專業班級信息
		// 保存床位分配信息(xyfp_flg:[0：学院未分配];zyfp_flg:[0:专业未分配])
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set bjdm='',zydm='',xydm='',nj='',xyfp_flg='0',zyfp_flg='0' where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='"
						+ cwhs[i].split("_")[1] + "'");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取指定学院年级的统计人数
	 * 
	 * @param inputV
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCwfpglInfo(CwfpglForm myForm) {

		// 学院或年级学院人数统计数据集
		String sql_rs = "( select nj,xydm,xb,count(*) num " + "from view_xsjbxx  " + "group by nj,xydm,xb )";

		String sql_cws = "( select nj,xydm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws, " + "sum(case when xh is null then 1 end) sycws "
				+ "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " + "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,qsxb )";

		// 楼栋统计【学院】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,b.qsxb ");

		// 寝室统计【学院】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,b.qsxb ");

		String sql = "select a.*,a.num-a.yycws wrzrs,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
				+ "rownum r from (select * from (select b.xydm || '_' || b.nj || '_' || b.xb pk,a.nj, a.xydm, a.xb,nvl(b.num,0) num," + "case when c.num >0 then c.num else 0 end cws, "
				+ "case when c.yycws >0 then c.yycws else 0 end yycws, " + "case when c.sycws >0 then c.sycws else 0 end sycws " + " , nvl(d.sumlddm,0) sumlddm " + " , nvl(e.sumqsh,0) sumqsh "
				+ " from " + "(select * from (select nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_njxyzybj_all group by nj,xydm,xymc,zydm,zymc,bjdm,bjmc)a,dmk_xb b)a left join " + sql_rs
				+ " b on a.nj=b.nj and a.xydm=b.xydm and a.xb=b.xb left join " + sql_cws + " c on a.xydm = c.xydm and a.nj =c.nj and a.xb =c.qsxb " +
				// 楼栋统计【学院】
				" left join (" + sql_ldsum + ") d on a.xydm = d.xydm and a.nj =d.nj and a.xb =d.qsxb " +

				// 寝室统计【学院】
				" left join (" + sql_qssum + ") e on a.xydm = e.xydm and a.nj =e.nj and a.xb =e.qsxb " +

				") where nj=? and xydm=? and xb=? " + " ) a ";
		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "yycws", "wrzrs", "cws", "sycws", "sumlddm", "sumqsh" };

		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getXb() }, colList);
	}

	/**
	 * 获取学院年级性别的楼栋分配情况
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx(CwfpglForm myForm) {
		String sql = "select a.*,b.xyqss,xycws from " + "( " + "select lddm,ldmc, " + "count(1) cws, " + "count(case when xydm is not null then 1 end) yfpcws, "
				+ "count(case when xydm is null then 1 end) wfpcws, " + "count(distinct qsh) qss, " + "count(case when qsxydm is not null then 1 end) yfpqss, "
				+ "count(case when qsxydm is null then 1 end) wfpqss " + "from view_xg_gygl_new_cwxx group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) xyqss,count(1) xycws from view_xg_gygl_new_cwxx " + "where nj=? and xydm=? and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getXb() });
	}

	public List<HashMap<String, String>> loadLdlist(CwfpglForm myForm) {
		DAO dao = DAO.getInstance();
		String xb = ("1".equals(myForm.getXb()) ? "男" : "女");
		String sql = "select lddm,ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc from xg_gygl_new_ldxxb a" + " where 1=1";
		if ("bjloadldlist".equals(myForm.getBz())) {
			sql += " and exists (select 1 from view_xg_gygl_new_cwxx x where x.bjdm='" + myForm.getBjdm() + "' and x.qsxb=? and x.lddm=a.lddm)";
		} else if (!Base.isNull(myForm.getXydm())) {
			sql += " and exists (select 1 from view_xg_gygl_new_cwxx x where x.xydm='" + myForm.getXydm() + "' and x.qsxb=? and x.nj='" + myForm.getNj() + "' and x.lddm=a.lddm)";
		} else if ("zyloadldlist".equalsIgnoreCase(myForm.getBz())) {
			sql += " and exists (select 1 from view_xg_gygl_new_cwxx x where exists(select 1 from view_njxyzybj t where bjdm='" + myForm.getBjdm()
					+ "' and x.zydm=t.zydm) and x.qsxb=? and x.lddm=a.lddm)";
		} else {
			sql += " and exists (select 1 from view_xg_gygl_new_cwxx x where  x.qsxb=? and x.lddm=a.lddm)";
		}
		return dao.getList(sql, new String[] { xb }, new String[] { "lddm", "ldmc" });

	}

	/**
	 * 获取床位分配统计数据 取得年级学院专业统计
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_zy(CwfpglForm myForm) throws Exception {

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 学院代码设定
		String xydmSql = "";

		if (myForm.getXydm() != null && !"".equalsIgnoreCase(myForm.getXydm())) {
			xydmSql = " and a.xydm ='" + myForm.getXydm() + "' ";
		}

		// 人数统计
		String sql_rs = "(select nvl(a.num,0) num,c.*,nvl(b.num,'0') rzrs,nvl(a.num- nvl(b.num, '0'),'0') wrzrs from ( select * from (select nj,xydm,xymc,zydm,zymc from view_njxyzybj_all a where 1=1 "
				+ xydmSql
				+ " group by nj,xydm,xymc,zydm,zymc)a,dmk_xb b) c "
				+ "left join (select nj,xydm,zydm,xb,count(*) num from view_xsjbxx a where 1=1 "
				+ xydmSql
				+ " group by nj,xydm,zydm,xb) a on c.nj = a.nj and a.xydm = c.xydm and a.zydm = c.zydm and a.xb = c.xb "
				+ "left join "
				+ "(select nj,xydm,zydm,xb,count(*) num from ( select a.* from view_xsjbxx a,xg_gygl_new_cwxxb b "
				+ "where a.xh = b.xh "
				+ xydmSql
				+ ") group by nj,xydm,zydm,xb) b "
				+ "on a.nj= b.nj and a.xydm = b.xydm and a.zydm = b.zydm and a.xb = b.xb)";
		// 床位统计
		String sql_cws = "( select nj,xydm,zydm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws " + "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a "
				+ "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,zydm,qsxb )";
		// 楼栋统计【专业】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,a.zydm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,a.zydm,b.qsxb ");

		// 寝室统计【专业】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,a.zydm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,a.zydm,b.qsxb ");

		StringBuffer sql = new StringBuffer();

		sql.append(" select a.*, ");

		sql.append("rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.zydm||'_'||a.xb pk,a.*,");
		sql.append("nvl(b.num,'0') cws, nvl(b.yycws,'0') yycws, nvl(b.num-nvl(b.yycws,0),'0') sycws  ");

		sql.append(" , nvl(c.sumlddm,'0') sumlddm "); // 樓棟統計
		sql.append(" , nvl(d.sumqsh,'0') sumqsh "); // 寝室統計
		sql.append(" from ");
		sql.append(sql_rs + " a left join " + sql_cws);
		sql.append(" b on a.xydm = b.xydm and a.nj =b.nj and a.zydm = b.zydm and a.xb =b.qsxb  ");

		// 楼栋统计
		sql.append(" left join ( ");
		sql.append(sql_ldsum.toString());
		sql.append(" ) c ");
		sql.append(" on a.xydm = c.xydm ");
		sql.append(" and a.nj = c.nj ");
		sql.append(" and a.zydm = c.zydm ");
		sql.append(" and a.xb = c.qsxb  ");

		// 寝室统计
		sql.append(" left join ( ");
		sql.append(sql_qssum.toString());
		sql.append(" ) d ");
		sql.append(" on a.xydm = d.xydm ");
		sql.append(" and a.nj = d.nj ");
		sql.append(" and a.zydm = d.zydm ");
		sql.append(" and a.xb = d.qsxb  ");

		sql.append(" ) where 1=1 ");
		sql.append(searchTj + " order by nj desc,xydm,xb) a ");

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "xb", "num", "cws", "sumqsh", "sumlddm" };

		return commonQuery(sql.toString(), "", inputV, colList, myForm);

	}

	// ################################################以下为学院部分
	/**
	 * 获取床位分配统计数据 取得年级学院专业班级统计
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCwfpglInfoList_xy(CwfpglForm myForm) throws Exception {

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		// 学院代码设定
		String xydmSql = "";

		if (myForm.getXydm() != null && !"".equalsIgnoreCase(myForm.getXydm())) {
			xydmSql = " and a.xydm ='" + myForm.getXydm() + "' ";
		}

		// 学院或年级学院人数统计数据集
		/*
		 * String sql_rs = "( select nj,xydm,zydm,bjdm,xb,count(*) num " +
		 * "from view_xsjbxx where xydm='"+myForm.getXydm()+"' " +
		 * "group by nj,xydm,zydm,bjdm,xb )";
		 * 
		 * String sql_cws = "( select nj,xydm,bjdm,qsxb,count(*) num, " +
		 * "sum(case when xh is not null then 1 end) yycws, " +
		 * "sum(case when xh is null then 1 end) sycws " +
		 * "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " +
		 * "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" +
		 * "group by nj,xydm,bjdm,qsxb )";
		 * 
		 * String sql = "select a.*,a.num-a.yycws wrzrs," +
		 * "(select distinct b.zymc from view_njxyzybj_all b where a.xydm = b.xydm and a.zydm=b.zydm) zymc,"
		 * +
		 * "(select b.bjmc from view_njxyzybj_all b where a.xydm = b.xydm and a.bjdm=b.bjdm) bjmc,"
		 * +
		 * "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.zydm||'_'||a.bjdm||'_'||a.xb pk,a.*,"
		 * + "case when b.num >0 then b.num else 0 end cws, " +
		 * "case when b.yycws >0 then b.yycws else 0 end yycws, " +
		 * "case when b.sycws >0 then b.sycws else 0 end sycws from "+ sql_rs
		 * +" a left join "+sql_cws+
		 * " b on a.xydm = b.xydm and a.nj =b.nj and a.bjdm=b.bjdm and a.xb =b.qsxb ) where 1=1 "
		 * + searchTj+" order by nj desc,xydm,bjdm,xb) a ";
		 */
		String sql_rs = "(select nvl(a.num,0) num,c.*,nvl(b.num,'0') rzrs,nvl(a.num- nvl(b.num, '0'),'0') wrzrs from ( select * from (select nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_njxyzybj_all a where 1=1 "
				+ xydmSql
				+ " group by nj,xydm,xymc,zydm,zymc,bjdm,bjmc)a,dmk_xb b) c "
				+ "left join (select nj,xydm,zydm,bjdm,xb,count(*) num from view_xsjbxx a where 1=1 "
				+ xydmSql
				+ " group by nj,xydm,zydm,bjdm,xb) a on c.nj = a.nj and a.xydm = c.xydm and a.zydm = c.zydm and a.bjdm = c.bjdm and a.xb = c.xb "
				+ "left join "
				+ "(select nj,xydm,zydm,bjdm,xb,count(*) num from ( select a.* from view_xsjbxx a,xg_gygl_new_cwxxb b "
				+ "where a.xh = b.xh "
				+ xydmSql
				+ " ) group by nj,xydm,zydm,bjdm,xb) b "
				+ "on a.nj= b.nj and a.xydm = b.xydm and a.zydm = b.zydm and a.bjdm=b.bjdm and a.xb = b.xb)";

		String sql_cws = "( select nj,xydm,bjdm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws " + "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a "
				+ "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,bjdm,qsxb )";

		// 楼栋统计【班级】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");

		// 寝室统计【班级】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");

		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*, ");
		sql.append(" rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.zydm||'_'||a.bjdm||'_'||a.xb pk,a.*,");
		sql.append("nvl(b.num,'0') cws, nvl(b.yycws,'0') yycws, nvl(b.num-nvl(b.yycws,0),'0') sycws  ");

		sql.append(" , nvl(c.sumlddm,'0') sumlddm "); // 樓棟統計
		sql.append(" , nvl(d.sumqsh,'0') sumqsh "); // 寝室統計
		sql.append(" from ");
		sql.append(sql_rs + " a left join " + sql_cws);
		sql.append(" b on a.xydm = b.xydm and a.nj =b.nj and a.bjdm=b.bjdm and a.xb =b.qsxb  ");

		// 楼栋统计
		sql.append(" left join ( ");
		sql.append(sql_ldsum.toString());
		sql.append(" ) c ");
		sql.append(" on a.xydm = c.xydm ");
		sql.append(" and a.nj = c.nj ");
		sql.append(" and a.zydm = c.zydm ");
		sql.append(" and a.bjdm = c.bjdm ");
		sql.append(" and a.xb = c.qsxb ");

		// 寝室统计
		sql.append(" left join ( ");
		sql.append(sql_qssum.toString());
		sql.append(" ) d ");
		sql.append(" on a.xydm = d.xydm ");
		sql.append(" and a.nj = d.nj ");
		sql.append(" and a.zydm = d.zydm ");
		sql.append(" and a.bjdm = d.bjdm ");
		sql.append(" and a.xb = d.qsxb  ");

		sql.append(" ) where 1=1 ");
		sql.append(searchTj + " order by nj desc,xydm,bjdm,xb) a ");

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc", "xb", "num", "cws", "sumqsh", "sumlddm" };

		return commonQuery(sql.toString(), "", inputV, colList, myForm);
	}

	/**
	 * 统计楼栋分配信息（按专业）
	 * 
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_zy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		String xydm = myForm.getXydm();
		String nj = myForm.getNj();
		String zydm = myForm.getZydm();

		DAO dao = DAO.getInstance();
		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --查询楼栋基本信息
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		// 楼栋寝室统计信息（yfp_qss：已分配寝室数，wfp_qss:未分配寝室数[未分配给任何学院/专业/班级]）
		sql = "select count(1) qss,count(case when xydm is not null or nj is not null then 1 end) yfp_qss, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss "
				+ "from xg_gygl_new_qsxxb where lddm=?";// --楼栋寝室统计信息
		HashMap<String, String> ldqstjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldqstjxx);

		// 楼栋统计cws:该学院已分配床位 、yfp_cws:该专业已分配床位、wfp_cws:该学院未分配床位
		sql = "select count(case when xydm=? and nj=? then 1 end) cws," + "count(case when xydm=? and nj=? and zydm=? then 1 end) yfp_cws, "
				+ "count(case when xydm=? and nj=? and zydm is null then 1 end) wfp_cws " + "from xg_gygl_new_cwxxb where lddm=?";// --楼栋床位统计信息
		HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { xydm, nj, xydm, nj, zydm, xydm, nj, lddm });
		ldjbxx.putAll(ldcwtjxx);

		request.setAttribute("ldjbxx", ldjbxx);

		// 楼栋楼层基本信息：cws:该学院已分配床位 yfp_cws：该学院的专业已分配床位 yfp_cws：该学院的专业未分配床位
		// bzy_cws：本专业已分配床位数
		sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bzy_cws,b.qss from " + "( " + "select ch,chmc," + "count(case when xydm=? and nj=? and qsxb=? then 1 end) cws, "
				+ "count(case when xydm=? and nj=? and qsxb=? and zydm is not null then 1 end) yfp_cws, " + "count(case when xydm=? and nj=? and qsxb=? and zydm is null then 1 end) wfp_cws, "
				+ "count(case when xydm=? and nj=? and qsxb=? and zydm=? then 1 end) bzy_cws " + "from view_xg_gygl_new_cwxx where sfbl='否' and lddm=? and xydm=? and nj=? and qsxb=? "
				+ " group by ch,chmc order by to_number(ch) " + // --楼层床位统计信息
				") a left join (";
		sql += "select ch,count(distinct qsh)qss from view_xg_gygl_new_cwxx where lddm = ? and xydm = ? and nj = ? and qsxb = ? group by ch)b on a.ch=b.ch order by to_number(ch)";

		List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { xydm, nj, xb, xydm, nj, xb, xydm, nj, xb, xydm, nj, xb, zydm, lddm, xydm, nj, xb, lddm, xydm, nj, xb });
		request.setAttribute("ldlcxx", ldlcxx);

		String cwzt_sql = "";
		if ("未分配".equals(cwzt)) {
			cwzt_sql = " and a.zydm is null ";
		} else if ("已分配".equals(cwzt)) {
			cwzt_sql = " and a.zydm is not null ";
		}
		//辽宁机电职业技术学院 床位号可以有汉字出现_个性化
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " order by to_number(ch),qsh,cwh ";
		}else{
			sb = "order by to_number(ch),qsh,to_number(cwh)";
		}
		// 楼栋楼层寝室床位信息表
		sql = "select a.*,b.xm from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where a.sfbl='否' and lddm=? and a.xydm=? and a.nj=? and qsxb=? " + cwzt_sql
				+ " "+sb+" ";// --楼栋楼层寝室床位信息
		List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xydm, nj, xb });
		request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);
	}

	/**
	 * 统计楼栋分配信息（学院）
	 * 
	 * @param request
	 * @param lddm
	 */
	public void tjldFpxx_xy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		String xb = myForm.getXb();
		String cwzt = myForm.getCwzt();
		String xydm = myForm.getXydm();
		String nj = myForm.getNj();
		String bjdm = myForm.getBjdm();
		String zydm = myForm.getZydm();

		DAO dao = DAO.getInstance();
		String sql = "select * from xg_gygl_new_ldxxb where lddm=?";// --查询楼栋基本信息
		HashMap<String, String> ldjbxx = dao.getMapNotOut(sql, new String[] { lddm });

		// 因寝室没有所属到班级，故该处不再统计寝室数
		sql = "select count(1) qss,count(case when xydm is not null or nj is not null then 1 end) yfp_qss, " + "count(case when xydm is not null or nj is not null then null else 1 end) wfp_qss "
				+ "from xg_gygl_new_qsxxb where lddm=?";// --楼栋寝室统计信息
		HashMap<String, String> ldqstjxx = dao.getMapNotOut(sql, new String[] { lddm });
		ldjbxx.putAll(ldqstjxx);

		StringBuffer sqlGetFlg = new StringBuffer();
		sqlGetFlg.append(" select nvl(max(xyfp_flg),0) xyfp_flg , ");
		sqlGetFlg.append(" 		  nvl(max(zyfp_flg),0) zyfp_flg ");
		sqlGetFlg.append(" from xg_gygl_new_cwxxb a left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh ");
		sqlGetFlg.append(" where a.nj=? and a.xydm=? and a.zydm=? and b.qsxb = ? ");

		// 取得学院/专业分配FLG状态
		HashMap<String, String> fpFlg = dao.getMapNotOut(sqlGetFlg.toString(), new String[] { nj, xydm, zydm, xb });
		String zyfp_flg = "0";
		// 取得专业分配FLG
		if (fpFlg != null && fpFlg.size() != 0) {
			zyfp_flg = fpFlg.get("zyfp_flg");
		}

		// 专业分配FLG为：0【未分配】
		if ("0".equalsIgnoreCase(zyfp_flg)) {

			sql = "select count(case when xydm=? and nj=? then 1 end) cws," + "count(case when xydm=? and nj=? and bjdm=? then 1 end) yfp_cws, "
					+ "count(case when xydm=? and nj=? and bjdm is null then 1 end) wfp_cws " + "from xg_gygl_new_cwxxb where lddm=?";// --楼栋床位统计信息
			HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { xydm, nj, xydm, nj, bjdm, xydm, nj, lddm });
			ldjbxx.putAll(ldcwtjxx);

			request.setAttribute("ldjbxx", ldjbxx);

			sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bbj_cws,b.qss from " + "( " + "select ch,chmc," + "count(case when xydm=? and nj=? and qsxb=? then 1 end) cws, "
					+ "count(case when xydm=? and nj=? and qsxb=? and bjdm is not null then 1 end) yfp_cws, " + "count(case when xydm=? and nj=? and qsxb=? and bjdm is null then 1 end) wfp_cws, "
					+ "count(case when xydm=? and nj=? and qsxb=? and bjdm=? then 1 end) bbj_cws " + "from view_xg_gygl_new_cwxx where sfbl='否' and lddm=? and xydm=? and nj=? and qsxb=? "
					+ " group by ch,chmc order by to_number(ch) " + // --楼层床位统计信息
					") a left join (";
			sql += "select ch,count(distinct qsh)qss from view_xg_gygl_new_cwxx where lddm = ? and xydm = ? and nj = ? and qsxb = ? group by ch)b on a.ch=b.ch order by to_number(ch)";
			List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { xydm, nj, xb, xydm, nj, xb, xydm, nj, xb, xydm, nj, xb, bjdm, lddm, xydm, nj, xb, lddm, xydm, nj, xb });
			request.setAttribute("ldlcxx", ldlcxx);

			String cwzt_sql = "";
			if ("未分配".equals(cwzt)) {
				cwzt_sql = " and a.bjdm is null ";
			} else if ("已分配".equals(cwzt)) {
				cwzt_sql = " and a.bjdm is not null ";
			}
			//辽宁机电职业技术学院 床位号可以有汉字出现_个性化
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "order by to_number(ch),qsh,cwh";
			}else{
				sb = "order by to_number(ch),qsh,to_number(cwh)";
			}
			sql = "select a.*,b.xm from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where a.sfbl='否' and lddm=? and a.xydm=? and a.nj=? and qsxb=? " + cwzt_sql
					+ " "+sb+" ";// --楼栋楼层寝室床位信息
			List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xydm, nj, xb });
			request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);
		} else
		// 专业分配FLG为：1【已分配】
		if ("1".equalsIgnoreCase(zyfp_flg)) {

			// 楼栋床位统计信息
			sql = "select count(case when xydm=? and nj=? and zydm=? then 1 end) cws," + "count(case when xydm=? and nj=? and zydm=? and bjdm=? then 1 end) yfp_cws, "
					+ "count(case when xydm=? and nj=? and zydm=? and bjdm is null then 1 end) wfp_cws " + "from xg_gygl_new_cwxxb where lddm=?";// --楼栋床位统计信息
			HashMap<String, String> ldcwtjxx = dao.getMapNotOut(sql, new String[] { xydm, nj, zydm, xydm, nj, zydm, bjdm, xydm, nj, zydm, lddm });
			ldjbxx.putAll(ldcwtjxx);

			request.setAttribute("ldjbxx", ldjbxx);

			sql = "select a.ch,a.chmc,a.cws,a.yfp_cws,a.wfp_cws,a.bbj_cws,b.qss from " + "( " + "select ch,chmc," + "count(case when xydm=? and nj=? and zydm=? and qsxb=? then 1 end) cws, "
					+ "count(case when xydm=? and nj=? and zydm=? and qsxb=? and bjdm is not null then 1 end) yfp_cws, "
					+ "count(case when xydm=? and nj=? and zydm=? and qsxb=? and bjdm is null then 1 end) wfp_cws, "
					+ "count(case when xydm=? and nj=? and zydm=? and qsxb=? and bjdm=? then 1 end) bbj_cws "
					+ "from view_xg_gygl_new_cwxx where sfbl='否' and lddm=? and xydm=? and nj=? and zydm=? and qsxb=? " + " group by ch,chmc order by to_number(ch) " + // --楼层床位统计信息
					") a left join (";
			sql += "select ch,count(distinct qsh)qss from view_xg_gygl_new_cwxx where lddm = ? and xydm = ? and nj = ? and zydm=? and qsxb = ? group by ch)b on a.ch=b.ch order by to_number(ch)";
			List<HashMap<String, String>> ldlcxx = dao.getListNotOut(sql, new String[] { xydm, nj, zydm, xb, xydm, nj, zydm, xb, xydm, nj, zydm, xb, xydm, nj, zydm, xb, bjdm, lddm, xydm, nj, zydm,
					xb, lddm, xydm, nj, zydm, xb });
			request.setAttribute("ldlcxx", ldlcxx);

			String cwzt_sql = "";
			if ("未分配".equals(cwzt)) {
				cwzt_sql = " and a.bjdm is null ";
			} else if ("已分配".equals(cwzt)) {
				cwzt_sql = " and a.bjdm is not null ";
			}
			//辽宁机电职业技术学院 床位号可以有汉字出现_个性化
			String sb1 = "";
			if("12898".equals(Base.xxdm)){
				sb1 = "order by to_number(ch),qsh,cwh";
			}else{
				sb1 = "order by to_number(ch),qsh,to_number(cwh)";
			}
			sql = "select a.*,b.xm from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh " + "where a.sfbl='否' and lddm=? and a.xydm=? and a.nj=? and a.zydm=? and qsxb=? " + cwzt_sql
					+ " "+sb1+" ";// --楼栋楼层寝室床位信息
			List<HashMap<String, String>> ldlcqscwxxb = dao.getListNotOut(sql, new String[] { lddm, xydm, nj, zydm, xb });
			request.setAttribute("ldlcqscwxxb", ldlcqscwxxb);
		}
	}

	/**
	 * 获取楼栋列表（学院）
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLdList_xy(CwfpglForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select lddm,ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc " + "from xg_gygl_new_ldxxb a where 1=1 "
				+ "and exists (select 1 from view_xg_gygl_new_cwxx x where x.qsxb=? and x.nj=? and x.xydm=? and x.lddm=a.lddm)";
		return dao.getListNotOut(sql, new String[] { myForm.getXb(), myForm.getNj(), myForm.getXydm() });
	}

	/**
	 * 保存寝室床位分配信息(按專業保存)
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public boolean saveQscwfpxx_zy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号
		String nj = myForm.getNj();// 年级
		String xydm = myForm.getXydm();// 学院
		String zydm = myForm.getZydm();// 专业

		// 保存床位分配信息(zyfp_flg:[1：专业已分配])
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set zydm = '" + zydm + "',zyfp_flg='1' where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='" + cwhs[i].split("_")[1] + "' "
						+ " and xydm='" + xydm + "' and nj='" + nj + "' and zydm is null");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 保存寝室床位分配信息(学院)
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public boolean saveQscwfpxx_xy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号
		String xydm = myForm.getXydm();// 学院
		String nj = myForm.getNj();// 年级
		String bjdm = myForm.getBjdm();// 班级
		String zydm = myForm.getZydm();// 专业

		// 保存床位分配信息(若床位未按专业分配过则zyfp_flg：'0'，否则'1')
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set zydm = '" + zydm + "' ,bjdm='" + bjdm + "', zyfp_flg=(case when zyfp_flg is null or zyfp_flg = '0' then '0'  else '1' end) " + " where lddm='"
						+ lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='" + cwhs[i].split("_")[1] + "' " + " and xydm='" + xydm + "' and nj='" + nj + "' and bjdm is null");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 取消寝室床位分配信息（学院）
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public synchronized boolean qxQscwfpxx_zy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号
		// 直接清空bjdm和zydm，如果該數據被更新為其他專業/班級的話，容易被沖掉qilm 暫不修改
		// String xydm=myForm.getXydm();//学院
		// String nj=myForm.getNj();//年级
		// String bjdm=myForm.getBjdm();//班级
		// String zydm=myForm.getZydm();//专业
		// 保存床位分配信息(更新0：专业未分配)
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set bjdm='',zydm='',zyfp_flg='0' where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='" + cwhs[i].split("_")[1] + "'");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 取消寝室床位分配信息（学院）
	 * 
	 * @param request
	 * @param lddm
	 * @return
	 */
	public synchronized boolean qxQscwfpxx_xy(HttpServletRequest request, CwfpglForm myForm) {
		String lddm = myForm.getLddm();
		boolean b = false;
		ArrayList<String> sqls = new ArrayList<String>();

		String[] cwhs = request.getParameterValues("checkbox_cwh");// 床位号
		// 直接清空bjdm和更新zydm，如果該數據被更新為其他班級的話，容易被沖掉qilm 暫不修改
		// String xydm=myForm.getXydm();//学院
		// String nj=myForm.getNj();//年级
		// String bjdm=myForm.getBjdm();//班级
		// String zydm=myForm.getZydm();//专业
		// 保存床位分配信息
		if (cwhs != null && cwhs.length > 0) {
			for (int i = 0; i < cwhs.length; i++) {
				sqls.add("update xg_gygl_new_cwxxb set bjdm='',zydm=(case when zyfp_flg ='0' then '' else zydm end) where lddm='" + lddm + "' and qsh='" + cwhs[i].split("_")[0] + "' and cwh='"
						+ cwhs[i].split("_")[1] + "'");
			}
		}

		CommDAO dao = new CommDAO();
		try {
			b = dao.saveArrDate(sqls.toArray(new String[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取指定学院年级的统计人数(学院)qilm
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getCwfpglInfo_zy(CwfpglForm myForm) {

		// 学院或年级学院人数统计数据集
		String sql_rs = "( select nj, xydm, zydm, xb, count(*) num " + "from view_xsjbxx " + "group by nj, xydm, zydm, xb )";

		String sql_cws = "( select nj,xydm,zydm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws, " + "sum(case when xh is null then 1 end) sycws "
				+ "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " + "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,zydm,qsxb )";

		// 楼栋统计【专业】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,a.zydm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,a.zydm,b.qsxb ");

		// 寝室统计【专业】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,a.zydm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,a.zydm,b.qsxb ");

		// 床位统计【当前学院】
		StringBuffer sql_cws_xy = new StringBuffer();
		sql_cws_xy.append(" select a.nj, a.xydm, b.qsxb, ");
		sql_cws_xy.append(" nvl(count(*),0) sumxycws, "); // 当前学院床位统计
		sql_cws_xy.append(" nvl(sum(case when zydm is null then 1 end),0) sumxysycws "); // 当前学院剩余床位统计
		sql_cws_xy.append(" from xg_gygl_new_cwxxb a ");
		sql_cws_xy.append(" left join xg_gygl_new_qsxxb b ");
		sql_cws_xy.append("      on a.lddm = b.lddm ");
		sql_cws_xy.append("      and a.qsh = b.qsh ");
		sql_cws_xy.append(" group by a.nj,a.xydm,b.qsxb ");

		String sql = "select a.*,a.num-a.yycws wrzrs,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
				+ "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.nj, a.xydm, a.zydm, a.xb, nvl(b.num, 0) num," + "case when c.num >0 then c.num else 0 end cws, "
				+ "case when c.yycws >0 then c.yycws else 0 end yycws, " + "case when c.sycws >0 then c.sycws else 0 end sycws " + " , nvl(d.sumlddm,0) sumlddm "
				+ // 当前专业 分配到的楼栋数
				" , nvl(e.sumqsh,0) sumqsh "
				+ // 当前专业 分配到的寝室数
				" , nvl(f.sumxycws,0) sumxycws "
				+ // 当前学院床位统计
				" , nvl(f.sumxysycws,0) sumxysycws "
				+ // 当前学院剩余床位统计
				" from " + "(select * from (select nj, xydm, xymc, zydm, zymc from view_njxyzybj_all group by nj, xydm, xymc, zydm, zymc) a, dmk_xb b) a left join" + sql_rs
				+ " b on a.nj = b.nj and a.xydm = b.xydm and a.zydm = b.zydm and a.xb = b.xb left join " + sql_cws + " c on a.xydm = c.xydm and a.nj =c.nj and a.zydm=c.zydm and a.xb =c.qsxb " +

				// 楼栋统计【学院】
				" left join (" + sql_ldsum + ") d on a.xydm = d.xydm and a.nj =d.nj and a.zydm=d.zydm and a.xb =d.qsxb " +

				// 寝室统计【学院】
				" left join (" + sql_qssum + ") e on a.xydm = e.xydm and a.nj =e.nj and a.zydm=e.zydm and a.xb =e.qsxb " +

				// 床位统计【当前学院】
				" left join (" + sql_cws_xy + ") f on a.xydm = f.xydm and a.nj =f.nj and a.xb =f.qsxb " +

				")  " + " where nj=? and xydm=? and zydm=? and xb=?) a ";
		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "yycws", "wrzrs", "cws", "sycws", "sumlddm", "sumqsh", "sumxycws", "sumxysycws" };

		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getZydm(), myForm.getXb() }, colList);
	}

	/**
	 * 获取指定学院年级的统计人数(学院)
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getCwfpglInfo_xy(CwfpglForm myForm) {

		// 学院或年级学院人数统计数据集
		String sql_rs = "( select nj, xydm, zydm, bjdm, xb, count(*) num " + "from view_xsjbxx " + "group by nj, xydm, zydm, bjdm, xb )";

		String sql_cws = "( select nj,xydm,zydm,bjdm,qsxb,count(*) num, " + "sum(case when xh is not null then 1 end) yycws, " + "sum(case when xh is null then 1 end) sycws "
				+ "from (select a.*,b.qsxb from xg_gygl_new_cwxxb a " + "left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh)" + "group by nj,xydm,zydm,bjdm,qsxb )";

		// 楼栋统计【班级】
		StringBuffer sql_ldsum = new StringBuffer();
		sql_ldsum.append(" select count(distinct a.lddm) sumlddm,a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");
		sql_ldsum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_ldsum.append(" left join xg_gygl_new_qsxxb b ");
		sql_ldsum.append("      on a.lddm = b.lddm ");
		sql_ldsum.append("      and a.qsh = b.qsh ");
		sql_ldsum.append(" group by a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");

		// 寝室统计【班级】
		StringBuffer sql_qssum = new StringBuffer();
		sql_qssum.append(" select count(distinct a.lddm||a.qsh) sumqsh,a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");
		sql_qssum.append(" from   xg_gygl_new_cwxxb a  ");
		sql_qssum.append(" left join xg_gygl_new_qsxxb b ");
		sql_qssum.append("      on a.lddm = b.lddm ");
		sql_qssum.append("      and a.qsh = b.qsh ");
		sql_qssum.append(" group by a.nj,a.xydm,a.zydm,a.bjdm,b.qsxb ");

		// 床位统计【当前学院】
		StringBuffer sql_cws_xy = new StringBuffer();
		sql_cws_xy.append(" select a.nj, a.xydm, b.qsxb, ");
		sql_cws_xy.append(" nvl(count(*),0) sumxycws, "); // 当前学院床位统计
		sql_cws_xy.append(" nvl(sum(case when zydm is null then 1 end),0) sumxysycws "); // 当前学院剩余床位统计
		sql_cws_xy.append(" from xg_gygl_new_cwxxb a ");
		sql_cws_xy.append(" left join xg_gygl_new_qsxxb b ");
		sql_cws_xy.append("      on a.lddm = b.lddm ");
		sql_cws_xy.append("      and a.qsh = b.qsh ");
		sql_cws_xy.append(" group by a.nj,a.xydm,b.qsxb ");

		// 床位统计【当前专业】
		StringBuffer sql_cws_zy = new StringBuffer();
		sql_cws_zy.append(" select a.nj, a.xydm, a.zydm, b.qsxb, ");
		sql_cws_zy.append(" nvl(count(*),0) sumzycws, "); // 当前专业床位统计
		sql_cws_zy.append(" nvl(sum(case when bjdm is null then 1 end),0) sumzysycws "); // 当前专业剩余床位统计
		sql_cws_zy.append(" from xg_gygl_new_cwxxb a ");
		sql_cws_zy.append(" left join xg_gygl_new_qsxxb b ");
		sql_cws_zy.append("      on a.lddm = b.lddm ");
		sql_cws_zy.append("      and a.qsh = b.qsh ");
		sql_cws_zy.append(" group by a.nj,a.xydm, a.zydm, b.qsxb ");

		String sql = "select a.*,a.num-a.yycws wrzrs,(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,"
				+ "rownum r from (select * from (select a.xydm||'_'||a.nj||'_'||a.xb pk,a.nj, a.xydm, a.zydm, a.bjdm, a.xb, nvl(b.num, 0) num,"
				+ "case when c.num >0 then c.num else 0 end cws, "
				+ "case when c.yycws >0 then c.yycws else 0 end yycws, "
				+ "case when c.sycws >0 then c.sycws else 0 end sycws "
				+ " , nvl(d.sumlddm,0) sumlddm "
				+ // 当前专业分到到的楼栋数
				" , nvl(e.sumqsh,0) sumqsh "
				+ // 当前专业分到到的寝室数
				" , nvl(f.sumxycws,0) sumxycws "
				+ // 当前学院床位统计
				" , nvl(f.sumxysycws,0) sumxysycws "
				+ // 当前学院剩余床位统计
				" , nvl(g.sumzycws,0) sumzycws "
				+ // 当前专业床位统计
				" , nvl(g.sumzysycws,0) sumzysycws "
				+ // 当前专业剩余床位统计
				" from " + "(select * from (select nj, xydm, xymc, zydm, zymc, bjdm, bjmc from view_njxyzybj_all group by nj, xydm, xymc, zydm, zymc, bjdm, bjmc) a, dmk_xb b) a left join" + sql_rs
				+ " b on a.nj = b.nj and a.xydm = b.xydm and a.zydm=b.zydm and a.bjdm = b.bjdm and a.xb = b.xb left join " + sql_cws
				+ " c on a.xydm = c.xydm and a.nj =c.nj and a.zydm=c.zydm and a.bjdm=c.bjdm and a.xb =c.qsxb " +

				// 楼栋统计【班级】
				" left join (" + sql_ldsum + ") d on a.xydm = d.xydm and a.nj =d.nj and a.zydm=d.zydm and a.bjdm=d.bjdm and a.xb =d.qsxb " +

				// 寝室统计【班级】
				" left join (" + sql_qssum + ") e on a.xydm = e.xydm and a.nj =e.nj and a.zydm=e.zydm and a.bjdm=e.bjdm and a.xb =e.qsxb " +

				// 寝室统计【当前学院】
				" left join (" + sql_cws_xy + ") f on a.xydm = f.xydm and a.nj =f.nj and a.xb =f.qsxb " +

				// 寝室统计【当前专业】
				" left join (" + sql_cws_zy + ") g on a.xydm = g.xydm and a.zydm=g.zydm and a.nj =g.nj and a.xb =g.qsxb " +

				")  " + " where nj=? and xydm=? and zydm=? and bjdm=? and xb=?) a ";
		String[] colList = new String[] { "pk", "nj", "xymc", "xb", "num", "yycws", "wrzrs", "cws", "sycws", "sumlddm", "sumqsh", "sumxycws", "sumxysycws", "sumzycws", "sumzysycws" };

		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getXb() }, colList);
	}

	/**
	 * 获取学院年级性别的楼栋分配情况(学院) qilm
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx_zy(CwfpglForm myForm) {
		String sql = "select a.*,b.xyqss,xycws from " + "( " + "select lddm,ldmc, " + "count(1) cws, " + "count(case when zydm is not null then 1 end) yfpcws, "
				+ "count(case when zydm is null then 1 end) wfpcws " + "from view_xg_gygl_new_cwxx where nj=? and xydm=? group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) xyqss,count(1) xycws from view_xg_gygl_new_cwxx " + "where nj=? and xydm=? and zydm=? and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getNj(), myForm.getXydm(), myForm.getZydm(), myForm.getXb() });
	}

	/**
	 * 获取学院年级性别的楼栋分配情况(学院)
	 * 
	 * @param inputValue
	 * @return
	 */
	public List<HashMap<String, String>> getXynjxbLdfpxx_xy(CwfpglForm myForm) {
		String sql = "select a.*,b.xyqss,xycws from " + "( " + "select lddm,ldmc, " + "count(1) cws, " + "count(case when bjdm is not null then 1 end) yfpcws, "
				+ "count(case when bjdm is null then 1 end) wfpcws " + "from view_xg_gygl_new_cwxx where nj=? and xydm=? group by lddm,ldmc " + ") a, " + "( "
				+ "select lddm,count(distinct qsh) xyqss,count(1) xycws from view_xg_gygl_new_cwxx " + "where nj=? and xydm=? and bjdm=? and qsxb=? group by lddm " + ") b " + "where a.lddm=b.lddm";
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql, new String[] { myForm.getNj(), myForm.getXydm(), myForm.getNj(), myForm.getXydm(), myForm.getBjdm(), myForm.getXb() });
	}

	/**
	 * 加载楼栋list（学院）
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> loadLdlist_xy(CwfpglForm myForm) {
		DAO dao = DAO.getInstance();
		String xb = ("1".equals(myForm.getXb()) ? "男" : "女");
		String sql = "select lddm,ldmc||(case when ldxb='混住' then '('||ldxb||')' else '' end) ldmc from xg_gygl_new_ldxxb a" + " where (ldxb=? or ldxb='混住')";
		if (!Base.isNull(myForm.getXydm())) {
			sql += " and exists (select 1 from xg_gygl_new_cwxxb x where x.xydm='" + myForm.getXydm() + "' and x.nj='" + myForm.getNj() + "' and x.lddm=a.lddm)";
		}
		return dao.getList(sql, new String[] { xb }, new String[] { "lddm", "ldmc" });

	}

	/**
	 * 
	 * @描述:联动加载楼栋
	 * @作者：xiaxia
	 * @日期：2014-9-12 下午03:27:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> cwfpglInitLdList(HttpServletRequest request,String xydm, String zydm, String cwzt, String xbdm) {
		String gyglyQx = (String) request.getSession().getAttribute("gyglyQx");
		String username = (String) request.getSession().getAttribute("userName");
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		String xb = ("1".equals(xbdm) ? "男" : "女");
		sql.append("select distinct(lddm),ldmc||(case when qsxb='混住' then '('||qsxb||')' else '' end) ldmc " + "from view_xg_gygl_new_cwxx where (qsxb=? or qsxb='混住')");
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql.append(" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '" + username + "')");
		}
		if(StringUtils.isNotNull(zydm)||"已分配".equals(cwzt)){
			sql.append(" and xydm =?");
			return dao.getListNotOut(sql.toString(), new String[] { xb, xydm });
		}else{
			return dao.getListNotOut(sql.toString(), new String[] { xb });
		}
		

	}
}
