package xgxt.action.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.SearchUtils;
import xgxt.xszz.dao.bjlhdx.XszzDAO;

public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	public ActionForward fsbzmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XszzDAO dao = XszzDAO.getInstance();
		String bzffny = Base.chgNull(request.getParameter("bzffny"), " ", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), " ", 1);
		// String ssdm = request.getParameter("ssdm");
		String cxxydmSrc = Base.chgNull(request.getParameter("cxxydmSrc"), " ",
				1);
		String cxxydmDest = Base.chgNull(request.getParameter("cxxydmDest"),
				" ", 1);
		String cxxydmSrcmc = Base.chgNull(request.getParameter("cxxydmSrcmc"),
				" ", 1);
		String cxxydmDestmc = Base.chgNull(
				request.getParameter("cxxydmDestmc"), " ", 1);
		String flag = Base.chgNull(request.getParameter("flag"), " ", 1);

		bzffny = Base.isNull(bzffny) ? "%" : bzffny;
		xydm = Base.isNull(xydm) ? "%" : xydm;

		List list = dao.fsbzmx(bzffny, cxxydmDest, flag);
		List zjList = dao.zjFsbzmx(bzffny, cxxydmDest, flag);
		request.setAttribute("list", list);
		request.setAttribute("zjList", zjList);
		request.setAttribute("bzffny", bzffny);
		request.setAttribute("flag", flag);

		request.setAttribute("cxxydmSrcmc", cxxydmSrcmc);
		request.setAttribute("cxxydmDestmc", cxxydmDestmc);
		request.setAttribute("cxxydmSrc", cxxydmSrc);
		request.setAttribute("cxxydmDest", cxxydmDest);
		request.setAttribute("headList", (cxxydmDestmc).split("-"));

		return mapping.findForward("fsbzmx");
	}

	/**
	 * @describe 国家助学贷款金额初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkjecsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("xszz_bjlh_gjzxdkjeszb", new String[] { "1" },
				new String[] { "1" }, request);

		dao
				.runUpdateTab("insert into xszz_bjlh_gjzxdkjeszb(zydm) select zydm from view_njxyzybj group by zydm");
		return new ActionForward("/bjlhdx_xszz.do?method=gjzxdkjesd&go=go",
				false);
	}

	/**
	 * @describe 国家助学贷款金额设置
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkjesd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "zydm";
		String tips = "学生资助 - 基础数据维护 - 国家助学贷款金额设定";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_xszz_bjlh_gjzxdkjeszb";
		String[] colList = new String[] { "主键", "xymc", "zymc", "dkze", "xfje" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 0);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("zydm", zydm);
		map.put("xydm", xydm);

		String sql = "select zydm 主键,a.* from view_xszz_bjlh_gjzxdkjeszb a"
				+ querry.toString() + " order by xydm,zydm";
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

		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("gjzxdkjesd");
	}

	/**
	 * @describe 根据主键得到国家助学贷款金额详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdkjeEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xymc,xydm,zymc,zydm,dkze,xfje from view_xszz_bjlh_gjzxdkjeszb where zydm=?";
		String[] outString = new String[] { "xymc", "xydm", "zymc", "zydm",
				"dkze", "xfje" };

		if ("save".equalsIgnoreCase(act)) {
			String zydm = Base.chgNull(request.getParameter("zydm"), "", 1);
			String dkze = Base.chgNull(request.getParameter("dkze"), "", 1);
			String xfje = Base.chgNull(request.getParameter("xfje"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_xszz_bjlh_gjzxdkjeszb where zydm=?",
							new String[] { zydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("xszz_bjlh_gjzxdkjeszb",
						new String[] { "zydm", "dkze", "xfje" }, new String[] {
								zydm, dkze, xfje }, request);
			} else {
				b = StandardOperation.update("xszz_bjlh_gjzxdkjeszb",
						new String[] { "dkze", "xfje" }, new String[] { dkze,
								xfje }, "zydm", zydm, request);
			}
			if (b) {
				request.setAttribute("ok", "ok");
				pkVal = zydm;
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
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

		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkjeEdit");
	}

	/**
	 * @describe 批量设置国家助学贷款金额
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkjePlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String dkze = "0";
		String xfje = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			dkze = Base.chgNull(request.getParameter("dkze"), "", 1);
			xfje = Base.chgNull(request.getParameter("xfje"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update xszz_bjlh_gjzxdkjeszb set dkze='" + dkze
						+ "',xfje='" + xfje + "' where zydm='" + pkT[i] + "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("dkze", dkze);
		map.put("xfje", xfje);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("gjzxdkjePlsz");
	}

	/**
	 * @describe 国家助学贷款申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xjzt,pycc,yddh,gddh,jtzz,yzbm,jtjjqk,fqxm,fqgzdw,fqlxdh,fqsfzh,mqxm,mqgzdw,mqlxdh,mqsfzh,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,xfdkze,shfdkze,dkze,dkhth,dkfdsj,dkdqsj,jbyh,fzjgmc,pzrq,jby,hkksrq,hkjzrq,sqsj,sqzt,fdysh,fdyshyj,fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj,'' byny from view_xszz_bjlh_gjzxdksqb where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String xn = Base.currXn;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "", 1);

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
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String pycc = Base.chgNull(request.getParameter("pycc"),
							"", 1);
					String yddh = Base.chgNull(request.getParameter("yddh"),
							"", 1);
					String gddh = Base.chgNull(request.getParameter("gddh"),
							"", 1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),
							"", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String jtjjqk = Base.chgNull(
							request.getParameter("jtjjqk"), "", 1);
					String fqxm = Base.chgNull(request.getParameter("fqxm"),
							"", 1);
					String fqgzdw = Base.chgNull(
							request.getParameter("fqgzdw"), "", 1);
					String fqlxdh = Base.chgNull(
							request.getParameter("fqlxdh"), "", 1);
					String fqsfzh = Base.chgNull(
							request.getParameter("fqsfzh"), "", 1);
					String mqxm = Base.chgNull(request.getParameter("mqxm"),
							"", 1);
					String mqgzdw = Base.chgNull(
							request.getParameter("mqgzdw"), "", 1);
					String mqlxdh = Base.chgNull(
							request.getParameter("mqlxdh"), "", 1);
					String mqsfzh = Base.chgNull(request
							.getParameter("mqsfzh"), "", 1);
					String sxncj1_mc = Base.chgNull(request
							.getParameter("sxncj1_mc"), "", 1);
					String sxncj1_cj = Base.chgNull(request
							.getParameter("sxncj1_cj"), "", 1);
					String sxncj2_mc = Base.chgNull(request
							.getParameter("sxncj2_mc"), "", 1);
					String sxncj2_cj = Base.chgNull(request
							.getParameter("sxncj2_cj"), "", 1);
					String sxncj3_mc = Base.chgNull(request
							.getParameter("sxncj3_mc"), "", 1);
					String sxncj3_cj = Base.chgNull(request
							.getParameter("sxncj3_cj"), "", 1);
					String sxncj4_mc = Base.chgNull(request
							.getParameter("sxncj4_mc"), "", 1);
					String sxncj4_cj = Base.chgNull(request
							.getParameter("sxncj4_cj"), "", 1);
					String sxncj5_mc = Base.chgNull(request
							.getParameter("sxncj5_mc"), "", 1);
					String sxncj5_cj = Base.chgNull(request
							.getParameter("sxncj5_cj"), "", 1);
					String sxncj6_mc = Base.chgNull(request
							.getParameter("sxncj6_mc"), "", 1);
					String sxncj6_cj = Base.chgNull(request
							.getParameter("sxncj6_cj"), "", 1);
					String sxncj7_mc = Base.chgNull(request
							.getParameter("sxncj7_mc"), "", 1);
					String sxncj7_cj = Base.chgNull(request
							.getParameter("sxncj7_cj"), "", 1);
					String sxncj8_mc = Base.chgNull(request
							.getParameter("sxncj8_mc"), "", 1);
					String sxncj8_cj = Base.chgNull(request
							.getParameter("sxncj8_cj"), "", 1);
					String sxncj9_mc = Base.chgNull(request
							.getParameter("sxncj9_mc"), "", 1);
					String sxncj9_cj = Base.chgNull(request
							.getParameter("sxncj9_cj"), "", 1);
					String sxncj10_mc = Base.chgNull(request
							.getParameter("sxncj10_mc"), "", 1);
					String sxncj10_cj = Base.chgNull(request
							.getParameter("sxncj10_cj"), "", 1);
					String rxlbjgkm = Base.chgNull(request
							.getParameter("rxlbjgkm"), "", 1);
					String rxlbktgkm = Base.chgNull(request
							.getParameter("rxlbktgkm"), "", 1);
					String xfdkze = Base.chgNull(
							request.getParameter("xfdkze"), "", 1);
					String shfdkze = Base.chgNull(request
							.getParameter("shfdkze"), "", 1);
					String dkze = Base.chgNull(request.getParameter("dkze"),
							"", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xn + xh;
					}
					sql = "select sqzt from XSZZ_BJLH_GJZXDKSQB where xn||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "sqzt" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("银行贷审批")
									|| temp[0].equalsIgnoreCase("已放贷") || temp[0]
									.equalsIgnoreCase("已还款"))) {
						boolean tBol = StandardOperation.update(
								"xszz_bjlhdx_gjzxgk_xsjbxx", new String[] {
										"yddh", "gddh", "jtzz", "yzbm",
										"jtjjqk", "fqxm", "fqgzdw", "fqlxdh",
										"fqsfzh", "mqxm", "mqgzdw", "mqlxdh",
										"mqsfzh" }, new String[] { yddh, gddh,
										jtzz, yzbm, jtjjqk, fqxm, fqgzdw,
										fqlxdh, fqsfzh, mqxm, mqgzdw, mqlxdh,
										mqsfzh }, "xh", xh, request);
						request.setAttribute("tBol", tBol);
						request.setAttribute("isPASS", "is");
					} else {
						sql = "select count(*) num from xszz_bjlhdx_gjzxgk_xsjbxx where xh=?";
						String tNum = dao.getOneRs(sql, new String[] { xh },
								"num");
						if (tNum.equalsIgnoreCase("0")) {
							StandardOperation.insert(
									"xszz_bjlhdx_gjzxgk_xsjbxx", new String[] {
											"xh", "pycc", "yddh", "gddh",
											"jtzz", "yzbm", "jtjjqk", "fqxm",
											"fqgzdw", "fqlxdh", "fqsfzh",
											"mqxm", "mqgzdw", "mqlxdh",
											"mqsfzh", "sxncj1_mc", "sxncj1_cj",
											"sxncj2_mc", "sxncj2_cj",
											"sxncj3_mc", "sxncj3_cj",
											"sxncj4_mc", "sxncj4_cj",
											"sxncj5_mc", "sxncj5_cj",
											"sxncj6_mc", "sxncj6_cj",
											"sxncj7_mc", "sxncj7_cj",
											"sxncj8_mc", "sxncj8_cj",
											"sxncj9_mc", "sxncj9_cj",
											"sxncj10_mc", "sxncj10_cj",
											"rxlbjgkm", "rxlbktgkm" },
									new String[] { xh, pycc, yddh, gddh, jtzz,
											yzbm, jtjjqk, fqxm, fqgzdw, fqlxdh,
											fqsfzh, mqxm, mqgzdw, mqlxdh,
											mqsfzh, sxncj1_mc, sxncj1_cj,
											sxncj2_mc, sxncj2_cj, sxncj3_mc,
											sxncj3_cj, sxncj4_mc, sxncj4_cj,
											sxncj5_mc, sxncj5_cj, sxncj6_mc,
											sxncj6_cj, sxncj7_mc, sxncj7_cj,
											sxncj8_mc, sxncj8_cj, sxncj9_mc,
											sxncj9_cj, sxncj10_mc, sxncj10_cj,
											rxlbjgkm, rxlbktgkm }, request);
						} else {
							StandardOperation.update(
									"xszz_bjlhdx_gjzxgk_xsjbxx", new String[] {
											"pycc", "yddh", "gddh", "jtzz",
											"yzbm", "jtjjqk", "fqxm", "fqgzdw",
											"fqlxdh", "fqsfzh", "mqxm",
											"mqgzdw", "mqlxdh", "mqsfzh",
											"sxncj1_mc", "sxncj1_cj",
											"sxncj2_mc", "sxncj2_cj",
											"sxncj3_mc", "sxncj3_cj",
											"sxncj4_mc", "sxncj4_cj",
											"sxncj5_mc", "sxncj5_cj",
											"sxncj6_mc", "sxncj6_cj",
											"sxncj7_mc", "sxncj7_cj",
											"sxncj8_mc", "sxncj8_cj",
											"sxncj9_mc", "sxncj9_cj",
											"sxncj10_mc", "sxncj10_cj",
											"rxlbjgkm", "rxlbktgkm" },
									new String[] { pycc, yddh, gddh, jtzz,
											yzbm, jtjjqk, fqxm, fqgzdw, fqlxdh,
											fqsfzh, mqxm, mqgzdw, mqlxdh,
											mqsfzh, sxncj1_mc, sxncj1_cj,
											sxncj2_mc, sxncj2_cj, sxncj3_mc,
											sxncj3_cj, sxncj4_mc, sxncj4_cj,
											sxncj5_mc, sxncj5_cj, sxncj6_mc,
											sxncj6_cj, sxncj7_mc, sxncj7_cj,
											sxncj8_mc, sxncj8_cj, sxncj9_mc,
											sxncj9_cj, sxncj10_mc, sxncj10_cj,
											rxlbjgkm, rxlbktgkm }, "xh", xh,
									request);
						}

						StandardOperation.delete("XSZZ_BJLH_GJZXDKSQB",
								"xn||xh", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh",
								"xfdkze", "shfdkze", "dkze" };

						valueForOut = new String[] { xn, xh, xfdkze, shfdkze,
								dkze };

						boolean ok = false;
						ok = StandardOperation.insert("XSZZ_BJLH_GJZXDKSQB",
								colName1, valueForOut, request);
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
				sql = "select count(*) num from xszz_bjlhdx_gjzxgk_xsjbxx where xh=?";
				String tNum = dao.getOneRs(sql, new String[] { xh }, "num");
				if (tNum.equalsIgnoreCase("0")) {
					sfksq = "-1";
					request.setAttribute("sfksq", sfksq);// 不能申请
				} else {
					if (doType != null && doType.equalsIgnoreCase("save")) {
						String yddh = Base.chgNull(
								request.getParameter("yddh"), "", 1);
						String gddh = Base.chgNull(
								request.getParameter("gddh"), "", 1);
						String jtzz = Base.chgNull(
								request.getParameter("jtzz"), "", 1);
						String yzbm = Base.chgNull(
								request.getParameter("yzbm"), "", 1);
						String fqxm = Base.chgNull(
								request.getParameter("fqxm"), "", 1);
						String fqgzdw = Base.chgNull(request
								.getParameter("fqgzdw"), "", 1);
						String fqlxdh = Base.chgNull(request
								.getParameter("fqlxdh"), "", 1);
						String fqsfzh = Base.chgNull(request
								.getParameter("fqsfzh"), "", 1);
						String mqxm = Base.chgNull(
								request.getParameter("mqxm"), "", 1);
						String mqgzdw = Base.chgNull(request
								.getParameter("mqgzdw"), "", 1);
						String mqlxdh = Base.chgNull(request
								.getParameter("mqlxdh"), "", 1);
						String mqsfzh = Base.chgNull(request
								.getParameter("mqsfzh"), "", 1);
						boolean ok = false;
						ok = StandardOperation.update(
								"xszz_bjlhdx_gjzxgk_xsjbxx", new String[] {
										"yddh", "gddh", "jtzz", "yzbm", "fqxm",
										"fqgzdw", "fqlxdh", "fqsfzh", "mqxm",
										"mqgzdw", "mqlxdh", "mqsfzh" },
								new String[] { yddh, gddh, jtzz, yzbm, fqxm,
										fqgzdw, fqlxdh, fqsfzh, mqxm, mqgzdw,
										mqlxdh, mqsfzh }, "xh", xh, request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
					sfksq = "-2";
					request.setAttribute("sfksq", sfksq);
				}
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String pycc = Base.chgNull(request.getParameter("pycc"), "", 1);
				String yddh = Base.chgNull(request.getParameter("yddh"), "", 1);
				String gddh = Base.chgNull(request.getParameter("gddh"), "", 1);
				String jtzz = Base.chgNull(request.getParameter("jtzz"), "", 1);
				String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
				String jtjjqk = Base.chgNull(request.getParameter("jtjjqk"),
						"", 1);
				String fqxm = Base.chgNull(request.getParameter("fqxm"), "", 1);
				String fqgzdw = Base.chgNull(request.getParameter("fqgzdw"),
						"", 1);
				String fqlxdh = Base.chgNull(request.getParameter("fqlxdh"),
						"", 1);
				String fqsfzh = Base.chgNull(request.getParameter("fqsfzh"),
						"", 1);
				String mqxm = Base.chgNull(request.getParameter("mqxm"), "", 1);
				String mqgzdw = Base.chgNull(request.getParameter("mqgzdw"),
						"", 1);
				String mqlxdh = Base.chgNull(request.getParameter("mqlxdh"),
						"", 1);
				String mqsfzh = Base.chgNull(request.getParameter("mqsfzh"),
						"", 1);
				String sxncj1_mc = Base.chgNull(request
						.getParameter("sxncj1_mc"), "", 1);
				String sxncj1_cj = Base.chgNull(request
						.getParameter("sxncj1_cj"), "", 1);
				String sxncj2_mc = Base.chgNull(request
						.getParameter("sxncj2_mc"), "", 1);
				String sxncj2_cj = Base.chgNull(request
						.getParameter("sxncj2_cj"), "", 1);
				String sxncj3_mc = Base.chgNull(request
						.getParameter("sxncj3_mc"), "", 1);
				String sxncj3_cj = Base.chgNull(request
						.getParameter("sxncj3_cj"), "", 1);
				String sxncj4_mc = Base.chgNull(request
						.getParameter("sxncj4_mc"), "", 1);
				String sxncj4_cj = Base.chgNull(request
						.getParameter("sxncj4_cj"), "", 1);
				String sxncj5_mc = Base.chgNull(request
						.getParameter("sxncj5_mc"), "", 1);
				String sxncj5_cj = Base.chgNull(request
						.getParameter("sxncj5_cj"), "", 1);
				String sxncj6_mc = Base.chgNull(request
						.getParameter("sxncj6_mc"), "", 1);
				String sxncj6_cj = Base.chgNull(request
						.getParameter("sxncj6_cj"), "", 1);
				String sxncj7_mc = Base.chgNull(request
						.getParameter("sxncj7_mc"), "", 1);
				String sxncj7_cj = Base.chgNull(request
						.getParameter("sxncj7_cj"), "", 1);
				String sxncj8_mc = Base.chgNull(request
						.getParameter("sxncj8_mc"), "", 1);
				String sxncj8_cj = Base.chgNull(request
						.getParameter("sxncj8_cj"), "", 1);
				String sxncj9_mc = Base.chgNull(request
						.getParameter("sxncj9_mc"), "", 1);
				String sxncj9_cj = Base.chgNull(request
						.getParameter("sxncj9_cj"), "", 1);
				String sxncj10_mc = Base.chgNull(request
						.getParameter("sxncj10_mc"), "", 1);
				String sxncj10_cj = Base.chgNull(request
						.getParameter("sxncj10_cj"), "", 1);
				String rxlbjgkm = Base.chgNull(
						request.getParameter("rxlbjgkm"), "", 1);
				String rxlbktgkm = Base.chgNull(request
						.getParameter("rxlbktgkm"), "", 1);
				String xfdkze = Base.chgNull(request.getParameter("xfdkze"),
						"", 1);
				String shfdkze = Base.chgNull(request.getParameter("shfdkze"),
						"", 1);
				String dkze = Base.chgNull(request.getParameter("dkze"), "", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xn + xh;
				}
				sql = "select sqzt from XSZZ_BJLH_GJZXDKSQB where xn||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "sqzt" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("银行贷审批")
								|| temp[0].equalsIgnoreCase("已放贷") || temp[0]
								.equalsIgnoreCase("已还款"))) {
					boolean tBol = StandardOperation.update(
							"xszz_bjlhdx_gjzxgk_xsjbxx", new String[] { "yddh",
									"gddh", "jtzz", "yzbm", "jtjjqk", "fqxm",
									"fqgzdw", "fqlxdh", "fqsfzh", "mqxm",
									"mqgzdw", "mqlxdh", "mqsfzh" },
							new String[] { yddh, gddh, jtzz, yzbm, jtjjqk,
									fqxm, fqgzdw, fqlxdh, fqsfzh, mqxm, mqgzdw,
									mqlxdh, mqsfzh }, "xh", xh, request);
					if ("is".equalsIgnoreCase(isQuery)){
						String dkhth = Base.chgNull(request
								.getParameter("dkhth"), "", 1);
						String jbyh = Base.chgNull(request
								.getParameter("jbyh"), "", 1);
						String fzjgmc = Base.chgNull(request
								.getParameter("fzjgmc"), "", 1);
						String pzrq = Base.chgNull(request
								.getParameter("pzrq"), "", 1);
						String jby = Base.chgNull(request
								.getParameter("jby"), "", 1);
						String hkksrq = Base.chgNull(request
								.getParameter("hkksrq"), "", 1);
						String hkjzrq = Base.chgNull(request
								.getParameter("hkjzrq"), "", 1);
						StandardOperation.update("XSZZ_BJLH_GJZXDKSQB", new String[]{"dkhth", "jbyh", "fzjgmc", "pzrq", "jby", "hkksrq", "hkjzrq"}, new String[]{dkhth,jbyh,fzjgmc,pzrq,jby,hkksrq,hkjzrq}, "xn||xh",pkVal, request);
					}
					request.setAttribute("tBol", tBol);
					request.setAttribute("isPASS", "is");
				} else {
					sql = "select count(*) num from xszz_bjlhdx_gjzxgk_xsjbxx where xh=?";
					String tNum = dao.getOneRs(sql, new String[] { xh }, "num");
					if (tNum.equalsIgnoreCase("0")) {
						StandardOperation.insert("xszz_bjlhdx_gjzxgk_xsjbxx",
								new String[] { "xh", "pycc", "yddh", "gddh",
										"jtzz", "yzbm", "jtjjqk", "fqxm",
										"fqgzdw", "fqlxdh", "fqsfzh", "mqxm",
										"mqgzdw", "mqlxdh", "mqsfzh",
										"sxncj1_mc", "sxncj1_cj", "sxncj2_mc",
										"sxncj2_cj", "sxncj3_mc", "sxncj3_cj",
										"sxncj4_mc", "sxncj4_cj", "sxncj5_mc",
										"sxncj5_cj", "sxncj6_mc", "sxncj6_cj",
										"sxncj7_mc", "sxncj7_cj", "sxncj8_mc",
										"sxncj8_cj", "sxncj9_mc", "sxncj9_cj",
										"sxncj10_mc", "sxncj10_cj", "rxlbjgkm",
										"rxlbktgkm" }, new String[] { xh, pycc,
										yddh, gddh, jtzz, yzbm, jtjjqk, fqxm,
										fqgzdw, fqlxdh, fqsfzh, mqxm, mqgzdw,
										mqlxdh, mqsfzh, sxncj1_mc, sxncj1_cj,
										sxncj2_mc, sxncj2_cj, sxncj3_mc,
										sxncj3_cj, sxncj4_mc, sxncj4_cj,
										sxncj5_mc, sxncj5_cj, sxncj6_mc,
										sxncj6_cj, sxncj7_mc, sxncj7_cj,
										sxncj8_mc, sxncj8_cj, sxncj9_mc,
										sxncj9_cj, sxncj10_mc, sxncj10_cj,
										rxlbjgkm, rxlbktgkm }, request);
					} else {
						StandardOperation
								.update(
										"xszz_bjlhdx_gjzxgk_xsjbxx",
										new String[] { "pycc", "yddh", "gddh",
												"jtzz", "yzbm", "jtjjqk",
												"fqxm", "fqgzdw", "fqlxdh",
												"fqsfzh", "mqxm", "mqgzdw",
												"mqlxdh", "mqsfzh",
												"sxncj1_mc", "sxncj1_cj",
												"sxncj2_mc", "sxncj2_cj",
												"sxncj3_mc", "sxncj3_cj",
												"sxncj4_mc", "sxncj4_cj",
												"sxncj5_mc", "sxncj5_cj",
												"sxncj6_mc", "sxncj6_cj",
												"sxncj7_mc", "sxncj7_cj",
												"sxncj8_mc", "sxncj8_cj",
												"sxncj9_mc", "sxncj9_cj",
												"sxncj10_mc", "sxncj10_cj",
												"rxlbjgkm", "rxlbktgkm" },
										new String[] { pycc, yddh, gddh, jtzz,
												yzbm, jtjjqk, fqxm, fqgzdw,
												fqlxdh, fqsfzh, mqxm, mqgzdw,
												mqlxdh, mqsfzh, sxncj1_mc,
												sxncj1_cj, sxncj2_mc,
												sxncj2_cj, sxncj3_mc,
												sxncj3_cj, sxncj4_mc,
												sxncj4_cj, sxncj5_mc,
												sxncj5_cj, sxncj6_mc,
												sxncj6_cj, sxncj7_mc,
												sxncj7_cj, sxncj8_mc,
												sxncj8_cj, sxncj9_mc,
												sxncj9_cj, sxncj10_mc,
												sxncj10_cj, rxlbjgkm, rxlbktgkm },
										"xh", xh, request);
					}

					if ("is".equalsIgnoreCase(isQuery)) {
						String num = dao.getOneRs("select count(*) num from XSZZ_BJLH_GJZXDKSQB where xn||xh=?", new String[]{pkVal}, "num");
						boolean ok = false;
						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh",
								"xfdkze", "shfdkze", "dkze" };
						
						valueForOut = new String[] { xn, xh, xfdkze, shfdkze,
								dkze };
						if ("0".equalsIgnoreCase(num)) {
							ok = StandardOperation.insert(
									"XSZZ_BJLH_GJZXDKSQB", colName1,
									valueForOut, request);
						} else {
							ok = StandardOperation.update(
									"XSZZ_BJLH_GJZXDKSQB", new String[] {
											"xfdkze", "shfdkze", "dkze" },
									new String[] { xfdkze, shfdkze, dkze },
									"xn||xh", pkVal, request);
						}

						if (ok) {
							String dkhth = Base.chgNull(request
									.getParameter("dkhth"), "", 1);
							String jbyh = Base.chgNull(request
									.getParameter("jbyh"), "", 1);
							String fzjgmc = Base.chgNull(request
									.getParameter("fzjgmc"), "", 1);
							String pzrq = Base.chgNull(request
									.getParameter("pzrq"), "", 1);
							String jby = Base.chgNull(request
									.getParameter("jby"), "", 1);
							String hkksrq = Base.chgNull(request
									.getParameter("hkksrq"), "", 1);
							String hkjzrq = Base.chgNull(request
									.getParameter("hkjzrq"), "", 1);
							String htzje = Base.chgNull(request
									.getParameter("htzje"), "", 1);
							StandardOperation
									.update(
											"XSZZ_BJLH_GJZXDKSQB",
											new String[] { "dkhth", "jbyh",
													"fzjgmc", "pzrq", "jby",
													"hkksrq", "hkjzrq", "htzje" },
											new String[] { dkhth, jbyh, fzjgmc,
													pzrq, jby, hkksrq, hkjzrq, htzje },
											"xn||xh", pkVal, request);
							logMsg = "申请" + titName;
							Base.log(request, logMsg, sUName);
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						StandardOperation.delete("XSZZ_BJLH_GJZXDKSQB",
								"xn||xh", pkVal, request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh",
								"xfdkze", "shfdkze", "dkze" };

						valueForOut = new String[] { xn, xh, xfdkze, shfdkze,
								dkze };

						boolean ok = false;
						ok = StandardOperation.insert("XSZZ_BJLH_GJZXDKSQB",
								colName1, valueForOut, request);
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
		}
		pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if (!"".equalsIgnoreCase(pkVal)) {
		} else if (!"".equalsIgnoreCase(xh)){
			pkVal = xn + xh;
		}

		sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xjzt,pycc,yddh,gddh,jtzz,yzbm,jtjjqk,fqxm,fqgzdw,fqlxdh,fqsfzh,mqxm,mqgzdw,mqlxdh,mqsfzh,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,xfdkze,shfdkze,dkze,dkhth,dkfdsj,dkdqsj,htzje,jbyh,fzjgmc,pzrq,jby,hkksrq,hkjzrq,sqsj,sqzt,fdysh,fdyshyj,fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny from view_xszz_bjlh_gjzxdksqb a where xn||xh=?";
		outString = new String[] { "xn", "xh", "xm", "xb", "mzmc", "zzmmmc",
				"xz", "nj", "sfzh", "rxny", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "xjzt", "pycc", "yddh", "gddh", "jtzz", "yzbm",
				"jtjjqk", "fqxm", "fqgzdw", "fqlxdh", "fqsfzh", "mqxm",
				"mqgzdw", "mqlxdh", "mqsfzh", "sxncj1_mc", "sxncj1_cj",
				"sxncj2_mc", "sxncj2_cj", "sxncj3_mc", "sxncj3_cj",
				"sxncj4_mc", "sxncj4_cj", "sxncj5_mc", "sxncj5_cj",
				"sxncj6_mc", "sxncj6_cj", "sxncj7_mc", "sxncj7_cj",
				"sxncj8_mc", "sxncj8_cj", "sxncj9_mc", "sxncj9_cj",
				"sxncj10_mc", "sxncj10_cj", "rxlbjgkm", "rxlbktgkm", "xfdkze",
				"shfdkze", "dkze", "dkhth", "dkfdsj", "dkdqsj", "htzje", "jbyh",
				"fzjgmc", "pzrq", "jby", "hkksrq", "hkjzrq", "sqsj", "sqzt",
				"fdysh", "fdyshyj", "fdyshsj", "xysh", "xyshyj", "xyshsj",
				"xxsh", "xxshyj", "xxshsj", "byny" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh && !"".equalsIgnoreCase(xh)) {
				sql = "select count(*) num from bjlhdx_kns where xh=? and xxsh in ('一般困难', '特殊困难', '其他困难')";
				String tNum = dao.getOneRs(sql, new String[] { xh }, "num");
				if (tNum.equalsIgnoreCase("0")) {
					request.setAttribute("notKNS", "notKNS");
				} else {
					sql = "select xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xjzt,pycc,yddh,gddh,jtzz,yzbm,jtjjqk,fqxm,fqgzdw,fqlxdh,fqsfzh,mqxm,mqgzdw,mqlxdh,mqsfzh,rxlbjgkm,rxlbktgkm,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny from view_xszz_bjlhdx_gjzxgk_xsjbxx a where xh=?";

					String[] colName = new String[] { "xh", "xm", "xb", "mzmc",
							"zzmmmc", "xz", "nj", "sfzh", "rxny", "xydm",
							"xymc", "zydm", "zymc", "bjdm", "bjmc", "xjzt",
							"pycc", "yddh", "gddh", "jtzz", "yzbm", "jtjjqk",
							"fqxm", "fqgzdw", "fqlxdh", "fqsfzh", "mqxm",
							"mqgzdw", "mqlxdh", "mqsfzh", "rxlbjgkm",
							"rxlbktgkm", "byny" };
					String[] outVal = dao.getOneRs(sql, new String[] { xh },
							colName);

					if (outVal == null) {
						sql = "select a.xh,a.xm,a.xb,a.mzmc,a.zzmmmc,a.xz,a.nj,a.sfzh,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,xymc,zymc,zydm,bjmc,NVL((select x.YDHXJZTM xjzt from view_xjydjbxx x where x.ydxh in (select max(y.ydxh) from view_xjydjbxx y where y.ydrq in (select max(z.ydrq) from view_xjydjbxx z where substr(z.ydrq,1,7)=to_char(sysdate,'yyyy-mm')) and y.xh=a.xh)),'正常') xjzt,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny from view_stu_details a where a.xh=?";

						String[] cName = new String[] { "xh", "xm", "xb",
								"mzmc", "zzmmmc", "xz", "nj", "sfzh", "rxny",
								"xymc", "zymc", "zydm", "bjmc", "xjzt", "byny" };
						String[] oVal = dao.getOneRs(sql, new String[] { xh },
								cName);

						for (int i = 0; i < cName.length; i++) {
							if (null != oVal[i]) {
								map.put(cName[i], oVal[i]);
							} else {
								map.put(cName[i], "");
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

		String now = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		map.put("sqsj", now);
		map.put("xn", xn);

		String zydm = dao.getOneRs(
				"select zydm from view_stu_details where xh=?",
				new String[] { "".equalsIgnoreCase(pkVal) ? "" : pkVal.substring(9) }, "zydm");
		if (zydm != null && !zydm.equalsIgnoreCase("")) {
			String[] outS = dao
					.getOneRs(
							"select dkze,xfje from view_XSZZ_BJLH_GJZXDKJESZB where zydm=?",
							new String[] { zydm }, new String[] { "dkze",
									"xfje" });
			if (outS != null) {
				map.put("szdkze", outS[0]);
				map.put("szxfje", outS[1]);
			} else {
				map.put("szdkze", "0");
				map.put("szxfje", "0");
			}
			if (outS[0].equalsIgnoreCase(outS[1])) {
				request.setAttribute("equ", "equ");
			} else {
				request.setAttribute("equ", "notEqu");
			}
		} else {
			map.put("szdkze", "0");
			map.put("szxfje", "0");
			request.setAttribute("equ", "equ");
		}
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("isQuery", isQuery);
		request.setAttribute("rs", map);
		return mapping.findForward("gjzxdksq");
	}

	/**
	 * @describe 打印国家助学贷款申请表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String xz = Base.chgNull(request.getParameter("xz"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String pycc = Base.chgNull(request.getParameter("pycc"), "", 1);
		String yddh = Base.chgNull(request.getParameter("yddh"), "", 1);
		String gddh = Base.chgNull(request.getParameter("gddh"), "", 1);
		String jtzz = Base.chgNull(request.getParameter("jtzz"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtjjqk = Base.chgNull(request.getParameter("jtjjqk"), "", 1);
		String sxncj1_mc = Base.chgNull(request.getParameter("sxncj1_mc"), "",
				1);
		String sxncj1_cj = Base.chgNull(request.getParameter("sxncj1_cj"), "",
				1);
		String sxncj2_mc = Base.chgNull(request.getParameter("sxncj2_mc"), "",
				1);
		String sxncj2_cj = Base.chgNull(request.getParameter("sxncj2_cj"), "",
				1);
		String sxncj3_mc = Base.chgNull(request.getParameter("sxncj3_mc"), "",
				1);
		String sxncj3_cj = Base.chgNull(request.getParameter("sxncj3_cj"), "",
				1);
		String sxncj4_mc = Base.chgNull(request.getParameter("sxncj4_mc"), "",
				1);
		String sxncj4_cj = Base.chgNull(request.getParameter("sxncj4_cj"), "",
				1);
		String sxncj5_mc = Base.chgNull(request.getParameter("sxncj5_mc"), "",
				1);
		String sxncj5_cj = Base.chgNull(request.getParameter("sxncj5_cj"), "",
				1);
		String sxncj6_mc = Base.chgNull(request.getParameter("sxncj6_mc"), "",
				1);
		String sxncj6_cj = Base.chgNull(request.getParameter("sxncj6_cj"), "",
				1);
		String sxncj7_mc = Base.chgNull(request.getParameter("sxncj7_mc"), "",
				1);
		String sxncj7_cj = Base.chgNull(request.getParameter("sxncj7_cj"), "",
				1);
		String sxncj8_mc = Base.chgNull(request.getParameter("sxncj8_mc"), "",
				1);
		String sxncj8_cj = Base.chgNull(request.getParameter("sxncj8_cj"), "",
				1);
		String sxncj9_mc = Base.chgNull(request.getParameter("sxncj9_mc"), "",
				1);
		String sxncj9_cj = Base.chgNull(request.getParameter("sxncj9_cj"), "",
				1);
		String sxncj10_mc = Base.chgNull(request.getParameter("sxncj10_mc"),
				"", 1);
		String sxncj10_cj = Base.chgNull(request.getParameter("sxncj10_cj"),
				"", 1);
		String rxlbjgkm = Base.chgNull(request.getParameter("rxlbjgkm"), "", 1);
		String rxlbktgkm = Base.chgNull(request.getParameter("rxlbktgkm"), "",
				1);
		String xfdkze = Base.chgNull(request.getParameter("xfdkze"), "0", 1);
		String shfdkze = Base.chgNull(request.getParameter("shfdkze"), "0", 1);
		String dkze = Base.chgNull(request.getParameter("dkze"), "0", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String fdyshyj = Base.chgNull(request.getParameter("fdyshyj"), "", 1);
		String fdyshsj = Base.chgNull(request.getParameter("fdyshsj"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String byny = Base.chgNull(request.getParameter("byny"), "", 1);
		String yjxf = Base.chgNull(request.getParameter("szxfje"), "0", 1);

		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";

		if ((null != fdyshsj) && (fdyshsj.length() == 10)) {
			year = fdyshsj.substring(0, 4);
			mon = fdyshsj.substring(5, 7);
			day = fdyshsj.substring(8);
			fdyshsj = year + "年" + mon + "月" + day + "日";
		}

		if ((null != xyshsj) && (xyshsj.length() == 10)) {
			year = xyshsj.substring(0, 4);
			mon = xyshsj.substring(5, 7);
			day = xyshsj.substring(8);
			xyshsj = year + "年" + mon + "月" + day + "日";
		}

		if ((null != xxshsj) && (xxshsj.length() == 10)) {
			year = xxshsj.substring(0, 4);
			mon = xxshsj.substring(5, 7);
			day = xxshsj.substring(8);
			xxshsj = year + "年" + mon + "月" + day + "日";
		}

		String[] outValue = new String[] { xh, xm, xb, xz, sfzh, rxny, xymc,
				zymc, bjmc, pycc, yddh, gddh, jtzz, yzbm, jtjjqk, sxncj1_mc,
				sxncj1_cj, sxncj2_mc, sxncj2_cj, sxncj3_mc, sxncj3_cj,
				sxncj4_mc, sxncj4_cj, sxncj5_mc, sxncj5_cj, sxncj6_mc,
				sxncj6_cj, sxncj7_mc, sxncj7_cj, sxncj8_mc, sxncj8_cj,
				sxncj9_mc, sxncj9_cj, sxncj10_mc, sxncj10_cj, rxlbjgkm,
				rxlbktgkm, xfdkze, shfdkze, dkze, sqsj, fdyshyj, fdyshsj,
				xyshyj, xyshsj, xxshyj, xxshsj, byny, yjxf };
		String[] outString = new String[] { "xh", "xm", "xb", "xz", "sfzh",
				"rxny", "xymc", "zymc", "bjmc", "pycc", "yddh", "gddh", "jtzz",
				"yzbm", "jtjjqk", "sxncj1_mc", "sxncj1_cj", "sxncj2_mc",
				"sxncj2_cj", "sxncj3_mc", "sxncj3_cj", "sxncj4_mc",
				"sxncj4_cj", "sxncj5_mc", "sxncj5_cj", "sxncj6_mc",
				"sxncj6_cj", "sxncj7_mc", "sxncj7_cj", "sxncj8_mc",
				"sxncj8_cj", "sxncj9_mc", "sxncj9_cj", "sxncj10_mc",
				"sxncj10_cj", "rxlbjgkm", "rxlbktgkm", "xfdkze", "shfdkze",
				"dkze", "sqsj", "fdyshyj", "fdyshsj", "xyshyj", "xyshsj",
				"xxshyj", "xxshsj", "byny", "yjxf" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("gjzxdksqb");
	}

	/**
	 * @describe 国家助学贷款审核列表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 初始化页面，返回查询信息
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String sqzt = Base.chgNull(request.getParameter("sqzt"), "", 1);

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "xszz_bjlh_gjzxdksqb";
		pk = "xn||xh";
		tableName = "view_xszz_bjlh_gjzxdksqb";

		String xn = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			xn = Base.currXn;
		} else {
			xn = Base.chgNull(request.getParameter("xn"), "", 1);
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xn)) {
			querry.append(" and xn='");
			querry.append(xn);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(sqzt)) {
			querry.append(" and sqzt='");
			querry.append(sqzt);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if ("true".equalsIgnoreCase(isFdy)) {
			querry.append(" and bjdm in ('###'");
			for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
				querry.append(", '");
				querry.append(it.next());
				querry.append("'");
			}
			querry.append(" ) ");
		} else {
			if (!isNull(bj)) {
				querry.append(" and bjdm='");
				querry.append(bj);
				querry.append("' ");
			}
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 国家助学贷款审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学贷款";
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "dkze", "sqzt",
					"fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.dkze,a.sqzt,a.fdysh,a.xysh,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.dkze,a.sqzt,a.fdysh,a.xysh,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "dkze",
						"sqzt", "fdysh", "xysh", "" };
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "dkze",
							"sqzt", "" };
				} else {
					colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "dkze",
							"sqzt", "fdysh", "" };
				}
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.dkze,a.sqzt,a.fdysh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xysh='通过' order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("user", "xx");
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.dkze,a.sqzt,a.fdysh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' order by fdysh desc) a";
					colList[colList.length - 1] = "fdysh";
					request.setAttribute("user", "fdy");
				} else {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.dkze,a.sqzt,a.fdysh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' order by xysh desc) a";
					colList[colList.length - 1] = "xysh";
					request.setAttribute("user", "xy");
				}
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
		map.put("xn", xn);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("sqzt", sqzt);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("sqztAllList", dao.getChkList(18));
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "gjzxdksh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("gjzxdksh");
	}

	/**
	 * @describe 国家助学贷款审核单个信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdkshXxxx(ActionMapping mapping, ActionForm form,
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
		String yesNo = Base.chgNull(request.getParameter("yesNo"), "", 1);
		String fdyshyj = Base.chgNull(request.getParameter("fdyshyj"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xyshzt = Base.chgNull(request.getParameter("sqzt"), "", 1);
		String dkhth = Base.chgNull(request.getParameter("dkhth"), "", 1);
		String jbyh = Base.chgNull(request.getParameter("jbyh"), "", 1);
		String dkdqsj = Base.chgNull(request.getParameter("dkdqsj"), "", 1);
		String isFdy = session.getAttribute("isFdy").toString();
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "delete xszz_bjlh_gjzxdksqb where xn||xh='"
								+ pkT + "' and xysh<>'通过'";
					} else {
						sqlT[i] = "delete xszz_bjlh_gjzxdksqb where xn||xh='"
								+ pkT + "' and xxsh<>'通过'";
					}
				} else {
					sqlT[i] = "delete xszz_bjlh_gjzxdksqb where xn||xh='" + pkT
							+ "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}

		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "update xszz_bjlh_gjzxdksqb set fdysh='通过',fdyshsj='"
								+ now
								+ "',sqzt='审核中' where xn||xh='"
								+ pkT
								+ "'";
					} else {
						sqlT[i] = "update xszz_bjlh_gjzxdksqb set fdysh='通过',fdyshsj='"
								+ now
								+ "',sqzt='审核中',xysh='通过',xyshsj='"
								+ now
								+ "' where xn||xh='" + pkT + "'";
					}
				} else {
					sqlT[i] = "update xszz_bjlh_gjzxdksqb set xxsh='通过',xxshsj='"
							+ now + "',sqzt='银行待审批' where xn||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}

		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						String xyshT = dao
								.getOneRs(
										"select xysh from xszz_bjlh_gjzxdksqb where xn||xh=?",
										new String[] { pkT }, "xysh");
						if (!"通过".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update xszz_bjlh_gjzxdksqb set fdysh='不通过',fdyshsj='"
									+ now
									+ "',sqzt='不合格' where xn||xh='"
									+ pkT
									+ "'";
						}
					} else {
						String xxshT = dao
								.getOneRs(
										"select xxsh from xszz_bjlh_gjzxdksqb where xn||xh=?",
										new String[] { pkT }, "xxsh");
						if (!"通过".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update xszz_bjlh_gjzxdksqb set xysh='不通过',xyshsj='"
									+ now
									+ "',sqzt='不合格' where xn||xh='"
									+ pkT
									+ "'";
						}
					}
				} else {
					sqlT[i] = "update xszz_bjlh_gjzxdksqb set xxsh='不通过',xxshsj='"
							+ now + "',sqzt='不合格' where xn||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao
							.getOneRs(
									"select xysh from xszz_bjlh_gjzxdksqb where xn||xh=?",
									new String[] { pkVal }, "xysh");
					if ("通过".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						String sqzt = "未审核";
						if (yesNo.equalsIgnoreCase("通过")) {
							sqzt = "审核中";
						} else if (yesNo.equalsIgnoreCase("不通过")) {
							sqzt = "不合格";
						}
						StandardOperation.update("xszz_bjlh_gjzxdksqb",
								new String[] { "fdysh", "fdyshsj", "fdyshyj",
										"sqzt" }, new String[] { yesNo, now,
										fdyshyj, sqzt }, "xn||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao
							.getOneRs(
									"select xxsh from xszz_bjlh_gjzxdksqb where xn||xh=?",
									new String[] { pkVal }, "xxsh");
					if ("通过".equalsIgnoreCase(xxshT)) {
						if (xyshzt.equalsIgnoreCase("已放贷")) {
							StandardOperation.update("xszz_bjlh_gjzxdksqb",
									new String[] { "sqzt", "dkfdsj", "dkhth",
											"dkdqsj", "jbyh" }, new String[] {
											xyshzt, now, dkhth, dkdqsj, jbyh },
									"xn||xh", pkVal, request);
						} else {
							StandardOperation.update("xszz_bjlh_gjzxdksqb",
									new String[] { "sqzt", "dkhth", "dkdqsj",
											"jbyh" }, new String[] { xyshzt,
											dkhth, dkdqsj, jbyh }, "xn||xh",
									pkVal, request);
						}
					} else {
						String sqzt = "审核中";
						if (yesNo.equalsIgnoreCase("不通过")) {
							sqzt = "不合格";
						}
						StandardOperation.update("xszz_bjlh_gjzxdksqb",
								new String[] { "fdysh", "fdyshsj", "xysh",
										"xyshyj", "xyshsj", "sqzt" },
								new String[] { yesNo, now, yesNo, xyshyj, now,
										sqzt }, "xn||xh", pkVal, request);
					}
				}
			} else {
				String sqzt = "审核中";
				if (yesNo.equalsIgnoreCase("不通过")) {
					sqzt = "不合格";
				} else if (yesNo.equalsIgnoreCase("通过")) {
					sqzt = "银行待审批";
				}
				StandardOperation.update("xszz_bjlh_gjzxdksqb", new String[] {
						"xxsh", "xxshyj", "xxshsj", "sqzt" }, new String[] {
						yesNo, xxshyj, now, sqzt }, "xn||xh", pkVal, request);
			}
		}
		realTable = "xszz_bjlh_gjzxdksqb";
		pk = "xn||xh";
		String user;
		sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xjzt,pycc,yddh,gddh,jtzz,yzbm,jtjjqk,fqxm,fqgzdw,fqlxdh,fqsfzh,mqxm,mqgzdw,mqlxdh,mqsfzh,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,xfdkze,shfdkze,dkze,dkhth,dkfdsj,dkdqsj,jbyh,sqsj,sqzt,fdysh,fdyshyj,fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj from view_xszz_bjlh_gjzxdksqb where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.mzmc,a.zzmmmc,a.xz,a.nj,a.sfzh,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xjzt,a.pycc,a.yddh,a.gddh,a.jtzz,a.yzbm,a.jtjjqk,a.fqxm,a.fqgzdw,a.fqlxdh,a.fqsfzh,a.mqxm,a.mqgzdw,a.mqlxdh,a.mqsfzh,a.sxncj1_mc,a.sxncj1_cj,a.sxncj2_mc,a.sxncj2_cj,a.sxncj3_mc,a.sxncj3_cj,a.sxncj4_mc,a.sxncj4_cj,a.sxncj5_mc,a.sxncj5_cj,a.sxncj6_mc,a.sxncj6_cj,a.sxncj7_mc,a.sxncj7_cj,a.sxncj8_mc,a.sxncj8_cj,a.sxncj9_mc,a.sxncj9_cj,a.sxncj10_mc,a.sxncj10_cj,a.rxlbjgkm,a.rxlbktgkm,a.xfdkze,a.shfdkze,a.dkze,a.dkhth,a.dkfdsj,a.dkdqsj,a.jbyh,a.sqsj,a.sqzt,a.fdysh,a.fdyshyj,a.fdyshsj,a.xysh,a.xyshyj,a.xyshsj,a.xxsh,a.xxshyj,a.xxshsj,a.fdysh yesNo "
						+ "from view_xszz_bjlh_gjzxdksqb a where " + pk + "='"
						+ pkVal + "'";
				user = "fdy";
			} else {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.mzmc,a.zzmmmc,a.xz,a.nj,a.sfzh,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xjzt,a.pycc,a.yddh,a.gddh,a.jtzz,a.yzbm,a.jtjjqk,a.fqxm,a.fqgzdw,a.fqlxdh,a.fqsfzh,a.mqxm,a.mqgzdw,a.mqlxdh,a.mqsfzh,a.sxncj1_mc,a.sxncj1_cj,a.sxncj2_mc,a.sxncj2_cj,a.sxncj3_mc,a.sxncj3_cj,a.sxncj4_mc,a.sxncj4_cj,a.sxncj5_mc,a.sxncj5_cj,a.sxncj6_mc,a.sxncj6_cj,a.sxncj7_mc,a.sxncj7_cj,a.sxncj8_mc,a.sxncj8_cj,a.sxncj9_mc,a.sxncj9_cj,a.sxncj10_mc,a.sxncj10_cj,a.rxlbjgkm,a.rxlbktgkm,a.xfdkze,a.shfdkze,a.dkze,a.dkhth,a.dkfdsj,a.dkdqsj,a.jbyh,a.sqsj,a.sqzt,a.fdysh,a.fdyshyj,a.fdyshsj,a.xysh,a.xyshyj,a.xyshsj,a.xxsh,a.xxshyj,a.xxshsj,a.xysh yesNo "
						+ "from view_xszz_bjlh_gjzxdksqb a where " + pk + "='"
						+ pkVal + "'";
				user = "xy";
			}
		} else {
			sql = "select "
					+ pk
					+ " pk,a.xn,a.xh,a.xm,a.xb,a.mzmc,a.zzmmmc,a.xz,a.nj,a.sfzh,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xjzt,a.pycc,a.yddh,a.gddh,a.jtzz,a.yzbm,a.jtjjqk,a.fqxm,a.fqgzdw,a.fqlxdh,a.fqsfzh,a.mqxm,a.mqgzdw,a.mqlxdh,a.mqsfzh,a.sxncj1_mc,a.sxncj1_cj,a.sxncj2_mc,a.sxncj2_cj,a.sxncj3_mc,a.sxncj3_cj,a.sxncj4_mc,a.sxncj4_cj,a.sxncj5_mc,a.sxncj5_cj,a.sxncj6_mc,a.sxncj6_cj,a.sxncj7_mc,a.sxncj7_cj,a.sxncj8_mc,a.sxncj8_cj,a.sxncj9_mc,a.sxncj9_cj,a.sxncj10_mc,a.sxncj10_cj,a.rxlbjgkm,a.rxlbktgkm,a.xfdkze,a.shfdkze,a.dkze,a.dkhth,a.dkfdsj,a.dkdqsj,a.jbyh,a.sqsj,a.sqzt,a.fdysh,a.fdyshyj,a.fdyshsj,a.xysh,a.xyshyj,a.xyshsj,a.xxsh,a.xxshyj,a.xxshsj,a.xxsh yesNo "
					+ "from view_xszz_bjlh_gjzxdksqb a where " + pk + "='"
					+ pkVal + "'";
			user = "xx";
		}
		colList = new String[(outString.length + 2)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i + 1] = outString[i].toLowerCase();
		}
		colList[i + 1] = "yesNo";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		String xxsh = "未审核";
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("xxsh")) {
				xxsh = rs[i];
			}
			hs.put(colList[i], rs[i]);
		}
		hs.put("shsj", now);

		if (xxsh.equalsIgnoreCase("通过") && user.equalsIgnoreCase("xy")) {
			request.setAttribute("htxx", "is");
		} else {
			request.setAttribute("htxx", "no");
		}
		request.setAttribute("user", user);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("sqztList", dao.getChkList(17));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_xszz_bjlh_gjzxdksqb");
		request.setAttribute("act", "gjzxdksh");
		return mapping.findForward("gjzxdkshXxxx");
	}

	/**
	 * @describe 国家助学贷款列表导出
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxdkshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);

		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String sqzt = Base.chgNull(request.getParameter("sqzt"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String xn = "";
		if (!isQuery.equalsIgnoreCase("is")) {
			xn = Base.currXn;
		} else {
			xn = Base.chgNull(request.getParameter("xn"), "", 1);
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(sqzt)) {
			querry.append(" and sqzt='");
			querry.append(sqzt);
			querry.append("' ");
		}
		if (!isNull(xn)) {
			querry.append(" and xn='");
			querry.append(xn);
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
		if ("true".equalsIgnoreCase(isFdy)) {
			querry.append(" and bjdm in ('###'");
			for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
				querry.append(", '");
				querry.append(it.next());
				querry.append("'");
			}
			querry.append(" ) ");
		} else {
			if (!isNull(bj)) {
				querry.append(" and bjdm='");
				querry.append(bj);
				querry.append("' ");
			}
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_xszz_bjlh_gjzxdksqb " + querry.toString();

		String[] colList = dao
				.getColumnName("select * from view_xszz_bjlh_gjzxdksqb where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_xszz_bjlh_gjzxdksqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjzxdkshExp");
	}

	/**
	 * @describe 困难生申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,xm,xb,csrq,rxny,byny,xz,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xl,rxqhk,zxlxdh,fqxm,fqnl,fqnsr,mqxm,mqnl,mqnsr,jtrks,jtrjysr,tgzmlx,kjzmsj,yxjtjjzyys,yxjtjjzkqtxx,bz,qtzd,xypdyj,xypdjg,pdsj,sqsj,xxsh from view_bjlhdx_kns where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";

		String sfksq = "-1";
		String pkVal = "";
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
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String xl = Base.chgNull(request.getParameter("xl"), "", 1);
					String rxqhk = Base.chgNull(request.getParameter("rxqhk"),
							"", 1);
					String zxlxdh = Base.chgNull(
							request.getParameter("zxlxdh"), "", 1);
					String fqxm = Base.chgNull(request.getParameter("fqxm"),
							"", 1);
					String fqnl = Base.chgNull(request.getParameter("fqnl"),
							"", 1);
					String fqnsr = Base.chgNull(request.getParameter("fqnsr"),
							"", 1);
					String mqxm = Base.chgNull(request.getParameter("mqxm"),
							"", 1);
					String mqnl = Base.chgNull(request.getParameter("mqnl"),
							"", 1);
					String mqnsr = Base.chgNull(request.getParameter("mqnsr"),
							"", 1);
					String jtrks = Base.chgNull(request.getParameter("jtrks"),
							"", 1);
					String jtrjysr = Base.chgNull(request
							.getParameter("jtrjysr"), "", 1);
					String tgzmlx = Base.chgNull(
							request.getParameter("tgzmlx"), "", 1);
					String kjzmsj = Base.chgNull(
							request.getParameter("kjzmsj"), "", 1);
					String yxjtjjzyys = Base.chgNull(request
							.getParameter("yxjtjjzyys"), "", 1);
					String yxjtjjzkqtxx = Base.chgNull(request
							.getParameter("yxjtjjzkqtxx"), "", 1);
					String bz = Base.chgNull(request.getParameter("bz"), "", 1);
					String qtzd = Base.chgNull(request.getParameter("qtzd"),
							"", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh;
					}
					sql = "select count(*) num from bjlhdx_kns where xh=?";
					String tNum = dao.getOneRs(sql, new String[] { xh }, "num");
					boolean ok = false;
					if (tNum.equalsIgnoreCase("0")) {
						ok = StandardOperation.insert("bjlhdx_kns",
								new String[] { "xh", "xl", "rxqhk", "zxlxdh",
										"fqxm", "fqnl", "fqnsr", "mqxm",
										"mqnl", "mqnsr", "jtrks", "jtrjysr",
										"tgzmlx", "kjzmsj", "yxjtjjzyys",
										"yxjtjjzkqtxx", "bz", "qtzd" },
								new String[] { xh, xl, rxqhk, zxlxdh, fqxm,
										fqnl, fqnsr, mqxm, mqnl, mqnsr, jtrks,
										jtrjysr, tgzmlx, kjzmsj, yxjtjjzyys,
										yxjtjjzkqtxx, bz, qtzd }, request);
					} else {
						String sT = dao
								.getOneRs(
										"select to_char(sysdate,'yyyy-mm-dd') n from dual",
										new String[] {}, "n");
						ok = StandardOperation.update("bjlhdx_kns",
								new String[] { "xl", "rxqhk", "zxlxdh", "fqxm",
										"fqnl", "fqnsr", "mqxm", "mqnl",
										"mqnsr", "jtrks", "jtrjysr", "tgzmlx",
										"kjzmsj", "yxjtjjzyys", "yxjtjjzkqtxx",
										"bz", "qtzd", "sqsj", "xypdyj",
										"xypdjg", "pdsj", "xxsh" },
								new String[] { xl, rxqhk, zxlxdh, fqxm, fqnl,
										fqnsr, mqxm, mqnl, mqnsr, jtrks,
										jtrjysr, tgzmlx, kjzmsj, yxjtjjzyys,
										yxjtjjzkqtxx, bz, qtzd, sT, "", "未审核",
										"", "未审核" }, "xh", xh, request);
					}

					if (ok) {
						logMsg = "申请" + titName;
						Base.log(request, logMsg, sUName);
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				}
			} else {
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String xl = Base.chgNull(request.getParameter("xl"), "", 1);
				String rxqhk = Base.chgNull(request.getParameter("rxqhk"), "",
						1);
				String zxlxdh = Base.chgNull(request.getParameter("zxlxdh"),
						"", 1);
				String fqxm = Base.chgNull(request.getParameter("fqxm"), "", 1);
				String fqnl = Base.chgNull(request.getParameter("fqnl"), "", 1);
				String fqnsr = Base.chgNull(request.getParameter("fqnsr"), "",
						1);
				String mqxm = Base.chgNull(request.getParameter("mqxm"), "", 1);
				String mqnl = Base.chgNull(request.getParameter("mqnl"), "", 1);
				String mqnsr = Base.chgNull(request.getParameter("mqnsr"), "",
						1);
				String jtrks = Base.chgNull(request.getParameter("jtrks"), "",
						1);
				String jtrjysr = Base.chgNull(request.getParameter("jtrjysr"),
						"", 1);
				String tgzmlx = Base.chgNull(request.getParameter("tgzmlx"),
						"", 1);
				String kjzmsj = Base.chgNull(request.getParameter("kjzmsj"),
						"", 1);
				String yxjtjjzyys = Base.chgNull(request
						.getParameter("yxjtjjzyys"), "", 1);
				String yxjtjjzkqtxx = Base.chgNull(request
						.getParameter("yxjtjjzkqtxx"), "", 1);
				String bz = Base.chgNull(request.getParameter("bz"), "", 1);
				String qtzd = Base.chgNull(request.getParameter("qtzd"), "", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh;
				}
				sql = "select count(*) num from bjlhdx_kns where xh=?";
				String tNum = dao.getOneRs(sql, new String[] { xh }, "num");
				boolean ok = false;
				if (tNum.equalsIgnoreCase("0")) {
					ok = StandardOperation.insert("bjlhdx_kns", new String[] {
							"xh", "xl", "rxqhk", "zxlxdh", "fqxm", "fqnl",
							"fqnsr", "mqxm", "mqnl", "mqnsr", "jtrks",
							"jtrjysr", "tgzmlx", "kjzmsj", "yxjtjjzyys",
							"yxjtjjzkqtxx", "bz", "qtzd" }, new String[] { xh,
							xl, rxqhk, zxlxdh, fqxm, fqnl, fqnsr, mqxm, mqnl,
							mqnsr, jtrks, jtrjysr, tgzmlx, kjzmsj, yxjtjjzyys,
							yxjtjjzkqtxx, bz, qtzd }, request);
				} else {
					String sT = dao.getOneRs(
							"select to_char(sysdate,'yyyy-mm-dd') n from dual",
							new String[] {}, "n");
					ok = StandardOperation.update("bjlhdx_kns", new String[] {
							"xl", "rxqhk", "zxlxdh", "fqxm", "fqnl", "fqnsr",
							"mqxm", "mqnl", "mqnsr", "jtrks", "jtrjysr",
							"tgzmlx", "kjzmsj", "yxjtjjzyys", "yxjtjjzkqtxx",
							"bz", "qtzd", "sqsj", "xypdyj", "xypdjg", "pdsj",
							"xxsh" }, new String[] { xl, rxqhk, zxlxdh, fqxm,
							fqnl, fqnsr, mqxm, mqnl, mqnsr, jtrks, jtrjysr,
							tgzmlx, kjzmsj, yxjtjjzyys, yxjtjjzkqtxx, bz, qtzd,
							sT, "", "未审核", "", "未审核" }, "xh", xh, request);
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
		pkVal = request.getParameter("pkVal");
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh;
		}

		sql = "select xh,xm,xb,csrq,rxny,byny,xz,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xl,rxqhk,zxlxdh,fqxm,fqnl,fqnsr,mqxm,mqnl,mqnsr,jtrks,jtrjysr,tgzmlx,kjzmsj,yxjtjjzyys,yxjtjjzkqtxx,bz,qtzd,xypdyj,xypdjg,pdsj,sqsj,xxsh from view_bjlhdx_kns where xh=?";
		outString = new String[] { "xh", "xm", "xb", "csrq", "rxny", "byny",
				"xz", "sfzh", "mzmc", "zzmmmc", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "xl", "rxqhk", "zxlxdh", "fqxm",
				"fqnl", "fqnsr", "mqxm", "mqnl", "mqnsr", "jtrks", "jtrjysr",
				"tgzmlx", "kjzmsj", "yxjtjjzyys", "yxjtjjzkqtxx", "bz", "qtzd",
				"xypdyj", "xypdjg", "pdsj", "sqsj", "xxsh" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);

		if (outValue == null) {
			if (xh != null) {
				sql = "select a.xh,a.xm,a.xb,a.csrq,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,"
						+ "(select substr(nvl(b.rxny,to_char(sysdate,'yyyy-mm')),1,4)+b.xz from bks_xsjbxx b where a.xh=b.xh) byny,"
						+ "a.xz,a.sfzh,a.mzmc,a.zzmmmc,a.nj,a.xymc,a.zymc,a.bjmc from view_stu_details a where a.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"rxny", "byny", "xz", "sfzh", "mzmc", "zzmmmc", "nj",
						"xymc", "zymc", "bjmc" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {

				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq", "rxny",
							"byny", "xz", "sfzh", "mzmc", "zzmmmc", "nj",
							"xymc", "zymc", "bjmc" };
					for (int i = 0; i < colName.length; i++) {
						if (outVal[i] != null) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
			}
		} else {
			for (int i = 0; i < outString.length; i++) {
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("knssq");
	}

	/**
	 * @describe 困难生审核列表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 初始化页面，返回查询信息
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
		realTable = "bjlhdx_kns";
		pk = "xh";
		tableName = "view_bjlhdx_kns";

		String xn = "";
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(xn)) {
			querry.append(" and xn='");
			querry.append(xn);
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
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 困难生审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 困难生";
			colList = new String[] { "bgcolor", "主键", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "xypdjg", "pdsj", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('一般困难', '特殊困难', '其他困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.sqsj,a.xypdjg,a.pdsj,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('一般困难', '特殊困难', '其他困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.sqsj,a.xypdjg,a.pdsj,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xypdjg desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "xypdjg", "pdsj", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('一般困难', '特殊困难', '其他困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.sqsj,a.xypdjg,a.pdsj,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xypdjg in ('一般困难', '特殊困难', '其他困难') order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xypdjg,'未审核') in ('一般困难', '特殊困难', '其他困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.sqsj,a.xypdjg,a.pdsj,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xypdjg desc) a";
				request.setAttribute("user", "xy");
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
		map.put("xn", xn);
		map.put("nj", nj);
		map.put("xh", xh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
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
		request.setAttribute("act", "knssh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("knssh");
	}

	/**
	 * @describe 困难生审核单个信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward knsshXxxx(ActionMapping mapping, ActionForm form,
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
		String yesNo = Base.chgNull(request.getParameter("yesNo"), "", 1);
		String xypdyj = Base.chgNull(request.getParameter("xypdyj"), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete bjlhdx_kns where xh='"+pkT+"' and xxsh not in ('一般困难','特殊困难','其他困难')";
				} else {
					sqlT[i] = "delete bjlhdx_kns where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if ("tskn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update bjlhdx_kns set xypdjg='特殊困难',pdsj='"+now+"' where xh='"+pkT+"'";
				} else {
					sqlT[i] = "update bjlhdx_kns set xxsh='特殊困难' where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if ("ybkn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update bjlhdx_kns set xypdjg='一般困难',pdsj='"+now+"' where xh='"+pkT+"'";
				} else {
					sqlT[i] = "update bjlhdx_kns set xxsh='一般困难' where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if ("qtkn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update bjlhdx_kns set xypdjg='其他困难',pdsj='"+now+"' where xh='"+pkT+"'";
				} else {
					sqlT[i] = "update bjlhdx_kns set xxsh='其他困难' where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if ("bkn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					String xxshT = dao.getOneRs(
							"select xxsh from bjlhdx_kns where h=?",
							new String[] { pkT }, "xxsh");
					if ("未审核".equalsIgnoreCase(xxshT)
							|| "不困难".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update bjlhdx_kns set xypdjg='不困难',pdsj='"+now+"' where xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update bjlhdx_kns set xxsh='不困难' where xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/bjlhdx_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs(
						"select xxsh from bjlhdx_kns where xh=?",
						new String[] { pkVal }, "xxsh");
				if ("未审核".equalsIgnoreCase(xxshT)
						|| "不困难".equalsIgnoreCase(xxshT)) {
					StandardOperation.update("bjlhdx_kns", new String[] {
							"xypdjg", "pdsj", "xypdyj" }, new String[] { yesNo,
							now, xypdyj }, "xh", pkVal, request);
				}
			} else {
				StandardOperation.update("bjlhdx_kns", new String[] { "xxsh" },
						new String[] { yesNo }, "xh", pkVal, request);
			}
		}
		realTable = "bjlhdx_kns";
		pk = "xh";
		String user;
		sql = "select xh,xm,xb,csrq,rxny,byny,xz,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xl,rxqhk,zxlxdh,fqxm,fqnl,fqnsr,mqxm,mqnl,mqnsr,jtrks,jtrjysr,tgzmlx,kjzmsj,yxjtjjzyys,yxjtjjzkqtxx,bz,qtzd,xypdyj,xypdjg,pdsj,sqsj,xxsh from view_bjlhdx_kns where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,xh,xm,xb,csrq,rxny,byny,xz,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xl,rxqhk,zxlxdh,fqxm,fqnl,fqnsr,mqxm,mqnl,mqnsr,jtrks,jtrjysr,tgzmlx,kjzmsj,yxjtjjzyys,yxjtjjzkqtxx,bz,qtzd,xypdyj,xypdjg,pdsj,sqsj,xxsh,xypdjg yesNo "
					+ "from view_bjlhdx_kns a where " + pk + "='" + pkVal + "'";
			user = "xy";
		} else {
			sql = "select "
					+ pk
					+ " pk,xh,xm,xb,csrq,rxny,byny,xz,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xl,rxqhk,zxlxdh,fqxm,fqnl,fqnsr,mqxm,mqnl,mqnsr,jtrks,jtrjysr,tgzmlx,kjzmsj,yxjtjjzyys,yxjtjjzkqtxx,bz,qtzd,xypdyj,xypdjg,pdsj,sqsj,xxsh,xxsh yesNo "
					+ "from view_bjlhdx_kns a where " + pk + "='" + pkVal + "'";
			user = "xx";
		}
		colList = new String[(outString.length + 2)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i + 1] = outString[i].toLowerCase();
		}
		colList[i + 1] = "yesNo";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int j = 0; j < rs.length; j++) {
			hs.put(colList[j], rs[j]);
		}
		hs.put("now", dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') n from dual",
				new String[] {}, "n"));
		request.setAttribute("user", user);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(25));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_bjlhdx_kns");
		request.setAttribute("act", "knssh");
		return mapping.findForward("knsshXxxx");
	}

	/**
	 * @describe 困难生列表导出
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward knsshExp(ActionMapping mapping, ActionForm form,
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
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
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
		sql = "select * from view_bjlhdx_kns " + querry.toString();

		String[] colList = dao
				.getColumnName("select * from view_bjlhdx_kns where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_bjlhdx_kns");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("knsshExp");
	}
}
