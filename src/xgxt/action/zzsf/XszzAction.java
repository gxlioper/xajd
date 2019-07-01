/*
 * 创建日期 2007-11-06 zhoumi
 *
 */
package xgxt.action.zzsf;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.XszzForm;
import xgxt.utils.Fdypd;
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
			if (act.equalsIgnoreCase("zzsf_tkbz")) {// 漳州师范学院-校内特困补助申请
				myActFwd = zzsf_tkbz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_tkbzb")) {// 漳州师范学院-校内特困补助申请表
				myActFwd = zzsf_tkbzb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_tkbz")) {// 漳州师范学院-校内特困补助审核
				myActFwd = auditing_zzsf_tkbz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_tkbz_one")) {// 漳州师范学院-校内特困补助单个审核
				myActFwd = auditing_zzsf_tkbz_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_kns")) {// 漳州师范学院-困难生申请表
				myActFwd = zzsf_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_knsb")) {// 漳州师范学院-困难生申请表表
				myActFwd = zzsf_knsb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_kns")) {// 漳州师范学院-困难生审核
				myActFwd = auditing_zzsf_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_kns_one")) {// 漳州师范学院-困难生单个审核
				myActFwd = auditing_zzsf_kns_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_xsxfjm")) {// 漳州师范学院-学生学费减免审核
				myActFwd = auditing_zzsf_xsxfjm(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_xsxfjm_one")) {// 漳州师范学院-学生学费减免单个审核
				myActFwd = auditing_zzsf_xsxfjm_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_gjlzjxj")) {// 漳州师范学院-国家励志奖学金申请
				myActFwd = zzsf_gjlzjxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_gjlzjxjb")) {// 漳州师范学院-国家励志奖学金申请表
				myActFwd = zzsf_gjlzjxjb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjlzjxj")) {// 漳州师范学院-国家励志奖学金审核
				myActFwd = auditing_zzsf_gjlzjxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjlzjxj_one")) {// 漳州师范学院-国家励志奖学金单个审核
				myActFwd = auditing_zzsf_gjlzjxj_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_gjjxj")) {// 漳州师范学院-国家奖学金申请
				myActFwd = zzsf_gjjxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_gjjxjb")) {// 漳州师范学院-国家奖学金申请表
				myActFwd = zzsf_gjjxjb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjjxj")) {// 漳州师范学院-国家奖学金审核
				myActFwd = auditing_zzsf_gjjxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjjxj_one")) {// 漳州师范学院-国家奖学金单个审核
				myActFwd = auditing_zzsf_gjjxj_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_gjzxj")) {// 漳州师范学院-国家助学金申请
				myActFwd = zzsf_gjzxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_gjzxjb")) {// 漳州师范学院-国家助学金申请表
				myActFwd = zzsf_gjzxjb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjzxj")) {// 漳州师范学院-国家助学金审核
				myActFwd = auditing_zzsf_gjzxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjzxj_one")) {// 漳州师范学院-国家助学金单个审核
				myActFwd = auditing_zzsf_gjzxj_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_lstd")) {// 漳州师范学院-绿色通道申请
				myActFwd = zzsf_lstd(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_lstdb")) {// 漳州师范学院-绿色通道申请表
				myActFwd = zzsf_lstdb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_lstd")) {// 漳州师范学院-绿色通道审核
				myActFwd = auditing_zzsf_lstd(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_lstd_one")) {// 漳州师范学院-绿色通道单个审核
				myActFwd = auditing_zzsf_lstd_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_gjzxdk")) {// 漳州师范学院-国家助学贷款申请
				myActFwd = zzsf_gjzxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_gjzxdkb")) {// 漳州师范学院-国家助学贷款申请表
				myActFwd = zzsf_gjzxdkb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjzxdk")) {// 漳州师范学院-国家助学贷款审核
				myActFwd = auditing_zzsf_gjzxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_gjzxdk_one")) {// 漳州师范学院-国家助学贷款单个审核
				myActFwd = auditing_zzsf_gjzxdk_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("zzsf_sydxyzxdk")) {// 漳州师范学院-生源地信用助学贷款申请
				myActFwd = zzsf_sydxyzxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("zzsf_sydxyzxdkb")) {// 漳州师范学院-生源地信用助学贷款申请表
				myActFwd = zzsf_sydxyzxdkb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_sydxyzxdk")) {// 漳州师范学院-生源地信用助学贷款审核
				myActFwd = auditing_zzsf_sydxyzxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_zzsf_sydxyzxdk_one")) {// 漳州师范学院-生源地信用助学贷款单个审核
				myActFwd = auditing_zzsf_sydxyzxdk_one(mapping, form, request,
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

	private ActionForward zzsf_tkbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
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

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='校内特困补助' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String xslb = DealString.toGBK(request.getParameter("xslb")
							.toString());
					String jthk = DealString.toGBK(request.getParameter("jthk")
							.toString());
					String jtrks = DealString.toGBK(request.getParameter(
							"jtrks").toString());
					String jtyzsr = DealString.toGBK(request.getParameter(
							"jtyzsr").toString());
					String jtzz = DealString.toGBK(request.getParameter("jtzz")
							.toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String jtdh = DealString.toGBK(request.getParameter("jtdh")
							.toString());
					String jtzyjjly = DealString.toGBK(request.getParameter(
							"jtzyjjly").toString());
					String jtcy1_xm = DealString.toGBK(request.getParameter(
							"jtcy1_xm").toString());
					String jtcy1_nl = DealString.toGBK(request.getParameter(
							"jtcy1_nl").toString());
					String jtcy1_cw = DealString.toGBK(request.getParameter(
							"jtcy1_cw").toString());
					String jtcy1_szdw = DealString.toGBK(request.getParameter(
							"jtcy1_szdw").toString());
					String jtcy1_ysr = DealString.toGBK(request.getParameter(
							"jtcy1_ysr").toString());
					String jtcy2_xm = DealString.toGBK(request.getParameter(
							"jtcy2_xm").toString());
					String jtcy2_nl = DealString.toGBK(request.getParameter(
							"jtcy2_nl").toString());
					String jtcy2_cw = DealString.toGBK(request.getParameter(
							"jtcy2_cw").toString());
					String jtcy2_szdw = DealString.toGBK(request.getParameter(
							"jtcy2_szdw").toString());
					String jtcy2_ysr = DealString.toGBK(request.getParameter(
							"jtcy2_ysr").toString());
					String jtcy3_xm = DealString.toGBK(request.getParameter(
							"jtcy3_xm").toString());
					String jtcy3_nl = DealString.toGBK(request.getParameter(
							"jtcy3_nl").toString());
					String jtcy3_cw = DealString.toGBK(request.getParameter(
							"jtcy3_cw").toString());
					String jtcy3_szdw = DealString.toGBK(request.getParameter(
							"jtcy3_szdw").toString());
					String jtcy3_ysr = DealString.toGBK(request.getParameter(
							"jtcy3_ysr").toString());
					String jtcy4_xm = DealString.toGBK(request.getParameter(
							"jtcy4_xm").toString());
					String jtcy4_nl = DealString.toGBK(request.getParameter(
							"jtcy4_nl").toString());
					String jtcy4_cw = DealString.toGBK(request.getParameter(
							"jtcy4_cw").toString());
					String jtcy4_szdw = DealString.toGBK(request.getParameter(
							"jtcy4_szdw").toString());
					String jtcy4_ysr = DealString.toGBK(request.getParameter(
							"jtcy4_ysr").toString());
					String jtcy5_xm = DealString.toGBK(request.getParameter(
							"jtcy5_xm").toString());
					String jtcy5_nl = DealString.toGBK(request.getParameter(
							"jtcy5_nl").toString());
					String jtcy5_cw = DealString.toGBK(request.getParameter(
							"jtcy5_cw").toString());
					String jtcy5_szdw = DealString.toGBK(request.getParameter(
							"jtcy5_szdw").toString());
					String jtcy5_ysr = DealString.toGBK(request.getParameter(
							"jtcy5_ysr").toString());
					String bxqjcqk = DealString.toGBK(request.getParameter(
							"bxqjcqk").toString());
					String bxqszzqk = DealString.toGBK(request.getParameter(
							"bxqszzqk").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}
					
					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "xslb",
							"jthk", "jtrks", "jtyzsr", "jtzz", "yzbm",
							"jtdh", "jtzyjjly", "jtcy1_xm", "jtcy1_nl",
							"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
							"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
							"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
							"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
							"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
							"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
							"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
							"jtcy5_szdw", "jtcy5_ysr", "bxqjcqk",
							"bxqszzqk" };

					valueForOut = new String[] { xh, nd, xslb, jthk, jtrks,
							jtyzsr, jtzz, yzbm, jtdh, jtzyjjly, jtcy1_xm,
							jtcy1_nl, jtcy1_cw, jtcy1_szdw, jtcy1_ysr,
							jtcy2_xm, jtcy2_nl, jtcy2_cw, jtcy2_szdw,
							jtcy2_ysr, jtcy3_xm, jtcy3_nl, jtcy3_cw,
							jtcy3_szdw, jtcy3_ysr, jtcy4_xm, jtcy4_nl,
							jtcy4_cw, jtcy4_szdw, jtcy4_ysr, jtcy5_xm,
							jtcy5_nl, jtcy5_cw, jtcy5_szdw, jtcy5_ysr,
							bxqjcqk, bxqszzqk };

					sql = "select xxsh from zzsf_tkbzsq where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_tkbzsq", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xslb", "jthk", "jtrks",
									"jtyzsr", "jtzz", "yzbm", "jtdh",
									"jtzyjjly", "jtcy1_xm", "jtcy1_nl",
									"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
									"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
									"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
									"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
									"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
									"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
									"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
									"jtcy5_szdw", "jtcy5_ysr", "bxqjcqk",
									"bxqszzqk", "sqsj", "xysh", "xxsh",
									"xyshyj", "xxshyj", "zzdjdm", "xyzzfzryj",
									"xyshryhm" };

							valueForOut = new String[] { xslb, jthk, jtrks,
									jtyzsr, jtzz, yzbm, jtdh, jtzyjjly,
									jtcy1_xm, jtcy1_nl, jtcy1_cw, jtcy1_szdw,
									jtcy1_ysr, jtcy2_xm, jtcy2_nl, jtcy2_cw,
									jtcy2_szdw, jtcy2_ysr, jtcy3_xm, jtcy3_nl,
									jtcy3_cw, jtcy3_szdw, jtcy3_ysr, jtcy4_xm,
									jtcy4_nl, jtcy4_cw, jtcy4_szdw, jtcy4_ysr,
									jtcy5_xm, jtcy5_nl, jtcy5_cw, jtcy5_szdw,
									jtcy5_ysr, bxqjcqk, bxqszzqk, rightNow,
									"未审核", "未审核", "", "", "", "", "" };
							
							boolean ok = false;
							ok = StandardOperation.update("zzsf_tkbzsq", colName1,
									valueForOut, "xh||nd", pkVal, request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String xslb = DealString.toGBK(request.getParameter("xslb")
						.toString());
				String jthk = DealString.toGBK(request.getParameter("jthk")
						.toString());
				String jtrks = DealString.toGBK(request.getParameter("jtrks")
						.toString());
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
						.toString());
				String jtzz = DealString.toGBK(request.getParameter("jtzz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String jtdh = DealString.toGBK(request.getParameter("jtdh")
						.toString());
				String jtzyjjly = DealString.toGBK(request.getParameter(
						"jtzyjjly").toString());
				String jtcy1_xm = DealString.toGBK(request.getParameter(
						"jtcy1_xm").toString());
				String jtcy1_nl = DealString.toGBK(request.getParameter(
						"jtcy1_nl").toString());
				String jtcy1_cw = DealString.toGBK(request.getParameter(
						"jtcy1_cw").toString());
				String jtcy1_szdw = DealString.toGBK(request.getParameter(
						"jtcy1_szdw").toString());
				String jtcy1_ysr = DealString.toGBK(request.getParameter(
						"jtcy1_ysr").toString());
				String jtcy2_xm = DealString.toGBK(request.getParameter(
						"jtcy2_xm").toString());
				String jtcy2_nl = DealString.toGBK(request.getParameter(
						"jtcy2_nl").toString());
				String jtcy2_cw = DealString.toGBK(request.getParameter(
						"jtcy2_cw").toString());
				String jtcy2_szdw = DealString.toGBK(request.getParameter(
						"jtcy2_szdw").toString());
				String jtcy2_ysr = DealString.toGBK(request.getParameter(
						"jtcy2_ysr").toString());
				String jtcy3_xm = DealString.toGBK(request.getParameter(
						"jtcy3_xm").toString());
				String jtcy3_nl = DealString.toGBK(request.getParameter(
						"jtcy3_nl").toString());
				String jtcy3_cw = DealString.toGBK(request.getParameter(
						"jtcy3_cw").toString());
				String jtcy3_szdw = DealString.toGBK(request.getParameter(
						"jtcy3_szdw").toString());
				String jtcy3_ysr = DealString.toGBK(request.getParameter(
						"jtcy3_ysr").toString());
				String jtcy4_xm = DealString.toGBK(request.getParameter(
						"jtcy4_xm").toString());
				String jtcy4_nl = DealString.toGBK(request.getParameter(
						"jtcy4_nl").toString());
				String jtcy4_cw = DealString.toGBK(request.getParameter(
						"jtcy4_cw").toString());
				String jtcy4_szdw = DealString.toGBK(request.getParameter(
						"jtcy4_szdw").toString());
				String jtcy4_ysr = DealString.toGBK(request.getParameter(
						"jtcy4_ysr").toString());
				String jtcy5_xm = DealString.toGBK(request.getParameter(
						"jtcy5_xm").toString());
				String jtcy5_nl = DealString.toGBK(request.getParameter(
						"jtcy5_nl").toString());
				String jtcy5_cw = DealString.toGBK(request.getParameter(
						"jtcy5_cw").toString());
				String jtcy5_szdw = DealString.toGBK(request.getParameter(
						"jtcy5_szdw").toString());
				String jtcy5_ysr = DealString.toGBK(request.getParameter(
						"jtcy5_ysr").toString());
				String bxqjcqk = DealString.toGBK(request.getParameter(
						"bxqjcqk").toString());
				String bxqszzqk = DealString.toGBK(request.getParameter(
						"bxqszzqk").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "xslb",
						"jthk", "jtrks", "jtyzsr", "jtzz", "yzbm",
						"jtdh", "jtzyjjly", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
						"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
						"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
						"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
						"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
						"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
						"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
						"jtcy5_szdw", "jtcy5_ysr", "bxqjcqk",
						"bxqszzqk" };

				valueForOut = new String[] { xh, nd, xslb, jthk, jtrks,
						jtyzsr, jtzz, yzbm, jtdh, jtzyjjly, jtcy1_xm,
						jtcy1_nl, jtcy1_cw, jtcy1_szdw, jtcy1_ysr,
						jtcy2_xm, jtcy2_nl, jtcy2_cw, jtcy2_szdw,
						jtcy2_ysr, jtcy3_xm, jtcy3_nl, jtcy3_cw,
						jtcy3_szdw, jtcy3_ysr, jtcy4_xm, jtcy4_nl,
						jtcy4_cw, jtcy4_szdw, jtcy4_ysr, jtcy5_xm,
						jtcy5_nl, jtcy5_cw, jtcy5_szdw, jtcy5_ysr,
						bxqjcqk, bxqszzqk };

				sql = "select xxsh from zzsf_tkbzsq where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_tkbzsq", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xslb", "jthk", "jtrks",
								"jtyzsr", "jtzz", "yzbm", "jtdh", "jtzyjjly",
								"jtcy1_xm", "jtcy1_nl", "jtcy1_cw",
								"jtcy1_szdw", "jtcy1_ysr", "jtcy2_xm",
								"jtcy2_nl", "jtcy2_cw", "jtcy2_szdw",
								"jtcy2_ysr", "jtcy3_xm", "jtcy3_nl",
								"jtcy3_cw", "jtcy3_szdw", "jtcy3_ysr",
								"jtcy4_xm", "jtcy4_nl", "jtcy4_cw",
								"jtcy4_szdw", "jtcy4_ysr", "jtcy5_xm",
								"jtcy5_nl", "jtcy5_cw", "jtcy5_szdw",
								"jtcy5_ysr", "bxqjcqk", "bxqszzqk", "sqsj",
								"xysh", "xxsh", "xyshyj", "xxshyj", "zzdjdm",
								"xyzzfzryj", "xyshryhm" };

						valueForOut = new String[] { xslb, jthk, jtrks, jtyzsr,
								jtzz, yzbm, jtdh, jtzyjjly, jtcy1_xm, jtcy1_nl,
								jtcy1_cw, jtcy1_szdw, jtcy1_ysr, jtcy2_xm,
								jtcy2_nl, jtcy2_cw, jtcy2_szdw, jtcy2_ysr,
								jtcy3_xm, jtcy3_nl, jtcy3_cw, jtcy3_szdw,
								jtcy3_ysr, jtcy4_xm, jtcy4_nl, jtcy4_cw,
								jtcy4_szdw, jtcy4_ysr, jtcy5_xm, jtcy5_nl,
								jtcy5_cw, jtcy5_szdw, jtcy5_ysr, bxqjcqk,
								bxqszzqk, rightNow, "未审核", "未审核", "", "", "",
								"", "" };
						
						boolean ok = false;
						ok = StandardOperation.update("zzsf_tkbzsq", colName1,
								valueForOut, "xh||nd", pkVal, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
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

		sql = "select xh,nd,xm,xb,mzmc,csny,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,xslb,"
				+ "zzdjdm,zzdjmc,zzdjje,jthk,jtrks,jtyzsr,jtzz,yzbm,jtdh,jtzyjjly,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,"
				+ "bxqjcqk,bxqszzqk,sqsj,xysh,xxsh,xyshyj,xxshyj,"
				+ "xyshryhm,xyshrxm,xyzzfzryj from view_zzsf_tkbzsq "
				+ "where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "mzmc", "csny",
				"zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"xslb", "zzdjdm", "zzdjmc", "zzdjje", "jthk", "jtrks",
				"jtyzsr", "jtzz", "yzbm", "jtdh", "jtzyjjly", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_cw", "jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw", "jtcy3_ysr", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_cw", "jtcy5_szdw", "jtcy5_ysr", "bxqjcqk",
				"bxqszzqk", "sqsj", "xysh", "xxsh", "xyshyj", "xxshyj",
				"xyshryhm", "xyshrxm", "xyzzfzryj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,b.csrq csny,"
						+ "b.mzmc,b.zzmmmc,"
						+ "(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) sqsj,"
						+ "(select dqnd nd from xtszb) nd from view_xsjbxx a,view_stu_details b "
						+ "where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xymc", "zymc",
						"bjmc", "xb", "csny", "mzmc", "zzmmmc", "sqsj", "nd" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xymc", "zymc",
							"bjmc", "xb", "csny", "mzmc", "zzmmmc", "sqsj",
							"nd" };
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
			map.put("sqsj", rightNow);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}
		if (null != xh && dao.isKns(xh)) {
			request.setAttribute("isKNS", "is");
		} else {
			request.setAttribute("isKNS", "no");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward zzsf_tkbzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String csny = DealString.toGBK(request.getParameter("csny").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String xslb = DealString.toGBK(request.getParameter("xslb").toString());
		String jthk = DealString.toGBK(request.getParameter("jthk").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
				.toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String jtdh = DealString.toGBK(request.getParameter("jtdh").toString());
		String jtzyjjly = DealString.toGBK(request.getParameter("jtzyjjly")
				.toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm")
				.toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl")
				.toString());
		String jtcy1_cw = DealString.toGBK(request.getParameter("jtcy1_cw")
				.toString());
		String jtcy1_szdw = DealString.toGBK(request.getParameter("jtcy1_szdw")
				.toString());
		String jtcy1_ysr = DealString.toGBK(request.getParameter("jtcy1_ysr")
				.toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm")
				.toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl")
				.toString());
		String jtcy2_cw = DealString.toGBK(request.getParameter("jtcy2_cw")
				.toString());
		String jtcy2_szdw = DealString.toGBK(request.getParameter("jtcy2_szdw")
				.toString());
		String jtcy2_ysr = DealString.toGBK(request.getParameter("jtcy2_ysr")
				.toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm")
				.toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl")
				.toString());
		String jtcy3_cw = DealString.toGBK(request.getParameter("jtcy3_cw")
				.toString());
		String jtcy3_szdw = DealString.toGBK(request.getParameter("jtcy3_szdw")
				.toString());
		String jtcy3_ysr = DealString.toGBK(request.getParameter("jtcy3_ysr")
				.toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm")
				.toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl")
				.toString());
		String jtcy4_cw = DealString.toGBK(request.getParameter("jtcy4_cw")
				.toString());
		String jtcy4_szdw = DealString.toGBK(request.getParameter("jtcy4_szdw")
				.toString());
		String jtcy4_ysr = DealString.toGBK(request.getParameter("jtcy4_ysr")
				.toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm")
				.toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl")
				.toString());
		String jtcy5_cw = DealString.toGBK(request.getParameter("jtcy5_cw")
				.toString());
		String jtcy5_szdw = DealString.toGBK(request.getParameter("jtcy5_szdw")
				.toString());
		String jtcy5_ysr = DealString.toGBK(request.getParameter("jtcy5_ysr")
				.toString());
		String bxqjcqk = DealString.toGBK(request.getParameter("bxqjcqk")
				.toString());
		String bxqszzqk = DealString.toGBK(request.getParameter("bxqszzqk")
				.toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());

		String[] outValue = new String[] { xh, nd, xm, xb, mzmc, csny, zzmmmc,
				xymc, zymc, bjmc, xslb, jthk, jtrks, jtyzsr, jtzz, yzbm, jtdh,
				jtzyjjly, jtcy1_xm, jtcy1_nl, jtcy1_cw, jtcy1_szdw, jtcy1_ysr,
				jtcy2_xm, jtcy2_nl, jtcy2_cw, jtcy2_szdw, jtcy2_ysr, jtcy3_xm,
				jtcy3_nl, jtcy3_cw, jtcy3_szdw, jtcy3_ysr, jtcy4_xm, jtcy4_nl,
				jtcy4_cw, jtcy4_szdw, jtcy4_ysr, jtcy5_xm, jtcy5_nl, jtcy5_cw,
				jtcy5_szdw, jtcy5_ysr, bxqjcqk, bxqszzqk, xyshyj, xxshyj };
		String[] outString = new String[] { "xh", "nd", "xm", "xb", "mzmc",
				"csny", "zzmmmc", "xymc", "zymc", "bjmc", "xslb", "jthk",
				"jtrks", "jtyzsr", "jtzz", "yzbm", "jtdh", "jtzyjjly",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_cw", "jtcy2_szdw", "jtcy2_ysr",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_cw", "jtcy3_szdw", "jtcy3_ysr",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_cw", "jtcy5_szdw", "jtcy5_ysr",
				"bxqjcqk", "bxqszzqk", "xyshyj", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_tkbz(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_tkbzsq";
		pk = "xh||nd";
		tableName = "view_zzsf_tkbzsq";
		
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 特困补助审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 特困补助";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshrxm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.jtzyjjly||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.nj||'##OneSpile##'||a.bxqjcqk||'##OneSpile##'||a.bxqszzqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.jtzyjjly||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.nj||'##OneSpile##'||a.bxqjcqk||'##OneSpile##'||a.bxqszzqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
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
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshrxm", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.jtzyjjly||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.nj||'##OneSpile##'||a.bxqjcqk||'##OneSpile##'||a.bxqszzqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.csny||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.jtzyjjly||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj||'##OneSpile##'||a.nj||'##OneSpile##'||a.bxqjcqk||'##OneSpile##'||a.bxqszzqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##mzmc##OneSpile##csny##OneSpile##zzmmmc##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xslb##OneSpile##jthk##OneSpile##jtrks##OneSpile##jtyzsr##OneSpile##jtzz##OneSpile##yzbm##OneSpile##jtdh##OneSpile##jtzyjjly##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_cw##OneSpile##jtcy1_szdw##OneSpile##jtcy1_ysr##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_cw##OneSpile##jtcy2_szdw##OneSpile##jtcy2_ysr##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_cw##OneSpile##jtcy3_szdw##OneSpile##jtcy3_ysr##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_cw##OneSpile##jtcy4_szdw##OneSpile##jtcy4_ysr##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_cw##OneSpile##jtcy5_szdw##OneSpile##jtcy5_ysr##OneSpile##xyshryhm##OneSpile##xyshrxm##OneSpile##xyzzfzryj##OneSpile##nj##OneSpile##bxqjcqk##OneSpile##bxqszzqk##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##xyshyj##OneSpile##xxshyj";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_tkbz_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshryhm = session.getAttribute("userName").toString();

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

		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_tkbzsq where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_tkbzsq where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_tkbz.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_tkbzsq set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_tkbzsq set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_tkbz.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_tkbzsq", new String[] {
						"xysh", "xyshyj", "zzdjdm", "xyshryhm", "xyzzfzryj" },
						new String[] { yesNo, xyshyj, zzdjdm, xyshryhm,
								xyzzfzryj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_tkbzsq", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_tkbzsq";
				Base.log(request, logMsg, sUName);
			}
		}

		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_tkbzsq";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,mzmc,csny,zzmmmc,xydm,xymc,zydm,zymc,bjdm,"
					+ "bjmc,xslb,zzdjdm,zzdjmc,zzdjje,jthk,jtrks,jtyzsr,jtzz,yzbm,jtdh,jtzyjjly,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,"
					+ "bxqjcqk,bxqszzqk,sqsj,xysh,xxsh,xyshyj,xxshyj,"
					+ "xyshryhm,xyshrxm,xyzzfzryj,XYSH yesNo from "
					+ "view_zzsf_tkbzsq where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,mzmc,csny,zzmmmc,xydm,xymc,zydm,zymc,bjdm,"
					+ "bjmc,xslb,zzdjdm,zzdjmc,zzdjje,jthk,jtrks,jtyzsr,jtzz,yzbm,jtdh,jtzyjjly,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,"
					+ "jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,"
					+ "jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,"
					+ "jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,"
					+ "bxqjcqk,bxqszzqk,sqsj,xysh,xxsh,xyshyj,xxshyj,"
					+ "xyshryhm,xyshrxm,xyzzfzryj,XXSH yesNo from "
					+ "view_zzsf_tkbzsq where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "mzmc", "csny",
				"zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"xslb", "zzdjdm", "zzdjmc", "zzdjje", "jthk", "jtrks",
				"jtyzsr", "jtzz", "yzbm", "jtdh", "jtzyjjly", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_cw", "jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw", "jtcy3_ysr", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_cw", "jtcy5_szdw", "jtcy5_ysr", "bxqjcqk",
				"bxqszzqk", "sqsj", "xysh", "xxsh", "xyshyj", "xxshyj",
				"xyshryhm", "xyshrxm", "xyzzfzryj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_tkbzsq");
		return mapping.findForward("success");
	}

	private ActionForward zzsf_kns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

//		 String userNameReal;

		String sUName;

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
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
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
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生认定' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr")
							.toString());
					String xslb = DealString.toGBK(request.getParameter(
							"xslb").toString());
					String lxdh = DealString.toGBK(request.getParameter(
							"lxdh").toString());
					String sqly = DealString.toGBK(request.getParameter("sqly")
							.toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}
					
					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd",
							"jtrjnsr", "xslb", "lxdh", "sqly" };

					valueForOut = new String[] { xh, nd, jtrjnsr, xslb,
							lxdh, sqly };

					sql = "select xxsh from zzsf_knsxx where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_knsxx", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if (temp[0].equalsIgnoreCase("通过")) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "nd", "jtrjnsr",
									"xslb", "lxdh", "sqly", "sqsj", "fdysh",
									"fdyshyj", "xysh", "xyshyj", "xxsh",
									"xxshyj" };

							valueForOut = new String[] { xh, nd, jtrjnsr, xslb,
									lxdh, sqly, rightNow, "未审核", "", "未审核", "",
									"未审核", "" };
	
							boolean ok = false;
							ok = StandardOperation.update("zzsf_knsxx", colName1,
									valueForOut, "xh||nd", pkVal, request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr")
						.toString());
				String xslb = DealString.toGBK(request.getParameter(
						"xslb").toString());
				String lxdh = DealString.toGBK(request.getParameter(
						"lxdh").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd",
						"jtrjnsr", "xslb", "lxdh", "sqly" };

				valueForOut = new String[] { xh, nd, jtrjnsr, xslb,
						lxdh, sqly };

				sql = "select xxsh from zzsf_knsxx where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_knsxx", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if (temp[0].equalsIgnoreCase("通过")) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "nd", "jtrjnsr",
								"xslb", "lxdh", "sqly", "sqsj", "fdysh",
								"fdyshyj", "xysh", "xyshyj", "xxsh",
								"xxshyj" };

						valueForOut = new String[] { xh, nd, jtrjnsr, xslb,
								lxdh, sqly, rightNow, "未审核", "", "未审核", "",
								"未审核", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_knsxx", colName1,
								valueForOut, "xh||nd", pkVal, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
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

		sql = "select xh,nd,xm,xb,csrq,mzmc,sfzh,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,"
				+ "bjmc,jtrjnsr,xslb,lxdh,sqly,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj,sqsj "
				+ "from view_zzsf_knsxx where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "csrq", "mzmc",
				"sfzh", "zzmmmc", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "jtrjnsr", "xslb", "lxdh", "sqly", "fdysh", "fdyshyj",
				"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,b.csrq,b.mzmc,a.sfzh,b.zzmmmc,a.nj,"
						+ "a.xymc,a.zymc,a.bjmc from view_xsjbxx a,view_stu_details b where b.xh=a.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "sfzh", "zzmmmc", "nj", "zzmmmc", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "sfzh", "zzmmmc", "nj", "zzmmmc", "xymc", "zymc", "bjmc" };
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
			map.put("sqsj", rightNow);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}

		
		List xszzXslbList = dao.getXszzXslbList();
		request.setAttribute("xszzXslbList", xszzXslbList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward zzsf_knsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String csrq = DealString.toGBK(request.getParameter("csrq").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh")
				.toString());
		String jtrjnsr = DealString.toGBK(request.getParameter("jtrjnsr")
				.toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xslb = DealString.toGBK(request.getParameter("xslb")
				.toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String sqly = DealString.toGBK(request.getParameter("sqly").toString()
				);
		String fdysh = DealString.toGBK(request.getParameter("fdysh").toString());
		String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj").toString());
		String xysh = DealString.toGBK(request.getParameter("xysh")
				.toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxsh = DealString.toGBK(request.getParameter("xxsh")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String nd = DealString.toGBK(request.getParameter("nd")
				.toString());
		if(null == fdysh || "未审核".equalsIgnoreCase(fdysh)){
			fdysh = "";
		}
		if(null == xysh || "未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if(null == xxsh || "未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}

		String[] outValue = new String[] { xh, nd, xm, xb, csrq, mzmc, sfzh,
				zzmmmc, nj, xymc, zymc, bjmc, jtrjnsr, xslb, lxdh, sqly, fdysh,
				fdyshyj, xysh, xyshyj, xxsh, xxshyj };
		String[] outString = new String[] { "xh", "nd", "xm", "xb", "csrq",
				"mzmc", "sfzh", "zzmmmc", "nj", "xymc", "zymc", "bjmc",
				"jtrjnsr", "xslb", "lxdh", "sqly", "fdysh", "fdyshyj", "xysh",
				"xyshyj", "xxsh", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		if(fdysh.equalsIgnoreCase(xysh)){
			request.setAttribute("fdy_xy", "same");
		} else {
			request.setAttribute("fdy_xy", "notSame");
		} 
		if(xysh.equalsIgnoreCase(xxsh)){
			request.setAttribute("xy_xx", "same");
		} else {
			request.setAttribute("xy_xx", "notSame");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_kns(ActionMapping mapping,
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
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String xh = DealString.toGBK(checkForm.getXh());
		String xm = DealString.toGBK(checkForm.getXm());
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))&&(bj==null||bj.trim().equalsIgnoreCase(""))) {
			xy = userDep;
		}
		
		realTable = "zzsf_knsxx";
		pk = "xh||nd";
		tableName = "view_zzsf_knsxx";
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 困难生审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 困难生";
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "bjmc",
					"sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键, a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xslb||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xslb||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "bjmc",
					"sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键, a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('一般困难','困难','特殊困难') order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xslb||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('一般困难','困难','特殊困难') order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				if (userBj.size() == 0) {
					sql = "select (case when nvl(a.xysh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xh pk2,a.* from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' order by fdysh,xysh desc) a";//and fdysh in ('一般困难','困难','特殊困难') 
					sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xslb||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh,xysh desc";//and fdysh in ('一般困难','困难','特殊困难') 
				} else {
					sql = "select (case when nvl(a.fdysh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by fdysh desc) a";
					sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtrjnsr||'##OneSpile##'||a.xslb||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sqly||'##OneSpile##'||a.fdysh||'##OneSpile##'||a.fdyshyj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc";
				}
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##mzmc##OneSpile##sfzh##OneSpile##zzmmmc##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##jtrjnsr##OneSpile##xslb##OneSpile##lxdh##OneSpile##sqly##OneSpile##fdysh##OneSpile##fdyshyj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##sqsj";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
        if(Fdypd.isFdy(userName)){
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList (userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList (userName));// 发送班级列表		   
		}else{
			request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
			request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		}
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数	
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_kns_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		ArrayList<String> userBj = new ArrayList<String>();
		String userName = session.getAttribute("userName").toString();
		userBj = dao.getUserBj(userName);

		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String fdyshyj = DealString.toGBK(request.getParameter("fdyshyj"));
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		Date date = new Date();
		System.out.println("1:"+date.toString());
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if (userBj.size() == 0) {
						sqlT[i] = "delete zzsf_knsxx where xh||nd='"+pkT+"' and xxsh not in ('一般困难','困难','特殊困难')";
					} else {
						sqlT[i] = "delete zzsf_knsxx where xh||nd='"+pkT+"' and xysh not in ('一般困难','困难','特殊困难')";
					}
				} else {
					sqlT[i] = "delete zzsf_knsxx where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_kns.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String jg = request.getParameter("jg");
			if ("ybkn".equalsIgnoreCase(jg)){
				jg = "一般困难";
			} else if ("kn".equalsIgnoreCase(jg)){
				jg = "困难";
			} else if ("tskn".equalsIgnoreCase(jg)){
				jg = "特殊困难";
			} else if ("bkn".equalsIgnoreCase(jg)){
				jg = "不困难";
			} else {
				jg = "未审核";
			}
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if (userBj.size() == 0) {
						sqlT[i] = "update zzsf_knsxx set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh not in ('一般困难','困难','特殊困难')";
					} else {
						sqlT[i] = "update zzsf_knsxx set fdysh='"+jg+"' where xh||nd='"+pkT+"' and xysh not in ('一般困难','困难','特殊困难')";
					}
				} else {
					sqlT[i] = "update zzsf_knsxx set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_kns.do?go=go", false);
			return newFwd;
		}
		Date date5 = new Date();
		System.out.println("2:"+date5.toString());
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if (userBj.size() == 0) {
					StandardOperation.update("zzsf_knsxx", new String[] {
							"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
							"xh||nd", pkVal, request);
				} else {
					if ("未审核".equalsIgnoreCase(yesNo) || "不困难".equalsIgnoreCase(yesNo)){
						StandardOperation.update("zzsf_knsxx",
								new String[] { "fdysh", "fdyshyj", "xysh" },
								new String[] { yesNo, fdyshyj, "未审核" }, "xh||nd",
								pkVal, request);
					} else {
						StandardOperation.update("zzsf_knsxx",
								new String[] { "fdysh", "fdyshyj" },
								new String[] { yesNo, fdyshyj }, "xh||nd",
								pkVal, request);
					}
				}
			} else {
				StandardOperation.update("zzsf_knsxx", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
		}
		Date date6 = new Date();
		System.out.println("3:"+date6.toString());
		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_knsxx";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if (userBj.size() == 0) {
				sql = "select "
						+ pk
						+ " pk,xh,nd,xm,xb,csrq,mzmc,sfzh,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,"
						+ "bjmc,jtrjnsr,xslb,lxdh,sqly,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj,sqsj,XYSH yesNo from "
						+ "view_zzsf_knsxx where " + pk + "='" + pkVal + "'";
				request.setAttribute("userType1", "xy");
			} else {
				sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,sfzh,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,"
					+ "bjmc,jtrjnsr,xslb,lxdh,sqly,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj,sqsj,FDYSH yesNo from "
					+ "view_zzsf_knsxx where " + pk + "='" + pkVal + "'";
				request.setAttribute("userType1", "fdy");
			}
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,sfzh,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,"
					+ "bjmc,jtrjnsr,xslb,lxdh,sqly,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj,sqsj,XXSH yesNo from "
					+ "view_zzsf_knsxx where " + pk + "='" + pkVal + "'";
			request.setAttribute("userType1", "xx");
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "csrq", "mzmc",
				"sfzh", "zzmmmc", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "jtrjnsr", "xslb", "lxdh", "sqly", "fdysh", "fdyshyj",
				"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		Date date3 = new Date();
		System.out.println("4:"+date3.toString());
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_knsxx");
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_xsxfjm(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_xsxfjmsq";
		pk = "xh||nd";
		tableName = "view_zzsf_xsxfjmsq";
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 学生学费减免审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 学生学费减免";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqzl", "sqje", "tyjmje",
					"xyshrxm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.syd||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.xslbmc||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jzxm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.ssdz||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.jtjjqk||'##OneSpile##'||a.yxknbzqk||'##OneSpile##'||a.qggw||'##OneSpile##'||a.psbxqk||'##OneSpile##'||a.sqzl||'##OneSpile##'||a.sqje||'##OneSpile##'||a.gehjqk||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.tyjmje||'##OneSpile##'||a.tyjmjedx||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.syd||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.xslbmc||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jzxm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.ssdz||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.jtjjqk||'##OneSpile##'||a.yxknbzqk||'##OneSpile##'||a.qggw||'##OneSpile##'||a.psbxqk||'##OneSpile##'||a.sqzl||'##OneSpile##'||a.sqje||'##OneSpile##'||a.gehjqk||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.tyjmje||'##OneSpile##'||a.tyjmjedx||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xymc", "zymc", "bjmc", "sqzl", "sqje", "tyjmje",
					"xyshrxm", "sqsj", ""  };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.syd||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.xslbmc||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jzxm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.ssdz||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.jtjjqk||'##OneSpile##'||a.yxknbzqk||'##OneSpile##'||a.qggw||'##OneSpile##'||a.psbxqk||'##OneSpile##'||a.sqzl||'##OneSpile##'||a.sqje||'##OneSpile##'||a.gehjqk||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.tyjmje||'##OneSpile##'||a.tyjmjedx||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj col from "
					+ tableName + " a" + querry.toString() + " and xysh='通过' order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.syd||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.xslbmc||'##OneSpile##'||a.xslb||'##OneSpile##'||a.jzxm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.ssdz||'##OneSpile##'||a.ssdh||'##OneSpile##'||a.jtjjqk||'##OneSpile##'||a.yxknbzqk||'##OneSpile##'||a.qggw||'##OneSpile##'||a.psbxqk||'##OneSpile##'||a.sqzl||'##OneSpile##'||a.sqje||'##OneSpile##'||a.gehjqk||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.tyjmje||'##OneSpile##'||a.tyjmjedx||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xyzzfzryj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##csrq##OneSpile##syd##OneSpile##zzmm##OneSpile##xslbmc##OneSpile##xslb##OneSpile##jzxm##OneSpile##jtdz##OneSpile##yzbm##OneSpile##jtdh##OneSpile##ssdz##OneSpile##ssdh##OneSpile##jtjjqk##OneSpile##yxknbzqk##OneSpile##qggw##OneSpile##psbxqk##OneSpile##sqzl##OneSpile##sqje##OneSpile##gehjqk##OneSpile##xxcj##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##tyjmje##OneSpile##tyjmjedx##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##bz##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyshrxm##OneSpile##xyzzfzryj";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("act", "zzsf_xsxfjmsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_xsxfjm_one(ActionMapping mapping,
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
		String userName = session.getAttribute("userName").toString();
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));

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
					sqlT[i] = "delete zzsf_xsxfjmsq where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_xsxfjmsq where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_xsxfjm.do?go=go", false);
			return newFwd;
		}
		
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_xsxfjmsq set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_xsxfjmsq set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_xsxfjm.do?go=go", false);
			return newFwd;
		}

		String tyjmje = DealString.toGBK(request.getParameter("tyjmje"));
		if((tyjmje == null) || "".equalsIgnoreCase(tyjmje)){
			tyjmje = "0";
		}
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		String[] tyjmjeTemp = dao.getProVal(sqlT, new String[] {tyjmje}, new int[] {2});
		String tyjmjedx = tyjmjeTemp[0];
		
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation
						.update("zzsf_xsxfjmsq", new String[] { "xysh",
								"xyshyj", "tyjmje", "tyjmjedx", "zzdjdm",
								"xyshryhm", "xyzzfzryj" }, new String[] {
								yesNo, xyshyj, tyjmje, tyjmjedx, zzdjdm,
								userName, xyzzfzryj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_xsxfjmsq", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) zzsf_xsxfjmsq";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "zzsf_xsxfjmsq";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,"
				+ "csrq,syd,zzmm,xslbmc,xslb,jzxm,jtdz,yzbm,jtdh,ssdz,"
				+ "ssdh,jtjjqk,yxknbzqk,qggw,psbxqk,sqzl,sqje,gehjqk,"
				+ "xxcj,sqsj,xysh,xyshyj,tyjmje,tyjmjedx,xxsh,xxshyj,"
				+ "bz,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyshrxm,xyzzfzryj,"
				+ "XYSH yesNo from view_zzsf_xsxfjmsq where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,"
				+ "csrq,syd,zzmm,xslbmc,xslb,jzxm,jtdz,yzbm,jtdh,ssdz,"
				+ "ssdh,jtjjqk,yxknbzqk,qggw,psbxqk,sqzl,sqje,gehjqk,"
				+ "xxcj,sqsj,xysh,xyshyj,tyjmje,tyjmjedx,xxsh,xxshyj,"
				+ "bz,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyshrxm,xyzzfzryj,"
				+ "XXSH yesNo from view_zzsf_xsxfjmsq where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", 
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", 
				"syd", "zzmm", "xslbmc", "xslb", "jzxm", "jtdz", "yzbm", 
				"jtdh", "ssdz", "ssdh", "jtjjqk", "yxknbzqk", "qggw", 
				"psbxqk", "sqzl", "sqje", "gehjqk", "xxcj", "sqsj", 
				"xysh", "xyshyj", "tyjmje", "tyjmjedx", "xxsh", 
				"xxshyj", "bz", "zzdjdm", "zzdjmc", "zzdjje", 
				"xyshryhm", "xyshrxm", "xyzzfzryj", "yesNo" };
		
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}

		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_xsxfjmsq");
		return mapping.findForward("success");
	}

	private ActionForward zzsf_gjlzjxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
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

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家励志奖学金' and b.xh=? ";
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
					String chhzjl = DealString.toGBK(request.getParameter("chhzjl")
							.toString());
					String jthk = DealString.toGBK(request.getParameter(
							"jthk").toString());
					String jtzrks = DealString.toGBK(request.getParameter(
							"jtzrks").toString());
					String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
							.toString());
					String rjysr = DealString.toGBK(request.getParameter("rjysr")
							.toString());
					String srly = DealString.toGBK(request.getParameter("srly")
							.toString());
					String jtzz = DealString.toGBK(request.getParameter(
							"jtzz").toString());
					String yzbm = DealString.toGBK(request.getParameter(
							"yzbm").toString());
					String sxnzhcpzpm = DealString.toGBK(request.getParameter(
							"sxnzhcpzpm").toString());
					String sxnxxcjzpm = DealString.toGBK(request.getParameter(
							"sxnxxcjzpm").toString());
					String sqly = DealString.toGBK(request.getParameter(
							"sqly").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "lxdh",
							"chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
							"srly", "jtzz", "yzbm", "sxnzhcpzpm",
							"sxnxxcjzpm", "sqly" };

					valueForOut = new String[] { xh, nd, lxdh, chhzjl, jthk,
							jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
							sxnzhcpzpm, sxnxxcjzpm, sqly };
					
					sql = "select xxsh from view_zzsf_gjlzjxj where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_gjlzjxj", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "nd", "lxdh",
									"chhzjl", "jthk", "jtzrks", "jtyzsr",
									"rjysr", "srly", "jtzz", "yzbm",
									"sxnzhcpzpm", "sxnxxcjzpm", "sqly", "sqsj",
									"xysh", "xxsh", "xyshyj", "xxshyj",
									"zzdjdm", "xyzzfzryj", "xyshryhm" };

							valueForOut = new String[] { xh, nd, lxdh, chhzjl,
									jthk, jtzrks, jtyzsr, rjysr, srly, jtzz,
									yzbm, sxnzhcpzpm, sxnxxcjzpm, sqly,
									rightNow, "未审核", "未审核", "", "", "", "", "" };

							boolean ok = false;
							ok = StandardOperation.update("zzsf_gjlzjxj",
									colName1, valueForOut, "xh||nd", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String chhzjl = DealString.toGBK(request.getParameter("chhzjl")
						.toString());
				String jthk = DealString.toGBK(request.getParameter(
						"jthk").toString());
				String jtzrks = DealString.toGBK(request.getParameter(
						"jtzrks").toString());
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
						.toString());
				String rjysr = DealString.toGBK(request.getParameter("rjysr")
						.toString());
				String srly = DealString.toGBK(request.getParameter("srly")
						.toString());
				String jtzz = DealString.toGBK(request.getParameter(
						"jtzz").toString());
				String yzbm = DealString.toGBK(request.getParameter(
						"yzbm").toString());
				String sxnzhcpzpm = DealString.toGBK(request.getParameter(
						"sxnzhcpzpm").toString());
				String sxnxxcjzpm = DealString.toGBK(request.getParameter(
						"sxnxxcjzpm").toString());
				String sqly = DealString.toGBK(request.getParameter(
						"sqly").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "lxdh",
						"chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
						"srly", "jtzz", "yzbm", "sxnzhcpzpm",
						"sxnxxcjzpm", "sqly" };

				valueForOut = new String[] { xh, nd, lxdh, chhzjl, jthk,
						jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
						sxnzhcpzpm, sxnxxcjzpm, sqly };
				
				sql = "select xxsh from view_zzsf_gjlzjxj where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_gjlzjxj", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "nd", "lxdh",
								"chhzjl", "jthk", "jtzrks", "jtyzsr",
								"rjysr", "srly", "jtzz", "yzbm",
								"sxnzhcpzpm", "sxnxxcjzpm", "sqly", "sqsj",
								"xysh", "xxsh", "xyshyj", "xxshyj",
								"zzdjdm", "xyzzfzryj", "xyshryhm" };

						valueForOut = new String[] { xh, nd, lxdh, chhzjl,
								jthk, jtzrks, jtyzsr, rjysr, srly, jtzz,
								yzbm, sxnzhcpzpm, sxnxxcjzpm, sqly,
								rightNow, "未审核", "未审核", "", "", "", "", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_gjlzjxj",
								colName1, valueForOut, "xh||nd", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
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

		sql = "select xh,nd,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sxnzhcpzpm,sxnxxcjzpm,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj from view_zzsf_gjlzjxj "
				+ "where xh||nd=?";
		outString = new String[] { "xh","nd","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xydm","xymc","zydm","zymc","bjdm","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","sxnzhcpzpm","sxnxxcjzpm","sqly","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.csrq,a.mzmc,a.zzmmmc,(select x.rxny from bks_xsjbxx x where x.xh=a.xh) rxrq,a.sfzh,a.xymc,a.zymc,a.bjmc from view_zzsf_knsxx a where a.xh=? and nd=? and xxsh in ('一般困难','困难','特殊困难')";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh,nd },
						colName);
				if (outVal == null) {
					request.setAttribute("notKns", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
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
			map.put("sqsj", rightNow);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}
		if (null != xh && dao.isKns(xh)) {
			request.setAttribute("isKNS", "is");
		} else {
			request.setAttribute("isKNS", "no");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_gjlzjxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString()); 
		String nd = DealString.toGBK(request.getParameter("nd").toString()); 
		String xm = DealString.toGBK(request.getParameter("xm").toString()); 
		String xb = DealString.toGBK(request.getParameter("xb").toString()); 
		String csrq = DealString.toGBK(request.getParameter("csrq").toString()); 
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString()); 
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc").toString()); 
		String rxrq = DealString.toGBK(request.getParameter("rxrq").toString()); 
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString()); 
		String xymc = DealString.toGBK(request.getParameter("xymc").toString()); 
		String zymc = DealString.toGBK(request.getParameter("zymc").toString()); 
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString()); 
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString()); 
		String chhzjl = DealString.toGBK(request.getParameter("chhzjl").toString()); 
		String jthk = DealString.toGBK(request.getParameter("jthk").toString()); 
		String jtzrks = DealString.toGBK(request.getParameter("jtzrks").toString()); 
		String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr").toString()); 
		String rjysr = DealString.toGBK(request.getParameter("rjysr").toString()); 
		String srly = DealString.toGBK(request.getParameter("srly").toString()); 
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString()); 
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString()); 
		String sxnzhcpzpm = DealString.toGBK(request.getParameter("sxnzhcpzpm").toString()); 
		String sxnxxcjzpm = DealString.toGBK(request.getParameter("sxnxxcjzpm").toString()); 
		String sqly = DealString.toGBK(request.getParameter("sqly").toString()); 
		String xysh = DealString.toGBK(request.getParameter("xysh").toString()); 
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj").toString()); 
		String xxsh = DealString.toGBK(request.getParameter("xxsh").toString()); 
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj").toString()); 
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj").toString()); 
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}

		String[] outValue = new String[] { xh,nd,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xymc,zymc,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sxnzhcpzpm,sxnxxcjzpm,sqly,xysh,xyshyj,xxsh,xxshyj,xyzzfzryj };
		String[] outString = new String[] { "xh","nd","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xymc","zymc","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","sxnzhcpzpm","sxnxxcjzpm","sqly","xysh","xyshyj","xxsh","xxshyj","xyzzfzryj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjlzjxj(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_gjlzjxj";
		pk = "xh||nd";
		tableName = "view_zzsf_gjlzjxj";
		
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 国家励志奖学金审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家励志奖学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sxnzhcpzpm||'##OneSpile##'||a.sxnxxcjzpm||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sxnzhcpzpm||'##OneSpile##'||a.sxnxxcjzpm||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sxnzhcpzpm||'##OneSpile##'||a.sxnxxcjzpm||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sxnzhcpzpm||'##OneSpile##'||a.sxnxxcjzpm||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##rxrq##OneSpile##sfzh##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##lxdh##OneSpile##chhzjl##OneSpile##jthk##OneSpile##jtzrks##OneSpile##jtyzsr##OneSpile##rjysr##OneSpile##srly##OneSpile##jtzz##OneSpile##yzbm##OneSpile##sxnzhcpzpm##OneSpile##sxnxxcjzpm##OneSpile##sqly##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyzzfzryj";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("act", "zzsf_gjlzjxj");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjlzjxj_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshryhm = session.getAttribute("userName").toString();

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

		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_gjlzjxj where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_gjlzjxj where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjlzjxj.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_gjlzjxj set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_gjlzjxj set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjlzjxj.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_gjlzjxj", new String[] {
						"xysh", "xyshyj", "zzdjdm", "xyshryhm", "xyzzfzryj" },
						new String[] { yesNo, xyshyj, zzdjdm, xyshryhm,
								xyzzfzryj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_gjlzjxj", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_gjlzjxj";
				Base.log(request, logMsg, sUName);
			}
		}

		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_gjlzjxj";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sxnzhcpzpm,sxnxxcjzpm,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XYSH yesNo from "
					+ "view_zzsf_gjlzjxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sxnzhcpzpm,sxnxxcjzpm,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XXSH yesNo from "
					+ "view_zzsf_gjlzjxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh","nd","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xydm","xymc","zydm","zymc","bjdm","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","sxnzhcpzpm","sxnxxcjzpm","sqly","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_gjlzjxj");
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_gjjxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String xn = Base.currXn;

		// String zzdjdm = DealString.toGBK(xszzForm.getZzdjdm());
		// List xszzDjList = dao.getXszzDjList();
		// request.setAttribute("xszzDjList", xszzDjList);
		// sql = "select zzdjdm,zzdjmc,zzdjje from zzsf_xszzdjdmb where
		// zzdjdm=?";
		// String[] zzdj = dao.getOneRs(sql, new String[] { zzdjdm },
		// new String[] { "zzdjdm", "zzdjmc", "zzdjje" });

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家奖学金' and b.xh=? ";
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
					String gxnbxkcs = DealString.toGBK(request.getParameter(
							"gxnbxkcs").toString());
					String yxkcs = DealString.toGBK(request.getParameter(
							"yxkcs").toString());
					String lhkcs = DealString.toGBK(request.getParameter(
							"lhkcs").toString());
					String cjpm = DealString.toGBK(request.getParameter("cjpm")
							.toString());
					String zhkpcj = DealString.toGBK(request.getParameter(
							"zhkpcj").toString());
					String zhkppm = DealString.toGBK(request.getParameter(
							"zhkppm").toString());
					String hjqk = DealString.toGBK(request.getParameter("hjqk")
							.toString());
					String hjqk_xj = DealString.toGBK(request.getParameter(
							"hjqk_xj").toString());
					String hjqk_xxj = DealString.toGBK(request.getParameter(
							"hjqk_xxj").toString());
					String hjqk_sj = DealString.toGBK(request.getParameter(
							"hjqk_sj").toString());
					String sqly = DealString.toGBK(request.getParameter("sqly")
							.toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + xn;
					}

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "xn", "lxdh",
							"gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj",
							"zhkppm", "hjqk", "hjqk_xj", "hjqk_xxj",
							"hjqk_sj", "sqly" };

					valueForOut = new String[] { xh, xn, lxdh, gxnbxkcs,
							yxkcs, lhkcs, cjpm, zhkpcj, zhkppm, hjqk,
							hjqk_xj, hjqk_xxj, hjqk_sj, sqly };
					
					sql = "select xxsh from zzsf_gjjxj where xh||xn=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_gjjxj", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "xn", "lxdh",
									"gxnbxkcs", "yxkcs", "lhkcs", "cjpm",
									"zhkpcj", "zhkppm", "hjqk", "hjqk_xj",
									"hjqk_xxj", "hjqk_sj", "sqly", "sqsj",
									"xysh", "xxsh", "xyshyj", "xxshyj",
									"zzdjdm", "xyzzfzryj", "xyshryhm" };

							valueForOut = new String[] { xh, xn, lxdh,
									gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj,
									zhkppm, hjqk, hjqk_xj, hjqk_xxj, hjqk_sj,
									sqly, rightNow, "未审核", "未审核", "", "", "",
									"", "" };

							boolean ok = false;
							ok = StandardOperation.update("zzsf_gjjxj",
									colName1, valueForOut, "xh||xn", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String gxnbxkcs = DealString.toGBK(request.getParameter(
						"gxnbxkcs").toString());
				String yxkcs = DealString.toGBK(request.getParameter(
						"yxkcs").toString());
				String lhkcs = DealString.toGBK(request.getParameter(
						"lhkcs").toString());
				String cjpm = DealString.toGBK(request.getParameter("cjpm")
						.toString());
				String zhkpcj = DealString.toGBK(request.getParameter(
						"zhkpcj").toString());
				String zhkppm = DealString.toGBK(request.getParameter(
						"zhkppm").toString());
				String hjqk = DealString.toGBK(request.getParameter("hjqk")
						.toString());
				String hjqk_xj = DealString.toGBK(request.getParameter(
						"hjqk_xj").toString());
				String hjqk_xxj = DealString.toGBK(request.getParameter(
						"hjqk_xxj").toString());
				String hjqk_sj = DealString.toGBK(request.getParameter(
						"hjqk_sj").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + xn;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "xn", "lxdh",
						"gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj",
						"zhkppm", "hjqk", "hjqk_xj", "hjqk_xxj",
						"hjqk_sj", "sqly" };

				valueForOut = new String[] { xh, xn, lxdh, gxnbxkcs,
						yxkcs, lhkcs, cjpm, zhkpcj, zhkppm, hjqk,
						hjqk_xj, hjqk_xxj, hjqk_sj, sqly };
				
				sql = "select xxsh from zzsf_gjjxj where xh||xn=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_gjjxj", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "xn", "lxdh",
								"gxnbxkcs", "yxkcs", "lhkcs", "cjpm",
								"zhkpcj", "zhkppm", "hjqk", "hjqk_xj",
								"hjqk_xxj", "hjqk_sj", "sqly", "sqsj",
								"xysh", "xxsh", "xyshyj", "xxshyj",
								"zzdjdm", "xyzzfzryj", "xyshryhm" };

						valueForOut = new String[] { xh, xn, lxdh,
								gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj,
								zhkppm, hjqk, hjqk_xj, hjqk_xxj, hjqk_sj,
								sqly, rightNow, "未审核", "未审核", "", "", "",
								"", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_gjjxj",
								colName1, valueForOut, "xh||xn", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
				}
			}
		}
		pkVal = request.getParameter("pkVal");
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + xn;
		}

		sql = "select xh,xn,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,rxrq,mzmc,zzmmmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,zhkppm,hjqk,hjqk_xj,hjqk_xxj,hjqk_sj,sqly,sqsj,zzdjdm,zzdjmc,zzdjje,xysh,xyshyj,xxsh,xxshyj,xyshrxm,xydzzshyj from view_zzsf_gjjxj "
				+ "where xh||xn=?";
		outString = new String[] { "xh", "xn", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "csrq", "rxrq",
				"mzmc", "zzmmmc", "lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm",
				"zhkpcj", "zhkppm", "hjqk", "hjqk_xj", "hjqk_xxj", "hjqk_sj",
				"sqly", "sqsj", "zzdjdm", "zzdjmc", "zzdjje", "xysh", "xyshyj",
				"xxsh", "xxshyj", "xyshrxm", "xydzzshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,b.csrq,b.mzmc,b.zzmmmc,(select j.rxny from bks_xsjbxx j where j.xh=a.xh) rxrq,a.sfzh,a.xymc,a.zymc,a.bjmc from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
					request.setAttribute("notKns", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
				map.put("sqsj", rightNow);
			}
		} else {
			map.put("xn", xn);
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
	
	private ActionForward zzsf_gjjxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh=DealString.toGBK(request.getParameter("xh"));
		String xn=DealString.toGBK(request.getParameter("xn"));
		String xm=DealString.toGBK(request.getParameter("xm"));
		String xb=DealString.toGBK(request.getParameter("xb"));
		String xymc=DealString.toGBK(request.getParameter("xymc"));
		String zymc=DealString.toGBK(request.getParameter("zymc"));
		String bjmc=DealString.toGBK(request.getParameter("bjmc"));
		String sfzh=DealString.toGBK(request.getParameter("sfzh"));
		String csrq=DealString.toGBK(request.getParameter("csrq"));
		String rxrq=DealString.toGBK(request.getParameter("rxrq"));
		String mzmc=DealString.toGBK(request.getParameter("mzmc"));
		String zzmmmc=DealString.toGBK(request.getParameter("zzmmmc"));
		String lxdh=DealString.toGBK(request.getParameter("lxdh"));
		String gxnbxkcs=DealString.toGBK(request.getParameter("gxnbxkcs"));
		String yxkcs=DealString.toGBK(request.getParameter("yxkcs"));
		String lhkcs=DealString.toGBK(request.getParameter("lhkcs"));
		String cjpm=DealString.toGBK(request.getParameter("cjpm"));
		String zhkpcj=DealString.toGBK(request.getParameter("zhkpcj"));
		String zhkppm=DealString.toGBK(request.getParameter("zhkppm"));
		String hjqk=DealString.toGBK(request.getParameter("hjqk"));
		String hjqk_xj=DealString.toGBK(request.getParameter("hjqk_xj"));
		String hjqk_xxj=DealString.toGBK(request.getParameter("hjqk_xxj"));
		String hjqk_sj=DealString.toGBK(request.getParameter("hjqk_sj"));
		String sqly=DealString.toGBK(request.getParameter("sqly"));
		String sqsj=DealString.toGBK(request.getParameter("sqsj"));
		String zzdjdm=DealString.toGBK(request.getParameter("zzdjdm"));
		String zzdjmc=DealString.toGBK(request.getParameter("zzdjmc"));
		String zzdjje=DealString.toGBK(request.getParameter("zzdjje"));
		String xysh=DealString.toGBK(request.getParameter("xysh"));
		String xyshyj=DealString.toGBK(request.getParameter("xyshyj"));
		String xxsh=DealString.toGBK(request.getParameter("xxsh"));
		String xxshyj=DealString.toGBK(request.getParameter("xxshyj"));
		String xyshrxm=DealString.toGBK(request.getParameter("xyshrxm"));
		String xydzzshyj=DealString.toGBK(request.getParameter("xydzzshyj"));
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}
		String[] sT = new String[] { "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "" };
		if(null != sfzh){

			for (int i = 0; i < sfzh.length(); i++) {
				sT[i] = sfzh.substring(i, i + 1);
			}
			map.put("sfzh1", sT[0]);
			map.put("sfzh2", sT[1]);
			map.put("sfzh3", sT[2]);
			map.put("sfzh4", sT[3]);
			map.put("sfzh5", sT[4]);
			map.put("sfzh6", sT[5]);
			map.put("sfzh7", sT[6]);
			map.put("sfzh8", sT[7]);
			map.put("sfzh9", sT[8]);
			map.put("sfzh10", sT[9]);
			map.put("sfzh11", sT[10]);
			map.put("sfzh12", sT[11]);
			map.put("sfzh13", sT[12]);
			map.put("sfzh14", sT[13]);
			map.put("sfzh15", sT[14]);
			map.put("sfzh16", sT[15]);
			map.put("sfzh17", sT[16]);
			map.put("sfzh18", sT[17]);
		}

		String[] outValue = new String[] { xh,xn,xm,xb,xymc,zymc,bjmc,sfzh,csrq,rxrq,mzmc,zzmmmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,zhkppm,hjqk,hjqk_xj,hjqk_xxj,hjqk_sj,sqly,sqsj,zzdjdm,zzdjmc,zzdjje,xysh,xyshyj,xxsh,xxshyj,xyshrxm,xydzzshyj };
		String[] outString = new String[] { "xh", "xn", "xm", "xb", "xymc", "zymc", "bjmc", "sfzh", "csrq", "rxrq", "mzmc", "zzmmmc", "lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj", "zhkppm", "hjqk", "hjqk_xj", "hjqk_xxj", "hjqk_sj", "sqly", "sqsj", "zzdjdm", "zzdjmc", "zzdjje", "xysh", "xyshyj", "xxsh", "xxshyj", "xyshrxm", "xydzzshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjjxj(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_gjjxj";
		pk = "xh||xn";
		tableName = "view_zzsf_gjjxj";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
			xn = Base.currXn;
		} else {
			xn = request.getParameter("xn");
		}
		if (isNull(xn)) {
		} else {
			querry.append(" and xn='");
			querry.append(xn);
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 国家奖学金审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家奖学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshrxm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gxnbxkcs||'##OneSpile##'||a.yxkcs||'##OneSpile##'||a.lhkcs||'##OneSpile##'||a.cjpm||'##OneSpile##'||a.zhkpcj||'##OneSpile##'||a.zhkppm||'##OneSpile##'||a.hjqk||'##OneSpile##'||a.hjqk_xj||'##OneSpile##'||a.hjqk_xxj||'##OneSpile##'||a.hjqk_sj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xydzzshyj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gxnbxkcs||'##OneSpile##'||a.yxkcs||'##OneSpile##'||a.lhkcs||'##OneSpile##'||a.cjpm||'##OneSpile##'||a.zhkpcj||'##OneSpile##'||a.zhkppm||'##OneSpile##'||a.hjqk||'##OneSpile##'||a.hjqk_xj||'##OneSpile##'||a.hjqk_xxj||'##OneSpile##'||a.hjqk_sj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xydzzshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshrxm", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gxnbxkcs||'##OneSpile##'||a.yxkcs||'##OneSpile##'||a.lhkcs||'##OneSpile##'||a.cjpm||'##OneSpile##'||a.zhkpcj||'##OneSpile##'||a.zhkppm||'##OneSpile##'||a.hjqk||'##OneSpile##'||a.hjqk_xj||'##OneSpile##'||a.hjqk_xxj||'##OneSpile##'||a.hjqk_sj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xydzzshyj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.gxnbxkcs||'##OneSpile##'||a.yxkcs||'##OneSpile##'||a.lhkcs||'##OneSpile##'||a.cjpm||'##OneSpile##'||a.zhkpcj||'##OneSpile##'||a.zhkppm||'##OneSpile##'||a.hjqk||'##OneSpile##'||a.hjqk_xj||'##OneSpile##'||a.hjqk_xxj||'##OneSpile##'||a.hjqk_sj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xyshrxm||'##OneSpile##'||a.xydzzshyj col from "
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
		String colListS = "xh##OneSpile##xn##OneSpile##xm##OneSpile##xb##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##sfzh##OneSpile##csrq##OneSpile##rxrq##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##lxdh##OneSpile##gxnbxkcs##OneSpile##" +
				"yxkcs##OneSpile##lhkcs##OneSpile##cjpm##OneSpile##zhkpcj##OneSpile##zhkppm##OneSpile##hjqk##OneSpile##hjqk_xj##OneSpile##hjqk_xxj##OneSpile##hjqk_sj##OneSpile##sqly##OneSpile##sqsj##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##xyshrxm##OneSpile##xydzzshyj";
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
		map.put("xn", xn);
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsf_gjjxj");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjjxj_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshrxm = session.getAttribute("userName").toString();

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

		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xydzzshyj = DealString.toGBK(request.getParameter("xydzzshyj"));
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_gjjxj where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_gjjxj where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjjxj.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_gjjxj set xysh='"+jg+"' where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_gjjxj set xxsh='"+jg+"' where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjjxj.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_gjjxj", new String[] {
						"xysh", "xyshyj", "zzdjdm", "xyshrxm", "xydzzshyj" },
						new String[] { yesNo, xyshyj, zzdjdm, xyshrxm,
						xydzzshyj }, "xh||xn", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_gjjxj", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||xn", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_gjjxj";
				Base.log(request, logMsg, sUName);
			}
		}

		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_gjjxj";
		pk = "xh||xn";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,xn,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,rxrq,mzmc,zzmmmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,zhkppm,hjqk,hjqk_xj,hjqk_xxj,hjqk_sj,sqly,sqsj,zzdjdm,zzdjmc,zzdjje,xysh,xyshyj,xxsh,xxshyj,xyshrxm,xydzzshyj,XYSH yesNo from "
					+ "view_zzsf_gjjxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xn,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,rxrq,mzmc,zzmmmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,zhkppm,hjqk,hjqk_xj,hjqk_xxj,hjqk_sj,sqly,sqsj,zzdjdm,zzdjmc,zzdjje,xysh,xyshyj,xxsh,xxshyj,xyshrxm,xydzzshyj,XXSH yesNo from "
					+ "view_zzsf_gjjxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xn", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "csrq", "rxrq", "mzmc", "zzmmmc", "lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj", "zhkppm", "hjqk", "hjqk_xj", "hjqk_xxj", "hjqk_sj", "sqly", "sqsj", "zzdjdm", "zzdjmc", "zzdjje", "xysh", "xyshyj", "xxsh", "xxshyj", "xyshrxm", "xydzzshyj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("xyshrxm", xyshrxm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_gjjxj");
		return mapping.findForward("success");
	}

	private ActionForward zzsf_gjzxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String xn = Base.currXn;

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学金' and b.xh=? ";
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
					String chhzjl = DealString.toGBK(request.getParameter("chhzjl")
							.toString());
					String jthk = DealString.toGBK(request.getParameter(
							"jthk").toString());
					String jtzrks = DealString.toGBK(request.getParameter(
							"jtzrks").toString());
					String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
							.toString());
					String rjysr = DealString.toGBK(request.getParameter("rjysr")
							.toString());
					String srly = DealString.toGBK(request.getParameter("srly")
							.toString());
					String jtzz = DealString.toGBK(request.getParameter(
							"jtzz").toString());
					String yzbm = DealString.toGBK(request.getParameter(
							"yzbm").toString());
					String xslb = DealString.toGBK(request.getParameter(
							"xslb").toString());
					String xxcj = DealString.toGBK(request.getParameter(
							"xxcj").toString());
					String sqly = DealString.toGBK(request.getParameter(
							"sqly").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + xn;
					}

					sql = "select xxsh from zzsf_gjzxj where xh||xn=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "xn", "lxdh",
							"chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
							"srly", "jtzz", "yzbm", "xslb",
							"xxcj", "sqly" };

					valueForOut = new String[] { xh, xn, lxdh, chhzjl, jthk,
							jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
							xslb, xxcj, sqly };
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_gjzxj", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "lxdh", "chhzjl", "jthk",
									"jtzrks", "jtyzsr", "rjysr", "srly",
									"jtzz", "yzbm", "xslb", "xxcj", "sqly",
									"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj",
									"zzdjdm", "xyshryhm", "xyzzfzryj" };

							valueForOut = new String[] { lxdh, chhzjl, jthk,
									jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
									xslb, xxcj, sqly, rightNow, "未审核", "",
									"未审核", "", "", "", "" };
							boolean ok = false;
							ok = StandardOperation.update("zzsf_gjzxj",
									colName1, valueForOut, "xh||xn", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String chhzjl = DealString.toGBK(request.getParameter("chhzjl")
						.toString());
				String jthk = DealString.toGBK(request.getParameter(
						"jthk").toString());
				String jtzrks = DealString.toGBK(request.getParameter(
						"jtzrks").toString());
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
						.toString());
				String rjysr = DealString.toGBK(request.getParameter("rjysr")
						.toString());
				String srly = DealString.toGBK(request.getParameter("srly")
						.toString());
				String jtzz = DealString.toGBK(request.getParameter(
						"jtzz").toString());
				String yzbm = DealString.toGBK(request.getParameter(
						"yzbm").toString());
				String xslb = DealString.toGBK(request.getParameter(
						"xslb").toString());
				String xxcj = DealString.toGBK(request.getParameter(
						"xxcj").toString());
				String sqly = DealString.toGBK(request.getParameter(
						"sqly").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + xn;
				}
				
				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "xn", "lxdh",
						"chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
						"srly", "jtzz", "yzbm", "xslb",
						"xxcj", "sqly" };

				valueForOut = new String[] { xh, xn, lxdh, chhzjl, jthk,
						jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
						xslb, xxcj, sqly };

				sql = "select xxsh from zzsf_gjzxj where xh||xn=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_gjzxj", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "lxdh", "chhzjl", "jthk",
								"jtzrks", "jtyzsr", "rjysr", "srly",
								"jtzz", "yzbm", "xslb", "xxcj", "sqly",
								"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj",
								"zzdjdm", "xyshryhm", "xyzzfzryj" };

						valueForOut = new String[] { lxdh, chhzjl, jthk,
								jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
								xslb, xxcj, sqly, rightNow, "未审核", "",
								"未审核", "", "", "", "" };
						boolean ok = false;
						ok = StandardOperation.update("zzsf_gjzxj",
								colName1, valueForOut, "xh||xn", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
				}
			}
		}
		pkVal = request.getParameter("pkVal");
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + xn;
		}

		sql = "select xh,xn,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xslb,xxcj,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj from view_zzsf_gjzxj "
				+ "where xh||xn=?";
		outString = new String[] { "xh","xn","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xydm","xymc","zydm","zymc","bjdm","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","xslb","xxcj","sqly","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,b.csrq,b.mzmc,b.zzmmmc,(select j.rxny from bks_xsjbxx j where j.xh=a.xh) rxrq,a.sfzh,a.xymc,a.zymc,a.bjmc from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
					request.setAttribute("notKns", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
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
			map.put("sqsj", rightNow);
			map.put("xn", xn);
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
		if (null != xh && dao.isKns(xh)) {
			request.setAttribute("isKNS", "is");
		} else {
			request.setAttribute("isKNS", "no");
		}
		List xszzXslbList = dao.getXszzXslbList();
		request.setAttribute("xszzXslbList", xszzXslbList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_gjzxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString()); 
		String xn = DealString.toGBK(request.getParameter("xn").toString()); 
		String xm = DealString.toGBK(request.getParameter("xm").toString()); 
		String xb = DealString.toGBK(request.getParameter("xb").toString()); 
		String csrq = DealString.toGBK(request.getParameter("csrq").toString()); 
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString()); 
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc").toString()); 
		String rxrq = DealString.toGBK(request.getParameter("rxrq").toString()); 
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString()); 
		String xymc = DealString.toGBK(request.getParameter("xymc").toString()); 
		String zymc = DealString.toGBK(request.getParameter("zymc").toString()); 
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString()); 
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString()); 
		String chhzjl = DealString.toGBK(request.getParameter("chhzjl").toString()); 
		String jthk = DealString.toGBK(request.getParameter("jthk").toString()); 
		String jtzrks = DealString.toGBK(request.getParameter("jtzrks").toString()); 
		String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr").toString()); 
		String rjysr = DealString.toGBK(request.getParameter("rjysr").toString()); 
		String srly = DealString.toGBK(request.getParameter("srly").toString()); 
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString()); 
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString()); 
		String xslb = DealString.toGBK(request.getParameter("xslb").toString()); 
		String xxcj = DealString.toGBK(request.getParameter("xxcj").toString()); 
		String sqly = DealString.toGBK(request.getParameter("sqly").toString()); 
		String xysh = DealString.toGBK(request.getParameter("xysh").toString()); 
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj").toString()); 
		String xxsh = DealString.toGBK(request.getParameter("xxsh").toString()); 
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj").toString()); 
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj").toString()); 
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}

		String[] outValue = new String[] { xh,xn,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xymc,zymc,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xslb,xxcj,sqly,xysh,xyshyj,xxsh,xxshyj,xyzzfzryj };
		String[] outString = new String[] { "xh","xn","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xymc","zymc","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","xslb","xxcj","sqly","xysh","xyshyj","xxsh","xxshyj","xyzzfzryj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjzxj(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_gjzxj";
		pk = "xh||xn";
		tableName = "view_zzsf_gjzxj";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
			xn = Base.currXn;
		} else {
			xn = request.getParameter("xn");
		}
		if (isNull(xn)) {
		} else {
			querry.append(" and xn='");
			querry.append(xn);
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 国家助学金审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xslb||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xslb||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xslb||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.xn||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.rxrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.chhzjl||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.rjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.xslb||'##OneSpile##'||a.xxcj||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
		String colListS = "xh##OneSpile##xn##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##rxrq##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc" +
				"##OneSpile##lxdh##OneSpile##chhzjl##OneSpile##jthk##OneSpile##jtzrks##OneSpile##jtyzsr##OneSpile##rjysr##OneSpile##srly##OneSpile##jtzz##OneSpile##yzbm##OneSpile##xslb##OneSpile##xxcj##OneSpile##sqly##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyzzfzryj";
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
		map.put("xn", xn);
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsf_gjzxj");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjzxj_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshryhm = session.getAttribute("userName").toString();

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

		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_gjzxj where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_gjzxj where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjzxj.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_gjzxj set xysh='"+jg+"' where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_gjzxj set xxsh='"+jg+"' where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjzxj.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_gjzxj", new String[] {
						"xysh", "xyshyj", "zzdjdm", "xyshryhm", "xyzzfzryj" },
						new String[] { yesNo, xyshyj, zzdjdm, xyshryhm,
								xyzzfzryj }, "xh||xn", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_gjzxj", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||xn", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_gjzxj";
				Base.log(request, logMsg, sUName);
			}
		}

		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_gjzxj";
		pk = "xh||xn";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,xn,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xslb,xxcj,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XYSH yesNo from "
					+ "view_zzsf_gjzxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xn,xm,xb,csrq,mzmc,zzmmmc,rxrq,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xslb,xxcj,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XXSH yesNo from "
					+ "view_zzsf_gjzxj where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh","xn","xm","xb","csrq","mzmc","zzmmmc","rxrq","sfzh","xydm","xymc","zydm","zymc","bjdm","bjmc","lxdh","chhzjl","jthk","jtzrks","jtyzsr","rjysr","srly","jtzz","yzbm","xslb","xxcj","sqly","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_gjzxj");
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_lstd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
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
					String jthk = DealString.toGBK(request.getParameter("jthk")
							.toString());
					String jtzrks = DealString.toGBK(request.getParameter(
							"jtzrks").toString());
					String nzsr = DealString.toGBK(request.getParameter("nzsr")
							.toString());
					String jtzz = DealString.toGBK(
							request.getParameter("jtzz").toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String lxdh = DealString.toGBK(request.getParameter("lxdh")
							.toString());
					String jtjjknzyyy = DealString.toGBK(
							request.getParameter("jtjjknzyyy").toString());
					String jtcy1_xm = DealString.toGBK(request.getParameter(
							"jtcy1_xm").toString());
					String jtcy1_nl = DealString.toGBK(request.getParameter(
							"jtcy1_nl").toString());
					String jtcy1_cw = DealString.toGBK(request.getParameter(
							"jtcy1_cw").toString());
					String jtcy1_szdw = DealString.toGBK(request.getParameter(
							"jtcy1_szdw").toString());
					String jtcy1_ysr = DealString.toGBK(request.getParameter(
							"jtcy1_ysr").toString());
					String jtcy2_xm = DealString.toGBK(request.getParameter(
							"jtcy2_xm").toString());
					String jtcy2_nl = DealString.toGBK(request.getParameter(
							"jtcy2_nl").toString());
					String jtcy2_cw = DealString.toGBK(request.getParameter(
							"jtcy2_cw").toString());
					String jtcy2_szdw = DealString.toGBK(request.getParameter(
							"jtcy2_szdw").toString());
					String jtcy2_ysr = DealString.toGBK(request.getParameter(
							"jtcy2_ysr").toString());
					String jtcy3_xm = DealString.toGBK(request.getParameter(
							"jtcy3_xm").toString());
					String jtcy3_nl = DealString.toGBK(request.getParameter(
							"jtcy3_nl").toString());
					String jtcy3_cw = DealString.toGBK(request.getParameter(
							"jtcy3_cw").toString());
					String jtcy3_szdw = DealString.toGBK(request.getParameter(
							"jtcy3_szdw").toString());
					String jtcy3_ysr = DealString.toGBK(request.getParameter(
							"jtcy3_ysr").toString());
					String jtcy4_xm = DealString.toGBK(request.getParameter(
							"jtcy4_xm").toString());
					String jtcy4_nl = DealString.toGBK(request.getParameter(
							"jtcy4_nl").toString());
					String jtcy4_cw = DealString.toGBK(request.getParameter(
							"jtcy4_cw").toString());
					String jtcy4_szdw = DealString.toGBK(request.getParameter(
							"jtcy4_szdw").toString());
					String jtcy4_ysr = DealString.toGBK(request.getParameter(
							"jtcy4_ysr").toString());
					String jtcy5_xm = DealString.toGBK(request.getParameter(
							"jtcy5_xm").toString());
					String jtcy5_nl = DealString.toGBK(request.getParameter(
							"jtcy5_nl").toString());
					String jtcy5_cw = DealString.toGBK(request.getParameter(
							"jtcy5_cw").toString());
					String jtcy5_szdw = DealString.toGBK(request.getParameter(
							"jtcy5_szdw").toString());
					String jtcy5_ysr = DealString.toGBK(request.getParameter(
							"jtcy5_ysr").toString());
					String qfqk_xf = DealString.toGBK(request.getParameter(
							"qfqk_xf").toString());
					String qfqk_zsf = DealString.toGBK(request.getParameter(
							"qfqk_zsf").toString());
					String qfqk_jcf = DealString.toGBK(request.getParameter(
							"qfqk_jcf").toString());
					String qfqk_hj = DealString.toGBK(request.getParameter(
							"qfqk_hj").toString());
					String jfjh = DealString.toGBK(
							request.getParameter("jfjh").toString());
					String fmszdwdh = DealString.toGBK(request.getParameter(
							"fmszdwdh").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "jthk",
							"jtzrks", "nzsr", "jtzz", "yzbm", "lxdh",
							"jtjjknzyyy", "jtcy1_xm", "jtcy1_nl",
							"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
							"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
							"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
							"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
							"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
							"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
							"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
							"jtcy5_szdw", "jtcy5_ysr", "qfqk_xf",
							"qfqk_zsf", "qfqk_jcf", "qfqk_hj", "jfjh",
							"fmszdwdh" };

					valueForOut = new String[] { xh, nd, jthk, jtzrks,
							nzsr, jtzz, yzbm, lxdh, jtjjknzyyy, jtcy1_xm,
							jtcy1_nl, jtcy1_cw, jtcy1_szdw, jtcy1_ysr,
							jtcy2_xm, jtcy2_nl, jtcy2_cw, jtcy2_szdw,
							jtcy2_ysr, jtcy3_xm, jtcy3_nl, jtcy3_cw,
							jtcy3_szdw, jtcy3_ysr, jtcy4_xm, jtcy4_nl,
							jtcy4_cw, jtcy4_szdw, jtcy4_ysr, jtcy5_xm,
							jtcy5_nl, jtcy5_cw, jtcy5_szdw, jtcy5_ysr,
							qfqk_xf, qfqk_zsf, qfqk_jcf, qfqk_hj, jfjh,
							fmszdwdh };
					
					sql = "select xxsh from zzsf_lstd where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_lstd", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "nd", "jthk",
									"jtzrks", "nzsr", "jtzz", "yzbm", "lxdh",
									"jtjjknzyyy", "jtcy1_xm", "jtcy1_nl",
									"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
									"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
									"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
									"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
									"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
									"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
									"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
									"jtcy5_szdw", "jtcy5_ysr", "qfqk_xf",
									"qfqk_zsf", "qfqk_jcf", "qfqk_hj", "jfjh",
									"fmszdwdh", "sqsj", "xysh", "xxsh",
									"xyshyj", "xxshyj", "zzdjdm", "xyzzfzryj",
									"xyshryhm" };

							valueForOut = new String[] { xh, nd, jthk, jtzrks,
									nzsr, jtzz, yzbm, lxdh, jtjjknzyyy,
									jtcy1_xm, jtcy1_nl, jtcy1_cw, jtcy1_szdw,
									jtcy1_ysr, jtcy2_xm, jtcy2_nl, jtcy2_cw,
									jtcy2_szdw, jtcy2_ysr, jtcy3_xm, jtcy3_nl,
									jtcy3_cw, jtcy3_szdw, jtcy3_ysr, jtcy4_xm,
									jtcy4_nl, jtcy4_cw, jtcy4_szdw, jtcy4_ysr,
									jtcy5_xm, jtcy5_nl, jtcy5_cw, jtcy5_szdw,
									jtcy5_ysr, qfqk_xf, qfqk_zsf, qfqk_jcf,
									qfqk_hj, jfjh, fmszdwdh, rightNow, "未审核",
									"未审核", "", "", "", "", "" };

							boolean ok = false;
							ok = StandardOperation.update("zzsf_lstd",
									colName1, valueForOut, "xh||nd", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String jthk = DealString.toGBK(request.getParameter("jthk")
						.toString());
				String jtzrks = DealString.toGBK(request.getParameter(
						"jtzrks").toString());
				String nzsr = DealString.toGBK(request.getParameter("nzsr")
						.toString());
				String jtzz = DealString.toGBK(
						request.getParameter("jtzz").toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String lxdh = DealString.toGBK(request.getParameter("lxdh")
						.toString());
				String jtjjknzyyy = DealString.toGBK(
						request.getParameter("jtjjknzyyy").toString());
				String jtcy1_xm = DealString.toGBK(request.getParameter(
						"jtcy1_xm").toString());
				String jtcy1_nl = DealString.toGBK(request.getParameter(
						"jtcy1_nl").toString());
				String jtcy1_cw = DealString.toGBK(request.getParameter(
						"jtcy1_cw").toString());
				String jtcy1_szdw = DealString.toGBK(request.getParameter(
						"jtcy1_szdw").toString());
				String jtcy1_ysr = DealString.toGBK(request.getParameter(
						"jtcy1_ysr").toString());
				String jtcy2_xm = DealString.toGBK(request.getParameter(
						"jtcy2_xm").toString());
				String jtcy2_nl = DealString.toGBK(request.getParameter(
						"jtcy2_nl").toString());
				String jtcy2_cw = DealString.toGBK(request.getParameter(
						"jtcy2_cw").toString());
				String jtcy2_szdw = DealString.toGBK(request.getParameter(
						"jtcy2_szdw").toString());
				String jtcy2_ysr = DealString.toGBK(request.getParameter(
						"jtcy2_ysr").toString());
				String jtcy3_xm = DealString.toGBK(request.getParameter(
						"jtcy3_xm").toString());
				String jtcy3_nl = DealString.toGBK(request.getParameter(
						"jtcy3_nl").toString());
				String jtcy3_cw = DealString.toGBK(request.getParameter(
						"jtcy3_cw").toString());
				String jtcy3_szdw = DealString.toGBK(request.getParameter(
						"jtcy3_szdw").toString());
				String jtcy3_ysr = DealString.toGBK(request.getParameter(
						"jtcy3_ysr").toString());
				String jtcy4_xm = DealString.toGBK(request.getParameter(
						"jtcy4_xm").toString());
				String jtcy4_nl = DealString.toGBK(request.getParameter(
						"jtcy4_nl").toString());
				String jtcy4_cw = DealString.toGBK(request.getParameter(
						"jtcy4_cw").toString());
				String jtcy4_szdw = DealString.toGBK(request.getParameter(
						"jtcy4_szdw").toString());
				String jtcy4_ysr = DealString.toGBK(request.getParameter(
						"jtcy4_ysr").toString());
				String jtcy5_xm = DealString.toGBK(request.getParameter(
						"jtcy5_xm").toString());
				String jtcy5_nl = DealString.toGBK(request.getParameter(
						"jtcy5_nl").toString());
				String jtcy5_cw = DealString.toGBK(request.getParameter(
						"jtcy5_cw").toString());
				String jtcy5_szdw = DealString.toGBK(request.getParameter(
						"jtcy5_szdw").toString());
				String jtcy5_ysr = DealString.toGBK(request.getParameter(
						"jtcy5_ysr").toString());
				String qfqk_xf = DealString.toGBK(request.getParameter(
						"qfqk_xf").toString());
				String qfqk_zsf = DealString.toGBK(request.getParameter(
						"qfqk_zsf").toString());
				String qfqk_jcf = DealString.toGBK(request.getParameter(
						"qfqk_jcf").toString());
				String qfqk_hj = DealString.toGBK(request.getParameter(
						"qfqk_hj").toString());
				String jfjh = DealString.toGBK(
						request.getParameter("jfjh").toString());
				String fmszdwdh = DealString.toGBK(request.getParameter(
						"fmszdwdh").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "jthk",
						"jtzrks", "nzsr", "jtzz", "yzbm", "lxdh",
						"jtjjknzyyy", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
						"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
						"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
						"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
						"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
						"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
						"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
						"jtcy5_szdw", "jtcy5_ysr", "qfqk_xf",
						"qfqk_zsf", "qfqk_jcf", "qfqk_hj", "jfjh",
						"fmszdwdh" };

				valueForOut = new String[] { xh, nd, jthk, jtzrks,
						nzsr, jtzz, yzbm, lxdh, jtjjknzyyy, jtcy1_xm,
						jtcy1_nl, jtcy1_cw, jtcy1_szdw, jtcy1_ysr,
						jtcy2_xm, jtcy2_nl, jtcy2_cw, jtcy2_szdw,
						jtcy2_ysr, jtcy3_xm, jtcy3_nl, jtcy3_cw,
						jtcy3_szdw, jtcy3_ysr, jtcy4_xm, jtcy4_nl,
						jtcy4_cw, jtcy4_szdw, jtcy4_ysr, jtcy5_xm,
						jtcy5_nl, jtcy5_cw, jtcy5_szdw, jtcy5_ysr,
						qfqk_xf, qfqk_zsf, qfqk_jcf, qfqk_hj, jfjh,
						fmszdwdh };
				
				sql = "select xxsh from zzsf_lstd where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_lstd", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "nd", "jthk",
								"jtzrks", "nzsr", "jtzz", "yzbm", "lxdh",
								"jtjjknzyyy", "jtcy1_xm", "jtcy1_nl",
								"jtcy1_cw", "jtcy1_szdw", "jtcy1_ysr",
								"jtcy2_xm", "jtcy2_nl", "jtcy2_cw",
								"jtcy2_szdw", "jtcy2_ysr", "jtcy3_xm",
								"jtcy3_nl", "jtcy3_cw", "jtcy3_szdw",
								"jtcy3_ysr", "jtcy4_xm", "jtcy4_nl",
								"jtcy4_cw", "jtcy4_szdw", "jtcy4_ysr",
								"jtcy5_xm", "jtcy5_nl", "jtcy5_cw",
								"jtcy5_szdw", "jtcy5_ysr", "qfqk_xf",
								"qfqk_zsf", "qfqk_jcf", "qfqk_hj", "jfjh",
								"fmszdwdh", "sqsj", "xysh", "xxsh",
								"xyshyj", "xxshyj", "zzdjdm", "xyzzfzryj",
								"xyshryhm" };

						valueForOut = new String[] { xh, nd, jthk, jtzrks,
								nzsr, jtzz, yzbm, lxdh, jtjjknzyyy,
								jtcy1_xm, jtcy1_nl, jtcy1_cw, jtcy1_szdw,
								jtcy1_ysr, jtcy2_xm, jtcy2_nl, jtcy2_cw,
								jtcy2_szdw, jtcy2_ysr, jtcy3_xm, jtcy3_nl,
								jtcy3_cw, jtcy3_szdw, jtcy3_ysr, jtcy4_xm,
								jtcy4_nl, jtcy4_cw, jtcy4_szdw, jtcy4_ysr,
								jtcy5_xm, jtcy5_nl, jtcy5_cw, jtcy5_szdw,
								jtcy5_ysr, qfqk_xf, qfqk_zsf, qfqk_jcf,
								qfqk_hj, jfjh, fmszdwdh, rightNow, "未审核",
								"未审核", "", "", "", "", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_lstd",
								colName1, valueForOut, "xh||nd", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
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

		sql = "select xh,nd,xm,xb,csrq,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtzrks,nzsr,jtzz,yzbm,lxdh,jtjjknzyyy,jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,qfqk_xf,qfqk_zsf,qfqk_jcf,qfqk_hj,jfjh,fmszdwdh,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj from view_zzsf_lstd "
				+ "where xh||nd=?";
		outString = new String[] { "xh","nd","xm","xb","csrq","mzmc","zzmmmc","xydm","xymc","zydm","zymc","bjdm","bjmc","jthk","jtzrks","nzsr","jtzz","yzbm","lxdh","jtjjknzyyy","jtcy1_xm","jtcy1_nl","jtcy1_cw","jtcy1_szdw","jtcy1_ysr","jtcy2_xm","jtcy2_nl","jtcy2_cw","jtcy2_szdw","jtcy2_ysr","jtcy3_xm","jtcy3_nl","jtcy3_cw","jtcy3_szdw","jtcy3_ysr","jtcy4_xm","jtcy4_nl","jtcy4_cw","jtcy4_szdw","jtcy4_ysr","jtcy5_xm","jtcy5_nl","jtcy5_cw","jtcy5_szdw","jtcy5_ysr","qfqk_xf","qfqk_zsf","qfqk_jcf","qfqk_hj","jfjh","fmszdwdh","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,b.csrq,b.mzmc,b.zzmmmc,(select j.rxny from bks_xsjbxx j where j.xh=a.xh) rxrq,a.sfzh,a.xymc,a.zymc,a.bjmc from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
					request.setAttribute("notKns", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "zzmmmc", "rxrq", "sfzh", "xymc", "zymc", "bjmc" };
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
			map.put("sqsj", rightNow);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_lstdb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String csrq = DealString.toGBK(request.getParameter("csrq").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String jthk = DealString.toGBK(request.getParameter("jthk").toString());
		String jtzrks = DealString.toGBK(request.getParameter("jtzrks").toString());
		String nzsr = DealString.toGBK(request.getParameter("nzsr").toString());
		String jtzz = DealString.toGBK(request.getParameter("jtzz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtjjknzyyy = DealString.toGBK(request.getParameter("jtjjknzyyy").toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm").toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl").toString());
		String jtcy1_cw = DealString.toGBK(request.getParameter("jtcy1_cw").toString());
		String jtcy1_szdw = DealString.toGBK(request.getParameter("jtcy1_szdw").toString());
		String jtcy1_ysr = DealString.toGBK(request.getParameter("jtcy1_ysr").toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm").toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl").toString());
		String jtcy2_cw = DealString.toGBK(request.getParameter("jtcy2_cw").toString());
		String jtcy2_szdw = DealString.toGBK(request.getParameter("jtcy2_szdw").toString());
		String jtcy2_ysr = DealString.toGBK(request.getParameter("jtcy2_ysr").toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm").toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl").toString());
		String jtcy3_cw = DealString.toGBK(request.getParameter("jtcy3_cw").toString());
		String jtcy3_szdw = DealString.toGBK(request.getParameter("jtcy3_szdw").toString());
		String jtcy3_ysr = DealString.toGBK(request.getParameter("jtcy3_ysr").toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm").toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl").toString());
		String jtcy4_cw = DealString.toGBK(request.getParameter("jtcy4_cw").toString());
		String jtcy4_szdw = DealString.toGBK(request.getParameter("jtcy4_szdw").toString());
		String jtcy4_ysr = DealString.toGBK(request.getParameter("jtcy4_ysr").toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm").toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl").toString());
		String jtcy5_cw = DealString.toGBK(request.getParameter("jtcy5_cw").toString());
		String jtcy5_szdw = DealString.toGBK(request.getParameter("jtcy5_szdw").toString());
		String jtcy5_ysr = DealString.toGBK(request.getParameter("jtcy5_ysr").toString());
		String qfqk_xf = DealString.toGBK(request.getParameter("qfqk_xf").toString());
		String qfqk_zsf = DealString.toGBK(request.getParameter("qfqk_zsf").toString());
		String qfqk_jcf = DealString.toGBK(request.getParameter("qfqk_jcf").toString());
		String qfqk_hj = DealString.toGBK(request.getParameter("qfqk_hj").toString());
		String jfjh = DealString.toGBK(request.getParameter("jfjh").toString());
		String fmszdwdh = DealString.toGBK(request.getParameter("fmszdwdh").toString());
		String xysh = DealString.toGBK(request.getParameter("xysh").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj").toString());
		String xxsh = DealString.toGBK(request.getParameter("xxsh").toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj").toString());
		String xyzzfzryj  = DealString.toGBK(request.getParameter("xyzzfzryj").toString());
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}

		String[] outValue = new String[] { xh,nd,xm,xb,csrq,mzmc,zzmmmc,xymc,zymc,bjmc,jthk,jtzrks,nzsr,jtzz,yzbm,lxdh,jtjjknzyyy,jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,qfqk_xf,qfqk_zsf,qfqk_jcf,qfqk_hj,jfjh,fmszdwdh,xysh,xyshyj,xxsh,xxshyj,xyzzfzryj };
		String[] outString = new String[] { "xh","nd","xm","xb","csrq","mzmc","zzmmmc","xymc","zymc","bjmc","jthk","jtzrks","nzsr","jtzz","yzbm","lxdh","jtjjknzyyy","jtcy1_xm","jtcy1_nl","jtcy1_cw","jtcy1_szdw","jtcy1_ysr","jtcy2_xm","jtcy2_nl","jtcy2_cw","jtcy2_szdw","jtcy2_ysr","jtcy3_xm","jtcy3_nl","jtcy3_cw","jtcy3_szdw","jtcy3_ysr","jtcy4_xm","jtcy4_nl","jtcy4_cw","jtcy4_szdw","jtcy4_ysr","jtcy5_xm","jtcy5_nl","jtcy5_cw","jtcy5_szdw","jtcy5_ysr","qfqk_xf","qfqk_zsf","qfqk_jcf","qfqk_hj","jfjh","fmszdwdh","xysh","xyshyj","xxsh","xxshyj","xyzzfzryj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_lstd(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_lstd";
		pk = "xh||nd";
		tableName = "view_zzsf_lstd";
		
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
//		String xn = colList[0];
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = colList[1];
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 绿色通道审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 绿色通道";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.nzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtjjknzyyy||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.qfqk_xf||'##OneSpile##'||a.qfqk_zsf||'##OneSpile##'||a.qfqk_jcf||'##OneSpile##'||a.qfqk_hj||'##OneSpile##'||a.jfjh||'##OneSpile##'||a.fmszdwdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.nzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtjjknzyyy||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.qfqk_xf||'##OneSpile##'||a.qfqk_zsf||'##OneSpile##'||a.qfqk_jcf||'##OneSpile##'||a.qfqk_hj||'##OneSpile##'||a.jfjh||'##OneSpile##'||a.fmszdwdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
					"zymc", "bjmc", "zzdjmc", "zzdjje", "xyshryhm", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.nzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtjjknzyyy||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.qfqk_xf||'##OneSpile##'||a.qfqk_zsf||'##OneSpile##'||a.qfqk_jcf||'##OneSpile##'||a.qfqk_hj||'##OneSpile##'||a.jfjh||'##OneSpile##'||a.fmszdwdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jthk||'##OneSpile##'||a.jtzrks||'##OneSpile##'||a.nzsr||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtjjknzyyy||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_szdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_szdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_szdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_szdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_szdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.qfqk_xf||'##OneSpile##'||a.qfqk_zsf||'##OneSpile##'||a.qfqk_jcf||'##OneSpile##'||a.qfqk_hj||'##OneSpile##'||a.jfjh||'##OneSpile##'||a.fmszdwdh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.zzdjdm||'##OneSpile##'||a.zzdjmc||'##OneSpile##'||a.zzdjje||'##OneSpile##'||a.xyshryhm||'##OneSpile##'||a.xyzzfzryj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##mzmc##OneSpile##zzmmmc##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##jthk##OneSpile##jtzrks##OneSpile##nzsr##OneSpile##jtzz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtjjknzyyy##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_cw##OneSpile##jtcy1_szdw##OneSpile##jtcy1_ysr##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_cw##OneSpile##jtcy2_szdw##OneSpile##jtcy2_ysr##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_cw##OneSpile##jtcy3_szdw##OneSpile##jtcy3_ysr##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_cw##OneSpile##jtcy4_szdw##OneSpile##jtcy4_ysr##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_cw##OneSpile##jtcy5_szdw##OneSpile##jtcy5_ysr##OneSpile##qfqk_xf##OneSpile##qfqk_zsf##OneSpile##qfqk_jcf##OneSpile##qfqk_hj##OneSpile##jfjh##OneSpile##fmszdwdh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyzzfzryj";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("act", "zzsf_lstd");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_lstd_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshryhm = session.getAttribute("userName").toString();

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

		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_lstd where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_lstd where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_lstd.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_lstd set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_lstd set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_lstd.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_lstd", new String[] {
						"xysh", "xyshyj", "zzdjdm", "xyshryhm", "xyzzfzryj" },
						new String[] { yesNo, xyshyj, zzdjdm, xyshryhm,
								xyzzfzryj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_lstd", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_lstd";
				Base.log(request, logMsg, sUName);
			}
		}

		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);

		realTable = "zzsf_lstd";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtzrks,nzsr,jtzz,yzbm,lxdh,jtjjknzyyy,jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,qfqk_xf,qfqk_zsf,qfqk_jcf,qfqk_hj,jfjh,fmszdwdh,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XYSH yesNo from "
					+ "view_zzsf_lstd where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtzrks,nzsr,jtzz,yzbm,lxdh,jtjjknzyyy,jtcy1_xm,jtcy1_nl,jtcy1_cw,jtcy1_szdw,jtcy1_ysr,jtcy2_xm,jtcy2_nl,jtcy2_cw,jtcy2_szdw,jtcy2_ysr,jtcy3_xm,jtcy3_nl,jtcy3_cw,jtcy3_szdw,jtcy3_ysr,jtcy4_xm,jtcy4_nl,jtcy4_cw,jtcy4_szdw,jtcy4_ysr,jtcy5_xm,jtcy5_nl,jtcy5_cw,jtcy5_szdw,jtcy5_ysr,qfqk_xf,qfqk_zsf,qfqk_jcf,qfqk_hj,jfjh,fmszdwdh,sqsj,xysh,xyshyj,xxsh,xxshyj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyzzfzryj,XXSH yesNo from "
					+ "view_zzsf_lstd where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh","nd","xm","xb","csrq","mzmc","zzmmmc","xydm","xymc","zydm","zymc","bjdm","bjmc","jthk","jtzrks","nzsr","jtzz","yzbm","lxdh","jtjjknzyyy","jtcy1_xm","jtcy1_nl","jtcy1_cw","jtcy1_szdw","jtcy1_ysr","jtcy2_xm","jtcy2_nl","jtcy2_cw","jtcy2_szdw","jtcy2_ysr","jtcy3_xm","jtcy3_nl","jtcy3_cw","jtcy3_szdw","jtcy3_ysr","jtcy4_xm","jtcy4_nl","jtcy4_cw","jtcy4_szdw","jtcy4_ysr","jtcy5_xm","jtcy5_nl","jtcy5_cw","jtcy5_szdw","jtcy5_ysr","qfqk_xf","qfqk_zsf","qfqk_jcf","qfqk_hj","jfjh","fmszdwdh","sqsj","xysh","xyshyj","xxsh","xxshyj","zzdjdm","zzdjmc","zzdjje","xyshryhm","xyzzfzryj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				zzdjdm = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_lstd");
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_gjzxdk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
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

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学贷款' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String jg = DealString.toGBK(request.getParameter("jg")
							.toString());
					String hjszd = DealString.toGBK(request.getParameter(
							"hjszd").toString());
					String jkzk = DealString.toGBK(request.getParameter("jkzk")
							.toString());
					String hyzk = DealString.toGBK(request.getParameter("hyzk")
							.toString());
					String zgxl = DealString.toGBK(request.getParameter("zgxl")
							.toString());
					String jtdh = DealString.toGBK(request.getParameter("jtdh")
							.toString());
					String yddh = DealString.toGBK(request.getParameter("yddh")
							.toString());
					String jtdz = DealString.toGBK(request.getParameter("jtdz")
							.toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String e_mail = DealString.toGBK(request.getParameter(
							"e_mail").toString());
					String byny = DealString.toGBK(request.getParameter("byny")
							.toString());
					String jzr1_xm = DealString.toGBK(request.getParameter(
							"jzr1_xm").toString());
					String jzr1_xb = DealString.toGBK(request.getParameter(
							"jzr1_xb").toString());
					String jzr1_zjlxjhm = DealString.toGBK(request
							.getParameter("jzr1_zjlxjhm").toString());
					String jzr1_yjkrgx = DealString.toGBK(request.getParameter(
							"jzr1_yjkrgx").toString());
					String jzr1_lxdh = DealString.toGBK(request.getParameter(
							"jzr1_lxdh").toString());
					String jzr1_txdz = DealString.toGBK(request.getParameter(
							"jzr1_txdz").toString());
					String jzr2_xm = DealString.toGBK(request.getParameter(
							"jzr2_xm").toString());
					String jzr2_xb = DealString.toGBK(request.getParameter(
							"jzr2_xb").toString());
					String jzr2_zjlxjhm = DealString.toGBK(request
							.getParameter("jzr2_zjlxjhm").toString());
					String jzr2_yjkrgx = DealString.toGBK(request.getParameter(
							"jzr2_yjkrgx").toString());
					String jzr2_lxdh = DealString.toGBK(request.getParameter(
							"jzr2_lxdh").toString());
					String jzr2_txdz = DealString.toGBK(request.getParameter(
							"jzr2_txdz").toString());
					String jkzje = DealString.toGBK(request.getParameter(
							"jkzje").toString());
					String dkqx = DealString.toGBK(request.getParameter("dkqx")
							.toString());
					String hkfs = DealString.toGBK(request.getParameter("hkfs")
							.toString());
					String dkzl = DealString.toGBK(request.getParameter("dkzl")
							.toString());
					String xfdj = DealString.toGBK(request.getParameter("xfdj")
							.toString());
					String zsfdj = DealString.toGBK(request.getParameter(
							"zsfdj").toString());
					String shffs = DealString.toGBK(request.getParameter(
							"shffs").toString());
					String shfdj = DealString.toGBK(request.getParameter(
							"shfdj").toString());
					String skrzhlxjzh = DealString.toGBK(request.getParameter(
							"skrzhlxjzh").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "jg",
							"hjszd", "jkzk", "hyzk", "zgxl", "jtdh",
							"yddh", "jtdz", "yzbm", "e_mail", "byny",
							"jzr1_xm", "jzr1_xb", "jzr1_zjlxjhm",
							"jzr1_yjkrgx", "jzr1_lxdh", "jzr1_txdz",
							"jzr2_xm", "jzr2_xb", "jzr2_zjlxjhm",
							"jzr2_yjkrgx", "jzr2_lxdh", "jzr2_txdz",
							"jkzje", "dkqx", "hkfs", "dkzl", "xfdj",
							"zsfdj", "shffs", "shfdj", "skrzhlxjzh" };

					valueForOut = new String[] { xh, nd, jg, hjszd, jkzk,
							hyzk, zgxl, jtdh, yddh, jtdz, yzbm, e_mail,
							byny, jzr1_xm, jzr1_xb, jzr1_zjlxjhm,
							jzr1_yjkrgx, jzr1_lxdh, jzr1_txdz, jzr2_xm,
							jzr2_xb, jzr2_zjlxjhm, jzr2_yjkrgx, jzr2_lxdh,
							jzr2_txdz, jkzje, dkqx, hkfs, dkzl, xfdj,
							zsfdj, shffs, shfdj, skrzhlxjzh };
					
					sql = "select xxsh from zzsf_gjzxdk where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_gjzxdk", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "nd", "jg",
									"hjszd", "jkzk", "hyzk", "zgxl", "jtdh",
									"yddh", "jtdz", "yzbm", "e_mail", "byny",
									"jzr1_xm", "jzr1_xb", "jzr1_zjlxjhm",
									"jzr1_yjkrgx", "jzr1_lxdh", "jzr1_txdz",
									"jzr2_xm", "jzr2_xb", "jzr2_zjlxjhm",
									"jzr2_yjkrgx", "jzr2_lxdh", "jzr2_txdz",
									"jkzje", "dkqx", "hkfs", "dkzl", "xfdj",
									"zsfdj", "shffs", "shfdj", "skrzhlxjzh",
									"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj",
									"dcr", "scr", "qpr" };

							valueForOut = new String[] { xh, nd, jg, hjszd,
									jkzk, hyzk, zgxl, jtdh, yddh, jtdz, yzbm,
									e_mail, byny, jzr1_xm, jzr1_xb,
									jzr1_zjlxjhm, jzr1_yjkrgx, jzr1_lxdh,
									jzr1_txdz, jzr2_xm, jzr2_xb, jzr2_zjlxjhm,
									jzr2_yjkrgx, jzr2_lxdh, jzr2_txdz, jkzje,
									dkqx, hkfs, dkzl, xfdj, zsfdj, shffs,
									shfdj, skrzhlxjzh, rightNow, "未审核", "",
									"未审核", "", "", "", "" };

							boolean ok = false;
							ok = StandardOperation.update("zzsf_gjzxdk",
									colName1, valueForOut, "xh||nd", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String jg = DealString.toGBK(request.getParameter("jg")
						.toString());
				String hjszd = DealString.toGBK(request.getParameter(
						"hjszd").toString());
				String jkzk = DealString.toGBK(request.getParameter("jkzk")
						.toString());
				String hyzk = DealString.toGBK(request.getParameter("hyzk")
						.toString());
				String zgxl = DealString.toGBK(request.getParameter("zgxl")
						.toString());
				String jtdh = DealString.toGBK(request.getParameter("jtdh")
						.toString());
				String yddh = DealString.toGBK(request.getParameter("yddh")
						.toString());
				String jtdz = DealString.toGBK(request.getParameter("jtdz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String e_mail = DealString.toGBK(request.getParameter(
						"e_mail").toString());
				String byny = DealString.toGBK(request.getParameter("byny")
						.toString());
				String jzr1_xm = DealString.toGBK(request.getParameter(
						"jzr1_xm").toString());
				String jzr1_xb = DealString.toGBK(request.getParameter(
						"jzr1_xb").toString());
				String jzr1_zjlxjhm = DealString.toGBK(request
						.getParameter("jzr1_zjlxjhm").toString());
				String jzr1_yjkrgx = DealString.toGBK(request.getParameter(
						"jzr1_yjkrgx").toString());
				String jzr1_lxdh = DealString.toGBK(request.getParameter(
						"jzr1_lxdh").toString());
				String jzr1_txdz = DealString.toGBK(request.getParameter(
						"jzr1_txdz").toString());
				String jzr2_xm = DealString.toGBK(request.getParameter(
						"jzr2_xm").toString());
				String jzr2_xb = DealString.toGBK(request.getParameter(
						"jzr2_xb").toString());
				String jzr2_zjlxjhm = DealString.toGBK(request
						.getParameter("jzr2_zjlxjhm").toString());
				String jzr2_yjkrgx = DealString.toGBK(request.getParameter(
						"jzr2_yjkrgx").toString());
				String jzr2_lxdh = DealString.toGBK(request.getParameter(
						"jzr2_lxdh").toString());
				String jzr2_txdz = DealString.toGBK(request.getParameter(
						"jzr2_txdz").toString());
				String jkzje = DealString.toGBK(request.getParameter(
						"jkzje").toString());
				String dkqx = DealString.toGBK(request.getParameter("dkqx")
						.toString());
				String hkfs = DealString.toGBK(request.getParameter("hkfs")
						.toString());
				String dkzl = DealString.toGBK(request.getParameter("dkzl")
						.toString());
				String xfdj = DealString.toGBK(request.getParameter("xfdj")
						.toString());
				String zsfdj = DealString.toGBK(request.getParameter(
						"zsfdj").toString());
				String shffs = DealString.toGBK(request.getParameter(
						"shffs").toString());
				String shfdj = DealString.toGBK(request.getParameter(
						"shfdj").toString());
				String skrzhlxjzh = DealString.toGBK(request.getParameter(
						"skrzhlxjzh").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "nd", "jg",
						"hjszd", "jkzk", "hyzk", "zgxl", "jtdh",
						"yddh", "jtdz", "yzbm", "e_mail", "byny",
						"jzr1_xm", "jzr1_xb", "jzr1_zjlxjhm",
						"jzr1_yjkrgx", "jzr1_lxdh", "jzr1_txdz",
						"jzr2_xm", "jzr2_xb", "jzr2_zjlxjhm",
						"jzr2_yjkrgx", "jzr2_lxdh", "jzr2_txdz",
						"jkzje", "dkqx", "hkfs", "dkzl", "xfdj",
						"zsfdj", "shffs", "shfdj", "skrzhlxjzh" };

				valueForOut = new String[] { xh, nd, jg, hjszd, jkzk,
						hyzk, zgxl, jtdh, yddh, jtdz, yzbm, e_mail,
						byny, jzr1_xm, jzr1_xb, jzr1_zjlxjhm,
						jzr1_yjkrgx, jzr1_lxdh, jzr1_txdz, jzr2_xm,
						jzr2_xb, jzr2_zjlxjhm, jzr2_yjkrgx, jzr2_lxdh,
						jzr2_txdz, jkzje, dkqx, hkfs, dkzl, xfdj,
						zsfdj, shffs, shfdj, skrzhlxjzh };
				
				sql = "select xxsh from zzsf_gjzxdk where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_gjzxdk", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "nd", "jg",
								"hjszd", "jkzk", "hyzk", "zgxl", "jtdh",
								"yddh", "jtdz", "yzbm", "e_mail", "byny",
								"jzr1_xm", "jzr1_xb", "jzr1_zjlxjhm",
								"jzr1_yjkrgx", "jzr1_lxdh", "jzr1_txdz",
								"jzr2_xm", "jzr2_xb", "jzr2_zjlxjhm",
								"jzr2_yjkrgx", "jzr2_lxdh", "jzr2_txdz",
								"jkzje", "dkqx", "hkfs", "dkzl", "xfdj",
								"zsfdj", "shffs", "shfdj", "skrzhlxjzh",
								"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj",
								"dcr", "scr", "qpr" };

						valueForOut = new String[] { xh, nd, jg, hjszd,
								jkzk, hyzk, zgxl, jtdh, yddh, jtdz, yzbm,
								e_mail, byny, jzr1_xm, jzr1_xb,
								jzr1_zjlxjhm, jzr1_yjkrgx, jzr1_lxdh,
								jzr1_txdz, jzr2_xm, jzr2_xb, jzr2_zjlxjhm,
								jzr2_yjkrgx, jzr2_lxdh, jzr2_txdz, jkzje,
								dkqx, hkfs, dkzl, xfdj, zsfdj, shffs,
								shfdj, skrzhlxjzh, rightNow, "未审核", "",
								"未审核", "", "", "", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_gjzxdk",
								colName1, valueForOut, "xh||nd", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
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

		sql = "select xh,nd,xm,xb,csrq,sfzh,mzmc,jg,hjszd,jkzk,hyzk,zgxl,jtdh,yddh,jtdz,yzbm,e_mail,rxny,byny,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jzr1_xm,jzr1_xb,jzr1_zjlxjhm,jzr1_yjkrgx,jzr1_lxdh,jzr1_txdz,jzr2_xm,jzr2_xb,jzr2_zjlxjhm,jzr2_yjkrgx,jzr2_lxdh,jzr2_txdz,jkzje,dkqx,hkfs,dkzl,xfdj,zsfdj,shffs,shfdj,skrzhlxjzh,sqsj,xysh,xyshyj,xxsh,xxshyj from view_zzsf_gjzxdk "
				+ "where xh||nd=?";
		outString = new String[] { "xh","nd","xm","xb","csrq","sfzh","mzmc","jg","hjszd","jkzk","hyzk","zgxl","jtdh","yddh","jtdz","yzbm","e_mail","rxny","byny","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jzr1_xm","jzr1_xb","jzr1_zjlxjhm","jzr1_yjkrgx","jzr1_lxdh","jzr1_txdz","jzr2_xm","jzr2_xb","jzr2_zjlxjhm","jzr2_yjkrgx","jzr2_lxdh","jzr2_txdz","jkzje","dkqx","hkfs","dkzl","xfdj","zsfdj","shffs","shfdj","skrzhlxjzh","sqsj","xysh","xyshyj","xxsh","xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,NVL(b.csrq,'') csrq,b.mzmc,a.sfzh,NVL((select j.rxny from bks_xsjbxx j where j.xh=a.xh),'') rxny,a.xymc,a.zymc,a.bjmc,NVL(a.xz,'0') xz,a.nj from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "sfzh", "rxny", "xymc", "zymc", "bjmc", "xz", "nj" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					String rxny = outVal[6];
					String xz = outVal[10];
					String csrq = outVal[3];
					String byny = "";
					String st1,st2,st3;
					if((null != rxny) && (rxny.length() == 6)){
						st1 = rxny.substring(0, 4);
						st2 = rxny.substring(4);
						byny = String.valueOf((Integer.parseInt(st1) + Integer.parseInt(xz))) + "年7月";
						rxny = st1 + "年" + st2 + "月";
					}
					if((null != csrq) && (csrq.length() == 8)){
						st1 = csrq.substring(0,4);
						st2 = csrq.substring(4, 6);
						st3 = csrq.substring(6);
						csrq = st1 + "年" + st2 + "月" + st3 + "日";
					}
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "sfzh", "rxny", "xymc", "zymc", "bjmc", "xz", "nj" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
					map.put("rxny", rxny);
					map.put("byny", byny);
					map.put("csrq", csrq);
				}
			}
		} else {
			map.put("sqsj", rightNow);
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
				} else {
					map.put(outString[i], "");
				}
			}
		}
		if (null != xh && dao.isKns(xh)) {
			request.setAttribute("isKNS", "is");
		} else {
			request.setAttribute("isKNS", "no");
		}
		List hyzkList = xszzDao.getXszzHyzkList();
		List zgxlList = xszzDao.getXszzZgxlList();
		request.setAttribute("hyzkList", hyzkList);
		request.setAttribute("zgxlList", zgxlList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward zzsf_gjzxdkb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String csrq = DealString.toGBK(request.getParameter("csrq").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String jg = DealString.toGBK(request.getParameter("jg").toString());
		String hjszd = DealString.toGBK(request.getParameter("hjszd").toString());
		String jkzk = DealString.toGBK(request.getParameter("jkzk").toString());
		String hyzk = DealString.toGBK(request.getParameter("hyzk").toString());
		String zgxl = DealString.toGBK(request.getParameter("zgxl").toString());
		String jtdh = DealString.toGBK(request.getParameter("jtdh").toString());
		String yddh = DealString.toGBK(request.getParameter("yddh").toString());
		String jtdz = DealString.toGBK(request.getParameter("jtdz").toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String e_mail = DealString.toGBK(request.getParameter("e_mail").toString());
		String rxny = DealString.toGBK(request.getParameter("rxny").toString());
		String byny = DealString.toGBK(request.getParameter("byny").toString());
		String xz = DealString.toGBK(request.getParameter("xz").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String jzr1_xm = DealString.toGBK(request.getParameter("jzr1_xm").toString());
		String jzr1_xb = DealString.toGBK(request.getParameter("jzr1_xb").toString());
		String jzr1_zjlxjhm = DealString.toGBK(request.getParameter("jzr1_zjlxjhm").toString());
		String jzr1_yjkrgx = DealString.toGBK(request.getParameter("jzr1_yjkrgx").toString());
		String jzr1_lxdh = DealString.toGBK(request.getParameter("jzr1_lxdh").toString());
		String jzr1_txdz = DealString.toGBK(request.getParameter("jzr1_txdz").toString());
		String jzr2_xm = DealString.toGBK(request.getParameter("jzr2_xm").toString());
		String jzr2_xb = DealString.toGBK(request.getParameter("jzr2_xb").toString());
		String jzr2_zjlxjhm = DealString.toGBK(request.getParameter("jzr2_zjlxjhm").toString());
		String jzr2_yjkrgx = DealString.toGBK(request.getParameter("jzr2_yjkrgx").toString());
		String jzr2_lxdh = DealString.toGBK(request.getParameter("jzr2_lxdh").toString());
		String jzr2_txdz = DealString.toGBK(request.getParameter("jzr2_txdz").toString());
		String jkzje = DealString.toGBK(request.getParameter("jkzje").toString());
		String dkqx = DealString.toGBK(request.getParameter("dkqx").toString());
		String hkfs = DealString.toGBK(request.getParameter("hkfs").toString());
		String dkzl = DealString.toGBK(request.getParameter("dkzl").toString());
		String xfdj = DealString.toGBK(request.getParameter("xfdj").toString());
		String zsfdj = DealString.toGBK(request.getParameter("zsfdj").toString());
		String shffs = DealString.toGBK(request.getParameter("shffs").toString());
		String shfdj = DealString.toGBK(request.getParameter("shfdj").toString());
		String skrzhlxjzh = DealString.toGBK(request.getParameter("skrzhlxjzh").toString());
		String[] sfzhL = new String[] { "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "" };
		for (int i = 0; i < sfzh.length(); i++){
			sfzhL[i] = sfzh.substring(i,i+1);
		}
		for (int i = 1; i <= 18; i++){
			map.put("sfzh" + i, sfzhL[i-1]);
		}
		String st1,st2,st3;
		if((null != rxny) && (rxny.length() == 6)){
			st1 = rxny.substring(0, 4);
			st2 = rxny.substring(4);
			rxny = st1 + "年" + st2 + "月";
		} else if(rxny.length() >= 7){
			st1 = rxny.substring(0, 4);
			st2 = rxny.substring(5, 7);
			rxny = st1 + "年" + st2 + "月";
		}
		if((null != byny) && (byny.length() == 6)){
			st1 = byny.substring(0, 4);
			st2 = byny.substring(4);
			byny = st1 + "年" + st2 + "月";
		} else if(byny.length() >= 7){
			st1 = byny.substring(0, 4);
			st2 = byny.substring(5, 7);
			byny = st1 + "年" + st2 + "月";
		}
		if((null != csrq) && (csrq.length() == 8)){
			st1 = csrq.substring(0,4);
			st2 = csrq.substring(4, 6);
			st3 = csrq.substring(6);
			csrq = st1 + "年" + st2 + "月" + st3 + "日";
		} else if(csrq.length() == 10){
			st1 = csrq.substring(0,4);
			st2 = csrq.substring(5, 7);
			st3 = csrq.substring(9);
			csrq = st1 + "年" + st2 + "月" + st3 + "日";
		}

		String[] outValue = new String[] { xh,xm,xb,csrq,sfzh,mzmc,jg,hjszd,jkzk,hyzk,zgxl,jtdh,yddh,jtdz,yzbm,e_mail,rxny,byny,xz,nj,xymc,zymc,bjmc,jzr1_xm,jzr1_xb,jzr1_zjlxjhm,jzr1_yjkrgx,jzr1_lxdh,jzr1_txdz,jzr2_xm,jzr2_xb,jzr2_zjlxjhm,jzr2_yjkrgx,jzr2_lxdh,jzr2_txdz,jkzje,dkqx,hkfs,dkzl,xfdj,zsfdj,shffs,shfdj,skrzhlxjzh };
		String[] outString = new String[] { "xh","xm","xb","csrq","sfzh","mzmc","jg","hjszd","jkzk","hyzk","zgxl","jtdh","yddh","jtdz","yzbm","e_mail","rxny","byny","xz","nj","xymc","zymc","bjmc","jzr1_xm","jzr1_xb","jzr1_zjlxjhm","jzr1_yjkrgx","jzr1_lxdh","jzr1_txdz","jzr2_xm","jzr2_xb","jzr2_zjlxjhm","jzr2_yjkrgx","jzr2_lxdh","jzr2_txdz","jkzje","dkqx","hkfs","dkzl","xfdj","zsfdj","shffs","shfdj","skrzhlxjzh" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		outValue = new String[] {jkzk,hyzk,zgxl,hkfs,dkzl,shffs};
		outString = new String[] { "jkzk","hyzk","zgxl","hkfs","dkzl","shffs" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				request.setAttribute(outString[i], outValue[i]);
			} else
				request.setAttribute(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjzxdk(ActionMapping mapping,
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
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_gjzxdk";
		pk = "xh||nd";
		tableName = "view_zzsf_gjzxdk";
		
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
//		String xn = colList[0];
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = colList[1];
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
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 国家助学贷款审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学贷款";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
					"zymc", "bjmc", "jkzje", "dkqx", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jg||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.jkzk||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.zgxl||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.yddh||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.e_mail||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.xz||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jzr1_xm||'##OneSpile##'||a.jzr1_xb||'##OneSpile##'||a.jzr1_zjlxjhm||'##OneSpile##'||a.jzr1_yjkrgx||'##OneSpile##'||a.jzr1_lxdh||'##OneSpile##'||a.jzr1_txdz||'##OneSpile##'||a.jzr2_xm||'##OneSpile##'||a.jzr2_xb||'##OneSpile##'||a.jzr2_zjlxjhm||'##OneSpile##'||a.jzr2_yjkrgx||'##OneSpile##'||a.jzr2_lxdh||'##OneSpile##'||a.jzr2_txdz||'##OneSpile##'||a.jkzje||'##OneSpile##'||a.dkqx||'##OneSpile##'||a.hkfs||'##OneSpile##'||a.dkzl||'##OneSpile##'||a.xfdj||'##OneSpile##'||a.zsfdj||'##OneSpile##'||a.shffs||'##OneSpile##'||a.shfdj||'##OneSpile##'||a.skrzhlxjzh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.dcr||'##OneSpile##'||a.scr||'##OneSpile##'||a.qpr col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jg||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.jkzk||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.zgxl||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.yddh||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.e_mail||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.xz||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jzr1_xm||'##OneSpile##'||a.jzr1_xb||'##OneSpile##'||a.jzr1_zjlxjhm||'##OneSpile##'||a.jzr1_yjkrgx||'##OneSpile##'||a.jzr1_lxdh||'##OneSpile##'||a.jzr1_txdz||'##OneSpile##'||a.jzr2_xm||'##OneSpile##'||a.jzr2_xb||'##OneSpile##'||a.jzr2_zjlxjhm||'##OneSpile##'||a.jzr2_yjkrgx||'##OneSpile##'||a.jzr2_lxdh||'##OneSpile##'||a.jzr2_txdz||'##OneSpile##'||a.jkzje||'##OneSpile##'||a.dkqx||'##OneSpile##'||a.hkfs||'##OneSpile##'||a.dkzl||'##OneSpile##'||a.xfdj||'##OneSpile##'||a.zsfdj||'##OneSpile##'||a.shffs||'##OneSpile##'||a.shfdj||'##OneSpile##'||a.skrzhlxjzh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.dcr||'##OneSpile##'||a.scr||'##OneSpile##'||a.qpr col from "
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
					"zymc", "bjmc", "jkzje", "dkqx", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jg||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.jkzk||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.zgxl||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.yddh||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.e_mail||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.xz||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jzr1_xm||'##OneSpile##'||a.jzr1_xb||'##OneSpile##'||a.jzr1_zjlxjhm||'##OneSpile##'||a.jzr1_yjkrgx||'##OneSpile##'||a.jzr1_lxdh||'##OneSpile##'||a.jzr1_txdz||'##OneSpile##'||a.jzr2_xm||'##OneSpile##'||a.jzr2_xb||'##OneSpile##'||a.jzr2_zjlxjhm||'##OneSpile##'||a.jzr2_yjkrgx||'##OneSpile##'||a.jzr2_lxdh||'##OneSpile##'||a.jzr2_txdz||'##OneSpile##'||a.jkzje||'##OneSpile##'||a.dkqx||'##OneSpile##'||a.hkfs||'##OneSpile##'||a.dkzl||'##OneSpile##'||a.xfdj||'##OneSpile##'||a.zsfdj||'##OneSpile##'||a.shffs||'##OneSpile##'||a.shfdj||'##OneSpile##'||a.skrzhlxjzh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.dcr||'##OneSpile##'||a.scr||'##OneSpile##'||a.qpr col from "
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jg||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.jkzk||'##OneSpile##'||a.hyzk||'##OneSpile##'||a.zgxl||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.yddh||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.e_mail||'##OneSpile##'||a.rxny||'##OneSpile##'||a.byny||'##OneSpile##'||a.xz||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jzr1_xm||'##OneSpile##'||a.jzr1_xb||'##OneSpile##'||a.jzr1_zjlxjhm||'##OneSpile##'||a.jzr1_yjkrgx||'##OneSpile##'||a.jzr1_lxdh||'##OneSpile##'||a.jzr1_txdz||'##OneSpile##'||a.jzr2_xm||'##OneSpile##'||a.jzr2_xb||'##OneSpile##'||a.jzr2_zjlxjhm||'##OneSpile##'||a.jzr2_yjkrgx||'##OneSpile##'||a.jzr2_lxdh||'##OneSpile##'||a.jzr2_txdz||'##OneSpile##'||a.jkzje||'##OneSpile##'||a.dkqx||'##OneSpile##'||a.hkfs||'##OneSpile##'||a.dkzl||'##OneSpile##'||a.xfdj||'##OneSpile##'||a.zsfdj||'##OneSpile##'||a.shffs||'##OneSpile##'||a.shfdj||'##OneSpile##'||a.skrzhlxjzh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.dcr||'##OneSpile##'||a.scr||'##OneSpile##'||a.qpr col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##sfzh##OneSpile##mzmc##OneSpile##jg##OneSpile##hjszd##OneSpile##jkzk##OneSpile##hyzk##OneSpile##zgxl##OneSpile##jtdh##OneSpile##yddh##OneSpile##jtdz##OneSpile##yzbm##OneSpile##e_mail##OneSpile##rxny##OneSpile##byny##OneSpile##xz##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##jzr1_xm##OneSpile##jzr1_xb##OneSpile##jzr1_zjlxjhm##OneSpile##jzr1_yjkrgx##OneSpile##jzr1_lxdh##OneSpile##jzr1_txdz##OneSpile##jzr2_xm##OneSpile##jzr2_xb##OneSpile##jzr2_zjlxjhm##OneSpile##jzr2_yjkrgx##OneSpile##jzr2_lxdh##OneSpile##jzr2_txdz##OneSpile##jkzje##OneSpile##dkqx##OneSpile##hkfs##OneSpile##dkzl##OneSpile##xfdj##OneSpile##zsfdj##OneSpile##shffs##OneSpile##shfdj##OneSpile##skrzhlxjzh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##dcr##OneSpile##scr##OneSpile##qpr";
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
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("act", "zzsf_gjzxdk");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_zzsf_gjzxdk_one(ActionMapping mapping,
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
		String dcr = DealString.toGBK(request.getParameter("dcr"));
		String scr = DealString.toGBK(request.getParameter("scr"));
		String qpr = DealString.toGBK(request.getParameter("qpr"));
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
					sqlT[i] = "delete zzsf_gjzxdk where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_gjzxdk where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjzxdk.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_gjzxdk set xysh='"+jg+"' where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_gjzxdk set xxsh='"+jg+"' where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_gjzxdk.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("zzsf_gjzxdk", new String[] {
						"xysh", "xyshyj" },
						new String[] { yesNo, xyshyj }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("zzsf_gjzxdk", new String[] {
						"xxsh", "dcr", "scr", "qpr", "xxshyj" }, new String[] { yesNo, dcr, scr, qpr, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) zzsf_gjzxdk";
				Base.log(request, logMsg, sUName);
			}
		}

		realTable = "zzsf_gjzxdk";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,sfzh,mzmc,jg,hjszd,jkzk,hyzk,zgxl,jtdh,yddh,jtdz,yzbm,e_mail,rxny,byny,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jzr1_xm,jzr1_xb,jzr1_zjlxjhm,jzr1_yjkrgx,jzr1_lxdh,jzr1_txdz,jzr2_xm,jzr2_xb,jzr2_zjlxjhm,jzr2_yjkrgx,jzr2_lxdh,jzr2_txdz,jkzje,dkqx,hkfs,dkzl,xfdj,zsfdj,shffs,shfdj,skrzhlxjzh,sqsj,xysh,xyshyj,xxsh,xxshyj,dcr,scr,qpr,XYSH yesNo from "
					+ "view_zzsf_gjzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,csrq,sfzh,mzmc,jg,hjszd,jkzk,hyzk,zgxl,jtdh,yddh,jtdz,yzbm,e_mail,rxny,byny,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jzr1_xm,jzr1_xb,jzr1_zjlxjhm,jzr1_yjkrgx,jzr1_lxdh,jzr1_txdz,jzr2_xm,jzr2_xb,jzr2_zjlxjhm,jzr2_yjkrgx,jzr2_lxdh,jzr2_txdz,jkzje,dkqx,hkfs,dkzl,xfdj,zsfdj,shffs,shfdj,skrzhlxjzh,sqsj,xysh,xyshyj,xxsh,xxshyj,dcr,scr,qpr,XXSH yesNo from "
					+ "view_zzsf_gjzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh","nd","xm","xb","csrq","sfzh","mzmc","jg","hjszd","jkzk","hyzk","zgxl","jtdh","yddh","jtdz","yzbm","e_mail","rxny","byny","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jzr1_xm","jzr1_xb","jzr1_zjlxjhm","jzr1_yjkrgx","jzr1_lxdh","jzr1_txdz","jzr2_xm","jzr2_xb","jzr2_zjlxjhm","jzr2_yjkrgx","jzr2_lxdh","jzr2_txdz","jkzje","dkqx","hkfs","dkzl","xfdj","zsfdj","shffs","shfdj","skrzhlxjzh","sqsj","xysh","xyshyj","xxsh","xxshyj","dcr","scr","qpr", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		if ("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "zzsf_gjzxdk");
		return mapping.findForward("success");
	}

	private ActionForward zzsf_sydxyzxdk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate");
		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xn,xh,xm,xb,nj,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,jtxxdz,jtdh,hjszd,yzbm,yddh,gtjkr_xm,gtjkr_gx,gtjkr_sfzh,gtjkr_gzdw,gtjkr_zw,gtjkr_jtxxdz,gtjkr_jtdh,gtjkr_hjszd,gtjkr_yzbm,gtjkr_yddh,knlx_dsr,knlx_cnh,knlx_sxg,knlx_dbh,knlx_zbh,knlx_wsr,knlx_lszn,knlx_gr,knlx_qt,xxyy,dknx,dkzje,fqffcsje,jbyh,sydxyzxdkhtbh,sqsj,xysh,xyshyj,xxsh,xxshyj "
				+ "from view_zzsf_sydxyzxdk where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String xn = Base.currXn;

		String sfksq = "-1";
		String pkVal = "";
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='生源地信用助学贷款' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String jtxxdz = DealString.toGBK(request
							.getParameter("jtxxdz"));
					String jtdh = DealString
							.toGBK(request.getParameter("jtdh"));
					String hjszd = DealString.toGBK(request
							.getParameter("hjszd"));
					String yzbm = DealString
							.toGBK(request.getParameter("yzbm"));
					String yddh = DealString
							.toGBK(request.getParameter("yddh"));
					String gtjkr_xm = DealString.toGBK(request
							.getParameter("gtjkr_xm"));
					String gtjkr_gx = DealString.toGBK(request
							.getParameter("gtjkr_gx"));
					String gtjkr_sfzh = DealString.toGBK(request
							.getParameter("gtjkr_sfzh"));
					String gtjkr_gzdw = DealString.toGBK(request
							.getParameter("gtjkr_gzdw"));
					String gtjkr_zw = DealString.toGBK(request
							.getParameter("gtjkr_zw"));
					String gtjkr_jtxxdz = DealString.toGBK(request
							.getParameter("gtjkr_jtxxdz"));
					String gtjkr_jtdh = DealString.toGBK(request
							.getParameter("gtjkr_jtdh"));
					String gtjkr_hjszd = DealString.toGBK(request
							.getParameter("gtjkr_hjszd"));
					String gtjkr_yzbm = DealString.toGBK(request
							.getParameter("gtjkr_yzbm"));
					String gtjkr_yddh = DealString.toGBK(request
							.getParameter("gtjkr_yddh"));
					String knlx_dsr = DealString.toGBK(request
							.getParameter("knlx_dsr"));
					String knlx_cnh = DealString.toGBK(request
							.getParameter("knlx_cnh"));
					String knlx_sxg = DealString.toGBK(request
							.getParameter("knlx_sxg"));
					String knlx_dbh = DealString.toGBK(request
							.getParameter("knlx_dbh"));
					String knlx_zbh = DealString.toGBK(request
							.getParameter("knlx_zbh"));
					String knlx_wsr = DealString.toGBK(request
							.getParameter("knlx_wsr"));
					String knlx_lszn = DealString.toGBK(request
							.getParameter("knlx_lszn"));
					String knlx_gr = DealString.toGBK(request
							.getParameter("knlx_gr"));
					String knlx_qt = DealString.toGBK(request
							.getParameter("knlx_qt"));
					String xxyy = DealString
							.toGBK(request.getParameter("xxyy"));
					String dknx = DealString
							.toGBK(request.getParameter("dknx"));
					String dkzje = DealString.toGBK(request
							.getParameter("dkzje"));
					String fqffcsje = DealString.toGBK(request
							.getParameter("fqffcsje"));
					String jbyh = DealString
							.toGBK(request.getParameter("jbyh"));
					String sydxyzxdkhtbh = DealString.toGBK(request
							.getParameter("sydxyzxdkhtbh"));
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + xn;
					}

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "xn",
							"jtxxdz", "jtdh", "hjszd", "yzbm", "yddh",
							"gtjkr_xm", "gtjkr_gx", "gtjkr_sfzh",
							"gtjkr_gzdw", "gtjkr_zw", "gtjkr_jtxxdz",
							"gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
							"gtjkr_yddh", "knlx_dsr", "knlx_cnh",
							"knlx_sxg", "knlx_dbh", "knlx_zbh", "knlx_wsr",
							"knlx_lszn", "knlx_gr", "knlx_qt", "xxyy",
							"dknx", "dkzje", "fqffcsje", "jbyh",
							"sydxyzxdkhtbh" };

					valueForOut = new String[] { xh, xn, jtxxdz, jtdh,
							hjszd, yzbm, yddh, gtjkr_xm, gtjkr_gx,
							gtjkr_sfzh, gtjkr_gzdw, gtjkr_zw, gtjkr_jtxxdz,
							gtjkr_jtdh, gtjkr_hjszd, gtjkr_yzbm,
							gtjkr_yddh, knlx_dsr, knlx_cnh, knlx_sxg,
							knlx_dbh, knlx_zbh, knlx_wsr, knlx_lszn,
							knlx_gr, knlx_qt, xxyy, dknx, dkzje, fqffcsje,
							jbyh, sydxyzxdkhtbh };
					
					sql = "select xxsh from zzsf_sydxyzxdk where xh||xn=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if (temp == null) {
						boolean ok = false;
						ok = StandardOperation.insert("zzsf_sydxyzxdk", colName1,
								valueForOut, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						if ("通过".equalsIgnoreCase(temp[0])) {
							request.setAttribute("isPASS", "is");
						} else {
							colName1 = new String[] { "xh", "xn", "jtxxdz",
									"jtdh", "hjszd", "yzbm", "yddh",
									"gtjkr_xm", "gtjkr_gx", "gtjkr_sfzh",
									"gtjkr_gzdw", "gtjkr_zw", "gtjkr_jtxxdz",
									"gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
									"gtjkr_yddh", "knlx_dsr", "knlx_cnh",
									"knlx_sxg", "knlx_dbh", "knlx_zbh",
									"knlx_wsr", "knlx_lszn", "knlx_gr",
									"knlx_qt", "xxyy", "dknx", "dkzje",
									"fqffcsje", "jbyh", "sydxyzxdkhtbh",
									"sqsj", "xysh", "xxsh", "xyshyj", "xxshyj" };

							valueForOut = new String[] { xh, xn, jtxxdz, jtdh,
									hjszd, yzbm, yddh, gtjkr_xm, gtjkr_gx,
									gtjkr_sfzh, gtjkr_gzdw, gtjkr_zw,
									gtjkr_jtxxdz, gtjkr_jtdh, gtjkr_hjszd,
									gtjkr_yzbm, gtjkr_yddh, knlx_dsr, knlx_cnh,
									knlx_sxg, knlx_dbh, knlx_zbh, knlx_wsr,
									knlx_lszn, knlx_gr, knlx_qt, xxyy, dknx,
									dkzje, fqffcsje, jbyh, sydxyzxdkhtbh,
									rightNow, "未审核", "未审核", "", "" };

							boolean ok = false;
							ok = StandardOperation.update("zzsf_sydxyzxdk",
									colName1, valueForOut, "xh||xn", pkVal,
									request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
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
				String jtxxdz = DealString.toGBK(request
						.getParameter("jtxxdz"));
				String jtdh = DealString
						.toGBK(request.getParameter("jtdh"));
				String hjszd = DealString.toGBK(request
						.getParameter("hjszd"));
				String yzbm = DealString
						.toGBK(request.getParameter("yzbm"));
				String yddh = DealString
						.toGBK(request.getParameter("yddh"));
				String gtjkr_xm = DealString.toGBK(request
						.getParameter("gtjkr_xm"));
				String gtjkr_gx = DealString.toGBK(request
						.getParameter("gtjkr_gx"));
				String gtjkr_sfzh = DealString.toGBK(request
						.getParameter("gtjkr_sfzh"));
				String gtjkr_gzdw = DealString.toGBK(request
						.getParameter("gtjkr_gzdw"));
				String gtjkr_zw = DealString.toGBK(request
						.getParameter("gtjkr_zw"));
				String gtjkr_jtxxdz = DealString.toGBK(request
						.getParameter("gtjkr_jtxxdz"));
				String gtjkr_jtdh = DealString.toGBK(request
						.getParameter("gtjkr_jtdh"));
				String gtjkr_hjszd = DealString.toGBK(request
						.getParameter("gtjkr_hjszd"));
				String gtjkr_yzbm = DealString.toGBK(request
						.getParameter("gtjkr_yzbm"));
				String gtjkr_yddh = DealString.toGBK(request
						.getParameter("gtjkr_yddh"));
				String knlx_dsr = DealString.toGBK(request
						.getParameter("knlx_dsr"));
				String knlx_cnh = DealString.toGBK(request
						.getParameter("knlx_cnh"));
				String knlx_sxg = DealString.toGBK(request
						.getParameter("knlx_sxg"));
				String knlx_dbh = DealString.toGBK(request
						.getParameter("knlx_dbh"));
				String knlx_zbh = DealString.toGBK(request
						.getParameter("knlx_zbh"));
				String knlx_wsr = DealString.toGBK(request
						.getParameter("knlx_wsr"));
				String knlx_lszn = DealString.toGBK(request
						.getParameter("knlx_lszn"));
				String knlx_gr = DealString.toGBK(request
						.getParameter("knlx_gr"));
				String knlx_qt = DealString.toGBK(request
						.getParameter("knlx_qt"));
				String xxyy = DealString
						.toGBK(request.getParameter("xxyy"));
				String dknx = DealString
						.toGBK(request.getParameter("dknx"));
				String dkzje = DealString.toGBK(request
						.getParameter("dkzje"));
				String fqffcsje = DealString.toGBK(request
						.getParameter("fqffcsje"));
				String jbyh = DealString
						.toGBK(request.getParameter("jbyh"));
				String sydxyzxdkhtbh = DealString.toGBK(request
						.getParameter("sydxyzxdkhtbh"));
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + xn;
				}

				String[] valueForOut = null;
				String[] colName1 = new String[] { "xh", "xn",
						"jtxxdz", "jtdh", "hjszd", "yzbm", "yddh",
						"gtjkr_xm", "gtjkr_gx", "gtjkr_sfzh",
						"gtjkr_gzdw", "gtjkr_zw", "gtjkr_jtxxdz",
						"gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
						"gtjkr_yddh", "knlx_dsr", "knlx_cnh",
						"knlx_sxg", "knlx_dbh", "knlx_zbh", "knlx_wsr",
						"knlx_lszn", "knlx_gr", "knlx_qt", "xxyy",
						"dknx", "dkzje", "fqffcsje", "jbyh",
						"sydxyzxdkhtbh" };

				valueForOut = new String[] { xh, xn, jtxxdz, jtdh,
						hjszd, yzbm, yddh, gtjkr_xm, gtjkr_gx,
						gtjkr_sfzh, gtjkr_gzdw, gtjkr_zw, gtjkr_jtxxdz,
						gtjkr_jtdh, gtjkr_hjszd, gtjkr_yzbm,
						gtjkr_yddh, knlx_dsr, knlx_cnh, knlx_sxg,
						knlx_dbh, knlx_zbh, knlx_wsr, knlx_lszn,
						knlx_gr, knlx_qt, xxyy, dknx, dkzje, fqffcsje,
						jbyh, sydxyzxdkhtbh };
				
				sql = "select xxsh from zzsf_sydxyzxdk where xh||xn=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if (temp == null) {
					boolean ok = false;
					ok = StandardOperation.insert("zzsf_sydxyzxdk", colName1,
							valueForOut, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					if ("通过".equalsIgnoreCase(temp[0])) {
						request.setAttribute("isPASS", "is");
					} else {
						colName1 = new String[] { "xh", "xn", "jtxxdz",
								"jtdh", "hjszd", "yzbm", "yddh",
								"gtjkr_xm", "gtjkr_gx", "gtjkr_sfzh",
								"gtjkr_gzdw", "gtjkr_zw", "gtjkr_jtxxdz",
								"gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
								"gtjkr_yddh", "knlx_dsr", "knlx_cnh",
								"knlx_sxg", "knlx_dbh", "knlx_zbh",
								"knlx_wsr", "knlx_lszn", "knlx_gr",
								"knlx_qt", "xxyy", "dknx", "dkzje",
								"fqffcsje", "jbyh", "sydxyzxdkhtbh",
								"sqsj", "xysh", "xxsh", "xyshyj", "xxshyj" };

						valueForOut = new String[] { xh, xn, jtxxdz, jtdh,
								hjszd, yzbm, yddh, gtjkr_xm, gtjkr_gx,
								gtjkr_sfzh, gtjkr_gzdw, gtjkr_zw,
								gtjkr_jtxxdz, gtjkr_jtdh, gtjkr_hjszd,
								gtjkr_yzbm, gtjkr_yddh, knlx_dsr, knlx_cnh,
								knlx_sxg, knlx_dbh, knlx_zbh, knlx_wsr,
								knlx_lszn, knlx_gr, knlx_qt, xxyy, dknx,
								dkzje, fqffcsje, jbyh, sydxyzxdkhtbh,
								rightNow, "未审核", "未审核", "", "" };

						boolean ok = false;
						ok = StandardOperation.update("zzsf_sydxyzxdk",
								colName1, valueForOut, "xh||xn", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
				}
			}
		}
		pkVal = request.getParameter("pkVal");
		if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + xn;
		}

		sql = "select xn,xh,xm,xb,nj,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,jtxxdz,jtdh,hjszd,yzbm,yddh,gtjkr_xm,gtjkr_gx,gtjkr_sfzh,gtjkr_gzdw,gtjkr_zw,gtjkr_jtxxdz,gtjkr_jtdh,gtjkr_hjszd,gtjkr_yzbm,gtjkr_yddh,knlx_dsr,knlx_cnh,knlx_sxg,knlx_dbh,knlx_zbh,knlx_wsr,knlx_lszn,knlx_gr,knlx_qt,xxyy,dknx,dkzje,fqffcsje,jbyh,sydxyzxdkhtbh,sqsj,xysh,xyshyj,xxsh,xxshyj from view_zzsf_sydxyzxdk "
				+ "where xh||xn=?";
		outString = new String[] { "xn", "xh", "xm", "xb", "nj", "xz", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "csrq",
				"jtxxdz", "jtdh", "hjszd", "yzbm", "yddh", "gtjkr_xm",
				"gtjkr_gx", "gtjkr_sfzh", "gtjkr_gzdw", "gtjkr_zw",
				"gtjkr_jtxxdz", "gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
				"gtjkr_yddh", "knlx_dsr", "knlx_cnh", "knlx_sxg", "knlx_dbh",
				"knlx_zbh", "knlx_wsr", "knlx_lszn", "knlx_gr", "knlx_qt",
				"xxyy", "dknx", "dkzje", "fqffcsje", "jbyh", "sydxyzxdkhtbh",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,b.csrq,a.sfzh,a.xymc,a.zymc,a.bjmc from view_xsjbxx a,view_stu_details b where a.xh=b.xh and a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"sfzh", "xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal != null) {
					colName = new String[] { "xh", "xm", "xb", "csrq",
							"sfzh", "xymc", "zymc", "bjmc" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
				String now = dao.getOneRs(
						"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
						new String[] {}, "sdate");
				map.put("sqsj", now);
			}
		} else {
			map.put("sqsj", rightNow);
			map.put("xn", xn);
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
	
	private ActionForward zzsf_sydxyzxdkb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xz = DealString.toGBK(request.getParameter("xz"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String sfzh = DealString.toGBK(request.getParameter("sfzh"));
		String csrq = DealString.toGBK(request.getParameter("csrq"));
		String jtxxdz = DealString.toGBK(request.getParameter("jtxxdz"));
		String jtdh = DealString.toGBK(request.getParameter("jtdh"));
		String hjszd = DealString.toGBK(request.getParameter("hjszd"));
		String yzbm = DealString.toGBK(request.getParameter("yzbm"));
		String yddh = DealString.toGBK(request.getParameter("yddh"));
		String gtjkr_xm = DealString.toGBK(request.getParameter("gtjkr_xm"));
		String gtjkr_gx = DealString.toGBK(request.getParameter("gtjkr_gx"));
		String gtjkr_sfzh = DealString
				.toGBK(request.getParameter("gtjkr_sfzh"));
		String gtjkr_gzdw = DealString
				.toGBK(request.getParameter("gtjkr_gzdw"));
		String gtjkr_zw = DealString.toGBK(request.getParameter("gtjkr_zw"));
		String gtjkr_jtxxdz = DealString.toGBK(request
				.getParameter("gtjkr_jtxxdz"));
		String gtjkr_jtdh = DealString
				.toGBK(request.getParameter("gtjkr_jtdh"));
		String gtjkr_hjszd = DealString.toGBK(request
				.getParameter("gtjkr_hjszd"));
		String gtjkr_yzbm = DealString
				.toGBK(request.getParameter("gtjkr_yzbm"));
		String gtjkr_yddh = DealString
				.toGBK(request.getParameter("gtjkr_yddh"));
		String knlx_dsr = DealString.toGBK(request.getParameter("knlx_dsr"));
		String knlx_cnh = DealString.toGBK(request.getParameter("knlx_cnh"));
		String knlx_sxg = DealString.toGBK(request.getParameter("knlx_sxg"));
		String knlx_dbh = DealString.toGBK(request.getParameter("knlx_dbh"));
		String knlx_zbh = DealString.toGBK(request.getParameter("knlx_zbh"));
		String knlx_wsr = DealString.toGBK(request.getParameter("knlx_wsr"));
		String knlx_lszn = DealString.toGBK(request.getParameter("knlx_lszn"));
		String knlx_gr = DealString.toGBK(request.getParameter("knlx_gr"));
		String knlx_qt = DealString.toGBK(request.getParameter("knlx_qt"));
		String xxyy = DealString.toGBK(request.getParameter("xxyy"));
		String dknx = DealString.toGBK(request.getParameter("dknx"));
		String dkzje = DealString.toGBK(request.getParameter("dkzje"));
		String fqffcsje = DealString.toGBK(request.getParameter("fqffcsje"));
		String jbyh = DealString.toGBK(request.getParameter("jbyh"));
		String sydxyzxdkhtbh = DealString.toGBK(request
				.getParameter("sydxyzxdkhtbh"));
		String sqsj = DealString.toGBK(request.getParameter("sqsj"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = "";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = "";
		}

		String[] outValue = new String[] { xn, xh, xm, xb, nj, xz, xymc, zymc,
				bjmc, sfzh, csrq, jtxxdz, jtdh, hjszd, yzbm, yddh, gtjkr_xm,
				gtjkr_gx, gtjkr_sfzh, gtjkr_gzdw, gtjkr_zw, gtjkr_jtxxdz,
				gtjkr_jtdh, gtjkr_hjszd, gtjkr_yzbm, gtjkr_yddh, knlx_dsr,
				knlx_cnh, knlx_sxg, knlx_dbh, knlx_zbh, knlx_wsr, knlx_lszn,
				knlx_gr, knlx_qt, xxyy, dknx, dkzje, fqffcsje, jbyh,
				sydxyzxdkhtbh, sqsj, xysh, xyshyj, xxsh, xxshyj };
		String[] outString = new String[] { "xn", "xh", "xm", "xb", "nj", "xz",
				"xymc", "zymc", "bjmc", "sfzh", "csrq", "jtxxdz", "jtdh",
				"hjszd", "yzbm", "yddh", "gtjkr_xm", "gtjkr_gx", "gtjkr_sfzh",
				"gtjkr_gzdw", "gtjkr_zw", "gtjkr_jtxxdz", "gtjkr_jtdh",
				"gtjkr_hjszd", "gtjkr_yzbm", "gtjkr_yddh", "knlx_dsr",
				"knlx_cnh", "knlx_sxg", "knlx_dbh", "knlx_zbh", "knlx_wsr",
				"knlx_lszn", "knlx_gr", "knlx_qt", "xxyy", "dknx", "dkzje",
				"fqffcsje", "jbyh", "sydxyzxdkhtbh", "sqsj", "xysh", "xyshyj",
				"xxsh", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_zzsf_sydxyzxdk(ActionMapping mapping,
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

		String nj = checkForm.getNj();
		String xh = DealString.toGBK(checkForm.getXh());
		String xm = DealString.toGBK(checkForm.getXm());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "zzsf_sydxyzxdk";
		pk = "xh||xn";
		tableName = "view_zzsf_sydxyzxdk";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
			xn = Base.currXn;
		} else {
			xn = request.getParameter("xn");
		}
		if (isNull(xn)) {
		} else {
			querry.append(" and xn='");
			querry.append(xn);
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
		if (isNull(nj)) {
		} else {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 生源地信用助学贷款";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 生源地信用助学贷款";
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "sydxyzxdkhtbh", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xn||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xz||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.jtxxdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.gtjkr_xm||'##OneSpile##'||a.gtjkr_gx||'##OneSpile##'||a.gtjkr_sfzh||'##OneSpile##'||a.gtjkr_gzdw||'##OneSpile##'||a.gtjkr_zw||'##OneSpile##'||a.gtjkr_jtxxdz||'##OneSpile##'||a.gtjkr_jtdh||'##OneSpile##'||a.gtjkr_hjszd||'##OneSpile##'||a.gtjkr_yzbm||'##OneSpile##'||a.gtjkr_yddh||'##OneSpile##'||a.knlx_dsr||'##OneSpile##'||a.knlx_cnh||'##OneSpile##'||a.knlx_sxg||'##OneSpile##'||a.knlx_dbh||'##OneSpile##'||a.knlx_zbh||'##OneSpile##'||a.knlx_wsr||'##OneSpile##'||a.knlx_lszn||'##OneSpile##'||a.knlx_gr||'##OneSpile##'||a.knlx_qt||'##OneSpile##'||a.xxyy||'##OneSpile##'||a.dknx||'##OneSpile##'||a.dkzje||'##OneSpile##'||a.fqffcsje||'##OneSpile##'||a.jbyh||'##OneSpile##'||a.sydxyzxdkhtbh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
				sqlExp = "select a.xn||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xz||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.jtxxdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.gtjkr_xm||'##OneSpile##'||a.gtjkr_gx||'##OneSpile##'||a.gtjkr_sfzh||'##OneSpile##'||a.gtjkr_gzdw||'##OneSpile##'||a.gtjkr_zw||'##OneSpile##'||a.gtjkr_jtxxdz||'##OneSpile##'||a.gtjkr_jtdh||'##OneSpile##'||a.gtjkr_hjszd||'##OneSpile##'||a.gtjkr_yzbm||'##OneSpile##'||a.gtjkr_yddh||'##OneSpile##'||a.knlx_dsr||'##OneSpile##'||a.knlx_cnh||'##OneSpile##'||a.knlx_sxg||'##OneSpile##'||a.knlx_dbh||'##OneSpile##'||a.knlx_zbh||'##OneSpile##'||a.knlx_wsr||'##OneSpile##'||a.knlx_lszn||'##OneSpile##'||a.knlx_gr||'##OneSpile##'||a.knlx_qt||'##OneSpile##'||a.xxyy||'##OneSpile##'||a.dknx||'##OneSpile##'||a.dkzje||'##OneSpile##'||a.fqffcsje||'##OneSpile##'||a.jbyh||'##OneSpile##'||a.sydxyzxdkhtbh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "xn", "xh", "xm", "xymc",
					"zymc", "bjmc", "sydxyzxdkhtbh", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a" + querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xn||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xz||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.jtxxdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.gtjkr_xm||'##OneSpile##'||a.gtjkr_gx||'##OneSpile##'||a.gtjkr_sfzh||'##OneSpile##'||a.gtjkr_gzdw||'##OneSpile##'||a.gtjkr_zw||'##OneSpile##'||a.gtjkr_jtxxdz||'##OneSpile##'||a.gtjkr_jtdh||'##OneSpile##'||a.gtjkr_hjszd||'##OneSpile##'||a.gtjkr_yzbm||'##OneSpile##'||a.gtjkr_yddh||'##OneSpile##'||a.knlx_dsr||'##OneSpile##'||a.knlx_cnh||'##OneSpile##'||a.knlx_sxg||'##OneSpile##'||a.knlx_dbh||'##OneSpile##'||a.knlx_zbh||'##OneSpile##'||a.knlx_wsr||'##OneSpile##'||a.knlx_lszn||'##OneSpile##'||a.knlx_gr||'##OneSpile##'||a.knlx_qt||'##OneSpile##'||a.xxyy||'##OneSpile##'||a.dknx||'##OneSpile##'||a.dkzje||'##OneSpile##'||a.fqffcsje||'##OneSpile##'||a.jbyh||'##OneSpile##'||a.sydxyzxdkhtbh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
				sqlExp = "select a.xn||'##OneSpile##'||a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xz||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.jtxxdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.hjszd||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.yddh||'##OneSpile##'||a.gtjkr_xm||'##OneSpile##'||a.gtjkr_gx||'##OneSpile##'||a.gtjkr_sfzh||'##OneSpile##'||a.gtjkr_gzdw||'##OneSpile##'||a.gtjkr_zw||'##OneSpile##'||a.gtjkr_jtxxdz||'##OneSpile##'||a.gtjkr_jtdh||'##OneSpile##'||a.gtjkr_hjszd||'##OneSpile##'||a.gtjkr_yzbm||'##OneSpile##'||a.gtjkr_yddh||'##OneSpile##'||a.knlx_dsr||'##OneSpile##'||a.knlx_cnh||'##OneSpile##'||a.knlx_sxg||'##OneSpile##'||a.knlx_dbh||'##OneSpile##'||a.knlx_zbh||'##OneSpile##'||a.knlx_wsr||'##OneSpile##'||a.knlx_lszn||'##OneSpile##'||a.knlx_gr||'##OneSpile##'||a.knlx_qt||'##OneSpile##'||a.xxyy||'##OneSpile##'||a.dknx||'##OneSpile##'||a.dkzje||'##OneSpile##'||a.fqffcsje||'##OneSpile##'||a.jbyh||'##OneSpile##'||a.sydxyzxdkhtbh||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xn##OneSpile##xh##OneSpile##xm##OneSpile##xb##OneSpile##nj##OneSpile##xz##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##sfzh##OneSpile##csrq##OneSpile##jtxxdz##OneSpile##jtdh##OneSpile##hjszd##OneSpile##yzbm##OneSpile##yddh##OneSpile##gtjkr_xm##OneSpile##gtjkr_gx##OneSpile##gtjkr_sfzh##OneSpile##gtjkr_gzdw##OneSpile##gtjkr_zw##OneSpile##gtjkr_jtxxdz##OneSpile##gtjkr_jtdh" +
				"##OneSpile##gtjkr_hjszd##OneSpile##gtjkr_yzbm##OneSpile##gtjkr_yddh##OneSpile##knlx_dsr##OneSpile##knlx_cnh##OneSpile##knlx_sxg##OneSpile##knlx_dbh##OneSpile##knlx_zbh##OneSpile##knlx_wsr##OneSpile##knlx_lszn##OneSpile##knlx_gr##OneSpile##knlx_qt##OneSpile##xxyy##OneSpile##dknx##OneSpile##dkzje##OneSpile##fqffcsje##OneSpile##jbyh##OneSpile##sydxyzxdkhtbh##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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

		map.put("nj", nj);
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("xn", xn);
		map.put("xh", xh);
		map.put("xm", xm);
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
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsf_sydxyzxdk");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_zzsf_sydxyzxdk_one(ActionMapping mapping,
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

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete zzsf_sydxyzxdk where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete zzsf_sydxyzxdk where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_sydxyzxdk.do?go=go", false);
			return newFwd;
		}
		if ("plsh".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String jg = request.getParameter("jg");
			if ("tg".equalsIgnoreCase(jg)){
				jg = "通过";
			} else if ("btg".equalsIgnoreCase(jg)){
				jg = "不通过";
			} else {
				jg = "未审核";
			}
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update zzsf_sydxyzxdk set xysh='"+jg+"' where xh||xn='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "update zzsf_sydxyzxdk set xxsh='"+jg+"' where xh||xn='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_zzsf_sydxyzxdk.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				StandardOperation.update("zzsf_sydxyzxdk", new String[] {
						"xysh", "xyshyj" },
						new String[] { yesNo, xyshyj }, "xh||xn", pkVal, request);
			} else {
				StandardOperation.update("zzsf_sydxyzxdk", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||xn", pkVal, request);
			}
		}

		realTable = "zzsf_sydxyzxdk";
		pk = "xh||xn";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xn,xh,xm,xb,nj,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,jtxxdz,jtdh,hjszd,yzbm,yddh,gtjkr_xm,gtjkr_gx,gtjkr_sfzh,gtjkr_gzdw,gtjkr_zw,gtjkr_jtxxdz,gtjkr_jtdh,gtjkr_hjszd,gtjkr_yzbm,gtjkr_yddh,knlx_dsr,knlx_cnh,knlx_sxg,knlx_dbh,knlx_zbh,knlx_wsr,knlx_lszn,knlx_gr,knlx_qt,xxyy,dknx,dkzje,fqffcsje,jbyh,sydxyzxdkhtbh,sqsj,xysh,xyshyj,xxsh,xxshyj,XYSH yesNo from "
					+ "view_zzsf_sydxyzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xn,xh,xm,xb,nj,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,sfzh,csrq,jtxxdz,jtdh,hjszd,yzbm,yddh,gtjkr_xm,gtjkr_gx,gtjkr_sfzh,gtjkr_gzdw,gtjkr_zw,gtjkr_jtxxdz,gtjkr_jtdh,gtjkr_hjszd,gtjkr_yzbm,gtjkr_yddh,knlx_dsr,knlx_cnh,knlx_sxg,knlx_dbh,knlx_zbh,knlx_wsr,knlx_lszn,knlx_gr,knlx_qt,xxyy,dknx,dkzje,fqffcsje,jbyh,sydxyzxdkhtbh,sqsj,xysh,xyshyj,xxsh,xxshyj,XXSH yesNo from "
					+ "view_zzsf_sydxyzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xn", "xh", "xm", "xb", "nj", "xz",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh", "csrq",
				"jtxxdz", "jtdh", "hjszd", "yzbm", "yddh", "gtjkr_xm",
				"gtjkr_gx", "gtjkr_sfzh", "gtjkr_gzdw", "gtjkr_zw",
				"gtjkr_jtxxdz", "gtjkr_jtdh", "gtjkr_hjszd", "gtjkr_yzbm",
				"gtjkr_yddh", "knlx_dsr", "knlx_cnh", "knlx_sxg", "knlx_dbh",
				"knlx_zbh", "knlx_wsr", "knlx_lszn", "knlx_gr", "knlx_qt",
				"xxyy", "dknx", "dkzje", "fqffcsje", "jbyh", "sydxyzxdkhtbh",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj", "yesNo" };

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
		request.setAttribute("act", "zzsf_sydxyzxdk");
		return mapping.findForward("success");
	}
}
