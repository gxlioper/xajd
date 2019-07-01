/*
 * 创建日期 2007-11-06 zhoumi
 *
 */
package xgxt.action.xnmz;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.XszzForm;
import xgxt.action.Base;
import xgxt.base.*;

/** 学生资助 */
public class XszzAction extends Action {
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
			if (act.equalsIgnoreCase("auditing_xnmz_kns")) {// 西南民族大学-困难生审核
				myActFwd = auditing_xnmz_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_xnmz_kns_one")) {// 西南民族大学-困难生单个审核
				myActFwd = auditing_xnmz_kns_one(mapping, form, request,
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

	private ActionForward auditing_xnmz_kns(ActionMapping mapping,
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
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String xxdm = StandardOperation.getXxdm();
		String tips = "";
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

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
		realTable = "XSJTQKDCB";
		pk = "xh";
		tableName = "view_xsjtqkdcb";
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
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ) || xxdm.equalsIgnoreCase(Globals.XXDM_SCJZZYJSXY)) {
				if (userBj.size() != 0) {
					querry.append(" and bjdm in ('###'");
					for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
						querry.append(", '");
						querry.append(it.next());
						querry.append("'");
					}
					querry.append(" ) ");
				}
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
		tips = "当前所在位置：学生资助 - 审核 - 困难生审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 困难生";
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm",
					"xymc", "xmc", "bjmc", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.jtxxtxdz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsyhhjqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbmxxtxdz||'##OneSpile##'||a.mzbmyzbm||'##OneSpile##'||a.mzbmlxdh||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xdm col from "
					+ tableName + " a" + querry.toString() + " order by xxsh desc";
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
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.jtxxtxdz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsyhhjqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbmxxtxdz||'##OneSpile##'||a.mzbmyzbm||'##OneSpile##'||a.mzbmlxdh||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xdm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "xb",
					"sfzh", "xymc", "xmc", "nj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.jtxxtxdz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsyhhjqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbmxxtxdz||'##OneSpile##'||a.mzbmyzbm||'##OneSpile##'||a.mzbmlxdh||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xdm col from "
					+ tableName + " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				if (userBj.size() == 0) {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xh pk2,a.* from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh desc) a";
					sqlExp = "select a.xh||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.jtxxtxdz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsyhhjqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbmxxtxdz||'##OneSpile##'||a.mzbmyzbm||'##OneSpile##'||a.mzbmlxdh||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xdm col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' and fdysh='通过' order by xysh desc";
					colList[colList.length - 1] = "xysh";
				} else {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xh pk2,a.* from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' order by fdysh desc) a";
					sqlExp = "select a.xh||'##OneSpile##'||a.xxmc||'##OneSpile##'||a.xymc||'##OneSpile##'||a.xmc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.jtxxtxdz||'##OneSpile##'||a.jtyzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsyhhjqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbmxxtxdz||'##OneSpile##'||a.mzbmyzbm||'##OneSpile##'||a.mzbmlxdh||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xdm col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc";
					colList[colList.length - 1] = "fdysh";
				}
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xxmc##OneSpile##xymc##OneSpile##xmc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##nj##OneSpile##xm##OneSpile##xb##OneSpile##csny##OneSpile##mzmc##OneSpile##sfzh##OneSpile##zzmm##OneSpile##rxqhk##OneSpile##jtrks##OneSpile##byxx##OneSpile##grtc##OneSpile##sfgc##OneSpile##sfdq##OneSpile##sflszn##OneSpile##jtxxtxdz##OneSpile##jtyzbm##OneSpile##jtlxdh##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_zy##OneSpile##jtcy1_nsr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_zy##OneSpile##jtcy2_nsr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_zy##OneSpile##jtcy3_nsr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_zy##OneSpile##jtcy4_nsr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_zy##OneSpile##jtcy5_nsr##OneSpile##jtcy5_jkzk##OneSpile##jtrjnsr##OneSpile##xsyhhjqk##OneSpile##jtzszrzhqk##OneSpile##jtzstfywsj##OneSpile##jtcyndlrqk##OneSpile##jtcysyqk##OneSpile##jtqzqk##OneSpile##qtqk##OneSpile##mzbmxxtxdz##OneSpile##mzbmyzbm##OneSpile##mzbmlxdh##OneSpile##fdysh##OneSpile##fdyshyj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##sqsj##OneSpile##xydm##OneSpile##xdm";
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
		request.setAttribute("act", "xsjxjzxjsqb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_xnmz_kns_one(ActionMapping mapping,
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
		String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj"));

		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if (userBj.size() == 0) {
						sqlT[i] = "delete xsjtqkdcb where xh='"+pkT+"' and xxsh<>'通过'";
					} else {
						sqlT[i] = "delete xsjtqkdcb where xh='"+pkT+"' and xysh<>'通过'";
					}
				} else {
					sqlT[i] = "delete xsjtqkdcb where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_xnmz_kns.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if (userBj.size() == 0) {
					ok = StandardOperation.update("xsjtqkdcb", new String[] {
							"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
							"xh", pkVal, request);
				} else {
					if ("未审核".equalsIgnoreCase(yesNo) || "不通过".equalsIgnoreCase(yesNo)){
						ok = StandardOperation.update("xsjtqkdcb",
								new String[] { "fdysh", "fdyshyj", "xysh" },
								new String[] { yesNo, fdyshyj, "未审核" }, "xh", pkVal,
								request);
					} else {
						ok = StandardOperation.update("xsjtqkdcb",
								new String[] { "fdysh", "fdyshyj" },
								new String[] { yesNo, fdyshyj }, "xh", pkVal,
								request);
					}
				}
			} else {
				ok = StandardOperation.update("xsjtqkdcb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) xsjtqkdcb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "xsjtqkdcb";
		pk = "xh";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if (userBj.size() == 0) {
				sql = "select "
						+ pk
						+ " pk,xh,xxmc,xymc,xmc,nj,xm,xb,csny,mzmc,sfzh,zzmm,rxqhk,"
						+ "jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,"
						+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
						+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
						+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
						+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
						+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
						+ "jtrjnsr,xsyhhjqk,jtzszrzhqk,jtzstfywsj,jtcyndlrqk,jtcysyqk,jtqzqk,"
						+ "qtqk,mzbmxxtxdz,mzbmyzbm,mzbmlxdh,fdyshyj,xyshyj,xxshyj,sqsj,xydm,xdm,xxsh,xysh yesNo "
						+ "from VIEW_XSJTQKDCB where " + pk + "='" + pkVal
						+ "'";
			} else {
				sql = "select "
						+ pk
						+ " pk,xh,xxmc,xymc,xmc,nj,xm,xb,csny,mzmc,sfzh,zzmm,rxqhk,"
						+ "jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,"
						+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
						+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
						+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
						+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
						+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
						+ "jtrjnsr,xsyhhjqk,jtzszrzhqk,jtzstfywsj,jtcyndlrqk,jtcysyqk,jtqzqk,"
						+ "qtqk,mzbmxxtxdz,mzbmyzbm,mzbmlxdh,fdyshyj,xyshyj,xxshyj,sqsj,xydm,xdm,xxsh,fdysh yesNo "
						+ "from VIEW_XSJTQKDCB where " + pk + "='" + pkVal
						+ "'";
			}
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xxmc,xymc,xmc,nj,xm,xb,csny,mzmc,sfzh,zzmm,rxqhk,"
					+ "jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
					+ "jtrjnsr,xsyhhjqk,jtzszrzhqk,jtzstfywsj,jtcyndlrqk,jtcysyqk,jtqzqk,"
					+ "qtqk,mzbmxxtxdz,mzbmyzbm,mzbmlxdh,fdyshyj,xyshyj,xxshyj,sqsj,xydm,xdm,xxsh,xxsh yesNo "
					+ "from VIEW_XSJTQKDCB where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xxmc", "xymc", "xmc", "nj", "xm",
				"xb", "csny", "mzmc", "sfzh", "zzmm", "rxqhk", "jtrks", "byxx",
				"grtc", "sfgc", "sfdq", "sflszn", "jtxxtxdz", "jtyzbm",
				"jtlxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
				"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
				"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr", "xsyhhjqk",
				"jtzszrzhqk", "jtzstfywsj", "jtcyndlrqk", "jtcysyqk", "jtqzqk",
				"qtqk", "mzbmxxtxdz", "mzbmyzbm", "mzbmlxdh", "fdyshyj",
				"xyshyj", "xxshyj", "sqsj", "xydm", "xdm", "xxsh", "yesNo" };

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
}
