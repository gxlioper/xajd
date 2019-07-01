/*
 * 创建日期 2007-11-06 zhoumi
 *
 */
package xgxt.action.shnlzyjsxy;

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
			if (act.equalsIgnoreCase("shnl_xsjtqkdc")) {// 上海农林职业技术学院-学生及家庭情况调查申请
				myActFwd = shnl_xsjtqkdc(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("shnl_xsjtqkdcb")) {// 上海农林职业技术学院-学生及家庭情况调查申请表
				myActFwd = shnl_xsjtqkdcb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("shnl_kns")) {// 上海农林职业技术学院-家庭经济困难学生认定
				myActFwd = shnl_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("shnl_knsb")) {// 上海农林职业技术学院-家庭经济困难学生认定表
				myActFwd = shnl_knsb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shnl_kns")) {// 上海工程技术大学-家庭经济困难学生认定审核
				myActFwd = auditing_shnl_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_shnl_kns_one")) {// 上海工程技术大学-家庭经济困难学生认定单个审核
				myActFwd = auditing_shnl_kns_one(mapping, form, request,
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

	private ActionForward shnl_xsjtqkdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String[] titNames = null;

		titNames = new String[] { "shnl_xsjtqkdc" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,csny,mzmc,"
				+ "zzmmmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfsjqk,jtcyycjnmrldnlrqk,jtcysyqk,"
				+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj from "
				+ "view_shnl_xsjjtqkdc where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;
		String jtlxdh1 = "";
		String jtlxdh2 = "";
		String mzbm_lxdh1 = "";
		String mzbm_lxdh2 = "";

		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
				String rxqhk = DealString.toGBK(request.getParameter("rxqhk")
						.toString());
				String jtrks = DealString.toGBK(request.getParameter("jtrks")
						.toString());
				String byxx = DealString.toGBK(request.getParameter("byxx")
						.toString());
				String grtc = DealString.toGBK(request.getParameter("grtc")
						.toString());
				String sfgc = DealString.toGBK(request.getParameter("sfgc")
						.toString());
				String sfdq = DealString.toGBK(request.getParameter("sfdq")
						.toString());
				String sflszn = DealString.toGBK(request.getParameter("sflszn")
						.toString());
				String jtxxtxdz = DealString.toGBK(request.getParameter(
						"jtxxtxdz").toString());
				String jtyzbm = DealString.toGBK(request.getParameter("jtyzbm")
						.toString());
				jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
						.toString());
				jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
						.toString());
				String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
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
				String xsbxnyhzzqk = DealString.toGBK(request.getParameter(
						"xsbxnyhzzqk").toString());
				String jtzszrzhqk = DealString.toGBK(request.getParameter(
						"jtzszrzhqk").toString());
				String jtzstfsjqk = DealString.toGBK(request.getParameter(
						"jtzstfsjqk").toString());
				String jtcyycjnmrldnlrqk = DealString.toGBK(request
						.getParameter("jtcyycjnmrldnlrqk").toString());
				String jtcysyqk = DealString.toGBK(request.getParameter(
						"jtcysyqk").toString());
				String jtqzqk = DealString.toGBK(request.getParameter("jtqzqk")
						.toString());
				String qtqk = DealString.toGBK(request.getParameter("qtqk")
						.toString());
				String mzbm_xxtxdz = DealString.toGBK(request.getParameter(
						"mzbm_xxtxdz").toString());
				String mzbm_yzbm = DealString.toGBK(request.getParameter(
						"mzbm_yzbm").toString());
				mzbm_lxdh1 = DealString.toGBK(request
						.getParameter("mzbm_lxdh1").toString());
				mzbm_lxdh2 = DealString.toGBK(request
						.getParameter("mzbm_lxdh2").toString());
				String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				StandardOperation.delete("shnl_xsjjtqkdc", "xh||nd", pkVal,
						request);

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "rxqhk",
						"jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
						"jtxxtxdz", "jtyzbm", "jtlxdh", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
						"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
						"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
						"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
						"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
						"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
						"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
						"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
						"jtcy5_jkzk", "jtrjnsr", "xsbxnyhzzqk", "jtzszrzhqk",
						"jtzstfsjqk", "jtcyycjnmrldnlrqk", "jtcysyqk",
						"jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
						"mzbm_lxdh" };

				valueForOut = new String[] { xh, nd, rxqhk, jtrks, byxx, grtc,
						sfgc, sfdq, sflszn, jtxxtxdz, jtyzbm, jtlxdh, jtcy1_xm,
						jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
						jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
						jtcy2_zy, jtcy2_nsr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
						jtcy3_gx, jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
						jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
						jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
						jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
						xsbxnyhzzqk, jtzszrzhqk, jtzstfsjqk, jtcyycjnmrldnlrqk,
						jtcysyqk, jtqzqk, qtqk, mzbm_xxtxdz, mzbm_yzbm,
						mzbm_lxdh };

				boolean ok = false;
				ok = StandardOperation.insert("shnl_xsjjtqkdc", colName1,
						valueForOut, request);
				if (ok) {
					logMsg = "申请" + titName;
					Base.log(request, logMsg, sUName);
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = xszzForm.getXh();
			if (doType != null && doType.equalsIgnoreCase("add")) {
				xh = DealString.toGBK(request.getParameter("xh").toString());
				String rxqhk = DealString.toGBK(request.getParameter("rxqhk")
						.toString());
				String jtrks = DealString.toGBK(request.getParameter("jtrks")
						.toString());
				String byxx = DealString.toGBK(request.getParameter("byxx")
						.toString());
				String grtc = DealString.toGBK(request.getParameter("grtc")
						.toString());
				String sfgc = DealString.toGBK(request.getParameter("sfgc")
						.toString());
				String sfdq = DealString.toGBK(request.getParameter("sfdq")
						.toString());
				String sflszn = DealString.toGBK(request.getParameter("sflszn")
						.toString());
				String jtxxtxdz = DealString.toGBK(request.getParameter(
						"jtxxtxdz").toString());
				String jtyzbm = DealString.toGBK(request.getParameter("jtyzbm")
						.toString());
				jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
						.toString());
				jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
						.toString());
				String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
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
				String xsbxnyhzzqk = DealString.toGBK(request.getParameter(
						"xsbxnyhzzqk").toString());
				String jtzszrzhqk = DealString.toGBK(request.getParameter(
						"jtzszrzhqk").toString());
				String jtzstfsjqk = DealString.toGBK(request.getParameter(
						"jtzstfsjqk").toString());
				String jtcyycjnmrldnlrqk = DealString.toGBK(request
						.getParameter("jtcyycjnmrldnlrqk").toString());
				String jtcysyqk = DealString.toGBK(request.getParameter(
						"jtcysyqk").toString());
				String jtqzqk = DealString.toGBK(request.getParameter("jtqzqk")
						.toString());
				String qtqk = DealString.toGBK(request.getParameter("qtqk")
						.toString());
				String mzbm_xxtxdz = DealString.toGBK(request.getParameter(
						"mzbm_xxtxdz").toString());
				String mzbm_yzbm = DealString.toGBK(request.getParameter(
						"mzbm_yzbm").toString());
				mzbm_lxdh1 = DealString.toGBK(request
						.getParameter("mzbm_lxdh1").toString());
				mzbm_lxdh2 = DealString.toGBK(request
						.getParameter("mzbm_lxdh2").toString());
				String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				StandardOperation.delete("shnl_xsjjtqkdc", "xh||nd", pkVal,
						request);

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "rxqhk",
						"jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
						"jtxxtxdz", "jtyzbm", "jtlxdh", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
						"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
						"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
						"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
						"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
						"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
						"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
						"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
						"jtcy5_jkzk", "jtrjnsr", "xsbxnyhzzqk", "jtzszrzhqk",
						"jtzstfsjqk", "jtcyycjnmrldnlrqk", "jtcysyqk",
						"jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
						"mzbm_lxdh" };

				valueForOut = new String[] { xh, nd, rxqhk, jtrks, byxx, grtc,
						sfgc, sfdq, sflszn, jtxxtxdz, jtyzbm, jtlxdh, jtcy1_xm,
						jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
						jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
						jtcy2_zy, jtcy2_nsr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl,
						jtcy3_gx, jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
						jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
						jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
						jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
						xsbxnyhzzqk, jtzszrzhqk, jtzstfsjqk, jtcyycjnmrldnlrqk,
						jtcysyqk, jtqzqk, qtqk, mzbm_xxtxdz, mzbm_yzbm,
						mzbm_lxdh };

				boolean ok = false;
				ok = StandardOperation.insert("shnl_xsjjtqkdc", colName1,
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
		pkVal = request.getParameter("pkVal");
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,csny,mzmc,"
				+ "zzmmmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfsjqk,jtcyycjnmrldnlrqk,jtcysyqk,"
				+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj from view_shnl_xsjjtqkdc "
				+ "where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "csny", "mzmc",
				"zzmmmc", "rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq",
				"sflszn", "jtxxtxdz", "jtyzbm", "jtlxdh", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
				"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
				"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
				"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
				"jtcy5_jkzk", "jtrjnsr", "xsbxnyhzzqk", "jtzszrzhqk",
				"jtzstfsjqk", "jtcyycjnmrldnlrqk", "jtcysyqk", "jtqzqk",
				"qtqk", "mzbm_xxtxdz", "mzbm_yzbm", "mzbm_lxdh", "sqsj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.sfzh,a.nj,b.csrq csny,"
						+ "b.mzmc,b.zzmmmc,"
						+ "(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) sqsj,"
						+ "(select dqnd nd from xtszb) nd from view_xsjbxx a,view_stu_details b "
						+ "where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xymc", "zymc",
						"bjmc", "xb", "sfzh", "nj", "csny", "mzmc", "zzmmmc",
						"sqsj", "nd" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xymc", "zymc",
							"bjmc", "xb", "sfzh", "nj", "csny", "mzmc",
							"zzmmmc", "sqsj", "nd" };
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
			String now = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
			map.put("sqsj", now);
			map.put("nd", nd);
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
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward shnl_xsjtqkdcb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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
		String csny = DealString.toGBK(request.getParameter("csny").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String rxqhk = DealString.toGBK(request.getParameter("rxqhk")
				.toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String byxx = DealString.toGBK(request.getParameter("byxx").toString());
		String grtc = DealString.toGBK(request.getParameter("grtc").toString());
		String sfgc = DealString.toGBK(request.getParameter("sfgc").toString());
		String sfdq = DealString.toGBK(request.getParameter("sfdq").toString());
		String sflszn = DealString.toGBK(request.getParameter("sflszn")
				.toString());
		String jtxxtxdz = DealString.toGBK(request.getParameter("jtxxtxdz")
				.toString());
		String jtyzbm = DealString.toGBK(request.getParameter("jtyzbm")
				.toString());
		String jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1")
				.toString());
		String jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2")
				.toString());
		String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
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
		String xsbxnyhzzqk = DealString.toGBK(request.getParameter(
				"xsbxnyhzzqk").toString());
		String jtzszrzhqk = DealString.toGBK(request.getParameter("jtzszrzhqk")
				.toString());
		String jtzstfsjqk = DealString.toGBK(request.getParameter("jtzstfsjqk")
				.toString());
		String jtcyycjnmrldnlrqk = DealString.toGBK(request.getParameter(
				"jtcyycjnmrldnlrqk").toString());
		String jtcysyqk = DealString.toGBK(request.getParameter("jtcysyqk")
				.toString());
		String jtqzqk = DealString.toGBK(request.getParameter("jtqzqk")
				.toString());
		String qtqk = DealString.toGBK(request.getParameter("qtqk").toString());
		String mzbm_xxtxdz = DealString.toGBK(request.getParameter(
				"mzbm_xxtxdz").toString());
		String mzbm_yzbm = DealString.toGBK(request.getParameter("mzbm_yzbm")
				.toString());
		String mzbm_lxdh1 = DealString.toGBK(request.getParameter("mzbm_lxdh1")
				.toString());
		String mzbm_lxdh2 = DealString.toGBK(request.getParameter("mzbm_lxdh2")
				.toString());
		String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;

		String[] outValue = new String[] { xh, xm, xb, sfzh, xymc, zymc, bjmc,
				nj, csny, mzmc, zzmmmc, rxqhk, jtrks, byxx, grtc, sfgc, sfdq,
				sflszn, jtxxtxdz, jtyzbm, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx,
				jtcy1_gzdw, jtcy1_zy, jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
				jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
				jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
				jtcy3_nsr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
				jtcy4_gzdw, jtcy4_zy, jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
				jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
				jtcy5_jkzk, jtrjnsr, xsbxnyhzzqk, jtzszrzhqk, jtzstfsjqk,
				jtcyycjnmrldnlrqk, jtcysyqk, jtqzqk, qtqk, mzbm_xxtxdz,
				mzbm_yzbm, mzbm_lxdh, xxmc };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "xymc",
				"zymc", "bjmc", "nj", "csny", "mzmc", "zzmmmc", "rxqhk",
				"jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn", "jtxxtxdz",
				"jtyzbm", "jtlxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xsbxnyhzzqk", "jtzszrzhqk", "jtzstfsjqk", "jtcyycjnmrldnlrqk",
				"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
				"mzbm_lxdh", "xxmc" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		outValue = new String[] { xxmc, xymc, zymc, bjmc, nj, sfgc, sfdq,
				sflszn, jtrjnsr, xsbxnyhzzqk, jtzszrzhqk, jtzstfsjqk,
				jtcyycjnmrldnlrqk, jtcysyqk, jtqzqk, qtqk, jtlxdh, mzbm_lxdh,
				xh, rxqhk };
		outString = new String[] { "xxmc", "xymc", "zymc", "bjmc", "nj",
				"sfgc", "sfdq", "sflszn", "jtrjnsr", "xsbxnyhzzqk",
				"jtzszrzhqk", "jtzstfsjqk", "jtcyycjnmrldnlrqk", "jtcysyqk",
				"jtqzqk", "qtqk", "jtlxdh", "mzbm_lxdh", "xh", "rxqhk" };
		for (int i = 0; i < outValue.length; i++) {
			if ((outValue[i] != null) && (!"".equalsIgnoreCase(outValue[i]))) {
				request.setAttribute(outString[i], outValue[i]);
			} else
				request.setAttribute(outString[i], "isnull");
		}
		return mapping.findForward("success");
	}

	private ActionForward shnl_kns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
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

		titNames = new String[] { "shnl_knsxx" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,"
				+ "yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,"
				+ "mzbm_lxdh,shjg,sqsj from view_shnl_knsxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

		// String zzdjdm = DealString.toGBK(xszzForm.getZzdjdm());
		// List xszzDjList = dao.getXszzDjList();
		// request.setAttribute("xszzDjList", xszzDjList);
		// sql = "select zzdjdm,zzdjmc,zzdjje from zzsf_xszzdjdmb where
		// zzdjdm=?";
		// String[] zzdj = dao.getOneRs(sql, new String[] { zzdjdm },
		// new String[] { "zzdjdm", "zzdjmc", "zzdjje" });

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

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
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

					sql = "select shjg from shnl_knsxx where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "shjg" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("特别困难") || temp[0]
									.equalsIgnoreCase("困难"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("shnl_knsxx", "xh||nd", pkVal,
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
								"qtqk", "mzbm_txdz", "mzbm_yzbm", "mzbm_lxdh" };

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
								mzbm_txdz, mzbm_yzbm, mzbm_lxdh };

						boolean ok = false;
						ok = StandardOperation.insert("shnl_knsxx", colName1,
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

				sql = "select shjg from shnl_knsxx where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "shjg" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("特别困难") || temp[0]
								.equalsIgnoreCase("困难"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("shnl_knsxx", "xh||nd", pkVal,
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
							"mzbm_lxdh" };

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
							jtzstfywsj, qtqk, mzbm_txdz, mzbm_yzbm, mzbm_lxdh };

					boolean ok = false;
					ok = StandardOperation.insert("shnl_knsxx", colName1,
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
				+ "mzbm_lxdh,shjg,sqsj from view_shnl_knsxx where xh||nd=?";
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
				"mzbm_yzbm", "mzbm_lxdh", "shjg", "sqsj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,"
						+ "a.mzmc,a.zzmmmc,a.rxqhk,a.jtxxtxdz jtzz,a.jtyzbm yzbm,a.jtlxdh,"
						+ "a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,"
						+ "a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,"
						+ "a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,"
						+ "a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,"
						+ "a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,"
						+ "a.jtrjnsr,a.jtzszrzhqk,a.jtzstfsjqk jtzstfywsj,a.qtqk,a.mzbm_xxtxdz mzbm_txdz,"
						+ "a.mzbm_yzbm,a.mzbm_lxdh from view_shnl_xsjjtqkdc a where a.xh=? and a.nd=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xymc", "zymc", "bjmc", "nj", "mzmc", "zzmmmc",
						"rxqhk", "jtzz", "yzbm", "jtlxdh", "jtcy1_xm",
						"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
						"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
						"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
						"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
						"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
						"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
						"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
						"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
						"jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr", "jtzszrzhqk",
						"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
						"mzbm_lxdh" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh, nd },
						colName);
				if (outVal == null) {
					request.setAttribute("notSq", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh",
							"xymc", "zymc", "bjmc", "nj", "mzmc", "zzmmmc",
							"rxqhk", "jtzz", "yzbm", "jtlxdh", "jtcy1_xm",
							"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
							"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
							"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
							"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
							"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
							"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
							"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
							"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
							"jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr", "jtzszrzhqk",
							"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
							"mzbm_lxdh" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
							if ("jtlxdh".equalsIgnoreCase(colName[i])) {
								String[] temS = outVal[i].split("-");
								map.put("jtlxdh1", temS[0]);
								map.put("jtlxdh2", temS[1]);
							}
							if ("mzbm_lxdh".equalsIgnoreCase(colName[i])) {
								String[] temS = outVal[i].split("-");
								map.put("mzbm_lxdh1", temS[0]);
								map.put("mzbm_lxdh2", temS[1]);
							}
						} else {
							map.put(colName[i], "");
						}
					}
				}
			}
		} else {
			String now = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
			map.put("sqsj", now);
			map.put("nd", nd);
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

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward shnl_knsb(ActionMapping mapping, ActionForm form,
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
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String rxqhk = DealString.toGBK(request.getParameter("rxqhk").toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String jtlxdh1 = DealString.toGBK(request.getParameter("jtlxdh1").toString());
		String jtlxdh2 = DealString.toGBK(request.getParameter("jtlxdh2").toString());
		String jtlxdh = jtlxdh1 + "-" + jtlxdh2;
		String sfyycjcshzyhd = DealString.toGBK(request.getParameter("sfyycjcshzyhd").toString());
		String sfyysqgjzxdkhqgzx = DealString.toGBK(request.getParameter("sfyysqgjzxdkhqgzx").toString());
		String sfjq = DealString.toGBK(request.getParameter("sfjq").toString());
		String sfge = DealString.toGBK(request.getParameter("sfge").toString());
		String sfdq = DealString.toGBK(request.getParameter("sfdq").toString());
		String sfcj = DealString.toGBK(request.getParameter("sfcj").toString());
		String sfjls = DealString.toGBK(request.getParameter("sfjls").toString());
		String sfly = DealString.toGBK(request.getParameter("sfly").toString());
		String sfzb = DealString.toGBK(request.getParameter("sfzb").toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm").toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl").toString());
		String jtcy1_gx = DealString.toGBK(request.getParameter("jtcy1_gx").toString());
		String jtcy1_gzdw = DealString.toGBK(request.getParameter("jtcy1_gzdw").toString());
		String jtcy1_zy = DealString.toGBK(request.getParameter("jtcy1_zy").toString());
		String jtcy1_nsr = DealString.toGBK(request.getParameter("jtcy1_nsr").toString());
		String jtcy1_jkzk = DealString.toGBK(request.getParameter("jtcy1_jkzk").toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm").toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl").toString());
		String jtcy2_gx = DealString.toGBK(request.getParameter("jtcy2_gx").toString());
		String jtcy2_gzdw = DealString.toGBK(request.getParameter("jtcy2_gzdw").toString());
		String jtcy2_zy = DealString.toGBK(request.getParameter("jtcy2_zy").toString());
		String jtcy2_nsr = DealString.toGBK(request.getParameter("jtcy2_nsr").toString());
		String jtcy2_jkzk = DealString.toGBK(request.getParameter("jtcy2_jkzk").toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm").toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl").toString());
		String jtcy3_gx = DealString.toGBK(request.getParameter("jtcy3_gx").toString());
		String jtcy3_gzdw = DealString.toGBK(request.getParameter("jtcy3_gzdw").toString());
		String jtcy3_zy = DealString.toGBK(request.getParameter("jtcy3_zy").toString());
		String jtcy3_nsr = DealString.toGBK(request.getParameter("jtcy3_nsr").toString());
		String jtcy3_jkzk = DealString.toGBK(request.getParameter("jtcy3_jkzk").toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm").toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl").toString());
		String jtcy4_gx = DealString.toGBK(request.getParameter("jtcy4_gx").toString());
		String jtcy4_gzdw = DealString.toGBK(request.getParameter("jtcy4_gzdw").toString());
		String jtcy4_zy = DealString.toGBK(request.getParameter("jtcy4_zy").toString());
		String jtcy4_nsr = DealString.toGBK(request.getParameter("jtcy4_nsr").toString());
		String jtcy4_jkzk = DealString.toGBK(request.getParameter("jtcy4_jkzk").toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm").toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl").toString());
		String jtcy5_gx = DealString.toGBK(request.getParameter("jtcy5_gx").toString());
		String jtcy5_gzdw = DealString.toGBK(request.getParameter("jtcy5_gzdw").toString());
		String jtcy5_zy = DealString.toGBK(request.getParameter("jtcy5_zy").toString());
		String jtcy5_nsr = DealString.toGBK(request.getParameter("jtcy5_nsr").toString());
		String jtcy5_jkzk = DealString.toGBK(request.getParameter("jtcy5_jkzk").toString());
		String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr").toString());
		String xszbdszqk = DealString.toGBK(request.getParameter("xszbdszqk").toString());
		String jtzszrzhqk = DealString.toGBK(request.getParameter("jtzszrzhqk").toString());
		String jtzstfywsj = DealString.toGBK(request.getParameter("jtzstfywsj").toString());
		String qtqk = DealString.toGBK(request.getParameter("qtqk").toString());
		String mzbm_txdz = DealString.toGBK(request.getParameter("mzbm_txdz").toString());
		String mzbm_yzbm = DealString.toGBK(request.getParameter("mzbm_yzbm").toString());
		String mzbm_lxdh1 = DealString.toGBK(request.getParameter("mzbm_lxdh1").toString());
		String mzbm_lxdh2 = DealString.toGBK(request.getParameter("mzbm_lxdh2").toString());
		String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;

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
				mzbm_lxdh };
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
				"mzbm_yzbm", "mzbm_lxdh" };
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
				jtzstfywsj, qtqk, mzbm_lxdh };
		outString = new String[] { "xxmc", "xymc", "zymc", "bjmc", "nj", "xh",
				"rxqhk", "jtlxdh", "sfyycjcshzyhd", "sfyysqgjzxdkhqgzx",
				"sfjq", "sfge", "sfdq", "sfcj", "sfjls", "sfly", "sfzb",
				"jtrjnsr", "xszbdszqk", "jtzszrzhqk", "jtzstfywsj", "qtqk",
				"mzbm_lxdh" };
		for (int i = 0; i < outValue.length; i++) {
			if ((outValue[i] != null) && (!"".equalsIgnoreCase(outValue[i]))) {
				request.setAttribute(outString[i], outValue[i]);
			} else
				request.setAttribute(outString[i], "isnull");
		}
		return mapping.findForward("success");
	}

	private ActionForward auditing_shnl_kns(ActionMapping mapping,
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
		realTable = "shnl_knsxx";
		pk = "xh||nd";
		tableName = "view_shnl_knsxx";

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
		tips = "当前所在位置：学生资助 - 审核 - 家庭经济困难学生认定审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 家庭经济困难学生认定";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqsj", "shjg" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.shjg,'未审核') in ('特别困难','困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by shjg desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.sfjq||'##OneSpile##'||a.sfge||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfjls||'##OneSpile##'||a.sfly||'##OneSpile##'||a.sfzb||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.shjg||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by shjg desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.shjg,'未审核') in ('特别困难','困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by shjg desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.sfjq||'##OneSpile##'||a.sfge||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfjls||'##OneSpile##'||a.sfly||'##OneSpile##'||a.sfzb||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.shjg||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by shjg desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqsj", "shjg" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.shjg,'未审核') in ('特别困难','困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by shjg desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.sfjq||'##OneSpile##'||a.sfge||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfjls||'##OneSpile##'||a.sfly||'##OneSpile##'||a.sfzb||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.shjg||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by shjg desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.shjg,'未审核') in ('特别困难','困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by shjg desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.sfyycjcshzyhd||'##OneSpile##'||a.sfyysqgjzxdkhqgzx||'##OneSpile##'||a.sfjq||'##OneSpile##'||a.sfge||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfjls||'##OneSpile##'||a.sfly||'##OneSpile##'||a.sfzb||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xszbdszqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_txdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.shjg||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep + "' order by shjg desc";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##nj##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##lxdh##OneSpile##rxqhk##OneSpile##jtzz##OneSpile##yzbm##OneSpile##jtlxdh##OneSpile##sfyycjcshzyhd##OneSpile##sfyysqgjzxdkhqgzx##OneSpile##sfjq##OneSpile##sfge##OneSpile##sfdq##OneSpile##sfcj##OneSpile##sfjls##OneSpile##sfly##OneSpile##sfzb##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_zy##OneSpile##jtcy1_nsr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_zy##OneSpile##jtcy2_nsr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_zy##OneSpile##jtcy3_nsr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_zy##OneSpile##jtcy4_nsr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_zy##OneSpile##jtcy5_nsr##OneSpile##jtcy5_jkzk##OneSpile##jtrjnsr##OneSpile##xszbdszqk##OneSpile##jtzszrzhqk##OneSpile##jtzstfywsj##OneSpile##qtqk##OneSpile##mzbm_txdz##OneSpile##mzbm_yzbm##OneSpile##mzbm_lxdh##OneSpile##shjg##OneSpile##sqsj";
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
		map.put("nd", nd);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
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
		request.setAttribute("act", "shnl_knsxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_shnl_kns_one(ActionMapping mapping,
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
		String yn = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;

		if ("tbkn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "update shnl_knsxx set shjg='特别困难' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shnl_kns.do?go=go", false);
			return newFwd;
		}
		if ("kn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "update shnl_knsxx set shjg='困难' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shnl_kns.do?go=go", false);
			return newFwd;
		}
		if ("bkn".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "update shnl_knsxx set shjg='不困难' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shnl_kns.do?go=go", false);
			return newFwd;
		}
		if ("wsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "update shnl_knsxx set shjg='未审核' where xh||nd='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shnl_kns.do?go=go", false);
			return newFwd;
		}
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete shnl_knsxx where xh||nd='"+pkT+"' and shjg not in('特别困难','困难')";
				} else {
					sqlT[i] = "delete shnl_knsxx where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_shnl_kns.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			ok = StandardOperation.update("shnl_knsxx", new String[] { "shjg" },
					new String[] { yesNo }, "xh||nd", pkVal, request);
			if (ok) {
				logMsg = "修改(审核) shnl_knsxx";
				Base.log(request, logMsg, sUName);
			}
		}

		realTable = "shnl_knsxx";
		pk = "xh||nd";
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
				+ "mzbm_lxdh,shjg,sqsj,shjg yesNo from "
				+ "view_shnl_knsxx where " + pk + "='" + pkVal + "'";
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
				"mzbm_yzbm", "mzbm_lxdh", "shjg", "sqsj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("yesNo")) {
				yn = rs[i];
			}
			request.setAttribute(colList[i], rs[i]);
		}
		hs.put("yesNo", yn);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(14));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "shnl_knsxx");
		return mapping.findForward("success");
	}
}
