package xgxt.action.zbdx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.zbdx.StudentForm;
import xgxt.utils.SearchUtils;
import xgxt.action.Base;

public class XszzAction extends BaseAction {
	StandardOperation so = new StandardOperation();

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 中北大学国家助学贷款申请表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gjzxdk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		//			XszzForm temp = (XszzForm) form;
		//			String sql = "select * from view_jsxx_gjzxdk1 where nd=?";
		//			List list = dao.getListNotOut(sql, new String[]{temp.getNd()});
		//			request.setAttribute("list", list);
		HttpSession session = request.getSession();
		String userSpecType = session.getAttribute("userType").toString();
		if ("stu".equalsIgnoreCase(userSpecType)) {
			String sql = "select a.* from view_zbdx_xsjbxx a where xh=? ";
			String xh = String.valueOf(session.getAttribute("userName"));
			request.setAttribute("map", dao.getMapNotOut(sql,
					new String[] { xh }));
		} else {
			Map map = new HashMap();
			request.setAttribute("map", map);
		}
		return mapping.findForward("gjzxdk");
		//		}

	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 查询学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward stuInfoQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		//			String xxmc=session.getAttribute("xxmc").toString();

		boolean disabled = true;
		if (userType.equalsIgnoreCase("student")) {
			String xh = session.getAttribute("userName").toString();
			return new ActionForward("/stu_info_details.do?xh=" + xh, false);
		}
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String nj = Base.chgNull(studentForm.getNj(), "", 0);
		String xy = Base.chgNull(studentForm.getXy(), "", 0);
		String zy = Base.chgNull(studentForm.getZydm(), "", 0);
		String bj = Base.chgNull(studentForm.getBjdm(), "", 0);
		//			String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String xm = Base.chgNull(studentForm.getXm(), "", 0);

		if (userSpecType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			studentForm.setXy(xy);
			disabled = false;
		}
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("xydm", xy));
		querry.append(SearchUtils.equalSql("zydm", zy));
		querry.append(SearchUtils.equalSql("bjdm", bj));
		querry.append(SearchUtils.likeSql("xm", DealString.toGBK(xm)));
		studentForm.setXm(DealString.toGBK(xm));

		colList = new String[] { "xh", "xm", "xb", "nj", "xz", "bjmc" };
		sql = "select distinct a.xh, a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsjbxx a"
				+ querry;
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
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

		String oper = request.getParameter("oper");
		request.setAttribute("oper", oper);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("disabled", disabled);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("xxmc", session.getAttribute("xxmc"));
		return mapping.findForward("success");
		//		}
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 获得学生信息,有些信息用于填充"中北大学国家助学贷款申请表"
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xh = Base.chgNull(studentForm.getXh(), "", 0);

		String countSql = "SELECT COUNT(*) isHas FROM knssqb WHERE xh=? AND xxsh='通过' AND nd=? AND xn=?"; //这里年度需要讨论
		String count = dao.getMapNotOut(countSql, new String[] { xh, nd, xn })
				.get("ishas");
		if (Integer.parseInt(count) == 0) { //判断是否为困难生
			request.setAttribute("map", new HashMap());
		} else {
			//String cjSql = "SELECT COUNT(";//判断成绩 是否符合要求
			String sql = "select * from view_zbdx_gjdksq a, zbdx_gjzxdk b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";
			request.setAttribute("map", dao.getMapNotOut(sql, new String[] {
					xh, nd }));
		}
		request.setAttribute("iskns", count);

		return mapping.findForward("gjzxdk");
		//		}
	}

	/**
	 * @describe 得到贷款确认书 和 学生的基本信息
	 */
	public ActionForward getDkqrInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		HttpSession session = request.getSession();
		StudentForm studentForm = (StudentForm) form;
		String nd = Base.currNd;
		String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String sftgSql = new String(
				"select count(*) sftg from zbdx_gjzxdk where xxsh='通过' and xh=? and nd=?");
		String count = dao.getMapNotOut(sftgSql, new String[] { xh, nd }).get(
				"sftg");
		String userOnline = session.getAttribute("userOnLine").toString();
		if ("student".equalsIgnoreCase(userOnline)) {
			xh = session.getAttribute("userName").toString();
			count = dao.getMapNotOut(sftgSql, new String[] { xh, nd }).get(
					"sftg");
		}
		HashMap<String, String> map = new HashMap<String, String>();

		if (Integer.parseInt(count) == 0) { //判断"国家助学贷款 审核 是否通过"
			request.setAttribute("map", new HashMap());
		} else {
			String sql = "select * from view_zbdx_xsjbxx a, zbdx_dkqrs b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";
			map = dao.getMapNotOut(sql, new String[] { xh, nd });
			sql = "select dqxn,dqxq,dqnd from xtszb ";
			HashMap map2 = new HashMap();
			HashMap map3 = new HashMap();
			map2 = dao.getMapNotOut(sql, null);
			sql = " select xqmc from xqdzb where xqdm=? ";
			String xqdm = map2.get("dqxq").toString();
			map3 = dao.getMapNotOut(sql, new String[] { xqdm });
			String xqmc = map3.get("xqmc").toString();
			map.put("xn", map2.get("dqxn").toString());
			map.put("xq", xqmc);
			map.put("nd", map2.get("dqnd").toString());
			request.setAttribute("map", map);
		}
		request.setAttribute("sftg", count);

		return mapping.findForward("dkqrs");
		//		}
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 国家助学贷款申请 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String xzz = Base.chgNull(studentForm.getXzz(), "", 0);
		String xsdh = Base.chgNull(studentForm.getXsdh(), "", 0);
		String sfzh = Base.chgNull(studentForm.getSfzh(), "", 0);
		String jtdz = Base.chgNull(studentForm.getJtdz(), "", 0);
		String yzbm = Base.chgNull(studentForm.getYzbm(), "", 0);
		String jtrk = Base.chgNull(studentForm.getJtrk(), "", 0);
		String nsr = Base.chgNull(studentForm.getNsr(), "", 0);
		String jtdh = Base.chgNull(studentForm.getJtdh(), "", 0);

		String nd = Base.currNd;

		String sql = "select count(xh) jls from zbdx_gjzxdk where xh=? and nd=?";

		if (null != xh && "" != xh) {
			String sql_lrh = "select * from view_knsxx where  xxsh='通过' and xh=?";
			String tag = dao.returntag(sql_lrh, new String[] { xh });
			if ("empty".equalsIgnoreCase(tag)) {
				request.setAttribute("IsKns", "no");
				//return mapping.findForward("notKns");
			}
		}

		int jls = Integer.parseInt(dao.getMapNotOut(sql,
				new String[] { xh, nd }).get("jls"));
		if (jls == 0) { // 记录不存在，就进行插入操作。
			StandardOperation.insert("zbdx_gjzxdk", new String[] { "xh", "nd",
					"xzz", "xsdh", "sfzh", "jtdz", "yzbm", "jtrk", "nsr",
					"jtdh" }, new String[] { xh, nd, xzz, xsdh, sfzh, jtdz,
					yzbm, jtrk, nsr, jtdh }, request);
		} else { // 记录存在，就进行更新操作。
			StandardOperation
					.update("zbdx_gjzxdk", new String[] { "xzz", "xsdh",
							"sfzh", "jtdz", "yzbm", "jtrk", "nsr", "jtdh" },
							new String[] { xzz, xsdh, sfzh, jtdz, yzbm, jtrk,
									nsr, jtdh }, "xh||nd", xh + nd, request);
		}
		sql = "select a.*, b.* from view_zbdx_gjzxdk a, zbdx_gjzxdk b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";
		request.setAttribute("map", dao.getMapNotOut(sql,
				new String[] { xh, nd }));
		return mapping.findForward("gjzxdk");
		//		}

	}

	/**
	 * @describe 贷款确认书 数据保存 
	 */
	public ActionForward dkqrsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//			String url = "xszz.do?method=gjzxdk";
		//			if (this.power(mapping, request, url) != null) {// 权限控制代码
		//				request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//				return this.power(mapping, request, url);
		//			} else {
		// 真正的功能实现在这里.
		//				int a=0;
		StudentForm studentForm = (StudentForm) form;
		String xh = Base.chgNull(studentForm.getXh(), "", 1);
		String ghzh = Base.chgNull(request.getParameter("ghzh"), "", 1);
		String dkqx = Base.chgNull(request.getParameter("dkqx"), "", 1);
		String dkze = Base.chgNull(request.getParameter("dkze"), "", 1);
		String qygx = Base.chgNull(request.getParameter("qygx"), "", 1);
		String sqdkxf = Base.chgNull(request.getParameter("sqdkxf"), "", 1);
		String qylxdh = Base.chgNull(request.getParameter("qylxdh"), "", 1);
		String qsdkshf = Base.chgNull(request.getParameter("qsdkshf"), "", 1);
		String qysfzh = Base.chgNull(request.getParameter("qysfzh"), "", 1);
		String sqdkzsf = Base.chgNull(request.getParameter("sqdkzsf"), "", 1);
		String qygzdw = Base.chgNull(request.getParameter("qygzdw"), "", 1);
		String yxlxfs = Base.chgNull(request.getParameter("yxlxfs"), "", 1);
		String qyzz = Base.chgNull(request.getParameter("qyzz"), "", 1);
		String fqxm = Base.chgNull(request.getParameter("fqxm"), "", 1);
		String mqxm = Base.chgNull(request.getParameter("mqxm"), "", 1);
		String fqsfzh = Base.chgNull(request.getParameter("fqsfzh"), "", 1);
		String mqsfzh = Base.chgNull(request.getParameter("mqsfzh"), "", 1);
		String fqgzdw = Base.chgNull(request.getParameter("fqgzdw"), "", 1);
		String mqgzdw = Base.chgNull(request.getParameter("mqgzdw"), "", 1);
		String fqlxfs = Base.chgNull(request.getParameter("fqlxfs"), "", 1);
		String mqlxfs = Base.chgNull(request.getParameter("mqlxfs"), "", 1);
		String scbyqx = Base.chgNull(request.getParameter("scbyqx"), "", 1);
		String ffsj = Base.chgNull(request.getParameter("ffsj"), "", 1);
		String hth1 = Base.chgNull(request.getParameter("hth1"), "", 1);
		String hth2 = Base.chgNull(request.getParameter("hth2"), "", 1);
		String htjbjg1 = Base.chgNull(request.getParameter("htjbjg1"), "", 1);
		String htjbjg2 = Base.chgNull(request.getParameter("htjbjg2"), "", 1);
		String fzjgmc1 = Base.chgNull(request.getParameter("fzjgmc1"), "", 1);
		String fzjgmc2 = Base.chgNull(request.getParameter("fzjgmc2"), "", 1);
		String dkje1 = Base.chgNull(request.getParameter("dkje1"), "", 1);
		String dkje2 = Base.chgNull(request.getParameter("dkje2"), "", 1);
		String hknf1 = Base.chgNull(request.getParameter("hknf1"), "", 1);
		String hknf2 = Base.chgNull(request.getParameter("hknf2"), "", 1);
		String hth3 = Base.chgNull(request.getParameter("hth3"), "", 1);
		String dqgzdw = Base.chgNull(request.getParameter("dqgzdw"), "", 1);
		String htjbjg3 = Base.chgNull(request.getParameter("htjbjg3"), "", 1);
		String fzjgmc3 = Base.chgNull(request.getParameter("fzjgmc3"), "", 1);
		String dqdwyb = Base.chgNull(request.getParameter("dqdwyb"), "", 1);
		String dkjg3 = Base.chgNull(request.getParameter("dkjg3"), "", 1);
		String dqdwlxfs = Base.chgNull(request.getParameter("dqdwlxfs"), "", 1);
		String hknf = Base.chgNull(request.getParameter("hknf"), "", 1);
		String yjfsbj = Base.chgNull(request.getParameter("yjfsbj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String fsdkze = Base.chgNull(request.getParameter("fsdkze"), "", 1);
		String fsdkzsf = Base.chgNull(request.getParameter("fsdkzsf"), "", 1);
		String fsdkshf = Base.chgNull(request.getParameter("fsdkshf"), "", 1);
		String yffzxdkje = Base.chgNull(request.getParameter("yffzxdkje"), "",
				1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		String beizhu = Base.chgNull(request.getParameter("beizhu"), "", 1);

		String nd = Base.currNd;

		String sql = "select count(xh) jls from zbdx_dkqrs where xh=? and nd=?";
		int jls = Integer.parseInt(dao.getMapNotOut(sql,
				new String[] { xh, nd }).get("jls"));
		if (jls == 0) { //记录不存在，就进行插入操作。
			xysh = "未审核";
			xxsh = "未审核";
			String[] colName = new String[] { "xh", "nd", "ghzh", "dkqx",
					"dkze", "qygx", "sqdkxf", "qylxdh", "qsdkshf", "qysfzh",
					"sqdkzsf", "qygzdw", "yxlxfs", "qyzz", "fqxm", "mqxm",
					"fqsfzh", "mqsfzh", "fqgzdw", "mqgzdw", "fqlxfs", "mqlxfs",
					"scbyqx", "ffsj", "hth1", "hth2", "htjbjg1", "htjbjg2",
					"fzjgmc1", "fzjgmc2", "dkje1", "dkje2", "hknf1", "hknf2",
					"hth3", "dqgzdw", "htjbjg3", "fzjgmc3", "dqdwyb", "dkjg3",
					"dqdwlxfs", "hknf", "yjfsbj", "xysh", "xxsh", "fsdkze",
					"fsdkzsf", "fsdkshf", "yffzxdkje", "sqly", "beizhu" };
			StandardOperation.insert("zbdx_dkqrs", colName, new String[] { xh,
					nd, ghzh, dkqx, dkze, qygx, sqdkxf, qylxdh, qsdkshf,
					qysfzh, sqdkzsf, qygzdw, yxlxfs, qyzz, fqxm, mqxm, fqsfzh,
					mqsfzh, fqgzdw, mqgzdw, fqlxfs, mqlxfs, scbyqx, ffsj, hth1,
					hth2, htjbjg1, htjbjg2, fzjgmc1, fzjgmc2, dkje1, dkje2,
					hknf1, hknf2, hth3, dqgzdw, htjbjg3, fzjgmc3, dqdwyb,
					dkjg3, dqdwlxfs, hknf, yjfsbj, xysh, xxsh, fsdkze, fsdkzsf,
					fsdkshf, yffzxdkje, sqly, beizhu }, request);
		} else { //记录存在，就进行更新操作。
			StandardOperation.update("zbdx_dkqrs", new String[] { "ghzh",
					"dkqx", "dkze", "qygx", "sqdkxf", "qylxdh", "qsdkshf",
					"qysfzh", "sqdkzsf", "qygzdw", "yxlxfs", "qyzz", "fqxm",
					"mqxm", "fqsfzh", "mqsfzh", "fqgzdw", "mqgzdw", "fqlxfs",
					"mqlxfs", "scbyqx", "ffsj", "hth1", "hth2", "htjbjg1",
					"htjbjg2", "fzjgmc1", "fzjgmc2", "dkje1", "dkje2", "hknf1",
					"hknf2", "hth3", "dqgzdw", "htjbjg3", "fzjgmc3", "dqdwyb",
					"dkjg3", "dqdwlxfs", "hknf", "yjfsbj", "xysh", "xxsh",
					"fsdkze", "fsdkzsf", "fsdkshf", "yffzxdkje", "sqly",
					"beizhu" },
					new String[] { ghzh, dkqx, dkze, qygx, sqdkxf, qylxdh,
							qsdkshf, qysfzh, sqdkzsf, qygzdw, yxlxfs, qyzz,
							fqxm, mqxm, fqsfzh, mqsfzh, fqgzdw, mqgzdw, fqlxfs,
							mqlxfs, scbyqx, ffsj, hth1, hth2, htjbjg1, htjbjg2,
							fzjgmc1, fzjgmc2, dkje1, dkje2, hknf1, hknf2, hth3,
							dqgzdw, htjbjg3, fzjgmc3, dqdwyb, dkjg3, dqdwlxfs,
							hknf, yjfsbj, xysh, xxsh, fsdkze, fsdkzsf, fsdkshf,
							yffzxdkje, sqly, beizhu, xh, nd }, "xh||nd", xh
							+ nd, request);
		}
		sql = "select a.*, b.* from view_zbdx_xsjbxx a, zbdx_dkqrs b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";

		request.setAttribute("map", dao.getMapNotOut(sql,
				new String[] { xh, nd }));
		return mapping.findForward("dkqrs");
		//			}

	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 国家助学贷款申请 报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String nd = Base.currNd;

		String sql = "select a.*, b.* from view_zbdx_gjdksq a, zbdx_gjzxdk b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";

		request.setAttribute("map", dao.getMapNotOut(sql,
				new String[] { xh, nd }));
		return mapping.findForward("gjzxdkPrint");
		//		}

	}

	/**
	 * @describe 贷款确认书的打印功能
	 */
	public ActionForward dkqrsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.

		String xh = Base.chgNull(request.getParameter("xh"), "", 0);
		String nd = Base.currNd;

		String sql = "select a.*, b.* from view_zbdx_xsjbxx a, zbdx_gjzxdk b where a.xh=b.xh(+) and a.xh=? and b.nd(+)=?";

		request.setAttribute("map", dao.getMapNotOut(sql,
				new String[] { xh, nd }));
		return mapping.findForward("dkqrsPrint");
		//		}

	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 国家助学贷款审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkqrsShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long time = System.currentTimeMillis();
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		//StudentForm studentForm = (StudentForm) form;
		HttpSession session = request.getSession();
		StudentForm studentForm = (StudentForm) form;
		String userDep = session.getAttribute("userDep").toString();
		String zy = studentForm.getZydm();
		String bj = studentForm.getBjdm();
		//			String shxm = DealString.toGBK(request.getParameter("shxm")).trim();
		String sql = "select dqxn,dqnd from xtszb where rownum=1";
		Map<String, String> map = dao.getMapNotOut(sql, null);
		String xn = map.get("dqxn");
		String nd = map.get("dqnd");
		studentForm.setXn(xn);
		studentForm.setNd(nd);
		String xy = Base.chgNull(studentForm.getXy(), "", 0);
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		StringBuffer querry = new StringBuffer(" where 1=1 ");

		querry.append("and nd = '" + nd + "' ");

		String nj = Base.chgNull(studentForm.getNj(), "", 0);
		String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String sfzh = Base.chgNull(studentForm.getSfzh(), "", 0);

		if (nj == "") {
		} else {
			querry.append("and nj = '" + nj + "' ");
		}
		if (xy == "") {
		} else {
			querry.append("and xydm = '" + xy + "' ");
		}
		if (zy == "") {
		} else {
			querry.append("and zydm = '" + zy + "' ");
		}
		if (bj == "") {
		} else {
			querry.append("and bjdm = '" + bj + "' ");
		}
		if (xh == "") {
		} else {
			querry.append("and xh = '" + xh + "' ");
		}
		if (sfzh == "") {
		} else {
			querry.append("and sfzh = '" + sfzh + "' ");
		}
		Vector<String[]> rs = null;
		int rsNum = 0;

		sql = "select 'lstdsq' shxmdm,'贷款确认书' shxmmc from dual";
		List shxmList = dao.getListNotOut(sql, null);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("userType", userType);

		if (userType.equalsIgnoreCase("xx")) {
			sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " 主键, a.xymc, a.zymc, a.bjmc, a.xm, a.xb, a.xxsh from(select "
					+ "xh||nd"
					+ " 主键,a.* from "
					+ "view_zbdx_dkqrs"
					+ " a"
					+ querry + " and xysh='通过'" + " order by xxsh desc) a";
		} else {
			xy = userDep;
			studentForm.setXy(xy);
			sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " 主键, a.xymc, a.zymc, a.bjmc, a.xm, a.xb, a.xysh from(select "
					+ "xh||nd"
					+ " 主键,a.* from "
					+ "view_zbdx_dkqrs"
					+ " a"
					+ querry + " and xydm='" + xy + "' order by xysh desc) a";
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = dao.rsToVatorNotOutCN(sql, null, "view_zbdx_dkqrs");
			if (rs != null) {
				rsNum = rs.size() - 1;
			}
		}

		if (userType.equalsIgnoreCase("xx")) {
			request.setAttribute("writeAble", "yes");
		} else if (userType.equalsIgnoreCase("xy")) {
			request.setAttribute("writeAble", "no");
		}

		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("tableName", "view_zbdx_dkqrs");
		request.setAttribute("realTable", "zbdx_dkqrs");

		request.setAttribute("xnList", dao.getNdList());// 发送年级列表
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum + "");
		System.out.println("每次对连接不关闭所消耗的时间："
				+ (System.currentTimeMillis() - time));
		return mapping.findForward("dkqrsShList");
		//		}

	}

	@SuppressWarnings("unchecked")
	public ActionForward gjzxdkShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		String zy = studentForm.getZydm();
		String bj = studentForm.getBjdm();
		String xn = Base.currXn;
		String nd = Base.currNd;
		String sql = "";
		studentForm.setXn(xn);
		studentForm.setNd(nd);
		String xy = studentForm.getXy();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());

		StringBuffer querry = new StringBuffer(" where 1=1 ");

		querry.append("and nd = '" + nd + "' ");

		String nj = Base.chgNull(studentForm.getNj(), "", 0);
		String xh = Base.chgNull(studentForm.getXh(), "", 0);
		String sfzh = Base.chgNull(studentForm.getSfzh(), "", 0);

		if (null == nj || "" == nj) {
		} else {
			querry.append("and nj = '" + nj + "' ");
		}
		if (null == xy || "" == xy) {
		} else {
			querry.append("and xydm = '" + xy + "' ");
		}
		if (null == zy || "" == zy) {
		} else {
			querry.append("and zydm = '" + zy + "' ");
		}
		if (null == bj || "" == bj) {
		} else {
			querry.append("and bjdm = '" + bj + "' ");
		}
		if (null == xh || "" == xh) {
		} else {
			querry.append("and xh = '" + xh + "' ");
		}
		if (null == sfzh || "" == sfzh) {
		} else {
			querry.append("and sfzh = '" + sfzh + "' ");
		}
		Vector<String[]> rs = null;
		int rsNum = 0;

		sql = "select 'lstdsq' shxmdm,'国家助学贷款' shxmmc from dual";
		List shxmList = dao.getListNotOut(sql, null);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("userType", userType);

		if (userType.equalsIgnoreCase("xx")) {
			sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " 主键, a.xymc, a.zymc, a.bjmc, a.xm, a.xb, a.xxsh from(select "
					+ "xh||nd"
					+ " 主键,a.* from "
					+ "view_zbdx_gjzxdk"
					+ " a"
					+ querry + " and xysh='通过'" + " order by xxsh desc) a";
		} else {
			studentForm.setXy(userDep);
			sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " 主键, a.xymc, a.zymc, a.bjmc, a.xm, a.xb, a.xysh from(select "
					+ "xh||nd"
					+ " 主键,a.* from "
					+ "view_zbdx_gjzxdk"
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "'"
					+ " order by xysh desc) a";
			xy = userDep;
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = dao.rsToVatorNotOutCN(sql, null, "view_zbdx_gjzxdk");
			if (null != rs) {
				rsNum = rs.size() - 1;
				if (rsNum > 0) {
					request.setAttribute("rs", rs);
				}
			}
		}

		if (userType.equalsIgnoreCase("xx")) {
			request.setAttribute("writeAble", "yes");
		} else if (userType.equalsIgnoreCase("xy")) {
			request.setAttribute("writeAble", "no");
		}

		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("userType", userType);
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("tableName", "view_zbdx_gjzxdk");
		request.setAttribute("realTable", "zbdx_gjzxdk");
		request.setAttribute("xnList", dao.getNdList());// 发送年级列表
		request.setAttribute("rsNum", rsNum + "");
		return mapping.findForward("gjzxdkShList");
		//		}

	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 审核信息的更新
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkShSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		StudentForm studentForm = (StudentForm) form;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());

		String nd = studentForm.getNd();
		String xh = Base.chgNull(studentForm.getXh(), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);

		if (userType.equalsIgnoreCase("xx")) { // 保存学校审核信息
			StandardOperation.update("zbdx_gjzxdk", new String[] { "xxsh",
					"xxshyj" }, new String[] { xxsh, xxshyj }, "xh||nd", xh
					+ nd, request);
		} else { // 保存学院审核信息
			StandardOperation.update("zbdx_gjzxdk", new String[] { "xysh",
					"xyshyj" }, new String[] { xysh, xyshyj }, "xh||nd", xh
					+ nd, request);
		}
		request.setAttribute("userType", userType);
		return null;//mapping.findForward("gjzxdkShList");
		//		}
	}

	public ActionForward dkqrsShSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.

		StudentForm studentForm = (StudentForm) form;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());

		String nd = studentForm.getNd();
		String xh = Base.chgNull(studentForm.getXh(), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);

		if (userType.equalsIgnoreCase("xx")) { // 保存学校审核信息
			StandardOperation.update("zbdx_dkqrs", new String[] { "xxsh",
					"xxshyj" }, new String[] { xxsh, xxshyj }, "xh||nd", xh
					+ nd, request);
		} else { // 保存学院审核信息
			StandardOperation.update("zbdx_dkqrs", new String[] { "xysh",
					"xyshyj" }, new String[] { xysh, xyshyj }, "xh||nd", xh
					+ nd, request);
		}
		request.setAttribute("userType", userType);
		return null;//mapping.findForward("gjzxdkShList");
		//		}
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 审核信息的查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGjzxdkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { //如果要对这个模块进行权限控制，则需要在GNMKDMB和yhqxb中建立数据
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		//			StudentForm studentForm = (StudentForm) form;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);

		String sql = "select a.*, b.* from view_zbdx_gjdksq a, zbdx_gjzxdk b where a.xh=b.xh(+) and b.xh||b.nd=?";

		request.setAttribute("map", dao.getMapNotOut(sql,
				new String[] { pkVal }));
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);

		return mapping.findForward("gjzxdkSh");
		//		}

	}

	public ActionForward getDkqrsOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { //如果要对这个模块进行权限控制，则需要在GNMKDMB和yhqxb中建立数据
		DAO dao = DAO.getInstance();
		//		String url = "xszz.do?method=gjzxdk";
		//		if (this.power(mapping, request, url) != null) {// 权限控制代码
		//			request.setAttribute("errMsg", "对不起，您目前没有权限！");
		//			return this.power(mapping, request, url);
		//		} else {
		// 真正的功能实现在这里.
		//			StudentForm studentForm = (StudentForm) form;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);

		String sql = "select a.*, b.* from view_zbdx_xsjbxx a, zbdx_dkqrs b where a.xh=b.xh(+) and b.xh||b.nd=?";
		HashMap map = new HashMap();
		map = dao.getMapNotOut(sql, new String[] { pkVal });
		request.setAttribute("map", map);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);

		return mapping.findForward("dkqrsSh");
		//		}

	}

}