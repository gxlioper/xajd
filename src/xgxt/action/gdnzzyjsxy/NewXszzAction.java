package xgxt.action.gdnzzyjsxy;

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
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;

public class NewXszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	/**
	 * @describe 助学贷款申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		String now = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String dklxdm = Base.chgNull(request.getParameter("dklxdm"), "", 1);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;
		String dkqx = dao
				.getOneRs(
						"select to_char(add_months(to_date(rxny,'yyyy-mm-dd'),floor((NVL(xz,3)+(floor('"
								+ nd
								+ "')-nj+3))*12)),'yyyy-mm-dd') dkqx from bks_xsjbxx where xh=?",
						new String[] { xh }, "dkqx");
		String htbh = "";
		String xxdm = StandardOperation.getXxdm();

//		boolean sfkns = false;
		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = nd + xh;
		}
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
				if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
					String sfzh = Base.chgNull(request.getParameter("sfzh"),
							"", 1);
					String hjszd = Base.chgNull(request.getParameter("hjszd"),
							"", 1);
					String jtjzdz = Base.chgNull(
							request.getParameter("jtjzdz"), "", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String jtlxdh = Base.chgNull(
							request.getParameter("jtlxdh"), "", 1);
					String grlxdh = Base.chgNull(
							request.getParameter("grlxdh"), "", 1);
					String yxdz = Base.chgNull(request.getParameter("yxdz"),
							"", 1);
					String jtrk = Base.chgNull(request.getParameter("jtrk"),
							"", 1);
					String jtnzsr = Base.chgNull(
							request.getParameter("jtnzsr"), "", 1);
					String jtrjysr = Base.chgNull(request
							.getParameter("jtrjysr"), "", 1);
					String fqxm = Base.chgNull(request.getParameter("fqxm"),
							"", 1);
					String fqsfzh = Base.chgNull(
							request.getParameter("fqsfzh"), "", 1);
					String fqgzdw = Base.chgNull(
							request.getParameter("fqgzdw"), "", 1);
					String fqzy = Base.chgNull(request.getParameter("fqzy"),
							"", 1);
					String fqlxdh = Base.chgNull(
							request.getParameter("fqlxdh"), "", 1);
					String mqxm = Base.chgNull(request.getParameter("mqxm"),
							"", 1);
					String mqsfzh = Base.chgNull(
							request.getParameter("mqsfzh"), "", 1);
					String mqgzdw = Base.chgNull(
							request.getParameter("mqgzdw"), "", 1);
					String mqzy = Base.chgNull(request.getParameter("mqzy"),
							"", 1);
					String mqlxdh = Base.chgNull(
							request.getParameter("mqlxdh"), "", 1);
					String jtszjwhdh = Base.chgNull(request
							.getParameter("jtszjwhdh"), "", 1);
					String dkje = Base.chgNull(request.getParameter("dkje"),
							"", 1);
					String hkzhlx = Base.chgNull(
							request.getParameter("hkzhlx"), "", 1);
					String hkzhhm = Base.chgNull(
							request.getParameter("hkzhhm"), "", 1);
					String bz = Base.chgNull(request.getParameter("bz"), "", 1);
					String sqsj = now;
					htbh = nd + xxdm + sfzh;

					sql = "select count(*) num from gnnzzy_gjzxdksqb where nd||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("gnnzzy_gjzxdksqb",
								new String[] { "nd", "xh", "hjszd", "jtjzdz",
										"yzbm", "jtlxdh", "grlxdh", "yxdz",
										"jtrk", "jtnzsr", "jtrjysr", "fqxm",
										"fqsfzh", "fqgzdw", "fqzy", "fqlxdh",
										"mqxm", "mqsfzh", "mqgzdw", "mqzy",
										"mqlxdh", "jtszjwhdh", "htbh",
										"dklxdm", "dkje", "dkqx", "hkzhlx",
										"hkzhhm", "bz", "sqsj" }, new String[] {
										nd, xh, hjszd, jtjzdz, yzbm, jtlxdh,
										grlxdh, yxdz, jtrk, jtnzsr, jtrjysr,
										fqxm, fqsfzh, fqgzdw, fqzy, fqlxdh,
										mqxm, mqsfzh, mqgzdw, mqzy, mqlxdh,
										jtszjwhdh, htbh, dklxdm, dkje, dkqx,
										hkzhlx, hkzhhm, bz, sqsj }, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from gnnzzy_gjzxdksqb where nd||xh=? and xxsh='通过'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("gnnzzy_gjzxdksqb",
									new String[] { "nd", "xh", "hjszd",
											"jtjzdz", "yzbm", "jtlxdh",
											"grlxdh", "yxdz", "jtrk", "jtnzsr",
											"jtrjysr", "fqxm", "fqsfzh",
											"fqgzdw", "fqzy", "fqlxdh", "mqxm",
											"mqsfzh", "mqgzdw", "mqzy",
											"mqlxdh", "jtszjwhdh", "htbh",
											"dklxdm", "dkje", "dkqx", "hkzhlx",
											"hkzhhm", "bz", "sqsj", "xysh",
											"xyshyj", "xxsh", "xxshyj" },
									new String[] { nd, xh, hjszd, jtjzdz, yzbm,
											jtlxdh, grlxdh, yxdz, jtrk, jtnzsr,
											jtrjysr, fqxm, fqsfzh, fqgzdw,
											fqzy, fqlxdh, mqxm, mqsfzh, mqgzdw,
											mqzy, mqlxdh, jtszjwhdh, htbh,
											dklxdm, dkje, dkqx, hkzhlx, hkzhhm,
											bz, sqsj, "未审核", "", "未审核", "" },
									"nd||xh", pkVal, request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
//			sfkns = dao.isKns(xh);

			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
				String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
				String hjszd = Base.chgNull(request.getParameter("hjszd"), "",
						1);
				String jtjzdz = Base.chgNull(request.getParameter("jtjzdz"),
						"", 1);
				String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
				String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),
						"", 1);
				String grlxdh = Base.chgNull(request.getParameter("grlxdh"),
						"", 1);
				String yxdz = Base.chgNull(request.getParameter("yxdz"), "", 1);
				String jtrk = Base.chgNull(request.getParameter("jtrk"), "", 1);
				String jtnzsr = Base.chgNull(request.getParameter("jtnzsr"),
						"", 1);
				String jtrjysr = Base.chgNull(request.getParameter("jtrjysr"),
						"", 1);
				String fqxm = Base.chgNull(request.getParameter("fqxm"), "", 1);
				String fqsfzh = Base.chgNull(request.getParameter("fqsfzh"),
						"", 1);
				String fqgzdw = Base.chgNull(request.getParameter("fqgzdw"),
						"", 1);
				String fqzy = Base.chgNull(request.getParameter("fqzy"), "", 1);
				String fqlxdh = Base.chgNull(request.getParameter("fqlxdh"),
						"", 1);
				String mqxm = Base.chgNull(request.getParameter("mqxm"), "", 1);
				String mqsfzh = Base.chgNull(request.getParameter("mqsfzh"),
						"", 1);
				String mqgzdw = Base.chgNull(request.getParameter("mqgzdw"),
						"", 1);
				String mqzy = Base.chgNull(request.getParameter("mqzy"), "", 1);
				String mqlxdh = Base.chgNull(request.getParameter("mqlxdh"),
						"", 1);
				String jtszjwhdh = Base.chgNull(request
						.getParameter("jtszjwhdh"), "", 1);
				String dkje = Base.chgNull(request.getParameter("dkje"), "", 1);
				String hkzhlx = Base.chgNull(request.getParameter("hkzhlx"),
						"", 1);
				String hkzhhm = Base.chgNull(request.getParameter("hkzhhm"),
						"", 1);
				String bz = Base.chgNull(request.getParameter("bz"), "", 1);
				String sqsj = now;
				htbh = nd + xxdm + sfzh;

				sql = "select count(*) num from gnnzzy_gjzxdksqb where nd||xh=?";
				String num = dao.getOneRs(sql, new String[] { pkVal }, "num");
				if ("0".equalsIgnoreCase(num)) {
					boolean ok = false;
					ok = StandardOperation.insert("gnnzzy_gjzxdksqb",
							new String[] { "nd", "xh", "hjszd", "jtjzdz",
									"yzbm", "jtlxdh", "grlxdh", "yxdz", "jtrk",
									"jtnzsr", "jtrjysr", "fqxm", "fqsfzh",
									"fqgzdw", "fqzy", "fqlxdh", "mqxm",
									"mqsfzh", "mqgzdw", "mqzy", "mqlxdh",
									"jtszjwhdh", "htbh", "dklxdm", "dkje",
									"dkqx", "hkzhlx", "hkzhhm", "bz", "sqsj" },
							new String[] { nd, xh, hjszd, jtjzdz, yzbm, jtlxdh,
									grlxdh, yxdz, jtrk, jtnzsr, jtrjysr, fqxm,
									fqsfzh, fqgzdw, fqzy, fqlxdh, mqxm, mqsfzh,
									mqgzdw, mqzy, mqlxdh, jtszjwhdh, htbh,
									dklxdm, dkje, dkqx, hkzhlx, hkzhhm, bz,
									sqsj }, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
					sql = "select count(*) num from gnnzzy_gjzxdksqb where nd||xh=? and xxsh='通过'";
					num = dao.getOneRs(sql, new String[] { pkVal }, "num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.update("gnnzzy_gjzxdksqb",
								new String[] { "nd", "xh", "hjszd", "jtjzdz",
										"yzbm", "jtlxdh", "grlxdh", "yxdz",
										"jtrk", "jtnzsr", "jtrjysr", "fqxm",
										"fqsfzh", "fqgzdw", "fqzy", "fqlxdh",
										"mqxm", "mqsfzh", "mqgzdw", "mqzy",
										"mqlxdh", "jtszjwhdh", "htbh",
										"dklxdm", "dkje", "dkqx", "hkzhlx",
										"hkzhhm", "bz", "sqsj", "xysh",
										"xyshyj", "xxsh", "xxshyj" },
								new String[] { nd, xh, hjszd, jtjzdz, yzbm,
										jtlxdh, grlxdh, yxdz, jtrk, jtnzsr,
										jtrjysr, fqxm, fqsfzh, fqgzdw, fqzy,
										fqlxdh, mqxm, mqsfzh, mqgzdw, mqzy,
										mqlxdh, jtszjwhdh, htbh, dklxdm, dkje,
										dkqx, hkzhlx, hkzhhm, bz, sqsj, "未审核",
										"", "未审核", "" }, "nd||xh", pkVal,
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						request.setAttribute("have", "have");
					}
				}
			}
		}

		sql = "select nd,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,jtrk,jtnzsr,jtrjysr,fqxm,fqsfzh,fqgzdw,fqzy,fqlxdh,mqxm,mqsfzh,mqgzdw,mqzy,mqlxdh,jtszjwhdh,spbbh,htbh,dklxdm,dklxmc,dkje,dkqx,nll,hkzhlx,hkzhhm,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_gnnzzy_gjzxdksqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		sql = "select nd,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,jtrk,jtnzsr,jtrjysr,fqxm,fqsfzh,fqgzdw,fqzy,fqlxdh,mqxm,mqsfzh,mqgzdw,mqzy,mqlxdh,jtszjwhdh,spbbh,htbh,dklxdm,dklxmc,dkje,dkqx,nll,hkzhlx,hkzhhm,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_gnnzzy_gjzxdksqb where nd||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.sfzh,a.xz,a.nj,a.xymc,a.zymc,a.bjmc,'"
						+ nd
						+ "'||'"
						+ xxdm
						+ "'||a.sfzh htbh,(select to_char(add_months(to_date(b.rxny,'yyyymm'),floor((NVL(b.xz,3)+(floor('"
						+ nd
						+ "')-b.nj+3))*12)),'yyyy-mm-dd') from bks_xsjbxx b where a.xh=b.xh) dkqx,(select z.nll from gdnzzyxy_dknlldmb z where z.dkqx=floor(NVL(a.xz,'3')+3)) nll,(select to_char(sysdate,'yyyy-mm-dd') sqsj from dual) sqsj from view_xsjbxx a where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xz", "nj", "xymc", "zymc", "bjmc", "htbh", "dkqx",
						"nll", "sqsj" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh", "xz",
							"nj", "xymc", "zymc", "bjmc", "htbh", "dkqx",
							"nll", "sqsj" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
					map.put("nd", nd);
					map.put("dklxmc",dao.getOneRs("select dklxmc from gdnzzyxy_dklxdmb where dklxdm=?",
							new String[] { dklxdm },
							"dklxmc"));
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
				outString[i] = outString[i].toLowerCase();
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("dklxList", xszzDao.getGdnzDklxList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxdksq");
	}
	
	/**
	 * @describe 助学贷款申请报表打印
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String xz = Base.chgNull(request.getParameter("xz"), "", 1);
		String hjszd = Base.chgNull(request.getParameter("hjszd"), "", 1);
		String jtjzdz = Base.chgNull(request.getParameter("jtjzdz"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"), "", 1);
		String grlxdh = Base.chgNull(request.getParameter("grlxdh"), "", 1);
		String yxdz = Base.chgNull(request.getParameter("yxdz"), "", 1);
		String jtrk = Base.chgNull(request.getParameter("jtrk"), "", 1);
		String jtnzsr = Base.chgNull(request.getParameter("jtnzsr"), "", 1);
		String jtrjysr = Base.chgNull(request.getParameter("jtrjysr"), "", 1);
		String fqxm = Base.chgNull(request.getParameter("fqxm"), "", 1);
		String fqsfzh = Base.chgNull(request.getParameter("fqsfzh"), "", 1);
		String fqgzdw = Base.chgNull(request.getParameter("fqgzdw"), "", 1);
		String fqzy = Base.chgNull(request.getParameter("fqzy"), "", 1);
		String fqlxdh = Base.chgNull(request.getParameter("fqlxdh"), "", 1);
		String mqxm = Base.chgNull(request.getParameter("mqxm"), "", 1);
		String mqsfzh = Base.chgNull(request.getParameter("mqsfzh"), "", 1);
		String mqgzdw = Base.chgNull(request.getParameter("mqgzdw"), "", 1);
		String mqzy = Base.chgNull(request.getParameter("mqzy"), "", 1);
		String mqlxdh = Base.chgNull(request.getParameter("mqlxdh"), "", 1);
		String jtszjwhdh = Base.chgNull(request.getParameter("jtszjwhdh"), "",
				1);
		String spbbh = Base.chgNull(request.getParameter("spbbh"), "", 1);
		String htbh = Base.chgNull(request.getParameter("htbh"), "", 1);
		String dklxmc = Base.chgNull(request.getParameter("dklxmc"), "", 1);
		String dkje = Base.chgNull(request.getParameter("dkje"), "", 1);
		String dkqx = Base.chgNull(request.getParameter("dkqx"), "", 1);
		String nll = Base.chgNull(request.getParameter("nll"), "", 1);
		String hkzhlx = Base.chgNull(request.getParameter("hkzhlx"), "", 1);
		String hkzhhm = Base.chgNull(request.getParameter("hkzhhm"), "", 1);
		String bz = Base.chgNull(request.getParameter("bz"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		if("未审核".equalsIgnoreCase(xysh)){
			xysh=" ";
			xyshyj=" ";
		}
		if("未审核".equalsIgnoreCase(xxsh)){
			xxsh=" ";
			xxshyj=" ";
		}
		String sqsj_year = "";
		String sqsj_mon = "";
		String sqsj_day = "";
		if(sqsj != null && !"".equalsIgnoreCase(sqsj)){
			sqsj_year = sqsj.substring(0,4);
			sqsj_mon = sqsj.substring(5, 7);
			sqsj_day = sqsj.substring(8);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = new String[] { nd, xh, xm, xb, sfzh, nj, xymc,
				zymc, bjmc, xz, hjszd, jtjzdz, yzbm, jtlxdh, grlxdh, yxdz,
				jtrk, jtnzsr, jtrjysr, fqxm, fqsfzh, fqgzdw, fqzy, fqlxdh,
				mqxm, mqsfzh, mqgzdw, mqzy, mqlxdh, jtszjwhdh, spbbh, htbh,
				dklxmc, dkje, dkqx, nll, hkzhlx, hkzhhm, bz, sqsj, xysh,
				xyshyj, xxsh, xxshyj, sqsj_year, sqsj_mon, sqsj_day };
		String[] outString = new String[] { "nd", "xh", "xm", "xb", "sfzh",
				"nj", "xymc", "zymc", "bjmc", "xz", "hjszd", "jtjzdz", "yzbm",
				"jtlxdh", "grlxdh", "yxdz", "jtrk", "jtnzsr", "jtrjysr",
				"fqxm", "fqsfzh", "fqgzdw", "fqzy", "fqlxdh", "mqxm", "mqsfzh",
				"mqgzdw", "mqzy", "mqlxdh", "jtszjwhdh", "spbbh", "htbh",
				"dklxmc", "dkje", "dkqx", "nll", "hkzhlx", "hkzhhm", "bz",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj", "sqsj_year",
				"sqsj_mon", "sqsj_day" };
		// System.out.println(outString[35]+outValue[35]);
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				// System.out.println("i="+i+" "+outString[i]);
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], " ");
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("zxdksqb");
	}

	/**
	 * @describe 助学贷款审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
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
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String spbbh = Base.chgNull(request.getParameter("spbbh"), "", 1);
		String htbh = Base.chgNull(request.getParameter("htbh"), "", 1);
		realTable = "gnnzzy_gjzxdksqb";
		pk = "nd||xh";
		tableName = "view_gnnzzy_gjzxdksqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(spbbh)) {
			querry.append(" and spbbh='");
			querry.append(spbbh);
			querry.append("' ");
		}
		if (!isNull(htbh)) {
			querry.append(" and htbh='");
			querry.append(htbh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 助学贷款项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 助学贷款";
			colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
					"xb", "xymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "spbbh", "htbh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.spbbh,a.htbh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.spbbh,a.htbh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
						"xb", "xymc", "bjmc", "nj", "sqsj", "spbbh", "htbh", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "nd", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "spbbh", "htbh", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.spbbh,a.htbh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.spbbh,a.htbh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
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

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("spbbh", spbbh);
		map.put("htbh", htbh);
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
		request.setAttribute("zzxmList", xszzDao.getShgcZzxmList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("zxdkshList");
	}

	/**
	 * @describe 助学贷款审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdkshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String actDo = Base.chgNull(request.getParameter("actDo"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String yesNo = Base.chgNull(request.getParameter("yesNo"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String spbbh = Base.chgNull(request.getParameter("spbbh"), "", 1);

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete gnnzzy_gjzxdksqb where nd||xh='"+pkT+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete gnnzzy_gjzxdksqb where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/gnnzzy_gjzxdk.do?method=zxdkshList&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update gnnzzy_gjzxdksqb set xysh='通过' where nd||xh='"+pkT+"'";
				} else {
					sqlT[i] = "update gnnzzy_gjzxdksqb set xxsh='通过' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/gnnzzy_gjzxdk.do?method=zxdkshList&go=go", false);
			return newFwd;
		}
		
		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					String xxshT = dao
							.getOneRs(
									"select xxsh from gnnzzy_gjzxdksqb where nd||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"通过".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update gnnzzy_gjzxdksqb set xysh='不通过' where nd||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update gnnzzy_gjzxdksqb set xxsh='不通过' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/gnnzzy_gjzxdk.do?method=zxdkshList&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs("select xxsh from gnnzzy_gjzxdksqb where nd||xh=?", new String[]{pkVal}, "xxsh");
				if("通过".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					StandardOperation
							.update(
									"gnnzzy_gjzxdksqb",
									new String[] { "xysh", "xyshyj" },
									new String[] { yesNo, xyshyj },
									"nd||xh", pkVal, request);
				}
			} else {
				StandardOperation.update("gnnzzy_gjzxdksqb", new String[] {
						"xxsh", "xxshyj", "spbbh" }, new String[] {
						yesNo, xxshyj, spbbh }, "nd||xh", pkVal,
						request);
			}
		}
		realTable = "gnnzzy_gjzxdksqb";
		pk = "nd||xh";
		sql = "select nd,xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,jtrk,jtnzsr,jtrjysr,fqxm,fqsfzh,fqgzdw,fqzy,fqlxdh,mqxm,mqsfzh,mqgzdw,mqzy,mqlxdh,jtszjwhdh,spbbh,htbh,dklxdm,dklxmc,dkje,dkqx,nll,hkzhlx,hkzhhm,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_gnnzzy_gjzxdksqb where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xz,a.hjszd,a.jtjzdz,a.yzbm,a.jtlxdh,a.grlxdh,a.yxdz,a.jtrk,a.jtnzsr,a.jtrjysr,a.fqxm,a.fqsfzh,a.fqgzdw,a.fqzy,a.fqlxdh,a.mqxm,a.mqsfzh,a.mqgzdw,a.mqzy,a.mqlxdh,a.jtszjwhdh,a.spbbh,a.htbh,a.dklxdm,a.dklxmc,a.dkje,a.dkqx,a.nll,a.hkzhlx,a.hkzhhm,a.bz,a.sqsj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xysh yesNo "
				+ "from view_gnnzzy_gjzxdksqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xz,a.hjszd,a.jtjzdz,a.yzbm,a.jtlxdh,a.grlxdh,a.yxdz,a.jtrk,a.jtnzsr,a.jtrjysr,a.fqxm,a.fqsfzh,a.fqgzdw,a.fqzy,a.fqlxdh,a.mqxm,a.mqsfzh,a.mqgzdw,a.mqzy,a.mqlxdh,a.jtszjwhdh,a.spbbh,a.htbh,a.dklxdm,a.dklxmc,a.dkje,a.dkqx,a.nll,a.hkzhlx,a.hkzhhm,a.bz,a.sqsj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xxsh yesNo "
				+ "from view_gnnzzy_gjzxdksqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[(outString.length+2)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i].toLowerCase(), rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("spbbhList", xszzDao.getGdnzSpbbhList());
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_gnnzzy_gjzxdksqb");
		request.setAttribute("act", "zzsh");
		return mapping.findForward("zxdkshXxxx");
	}
	
	/**
	 * @describe 助学贷款列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String htbh = Base.chgNull(request.getParameter("htbh"), "", 1);
		String spbbh = Base.chgNull(request.getParameter("spbbh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(htbh)) {
			querry.append(" and htbh='");
			querry.append(htbh);
			querry.append("' ");
		}
		if (!isNull(spbbh)) {
			querry.append(" and spbbh='");
			querry.append(spbbh);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_gnnzzy_gjzxdksqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_gnnzzy_gjzxdksqb where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_gnnzzy_gjzxdksqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("zxdkExp");
	}

	/**
	 * @describe 还贷基本信息申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward hdxxsq(ActionMapping mapping, ActionForm form,
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

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String hth = Base.chgNull(request.getParameter("hth"), "", 1);
		String sql = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();

//		boolean sfkns = false;
		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = xh + hth;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
//			String[] jxjksjssj = null;
////			sfkns = dao.isKns(xh);
//
//			sql = "select ZXDKSQKSSJ,ZXDKSQJSSJ from xszhsjb a where "
//					+ "exists(select 1 from view_xsjbxx b where a.xydm=b.xydm and b.xh=?)";
//			jxjksjssj = dao.getOneRs(sql1, new String[] { xh }, new String[] {
//					"ZXDKSQKSSJ", "ZXDKSQJSSJ" });
//			if (jxjksjssj == null) {
//				sfksq = "1";// /可以进行申请
//				request.setAttribute("sfksq", sfksq);
//			} else if (jxjksjssj != null
//					&& jxjksjssj[0].compareToIgnoreCase(rightNow) < 0
//					&& jxjksjssj[1].compareToIgnoreCase(rightNow) > 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
					String gzdwmc = Base.chgNull(request.getParameter("gzdwmc"),"", 1);
					String gzdwdz = Base.chgNull(request.getParameter("gzdwdz"),"", 1);
					String gzdwyb = Base.chgNull(request.getParameter("gzdwyb"),"", 1);
					String dwdh   = Base.chgNull(request.getParameter("dwdh"),"", 1);

					sql = "select count(*) num from gdnzzy_hdjbxx where xh||hth=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("gdnzzy_hdjbxx",
							new String[] { "xh", "hth", "gzdwmc", "gzdwdz",
									"gzdwyb", "dwdh" }, new String[] { xh, hth,
									gzdwmc, gzdwdz, gzdwyb, dwdh }, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
							boolean ok = false;
							ok = StandardOperation.update("gdnzzy_hdjbxx",
								new String[] { "xh", "hth", "gzdwmc", "gzdwdz",
										"gzdwyb", "dwdh" }, new String[] { xh,
										hth, gzdwmc, gzdwdz, gzdwyb, dwdh },
								"xh||hth", pkVal, request);
							if (ok) {
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
					}
				}
//			} else {// 不在申请时间范围内
//				sfksq = "-1";
//				request.setAttribute("sfksq", sfksq);// 不能申请
//			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
//			sfkns = dao.isKns(xh);

			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			if (act != null && act.equalsIgnoreCase("save")) {// /学生填写申请
				String gzdwmc = Base.chgNull(request.getParameter("gzdwmc"),"", 1);
				String gzdwdz = Base.chgNull(request.getParameter("gzdwdz"),"", 1);
				String gzdwyb = Base.chgNull(request.getParameter("gzdwyb"),"", 1);
				String dwdh   = Base.chgNull(request.getParameter("dwdh"),"", 1);

				sql = "select count(*) num from gdnzzy_hdjbxx where xh||hth=?";
				String num = dao.getOneRs(sql, new String[] { pkVal },
						"num");
				if ("0".equalsIgnoreCase(num)) {
					boolean ok = false;
					ok = StandardOperation.insert("gdnzzy_hdjbxx",
						new String[] { "xh", "hth", "gzdwmc", "gzdwdz",
								"gzdwyb", "dwdh" }, new String[] { xh, hth,
								gzdwmc, gzdwdz, gzdwyb, dwdh }, request);
					if (ok) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				} else {
						boolean ok = false;
						ok = StandardOperation.update("gdnzzy_hdjbxx",
							new String[] { "xh", "hth", "gzdwmc", "gzdwdz",
									"gzdwyb", "dwdh" }, new String[] { xh,
									hth, gzdwmc, gzdwdz, gzdwyb, dwdh },
							"xh||hth", pkVal, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
				}
			}
		}

		sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,gzdwmc,gzdwdz,gzdwyb,dwdh,hth,htje,htzje,dkqx,zfxjtrq,nll,hkzhlx,hkzhhm,hksj,yhkje,yqqs,yqbj,yqfx,yqyy,bz from view_gdnzzy_hdjbxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,gzdwmc,gzdwdz,gzdwyb,dwdh,hth,htje,htzje,dkqx,zfxjtrq,nll,hkzhlx,hkzhhm,hksj,yhkje,yqqs,yqbj,yqfx,yqyy,bz from view_gdnzzy_hdjbxx where xh||hth=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select * from (select a.xh,a.xm,a.xb,a.sfzh,a.xz,a.nj,a.xymc,a.zymc,a.bjmc,b.grlxdh,b.yxdz,b.hjszd,b.jtjzdz,b.yzbm,b.jtlxdh from view_xsjbxx a left join view_gnnzzy_gjzxdksqb b on a.xh=b.xh) where xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "sfzh",
						"xz", "nj", "xymc", "zymc", "bjmc", "grlxdh", "yxdz",
						"hjszd", "jtjzdz", "yzbm", "jtlxdh" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "sfzh", "xz",
							"nj", "xymc", "zymc", "bjmc", "grlxdh", "yxdz",
							"hjszd", "jtjzdz", "yzbm", "jtlxdh" };
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
				outString[i] = outString[i].toLowerCase();
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}

		request.setAttribute("hthList", xszzDao.getGdnzHthList(xh));
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("hdxxsq");
	}

	/**
	 * @describe 还贷基本信息申请报表打印
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward hdxxsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xh = Base.chgNull(request.getParameter("xh"),"",1);
		String xm = Base.chgNull(request.getParameter("xm"),"",1);
		String xb = Base.chgNull(request.getParameter("xb"),"",1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"),"",1);
		String nj = Base.chgNull(request.getParameter("nj"),"",1);
		String xymc = Base.chgNull(request.getParameter("xymc"),"",1);
		String zymc = Base.chgNull(request.getParameter("zymc"),"",1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"),"",1);
		String xz = Base.chgNull(request.getParameter("xz"),"",1);
		String hjszd = Base.chgNull(request.getParameter("hjszd"),"",1);
		String jtjzdz = Base.chgNull(request.getParameter("jtjzdz"),"",1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"),"",1);
		String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),"",1);
		String grlxdh = Base.chgNull(request.getParameter("grlxdh"),"",1);
		String yxdz = Base.chgNull(request.getParameter("yxdz"),"",1);
		String gzdwmc = Base.chgNull(request.getParameter("gzdwmc"),"",1);
		String gzdwdz = Base.chgNull(request.getParameter("gzdwdz"),"",1);
		String gzdwyb = Base.chgNull(request.getParameter("gzdwyb"),"",1);
		String dwdh = Base.chgNull(request.getParameter("dwdh"),"",1);
		String hth = Base.chgNull(request.getParameter("hth"),"",1);
		String htje = Base.chgNull(request.getParameter("htje"),"",1);
		String htzje = Base.chgNull(request.getParameter("htzje"),"",1);
		String dkqx = Base.chgNull(request.getParameter("dkqx"),"",1);
		String zfxjtrq = Base.chgNull(request.getParameter("zfxjtrq"),"",1);
		String nll = Base.chgNull(request.getParameter("nll"),"",1);
		String hkzhlx = Base.chgNull(request.getParameter("hkzhlx"),"",1);
		String hkzhhm = Base.chgNull(request.getParameter("hkzhhm"),"",1);
		String hksj = Base.chgNull(request.getParameter("hksj"),"",1);
		String yhkje = Base.chgNull(request.getParameter("yhkje"),"",1);
		String yqqs = Base.chgNull(request.getParameter("yqqs"),"",1);
		String yqbj = Base.chgNull(request.getParameter("yqbj"),"",1);
		String yqfx = Base.chgNull(request.getParameter("yqfx"),"",1);
		String yqyy = Base.chgNull(request.getParameter("yqyy"),"",1);
		String bz = Base.chgNull(request.getParameter("bz"),"",1);

		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = new String[] { xh, xm, xb, sfzh, nj, xymc, zymc,
				bjmc, xz, hjszd, jtjzdz, yzbm, jtlxdh, grlxdh, yxdz, gzdwmc,
				gzdwdz, gzdwyb, dwdh, hth, htje, htzje, dkqx, zfxjtrq, nll,
				hkzhlx, hkzhhm, hksj, yhkje, yqqs, yqbj, yqfx, yqyy, bz };
		String[] outString = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "xz", "hjszd", "jtjzdz", "yzbm",
				"jtlxdh", "grlxdh", "yxdz", "gzdwmc", "gzdwdz", "gzdwyb",
				"dwdh", "hth", "htje", "htzje", "dkqx", "zfxjtrq", "nll",
				"hkzhlx", "hkzhhm", "hksj", "yhkje", "yqqs", "yqbj", "yqfx",
				"yqyy", "bz" };
		// System.out.println(outString[35]+outValue[35]);
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				// System.out.println("i="+i+" "+outString[i]);
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], " ");
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("hdxxsqb");
	}

	/**
	 * @describe 还贷基本信息审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward hdxxshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
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
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String hth = Base.chgNull(request.getParameter("hth"), "", 1);
		realTable = "gdnzzy_hdjbxx";
		pk = "xh||hth";
		tableName = "view_gdnzzy_hdjbxx";
		
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(hth)) {
			querry.append(" and hth='");
			querry.append(hth);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 还贷基本信息审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 还贷基本信息";
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm",
					"xb", "xymc", "bjmc", "nj", "hth", "htje", "dkqx",
					"zfxjtrq", "hksj" };
			sql = "select '#FFFFFF' bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.hth pk3,a.xh,a.xm,a.xb,a.xymc,a.bjmc,a.nj,a.hth,a.htje,a.dkqx,a.zfxjtrq,a.hksj from "
					+ tableName + " a" + querry.toString()
					+ " order by hth,htje,dkqx,zfxjtrq) a";
			request.setAttribute("isXX", "is");
		} else {
			colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "xh", "xm",
					"xb", "xymc", "bjmc", "nj", "hth", "htje", "dkqx",
					"zfxjtrq", "hksj" };
			sql = "select '#FFFFFF' bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.hth pk3,a.xh,a.xm,a.xb,a.xymc,a.bjmc,a.nj,a.hth,a.htje,a.dkqx,a.zfxjtrq,a.hksj from "
					+ tableName + " a" + querry.toString()
					+ " order by hth,htje,dkqx,zfxjtrq) a";
			request.setAttribute("isXX", "is");
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
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

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("hth", hth);
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
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("hdxxshList");
	}

	/**
	 * @describe 还贷基本信息审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward hdxxshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String actDo = Base.chgNull(request.getParameter("actDo"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String yhkje = Base.chgNull(request.getParameter("yhkje"), "", 1);
		String yqqs = Base.chgNull(request.getParameter("yqqs"), "", 1);
		String yqbj = Base.chgNull(request.getParameter("yqbj"), "", 1);
		String yqfx = Base.chgNull(request.getParameter("yqfx"), "", 1);
		String yqyy = Base.chgNull(request.getParameter("yqyy"), "", 1);
		String bz = Base.chgNull(request.getParameter("bz"), "", 1);

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				sqlT[i] = "delete gdnzzy_hdjbxx where xh||hth='"+pkT+"'";
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/gnnzzy_gjzxdk.do?method=hdxxshList&go=go", false);
			return newFwd;
		}
		

		if (actDo.equalsIgnoreCase("save")) {
			StandardOperation.update("gdnzzy_hdjbxx", new String[] { "yhkje",
					"yqqs", "yqbj", "yqfx", "yqyy", "bz" }, new String[] {
					yhkje, yqqs, yqbj, yqfx, yqyy, bz }, "xh||hth", pkVal,
					request);
		}
		realTable = "gdnzzy_hdjbxx";
		pk = "xh||hth";
		sql = "select xh,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,hjszd,jtjzdz,yzbm,jtlxdh,grlxdh,yxdz,gzdwmc,gzdwdz,gzdwyb,dwdh,hth,htje,htzje,dkqx,zfxjtrq,nll,hkzhlx,hkzhhm,hksj,yhkje,yqqs,yqbj,yqfx,yqyy,bz from view_gdnzzy_hdjbxx where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
			sql = "select "
				+ pk
				+ " pk,a.xh,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xz,a.hjszd,a.jtjzdz,a.yzbm,a.jtlxdh,a.grlxdh,a.yxdz,a.gzdwmc,a.gzdwdz,a.gzdwyb,a.dwdh,a.hth,a.htje,a.htzje,a.dkqx,a.zfxjtrq,a.nll,a.hkzhlx,a.hkzhhm,a.hksj,a.yhkje,a.yqqs,a.yqbj,a.yqfx,a.yqyy,a.bz "
				+ "from view_gdnzzy_hdjbxx a where " + pk + "='" + pkVal + "'";
		colList = new String[(outString.length+1)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			request.setAttribute(colList[i].toLowerCase(), rs[i]);
			hs.put(colList[i].toLowerCase(), rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_gdnzzy_hdjbxx");
		request.setAttribute("act", "zzsh");
		return mapping.findForward("hdxxshXxxx");
	}
	
	/**
	 * @describe 还贷基本信息列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward hdxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String hth = Base.chgNull(request.getParameter("hth"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		if (!isNull(hth)) {
			querry.append(" and hth='");
			querry.append(hth);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_gdnzzy_hdjbxx " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_gdnzzy_hdjbxx where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_gdnzzy_hdjbxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("hdxxExp");
	}
}
