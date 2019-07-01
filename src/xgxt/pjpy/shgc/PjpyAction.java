package xgxt.pjpy.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.PjpyDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.utils.Arrays2;
import xgxt.utils.SearchUtils;

public class PjpyAction extends BaseAction {

	/**
	 * @describe 项目总体人数设定
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_xmrswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userType = session.getAttribute("userType").toString();
		String pk = "bbdm";
		String tips = "评奖评优 - 基础数据维护 - 奖学金人数维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_pjpy_shgc_rswfzb";
		String[] colList = new String[] { "主键", "bbdm", "bbmc", "zrs", "yfprs" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 1);

		querry.append(SearchUtils.equalSql("bbdm", bbdm));
		querry.append(SearchUtils.likeSql("bbmc", bbmc));
		
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("bbmc", bbmc);

		String sql = "select bbdm 主键,a.* from view_pjpy_shgc_rswfzb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmListAll());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("shgc_pjpy_xmrswh");
	}
	
	/**
	 * @describe 批量设置项目人数
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_xmrsPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String zrs = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			zrs = Base.chgNull(request.getParameter("zrs"), "0", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update pjpy_shgc_rswfzb set zrs='"+zrs+"' where bbdm='"+pkT[i]+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("zrs", zrs);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("shgc_pjpy_xmrsPlsz");
	}

	/**
	 * @describe 初始化项目人数设置数据
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_xmrscsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sfbc = Base.chgNull(request.getParameter("sfbc"), "", 1);

		if ("no".equalsIgnoreCase(sfbc)) {
			String[] sqlTe = new String[4];
			sqlTe[0] = "delete pjpy_shgc_bjrsb where 1=1";
			sqlTe[1] = "delete pjpy_shgc_zyrsb where 1=1";
			sqlTe[2] = "delete pjpy_shgc_xyrsb where 1=1";
			sqlTe[3] = "delete pjpy_shgc_rswfzb where 1=1";
			dao.runBatch(sqlTe);
			String[] sqlT = new String[3];
			sqlT[0] = "insert into pjpy_shgc_rswfzb(bbdm) (select bbdm from pjpy_shgc_jxjbbdmb)";
			sqlT[1] = "insert into pjpy_shgc_rswfzb(bbdm) values('优秀奖学金')";
			sqlT[2] = "insert into pjpy_shgc_rswfzb(bbdm) values('自强奖学金')";
			dao.runBatch(sqlT);
		} else {
			String[] bbList = dao.getArray(
					"SELECT bbdm FROM pjpy_shgc_jxjbbdmb UNION ALL SELECT '优秀奖学金' FROM dual UNION ALL SELECT '自强奖学金' FROM dual",
					new String[] {}, "bbdm");
			int i = 0;
			for (int j = 0; j < bbList.length; j++) {
				String bbdm = bbList[j];
				String num = dao
						.getOneRs(
								"select count(*) num from pjpy_shgc_rswfzb where bbdm=?",
								new String[] { bbdm }, "num");
				String[] sqlT = new String[bbList.length];
				if ("0".equalsIgnoreCase(num)) {
					sqlT[i] = "insert into pjpy_shgc_rswfzb(bbdm) values('"+bbdm+"')";
					i++;
				}
				dao.runBatch(sqlT);
			}
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/shgc_pjpy_new.do?method=shgc_pjpy_xmrswh&go=go", false);
	}

	/**
	 * @describe 根据主键得到项目学院人数设置信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_xyrsEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyShgcActionForm pjpyShgcActionForm = (PjpyShgcActionForm)form;
		PjpyShgcService service = new PjpyShgcService();
		List<Object> rs = new ArrayList<Object>();
		String writeAble = "yes";// 写权限
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		
		String[] xyList = dao.getArray(
				"select xydm from view_xsjbxx group by xydm", new String[] {},
				"xydm");
		
		int i = 0;
		String[] sqlT = new String[xyList.length];
		for (String xydm : xyList){
			String num = dao
					.getOneRs(
							"select count(*) num from pjpy_shgc_xyrsb where bbdm=? and xydm=?",
							new String[] { pkVal, xydm }, "num");
			if ("0".equalsIgnoreCase(num)){
				sqlT[i] = "insert into pjpy_shgc_xyrsb(bbdm,xydm) values('"+pkVal+"','"+xydm+"')";
				i++;
			}
		}
		dao.runBatch(sqlT);
		
		if ("save".equalsIgnoreCase(act)) {
			service.saveJxjxyrs(pjpyShgcActionForm.getPks(), Arrays2
					.toGBKArrays(request.getParameterValues("szrs")), request);
			request.setAttribute("end", "end");
		}
		
		String sql = "select bbdm||xydm pks,bbdm,xydm,a.xymc,a.xyrs,a.szrs,a.yfprs from view_pjpy_shgc_xyrsb a where a.bbdm=?";
		rs.addAll(dao.getArrayList(sql, new String[] { pkVal }, new String[] {
				"pks", "bbdm", "xydm", "xymc", "xyrs", "szrs", "yfprs" }));

		String[] tS = dao.getOneRs(
				"select bbmc,zrs from view_pjpy_shgc_rswfzb where bbdm=?",
				new String[] { pkVal }, new String[] { "bbmc", "zrs" });
		request.setAttribute("bbmc", tS[0]);
		request.setAttribute("zrs", tS[1]);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", rs);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("shgc_pjpy_xyrsEdit");
	}
	
	/**
	 * @describe 根据主键得到项目专业人数设置信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_zyrsEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyShgcActionForm pjpyShgcActionForm = (PjpyShgcActionForm)form;
		PjpyShgcService service = new PjpyShgcService();
		List<Object> rs = new ArrayList<Object>();
		String writeAble = "yes";// 写权限
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		
		String[] zyList = dao.getArray(
				"select zydm from view_xsjbxx where xydm=? group by zydm", new String[] {xydm},
				"zydm");
		
		int i = 0;
		String[] sqlT = new String[zyList.length];
		for (String zydm : zyList){
			String num = dao
					.getOneRs(
							"select count(*) num from pjpy_shgc_zyrsb where bbdm=? and zydm=?",
							new String[] { bbdm, zydm }, "num");
			if ("0".equalsIgnoreCase(num)){
				sqlT[i] = "insert into pjpy_shgc_zyrsb(bbdm,zydm) values('"+bbdm+"','"+zydm+"')";
				i++;
			}
		}
		dao.runBatch(sqlT);
		
		if ("save".equalsIgnoreCase(act)) {
			service.saveJxjzyrs(pjpyShgcActionForm.getPks(), Arrays2
					.toGBKArrays(request.getParameterValues("szrs")), request);
			request.setAttribute("end", "end");
		}
		
		String sql = "select bbdm||zydm pks,bbdm,zydm,a.zymc,a.zyrs,a.szrs,a.yfprs from view_pjpy_shgc_zyrsb a where a.bbdm=? and (select x.bmdm from bks_zydm x where x.zydm=a.zydm)=?";
		rs.addAll(dao.getArrayList(sql, new String[] { bbdm,xydm }, new String[] {
				"pks", "bbdm", "zydm", "zymc", "zyrs", "szrs", "yfprs" }));

		String[] tS = dao.getOneRs(
				"select bbmc,szrs zrs,xymc from view_pjpy_shgc_xyrsb where bbdm=? and xydm=?",
				new String[] { bbdm, xydm }, new String[] { "bbmc", "zrs", "xymc" });
		request.setAttribute("bbmc", tS[0]);
		request.setAttribute("zrs", tS[1]);
		request.setAttribute("xymc", tS[2]);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", rs);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("bbdm", bbdm);
		request.setAttribute("xydm", xydm);
		return mapping.findForward("shgc_pjpy_zyrsEdit");
	}
	
	/**
	 * @describe 根据主键得到项目班级人数设置信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_bjrsEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyShgcActionForm pjpyShgcActionForm = (PjpyShgcActionForm)form;
		PjpyShgcService service = new PjpyShgcService();
		List<Object> rs = new ArrayList<Object>();
		String writeAble = "yes";// 写权限
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		
		String[] bjList = dao.getArray(
				"select bjdm from view_xsjbxx where zydm=? group by bjdm", new String[] {zydm},
				"bjdm");
		
		int i = 0;
		String[] sqlT = new String[bjList.length];
		for (String bjdm : bjList){
			String num = dao
					.getOneRs(
							"select count(*) num from pjpy_shgc_bjrsb where bbdm=? and bjdm=?",
							new String[] { bbdm, bjdm }, "num");
			if ("0".equalsIgnoreCase(num)){
				sqlT[i] = "insert into pjpy_shgc_bjrsb(bbdm,bjdm) values('"+bbdm+"','"+bjdm+"')";
				i++;
			}
		}
		dao.runBatch(sqlT);
		
		if ("save".equalsIgnoreCase(act)) {
			service.saveJxjbjrs(pjpyShgcActionForm.getPks(), Arrays2
					.toGBKArrays(request.getParameterValues("szrs")), request);
			request.setAttribute("end", "end");
		}
		
		String sql = "select bbdm||bjdm pks,bbdm,bjdm,a.bjmc,a.bjrs,a.szrs from view_pjpy_shgc_bjrsb a where a.bbdm=? and (select x.zydm from bks_bjdm x where x.bjdm=a.bjdm)=?";
		rs.addAll(dao.getArrayList(sql, new String[] { bbdm, zydm }, new String[] {
				"pks", "bbdm", "bjdm", "bjmc", "bjrs", "szrs" }));

		String[] tS = dao.getOneRs(
				"select bbmc,szrs zrs,zymc from view_pjpy_shgc_zyrsb where bbdm=? and zydm=?",
				new String[] { bbdm, zydm }, new String[] { "bbmc", "zrs", "zymc" });
		request.setAttribute("bbmc", tS[0]);
		request.setAttribute("zrs", tS[1]);
		request.setAttribute("zymc", tS[2]);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", rs);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("bbdm", bbdm);
		request.setAttribute("zydm", zydm);
		return mapping.findForward("shgc_pjpy_bjrsEdit");
	}
	
	/**
	 * @describe 项目人数信息查询
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_fprsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String[] colList = new String[] { "bbmc", "szrs" };

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "%", 1);
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
		}

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xydm", "%".equalsIgnoreCase(xydm) ? "" : xydm);
		
		String sql = "select bbmc,zrs from view_pjpy_shgc_rswfzb where bbdm=?";
		String[] xmT = dao.getOneRs(sql, new String[] { xmdm }, new String[] {
				"bbmc", "zrs" });
		
		if (null != xmT) {
			request.setAttribute("bbmc", xmT[0]);
			request.setAttribute("zrs", xmT[1]);

			dao.getProVal("{call shgcpjpyrsxx(?,?,?,?)}", new String[] {
					userType, userDep, xydm, xmdm }, new int[] {});

			rs
					.addAll(dao
							.rsToVator(
									"select bmmc,gdrs from shgc_pjpy_szrsxxtem order by to_number(xssx)",
									new String[] {}, new String[] { "bmmc",
											"gdrs" }));
		} else {
			request.setAttribute("isNull", "is");
		}

		String[] colListCN = new String[] { "部门名称", "规定人数" };
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmListAll());
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("userType", userType);
		return mapping.findForward("shgc_pjpy_fprsxx");
	}
	
	/**
	 * @describe 项目人数信息导出
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_fprsxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "%", 1);
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
		}
		List<Object> rs = new ArrayList<Object>();
		
		dao.getProVal("{call shgcpjpyrsxx(?,?,?,?)}", new String[] {
				userType, userDep, xydm, xmdm }, new int[] {});
		
		String sql = "select bmmc,gdrs from shgc_pjpy_szrsxxtem order by to_number(xssx)";
		
		String[] colList = dao.getColumnName("select bmmc,gdrs from shgc_pjpy_szrsxxtem where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "shgc_pjpy_szrsxxtem");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("shgc_pjpy_fprsxxExp");
	}

	/**
	 * @describe 奖学金统计
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_jxjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String[] colList = new String[] { "bmmc", "jxjmc", "hjrs" };

		String tjlx = Base.chgNull(request.getParameter("tjlx"), "", 1);
		String jxjdm = Base.chgNull(request.getParameter("jxjdm"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xq = Base.chgNull(request.getParameter("xq"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
		}

		List<Object> rs = new ArrayList<Object>();
		
		dao.getProVal("{call Pro_ShgcPjpyRstj(?,?,?,?,?,?,?,?)}", new String[] {
				tjlx, "".equalsIgnoreCase(jxjdm) ? "%" : jxjdm,
				"".equalsIgnoreCase(xydm) ? "%" : xydm,
				"".equalsIgnoreCase(zydm) ? "%" : zydm,
				"".equalsIgnoreCase(bjdm) ? "%" : bjdm,
				"".equalsIgnoreCase(nd) ? "%" : nd,
				"".equalsIgnoreCase(xq) ? "%" : xq,
				"".equalsIgnoreCase(nj) ? "%" : nj }, new int[] {});

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs
					.addAll(dao
							.rsToVator(
									"select bmmc,jxjmc,hjrs from shgc_pjpy_jxjtjtemp order by to_number(xssx)",
									new String[] {}, new String[] { "bmmc",
											"jxjmc", "hjrs" }));
		}

		String[] colListCN = new String[] { "部门名称", "奖学金名称", "获奖人数" };
		List topTr = dao.arrayToList(colList, colListCN);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tjlx", tjlx);
		map.put("jxjdm", jxjdm);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("nd", nd);
		map.put("xq", xq);
		map.put("nj", nj);
		String zyKey = xydm==null ? "":xydm;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmListAll());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("ndList", Base.getXnndList());//年度列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("userType", userType);
		return mapping.findForward("shgc_pjpy_jxjtj");
	}
	
	/**
	 * @describe 奖学金统计信息导出
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgc_pjpy_jxjtjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		List<Object> rs = new ArrayList<Object>();
		
		String[] colList = dao.getColumnName("select bmmc,jxjmc,hjrs from shgc_pjpy_jxjtjtemp where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator("select bmmc,jxjmc,hjrs from shgc_pjpy_jxjtjtemp order by to_number(xssx)", new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "shgc_pjpy_jxjtjtemp");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("shgc_pjpy_jxjtjExp");
	}
}
