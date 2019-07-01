/*
 * 创建日期 2007-11-20 zhoumi
 *
 */
package xgxt.action.gdnzzyjsxy;

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
			if (act.equalsIgnoreCase("gdnz_kns")) {// 广东女子职业技术学院-困难生申请
				myActFwd = gdnz_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("gdnz_knsb")) {// 广东女子职业技术学院-困难生申请表
				myActFwd = gdnz_knsb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnz_kns")) {// 广东女子职业技术学院-困难生审核
				myActFwd = auditing_gdnz_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnz_kns_one")) {// 广东女子职业技术学院-困难生单个审核
				myActFwd = auditing_gdnz_kns_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_jxjsqxx")) {// 广东女子职业技术学院-奖学金申请
				myActFwd = gdnzzyjsxy_jxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_jxjsqxxb")) {// 广东女子职业技术学院-奖学金申请表
				myActFwd = gdnzzyjsxy_jxjsqxxb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_jxjsqxx")) {// 广东女子职业技术学院-奖学金审核
				myActFwd = auditing_gdnzzyjsxy_jxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_jxjsqxx_one")) {// 广东女子职业技术学院-奖学金单个审核
				myActFwd = auditing_gdnzzyjsxy_jxjsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_jxjsqxx")) {// 广东女子职业技术学院-奖学金金额发放记录
				myActFwd = ffje_gdnzzyjsxy_jxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_jxjsqxx_one")) {// 广东女子职业技术学院-奖学金金额发放单个记录
				myActFwd = ffje_gdnzzyjsxy_jxjsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_zxjsqxx")) {// 广东女子职业技术学院-助学金申请
				myActFwd = gdnzzyjsxy_zxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_zxjsqxxb")) {// 广东女子职业技术学院-助学金申请表
				myActFwd = gdnzzyjsxy_zxjsqxxb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_zxjsqxx")) {// 广东女子职业技术学院-助学金审核
				myActFwd = auditing_gdnzzyjsxy_zxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_zxjsqxx_one")) {// 广东女子职业技术学院-助学金单个审核
				myActFwd = auditing_gdnzzyjsxy_zxjsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_zxjsqxx")) {// 广东女子职业技术学院-助学金金额发放记录
				myActFwd = ffje_gdnzzyjsxy_zxjsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_zxjsqxx_one")) {// 广东女子职业技术学院-助学金金额发放单个记录
				myActFwd = ffje_gdnzzyjsxy_zxjsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_knbzsqxx")) {// 广东女子职业技术学院-困难补助申请
				myActFwd = gdnzzyjsxy_knbzsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("gdnzzyjsxy_knbzsqxxb")) {// 广东女子职业技术学院-困难补助申请表
				myActFwd = gdnzzyjsxy_knbzsqxxb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_knbzsqxx")) {// 广东女子职业技术学院-困难补助审核
				myActFwd = auditing_gdnzzyjsxy_knbzsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_gdnzzyjsxy_knbzsqxx_one")) {// 广东女子职业技术学院-困难补助单个审核
				myActFwd = auditing_gdnzzyjsxy_knbzsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_knbzsqxx")) {// 广东女子职业技术学院-困难补助金额发放记录
				myActFwd = ffje_gdnzzyjsxy_knbzsqxx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ffje_gdnzzyjsxy_knbzsqxx_one")) {// 广东女子职业技术学院-困难补助金额发放单个记录
				myActFwd = ffje_gdnzzyjsxy_knbzsqxx_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("xszz_gdnz_lnzzcx")) {// 广东女子职业技术学院-历年资料查询
				myActFwd = xszz_gdnz_lnzzcx(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("xszz_gdnz_lnzzcx_one")) {// 广东女子职业技术学院-历年资料查询-详细信息
				myActFwd = xszz_gdnz_lnzzcx_one(mapping, form, request,
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

	private ActionForward gdnz_kns(ActionMapping mapping, ActionForm form,
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

		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String sfksq = "-1";
//		String knsrs = "";

		String[] titNames = null;
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		titNames = new String[] { "gdnz_kns" };

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		XszzDao xszzDao = new XszzDao();
		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select * from view_gdnzzyjsxy_knsxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String syd = DealString.toGBK(request.getParameter("syd")
							.toString());
					String jtjzdz = DealString.toGBK(request.getParameter(
							"jtjzdz").toString());
					String hkxz = DealString.toGBK(request.getParameter("hkxz")
							.toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String lxdh = DealString.toGBK(request.getParameter("lxdh")
							.toString());
					String jtrks = DealString.toGBK(request.getParameter(
							"jtrks").toString());
					String jtnzsr = DealString.toGBK(request.getParameter(
							"jtnzsr").toString());
					String jtrjysr = DealString.toGBK(request.getParameter(
							"jtrjysr").toString());
					String srly = DealString.toGBK(request.getParameter("srly")
							.toString());
					String xsbrysjxfed = DealString.toGBK(request.getParameter(
							"xsbrysjxfed").toString());
					String jtqk = DealString.toGBK(request.getParameter("jtqk")
							.toString());
					String jtjjqksm = DealString.toGBK(request.getParameter(
							"jtjjqksm").toString());
					String sfycjqgzx = DealString.toGBK(request.getParameter(
							"sfycjqgzx").toString());
					String qxfje = DealString.toGBK(request.getParameter(
							"qxfje").toString());
					String bz = DealString.toGBK(request.getParameter("bz")
							.toString());

					
					sql = "select xxsh from gdnzzyjsxy_knsxx where xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { xh },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("一般困难") || temp[0]
									.equalsIgnoreCase("特别困难"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("gdnzzy_xszz_xsxxb", "xh", xh,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "syd", "hkxz",
								"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr",
								"jtrjysr", "srly", "xsbrysjxfed", "jtqk",
								"jtjjqksm", "sfycjqgzx", "qxfje", "bz" };
						valueForOut = new String[] { xh, syd, hkxz, jtjzdz,
								yzbm, lxdh, jtrks, jtnzsr, jtrjysr, srly,
								xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje,
								bz };
						StandardOperation.insert("gdnzzy_xszz_xsxxb", colName1,
								valueForOut, request);

						StandardOperation.delete("gdnzzyjsxy_knsxx", "xh", xh,
								request);

						colName1 = new String[] { "xh" };

						valueForOut = new String[] { xh };

						boolean ok = false;
						ok = StandardOperation.insert("gdnzzyjsxy_knsxx",
								colName1, valueForOut, request);
						;
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
			xh = xszzForm.getXh();
			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			if (doType != null && doType.equalsIgnoreCase("add")) {
				xh = DealString.toGBK(request.getParameter("xh").toString());
//				String[] jxjksjssj = null;
//				sql = "select count(*) rs from gdnzzyjsxy_knsxx where "
//						+ "xxsh in ('一般困难','特别困难') and xh in "
//						+ "(select xh from view_xsjbxx where xydm=?)";
//				knsrs = dao.getOneRs(sql, new String[] { userDep }, "rs");
//
//				sql1 = "select knsrsxz from xszhsjb a,"
//						+ "view_xsjbxx b where a.XYDM=b.xydm and b.xh=? ";
//				jxjksjssj = dao.getOneRs(sql1, new String[] { xh },
//						new String[] { "knsrsxz" });
//				if (jxjksjssj != null
//						&& (Integer.parseInt(knsrs) < Integer
//								.parseInt(jxjksjssj[0]))) {// /在申请时间范围内
					String syd = DealString.toGBK(request.getParameter("syd")
							.toString());
					String jtjzdz = DealString.toGBK(request.getParameter(
							"jtjzdz").toString());
					String hkxz = DealString.toGBK(request.getParameter("hkxz")
							.toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String lxdh = DealString.toGBK(request.getParameter("lxdh")
							.toString());
					String jtrks = DealString.toGBK(request.getParameter(
							"jtrks").toString());
					String jtnzsr = DealString.toGBK(request.getParameter(
							"jtnzsr").toString());
					String jtrjysr = DealString.toGBK(request.getParameter(
							"jtrjysr").toString());
					String srly = DealString.toGBK(request.getParameter("srly")
							.toString());
					String xsbrysjxfed = DealString.toGBK(request.getParameter(
							"xsbrysjxfed").toString());
					String jtqk = DealString.toGBK(request.getParameter("jtqk")
							.toString());
					String jtjjqksm = DealString.toGBK(request.getParameter(
							"jtjjqksm").toString());
					String sfycjqgzx = DealString.toGBK(request.getParameter(
							"sfycjqgzx").toString());
					String qxfje = DealString.toGBK(request.getParameter(
							"qxfje").toString());
					String bz = DealString.toGBK(request.getParameter("bz")
							.toString());


					sql = "select xxsh from gdnzzyjsxy_knsxx where xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { xh },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("一般困难") || temp[0]
									.equalsIgnoreCase("特别困难"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("gdnzzy_xszz_xsxxb", "xh", xh,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "syd", "hkxz",
								"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr",
								"jtrjysr", "srly", "xsbrysjxfed", "jtqk",
								"jtjjqksm", "sfycjqgzx", "qxfje", "bz" };
						valueForOut = new String[] { xh, syd, hkxz, jtjzdz,
								yzbm, lxdh, jtrks, jtnzsr, jtrjysr, srly,
								xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje,
								bz };
						StandardOperation.insert("gdnzzy_xszz_xsxxb", colName1,
								valueForOut, request);

						StandardOperation.delete("gdnzzyjsxy_knsxx", "xh", xh,
								request);

						colName1 = new String[] { "xh" };

						valueForOut = new String[] { xh };

						boolean ok = false;
						ok = StandardOperation.insert("gdnzzyjsxy_knsxx",
								colName1, valueForOut, request);
						if (ok) {
							logMsg = "申请" + titName;
							Base.log(request, logMsg, sUName);
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
//				} else {// 不在申请时间范围内
//					sfksq = "-2";
//					request.setAttribute("sfksq", sfksq);// 不能申请
//				}
			}
		}

		sql = "select * from view_gdnzzyjsxy_knsxx where xh=?";
		outValue = dao.getOneRs(sql, new String[] { xh }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
						+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
						+ "jtjjqksm,sfycjqgzx,qxfje,bz from view_gdnzzy_xszz_xsxxb where xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
						"syd", "hkxz", "jtjzdz", "yzbm", "lxdh", "jtrks",
						"jtnzsr", "jtrjysr", "srly", "xsbrysjxfed", "jtqk",
						"jtjjqksm", "sfycjqgzx", "qxfje", "bz" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);

				if (outVal == null) {
					sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc "
							+ "from view_xsjbxx where xh=?";
					String[] colNameT = new String[] { "xh", "xm", "xb",
							"sfzh", "nj", "xydm", "xymc", "zydm", "zymc",
							"bjdm", "bjmc" };
					String[] outValT = dao.getOneRs(sql, new String[] { xh },
							colNameT);

					if (outValT == null) {
					} else {
						for (int i = 0; i < colNameT.length; i++) {
							if (null != outValT[i]) {
								map.put(colNameT[i], outValT[i]);
							} else {
								map.put(colNameT[i], "");
							}
						}
					}
				} else {
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
					map.put(outString[i].toLowerCase(), outValue[i]);
				} else {
					map.put(outString[i].toLowerCase(), "");
				}
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward gdnz_knsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String syd = DealString.toGBK(request.getParameter("syd").toString());
		String hkxz = DealString.toGBK(request.getParameter("hkxz").toString());
		String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
				.toString());
		String jtrjysr = DealString.toGBK(request.getParameter("jtrjysr")
				.toString());
		String srly = DealString.toGBK(request.getParameter("srly").toString());
		String xsbrysjxfed = DealString.toGBK(request.getParameter(
				"xsbrysjxfed").toString());
		String jtqk = DealString.toGBK(request.getParameter("jtqk").toString()
				);
		String jtjjqksm = DealString.toGBK(request.getParameter("jtjjqksm")
				.toString());
		String sfycjqgzx = DealString.toGBK(request.getParameter("sfycjqgzx")
				.toString());
		String qxfje = DealString.toGBK(request.getParameter("qxfje")
				.toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		String kncdpm = DealString.toGBK(request.getParameter("kncdpm")
				.toString());
		String xysh = DealString.toGBK(request.getParameter("xysh").toString());
		String xxsh = DealString.toGBK(request.getParameter("xxsh").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";
		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);
		
		if("未审核".equalsIgnoreCase(xysh)){
			xysh = " ";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh = " ";
		}

		String[] outValue = new String[] { xh, xm, xb, sfzh, nj, xymc, zymc,
				bjmc, syd, hkxz, jtjzdz, yzbm, lxdh, jtrks, jtnzsr, jtrjysr,
				srly, xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje, bz,
				kncdpm, xyshyj, xxshyj, xysh, xxsh, sqsj };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "syd", "hkxz", "jtjzdz", "yzbm",
				"lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly", "xsbrysjxfed",
				"jtqk", "jtjjqksm", "sfycjqgzx", "qxfje", "bz", "kncdpm",
				"xyshyj", "xxshyj", "xysh", "xxsh", "sqsj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward auditing_gdnz_kns(ActionMapping mapping,
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
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
			isQuery = "no";
		}
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
		realTable = "gdnzzyjsxy_knsxx";
		pk = "xh";
		tableName = "view_gdnzzyjsxy_knsxx";
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
		String tips = "当前所在位置：学生资助 - 审核 - 贫困生";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 贫困生";
			colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "xymc", "zymc", "bjmc",
					"kncdpm", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' "
					+ "then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.kncdpm||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%困%' "
					+ "then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.kncdpm||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
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
				colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "bjmc",
						"sqsj", "xysh", "kncdpm", "" };
			} else {
				colList = new String[] { "bgcolor", "主键", "pk2", "xh", "xm", "bjmc",
						"sqsj", "kncdpm", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%困%' "
						+ "then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh in ('一般困难','特别困难') order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.kncdpm||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('一般困难','特别困难') order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%困%' "
						+ "then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.kncdpm||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj col from "
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
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##kncdpm##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##sqsj";
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
		request.setAttribute("isQuery", isQuery);
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

	private ActionForward auditing_gdnz_kns_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
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
		String kncdpm = DealString.toGBK(request.getParameter("kncdpm"));
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
					sqlT[i] = "delete gdnzzyjsxy_knsxx where xh='"+pkT+"' and xxsh not in ('一般困难','特别困难')";
				} else {
					sqlT[i] = "delete gdnzzyjsxy_knsxx where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_gdnz_kns.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("gdnzzyjsxy_knsxx", new String[] {
						"xysh", "xyshyj", "kncdpm" }, new String[] { yesNo,
						xyshyj, kncdpm }, "xh", pkVal, request);
			} else {
				ok = StandardOperation.update("gdnzzyjsxy_knsxx", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh", pkVal, request);
			}
			if(ok){
				logMsg = "修改（审核） gdnzzyjsxy_knsxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(pkVal);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_knsxx";
		pk = "xh";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,sqsj,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,"
					+ "hkxz,jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,"
					+ "jtqk,jtjjqksm,sfycjqgzx,qxfje,bz,kncdpm,xysh,xyshyj,xxsh,"
					+ "xxshyj,XYSH yesNo from view_gdnzzyjsxy_knsxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,sqsj,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,"
					+ "hkxz,jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,"
					+ "jtqk,jtjjqksm,sfycjqgzx,qxfje,bz,kncdpm,xysh,xyshyj,xxsh,"
					+ "xxshyj,XXSH yesNo from view_gdnzzyjsxy_knsxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "sqsj", "xh", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sfycjqgzx", "qxfje", "bz",
				"kncdpm", "xysh", "xyshyj", "xxsh", "xxshyj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(10));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_knsxx");
		return mapping.findForward("success");
	}

	private ActionForward gdnzzyjsxy_jxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String[] titNames = null;

		titNames = new String[] { "gdnzzyjsxy_jxjsqxx" };
		
		String nd = Base.currNd;

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		XszzDao xszzDao = new XszzDao();
		String sql = "";
		String[] outString = new String[] {};
		sql = "select * from view_gdnzzyjsxy_jxjsqxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String jxjdm = "";
		String jxjdm1 = "";
		List jxjList = xszzDao.getGdJxjList();
		request.setAttribute("jxjList", jxjList);
		jxjdm1 = request.getParameter("jxjdm1");
		if(jxjdm1 != null){
			jxjdm1 = DealString.toGBK(jxjdm1);
			String[] jxjTemp = jxjdm1.split("!!splitOne!!");
			if(jxjTemp != null){
				jxjdm = jxjTemp[0];
			}
		}
		String queryPK = request.getParameter("queryPK");
		if ((null != queryPK) && (!"".equalsIgnoreCase(queryPK))){
			queryPK = DealString.toGBK(queryPK);
			sql = "select jxjdm||'!!splitOne!!'||jlmc||'!!splitOne!!'||bjdw||"
				+ "'!!splitOne!!'||jlze||'!!splitOne!!'||jxjdj||'!!splitOne!!'||"
				+ "jlgrje jxjdm1 from GdNZZY_xszz_jzjdmb where jxjdm=?";
			String tJxjdm = dao.getOneRs(sql, new String[] { queryPK }, "jxjdm1");
			jxjdm = queryPK;
			jxjdm1 = tJxjdm;
		}
		String pkVal = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
				String syd = DealString.toGBK(request.getParameter("syd")
						.toString());
				String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
						.toString());
				String hkxz = DealString.toGBK(request.getParameter("hkxz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String lxdh = DealString.toGBK(request.getParameter("lxdh")
						.toString());
				String jtrks = DealString.toGBK(request.getParameter("jtrks")
						.toString());
				String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
						.toString());
				String jtrjysr = DealString.toGBK(request.getParameter(
						"jtrjysr").toString());
				String srly = DealString.toGBK(request.getParameter("srly")
						.toString());
				String xsbrysjxfed = DealString.toGBK(request.getParameter(
						"xsbrysjxfed").toString());
				String jtqk = DealString.toGBK(request.getParameter("jtqk")
						.toString());
				String jtjjqksm = DealString.toGBK(request.getParameter(
						"jtjjqksm").toString());
				String sxqcjpm = DealString.toGBK(request.getParameter(
						"sxqcjpm").toString());
				String zhcppm = DealString.toGBK(request.getParameter(
						"zhcppm").toString());
				String sfycjqgzx = DealString.toGBK(request.getParameter(
						"sfycjqgzx").toString());
				String qxfje = DealString.toGBK(request.getParameter("qxfje")
						.toString());
				String bz = DealString.toGBK(request.getParameter("bz")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + jxjdm;
				}
				
				sql = "select xxsh from gdnzzyjsxy_jxjsqxx where xh||nd||jxjdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("gdnzzy_xszz_xsxxb", "xh", xh,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "syd", "hkxz",
							"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr",
							"jtrjysr", "srly", "xsbrysjxfed", "jtqk",
							"jtjjqksm", "sfycjqgzx", "qxfje", "bz", "sxqcjpm",
							"zhcppm" };
					valueForOut = new String[] { xh, syd, hkxz, jtjzdz, yzbm,
							lxdh, jtrks, jtnzsr, jtrjysr, srly, xsbrysjxfed,
							jtqk, jtjjqksm, sfycjqgzx, qxfje, bz, sxqcjpm,
							zhcppm };
					StandardOperation.insert("gdnzzy_xszz_xsxxb", colName1,
							valueForOut, request);

					StandardOperation.delete("gdnzzyjsxy_jxjsqxx",
							"xh||nd||jxjdm", pkVal, request);

					colName1 = new String[] { "xh", "nd", "jxjdm" };

					valueForOut = new String[] { xh, nd, jxjdm };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_jxjsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm",
								"奖学金" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "奖学金", nd, jxjdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
				String syd = DealString.toGBK(request.getParameter("syd")
						.toString());
				String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
						.toString());
				String hkxz = DealString.toGBK(request.getParameter("hkxz")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String lxdh = DealString.toGBK(request.getParameter("lxdh")
						.toString());
				String jtrks = DealString.toGBK(request.getParameter("jtrks")
						.toString());
				String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
						.toString());
				String jtrjysr = DealString.toGBK(request.getParameter(
						"jtrjysr").toString());
				String srly = DealString.toGBK(request.getParameter("srly")
						.toString());
				String xsbrysjxfed = DealString.toGBK(request.getParameter(
						"xsbrysjxfed").toString());
				String jtqk = DealString.toGBK(request.getParameter("jtqk")
						.toString());
				String jtjjqksm = DealString.toGBK(request.getParameter(
						"jtjjqksm").toString());
				String sxqcjpm = DealString.toGBK(request.getParameter(
						"sxqcjpm").toString());
				String zhcppm = DealString.toGBK(request.getParameter(
						"zhcppm").toString());
				String sfycjqgzx = DealString.toGBK(request.getParameter(
						"sfycjqgzx").toString());
				String qxfje = DealString.toGBK(request.getParameter("qxfje")
						.toString());
				String bz = DealString.toGBK(request.getParameter("bz")
						.toString());
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + jxjdm;
				}

				sql = "select xxsh from gdnzzyjsxy_jxjsqxx where xh||nd||jxjdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("gdnzzy_xszz_xsxxb", "xh", xh,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "syd", "hkxz",
							"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr",
							"jtrjysr", "srly", "xsbrysjxfed", "jtqk",
							"jtjjqksm", "sfycjqgzx", "qxfje", "bz", "sxqcjpm",
							"zhcppm" };
					valueForOut = new String[] { xh, syd, hkxz, jtjzdz, yzbm,
							lxdh, jtrks, jtnzsr, jtrjysr, srly, xsbrysjxfed,
							jtqk, jtjjqksm, sfycjqgzx, qxfje, bz, sxqcjpm,
							zhcppm };
					StandardOperation.insert("gdnzzy_xszz_xsxxb", colName1,
							valueForOut, request);

					StandardOperation.delete("gdnzzyjsxy_jxjsqxx",
							"xh||nd||jxjdm", pkVal, request);

					colName1 = new String[] { "xh", "nd", "jxjdm" };

					valueForOut = new String[] { xh, nd, jxjdm };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_jxjsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm",
								"奖学金" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "奖学金", nd, jxjdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
			pkVal = xh + nd + jxjdm;
		}

		sql = "select * from view_gdnzzyjsxy_jxjsqxx where xh||nd||jxjdm=?";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
						+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
						+ "jtjjqksm,sfycjqgzx,qxfje,bz,sxqcjpm,zhcppm from "
						+ "view_gdnzzy_xszz_xsxxb where xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
						"syd", "hkxz", "jtjzdz", "yzbm", "lxdh", "jtrks",
						"jtnzsr", "jtrjysr", "srly", "xsbrysjxfed", "jtqk",
						"jtjjqksm", "sfycjqgzx", "qxfje", "bz", "sxqcjpm", "zhcppm" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);

				if (outVal == null) {
					sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc "
							+ "from view_xsjbxx where xh=?";
					String[] colNameT = new String[] { "xh", "xm", "xb",
							"sfzh", "nj", "xydm", "xymc", "zydm", "zymc",
							"bjdm", "bjmc" };
					String[] outValT = dao.getOneRs(sql, new String[] { xh },
							colNameT);

					if (outValT == null) {
					} else {
						for (int i = 0; i < colNameT.length; i++) {
							if (null != outValT[i]) {
								map.put(colNameT[i], outValT[i]);
							} else {
								map.put(colNameT[i], "");
							}
						}
					}
				} else {
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
			map.put("jxjdm1", jxjdm1);
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
					map.put(outString[i].toLowerCase(), outValue[i]);
				} else {
					map.put(outString[i].toLowerCase(), "");
				}
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward gdnzzyjsxy_jxjsqxxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String syd = DealString.toGBK(request.getParameter("syd").toString());
		String hkxz = DealString.toGBK(request.getParameter("hkxz").toString());
		String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
				.toString());
		String jtrjysr = DealString.toGBK(request.getParameter("jtrjysr")
				.toString());
		String srly = DealString.toGBK(request.getParameter("srly").toString());
		String xsbrysjxfed = DealString.toGBK(request.getParameter(
				"xsbrysjxfed").toString());
		String jtqk = DealString.toGBK(request.getParameter("jtqk").toString()
				);
		String jtjjqksm = DealString.toGBK(request.getParameter("jtjjqksm")
				.toString());
		String sfycjqgzx = DealString.toGBK(request.getParameter("sfycjqgzx")
				.toString());
		String qxfje = DealString.toGBK(request.getParameter("qxfje")
				.toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm")
				.toString());
		String zhcppm = DealString.toGBK(request.getParameter("zhcppm")
				.toString());
		String jxjdm1 = DealString.toGBK(request.getParameter("jxjdm1")
				.toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";
		
		String jxjdm = "";
		String jlmc = "";
		String bjdw = "";
		String jlze = "";
		String jxjdj = "";
		String jlgrje = "";
		String[] jxjTemp = jxjdm1.split("!!splitOne!!");
		if(jxjTemp != null){
			jxjdm = jxjTemp[0];
			jlmc = jxjTemp[1];
			bjdw = jxjTemp[2];
			jlze = jxjTemp[3];
			jxjdj = jxjTemp[4];
			jlgrje = jxjTemp[5];
		}
		
		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		String[] outValue = new String[] { xh, xm, xb, sfzh, nj, xymc, zymc,
				bjmc, syd, hkxz, jtjzdz, yzbm, lxdh, jtrks, jtnzsr, jtrjysr,
				srly, xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje, bz,
				xyshyj, xxshyj, sqsj, sxqcjpm, zhcppm, jxjdm, jlmc, bjdw, jlze,
				jxjdj, jlgrje, nd };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "syd", "hkxz", "jtjzdz", "yzbm",
				"lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly", "xsbrysjxfed",
				"jtqk", "jtjjqksm", "sfycjqgzx", "qxfje", "bz", "xyshyj",
				"xxshyj", "sqsj", "sxqcjpm", "zhcppm", "jxjdm", "jlmc", "bjdw",
				"jlze", "jxjdj", "jlgrje", "nd" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_jxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		HashMap<String, String> map = new HashMap<String, String>();
		String isQuery = request.getParameter("isQuery");
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		String jxjdm1 = request.getParameter("jxjdm1");
		String jxjdm = "";
		if(!isNull(jxjdm1)){
			jxjdm1 = DealString.toGBK(jxjdm1);
			String[] jxjTemp = jxjdm1.split("!!splitOne!!");
			jxjdm = jxjTemp[0];
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
		realTable = "gdnzzyjsxy_jxjsqxx";
		pk = "xh||nd||jxjdm";
		tableName = "view_gdnzzyjsxy_jxjsqxx";
		
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
			querry.append("' ");;
		}
		if (isNull(jxjdm)) {
		} else {
			querry.append(" and jxjdm='");
			querry.append(jxjdm);
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
		String tips = "当前所在位置：学生资助 - 审核 - 奖学金";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 奖学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "jlmc", "jlgrje",
					"sqsj", "xysh", "xxsh", "ffrq", "ffje", "cjje", "sfje" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.jxjdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.jxjdm||'##OneSpile##'||a.jlmc||'##OneSpile##'||a.bjdw||'##OneSpile##'||a.jlze||'##OneSpile##'||a.jxjdj||'##OneSpile##'||a.jlgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
					+ " 主键,a.xh pk2,a.jxjdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.jxjdm||'##OneSpile##'||a.jlmc||'##OneSpile##'||a.bjdw||'##OneSpile##'||a.jlze||'##OneSpile##'||a.jxjdj||'##OneSpile##'||a.jlgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "bjmc",
					"sqsj", "jlmc", "jxjdj", "jlgrje", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%通过%' "
						+ "then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jxjdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.jxjdm||'##OneSpile##'||a.jlmc||'##OneSpile##'||a.bjdw||'##OneSpile##'||a.jlze||'##OneSpile##'||a.jxjdj||'##OneSpile##'||a.jlgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%通过%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.jxjdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.jxjdm||'##OneSpile##'||a.jlmc||'##OneSpile##'||a.bjdw||'##OneSpile##'||a.jlze||'##OneSpile##'||a.jxjdj||'##OneSpile##'||a.jlgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##jxjdm##OneSpile##jlmc##OneSpile##bjdw##OneSpile##jlze##OneSpile##jxjdj##OneSpile##jlgrje##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		map.put("jxjdm1", jxjdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("jxjList", xszzDao.getGdJxjList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_jxjsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_jxjsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
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

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length*2];
			int j = 0;
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[j] = "delete gdnzzyjsxy_jxjsqxx where xh||nd||jxjdm='"+pkT+"' and xxsh<>'通过'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zzxldm='奖学金"+pkT+"'";
					j++;
				} else {
					sqlT[j] = "delete gdnzzyjsxy_jxjsqxx where xh||nd||jxjdm='"+pkT+"'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zzxldm='奖学金"+pkT+"'";
					j++;
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_gdnzzyjsxy_jxjsqxx.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("gdnzzyjsxy_jxjsqxx",
						new String[] { "xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xh||nd||jxjdm", pkVal,
						request);
			} else {
				ok = StandardOperation.update("gdnzzyjsxy_jxjsqxx",
						new String[] { "xxsh", "xxshyj" }, new String[] {
								yesNo, xxshyj }, "xh||nd||jxjdm", pkVal,
						request);
			}
			if(ok){
				logMsg = "修改(审核) gdnzzyjsxy_jxjsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_jxjsqxx";
		pk = "xh||nd||jxjdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,jtjjqksm,"
					+ "sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,jxjdm,jlmc,bjdw,jlze,jxjdj,jlgrje,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,XYSH yesNo from "
					+ "view_gdnzzyjsxy_jxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,jtjjqksm,"
					+ "sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,jxjdm,jlmc,bjdw,jlze,jxjdj,jlgrje,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,XXSH yesNo from "
					+ "view_gdnzzyjsxy_jxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "jxjdm", "jlmc", "bjdw", "jlze",
				"jxjdj", "jlgrje", "ffrq", "ffje", "cjje", "sfje", "sqsj",
				"xysh", "xyshyj", "xxsh", "xxshyj", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_jxjsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_jxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
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
		String jxjdm1 = request.getParameter("jxjdm1");
		String jxjdm = "";
		if(!isNull(jxjdm1)){
			jxjdm1 = DealString.toGBK(jxjdm1);
			String[] jxjTemp = jxjdm1.split("!!splitOne!!");
			jxjdm = jxjTemp[0];
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
		realTable = "gdnzzyjsxy_jxjsqxx";
		pk = "xh||nd||jxjdm";
		tableName = "view_gdnzzyjsxy_jxjsqxx";
		colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "bjmc",
				"sqsj", "jlmc", "jxjdj", "jlgrje", "ffrq", "ffje", "qxfje" };
		if (isNull(jxjdm)) {
		} else {
			querry.append(" and jxjdm='");
			querry.append(jxjdm);
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
		sql = "select (case when nvl(a.ffje,'0')='0' then '#CCCCCC' else '#FFFFFF' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " 主键,a.xh pk2,a.* from "
				+ tableName
				+ " a"
				+ querry.toString() + " and xxsh='通过' order by xxsh desc) a";
		sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.jxjdm||'##OneSpile##'||a.jlmc||'##OneSpile##'||a.bjdw||'##OneSpile##'||a.jlze||'##OneSpile##'||a.jxjdj||'##OneSpile##'||a.jlgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj col from "
			+ tableName
			+ " a"
			+ querry.toString() + " and xxsh='通过' order by xxsh desc";
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##jxjdm##OneSpile##jlmc##OneSpile##bjdw##OneSpile##jlze##OneSpile##jxjdj##OneSpile##jlgrje##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj";
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
		map.put("jxjdm1", jxjdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("jxjList", xszzDao.getGdJxjList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_jxjsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_jxjsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String ffje = DealString.toGBK(request.getParameter("ffje"));
		String cjje = DealString.toGBK(request.getParameter("cjje"));
		String sfje = DealString.toGBK(request.getParameter("sfje"));
		String sql = "";
		String pkDel = DealString.toGBK(request.getParameter("pkDel"));
		request.setAttribute("pkDel", pkDel);
		HttpSession session = request.getSession();
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		String sj = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sj from dual",
				new String[] {}, "sj");

		if ("more".equalsIgnoreCase(actDo)) {
			String toDo = request.getParameter("toDo");
			if ("save".equalsIgnoreCase(toDo)) {
				String[] pkDelT = pkDel.split("!!splitOne!!");
				String[] sqlT = new String[pkDelT.length];
				for (int i = 1; i < pkDelT.length; i++) {
					String pkT = pkDelT[i];
					sqlT[i] = "update gdnzzyjsxy_jxjsqxx set ffje='" + ffje
							+ "',cjje='" + cjje + "',sfje='" + sfje
							+ "',ffrq='" + sj + "' where xh||nd||jxjdm='" + pkT
							+ "'";
				}
				dao.runBatch(sqlT);
				return null;
			}
			ActionForward newFwd = new ActionForward(
					"/xszz/gdnzzyjsxy/ffje_gdnzzyjsxy_jxjsqxx_more.jsp", false);
			return newFwd;
		}
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		
		String xyshryhm = session.getAttribute("userName").toString();
		
		String pk = "";
		String[] colList = new String[] {};
		String yn = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		if (actDo.equalsIgnoreCase("save")) {
			ok = StandardOperation.update("gdnzzyjsxy_jxjsqxx",
					new String[] { "ffje", "cjje", "sfje", "ffrq" },
					new String[] { ffje, cjje, sfje, sj },
					"xh||nd||jxjdm", pkVal, request);
			if(ok){
				logMsg = "修改 gdnzzyjsxy_jxjsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_jxjsqxx";
		pk = "xh||nd||jxjdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,jtjjqksm,"
					+ "sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,jxjdm,jlmc,bjdw,jlze,jxjdj,jlgrje,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,XYSH yesNo from "
					+ "view_gdnzzyjsxy_jxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,jtjjqksm,"
					+ "sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,jxjdm,jlmc,bjdw,jlze,jxjdj,jlgrje,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,XXSH yesNo from "
					+ "view_gdnzzyjsxy_jxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "jxjdm", "jlmc", "bjdw", "jlze",
				"jxjdj", "jlgrje", "ffrq", "ffje", "cjje", "sfje", "sqsj",
				"xysh", "xyshyj", "xxsh", "xxshyj", "yesNo" };

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
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_jxjsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward gdnzzyjsxy_zxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String[] titNames = null;

		titNames = new String[] { "gdnzzyjsxy_zxjsqxx" };
		
		String nd = Base.currNd;

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		XszzDao xszzDao = new XszzDao();
		String sql = "";
		String[] outString = new String[] {};
		sql = "select * from view_gdnzzyjsxy_zxjsqxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String zxjdm = "";
		String zxjdm1 = "";
		List zxjList = xszzDao.getGdZxjList();
		request.setAttribute("zxjList", zxjList);
		zxjdm1 = request.getParameter("zxjdm1");
		if(zxjdm1 != null){
			zxjdm1 = DealString.toGBK(zxjdm1);
			String[] zxjTemp = zxjdm1.split("!!splitOne!!");
			if(zxjTemp != null){
				zxjdm = zxjTemp[0];
			}
		}
		String queryPK = request.getParameter("queryPK");
		if ((null != queryPK) && (!"".equalsIgnoreCase(queryPK))){
			queryPK = DealString.toGBK(queryPK);
			sql = "select zxjdm||'!!splitOne!!'||zzmc||'!!splitOne!!'||zzdw||"
					+ "'!!splitOne!!'||zzze||'!!splitOne!!'||zzgrje zxjdm1,zzmc "
					+ "from GdNZZY_xszz_zxjdmb where zxjdm=?";
			String tZxjdm = dao.getOneRs(sql, new String[] { queryPK }, "zxjdm1");
			zxjdm = queryPK;
			zxjdm1 = tZxjdm;
		}
		String pkVal = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + zxjdm;
				}
				sql = "select xxsh from gdnzzyjsxy_zxjsqxx where xh||nd||zxjdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm"));
					String zhcppm = DealString.toGBK(request.getParameter("zhcppm"));
					StandardOperation.update("gdnzzy_xszz_xsxxb", new String[]{"sxqcjpm", "zhcppm"},
							new String[]{sxqcjpm,zhcppm}, "xh", xh, request);
					
					StandardOperation.delete("gdnzzyjsxy_zxjsqxx",
							"xh||nd||zxjdm", pkVal, request);

					String[] colName1 = new String[] { "xh", "nd", "zxjdm" };

					String[] valueForOut = new String[] { xh, nd, zxjdm };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_zxjsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm",
								"助学金" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "助学金", nd, zxjdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + zxjdm;
				}

				sql = "select xxsh from gdnzzyjsxy_zxjsqxx where xh||nd||zxjdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm"));
					String zhcppm = DealString.toGBK(request.getParameter("zhcppm"));
					StandardOperation.update("gdnzzy_xszz_xsxxb", new String[]{"sxqcjpm", "zhcppm"},
							new String[]{sxqcjpm,zhcppm}, "xh", xh, request);
					
					StandardOperation.delete("gdnzzyjsxy_zxjsqxx",
							"xh||nd||zxjdm", pkVal, request);

					String[] colName1 = new String[] { "xh", "nd", "zxjdm" };

					String[] valueForOut = new String[] { xh, nd, zxjdm };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_zxjsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm",
								"助学金" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "助学金", nd, zxjdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
			pkVal = xh + nd + zxjdm;
		}

		sql = "select * from view_gdnzzyjsxy_zxjsqxx where xh||nd||zxjdm=?";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.syd,a.hkxz,"
						+ "a.jtjzdz,a.yzbm,a.lxdh,a.jtrks,a.jtnzsr,a.jtrjysr,a.srly,a.xsbrysjxfed,a.jtqk,"
						+ "a.jtjjqksm,a.sfycjqgzx,a.qxfje,a.bz,a.sxqcjpm,a.zhcppm,b.kncdpm from "
						+ "view_gdnzzy_xszz_xsxxb a,view_gdnzzyjsxy_knsxx b where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
						"syd", "hkxz", "jtjzdz", "yzbm", "lxdh", "jtrks",
						"jtnzsr", "jtrjysr", "srly", "xsbrysjxfed", "jtqk",
						"jtjjqksm", "sfycjqgzx", "qxfje", "bz", "sxqcjpm", "zhcppm",
						"kncdpm" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);

				if (outVal == null) {
					request.setAttribute("notKNS", "is");
				} else {
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
			map.put("zxjdm1", zxjdm1);
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
					map.put(outString[i].toLowerCase(), outValue[i]);
				} else {
					map.put(outString[i].toLowerCase(), "");
				}
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward gdnzzyjsxy_zxjsqxxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String syd = DealString.toGBK(request.getParameter("syd").toString());
		String hkxz = DealString.toGBK(request.getParameter("hkxz").toString());
		String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
				.toString());
		String jtrjysr = DealString.toGBK(request.getParameter("jtrjysr")
				.toString());
		String srly = DealString.toGBK(request.getParameter("srly").toString());
		String xsbrysjxfed = DealString.toGBK(request.getParameter(
				"xsbrysjxfed").toString());
		String jtqk = DealString.toGBK(request.getParameter("jtqk").toString()
				);
		String jtjjqksm = DealString.toGBK(request.getParameter("jtjjqksm")
				.toString());
		String sfycjqgzx = DealString.toGBK(request.getParameter("sfycjqgzx")
				.toString());
		String qxfje = DealString.toGBK(request.getParameter("qxfje")
				.toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm")
				.toString());
		String zhcppm = DealString.toGBK(request.getParameter("zhcppm")
				.toString());
		String kncdpm = DealString.toGBK(request.getParameter("kncdpm")
				.toString());
		String zxjdm1 = DealString.toGBK(request.getParameter("zxjdm1")
				.toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";
		
		String zxjdm = "";
		String zzmc = "";
		String zzdw = "";
		String zzze = "";
		String zzgrje = "";
		String[] zxjTemp = zxjdm1.split("!!splitOne!!");
		if(zxjTemp != null){
			zxjdm = zxjTemp[0];
			zzmc = zxjTemp[1];
			zzdw = zxjTemp[2];
			zzze = zxjTemp[3];
			zzgrje = zxjTemp[4];
		}
		
		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		String[] outValue = new String[] { xh, xm, xb, sfzh, nj, xymc, zymc,
				bjmc, syd, hkxz, jtjzdz, yzbm, lxdh, jtrks, jtnzsr, jtrjysr,
				srly, xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje, bz,
				xyshyj, xxshyj, sqsj, sxqcjpm, zhcppm, kncdpm, zxjdm, zzmc, zzdw, zzze,
				zzgrje, nd };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "syd", "hkxz", "jtjzdz", "yzbm",
				"lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly", "xsbrysjxfed",
				"jtqk", "jtjjqksm", "sfycjqgzx", "qxfje", "bz", "xyshyj",
				"xxshyj", "sqsj", "sxqcjpm", "zhcppm", "kncdpm", "zxjdm", "zzmc",
				"zzdw", "zzze", "zzgrje", "nd" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_zxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		HashMap<String, String> map = new HashMap<String, String>();
		String isQuery = request.getParameter("isQuery");
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		String zxjdm1 = request.getParameter("zxjdm1");
		String zxjdm = "";
		if(!isNull(zxjdm1)){
			zxjdm1 = DealString.toGBK(zxjdm1);
			String[] zxjTemp = zxjdm1.split("!!splitOne!!");
			zxjdm = zxjTemp[0];
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
		realTable = "gdnzzyjsxy_zxjsqxx";
		pk = "xh||nd||zxjdm";
		tableName = "view_gdnzzyjsxy_zxjsqxx";
		
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
		if (isNull(zxjdm)) {
		} else {
			querry.append(" and zxjdm='");
			querry.append(zxjdm);
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
		String tips = "当前所在位置：学生资助 - 审核 - 助学金";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 助学金";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "zzmc", "zzgrje",
					"sqsj", "xysh", "xxsh", "ffrq", "ffje", "cjje", "sfje" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.zxjdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.zxjdm||'##OneSpile##'||a.zzmc||'##OneSpile##'||a.zzdw||'##OneSpile##'||a.zzze||'##OneSpile##'||a.zzgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
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
					+ " 主键,a.xh pk2,a.zxjdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.zxjdm||'##OneSpile##'||a.zzmc||'##OneSpile##'||a.zzdw||'##OneSpile##'||a.zzze||'##OneSpile##'||a.zzgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "bjmc",
					"sqsj", "zzmc", "zzze", "zzgrje", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%通过%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.zxjdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.zxjdm||'##OneSpile##'||a.zzmc||'##OneSpile##'||a.zzdw||'##OneSpile##'||a.zzze||'##OneSpile##'||a.zzgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%通过%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.zxjdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.zxjdm||'##OneSpile##'||a.zzmc||'##OneSpile##'||a.zzdw||'##OneSpile##'||a.zzze||'##OneSpile##'||a.zzgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##zxjdm##OneSpile##zzmc##OneSpile##zzdw##OneSpile##zzze##OneSpile##zzgrje##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##kncdpm";
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
		map.put("zxjdm1", zxjdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("zxjList", xszzDao.getGdZxjList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_zxjsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_zxjsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
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

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length*2];
			int j = 0;
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[j] = "delete gdnzzyjsxy_zxjsqxx where xh||nd||zxjdm='"+pkT+"' and xxsh<>'通过'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zxjdm='助学金"+pkT+"'";
					j++;
				} else {
					sqlT[j] = "delete gdnzzyjsxy_zxjsqxx where xh||nd||zxjdm='"+pkT+"'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zxjdm='助学金"+pkT+"'";
					j++;
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_gdnzzyjsxy_zxjsqxx.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("gdnzzyjsxy_zxjsqxx",
						new String[] { "xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xh||nd||zxjdm", pkVal,
						request);
			} else {
				ok = StandardOperation.update("gdnzzyjsxy_zxjsqxx",
						new String[] { "xxsh", "xxshyj" }, new String[] {
								yesNo, xxshyj }, "xh||nd||zxjdm", pkVal,
						request);
			}
			if(ok){
				logMsg = "修改(审核) gdnzzyjsxy_zxjsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_zxjsqxx";
		pk = "xh||nd||zxjdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,zxjdm,zzmc,zzdw,"
					+ "zzze,zzgrje,ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,"
					+ "kncdpm,XYSH yesNo from view_gdnzzyjsxy_zxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,zxjdm,zzmc,zzdw,"
					+ "zzze,zzgrje,ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,"
					+ "kncdpm,XXSH yesNo from view_gdnzzyjsxy_zxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "zxjdm", "zzmc", "zzdw", "zzze",
				"zzgrje", "ffrq", "ffje", "cjje", "sfje", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "kncdpm", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_zxjsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_zxjsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
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
		String zxjdm1 = request.getParameter("zxjdm1");
		String zxjdm = "";
		if(!isNull(zxjdm1)){
			zxjdm1 = DealString.toGBK(zxjdm1);
			String[] zxjTemp = zxjdm1.split("!!splitOne!!");
			zxjdm = zxjTemp[0];
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
		realTable = "gdnzzyjsxy_zxjsqxx";
		pk = "xh||nd||zxjdm";
		tableName = "view_gdnzzyjsxy_zxjsqxx";
		colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "bjmc",
				"sqsj", "zzmc", "zzze", "zzgrje", "ffrq", "ffje", "qxfje" };
		if (isNull(zxjdm)) {
		} else {
			querry.append(" and zxjdm='");
			querry.append(zxjdm);
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
		sql = "select (case when nvl(a.ffje,'0')='0' then '#CCCCCC' else '#FFFFFF' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " 主键,a.xh pk2,a.* from "
				+ tableName
				+ " a"
				+ querry.toString() + " and xxsh='通过' order by xxsh desc) a";
		sql = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.zxjdm||'##OneSpile##'||a.zzmc||'##OneSpile##'||a.zzdw||'##OneSpile##'||a.zzze||'##OneSpile##'||a.zzgrje||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
			+ tableName
			+ " a"
			+ querry.toString() + " and xxsh='通过' order by xxsh desc";
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##zxjdm##OneSpile##zzmc##OneSpile##zzdw##OneSpile##zzze##OneSpile##zzgrje##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##kncdpm";
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
		map.put("zxjdm1", zxjdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("zxjList", xszzDao.getGdZxjList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_zxjsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_zxjsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String ffje = DealString.toGBK(request.getParameter("ffje"));
		String cjje = DealString.toGBK(request.getParameter("cjje"));
		String sfje = DealString.toGBK(request.getParameter("sfje"));
		String sql = "";
		String pkDel = DealString.toGBK(request.getParameter("pkDel"));
		request.setAttribute("pkDel", pkDel);
		HttpSession session = request.getSession();
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String sj = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') sj from dual", new String[]{}, "sj");
		boolean ok = false;

		if ("more".equalsIgnoreCase(actDo)) {
			String toDo = request.getParameter("toDo");
			if ("save".equalsIgnoreCase(toDo)) {
				String[] pkDelT = pkDel.split("!!splitOne!!");
				String[] sqlT = new String[pkDelT.length];
				for (int i = 1; i < pkDelT.length; i++) {
					String pkT = pkDelT[i];
					sqlT[i] = "update gdnzzyjsxy_zxjsqxx set ffje='" + ffje
							+ "',cjje='" + cjje + "',sfje='" + sfje
							+ "',ffrq='" + sj + "' where xh||nd||zxjdm='" + pkT
							+ "'";
				}
				dao.runBatch(sqlT);
				return null;
			}
			ActionForward newFwd = new ActionForward(
					"/xszz/gdnzzyjsxy/ffje_gdnzzyjsxy_zxjsqxx_more.jsp", false);
			return newFwd;
		}
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		
		String xyshryhm = session.getAttribute("userName").toString();
		
		String pk = "";
		String[] colList = new String[] {};
		String yn = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		if (actDo.equalsIgnoreCase("save")) {
			ok = StandardOperation.update("gdnzzyjsxy_zxjsqxx",
					new String[] { "ffje", "cjje", "sfje", "ffrq" },
					new String[] { ffje, cjje, sfje, sj },
					"xh||nd||zxjdm", pkVal, request);
			if(ok){
				logMsg = "修改 gdnzzyjsxy_zxjsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_zxjsqxx";
		pk = "xh||nd||zxjdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,zxjdm,zzmc,zzdw,"
					+ "zzze,zzgrje,ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,"
					+ "kncdpm,XYSH yesNo from view_gdnzzyjsxy_zxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,zxjdm,zzmc,zzdw,"
					+ "zzze,zzgrje,ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,"
					+ "kncdpm,XXSH yesNo from view_gdnzzyjsxy_zxjsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "zxjdm", "zzmc", "zzdw", "zzze",
				"zzgrje", "ffrq", "ffje", "cjje", "sfje", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "kncdpm", "yesNo" };

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
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_zxjsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward gdnzzyjsxy_knbzsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		XszzForm xszzForm = (XszzForm) form;

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String[] titNames = null;

		titNames = new String[] { "gdnzzyjsxy_knbzsqxx" };
		
		String nd = Base.currNd;

		if (null == titName)
			titName = titNames[0];

		request.setAttribute("titName", titName);
		XszzDao xszzDao = new XszzDao();
		String sql = "";
		String[] outString = new String[] {};
		sql = "select * from view_gdnzzyjsxy_knbzsqxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String knbzdm = "";
		String knbzdm1 = "";
		List knbzList = xszzDao.getGdKnbzList();
		request.setAttribute("knbzList", knbzList);
		knbzdm1 = request.getParameter("knbzdm1");
		if(knbzdm1 != null){
			knbzdm1 = DealString.toGBK(knbzdm1);
			String[] knbzTemp = knbzdm1.split("!!splitOne!!");
			if(knbzTemp != null){
				knbzdm = knbzTemp[0];
			}
		}
		String queryPK = request.getParameter("queryPK");
		if ((null != queryPK) && (!"".equalsIgnoreCase(queryPK))){
			queryPK = DealString.toGBK(queryPK);
			sql = "select knbzdm||'!!splitOne!!'||knbzmc knbzdm1,knbzmc from GdNZZY_xszz_knbzdmb where knbzdm=?";
			String tKnbzdm = dao.getOneRs(sql, new String[] { queryPK }, "knbzdm1");
			knbzdm = queryPK;
			knbzdm1 = tKnbzdm;
		}
		String pkVal = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + knbzdm;
				}
				sql = "select xxsh from gdnzzyjsxy_knbzsqxx where xh||nd||knbzdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm"));
					String zhcppm = DealString.toGBK(request.getParameter("zhcppm"));
					StandardOperation.update("gdnzzy_xszz_xsxxb", new String[]{"sxqcjpm", "zhcppm"},
							new String[]{sxqcjpm,zhcppm}, "xh", xh, request);
					
					String sqbzyy = DealString.toGBK(request.getParameter(
							"sqbzyy").toString());
					StandardOperation.delete("gdnzzyjsxy_knbzsqxx",
							"xh||nd||knbzdm", pkVal, request);

					String[] colName1 = new String[] { "xh", "nd", "knbzdm",
							"sqbzyy" };

					String[] valueForOut = new String[] { xh, nd, knbzdm,
							sqbzyy };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_knbzsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm", "困难补助" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "困难补助", nd, knbzdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
				pkVal = request.getParameter("pkVal");
				if((null != pkVal) && (!"".equalsIgnoreCase(pkVal))){
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + knbzdm;
				}

				sql = "select xxsh from gdnzzyjsxy_knbzsqxx where xh||nd||knbzdm=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null) && (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm"));
					String zhcppm = DealString.toGBK(request.getParameter("zhcppm"));
					StandardOperation.update("gdnzzy_xszz_xsxxb", new String[]{"sxqcjpm", "zhcppm"},
							new String[]{sxqcjpm,zhcppm}, "xh", xh, request);
					
					String sqbzyy = DealString.toGBK(request.getParameter(
							"sqbzyy").toString());
					StandardOperation.delete("gdnzzyjsxy_knbzsqxx",
							"xh||nd||knbzdm", pkVal, request);

					String[] colName1 = new String[] { "xh", "nd", "knbzdm",
							"sqbzyy" };

					String[] valueForOut = new String[] { xh, nd, knbzdm,
							sqbzyy };

					boolean ok = false;
					ok = StandardOperation.insert("gdnzzyjsxy_knbzsqxx",
							colName1, valueForOut, request);
					if (ok) {
						StandardOperation.delete("gdnzzy_xszz_zzjlb",
								"zzdl||xh||nd||zzxldm", "困难补助" + pkVal, request);

						colName1 = new String[] { "zzdl", "nd", "zzxldm", "xh" };
						valueForOut = new String[] { "困难补助", nd, knbzdm, xh };

						ok = StandardOperation.insert("gdnzzy_xszz_zzjlb",
								colName1, valueForOut, request);
					}
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
			pkVal = xh + nd + knbzdm;
		}

		sql = "select * from view_gdnzzyjsxy_knbzsqxx where xh||nd||knbzdm=?";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.syd,a.hkxz,"
						+ "a.jtjzdz,a.yzbm,a.lxdh,a.jtrks,a.jtnzsr,a.jtrjysr,a.srly,a.xsbrysjxfed,a.jtqk,"
						+ "a.jtjjqksm,a.sfycjqgzx,a.qxfje,a.bz,a.sxqcjpm,a.zhcppm,b.kncdpm from "
						+ "view_gdnzzy_xszz_xsxxb a,view_gdnzzyjsxy_knsxx b where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
						"syd", "hkxz", "jtjzdz", "yzbm", "lxdh", "jtrks",
						"jtnzsr", "jtrjysr", "srly", "xsbrysjxfed", "jtqk",
						"jtjjqksm", "sfycjqgzx", "qxfje", "bz", "sxqcjpm", "zhcppm",
						"kncdpm" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);

				if (outVal == null) {
					request.setAttribute("notKNS", "is");
				} else {
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
			map.put("knbzdm1", knbzdm1);
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
					map.put(outString[i].toLowerCase(), outValue[i]);
				} else {
					map.put(outString[i].toLowerCase(), "");
				}
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward gdnzzyjsxy_knbzsqxxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String xh = DealString.toGBK(request.getParameter("xh").toString());
		String nd = DealString.toGBK(request.getParameter("nd").toString());
		String xm = DealString.toGBK(request.getParameter("xm").toString());
		String xb = DealString.toGBK(request.getParameter("xb").toString());
		String sfzh = DealString.toGBK(request.getParameter("sfzh").toString());
		String nj = DealString.toGBK(request.getParameter("nj").toString());
		String xymc = DealString.toGBK(request.getParameter("xymc").toString());
		String zymc = DealString.toGBK(request.getParameter("zymc").toString());
		String bjmc = DealString.toGBK(request.getParameter("bjmc").toString());
		String syd = DealString.toGBK(request.getParameter("syd").toString());
		String hkxz = DealString.toGBK(request.getParameter("hkxz").toString());
		String jtjzdz = DealString.toGBK(request.getParameter("jtjzdz")
				.toString());
		String yzbm = DealString.toGBK(request.getParameter("yzbm").toString());
		String lxdh = DealString.toGBK(request.getParameter("lxdh").toString());
		String jtrks = DealString.toGBK(request.getParameter("jtrks")
				.toString());
		String jtnzsr = DealString.toGBK(request.getParameter("jtnzsr")
				.toString());
		String jtrjysr = DealString.toGBK(request.getParameter("jtrjysr")
				.toString());
		String srly = DealString.toGBK(request.getParameter("srly").toString());
		String xsbrysjxfed = DealString.toGBK(request.getParameter(
				"xsbrysjxfed").toString());
		String jtqk = DealString.toGBK(request.getParameter("jtqk").toString()
				);
		String jtjjqksm = DealString.toGBK(request.getParameter("jtjjqksm")
				.toString());
		String sfycjqgzx = DealString.toGBK(request.getParameter("sfycjqgzx")
				.toString());
		String qxfje = DealString.toGBK(request.getParameter("qxfje")
				.toString());
		String bz = DealString.toGBK(request.getParameter("bz").toString());
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")
				.toString());
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")
				.toString());
		String sxqcjpm = DealString.toGBK(request.getParameter("sxqcjpm")
				.toString());
		String zhcppm = DealString.toGBK(request.getParameter("zhcppm")
				.toString());
		String kncdpm = DealString.toGBK(request.getParameter("kncdpm")
				.toString());
		String knbzdm1 = DealString.toGBK(request.getParameter("knbzdm1")
				.toString());
		String sqsj = DealString.toGBK(request.getParameter("sqsj").toString());
		String sqbzyy = DealString.toGBK(
				request.getParameter("sqbzyy").toString());
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";
		
		String knbzdm = "";
		String knbzmc = "";
		String[] knbzTemp = knbzdm1.split("!!splitOne!!");
		if(knbzTemp != null){
			knbzdm = knbzTemp[0];
			knbzmc = knbzTemp[1];
		}
		
		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		String[] outValue = new String[] { xh, xm, xb, sfzh, nj, xymc, zymc,
				bjmc, syd, hkxz, jtjzdz, yzbm, lxdh, jtrks, jtnzsr, jtrjysr,
				srly, xsbrysjxfed, jtqk, jtjjqksm, sfycjqgzx, qxfje, bz,
				xyshyj, xxshyj, sqsj, sxqcjpm, zhcppm, kncdpm, knbzdm, knbzmc, sqbzyy, nd };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "syd", "hkxz", "jtjzdz", "yzbm",
				"lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly", "xsbrysjxfed",
				"jtqk", "jtjjqksm", "sfycjqgzx", "qxfje", "bz", "xyshyj",
				"xxshyj", "sqsj", "sxqcjpm", "zhcppm", "kncdpm", "knbzdm", "knbzmc",
				"sqbzyy", "nd" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_knbzsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		HashMap<String, String> map = new HashMap<String, String>();
		String isQuery = request.getParameter("isQuery");
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
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
		String knbzdm1 = request.getParameter("knbzdm1");
		String knbzdm = "";
		if(!isNull(knbzdm1)){
			knbzdm1 = DealString.toGBK(knbzdm1);
			String[] knbzTemp = knbzdm1.split("!!splitOne!!");
			knbzdm = knbzTemp[0];
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
		realTable = "gdnzzyjsxy_knbzsqxx";
		pk = "xh||nd||knbzdm";
		tableName = "view_gdnzzyjsxy_knbzsqxx";
		
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
		if (isNull(knbzdm)) {
		} else {
			querry.append(" and knbzdm='");
			querry.append(knbzdm);
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
		String tips = "当前所在位置：学生资助 - 审核 - 困难补助";
		if (isQuery.equalsIgnoreCase("is")){
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 困难补助";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "knbzmc",
					"sqsj", "xysh", "xxsh", "ffrq", "ffje", "cjje", "sfje" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.knbzdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.knbzdm||'##OneSpile##'||a.knbzmc||'##OneSpile##'||a.sqbzyy||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
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
					+ " 主键,a.xh pk2,a.knbzdm pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.knbzdm||'##OneSpile##'||a.knbzmc||'##OneSpile##'||a.sqbzyy||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm", "bjmc",
					"sqsj", "knbzmc", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') like '%通过%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.knbzdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.knbzdm||'##OneSpile##'||a.knbzmc||'##OneSpile##'||a.sqbzyy||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核') like '%通过%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.knbzdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.knbzdm||'##OneSpile##'||a.knbzmc||'##OneSpile##'||a.sqbzyy||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##knbzdm##OneSpile##knbzmc##OneSpile##sqbzyy##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##kncdpm";
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
		map.put("knbzdm1", knbzdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("knbzList", xszzDao.getGdKnbzList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_knbzsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_gdnzzyjsxy_knbzsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
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

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length*2];
			int j = 0;
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[j] = "delete gdnzzyjsxy_knbzsqxx where xh||nd||knbzdm='"+pkT+"' and xxsh<>'通过'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zzxldm='困难补助"+pkT+"'";
					j++;
				} else {
					sqlT[j] = "delete gdnzzyjsxy_knbzsqxx where xh||nd||knbzdm='"+pkT+"'";
					j++;
					sqlT[j] = "delete gdnzzy_xszz_zzjlb where zzdl||xh||nd||zzxldm='困难补助"+pkT+"'";
					j++;
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_gdnzzyjsxy_knbzsqxx.do?go=go", false);
			return newFwd;
		}
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("gdnzzyjsxy_knbzsqxx",
						new String[] { "xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xh||nd||knbzdm", pkVal,
						request);
			} else {
				ok = StandardOperation.update("gdnzzyjsxy_knbzsqxx",
						new String[] { "xxsh", "xxshyj" }, new String[] {
								yesNo, xxshyj }, "xh||nd||knbzdm", pkVal,
						request);
			}
			if(ok){
				logMsg = "修改(审核) gdnzzyjsxy_knbzsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_knbzsqxx";
		pk = "xh||nd||knbzdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,knbzdm,knbzmc,sqbzyy,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,kncdpm,"
					+ "XYSH yesNo from view_gdnzzyjsxy_knbzsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,knbzdm,knbzmc,sqbzyy,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,kncdpm,"
					+ "XXSH yesNo from view_gdnzzyjsxy_knbzsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "knbzdm", "knbzmc", "sqbzyy",
				"ffrq", "ffje", "cjje", "sfje", "sqsj", "xysh", "xyshyj",
				"xxsh", "xxshyj", "kncdpm", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_knbzsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_knbzsqxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
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
		String knbzdm1 = request.getParameter("knbzdm1");
		String knbzdm = "";
		if(!isNull(knbzdm1)){
			knbzdm1 = DealString.toGBK(knbzdm1);
			String[] knbzTemp = knbzdm1.split("!!splitOne!!");
			knbzdm = knbzTemp[0];
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
		realTable = "gdnzzyjsxy_knbzsqxx";
		pk = "xh||nd||knbzdm";
		tableName = "view_gdnzzyjsxy_knbzsqxx";
		colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm", "bjmc",
				"sqsj", "knbzmc", "ffrq", "ffje", "qxfje" };
		if (isNull(knbzdm)) {
		} else {
			querry.append(" and knbzdm='");
			querry.append(knbzdm);
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
		sql = "select (case when nvl(a.ffje,'0')='0' then '#CCCCCC' else '#FFFFFF' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " 主键,a.xh pk2,a.* from "
				+ tableName
				+ " a"
				+ querry.toString() + " and xxsh='通过' order by xxsh desc) a";
		sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz||'##OneSpile##'||a.knbzdm||'##OneSpile##'||a.knbzmc||'##OneSpile##'||a.sqbzyy||'##OneSpile##'||a.ffrq||'##OneSpile##'||a.ffje||'##OneSpile##'||a.cjje||'##OneSpile##'||a.sfje||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.kncdpm col from "
			+ tableName
			+ " a"
			+ querry.toString() + " and xxsh='通过' order by xxsh desc";
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz##OneSpile##knbzdm##OneSpile##knbzmc##OneSpile##sqbzyy##OneSpile##ffrq##OneSpile##ffje##OneSpile##cjje##OneSpile##sfje##OneSpile##sqsj##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxsh##OneSpile##xxshyj##OneSpile##kncdpm";
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
		map.put("knbzdm1", knbzdm1);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("knbzList", xszzDao.getGdKnbzList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gdnzzyjsxy_zxjsqxx");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward ffje_gdnzzyjsxy_knbzsqxx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String ffje = DealString.toGBK(request.getParameter("ffje"));
		String cjje = DealString.toGBK(request.getParameter("cjje"));
		String sfje = DealString.toGBK(request.getParameter("sfje"));
		String sql = "";
		String pkDel = DealString.toGBK(request.getParameter("pkDel"));
		request.setAttribute("pkDel", pkDel);
		HttpSession session = request.getSession();
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		boolean ok = false;
		String sj = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') sj from dual", new String[]{}, "sj");

		if ("more".equalsIgnoreCase(actDo)) {
			String toDo = request.getParameter("toDo");
			if ("save".equalsIgnoreCase(toDo)) {
				String[] pkDelT = pkDel.split("!!splitOne!!");
				String[] sqlT = new String[pkDelT.length];
				for (int i = 1; i < pkDelT.length; i++) {
					String pkT = pkDelT[i];
					sqlT[i] = "update gdnzzyjsxy_knbzsqxx set ffje='" + ffje
							+ "',cjje='" + cjje + "',sfje='" + sfje
							+ "',ffrq='" + sj + "' where xh||nd||knbzdm='"
							+ pkT + "'";
				}
				dao.runBatch(sqlT);
				return null;
			}
			ActionForward newFwd = new ActionForward(
					"/xszz/gdnzzyjsxy/ffje_gdnzzyjsxy_knbzsqxx_more.jsp", false);
			return newFwd;
		}
		String pkVal = request.getParameter("pkVal");
		String xh = request.getParameter("xh");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		
		String xyshryhm = session.getAttribute("userName").toString();
		
		String pk = "";
		String[] colList = new String[] {};
		String yn = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		if (actDo.equalsIgnoreCase("save")) {
			ok = StandardOperation.update("gdnzzyjsxy_knbzsqxx",
					new String[] { "ffje", "cjje", "sfje", "ffrq" },
					new String[] { ffje, cjje, sfje, sj },
					"xh||nd||knbzdm", pkVal, request);
			if(ok){
				logMsg = "修改 gdnzzyjsxy_knbzsqxx";
				Base.log(request, logMsg, sUName);
			}
		}

		ArrayList<String> zzjl = xszzDao.getXszzJL(xh);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzyjsxy_knbzsqxx";
		pk = "xh||nd||knbzdm";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,knbzdm,knbzmc,sqbzyy,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,kncdpm,"
					+ "XYSH yesNo from view_gdnzzyjsxy_knbzsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,nd,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,syd,hkxz,"
					+ "jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,xsbrysjxfed,jtqk,"
					+ "jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz,knbzdm,knbzmc,sqbzyy,"
					+ "ffrq,ffje,cjje,sfje,sqsj,xysh,xyshyj,xxsh,xxshyj,kncdpm,"
					+ "XXSH yesNo from view_gdnzzyjsxy_knbzsqxx where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "xb", "sfzh", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz", "knbzdm", "knbzmc", "sqbzyy",
				"ffrq", "ffje", "cjje", "sfje", "sqsj", "xysh", "xyshyj",
				"xxsh", "xxshyj", "kncdpm", "yesNo" };

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
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzyjsxy_knbzsqxx");
		return mapping.findForward("success");
	}
	
	private ActionForward xszz_gdnz_lnzzcx(ActionMapping mapping,
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
		realTable = "gdnzzy_xszz_xsxxb";
		pk = "xh";
		tableName = "view_gdnzzy_xszz_xsxxb";
		
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
		String tips = "当前所在位置：学生资助 - 信息维护 - 历年资料查询";
			colList = new String[] { "主键", "xh", "xm", "nj", "xymc", "zymc", "bjmc" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select "
						+ " a.* from(select "
						+ pk
						+ " 主键,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ ") a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz col from "
					+ tableName
					+ " a"
					+ querry.toString();
			} else {
				checkForm.setXydm(userDep);
				sql = "select "
						+ "a.* from(select "
						+ pk
						+ " 主键,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "') a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.syd||'##OneSpile##'||a.hkxz||'##OneSpile##'||a.jtjzdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtrks||'##OneSpile##'||a.jtnzsr||'##OneSpile##'||a.jtrjysr||'##OneSpile##'||a.srly||'##OneSpile##'||a.xsbrysjxfed||'##OneSpile##'||a.jtqk||'##OneSpile##'||a.jtjjqksm||'##OneSpile##'||a.sxqcjpm||'##OneSpile##'||a.zhcppm||'##OneSpile##'||a.sfycjqgzx||'##OneSpile##'||a.qxfje||'##OneSpile##'||a.bz col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep;
			}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##syd##OneSpile##hkxz##OneSpile##jtjzdz##OneSpile##yzbm##OneSpile##lxdh##OneSpile##jtrks##OneSpile##jtnzsr##OneSpile##jtrjysr##OneSpile##srly##OneSpile##xsbrysjxfed##OneSpile##jtqk##OneSpile##jtjjqksm##OneSpile##sxqcjpm##OneSpile##zhcppm##OneSpile##sfycjqgzx##OneSpile##qxfje##OneSpile##bz";
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
		request.setAttribute("act", "view_gdnzzy_xszz_xsxxb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("success");
	}
	
	private ActionForward xszz_gdnz_lnzzcx_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzDao xszzDao = new XszzDao();
		DAO dao = DAO.getInstance();
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();

		String xyshryhm = session.getAttribute("userName").toString();

		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String yn = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
//		String sUName;
//		String logMsg;
//		sUName = session.getAttribute("userName").toString();

		ArrayList<String> zzjl = xszzDao.getXszzJL(pkVal);
		if (zzjl.size() == 0) {
			String temp = "无";
			zzjl.add(temp);
		}
		request.setAttribute("zzjl", zzjl);

		realTable = "gdnzzy_xszz_xsxxb";
		pk = "xh";
			sql = "select "
					+ pk
					+ " pk,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,"
					+ "syd,hkxz,jtjzdz,yzbm,lxdh,jtrks,jtnzsr,jtrjysr,srly,"
					+ "xsbrysjxfed,jtqk,jtjjqksm,sxqcjpm,zhcppm,sfycjqgzx,qxfje,bz "
					+ "from view_gdnzzy_xszz_xsxxb where "
					+ pk + "='" + pkVal + "'";
		colList = new String[] { "pk", "xh", "xm", "xb", "sfzh", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "syd", "hkxz",
				"jtjzdz", "yzbm", "lxdh", "jtrks", "jtnzsr", "jtrjysr", "srly",
				"xsbrysjxfed", "jtqk", "jtjjqksm", "sxqcjpm", "zhcppm",
				"sfycjqgzx", "qxfje", "bz" };

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
		request.setAttribute("xyshryhm", xyshryhm);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "gdnzzy_xszz_xsxxb");
		return mapping.findForward("success");
	}
}
