/*
 * 创建日期 2007-11-06 zhoumi
 *
 */
package xgxt.action.shgc;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.XszzForm;
import xgxt.action.Base;
import xgxt.base.*;

/** 学生资助 */
public class XszzAction_zm extends Action {
	StandardOperation so = new StandardOperation();

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// String userType;

		// String userDep;

		// String sUName;

		// String logMsg;
		HttpSession session = request.getSession();
		try {
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			// boolean isStu = true;
			// userType = session.getAttribute("userType").toString();
			// isStu = (userType.equalsIgnoreCase("stu"));
			// sUName = session.getAttribute("userName").toString();
			// userDep = session.getAttribute("userDep").toString();
			String writeAble;
			int p = -1;

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			// String power = "";
			if (act.equalsIgnoreCase("auditing_shgc_zxdk")) {// 上海工程技术大学-助学贷款审核
				myActFwd = auditing_shgc_zxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shgc_zxdk_one")) {// 上海工程技术大学-助学贷款单个审核
				myActFwd = auditing_shgc_zxdk_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_lyjszxjsq")) {// 上海工程技术大学-龙元建设助学金审核
				myActFwd = auditing_shgc_lyjszxjsq(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_lyjszxjsq_one")) {// 上海工程技术大学-龙元建设助学金单个审核
				myActFwd = auditing_shgc_lyjszxjsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_xsxfjmsq")) {// 上海工程技术大学-学生学费减免审核
				myActFwd = auditing_shgc_xsxfjmsq(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_xsxfjmsq_one")) {// 上海工程技术大学-学生学费减免单个审核
				myActFwd = auditing_shgc_xsxfjmsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_hkxy")) {// 上海工程技术大学-还款协议审核
				myActFwd = auditing_shgc_hkxy(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shgc_hkxy_one")) {// 上海工程技术大学-还款协议单个审核
				myActFwd = auditing_shgc_hkxy_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("shgc_kns")) {// 上海工程技术大学-家庭经济困难学生认定
				myActFwd = shgc_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("shgc_knsb")) {// 上海工程技术大学-家庭经济困难学生认定表
				myActFwd = shgc_knsb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shgc_kns")) {// 上海工程技术大学-家庭经济困难学生认定审核
				myActFwd = auditing_shgc_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shgc_kns_one")) {// 上海工程技术大学-家庭经济困难学生认定单个审核
				myActFwd = auditing_shgc_kns_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_shgc_kns_knssjrd")) {// 上海工程技术大学-困难生认定时间设置
				myActFwd = auditing_shgc_kns_knssjrd(mapping, form, request,
						response);
			}
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "出现灾难性故障，" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
	}

	private ActionForward auditing_shgc_zxdk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String shzt = Base.chgNull(request.getParameter("shzt"), "", 1);
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zxdk_xssqb";
		pk = "xh||nd";
		tableName = "view_zxdk_xssqxx";
		String nd = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(shzt)) {
			if ("xy".equalsIgnoreCase(userType)) {
				querry.append(" and xysh='");
				querry.append(shzt);
				querry.append("' ");
			} else {
				querry.append(" and xxsh='");
				querry.append(shzt);
				querry.append("' ");
			}
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 助学贷款审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 助学贷款";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xb", "xymc", "JTSR", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.xz||'##OneSpile##'||a.xl||'##OneSpile##'||a.dkze||'##OneSpile##'||a.xfdks||'##OneSpile##'||a.shfdks||'##OneSpile##'||a.zsfdks||'##OneSpile##'||a.dkqxkssj||'##OneSpile##'||a.dkqxjssj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.dh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.jtsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.jtrjsr col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.xz||'##OneSpile##'||a.xl||'##OneSpile##'||a.dkze||'##OneSpile##'||a.xfdks||'##OneSpile##'||a.shfdks||'##OneSpile##'||a.zsfdks||'##OneSpile##'||a.dkqxkssj||'##OneSpile##'||a.dkqxjssj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.dh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.jtsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.jtrjsr col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xb", "xymc", "JTSR", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.xz||'##OneSpile##'||a.xl||'##OneSpile##'||a.dkze||'##OneSpile##'||a.xfdks||'##OneSpile##'||a.shfdks||'##OneSpile##'||a.zsfdks||'##OneSpile##'||a.dkqxkssj||'##OneSpile##'||a.dkqxjssj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.dh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.jtsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.jtrjsr col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.xz||'##OneSpile##'||a.xl||'##OneSpile##'||a.dkze||'##OneSpile##'||a.xfdks||'##OneSpile##'||a.shfdks||'##OneSpile##'||a.zsfdks||'##OneSpile##'||a.dkqxkssj||'##OneSpile##'||a.dkqxjssj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.dh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.jtsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.jtrjsr col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##csny##OneSpile##xxmc##OneSpile##nj##OneSpile##kh##OneSpile##xymc##OneSpile##xmc##OneSpile##xydm##OneSpile##zydm##OneSpile##bjdm##OneSpile##ssdh##OneSpile##xz##OneSpile##xl##OneSpile##dkze##OneSpile##xfdks##OneSpile##shfdks##OneSpile##zsfdks##OneSpile##dkqxkssj##OneSpile##dkqxjssj##OneSpile##jtzz##OneSpile##yzbm##OneSpile##dh##OneSpile##fqxm##OneSpile##fqzy##OneSpile##fqsfzh##OneSpile##mqxm##OneSpile##mqzy##OneSpile##mqsfzh##OneSpile##jtsr##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##xyshyj##OneSpile##jtrjsr";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		// sql = "select count(*) count from " + tableName + " a" + querry;
		// checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql,
		// new String[]{}, "count")));
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("shzt", shzt);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "xsjxjzxjsqb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_zxdk_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zxdk_xssqb where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zxdk_xssqb where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_zxdk.do?go=go", false);
			return newFwd;
		}
		
		if ("tg".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length*3];
			int x = 0;
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[x] = "update zxdk_xssqb set xysh='通过' where xh||nd='"+pkT+"'";
					x++;
				} else {
					sqlT[x] = "update zxdk_xssqb set xxsh='通过' where xh||nd='"+pkT+"'";
					x++;
					sql = "select a.xh, (select j.ksh from bks_xsjbxx j where a.xh=j.xh) ksh, a.xm, a.xb, (select s.csrq from view_stu_details s where s.xh=a.xh) csrq, a.xz, a.xl xslx, a.xxmc, a.xymc, a.xmc, a.ssdh sedh, a.jtsr, a.fqsfzh,  a.mqsfzh, a.jtzz, a.yzbm, a.fqxm, a.MQZY fqgzdw, a.mqxm, a.FQZY mqgzdw, (select j.rxny from bks_xsjbxx j where a.xh=j.xh) rxny, (select s.mzmc from view_stu_details s where s.xh=a.xh) mzmc, (select x.sfzh from view_xsjbxx x where x.xh=a.xh) sfzh, a.jtrjsr from view_zxdk_xssqxx a where a.xh||a.nd=?";
					String[] colName1 = new String[] { "xh", "ksh", "xm", "xb", "csrq", "xz", "xslx",
							"xxmc", "xymc", "xmc", "sedh", "jtsr", "fqsfzh", "mqsfzh",
							"jtzz", "yzbm", "fqxm", "fqgzdw", "mqxm", "mqgzdw", "rxny",
							"mzmc", "sfzh", "jtrjsr" };
					String tXh = pkT.substring(0, pkT.length()-4);
					String[] outVal = dao.getOneRs(sql, new String[] { pkT }, colName1);
					if(outVal != null){
						sqlT[x] = "delete zxdk_xsjbxx where xh='"+tXh+"'";
						x++;
						sqlT[x] = "insert into zxdk_xsjbxx(xh,ksh,xm,xb,csrq,xz,xslx,xxmc,xymc,xmc,sedh,jtsr,fqsfzh,mqsfzh,jtzz,yzbm,fqxm,fqgzdw,mqxm,mqgzdw,rxny,mzmc,sfzh,jtrjsr) values('"+outVal[0]+"','"+outVal[1]+"','"+outVal[2]+"','"+outVal[3]+"','"+outVal[4]+"','"+outVal[5]+"','"+outVal[6]+"','"+outVal[7]+"','"+outVal[8]+"','"+outVal[9]+"','"+outVal[10]+"','"+outVal[11]+"','"+outVal[12]+"','"+outVal[13]+"','"+outVal[14]+"','"+outVal[15]+"','"+outVal[16]+"','"+outVal[17]+"','"+outVal[18]+"','"+outVal[19]+"','"+outVal[20]+"','"+outVal[21]+"','"+outVal[22]+"','"+outVal[23]+"')";
						x++;
					}
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_zxdk.do?go=go", false);
			return newFwd;
		}
		
		if ("btg".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select xxsh from zxdk_xssqb where xh||nd=?";
					String tSh = dao.getOneRs(sql, new String[]{pkT}, "xxsh");
					if ((null != tSh) && (!"".equalsIgnoreCase(tSh))
							&& (!"通过".equalsIgnoreCase(tSh))) {
						sqlT[i] = "update zxdk_xssqb set xysh='不通过' where xh||nd='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update zxdk_xssqb set xxsh='不通过' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_zxdk.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zxdk_xssqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zxdk_xssqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
				if("通过".equalsIgnoreCase(yesNo)){
					ActionForward newFwd = new ActionForward(
							"/inputStu.do", false);
					return newFwd;
				}
			}
			if (ok) {
				logMsg = "修改(审核) zxdk_xssqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "zxdk_xssqb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,XH,XM,XB,CSNY,XXMC,XYMC,XMC,SSDH,XZ,XL,DKZE,XFDKS,"
					+ "SHFDKS,ZSFDKS,DKQXKSSJ,DKQXJSSJ,JTZZ,YZBM,DH,FQXM,FQZY,"
					+ "FQSFZH,MQXM,MQZY,MQSFZH,JTSR,SQSJ,ND,XXSHYJ,XYSHYJ,kh,JTRJSR,xxsh,"
					+ "XYSH yesNo from view_zxdk_xssqxx where " + pk + "='"
					+ pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,XH,XM,XB,CSNY,XXMC,XYMC,XMC,SSDH,XZ,XL,DKZE,XFDKS,"
					+ "SHFDKS,ZSFDKS,DKQXKSSJ,DKQXJSSJ,JTZZ,YZBM,DH,FQXM,FQZY,"
					+ "FQSFZH,MQXM,MQZY,MQSFZH,JTSR,SQSJ,ND,XXSHYJ,XYSHYJ,kh,JTRJSR,xxsh,"
					+ "XXSH yesNo from view_zxdk_xssqxx where " + pk + "='"
					+ pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "XH", "XM", "XB", "CSNY", "XXMC",
				"XYMC", "XMC", "SSDH", "XZ", "XL", "DKZE", "XFDKS", "SHFDKS",
				"ZSFDKS", "DKQXKSSJ", "DKQXJSSJ", "JTZZ", "YZBM", "DH", "FQXM",
				"FQZY", "FQSFZH", "MQXM", "MQZY", "MQSFZH", "JTSR", "SQSJ",
				"ND", "XXSHYJ", "XYSHYJ", "kh", "JTRJSR", "xxsh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "xnmz_knssq");
		request.setAttribute("act", "lstdsq");
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_lyjszxjsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "xsjxjzxjsqb";
		pk = "xh||nd||jzxjmc";
		tableName = "view_xsjxjzxjsq";
		String nd = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry1.append(" and JZXJMC='lyjszxjsq' ");
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 龙元建设助学金审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 龙元建设助学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh",
					"xm", "xb", "bjmc", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jzxjmc pk3,a.* from "
						+ tableName
						+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jzxjzwmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jzxjmc||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.nj||'##OneSpile##'||a.gkfs||'##OneSpile##'||a.bygz||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kh||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xyshrxm col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jzxjmc pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jzxjzwmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jzxjmc||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.nj||'##OneSpile##'||a.gkfs||'##OneSpile##'||a.bygz||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kh||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xyshrxm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh",
					"xm", "xb", "bjmc", "JTYZSR", "JTRJSR", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jzxjmc pk3,a.* from "
						+ tableName
						+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jzxjzwmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jzxjmc||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.nj||'##OneSpile##'||a.gkfs||'##OneSpile##'||a.bygz||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kh||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xyshrxm col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jzxjmc pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jzxjzwmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jzxjmc||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.nj||'##OneSpile##'||a.gkfs||'##OneSpile##'||a.bygz||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kh||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xyshrxm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##nd##OneSpile##csny##OneSpile##rxny##OneSpile##byny##OneSpile##lxdh##OneSpile##jzxjzwmc##OneSpile##sfzh##OneSpile##xb##OneSpile##bjmc##OneSpile##xy##OneSpile##mzmc##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdz##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdz##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdz##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdz##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdz##OneSpile##sfkns##OneSpile##sqsj##OneSpile##jzxjmc##OneSpile##jlxx##OneSpile##hkrs##OneSpile##jtyzsr##OneSpile##jtrjsr##OneSpile##jtsrly##OneSpile##jtzz##OneSpile##sqly##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##yzbm##OneSpile##radjthk##OneSpile##xmc##OneSpile##xxmc##OneSpile##xysh##OneSpile##xxsh##OneSpile##xydm##OneSpile##zydm##OneSpile##bjdm##OneSpile##nj##OneSpile##gkfs##OneSpile##bygz##OneSpile##kc1_mc##OneSpile##kc1_cj##OneSpile##kc2_mc##OneSpile##kc2_cj##OneSpile##kc3_mc##OneSpile##kc3_cj##OneSpile##kc4_mc##OneSpile##kc4_cj##OneSpile##kc5_mc##OneSpile##kc5_cj##OneSpile##kc6_mc##OneSpile##kc6_cj##OneSpile##kc7_mc##OneSpile##kc7_cj##OneSpile##kc8_mc##OneSpile##kc8_cj##OneSpile##kc9_mc##OneSpile##kc9_cj##OneSpile##kc10_mc##OneSpile##kc10_cj##OneSpile##kc11_mc##OneSpile##kc11_cj##OneSpile##kc12_mc##OneSpile##kc12_cj##OneSpile##kh##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyzzfzryj##OneSpile##fdysh##OneSpile##fdyshyj##OneSpile##xyshrxm";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		// sql = "select count(*) count from " + tableName + " a" + querry;
		// checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql,
		// new String[]{}, "count")));
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "xsjxjzxjsqb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_lyjszxjsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete xsjxjzxjsqb where xh||nd||jzxjmc='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete xsjxjzxjsqb where xh||nd||jzxjmc='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_lyjszxjsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("xsjxjzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd||jzxjmc", pkVal, request);
			} else {
				ok = StandardOperation.update("xsjxjzxjsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd||jzxjmc", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) xsjxjzxjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "xsjxjzxjsqb";
		pk = "xh||nd||jzxjmc";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,xm,nd,csny,rxny,sfzh,xb,bjmc,xy,mzmc,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sfkns,sqsj,jzxjmc,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,"
					+ "jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,xmc,xxmc,kh,xxsh,XYSH yesNo from view_xsjxjzxjsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xm,nd,csny,rxny,sfzh,xb,bjmc,xy,mzmc,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sfkns,sqsj,jzxjmc,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,"
					+ "jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,xmc,xxmc,kh,xxsh,XXSH yesNo from view_xsjxjzxjsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xm", "nd", "csny", "rxny",
				"sfzh", "xb", "bjmc", "xy", "mzmc", "jtcy1_xm", "jtcy1_nl",
				"jtcy1_gx", "jtcy1_gzdz", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdz",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdz", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdz", "sfkns", "sqsj",
				"jzxjmc", "jlxx", "hkrs", "jtyzsr", "jtrjsr", "jtsrly", "jtzz",
				"sqly", "xyshyj", "xxshyj", "yzbm", "radjthk", "xmc", "xxmc",
				"kh", "xxsh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "xsjxjzxjsqb");
		request.setAttribute("act", "lstdsq");
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_xsxfjmsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "xsxfjmspb";
		pk = "xh||nd";
		tableName = "view_xsxfjmsq";
		String nd = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 学生学费减免审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 学生学费减免";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xb", "bjmc", "lxdh", "xysh", "njmje", "xxsh", "jmje",
					"sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xrzw||'##OneSpile##'||a.qs||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.yxsbzdkqk||'##OneSpile##'||a.zxcjqgzxqk||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.njmje||'##OneSpile##'||a.jmje||'##OneSpile##'||a.njmjedx||'##OneSpile##'||a.jmjedx||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xrzw||'##OneSpile##'||a.qs||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.yxsbzdkqk||'##OneSpile##'||a.zxcjqgzxqk||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.njmje||'##OneSpile##'||a.jmje||'##OneSpile##'||a.njmjedx||'##OneSpile##'||a.jmjedx||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xb", "bjmc", "lxdh", "sqsj", "", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xrzw||'##OneSpile##'||a.qs||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.yxsbzdkqk||'##OneSpile##'||a.zxcjqgzxqk||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.njmje||'##OneSpile##'||a.jmje||'##OneSpile##'||a.njmjedx||'##OneSpile##'||a.jmjedx||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 2] = "jmje";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.nd||'##OneSpile##'||a.csny||'##OneSpile##'||a.nj||'##OneSpile##'||a.kh||'##OneSpile##'||a.xrzw||'##OneSpile##'||a.qs||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xb||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.xy||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.yxsbzdkqk||'##OneSpile##'||a.zxcjqgzxqk||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sfkns||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xmc||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.njmje||'##OneSpile##'||a.jmje||'##OneSpile##'||a.njmjedx||'##OneSpile##'||a.jmjedx||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by xysh desc";
				colList[colList.length - 2] = "njmje";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##nd##OneSpile##csny##OneSpile##nj##OneSpile##kh##OneSpile##xrzw##OneSpile##qs##OneSpile##lxdh##OneSpile##sfzh##OneSpile##xb##OneSpile##bjmc##OneSpile##xy##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##yxsbzdkqk##OneSpile##zxcjqgzxqk##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_ysr##OneSpile##jtcy1_gzdz##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_ysr##OneSpile##jtcy2_gzdz##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_ysr##OneSpile##jtcy3_gzdz##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_ysr##OneSpile##jtcy4_gzdz##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_ysr##OneSpile##jtcy5_gzdz##OneSpile##sqsj##OneSpile##jtzz##OneSpile##sqly##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##sfkns##OneSpile##yzbm##OneSpile##xmc##OneSpile##xxmc##OneSpile##xysh##OneSpile##xxsh##OneSpile##njmje##OneSpile##jmje##OneSpile##njmjedx##OneSpile##jmjedx##OneSpile##xydm##OneSpile##zydm##OneSpile##bjdm";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		// sql = "select count(*) count from " + tableName + " a" + querry;
		// checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql,
		// new String[]{}, "count")));
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "xsxfjmspb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_xsxfjmsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String njmje = DealString.toGBK(request.getParameter("njmje"));
		String jmje = DealString.toGBK(request.getParameter("jmje"));
		if ((null == njmje) || ("".equalsIgnoreCase(njmje))) {
			njmje = "0";
		}
		if ((null == jmje) || ("".equalsIgnoreCase(jmje))) {
			jmje = "0";
		}
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		String[] njmjedxT = dao.getProVal(sqlT, new String[] { njmje },
				new int[] { 2 });
		String njmjedx = njmjedxT[0];
		String[] jmjedxT = dao.getProVal(sqlT, new String[] { jmje },
				new int[] { 2 });
		String jmjedx = jmjedxT[0];

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlST = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlST[i] = "delete xsxfjmspb where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlST[i] = "delete xsxfjmspb where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlST);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_xsxfjmsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("xsxfjmspb", new String[] {
						"xysh", "xyshyj", "njmje", "njmjedx" }, new String[] {
						yesNo, xyshyj, njmje, njmjedx }, "xh||nd", pkVal,
						request);
			} else {
				ok = StandardOperation
						.update("xsxfjmspb", new String[] { "xxsh", "xxshyj",
								"jmje", "jmjedx" }, new String[] { yesNo,
								xxshyj, jmje, jmjedx }, "xh||nd", pkVal,
								request);
			}
			if (ok) {
				logMsg = "修改(审核) xsxfjmspb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "xsxfjmspb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,XH,XM,ND,CSNY,XRZW,QS,LXDH,SFZH,XB,BJMC,XY,MZMC,ZZMMMC,YXSBZDKQK,ZXCJQGZXQK,"
					+ "JTCY1_XM,JTCY1_NL,JTCY1_GX,JTCY1_YSR,JTCY1_GZDZ,"
					+ "JTCY2_XM,JTCY2_NL,JTCY2_GX,JTCY2_YSR,JTCY2_GZDZ,"
					+ "JTCY3_XM,JTCY3_NL,JTCY3_GX,JTCY3_YSR,JTCY3_GZDZ,"
					+ "JTCY4_XM,JTCY4_NL,JTCY4_GX,JTCY4_YSR,JTCY4_GZDZ,"
					+ "JTCY5_XM,JTCY5_NL,JTCY5_GX,JTCY5_YSR,JTCY5_GZDZ,"
					+ "SQSJ,JTZZ,SQLY,XYSHYJ,XXSHYJ,YZBM,XMC,XXMC,"
					+ "NJMJE,JMJE,njmjedx,jmjedx,kh,xxsh,XYSH yesNo from view_xsxfjmsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,XH,XM,ND,CSNY,XRZW,QS,LXDH,SFZH,XB,BJMC,XY,MZMC,ZZMMMC,YXSBZDKQK,ZXCJQGZXQK,"
					+ "JTCY1_XM,JTCY1_NL,JTCY1_GX,JTCY1_YSR,JTCY1_GZDZ,"
					+ "JTCY2_XM,JTCY2_NL,JTCY2_GX,JTCY2_YSR,JTCY2_GZDZ,"
					+ "JTCY3_XM,JTCY3_NL,JTCY3_GX,JTCY3_YSR,JTCY3_GZDZ,"
					+ "JTCY4_XM,JTCY4_NL,JTCY4_GX,JTCY4_YSR,JTCY4_GZDZ,"
					+ "JTCY5_XM,JTCY5_NL,JTCY5_GX,JTCY5_YSR,JTCY5_GZDZ,"
					+ "SQSJ,JTZZ,SQLY,XYSHYJ,XXSHYJ,YZBM,XMC,XXMC,"
					+ "NJMJE,JMJE,njmjedx,jmjedx,kh,xxsh,XXSH yesNo from view_xsxfjmsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xm", "nd", "csny", "xrzw", "qs",
				"lxdh", "sfzh", "xb", "bjmc", "xy", "mzmc", "zzmmmc",
				"yxsbzdkqk", "zxcjqgzxqk", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_ysr", "jtcy1_gzdz", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
				"jtcy2_ysr", "jtcy2_gzdz", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_ysr", "jtcy3_gzdz", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_ysr", "jtcy4_gzdz", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_ysr", "jtcy5_gzdz", "sqsj", "jtzz", "sqly", "xyshyj",
				"xxshyj", "yzbm", "xmc", "xxmc", "njmje", "jmje", "njmjedx",
				"jmjedx", "kh", "xxsh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "xsxfjmspb");
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_hkxy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		String shzt = Base.chgNull(request.getParameter("shzt"), "", 1);
		String hth = DealString.toGBK(checkForm.getHth());
		String sfzh = DealString.toGBK(checkForm.getSfzh());
		realTable = "zxdk_hkxy";
		pk = "xh||hth";
		tableName = "view_zxdk_hkxy";
		if (isNull(hth)) {
		} else {
			querry.append(" and hth='");
			querry.append(hth);
			querry.append("' ");
		}
		if (isNull(sfzh)) {
		} else {
			querry.append(" and sfzh='");
			querry.append(sfzh);
			querry.append("' ");
		}
		if (!isNull(shzt)) {
			if ("xy".equalsIgnoreCase(userType)) {
				querry.append(" and xysh='");
				querry.append(shzt);
				querry.append("' ");
			} else {
				querry.append(" and xxsh='");
				querry.append(shzt);
				querry.append("' ");
			}
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 还款协议审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 还款协议";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm",
					"sfzh", "hth", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.hth pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xl||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.poxm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gzdw||'##OneSpile##'||a.dwdh||'##OneSpile##'||a.dwdz||'##OneSpile##'||a.dwyzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.email||'##OneSpile##'||a.lxrxm||'##OneSpile##'||a.lxrxb||'##OneSpile##'||a.lxrcsrq||'##OneSpile##'||a.lxrgx||'##OneSpile##'||a.lxrdh||'##OneSpile##'||a.lxrdwdz||'##OneSpile##'||a.jtxxzz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.bz||'##OneSpile##'||a.hth||'##OneSpile##'||a.zhfkrq||'##OneSpile##'||a.fkzje||'##OneSpile##'||a.lxsj||'##OneSpile##'||a.lxyy||'##OneSpile##'||a.hkkssj||'##OneSpile##'||a.hkfs1||'##OneSpile##'||a.hkfs2||'##OneSpile##'||a.hkcs||'##OneSpile##'||a.hkqx||'##OneSpile##'||a.hkjssj||'##OneSpile##'||a.zffm||'##OneSpile##'||a.zfh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.hth pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xl||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.poxm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gzdw||'##OneSpile##'||a.dwdh||'##OneSpile##'||a.dwdz||'##OneSpile##'||a.dwyzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.email||'##OneSpile##'||a.lxrxm||'##OneSpile##'||a.lxrxb||'##OneSpile##'||a.lxrcsrq||'##OneSpile##'||a.lxrgx||'##OneSpile##'||a.lxrdh||'##OneSpile##'||a.lxrdwdz||'##OneSpile##'||a.jtxxzz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.bz||'##OneSpile##'||a.hth||'##OneSpile##'||a.zhfkrq||'##OneSpile##'||a.fkzje||'##OneSpile##'||a.lxsj||'##OneSpile##'||a.lxyy||'##OneSpile##'||a.hkkssj||'##OneSpile##'||a.hkfs1||'##OneSpile##'||a.hkfs2||'##OneSpile##'||a.hkcs||'##OneSpile##'||a.hkqx||'##OneSpile##'||a.hkjssj||'##OneSpile##'||a.zffm||'##OneSpile##'||a.zfh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm",
					"sfzh", "hth", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.hth pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xl||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.poxm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gzdw||'##OneSpile##'||a.dwdh||'##OneSpile##'||a.dwdz||'##OneSpile##'||a.dwyzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.email||'##OneSpile##'||a.lxrxm||'##OneSpile##'||a.lxrxb||'##OneSpile##'||a.lxrcsrq||'##OneSpile##'||a.lxrgx||'##OneSpile##'||a.lxrdh||'##OneSpile##'||a.lxrdwdz||'##OneSpile##'||a.jtxxzz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.bz||'##OneSpile##'||a.hth||'##OneSpile##'||a.zhfkrq||'##OneSpile##'||a.fkzje||'##OneSpile##'||a.lxsj||'##OneSpile##'||a.lxyy||'##OneSpile##'||a.hkkssj||'##OneSpile##'||a.hkfs1||'##OneSpile##'||a.hkfs2||'##OneSpile##'||a.hkcs||'##OneSpile##'||a.hkqx||'##OneSpile##'||a.hkjssj||'##OneSpile##'||a.zffm||'##OneSpile##'||a.zfh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString() + " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.hth pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xl||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.poxm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gzdw||'##OneSpile##'||a.dwdh||'##OneSpile##'||a.dwdz||'##OneSpile##'||a.dwyzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.email||'##OneSpile##'||a.lxrxm||'##OneSpile##'||a.lxrxb||'##OneSpile##'||a.lxrcsrq||'##OneSpile##'||a.lxrgx||'##OneSpile##'||a.lxrdh||'##OneSpile##'||a.lxrdwdz||'##OneSpile##'||a.jtxxzz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.fqxm||'##OneSpile##'||a.fqzy||'##OneSpile##'||a.fqsfzh||'##OneSpile##'||a.mqxm||'##OneSpile##'||a.mqzy||'##OneSpile##'||a.mqsfzh||'##OneSpile##'||a.bz||'##OneSpile##'||a.hth||'##OneSpile##'||a.zhfkrq||'##OneSpile##'||a.fkzje||'##OneSpile##'||a.lxsj||'##OneSpile##'||a.lxyy||'##OneSpile##'||a.hkkssj||'##OneSpile##'||a.hkfs1||'##OneSpile##'||a.hkfs2||'##OneSpile##'||a.hkcs||'##OneSpile##'||a.hkqx||'##OneSpile##'||a.hkjssj||'##OneSpile##'||a.zffm||'##OneSpile##'||a.zfh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.bjdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##xxmc##OneSpile##nj##OneSpile##xymc##OneSpile##xmc##OneSpile##sfzh##OneSpile##xl##OneSpile##hyzk##OneSpile##poxm##OneSpile##lxdh##OneSpile##gzdw##OneSpile##dwdh##OneSpile##dwdz##OneSpile##dwyzbm##OneSpile##yddh##OneSpile##email##OneSpile##lxrxm##OneSpile##lxrxb##OneSpile##lxrcsrq##OneSpile##lxrgx##OneSpile##lxrdh##OneSpile##lxrdwdz##OneSpile##jtxxzz##OneSpile##jtyzbm##OneSpile##jtlxdh##OneSpile##fqxm##OneSpile##fqzy##OneSpile##fqsfzh##OneSpile##mqxm##OneSpile##mqzy##OneSpile##mqsfzh##OneSpile##bz##OneSpile##hth##OneSpile##zhfkrq##OneSpile##fkzje##OneSpile##lxsj##OneSpile##lxyy##OneSpile##hkkssj##OneSpile##hkfs1##OneSpile##hkfs2##OneSpile##hkcs##OneSpile##hkqx##OneSpile##hkjssj##OneSpile##zffm##OneSpile##zfh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##xydm##OneSpile##zydm##OneSpile##bjdm";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		// sql = "select count(*) count from " + tableName + " a" + querry;
		// checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql,
		// new String[]{}, "count")));
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("sfzh", sfzh);
		map.put("hth", hth);
		map.put("xh", xh);
		map.put("shzt", shzt);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zxdk_hkxy");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_hkxy_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zxdk_hkxy where xh||hth='"+pkT+"' and xxsh='通过'";
				} else {
					sqlT[i] = "delete zxdk_hkxy where xh||hth='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_xsxfjmsq.do?go=go", false);
			return newFwd;
		}
		
		if ("tg".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zxdk_hkxy set xysh='通过' where xh||hth='"+pkT+"'";
				} else {
					sqlT[i] = "update zxdk_hkxy set xxsh='通过' where xh||hth='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_xsxfjmsq.do?go=go", false);
			return newFwd;
		}
		
		if ("btg".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select xxsh from zxdk_hkxy where xh||hth=?";
					String tSh = dao.getOneRs(sql, new String[]{pkT}, "xxsh");
					if ((null != tSh) && (!"".equalsIgnoreCase(tSh))
							&& ("通过".equalsIgnoreCase(tSh))) {
						sqlT[i] = "update zxdk_hkxy set xysh='不通过' where xh||hth='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update zxdk_hkxy set xxsh='不通过' where xh||hth='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_xsxfjmsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zxdk_hkxy", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||hth", pkVal, request);
			} else {
				ok = StandardOperation.update("zxdk_hkxy", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||hth", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) zxdk_hkxy";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "zxdk_hkxy";
		pk = "xh||hth";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select " + pk
					+ " pk,xh,xm,xb,csrq,xxmc,xymc,xmc,sfzh,xl,hyzk,poxm,lxdh,"
					+ "gzdw,dwdh,dwdz,dwyzbm,yddh,email,lxrxm,lxrxb,lxrcsrq,"
					+ "lxrgx,lxrdh,lxrdwdz,jtxxzz,jtyzbm,jtlxdh,fqxm,fqzy,"
					+ "fqsfzh,mqxm,mqzy,mqsfzh,bz,hth,zhfkrq,fkzje,lxsj,lxyy,"
					+ "hkkssj,hkfs1,hkfs2,hkcs,hkqx,hkjssj,zffm ,zfh,sqsj,"
					+ "xyshyj,xxshyj,xxsh,xysh yesNo from view_zxdk_hkxy where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select " + pk
					+ " pk,xh,xm,xb,csrq,xxmc,xymc,xmc,sfzh,xl,hyzk,poxm,lxdh,"
					+ "gzdw,dwdh,dwdz,dwyzbm,yddh,email,lxrxm,lxrxb,lxrcsrq,"
					+ "lxrgx,lxrdh,lxrdwdz,jtxxzz,jtyzbm,jtlxdh,fqxm,fqzy,"
					+ "fqsfzh,mqxm,mqzy,mqsfzh,bz,hth,zhfkrq,fkzje,lxsj,lxyy,"
					+ "hkkssj,hkfs1,hkfs2,hkcs,hkqx,hkjssj,zffm,zfh,sqsj,"
					+ "xyshyj,xxshyj,xxsh,xxsh yesNo from view_zxdk_hkxy where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xm", "xb", "csrq", "xxmc",
				"xymc", "xmc", "sfzh", "xl", "hyzk", "poxm", "lxdh", "gzdw",
				"dwdh", "dwdz", "dwyzbm", "yddh", "email", "lxrxm", "lxrxb",
				"lxrcsrq", "lxrgx", "lxrdh", "lxrdwdz", "jtxxzz", "jtyzbm",
				"jtlxdh", "fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh",
				"bz", "hth", "zhfkrq", "fkzje", "lxsj", "lxyy", "hkkssj",
				"hkfs1", "hkfs2", "hkcs", "hkqx", "hkjssj", "zffm", "zfh",
				"sqsj", "xyshyj", "xxshyj", "xxsh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zxdk_hkxy");
		return mapping.findForward("success");
	}

	private ActionForward shgc_kns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
//		userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "shgc_knsxx" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,"
				+ "yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,"
				+ "mzbm_lxdh,xysh,xyshyj,xxsh,xxshyj,sqsj,syd,rdsj "
				+ "from view_shgc_knsxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

//		String knsrs = "";
		String sfksq = "-1";
		String pkVal = "";
		String jtlxdh1 = "";
		String jtlxdh2 = "";
		String mzbm_lxdh1 = "";
		String mzbm_lxdh2 = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			String sql1 = "select a.kssj,a.jssj from view_shgc_gjzxdk_sjb a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and b.xh=? and a.xmmc='困难生'";// ,nd
				jxjksjssj = dao.getOneRs(sql1, new String[] { xh },
						new String[] { "kssj", "jssj" });

			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String lxdh = DealString.toGBK(request.getParameter("lxdh")
							.toString());
					String rxqhk = DealString.toGBK(request.getParameter(
							"rxqhk").toString());
					String jtzz = DealString.toGBK(request.getParameter("jtzz")
							.toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
							.toString());
					jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
							.toString());
					if((null == jtlxdh1) || ("".equalsIgnoreCase(jtlxdh1))){
						jtlxdh1 = " ";
					}
					if((null == jtlxdh2) || ("".equalsIgnoreCase(jtlxdh2))){
						jtlxdh2 = " ";
					}
					String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
					String sfyycjcshzyhd = DealString.toGBK(request
							.getParameter("sfyycjcshzyhd").toString());
					String sfyysqgjzxdkhqgzx = DealString.toGBK(request
							.getParameter("sfyysqgjzxdkhqgzx").toString());
					String sfjq = DealString.toGBK(request.getParameter("sfjq")
							.toString());
					String sfge = DealString.toGBK(request.getParameter("sfge")
							.toString());
					String sfdq = DealString.toGBK(request.getParameter("sfdq")
							.toString());
					String sfcj = DealString.toGBK(request.getParameter("sfcj")
							.toString());
					String sfjls = DealString.toGBK(request.getParameter(
							"sfjls").toString());
					String sfly = DealString.toGBK(request.getParameter("sfly")
							.toString());
					String sfzb = DealString.toGBK(request.getParameter("sfzb")
							.toString());
					String jtcy1_xm = DealString.toGBK(request.getParameter(
							"jtcy1_xm").toString());
					String jtcy1_nl = DealString.toGBK(request.getParameter(
							"jtcy1_nl").toString());
					String jtcy1_gx = DealString.toGBK(request.getParameter(
							"jtcy1_gx").toString());
					String jtcy1_gzdw = DealString.toGBK(request.getParameter(
							"jtcy1_gzdw").toString());
					String jtcy1_zy = DealString.toGBK(request.getParameter(
							"jtcy1_zy").toString());
					String jtcy1_nsr = DealString.toGBK(request.getParameter(
							"jtcy1_nsr").toString());
					String jtcy1_jkzk = DealString.toGBK(request.getParameter(
							"jtcy1_jkzk").toString());
					String jtcy2_xm = DealString.toGBK(request.getParameter(
							"jtcy2_xm").toString());
					String jtcy2_nl = DealString.toGBK(request.getParameter(
							"jtcy2_nl").toString());
					String jtcy2_gx = DealString.toGBK(request.getParameter(
							"jtcy2_gx").toString());
					String jtcy2_gzdw = DealString.toGBK(request.getParameter(
							"jtcy2_gzdw").toString());
					String jtcy2_zy = DealString.toGBK(request.getParameter(
							"jtcy2_zy").toString());
					String jtcy2_nsr = DealString.toGBK(request.getParameter(
							"jtcy2_nsr").toString());
					String jtcy2_jkzk = DealString.toGBK(request.getParameter(
							"jtcy2_jkzk").toString());
					String jtcy3_xm = DealString.toGBK(request.getParameter(
							"jtcy3_xm").toString());
					String jtcy3_nl = DealString.toGBK(request.getParameter(
							"jtcy3_nl").toString());
					String jtcy3_gx = DealString.toGBK(request.getParameter(
							"jtcy3_gx").toString());
					String jtcy3_gzdw = DealString.toGBK(request.getParameter(
							"jtcy3_gzdw").toString());
					String jtcy3_zy = DealString.toGBK(request.getParameter(
							"jtcy3_zy").toString());
					String jtcy3_nsr = DealString.toGBK(request.getParameter(
							"jtcy3_nsr").toString());
					String jtcy3_jkzk = DealString.toGBK(request.getParameter(
							"jtcy3_jkzk").toString());
					String jtcy4_xm = DealString.toGBK(request.getParameter(
							"jtcy4_xm").toString());
					String jtcy4_nl = DealString.toGBK(request.getParameter(
							"jtcy4_nl").toString());
					String jtcy4_gx = DealString.toGBK(request.getParameter(
							"jtcy4_gx").toString());
					String jtcy4_gzdw = DealString.toGBK(request.getParameter(
							"jtcy4_gzdw").toString());
					String jtcy4_zy = DealString.toGBK(request.getParameter(
							"jtcy4_zy").toString());
					String jtcy4_nsr = DealString.toGBK(request.getParameter(
							"jtcy4_nsr").toString());
					String jtcy4_jkzk = DealString.toGBK(request.getParameter(
							"jtcy4_jkzk").toString());
					String jtcy5_xm = DealString.toGBK(request.getParameter(
							"jtcy5_xm").toString());
					String jtcy5_nl = DealString.toGBK(request.getParameter(
							"jtcy5_nl").toString());
					String jtcy5_gx = DealString.toGBK(request.getParameter(
							"jtcy5_gx").toString());
					String jtcy5_gzdw = DealString.toGBK(request.getParameter(
							"jtcy5_gzdw").toString());
					String jtcy5_zy = DealString.toGBK(request.getParameter(
							"jtcy5_zy").toString());
					String jtcy5_nsr = DealString.toGBK(request.getParameter(
							"jtcy5_nsr").toString());
					String jtcy5_jkzk = DealString.toGBK(request.getParameter(
							"jtcy5_jkzk").toString());
					String jtrjnsr = DealString.toGBK(request.getParameter(
							"jtrjnsr").toString());
					String xszbdszqk = DealString.toGBK(request.getParameter(
							"xszbdszqk").toString());
					String jtzszrzhqk = DealString.toGBK(request.getParameter(
							"jtzszrzhqk").toString());
					String jtzstfywsj = DealString.toGBK(request.getParameter(
							"jtzstfywsj").toString());
					String qtqk = DealString.toGBK(request.getParameter("qtqk")
							.toString());
					String mzbm_txdz = DealString.toGBK(request.getParameter(
							"mzbm_txdz").toString());
					String mzbm_yzbm = DealString.toGBK(request.getParameter(
							"mzbm_yzbm").toString());
					mzbm_lxdh1 = DealString.toGBK(request.getParameter(
							"mzbm_lxdh1").toString());
					mzbm_lxdh2 = DealString.toGBK(request.getParameter(
							"mzbm_lxdh2").toString());
					String syd = DealString.toGBK(request.getParameter(
							"syd").toString());
					if((null == mzbm_lxdh1) || ("".equalsIgnoreCase(mzbm_lxdh1))){
						mzbm_lxdh1 = " ";
					}
					if((null == mzbm_lxdh2) || ("".equalsIgnoreCase(mzbm_lxdh2))){
						mzbm_lxdh2 = " ";
					}
					String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					sql = "select xxsh from shgc_knsxx where xh||nd=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("特别困难") || temp[0]
									.equalsIgnoreCase("一般困难") || temp[0].equalsIgnoreCase("特殊"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("shgc_knsxx", "xh||nd", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd", "lxdh",
								"rxqhk", "jtzz", "yzbm", "jtlxdh",
								"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx", "sfjq",
								"sfge", "sfdq", "sfcj", "sfjls", "sfly",
								"sfzb", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
								"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
								"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
								"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
								"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm",
								"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
								"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
								"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
								"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
								"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
								"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
								"jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
								"xszbdszqk", "jtzszrzhqk", "jtzstfywsj",
								"qtqk", "mzbm_txdz", "mzbm_yzbm", "mzbm_lxdh", "syd" };

						valueForOut = new String[] { xh, nd, lxdh, rxqhk, jtzz,
								yzbm, jtlxdh, sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
								sfjq, sfge, sfdq, sfcj, sfjls, sfly, sfzb,
								jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
								jtcy1_zy, jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
								jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
								jtcy2_nsr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
								jtcy3_gx, jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
								jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
								jtcy4_gzdw, jtcy4_zy, jtcy4_nsr, jtcy4_jkzk,
								jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
								jtcy5_zy, jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
								xszbdszqk, jtzszrzhqk, jtzstfywsj, qtqk,
								mzbm_txdz, mzbm_yzbm, mzbm_lxdh, syd };

						boolean ok = false;
						ok = StandardOperation.insert("shgc_knsxx", colName1,
								valueForOut, request);
						if (ok) {
							logMsg = "申请" + titName;
							Base.log(request, logMsg, sUName);
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			xh = xszzForm.getXh();
			if (doType != null && doType.equalsIgnoreCase("add")) {
				xh = DealString.toGBK(request.getParameter("xh").toString());
				String lxdh = DealString.toGBK(request.getParameter("lxdh")
						.toString());
				String rxqhk = DealString.toGBK(request.getParameter("rxqhk")
						.toString());
				String jtzz = DealString.toGBK(request.getParameter("jtzz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
						.toString());
				jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
						.toString());
				if((null == jtlxdh1) || ("".equalsIgnoreCase(jtlxdh1))){
					jtlxdh1 = " ";
				}
				if((null == jtlxdh2) || ("".equalsIgnoreCase(jtlxdh2))){
					jtlxdh2 = " ";
				}
				String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
				String sfyycjcshzyhd = DealString.toGBK(request.getParameter(
						"sfyycjcshzyhd").toString());
				String sfyysqgjzxdkhqgzx = DealString.toGBK(request
						.getParameter("sfyysqgjzxdkhqgzx").toString());
				String sfjq = DealString.toGBK(request.getParameter("sfjq")
						.toString());
				String sfge = DealString.toGBK(request.getParameter("sfge")
						.toString());
				String sfdq = DealString.toGBK(request.getParameter("sfdq")
						.toString());
				String sfcj = DealString.toGBK(request.getParameter("sfcj")
						.toString());
				String sfjls = DealString.toGBK(request.getParameter("sfjls")
						.toString());
				String sfly = DealString.toGBK(request.getParameter("sfly")
						.toString());
				String sfzb = DealString.toGBK(request.getParameter("sfzb")
						.toString());
				String jtcy1_xm = DealString.toGBK(request.getParameter(
						"jtcy1_xm").toString());
				String jtcy1_nl = DealString.toGBK(request.getParameter(
						"jtcy1_nl").toString());
				String jtcy1_gx = DealString.toGBK(request.getParameter(
						"jtcy1_gx").toString());
				String jtcy1_gzdw = DealString.toGBK(request.getParameter(
						"jtcy1_gzdw").toString());
				String jtcy1_zy = DealString.toGBK(request.getParameter(
						"jtcy1_zy").toString());
				String jtcy1_nsr = DealString.toGBK(request.getParameter(
						"jtcy1_nsr").toString());
				String jtcy1_jkzk = DealString.toGBK(request.getParameter(
						"jtcy1_jkzk").toString());
				String jtcy2_xm = DealString.toGBK(request.getParameter(
						"jtcy2_xm").toString());
				String jtcy2_nl = DealString.toGBK(request.getParameter(
						"jtcy2_nl").toString());
				String jtcy2_gx = DealString.toGBK(request.getParameter(
						"jtcy2_gx").toString());
				String jtcy2_gzdw = DealString.toGBK(request.getParameter(
						"jtcy2_gzdw").toString());
				String jtcy2_zy = DealString.toGBK(request.getParameter(
						"jtcy2_zy").toString());
				String jtcy2_nsr = DealString.toGBK(request.getParameter(
						"jtcy2_nsr").toString());
				String jtcy2_jkzk = DealString.toGBK(request.getParameter(
						"jtcy2_jkzk").toString());
				String jtcy3_xm = DealString.toGBK(request.getParameter(
						"jtcy3_xm").toString());
				String jtcy3_nl = DealString.toGBK(request.getParameter(
						"jtcy3_nl").toString());
				String jtcy3_gx = DealString.toGBK(request.getParameter(
						"jtcy3_gx").toString());
				String jtcy3_gzdw = DealString.toGBK(request.getParameter(
						"jtcy3_gzdw").toString());
				String jtcy3_zy = DealString.toGBK(request.getParameter(
						"jtcy3_zy").toString());
				String jtcy3_nsr = DealString.toGBK(request.getParameter(
						"jtcy3_nsr").toString());
				String jtcy3_jkzk = DealString.toGBK(request.getParameter(
						"jtcy3_jkzk").toString());
				String jtcy4_xm = DealString.toGBK(request.getParameter(
						"jtcy4_xm").toString());
				String jtcy4_nl = DealString.toGBK(request.getParameter(
						"jtcy4_nl").toString());
				String jtcy4_gx = DealString.toGBK(request.getParameter(
						"jtcy4_gx").toString());
				String jtcy4_gzdw = DealString.toGBK(request.getParameter(
						"jtcy4_gzdw").toString());
				String jtcy4_zy = DealString.toGBK(request.getParameter(
						"jtcy4_zy").toString());
				String jtcy4_nsr = DealString.toGBK(request.getParameter(
						"jtcy4_nsr").toString());
				String jtcy4_jkzk = DealString.toGBK(request.getParameter(
						"jtcy4_jkzk").toString());
				String jtcy5_xm = DealString.toGBK(request.getParameter(
						"jtcy5_xm").toString());
				String jtcy5_nl = DealString.toGBK(request.getParameter(
						"jtcy5_nl").toString());
				String jtcy5_gx = DealString.toGBK(request.getParameter(
						"jtcy5_gx").toString());
				String jtcy5_gzdw = DealString.toGBK(request.getParameter(
						"jtcy5_gzdw").toString());
				String jtcy5_zy = DealString.toGBK(request.getParameter(
						"jtcy5_zy").toString());
				String jtcy5_nsr = DealString.toGBK(request.getParameter(
						"jtcy5_nsr").toString());
				String jtcy5_jkzk = DealString.toGBK(request.getParameter(
						"jtcy5_jkzk").toString());
				String jtrjnsr = DealString.toGBK(request.getParameter(
						"jtrjnsr").toString());
				String xszbdszqk = DealString.toGBK(request.getParameter(
						"xszbdszqk").toString());
				String jtzszrzhqk = DealString.toGBK(request.getParameter(
						"jtzszrzhqk").toString());
				String jtzstfywsj = DealString.toGBK(request.getParameter(
						"jtzstfywsj").toString());
				String qtqk = DealString.toGBK(request.getParameter("qtqk")
						.toString());
				String mzbm_txdz = DealString.toGBK(request.getParameter(
						"mzbm_txdz").toString());
				String mzbm_yzbm = DealString.toGBK(request.getParameter(
						"mzbm_yzbm").toString());
				mzbm_lxdh1 = DealString.toGBK(request
						.getParameter("mzbm_lxdh1").toString());
				mzbm_lxdh2 = DealString.toGBK(request
						.getParameter("mzbm_lxdh2").toString());
				String syd = DealString.toGBK(request.getParameter(
						"syd").toString());
				if((null == mzbm_lxdh1) || ("".equalsIgnoreCase(mzbm_lxdh1))){
					mzbm_lxdh1 = " ";
				}
				if((null == mzbm_lxdh2) || ("".equalsIgnoreCase(mzbm_lxdh2))){
					mzbm_lxdh2 = " ";
				}
				String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from shgc_knsxx where xh||nd=? and sysdate-to_date(rdsj,'yyyy-mm-dd')>=0 and sysdate-to_date(rdsj,'yyyy-mm-dd')<=365";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("特别困难") || temp[0]
								.equalsIgnoreCase("一般困难") || temp[0].equalsIgnoreCase("特殊"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("shgc_knsxx", "xh||nd", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "lxdh",
							"rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
							"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq",
							"sfcj", "sfjls", "sfly", "sfzb", "jtcy1_xm",
							"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
							"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
							"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
							"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
							"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
							"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
							"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
							"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
							"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
							"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
							"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
							"mzbm_lxdh", "syd" };

					valueForOut = new String[] { xh, nd, lxdh, rxqhk, jtzz,
							yzbm, jtlxdh, sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
							sfjq, sfge, sfdq, sfcj, sfjls, sfly, sfzb,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
							jtcy1_nsr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
							jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
							jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
							jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
							jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
							jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl,
							jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
							jtcy5_jkzk, jtrjnsr, xszbdszqk, jtzszrzhqk,
							jtzstfywsj, qtqk, mzbm_txdz, mzbm_yzbm, mzbm_lxdh, syd };

					boolean ok = false;
					ok = StandardOperation.insert("shgc_knsxx", colName1,
							valueForOut, request);
					if (ok) {
						logMsg = "申请" + titName;
						Base.log(request, logMsg, sUName);
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				}
			}
		}
		pkVal = request.getParameter("pkVal");
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,"
				+ "yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,"
				+ "mzbm_lxdh,xysh,xyshyj,xxsh,xxshyj,sqsj,syd,rdsj from "
				+ "view_shgc_knsxx where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "mzmc", "zzmmmc",
				"lxdh", "rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
				"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq", "sfcj", "sfjls",
				"sfly", "sfzb", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xszbdszqk", "jtzszrzhqk", "jtzstfywsj", "qtqk", "mzbm_txdz",
				"mzbm_yzbm", "mzbm_lxdh", "xysh", "xyshyj",
				"xxsh", "xxshyj", "sqsj", "syd", "rdsj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,"
						+ "b.mzmc,b.zzmmmc "
						+ "from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xymc", "zymc", "bjmc", "nj", "mzmc", "zzmmmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh", "xymc",
							"zymc", "bjmc", "nj", "mzmc", "zzmmmc" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
			}
		} else {
			int len1 = outString.length;
			int len2 = outValue.length;
			int max = 0;
			if (len1 >= len2) {
				max = len2;
			} else {
				max = len1;
			}
			for (int i = 0; i < max; i++) {
				if (null != outValue[i]) {
					map.put(outString[i], outValue[i]);
					if ("jtlxdh".equalsIgnoreCase(outString[i])) {
						String[] temS = outValue[i].split("-");
						map.put("jtlxdh1", temS[0]);
						map.put("jtlxdh2", temS[1]);
					}
					if ("mzbm_lxdh".equalsIgnoreCase(outString[i])) {
						String[] temS = outValue[i].split("-");
						map.put("mzbm_lxdh1", temS[0]);
						map.put("mzbm_lxdh2", temS[1]);
					}
				} else {
					map.put(outString[i], "");
				}
			}
		}
		
		request.setAttribute("sydList", dao.getArrayList("SELECT qxmc syd FROM dmk_qx  WHERE qxdm LIKE '__0000' ORDER BY qxdm", new String[]{}, new String[]{"syd"}));
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward shgc_knsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xxmc = StandardOperation.getXxmc();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String rxqhk = DealString.toGBK(request.getParameter("rxqhk")
				.toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
				.toString());
		String jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
				.toString());
		String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
		String sfyycjcshzyhd = DealString.toGBK(request.getParameter(
				"sfyycjcshzyhd").toString());
		String sfyysqgjzxdkhqgzx = DealString.toGBK(request.getParameter(
				"sfyysqgjzxdkhqgzx").toString());
		String sfjq = DealString.toGBK(request.getParameter("sfjq").toString());
		String sfge = DealString.toGBK(request.getParameter("sfge").toString());
		String sfdq = DealString.toGBK(request.getParameter("sfdq").toString());
		String sfcj = DealString.toGBK(request.getParameter("sfcj").toString());
		String sfjls = DealString.toGBK(request.getParameter("sfjls")
				.toString());
		String sfly = DealString.toGBK(request.getParameter("sfly").toString());
		String sfzb = DealString.toGBK(request.getParameter("sfzb").toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm")
				.toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl")
				.toString());
		String jtcy1_gx = DealString.toGBK(request.getParameter("jtcy1_gx")
				.toString());
		String jtcy1_gzdw = DealString.toGBK(request.getParameter("jtcy1_gzdw")
				.toString());
		String jtcy1_zy = DealString.toGBK(request.getParameter("jtcy1_zy")
				.toString());
		String jtcy1_nsr = DealString.toGBK(request.getParameter("jtcy1_nsr")
				.toString());
		String jtcy1_jkzk = DealString.toGBK(request.getParameter("jtcy1_jkzk")
				.toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm")
				.toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl")
				.toString());
		String jtcy2_gx = DealString.toGBK(request.getParameter("jtcy2_gx")
				.toString());
		String jtcy2_gzdw = DealString.toGBK(request.getParameter("jtcy2_gzdw")
				.toString());
		String jtcy2_zy = DealString.toGBK(request.getParameter("jtcy2_zy")
				.toString());
		String jtcy2_nsr = DealString.toGBK(request.getParameter("jtcy2_nsr")
				.toString());
		String jtcy2_jkzk = DealString.toGBK(request.getParameter("jtcy2_jkzk")
				.toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm")
				.toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl")
				.toString());
		String jtcy3_gx = DealString.toGBK(request.getParameter("jtcy3_gx")
				.toString());
		String jtcy3_gzdw = DealString.toGBK(request.getParameter("jtcy3_gzdw")
				.toString());
		String jtcy3_zy = DealString.toGBK(request.getParameter("jtcy3_zy")
				.toString());
		String jtcy3_nsr = DealString.toGBK(request.getParameter("jtcy3_nsr")
				.toString());
		String jtcy3_jkzk = DealString.toGBK(request.getParameter("jtcy3_jkzk")
				.toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm")
				.toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl")
				.toString());
		String jtcy4_gx = DealString.toGBK(request.getParameter("jtcy4_gx")
				.toString());
		String jtcy4_gzdw = DealString.toGBK(request.getParameter("jtcy4_gzdw")
				.toString());
		String jtcy4_zy = DealString.toGBK(request.getParameter("jtcy4_zy")
				.toString());
		String jtcy4_nsr = DealString.toGBK(request.getParameter("jtcy4_nsr")
				.toString());
		String jtcy4_jkzk = DealString.toGBK(request.getParameter("jtcy4_jkzk")
				.toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm")
				.toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl")
				.toString());
		String jtcy5_gx = DealString.toGBK(request.getParameter("jtcy5_gx")
				.toString());
		String jtcy5_gzdw = DealString.toGBK(request.getParameter("jtcy5_gzdw")
				.toString());
		String jtcy5_zy = DealString.toGBK(request.getParameter("jtcy5_zy")
				.toString());
		String jtcy5_nsr = DealString.toGBK(request.getParameter("jtcy5_nsr")
				.toString());
		String jtcy5_jkzk = DealString.toGBK(request.getParameter("jtcy5_jkzk")
				.toString());
		String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr")
				.toString());
		String xszbdszqk = DealString.toGBK(request.getParameter("xszbdszqk")
				.toString());
		String jtzszrzhqk = DealString.toGBK(request.getParameter("jtzszrzhqk")
				.toString());
		String jtzstfywsj = DealString.toGBK(request.getParameter("jtzstfywsj")
				.toString());
		String qtqk = DealString.toGBK(request.getParameter("qtqk").toString());
		String mzbm_txdz = DealString.toGBK(request.getParameter("mzbm_txdz")
				.toString());
		String mzbm_yzbm = DealString.toGBK(request.getParameter("mzbm_yzbm")
				.toString());
		String mzbm_lxdh1 = DealString.toGBK(request.getParameter("mzbm_lxdh1")
				.toString());
		String mzbm_lxdh2 = DealString.toGBK(request.getParameter("mzbm_lxdh2")
				.toString());
		String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;
		String xysh = DealString.toGBK(request.getParameter("xysh").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxsh = DealString.toGBK(request.getParameter("xxsh").toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String syd = DealString.toGBK(request.getParameter(
				"syd").toString());
		if((null == xysh) || ("".equalsIgnoreCase(xysh))){
			xysh = "未审核";
		}
		if((null == xxsh) || ("".equalsIgnoreCase(xxsh))){
			xxsh = "未审核";
		}

		String[] outValue = new String[] { xxmc, xh, xm, xb, sfzh, xymc, zymc,
				bjmc, nj, mzmc, zzmmmc, lxdh, rxqhk, jtzz, yzbm, jtlxdh,
				sfyycjcshzyhd, sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq, sfcj,
				sfjls, sfly, sfzb, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
				jtcy1_zy, jtcy1_nsr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx,
				jtcy2_gzdw, jtcy2_zy, jtcy2_nsr, jtcy2_jkzk, jtcy3_xm,
				jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
				jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
				jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
				jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
				xszbdszqk, jtzszrzhqk, jtzstfywsj, qtqk, mzbm_txdz, mzbm_yzbm,
				mzbm_lxdh, xysh, xyshyj, xxsh, xxshyj, syd };
		String[] outString = new String[] { "xxmc", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "nj", "mzmc", "zzmmmc", "lxdh",
				"rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
				"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq", "sfcj", "sfjls",
				"sfly", "sfzb", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xszbdszqk", "jtzszrzhqk", "jtzstfywsj", "qtqk", "mzbm_txdz",
				"mzbm_yzbm", "mzbm_lxdh", "xysh", "xyshyj",
				"xxsh", "xxshyj", "syd" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		outValue = new String[] { xxmc, xymc, zymc, bjmc, nj, xh, rxqhk,
				jtlxdh, sfyycjcshzyhd, sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq,
				sfcj, sfjls, sfly, sfzb, jtrjnsr, xszbdszqk, jtzszrzhqk,
				jtzstfywsj, qtqk, mzbm_lxdh, xysh, xxsh };
		outString = new String[] { "xxmc", "xymc", "zymc", "bjmc", "nj", "xh",
				"rxqhk", "jtlxdh", "sfyycjcshzyhd", "sfyysqgjzxdkhqgzx",
				"sfjq", "sfge", "sfdq", "sfcj", "sfjls", "sfly", "sfzb",
				"jtrjnsr", "xszbdszqk", "jtzszrzhqk", "jtzstfywsj", "qtqk",
				"mzbm_lxdh", "xysh", "xxsh" };
		for (int i = 0; i < outValue.length; i++) {
			if ((outValue[i] != null) && (!"".equalsIgnoreCase(outValue[i]))) {
				request.setAttribute(outString[i], outValue[i]);
			} else
				request.setAttribute(outString[i], "isnull");
		}
		if(xysh.equalsIgnoreCase(xxsh)){
			request.setAttribute("xy_xx", "same");
		} else {
			request.setAttribute("xy_xx", "notSame");
		} 
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_kns(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		HashMap<String, String> map = new HashMap<String, String>();
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
//		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		String nj = DealString.toGBK(checkForm.getNj());
		String xysh = DealString.toGBK(checkForm.getXysh());
		String xxsh = DealString.toGBK(checkForm.getXxsh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		String sfgq = request.getParameter("sfgq");
		realTable = "shgc_knsxx";
		pk = "xh||nd";
		tableName = "view_shgc_knsxx";
//		ArrayList<String> userBj = new ArrayList<String>();
//		userBj = dao.getUserBj(userName);

		String nd = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(xysh)) {
		} else {
			if ("已审核".equalsIgnoreCase(xysh)){
				querry.append(" and xysh<>'未审核'");
			} else if ("已通过".equalsIgnoreCase(xysh)) {
				querry.append(" and xysh in ('一般困难','特别困难','特殊')");
			} else {
				querry.append(" and xysh='");
				querry.append(xysh);
				querry.append("' ");
			}
		}
		if (isNull(xxsh)) {
		} else {
			if ("已审核".equalsIgnoreCase(xxsh)){
				querry.append(" and xxsh<>'未审核'");
			} else if ("已通过".equalsIgnoreCase(xxsh)) {
				querry.append(" and xxsh in ('一般困难','特别困难','特殊')");
			} else {
				querry.append(" and xxsh='");
				querry.append(xxsh);
				querry.append("' ");
			}
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(sfgq)) {
			if ("1".equalsIgnoreCase(sfgq)) {
				querry.append(" and (to_date((to_number(substr(NVL(replace(rdsj,'-02-29','-02-28'),'1900-01-01'),0,4))+1)||substr(NVL(replace(rdsj,'-02-29','-02-28'),'1900-01-01'),5,10),'yyyy-mm-dd')-SYSDATE)<=0");
			} else {
				querry.append(" and (to_date((to_number(substr(NVL(replace(rdsj,'-02-29','-02-28'),'1900-01-01'),0,4))+1)||substr(NVL(replace(rdsj,'-02-29','-02-28'),'1900-01-01'),5,10),'yyyy-mm-dd')-SYSDATE)>0");
			}
		}
		if (isNull(nj)) {
		} else {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
//			if (userBj.size() != 0) {
//				querry.append(" and bjdm in ('###'");
//				for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
//					querry.append(", '");
//					querry.append(it.next());
//					querry.append("'");
//				}
//				querry.append(" ) ");
//			}
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 家庭经济困难学生认定审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 家庭经济困难学生认定";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqsj", "xysh", "xxsh", "rdsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('特别困难','一般困难','特殊') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.jtlx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.rdsj col from view_shgc_knsxxex a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核') in ('特别困难','一般困难','特殊') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.jtlx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.rdsj col from view_shgc_knsxxex a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqsj", "xysh", "xxsh", "rdsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('特别困难','一般困难','特殊') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh in ('特别困难','一般困难', '特殊') order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.jtlx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.rdsj col from view_shgc_knsxxex a"
					+ querry.toString()
					+ " and xysh in ('特别困难','一般困难','特殊') order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
//				if (userBj.size() == 0) {
					sql = "select (case when nvl(a.xysh,'未审核') in ('特别困难','一般困难', '特殊') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xh pk2,a.* from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' order by xysh desc) a";
					sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.jtlx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.rdsj col from view_shgc_knsxxex a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc";
//				} else {
//					sql = "select (case when nvl(a.fdysh,'未审核') in ('特别困难','困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
//							+ "a.* from(select "
//							+ pk
//							+ " 主键,a.xh pk2,a.* from "
//							+ tableName
//							+ " a"
//							+ querry.toString()
//							+ " and xydm='"
//							+ userDep
//							+ "' order by fdysh desc) a";
//					sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.sfjq||'##OneSpile##'||a.sfge||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfjls||'##OneSpile##'||a.sfly||'##OneSpile##'||a.sfzb||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
//						+ tableName
//						+ " a"
//						+ querry.toString()
//						+ " and xydm='"
//						+ userDep
//						+ "' order by fdysh desc";
//				}
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##nj##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##lxdh##OneSpile##rxqhk##OneSpile##jtzz##OneSpile##yzbm##OneSpile##jtlxdh##OneSpile##sfyycjcshzyhd##OneSpile##sfyysqgjzxdkhqgzx##OneSpile##家庭类型##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_zy##OneSpile##jtcy1_nsr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_zy##OneSpile##jtcy2_nsr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_zy##OneSpile##jtcy3_nsr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_zy##OneSpile##jtcy4_nsr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_zy##OneSpile##jtcy5_nsr##OneSpile##jtcy5_jkzk##OneSpile##jtrjnsr##OneSpile##xszbdszqk##OneSpile##jtzszrzhqk##OneSpile##jtzstfywsj##OneSpile##qtqk##OneSpile##mzbm_txdz##OneSpile##mzbm_yzbm##OneSpile##mzbm_lxdh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##syd##OneSpile##rdsj";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nj", nj);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		map.put("nd", nd);
		map.put("xh", xh);
		map.put("sfgq", sfgq);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "shgc_knsxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_kns_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String rdsj = DealString.toGBK(request.getParameter("rdsj"));

		if ("lsk".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length*2];
			int j = 0;
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[j] = "insert into SHGC_KNSXX_LSK(xh,nd,xm,xb,sfzh,xydm,zydm,bjdm,xymc,zymc,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,jtlx,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqsj,syd,rdsj,knrdjg) (select xh,nd,xm,xb,sfzh,xydm,zydm,bjdm,xymc,zymc,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,jtlx,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqsj,syd,rdsj,xxsh from view_shgc_knsxxex where xh||nd='"+pkT+"')";
				j++;
				sqlT[j] = "delete shgc_knsxx where xh||nd='"+pkT+"'";
				j++;
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete shgc_knsxx where xh||nd='"+pkT+"' and xxsh not in ('一般困难','特别困难','特殊')";
				} else {
					sqlT[i] = "delete shgc_knsxx where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("tbkn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update shgc_knsxx set xysh='特别困难' where xh||nd='"+pkT+"'";
				} else {
					sqlT[i] = "update shgc_knsxx set xxsh='特别困难' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("ybkn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update shgc_knsxx set xysh='一般困难' where xh||nd='"+pkT+"'";
				} else {
					sqlT[i] = "update shgc_knsxx set xxsh='一般困难' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("ts".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update shgc_knsxx set xysh='特殊' where xh||nd='"+pkT+"'";
				} else {
					sqlT[i] = "update shgc_knsxx set xxsh='特殊' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("bkn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select xxsh from shgc_knsxx where xh||nd=?";
					String tSh = dao
							.getOneRs(sql, new String[] { pkT }, "xxsh");
					if ((null != tSh) && (!"".equalsIgnoreCase(tSh))
							&& (!"一般困难".equalsIgnoreCase(tSh)) && (!"特别困难".equalsIgnoreCase(tSh)) && (!"特殊".equalsIgnoreCase(tSh))) {
						sqlT[i] = "update shgc_knsxx set xysh='不通过' where xh||nd='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update shgc_knsxx set xxsh='不通过' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if ("knsrdsj".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "update shgc_knsxx set rdsj='"+rdsj+"' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shgc_kns.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					ok = StandardOperation.update("shgc_knsxx", new String[] {
							"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
							"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("shgc_knsxx", new String[] {
						"xxsh", "xxshyj", "rdsj" }, new String[] { yesNo,
						xxshyj, rdsj }, "xh||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) shgc_knsxx";
				Base.log(request, logMsg, sUName);
			}
		}

		realTable = "shgc_knsxx";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				sql = "select "
						+ pk
						+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,"
						+ "yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,"
						+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
						+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
						+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
						+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
						+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
						+ "jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,"
						+ "mzbm_lxdh,xysh,xyshyj,xxsh,xxshyj,sqsj,syd,rdsj,xysh yesNo "
						+ "from view_shgc_knsxx where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("userT", "xy");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,"
					+ "yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
					+ "jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,"
					+ "mzbm_lxdh,xysh,xyshyj,xxsh,xxshyj,sqsj,syd,rdsj,xxsh yesNo "
					+ "from view_shgc_knsxx where " + pk + "='" + pkVal + "'";
			request.setAttribute("userT", "xx");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "mzmc", "zzmmmc",
				"lxdh", "rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
				"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq", "sfcj", "sfjls",
				"sfly", "sfzb", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xszbdszqk", "jtzszrzhqk", "jtzstfywsj", "qtqk", "mzbm_txdz",
				"mzbm_yzbm", "mzbm_lxdh", "xysh", "xyshyj",
				"xxsh", "xxshyj", "sqsj", "syd", "rdsj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(14));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "shgc_knsxx");
		return mapping.findForward("success");
	}

	private ActionForward auditing_shgc_kns_knssjrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String doit = Base.chgNull(request.getParameter("doit"), "", 1);
		String rdsj = Base.chgNull(request.getParameter("rdsj"), "", 1);
		
		if ("save".equalsIgnoreCase(doit)){
			String[] pkValT = pkVal.split("!!splitOne!!");
			String[] sqlT = new String[pkValT.length];
			for (int i = 1; i < pkValT.length; i++) {
				String pkT = pkValT[i];
				sqlT[i] = "update shgc_knsxx set rdsj='"+rdsj+"' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("ok", "ok");
		}
		request.setAttribute("rdsj", rdsj);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("success");
	}
}
