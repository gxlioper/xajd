/*
 * 创建日期 2007-11-07 zhoumi
 *
 */
package xgxt.action.jsxx;

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
			if (act.equalsIgnoreCase("jsxx_gjzxj")) {// 江苏信息职业技术学院-国家助学金申请
				myActFwd = jsxx_gjzxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("jsxx_gjzxjb")) {// 江苏信息职业技术学院-国家助学金申请表
				myActFwd = jsxx_gjzxjb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_gjzxj")) {// 江苏信息职业技术学院-国家助学金审核
				myActFwd = auditing_jsxx_gjzxj(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_gjzxj_one")) {// 江苏信息职业技术学院-国家助学金单个审核
				myActFwd = auditing_jsxx_gjzxj_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_knsxx")) {// 江苏信息职业技术学院-困难生审核
				myActFwd = auditing_jsxx_knsxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_knsxx_one")) {// 江苏信息职业技术学院-困难生单个审核
				myActFwd = auditing_jsxx_knsxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_gjzxdk")) {// 江苏信息职业技术学院-国家助学贷款审核
				myActFwd = auditing_jsxx_gjzxdk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_gjzxdk_one")) {// 江苏信息职业技术学院-国家助学贷款单个审核
				myActFwd = auditing_jsxx_gjzxdk_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_sbknbz")) {// 江苏信息职业技术学院-伤、病困难补助审核
				myActFwd = auditing_jsxx_sbknbz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_jsxx_sbknbz_one")) {// 江苏信息职业技术学院-伤、病困难补助单个审核
				myActFwd = auditing_jsxx_sbknbz_one(mapping, form, request,
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

	private ActionForward jsxx_gjzxj(ActionMapping mapping, ActionForm form,
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

		titNames = new String[] { "jsxx_gjzxj" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmmmc,mzmc,jtdz,jtdh,xsdh,"
				+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "btzw,nyyjngzfy,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,sqly,sfgr,sflszn,"
				+ "sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,sqsj,xysh,xytysqje,xyshyj,"
				+ "xxsh,xxtyshje,xxshyj,bz,jtjjknqk,xssqdjdm,xssqdjmc,xssqdjje,xytydjdm,"
				+ "xytydjmc,xxtydjdm,xxtydjmc from view_jsxx_gjzxj where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;
		List zxjList = dao.getJsxxGjzxjDj();
		request.setAttribute("gjzxjList", zxjList);

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
					String btzw = DealString.toGBK(request.getParameter("btzw").toString());
					String nyyjngzfy = DealString.toGBK(request.getParameter("nyyjngzfy").toString());
					String jtmntg = DealString.toGBK(request.getParameter("jtmntg").toString());
					String hjmnsqfy = DealString.toGBK(request.getParameter("hjmnsqfy").toString());
					String qjxfs = DealString.toGBK(request.getParameter("qjxfs").toString());
					String yhdkzljje = DealString.toGBK(request.getParameter("yhdkzljje").toString());
					String sqly = DealString.toGBK(request.getParameter("sqly").toString());
					String sfgr = DealString.toGBK(request.getParameter("sfgr").toString());
					String sflszn = DealString.toGBK(request.getParameter("sflszn").toString());
					String sfwsrh = DealString.toGBK(request.getParameter("sfwsrh").toString());
					String sfzbh = DealString.toGBK(request.getParameter("sfzbh").toString());
					String sfdbh = DealString.toGBK(request.getParameter("sfdbh").toString());
					String sffmsxg = DealString.toGBK(request.getParameter("sffmsxg").toString());
					String sfcnh = DealString.toGBK(request.getParameter("sfcnh").toString());
					String sfdsr = DealString.toGBK(request.getParameter("sfdsr").toString());
					String bz = DealString.toGBK(request.getParameter("bz").toString());
					String xssqdjdm = DealString.toGBK(request.getParameter("xssqdjdm").toString());
					pkVal = request.getParameter("pkVal");
					if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd;
					}

					sql = "select xxsh from jsxx_gjzxj where xh||nd=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("jsxx_gjzxj", "xh||nd", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd", "btzw",
								"nyyjngzfy", "jtmntg", "hjmnsqfy", "qjxfs",
								"yhdkzljje", "sqly", "sfgr", "sflszn",
								"sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh",
								"sfdsr", "bz", "xssqdjdm" };

						valueForOut = new String[] { xh, nd, btzw, nyyjngzfy,
								jtmntg, hjmnsqfy, qjxfs, yhdkzljje, sqly, sfgr,
								sflszn, sfwsrh, sfzbh, sfdbh, sffmsxg, sfcnh,
								sfdsr, bz, xssqdjdm };

						boolean ok = false;
						ok = StandardOperation.insert("jsxx_gjzxj", colName1,
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
				String btzw = DealString.toGBK(request.getParameter("btzw").toString());
				String nyyjngzfy = DealString.toGBK(request.getParameter("nyyjngzfy").toString());
				String jtmntg = DealString.toGBK(request.getParameter("jtmntg").toString());
				String hjmnsqfy = DealString.toGBK(request.getParameter("hjmnsqfy").toString());
				String qjxfs = DealString.toGBK(request.getParameter("qjxfs").toString());
				String yhdkzljje = DealString.toGBK(request.getParameter("yhdkzljje").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly").toString());
				String sfgr = DealString.toGBK(request.getParameter("sfgr").toString());
				String sflszn = DealString.toGBK(request.getParameter("sflszn").toString());
				String sfwsrh = DealString.toGBK(request.getParameter("sfwsrh").toString());
				String sfzbh = DealString.toGBK(request.getParameter("sfzbh").toString());
				String sfdbh = DealString.toGBK(request.getParameter("sfdbh").toString());
				String sffmsxg = DealString.toGBK(request.getParameter("sffmsxg").toString());
				String sfcnh = DealString.toGBK(request.getParameter("sfcnh").toString());
				String sfdsr = DealString.toGBK(request.getParameter("sfdsr").toString());
				String bz = DealString.toGBK(request.getParameter("bz").toString());
				String xssqdjdm = DealString.toGBK(request.getParameter("xssqdjdm").toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd;
				}

				sql = "select xxsh from jsxx_gjzxj where xh||nd=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && ("通过".equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("jsxx_gjzxj", "xh||nd", pkVal, request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "btzw",
							"nyyjngzfy", "jtmntg", "hjmnsqfy", "qjxfs",
							"yhdkzljje", "sqly", "sfgr", "sflszn",
							"sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh",
							"sfdsr", "bz", "xssqdjdm" };

					valueForOut = new String[] { xh, nd, btzw, nyyjngzfy,
							jtmntg, hjmnsqfy, qjxfs, yhdkzljje, sqly, sfgr,
							sflszn, sfwsrh, sfzbh, sfdbh, sffmsxg, sfcnh,
							sfdsr, bz, xssqdjdm };

					boolean ok = false;
					ok = StandardOperation.insert("jsxx_gjzxj", colName1,
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

		sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmmmc,mzmc,jtdz,jtdh,xsdh,"
				+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "btzw,nyyjngzfy,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,sqly,sfgr,sflszn,"
				+ "sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,sqsj,xysh,xytysqje,xyshyj,"
				+ "xxsh,xxtyshje,xxshyj,bz,jtjjknqk,xssqdjdm,xssqdjmc,xssqdjje,xytydjdm,"
				+ "xytydjmc,xxtydjdm,xxtydjmc from view_jsxx_gjzxj where xh||nd=?";
		outString = new String[] { "xh", "nd", "xm", "xb", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "zzmmmc", "mzmc",
				"jtdz", "jtdh", "xsdh", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
				"jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw",
				"jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk",
				"jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk",
				"jtcy5_gzdwjzw", "jtcy5_nsr", "btzw", "nyyjngzfy", "jtmntg",
				"hjmnsqfy", "qjxfs", "yhdkzljje", "sqly", "sfgr", "sflszn",
				"sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh", "sfdsr",
				"sqsj", "xysh", "xytysqje", "xyshyj", "xxsh", "xxtyshje",
				"xxshyj", "bz", "jtjjknqk", "xssqdjdm", "xssqdjmc", "xssqdjje",
				"xytydjdm", "xytydjmc", "xxtydjdm", "xxtydjmc" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.jtdz,a.jtdh,a.xsdh,a.zzmm zzmmmc,"
						+ "(select b.mzmc from view_stu_details b where a.xh=b.xh) mzmc,"
						+ "a.jtcy1_xm,a.jtcy1_cw,a.jtcy1_nl,a.jtcy1_jkzk,a.jtcy1_gzdwjzw,a.jtcy1_nsr,"
						+ "a.jtcy2_xm,a.jtcy2_cw,a.jtcy2_nl,a.jtcy2_jkzk,a.jtcy2_gzdwjzw,a.jtcy2_nsr,"
						+ "a.jtcy3_xm,a.jtcy3_cw,a.jtcy3_nl,a.jtcy3_jkzk,a.jtcy3_gzdwjzw,a.jtcy3_nsr,"
						+ "a.jtcy4_xm,a.jtcy4_cw,a.jtcy4_nl,a.jtcy4_jkzk,a.jtcy4_gzdwjzw,a.jtcy4_nsr,"
						+ "a.jtcy5_xm,a.jtcy5_cw,a.jtcy5_nl,a.jtcy5_jkzk,a.jtcy5_gzdwjzw,a.jtcy5_nsr,"
						+ "(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) sqsj,"
						+ "(select dqnd nd from xtszb) nd,a.sfgr,a.SFJSZN sflszn,a.sfwsrh,a.sfzbh,a.sfdbh,a.sffmsxg,a.sfcnh,"
						+ "a.sfdsr,jtjjknqk from view_jsxx_knsxx a where xxsh in ('一般困难','比较困难','特别困难') and "
						+ "a.xh=? and a.nd=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xymc", "zymc", "bjmc", "jtdz", "jtdh", "xsdh",
						"zzmmmc", "mzmc", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
						"jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm",
						"jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw",
						"jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl",
						"jtcy3_jkzk", "jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm",
						"jtcy4_cw", "jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw",
						"jtcy4_nsr", "jtcy5_xm", "jtcy5_cw", "jtcy5_nl",
						"jtcy5_jkzk", "jtcy5_gzdwjzw", "jtcy5_nsr", "sqsj",
						"nd", "sfgr", "sflszn", "sfwsrh", "sfzbh", "sfdbh",
						"sffmsxg", "sfcnh", "sfdsr", "jtjjknqk" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh,nd },
						colName);
				if (outVal == null) {
					request.setAttribute("notSQ", "is");
				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh",
							"xymc", "zymc", "bjmc", "jtdz", "jtdh", "xsdh",
							"zzmmmc", "mzmc", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
							"jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm",
							"jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw",
							"jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl",
							"jtcy3_jkzk", "jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm",
							"jtcy4_cw", "jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw",
							"jtcy4_nsr", "jtcy5_xm", "jtcy5_cw", "jtcy5_nl",
							"jtcy5_jkzk", "jtcy5_gzdwjzw", "jtcy5_nsr", "sqsj",
							"nd", "sfgr", "sflszn", "sfwsrh", "sfzbh", "sfdbh",
							"sffmsxg", "sfcnh", "sfdsr", "jtjjknqk" };
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
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward jsxx_gjzxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String mzmc = DealString.toGBK(request.getParameter("mzmc").toString());
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc")
				.toString());
		String btzw = DealString.toGBK(request.getParameter("btzw").toString());
		String nyyjngzfy = DealString.toGBK(request.getParameter("nyyjngzfy").toString());
		String jtmntg = DealString.toGBK(request.getParameter("jtmntg").toString());
		String hjmnsqfy = DealString.toGBK(request.getParameter("hjmnsqfy").toString());
		String qjxfs = DealString.toGBK(request.getParameter("qjxfs").toString());
		String yhdkzljje = DealString.toGBK(request.getParameter("yhdkzljje").toString());
		String sqly = DealString.toGBK(request.getParameter("sqly").toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		String sfgr = DealString.toGBK(request.getParameter("sfgr").toString());
		String sflszn = DealString.toGBK(request.getParameter("sflszn").toString());
		String sfwsrh = DealString.toGBK(request.getParameter("sfwsrh").toString());
		String sfzbh = DealString.toGBK(request.getParameter("sfzbh").toString());
		String sfdbh = DealString.toGBK(request.getParameter("sfdbh").toString());
		String sffmsxg = DealString.toGBK(request.getParameter("sffmsxg").toString());
		String sfcnh = DealString.toGBK(request.getParameter("sfcnh").toString());
		String sfdsr = DealString.toGBK(request.getParameter("sfdsr").toString());
		String jtdz = DealString.toGBK(request.getParameter("jtdz").toString());
		String jtdh = DealString.toGBK(request.getParameter("jtdh").toString());
		String xsdh = DealString.toGBK(request.getParameter("xsdh").toString());
		String jtcy1_xm = DealString.toGBK(request.getParameter("jtcy1_xm").toString());
		String jtcy1_cw = DealString.toGBK(request.getParameter("jtcy1_cw").toString());
		String jtcy1_nl = DealString.toGBK(request.getParameter("jtcy1_nl").toString());
		String jtcy1_jkzk = DealString.toGBK(request.getParameter("jtcy1_jkzk").toString());
		String jtcy1_gzdwjzw = DealString.toGBK(request.getParameter("jtcy1_gzdwjzw").toString());
		String jtcy1_nsr = DealString.toGBK(request.getParameter("jtcy1_nsr").toString());
		String jtcy2_xm = DealString.toGBK(request.getParameter("jtcy2_xm").toString());
		String jtcy2_cw = DealString.toGBK(request.getParameter("jtcy2_cw").toString());
		String jtcy2_nl = DealString.toGBK(request.getParameter("jtcy2_nl").toString());
		String jtcy2_jkzk = DealString.toGBK(request.getParameter("jtcy2_jkzk").toString());
		String jtcy2_gzdwjzw = DealString.toGBK(request.getParameter("jtcy2_gzdwjzw").toString());
		String jtcy2_nsr = DealString.toGBK(request.getParameter("jtcy2_nsr").toString());
		String jtcy3_xm = DealString.toGBK(request.getParameter("jtcy3_xm").toString());
		String jtcy3_cw = DealString.toGBK(request.getParameter("jtcy3_cw").toString());
		String jtcy3_nl = DealString.toGBK(request.getParameter("jtcy3_nl").toString());
		String jtcy3_jkzk = DealString.toGBK(request.getParameter("jtcy3_jkzk").toString());
		String jtcy3_gzdwjzw = DealString.toGBK(request.getParameter("jtcy3_gzdwjzw").toString());
		String jtcy3_nsr = DealString.toGBK(request.getParameter("jtcy3_nsr").toString());
		String jtcy4_xm = DealString.toGBK(request.getParameter("jtcy4_xm").toString());
		String jtcy4_cw = DealString.toGBK(request.getParameter("jtcy4_cw").toString());
		String jtcy4_nl = DealString.toGBK(request.getParameter("jtcy4_nl").toString());
		String jtcy4_jkzk = DealString.toGBK(request.getParameter("jtcy4_jkzk").toString());
		String jtcy4_gzdwjzw = DealString.toGBK(request.getParameter("jtcy4_gzdwjzw").toString());
		String jtcy4_nsr = DealString.toGBK(request.getParameter("jtcy4_nsr").toString());
		String jtcy5_xm = DealString.toGBK(request.getParameter("jtcy5_xm").toString());
		String jtcy5_cw = DealString.toGBK(request.getParameter("jtcy5_cw").toString());
		String jtcy5_nl = DealString.toGBK(request.getParameter("jtcy5_nl").toString());
		String jtcy5_jkzk = DealString.toGBK(request.getParameter("jtcy5_jkzk").toString());
		String jtcy5_gzdwjzw = DealString.toGBK(request.getParameter("jtcy5_gzdwjzw").toString());
		String jtcy5_nsr = DealString.toGBK(request.getParameter("jtcy5_nsr").toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj").toString());
		String xytysqje = DealString.toGBK(request.getParameter("xytysqje").toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj").toString());
		String xxtyshje = DealString.toGBK(request.getParameter("xxtyshje").toString());
		String xssqdjje = DealString.toGBK(request.getParameter("xssqdjje").toString());
		String jtjjknqk = DealString.toGBK(request.getParameter("jtjjknqk").toString());
		
		if((null == sqsj) || ("".equalsIgnoreCase(sqsj))){
			sqsj = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
		}
		String sqsj_year = sqsj.substring(0, 4);
		String sqsj_mon = sqsj.substring(5, 7);
		String sqsj_day = sqsj.substring(8);

		String[] outValue = new String[] { xh, nd, xm, xb, sfzh, xymc, zymc,
				bjmc, zzmmmc, mzmc, jtdz, jtdh, xsdh, jtcy1_xm, jtcy1_cw,
				jtcy1_nl, jtcy1_jkzk, jtcy1_gzdwjzw, jtcy1_nsr, jtcy2_xm,
				jtcy2_cw, jtcy2_nl, jtcy2_jkzk, jtcy2_gzdwjzw, jtcy2_nsr,
				jtcy3_xm, jtcy3_cw, jtcy3_nl, jtcy3_jkzk, jtcy3_gzdwjzw,
				jtcy3_nsr, jtcy4_xm, jtcy4_cw, jtcy4_nl, jtcy4_jkzk,
				jtcy4_gzdwjzw, jtcy4_nsr, jtcy5_xm, jtcy5_cw, jtcy5_nl,
				jtcy5_jkzk, jtcy5_gzdwjzw, jtcy5_nsr, btzw, nyyjngzfy, jtmntg,
				hjmnsqfy, qjxfs, yhdkzljje, sqly, sfgr, sflszn, sfwsrh, sfzbh,
				sfdbh, sffmsxg, sfcnh, sfdsr, sqsj, xytysqje, xyshyj, xxtyshje,
				xxshyj, bz, sqsj_year, sqsj_mon, sqsj_day, jtjjknqk, xssqdjje };
		String[] outString = new String[] { "xh", "nd", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "zzmmmc", "mzmc", "jtdz", "jtdh",
				"xsdh", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl", "jtcy1_jkzk",
				"jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm", "jtcy2_cw",
				"jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw", "jtcy2_nsr",
				"jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk",
				"jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk",
				"jtcy5_gzdwjzw", "jtcy5_nsr", "btzw", "nyyjngzfy", "jtmntg",
				"hjmnsqfy", "qjxfs", "yhdkzljje", "sqly", "sfgr", "sflszn",
				"sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh", "sfdsr",
				"sqsj", "xytysqje", "xyshyj", "xxtyshje", "xxshyj", "bz",
				"sqsj_year", "sqsj_mon", "sqsj_day", "jtjjknqk", "xssqdjje" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_jsxx_gjzxj(ActionMapping mapping,
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
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		realTable = "jsxx_gjzxj";
		pk = "xh||nd";
		tableName = "view_jsxx_gjzxj";
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
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		String tips = "当前所在位置：学生资助 - 审核 - 国家助学金审核";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学金查询";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xssqdjje", "xysh", "xytysqje", "xxsh", "xxtyshje", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.btzw||'##OneSpile##'||a.nyyjngzfy||'##OneSpile##'||a.jtmntg||'##OneSpile##'||a.hjmnsqfy||'##OneSpile##'||a.qjxfs||'##OneSpile##'||a.yhdkzljje||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xssqdjdm||'##OneSpile##'||a.xssqdjmc||'##OneSpile##'||a.xssqdjje||'##OneSpile##'||a.xytydjdm||'##OneSpile##'||a.xytydjmc||'##OneSpile##'||a.xytysqje||'##OneSpile##'||a.xxtydjdm||'##OneSpile##'||a.xxtydjmc||'##OneSpile##'||a.xxtyshje col from "
					+ tableName
					+ " a"
					+ querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.btzw||'##OneSpile##'||a.nyyjngzfy||'##OneSpile##'||a.jtmntg||'##OneSpile##'||a.hjmnsqfy||'##OneSpile##'||a.qjxfs||'##OneSpile##'||a.yhdkzljje||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xssqdjdm||'##OneSpile##'||a.xssqdjmc||'##OneSpile##'||a.xssqdjje||'##OneSpile##'||a.xytydjdm||'##OneSpile##'||a.xytydjmc||'##OneSpile##'||a.xytysqje||'##OneSpile##'||a.xxtydjdm||'##OneSpile##'||a.xxtydjmc||'##OneSpile##'||a.xxtyshje col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
						"zymc", "bjmc", "xssqdjje", "sqsj", "xytysqje", "", "" };
			} else {
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xymc",
						"zymc", "bjmc", "xssqdjje", "sqsj", "", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry.toString() + " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.btzw||'##OneSpile##'||a.nyyjngzfy||'##OneSpile##'||a.jtmntg||'##OneSpile##'||a.hjmnsqfy||'##OneSpile##'||a.qjxfs||'##OneSpile##'||a.yhdkzljje||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xssqdjdm||'##OneSpile##'||a.xssqdjmc||'##OneSpile##'||a.xssqdjje||'##OneSpile##'||a.xytydjdm||'##OneSpile##'||a.xytydjmc||'##OneSpile##'||a.xytysqje||'##OneSpile##'||a.xxtydjdm||'##OneSpile##'||a.xxtydjmc||'##OneSpile##'||a.xxtyshje col from "
					+ tableName
					+ " a"
					+ querry.toString() + " and xysh='通过' order by xxsh desc";
				colList[colList.length - 2] = "xxtyshje";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zzmmmc||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.btzw||'##OneSpile##'||a.nyyjngzfy||'##OneSpile##'||a.jtmntg||'##OneSpile##'||a.hjmnsqfy||'##OneSpile##'||a.qjxfs||'##OneSpile##'||a.yhdkzljje||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.sqly||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sflszn||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xssqdjdm||'##OneSpile##'||a.xssqdjmc||'##OneSpile##'||a.xssqdjje||'##OneSpile##'||a.xytydjdm||'##OneSpile##'||a.xytydjmc||'##OneSpile##'||a.xytysqje||'##OneSpile##'||a.xxtydjdm||'##OneSpile##'||a.xxtydjmc||'##OneSpile##'||a.xxtyshje col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 2] = "xytysqje";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##zzmmmc##OneSpile##mzmc##OneSpile##jtdz##OneSpile##jtdh##OneSpile##xsdh##OneSpile##jtcy1_xm##OneSpile##jtcy1_cw##OneSpile##jtcy1_nl##OneSpile##jtcy1_jkzk##OneSpile##jtcy1_gzdwjzw##OneSpile##jtcy1_nsr##OneSpile##jtcy2_xm##OneSpile##jtcy2_cw##OneSpile##jtcy2_nl##OneSpile##jtcy2_jkzk##OneSpile##jtcy2_gzdwjzw##OneSpile##jtcy2_nsr##OneSpile##jtcy3_xm##OneSpile##jtcy3_cw##OneSpile##jtcy3_nl##OneSpile##jtcy3_jkzk##OneSpile##jtcy3_gzdwjzw##OneSpile##jtcy3_nsr##OneSpile##jtcy4_xm##OneSpile##jtcy4_cw##OneSpile##jtcy4_nl##OneSpile##jtcy4_jkzk##OneSpile##jtcy4_gzdwjzw##OneSpile##jtcy4_nsr##OneSpile##jtcy5_xm##OneSpile##jtcy5_cw##OneSpile##jtcy5_nl##OneSpile##jtcy5_jkzk##OneSpile##jtcy5_gzdwjzw##OneSpile##jtcy5_nsr##OneSpile##btzw##OneSpile##nyyjngzfy##OneSpile##jtmntg##OneSpile##hjmnsqfy##OneSpile##qjxfs##OneSpile##yhdkzljje##OneSpile##jtjjknqk##OneSpile##sqly##OneSpile##sfgr##OneSpile##sflszn##OneSpile##sfwsrh##OneSpile##sfzbh##OneSpile##sfdbh##OneSpile##sffmsxg##OneSpile##sfcnh##OneSpile##sfdsr##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##bz##OneSpile##xssqdjdm##OneSpile##xssqdjmc##OneSpile##xssqdjje##OneSpile##xytydjdm##OneSpile##xytydjmc##OneSpile##xytysqje##OneSpile##xxtydjdm##OneSpile##xxtydjmc##OneSpile##xxtyshje";
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
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward auditing_jsxx_gjzxj_one(ActionMapping mapping,
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
		String xytydjdm = DealString.toGBK(request.getParameter("xytydjdm"));
		String xxtydjdm = DealString.toGBK(request.getParameter("xxtydjdm"));
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		List zxjList = dao.getJsxxGjzxjDj();
		request.setAttribute("gjzxjList", zxjList); 
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!#!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete jsxx_gjzxj where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete jsxx_gjzxj where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_jsxx_gjzxj.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("jsxx_gjzxj", new String[] {
						"xysh", "xyshyj", "xytydjdm" }, new String[] { yesNo,
						xyshyj, xytydjdm }, "xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("jsxx_gjzxj", new String[] {
						"xxsh", "xxshyj", "xxtydjdm" }, new String[] { yesNo,
						xxshyj, xxtydjdm }, "xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) jsxx_gjzxj";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "jsxx_gjzxj";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmmmc,mzmc,jtdz,jtdh,xsdh,"
					+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,"
					+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,"
					+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,"
					+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,"
					+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,"
					+ "btzw,nyyjngzfy,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,sqly,sfgr,sflszn,"
					+ "sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,sqsj,xysh,xytysqje,xyshyj,"
					+ "xxsh,xxtyshje,xxshyj,bz,jtjjknqk,xssqdjdm,xssqdjmc,xssqdjje,xytydjdm,"
					+ "xytydjmc,xxtydjdm,xxtydjmc,xxsh,XYSH yesNo from view_jsxx_gjzxj where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmmmc,mzmc,jtdz,jtdh,xsdh,"
					+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,"
					+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,"
					+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,"
					+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,"
					+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,"
					+ "btzw,nyyjngzfy,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,sqly,sfgr,sflszn,"
					+ "sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,sqsj,xysh,xytysqje,xyshyj,"
					+ "xxsh,xxtyshje,xxshyj,bz,jtjjknqk,xssqdjdm,xssqdjmc,xssqdjje,xytydjdm,"
					+ "xytydjmc,xxtydjdm,xxtydjmc,xxsh,XXSH yesNo from view_jsxx_gjzxj where " 
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "zzmmmc", "mzmc",
				"jtdz", "jtdh", "xsdh", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
				"jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw",
				"jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk",
				"jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk",
				"jtcy5_gzdwjzw", "jtcy5_nsr", "btzw", "nyyjngzfy", "jtmntg",
				"hjmnsqfy", "qjxfs", "yhdkzljje", "sqly", "sfgr", "sflszn",
				"sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh", "sfdsr",
				"sqsj", "xysh", "xytysqje", "xyshyj", "xxsh", "xxtyshje",
				"xxshyj", "bz", "jtjjknqk", "xssqdjdm", "xssqdjmc", "xssqdjje",
				"xytydjdm", "xytydjmc", "xxtydjdm", "xxtydjmc", "xxsh", "yesNo" };

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
		request.setAttribute("act", "jsxx_gjzxj");
		return mapping.findForward("success");
	}

	private ActionForward auditing_jsxx_knsxx(ActionMapping mapping,
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
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		realTable = "jsxx_knsxx";
		pk = "xh||nd";
		tableName = "view_jsxx_knsxx";
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
		String tips = "当前所在位置：学生资助 - 审核 - 经济困难学生及家庭情况调查";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 经济困难学生及家庭情况调查";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xysh", "xxsh",
					"sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfjszn||'##OneSpile##'||a.sfssmz||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.mzbm_lxr||'##OneSpile##'||a.jfqk_jttg||'##OneSpile##'||a.jfqk_qtqytg||'##OneSpile##'||a.jfqk_hjtg||'##OneSpile##'||a.jfqk_yjfy||'##OneSpile##'||a.jfqk_mysffy||'##OneSpile##'||a.jfqk_qnhjfy||'##OneSpile##'||a.jfqk_bxnjttgfybzs||'##OneSpile##'||a.jfqk_ljqf||'##OneSpile##'||a.jfqk_ywhjjh||'##OneSpile##'||a.zzqk_sfsqxnqgzx||'##OneSpile##'||a.zzqk_xnqgzxyapgw||'##OneSpile##'||a.zzqk_sfsqxwqgzx||'##OneSpile##'||a.zzqk_sfsbnczxdk||'##OneSpile##'||a.zzqk_lnyhnczxdhje||'##OneSpile##'||a.zzqk_sfsbgxzxdk||'##OneSpile##'||a.zzqk_lnyhgxzxdhje||'##OneSpile##'||a.zzqk_sfsbbxzxdk||'##OneSpile##'||a.zzqk_lnyhbxzxdhje||'##OneSpile##'||a.zzqk_lnjzxjqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfjszn||'##OneSpile##'||a.sfssmz||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.mzbm_lxr||'##OneSpile##'||a.jfqk_jttg||'##OneSpile##'||a.jfqk_qtqytg||'##OneSpile##'||a.jfqk_hjtg||'##OneSpile##'||a.jfqk_yjfy||'##OneSpile##'||a.jfqk_mysffy||'##OneSpile##'||a.jfqk_qnhjfy||'##OneSpile##'||a.jfqk_bxnjttgfybzs||'##OneSpile##'||a.jfqk_ljqf||'##OneSpile##'||a.jfqk_ywhjjh||'##OneSpile##'||a.zzqk_sfsqxnqgzx||'##OneSpile##'||a.zzqk_xnqgzxyapgw||'##OneSpile##'||a.zzqk_sfsqxwqgzx||'##OneSpile##'||a.zzqk_sfsbnczxdk||'##OneSpile##'||a.zzqk_lnyhnczxdhje||'##OneSpile##'||a.zzqk_sfsbgxzxdk||'##OneSpile##'||a.zzqk_lnyhgxzxdhje||'##OneSpile##'||a.zzqk_sfsbbxzxdk||'##OneSpile##'||a.zzqk_lnyhbxzxdhje||'##OneSpile##'||a.zzqk_lnjzxjqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			if(userType.equalsIgnoreCase("xx")){
				colList = new String[] { "bgcolor", "主键", "pk2", "ND", "XH",
						"XM", "XYMC", "ZYMC", "BJMC", "SQSJ", "XYSH", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "ND", "XH",
						"XM", "XYMC", "ZYMC", "BJMC", "SQSJ", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('一般困难','比较困难','特别困难') order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfjszn||'##OneSpile##'||a.sfssmz||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.mzbm_lxr||'##OneSpile##'||a.jfqk_jttg||'##OneSpile##'||a.jfqk_qtqytg||'##OneSpile##'||a.jfqk_hjtg||'##OneSpile##'||a.jfqk_yjfy||'##OneSpile##'||a.jfqk_mysffy||'##OneSpile##'||a.jfqk_qnhjfy||'##OneSpile##'||a.jfqk_bxnjttgfybzs||'##OneSpile##'||a.jfqk_ljqf||'##OneSpile##'||a.jfqk_ywhjjh||'##OneSpile##'||a.zzqk_sfsqxnqgzx||'##OneSpile##'||a.zzqk_xnqgzxyapgw||'##OneSpile##'||a.zzqk_sfsqxwqgzx||'##OneSpile##'||a.zzqk_sfsbnczxdk||'##OneSpile##'||a.zzqk_lnyhnczxdhje||'##OneSpile##'||a.zzqk_sfsbgxzxdk||'##OneSpile##'||a.zzqk_lnyhgxzxdhje||'##OneSpile##'||a.zzqk_sfsbbxzxdk||'##OneSpile##'||a.zzqk_lnyhbxzxdhje||'##OneSpile##'||a.zzqk_lnjzxjqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('一般困难','比较困难','特别困难') order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%困%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.csrq||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.jtdh||'##OneSpile##'||a.xsdh||'##OneSpile##'||a.rxqhk||'##OneSpile##'||a.sfgr||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfjszn||'##OneSpile##'||a.sfssmz||'##OneSpile##'||a.sfwsrh||'##OneSpile##'||a.sfzbh||'##OneSpile##'||a.sfdbh||'##OneSpile##'||a.sffmsxg||'##OneSpile##'||a.sfcnh||'##OneSpile##'||a.sfdsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.jtjjknqk||'##OneSpile##'||a.mzbm_yzbm||'##OneSpile##'||a.mzbm_lxdh||'##OneSpile##'||a.mzbm_lxr||'##OneSpile##'||a.jfqk_jttg||'##OneSpile##'||a.jfqk_qtqytg||'##OneSpile##'||a.jfqk_hjtg||'##OneSpile##'||a.jfqk_yjfy||'##OneSpile##'||a.jfqk_mysffy||'##OneSpile##'||a.jfqk_qnhjfy||'##OneSpile##'||a.jfqk_bxnjttgfybzs||'##OneSpile##'||a.jfqk_ljqf||'##OneSpile##'||a.jfqk_ywhjjh||'##OneSpile##'||a.zzqk_sfsqxnqgzx||'##OneSpile##'||a.zzqk_xnqgzxyapgw||'##OneSpile##'||a.zzqk_sfsqxwqgzx||'##OneSpile##'||a.zzqk_sfsbnczxdk||'##OneSpile##'||a.zzqk_lnyhnczxdhje||'##OneSpile##'||a.zzqk_sfsbgxzxdk||'##OneSpile##'||a.zzqk_lnyhgxzxdhje||'##OneSpile##'||a.zzqk_sfsbbxzxdk||'##OneSpile##'||a.zzqk_lnyhbxzxdhje||'##OneSpile##'||a.zzqk_lnjzxjqk||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##csrq##OneSpile##zzmm##OneSpile##jtdz##OneSpile##jtdh##OneSpile##xsdh##OneSpile##rxqhk##OneSpile##sfgr##OneSpile##sfcj##OneSpile##sfdq##OneSpile##sfjszn##OneSpile##sfssmz##OneSpile##sfwsrh##OneSpile##sfzbh##OneSpile##sfdbh##OneSpile##sffmsxg##OneSpile##sfcnh##OneSpile##sfdsr##OneSpile##rxcj##OneSpile##sxqpm##OneSpile##pjcj##OneSpile##cxdd##OneSpile##jtcy1_xm##OneSpile##jtcy1_cw##OneSpile##jtcy1_nl##OneSpile##jtcy1_gzdwjzw##OneSpile##jtcy1_nsr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_cw##OneSpile##jtcy2_nl##OneSpile##jtcy2_gzdwjzw##OneSpile##jtcy2_nsr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_cw##OneSpile##jtcy3_nl##OneSpile##jtcy3_gzdwjzw##OneSpile##jtcy3_nsr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_cw##OneSpile##jtcy4_nl##OneSpile##jtcy4_gzdwjzw##OneSpile##jtcy4_nsr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_cw##OneSpile##jtcy5_nl##OneSpile##jtcy5_gzdwjzw##OneSpile##jtcy5_nsr##OneSpile##jtcy5_jkzk##OneSpile##jtjjknqk##OneSpile##mzbm_yzbm##OneSpile##mzbm_lxdh##OneSpile##mzbm_lxr##OneSpile##jfqk_jttg##OneSpile##jfqk_qtqytg##OneSpile##jfqk_hjtg##OneSpile##jfqk_yjfy##OneSpile##jfqk_mysffy##OneSpile##jfqk_qnhjfy##OneSpile##jfqk_bxnjttgfybzs##OneSpile##jfqk_ljqf##OneSpile##jfqk_ywhjjh##OneSpile##zzqk_sfsqxnqgzx##OneSpile##zzqk_xnqgzxyapgw##OneSpile##zzqk_sfsqxwqgzx##OneSpile##zzqk_sfsbnczxdk##OneSpile##zzqk_lnyhnczxdhje##OneSpile##zzqk_sfsbgxzxdk##OneSpile##zzqk_lnyhgxzxdhje##OneSpile##zzqk_sfsbbxzxdk##OneSpile##zzqk_lnyhbxzxdhje##OneSpile##zzqk_lnjzxjqk##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_jsxx_knsxx_one(ActionMapping mapping,
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
		String temXszzDj = "";
		String xh = "";
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
		List zxjList = dao.getJsxxGjzxjDj();
		request.setAttribute("gjzxjList", zxjList); 
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!#!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete jsxx_knsxx where xh||nd='"+pkT+"' and xxsh not in ('特别困难','一般困难','比较困难')";
				} else {
					sqlT[i] = "delete jsxx_knsxx where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward("/auditing_jsxx_knsxx.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("jsxx_knsxx", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("jsxx_knsxx", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) jsxx_knsxx";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "jsxx_knsxx";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,zzmm,jtdz,"
				+ "jtdh,xsdh,rxqhk,sfgr,sfcj,sfdq,sfjszn,sfssmz,rxcj,sxqpm,pjcj,cxdd,"
				+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_gzdwjzw,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtjjknqk,mzbm_yzbm,mzbm_lxdh,mzbm_lxr,jfqk_jttg,jfqk_qtqytg,"
				+ "jfqk_hjtg,jfqk_yjfy,jfqk_mysffy,jfqk_qnhjfy,jfqk_bxnjttgfybzs,"
				+ "jfqk_ljqf,jfqk_ywhjjh,zzqk_sfsqxnqgzx,zzqk_xnqgzxyapgw,"
				+ "zzqk_sfsqxwqgzx,zzqk_sfsbnczxdk,zzqk_lnyhnczxdhje,zzqk_sfsbgxzxdk,"
				+ "zzqk_lnyhgxzxdhje,zzqk_sfsbbxzxdk,zzqk_lnyhbxzxdhje,zzqk_lnjzxjqk,"
				+ "sqsj,xysh,xyshyj,xxsh,xxshyj,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,"
				+ "XYSH yesNo from VIEW_jsxx_knsxx where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,zzmm,jtdz,"
				+ "jtdh,xsdh,rxqhk,sfgr,sfcj,sfdq,sfjszn,sfssmz,rxcj,sxqpm,pjcj,cxdd,"
				+ "jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,jtcy1_jkzk,"
				+ "jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_gzdwjzw,jtcy2_nsr,jtcy2_jkzk,"
				+ "jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,jtcy3_jkzk,"
				+ "jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,jtcy4_jkzk,"
				+ "jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,jtcy5_jkzk,"
				+ "jtjjknqk,mzbm_yzbm,mzbm_lxdh,mzbm_lxr,jfqk_jttg,jfqk_qtqytg,"
				+ "jfqk_hjtg,jfqk_yjfy,jfqk_mysffy,jfqk_qnhjfy,jfqk_bxnjttgfybzs,"
				+ "jfqk_ljqf,jfqk_ywhjjh,zzqk_sfsqxnqgzx,zzqk_xnqgzxyapgw,"
				+ "zzqk_sfsqxwqgzx,zzqk_sfsbnczxdk,zzqk_lnyhnczxdhje,zzqk_sfsbgxzxdk,"
				+ "zzqk_lnyhgxzxdhje,zzqk_sfsbbxzxdk,zzqk_lnyhbxzxdhje,zzqk_lnjzxjqk,"
				+ "sqsj,xysh,xyshyj,xxsh,xxshyj,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,"
				+ "XXSH yesNo from VIEW_jsxx_knsxx where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq",
				"zzmm", "jtdz", "jtdh", "xsdh", "rxqhk", "sfgr",
				"sfcj", "sfdq", "sfjszn", "sfssmz", "rxcj", "sxqpm",
				"pjcj", "cxdd", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl",
				"jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_nl", "jtcy2_gzdwjzw", "jtcy2_nsr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl",
				"jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
				"jtcy4_cw", "jtcy4_nl", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy4_jkzk", "jtcy5_xm", "jtcy5_cw", "jtcy5_nl",
				"jtcy5_gzdwjzw", "jtcy5_nsr", "jtcy5_jkzk", "jtjjknqk",
				"mzbm_yzbm", "mzbm_lxdh", "mzbm_lxr", "jfqk_jttg",
				"jfqk_qtqytg", "jfqk_hjtg", "jfqk_yjfy", "jfqk_mysffy",
				"jfqk_qnhjfy", "jfqk_bxnjttgfybzs", "jfqk_ljqf",
				"jfqk_ywhjjh", "zzqk_sfsqxnqgzx", "zzqk_xnqgzxyapgw",
				"zzqk_sfsqxwqgzx", "zzqk_sfsbnczxdk",
				"zzqk_lnyhnczxdhje", "zzqk_sfsbgxzxdk",
				"zzqk_lnyhgxzxdhje", "zzqk_sfsbbxzxdk",
				"zzqk_lnyhbxzxdhje", "zzqk_lnjzxjqk", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "sfwsrh", "sfzbh", "sfdbh",
				"sffmsxg", "sfcnh", "sfdsr", "yesNo" };

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
			if (colList[i].equalsIgnoreCase("zzdjdm")) {
				temXszzDj = rs[i];
			}
			if (colList[i].equalsIgnoreCase("xh")) {
				xh = rs[i];
			}
			request.setAttribute(colList[i], rs[i]);
			hs.put(colList[i], rs[i]);
		}
		hs.put("yesNo", yn);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(11));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "jsxx_knsxx");
		List<String> xszzList = dao.getJsxxStuJzxj(xh);
		if (xszzList.size() != 0) {
			request.setAttribute("notJzxj", "no");
			request.setAttribute("xszzList", xszzList);
		} else {
			request.setAttribute("notJzxj", "is");
		}
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("yesNo", yn);
		hm.put("zzdjdm", temXszzDj);
		request.setAttribute("rs1", hm);
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_jsxx_gjzxdk(ActionMapping mapping,
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
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		realTable = "jsxx_gjzxdk";
		pk = "xh||nd";
		tableName = "view_jsxx_gjzxdk";
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
		String tips = "当前所在位置：学生资助 - 审核 - 国家助学贷款";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学贷款";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "xysh", "xxsh",
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.xslxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.rcbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jtjjknzk||'##OneSpile##'||a.xnxf||'##OneSpile##'||a.jtfybzs||'##OneSpile##'||a.hjxf||'##OneSpile##'||a.bxqdks||'##OneSpile##'||a.jhhkkssj||'##OneSpile##'||a.jhhkjssj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.xslxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.rcbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jtjjknzk||'##OneSpile##'||a.xnxf||'##OneSpile##'||a.jtfybzs||'##OneSpile##'||a.hjxf||'##OneSpile##'||a.bxqdks||'##OneSpile##'||a.jhhkkssj||'##OneSpile##'||a.jhhkjssj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			if(userType.equalsIgnoreCase("xx")){
				colList = new String[] { "bgcolor", "主键", "pk2", "ND", "XH",
						"XM", "XYMC", "ZYMC", "SQSJ", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "ND", "XH",
						"XM", "XYMC", "ZYMC", "SQSJ", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.xslxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.rcbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jtjjknzk||'##OneSpile##'||a.xnxf||'##OneSpile##'||a.jtfybzs||'##OneSpile##'||a.hjxf||'##OneSpile##'||a.bxqdks||'##OneSpile##'||a.jhhkkssj||'##OneSpile##'||a.jhhkjssj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
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
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.xslxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.rxcj||'##OneSpile##'||a.sxqpm||'##OneSpile##'||a.pjcj||'##OneSpile##'||a.rcbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jtjjknzk||'##OneSpile##'||a.xnxf||'##OneSpile##'||a.jtfybzs||'##OneSpile##'||a.hjxf||'##OneSpile##'||a.bxqdks||'##OneSpile##'||a.jhhkkssj||'##OneSpile##'||a.jhhkjssj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.bz||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##csrq##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##jtzz##OneSpile##xslxdh##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gzdwjzw##OneSpile##jtcy1_nsr##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gzdwjzw##OneSpile##jtcy2_nsr##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gzdwjzw##OneSpile##jtcy3_nsr##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gzdwjzw##OneSpile##jtcy4_nsr##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gzdwjzw##OneSpile##jtcy5_nsr##OneSpile##rxcj##OneSpile##sxqpm##OneSpile##pjcj##OneSpile##rcbx##OneSpile##jcqk##OneSpile##jtjjknzk##OneSpile##xnxf##OneSpile##jtfybzs##OneSpile##hjxf##OneSpile##bxqdks##OneSpile##jhhkkssj##OneSpile##jhhkjssj##OneSpile##sqsj##OneSpile##bz##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_jsxx_gjzxdk_one(ActionMapping mapping,
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
			String[] pkDelT = pkDel.split("!!#!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete jsxx_gjzxdk where xh||nd='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete jsxx_gjzxdk where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward("/auditing_jsxx_gjzxdk.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("jsxx_gjzxdk", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("jsxx_gjzxdk", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) jsxx_gjzxdk";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "jsxx_gjzxdk";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtzz,xslxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "rxcj,sxqpm,pjcj,rcbx,jcqk,jtjjknzk,xnxf,"
				+ "jtfybzs,hjxf,bxqdks,jhhkkssj,jhhkjssj,sqsj,"
				+ "bz,xyshyj,xxshyj,xxsh,XYSH yesNo from VIEW_jsxx_GJZXDK where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,xb,sfzh,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtzz,xslxdh,"
				+ "jtcy1_xm,jtcy1_nl,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_xm,jtcy2_nl,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_xm,jtcy4_nl,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_xm,jtcy5_nl,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "rxcj,sxqpm,pjcj,rcbx,jcqk,jtjjknzk,xnxf,"
				+ "jtfybzs,hjxf,bxqdks,jhhkkssj,jhhkjssj,sqsj,"
				+ "bz,xyshyj,xxshyj,xxsh,XXSH yesNo from VIEW_jsxx_GJZXDK where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "csrq", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "jtzz", "xslxdh",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gzdwjzw", "jtcy1_nsr",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gzdwjzw", "jtcy2_nsr",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gzdwjzw", "jtcy3_nsr",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gzdwjzw", "jtcy5_nsr",
				"rxcj", "sxqpm", "pjcj", "rcbx", "jcqk", "jtjjknzk",
				"xnxf", "jtfybzs", "hjxf", "bxqdks", "jhhkkssj",
				"jhhkjssj", "sqsj", "bz", "xyshyj", "xxshyj", "xxsh", "yesNo" };

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
		request.setAttribute("act", "jsxx_gjzxdk");
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_jsxx_sbknbz(ActionMapping mapping,
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
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		String sbsj = checkForm.getSbsj();

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
		realTable = "jsxx_sbknbzsqb";
		pk = "xh||sbsj";
		tableName = "view_jsxx_sbknbzsqb";
		
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
		if (isNull(sbsj)) {
		} else {
			querry.append(" and sbsj='");
			querry.append(sbsj);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		String tips = "当前所在位置：学生资助 - 审核 - 伤、病困难补助";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 伤、病困难补助";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm", "xysh", "xxsh",
					"sbsj", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.sbsj pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_stzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_stzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_stzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_stzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_stzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.sfljs||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfsxg||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.xxqk||'##OneSpile##'||a.bkhywbjgkc||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.psbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.mnjttgfy||'##OneSpile##'||a.mnqphytgfy||'##OneSpile##'||a.mnhjtgfy||'##OneSpile##'||a.mnyjgzfy||'##OneSpile##'||a.mypjshf||'##OneSpile##'||a.mnhjfy||'##OneSpile##'||a.mncjynqgzxbt||'##OneSpile##'||a.cjywqgzxbc||'##OneSpile##'||a.ywshhjbgk||'##OneSpile##'||a.sbsj||'##OneSpile##'||a.zzyy||'##OneSpile##'||a.yyf||'##OneSpile##'||a.qtfy||'##OneSpile##'||a.sfqf||'##OneSpile##'||a.jtbxlpk||'##OneSpile##'||a.xybxlpk||'##OneSpile##'||a.jtjjzkjsqsbbzly||'##OneSpile##'||a.ywzm||'##OneSpile##'||a.nsqbzje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
					+ " 主键,a.xh pk2,a.sbsj pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_stzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_stzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_stzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_stzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_stzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.sfljs||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfsxg||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.xxqk||'##OneSpile##'||a.bkhywbjgkc||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.psbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.mnjttgfy||'##OneSpile##'||a.mnqphytgfy||'##OneSpile##'||a.mnhjtgfy||'##OneSpile##'||a.mnyjgzfy||'##OneSpile##'||a.mypjshf||'##OneSpile##'||a.mnhjfy||'##OneSpile##'||a.mncjynqgzxbt||'##OneSpile##'||a.cjywqgzxbc||'##OneSpile##'||a.ywshhjbgk||'##OneSpile##'||a.sbsj||'##OneSpile##'||a.zzyy||'##OneSpile##'||a.yyf||'##OneSpile##'||a.qtfy||'##OneSpile##'||a.sfqf||'##OneSpile##'||a.jtbxlpk||'##OneSpile##'||a.xybxlpk||'##OneSpile##'||a.jtjjzkjsqsbbzly||'##OneSpile##'||a.ywzm||'##OneSpile##'||a.nsqbzje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			if(userType.equalsIgnoreCase("xx")){
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "SBSJ", "XH",
						"XM", "XYMC", "ZYMC", "BJMC", "NSQBZJE", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "SBSJ", "XH",
						"XM", "XYMC", "ZYMC", "BJMC", "NSQBZJE", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.sbsj pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_stzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_stzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_stzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_stzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_stzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.sfljs||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfsxg||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.xxqk||'##OneSpile##'||a.bkhywbjgkc||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.psbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.mnjttgfy||'##OneSpile##'||a.mnqphytgfy||'##OneSpile##'||a.mnhjtgfy||'##OneSpile##'||a.mnyjgzfy||'##OneSpile##'||a.mypjshf||'##OneSpile##'||a.mnhjfy||'##OneSpile##'||a.mncjynqgzxbt||'##OneSpile##'||a.cjywqgzxbc||'##OneSpile##'||a.ywshhjbgk||'##OneSpile##'||a.sbsj||'##OneSpile##'||a.zzyy||'##OneSpile##'||a.yyf||'##OneSpile##'||a.qtfy||'##OneSpile##'||a.sfqf||'##OneSpile##'||a.jtbxlpk||'##OneSpile##'||a.xybxlpk||'##OneSpile##'||a.jtjjzkjsqsbbzly||'##OneSpile##'||a.ywzm||'##OneSpile##'||a.nsqbzje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
					+ " 主键,a.xh pk2,a.sbsj pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.csrq||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_stzk||'##OneSpile##'||a.jtcy1_gzdwjzw||'##OneSpile##'||a.jtcy1_nsr||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_stzk||'##OneSpile##'||a.jtcy2_gzdwjzw||'##OneSpile##'||a.jtcy2_nsr||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_stzk||'##OneSpile##'||a.jtcy3_gzdwjzw||'##OneSpile##'||a.jtcy3_nsr||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_stzk||'##OneSpile##'||a.jtcy4_gzdwjzw||'##OneSpile##'||a.jtcy4_nsr||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_stzk||'##OneSpile##'||a.jtcy5_gzdwjzw||'##OneSpile##'||a.jtcy5_nsr||'##OneSpile##'||a.sfljs||'##OneSpile##'||a.sfdq||'##OneSpile##'||a.sfsxg||'##OneSpile##'||a.sfcj||'##OneSpile##'||a.xxqk||'##OneSpile##'||a.bkhywbjgkc||'##OneSpile##'||a.cxdd||'##OneSpile##'||a.psbx||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.mnjttgfy||'##OneSpile##'||a.mnqphytgfy||'##OneSpile##'||a.mnhjtgfy||'##OneSpile##'||a.mnyjgzfy||'##OneSpile##'||a.mypjshf||'##OneSpile##'||a.mnhjfy||'##OneSpile##'||a.mncjynqgzxbt||'##OneSpile##'||a.cjywqgzxbc||'##OneSpile##'||a.ywshhjbgk||'##OneSpile##'||a.sbsj||'##OneSpile##'||a.zzyy||'##OneSpile##'||a.yyf||'##OneSpile##'||a.qtfy||'##OneSpile##'||a.sfqf||'##OneSpile##'||a.jtbxlpk||'##OneSpile##'||a.xybxlpk||'##OneSpile##'||a.jtjjzkjsqsbbzly||'##OneSpile##'||a.ywzm||'##OneSpile##'||a.nsqbzje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##csrq##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##jtdz##OneSpile##lxdh##OneSpile##jtcy1_cw##OneSpile##jtcy1_nl##OneSpile##jtcy1_stzk##OneSpile##jtcy1_gzdwjzw##OneSpile##jtcy1_nsr##OneSpile##jtcy2_cw##OneSpile##jtcy2_nl##OneSpile##jtcy2_stzk##OneSpile##jtcy2_gzdwjzw##OneSpile##jtcy2_nsr##OneSpile##jtcy3_cw##OneSpile##jtcy3_nl##OneSpile##jtcy3_stzk##OneSpile##jtcy3_gzdwjzw##OneSpile##jtcy3_nsr##OneSpile##jtcy4_cw##OneSpile##jtcy4_nl##OneSpile##jtcy4_stzk##OneSpile##jtcy4_gzdwjzw##OneSpile##jtcy4_nsr##OneSpile##jtcy5_cw##OneSpile##jtcy5_nl##OneSpile##jtcy5_stzk##OneSpile##jtcy5_gzdwjzw##OneSpile##jtcy5_nsr##OneSpile##sfljs##OneSpile##sfdq##OneSpile##sfsxg##OneSpile##sfcj##OneSpile##xxqk##OneSpile##bkhywbjgkc##OneSpile##cxdd##OneSpile##psbx##OneSpile##jcqk##OneSpile##mnjttgfy##OneSpile##mnqphytgfy##OneSpile##mnhjtgfy##OneSpile##mnyjgzfy##OneSpile##mypjshf##OneSpile##mnhjfy##OneSpile##mncjynqgzxbt##OneSpile##cjywqgzxbc##OneSpile##ywshhjbgk##OneSpile##sbsj##OneSpile##zzyy##OneSpile##yyf##OneSpile##qtfy##OneSpile##sfqf##OneSpile##jtbxlpk##OneSpile##xybxlpk##OneSpile##jtjjzkjsqsbbzly##OneSpile##ywzm##OneSpile##nsqbzje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		map.put("sbsj", sbsj);
		map.put("xh", xh);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("sbsj", sbsj);
		request.setAttribute("xh", xh);
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
		request.setAttribute("act", "zzsf_tkbzsq");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_jsxx_sbknbz_one(ActionMapping mapping,
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
			String[] pkDelT = pkDel.split("!!#!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete jsxx_sbknbzsqb where xh||sbsj='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete jsxx_sbknbzsqb where xh||sbsj='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward("/auditing_jsxx_sbknbz.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("jsxx_sbknbzsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||sbsj", pkVal, request);
			} else {
				ok = StandardOperation.update("jsxx_sbknbzsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||sbsj", pkVal, request);
			}
			if(ok){
				logMsg = "修改(审核) jsxx_sbknbzsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "jsxx_sbknbzsqb";
		pk = "xh||sbsj";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,xm,xb,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtdz,lxdh,"
				+ "jtcy1_cw,jtcy1_nl,jtcy1_stzk,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_cw,jtcy2_nl,jtcy2_stzk,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_cw,jtcy3_nl,jtcy3_stzk,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_cw,jtcy4_nl,jtcy4_stzk,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_cw,jtcy5_nl,jtcy5_stzk,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "sfljs,sfdq,sfsxg,sfcj,xxqk,bkhywbjgkc,cxdd,psbx,jcqk,"
				+ "mnjttgfy,mnqphytgfy,mnhjtgfy,mnyjgzfy,mypjshf,mnhjfy,"
				+ "mncjynqgzxbt,cjywqgzxbc,ywshhjbgk,sbsj,zzyy,yyf,qtfy,"
				+ "sfqf,jtbxlpk,xybxlpk,jtjjzkjsqsbbzly,ywzm,nsqbzje,sqsj,"
				+ "xyshyj,xxshyj,xxsh,XYSH yesNo from view_jsxx_sbknbzsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,xm,xb,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtdz,lxdh,"
				+ "jtcy1_cw,jtcy1_nl,jtcy1_stzk,jtcy1_gzdwjzw,jtcy1_nsr,"
				+ "jtcy2_cw,jtcy2_nl,jtcy2_stzk,jtcy2_gzdwjzw,jtcy2_nsr,"
				+ "jtcy3_cw,jtcy3_nl,jtcy3_stzk,jtcy3_gzdwjzw,jtcy3_nsr,"
				+ "jtcy4_cw,jtcy4_nl,jtcy4_stzk,jtcy4_gzdwjzw,jtcy4_nsr,"
				+ "jtcy5_cw,jtcy5_nl,jtcy5_stzk,jtcy5_gzdwjzw,jtcy5_nsr,"
				+ "sfljs,sfdq,sfsxg,sfcj,xxqk,bkhywbjgkc,cxdd,psbx,jcqk,"
				+ "mnjttgfy,mnqphytgfy,mnhjtgfy,mnyjgzfy,mypjshf,mnhjfy,"
				+ "mncjynqgzxbt,cjywqgzxbc,ywshhjbgk,sbsj,zzyy,yyf,qtfy,"
				+ "sfqf,jtbxlpk,xybxlpk,jtjjzkjsqsbbzly,ywzm,nsqbzje,sqsj,"
				+ "xyshyj,xxshyj,xxsh,XXSH yesNo from view_jsxx_sbknbzsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "xm", "xb", "csrq",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "jtdz", "lxdh",
				"jtcy1_cw", "jtcy1_nl", "jtcy1_stzk", "jtcy1_gzdwjzw", "jtcy1_nsr",
				"jtcy2_cw", "jtcy2_nl", "jtcy2_stzk", "jtcy2_gzdwjzw", "jtcy2_nsr",
				"jtcy3_cw", "jtcy3_nl", "jtcy3_stzk", "jtcy3_gzdwjzw", "jtcy3_nsr",
				"jtcy4_cw", "jtcy4_nl", "jtcy4_stzk", "jtcy4_gzdwjzw", "jtcy4_nsr",
				"jtcy5_cw", "jtcy5_nl", "jtcy5_stzk", "jtcy5_gzdwjzw", "jtcy5_nsr",
				"sfljs", "sfdq", "sfsxg", "sfcj", "xxqk", "bkhywbjgkc", "cxdd",
				"psbx", "jcqk", "mnjttgfy", "mnqphytgfy", "mnhjtgfy", "mnyjgzfy",
				"mypjshf", "mnhjfy", "mncjynqgzxbt", "cjywqgzxbc", "ywshhjbgk",
				"sbsj", "zzyy", "yyf", "qtfy", "sfqf", "jtbxlpk", "xybxlpk",
				"jtjjzkjsqsbbzly", "ywzm", "nsqbzje", "sqsj", "xyshyj", "xxshyj", "xxsh",
				"yesNo" };

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
		request.setAttribute("act", "jsxx_sbknbzsqb");
		return mapping.findForward("success");
	}
}
