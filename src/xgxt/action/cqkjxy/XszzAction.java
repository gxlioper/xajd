/*
 * 创建日期 2007-11-07 zhoumi
 *
 */
package xgxt.action.cqkjxy;

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
import xgxt.utils.dealDate;
import xgxt.action.Base;
import xgxt.base.*;

/** 学生资助 */
public class XszzAction extends Action {
	dealDate dealD = new dealDate();
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
			if (act.equalsIgnoreCase("cqkjxy_lstd")) {//重庆科技学院-绿色通道申请
				myActFwd = cqkjxy_lstd(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("cqkjxy_lstdb")) {// 重庆科技学院-绿色通道申请表
				myActFwd = cqkjxy_lstdb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_cqkjxy_lstd")) {// 重庆科技学院-绿色通道审核
				myActFwd = auditing_cqkjxy_lstd(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_cqkjxy_lstd_one")) {// 重庆科技学院-绿色通道单个审核
				myActFwd = auditing_cqkjxy_lstd_one(mapping, form, request,
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

	private ActionForward cqkjxy_lstd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userDep = session.getAttribute("userDep").toString();

		XszzForm xszzForm = (XszzForm) form;
		String sqsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String[] titNames = null;

		titNames = new String[] { "cqkjxy_lstd" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select sxh,xh,xm,xb,mzmc,xydm,xymc,zydm,zymc,bjdm,bjmc,zcxnxq,hjyy,sqsj,"
				+ "fdysh,fdyshyj,xysh,xyshyj,xxsh,tyhjxf,tyhjxfdx,hjqx,fdyspsj,xyspsj,"
				+ "xxspsj,jzrq,bz from view_cqkjxy_lstd where 1=2";
		
		String jwT = dao.getOneRs("select xqzcsj from view_cqkjxy_jwsj", new String[]{}, "xqzcsj");
		String[] jwS;
		String sqkssj = "";
		String sqjssj = "";
		String zcxnxq = "";
		if((null != jwT) && (!"".equalsIgnoreCase(jwT))){
			jwS = jwT.split("\\*");
			zcxnxq = jwS[0];
			sqkssj = jwS[1];
			sqjssj = jwS[2];
		}
		
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String sxh = request.getParameter("pkQuery");
//		String nd = dao.getOneRs("select dqnd nd from xtszb", new String[] {},
//				new String[] { "nd" })[0];

		String sfksq = "-1";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if ((!"".equalsIgnoreCase(sqkssj)) && (!"".equalsIgnoreCase(sqjssj)) &&
					sqkssj.compareToIgnoreCase(sqsj) < 0
					&& sqjssj.compareToIgnoreCase(sqsj) > 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String hjyy = DealString.toGBK(
						request.getParameter("hjyy").toString());
					String bz = DealString.toGBK(
							request.getParameter("bz").toString());

					sql = "select fdysh,xysh,xxsh from cqkjxy_lstd where xh=? and zcxnxq=?";
					String[] temp = dao.getOneRs(sql, new String[] { xh, zcxnxq },
							new String[]{ "fdysh", "xysh", "xxsh" });
					if (null != temp
							&& (!"不通过".equalsIgnoreCase(temp[0])
									&& !"不通过".equalsIgnoreCase(temp[1]) && !"不通过"
									.equalsIgnoreCase(temp[2]))) {
						request.setAttribute("noSh", "is");
					} else {
						String[] valueForOut = null;

						valueForOut = new String[] { xh, hjyy, zcxnxq, bz };
						sql = "insert into cqkjxy_lstd (sxh,xh,hjyy,zcxnxq,bz) "
							+ "values(SEQ_CQKJXY_LSTD.NEXTVAL,?,?,?,?)";
						boolean ok = false;
						ok = dao.runUpdate(sql,valueForOut);

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
				String hjyy = DealString.toGBK(
						request.getParameter("hjyy").toString());
				String bz = DealString.toGBK(
						request.getParameter("bz").toString());

				sql = "select fdysh,xysh,xxsh from cqkjxy_lstd where xh=? and zcxnxq=?";
				String[] temp = dao.getOneRs(sql, new String[] { xh, zcxnxq },
						new String[]{ "fdysh", "xysh", "xxsh" });
				if (null != temp
						&& (!"不通过".equalsIgnoreCase(temp[0])
								&& !"不通过".equalsIgnoreCase(temp[1]) && !"不通过"
								.equalsIgnoreCase(temp[2]))) {
					request.setAttribute("noSh", "is");
				} else {
					String[] valueForOut = null;

					valueForOut = new String[] { xh, hjyy, zcxnxq, bz };
					sql = "insert into cqkjxy_lstd (sxh,xh,hjyy,zcxnxq,bz) "
						+ "values(SEQ_CQKJXY_LSTD.NEXTVAL,?,?,?,?)";
					boolean ok = false;
					ok = dao.runUpdate(sql,valueForOut);
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

		if ((null != sxh) && (!"".equalsIgnoreCase(sxh))) {
			sql = "select sxh,xh,xm,xb,mzmc,xydm,xymc,zydm,zymc,bjdm,bjmc,zcxnxq,hjyy,sqsj,"
					+ "fdysh,fdyshyj,xysh,xyshyj,xxsh,tyhjxf,tyhjxfdx,hjqx,fdyspsj,xyspsj,"
					+ "xxspsj,jzrq,bz from view_cqkjxy_lstd where sxh=?";
			outString = new String[] { "sxh", "xh", "xm", "xb", "mzmc", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "hjyy", "sqsj",
					"fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "tyhjxf",
					"hjqx", "fdyspsj", "xyspsj", "xxspsj", "jzrq", "bz" };
			outValue = dao.getOneRs(sql, new String[] { sxh }, outString);
		} else {
			sql = "select sxh,xh,xm,xb,mzmc,xydm,xymc,zydm,zymc,bjdm,bjmc,zcxnxq,hjyy,sqsj,"
					+ "fdysh,fdyshyj,xysh,xyshyj,xxsh,tyhjxf,tyhjxfdx,hjqx,fdyspsj,xyspsj,"
					+ "xxspsj,jzrq,bz from view_cqkjxy_lstd where sxh="
					+ "(select max(sxh) from view_cqkjxy_lstd where xh=?)";
			outString = new String[] { "sxh", "xh", "xm", "xb", "mzmc", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "zcxnxq", "hjyy", "sqsj",
					"fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "tyhjxf", "tyhjxfdx",
					"hjqx", "fdyspsj", "xyspsj", "xxspsj", "jzrq", "bz" };
			outValue = dao.getOneRs(sql, new String[] { xh }, outString);
		}
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,"
						+ "(select b.mzmc from view_stu_details b where "
						+ "a.xh=b.xh) mzmc from view_xsjbxx a where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "mzmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					map.put("sqsj", sqsj);
					colName = new String[] { "xh", "xm", "xb", "xymc",
							"zymc", "bjmc", "mzmc" };
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
			map.put("sqsj", sqsj);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward cqkjxy_lstdb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String hjyy = DealString.toGBK(request.getParameter("hjyy").toString());
		String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj").toString());
		String tyhjxf = DealString.toGBK(request.getParameter("tyhjxf").toString());
		String tyhjxfdx = DealString.toGBK(request.getParameter("tyhjxfdx").toString());
		String jzrq = DealString.toGBK(request.getParameter("jzrq").toString());
		String fdyspsj = DealString.toGBK(request.getParameter("fdyspsj").toString());
		String xyspsj = DealString.toGBK(request.getParameter("xyspsj").toString());
		String xxspsj = DealString.toGBK(request.getParameter("xxspsj").toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		
		if((null == sqsj) || ("".equalsIgnoreCase(sqsj))){
			sqsj = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
		}
		String sqsj_year = sqsj.substring(0, 4);
		String sqsj_mon = sqsj.substring(5, 7);
		String sqsj_day = sqsj.substring(8);
		
		String jzrq_year, jzrq_mon, jzrq_day, fdyspsj_year, fdyspsj_mon, fdyspsj_day, xyspsj_year, xyspsj_mon, xyspsj_day, xxspsj_year, xxspsj_mon, xxspsj_day;
		if((null == jzrq) || ("".equalsIgnoreCase(jzrq))){
			jzrq_year = "empty";
			jzrq_mon = "empty";
			jzrq_day = "empty";
		} else {
			jzrq_year = jzrq.substring(0, 4);
			jzrq_mon = jzrq.substring(5, 7);
			jzrq_day = jzrq.substring(8);
		}
		if((null == fdyspsj) || ("".equalsIgnoreCase(fdyspsj))){
			fdyspsj_year = "empty";
			fdyspsj_mon = "empty";
			fdyspsj_day = "empty";
		} else {
			fdyspsj_year = fdyspsj.substring(0, 4);
			fdyspsj_mon = fdyspsj.substring(5, 7);
			fdyspsj_day = fdyspsj.substring(8);
		}
		if((null == xyspsj) || ("".equalsIgnoreCase(xyspsj))){
			xyspsj_year = "empty";
			xyspsj_mon = "empty";
			xyspsj_day = "empty";
		} else {
			xyspsj_year = xyspsj.substring(0, 4);
			xyspsj_mon = xyspsj.substring(5, 7);
			xyspsj_day = xyspsj.substring(8);
		}
		if((null == xxspsj) || ("".equalsIgnoreCase(xxspsj))){
			xxspsj_year = "empty";
			xxspsj_mon = "empty";
			xxspsj_day = "empty";
		} else {
			xxspsj_year = xxspsj.substring(0, 4);
			xxspsj_mon = xxspsj.substring(5, 7);
			xxspsj_day = xxspsj.substring(8);
		}
		
		if((null == tyhjxf) || ("".equalsIgnoreCase(tyhjxf))){
			tyhjxf = "empty";
			tyhjxfdx = "empty";
		}

		String[] outValue = new String[] { xh, xm, xb, xymc, zymc, bjmc, mzmc,
				hjyy, fdyshyj, xyshyj, tyhjxf, tyhjxfdx, bz, sqsj_year,
				sqsj_mon, sqsj_day, jzrq_year, jzrq_mon, jzrq_day,
				fdyspsj_year, fdyspsj_mon, fdyspsj_day, xyspsj_year,
				xyspsj_mon, xyspsj_day, xxspsj_year, xxspsj_mon, xxspsj_day };
		String[] outString = new String[] { "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "mzmc", "hjyy", "fdyshyj", "xyshyj", "tyhjxf",
				"tyhjxfdx", "bz", "sqsj_year", "sqsj_mon", "sqsj_day",
				"jzrq_year", "jzrq_mon", "jzrq_day", "fdyspsj_year",
				"fdyspsj_mon", "fdyspsj_day", "xyspsj_year", "xyspsj_mon",
				"xyspsj_day", "xxspsj_year", "xxspsj_mon", "xxspsj_day" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		outValue = new String[] { tyhjxf, tyhjxfdx, jzrq_year, jzrq_mon,
				jzrq_day, fdyspsj_year, fdyspsj_mon, fdyspsj_day, xyspsj_year,
				xyspsj_mon, xyspsj_day, xxspsj_year, xxspsj_mon, xxspsj_day };
		outString = new String[] { "tyhjxf", "tyhjxfdx", "jzrq_year",
				"jzrq_mon", "jzrq_day", "fdyspsj_year", "fdyspsj_mon",
				"fdyspsj_day", "xyspsj_year", "xyspsj_mon", "xyspsj_day",
				"xxspsj_year", "xxspsj_mon", "xxspsj_day" };
		for (int i = 0; i < outValue.length; i++) {
			request.setAttribute(outString[i], outValue[i]);
		}
		return mapping.findForward("success");
	}

	private ActionForward auditing_cqkjxy_lstd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String isQuery = request.getParameter("isQuery");
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String sqlExp = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String jwT = dao.getOneRs("select xqzcsj from view_cqkjxy_jwsj", new String[]{}, "xqzcsj");
		String[] jwS;
		String zcxnxq = "";
		if((null != jwT) || (!"".equalsIgnoreCase(jwT))){
			jwS = jwT.split("\\*");
			zcxnxq = jwS[0];
		}

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
		realTable = "cqkjxy_lstd";
		pk = "sxh||xh";
		tableName = "view_cqkjxy_lstd";
		if (isNull(zcxnxq)) {
		} else {
			querry.append(" and zcxnxq='");
			querry.append(zcxnxq);
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
			if (userBj.size() != 0) {
				querry.append(" and bjdm in ('###'");
				for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
					querry.append(", '");
					querry.append(it.next());
					querry.append("'");
				}
				querry.append(" ) ");
			}
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
		String tips = "当前所在位置：学生资助 - 审核 - 绿色通道审核";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 绿色通道";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm",
					"xb", "sqsj", "fdysh", "xysh", "xxsh", "tyhjxf", "jzrq" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.sxh pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " order by xxsh) a";
				sqlExp = "select a.sxh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zcxnxq||'##OneSpile##'||a.hjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.tyhjxf||'##OneSpile##'||a.tyhjxfdx||'##OneSpile##'||a.hjqx||'##OneSpile##'||a.fdyspsj||'##OneSpile##'||a.xyspsj||'##OneSpile##'||a.xxspsj||'##OneSpile##'||a.jzrq||'##OneSpile##'||a.bz||'##OneSpile##'||a.nj col from "
						+ tableName + " a" + querry.toString()
						+ "";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.sxh pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh) a";
				sqlExp = "select a.sxh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zcxnxq||'##OneSpile##'||a.hjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.tyhjxf||'##OneSpile##'||a.tyhjxfdx||'##OneSpile##'||a.hjqx||'##OneSpile##'||a.fdyspsj||'##OneSpile##'||a.xyspsj||'##OneSpile##'||a.xxspsj||'##OneSpile##'||a.jzrq||'##OneSpile##'||a.bz||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.sxh pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " and xysh='通过' order by xxsh) a";
				sqlExp = "select a.sxh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zcxnxq||'##OneSpile##'||a.hjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.tyhjxf||'##OneSpile##'||a.tyhjxfdx||'##OneSpile##'||a.hjqx||'##OneSpile##'||a.fdyspsj||'##OneSpile##'||a.xyspsj||'##OneSpile##'||a.xxspsj||'##OneSpile##'||a.jzrq||'##OneSpile##'||a.bz||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a"
					+ querry.toString() + " and xysh='通过' order by xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				if (userBj.size() == 0) {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xh pk2,a.sxh pk3,a.* from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh) a";
					sqlExp = "select a.sxh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zcxnxq||'##OneSpile##'||a.hjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.tyhjxf||'##OneSpile##'||a.tyhjxfdx||'##OneSpile##'||a.hjqx||'##OneSpile##'||a.fdyspsj||'##OneSpile##'||a.xyspsj||'##OneSpile##'||a.xxspsj||'##OneSpile##'||a.jzrq||'##OneSpile##'||a.bz||'##OneSpile##'||a.nj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' and fdysh='通过' order by xysh";
				} else {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.sxh pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh) a";
					sqlExp = "select a.sxh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zcxnxq||'##OneSpile##'||a.hjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.tyhjxf||'##OneSpile##'||a.tyhjxfdx||'##OneSpile##'||a.hjqx||'##OneSpile##'||a.fdyspsj||'##OneSpile##'||a.xyspsj||'##OneSpile##'||a.xxspsj||'##OneSpile##'||a.jzrq||'##OneSpile##'||a.bz||'##OneSpile##'||a.nj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh";
				}
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "sxh##OneSpile##xh##OneSpile##xm##OneSpile##xb##OneSpile##mzmc##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##zcxnxq##OneSpile##hjyy##OneSpile##sqsj##OneSpile##fdysh##OneSpile##fdyshyj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##tyhjxf##OneSpile##tyhjxfdx##OneSpile##hjqx##OneSpile##fdyspsj##OneSpile##xyspsj##OneSpile##xxspsj##OneSpile##jzrq##OneSpile##bz##OneSpile##nj";
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
		request.setAttribute("act", "cqkjxy_lstd");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_cqkjxy_lstd_one(ActionMapping mapping,
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
		String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj"));
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String tyhjxf = DealString.toGBK(request.getParameter("tyhjxf"));
		String hjqx = DealString.toGBK(request.getParameter("hjqx"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String spsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		
		String tyhjxfdx = "";
		String jzrq = "";
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String jwT = dao.getOneRs("select zdqfxe from view_cqkjxy_jwsj", new String[]{}, "zdqfxe");
		String zdqfxe = "0";
		if((null != jwT) || (!"".equalsIgnoreCase(jwT))){
			zdqfxe = jwT;
		}
		request.setAttribute("zdqfxe", zdqfxe);
		boolean ok = false;
		
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if (userBj.size() == 0) {
						sqlT[i] = "delete cqkjxy_lstd where sxh||xh='"+pkT+"' and xxsh<>'通过'";
					} else {
						sqlT[i] = "delete cqkjxy_lstd where sxh||xh='"+pkT+"' and xysh<>'通过'";
					}
				} else {
					sqlT[i] = "delete cqkjxy_lstd where sxh||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_cqkjxy_lstd.do?go=go", false);
			return newFwd;
		}
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if (userBj.size() == 0) {
						sqlT[i] = "update cqkjxy_lstd set xysh='通过' where sxh||xh='"+pkT+"'";
					} else {
						sqlT[i] = "update cqkjxy_lstd set fdysh='通过' where sxh||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update cqkjxy_lstd set xxsh='通过' where sxh||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_cqkjxy_lstd.do?go=go", false);
			return newFwd;
		}
		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select xxsh from cqkjxy_lstd where sxh||xh=?";
					String xxsh = dao.getOneRs(sql, new String[] { pkT },
							"xxsh");
					if (userBj.size() == 0) {
						if (!"通过".equalsIgnoreCase(xxsh)) {
							sqlT[i] = "update cqkjxy_lstd set xysh='不通过' where sxh||xh='"+pkT+"'";
						}
					} else {
						if (!"通过".equalsIgnoreCase(xxsh)) {
							sqlT[i] = "update cqkjxy_lstd set fdysh='不通过',xysh='未审核' where sxh||xh='"+pkT+"'";
						}
					}
				} else {
					sqlT[i] = "update cqkjxy_lstd set xxsh='不通过' where sxh||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_cqkjxy_lstd.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if (userBj.size() == 0) {
					ok = StandardOperation.update("cqkjxy_lstd", new String[] {
							"xysh", "xyshyj", "xyspsj" }, new String[] { yesNo,
							xyshyj, spsj }, "sxh||xh", pkVal, request);
				} else {
					if ("未审核".equalsIgnoreCase(yesNo) || "不通过".equalsIgnoreCase(yesNo)){
						ok = StandardOperation.update("cqkjxy_lstd", new String[] {
								"fdysh", "fdyshyj", "fdyspsj", "xysh" }, new String[] {
								yesNo, fdyshyj, spsj, "未审核" }, "sxh||xh", pkVal,
								request);
					} else {
						ok = StandardOperation.update("cqkjxy_lstd",
								new String[] { "fdysh", "fdyshyj", "fdyspsj" },
								new String[] { yesNo, fdyshyj, spsj },
								"sxh||xh", pkVal, request);
					}
				}
			} else {
				if((null == tyhjxf) || ("".equalsIgnoreCase(tyhjxf))){
					tyhjxf = "0";
				}
					String sqlT = "{call pro_Disp_dxje(?,?)}";
					String[] temp = dao.getProVal(sqlT, new String[] {tyhjxf}, new int[] {2});
					tyhjxfdx = temp[0];
				if ("半个月".equalsIgnoreCase(hjqx)) {
					jzrq = dao
							.getOneRs(
									"select to_char((sysdate+15),'yyyy-mm-dd') jzrq from dual",
									new String[] {}, "jzrq");
				} else if ("一个月".equalsIgnoreCase(hjqx)) {
					jzrq = dao
							.getOneRs(
									"select to_char(add_months(sysdate,1),'yyyy-mm-dd') jzrq from dual",
									new String[] {}, "jzrq");
				} else if ("两个月".equalsIgnoreCase(hjqx)) {
					jzrq = dao
							.getOneRs(
									"select to_char(add_months(sysdate,2),'yyyy-mm-dd') jzrq from dual",
									new String[] {}, "jzrq");
				} else if ("三个月".equalsIgnoreCase(hjqx)) {
					jzrq = dao
							.getOneRs(
									"select to_char(add_months(sysdate,3),'yyyy-mm-dd') jzrq from dual",
									new String[] {}, "jzrq");
				}
				ok = StandardOperation.update("cqkjxy_lstd",
						new String[] { "xxsh", "tyhjxf", "tyhjxfdx", "hjqx",
								"jzrq", "xxspsj" }, new String[] { yesNo,
								tyhjxf, tyhjxfdx, hjqx, jzrq, spsj },
						"sxh||xh", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) cqkjxy_lstd";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "cqkjxy_lstd";
		pk = "sxh||xh";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if (userBj.size() == 0) {
				sql = "select "
						+ pk
						+ " pk,a.*,"
						+ "XYSH yesNo from view_cqkjxy_lstd a where " + pk + "='"
						+ pkVal + "'";
				request.setAttribute("userType", "xy");
			} else {
				sql = "select "
					+ pk
					+ " pk,a.*,"
					+ "FDYSH yesNo from view_cqkjxy_lstd a where " + pk + "='"
					+ pkVal + "'";
				request.setAttribute("userType", "fdy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.*,"
				+ "XXSH yesNo from view_cqkjxy_lstd a where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("userType", "xx");
		}
		colList = new String[] { "pk", "sxh", "xh", "xm", "xb", "mzmc", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "zcxnxq", "hjyy",
				"sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "tyhjxf",
				"tyhjxfdx", "hjqx", "fdyspsj", "xyspsj", "xxspsj", "jzrq",
				"bz", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		sql = "select qjf from view_xszz_cqkjxy_qfxx where xh=?";
		String qjf = dao.getOneRs(sql, new String[]{xh}, "qjf");
		if ("".equalsIgnoreCase(qjf)){
			qjf = "0";
		}
		request.setAttribute("qjf", qjf);
		hs.put("qjf", qjf);
		
//		sql = "select '半个月' hjqx,'半个月' hjqx from dual union select '一个月' hjqx,'一个月' hjqx from dual union select '两个月' hjqx,'两个月' hjqx from dual union select '三个月' hjqx,'三个月' hjqx from dual";
//		List hjqxList = dao.getList(sql, new String[] {},
//				new String[] { "hjqx" , "hjqx" });
		ArrayList<HashMap<String, String>> hjqxList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("hjqx", "半个月");
		hjqxList.add(hashMap);
		hashMap = new HashMap<String, String>();
		hashMap.put("hjqx", "一个月");
		hjqxList.add(hashMap);
		hashMap = new HashMap<String, String>();
		hashMap.put("hjqx", "两个月");
		hjqxList.add(hashMap);
		hashMap = new HashMap<String, String>();
		hashMap.put("hjqx", "三个月");
		hjqxList.add(hashMap);
		request.setAttribute("hjqxList", hjqxList);
		sql = "select xh,xm,sqsj,hjyy,xxspsj,tyhjxf,jzrq from view_cqkjxy_lstd where xxsh='通过' and xh='" + xh + "' and " + pk + "<>'" + pkVal + "' order by sqsj";
		ArrayList<String[]> sqList = dao.getCqkjLstdList(sql);
		request.setAttribute("sqList", sqList);
		
		request.setAttribute("spsj", spsj);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "cqkjxy_lstd");
		return mapping.findForward("success");
	}
}
