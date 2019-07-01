/*
 * 创建日期 2007-10-31 zhoumi
 *
 */
package xgxt.action.hzzyjsxy;

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
		try {
//			/** 在线检测 */
//			int i = Base.chkTimeOut(session);
//			if (i <= 2) {
//				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
//				return new ActionForward("/login.jsp", false);
//			}
			String writeAble;
			int p = -1;

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			// String power = "";
			if (act.equalsIgnoreCase("hzzy_jtqkdc")) {// 杭州职业技术学院-家庭情况调查
				myActFwd = hzzy_jtqkdc(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("hzzy_jtqkdcb")) {// 杭州职业技术学院-家庭情况调查表
				myActFwd = hzzy_jtqkdcb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_jtqkdc")) {// 杭州职业技术学院-家庭情况调查表审核
				myActFwd = auditing_hzzy_jtqkdc(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_jtqkdc_one")) {// 杭州职业技术学院-家庭情况调查表单个审核
				myActFwd = auditing_hzzy_jtqkdc_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("hzzy_lstdsq")) {// 杭州职业技术学院-绿色通道申请
				myActFwd = hzzy_lstdsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("hzzy_lstdsqb")) {// 杭州职业技术学院-绿色通道申请表
				myActFwd = hzzy_lstdsqb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_lstdsq")) {// 杭州职业技术学院-绿色通道审核
				myActFwd = auditing_hzzy_lstdsq(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_lstdsq_one")) {// 杭州职业技术学院-绿色通道单个审核
				myActFwd = auditing_hzzy_lstdsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("hzzy_gajjsq")) {// 杭州职业技术学院-关爱基金申请
				myActFwd = hzzy_gajjsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("hzzy_gajjsqb")) {// 杭州职业技术学院-关爱基金申请表
				myActFwd = hzzy_gajjsqb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_gajjsq")) {// 杭州职业技术学院-关爱基金审核
				myActFwd = auditing_hzzy_gajjsq(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_gajjsq_one")) {// 杭州职业技术学院-关爱基金单个审核
				myActFwd = auditing_hzzy_gajjsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("hzzy_pksknbz")) {// 杭州职业技术学院-贫困生困难补助申请
				myActFwd = hzzy_pksknbz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("hzzy_pksknbzb")) {// 杭州职业技术学院-贫困生困难补助申请表
				myActFwd = hzzy_pksknbzb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_pksknbz")) {// 杭州职业技术学院-贫困生困难补助审核
				myActFwd = auditing_hzzy_pksknbz(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_pksknbz_one")) {// 杭州职业技术学院-贫困生困难补助单个审核
				myActFwd = auditing_hzzy_pksknbz_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("hzzy_jqzx")) {// 杭州职业技术学院-开发区慈善总会"金秋助学"申请
				myActFwd = hzzy_jqzx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("hzzy_jqzxb")) {// 杭州职业技术学院-开发区慈善总会"金秋助学"申请表
				myActFwd = hzzy_jqzxb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_jqzx")) {// 杭州职业技术学院-开发区慈善总会"金秋助学"审核
				myActFwd = auditing_hzzy_jqzx(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_hzzy_jqzx_one")) {// 杭州职业技术学院-开发区慈善总会"金秋助学"单个审核
				myActFwd = auditing_hzzy_jqzx_one(mapping, form, request,
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

	private ActionForward hzzy_jtqkdc(ActionMapping mapping, ActionForm form,
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

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "hzzy_jtqkdc" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String[] outString = new String[] {};
		sql = "select xh,xm,xb,csny,zzmmmc,mzmc,sfzh,xydm,xymc,zydm,zymc,"
				+ "bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfywsj,jtcyycjnmndlrqk,jtcysyqk,"
				+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,xysh,xxsh from "
				+ "VIEW_HZZY_JTQKDCB where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";

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
				String xxtxdz = DealString.toGBK(request.getParameter("xxtxdz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String lxdh1 = DealString.toGBK(request.getParameter("lxdh1")
						.toString());
				String lxdh2 = DealString.toGBK(request.getParameter("lxdh2")
						.toString());
				String lxdh = lxdh1 + "-" + lxdh2;
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
				String jtzstfywsj = DealString.toGBK(request.getParameter(
						"jtzstfywsj").toString());
				String jtcyycjnmndlrqk = DealString.toGBK(request.getParameter(
						"jtcyycjnmndlrqk").toString());
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
				String mzbm_lxdh1 = DealString.toGBK(request.getParameter(
						"mzbm_lxdh1").toString());
				String mzbm_lxdh2 = DealString.toGBK(request.getParameter(
						"mzbm_lxdh2").toString());
				String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;

				sql = "select xxsh from HZZY_JTQKDCB where xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { xh },
						new String[] { "xxsh" });
				if ((temp != null)
						&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
								.equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("HZZY_JTQKDCB", "xh", xh, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "rxqhk", "jtrks",
							"byxx", "grtc", "sfgc", "sfdq", "sflszn", "xxtxdz",
							"yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
							"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
							"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
							"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
							"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
							"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
							"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
							"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
							"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
							"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
							"jtcy5_jkzk", "jtrjnsr", "xsbxnyhzzqk",
							"jtzszrzhqk", "jtzstfywsj", "jtcyycjnmndlrqk",
							"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz",
							"mzbm_yzbm", "mzbm_lxdh" };

					valueForOut = new String[] { xh, rxqhk, jtrks, byxx, grtc,
							sfgc, sfdq, sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm,
							jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
							jtcy1_nsr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
							jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
							jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
							jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
							jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
							jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl,
							jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
							jtcy5_jkzk, jtrjnsr, xsbxnyhzzqk, jtzszrzhqk,
							jtzstfywsj, jtcyycjnmndlrqk, jtcysyqk, jtqzqk,
							qtqk, mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh };

					boolean ok = false;
					ok = StandardOperation.insert("HZZY_JTQKDCB", colName1,
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
				String xxtxdz = DealString.toGBK(request.getParameter("xxtxdz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String lxdh1 = DealString.toGBK(request.getParameter("lxdh1")
						.toString());
				String lxdh2 = DealString.toGBK(request.getParameter("lxdh2")
						.toString());
				String lxdh = lxdh1 + "-" + lxdh2;
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
				String jtzstfywsj = DealString.toGBK(request.getParameter(
						"jtzstfywsj").toString());
				String jtcyycjnmndlrqk = DealString.toGBK(request.getParameter(
						"jtcyycjnmndlrqk").toString());
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
				String mzbm_lxdh1 = DealString.toGBK(request.getParameter(
						"mzbm_lxdh1").toString());
				String mzbm_lxdh2 = DealString.toGBK(request.getParameter(
						"mzbm_lxdh2").toString());
				String mzbm_lxdh = mzbm_lxdh1 + "-" + mzbm_lxdh2;

				sql = "select xxsh from HZZY_JTQKDCB where xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { xh },
						new String[] { "xxsh" });
				if ((temp != null)
						&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
								.equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("HZZY_JTQKDCB", "xh", xh, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "rxqhk", "jtrks",
							"byxx", "grtc", "sfgc", "sfdq", "sflszn", "xxtxdz",
							"yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
							"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
							"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
							"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
							"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
							"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
							"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
							"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
							"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
							"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
							"jtcy5_jkzk", "jtrjnsr", "xsbxnyhzzqk",
							"jtzszrzhqk", "jtzstfywsj", "jtcyycjnmndlrqk",
							"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz",
							"mzbm_yzbm", "mzbm_lxdh" };

					valueForOut = new String[] { xh, rxqhk, jtrks, byxx, grtc,
							sfgc, sfdq, sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm,
							jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
							jtcy1_nsr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
							jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
							jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
							jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
							jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
							jtcy4_nsr, jtcy4_jkzk, jtcy5_xm, jtcy5_nl,
							jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
							jtcy5_jkzk, jtrjnsr, xsbxnyhzzqk, jtzszrzhqk,
							jtzstfywsj, jtcyycjnmndlrqk, jtcysyqk, jtqzqk,
							qtqk, mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh };

					boolean ok = false;
					ok = StandardOperation.insert("HZZY_JTQKDCB", colName1,
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

		sql = "select xh,xm,xb,csny,zzmmmc,mzmc,sfzh,xydm,xymc,zydm,zymc,"
				+ "bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfywsj,jtcyycjnmndlrqk,jtcysyqk,"
				+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,xysh,xxsh from "
				+ "VIEW_HZZY_JTQKDCB where xh=?";
		outString = new String[] { "xh", "xm", "xb", "csny", "zzmmmc", "mzmc",
				"sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
				"xxtxdz", "yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xsbxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjnmndlrqk",
				"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
				"mzbm_lxdh", "sqsj", "xysh", "xxsh" };
		outValue = dao.getOneRs(sql, new String[] { xh }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,b.csrq csny,b.mzmc,b.zzmmmc "
						+ "from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xymc", "zymc", "bjmc", "csny", "mzmc", "zzmmmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {

				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh", "xymc",
							"zymc", "bjmc", "csny", "mzmc", "zzmmmc" };
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
				} else {
					map.put(outString[i], "");
				}
			}
			sql = "select lxdh||' ' lxdh,mzbm_lxdh||' ' mzbm_lxdh from VIEW_HZZY_JTQKDCB where xh=?";
			outValue = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"lxdh", "mzbm_lxdh" });
			String lxdh[] = outValue[0].split("-");
			map.put("lxdh1", lxdh[0]);
			map.put("lxdh2", lxdh[1]);
			String mzbm_lxdh[] = outValue[1].split("-");
			map.put("mzbm_lxdh1", mzbm_lxdh[0]);
			map.put("mzbm_lxdh2", mzbm_lxdh[1]);
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward hzzy_jtqkdcb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String csny = DealString.toGBK(request.getParameter("csny").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
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
		String xxtxdz = DealString.toGBK(request.getParameter("xxtxdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh1 = DealString.toGBK(request.getParameter("lxdh1")
				.toString());
		String lxdh2 = DealString.toGBK(request.getParameter("lxdh2")
				.toString());
		String lxdh = lxdh1 + "-" + lxdh2;
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
		String jtzstfywsj = DealString.toGBK(request.getParameter("jtzstfywsj")
				.toString());
		String jtcyycjnmndlrqk = DealString.toGBK(request.getParameter(
				"jtcyycjnmndlrqk").toString());
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

		String[] outValue = new String[] { xh, xm, xb, csny, zzmmmc, mzmc,
				sfzh, xymc, zymc, bjmc, rxqhk, jtrks, byxx, grtc, sfgc, sfdq,
				sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx,
				jtcy1_gzdw, jtcy1_zy, jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
				jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_nsr,
				jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
				jtcy3_nsr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
				jtcy4_gzdw, jtcy4_zy, jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
				jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_nsr,
				jtcy5_jkzk, jtrjnsr, xsbxnyhzzqk, jtzszrzhqk, jtzstfywsj,
				jtcyycjnmndlrqk, jtcysyqk, jtqzqk, qtqk, mzbm_xxtxdz,
				mzbm_yzbm, mzbm_lxdh };
		String[] outString = new String[] { "xh", "xm", "xb", "csny", "zzmmmc",
				"mzmc", "sfzh", "xymc", "zymc", "bjmc", "rxqhk", "jtrks",
				"byxx", "grtc", "sfgc", "sfdq", "sflszn", "xxtxdz", "yzbm",
				"lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
				"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
				"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xsbxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjnmndlrqk",
				"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
				"mzbm_lxdh" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	private ActionForward auditing_hzzy_jtqkdc(ActionMapping mapping,
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
		String nj = request.getParameter("nj");
		String xm = Base.chgNull(request.getParameter("xm"),"",1);
		String xb = Base.chgNull(request.getParameter("xb"),"",1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"),"",1);
		String kndj = Base.chgNull(request.getParameter("kndj"),"",1);
		String xxtxdz = Base.chgNull(request.getParameter("xxtxdz"),"",1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
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
		realTable = "HZZY_JTQKDCB";
		pk = "xh";
		tableName = "view_HZZY_JTQKDCB";
		
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
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xm)) {
			querry.append(" and xm='");
			querry.append(xm);
			querry.append("' ");
		}
		if (!isNull(xb)) {
			querry.append(" and xb='");
			querry.append(xb);
			querry.append("' ");
		}
		if (!isNull(xxtxdz)) {
			querry.append(" and xxtxdz='");
			querry.append(xxtxdz);
			querry.append("' ");
		}
		if (!isNull(sfzh)) {
			querry.append(" and sfzh='");
			querry.append(sfzh);
			querry.append("' ");
		}
		if (!isNull(kndj)) {
			querry.append(" and kndj='");
			querry.append(kndj);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 学生及家庭情况调查审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 学生及家庭情况调查";
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsbxnyhzzqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyycjnmndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_xxtxdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsbxnyhzzqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyycjnmndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_xxtxdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsbxnyhzzqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyycjnmndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_xxtxdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.byxx||'##OneSpile##'||a.grtc||'##OneSpile##'||a.sfgc||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_zy||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_zy||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_zy||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_zy||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_zy||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xsbxnyhzzqk||'##OneSpile##'||a.jtzszrzhqk||'##OneSpile##'||a.jtzstfywsj||'##OneSpile##'||a.jtcyycjnmndlrqk||'##OneSpile##'||a.jtcysyqk||'##OneSpile##'||a.jtqzqk||'##OneSpile##'||a.qtqk||'##OneSpile##'||a.mzbm_xxtxdz||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
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
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##csny##OneSpile##zzmmmc##OneSpile##mzmc##OneSpile##sfzh##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##rxqhk##OneSpile##jtrks##OneSpile##byxx##OneSpile##grtc##OneSpile##sfgc##OneSpile##sfdq##OneSpile##sflszn##OneSpile##xxtxdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_zy##OneSpile##jtcy1_nsr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_zy##OneSpile##jtcy2_nsr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_zy##OneSpile##jtcy3_nsr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_zy##OneSpile##jtcy4_nsr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_zy##OneSpile##jtcy5_nsr##OneSpile##jtcy5_jkzk##OneSpile##jtrjnsr##OneSpile##xsbxnyhzzqk##OneSpile##jtzszrzhqk##OneSpile##jtzstfywsj##OneSpile##jtcyycjnmndlrqk##OneSpile##jtcysyqk##OneSpile##jtqzqk##OneSpile##qtqk##OneSpile##mzbm_xxtxdz##OneSpile##mzbm_yzbm##OneSpile##mzbm_lxdh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##nj";
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
		map.put("xh", xh);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("kndj", kndj);
		map.put("sfzh", sfzh);
		map.put("xxtxdz", xxtxdz);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
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
		request.setAttribute("act", "HZZY_JTQKDCB");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_jtqkdc_one(ActionMapping mapping,
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
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete HZZY_JTQKDCB where xh='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete HZZY_JTQKDCB where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_hzzy_jtqkdc.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("HZZY_JTQKDCB",
						new String[] { "xysh" }, new String[] { yesNo }, "xh",
						pkVal, request);
			} else {
				ok = StandardOperation.update("HZZY_JTQKDCB",
						new String[] { "xxsh" }, new String[] { yesNo }, "xh",
						pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) HZZY_JTQKDCB";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "HZZY_JTQKDCB";
		pk = "xh";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,xm,xb,csny,zzmmmc,mzmc,sfzh,xydm,xymc,zydm,zymc,"
					+ "bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
					+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfywsj,jtcyycjnmndlrqk,jtcysyqk,"
					+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,xysh,xxsh,XYSH yesNo "
					+ "from view_HZZY_JTQKDCB where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xm,xb,csny,zzmmmc,mzmc,sfzh,xydm,xymc,zydm,zymc,"
					+ "bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,"
					+ "jtrjnsr,xsbxnyhzzqk,jtzszrzhqk,jtzstfywsj,jtcyycjnmndlrqk,jtcysyqk,"
					+ "jtqzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,xysh,xxsh,XXSH yesNo "
					+ "from view_HZZY_JTQKDCB where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xm", "xb", "csny", "zzmmmc",
				"mzmc", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
				"xxtxdz", "yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
				"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk", "jtrjnsr",
				"xsbxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjnmndlrqk",
				"jtcysyqk", "jtqzqk", "qtqk", "mzbm_xxtxdz", "mzbm_yzbm",
				"mzbm_lxdh", "sqsj", "xysh", "xxsh", "yesNo" };

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
		request.setAttribute("tName", realTable);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("act", "HZZY_JTQKDCB");
		return mapping.findForward("success");

	}

	private ActionForward hzzy_lstdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		String sUName;

		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "hzzy_lstdsq" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,qfyy,xjnxf,xjnxf_dx,xjndgf,xjndgf_dx,"
				+ "hj,hj_dx,qybfhkfshsj,sqsj,xysh,xxsh from VIEW_hzzy_lstdsqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='绿色通道' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String qfyy = DealString.toGBK(request.getParameter("qfyy")
							.toString());
					String xjnxf = DealString.toGBK(request.getParameter(
							"xjnxf").toString());
					String xjndgf = DealString.toGBK(request.getParameter(
							"xjndgf").toString());
					String hj = DealString.toGBK(request.getParameter("hj")
							.toString());
					String qybfhkfshsj = DealString.toGBK(
							request.getParameter("qybfhkfshsj").toString());

					String sqlT = "{call pro_Disp_dxje(?,?)}";
					String[] jedxT = dao.getProVal(sqlT,
							new String[] { xjnxf }, new int[] { 2 });
					String xjnxf_dx = jedxT[0];
					jedxT = dao.getProVal(sqlT, new String[] { xjndgf },
							new int[] { 2 });
					String xjndgf_dx = jedxT[0];
					jedxT = dao.getProVal(sqlT, new String[] { hj },
							new int[] { 2 });
					String hj_dx = jedxT[0];
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					sql = "select xxsh from hzzy_lstdsqb where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("hzzy_lstdsqb", "xh||nd", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd", "qfyy",
								"xjnxf", "xjnxf_dx", "xjndgf", "xjndgf_dx",
								"hj", "hj_dx", "qybfhkfshsj" };

						valueForOut = new String[] { xh, nd, qfyy, xjnxf,
								xjnxf_dx, xjndgf, xjndgf_dx, hj, hj_dx,
								qybfhkfshsj };

						boolean ok = false;
						ok = StandardOperation.insert("hzzy_lstdsqb", colName1,
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
				String qfyy = DealString.toGBK(request.getParameter("qfyy")
						.toString());
				String xjnxf = DealString.toGBK(request.getParameter("xjnxf")
						.toString());
				String xjndgf = DealString.toGBK(request.getParameter("xjndgf")
						.toString());
				String hj = DealString.toGBK(request.getParameter("hj")
						.toString());
				String qybfhkfshsj = DealString.toGBK(
						request.getParameter("qybfhkfshsj").toString());

				String sqlT = "{call pro_Disp_dxje(?,?)}";
				String[] jedxT = dao.getProVal(sqlT, new String[] { xjnxf },
						new int[] { 2 });
				String xjnxf_dx = jedxT[0];
				jedxT = dao.getProVal(sqlT, new String[] { xjndgf },
						new int[] { 2 });
				String xjndgf_dx = jedxT[0];
				jedxT = dao.getProVal(sqlT, new String[] { hj },
						new int[] { 2 });
				String hj_dx = jedxT[0];
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from hzzy_lstdsqb where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("hzzy_lstdsqb", "xh||nd", pkVal, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "qfyy",
							"xjnxf", "xjnxf_dx", "xjndgf", "xjndgf_dx",
							"hj", "hj_dx", "qybfhkfshsj" };

					valueForOut = new String[] { xh, nd, qfyy, xjnxf,
							xjnxf_dx, xjndgf, xjndgf_dx, hj, hj_dx,
							qybfhkfshsj };

					boolean ok = false;
					ok = StandardOperation.insert("hzzy_lstdsqb", colName1,
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
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,qfyy,xjnxf,xjnxf_dx,xjndgf,xjndgf_dx,"
				+ "hj,hj_dx,qybfhkfshsj,sqsj,xysh,xxsh from view_hzzy_lstdsqb where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "qfyy", "xjnxf", "xjnxf_dx", "xjndgf",
				"xjndgf_dx", "hj", "hj_dx", "qybfhkfshsj", "sqsj", "xysh",
				"xxsh" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.sqsj from view_xsjbxx a,"
						+ "(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) b where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xymc", "zymc",
						"bjmc", "sqsj" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
					request.setAttribute("notSQ", "yes");
				} else {
					colName = new String[] { "xh", "xm", "xymc", "zymc",
							"bjmc", "sqsj" };
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

	private ActionForward hzzy_lstdsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String qfyy = DealString.toGBK(request.getParameter("qfyy").toString());
		String xjnxf = DealString.toGBK(request.getParameter("xjnxf")
				.toString());
		String xjndgf = DealString.toGBK(request.getParameter("xjndgf")
				.toString());
		String hj = DealString.toGBK(request.getParameter("hj").toString());
		String qybfhkfshsj = DealString.toGBK(
				request.getParameter("qybfhkfshsj").toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') da from dual",
					new String[] {}, new String("da"));
		}
		String sqsj_year = sqsj.substring(0, 4);
		String sqsj_mon = sqsj.substring(5, 7);
		String sqsj_day = sqsj.substring(8, 10);
		
		if((null == xjnxf) || ("".equalsIgnoreCase(xjnxf))){
			xjnxf = "0";
		}
		if((null == xjndgf) || ("".equalsIgnoreCase(xjndgf))){
			xjndgf = "0";
		}
		if((null == hj) || ("".equalsIgnoreCase(hj))){
			hj = "0";
		}

		String sqlT = "{call pro_Disp_dxje(?,?)}";
		String[] jedxT = dao.getProVal(sqlT, new String[] { xjnxf },
				new int[] { 2 });
		String xjnxf_dx = jedxT[0];
		jedxT = dao.getProVal(sqlT, new String[] { xjndgf }, new int[] { 2 });
		String xjndgf_dx = jedxT[0];
		jedxT = dao.getProVal(sqlT, new String[] { hj }, new int[] { 2 });
		String hj_dx = jedxT[0];
		
		xjnxf = dao.internationalMoney(xjnxf);
		xjndgf = dao.internationalMoney(xjndgf);
		hj = dao.internationalMoney(hj);

		if ("0".equalsIgnoreCase(xjnxf) && "0".equalsIgnoreCase(xjndgf)
				&& "0".equalsIgnoreCase(hj)) {
			request.setAttribute("isNULL", "is");
		} else {
			request.setAttribute("isNULL", "no");
		}
		if (xjnxf_dx.equalsIgnoreCase("零元整")){
			xjnxf_dx = " ";
		}
		if (xjndgf_dx.equalsIgnoreCase("零元整")){
			xjndgf_dx = " ";
		}
		if (hj_dx.equalsIgnoreCase("零元整")){
			hj_dx = " ";
		}
		
		String[] outValue = new String[] { xh, xm, xymc, bjmc, qfyy, xjnxf,
				xjnxf_dx, xjndgf, xjndgf_dx, hj, hj_dx, qybfhkfshsj, sqsj_year,
				sqsj_mon, sqsj_day };
		String[] outString = new String[] { "xh", "xm", "xymc", "bjmc", "qfyy",
				"xjnxf", "xjnxf_dx", "xjndgf", "xjndgf_dx", "hj", "hj_dx",
				"qybfhkfshsj", "sqsj_year", "sqsj_mon", "sqsj_day" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_lstdsq(ActionMapping mapping,
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
		String tips = "";
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
		realTable = "hzzy_lstdsqb";
		pk = "xh||nd";
		tableName = "view_hzzy_lstdsqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 绿色通道审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 绿色通道";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.qfyy||'##OneSpile##'||a.xjnxf||'##OneSpile##'||a.xjnxf_dx||'##OneSpile##'||a.xjndgf||'##OneSpile##'||a.xjndgf_dx||'##OneSpile##'||a.hj||'##OneSpile##'||a.hj_dx||'##OneSpile##'||a.qybfhkfshsj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.qfyy||'##OneSpile##'||a.xjnxf||'##OneSpile##'||a.xjnxf_dx||'##OneSpile##'||a.xjndgf||'##OneSpile##'||a.xjndgf_dx||'##OneSpile##'||a.hj||'##OneSpile##'||a.hj_dx||'##OneSpile##'||a.qybfhkfshsj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.qfyy||'##OneSpile##'||a.xjnxf||'##OneSpile##'||a.xjnxf_dx||'##OneSpile##'||a.xjndgf||'##OneSpile##'||a.xjndgf_dx||'##OneSpile##'||a.hj||'##OneSpile##'||a.hj_dx||'##OneSpile##'||a.qybfhkfshsj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.qfyy||'##OneSpile##'||a.xjnxf||'##OneSpile##'||a.xjnxf_dx||'##OneSpile##'||a.xjndgf||'##OneSpile##'||a.xjndgf_dx||'##OneSpile##'||a.hj||'##OneSpile##'||a.hj_dx||'##OneSpile##'||a.qybfhkfshsj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##qfyy##OneSpile##xjnxf##OneSpile##xjnxf_dx##OneSpile##xjndgf##OneSpile##xjndgf_dx##OneSpile##hj##OneSpile##hj_dx##OneSpile##qybfhkfshsj##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##nj";
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
		request.setAttribute("act", "HZZY_JTQKDCB");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_lstdsq_one(ActionMapping mapping,
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
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete hzzy_lstdsqb where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete hzzy_lstdsqb where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_hzzy_lstdsq.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("hzzy_lstdsqb",
						new String[] { "xysh" }, new String[] { yesNo },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("hzzy_lstdsqb",
						new String[] { "xxsh" }, new String[] { yesNo },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) hzzy_lstdsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "hzzy_lstdsqb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,qfyy,xjnxf,"
					+ "xjnxf_dx,xjndgf,xjndgf_dx,hj,hj_dx,qybfhkfshsj,sqsj,xysh,"
					+ "xxsh,XYSH yesNo from view_hzzy_lstdsqb where " + pk
					+ "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xydm,xymc,zydm,zymc,bjdm,bjmc,qfyy,xjnxf,"
					+ "xjnxf_dx,xjndgf,xjndgf_dx,hj,hj_dx,qybfhkfshsj,sqsj,xysh,"
					+ "xxsh,XXSH yesNo from view_hzzy_lstdsqb where " + pk
					+ "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "qfyy", "xjnxf", "xjnxf_dx",
				"xjndgf", "xjndgf_dx", "hj", "hj_dx", "qybfhkfshsj", "sqsj",
				"xysh", "xxsh", "yesNo" };

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
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "hzzy_knssqb");
		return mapping.findForward("success");
	}

	private ActionForward hzzy_gajjsq(ActionMapping mapping, ActionForm form,
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

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "hzzy_gajjsq" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,bjzhpm,"
				+ "lxdh,jtrks,jtrjnsr,xxtxdz,yzbm,jtlxdh,yhzzqk,sqjjyy,sqsj,xysh,xxsh "
				+ "from view_hzzy_gajjsqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='关爱基金' and b.xh=? ";
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
					String bjzhpm = DealString.toGBK(request.getParameter(
							"bjzhpm").toString());
					String yhzzqk = DealString.toGBK(request.getParameter(
							"yhzzqk").toString());
					String sqjjyy = DealString.toGBK(request.getParameter(
							"sqjjyy").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					sql = "select xxsh from hzzy_gajjsqb where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("hzzy_gajjsqb", "xh||nd", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd", "lxdh",
								"bjzhpm", "yhzzqk", "sqjjyy" };

						valueForOut = new String[] { xh, nd, lxdh, bjzhpm,
								yhzzqk, sqjjyy };

						boolean ok = false;
						ok = StandardOperation.insert("hzzy_gajjsqb", colName1,
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
				String bjzhpm = DealString.toGBK(request.getParameter("bjzhpm")
						.toString());
				String yhzzqk = DealString.toGBK(request.getParameter("yhzzqk")
						.toString());
				String sqjjyy = DealString.toGBK(request.getParameter("sqjjyy")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from hzzy_gajjsqb where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("hzzy_gajjsqb", "xh||nd", pkVal, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "lxdh",
							"bjzhpm", "yhzzqk", "sqjjyy" };

					valueForOut = new String[] { xh, nd, lxdh, bjzhpm,
							yhzzqk, sqjjyy };

					boolean ok = false;
					ok = StandardOperation.insert("hzzy_gajjsqb", colName1,
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
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xb,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,bjzhpm,"
				+ "lxdh,jtrks,jtrjnsr,xxtxdz,yzbm,jtlxdh,yhzzqk,sqjjyy,sqsj,xysh,xxsh "
				+ "from view_hzzy_gajjsqb where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "mzmc", "zzmmmc",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "bjzhpm",
				"lxdh", "jtrks", "jtrjnsr", "xxtxdz", "yzbm", "jtlxdh",
				"yhzzqk", "sqjjyy", "sqsj", "xysh", "xxsh" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.mzmc,a.zzmmmc,a.jtrks,a.jtrjnsr,a.xxtxdz,a.yzbm,"
						+ "a.lxdh jtlxdh,a.xymc,a.zymc,a.bjmc,b.sqsj from view_hzzy_jtqkdcb a,"
						+ "(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) b where a.xh=? and xxsh='通过'";
				String[] colName = new String[] { "xh", "xm", "xb", "mzmc",
						"zzmmmc", "jtrks", "jtrjnsr", "xxtxdz", "yzbm",
						"jtlxdh", "xymc", "zymc", "bjmc", "sqsj" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
					request.setAttribute("notSQ", "yes");
				} else {
					colName = new String[] { "xh", "xm", "xb", "mzmc",
							"zzmmmc", "jtrks", "jtrjnsr", "xxtxdz", "yzbm",
							"jtlxdh", "xymc", "zymc", "bjmc", "sqsj" };
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

	private ActionForward hzzy_gajjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String bjzhpm = DealString.toGBK(request.getParameter("bjzhpm")
				.toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr")
				.toString());
		String xxtxdz = DealString.toGBK(request.getParameter("xxtxdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String jtlxdh = DealString.toGBK(request.getParameter("jtlxdh")
				.toString());
		String yhzzqk = DealString.toGBK(request.getParameter("yhzzqk")
				.toString());
		String sqjjyy = DealString.toGBK(request.getParameter("sqjjyy")
				.toString());

		String[] outValue = new String[] { xh, xm, xb, mzmc, bjmc, bjzhpm,
				zzmmmc, lxdh, jtrks, jtrjnsr, xxtxdz, yzbm, jtlxdh, yhzzqk,
				sqjjyy };
		String[] outString = new String[] { "xh", "xm", "xb", "mzmc", "bjmc",
				"bjzhpm", "zzmmmc", "lxdh", "jtrks", "jtrjnsr", "xxtxdz",
				"yzbm", "jtlxdh", "yhzzqk", "sqjjyy" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_gajjsq(ActionMapping mapping,
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
		String tips = "";
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
		realTable = "hzzy_gajjsqb";
		pk = "xh||nd";
		tableName = "view_hzzy_gajjsqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 关爱基金审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 关爱基金";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.bjzhpm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.yhzzqk||'##OneSpile##'||a.sqjjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.bjzhpm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.yhzzqk||'##OneSpile##'||a.sqjjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.bjzhpm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.yhzzqk||'##OneSpile##'||a.sqjjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.bjzhpm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xxtxdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtlxdh||'##OneSpile##'||a.yhzzqk||'##OneSpile##'||a.sqjjyy||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.nj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##bjzhpm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtrjnsr##OneSpile##xxtxdz##OneSpile##yzbm##OneSpile##jtlxdh##OneSpile##yhzzqk##OneSpile##sqjjyy##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##nj";
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
		request.setAttribute("act", "HZZY_JTQKDCB");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_gajjsq_one(ActionMapping mapping,
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
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete hzzy_gajjsqb where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete hzzy_gajjsqb where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_hzzy_gajjsq.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("hzzy_gajjsqb",
						new String[] { "xysh" }, new String[] { yesNo },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("hzzy_gajjsqb",
						new String[] { "xxsh" }, new String[] { yesNo },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) hzzy_gajjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "hzzy_lstdsqb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,"
					+ "bjzhpm,lxdh,jtrks,jtrjnsr,xxtxdz,yzbm,jtlxdh,yhzzqk,sqjjyy,"
					+ "sqsj,xysh,xxsh,XYSH yesNo from view_hzzy_gajjsqb where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,"
					+ "bjzhpm,lxdh,jtrks,jtrjnsr,xxtxdz,yzbm,jtlxdh,yhzzqk,sqjjyy,"
					+ "sqsj,xysh,xxsh,XXSH yesNo from view_hzzy_gajjsqb where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "mzmc",
				"zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"bjzhpm", "lxdh", "jtrks", "jtrjnsr", "xxtxdz", "yzbm",
				"jtlxdh", "yhzzqk", "sqjjyy", "sqsj", "xysh", "xxsh", "yesNo" };

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
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "hzzy_gajjsqb");
		return mapping.findForward("success");
	}

	private ActionForward hzzy_pksknbz(ActionMapping mapping, ActionForm form,
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

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "hzzy_pksknbz" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,csny,mzmc,rxny,xydm,xymc,"
				+ "zydm,zymc,bjdm,bjmc,sfcjzxdk,sfcjqgzx,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,"
				+ "jthk,jtrks,jtzz,yzbm,jtyzsr,rjysr,srly,"
				+ "kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,"
				+ "kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,"
				+ "kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,"
				+ "kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,"
				+ "kc13_mc,kc13_cj,kc14_mc,kc14_cj,kc15_mc,kc15_cj,"
				+ "kc16_mc,kc16_cj,kc17_mc,kc17_cj,kc18_mc,kc18_cj,"
				+ "sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,sjhm,jldh from view_hzzy_pksknbz where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难补助' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String sfcjzxdk = DealString.toGBK(request.getParameter(
							"sfcjzxdk").toString());
					String sfcjqgzx = DealString.toGBK(request.getParameter(
							"sfcjqgzx").toString());
					String jtyzsr = DealString.toGBK(request.getParameter(
							"jtyzsr").toString());
					String rjysr = DealString.toGBK(request.getParameter(
							"rjysr").toString());
					String srly = DealString.toGBK(request.getParameter("srly")
							.toString());
					String kc1_mc = DealString.toGBK(request.getParameter(
							"kc1_mc").toString());
					String kc1_cj = DealString.toGBK(request.getParameter(
							"kc1_cj").toString());
					String kc2_mc = DealString.toGBK(request.getParameter(
							"kc2_mc").toString());
					String kc2_cj = DealString.toGBK(request.getParameter(
							"kc2_cj").toString());
					String kc3_mc = DealString.toGBK(request.getParameter(
							"kc3_mc").toString());
					String kc3_cj = DealString.toGBK(request.getParameter(
							"kc3_cj").toString());
					String kc4_mc = DealString.toGBK(request.getParameter(
							"kc4_mc").toString());
					String kc4_cj = DealString.toGBK(request.getParameter(
							"kc4_cj").toString());
					String kc5_mc = DealString.toGBK(request.getParameter(
							"kc5_mc").toString());
					String kc5_cj = DealString.toGBK(request.getParameter(
							"kc5_cj").toString());
					String kc6_mc = DealString.toGBK(request.getParameter(
							"kc6_mc").toString());
					String kc6_cj = DealString.toGBK(request.getParameter(
							"kc6_cj").toString());
					String kc7_mc = DealString.toGBK(request.getParameter(
							"kc7_mc").toString());
					String kc7_cj = DealString.toGBK(request.getParameter(
							"kc7_cj").toString());
					String kc8_mc = DealString.toGBK(request.getParameter(
							"kc8_mc").toString());
					String kc8_cj = DealString.toGBK(request.getParameter(
							"kc8_cj").toString());
					String kc9_mc = DealString.toGBK(request.getParameter(
							"kc9_mc").toString());
					String kc9_cj = DealString.toGBK(request.getParameter(
							"kc9_cj").toString());
					String kc10_mc = DealString.toGBK(request.getParameter(
							"kc10_mc").toString());
					String kc10_cj = DealString.toGBK(request.getParameter(
							"kc10_cj").toString());
					String kc11_mc = DealString.toGBK(request.getParameter(
							"kc11_mc").toString());
					String kc11_cj = DealString.toGBK(request.getParameter(
							"kc11_cj").toString());
					String kc12_mc = DealString.toGBK(request.getParameter(
							"kc12_mc").toString());
					String kc12_cj = DealString.toGBK(request.getParameter(
							"kc12_cj").toString());
					String kc13_mc = DealString.toGBK(request.getParameter(
							"kc13_mc").toString());
					String kc13_cj = DealString.toGBK(request.getParameter(
							"kc13_cj").toString());
					String kc14_mc = DealString.toGBK(request.getParameter(
							"kc14_mc").toString());
					String kc14_cj = DealString.toGBK(request.getParameter(
							"kc14_cj").toString());
					String kc15_mc = DealString.toGBK(request.getParameter(
							"kc15_mc").toString());
					String kc15_cj = DealString.toGBK(request.getParameter(
							"kc15_cj").toString());
					String kc16_mc = DealString.toGBK(request.getParameter(
							"kc16_mc").toString());
					String kc16_cj = DealString.toGBK(request.getParameter(
							"kc16_cj").toString());
					String kc17_mc = DealString.toGBK(request.getParameter(
							"kc17_mc").toString());
					String kc17_cj = DealString.toGBK(request.getParameter(
							"kc17_cj").toString());
					String kc18_mc = DealString.toGBK(request.getParameter(
							"kc18_mc").toString());
					String kc18_cj = DealString.toGBK(request.getParameter(
							"kc18_cj").toString());
					String sqly = DealString.toGBK(request.getParameter("sqly")
							.toString());
					String sjhm = DealString.toGBK(request.getParameter("sjhm")
							.toString());
					String jldh = DealString.toGBK(request.getParameter("jldh")
							.toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					sql = "select xxsh from hzzy_pksknbz where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("hzzy_pksknbz", "xh||nd", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd",
								"sfcjzxdk", "sfcjqgzx", "jtyzsr", "rjysr",
								"srly", "kc1_mc", "kc1_cj", "kc2_mc", "kc2_cj",
								"kc3_mc", "kc3_cj", "kc4_mc", "kc4_cj",
								"kc5_mc", "kc5_cj", "kc6_mc", "kc6_cj",
								"kc7_mc", "kc7_cj", "kc8_mc", "kc8_cj",
								"kc9_mc", "kc9_cj", "kc10_mc", "kc10_cj",
								"kc11_mc", "kc11_cj", "kc12_mc", "kc12_cj",
								"kc13_mc", "kc13_cj", "kc14_mc", "kc14_cj",
								"kc15_mc", "kc15_cj", "kc16_mc", "kc16_cj",
								"kc17_mc", "kc17_cj", "kc18_mc", "kc18_cj",
								"sqly", "sjhm", "jldh" };

						valueForOut = new String[] { xh, nd, sfcjzxdk,
								sfcjqgzx, jtyzsr, rjysr, srly, kc1_mc, kc1_cj,
								kc2_mc, kc2_cj, kc3_mc, kc3_cj, kc4_mc, kc4_cj,
								kc5_mc, kc5_cj, kc6_mc, kc6_cj, kc7_mc, kc7_cj,
								kc8_mc, kc8_cj, kc9_mc, kc9_cj, kc10_mc,
								kc10_cj, kc11_mc, kc11_cj, kc12_mc, kc12_cj,
								kc13_mc, kc13_cj, kc14_mc, kc14_cj, kc15_mc,
								kc15_cj, kc16_mc, kc16_cj, kc17_mc, kc17_cj,
								kc18_mc, kc18_cj, sqly, sjhm, jldh };

						boolean ok = false;
						ok = StandardOperation.insert("hzzy_pksknbz", colName1,
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
				String sfcjzxdk = DealString.toGBK(request.getParameter(
						"sfcjzxdk").toString());
				String sfcjqgzx = DealString.toGBK(request.getParameter(
						"sfcjqgzx").toString());
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
						.toString());
				String rjysr = DealString.toGBK(request.getParameter("rjysr")
						.toString());
				String srly = DealString.toGBK(request.getParameter("srly")
						.toString());
				String kc1_mc = DealString.toGBK(request.getParameter("kc1_mc")
						.toString());
				String kc1_cj = DealString.toGBK(request.getParameter("kc1_cj")
						.toString());
				String kc2_mc = DealString.toGBK(request.getParameter("kc2_mc")
						.toString());
				String kc2_cj = DealString.toGBK(request.getParameter("kc2_cj")
						.toString());
				String kc3_mc = DealString.toGBK(request.getParameter("kc3_mc")
						.toString());
				String kc3_cj = DealString.toGBK(request.getParameter("kc3_cj")
						.toString());
				String kc4_mc = DealString.toGBK(request.getParameter("kc4_mc")
						.toString());
				String kc4_cj = DealString.toGBK(request.getParameter("kc4_cj")
						.toString());
				String kc5_mc = DealString.toGBK(request.getParameter("kc5_mc")
						.toString());
				String kc5_cj = DealString.toGBK(request.getParameter("kc5_cj")
						.toString());
				String kc6_mc = DealString.toGBK(request.getParameter("kc6_mc")
						.toString());
				String kc6_cj = DealString.toGBK(request.getParameter("kc6_cj")
						.toString());
				String kc7_mc = DealString.toGBK(request.getParameter("kc7_mc")
						.toString());
				String kc7_cj = DealString.toGBK(request.getParameter("kc7_cj")
						.toString());
				String kc8_mc = DealString.toGBK(request.getParameter("kc8_mc")
						.toString());
				String kc8_cj = DealString.toGBK(request.getParameter("kc8_cj")
						.toString());
				String kc9_mc = DealString.toGBK(request.getParameter("kc9_mc")
						.toString());
				String kc9_cj = DealString.toGBK(request.getParameter("kc9_cj")
						.toString());
				String kc10_mc = DealString.toGBK(request.getParameter(
						"kc10_mc").toString());
				String kc10_cj = DealString.toGBK(request.getParameter(
						"kc10_cj").toString());
				String kc11_mc = DealString.toGBK(request.getParameter(
						"kc11_mc").toString());
				String kc11_cj = DealString.toGBK(request.getParameter(
						"kc11_cj").toString());
				String kc12_mc = DealString.toGBK(request.getParameter(
						"kc12_mc").toString());
				String kc12_cj = DealString.toGBK(request.getParameter(
						"kc12_cj").toString());
				String kc13_mc = DealString.toGBK(request.getParameter(
						"kc13_mc").toString());
				String kc13_cj = DealString.toGBK(request.getParameter(
						"kc13_cj").toString());
				String kc14_mc = DealString.toGBK(request.getParameter(
						"kc14_mc").toString());
				String kc14_cj = DealString.toGBK(request.getParameter(
						"kc14_cj").toString());
				String kc15_mc = DealString.toGBK(request.getParameter(
						"kc15_mc").toString());
				String kc15_cj = DealString.toGBK(request.getParameter(
						"kc15_cj").toString());
				String kc16_mc = DealString.toGBK(request.getParameter(
						"kc16_mc").toString());
				String kc16_cj = DealString.toGBK(request.getParameter(
						"kc16_cj").toString());
				String kc17_mc = DealString.toGBK(request.getParameter(
						"kc17_mc").toString());
				String kc17_cj = DealString.toGBK(request.getParameter(
						"kc17_cj").toString());
				String kc18_mc = DealString.toGBK(request.getParameter(
						"kc18_mc").toString());
				String kc18_cj = DealString.toGBK(request.getParameter(
						"kc18_cj").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());
				String sjhm = DealString.toGBK(request.getParameter("sjhm")
						.toString());
				String jldh = DealString.toGBK(request.getParameter("jldh")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from hzzy_pksknbz where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("hzzy_pksknbz", "xh||nd", pkVal, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd",
							"sfcjzxdk", "sfcjqgzx", "jtyzsr", "rjysr",
							"srly", "kc1_mc", "kc1_cj", "kc2_mc", "kc2_cj",
							"kc3_mc", "kc3_cj", "kc4_mc", "kc4_cj",
							"kc5_mc", "kc5_cj", "kc6_mc", "kc6_cj",
							"kc7_mc", "kc7_cj", "kc8_mc", "kc8_cj",
							"kc9_mc", "kc9_cj", "kc10_mc", "kc10_cj",
							"kc11_mc", "kc11_cj", "kc12_mc", "kc12_cj",
							"kc13_mc", "kc13_cj", "kc14_mc", "kc14_cj",
							"kc15_mc", "kc15_cj", "kc16_mc", "kc16_cj",
							"kc17_mc", "kc17_cj", "kc18_mc", "kc18_cj",
							"sqly", "sjhm", "jldh" };

					valueForOut = new String[] { xh, nd, sfcjzxdk,
							sfcjqgzx, jtyzsr, rjysr, srly, kc1_mc, kc1_cj,
							kc2_mc, kc2_cj, kc3_mc, kc3_cj, kc4_mc, kc4_cj,
							kc5_mc, kc5_cj, kc6_mc, kc6_cj, kc7_mc, kc7_cj,
							kc8_mc, kc8_cj, kc9_mc, kc9_cj, kc10_mc,
							kc10_cj, kc11_mc, kc11_cj, kc12_mc, kc12_cj,
							kc13_mc, kc13_cj, kc14_mc, kc14_cj, kc15_mc,
							kc15_cj, kc16_mc, kc16_cj, kc17_mc, kc17_cj,
							kc18_mc, kc18_cj, sqly, sjhm, jldh };

					boolean ok = false;
					ok = StandardOperation.insert("hzzy_pksknbz", colName1,
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
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xb,csny,mzmc,rxny,xydm,xymc,"
				+ "zydm,zymc,bjdm,bjmc,sfcjzxdk,sfcjqgzx,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,"
				+ "jthk,jtrks,jtzz,yzbm,jtyzsr,rjysr,srly,"
				+ "kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,"
				+ "kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,"
				+ "kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,"
				+ "kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,"
				+ "kc13_mc,kc13_cj,kc14_mc,kc14_cj,kc15_mc,kc15_cj,"
				+ "kc16_mc,kc16_cj,kc17_mc,kc17_cj,kc18_mc,kc18_cj,"
				+ "sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,sjhm,jldh from "
				+ "view_hzzy_pksknbz where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "csny", "mzmc",
				"rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"sfcjzxdk", "sfcjqgzx", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "jthk", "jtrks", "jtzz", "yzbm",
				"jtyzsr", "rjysr", "srly", "kc1_mc", "kc1_cj", "kc2_mc",
				"kc2_cj", "kc3_mc", "kc3_cj", "kc4_mc", "kc4_cj", "kc5_mc",
				"kc5_cj", "kc6_mc", "kc6_cj", "kc7_mc", "kc7_cj", "kc8_mc",
				"kc8_cj", "kc9_mc", "kc9_cj", "kc10_mc", "kc10_cj", "kc11_mc",
				"kc11_cj", "kc12_mc", "kc12_cj", "kc13_mc", "kc13_cj",
				"kc14_mc", "kc14_cj", "kc15_mc", "kc15_cj", "kc16_mc",
				"kc16_cj", "kc17_mc", "kc17_cj", "kc18_mc", "kc18_cj", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj", "sjhm", "jldh" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.csrq csny,"
						+ "(select x.rxny from bks_xsjbxx x where "
						+ "a.xh=x.xh) rxny from "
						+ "view_stu_details a,view_hzzy_knssqb b where "
						+ "a.xh=? and a.xh=b.xh and b.nd=? and b.xxsh in ('困难','特殊困难')";
				String[] colName = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "sfzh",
						"csny", "rxny" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh, nd },
						colName);
				if (outVal == null) {
					request.setAttribute("notSQ", "yes");
				} else {
					map.put("nd", nd);
					colName = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "mzmc", "zzmmmc", "sfzh", "csny",
							"rxny" };
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

	private ActionForward hzzy_pksknbzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String csny = DealString.toGBK(request.getParameter("csny").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String rxny = DealString.toGBK(request.getParameter("rxny").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String sfcjzxdk = DealString.toGBK(request.getParameter("sfcjzxdk")
				.toString());
		String sfcjqgzx = DealString.toGBK(request.getParameter("sfcjqgzx")
				.toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm")
				.toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl")
				.toString());
		String jtcy1_gx = DealString.toGBK(request.getParameter("jtcy1_gx")
				.toString());
		String jtcy1_gzdw = DealString.toGBK(request.getParameter("jtcy1_gzdw")
				.toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm")
				.toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl")
				.toString());
		String jtcy2_gx = DealString.toGBK(request.getParameter("jtcy2_gx")
				.toString());
		String jtcy2_gzdw = DealString.toGBK(request.getParameter("jtcy2_gzdw")
				.toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm")
				.toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl")
				.toString());
		String jtcy3_gx = DealString.toGBK(request.getParameter("jtcy3_gx")
				.toString());
		String jtcy3_gzdw = DealString.toGBK(request.getParameter("jtcy3_gzdw")
				.toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm")
				.toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl")
				.toString());
		String jtcy4_gx = DealString.toGBK(request.getParameter("jtcy4_gx")
				.toString());
		String jtcy4_gzdw = DealString.toGBK(request.getParameter("jtcy4_gzdw")
				.toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm")
				.toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl")
				.toString());
		String jtcy5_gx = DealString.toGBK(request.getParameter("jtcy5_gx")
				.toString());
		String jtcy5_gzdw = DealString.toGBK(request.getParameter("jtcy5_gzdw")
				.toString());
		String jthk = DealString.toGBK(request.getParameter("jthk").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
				.toString());
		String rjysr = DealString.toGBK(request.getParameter("rjysr")
				.toString());
		String srly = DealString.toGBK(request.getParameter("srly").toString());
		String kc1_mc = DealString.toGBK(request.getParameter("kc1_mc")
				.toString());
		String kc1_cj = DealString.toGBK(request.getParameter("kc1_cj")
				.toString());
		String kc2_mc = DealString.toGBK(request.getParameter("kc2_mc")
				.toString());
		String kc2_cj = DealString.toGBK(request.getParameter("kc2_cj")
				.toString());
		String kc3_mc = DealString.toGBK(request.getParameter("kc3_mc")
				.toString());
		String kc3_cj = DealString.toGBK(request.getParameter("kc3_cj")
				.toString());
		String kc4_mc = DealString.toGBK(request.getParameter("kc4_mc")
				.toString());
		String kc4_cj = DealString.toGBK(request.getParameter("kc4_cj")
				.toString());
		String kc5_mc = DealString.toGBK(request.getParameter("kc5_mc")
				.toString());
		String kc5_cj = DealString.toGBK(request.getParameter("kc5_cj")
				.toString());
		String kc6_mc = DealString.toGBK(request.getParameter("kc6_mc")
				.toString());
		String kc6_cj = DealString.toGBK(request.getParameter("kc6_cj")
				.toString());
		String kc7_mc = DealString.toGBK(request.getParameter("kc7_mc")
				.toString());
		String kc7_cj = DealString.toGBK(request.getParameter("kc7_cj")
				.toString());
		String kc8_mc = DealString.toGBK(request.getParameter("kc8_mc")
				.toString());
		String kc8_cj = DealString.toGBK(request.getParameter("kc8_cj")
				.toString());
		String kc9_mc = DealString.toGBK(request.getParameter("kc9_mc")
				.toString());
		String kc9_cj = DealString.toGBK(request.getParameter("kc9_cj")
				.toString());
		String kc10_mc = DealString.toGBK(request.getParameter("kc10_mc")
				.toString());
		String kc10_cj = DealString.toGBK(request.getParameter("kc10_cj")
				.toString());
		String kc11_mc = DealString.toGBK(request.getParameter("kc11_mc")
				.toString());
		String kc11_cj = DealString.toGBK(request.getParameter("kc11_cj")
				.toString());
		String kc12_mc = DealString.toGBK(request.getParameter("kc12_mc")
				.toString());
		String kc12_cj = DealString.toGBK(request.getParameter("kc12_cj")
				.toString());
		String kc13_mc = DealString.toGBK(request.getParameter("kc13_mc")
				.toString());
		String kc13_cj = DealString.toGBK(request.getParameter("kc13_cj")
				.toString());
		String kc14_mc = DealString.toGBK(request.getParameter("kc14_mc")
				.toString());
		String kc14_cj = DealString.toGBK(request.getParameter("kc14_cj")
				.toString());
		String kc15_mc = DealString.toGBK(request.getParameter("kc15_mc")
				.toString());
		String kc15_cj = DealString.toGBK(request.getParameter("kc15_cj")
				.toString());
		String kc16_mc = DealString.toGBK(request.getParameter("kc16_mc")
				.toString());
		String kc16_cj = DealString.toGBK(request.getParameter("kc16_cj")
				.toString());
		String kc17_mc = DealString.toGBK(request.getParameter("kc17_mc")
				.toString());
		String kc17_cj = DealString.toGBK(request.getParameter("kc17_cj")
				.toString());
		String kc18_mc = DealString.toGBK(request.getParameter("kc18_mc")
				.toString());
		String kc18_cj = DealString.toGBK(request.getParameter("kc18_cj")
				.toString());
		String sqly = DealString.toGBK(request.getParameter("sqly").toString()
				);
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String sjhm = DealString.toGBK(request.getParameter("sjhm")
				.toString());
		String jldh = DealString.toGBK(request.getParameter("jldh")
				.toString());

		String[] outValue = new String[] { xh, nd, xm, xb, csny, mzmc, rxny,
				xymc, zymc, bjmc, sfcjzxdk, sfcjqgzx, jtcy1_xm, jtcy1_nl,
				jtcy1_gx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
				jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl,
				jtcy4_gx, jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
				jthk, jtrks, jtzz, yzbm, jtyzsr, rjysr, srly, kc1_mc, kc1_cj,
				kc2_mc, kc2_cj, kc3_mc, kc3_cj, kc4_mc, kc4_cj, kc5_mc, kc5_cj,
				kc6_mc, kc6_cj, kc7_mc, kc7_cj, kc8_mc, kc8_cj, kc9_mc, kc9_cj,
				kc10_mc, kc10_cj, kc11_mc, kc11_cj, kc12_mc, kc12_cj, kc13_mc,
				kc13_cj, kc14_mc, kc14_cj, kc15_mc, kc15_cj, kc16_mc, kc16_cj,
				kc17_mc, kc17_cj, kc18_mc, kc18_cj, sqly, xyshyj, xxshyj, sjhm, jldh };
		String[] outString = new String[] { "xh", "nd", "xm", "xb", "csny",
				"mzmc", "rxny", "xymc", "zymc", "bjmc", "sfcjzxdk", "sfcjqgzx",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"jthk", "jtrks", "jtzz", "yzbm", "jtyzsr", "rjysr", "srly",
				"kc1_mc", "kc1_cj", "kc2_mc", "kc2_cj", "kc3_mc", "kc3_cj",
				"kc4_mc", "kc4_cj", "kc5_mc", "kc5_cj", "kc6_mc", "kc6_cj",
				"kc7_mc", "kc7_cj", "kc8_mc", "kc8_cj", "kc9_mc", "kc9_cj",
				"kc10_mc", "kc10_cj", "kc11_mc", "kc11_cj", "kc12_mc",
				"kc12_cj", "kc13_mc", "kc13_cj", "kc14_mc", "kc14_cj",
				"kc15_mc", "kc15_cj", "kc16_mc", "kc16_cj", "kc17_mc",
				"kc17_cj", "kc18_mc", "kc18_cj", "sqly", "xyshyj", "xxshyj", "sjhm", "jldh" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_pksknbz(ActionMapping mapping,
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
		String tips = "";
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
		String nj = request.getParameter("nj");
		String xm = Base.chgNull(request.getParameter("xm"),"",1);
		String xb = Base.chgNull(request.getParameter("xb"),"",1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"),"",1);
		String kndj = Base.chgNull(request.getParameter("kndj"),"",1);
		String xxtxdz = Base.chgNull(request.getParameter("xxtxdz"),"",1);

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
		realTable = "hzzy_pksknbz";
		pk = "xh||nd";
		tableName = "view_hzzy_pksknbz";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xm)) {
			querry.append(" and xm='");
			querry.append(xm);
			querry.append("' ");
		}
		if (!isNull(xb)) {
			querry.append(" and xb='");
			querry.append(xb);
			querry.append("' ");
		}
		if (!isNull(xxtxdz)) {
			querry.append(" and xxtxdz='");
			querry.append(xxtxdz);
			querry.append("' ");
		}
		if (!isNull(sfzh)) {
			querry.append(" and sfzh='");
			querry.append(sfzh);
			querry.append("' ");
		}
		if (!isNull(kndj)) {
			querry.append(" and kndj='");
			querry.append(kndj);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 贫困生困难补助审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 贫困生困难补助";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.rxny||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfcjzxdk||'##OneSpile##'||a.sfcjqgzx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kc13_mc||'##OneSpile##'||a.kc13_cj||'##OneSpile##'||a.kc14_mc||'##OneSpile##'||a.kc14_cj||'##OneSpile##'||a.kc15_mc||'##OneSpile##'||a.kc15_cj||'##OneSpile##'||a.kc16_mc||'##OneSpile##'||a.kc16_cj||'##OneSpile##'||a.kc17_mc||'##OneSpile##'||a.kc17_cj||'##OneSpile##'||a.kc18_mc||'##OneSpile##'||a.kc18_cj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.rxny||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfcjzxdk||'##OneSpile##'||a.sfcjqgzx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kc13_mc||'##OneSpile##'||a.kc13_cj||'##OneSpile##'||a.kc14_mc||'##OneSpile##'||a.kc14_cj||'##OneSpile##'||a.kc15_mc||'##OneSpile##'||a.kc15_cj||'##OneSpile##'||a.kc16_mc||'##OneSpile##'||a.kc16_cj||'##OneSpile##'||a.kc17_mc||'##OneSpile##'||a.kc17_cj||'##OneSpile##'||a.kc18_mc||'##OneSpile##'||a.kc18_cj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.rxny||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfcjzxdk||'##OneSpile##'||a.sfcjqgzx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kc13_mc||'##OneSpile##'||a.kc13_cj||'##OneSpile##'||a.kc14_mc||'##OneSpile##'||a.kc14_cj||'##OneSpile##'||a.kc15_mc||'##OneSpile##'||a.kc15_cj||'##OneSpile##'||a.kc16_mc||'##OneSpile##'||a.kc16_cj||'##OneSpile##'||a.kc17_mc||'##OneSpile##'||a.kc17_cj||'##OneSpile##'||a.kc18_mc||'##OneSpile##'||a.kc18_cj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.csny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.rxny||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfcjzxdk||'##OneSpile##'||a.sfcjqgzx||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.kc1_mc||'##OneSpile##'||a.kc1_cj||'##OneSpile##'||a.kc2_mc||'##OneSpile##'||a.kc2_cj||'##OneSpile##'||a.kc3_mc||'##OneSpile##'||a.kc3_cj||'##OneSpile##'||a.kc4_mc||'##OneSpile##'||a.kc4_cj||'##OneSpile##'||a.kc5_mc||'##OneSpile##'||a.kc5_cj||'##OneSpile##'||a.kc6_mc||'##OneSpile##'||a.kc6_cj||'##OneSpile##'||a.kc7_mc||'##OneSpile##'||a.kc7_cj||'##OneSpile##'||a.kc8_mc||'##OneSpile##'||a.kc8_cj||'##OneSpile##'||a.kc9_mc||'##OneSpile##'||a.kc9_cj||'##OneSpile##'||a.kc10_mc||'##OneSpile##'||a.kc10_cj||'##OneSpile##'||a.kc11_mc||'##OneSpile##'||a.kc11_cj||'##OneSpile##'||a.kc12_mc||'##OneSpile##'||a.kc12_cj||'##OneSpile##'||a.kc13_mc||'##OneSpile##'||a.kc13_cj||'##OneSpile##'||a.kc14_mc||'##OneSpile##'||a.kc14_cj||'##OneSpile##'||a.kc15_mc||'##OneSpile##'||a.kc15_cj||'##OneSpile##'||a.kc16_mc||'##OneSpile##'||a.kc16_cj||'##OneSpile##'||a.kc17_mc||'##OneSpile##'||a.kc17_cj||'##OneSpile##'||a.kc18_mc||'##OneSpile##'||a.kc18_cj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##nj##OneSpile##csny##OneSpile##mzmc##OneSpile##rxny##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##sfcjzxdk##OneSpile##sfcjqgzx##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jthk##OneSpile##jtrks##OneSpile##jtzz##OneSpile##yzbm##OneSpile##jtyzsr##OneSpile##rjysr##OneSpile##srly##OneSpile##kc1_mc##OneSpile##kc1_cj##OneSpile##kc2_mc##OneSpile##kc2_cj##OneSpile##kc3_mc##OneSpile##kc3_cj##OneSpile##kc4_mc##OneSpile##kc4_cj##OneSpile##kc5_mc##OneSpile##kc5_cj##OneSpile##kc6_mc##OneSpile##kc6_cj##OneSpile##kc7_mc##OneSpile##kc7_cj##OneSpile##kc8_mc##OneSpile##kc8_cj##OneSpile##kc9_mc##OneSpile##kc9_cj##OneSpile##kc10_mc##OneSpile##kc10_cj##OneSpile##kc11_mc##OneSpile##kc11_cj##OneSpile##kc12_mc##OneSpile##kc12_cj##OneSpile##kc13_mc##OneSpile##kc13_cj##OneSpile##kc14_mc##OneSpile##kc14_cj##OneSpile##kc15_mc##OneSpile##kc15_cj##OneSpile##kc16_mc##OneSpile##kc16_cj##OneSpile##kc17_mc##OneSpile##kc17_cj##OneSpile##kc18_mc##OneSpile##kc18_cj##OneSpile##sqly##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("sfzh", sfzh);
		map.put("kndj", kndj);
		map.put("xxtxdz", xxtxdz);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
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
		request.setAttribute("act", "HZZY_JTQKDCB");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_pksknbz_one(ActionMapping mapping,
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
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete hzzy_pksknbz where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete hzzy_pksknbz where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_hzzy_pksknbz.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("hzzy_pksknbz",
						new String[] { "xysh", "xyshyj" }, new String[] { yesNo,
								xyshyj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("hzzy_pksknbz",
						new String[] { "xxsh", "xxshyj" }, new String[] { yesNo,
								xxshyj }, "xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) hzzy_pksknbz";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "hzzy_pksknbz";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select " + pk + " pk,xh,nd,xm,xb,csny,mzmc,rxny,xydm,xymc,"
					+ "zydm,zymc,bjdm,bjmc,sfcjzxdk,sfcjqgzx,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,"
					+ "jthk,jtrks,jtzz,yzbm,jtyzsr,rjysr,srly,"
					+ "kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,"
					+ "kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,"
					+ "kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,"
					+ "kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,"
					+ "kc13_mc,kc13_cj,kc14_mc,kc14_cj,kc15_mc,kc15_cj,"
					+ "kc16_mc,kc16_cj,kc17_mc,kc17_cj,kc18_mc,kc18_cj,"
					+ "sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,sjhm,jldh,XYSH yesNo from "
					+ "view_hzzy_pksknbz where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select " + pk + " pk,xh,nd,xm,xb,csny,mzmc,rxny,xydm,xymc,"
					+ "zydm,zymc,bjdm,bjmc,sfcjzxdk,sfcjqgzx,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,"
					+ "jthk,jtrks,jtzz,yzbm,jtyzsr,rjysr,srly,"
					+ "kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,"
					+ "kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,"
					+ "kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,"
					+ "kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,"
					+ "kc13_mc,kc13_cj,kc14_mc,kc14_cj,kc15_mc,kc15_cj,"
					+ "kc16_mc,kc16_cj,kc17_mc,kc17_cj,kc18_mc,kc18_cj,"
					+ "sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,sjhm,jldh,XXSH yesNo from "
					+ "view_hzzy_pksknbz where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "csny", "mzmc",
				"rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"sfcjzxdk", "sfcjqgzx", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "jthk", "jtrks", "jtzz", "yzbm",
				"jtyzsr", "rjysr", "srly", "kc1_mc", "kc1_cj", "kc2_mc",
				"kc2_cj", "kc3_mc", "kc3_cj", "kc4_mc", "kc4_cj", "kc5_mc",
				"kc5_cj", "kc6_mc", "kc6_cj", "kc7_mc", "kc7_cj", "kc8_mc",
				"kc8_cj", "kc9_mc", "kc9_cj", "kc10_mc", "kc10_cj", "kc11_mc",
				"kc11_cj", "kc12_mc", "kc12_cj", "kc13_mc", "kc13_cj",
				"kc14_mc", "kc14_cj", "kc15_mc", "kc15_cj", "kc16_mc",
				"kc16_cj", "kc17_mc", "kc17_cj", "kc18_mc", "kc18_cj", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj", "sjhm", "jldh", "yesNo" };

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
		request.setAttribute("act", "hzzy_pksknbz");
		return mapping.findForward("success");
	}
	
	private ActionForward hzzy_jqzx(ActionMapping mapping, ActionForm form,
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

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		// String xxmc = StandardOperation.getXxmc();
		String[] titNames = null;

		titNames = new String[] { "hzzy_jqzx" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,"
				+ "zzmmmc,jtzz,jtrks,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj "
				+ "from view_hzzy_jqzx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;
		String pkVal = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if (doType != null && doType.equalsIgnoreCase("add")) {//学生填写申请
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from hzzy_jqzx where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
								.equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("hzzy_jqzx", "xh||nd", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "sqly" };

					valueForOut = new String[] { xh, nd, sqly };

					boolean ok = false;
					ok = StandardOperation.insert("hzzy_jqzx", colName1,
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
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = xszzForm.getXh();
			if (doType != null && doType.equalsIgnoreCase("add")) {
				xh = DealString.toGBK(request.getParameter("xh").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from hzzy_jqzx where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
								.equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("hzzy_jqzx", "xh||nd", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "sqly" };

					valueForOut = new String[] { xh, nd, sqly };

					boolean ok = false;
					ok = StandardOperation.insert("hzzy_jqzx", colName1,
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
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd;
		}

		sql = "select xh,nd,xm,xb,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,"
				+ "zzmmmc,jtzz,jtrks,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj "
				+ "from view_hzzy_jqzx where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "sfzh", "rxny",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "zzmmmc",
				"jtzz", "jtrks", "lxdh", "sqly", "sqsj", "xysh", "xyshyj",
				"xxsh", "xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.csrq csny,"
						+ "(select x.rxny from bks_xsjbxx x where "
						+ "a.xh=x.xh) rxny from "
						+ "view_stu_details a,view_hzzy_knssqb b where "
						+ "a.xh=? and a.xh=b.xh and b.nd=? and b.xxsh in ('困难','特殊困难')";
				String[] colName = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "sfzh",
						"csny", "rxny" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh, nd },
						colName);
				if (outVal == null) {
					request.setAttribute("notSQ", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "mzmc", "zzmmmc", "sfzh", "csny",
							"rxny" };
					for (int i = 0; i < colName.length; i++) {
						map.put("nd", nd);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward hzzy_jqzxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String rxny = DealString.toGBK(request.getParameter("rxny").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String sqly = DealString.toGBK(request.getParameter("sqly").toString()
				);
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());

		String[] outValue = new String[] { xh, nd, xm, xb, sfzh, rxny, zymc,
				zzmmmc, jtzz, jtrks, lxdh, sqly, xyshyj, xxshyj };
		String[] outString = new String[] { "xh", "nd", "xm", "xb", "sfzh",
				"rxny", "zymc", "zzmmmc", "jtzz", "jtrks", "lxdh", "sqly",
				"xyshyj", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_jqzx(ActionMapping mapping,
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
		String tips = "";
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
		realTable = "hzzy_jqzx";
		pk = "xh||nd";
		tableName = "view_hzzy_jqzx";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 开发区慈善总会\"金秋助学\"救助学生审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 开发区慈善总会\"金秋助学\"救助学生";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.rxny||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.rxny||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.rxny||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.rxny||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##rxny##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##zzmmmc##OneSpile##jtzz##OneSpile##jtrks##OneSpile##lxdh##OneSpile##sqly##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		request.setAttribute("act", "HZZY_JTQKDCB");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_hzzy_jqzx_one(ActionMapping mapping,
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
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete hzzy_jqzx where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete hzzy_jqzx where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_hzzy_jqzx.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("hzzy_jqzx", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("hzzy_jqzx", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) hzzy_jqzx";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "hzzy_pksknbz";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select " + pk + " pk,xh,nd,xm,xb,sfzh,rxny,xydm,xymc,"
					+ "zydm,zymc,bjdm,bjmc,zzmmmc,jtzz,jtrks,lxdh,sqly,"
					+ "sqsj,xysh,xyshyj,xxsh,xxshyj,XYSH yesNo from "
					+ "view_hzzy_jqzx where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select " + pk + " pk,xh,nd,xm,xb,sfzh,rxny,xydm,xymc,"
					+ "zydm,zymc,bjdm,bjmc,zzmmmc,jtzz,jtrks,lxdh,sqly,"
					+ "sqsj,xysh,xyshyj,xxsh,xxshyj,XXSH yesNo from "
					+ "view_hzzy_jqzx where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "rxny",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "zzmmmc",
				"jtzz", "jtrks", "lxdh", "sqly", "sqsj", "xysh", "xyshyj",
				"xxsh", "xxshyj", "yesNo" };

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
			hs.put(colList[i], rs[i]);
		}
		hs.put("yesNo", yn);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "hzzy_pksknbz");
		return mapping.findForward("success");
	}
}
