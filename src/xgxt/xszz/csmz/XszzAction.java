package xgxt.xszz.csmz;

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

public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
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

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xh,nd,xzjtxxdz,yzbm,yxxxx,jg,jtdh,xm,xb,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,mzmc,zzmmmc,sfzh,nj,zsjtcy1_xm,zsjtcy1_nl,zsjtcy1_gzdwjzw,zsjtcy1_lxdh,zsjtcy1_ysr,zsjtcy2_xm,zsjtcy2_nl,zsjtcy2_gzdwjzw,zsjtcy2_lxdh,zsjtcy2_ysr,zsjtcy3_xm,zsjtcy3_nl,zsjtcy3_gzdwjzw,zsjtcy3_lxdh,zsjtcy3_ysr,zsjtcy4_xm,zsjtcy4_nl,zsjtcy4_gzdwjzw,zsjtcy4_lxdh,zsjtcy4_ysr,zsjtcy5_xm,zsjtcy5_nl,zsjtcy5_gzdwjzw,zsjtcy5_lxdh,zsjtcy5_ysr,jtrks,jtjj_cz_qjnsr,jtjj_cz_rjysr,jtjj_nc_dnzsr,jtjj_nc_rjnsr,ddzdshshbz,zyshgx1_xm,zyshgx1_nl,zyshgx1_gx,zyshgx1_gzdwjzw,zyshgx1_ysr,zyshgx1_ynjtjjlxhgyqk,zyshgx2_xm,zyshgx2_nl,zyshgx2_gx,zyshgx2_gzdwjzw,zyshgx2_ysr,zyshgx2_ynjtjjlxhgyqk,zyshgx3_xm,zyshgx3_nl,zyshgx3_gx,zyshgx3_gzdwjzw,zyshgx3_ysr,zyshgx3_ynjtjjlxhgyqk,zyshgx4_xm,zyshgx4_nl,zyshgx4_gx,zyshgx4_gzdwjzw,zyshgx4_ysr,zyshgx4_ynjtjjlxhgyqk,jtjjknqkjyy,fqdw,mqdw,hscjgqgzx,tc,szqs,qsdh,jckkh,drxsgbqk,zxqjhschghzjxj,pkx,snjtsr,fqzy,mqzy,jtjjly,jtmytgshf,jzsfyqz,fmsfycj,fmsfjz,sqly,sqsj,fdysh,fdyshsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,zsjtcy1_gx,zsjtcy2_gx,zsjtcy3_gx,zsjtcy4_gx,zsjtcy5_gx from view_csmz_knsxx where 1=2";
		outString = dao.getColumnName(sql);// 获得列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = "";
		String nd = Base.currNd;

//		String knsrs = "";
		String sfksq = "-1";
		String pkVal = "";
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
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String xzjtxxdz = Base.chgNull(request
							.getParameter("xzjtxxdz"), "", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String yxxxx = Base.chgNull(request.getParameter("yxxxx"),
							"", 1);
					String jg = Base.chgNull(request.getParameter("jg"), "", 1);
					String jtdh = Base.chgNull(request.getParameter("jtdh"),
							"", 1);
					String zsjtcy1_xm = Base.chgNull(request
							.getParameter("zsjtcy1_xm"), "", 1);
					String zsjtcy1_nl = Base.chgNull(request
							.getParameter("zsjtcy1_nl"), "", 1);
					String zsjtcy1_gzdwjzw = Base.chgNull(request
							.getParameter("zsjtcy1_gzdwjzw"), "", 1);
					String zsjtcy1_lxdh = Base.chgNull(request
							.getParameter("zsjtcy1_lxdh"), "", 1);
					String zsjtcy1_ysr = Base.chgNull(request
							.getParameter("zsjtcy1_ysr"), "", 1);
					String zsjtcy2_xm = Base.chgNull(request
							.getParameter("zsjtcy2_xm"), "", 1);
					String zsjtcy2_nl = Base.chgNull(request
							.getParameter("zsjtcy2_nl"), "", 1);
					String zsjtcy2_gzdwjzw = Base.chgNull(request
							.getParameter("zsjtcy2_gzdwjzw"), "", 1);
					String zsjtcy2_lxdh = Base.chgNull(request
							.getParameter("zsjtcy2_lxdh"), "", 1);
					String zsjtcy2_ysr = Base.chgNull(request
							.getParameter("zsjtcy2_ysr"), "", 1);
					String zsjtcy3_xm = Base.chgNull(request
							.getParameter("zsjtcy3_xm"), "", 1);
					String zsjtcy3_nl = Base.chgNull(request
							.getParameter("zsjtcy3_nl"), "", 1);
					String zsjtcy3_gzdwjzw = Base.chgNull(request
							.getParameter("zsjtcy3_gzdwjzw"), "", 1);
					String zsjtcy3_lxdh = Base.chgNull(request
							.getParameter("zsjtcy3_lxdh"), "", 1);
					String zsjtcy3_ysr = Base.chgNull(request
							.getParameter("zsjtcy3_ysr"), "", 1);
					String zsjtcy4_xm = Base.chgNull(request
							.getParameter("zsjtcy4_xm"), "", 1);
					String zsjtcy4_nl = Base.chgNull(request
							.getParameter("zsjtcy4_nl"), "", 1);
					String zsjtcy4_gzdwjzw = Base.chgNull(request
							.getParameter("zsjtcy4_gzdwjzw"), "", 1);
					String zsjtcy4_lxdh = Base.chgNull(request
							.getParameter("zsjtcy4_lxdh"), "", 1);
					String zsjtcy4_ysr = Base.chgNull(request
							.getParameter("zsjtcy4_ysr"), "", 1);
					String zsjtcy5_xm = Base.chgNull(request
							.getParameter("zsjtcy5_xm"), "", 1);
					String zsjtcy5_nl = Base.chgNull(request
							.getParameter("zsjtcy5_nl"), "", 1);
					String zsjtcy5_gzdwjzw = Base.chgNull(request
							.getParameter("zsjtcy5_gzdwjzw"), "", 1);
					String zsjtcy5_lxdh = Base.chgNull(request
							.getParameter("zsjtcy5_lxdh"), "", 1);
					String zsjtcy5_ysr = Base.chgNull(request
							.getParameter("zsjtcy5_ysr"), "", 1);
					String jtjj_cz_qjnsr = Base.chgNull(request
							.getParameter("jtjj_cz_qjnsr"), "", 1);
					String jtjj_cz_rjysr = Base.chgNull(request
							.getParameter("jtjj_cz_rjysr"), "", 1);
					String jtjj_nc_dnzsr = Base.chgNull(request
							.getParameter("jtjj_nc_dnzsr"), "", 1);
					String jtjj_nc_rjnsr = Base.chgNull(request
							.getParameter("jtjj_nc_rjnsr"), "", 1);
					String jtrks = Base.chgNull(request.getParameter("jtrks"),
							"", 1);
					String ddzdshshbz = Base.chgNull(request
							.getParameter("ddzdshshbz"), "", 1);
					String zyshgx1_xm = Base.chgNull(request
							.getParameter("zyshgx1_xm"), "", 1);
					String zyshgx1_nl = Base.chgNull(request
							.getParameter("zyshgx1_nl"), "", 1);
					String zyshgx1_gx = Base.chgNull(request
							.getParameter("zyshgx1_gx"), "", 1);
					String zyshgx1_gzdwjzw = Base.chgNull(request
							.getParameter("zyshgx1_gzdwjzw"), "", 1);
					String zyshgx1_ysr = Base.chgNull(request
							.getParameter("zyshgx1_ysr"), "", 1);
					String zyshgx1_ynjtjjlxhgyqk = Base.chgNull(request
							.getParameter("zyshgx1_ynjtjjlxhgyqk"), "", 1);
					String zyshgx2_xm = Base.chgNull(request
							.getParameter("zyshgx2_xm"), "", 1);
					String zyshgx2_nl = Base.chgNull(request
							.getParameter("zyshgx2_nl"), "", 1);
					String zyshgx2_gx = Base.chgNull(request
							.getParameter("zyshgx2_gx"), "", 1);
					String zyshgx2_gzdwjzw = Base.chgNull(request
							.getParameter("zyshgx2_gzdwjzw"), "", 1);
					String zyshgx2_ysr = Base.chgNull(request
							.getParameter("zyshgx2_ysr"), "", 1);
					String zyshgx2_ynjtjjlxhgyqk = Base.chgNull(request
							.getParameter("zyshgx2_ynjtjjlxhgyqk"), "", 1);
					String zyshgx3_xm = Base.chgNull(request
							.getParameter("zyshgx3_xm"), "", 1);
					String zyshgx3_nl = Base.chgNull(request
							.getParameter("zyshgx3_nl"), "", 1);
					String zyshgx3_gx = Base.chgNull(request
							.getParameter("zyshgx3_gx"), "", 1);
					String zyshgx3_gzdwjzw = Base.chgNull(request
							.getParameter("zyshgx3_gzdwjzw"), "", 1);
					String zyshgx3_ysr = Base.chgNull(request
							.getParameter("zyshgx3_ysr"), "", 1);
					String zyshgx3_ynjtjjlxhgyqk = Base.chgNull(request
							.getParameter("zyshgx3_ynjtjjlxhgyqk"), "", 1);
					String zyshgx4_xm = Base.chgNull(request
							.getParameter("zyshgx4_xm"), "", 1);
					String zyshgx4_nl = Base.chgNull(request
							.getParameter("zyshgx4_nl"), "", 1);
					String zyshgx4_gx = Base.chgNull(request
							.getParameter("zyshgx4_gx"), "", 1);
					String zyshgx4_gzdwjzw = Base.chgNull(request
							.getParameter("zyshgx4_gzdwjzw"), "", 1);
					String zyshgx4_ysr = Base.chgNull(request
							.getParameter("zyshgx4_ysr"), "", 1);
					String zyshgx4_ynjtjjlxhgyqk = Base.chgNull(request
							.getParameter("zyshgx4_ynjtjjlxhgyqk"), "", 1);
					String jtjjknqkjyy = Base.chgNull(request
							.getParameter("jtjjknqkjyy"), "", 1);
					String fqdw = Base.chgNull(request.getParameter("fqdw"),
							"", 1);
					String mqdw = Base.chgNull(request.getParameter("mqdw"),
							"", 1);
					String hscjgqgzx = Base.chgNull(request
							.getParameter("hscjgqgzx"), "", 1);
					String tc = Base.chgNull(request.getParameter("tc"), "", 1);
					String szqs = Base.chgNull(request.getParameter("szqs"),
							"", 1);
					String qsdh = Base.chgNull(request.getParameter("qsdh"),
							"", 1);
					String jckkh = Base.chgNull(request.getParameter("jckkh"),
							"", 1);
					String drxsgbqk = Base.chgNull(request
							.getParameter("drxsgbqk"), "", 1);
					String zxqjhschghzjxj = Base.chgNull(request
							.getParameter("zxqjhschghzjxj"), "", 1);
					String pkx = Base.chgNull(request.getParameter("pkx"), "",
							1);
					String snjtsr = Base.chgNull(
							request.getParameter("snjtsr"), "", 1);
					String fqzy = Base.chgNull(request.getParameter("fqzy"),
							"", 1);
					String mqzy = Base.chgNull(request.getParameter("mqzy"),
							"", 1);
					String jtjjly = Base.chgNull(
							request.getParameter("jtjjly"), "", 1);
					String jtmytgshf = Base.chgNull(request
							.getParameter("jtmytgshf"), "", 1);
					String jzsfyqz = Base.chgNull(request
							.getParameter("jzsfyqz"), "", 1);
					String fmsfycj = Base.chgNull(request
							.getParameter("fmsfycj"), "", 1);
					String fmsfjz = Base.chgNull(
							request.getParameter("fmsfjz"), "", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String zsjtcy1_gx = Base.chgNull(request
							.getParameter("zsjtcy1_gx"), "", 1);
					String zsjtcy2_gx = Base.chgNull(request
							.getParameter("zsjtcy2_gx"), "", 1);
					String zsjtcy3_gx = Base.chgNull(request
							.getParameter("zsjtcy3_gx"), "", 1);
					String zsjtcy4_gx = Base.chgNull(request
							.getParameter("zsjtcy4_gx"), "", 1);
					String zsjtcy5_gx = Base.chgNull(request
							.getParameter("zsjtcy5_gx"), "", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = nd + xh;
					}

					sql = "select xxsh from csmz_knsxx where nd||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("贫困生") || temp[0]
									.equalsIgnoreCase("特困生"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("csmz_knsxx", "nd||xh", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xh", "nd",
								"xzjtxxdz", "yzbm", "yxxxx", "jg", "jtdh",
								"zsjtcy1_xm", "zsjtcy1_nl", "zsjtcy1_gzdwjzw",
								"zsjtcy1_lxdh", "zsjtcy1_ysr", "zsjtcy2_xm",
								"zsjtcy2_nl", "zsjtcy2_gzdwjzw",
								"zsjtcy2_lxdh", "zsjtcy2_ysr", "zsjtcy3_xm",
								"zsjtcy3_nl", "zsjtcy3_gzdwjzw",
								"zsjtcy3_lxdh", "zsjtcy3_ysr", "zsjtcy4_xm",
								"zsjtcy4_nl", "zsjtcy4_gzdwjzw",
								"zsjtcy4_lxdh", "zsjtcy4_ysr", "zsjtcy5_xm",
								"zsjtcy5_nl", "zsjtcy5_gzdwjzw",
								"zsjtcy5_lxdh", "zsjtcy5_ysr", "jtjj_cz_qjnsr",
								"jtjj_cz_rjysr", "jtjj_nc_dnzsr",
								"jtjj_nc_rjnsr", "jtrks", "ddzdshshbz",
								"zyshgx1_xm", "zyshgx1_nl", "zyshgx1_gx",
								"zyshgx1_gzdwjzw", "zyshgx1_ysr",
								"zyshgx1_ynjtjjlxhgyqk", "zyshgx2_xm",
								"zyshgx2_nl", "zyshgx2_gx", "zyshgx2_gzdwjzw",
								"zyshgx2_ysr", "zyshgx2_ynjtjjlxhgyqk",
								"zyshgx3_xm", "zyshgx3_nl", "zyshgx3_gx",
								"zyshgx3_gzdwjzw", "zyshgx3_ysr",
								"zyshgx3_ynjtjjlxhgyqk", "zyshgx4_xm",
								"zyshgx4_nl", "zyshgx4_gx", "zyshgx4_gzdwjzw",
								"zyshgx4_ysr", "zyshgx4_ynjtjjlxhgyqk",
								"jtjjknqkjyy", "fqdw", "mqdw", "hscjgqgzx",
								"tc", "szqs", "qsdh", "jckkh", "drxsgbqk",
								"zxqjhschghzjxj", "pkx", "snjtsr", "fqzy",
								"mqzy", "jtjjly", "jtmytgshf", "jzsfyqz",
								"fmsfycj", "fmsfjz", "sqly", "zsjtcy1_gx",
								"zsjtcy2_gx", "zsjtcy3_gx", "zsjtcy4_gx",
								"zsjtcy5_gx" };

						valueForOut = new String[] { xh, nd, xzjtxxdz, yzbm,
								yxxxx, jg, jtdh, zsjtcy1_xm, zsjtcy1_nl,
								zsjtcy1_gzdwjzw, zsjtcy1_lxdh, zsjtcy1_ysr,
								zsjtcy2_xm, zsjtcy2_nl, zsjtcy2_gzdwjzw,
								zsjtcy2_lxdh, zsjtcy2_ysr, zsjtcy3_xm,
								zsjtcy3_nl, zsjtcy3_gzdwjzw, zsjtcy3_lxdh,
								zsjtcy3_ysr, zsjtcy4_xm, zsjtcy4_nl,
								zsjtcy4_gzdwjzw, zsjtcy4_lxdh, zsjtcy4_ysr,
								zsjtcy5_xm, zsjtcy5_nl, zsjtcy5_gzdwjzw,
								zsjtcy5_lxdh, zsjtcy5_ysr, jtjj_cz_qjnsr,
								jtjj_cz_rjysr, jtjj_nc_dnzsr, jtjj_nc_rjnsr,
								jtrks, ddzdshshbz, zyshgx1_xm, zyshgx1_nl,
								zyshgx1_gx, zyshgx1_gzdwjzw, zyshgx1_ysr,
								zyshgx1_ynjtjjlxhgyqk, zyshgx2_xm, zyshgx2_nl,
								zyshgx2_gx, zyshgx2_gzdwjzw, zyshgx2_ysr,
								zyshgx2_ynjtjjlxhgyqk, zyshgx3_xm, zyshgx3_nl,
								zyshgx3_gx, zyshgx3_gzdwjzw, zyshgx3_ysr,
								zyshgx3_ynjtjjlxhgyqk, zyshgx4_xm, zyshgx4_nl,
								zyshgx4_gx, zyshgx4_gzdwjzw, zyshgx4_ysr,
								zyshgx4_ynjtjjlxhgyqk, jtjjknqkjyy, fqdw, mqdw,
								hscjgqgzx, tc, szqs, qsdh, jckkh, drxsgbqk,
								zxqjhschghzjxj, pkx, snjtsr, fqzy, mqzy,
								jtjjly, jtmytgshf, jzsfyqz, fmsfycj, fmsfjz,
								sqly, zsjtcy1_gx, zsjtcy2_gx, zsjtcy3_gx,
								zsjtcy4_gx, zsjtcy5_gx };

						boolean ok = false;
						ok = StandardOperation.insert("csmz_knsxx", colName1,
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
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String xzjtxxdz = Base.chgNull(
						request.getParameter("xzjtxxdz"), "", 1);
				String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
				String yxxxx = Base.chgNull(request.getParameter("yxxxx"), "",
						1);
				String jg = Base.chgNull(request.getParameter("jg"), "", 1);
				String jtdh = Base.chgNull(request.getParameter("jtdh"), "", 1);
				String zsjtcy1_xm = Base.chgNull(request
						.getParameter("zsjtcy1_xm"), "", 1);
				String zsjtcy1_nl = Base.chgNull(request
						.getParameter("zsjtcy1_nl"), "", 1);
				String zsjtcy1_gzdwjzw = Base.chgNull(request
						.getParameter("zsjtcy1_gzdwjzw"), "", 1);
				String zsjtcy1_lxdh = Base.chgNull(request
						.getParameter("zsjtcy1_lxdh"), "", 1);
				String zsjtcy1_ysr = Base.chgNull(request
						.getParameter("zsjtcy1_ysr"), "", 1);
				String zsjtcy2_xm = Base.chgNull(request
						.getParameter("zsjtcy2_xm"), "", 1);
				String zsjtcy2_nl = Base.chgNull(request
						.getParameter("zsjtcy2_nl"), "", 1);
				String zsjtcy2_gzdwjzw = Base.chgNull(request
						.getParameter("zsjtcy2_gzdwjzw"), "", 1);
				String zsjtcy2_lxdh = Base.chgNull(request
						.getParameter("zsjtcy2_lxdh"), "", 1);
				String zsjtcy2_ysr = Base.chgNull(request
						.getParameter("zsjtcy2_ysr"), "", 1);
				String zsjtcy3_xm = Base.chgNull(request
						.getParameter("zsjtcy3_xm"), "", 1);
				String zsjtcy3_nl = Base.chgNull(request
						.getParameter("zsjtcy3_nl"), "", 1);
				String zsjtcy3_gzdwjzw = Base.chgNull(request
						.getParameter("zsjtcy3_gzdwjzw"), "", 1);
				String zsjtcy3_lxdh = Base.chgNull(request
						.getParameter("zsjtcy3_lxdh"), "", 1);
				String zsjtcy3_ysr = Base.chgNull(request
						.getParameter("zsjtcy3_ysr"), "", 1);
				String zsjtcy4_xm = Base.chgNull(request
						.getParameter("zsjtcy4_xm"), "", 1);
				String zsjtcy4_nl = Base.chgNull(request
						.getParameter("zsjtcy4_nl"), "", 1);
				String zsjtcy4_gzdwjzw = Base.chgNull(request
						.getParameter("zsjtcy4_gzdwjzw"), "", 1);
				String zsjtcy4_lxdh = Base.chgNull(request
						.getParameter("zsjtcy4_lxdh"), "", 1);
				String zsjtcy4_ysr = Base.chgNull(request
						.getParameter("zsjtcy4_ysr"), "", 1);
				String zsjtcy5_xm = Base.chgNull(request
						.getParameter("zsjtcy5_xm"), "", 1);
				String zsjtcy5_nl = Base.chgNull(request
						.getParameter("zsjtcy5_nl"), "", 1);
				String zsjtcy5_gzdwjzw = Base.chgNull(request
						.getParameter("zsjtcy5_gzdwjzw"), "", 1);
				String zsjtcy5_lxdh = Base.chgNull(request
						.getParameter("zsjtcy5_lxdh"), "", 1);
				String zsjtcy5_ysr = Base.chgNull(request
						.getParameter("zsjtcy5_ysr"), "", 1);
				String jtjj_cz_qjnsr = Base.chgNull(request
						.getParameter("jtjj_cz_qjnsr"), "", 1);
				String jtjj_cz_rjysr = Base.chgNull(request
						.getParameter("jtjj_cz_rjysr"), "", 1);
				String jtjj_nc_dnzsr = Base.chgNull(request
						.getParameter("jtjj_nc_dnzsr"), "", 1);
				String jtjj_nc_rjnsr = Base.chgNull(request
						.getParameter("jtjj_nc_rjnsr"), "", 1);
				String jtrks = Base.chgNull(request.getParameter("jtrks"), "",
						1);
				String ddzdshshbz = Base.chgNull(request
						.getParameter("ddzdshshbz"), "", 1);
				String zyshgx1_xm = Base.chgNull(request
						.getParameter("zyshgx1_xm"), "", 1);
				String zyshgx1_nl = Base.chgNull(request
						.getParameter("zyshgx1_nl"), "", 1);
				String zyshgx1_gx = Base.chgNull(request
						.getParameter("zyshgx1_gx"), "", 1);
				String zyshgx1_gzdwjzw = Base.chgNull(request
						.getParameter("zyshgx1_gzdwjzw"), "", 1);
				String zyshgx1_ysr = Base.chgNull(request
						.getParameter("zyshgx1_ysr"), "", 1);
				String zyshgx1_ynjtjjlxhgyqk = Base.chgNull(request
						.getParameter("zyshgx1_ynjtjjlxhgyqk"), "", 1);
				String zyshgx2_xm = Base.chgNull(request
						.getParameter("zyshgx2_xm"), "", 1);
				String zyshgx2_nl = Base.chgNull(request
						.getParameter("zyshgx2_nl"), "", 1);
				String zyshgx2_gx = Base.chgNull(request
						.getParameter("zyshgx2_gx"), "", 1);
				String zyshgx2_gzdwjzw = Base.chgNull(request
						.getParameter("zyshgx2_gzdwjzw"), "", 1);
				String zyshgx2_ysr = Base.chgNull(request
						.getParameter("zyshgx2_ysr"), "", 1);
				String zyshgx2_ynjtjjlxhgyqk = Base.chgNull(request
						.getParameter("zyshgx2_ynjtjjlxhgyqk"), "", 1);
				String zyshgx3_xm = Base.chgNull(request
						.getParameter("zyshgx3_xm"), "", 1);
				String zyshgx3_nl = Base.chgNull(request
						.getParameter("zyshgx3_nl"), "", 1);
				String zyshgx3_gx = Base.chgNull(request
						.getParameter("zyshgx3_gx"), "", 1);
				String zyshgx3_gzdwjzw = Base.chgNull(request
						.getParameter("zyshgx3_gzdwjzw"), "", 1);
				String zyshgx3_ysr = Base.chgNull(request
						.getParameter("zyshgx3_ysr"), "", 1);
				String zyshgx3_ynjtjjlxhgyqk = Base.chgNull(request
						.getParameter("zyshgx3_ynjtjjlxhgyqk"), "", 1);
				String zyshgx4_xm = Base.chgNull(request
						.getParameter("zyshgx4_xm"), "", 1);
				String zyshgx4_nl = Base.chgNull(request
						.getParameter("zyshgx4_nl"), "", 1);
				String zyshgx4_gx = Base.chgNull(request
						.getParameter("zyshgx4_gx"), "", 1);
				String zyshgx4_gzdwjzw = Base.chgNull(request
						.getParameter("zyshgx4_gzdwjzw"), "", 1);
				String zyshgx4_ysr = Base.chgNull(request
						.getParameter("zyshgx4_ysr"), "", 1);
				String zyshgx4_ynjtjjlxhgyqk = Base.chgNull(request
						.getParameter("zyshgx4_ynjtjjlxhgyqk"), "", 1);
				String jtjjknqkjyy = Base.chgNull(request
						.getParameter("jtjjknqkjyy"), "", 1);
				String fqdw = Base.chgNull(request.getParameter("fqdw"), "", 1);
				String mqdw = Base.chgNull(request.getParameter("mqdw"), "", 1);
				String hscjgqgzx = Base.chgNull(request
						.getParameter("hscjgqgzx"), "", 1);
				String tc = Base.chgNull(request.getParameter("tc"), "", 1);
				String szqs = Base.chgNull(request.getParameter("szqs"), "", 1);
				String qsdh = Base.chgNull(request.getParameter("qsdh"), "", 1);
				String jckkh = Base.chgNull(request.getParameter("jckkh"), "",
						1);
				String drxsgbqk = Base.chgNull(
						request.getParameter("drxsgbqk"), "", 1);
				String zxqjhschghzjxj = Base.chgNull(request
						.getParameter("zxqjhschghzjxj"), "", 1);
				String pkx = Base.chgNull(request.getParameter("pkx"), "", 1);
				String snjtsr = Base.chgNull(request.getParameter("snjtsr"),
						"", 1);
				String fqzy = Base.chgNull(request.getParameter("fqzy"), "", 1);
				String mqzy = Base.chgNull(request.getParameter("mqzy"), "", 1);
				String jtjjly = Base.chgNull(request.getParameter("jtjjly"),
						"", 1);
				String jtmytgshf = Base.chgNull(request
						.getParameter("jtmytgshf"), "", 1);
				String jzsfyqz = Base.chgNull(request.getParameter("jzsfyqz"),
						"", 1);
				String fmsfycj = Base.chgNull(request.getParameter("fmsfycj"),
						"", 1);
				String fmsfjz = Base.chgNull(request.getParameter("fmsfjz"),
						"", 1);
				String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
				String zsjtcy1_gx = Base.chgNull(request
						.getParameter("zsjtcy1_gx"), "", 1);
				String zsjtcy2_gx = Base.chgNull(request
						.getParameter("zsjtcy2_gx"), "", 1);
				String zsjtcy3_gx = Base.chgNull(request
						.getParameter("zsjtcy3_gx"), "", 1);
				String zsjtcy4_gx = Base.chgNull(request
						.getParameter("zsjtcy4_gx"), "", 1);
				String zsjtcy5_gx = Base.chgNull(request
						.getParameter("zsjtcy5_gx"), "", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = nd + xh;
				}

				sql = "select xxsh from csmz_knsxx where nd||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("贫困生") || temp[0]
								.equalsIgnoreCase("特困生"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("csmz_knsxx", "nd||xh", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xh", "nd", "xzjtxxdz",
							"yzbm", "yxxxx", "jg", "jtdh", "zsjtcy1_xm",
							"zsjtcy1_nl", "zsjtcy1_gzdwjzw", "zsjtcy1_lxdh",
							"zsjtcy1_ysr", "zsjtcy2_xm", "zsjtcy2_nl",
							"zsjtcy2_gzdwjzw", "zsjtcy2_lxdh", "zsjtcy2_ysr",
							"zsjtcy3_xm", "zsjtcy3_nl", "zsjtcy3_gzdwjzw",
							"zsjtcy3_lxdh", "zsjtcy3_ysr", "zsjtcy4_xm",
							"zsjtcy4_nl", "zsjtcy4_gzdwjzw", "zsjtcy4_lxdh",
							"zsjtcy4_ysr", "zsjtcy5_xm", "zsjtcy5_nl",
							"zsjtcy5_gzdwjzw", "zsjtcy5_lxdh", "zsjtcy5_ysr",
							"jtjj_cz_qjnsr", "jtjj_cz_rjysr", "jtjj_nc_dnzsr",
							"jtjj_nc_rjnsr", "jtrks", "ddzdshshbz",
							"zyshgx1_xm", "zyshgx1_nl", "zyshgx1_gx",
							"zyshgx1_gzdwjzw", "zyshgx1_ysr",
							"zyshgx1_ynjtjjlxhgyqk", "zyshgx2_xm",
							"zyshgx2_nl", "zyshgx2_gx", "zyshgx2_gzdwjzw",
							"zyshgx2_ysr", "zyshgx2_ynjtjjlxhgyqk",
							"zyshgx3_xm", "zyshgx3_nl", "zyshgx3_gx",
							"zyshgx3_gzdwjzw", "zyshgx3_ysr",
							"zyshgx3_ynjtjjlxhgyqk", "zyshgx4_xm",
							"zyshgx4_nl", "zyshgx4_gx", "zyshgx4_gzdwjzw",
							"zyshgx4_ysr", "zyshgx4_ynjtjjlxhgyqk",
							"jtjjknqkjyy", "fqdw", "mqdw", "hscjgqgzx", "tc",
							"szqs", "qsdh", "jckkh", "drxsgbqk",
							"zxqjhschghzjxj", "pkx", "snjtsr", "fqzy", "mqzy",
							"jtjjly", "jtmytgshf", "jzsfyqz", "fmsfycj",
							"fmsfjz", "sqly", "zsjtcy1_gx", "zsjtcy2_gx",
							"zsjtcy3_gx", "zsjtcy4_gx", "zsjtcy5_gx" };

					valueForOut = new String[] { xh, nd, xzjtxxdz, yzbm, yxxxx,
							jg, jtdh, zsjtcy1_xm, zsjtcy1_nl, zsjtcy1_gzdwjzw,
							zsjtcy1_lxdh, zsjtcy1_ysr, zsjtcy2_xm, zsjtcy2_nl,
							zsjtcy2_gzdwjzw, zsjtcy2_lxdh, zsjtcy2_ysr,
							zsjtcy3_xm, zsjtcy3_nl, zsjtcy3_gzdwjzw,
							zsjtcy3_lxdh, zsjtcy3_ysr, zsjtcy4_xm, zsjtcy4_nl,
							zsjtcy4_gzdwjzw, zsjtcy4_lxdh, zsjtcy4_ysr,
							zsjtcy5_xm, zsjtcy5_nl, zsjtcy5_gzdwjzw,
							zsjtcy5_lxdh, zsjtcy5_ysr, jtjj_cz_qjnsr,
							jtjj_cz_rjysr, jtjj_nc_dnzsr, jtjj_nc_rjnsr, jtrks,
							ddzdshshbz, zyshgx1_xm, zyshgx1_nl, zyshgx1_gx,
							zyshgx1_gzdwjzw, zyshgx1_ysr,
							zyshgx1_ynjtjjlxhgyqk, zyshgx2_xm, zyshgx2_nl,
							zyshgx2_gx, zyshgx2_gzdwjzw, zyshgx2_ysr,
							zyshgx2_ynjtjjlxhgyqk, zyshgx3_xm, zyshgx3_nl,
							zyshgx3_gx, zyshgx3_gzdwjzw, zyshgx3_ysr,
							zyshgx3_ynjtjjlxhgyqk, zyshgx4_xm, zyshgx4_nl,
							zyshgx4_gx, zyshgx4_gzdwjzw, zyshgx4_ysr,
							zyshgx4_ynjtjjlxhgyqk, jtjjknqkjyy, fqdw, mqdw,
							hscjgqgzx, tc, szqs, qsdh, jckkh, drxsgbqk,
							zxqjhschghzjxj, pkx, snjtsr, fqzy, mqzy, jtjjly,
							jtmytgshf, jzsfyqz, fmsfycj, fmsfjz, sqly,
							zsjtcy1_gx, zsjtcy2_gx, zsjtcy3_gx, zsjtcy4_gx,
							zsjtcy5_gx };

					boolean ok = false;
					ok = StandardOperation.insert("csmz_knsxx", colName1,
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
			pkVal = nd + xh;
		}

		sql = "select xh,nd,xzjtxxdz,yzbm,yxxxx,jg,jtdh,xm,xb,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,mzmc,zzmmmc,sfzh,nj,zsjtcy1_xm,zsjtcy1_nl,zsjtcy1_gzdwjzw,zsjtcy1_lxdh,zsjtcy1_ysr,zsjtcy2_xm,zsjtcy2_nl,zsjtcy2_gzdwjzw,zsjtcy2_lxdh,zsjtcy2_ysr,zsjtcy3_xm,zsjtcy3_nl,zsjtcy3_gzdwjzw,zsjtcy3_lxdh,zsjtcy3_ysr,zsjtcy4_xm,zsjtcy4_nl,zsjtcy4_gzdwjzw,zsjtcy4_lxdh,zsjtcy4_ysr,zsjtcy5_xm,zsjtcy5_nl,zsjtcy5_gzdwjzw,zsjtcy5_lxdh,zsjtcy5_ysr,jtrks,jtjj_cz_qjnsr,jtjj_cz_rjysr,jtjj_nc_dnzsr,jtjj_nc_rjnsr,ddzdshshbz,zyshgx1_xm,zyshgx1_nl,zyshgx1_gx,zyshgx1_gzdwjzw,zyshgx1_ysr,zyshgx1_ynjtjjlxhgyqk,zyshgx2_xm,zyshgx2_nl,zyshgx2_gx,zyshgx2_gzdwjzw,zyshgx2_ysr,zyshgx2_ynjtjjlxhgyqk,zyshgx3_xm,zyshgx3_nl,zyshgx3_gx,zyshgx3_gzdwjzw,zyshgx3_ysr,zyshgx3_ynjtjjlxhgyqk,zyshgx4_xm,zyshgx4_nl,zyshgx4_gx,zyshgx4_gzdwjzw,zyshgx4_ysr,zyshgx4_ynjtjjlxhgyqk,jtjjknqkjyy,fqdw,mqdw,hscjgqgzx,tc,szqs,qsdh,jckkh,drxsgbqk,zxqjhschghzjxj,pkx,snjtsr,fqzy,mqzy,jtjjly,jtmytgshf,jzsfyqz,fmsfycj,fmsfjz,sqly,sqsj,fdysh,fdyshsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,zsjtcy1_gx,zsjtcy2_gx,zsjtcy3_gx,zsjtcy4_gx,zsjtcy5_gx from view_csmz_knsxx where nd||xh=?";
		outString = new String[] { "xh", "nd", "xzjtxxdz", "yzbm", "yxxxx",
				"jg", "jtdh", "xm", "xb", "csrq", "rxny", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "mzmc", "zzmmmc", "sfzh", "nj",
				"zsjtcy1_xm", "zsjtcy1_nl", "zsjtcy1_gzdwjzw", "zsjtcy1_lxdh",
				"zsjtcy1_ysr", "zsjtcy2_xm", "zsjtcy2_nl", "zsjtcy2_gzdwjzw",
				"zsjtcy2_lxdh", "zsjtcy2_ysr", "zsjtcy3_xm", "zsjtcy3_nl",
				"zsjtcy3_gzdwjzw", "zsjtcy3_lxdh", "zsjtcy3_ysr", "zsjtcy4_xm",
				"zsjtcy4_nl", "zsjtcy4_gzdwjzw", "zsjtcy4_lxdh", "zsjtcy4_ysr",
				"zsjtcy5_xm", "zsjtcy5_nl", "zsjtcy5_gzdwjzw", "zsjtcy5_lxdh",
				"zsjtcy5_ysr", "jtrks", "jtjj_cz_qjnsr", "jtjj_cz_rjysr",
				"jtjj_nc_dnzsr", "jtjj_nc_rjnsr", "ddzdshshbz", "zyshgx1_xm",
				"zyshgx1_nl", "zyshgx1_gx", "zyshgx1_gzdwjzw", "zyshgx1_ysr",
				"zyshgx1_ynjtjjlxhgyqk", "zyshgx2_xm", "zyshgx2_nl",
				"zyshgx2_gx", "zyshgx2_gzdwjzw", "zyshgx2_ysr",
				"zyshgx2_ynjtjjlxhgyqk", "zyshgx3_xm", "zyshgx3_nl",
				"zyshgx3_gx", "zyshgx3_gzdwjzw", "zyshgx3_ysr",
				"zyshgx3_ynjtjjlxhgyqk", "zyshgx4_xm", "zyshgx4_nl",
				"zyshgx4_gx", "zyshgx4_gzdwjzw", "zyshgx4_ysr",
				"zyshgx4_ynjtjjlxhgyqk", "jtjjknqkjyy", "fqdw", "mqdw",
				"hscjgqgzx", "tc", "szqs", "qsdh", "jckkh", "drxsgbqk",
				"zxqjhschghzjxj", "pkx", "snjtsr", "fqzy", "mqzy", "jtjjly",
				"jtmytgshf", "jzsfyqz", "fmsfycj", "fmsfjz", "sqly", "sqsj",
				"fdysh", "fdyshsj", "xysh", "xyshsj", "xyshyj", "xxsh",
				"xxshsj", "xxshyj", "zsjtcy1_gx", "zsjtcy2_gx", "zsjtcy3_gx",
				"zsjtcy4_gx", "zsjtcy5_gx" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select b.xh,b.xm,b.xb,b.csrq,b.mzmc,b.sfzh,b.zzmmmc,b.nj,"
						+ "b.xymc,b.zymc,b.bjmc,(select a.rxny from bks_xsjbxx a where a.xh=b.xh) rxny from view_stu_details b where b.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csrq",
						"mzmc", "sfzh", "zzmmmc", "nj", "zzmmmc", "xymc",
						"zymc", "bjmc", "rxny" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "csrq", "mzmc",
							"sfzh", "zzmmmc", "nj", "zzmmmc", "xymc", "zymc",
							"bjmc", "rxny" };
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
		return mapping.findForward("knssq");
	}
	
	/**
	 * @describe 打印家庭经济情况调查表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jtjjqkdcb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xzjtxxdz = Base
				.chgNull(request.getParameter("xzjtxxdz "), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String yxxxx = Base.chgNull(request.getParameter("yxxxx"), "", 1);
		String jg = Base.chgNull(request.getParameter("jg"), "", 1);
		String jtdh = Base.chgNull(request.getParameter("jtdh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String zsjtcy1_xm = Base.chgNull(request.getParameter("zsjtcy1_xm"),
				"", 1);
		String zsjtcy1_gx = Base.chgNull(request.getParameter("zsjtcy1_gx"),
				"", 1);
		String zsjtcy1_nl = Base.chgNull(request.getParameter("zsjtcy1_nl"),
				"", 1);
		String zsjtcy1_gzdwjzw = Base.chgNull(request
				.getParameter("zsjtcy1_gzdwjzw"), "", 1);
		String zsjtcy1_lxdh = Base.chgNull(
				request.getParameter("zsjtcy1_lxdh"), "", 1);
		String zsjtcy1_ysr = Base.chgNull(request.getParameter("zsjtcy1_ysr"),
				"", 1);
		String zsjtcy2_xm = Base.chgNull(request.getParameter("zsjtcy2_xm"),
				"", 1);
		String zsjtcy2_gx = Base.chgNull(request.getParameter("zsjtcy2_gx"),
				"", 1);
		String zsjtcy2_nl = Base.chgNull(request.getParameter("zsjtcy2_nl"),
				"", 1);
		String zsjtcy2_gzdwjzw = Base.chgNull(request
				.getParameter("zsjtcy2_gzdwjzw"), "", 1);
		String zsjtcy2_lxdh = Base.chgNull(
				request.getParameter("zsjtcy2_lxdh"), "", 1);
		String zsjtcy2_ysr = Base.chgNull(request.getParameter("zsjtcy2_ysr"),
				"", 1);
		String zsjtcy3_xm = Base.chgNull(request.getParameter("zsjtcy3_xm"),
				"", 1);
		String zsjtcy3_gx = Base.chgNull(request.getParameter("zsjtcy3_gx"),
				"", 1);
		String zsjtcy3_nl = Base.chgNull(request.getParameter("zsjtcy3_nl"),
				"", 1);
		String zsjtcy3_gzdwjzw = Base.chgNull(request
				.getParameter("zsjtcy3_gzdwjzw"), "", 1);
		String zsjtcy3_lxdh = Base.chgNull(
				request.getParameter("zsjtcy3_lxdh"), "", 1);
		String zsjtcy3_ysr = Base.chgNull(request.getParameter("zsjtcy3_ysr"),
				"", 1);
		String zsjtcy4_xm = Base.chgNull(request.getParameter("zsjtcy4_xm"),
				"", 1);
		String zsjtcy4_gx = Base.chgNull(request.getParameter("zsjtcy4_gx"),
				"", 1);
		String zsjtcy4_nl = Base.chgNull(request.getParameter("zsjtcy4_nl"),
				"", 1);
		String zsjtcy4_gzdwjzw = Base.chgNull(request
				.getParameter("zsjtcy4_gzdwjzw"), "", 1);
		String zsjtcy4_lxdh = Base.chgNull(
				request.getParameter("zsjtcy4_lxdh"), "", 1);
		String zsjtcy4_ysr = Base.chgNull(request.getParameter("zsjtcy4_ysr"),
				"", 1);
		String zsjtcy5_xm = Base.chgNull(request.getParameter("zsjtcy5_xm"),
				"", 1);
		String zsjtcy5_gx = Base.chgNull(request.getParameter("zsjtcy5_gx"),
				"", 1);
		String zsjtcy5_nl = Base.chgNull(request.getParameter("zsjtcy5_nl"),
				"", 1);
		String zsjtcy5_gzdwjzw = Base.chgNull(request
				.getParameter("zsjtcy5_gzdwjzw"), "", 1);
		String zsjtcy5_lxdh = Base.chgNull(
				request.getParameter("zsjtcy5_lxdh"), "", 1);
		String zsjtcy5_ysr = Base.chgNull(request.getParameter("zsjtcy5_ysr"),
				"", 1);
		String jtrks = Base.chgNull(request.getParameter("jtrks"), "", 1);
		String jtjj_cz_qjnsr = Base.chgNull(request
				.getParameter("jtjj_cz_qjnsr"), "", 1);
		String jtjj_cz_rjysr = Base.chgNull(request
				.getParameter("jtjj_cz_rjysr"), "", 1);
		String jtjj_nc_dnzsr = Base.chgNull(request
				.getParameter("jtjj_nc_dnzsr"), "", 1);
		String jtjj_nc_rjnsr = Base.chgNull(request
				.getParameter("jtjj_nc_rjnsr"), "", 1);
		String ddzdshshbz = Base.chgNull(request.getParameter("ddzdshshbz"),
				"", 1);
		String zyshgx1_xm = Base.chgNull(request.getParameter("zyshgx1_xm"),
				"", 1);
		String zyshgx1_nl = Base.chgNull(request.getParameter("zyshgx1_nl"),
				"", 1);
		String zyshgx1_gx = Base.chgNull(request.getParameter("zyshgx1_gx"),
				"", 1);
		String zyshgx1_gzdwjzw = Base.chgNull(request
				.getParameter("zyshgx1_gzdwjzw"), "", 1);
		String zyshgx1_ysr = Base.chgNull(request.getParameter("zyshgx1_ysr"),
				"", 1);
		String zyshgx1_ynjtjjlxhgyqk = Base.chgNull(request
				.getParameter("zyshgx1_ynjtjjlxhgyqk"), "", 1);
		String zyshgx2_xm = Base.chgNull(request.getParameter("zyshgx2_xm"),
				"", 1);
		String zyshgx2_nl = Base.chgNull(request.getParameter("zyshgx2_nl"),
				"", 1);
		String zyshgx2_gx = Base.chgNull(request.getParameter("zyshgx2_gx"),
				"", 1);
		String zyshgx2_gzdwjzw = Base.chgNull(request
				.getParameter("zyshgx2_gzdwjzw"), "", 1);
		String zyshgx2_ysr = Base.chgNull(request.getParameter("zyshgx2_ysr"),
				"", 1);
		String zyshgx2_ynjtjjlxhgyqk = Base.chgNull(request
				.getParameter("zyshgx2_ynjtjjlxhgyqk"), "", 1);
		String zyshgx3_xm = Base.chgNull(request.getParameter("zyshgx3_xm"),
				"", 1);
		String zyshgx3_nl = Base.chgNull(request.getParameter("zyshgx3_nl"),
				"", 1);
		String zyshgx3_gx = Base.chgNull(request.getParameter("zyshgx3_gx"),
				"", 1);
		String zyshgx3_gzdwjzw = Base.chgNull(request
				.getParameter("zyshgx3_gzdwjzw"), "", 1);
		String zyshgx3_ysr = Base.chgNull(request.getParameter("zyshgx3_ysr"),
				"", 1);
		String zyshgx3_ynjtjjlxhgyqk = Base.chgNull(request
				.getParameter("zyshgx3_ynjtjjlxhgyqk"), "", 1);
		String zyshgx4_xm = Base.chgNull(request.getParameter("zyshgx4_xm"),
				"", 1);
		String zyshgx4_nl = Base.chgNull(request.getParameter("zyshgx4_nl"),
				"", 1);
		String zyshgx4_gx = Base.chgNull(request.getParameter("zyshgx4_gx"),
				"", 1);
		String zyshgx4_gzdwjzw = Base.chgNull(request
				.getParameter("zyshgx4_gzdwjzw"), "", 1);
		String zyshgx4_ysr = Base.chgNull(request.getParameter("zyshgx4_ysr"),
				"", 1);
		String zyshgx4_ynjtjjlxhgyqk = Base.chgNull(request
				.getParameter("zyshgx4_ynjtjjlxhgyqk"), "", 1);
		String jtjjknqkjyy = Base.chgNull(request.getParameter("jtjjknqkjyy"),
				"", 1);
		String fqdw = Base.chgNull(request.getParameter("fqdw"), "", 1);
		String mqdw = Base.chgNull(request.getParameter("mqdw"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		
		if ((null == sqsj) || ("".equalsIgnoreCase(sqsj))) {
			sqsj = dao.getOneRs(
					"select to_char(sysdate,'yyyy-yy-dd') data from dual",
					new String[] {}, "data");
		}
		String year = sqsj.substring(0, 4);
		String mon = sqsj.substring(5, 7);
		String day = sqsj.substring(8);
		sqsj = year + "年" + mon + "月" + day + "日";

		String[] outValue = new String[] { xh, nd, xzjtxxdz, yzbm, yxxxx, jg,
				jtdh, xm, xb, csrq, rxny, xymc, zymc, bjmc, mzmc, zzmmmc, sfzh,
				nj, zsjtcy1_xm, zsjtcy1_gx, zsjtcy1_nl, zsjtcy1_gzdwjzw,
				zsjtcy1_lxdh, zsjtcy1_ysr, zsjtcy2_xm, zsjtcy2_gx, zsjtcy2_nl,
				zsjtcy2_gzdwjzw, zsjtcy2_lxdh, zsjtcy2_ysr, zsjtcy3_xm,
				zsjtcy3_gx, zsjtcy3_nl, zsjtcy3_gzdwjzw, zsjtcy3_lxdh,
				zsjtcy3_ysr, zsjtcy4_xm, zsjtcy4_gx, zsjtcy4_nl,
				zsjtcy4_gzdwjzw, zsjtcy4_lxdh, zsjtcy4_ysr, zsjtcy5_xm,
				zsjtcy5_gx, zsjtcy5_nl, zsjtcy5_gzdwjzw, zsjtcy5_lxdh,
				zsjtcy5_ysr, jtrks, jtjj_cz_qjnsr, jtjj_cz_rjysr,
				jtjj_nc_dnzsr, jtjj_nc_rjnsr, ddzdshshbz, zyshgx1_xm,
				zyshgx1_nl, zyshgx1_gx, zyshgx1_gzdwjzw, zyshgx1_ysr,
				zyshgx1_ynjtjjlxhgyqk, zyshgx2_xm, zyshgx2_nl, zyshgx2_gx,
				zyshgx2_gzdwjzw, zyshgx2_ysr, zyshgx2_ynjtjjlxhgyqk,
				zyshgx3_xm, zyshgx3_nl, zyshgx3_gx, zyshgx3_gzdwjzw,
				zyshgx3_ysr, zyshgx3_ynjtjjlxhgyqk, zyshgx4_xm, zyshgx4_nl,
				zyshgx4_gx, zyshgx4_gzdwjzw, zyshgx4_ysr,
				zyshgx4_ynjtjjlxhgyqk, jtjjknqkjyy, fqdw, mqdw, sqsj };
		String[] outString = new String[] { "xh", "nd", "xzjtxxdz", "yzbm",
				"yxxxx", "jg", "jtdh", "xm", "xb", "csrq", "rxny", "xymc",
				"zymc", "bjmc", "mzmc", "zzmmmc", "sfzh", "nj", "zsjtcy1_xm",
				"zsjtcy1_gx", "zsjtcy1_nl", "zsjtcy1_gzdwjzw", "zsjtcy1_lxdh",
				"zsjtcy1_ysr", "zsjtcy2_xm", "zsjtcy2_gx", "zsjtcy2_nl",
				"zsjtcy2_gzdwjzw", "zsjtcy2_lxdh", "zsjtcy2_ysr", "zsjtcy3_xm",
				"zsjtcy3_gx", "zsjtcy3_nl", "zsjtcy3_gzdwjzw", "zsjtcy3_lxdh",
				"zsjtcy3_ysr", "zsjtcy4_xm", "zsjtcy4_gx", "zsjtcy4_nl",
				"zsjtcy4_gzdwjzw", "zsjtcy4_lxdh", "zsjtcy4_ysr", "zsjtcy5_xm",
				"zsjtcy5_gx", "zsjtcy5_nl", "zsjtcy5_gzdwjzw", "zsjtcy5_lxdh",
				"zsjtcy5_ysr", "jtrks", "jtjj_cz_qjnsr", "jtjj_cz_rjysr",
				"jtjj_nc_dnzsr", "jtjj_nc_rjnsr", "ddzdshshbz", "zyshgx1_xm",
				"zyshgx1_nl", "zyshgx1_gx", "zyshgx1_gzdwjzw", "zyshgx1_ysr",
				"zyshgx1_ynjtjjlxhgyqk", "zyshgx2_xm", "zyshgx2_nl",
				"zyshgx2_gx", "zyshgx2_gzdwjzw", "zyshgx2_ysr",
				"zyshgx2_ynjtjjlxhgyqk", "zyshgx3_xm", "zyshgx3_nl",
				"zyshgx3_gx", "zyshgx3_gzdwjzw", "zyshgx3_ysr",
				"zyshgx3_ynjtjjlxhgyqk", "zyshgx4_xm", "zyshgx4_nl",
				"zyshgx4_gx", "zyshgx4_gzdwjzw", "zyshgx4_ysr",
				"zyshgx4_ynjtjjlxhgyqk", "jtjjknqkjyy", "fqdw", "mqdw", "sqsj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		
		if(null != jtjj_cz_qjnsr && !"".equalsIgnoreCase(jtjj_cz_qjnsr)){
			request.setAttribute("fkqk", "cz");
		} else {
			request.setAttribute("fkqk", "nc");
		}
		return mapping.findForward("jtjjqkdcb");
	}
	
	/**
	 * @describe 打印贫困学生登记表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward pksdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xzjtxxdz = Base.chgNull(request.getParameter("xzjtxxdz"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtdh = Base.chgNull(request.getParameter("jtdh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String jtrks = Base.chgNull(request.getParameter("jtrks"), "", 1);
		String hscjgqgzx = Base.chgNull(request.getParameter("hscjgqgzx"), "",
				1);
		String tc = Base.chgNull(request.getParameter("tc"), "", 1);
		String szqs = Base.chgNull(request.getParameter("szqs"), "", 1);
		String qsdh = Base.chgNull(request.getParameter("qsdh"), "", 1);
		String jckkh = Base.chgNull(request.getParameter("jckkh"), "", 1);
		String drxsgbqk = Base.chgNull(request.getParameter("drxsgbqk"), "", 1);
		String zxqjhschghzjxj = Base.chgNull(request
				.getParameter("zxqjhschghzjxj"), "", 1);
		String pkx = Base.chgNull(request.getParameter("pkx"), "", 1);
		String snjtsr = Base.chgNull(request.getParameter("snjtsr"), "", 1);
		String fqzy = Base.chgNull(request.getParameter("fqzy"), "", 1);
		String mqzy = Base.chgNull(request.getParameter("mqzy"), "", 1);
		String jtjjly = Base.chgNull(request.getParameter("jtjjly"), "", 1);
		String jtmytgshf = Base.chgNull(request.getParameter("jtmytgshf"), "",
				1);
		String jzsfyqz = Base.chgNull(request.getParameter("jzsfyqz"), "", 1);
		String fmsfycj = Base.chgNull(request.getParameter("fmsfycj"), "", 1);
		String fmsfjz = Base.chgNull(request.getParameter("fmsfjz"), "", 1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String fdysh = Base.chgNull(request.getParameter("fdysh"), "", 1);
		String fdyshsj = Base.chgNull(request.getParameter("fdyshsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		
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

		String[] outValue = new String[] { xh, nd, xzjtxxdz, yzbm, jtdh, xm,
				xb, csrq, rxny, xymc, zymc, bjmc, mzmc, zzmmmc, sfzh, nj,
				jtrks, hscjgqgzx, tc, szqs, qsdh, jckkh, drxsgbqk,
				zxqjhschghzjxj, pkx, snjtsr, fqzy, mqzy, jtjjly, jtmytgshf,
				jzsfyqz, fmsfycj, fmsfjz, sqly, sqsj, fdysh, fdyshsj, xysh,
				xyshsj, xyshyj, xxsh, xxshsj, xxshyj };
		String[] outString = new String[] { "xh", "nd", "xzjtxxdz", "yzbm",
				"jtdh", "xm", "xb", "csrq", "rxny", "xymc", "zymc", "bjmc",
				"mzmc", "zzmmmc", "sfzh", "nj", "jtrks", "hscjgqgzx", "tc",
				"szqs", "qsdh", "jckkh", "drxsgbqk", "zxqjhschghzjxj", "pkx",
				"snjtsr", "fqzy", "mqzy", "jtjjly", "jtmytgshf", "jzsfyqz",
				"fmsfycj", "fmsfjz", "sqly", "sqsj", "fdysh", "fdyshsj",
				"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		request.setAttribute("xxsh", xxsh);
		request.setAttribute("pkx", pkx);
		request.setAttribute("jzsfyqz", jzsfyqz);
		return mapping.findForward("pksdjb");
	}

	/**
	 * @describe 困难生审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "csmz_knsxx";
		pk = "nd||xh";
		tableName = "view_csmz_knsxx";
		
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
		tips = "当前所在位置：学生资助 - 审核 - 贫困生项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 贫困生";
			colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh", "xysh",
					"xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('贫困生','特困生') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('贫困生','特困生') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh",
						"xysh", "" };
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
				} else {
					colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj",
							"fdysh", "" };
				}
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核') in ('贫困生','特困生') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.fdysh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('贫困生','特困生') order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("user", "xx");
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					sql = "select (case when nvl(a.fdysh,'未审核') in ('贫困生','特困生') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.fdysh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc) a";
					colList[colList.length - 1] = "fdysh";
					request.setAttribute("user", "fdy");
				} else {
					sql = "select (case when nvl(a.xysh,'未审核') in ('贫困生','特困生') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.fdysh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh in ('贫困生','特困生') order by xysh desc) a";
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
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
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
		request.setAttribute("act", "zzsh");
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
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
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
						sqlT[i] = "delete csmz_knsxx where nd||xh='"+pkT+"' and xysh not in ('贫困生','特困生')";
					} else {
						sqlT[i] = "delete csmz_knsxx where nd||xh='"+pkT+"' and xxsh not in ('贫困生','特困生')";
					}
				} else {
					sqlT[i] = "delete csmz_knsxx where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("passPKS".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "update csmz_knsxx set fdysh='贫困生',fdyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
					} else {
						sqlT[i] = "update csmz_knsxx set xysh='贫困生',xyshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
					}
				} else {
					sqlT[i] = "update csmz_knsxx set xxsh='贫困生',xxshsj='"
						+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("passTKS".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "update csmz_knsxx set fdysh='特困生',fdyshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
					} else {
						sqlT[i] = "update csmz_knsxx set xysh='特困生',xyshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
					}
				} else {
					sqlT[i] = "update csmz_knsxx set xxsh='特困生',xxshsj='"
						+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=knssh&go=go", false);
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
						String xyshT = dao.getOneRs(
								"select xysh from csmz_knsxx where nd||xh=?",
								new String[] { pkT }, "xysh");
						if (!"贫困生".equalsIgnoreCase(xyshT)&&!"特困生".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update csmz_knsxx set fdysh='不通过',fdyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
						}
					} else {
						String xxshT = dao.getOneRs(
								"select xxsh from csmz_knsxx where nd||xh=?",
								new String[] { pkT }, "xxsh");
						if (!"贫困生".equalsIgnoreCase(xxshT)&&!"特困生".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update csmz_knsxx set xysh='不通过',xyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
						}
					}
				} else {
					sqlT[i] = "update csmz_knsxx set xxsh='不通过',xxshsj='"
						+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao.getOneRs(
							"select xysh from csmz_knsxx where nd||xh=?",
							new String[] { pkVal }, "xysh");
					if ("贫困生".equalsIgnoreCase(xyshT)
							|| "特困生".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						StandardOperation.update("csmz_knsxx", new String[] {
								"fdysh", "fdyshsj" },
								new String[] { yesNo, now }, "nd||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao.getOneRs(
							"select xxsh from csmz_knsxx where nd||xh=?",
							new String[] { pkVal }, "xxsh");
					if ("贫困生".equalsIgnoreCase(xxshT)
							|| "特困生".equalsIgnoreCase(xxshT)) {
						request.setAttribute("xxshjg", "pass");
					} else {
						StandardOperation.update("csmz_knsxx", new String[] {
								"xysh", "xyshyj", "xyshsj" }, new String[] {
								yesNo, xyshyj, now }, "nd||xh", pkVal, request);
					}
				}
			} else {
				StandardOperation.update("csmz_knsxx", new String[] {
						"xxsh", "xxshyj", "xxshsj" }, new String[] {
						yesNo, xxshyj, now }, "nd||xh", pkVal,
						request);
			}
		}
		realTable = "csmz_knsxx";
		pk = "nd||xh";
		sql = "select xh,nd,xzjtxxdz,yzbm,yxxxx,jg,jtdh,xm,xb,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,mzmc,zzmmmc,sfzh,nj,zsjtcy1_xm,zsjtcy1_gx,zsjtcy1_nl,zsjtcy1_gzdwjzw,zsjtcy1_lxdh,zsjtcy1_ysr,zsjtcy2_xm,zsjtcy2_gx,zsjtcy2_nl,zsjtcy2_gzdwjzw,zsjtcy2_lxdh,zsjtcy2_ysr,zsjtcy3_xm,zsjtcy3_gx,zsjtcy3_nl,zsjtcy3_gzdwjzw,zsjtcy3_lxdh,zsjtcy3_ysr,zsjtcy4_xm,zsjtcy4_gx,zsjtcy4_nl,zsjtcy4_gzdwjzw,zsjtcy4_lxdh,zsjtcy4_ysr,zsjtcy5_xm,zsjtcy5_gx,zsjtcy5_nl,zsjtcy5_gzdwjzw,zsjtcy5_lxdh,zsjtcy5_ysr,jtrks,jtjj_cz_qjnsr,jtjj_cz_rjysr,jtjj_nc_dnzsr,jtjj_nc_rjnsr,ddzdshshbz,zyshgx1_xm,zyshgx1_nl,zyshgx1_gx,zyshgx1_gzdwjzw,zyshgx1_ysr,zyshgx1_ynjtjjlxhgyqk,zyshgx2_xm,zyshgx2_nl,zyshgx2_gx,zyshgx2_gzdwjzw,zyshgx2_ysr,zyshgx2_ynjtjjlxhgyqk,zyshgx3_xm,zyshgx3_nl,zyshgx3_gx,zyshgx3_gzdwjzw,zyshgx3_ysr,zyshgx3_ynjtjjlxhgyqk,zyshgx4_xm,zyshgx4_nl,zyshgx4_gx,zyshgx4_gzdwjzw,zyshgx4_ysr,zyshgx4_ynjtjjlxhgyqk,jtjjknqkjyy,fqdw,mqdw,hscjgqgzx,tc,szqs,qsdh,jckkh,drxsgbqk,zxqjhschghzjxj,pkx,snjtsr,fqzy,mqzy,jtjjly,jtmytgshf,jzsfyqz,fmsfycj,fmsfjz,sqly,sqsj,fdysh,fdyshsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_csmz_knsxx where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.xh,a.nd,a.xzjtxxdz,a.yzbm,a.yxxxx,a.jg,a.jtdh,a.xm,a.xb,a.csrq,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.zsjtcy1_xm,a.zsjtcy1_gx,a.zsjtcy1_nl,a.zsjtcy1_gzdwjzw,a.zsjtcy1_lxdh,a.zsjtcy1_ysr,a.zsjtcy2_xm,a.zsjtcy2_gx,a.zsjtcy2_nl,a.zsjtcy2_gzdwjzw,a.zsjtcy2_lxdh,a.zsjtcy2_ysr,a.zsjtcy3_xm,a.zsjtcy3_gx,a.zsjtcy3_nl,a.zsjtcy3_gzdwjzw,a.zsjtcy3_lxdh,a.zsjtcy3_ysr,a.zsjtcy4_xm,a.zsjtcy4_gx,a.zsjtcy4_nl,a.zsjtcy4_gzdwjzw,a.zsjtcy4_lxdh,a.zsjtcy4_ysr,a.zsjtcy5_xm,a.zsjtcy5_gx,a.zsjtcy5_nl,a.zsjtcy5_gzdwjzw,a.zsjtcy5_lxdh,a.zsjtcy5_ysr,a.jtrks,a.jtjj_cz_qjnsr,a.jtjj_cz_rjysr,a.jtjj_nc_dnzsr,a.jtjj_nc_rjnsr,a.ddzdshshbz,a.zyshgx1_xm,a.zyshgx1_nl,a.zyshgx1_gx,a.zyshgx1_gzdwjzw,a.zyshgx1_ysr,a.zyshgx1_ynjtjjlxhgyqk,a.zyshgx2_xm,a.zyshgx2_nl,a.zyshgx2_gx,a.zyshgx2_gzdwjzw,a.zyshgx2_ysr,a.zyshgx2_ynjtjjlxhgyqk,a.zyshgx3_xm,a.zyshgx3_nl,a.zyshgx3_gx,a.zyshgx3_gzdwjzw,a.zyshgx3_ysr,a.zyshgx3_ynjtjjlxhgyqk,a.zyshgx4_xm,a.zyshgx4_nl,a.zyshgx4_gx,a.zyshgx4_gzdwjzw,a.zyshgx4_ysr,a.zyshgx4_ynjtjjlxhgyqk,a.jtjjknqkjyy,a.fqdw,a.mqdw,a.hscjgqgzx,a.tc,a.szqs,a.qsdh,a.jckkh,a.drxsgbqk,a.zxqjhschghzjxj,a.pkx,a.snjtsr,a.fqzy,a.mqzy,a.jtjjly,a.jtmytgshf,a.jzsfyqz,a.fmsfycj,a.fmsfjz,a.sqly,a.sqsj,a.fdysh,a.fdyshsj,a.xysh,a.xyshsj,a.xyshyj,a.xxsh,a.xxshsj,a.xxshyj,a.fdysh yesNo "
						+ "from view_csmz_knsxx a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "fdy");
			} else {
				sql = "select "
						+ pk
						+ " pk,a.xh,a.nd,a.xzjtxxdz,a.yzbm,a.yxxxx,a.jg,a.jtdh,a.xm,a.xb,a.csrq,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.zsjtcy1_xm,a.zsjtcy1_gx,a.zsjtcy1_nl,a.zsjtcy1_gzdwjzw,a.zsjtcy1_lxdh,a.zsjtcy1_ysr,a.zsjtcy2_xm,a.zsjtcy2_gx,a.zsjtcy2_nl,a.zsjtcy2_gzdwjzw,a.zsjtcy2_lxdh,a.zsjtcy2_ysr,a.zsjtcy3_xm,a.zsjtcy3_gx,a.zsjtcy3_nl,a.zsjtcy3_gzdwjzw,a.zsjtcy3_lxdh,a.zsjtcy3_ysr,a.zsjtcy4_xm,a.zsjtcy4_gx,a.zsjtcy4_nl,a.zsjtcy4_gzdwjzw,a.zsjtcy4_lxdh,a.zsjtcy4_ysr,a.zsjtcy5_xm,a.zsjtcy5_gx,a.zsjtcy5_nl,a.zsjtcy5_gzdwjzw,a.zsjtcy5_lxdh,a.zsjtcy5_ysr,a.jtrks,a.jtjj_cz_qjnsr,a.jtjj_cz_rjysr,a.jtjj_nc_dnzsr,a.jtjj_nc_rjnsr,a.ddzdshshbz,a.zyshgx1_xm,a.zyshgx1_nl,a.zyshgx1_gx,a.zyshgx1_gzdwjzw,a.zyshgx1_ysr,a.zyshgx1_ynjtjjlxhgyqk,a.zyshgx2_xm,a.zyshgx2_nl,a.zyshgx2_gx,a.zyshgx2_gzdwjzw,a.zyshgx2_ysr,a.zyshgx2_ynjtjjlxhgyqk,a.zyshgx3_xm,a.zyshgx3_nl,a.zyshgx3_gx,a.zyshgx3_gzdwjzw,a.zyshgx3_ysr,a.zyshgx3_ynjtjjlxhgyqk,a.zyshgx4_xm,a.zyshgx4_nl,a.zyshgx4_gx,a.zyshgx4_gzdwjzw,a.zyshgx4_ysr,a.zyshgx4_ynjtjjlxhgyqk,a.jtjjknqkjyy,a.fqdw,a.mqdw,a.hscjgqgzx,a.tc,a.szqs,a.qsdh,a.jckkh,a.drxsgbqk,a.zxqjhschghzjxj,a.pkx,a.snjtsr,a.fqzy,a.mqzy,a.jtjjly,a.jtmytgshf,a.jzsfyqz,a.fmsfycj,a.fmsfjz,a.sqly,a.sqsj,a.fdysh,a.fdyshsj,a.xysh,a.xyshsj,a.xyshyj,a.xxsh,a.xxshsj,a.xxshyj,a.xysh yesNo "
						+ "from view_csmz_knsxx a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "xy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.xh,a.nd,a.xzjtxxdz,a.yzbm,a.yxxxx,a.jg,a.jtdh,a.xm,a.xb,a.csrq,a.rxny,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.zsjtcy1_xm,a.zsjtcy1_gx,a.zsjtcy1_nl,a.zsjtcy1_gzdwjzw,a.zsjtcy1_lxdh,a.zsjtcy1_ysr,a.zsjtcy2_xm,a.zsjtcy2_gx,a.zsjtcy2_nl,a.zsjtcy2_gzdwjzw,a.zsjtcy2_lxdh,a.zsjtcy2_ysr,a.zsjtcy3_xm,a.zsjtcy3_gx,a.zsjtcy3_nl,a.zsjtcy3_gzdwjzw,a.zsjtcy3_lxdh,a.zsjtcy3_ysr,a.zsjtcy4_xm,a.zsjtcy4_gx,a.zsjtcy4_nl,a.zsjtcy4_gzdwjzw,a.zsjtcy4_lxdh,a.zsjtcy4_ysr,a.zsjtcy5_xm,a.zsjtcy5_gx,a.zsjtcy5_nl,a.zsjtcy5_gzdwjzw,a.zsjtcy5_lxdh,a.zsjtcy5_ysr,a.jtrks,a.jtjj_cz_qjnsr,a.jtjj_cz_rjysr,a.jtjj_nc_dnzsr,a.jtjj_nc_rjnsr,a.ddzdshshbz,a.zyshgx1_xm,a.zyshgx1_nl,a.zyshgx1_gx,a.zyshgx1_gzdwjzw,a.zyshgx1_ysr,a.zyshgx1_ynjtjjlxhgyqk,a.zyshgx2_xm,a.zyshgx2_nl,a.zyshgx2_gx,a.zyshgx2_gzdwjzw,a.zyshgx2_ysr,a.zyshgx2_ynjtjjlxhgyqk,a.zyshgx3_xm,a.zyshgx3_nl,a.zyshgx3_gx,a.zyshgx3_gzdwjzw,a.zyshgx3_ysr,a.zyshgx3_ynjtjjlxhgyqk,a.zyshgx4_xm,a.zyshgx4_nl,a.zyshgx4_gx,a.zyshgx4_gzdwjzw,a.zyshgx4_ysr,a.zyshgx4_ynjtjjlxhgyqk,a.jtjjknqkjyy,a.fqdw,a.mqdw,a.hscjgqgzx,a.tc,a.szqs,a.qsdh,a.jckkh,a.drxsgbqk,a.zxqjhschghzjxj,a.pkx,a.snjtsr,a.fqzy,a.mqzy,a.jtjjly,a.jtmytgshf,a.jzsfyqz,a.fmsfycj,a.fmsfjz,a.sqly,a.sqsj,a.fdysh,a.fdyshsj,a.xysh,a.xyshsj,a.xyshyj,a.xxsh,a.xxshsj,a.xxshyj,a.xxsh yesNo "
				+ "from view_csmz_knsxx a where " + pk + "='" + pkVal + "'";
			request.setAttribute("user", "xx");
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
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(15));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_csmz_knsxx");
		request.setAttribute("act", "zzsh");
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
		sql = "select * from view_csmz_knsxx " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_csmz_knsxx where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_csmz_knsxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("knsshExp");
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

		String doType = request.getParameter("doType");// 操作类型
		String titName = request.getParameter("titName");

		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select nd,xh,xm,xb,sfzh,csrq,rxny,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,dzyxhqqh,lb,xsdh,szss,jtfk,jtfkyb,jtxxxzz,jtxxxzzyb,fmhjhr1_xm,fmhjhr1_cw,fmhjhr1_sfzh,fmhjhr1_gddh,fmhjhr1_sjhm,fmhjhr2_xm,fmhjhr2_cw,fmhjhr2_sfzh,fmhjhr2_gddh,fmhjhr2_sjhm,sqje,sqjedx,sqdknx,sqsj,fdysh,fdyshsj,fdydh,xyshsj,xydh,xxsh,xxshsj,xxdh,xysh from view_csmz_gjzxdk where 1=2";
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

			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学贷款' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String dzyxhqqh = Base.chgNull(request
							.getParameter("dzyxhqqh"), "", 1);
					String lb = Base.chgNull(request.getParameter("lb"), "", 1);
					String xsdh = Base.chgNull(request.getParameter("xsdh"),
							"", 1);
					String szss = Base.chgNull(request.getParameter("szss"),
							"", 1);
					String jtfk = Base.chgNull(request.getParameter("jtfk"),
							"", 1);
					String jtfkyb = Base.chgNull(
							request.getParameter("jtfkyb"), "", 1);
					String jtxxxzz = Base.chgNull(request
							.getParameter("jtxxxzz"), "", 1);
					String jtxxxzzyb = Base.chgNull(request
							.getParameter("jtxxxzzyb"), "", 1);
					String fmhjhr1_xm = Base.chgNull(request
							.getParameter("fmhjhr1_xm"), "", 1);
					String fmhjhr1_cw = Base.chgNull(request
							.getParameter("fmhjhr1_cw"), "", 1);
					String fmhjhr1_sfzh = Base.chgNull(request
							.getParameter("fmhjhr1_sfzh"), "", 1);
					String fmhjhr1_gddh = Base.chgNull(request
							.getParameter("fmhjhr1_gddh"), "", 1);
					String fmhjhr1_sjhm = Base.chgNull(request
							.getParameter("fmhjhr1_sjhm"), "", 1);
					String fmhjhr2_xm = Base.chgNull(request
							.getParameter("fmhjhr2_xm"), "", 1);
					String fmhjhr2_cw = Base.chgNull(request
							.getParameter("fmhjhr2_cw"), "", 1);
					String fmhjhr2_sfzh = Base.chgNull(request
							.getParameter("fmhjhr2_sfzh"), "", 1);
					String fmhjhr2_gddh = Base.chgNull(request
							.getParameter("fmhjhr2_gddh"), "", 1);
					String fmhjhr2_sjhm = Base.chgNull(request
							.getParameter("fmhjhr2_sjhm"), "", 1);
					String sqje = Base.chgNull(request.getParameter("sqje"),
							"", 1);
					String sqdknx = Base.chgNull(
							request.getParameter("sqdknx"), "", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = nd + xh;
					}
					if(null == sqje || "".equalsIgnoreCase(sqje)){
						sqje = "0";
					}
					String sqlT = "{call pro_Disp_dxje(?,?)}";
					String[] jedxT = dao.getProVal(sqlT,
							new String[] { sqje }, new int[] { 2 });
					String sqjedx = jedxT[0];
					sql = "select xxsh from csmz_gjzxdk where nd||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("通过"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("csmz_gjzxdk", "nd||xh", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "nd", "xh",
								"dzyxhqqh", "lb", "xsdh", "szss", "jtfk",
								"jtfkyb", "jtxxxzz", "jtxxxzzyb", "fmhjhr1_xm",
								"fmhjhr1_cw", "fmhjhr1_sfzh", "fmhjhr1_gddh",
								"fmhjhr1_sjhm", "fmhjhr2_xm", "fmhjhr2_cw",
								"fmhjhr2_sfzh", "fmhjhr2_gddh", "fmhjhr2_sjhm",
								"sqje", "sqdknx", "sqjedx" };

						valueForOut = new String[] { nd, xh, dzyxhqqh, lb,
								xsdh, szss, jtfk, jtfkyb, jtxxxzz, jtxxxzzyb,
								fmhjhr1_xm, fmhjhr1_cw, fmhjhr1_sfzh,
								fmhjhr1_gddh, fmhjhr1_sjhm, fmhjhr2_xm,
								fmhjhr2_cw, fmhjhr2_sfzh, fmhjhr2_gddh,
								fmhjhr2_sjhm, sqje, sqdknx, sqjedx };

						boolean ok = false;
						ok = StandardOperation.insert("csmz_gjzxdk", colName1,
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
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String dzyxhqqh = Base.chgNull(request
						.getParameter("dzyxhqqh"), "", 1);
				String lb = Base.chgNull(request.getParameter("lb"), "", 1);
				String xsdh = Base.chgNull(request.getParameter("xsdh"),
						"", 1);
				String szss = Base.chgNull(request.getParameter("szss"),
						"", 1);
				String jtfk = Base.chgNull(request.getParameter("jtfk"),
						"", 1);
				String jtfkyb = Base.chgNull(
						request.getParameter("jtfkyb"), "", 1);
				String jtxxxzz = Base.chgNull(request
						.getParameter("jtxxxzz"), "", 1);
				String jtxxxzzyb = Base.chgNull(request
						.getParameter("jtxxxzzyb"), "", 1);
				String fmhjhr1_xm = Base.chgNull(request
						.getParameter("fmhjhr1_xm"), "", 1);
				String fmhjhr1_cw = Base.chgNull(request
						.getParameter("fmhjhr1_cw"), "", 1);
				String fmhjhr1_sfzh = Base.chgNull(request
						.getParameter("fmhjhr1_sfzh"), "", 1);
				String fmhjhr1_gddh = Base.chgNull(request
						.getParameter("fmhjhr1_gddh"), "", 1);
				String fmhjhr1_sjhm = Base.chgNull(request
						.getParameter("fmhjhr1_sjhm"), "", 1);
				String fmhjhr2_xm = Base.chgNull(request
						.getParameter("fmhjhr2_xm"), "", 1);
				String fmhjhr2_cw = Base.chgNull(request
						.getParameter("fmhjhr2_cw"), "", 1);
				String fmhjhr2_sfzh = Base.chgNull(request
						.getParameter("fmhjhr2_sfzh"), "", 1);
				String fmhjhr2_gddh = Base.chgNull(request
						.getParameter("fmhjhr2_gddh"), "", 1);
				String fmhjhr2_sjhm = Base.chgNull(request
						.getParameter("fmhjhr2_sjhm"), "", 1);
				String sqje = Base.chgNull(request.getParameter("sqje"),
						"", 1);
				String sqdknx = Base.chgNull(
						request.getParameter("sqdknx"), "", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = nd + xh;
				}
				if(null == sqje || "".equalsIgnoreCase(sqje)){
					sqje = "0";
				}
				String sqlT = "{call pro_Disp_dxje(?,?)}";
				String[] jedxT = dao.getProVal(sqlT,
						new String[] { sqje }, new int[] { 2 });
				String sqjedx = jedxT[0];
				sql = "select xxsh from csmz_gjzxdk where nd||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("csmz_gjzxdk", "nd||xh", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "nd", "xh",
							"dzyxhqqh", "lb", "xsdh", "szss", "jtfk",
							"jtfkyb", "jtxxxzz", "jtxxxzzyb", "fmhjhr1_xm",
							"fmhjhr1_cw", "fmhjhr1_sfzh", "fmhjhr1_gddh",
							"fmhjhr1_sjhm", "fmhjhr2_xm", "fmhjhr2_cw",
							"fmhjhr2_sfzh", "fmhjhr2_gddh", "fmhjhr2_sjhm",
							"sqje", "sqdknx", "sqjedx" };

					valueForOut = new String[] { nd, xh, dzyxhqqh, lb,
							xsdh, szss, jtfk, jtfkyb, jtxxxzz, jtxxxzzyb,
							fmhjhr1_xm, fmhjhr1_cw, fmhjhr1_sfzh,
							fmhjhr1_gddh, fmhjhr1_sjhm, fmhjhr2_xm,
							fmhjhr2_cw, fmhjhr2_sfzh, fmhjhr2_gddh,
							fmhjhr2_sjhm, sqje, sqdknx, sqjedx };

					boolean ok = false;
					ok = StandardOperation.insert("csmz_gjzxdk", colName1,
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
			pkVal = nd + xh;
		}

		sql = "select nd,xh,xm,xb,sfzh,csrq,rxny,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,dzyxhqqh,lb,xsdh,szss,jtfk,jtfkyb,jtxxxzz,jtxxxzzyb,fmhjhr1_xm,fmhjhr1_cw,fmhjhr1_sfzh,fmhjhr1_gddh,fmhjhr1_sjhm,fmhjhr2_xm,fmhjhr2_cw,fmhjhr2_sfzh,fmhjhr2_gddh,fmhjhr2_sjhm,sqje,sqjedx,sqdknx,sqsj,fdysh,fdyshsj,fdydh,xyshsj,xydh,xxsh,xxshsj,xxdh,xysh,(xz-((select to_char(sysdate,'yyyy') from dual)-nj+1)+3) zddknx from view_csmz_gjzxdk where nd||xh=?";
		outString = new String[] { "nd", "xh", "xm", "xb", "sfzh", "csrq",
				"rxny", "xz", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"nj", "dzyxhqqh", "lb", "xsdh", "szss", "jtfk", "jtfkyb",
				"jtxxxzz", "jtxxxzzyb", "fmhjhr1_xm", "fmhjhr1_cw",
				"fmhjhr1_sfzh", "fmhjhr1_gddh", "fmhjhr1_sjhm", "fmhjhr2_xm",
				"fmhjhr2_cw", "fmhjhr2_sfzh", "fmhjhr2_gddh", "fmhjhr2_sjhm",
				"sqje", "sqjedx", "sqdknx", "sqsj", "fdysh", "fdyshsj",
				"fdydh", "xyshsj", "xydh", "xxsh", "xxshsj", "xxdh", "xysh", "zddknx" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh && !"".equalsIgnoreCase(xh)) {
				boolean bk = dao.isKns(xh);
				if (bk) {
					sql = "select b.xh,b.xm,b.xb,b.csrq,b.mzmc,b.sfzh,b.zzmmmc,b.nj,b.xz,"
							+ "b.xymc,b.zymc,b.bjmc,(select a.rxny from bks_xsjbxx a where a.xh=b.xh) rxny,(b.xz-((select to_char(sysdate,'yyyy') from dual)-b.nj+1)+3) zddknx,(b.xz-((select to_char(sysdate,'yyyy') from dual)-b.nj+1)+3) zxdknx from view_stu_details b where b.xh=?";
					String[] colName = new String[] { "xh", "xm", "xb", "csrq",
							"mzmc", "sfzh", "zzmmmc", "nj", "xz", "zzmmmc",
							"xymc", "zymc", "bjmc", "rxny", "zddknx", "zxdknx" };
					String[] outVal = dao.getOneRs(sql, new String[] { xh },
							colName);
					if (outVal == null) {
					} else {
						colName = new String[] { "xh", "xm", "xb", "csrq",
								"mzmc", "sfzh", "zzmmmc", "nj", "xz", "zzmmmc",
								"xymc", "zymc", "bjmc", "rxny", "zddknx",
								"zxdknx" };
						for (int i = 0; i < colName.length; i++) {
							if (null != outVal[i]) {
								map.put(colName[i], outVal[i]);
							} else {
								map.put(colName[i], "");
							}
						}
					}
				} else {
					request.setAttribute("kns", "no");
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
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String xz = Base.chgNull(request.getParameter("xz"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String dzyxhqqh = Base.chgNull(request.getParameter("dzyxhqqh"), "", 1);
		String lb = Base.chgNull(request.getParameter("lb"), "", 1);
		String xsdh = Base.chgNull(request.getParameter("xsdh"), "", 1);
		String szss = Base.chgNull(request.getParameter("szss"), "", 1);
		String jtfk = Base.chgNull(request.getParameter("jtfk"), "", 1);
		String jtfkyb = Base.chgNull(request.getParameter("jtfkyb"), "", 1);
		String jtxxxzz = Base.chgNull(request.getParameter("jtxxxzz"), "", 1);
		String jtxxxzzyb = Base.chgNull(request.getParameter("jtxxxzzyb"), "",
				1);
		String fmhjhr1_xm = Base.chgNull(request.getParameter("fmhjhr1_xm"),
				"", 1);
		String fmhjhr1_cw = Base.chgNull(request.getParameter("fmhjhr1_cw"),
				"", 1);
		String fmhjhr1_sfzh = Base.chgNull(
				request.getParameter("fmhjhr1_sfzh"), "", 1);
		String fmhjhr1_gddh = Base.chgNull(
				request.getParameter("fmhjhr1_gddh"), "", 1);
		String fmhjhr1_sjhm = Base.chgNull(
				request.getParameter("fmhjhr1_sjhm"), "", 1);
		String fmhjhr2_xm = Base.chgNull(request.getParameter("fmhjhr2_xm"),
				"", 1);
		String fmhjhr2_cw = Base.chgNull(request.getParameter("fmhjhr2_cw"),
				"", 1);
		String fmhjhr2_sfzh = Base.chgNull(
				request.getParameter("fmhjhr2_sfzh"), "", 1);
		String fmhjhr2_gddh = Base.chgNull(
				request.getParameter("fmhjhr2_gddh"), "", 1);
		String fmhjhr2_sjhm = Base.chgNull(
				request.getParameter("fmhjhr2_sjhm"), "", 1);
		String sqje = Base.chgNull(request.getParameter("sqje"), "", 1);
		String sqjedx = Base.chgNull(request.getParameter("sqjedx"), "", 1);
		String sqdknx = Base.chgNull(request.getParameter("sqdknx"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String fdysh = Base.chgNull(request.getParameter("fdysh"), "", 1);
		String fdyshsj = Base.chgNull(request.getParameter("fdyshsj"), "", 1);
		String fdydh = Base.chgNull(request.getParameter("fdydh"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xydh = Base.chgNull(request.getParameter("xydh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String xxdh = Base.chgNull(request.getParameter("xxdh"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		
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

		String[] ywhddk = {"0","0","0","0","0","0"};
		ArrayList<String[]> tjlList = dao
				.rsToVator3(
						"select (nd-nj+1) dj,sqje from view_csmz_gjzxdk where xh=? and xxsh='通过' order by nd",
						new String[] { xh }, new String[] { "dj", "sqje" });
		if(tjlList != null){
			for (String[] stjl: tjlList){
				ywhddk[Integer.parseInt(stjl[0])-1] = stjl[1];
			}
		}
		
		String ywsjje1 = ywhddk[0];
		String ywsjje2 = ywhddk[1];
		String ywsjje3 = ywhddk[2];
		String ywsjje4 = ywhddk[3];
		String ywsjje5 = ywhddk[4];
		String ywsjje6 = ywhddk[5];
		
		String[] outValue = new String[] { nd, xh, xm, xb, sfzh, csrq, rxny,
				xz, xymc, zymc, bjmc, dzyxhqqh, lb, xsdh, szss, jtfk, jtfkyb,
				jtxxxzz, jtxxxzzyb, fmhjhr1_xm, fmhjhr1_cw, fmhjhr1_sfzh,
				fmhjhr1_gddh, fmhjhr1_sjhm, fmhjhr2_xm, fmhjhr2_cw,
				fmhjhr2_sfzh, fmhjhr2_gddh, fmhjhr2_sjhm, sqje, sqjedx, sqdknx,
				sqsj, fdysh, fdyshsj, fdydh, xyshsj, xydh, xxsh, xxshsj, xxdh,
				xysh, ywsjje1, ywsjje2, ywsjje3, ywsjje4, ywsjje5, ywsjje6 };
		String[] outString = new String[] { "nd", "xh", "xm", "xb", "sfzh",
				"csrq", "rxny", "xz", "xymc", "zymc", "bjmc", "dzyxhqqh", "lb",
				"xsdh", "szss", "jtfk", "jtfkyb", "jtxxxzz", "jtxxxzzyb",
				"fmhjhr1_xm", "fmhjhr1_cw", "fmhjhr1_sfzh", "fmhjhr1_gddh",
				"fmhjhr1_sjhm", "fmhjhr2_xm", "fmhjhr2_cw", "fmhjhr2_sfzh",
				"fmhjhr2_gddh", "fmhjhr2_sjhm", "sqje", "sqjedx", "sqdknx",
				"sqsj", "fdysh", "fdyshsj", "fdydh", "xyshsj", "xydh", "xxsh",
				"xxshsj", "xxdh", "xysh", "ywsjje1", "ywsjje2", "ywsjje3",
				"ywsjje4", "ywsjje5", "ywsjje6" };
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "csmz_gjzxdk";
		pk = "nd||xh";
		tableName = "view_csmz_gjzxdk";
		
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
		tips = "当前所在位置：学生资助 - 审核 - 国家助学贷款项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学贷款";
			colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "sqje", "sqdknx",
					"fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.sqje,a.sqdknx,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.sqje,a.sqdknx,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
						"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sqje", "sqdknx", "fdysh",
						"xysh", "" };
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sqje", "sqdknx", "" };
				} else {
					colList = new String[] { "bgcolor", "主键", "nd", "xh", "xm",
							"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "sqje", "sqdknx",
							"fdysh", "" };
				}
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sqje,a.sqdknx,a.fdysh from "
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
						+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sqje,a.sqdknx,a.fdysh from "
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
							+ " 主键,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sqje,a.sqdknx,a.fdysh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh desc) a";
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
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
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
		String lslxdh = Base.chgNull(request.getParameter("lslxdh"), "", 1);
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
						sqlT[i] = "delete csmz_gjzxdk where nd||xh='"+pkT+"' and xysh<>'通过'";
					} else {
						sqlT[i] = "delete csmz_gjzxdk where nd||xh='"+pkT+"' and xxsh<>'通过'";
					}
				} else {
					sqlT[i] = "delete csmz_gjzxdk where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxdksh&go=go", false);
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
						sqlT[i] = "update csmz_gjzxdk set fdysh='通过',fdyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
					} else {
						sqlT[i] = "update csmz_gjzxdk set xysh='通过',xyshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
					}
				} else {
					sqlT[i] = "update csmz_gjzxdk set xxsh='通过',xxshsj='"
						+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxdksh&go=go", false);
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
						String xyshT = dao.getOneRs(
								"select xysh from csmz_gjzxdk where nd||xh=?",
								new String[] { pkT }, "xysh");
						if (!"通过".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update csmz_gjzxdk set fdysh='不通过',fdyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
						}
					} else {
						String xxshT = dao.getOneRs(
								"select xxsh from csmz_gjzxdk where nd||xh=?",
								new String[] { pkT }, "xxsh");
						if (!"通过".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update csmz_gjzxdk set xysh='不通过',xyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
						}
					}
				} else {
					sqlT[i] = "update csmz_gjzxdk set xxsh='不通过',xxshsj='"
						+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao.getOneRs(
							"select xysh from csmz_gjzxdk where nd||xh=?",
							new String[] { pkVal }, "xysh");
					if ("通过".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjzxdk", new String[] {
								"fdysh", "fdyshsj", "fdydh" },
								new String[] { yesNo, now, lslxdh }, "nd||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao.getOneRs(
							"select xxsh from csmz_gjzxdk where nd||xh=?",
							new String[] { pkVal }, "xxsh");
					if ("通过".equalsIgnoreCase(xxshT)) {
						request.setAttribute("xxshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjzxdk", new String[] {
								"xysh", "xydh", "xyshsj" }, new String[] {
								yesNo, lslxdh, now }, "nd||xh", pkVal, request);
					}
				}
			} else {
				StandardOperation.update("csmz_gjzxdk", new String[] {
						"xxsh", "xxdh", "xxshsj" }, new String[] {
						yesNo, lslxdh, now }, "nd||xh", pkVal,
						request);
			}
		}
		realTable = "csmz_gjzxdk";
		pk = "nd||xh";
		sql = "select nd,xh,xm,xb,sfzh,csrq,rxny,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,dzyxhqqh,lb,xsdh,szss,jtfk,jtfkyb,jtxxxzz,jtxxxzzyb,fmhjhr1_xm,fmhjhr1_cw,fmhjhr1_sfzh,fmhjhr1_gddh,fmhjhr1_sjhm,fmhjhr2_xm,fmhjhr2_cw,fmhjhr2_sfzh,fmhjhr2_gddh,fmhjhr2_sjhm,sqje,sqjedx,sqdknx,sqsj,fdysh,fdyshsj,fdydh,xyshsj,xydh,xxsh,xxshsj,xxdh,xysh from view_csmz_gjzxdk where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.nd,a.xh,a.xm,a.xb,a.sfzh,a.csrq,a.rxny,a.xz,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.dzyxhqqh,a.lb,a.xsdh,a.szss,a.jtfk,a.jtfkyb,a.jtxxxzz,a.jtxxxzzyb,a.fmhjhr1_xm,a.fmhjhr1_cw,a.fmhjhr1_sfzh,a.fmhjhr1_gddh,a.fmhjhr1_sjhm,a.fmhjhr2_xm,a.fmhjhr2_cw,a.fmhjhr2_sfzh,a.fmhjhr2_gddh,a.fmhjhr2_sjhm,a.sqje,a.sqjedx,a.sqdknx,a.sqsj,a.fdysh,a.fdyshsj,a.fdydh,a.xyshsj,a.xydh,a.xxsh,a.xxshsj,a.xxdh,a.xysh,a.fdysh yesNo,a.fdydh lslxdh "
						+ "from view_csmz_gjzxdk a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "fdy");
			} else {
				sql = "select "
						+ pk
						+ " pk,a.nd,a.xh,a.xm,a.xb,a.sfzh,a.csrq,a.rxny,a.xz,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.dzyxhqqh,a.lb,a.xsdh,a.szss,a.jtfk,a.jtfkyb,a.jtxxxzz,a.jtxxxzzyb,a.fmhjhr1_xm,a.fmhjhr1_cw,a.fmhjhr1_sfzh,a.fmhjhr1_gddh,a.fmhjhr1_sjhm,a.fmhjhr2_xm,a.fmhjhr2_cw,a.fmhjhr2_sfzh,a.fmhjhr2_gddh,a.fmhjhr2_sjhm,a.sqje,a.sqjedx,a.sqdknx,a.sqsj,a.fdysh,a.fdyshsj,a.fdydh,a.xyshsj,a.xydh,a.xxsh,a.xxshsj,a.xxdh,a.xysh,a.xysh yesNo,a.xydh lslxdh "
						+ "from view_csmz_gjzxdk a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "xy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.xh,a.xm,a.xb,a.sfzh,a.csrq,a.rxny,a.xz,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.dzyxhqqh,a.lb,a.xsdh,a.szss,a.jtfk,a.jtfkyb,a.jtxxxzz,a.jtxxxzzyb,a.fmhjhr1_xm,a.fmhjhr1_cw,a.fmhjhr1_sfzh,a.fmhjhr1_gddh,a.fmhjhr1_sjhm,a.fmhjhr2_xm,a.fmhjhr2_cw,a.fmhjhr2_sfzh,a.fmhjhr2_gddh,a.fmhjhr2_sjhm,a.sqje,a.sqjedx,a.sqdknx,a.sqsj,a.fdysh,a.fdyshsj,a.fdydh,a.xyshsj,a.xydh,a.xxsh,a.xxshsj,a.xxdh,a.xysh,a.xxsh yesNo,a.xxdh lslxdh "
				+ "from view_csmz_gjzxdk a where " + pk + "='" + pkVal + "'";
			request.setAttribute("user", "xx");
		}
		colList = new String[(outString.length+3)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		colList[i+2] = "lslxdh";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		String xh = "";
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if("xh".equalsIgnoreCase(colList[i].toLowerCase())){
				xh = rs[i];
			}
			hs.put(colList[i].toLowerCase(), rs[i]);
		}

		String[] ywhddk = {"0","0","0","0","0","0"};
		ArrayList<String[]> tjlList = dao
				.rsToVator3(
						"select (nd-nj+1) dj,sqje from view_csmz_gjzxdk where xh=? and xxsh='通过' order by nd",
						new String[] { xh }, new String[] { "dj", "sqje" });
		if(tjlList != null){
			for (String[] stjl: tjlList){
				ywhddk[Integer.parseInt(stjl[0])-1] = stjl[1];
			}
		}
		
		hs.put("ywsjje1", ywhddk[0]);
		hs.put("ywsjje2", ywhddk[1]);
		hs.put("ywsjje3", ywhddk[2]);
		hs.put("ywsjje4", ywhddk[3]);
		hs.put("ywsjje5", ywhddk[4]);
		hs.put("ywsjje6", ywhddk[5]);
		
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_csmz_gjzxdk");
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
		sql = "select * from view_csmz_gjzxdk " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_csmz_gjzxdk where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_csmz_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjzxdkshExp");
	}

	/**
	 * @describe 国家奖学金申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

		// String userNameReal;

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

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,zzmmmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,gxnbxkcs,yxkc,lhkc,cjpm,zhkpcj,zhkppm,zyjx,yjjx,xjjx,sjjx,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjjxj where 1=2";
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

			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家奖学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String gxnbxkcs = Base.chgNull(request
							.getParameter("gxnbxkcs"), "", 1);
					String yxkc = Base.chgNull(request.getParameter("yxkc"),
							"", 1);
					String lhkc = Base.chgNull(request.getParameter("lhkc"),
							"", 1);
					String cjpm = Base.chgNull(request.getParameter("cjpm"),
							"", 1);
					String zhkpcj = Base.chgNull(
							request.getParameter("zhkpcj"), "", 1);
					String zhkppm = Base.chgNull(
							request.getParameter("zhkppm"), "", 1);
					String zyjx = Base.chgNull(request.getParameter("zyjx"),
							"", 1);
					String yjjx = Base.chgNull(request.getParameter("yjjx"),
							"", 1);
					String xjjx = Base.chgNull(request.getParameter("xjjx"),
							"", 1);
					String sjjx = Base.chgNull(request.getParameter("sjjx"),
							"", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xn + xh;
					}
					sql = "select xxsh from csmz_gjjxj where xn||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("通过"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("csmz_gjjxj", "xn||xh", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh", "lxdh",
								"gxnbxkcs", "yxkc", "lhkc", "cjpm", "zhkpcj",
								"zhkppm", "zyjx", "yjjx", "xjjx", "sjjx",
								"sqly" };

						valueForOut = new String[] { xn, xh, lxdh, gxnbxkcs,
								yxkc, lhkc, cjpm, zhkpcj, zhkppm, zyjx, yjjx,
								xjjx, sjjx, sqly };

						boolean ok = false;
						ok = StandardOperation.insert("csmz_gjjxj", colName1,
								valueForOut, request);
						if (ok) {
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
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String lxdh = Base.chgNull(request.getParameter("lxdh"),
						"", 1);
				String gxnbxkcs = Base.chgNull(request
						.getParameter("gxnbxkcs"), "", 1);
				String yxkc = Base.chgNull(request.getParameter("yxkc"),
						"", 1);
				String lhkc = Base.chgNull(request.getParameter("lhkc"),
						"", 1);
				String cjpm = Base.chgNull(request.getParameter("cjpm"),
						"", 1);
				String zhkpcj = Base.chgNull(
						request.getParameter("zhkpcj"), "", 1);
				String zhkppm = Base.chgNull(
						request.getParameter("zhkppm"), "", 1);
				String zyjx = Base.chgNull(request.getParameter("zyjx"),
						"", 1);
				String yjjx = Base.chgNull(request.getParameter("yjjx"),
						"", 1);
				String xjjx = Base.chgNull(request.getParameter("xjjx"),
						"", 1);
				String sjjx = Base.chgNull(request.getParameter("sjjx"),
						"", 1);
				String sqly = Base.chgNull(request.getParameter("sqly"),
						"", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xn + xh;
				}
				sql = "select xxsh from csmz_gjjxj where xn||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("csmz_gjjxj", "xn||xh", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xn", "xh", "lxdh",
							"gxnbxkcs", "yxkc", "lhkc", "cjpm", "zhkpcj",
							"zhkppm", "zyjx", "yjjx", "xjjx", "sjjx",
							"sqly" };

					valueForOut = new String[] { xn, xh, lxdh, gxnbxkcs,
							yxkc, lhkc, cjpm, zhkpcj, zhkppm, zyjx, yjjx,
							xjjx, sjjx, sqly };

					boolean ok = false;
					ok = StandardOperation.insert("csmz_gjjxj", colName1,
							valueForOut, request);
					if (ok) {
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
			pkVal = xn + xh;
		}

		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,zzmmmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,gxnbxkcs,yxkc,lhkc,cjpm,zhkpcj,zhkppm,zyjx,yjjx,xjjx,sjjx,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjjxj where xn||xh=?";
		outString = new String[] { "xn", "xh", "xm", "xb", "csny", "rxny",
				"mzmc", "zzmmmc", "sfzh", "nj", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "lxdh", "gxnbxkcs", "yxkc", "lhkc", "cjpm",
				"zhkpcj", "zhkppm", "zyjx", "yjjx", "xjjx", "sjjx", "sqly",
				"sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh) {
				sql = "select b.xh,b.xm,b.xb,b.csrq csny,b.mzmc,b.sfzh,b.zzmmmc,b.nj,b.xz,"
						+ "b.xymc,b.zymc,b.bjmc,(select a.rxny from bks_xsjbxx a where a.xh=b.xh) rxny from view_stu_details b where b.xh=?";
				String[] colName = new String[] { "xh", "xm", "xb", "csny",
						"mzmc", "sfzh", "zzmmmc", "nj", "xz", "zzmmmc", "xymc",
						"zymc", "bjmc", "rxny" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "csny", "mzmc",
							"sfzh", "zzmmmc", "nj", "xz", "zzmmmc", "xymc",
							"zymc", "bjmc", "rxny" };
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
		return mapping.findForward("gjjxjsq");
	}
	
	/**
	 * @describe 打印国家奖学金申请表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = Base.chgNull(request.getParameter("xn"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String csny = Base.chgNull(request.getParameter("csny"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String lxdh = Base.chgNull(request.getParameter("lxdh"), "", 1);
		String gxnbxkcs = Base.chgNull(request.getParameter("gxnbxkcs"), "", 1);
		String yxkc = Base.chgNull(request.getParameter("yxkc"), "", 1);
		String lhkc = Base.chgNull(request.getParameter("lhkc"), "", 1);
		String cjpm = Base.chgNull(request.getParameter("cjpm"), "", 1);
		String zhkpcj = Base.chgNull(request.getParameter("zhkpcj"), "", 1);
		String zhkppm = Base.chgNull(request.getParameter("zhkppm"), "", 1);
		String zyjx = Base.chgNull(request.getParameter("zyjx"), "", 1);
		String yjjx = Base.chgNull(request.getParameter("yjjx"), "", 1);
		String xjjx = Base.chgNull(request.getParameter("xjjx"), "", 1);
		String sjjx = Base.chgNull(request.getParameter("sjjx"), "", 1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		
		for (int i = 0; i < sfzh.length(); i++){
			map.put("sfzh"+(i+1),sfzh.substring(i, i+1));
		}
		
		String[] outValue = new String[] { xn, xh, xm, xb, csny, rxny, mzmc,
				zzmmmc, sfzh, nj, xymc, zymc, bjmc, lxdh, gxnbxkcs, yxkc, lhkc,
				cjpm, zhkpcj, zhkppm, zyjx, yjjx, xjjx, sjjx, sqly };
		String[] outString = new String[] { "xn", "xh", "xm", "xb", "csny",
				"rxny", "mzmc", "zzmmmc", "sfzh", "nj", "xymc", "zymc", "bjmc",
				"lxdh", "gxnbxkcs", "yxkc", "lhkc", "cjpm", "zhkpcj", "zhkppm",
				"zyjx", "yjjx", "xjjx", "sjjx", "sqly" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("gjjxjsqb");
	}

	/**
	 * @describe 国家奖学金审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjjxjsh(ActionMapping mapping, ActionForm form,
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "csmz_gjjxj";
		pk = "xn||xh";
		tableName = "view_csmz_gjjxj";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 国家奖学金项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家奖学金";
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
					"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh",
					"xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc) a";
					request.setAttribute("user", "fdy");
				} else {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh desc) a";
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
		request.setAttribute("act", "gjjxjsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("gjjxjsh");
	}
	
	/**
	 * @describe 国家奖学金审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjjxjshXxxx(ActionMapping mapping, ActionForm form,
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
		String isFdy = session.getAttribute("isFdy").toString();

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "delete csmz_gjjxj where xn||xh='"+pkT+"' and xysh<>'通过'";
					} else {
						sqlT[i] = "delete csmz_gjjxj where xn||xh='"+pkT+"' and xxsh<>'通过'";
					}
				} else {
					sqlT[i] = "delete csmz_gjjxj where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjjxjsh&go=go", false);
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
						sqlT[i] = "update csmz_gjjxj set fdysh='通过' where xn||xh='"+pkT+"'";
					} else {
						sqlT[i] = "update csmz_gjjxj set xysh='通过' where xn||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update csmz_gjjxj set xxsh='通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjjxjsh&go=go", false);
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
						String xyshT = dao.getOneRs(
								"select xysh from csmz_gjjxj where xn||xh=?",
								new String[] { pkT }, "xysh");
						if (!"通过".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update csmz_gjjxj set fdysh='不通过' where xn||xh='"+pkT+"'";
						}
					} else {
						String xxshT = dao.getOneRs(
								"select xxsh from csmz_gjjxj where xn||xh=?",
								new String[] { pkT }, "xxsh");
						if (!"通过".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update csmz_gjjxj set xysh='不通过' where xn||xh='"+pkT+"'";
						}
					}
				} else {
					sqlT[i] = "update csmz_gjjxj set xxsh='不通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjjxjsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao.getOneRs(
							"select xysh from csmz_gjjxj where xn||xh=?",
							new String[] { pkVal }, "xysh");
					if ("通过".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjjxj", new String[] {
								"fdysh", "fdyshyj" },
								new String[] { yesNo, fdyshyj }, "xn||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao.getOneRs(
							"select xxsh from csmz_gjjxj where xn||xh=?",
							new String[] { pkVal }, "xxsh");
					if ("通过".equalsIgnoreCase(xxshT)) {
						request.setAttribute("xxshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjjxj", new String[] {
								"xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xn||xh", pkVal, request);
					}
				}
			} else {
				StandardOperation.update("csmz_gjjxj", new String[] {
						"xxsh", "xxshyj" }, new String[] {
						yesNo, xxshyj }, "xn||xh", pkVal,
						request);
			}
		}
		realTable = "csmz_gjjxj";
		pk = "xn||xh";
		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,zzmmmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,gxnbxkcs,yxkc,lhkc,cjpm,zhkpcj,zhkppm,zyjx,yjjx,xjjx,sjjx,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjjxj where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.gxnbxkcs,a.yxkc,a.lhkc,a.cjpm,a.zhkpcj,a.zhkppm,a.zyjx,a.yjjx,a.xjjx,a.sjjx,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.fdysh yesNo "
						+ "from view_csmz_gjjxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "fdy");
			} else {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.gxnbxkcs,a.yxkc,a.lhkc,a.cjpm,a.zhkpcj,a.zhkppm,a.zyjx,a.yjjx,a.xjjx,a.sjjx,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xysh yesNo "
						+ "from view_csmz_gjjxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "xy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.zzmmmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.gxnbxkcs,a.yxkc,a.lhkc,a.cjpm,a.zhkpcj,a.zhkppm,a.zyjx,a.yjjx,a.xjjx,a.sjjx,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xxsh yesNo "
				+ "from view_csmz_gjjxj a where " + pk + "='" + pkVal + "'";
			request.setAttribute("user", "xx");
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
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_csmz_gjjxj");
		request.setAttribute("act", "gjzxdksh");
		return mapping.findForward("gjjxjshXxxx");
	}
	
	/**
	 * @describe 国家奖学金列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjjxjshExp(ActionMapping mapping, ActionForm form,
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
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		sql = "select * from view_csmz_gjjxj " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_csmz_gjjxj where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_csmz_gjjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjjxjshExp");
	}
	
	/**
	 * @describe 国家励志奖学金申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

		// String userNameReal;

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

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xn,xh,xm,xb,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtrkzs,jtyzsr,rjysr,jtzz,yzbm,xsjbqkjj,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjlzjxj where 1=2";
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

			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家励志奖学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String jthk = Base.chgNull(request.getParameter("jthk"),
							"", 1);
					String jtrkzs = Base.chgNull(
							request.getParameter("jtrkzs"), "", 1);
					String jtyzsr = Base.chgNull(
							request.getParameter("jtyzsr"), "", 1);
					String rjysr = Base.chgNull(request.getParameter("rjysr"),
							"", 1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),
							"", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String xsjbqkjj = Base.chgNull(request
							.getParameter("xsjbqkjj"), "", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xn + xh;
					}
					sql = "select xxsh from csmz_gjlzjxj where xn||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("通过"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("csmz_gjlzjxj", "xn||xh", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh", "jthk",
								"jtrkzs", "jtyzsr", "rjysr", "jtzz", "yzbm",
								"xsjbqkjj" };

						valueForOut = new String[] { xn, xh, jthk, jtrkzs,
								jtyzsr, rjysr, jtzz, yzbm, xsjbqkjj };

						boolean ok = false;
						ok = StandardOperation.insert("csmz_gjlzjxj", colName1,
								valueForOut, request);
						if (ok) {
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
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String jthk = Base.chgNull(request.getParameter("jthk"),
						"", 1);
				String jtrkzs = Base.chgNull(
						request.getParameter("jtrkzs"), "", 1);
				String jtyzsr = Base.chgNull(
						request.getParameter("jtyzsr"), "", 1);
				String rjysr = Base.chgNull(request.getParameter("rjysr"),
						"", 1);
				String jtzz = Base.chgNull(request.getParameter("jtzz"),
						"", 1);
				String yzbm = Base.chgNull(request.getParameter("yzbm"),
						"", 1);
				String xsjbqkjj = Base.chgNull(request
						.getParameter("xsjbqkjj"), "", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xn + xh;
				}
				sql = "select xxsh from csmz_gjlzjxj where xn||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("csmz_gjlzjxj", "xn||xh", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xn", "xh", "jthk",
							"jtrkzs", "jtyzsr", "rjysr", "jtzz", "yzbm",
							"xsjbqkjj" };

					valueForOut = new String[] { xn, xh, jthk, jtrkzs,
							jtyzsr, rjysr, jtzz, yzbm, xsjbqkjj };

					boolean ok = false;
					ok = StandardOperation.insert("csmz_gjlzjxj", colName1,
							valueForOut, request);
					if (ok) {
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
			pkVal = xn + xh;
		}

		sql = "select xn,xh,xm,xb,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtrkzs,jtyzsr,rjysr,jtzz,yzbm,xsjbqkjj,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjlzjxj where xn||xh=?";
		outString = new String[] { "xn", "xh", "xm", "xb", "rxny", "mzmc",
				"sfzh", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"jthk", "jtrkzs", "jtyzsr", "rjysr", "jtzz", "yzbm",
				"xsjbqkjj", "sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj",
				"xxsh", "xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh && !"".equalsIgnoreCase(xh)) {
				boolean bk = dao.isKns(xh);
				if (bk) {
					sql = "select b.xh,b.xm,b.xb,b.csrq csny,b.mzmc,b.sfzh,b.zzmmmc,b.nj,b.xz,"
							+ "b.xymc,b.zymc,b.bjmc,(select a.rxny from bks_xsjbxx a where a.xh=b.xh) rxny from view_stu_details b where b.xh=?";
					String[] colName = new String[] { "xh", "xm", "xb", "csny",
							"mzmc", "sfzh", "zzmmmc", "nj", "xz", "zzmmmc",
							"xymc", "zymc", "bjmc", "rxny" };
					String[] outVal = dao.getOneRs(sql, new String[] { xh },
							colName);
					if (outVal == null) {
					} else {
						colName = new String[] { "xh", "xm", "xb", "csny",
								"mzmc", "sfzh", "zzmmmc", "nj", "xz", "zzmmmc",
								"xymc", "zymc", "bjmc", "rxny" };
						for (int i = 0; i < colName.length; i++) {
							if (null != outVal[i]) {
								map.put(colName[i], outVal[i]);
							} else {
								map.put(colName[i], "");
							}
						}
					}
				} else {
					request.setAttribute("kns", "no");
				}
			}
		} else {
			String now = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
			map.put("sqsj", now);
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
		return mapping.findForward("gjlzjxjsq");
	}
	
	/**
	 * @describe 打印国家励志奖学金申请表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xn      = Base.chgNull(request.getParameter("xn"),"", 1);
		String xh      = Base.chgNull(request.getParameter("xh"),"", 1);
		String xm      = Base.chgNull(request.getParameter("xm"),"", 1);
		String xb      = Base.chgNull(request.getParameter("xb"),"", 1);
		String rxny    = Base.chgNull(request.getParameter("rxny"),"", 1);
		String mzmc    = Base.chgNull(request.getParameter("mzmc"),"", 1);
		String sfzh    = Base.chgNull(request.getParameter("sfzh"),"", 1);
		String nj      = Base.chgNull(request.getParameter("nj"),"", 1);
		String xymc    = Base.chgNull(request.getParameter("xymc"),"", 1);
		String zymc    = Base.chgNull(request.getParameter("zymc"),"", 1);
		String bjmc    = Base.chgNull(request.getParameter("bjmc"),"", 1);
		String jthk    = Base.chgNull(request.getParameter("jthk"),"", 1);
		String jtrkzs  = Base.chgNull(request.getParameter("jtrkzs"),"", 1);
		String jtyzsr  = Base.chgNull(request.getParameter("jtyzsr"),"", 1);
		String rjysr   = Base.chgNull(request.getParameter("rjysr"),"", 1);
		String jtzz    = Base.chgNull(request.getParameter("jtzz"),"", 1);
		String yzbm    = Base.chgNull(request.getParameter("yzbm"),"", 1);
		String xsjbqkjj= Base.chgNull(request.getParameter("xsjbqkjj"),"", 1);
		
		String[] outValue = new String[] { xn, xh, xm, xb, rxny, mzmc, sfzh,
				nj, xymc, zymc, bjmc, jthk, jtrkzs, jtyzsr, rjysr, jtzz, yzbm,
				xsjbqkjj };
		String[] outString = new String[] { "xn", "xh", "xm", "xb", "rxny",
				"mzmc", "sfzh", "nj", "xymc", "zymc", "bjmc", "jthk", "jtrkzs",
				"jtyzsr", "rjysr", "jtzz", "yzbm", "xsjbqkjj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("gjlzjxjsqb");
	}

	/**
	 * @describe 国家励志奖学金审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "csmz_gjlzjxj";
		pk = "xn||xh";
		tableName = "view_csmz_gjlzjxj";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 国家励志奖学金项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家励志奖学金";
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
					"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh",
					"xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc) a";
					request.setAttribute("user", "fdy");
				} else {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh desc) a";
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
		request.setAttribute("act", "gjjxjsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("gjlzjxjsh");
	}
	
	/**
	 * @describe 国家励志奖学金审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjlzjxjshXxxx(ActionMapping mapping, ActionForm form,
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
		String isFdy = session.getAttribute("isFdy").toString();

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "delete csmz_gjlzjxj where xn||xh='"+pkT+"' and xysh<>'通过'";
					} else {
						sqlT[i] = "delete csmz_gjlzjxj where xn||xh='"+pkT+"' and xxsh<>'通过'";
					}
				} else {
					sqlT[i] = "delete csmz_gjlzjxj where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjlzjxjsh&go=go", false);
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
						sqlT[i] = "update csmz_gjlzjxj set fdysh='通过' where xn||xh='"+pkT+"'";
					} else {
						sqlT[i] = "update csmz_gjlzjxj set xysh='通过' where xn||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update csmz_gjlzjxj set xxsh='通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjlzjxjsh&go=go", false);
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
						String xyshT = dao.getOneRs(
								"select xysh from csmz_gjlzjxj where xn||xh=?",
								new String[] { pkT }, "xysh");
						if (!"通过".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update csmz_gjlzjxj set fdysh='不通过' where xn||xh='"+pkT+"'";
						}
					} else {
						String xxshT = dao.getOneRs(
								"select xxsh from csmz_gjlzjxj where xn||xh=?",
								new String[] { pkT }, "xxsh");
						if (!"通过".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update csmz_gjlzjxj set xysh='不通过' where xn||xh='"+pkT+"'";
						}
					}
				} else {
					sqlT[i] = "update csmz_gjlzjxj set xxsh='不通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjlzjxjsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao.getOneRs(
							"select xysh from csmz_gjlzjxj where xn||xh=?",
							new String[] { pkVal }, "xysh");
					if ("通过".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjlzjxj", new String[] {
								"fdysh", "fdyshyj" },
								new String[] { yesNo, fdyshyj }, "xn||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao.getOneRs(
							"select xxsh from csmz_gjlzjxj where xn||xh=?",
							new String[] { pkVal }, "xxsh");
					if ("通过".equalsIgnoreCase(xxshT)) {
						request.setAttribute("xxshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjlzjxj", new String[] {
								"xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xn||xh", pkVal, request);
					}
				}
			} else {
				StandardOperation.update("csmz_gjlzjxj", new String[] {
						"xxsh", "xxshyj" }, new String[] {
						yesNo, xxshyj }, "xn||xh", pkVal,
						request);
			}
		}
		realTable = "csmz_gjlzjxj";
		pk = "xn||xh";
		sql = "select xn,xh,xm,xb,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,jthk,jtrkzs,jtyzsr,rjysr,jtzz,yzbm,xsjbqkjj,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjlzjxj where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.jthk,a.jtrkzs,a.jtyzsr,a.rjysr,a.jtzz,a.yzbm,a.xsjbqkjj,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.fdysh yesNo "
						+ "from view_csmz_gjlzjxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "fdy");
			} else {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.jthk,a.jtrkzs,a.jtyzsr,a.rjysr,a.jtzz,a.yzbm,a.xsjbqkjj,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xysh yesNo "
						+ "from view_csmz_gjlzjxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "xy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xh,a.xm,a.xb,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.jthk,a.jtrkzs,a.jtyzsr,a.rjysr,a.jtzz,a.yzbm,a.xsjbqkjj,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xxsh yesNo "
				+ "from view_csmz_gjlzjxj a where " + pk + "='" + pkVal + "'";
			request.setAttribute("user", "xx");
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
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_csmz_gjlzjxj");
		request.setAttribute("act", "gjzxdksh");
		return mapping.findForward("gjlzjxjshXxxx");
	}
	
	/**
	 * @describe 国家励志奖学金列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjlzjxjshExp(ActionMapping mapping, ActionForm form,
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
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		sql = "select * from view_csmz_gjlzjxj " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_csmz_gjlzjxj where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_csmz_gjlzjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjlzjxjshExp");
	}
	
	/**
	 * @describe 国家助学金申请页面
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

//		String userDep;

		// String userNameReal;

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

		String doType = request.getParameter("doType");// 操作类型

		String sql = "";
//		String sql1 = "";
		String[] outString = new String[] {};
		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjzxj where 1=2";
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

			sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("save")) {// /学生填写申请
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String jthk = Base.chgNull(request.getParameter("jthk"),
							"", 1);
					String jtzrks = Base.chgNull(
							request.getParameter("jtzrks"), "", 1);
					String jtyzsr = Base.chgNull(
							request.getParameter("jtyzsr"), "", 1);
					String rjysr = Base.chgNull(request.getParameter("rjysr"),
							"", 1);
					String srly = Base.chgNull(request.getParameter("srly"),
							"", 1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),
							"", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String jtcy1_xm = Base.chgNull(request
							.getParameter("jtcy1_xm"), "", 1);
					String jtcy1_nl = Base.chgNull(request
							.getParameter("jtcy1_nl"), "", 1);
					String jtcy1_gx = Base.chgNull(request
							.getParameter("jtcy1_gx"), "", 1);
					String jtcy1_gzdw = Base.chgNull(request
							.getParameter("jtcy1_gzdw"), "", 1);
					String jtcy2_xm = Base.chgNull(request
							.getParameter("jtcy2_xm"), "", 1);
					String jtcy2_nl = Base.chgNull(request
							.getParameter("jtcy2_nl"), "", 1);
					String jtcy2_gx = Base.chgNull(request
							.getParameter("jtcy2_gx"), "", 1);
					String jtcy2_gzdw = Base.chgNull(request
							.getParameter("jtcy2_gzdw"), "", 1);
					String jtcy3_xm = Base.chgNull(request
							.getParameter("jtcy3_xm"), "", 1);
					String jtcy3_nl = Base.chgNull(request
							.getParameter("jtcy3_nl"), "", 1);
					String jtcy3_gx = Base.chgNull(request
							.getParameter("jtcy3_gx"), "", 1);
					String jtcy3_gzdw = Base.chgNull(request
							.getParameter("jtcy3_gzdw"), "", 1);
					String jtcy4_xm = Base.chgNull(request
							.getParameter("jtcy4_xm"), "", 1);
					String jtcy4_nl = Base.chgNull(request
							.getParameter("jtcy4_nl"), "", 1);
					String jtcy4_gx = Base.chgNull(request
							.getParameter("jtcy4_gx"), "", 1);
					String jtcy4_gzdw = Base.chgNull(request
							.getParameter("jtcy4_gzdw"), "", 1);
					String jtcy5_xm = Base.chgNull(request
							.getParameter("jtcy5_xm"), "", 1);
					String jtcy5_nl = Base.chgNull(request
							.getParameter("jtcy5_nl"), "", 1);
					String jtcy5_gx = Base.chgNull(request
							.getParameter("jtcy5_gx"), "", 1);
					String jtcy5_gzdw = Base.chgNull(request
							.getParameter("jtcy5_gzdw"), "", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xn + xh;
					}
					sql = "select xxsh from csmz_gjzxj where xn||xh=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& (temp[0].equalsIgnoreCase("通过"))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("csmz_gjzxj", "xn||xh", pkVal,
								request);

						String[] valueForOut = null;
						String[] colName1 = new String[] { "xn", "xh", "lxdh",
								"jthk", "jtzrks", "jtyzsr", "rjysr", "srly",
								"jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
								"jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
								"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
								"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
								"jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
								"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm",
								"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" };

						valueForOut = new String[] { xn, xh, lxdh, jthk,
								jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
								jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
								jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
								jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
								jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
								jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly };

						boolean ok = false;
						ok = StandardOperation.insert("csmz_gjzxj", colName1,
								valueForOut, request);
						if (ok) {
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
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			if (doType != null && doType.equalsIgnoreCase("save")) {
				String lxdh = Base.chgNull(request.getParameter("lxdh"),
						"", 1);
				String jthk = Base.chgNull(request.getParameter("jthk"),
						"", 1);
				String jtzrks = Base.chgNull(
						request.getParameter("jtzrks"), "", 1);
				String jtyzsr = Base.chgNull(
						request.getParameter("jtyzsr"), "", 1);
				String rjysr = Base.chgNull(request.getParameter("rjysr"),
						"", 1);
				String srly = Base.chgNull(request.getParameter("srly"),
						"", 1);
				String jtzz = Base.chgNull(request.getParameter("jtzz"),
						"", 1);
				String yzbm = Base.chgNull(request.getParameter("yzbm"),
						"", 1);
				String jtcy1_xm = Base.chgNull(request
						.getParameter("jtcy1_xm"), "", 1);
				String jtcy1_nl = Base.chgNull(request
						.getParameter("jtcy1_nl"), "", 1);
				String jtcy1_gx = Base.chgNull(request
						.getParameter("jtcy1_gx"), "", 1);
				String jtcy1_gzdw = Base.chgNull(request
						.getParameter("jtcy1_gzdw"), "", 1);
				String jtcy2_xm = Base.chgNull(request
						.getParameter("jtcy2_xm"), "", 1);
				String jtcy2_nl = Base.chgNull(request
						.getParameter("jtcy2_nl"), "", 1);
				String jtcy2_gx = Base.chgNull(request
						.getParameter("jtcy2_gx"), "", 1);
				String jtcy2_gzdw = Base.chgNull(request
						.getParameter("jtcy2_gzdw"), "", 1);
				String jtcy3_xm = Base.chgNull(request
						.getParameter("jtcy3_xm"), "", 1);
				String jtcy3_nl = Base.chgNull(request
						.getParameter("jtcy3_nl"), "", 1);
				String jtcy3_gx = Base.chgNull(request
						.getParameter("jtcy3_gx"), "", 1);
				String jtcy3_gzdw = Base.chgNull(request
						.getParameter("jtcy3_gzdw"), "", 1);
				String jtcy4_xm = Base.chgNull(request
						.getParameter("jtcy4_xm"), "", 1);
				String jtcy4_nl = Base.chgNull(request
						.getParameter("jtcy4_nl"), "", 1);
				String jtcy4_gx = Base.chgNull(request
						.getParameter("jtcy4_gx"), "", 1);
				String jtcy4_gzdw = Base.chgNull(request
						.getParameter("jtcy4_gzdw"), "", 1);
				String jtcy5_xm = Base.chgNull(request
						.getParameter("jtcy5_xm"), "", 1);
				String jtcy5_nl = Base.chgNull(request
						.getParameter("jtcy5_nl"), "", 1);
				String jtcy5_gx = Base.chgNull(request
						.getParameter("jtcy5_gx"), "", 1);
				String jtcy5_gzdw = Base.chgNull(request
						.getParameter("jtcy5_gzdw"), "", 1);
				String sqly = Base.chgNull(request.getParameter("sqly"),
						"", 1);
				pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xn + xh;
				}
				sql = "select xxsh from csmz_gjzxj where xn||xh=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& (temp[0].equalsIgnoreCase("通过"))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("csmz_gjzxj", "xn||xh", pkVal,
							request);

					String[] valueForOut = null;
					String[] colName1 = new String[] { "xn", "xh", "lxdh",
							"jthk", "jtzrks", "jtyzsr", "rjysr", "srly",
							"jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
							"jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
							"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
							"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
							"jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
							"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm",
							"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" };

					valueForOut = new String[] { xn, xh, lxdh, jthk,
							jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
							jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
							jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
							jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
							jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly };

					boolean ok = false;
					ok = StandardOperation.insert("csmz_gjzxj", colName1,
							valueForOut, request);
					if (ok) {
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
			pkVal = xn + xh;
		}

		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjzxj where xn||xh=?";
		outString = new String[] { "xn", "xh", "xm", "xb", "csny", "rxny",
				"mzmc", "sfzh", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "lxdh", "jthk", "jtzrks", "jtyzsr", "rjysr", "srly",
				"jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "sqly", "sqsj", "fdysh", "fdyshyj",
				"xysh", "xyshyj", "xxsh", "xxshyj" };
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if (null != xh && !"".equalsIgnoreCase(xh)) {
				boolean bk = dao.isKns(xh);
				if (bk) {
					sql = "select b.xh,b.xm,b.xb,b.csrq csny,b.mzmc,b.sfzh,b.zzmmmc,b.nj,b.xz,"
							+ "b.xymc,b.zymc,b.bjmc,(select a.rxny from bks_xsjbxx a where a.xh=b.xh) rxny from view_stu_details b where b.xh=?";
					String[] colName = new String[] { "xh", "xm", "xb", "csny",
							"mzmc", "sfzh", "zzmmmc", "nj", "xz",
							"xymc", "zymc", "bjmc", "rxny" };
					String[] outVal = dao.getOneRs(sql, new String[] { xh },
							colName);
					if (outVal == null) {
					} else {
						colName = new String[] { "xh", "xm", "xb", "csny",
								"mzmc", "sfzh", "zzmmmc", "nj", "xz",
								"xymc", "zymc", "bjmc", "rxny" };
						for (int i = 0; i < colName.length; i++) {
							if (null != outVal[i]) {
								map.put(colName[i], outVal[i]);
							} else {
								map.put(colName[i], "");
							}
						}
					}
				} else {
					request.setAttribute("kns", "no");
				}
			}
		} else {
			String now = (dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					new String[] {}, new String[] { "sdate" }))[0];
			map.put("sqsj", now);
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
		return mapping.findForward("gjzxjsq");
	}
	
	/**
	 * @describe 打印国家助学金申请表
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xn         = Base.chgNull(request.getParameter("xn"),"", 1);
		String xh         = Base.chgNull(request.getParameter("xh"),"", 1);
		String xm         = Base.chgNull(request.getParameter("xm"),"", 1);
		String xb         = Base.chgNull(request.getParameter("xb"),"", 1);
		String csny       = Base.chgNull(request.getParameter("csny"),"", 1);
		String rxny       = Base.chgNull(request.getParameter("rxny"),"", 1);
		String mzmc       = Base.chgNull(request.getParameter("mzmc"),"", 1);
		String sfzh       = Base.chgNull(request.getParameter("sfzh"),"", 1);
		String nj         = Base.chgNull(request.getParameter("nj"),"", 1);
		String xymc       = Base.chgNull(request.getParameter("xymc"),"", 1);
		String zymc       = Base.chgNull(request.getParameter("zymc"),"", 1);
		String bjmc       = Base.chgNull(request.getParameter("bjmc"),"", 1);
		String lxdh       = Base.chgNull(request.getParameter("lxdh"),"", 1);
		String jthk       = Base.chgNull(request.getParameter("jthk"),"", 1);
		String jtzrks     = Base.chgNull(request.getParameter("jtzrks"),"", 1);
		String jtyzsr     = Base.chgNull(request.getParameter("jtyzsr"),"", 1);
		String rjysr      = Base.chgNull(request.getParameter("rjysr"),"", 1);
		String srly       = Base.chgNull(request.getParameter("srly"),"", 1);
		String jtzz       = Base.chgNull(request.getParameter("jtzz"),"", 1);
		String yzbm       = Base.chgNull(request.getParameter("yzbm"),"", 1);
		String jtcy1_xm   = Base.chgNull(request.getParameter("jtcy1_xm"),"", 1);
		String jtcy1_nl   = Base.chgNull(request.getParameter("jtcy1_nl"),"", 1);
		String jtcy1_gx   = Base.chgNull(request.getParameter("jtcy1_gx"),"", 1);
		String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),"", 1);
		String jtcy2_xm   = Base.chgNull(request.getParameter("jtcy2_xm"),"", 1);
		String jtcy2_nl   = Base.chgNull(request.getParameter("jtcy2_nl"),"", 1);
		String jtcy2_gx   = Base.chgNull(request.getParameter("jtcy2_gx"),"", 1);
		String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),"", 1);
		String jtcy3_xm   = Base.chgNull(request.getParameter("jtcy3_xm"),"", 1);
		String jtcy3_nl   = Base.chgNull(request.getParameter("jtcy3_nl"),"", 1);
		String jtcy3_gx   = Base.chgNull(request.getParameter("jtcy3_gx"),"", 1);
		String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),"", 1);
		String jtcy4_xm   = Base.chgNull(request.getParameter("jtcy4_xm"),"", 1);
		String jtcy4_nl   = Base.chgNull(request.getParameter("jtcy4_nl"),"", 1);
		String jtcy4_gx   = Base.chgNull(request.getParameter("jtcy4_gx"),"", 1);
		String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),"", 1);
		String jtcy5_xm   = Base.chgNull(request.getParameter("jtcy5_xm"),"", 1);
		String jtcy5_nl   = Base.chgNull(request.getParameter("jtcy5_nl"),"", 1);
		String jtcy5_gx   = Base.chgNull(request.getParameter("jtcy5_gx"),"", 1);
		String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),"", 1);
		String sqly       = Base.chgNull(request.getParameter("sqly"),"", 1);
		
		String[] outValue = new String[] { xn, xh, xm, xb, csny, rxny, mzmc,
				sfzh, nj, xymc, zymc, bjmc, lxdh, jthk, jtzrks, jtyzsr, rjysr,
				srly, jtzz, yzbm, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
				jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl,
				jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw,
				jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly };
		String[] outString = new String[] { "xn", "xh", "xm", "xb", "csny",
				"rxny", "mzmc", "sfzh", "nj", "xymc", "zymc", "bjmc", "lxdh",
				"jthk", "jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"sqly" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("gjzxjsqb");
	}

	/**
	 * @describe 国家助学金审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
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
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		realTable = "csmz_gjzxj";
		pk = "xn||xh";
		tableName = "view_csmz_gjzxj";
		
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		tips = "当前所在位置：学生资助 - 审核 - 国家助学金项目审核";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 国家助学金";
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm", "xb",
					"xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("user", "xy");
			}
		} else {
			colList = new String[] { "bgcolor", "主键", "xn", "xh", "xm",
					"xb", "xymc", "zymc", "bjmc", "nj", "sqsj", "fdysh",
					"xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				request.setAttribute("user", "xx");
			} else {
				if ("true".equalsIgnoreCase(isFdy)) {
					sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by fdysh desc) a";
					request.setAttribute("user", "fdy");
				} else {
					sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " 主键,a.xn,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sqsj,a.fdysh,a.xysh,a.xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ " and xydm='"
							+ userDep
							+ "' and fdysh='通过' order by xysh desc) a";
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
		request.setAttribute("act", "gjjxjsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("gjzxjsh");
	}
	
	/**
	 * @describe 国家助学金审核单个信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxjshXxxx(ActionMapping mapping, ActionForm form,
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
		String isFdy = session.getAttribute("isFdy").toString();

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					if ("true".equalsIgnoreCase(isFdy)) {
						sqlT[i] = "delete csmz_gjzxj where xn||xh='"+pkT+"' and xysh<>'通过'";
					} else {
						sqlT[i] = "delete csmz_gjzxj where xn||xh='"+pkT+"' and xxsh<>'通过'";
					}
				} else {
					sqlT[i] = "delete csmz_gjzxj where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxjsh&go=go", false);
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
						sqlT[i] = "update csmz_gjzxj set fdysh='通过' where xn||xh='"+pkT+"'";
					} else {
						sqlT[i] = "update csmz_gjzxj set xysh='通过' where xn||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update csmz_gjzxj set xxsh='通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxjsh&go=go", false);
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
						String xyshT = dao.getOneRs(
								"select xysh from csmz_gjzxj where xn||xh=?",
								new String[] { pkT }, "xysh");
						if (!"通过".equalsIgnoreCase(xyshT)) {
							sqlT[i] = "update csmz_gjzxj set fdysh='不通过' where xn||xh='"+pkT+"'";
						}
					} else {
						String xxshT = dao.getOneRs(
								"select xxsh from csmz_gjzxj where xn||xh=?",
								new String[] { pkT }, "xxsh");
						if (!"通过".equalsIgnoreCase(xxshT)) {
							sqlT[i] = "update csmz_gjzxj set xysh='不通过' where xn||xh='"+pkT+"'";
						}
					}
				} else {
					sqlT[i] = "update csmz_gjzxj set xxsh='不通过' where xn||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/csmz_xszz.do?method=gjzxjsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {
					String xyshT = dao.getOneRs(
							"select xysh from csmz_gjzxj where xn||xh=?",
							new String[] { pkVal }, "xysh");
					if ("通过".equalsIgnoreCase(xyshT)) {
						request.setAttribute("xyshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjzxj", new String[] {
								"fdysh", "fdyshyj" },
								new String[] { yesNo, fdyshyj }, "xn||xh", pkVal,
								request);
					}
				} else {
					String xxshT = dao.getOneRs(
							"select xxsh from csmz_gjzxj where xn||xh=?",
							new String[] { pkVal }, "xxsh");
					if ("通过".equalsIgnoreCase(xxshT)) {
						request.setAttribute("xxshjg", "pass");
					} else {
						StandardOperation.update("csmz_gjzxj", new String[] {
								"xysh", "xyshyj" }, new String[] {
								yesNo, xyshyj }, "xn||xh", pkVal, request);
					}
				}
			} else {
				StandardOperation.update("csmz_gjzxj", new String[] {
						"xxsh", "xxshyj" }, new String[] {
						yesNo, xxshyj }, "xn||xh", pkVal,
						request);
			}
		}
		realTable = "csmz_gjzxj";
		pk = "xn||xh";
		sql = "select xn,xh,xm,xb,csny,rxny,mzmc,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_csmz_gjzxj where 1=2";
		String[] outString = dao.getColumnName(sql);// 获得列名数组
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if ("true".equalsIgnoreCase(isFdy)) {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.jthk,a.jtzrks,a.jtyzsr,a.rjysr,a.srly,a.jtzz,a.yzbm,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.fdysh yesNo "
						+ "from view_csmz_gjzxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "fdy");
			} else {
				sql = "select "
						+ pk
						+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.jthk,a.jtzrks,a.jtyzsr,a.rjysr,a.srly,a.jtzz,a.yzbm,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xysh yesNo "
						+ "from view_csmz_gjzxj a where " + pk + "='" + pkVal
						+ "'";
				request.setAttribute("user", "xy");
			}
		} else {
			sql = "select "
				+ pk
				+ " pk,a.xn,a.xh,a.xm,a.xb,a.csny,a.rxny,a.mzmc,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.lxdh,a.jthk,a.jtzrks,a.jtyzsr,a.rjysr,a.srly,a.jtzz,a.yzbm,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.sqly,a.sqsj,a.fdysh,a.fdyshyj,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.xxsh yesNo "
				+ "from view_csmz_gjzxj a where " + pk + "='" + pkVal + "'";
			request.setAttribute("user", "xx");
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
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_csmz_gjzxj");
		request.setAttribute("act", "gjzxdksh");
		return mapping.findForward("gjzxjshXxxx");
	}
	
	/**
	 * @describe 国家助学金列表导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxjshExp(ActionMapping mapping, ActionForm form,
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
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String xn = "";
		if(!isQuery.equalsIgnoreCase("is")){
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
		sql = "select * from view_csmz_gjzxj " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_csmz_gjzxj where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_csmz_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjzxjshExp");
	}
}
