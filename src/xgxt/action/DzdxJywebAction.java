package xgxt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.CLOB;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.dealDate;

public class DzdxJywebAction extends DispatchAction {
	// DAO dao = DAO.getInstance();

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}


	// 查看全部就业指导信息
	public ActionForward allshgcjcjyinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> ggl = new ArrayList<Object>();
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		
			sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '基层就业'";
		
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '基层就业' order by a.fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj desc";
		colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
		ggl.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx = '基层就业'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("ggl", ggl);
		return mapping.findForward("success");
	}
	// 查看就业指导信息
	public ActionForward shgcjcjyinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		String[] tit = new String[] { "rowid", "wjbt", "fbr", "fbsj", "wjnr",
				"wjlx" };
		String sql = "select rowid,wjbt,fbr,fbsj,wjnr,wjlx from "
				+ "JYGL_ZCWJB where rowid=?";
		String[] rs = dao.getOneRs(sql, new String[] { rowid }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			if (i == 3) {
				String time = dao.doForTime(rs[3]);
				request.setAttribute(tit[3], isNull(rs[3]) ? " " : time);
			}
			if (i != 3) {
				request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
			}
		}
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "wjnr");
		String wjnr;
		if (clob == null) {
			wjnr = "";
		} else {
			wjnr = clob.getSubString(1L, (int) clob.length());
		}
		request.setAttribute("wjnr", wjnr);
		return mapping.findForward("success");
	}
	// 查看全部就业指导信息
	public ActionForward allshgcjyywtinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> ggl = new ArrayList<Object>();
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		
			sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '就业一网通'";
		
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '就业一网通' order by a.fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj desc";
		colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
		ggl.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx = '就业一网通'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("ggl", ggl);
		return mapping.findForward("success");
	}
	// 查看就业指导信息
	public ActionForward shgcjyywtinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		String[] tit = new String[] { "rowid", "wjbt", "fbr", "fbsj", "wjnr",
				"wjlx" };
		String sql = "select rowid,wjbt,fbr,fbsj,wjnr,wjlx from "
				+ "JYGL_ZCWJB where rowid=?";
		String[] rs = dao.getOneRs(sql, new String[] { rowid }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			if (i == 3) {
				String time = dao.doForTime(rs[3]);
				request.setAttribute(tit[3], isNull(rs[3]) ? " " : time);
			}
			if (i != 3) {
				request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
			}
		}
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "wjnr");
		String wjnr;
		if (clob == null) {
			wjnr = "";
		} else {
			wjnr = clob.getSubString(1L, (int) clob.length());
		}
		request.setAttribute("wjnr", wjnr);
		return mapping.findForward("success");
	}
	
	// 查看全部招聘信息
	public ActionForward alljyzpinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		CommanForm dataSearchForm = (CommanForm) form;

		String doType = request.getParameter("doType");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
//		HttpSession session = request.getSession();
		// 行业分类列表
		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc"));
		String zpzw = DealString.toGBK(request.getParameter("zpzw"));
		String gzdd = DealString.toGBK(request.getParameter("gzdd"));
		String hyfl = DealString.toGBK(request.getParameter("iData"));
		String wyyq = DealString.toGBK(request.getParameter("wyyq"));
		String xlyq = DealString.toGBK(request.getParameter("xlyq"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String zzxs = DealString.toGBK(request.getParameter("zzxs"));
		String xxly = DealString.toGBK(request.getParameter("xxly"));
		String zptype = DealString.toGBK(request.getParameter("zptype"));

		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		if ("query".equals(act)) {
			map.put("dwmc", dwmc);
			map.put("zpzw", zpzw);
			map.put("gzdd", gzdd);
			map.put("iData", hyfl);
			map.put("wyyq", wyyq);
			map.put("xlyq", xlyq);
			map.put("xb", xb);
			map.put("zzxs", zzxs);
			map.put("xjsj", xjsj);
			map.put("xxly", xxly);
		}

		StringBuffer query = new StringBuffer();
		if (!("".equals(dwmc))) {
			query.append(" and gsmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zpzw))) {
			query.append(" and zpzw like '%");
			query.append(zpzw);
			query.append("%'");
		}
		if (!("".equals(gzdd))) {
			query.append(" and gzdd like '%");
			query.append(gzdd);
			query.append("%'");
		}
		if (!("".equals(hyfl))) {
			query.append(" and hyfl='");
			query.append(hyfl);
			query.append("'");
		}
		if (!("".equals(wyyq))) {
			query.append(" and wyyq like '%");
			query.append(wyyq);
			query.append("%'");
		}
		if (!("".equals(xlyq))) {
			query.append(" and xlyq='");
			query.append(xlyq);
			query.append("'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb='");
			query.append(xb);
			query.append("'");
		}
		if (!("".equals(zzxs))) {
			query.append(" and zzxs='");
			query.append(zzxs);
			query.append("'");
		}
		if (!("".equals(xxly))) {
			query.append(" and xxly='");
			query.append(xxly);
			query.append("'");
		}
		if (!("".equals(xjsj))) {
			query.append(" and (fbsj between '");
			query.append(beforesj);
			query.append("' and '");
			query.append(nowsj);
			query.append("') ");
		}
		if("xnzp".equals(zptype)){
			query.append(" and fbfs='");
			query.append("校内");
			query.append("'");
		}else if("xwzp".equals(zptype)){
			query.append(" and fbfs='");
			query.append("校外");
			query.append("'");
		}
		
		String query1 = query.toString();
		if ("qingkong".equals(doType3)) {
			map = new HashMap<String, String>();
			query1 = "";
		}
		// 招聘信息
		
			//sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.zpzw,a.xxly,a.fbsj,a.readtimes from jygl_zpxxb a where xxsh='通过'"
			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.zpzw,a.xxly,a.fbsj,a.readtimes from jygl_zpxxb a where 1=1 "
					+ query1
					+ " order by fbsj desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by fbsj desc";

		colList = new String[] { "rid", "gsmc", "zpzw", "xxly", "fbsj",
				"readtimes" };
		rs.addAll(dao.rsToVator2(sql, new String[] {}, colList));

//			sql = "select count(*) from jygl_zpxxb where xxsh='通过'" + query1;
		sql = "select count(*) from jygl_zpxxb where 1=1" + query1;
			
		int rsNuminfo = dao.getOneRsint(sql);
		dataSearchForm.getPages().setMaxRecord(rsNuminfo);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}
	
	// 查看全部单位信息库信息
	public ActionForward alldwxxkinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		CommanForm dataSearchForm = (CommanForm) form;

//		String doType = request.getParameter("doType");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
//		HttpSession session = request.getSession();
		String viewdwxx = request.getParameter("view");
		String dwxzcx= "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);
		
		String zgdwmc = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);
		
		String hyflsql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(hyflsql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);
		
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("")
				.get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get(
				"xianList"));
		request.setAttribute("ssList", stuDao.getSsList());
		if("view".equals(viewdwxx)){
			pkValue = DealString.toGBK(request.getParameter("rowid"));
			sql = "select * from dwxxb where dwid='"+ pkValue + "'";
			colList = dao.getColumnName("select * from dwxxb a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String shen = map.get("szdqsh");
				String shi = map.get("szdqsi");
//				转换省代码
				String sqldzsh = "select * from dmk_sydq where sydqdm=?";
				String[] rs5 = dao.getOneRs(sqldzsh, new String[] { shen },
						new String[] { "sydq" });
				if (null != rs5) {
					map.put("szdqsh", rs5[0]);
					}
//				转换市代码
				String sqlshidw = "select qxmc from dmk_qx where qxdm=?";
				String[] rs6 = dao.getOneRs(sqlshidw, new String[] { shi },
						new String[] { "qxmc" });
				if (null != rs6) {
					map.put("szdqsi",rs6[0]);
					}
				map.put("gsmc", map.get("dwmc"));
				map.put("gswz", map.get("dwzy"));
			}
			request.setAttribute("rs", map);
			
			return mapping.findForward("success1");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 行业分类列表
		request.setAttribute("hyflList", hyflList);

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}


		String dwmc = DealString.toGBK(request.getParameter("dwmc"));
		String dwxz = DealString.toGBK(request.getParameter("dwxz"));
		String dwlb = DealString.toGBK(request.getParameter("dwlb"));
		String zgbm = DealString.toGBK(request.getParameter("zgbm"));
		String hyfl = DealString.toGBK(request.getParameter("hyfl"));
		String szdqsh = DealString.toGBK(request.getParameter("szdqsh"));
		String szdqsi = DealString.toGBK(request.getParameter("szdqsi"));

		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		if ("query".equals(act)) {
			map.put("dwmc", dwmc);
			map.put("dwxz", dwxz);
			map.put("dwlb", dwlb);
			map.put("iData", hyfl);
			map.put("zgbm", zgbm);
			map.put("szdqsh", szdqsh);
			map.put("szdqsi", szdqsi);
		}

		StringBuffer query = new StringBuffer("where 1=1 ");
		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(dwxz))) {
			query.append(" and dwxz like '%");
			query.append(dwxz);
			query.append("%'");
		}
		if (!("".equals(dwlb))) {
			query.append(" and dwlb like '%");
			query.append(dwlb);
			query.append("%'");
		}
		if (!("".equals(hyfl))) {
			query.append(" and hyfl='");
			query.append(hyfl);
			query.append("'");
		}
		if (!("".equals(zgbm))) {
			query.append(" and zgbm like '%");
			query.append(zgbm);
			query.append("%'");
		}
		if (!("".equals(szdqsh))) {
			query.append(" and szdqsh='");
			query.append(szdqsh);
			query.append("'");
		}
		if (!("".equals(szdqsi))) {
			query.append(" and szdqsi='");
			query.append(szdqsi);
			query.append("'");
		}
		

		String query1 = query.toString();

		if ("qingkong".equals(doType3)) {
			map = new HashMap<String, String>();
			query1 = "";
		}
		// 招聘信息

			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.* from dwxxb a "
					+ query1
					+ " order by dwmc desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by dwmc desc";

		colList = new String[] { "dwid","dwmc","dwxz","lxr","lxdh","hyfl"};
		ArrayList<String[]> dwxxcx = new ArrayList<String[]>();
		dwxxcx = dao.rsToVator2(sql, new String[] {}, colList);
		if(dwxxcx != null)
		{
			rs.addAll(dwxxcx);
		}
		sql = "select count(*) from dwxxb " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		dataSearchForm.getPages().setMaxRecord(rsNuminfo);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}
}