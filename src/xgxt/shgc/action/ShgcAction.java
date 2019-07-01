package xgxt.shgc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.shgc.service.HzjyServiceDao;
import xgxt.utils.GetTime;
import xgxt.utils.RowidToPk;

public class ShgcAction extends Action {
	private String userName;

	private String userType;

	private String userOnline;

	private String userDep;

	boolean isStu = false;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;

		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myAfw = new ActionForward();
			// DAO dao = DAO.getInstance();
			// HttpSession session = request.getSession();
			userName = session.getAttribute("userName").toString();
			userType = session.getAttribute("userType").toString();
			userOnline = session.getAttribute("userOnLine").toString();
			userDep = session.getAttribute("userDep").toString();
			if (userOnline.equalsIgnoreCase("student")) {
				request.setAttribute("userOnline", "student");
				isStu = true;
			}
			String myPar = mapping.getParameter();

			if (myPar.equalsIgnoreCase("HZJYXSSQ")) { // 非合作教育学生申请
				myAfw = hzjyxssq(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqxx")) { // 学生查询本人申请信息
				myAfw = hzjysqxx(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("HZJYSH")) { // 合作教育申请查询
				myAfw = hzjysh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqsjsz")) { // 合作教育申请时间设置
				myAfw = hzjysjsz(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqxysh")) { // 合作教育申请学院审核
				myAfw = hzjysqxysh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqxxsh")) { // 合作教育申请学校审核（批量）
				myAfw = hzjysqxxsh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqviewmore")) { // 合作教育申请查看详细信息
				myAfw = hzjysqviewmore(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjysqxxupdate")) { // 合作教育申请修改
				myAfw = hzjysqxxupdate(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyDataExport")) { // 合作教育数据导出
				myAfw = hzjyDataExport(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyxxwh")) { // 合作教育协调员维护页面打开-删除-查询
				myAfw = hzjyxtyxxwh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyxxapply")) { // 合作教育协调员增加
				myAfw = hzjyxtyxxapply(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xtyxxViewmore")) { // 合作教育协调员详细信息
				myAfw = xtyxxViewmore(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xtyxxupdate")) { // 合作教育协调员信息修改
				myAfw = xtyxxupdate(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyxxexport")) { // 合作教育协调员信息导出
				myAfw = hzjyDataExport(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtypipei")) { // 合作教育协调员匹配
				myAfw = hzjyxtypipei(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazapply")) { // 安置信息登记
				myAfw = hzjyazapply(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazaquery")) { // 安置信息查询
				myAfw = hzjyazaquery(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("viewazxxinfo")) { // 安置信息详细内容查看
				myAfw = viewazxxinfo(mapping, form, request, response);

			} else if (myPar.equalsIgnoreCase("hzjyazupdate")) { // 雇主信息申请变更
				myAfw = hzjyazupdate(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazupdatequery")) { // 雇主信息变更查询
				myAfw = hzjyazupdatequery(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazupdatemorequery")) { // 雇主信息变更详细内容查看
				myAfw = hzjyazupdatemorequery(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazupdatexysh")) { // 雇主信息变更学院审核
				myAfw = hzjyazupdatexysh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazupdatexxsh")) { // 雇主信息变更学校审核
				myAfw = hzjyazupdatexxsh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyazxyupdate")) { // 雇主信息变更学院修改
				myAfw = hzjyazxyupdate(mapping, form, request, response);	
				
				
			} else if (myPar.equalsIgnoreCase("xsazbgxtyresult")) { // 雇主信息变更审核结果查询
				myAfw = xsazbgxtyresult(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xsazbgxtyresultupdate")) { // 雇主信息变更审核结果修改
				myAfw = xsazbgxtyresultupdate(mapping, form, request, response);

			} else if (myPar.equalsIgnoreCase("hzjyazupdatequerystuinfo")) { // 弹出学生信息查询窗口
				myAfw = hzjyazupdatequerystuinfo(mapping, form, request,
						response);

			} else if (myPar.equalsIgnoreCase("xsazxysh")) { // 安置信息学院审核
				myAfw = xsazxysh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xsazxxsh")) { // 安置信息学校审核
				myAfw = xsazxxsh(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xsazsturesult")) { // 学生安置结果查询
				myAfw = xsazsturesult(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xsazresultupdate")) { // 学生安置结果修改
				myAfw = xsazresultupdate(mapping, form, request, response);

			} else if (myPar.equalsIgnoreCase("xtyglscoreset")) { // 协调员管理评分参数设定
				myAfw = xtyglscoreset(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyglscoreinput")) { // 协调员管理学生成绩评定
				myAfw = hzjyxtyglscoreinput(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyglscorequery")) { // 协调员管理学生成绩查询
				myAfw = hzjyxtyglscorequery(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyglscoremorequery")) { // 协调员管理学生成绩详细查看
				myAfw = hzjyxtyglscoremorequery(mapping, form, request,
						response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyglscoreupdate")) { // 协调员管理学生成绩修改
				myAfw = hzjyxtyglscoreupdate(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyxtyglstuqueryscore")) { // 协调员管理学生查看成绩
				myAfw = hzjyxtyglstuqueryscore(mapping, form, request, response);

			} else if (myPar.equalsIgnoreCase("hzjyXtyglGzjlInput")) { // 协调员管理工作记录维护
				myAfw = hzjyXtyglGzjlInput(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyXtyglGzjlUpdate")) { // 协调员管理工作记录修改
				myAfw = hzjyXtyglGzjlUpdate(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyXtyglGzjlQuery")) { // 协调员管理工作记录查询
				myAfw = hzjyXtyglGzjlQuery(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("hzjyXtyglGzjlViewmore")) { // 协调员管理工作记录详细
				myAfw = hzjyXtyglGzjlViewmore(mapping, form, request, response);
			} else if (myPar.equalsIgnoreCase("xsjbxxinput")) {
				myAfw = xsjbxxinput(mapping, form, request, response, userType); // 学生基本信息输入
			} else if (myPar.equalsIgnoreCase("xsjbxxquery")) {
				myAfw = xsjbxxquery(mapping, form, request, response, userType); // 学生基本信息查询
			} else if (myPar.equalsIgnoreCase("xsjbxxViewMore")) {
				myAfw = xsjbxxViewMore(mapping, form, request, response); // 学生详细信息查询
			} else if (myPar.equalsIgnoreCase("xsjbxxUpdate")) {
				myAfw = xsjbxxUpdate(mapping, form, request, response); // 学生信息修改
			} else if (myPar.equalsIgnoreCase("hzjysjdsz")) {
				myAfw = hzjysjdsz(mapping, form, request, response); // 合作教育时间段设置
			} else if (myPar.equalsIgnoreCase("hzjysjdszViewMore")) {
				myAfw = hzjysjdszViewMore(mapping, form, request, response); // 合作教育时间段设置详细查看
			} else if (myPar.equalsIgnoreCase("hzjyStuGrxxQuery")) {
				myAfw = hzjyStuGrxxQuery(mapping, form, request, response); // 合作教育学生个人信息查询
			} else if (myPar.equalsIgnoreCase("hzjyXtyglGzjlAllinput")) {
				myAfw = hzjyXtyglGzjlAllinput(mapping, form, request, response); // 合作教育协调员工作记录批量维护
			} else if (myPar.equalsIgnoreCase("hzjyXtyglDjxsQuery")) {
				myAfw = hzjyXtyglDjxsQuery(mapping, form, request, response); // 合作教育协调员代教学生查询
			} else if (myPar.equalsIgnoreCase("hzjyazxxquery")) {
				myAfw = hzjyazxxquery(mapping, form, request, response); // 合作教育安置信息查询
			} else if (myPar.equalsIgnoreCase("xsjbxxpllr")) {
				myAfw = xsjbxxpllr(mapping, form, request, response,userType); // 合作教育学生信息批量录入
			} else if (myPar.equalsIgnoreCase("hzjyhistory")) {
				myAfw = azxx_history(mapping, form, request, response); // 合作教育学生安置信息转历史
			} else if (myPar.equalsIgnoreCase("hzjyazxxhistory")) {
				myAfw = hzjyazxxhistory(mapping, form, request, response); // 合作教育安置信息查询
			} else if (myPar.equalsIgnoreCase("setxtyxx")) {
				myAfw = setxty(mapping, form, request, response); // 合作教育设置当前协调员
			} else if (myPar.equalsIgnoreCase("scoreoperate")){//带教学生成绩维护
				myAfw = scoreOperate(mapping, form, request, response);
			}
			return myAfw;

		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	// 合作教育 学生申请
	private ActionForward hzjyXtyglGzjlAllinput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String xn = Base.currXn;
		String pkstring = request.getParameter("pkstring");
		map.put("pkstring", pkstring);
		HttpSession session = request.getSession();
		String username = session.getAttribute("userName").toString();
		String[] xtyinfo = dao.getOneRs(
				"select xtyxm,xtydm from hzjy_xtyxxb where xtyyhm=?",
				new String[] { username }, new String[] { "xtyxm", "xtydm" });
		String xtyxm = "";
		String xtydm = "";
		if (null != xtyinfo) {
			xtyxm = xtyinfo[0];
			xtydm = xtyinfo[1];
		}

		// 批量插入
		if ("inputall".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String gzfs = DealString.toGBK(request.getParameter("gzfs"));
			String sj = DealString.toGBK(request.getParameter("sj"));
			String js = DealString.toGBK(request.getParameter("js"));
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				String xm = dao.getOneRs(
						"select name from hzjy_xsjbxxb where xh=?",
						new String[] { whichxh }, "name");
				judge = StandardOperation.insert("hzjy_xtygzjlb",
						new String[] { "xh", "xm", "xn", "xtyxm", "xtydm",
								"gzfs", "sj", "js" }, new String[] { whichxh,
								xm, xn, xtyxm, xtydm, gzfs, sj, js }, request);
			}
			if (judge) {
				request.setAttribute("allinput", "ok");
			} else {
				request.setAttribute("allinput", "no");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 合作教育 学生申请
	private ActionForward hzjyxssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HttpSession session = request.getSession(false);
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String userLx = session.getAttribute("userOnLine") == null?"":session.getAttribute("userOnLine").toString();
		String bmdm = session.getAttribute("userDep") == null?"":session.getAttribute("userDep").toString();
		String[] timeVals = null;
		request.setAttribute("realTable", "hzjysqb");
		sql = "select * from hzjysjszb where xn=? and xq=? and fwbz=?";
		if(userLx.equals("teacher")){
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,"-100"},
					new String[]{"sqkssj", "sqjssj" });
		}else{
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,bmdm}, 
					new String[] {"sqkssj", "sqjssj" });
		}		
		String rightTime = dao.getOneRs(
				"select to_char(sysdate,'yyyymmdd') rtime from dual",
				new String[] {}, new String[] { "rtime" })[0];

		if ("save".equalsIgnoreCase(doType)) {

			sql = "select dqxn,dqxq,dqnd from xtszb";
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, new String[] {
					"dqxn", "dqxq", "dqnd" });

			String tableName = "hzjysqb";
			String xn = "";
			String xq = "";
			String nd = "";
			if (null != stuinfo) {
				xn = stuinfo[0];
				xq = stuinfo[1];
				nd = stuinfo[2];
			}

			String xh = request.getParameter("xh");
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String bzxm = DealString.toGBK(request.getParameter("bzxm"));
			String bzrxm = DealString.toGBK(request.getParameter("bzrxm"));
			String jtdh = DealString.toGBK(request.getParameter("jtdh"));
			String sjh = DealString.toGBK(request.getParameter("sjh"));
			String sqly = DealString.toGBK(request.getParameter("sqly"));

			sql = "select count(*) from hzjysqb where xh=?";
			String[] stuinfo2 = dao.getOneRs(sql, new String[] { xh },
					new String[] { "count(*)" });
			sql = "select * from hzjysqb where xh=? and nd=?";
			String[] stuinfo3 = dao.getOneRs(sql, new String[] { xh, nd },
					new String[] { "xh" });

			int timeint = 0;
			if (null != stuinfo2) {
				timeint = Integer.parseInt(stuinfo2[0]);
			}
			boolean judge = false;

			sql = "select xh from hzjy_xsjbxxb where xh=?";

			String stuxh = dao.getOneRs(sql, new String[] { xh }, "xh");
			if (null != stuxh && !"".equals(stuxh)) {
				request.setAttribute("have", "yes");
			} else {
				if (null == stuinfo3) {
					if (!(timeint > 2)) {

						judge = StandardOperation.insert(tableName,
								new String[] { "xn", "xq", "nd", "xh", "xymc",
										"bjmc", "xm", "bzxm", "bzrxm", "jtdh",
										"sjh", "sqly" }, new String[] { xn, xq,
										nd, xh, xymc, bjmc, xm, bzxm, bzrxm,
										jtdh, sjh, sqly }, request);
					}
				}
				if (judge) {
					request.setAttribute("insert", "ok");
				} else {
					request.setAttribute("insert", "no");
				}
			}
		}
		if (timeVals !=null && (timeVals[0].compareTo(rightTime) <= 0)
				&& (timeVals[1].compareTo(rightTime) >= 0)) {
			if (userOnline.equalsIgnoreCase("teacher")) {
				request.setAttribute("sfksq", true);// 可以申请
				request.setAttribute("userType", "teacher");
				request.setAttribute("rs", map);
				return mapping.findForward("success");
			} else {
				request.setAttribute("userType", "student");
				String xh = userName;
				sql = "select xh,xymc,bjmc,xm from view_xsjbxx where xh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { xh },
						new String[] { "xh", "xymc", "bjmc", "xm" });
				if (null != stuinfo) {
					map.put("xh", stuinfo[0]);
					map.put("xymc", stuinfo[1]);
					map.put("bjmc", stuinfo[2]);
					map.put("xm", stuinfo[3]);
				}
				request.setAttribute("rs", map);
				request.setAttribute("sfksq", true);// 可以申请
				return mapping.findForward("success");
			}
		} else if (timeVals ==null || (timeVals[0].compareTo(rightTime) > 0)
				|| (timeVals[1].compareTo(rightTime) < 0)) {
			request.setAttribute("sfksq", false);
			return mapping.findForward("success");
		}
		return mapping.findForward("false");
	}

	// 学生申请本人查询—删除
	private ActionForward hzjysqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		// HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "view_hzjysh"; // 查询信息源表（多为视图名）
		String realTable = "hzjysqb"; // 合作教育申请表
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String xxsh = "";
		String xysh = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if (!("stu".equals(userType))) {
			return mapping.findForward("false");
		}

		if ("stu".equals(userType)) {
			if ("del".equals(doType2)) {
				sql = "select xxsh,xysh from " + tableName + " where " + pk
						+ " =?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "xxsh", "xysh" });
				if (null != stuinfo) {
					xxsh = stuinfo[0];
					xysh = stuinfo[1];
				}
				if ("未审核".equals(xxsh) && "未审核".equals(xysh)) {
					boolean judge = false;
					judge = StandardOperation.delete(realTable, pk, pkValue,
							request);
					if (judge) {
						request.setAttribute("delete", "ok");
					} else {
						request.setAttribute("delete", "no");
					}
				} else {
					request.setAttribute("delete", "over");
				}
			}
			if ("query".equals(doType)) {
				sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
						+ " a where " + pk + " =?";
				colList = new String[] { "主键", "行号", "nd", "xm", "xh", "xymc",
						"bjmc", "sqsj", "xyshimg", "xxshimg" };
				colListCN = dao.getColumnNameCN(colList, tableName);
				List topTr = dao.arrayToList(colList, colListCN);
				rs.addAll(dao
						.rsToVator(sql, new String[] { userName }, colList));
				request.setAttribute("topTr", topTr);
			}
		}

		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// 学生申请本人查看并修改
	private ActionForward hzjysqxxupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjysh";
		String realTable = "hzjysqb"; // 合作教育申请表
		String xxsh = "";
		String xysh = "";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		// String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("update".equals(doType)) {
			sql = "select xxsh,xysh from " + tableName + " where " + pk + " =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xxsh", "xysh" });
			if (null != stuinfo) {
				xxsh = stuinfo[0];
				xysh = stuinfo[1];
			}
			String bzxm = DealString.toGBK(request.getParameter("bzxm"));
			String bzrxm = DealString.toGBK(request.getParameter("bzrxm"));
			String jtdh = DealString.toGBK(request.getParameter("jtdh"));
			String sjh = DealString.toGBK(request.getParameter("sjh"));
			String sqly = DealString.toGBK(request.getParameter("sqly"));
			String btgyy = "注：该申请已修改并重新提交！";
			if ("未通过X".equals(xxsh) || "未通过X".equals(xysh)
					|| ("未审核".equals(xxsh) && "未审核".equals(xysh))) {
				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"bzxm", "bzrxm", "jtdh", "sjh", "sqly", "btgyy",
						"xxsh", "xxshr", "xysh", "xyshr" }, new String[] {
						bzxm, bzrxm, jtdh, sjh, sqly, btgyy, "未审核", "", "未审核",
						"" }, pk, userName, request);
				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			} else {
				request.setAttribute("update", "over");
			}
		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			if ("update".equals(doType)) {
				pkValue = userName;
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
			String time = map.get("sqsj");
			if (null != time) {
				String aftertime = dao.doForTime(time);
				map.put("sqsj", aftertime);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生申请审核-查询-删除-批量
	private ActionForward hzjysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String tableName = "view_hzjysh"; // 查询信息源表（多为视图名）
		String realTable = "hzjysqb"; // 合作教育申请表
		String rsNum = "0";// 返回的记录数

		String xh = request.getParameter("xh"); // 接收学号
		String xm = DealString.toGBK(request.getParameter("xm")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xydm = request.getParameter("xydm"); // 接收学院名称
		String zydm = request.getParameter("zydm"); // 接收专业名称
		String bjdm = request.getParameter("bjdm"); // 接收班级名称
		String xysh = DealString.toGBK(request.getParameter("xysh"));// 接收学院审核
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 接收学校审核
		String nd = request.getParameter("nd"); // 接收年度
		String pk = "xh"; // 主键
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				map.put("xydm", xydm);
			}
			request.setAttribute("who", "xy");
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);

				// 对学生基本表进行删除
				judge = StandardOperation.delete("hzjy_xsjbxxb", "xh", whichxh,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}
		// 学校批量审核通过
		if ("passall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"xxsh", "btgyy", "xxshr" }, new String[] { "已通过√", "",
						userName }, pk, whichxh, request);
				// 对学生基本表进行插入
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sfzh from view_xsjbxx where xh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { whichxh },
						new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
								"bjmc", "sfzh" });
				if (null != stuinfo) {
					String xn = Base.currXn;
					StandardOperation.insert("hzjy_xsjbxxb", new String[] {
							"xh", "name", "xb", "nj", "xymc", "zymc", "bjmc",
							"sfzh", "stutype", "xn" }, new String[] {
							stuinfo[0], stuinfo[1], stuinfo[2], stuinfo[3],
							stuinfo[4], stuinfo[5], stuinfo[6], stuinfo[7],
							"申请", xn }, request);
				}
				if (judge) {
					request.setAttribute("passall", "ok");
				} else {
					request.setAttribute("passall", "no");
				}
			}
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(realTable, pk, pkValue, request);
			// 对学生基本表进行删除
			StandardOperation.delete("hzjy_xsjbxxb", "xh", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("nd", nd);
			map.put("xysh", xysh);
			map.put("xxsh", xxsh);

		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");

		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((xm != null) && !xm.equals("")) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xydm != null) && !xydm.equals("")) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("' ");
		}
		if ((zydm != null) && !zydm.equals("")) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("' ");
		}
		if ((bjdm != null) && !bjdm.equals("")) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("' ");
		}
		if ((nd != null) && !nd.equals("")) {
			query.append(" and nd='");
			query.append(nd);
			query.append("' ");
		}
		if ((xysh != null) && !xysh.equals("")) {
			query.append(" and xysh='");
			query.append(xysh);
			query.append("' ");
		}
		if ((xxsh != null) && !xxsh.equals("")) {
			query.append(" and xxsh='");
			query.append(xxsh);
			query.append("' ");
		}

		// if ((xh == null) || xh.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xh like '%" + xh + "%' ";
		// }
		//		
		//		
		// if ((xm == null) || xm.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xm like '%" + xm + "%' ";
		// }
		//		
		//		
		// if ((xb == null) || xb.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and xb='" + xb + "' ";
		// }
		//		
		// if ((xydm == null) || xydm.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and xydm='" + xydm + "' ";
		// }
		//		
		//		
		// if ((zydm == null) || zydm.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and zydm='" + zydm + "' ";
		// }
		// if ((bjdm == null) || bjdm.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and bjdm='" + bjdm + "' ";
		// }
		//		
		//		
		//		
		// if ((nd == null) || nd.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and nd='" + nd + "' ";
		// }
		// if ((xysh == null) || xysh.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and xysh='" + xysh + "' ";
		// }
		// if ((xxsh == null) || xxsh.equals("")) {
		// querry += " and 1=1 ";
		// } else {
		// querry += " and xxsh='" + xxsh + "' ";
		// }

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ query.toString();
		colList = new String[] { "主键", "行号", "nd", "xm", "xh", "xymc", "bjmc",
				"sqsj", "xyshimg", "xxshimg" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");

	}

	// 学生申请学院审核
	private ActionForward hzjysqxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjysh";
		String realTable = "hzjysqb";
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("xyshenhe".equals(doType)) {
			if ("未审核".equals(xysh)) {
				btgyy = "";
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xysh",
					"xyshr", "btgyy" }, new String[] { xysh, userName, btgyy },
					pk, request.getParameter("xh"), request);
			if (judge) {
				request.setAttribute("xyshenhe", "ok");
			} else {
				request.setAttribute("xyshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xyshenhe".equals(doType)) {
				pkValue = request.getParameter("xh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
			String time = map.get("sqsj");
			if (null != time) {
				String aftertime = dao.doForTime(time);
				map.put("sqsj", aftertime);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生申请学校审核
	private ActionForward hzjysqxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjysh";
		String realTable = "hzjysqb";
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("xxshenhe".equals(doType)) {
			if ("未审核".equals(xxsh)) {
				if (!("未通过X".equals(xysh))) {
					btgyy = "";
				}
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xxsh",
					"xxshr", "btgyy" }, new String[] { xxsh, userName, btgyy },
					pk, request.getParameter("xh"), request);
			if (judge) {
				request.setAttribute("xxshenhe", "ok");
				if ("已通过√".equals(xxsh)) {
					pkValue = request.getParameter("xh");
					sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sfzh from view_xsjbxx where xh=?";
					String[] stuinfo = dao.getOneRs(sql,
							new String[] { pkValue }, new String[] { "xh",
									"xm", "xb", "nj", "xymc", "zymc", "bjmc",
									"sfzh" });
					if (null != stuinfo) {
						String xn = Base.currXn;
						judge = StandardOperation.insert("hzjy_xsjbxxb",
								new String[] { "xh", "name", "xb", "nj",
										"xymc", "zymc", "bjmc", "sfzh",
										"stutype", "xn" }, new String[] {
										stuinfo[0], stuinfo[1], stuinfo[2],
										stuinfo[3], stuinfo[4], stuinfo[5],
										stuinfo[6], stuinfo[7], "申请", xn },
								request);
					}
				} else {
					pkValue = request.getParameter("xh");
					judge = StandardOperation.delete("hzjy_xsjbxxb", pk,
							pkValue, request);
				}
			} else {
				request.setAttribute("xxshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xxshenhe".equals(doType)) {
				pkValue = request.getParameter("xh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
			String time = map.get("sqsj");
			if (null != time) {
				String aftertime = dao.doForTime(time);
				map.put("sqsj", aftertime);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生申请详细信息查看
	private ActionForward hzjysqviewmore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjysh";
		String act = request.getParameter("act");

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
			String time = map.get("sqsj");
			if (null != time) {
				String aftertime = dao.doForTime(time);
				map.put("sqsj", aftertime);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 协调员信息维护 - 查询 - 删除
	private ActionForward hzjyxtyxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "view_xtyxxb";
		String realTable = "hzjy_xtyxxb";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		String pk = "xtydm"; // 主键
		String pkValue = request.getParameter("pkValue"); // 主键值

		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String rsNum = "0";// 返回的记录数

		String xtydm = request.getParameter("xtydm"); // 协调员代码
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm")); // 接收姓名
		String xtyyb = request.getParameter("xtyyb"); // 协调员邮编
		String xtyxb = DealString.toGBK(request.getParameter("xtyxb"));// 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));// 协调员区域

		HashMap<String, String> map = new HashMap<String, String>();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				String bmdm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
				String[] bminfo = dao.getOneRs(sql, new String[] { bmdm },
						new String[] { "bmmc" });
				if (null != bminfo) {
					map.put("xymc", bminfo[0]);
				}
			}
			request.setAttribute("who", "xy");
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(realTable, pk, pkValue, request);

			// 删除学生基本信息表里的信息
			sql = "update hzjy_xsjbxxb set xtydm='', xtyxm='' , xtysjh='', xtylxdh='' where xtydm=?";
			dao.runUpdate(sql, new String[] { pkValue });
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);
				// 删除学生基本信息表里的信息
				sql = "update hzjy_xsjbxxb set xtydm='', xtyxm='' , xtysjh='', xtylxdh='' where xtydm=?";
				dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xtydm", xtydm);
			map.put("xtyxm", xtyxm);
			map.put("xtyyb", xtyyb);
			map.put("xtyxb", xtyxb);
			map.put("xymc", xymc);
			map.put("xtyqy", xtyqy);
		}

		if (xtydm == null) {
			xtydm = "";
		}
		if (xtyxm == null) {
			xtyxm = "";
		}
		if (xtyyb == null) {
			xtyyb = "";
		}
		if (xtyxb == null) {
			xtyxb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (xtyqy == null) {
			xtyqy = "";
		}
		if ((xtydm == null) || xtydm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xtydm like '%" + xtydm + "%' ";
		}
		if ((xtyxm == null) || xtyxm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xtyxm like '%" + xtyxm + "%' ";
		}
		if ((xtyyb == null) || xtyyb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyyb like'" + xtyyb + "%' ";
		}
		if ((xtyxb == null) || xtyxb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyxb='" + xtyxb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((xtyqy == null) || xtyqy.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyqy='" + xtyqy + "' ";
		}

		sql = "select " + pk + " ,rownum 行号,a.*,decode(a.xn,'"+Base.currXn+"','是','否') flag from " + realTable + " a "
				+ querry;
		colList = new String[] { "xtydm", "行号", "xtydm", "xtyyhm", "xtyxm",
				"xtyxb", "xymc", "xtyqy", "xtyyb", "xtylxdh", "xtysjh","flag" };
		colListCN = new String[] { "协调员代码", "行号", "协调员代码", "协调员用户名", "协调员姓名",
				"协调员性别", Base.YXPZXY_KEY+"名称", "协调员区域", "协调员邮编", "协调员联系电话", "协调员手机号","是否当前协调员" };
		//colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		return mapping.findForward("success");
	}

	// 协调员信息增加
	private ActionForward hzjyxtyxxapply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xtyxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		// String act = request.getParameter("act");
		@SuppressWarnings("unused")
		String xtydm = request.getParameter("xtydm");
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
		String xtyxb = DealString.toGBK(request.getParameter("xtyxb"));
		String xtylxdh = request.getParameter("xtylxdh");
		String xtysjh = request.getParameter("xtysjh");
		String xtyyb = request.getParameter("xtyyb");
		String xtyyhm = request.getParameter("xtyyhm");
		String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));
		String xtyjtzz = DealString.toGBK(request.getParameter("xtyjtzz"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String sql = "";

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("save".equals(doType)) {
			sql = "select xtydm from hzjy_xtyxxb where xtyyhm=? and xn=? and xymc=?";
			String[] xtydminfo = dao.getOneRs(sql, new String[] { xtyyhm,Base.currXn,xymc },
					new String[] { "xtydm" });
			if (null != xtydminfo) {
				request.setAttribute("insert", "duo");
				request.setAttribute("rs", map);
				request.setAttribute("xyList", dao.getXyList());
				return mapping.findForward("success");
			}
			String id = dao.getOneRs("select autoid.nextval vid from dual", new String[]{}, "vid");
			boolean judge = false;
			judge = StandardOperation.insert(realTable, new String[] { "xtydm",
					"xtyxm", "xtyxb", "xtylxdh", "xtysjh", "xtyyb", "xtyqy",
					"xtyjtzz", "xymc", "xtyyhm","xn" },
					new String[] { id, xtyxm, xtyxb, xtylxdh, xtysjh, xtyyb,
							xtyqy, xtyjtzz, xymc, xtyyhm,Base.currXn}, request);
			sql = "select yhm from yhb where yhm=?";
			String temp = dao.getOneRs(sql, new String[]{xtyyhm}, "yhm");
			String xydm = dao.getOneRs("select bmdm from zxbz_xxbmdm where bmmc=?", new String[]{xymc}, "bmdm");
			if(Base.isNull(temp)){
				sql = " insert into yhb (yhm,kl,zdm,xm,szbm,dwdm) values (?,?,?,?,?,?)";
				judge = dao.runUpdate(sql, new String[]{xtyyhm,"u","999999",xtyxm,xydm,"01"});
				sql = "insert into yhqxb(yhm,gnmkdm,dxq) select ?,gnmkdm,dxq from yhzqxb where zdm='999999'";
				judge = dao.runUpdate(sql, new String[]{xtyyhm});
			}else{
				sql = "insert into yhqxb(yhm,gnmkdm,dxq) select ?,a.gnmkdm,a.dxq from yhzqxb a where zdm='999999' and not exists (select gnmkdm from yhqxb where gnmkdm=a.gnmkdm and yhm=?)";
				judge = dao.runUpdate(sql, new String[]{xtyyhm,xtyyhm});
			}
			if (judge) {
				request.setAttribute("insert", "ok");
			} else {
				request.setAttribute("insert", "no");
			}
		}

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType)) {
			String xydm = session.getAttribute("userDep") == null?"":session.getAttribute("userDep").toString();
			sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
			String[] bminfo = dao.getOneRs(sql, new String[] { xydm },
					new String[] { "bmmc" });
			if (null != bminfo) {
				map.put("xymc", bminfo[0]);
			}
//			String dmb_xtydm = "";
//			String xtyxx_xtydm = "";
//			int lastxtydm = 0;
//			String bmmc = map.get("xymc");
//			sql = "select xtydm from hzjy_xtydmb where xymc=?";
//			String[] xtydminfo = dao.getOneRs(sql, new String[] { bmmc },
//					new String[] { "xtydm" });
//			if (xtydminfo != null) {
//				dmb_xtydm = xtydminfo[0];
//			}
//			sql = "select max(xtydm) from hzjy_xtyxxb where xymc=?";
//			String[] xtydm2info = dao.getOneRs(sql, new String[] { bmmc },
//					new String[] { "MAX(XTYDM)" });
//			if (null != xtydm2info[0]) {
//				xtyxx_xtydm = xtydm2info[0];
//				lastxtydm = Integer.valueOf(xtyxx_xtydm) + 1;
//			} else {
//				lastxtydm = Integer.valueOf(dmb_xtydm) + 1;
//			}
//			xtydm = String.valueOf(lastxtydm);
//			if (!("".equals(xtydm))) {
//				map.put("xtydm", xtydm);
//			}
			request.setAttribute("who", "xy");
		}

		request.setAttribute("rs", map);
		request.setAttribute("xyList", dao.getXyList());
		return mapping.findForward("success");
	}

	// 查看协调员详细信息

	private ActionForward xtyxxViewmore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xtyxxb";
		String pk = "xtydm";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 协调员信息修改
	private ActionForward xtyxxupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xtyxxb";
		String pk = "xtydm";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				String bmdm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
				String[] bminfo = dao.getOneRs(sql, new String[] { bmdm },
						new String[] { "bmmc" });
				if (null != bminfo) {
					map.put("xymc", bminfo[0]);
				}
			}
			request.setAttribute("who", "xy");
		}

		if ("update".equals(doType2)) {
			String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
			String xtyxb = DealString.toGBK(request.getParameter("xtyxb"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));
			String xtyjtzz = DealString.toGBK(request.getParameter("xtyjtzz"));
			String xtyyb = request.getParameter("xtyyb");
			String xtylxdh = request.getParameter("xtylxdh");
			String xtysjh = request.getParameter("xtysjh");
			@SuppressWarnings("unused")
			String xtyyhm = request.getParameter("xtyyhm");

			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xtyxm",
					"xtyxb", "xymc", "xtyqy", "xtyjtzz", "xtyyb", "xtylxdh",
					"xtysjh" }, new String[] { xtyxm, xtyxb, xymc,
					xtyqy, xtyjtzz, xtyyb, xtylxdh, xtysjh}, pk,
					pkValue, request);
			sql = "update hzjy_xsjbxxb set xtyxm=?,xtysjh=?,xtylxdh=? where xtydm=? and xn=?";
			dao.runUpdate(sql, new String[] { xtyxm, xtysjh, xtylxdh,
							pkValue,Base.currXn });

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 协调员与学生匹配
	private ActionForward hzjyxtypipei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String tableName = "hzjy_xsjbxxb";// 学生基础表
		// String tableName2 = "hzjy_xtyxxb";// 学生基础表
		String realTable = "hzjy_xsjbxxb";// 学生基础表
		String realTable2 = "view_xtyxxb";// 协调员视图
		HashMap<String, String> map = new HashMap<String, String>(); // 学生查询条件
		Vector<Object> rs = new Vector<Object>(); // 获得学生记录
		HashMap<String, String> mapxty = new HashMap<String, String>(); // 协调员查询条件
		Vector<Object> rsxty = new Vector<Object>(); // 获得协调员记录

		// 学生条件
		String xh = request.getParameter("xh");
		String name = DealString.toGBK(request.getParameter("name"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String qy = DealString.toGBK(request.getParameter("qy"));
		String kong = request.getParameter("kong");
		String xsxtyxm = DealString.toGBK(request.getParameter("xsxtyxm"));
		String dwyb = DealString.toGBK(request.getParameter("dwyb"));

		// 协调员条件
		String xtydm = request.getParameter("xtydm");
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
		String xtyxb = DealString.toGBK(request.getParameter("xtyxb"));
		String xtyyb = request.getParameter("xtyyb");
		String xymc2 = DealString.toGBK(request.getParameter("xymc2"));
		String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));

		// 操作类型
		String doType = request.getParameter("doType"); // 针对学生
		String doType2 = request.getParameter("doType2"); // 针对协调员
		String act = request.getParameter("act"); // 针对学生
		String act2 = request.getParameter("act2"); // 针对协调员
		String doType3 = request.getParameter("doType3"); // 进行匹配

		// 访问用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String rsNum = "0"; // 学生记录数
		String rsNum2 = "0"; // 协调员记录数
		String querry = " where 1=1 "; // 学生
		String querry2 = " where 1=1 "; // 协调员
		String pk = "xh"; // 学生学号
		String pk2 = "xtydm"; // 协调员代码
		// String pkValue = request.getParameter("pkValue"); // 学生学号的值
		// String pkValue2 = request.getParameter("pkValue2"); // 协调员代码的值
		String[] colList = null;
		String[] colListCN = null;
		String[] colList2 = null;
		String[] colListCN2 = null;
		String xydm = "";
		String zydm = "";

		// 传递区域列表
		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else {
			sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
			String[] bmmcinfo = dao.getOneRs(sql, new String[] { userDep },
					new String[] { "bmmc" });
			if (null != bmmcinfo) {
				xymc = bmmcinfo[0];
				xymc2 = bmmcinfo[0];
			}
			map.put("xymc", xymc);
			mapxty.put("xymc2", xymc2);
			request.setAttribute("who", "xy");
		}

		// 进行匹配
		if ("pipei".equals(doType3)) {
			String xtypipeidm = request.getParameter("pk2");
			String xtypipeixm = "";
			String xtypipeisjh = "";
			String xtypipeilxdh = "";
			sql = "select xtyxm,xtysjh,xtylxdh from hzjy_xtyxxb where xtydm=?";
			String[] infoxty = dao.getOneRs(sql, new String[] { xtypipeidm },
					new String[] { "xtyxm", "xtysjh", "xtylxdh" });
			if (null != infoxty) {
				xtypipeixm = infoxty[0];
				xtypipeisjh = infoxty[1];
				xtypipeilxdh = infoxty[2];
			}

			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.update(tableName, new String[] {
						"xtydm", "xtyxm", "xtysjh", "xtylxdh" }, new String[] {
						xtypipeidm, xtypipeixm, xtypipeisjh, xtypipeilxdh },
						pk, whichxh, request);

				if (judge) {
					request.setAttribute("pipei", "ok");
				} else {
					request.setAttribute("pipei", "no");
				}
			}
		}
		// 取消匹配
		if ("quxiaopipei".equals(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.update(tableName, new String[] {
						"xtydm", "xtyxm", "xtysjh", "xtylxdh" }, new String[] {
						"", "", "", "" }, pk, whichxh, request);
				if (judge) {
					request.setAttribute("quxiaopipei", "ok");
				} else {
					request.setAttribute("quxiaopipei", "no");
				}
			}
		}

		// 接受彼此信息
		String rsjudge = request.getParameter("rsjudge");
		String rsjudge2 = request.getParameter("rsjudge2");
		if ("you".equals(rsjudge)) {
			doType = "query";
			act = "go";
		}
		if ("you".equals(rsjudge2)) {
			doType2 = "query";
			act2 = "go";
		}

		// 返回学生查询条件
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("nj", nj);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("qy", qy);
			map.put("kong", kong);
			map.put("xsxtyxm", xsxtyxm);
			map.put("dwyb", dwyb);
		}

		sql = "select distinct xydm,xymc from view_njxyzybj where xymc=?";
		String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
				new String[] { "xydm" });
		if (null != xyinfo) {
			xydm = xyinfo[0];
		}
		sql = "select distinct zydm,zymc from view_njxyzybj where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		if (xh == null) {
			xh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (zymc == null) {
			zymc = "";
		}
		if (bjmc == null) {
			bjmc = "";
		}
		if (qy == null) {
			qy = "";
		}
		if (kong == null) {
			kong = "";
		}
		if (kong == null) {
			kong = "";
		}
		if (xsxtyxm == null) {
			xsxtyxm = "";
		}
		if (dwyb == null) {
			dwyb = "";
		}

		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xymc = '" + xymc + "'";
		}
		if ((nj == null) || nj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "'";
		}
		if ((zymc == null) || zymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zymc = '" + zymc + "' ";
		}
		if ((bjmc == null) || bjmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjmc = '" + bjmc + "' ";
		}
		if ((qy == null) || qy.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and qy = '" + qy + "' ";
		}
		if ((kong == null) || kong.equals("")) {
			querry += "and 1=1 ";
		} else if ("1".equals(kong)) {
			querry += "and xtydm is not null ";
		} else if ("2".equals(kong)) {
			querry += "and xtydm is null ";
		}
		if ((xsxtyxm == null) || xsxtyxm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xtyxm like '%" + xsxtyxm + "%' ";
		}
		if ((dwyb == null) || dwyb.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and yb = '" + dwyb + "' ";
		}
		querry += "and xn = '" + Base.currXn + "' ";
		sql = "select " + pk + " 主键,a.* from " + tableName + " a " + querry;
		colList = new String[] { "主键", "name", "xh", "gzdwdz", "qy", "yb",
				"xtyxm" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		// *****************协调员查询***********

		// 把上次提交的查询条件值传回去
		if ("query".equals(doType2)) {
			mapxty.put("xtydm", xtydm);
			mapxty.put("xtyxm", xtyxm);
			mapxty.put("xtyyb", xtyyb);
			mapxty.put("xtyxb", xtyxb);
			mapxty.put("xymc2", xymc2);
			mapxty.put("xtyqy", xtyqy);
		}

		if (xtydm == null) {
			xtydm = "";
		}
		if (xtyxm == null) {
			xtyxm = "";
		}
		if (xtyyb == null) {
			xtyyb = "";
		}
		if (xtyxb == null) {
			xtyxb = "";
		}
		if (xymc2 == null) {
			xymc2 = "";
		}
		if (xtyqy == null) {
			xtyqy = "";
		}
		if ((xtydm == null) || xtydm.equals("")) {
			querry2 += "and 1=1 ";
		} else {
			querry2 += "and xtydm like '%" + xtydm + "%' ";
		}
		if ((xtyxm == null) || xtyxm.equals("")) {
			querry2 += "and 1=1 ";
		} else {
			querry2 += "and xtyxm like '%" + xtyxm + "%' ";
		}
		if ((xtyyb == null) || xtyyb.equals("")) {
			querry2 += " and 1=1 ";
		} else {
			querry2 += " and xtyyb like'" + xtyyb + "%' ";
		}
		if ((xtyxb == null) || xtyxb.equals("")) {
			querry2 += " and 1=1 ";
		} else {
			querry2 += " and xtyxb='" + xtyxb + "' ";
		}
		if ((xymc2 == null) || xymc2.equals("")) {
			querry2 += " and 1=1 ";
		} else {
			querry2 += " and xymc='" + xymc2 + "' ";
		}
		if ((xtyqy == null) || xtyqy.equals("")) {
			querry2 += " and 1=1 ";
		} else {
			querry2 += " and xtyqy='" + xtyqy + "' ";
		}
		querry2 += " and xn='" + Base.currXn + "' ";
		sql = "select " + pk2 + " ,a.* from " + realTable2 + " a " + querry2;
		colList2 = new String[] { "xtydm", "xtyxm", "xtyqy", "xtyyb", "xtygzl" };
		colListCN2 = dao.getColumnNameCN(colList2, realTable2);
		List topTr2 = dao.arrayToList(colList2, colListCN2);
		if ("go".equals(act2)) {
			rsxty.addAll(dao.rsToVator(sql, new String[] {}, colList2));
			if (rsxty == null) {
				rsNum2 = "0";
			} else {
				rsNum2 = String.valueOf(rsxty.size());
			}
		}

		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		request.setAttribute("realTable", realTable);
		request.setAttribute("realTable2", realTable2);
		request.setAttribute("topTr", topTr);
		request.setAttribute("topTr2", topTr2);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rsNum2", rsNum2);
		request.setAttribute("rs1", map);
		request.setAttribute("rsxty1", mapxty);
		request.setAttribute("rs", rs);
		request.setAttribute("rsxty", rsxty);
		return mapping.findForward("success");
	}

	// 学生安置信息登记
	private ActionForward hzjyazapply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession(false);
		String doType = request.getParameter("doType");
		String tableName = "hzjy_xsjbxxb";
		String realTable = "hzjy_xsazxxb";
		String pk = "xh";
		String pkValue = request.getParameter("xh");
		String xn = Base.currXn; // 当前学年

		// 查询是否为合法登记时间
		String userLx = session.getAttribute("userOnLine") == null?"":session.getAttribute("userOnLine").toString();
		String bmdm = session.getAttribute("userDep") == null?"":session.getAttribute("userDep").toString();
		String[] timeVals = null;
		sql = "select * from hzjysjszb where xn=? and xq=? and fwbz=?";
		if(userLx.equals("teacher")){
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,"-100"},
					new String[]{"xsazsqkssj", "xsazsqjssj" });
		}else{
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,bmdm}, 
					new String[] {"xsazsqkssj", "xsazsqjssj" });
		}
		String rightTime = dao.getOneRs(
				"select to_char(sysdate,'yyyymmdd') rtime from dual",
				new String[] {}, new String[] { "rtime" })[0];

		if (timeVals != null && (timeVals[0].compareTo(rightTime) <= 0)
				&& (timeVals[1].compareTo(rightTime) >= 0)) {
			request.setAttribute("sfkssq", "ok");
		} else {
			request.setAttribute("sfkssq", "no");
		}

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		String userType = session.getAttribute("userType").toString();

		if ("stu".equals(userType)) {
			pkValue = session.getAttribute("userName").toString();
			sql = "select * from view_xsjbxx where " + pk + "='" + pkValue
					+ "'";
			String[] colList = dao
					.getColumnName("select * from view_xsjbxx where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("name", map.get("xm"));
			}
		}

		if ("save".equals(doType)) {
			String xh = DealString.toGBK(request.getParameter("xh"));// 学号
			String xm = DealString.toGBK(request.getParameter("name"));// 姓名
			String jtdh = request.getParameter("jtdh"); // 家庭电话
			String sjh = request.getParameter("sjh"); // 手机号
			String gzdwqc = DealString.toGBK(request.getParameter("gzdwqc"));// 工作单位全称
			String gzdwdz = DealString.toGBK(request.getParameter("gzdwdz")); // 工作单位地址
			String qy = DealString.toGBK(request.getParameter("qy")); // 工作单位区域
			String yb = request.getParameter("yb"); // 邮编
			String lxr = DealString.toGBK(request.getParameter("lxr")); // 联系人
			String gzbm = DealString.toGBK(request.getParameter("gzbm")); // 工作部门
			String lxrdh = request.getParameter("lxrdh"); // 联系人电话
			String lxrsjh = request.getParameter("lxrsjh"); // 联系人手机号
			String hzjykssj = request.getParameter("hzjykssj");
			String hzjyjssj = request.getParameter("hzjyjssj");

			// 判断是否属于合作教育学生
			sql = "select sfdjaz from " + tableName + " where xh=? and xn=?";
			String sfdjaz2 = "";
			String[] sfdjinfo = dao.getOneRs(sql, new String[] { pkValue, xn },
					new String[] { "sfdjaz" });

			// 判断是否已经在登记表中
			sql = "select xh from " + realTable + " where xh=? and xn=?";
			String sfdjazinfo = dao.getOneRs(sql, new String[] { pkValue, xn },
					"xh");

			// 判断合作教育要求后与页面交互
			if (null != sfdjinfo) {
				sfdjaz2 = sfdjinfo[0];
			} else {
				request.setAttribute("nopower", "nopower");// 不属于合作教育学生
				request.setAttribute("rs", map);
				return mapping.findForward("success");
			}

			// 进行保存操作
			if ("".equals(sfdjazinfo) || null == sfdjazinfo) {
				if ("否".equals(sfdjaz2) || "".equals(sfdjaz2)) {
					boolean judge = false;
					judge = StandardOperation.insert(realTable,
							new String[] { "xh", "xm", "jtdh", "sjh", "gzdwqc",
									"gzdwdz", "qy", "yb", "lxr", "gzbm",
									"lxrdh", "lxrsjh", "xn" }, new String[] {
									xh, xm, jtdh, sjh, gzdwqc, gzdwdz, qy, yb,
									lxr, gzbm, lxrdh, lxrsjh, xn }, request);
					judge = StandardOperation.update(tableName, new String[]{"hzjykssj","hzjyjssj"},
							new String[]{hzjykssj,hzjyjssj}, new String[]{"xh","xn"}, new String[]{pkValue, xn }, request);
					if (judge) {
						request.setAttribute("save", "ok");
					} else {
						request.setAttribute("save", "no");
					}
				}
			} else {
				request.setAttribute("havemessage", "yes");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 安置信息审核
	private ActionForward hzjyazaquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String tableName = "view_hzjyaz"; // 查询信息源表（多为视图名）
		String realTable = "hzjy_xsazxxb"; // 学生安置信息表
		String realTable1 = "hzjy_xsjbxxb"; // 合作教育学生基本信息表
		String rsNum = "0";// 返回的记录数
		String xn = Base.currXn;// 学年
		String xh = request.getParameter("xh"); // 接收学号
		String lrqk = request.getParameter("lrqk"); // 是否录入安置信息
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String xydm = "";
		String zydm = "";
		String azxysh = DealString.toGBK(request.getParameter("azxysh"));// 接收学院审核
		String azxxsh = DealString.toGBK(request.getParameter("azxxsh"));// 接收学校审核
		String nj = request.getParameter("nj"); // 接收年级
		String pk = "xh"; // 主键
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
			sql = "select xydm from view_njxyzybj where xymc=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
			request.setAttribute("who", "xy");
		}

		// sql = "select bmdm from zxbz_xxbmdm where bmmc=?";
		// String[] bminfo = dao.getOneRs(sql, new String[] { xymc },
		// new String[] { "bmdm" });
		// if (null != bminfo) {
		// xydm = bminfo[0];
		// }

		sql = "select zydm from bks_zydm where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);

				// 对学生安置表

				judge = StandardOperation.delete(realTable, new String[] {
						"xh", "xn" }, new String[] { whichxh, xn }, request);

				// 清空基础表中相关信息
				StandardOperation.update(realTable1, new String[] { "jtdh",
						"sjh", "gzdwqc", "gzdwdz", "qy", "yb", "lxr", "gzbm",
						"lxrdh", "lxrsjh", "sfdjaz" }, new String[] { "", "",
						"", "", "", "", "", "", "", "", "否" }, pk, whichxh,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}
		// 学校批量审核通过
		if ("passall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"xxsh", "btgyy", "xxshr" }, new String[] { "已通过√", "",
						userName }, pk, whichxh, request);
				// 对学生基本表进行插入
				sql = "select  jtdh, sjh, gzdwqc,gzdwdz, qy, yb, lxr, gzbm,lxrdh, lxrsjh from view_hzjyaz where xh=? and xn=?";
				String[] stuinfo = dao.getOneRs(sql,
						new String[] { whichxh, xn }, new String[] { "jtdh",
								"sjh", "gzdwqc", "gzdwdz", "qy", "yb", "lxr",
								"gzbm", "lxrdh", "lxrsjh" });
				if (null != stuinfo) {
					StandardOperation.update("hzjy_xsjbxxb", new String[] {
							"jtdh", "sjh", "gzdwqc", "gzdwdz", "qy", "yb",
							"lxr", "gzbm", "lxrdh", "lxrsjh", "sfdjaz" },
							new String[] { stuinfo[0], stuinfo[1], stuinfo[2],
									stuinfo[3], stuinfo[4], stuinfo[5],
									stuinfo[6], stuinfo[7], stuinfo[8],
									stuinfo[9], "是" }, new String[] { "xh",
									"xn" }, new String[] { whichxh, xn },
							request);
				}
				if (judge) {
					request.setAttribute("passall", "ok");
				} else {
					request.setAttribute("passall", "no");
				}
			}
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(realTable, new String[] { "xh",
					"xn" }, new String[] { pkValue, xn }, request);
			// 对学生基本表进行清清理
			StandardOperation.update(realTable1, new String[] { "jtdh", "sjh",
					"gzdwqc", "gzdwdz", "qy", "yb", "lxr", "gzbm", "lxrdh",
					"lxrsjh", "sfdjaz" }, new String[] { "", "", "", "", "",
					"", "", "", "", "", "否" }, new String[] { "xh", "xn" },
					new String[] { pkValue, xn }, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("azxysh", azxysh);
			map.put("azxxsh", azxxsh);
			map.put("lrqk", lrqk);
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");

		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		
		if ((azxysh != null) && !azxysh.equals("")) {
			query.append(" and xysh='");
			query.append(azxysh);
			query.append("' ");
		}
		if ((azxxsh != null) && !azxxsh.equals("")) {
			query.append(" and xxsh='");
			query.append(azxxsh);
			query.append("' ");
		}
		if(lrqk != null && lrqk.equals("ylr")){
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
					+ query.toString() + " and xn='" + xn + "'";
			colList = new String[] { "主键", "行号", "nj", "name", "xh", "xymc",
					"bjmc", "qy", "xyshimg", "xxshimg" };
			colListCN = dao.getColumnNameCN(colList, tableName);
		}else{
			sql = "select rownum 行号,a.* from hzjy_xsjbxxb a "+ query.toString()+" and not exists (select xh from hzjy_xsazxxb where xh=a.xh) ";
			colList = new String[] {"行号", "xh", "name", "nj", "xymc","zymc","bjmc"};
			colListCN = dao.getColumnNameCN(colList, "hzjy_xsjbxxb");
			if(lrqk != null){
				request.setAttribute("view", "no");
			}
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");

	}
	//安置信息查询
	private ActionForward hzjyazxxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String tableName = "view_hzjyaz"; // 查询信息源表（多为视图名）
		String realTable = "hzjy_xsazxxb"; // 学生安置信息表
		String rsNum = "0";// 返回的记录数
		String xn = request.getParameter("xn");// 学年
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String doType = request.getParameter("doType");
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String xydm = "";
		String zydm = "";
		String azxysh = DealString.toGBK(request.getParameter("azxysh"));// 接收学院审核
		String azxxsh = DealString.toGBK(request.getParameter("azxxsh"));// 接收学校审核
		String nj = request.getParameter("nj"); // 接收年级
		String pk = "xh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
			sql = "select xydm from view_njxyzybj where xymc=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
			request.setAttribute("who", "xy");
		}
		sql = "select zydm from bks_zydm where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}
//		 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("azxysh", azxysh);
			map.put("azxxsh", azxxsh);
		}
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		if ((xn != null) && !xn.equals("")) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("' ");
		}
		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		if ((azxxsh != null) && !azxxsh.equals("")) {
			query.append(" and xxsh='");
			query.append(azxxsh);
			query.append("' ");
		}
		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ query.toString();
		colList = new String[] { "主键", "行号", "xh", "name", "xsdh", "gzdwqc",
				"dwlxdh", "lxr","hzjyqzsj"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xnndList", dao.getXnndList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		return mapping.findForward("success");

	}
	
	
	// 安置信息学院学校修改
	private ActionForward hzjyazxyupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjyaz";
		String realTable ="hzjy_xsazxxb";
		String realTable2 ="hzjy_xsjbxxb";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String xn = Base.currXn;

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}
		
		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);
		
		if("update".equalsIgnoreCase(doType)){
			
			String xh = pkValue;
			String jtdh = DealString.toGBK(request.getParameter("jtdh"));
			String sjh = DealString.toGBK(request.getParameter("sjh"));
			String gzdwqc = DealString.toGBK(request.getParameter("gzdwqc"));
			String gzdwdz = DealString.toGBK(request.getParameter("gzdwdz"));
			String qy = DealString.toGBK(request.getParameter("dqmc"));
			String yb = DealString.toGBK(request.getParameter("yb"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gzbm = DealString.toGBK(request.getParameter("gzbm"));
			String lxrdh = DealString.toGBK(request.getParameter("lxrdh"));
			String lxrsjh = DealString.toGBK(request.getParameter("lxrsjh"));
			String hzjykssj = DealString.toGBK(request.getParameter("hzjykssj"));
			String hzjyjssj = DealString.toGBK(request.getParameter("hzjyjssj"));
			
			
			boolean judge = false;
			
			StandardOperation.update(realTable, new String[]{
					"jtdh","sjh","gzdwqc","gzdwdz","qy","yb","lxr","gzbm","lxrdh","lxrsjh"
			}, new String[]{
					jtdh,sjh,gzdwqc,gzdwdz,qy,yb,lxr,gzbm,lxrdh,lxrsjh
			}, new String[]{"xh","xn"}, new String[]{xh,xn}, request);
			
			
			judge=StandardOperation.update(realTable2, new String[]{
					"jtdh","sjh","gzdwqc","gzdwdz","qy","yb","lxr","gzbm","lxrdh","lxrsjh"
			}, new String[]{
					jtdh,sjh,gzdwqc,gzdwdz,qy,yb,lxr,gzbm,lxrdh,lxrsjh
			}, new String[]{"xh","xn"}, new String[]{xh,xn}, request);
			judge=StandardOperation.update("hzjy_xsjbxxb", new String[]{
					"hzjykssj","hzjyjssj"}, new String[]{hzjykssj,hzjyjssj}, 
					new String[]{"xh","xn"}, new String[]{xh,xn}, request);
			
			if(judge){
				request.setAttribute("update", "ok");
			}else{
				request.setAttribute("update", "no");
			}
			
		}
	

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "' and xn='"+xn+"'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					map.put("dqmc", map.get("qy"));
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
		

	}
	
	
	
	
	
	

	// 安置信息登记详细内容查看
	private ActionForward viewazxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = "view_hzjyaz";
		String act = request.getParameter("act");
		if(!Base.isNull(doType) && doType.equals("history")){
			pk = "xn||xh";
			tableName = "HZJY_XSAZXXLSB";
		}
		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		request.setAttribute("rs", map);
		if(!Base.isNull(doType) && doType.equals("history")){
			return mapping.findForward("history");
		}else{
			return mapping.findForward("success");
		}
	}

	// 学生安置学校审核
	private ActionForward xsazxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjyaz";
		String realTable = "hzjy_xsazxxb";
		String xn = Base.currXn;
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("xxshenhe".equals(doType)) {
			if ("未审核".equals(xxsh)) {
				if (!("未通过X".equals(xysh))) {
					btgyy = "";
				}
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xxsh",
					"xxshr", "btgyy" }, new String[] { xxsh, userName, btgyy },
					pk, request.getParameter("xh"), request);
			if (judge) {
				request.setAttribute("xxshenhe", "ok");
				if ("已通过√".equals(xxsh)) {
					pkValue = request.getParameter("xh");
					sql = "select jtdh,sjh,gzdwqc,gzdwdz,qy,yb,lxr,gzbm,lxrdh,lxrsjh from view_hzjyaz where xh=? and xn=?";
					String[] stuinfo = dao.getOneRs(sql, new String[] {
							pkValue, xn }, new String[] { "jtdh", "sjh",
							"gzdwqc", "gzdwdz", "qy", "yb", "lxr", "gzbm",
							"lxrdh", "lxrsjh" });
					if (null != stuinfo) {
						judge = StandardOperation.update("hzjy_xsjbxxb",
								new String[] { "jtdh", "sjh", "gzdwqc",
										"gzdwdz", "qy", "yb", "lxr", "gzbm",
										"lxrdh", "lxrsjh", "sfdjaz" },
								new String[] { stuinfo[0], stuinfo[1],
										stuinfo[2], stuinfo[3], stuinfo[4],
										stuinfo[5], stuinfo[6], stuinfo[7],
										stuinfo[8], stuinfo[9], "是" }, pk,
								pkValue, request);
					}
				} else {
					pkValue = request.getParameter("xh");
					judge = StandardOperation.update("hzjy_xsjbxxb",
							new String[] { "jtdh", "sjh", "gzdwqc", "gzdwdz",
									"qy", "yb", "lxr", "gzbm", "lxrdh",
									"lxrsjh", "sfdjaz" }, new String[] { "",
									"", "", "", "", "", "", "", "", "", "否" },
							new String[] { "xh", "xn" }, new String[] {
									pkValue, xn }, request);
				}
			} else {
				request.setAttribute("xxshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xxshenhe".equals(doType)) {
				pkValue = request.getParameter("xh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生安置学院审核
	private ActionForward xsazxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjyaz";
		String realTable = "hzjy_xsazxxb";
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("xyshenhe".equals(doType)) {
			if ("未审核".equals(xysh)) {
				btgyy = "";
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xysh",
					"xyshr", "btgyy" }, new String[] { xysh, userName, btgyy },
					pk, request.getParameter("xh"), request);
			if (judge) {
				request.setAttribute("xyshenhe", "ok");
			} else {
				request.setAttribute("xyshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xyshenhe".equals(doType)) {
				pkValue = request.getParameter("xh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生安置审核结果查看
	private ActionForward xsazsturesult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sql = "";
		DAO dao = DAO.getInstance();
		String pk = "xh";
		String xn = Base.currXn;
		String tableName = "view_hzjyaz";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>();

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		if (!"stu".equals(userType)) {
			return mapping.findForward("false");
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
				+ " a where xh=? and xn=?";
		colList = new String[] { "主键", "行号", "nj", "name", "xh", "xymc",
				"bjmc", "qy", "xyshimg", "xxshimg" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] { userName, xn }, colList));

		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// 安置信息结果修改
	private ActionForward xsazresultupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjyaz";
		String realTable = "hzjy_xsazxxb";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("update".equals(doType)) {
			pkValue = request.getParameter("xh");
			xn = request.getParameter("xn");
			if (("已通过√".equals(xysh) && !("未通过X").equals(xxsh))
					|| "已通过√".equals(xxsh)) {
				request.setAttribute("pass", "ok");
			} else {
				String jtdh = DealString.toGBK(request.getParameter("jtdh"));
				String sjh = DealString.toGBK(request.getParameter("sjh"));
				String gzdwqc = DealString
						.toGBK(request.getParameter("gzdwqc"));
				String gzdwdz = DealString
						.toGBK(request.getParameter("gzdwdz"));
				String qy = DealString.toGBK(request.getParameter("qy"));
				String yb = DealString.toGBK(request.getParameter("yb"));
				String lxr = DealString.toGBK(request.getParameter("lxr"));
				String lxrdh = DealString.toGBK(request.getParameter("lxrdh"));
				String lxrsjh = DealString
						.toGBK(request.getParameter("lxrsjh"));
				String gzbm = DealString.toGBK(request.getParameter("gzbm"));

				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"jtdh", "sjh", "gzdwqc", "gzdwdz", "qy", "yb", "lxr",
						"lxrdh", "lxrsjh", "gzbm", "xysh", "xxsh", "btgyy",
						"xyshr", "xxshr" }, new String[] { jtdh, sjh, gzdwqc,
						gzdwdz, qy, yb, lxr, lxrdh, lxrsjh, gzbm, "未审核", "未审核",
						"注：该信息已被学生修改并重新提交！", "", "" }, new String[] { "xh",
						"xn" }, new String[] { pkValue, xn }, request);

				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			}
		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "' and xn='" + xn + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 安置信息申请变更-输入
	private ActionForward hzjyazupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String xn = Base.currXn;
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		String pk = "xh";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tableName = "hzjy_xsjbxxb";
		String realTable = "HZJY_AZXXBGB";
		String act = request.getParameter("act");

		// 查询是否为合法申请时间
		HttpSession session = request.getSession(false);
		String bmdm = session.getAttribute("userDep") == null?"":session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType") == null?"":session.getAttribute("userType").toString();
		String[] timeVals = null;
		sql = "select * from hzjysjszb where xn=? and xq=? and fwbz=?";	
		if(userType.equals("xx") || userType.equals("admin")){
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,"-100"},
					new String[]{"bgkssj", "bgjssj" });
		}else{
			timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,bmdm},
					new String[]{"bgkssj", "bgjssj" });
		}
		if(userType.equals("stu")){
			if(Base.isNull(act)){
				act = "go";
			}
			pkValue = session.getAttribute("userName") == null?"":session.getAttribute("userName").toString();
		}
		String rightTime = dao.getOneRs(
				"select to_char(sysdate,'yyyymmdd') rtime from dual",
				new String[] {}, new String[] { "rtime" })[0];

		if (!(timeVals !=null && (timeVals[0].compareTo(rightTime) <= 0) && (timeVals[1]
				.compareTo(rightTime) >= 0))) {
			return mapping.findForward("false");
		}
		
		if ("save".equals(act)) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String jtdh = DealString.toGBK(request.getParameter("jtdh"));
			String sjh = DealString.toGBK(request.getParameter("sjh"));
			String gzdwqc = DealString.toGBK(request.getParameter("gzdwqc"));
			String gzdwdz = DealString.toGBK(request.getParameter("gzdwdz"));
			String qy = DealString.toGBK(request.getParameter("qy"));
			String yb = DealString.toGBK(request.getParameter("yb"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gzbm = DealString.toGBK(request.getParameter("gzbm"));
			String lxrdh = DealString.toGBK(request.getParameter("lxrdh"));
			String lxrsjh = DealString.toGBK(request.getParameter("lxrsjh"));

			String jtdh2 = DealString.toGBK(request.getParameter("jtdh2"));
			String sjh2 = DealString.toGBK(request.getParameter("sjh2"));
			String gzdwqc2 = DealString.toGBK(request.getParameter("gzdwqc2"));
			String gzdwdz2 = DealString.toGBK(request.getParameter("gzdwdz2"));
			String qy2 = DealString.toGBK(request.getParameter("qy2"));
			String yb2 = DealString.toGBK(request.getParameter("yb2"));
			String lxr2 = DealString.toGBK(request.getParameter("lxr2"));
			String gzbm2 = DealString.toGBK(request.getParameter("gzbm2"));
			String lxrdh2 = DealString.toGBK(request.getParameter("lxrdh2"));
			String lxrsjh2 = DealString.toGBK(request.getParameter("lxrsjh2"));

			String jtdhmark = DealString
					.toGBK(request.getParameter("jtdhmark"));
			String sjhmark = DealString.toGBK(request.getParameter("sjhmark"));
			String gzdwqcmark = DealString.toGBK(request
					.getParameter("gzdwqcmark"));
			String gzdwdzmark = DealString.toGBK(request
					.getParameter("gzdwdzmark"));
			String qymark = DealString.toGBK(request.getParameter("qymark"));
			String ybmark = DealString.toGBK(request.getParameter("ybmark"));
			String lxrmark = DealString.toGBK(request.getParameter("lxrmark"));
			String gzbmmark = DealString
					.toGBK(request.getParameter("gzbmmark"));
			String lxrdhmark = DealString.toGBK(request
					.getParameter("lxrdhmark"));
			String lxrsjhmark = DealString.toGBK(request
					.getParameter("lxrsjhmark"));

			if (!"on".equals(jtdhmark)) {
				jtdh2 = "";
			}
			if (!"on".equals(sjhmark)) {
				sjh2 = "";
			}
			if (!"on".equals(gzdwqcmark)) {
				gzdwqcmark = "";
			}
			if (!"on".equals(gzdwdzmark)) {
				gzdwdzmark = "";
			}
			if (!"on".equals(qymark)) {
				qymark = "";
			}
			if (!"on".equals(ybmark)) {
				ybmark = "";
			}
			if (!"on".equals(lxrmark)) {
				lxrmark = "";
			}
			if (!"on".equals(gzbmmark)) {
				gzbmmark = "";
			}
			if (!"on".equals(lxrdhmark)) {
				lxrdhmark = "";
			}
			if (!"on".equals(lxrsjhmark)) {
				lxrsjhmark = "";
			}

			sql = "select * from hzjy_azxxbgb where xxsh='未通过X' or xxsh='未审核' and xh=? and xn=? ";
			String stuinfo = dao.getOneRs(sql, new String[] { xh, xn }, "xxsh");

			if (null != stuinfo && !"".equals(stuinfo)) {
				request.setAttribute("nopass", "nopass");
				request.setAttribute("rs", map);
				return mapping.findForward("success");
			}

			boolean judge = false;
			judge = StandardOperation.insert(realTable, new String[] { "xh",
					"xn", "jtdh", "sjh", "gzdwqc", "gzdwdz", "qy", "yb", "lxr",
					"gzbm", "lxrdh", "lxrsjh", "jtdh2", "sjh2", "gzdwqc2",
					"gzdwdz2", "qy2", "yb2", "lxr2", "gzbm2", "lxrdh2",
					"lxrsjh2", "jtdhmark", "sjhmark", "gzdwqcmark",
					"gzdwdzmark", "qymark", "ybmark", "lxrmark", "gzbmmark",
					"lxrdhmark", "lxrsjhmark" }, new String[] { xh, xn, jtdh,
					sjh, gzdwqc, gzdwdz, qy, yb, lxr, gzbm, lxrdh, lxrsjh,
					jtdh2, sjh2, gzdwqc2, gzdwdz2, qy2, yb2, lxr2, gzbm2,
					lxrdh2, lxrsjh2, jtdhmark, sjhmark, gzdwqcmark, gzdwdzmark,
					qymark, ybmark, lxrmark, gzbmmark, lxrdhmark, lxrsjhmark },
					request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			act = "go";
		}
		if ("go".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "' and xn='" + xn + "' and sfdjaz='是'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			} else {
				request.setAttribute("nocheckin", "nocheckin");
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	// 安置信息申请变更-查询
	private ActionForward hzjyazupdatequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "view_azxxbgb"; // 查询信息源表（多为视图名）
		String realTable = "hzjy_azxxbgb"; // 学生安置变更表
		String rsNum = "0";// 返回的记录数
		String xn = Base.currXn;// 学年
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String xydm = "";
		String zydm = "";
		String xysh = DealString.toGBK(request.getParameter("xysh"));// 接收学院审核
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 接收学校审核
		String nj = request.getParameter("nj"); // 接收年级
		String pk = "rowid"; // 主键
		String pkValue = request.getParameter("pkValue");

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

//		if ("xx".equals(userType) || "admin".equals(userType)) {
//			request.setAttribute("who", "xx");
//		} else if ("xy".equals(userType)) {
//			sql = "select szbm from yhb where yhm =?";
//			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
//					new String[] { "szbm" });
//			if (null != stuinfo) {
//				xydm = stuinfo[0];
//				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
//				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
//				map.put("xydm", xydm);
//			}
//			request.setAttribute("who", "xy");
//		} 
		sql = "select distinct xtydm from hzjy_xtyxxb where xtyyhm=? and xn=?";
		String[] xtydm = dao.getRs(sql, new String[]{userName,Base.currXn}, "xtydm");
		
		sql = "select zydm from bks_zydm where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete(realTable, pk, whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation
					.delete(realTable, new String[] { "rowid" },
							new String[] { pkValue }, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("xysh", xysh);
			map.put("xxsh", xxsh);

		}

		if (xh == null) {
			xh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (zymc == null) {
			zymc = "";
		}
		if (bjmc == null) {
			bjmc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (xysh == null) {
			xysh = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((zymc == null) || zymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and zymc='" + zymc + "' ";
		}
		if ((bjmc == null) || bjmc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bjmc='" + bjmc + "' ";
		}
		if ((nj == null) || nj.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nj='" + nj + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}
		if(xtydm!=null && xtydm.length>0){
			if(xtydm.length ==1){
				querry += " and xtydm='" + xtydm[0] + "' ";
			}else{
				querry += " and ( ";
				for(int i=0;i<xtydm.length;i++){
					if(i == xtydm.length-1){
						querry += " xtydm='" + xtydm[i] + "') ";
					}else{
						querry += " xtydm='" + xtydm[i] + "' or ";
					}
				}
			}
		}else{
			querry += " and 1=2 ";
		}
		sql = "select rowid,rownum 行号,a.* from " + tableName + " a " + querry
				+ " and xn='" + xn + "'";
		colList = new String[] { "rowid", "行号", "nj", "name", "xh", "xymc",
				"bjmc", "qy", "xxshimg" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");
	}

	// 查看雇主信息变更详细信息
	private ActionForward hzjyazupdatemorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_azxxbgb";
		String act = request.getParameter("act");

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}

				request.setAttribute("jtdhmarkcolor", "on".equals(map
						.get("jtdhmark")) ? "B5D4F7" : "");
				request.setAttribute("sjhmarkcolor", "on".equals(map
						.get("sjhmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwqcmarkcolor", "on".equals(map
						.get("gzdwqcmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwdzmarkcolor", "on".equals(map
						.get("gzdwdzmark")) ? "B5D4F7" : "");
				request.setAttribute("qymarkcolor", "on".equals(map
						.get("qymark")) ? "B5D4F7" : "");
				request.setAttribute("ybmarkcolor", "on".equals(map
						.get("ybmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrmarkcolor", "on".equals(map
						.get("lxrmark")) ? "B5D4F7" : "");
				request.setAttribute("gzbmmarkcolor", "on".equals(map
						.get("gzbmmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrdhmarkcolor", "on".equals(map
						.get("lxrdhmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrsjhmarkcolor", "on".equals(map
						.get("lxrsjhmark")) ? "B5D4F7" : "");

			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 雇主信息修改学院审核
	private ActionForward hzjyazupdatexysh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_azxxbgb";
		String realTable = "hzjy_azxxbgb";
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("xyshenhe".equals(doType)) {
			if ("未审核".equals(xysh)) {
				btgyy = "";
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xysh",
					"xyshr", "btgyy" }, new String[] { xysh, userName, btgyy },
					pk, request.getParameter("rowid"), request);
			if (judge) {
				request.setAttribute("xyshenhe", "ok");
			} else {
				request.setAttribute("xyshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xyshenhe".equals(doType)) {
				pkValue = request.getParameter("rowid");
			}
			sql = "select a.rowid,a.* from " + tableName + " a where " + pk
					+ "='" + pkValue + "'";
			String[] colList = dao.getColumnName("select a.rowid,a.* from "
					+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				request.setAttribute("jtdhmarkcolor", "on".equals(map
						.get("jtdhmark")) ? "B5D4F7" : "");
				request.setAttribute("sjhmarkcolor", "on".equals(map
						.get("sjhmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwqcmarkcolor", "on".equals(map
						.get("gzdwqcmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwdzmarkcolor", "on".equals(map
						.get("gzdwdzmark")) ? "B5D4F7" : "");
				request.setAttribute("qymarkcolor", "on".equals(map
						.get("qymark")) ? "B5D4F7" : "");
				request.setAttribute("ybmarkcolor", "on".equals(map
						.get("ybmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrmarkcolor", "on".equals(map
						.get("lxrmark")) ? "B5D4F7" : "");
				request.setAttribute("gzbmmarkcolor", "on".equals(map
						.get("gzbmmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrdhmarkcolor", "on".equals(map
						.get("lxrdhmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrsjhmarkcolor", "on".equals(map
						.get("lxrsjhmark")) ? "B5D4F7" : "");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	// 雇主信息修改学校审核
	private ActionForward hzjyazupdatexxsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_azxxbgb";
		String realTable = "hzjy_azxxbgb";
		String realTable2 = "hzjy_xsjbxxb";
		// String xn = Base.currXn;
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("xxshenhe".equals(doType)) {
			if ("未审核".equals(xxsh)) {
				if (!("未通过X".equals(xysh))) {
					btgyy = "";
				}
				userName = "";
			}
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xxsh",
					"xxshr", "btgyy" }, new String[] { xxsh, userName, btgyy },
					pk, request.getParameter("rowid"), request);
			if (judge) {

				if ("已通过√".equals(xxsh)) {
					pkValue = request.getParameter("rowid");
					sql = "select * from view_azxxbgb where rowid=?";
					String[] stuinfo = dao.getOneRs(sql,
							new String[] { pkValue }, new String[] {
									"jtdhmark", "sjhmark", "gzdwqcmark",
									"gzdwdzmark", "qymark", "ybmark",
									"lxrmark", "gzbmmark", "lxrdhmark",
									"lxrsjhmark", "jtdh2", "sjh2", "gzdwqc2",
									"gzdwdz2", "qy2", "yb2", "lxr2", "gzbm2",
									"lxrdh2", "lxrsjh2", "xh", "xn" });

					if (null != stuinfo) {
						String[] allname = { "jtdh", "sjh", "gzdwqc", "gzdwdz",
								"qy", "yb", "lxr", "gzbm", "lxrdh", "lxrsjh" };
						String[] updatewhich = new String[] {};
						String[] updateinfo = new String[] {};
						String updatewhich1 = "";
						String updateinfo1 = "";
						for (int i = 0; i < 10; i++) {
							if ("on".equals(stuinfo[i])) {
								updatewhich1 += allname[i] + "!##!";
								updateinfo1 += stuinfo[i + 10] + "!##!";
							}
						}
						updatewhich = updatewhich1.split("!##!");
						updateinfo = updateinfo1.split("!##!");

						StandardOperation.update(realTable2, updatewhich,
								updateinfo, new String[] { "xh", "xn" },
								new String[] { stuinfo[20], stuinfo[21] },
								request);
					}
				}
				request.setAttribute("xxshenhe", "ok");
			} else {
				request.setAttribute("xxshenhe", "no");
			}
		}

		if (act.equalsIgnoreCase("view")) {
			// 查询数据
			if ("xxshenhe".equals(doType)) {
				pkValue = request.getParameter("rowid");
			}
			sql = "select a.rowid,a.* from " + tableName + " a where " + pk
					+ "='" + pkValue + "'";
			String[] colList = dao.getColumnName("select a.rowid,a.* from "
					+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				request.setAttribute("jtdhmarkcolor", "on".equals(map
						.get("jtdhmark")) ? "B5D4F7" : "");
				request.setAttribute("sjhmarkcolor", "on".equals(map
						.get("sjhmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwqcmarkcolor", "on".equals(map
						.get("gzdwqcmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwdzmarkcolor", "on".equals(map
						.get("gzdwdzmark")) ? "B5D4F7" : "");
				request.setAttribute("qymarkcolor", "on".equals(map
						.get("qymark")) ? "B5D4F7" : "");
				request.setAttribute("ybmarkcolor", "on".equals(map
						.get("ybmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrmarkcolor", "on".equals(map
						.get("lxrmark")) ? "B5D4F7" : "");
				request.setAttribute("gzbmmarkcolor", "on".equals(map
						.get("gzbmmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrdhmarkcolor", "on".equals(map
						.get("lxrdhmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrsjhmarkcolor", "on".equals(map
						.get("lxrsjhmark")) ? "B5D4F7" : "");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}
	

	
	
	

	// 雇主信息变更审核结果查看
	private ActionForward xsazbgxtyresult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sql = "";
		DAO dao = DAO.getInstance();
		String pk = "rowid";
		String xn = Base.currXn;
		String tableName = "view_azxxbgb";
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String xydm = "";
		String zydm = "";
		String xysh = DealString.toGBK(request.getParameter("xysh"));// 接收学院审核
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 接收学校审核
		String nj = request.getParameter("nj"); // 接收年级
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		if("xy".equals(userType)){
			sql = "select xymc from view_njxyzybj a,yhb b where a.xydm=b.szbm and yhm=?";
			xymc = dao.getOneRs(sql, new String[]{userName}, "xymc");
			map.put("xymc", xymc);
		}
		String rsNum = "";
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("xysh", xysh);
			map.put("xxsh", xxsh);

		}

		if (xh == null) {
			xh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (zymc == null) {
			zymc = "";
		}
		if (bjmc == null) {
			bjmc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (xysh == null) {
			xysh = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((zymc == null) || zymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and zymc='" + zymc + "' ";
		}
		if ((bjmc == null) || bjmc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bjmc='" + bjmc + "' ";
		}
		if ((nj == null) || nj.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nj='" + nj + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}
		List topTr = new ArrayList<HashMap<String,String>>();
		if ("go".equals(act)) {
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
			+ " a " + querry + " and xn=?";
			colList = new String[] { "主键", "行号", "nj", "name", "xh", "xymc",
					"bjmc", "qy", "xxshimg" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[] {xn}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");
	}

	// 雇主信息结果修改
	private ActionForward xsazbgxtyresultupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String sql = "";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_azxxbgb";
		String realTable = "hzjy_azxxbgb";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String lx = request.getParameter("lx");
//		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
//		String xysh = DealString.toGBK(request.getParameter("xysh"));

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}
		if("update".equals(lx)){
			request.setAttribute("view", "yes");
		}else{
			request.setAttribute("view", "no");
		}
		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		request.setAttribute("dqmcList", dqmcList);

		if ("update".equals(doType)) {
			pkValue = request.getParameter("rowid");
			if (null != pkValue) {
				pkValue = pkValue.replace(" ", "+");
			}
			

				String jtdh2 = DealString.toGBK(request.getParameter("jtdh2"));
				String sjh2 = DealString.toGBK(request.getParameter("sjh2"));
				String gzdwqc2 = DealString.toGBK(request
						.getParameter("gzdwqc2"));
				String gzdwdz2 = DealString.toGBK(request
						.getParameter("gzdwdz2"));
				String qy2 = DealString.toGBK(request.getParameter("qy2"));
				String yb2 = DealString.toGBK(request.getParameter("yb2"));
				String lxr2 = DealString.toGBK(request.getParameter("lxr2"));
				String gzbm2 = DealString.toGBK(request.getParameter("gzbm2"));
				String lxrdh2 = DealString
						.toGBK(request.getParameter("lxrdh2"));
				String lxrsjh2 = DealString.toGBK(request
						.getParameter("lxrsjh2"));

				String jtdhmark = DealString.toGBK(request
						.getParameter("jtdhmark"));
				String sjhmark = DealString.toGBK(request
						.getParameter("sjhmark"));
				String gzdwqcmark = DealString.toGBK(request
						.getParameter("gzdwqcmark"));
				String gzdwdzmark = DealString.toGBK(request
						.getParameter("gzdwdzmark"));
				String qymark = DealString
						.toGBK(request.getParameter("qymark"));
				String ybmark = DealString
						.toGBK(request.getParameter("ybmark"));
				String lxrmark = DealString.toGBK(request
						.getParameter("lxrmark"));
				String gzbmmark = DealString.toGBK(request
						.getParameter("gzbmmark"));
				String lxrdhmark = DealString.toGBK(request
						.getParameter("lxrdhmark"));
				String lxrsjhmark = DealString.toGBK(request
						.getParameter("lxrsjhmark"));

				if (!"on".equals(jtdhmark)) {
					jtdh2 = "";
				}
				if (!"on".equals(sjhmark)) {
					sjh2 = "";
				}
				if (!"on".equals(gzdwqcmark)) {
					gzdwqcmark = "";
				}
				if (!"on".equals(gzdwdzmark)) {
					gzdwdzmark = "";
				}
				if (!"on".equals(qymark)) {
					qymark = "";
				}
				if (!"on".equals(ybmark)) {
					ybmark = "";
				}
				if (!"on".equals(lxrmark)) {
					lxrmark = "";
				}
				if (!"on".equals(gzbmmark)) {
					gzbmmark = "";
				}
				if (!"on".equals(lxrdhmark)) {
					lxrdhmark = "";
				}
				if (!"on".equals(lxrsjhmark)) {
					lxrsjhmark = "";
				}

				boolean judge = false;
				judge = StandardOperation.update(realTable,
						new String[] { "jtdh2", "sjh2", "gzdwqc2", "gzdwdz2",
								"qy2", "yb2", "lxr2", "gzbm2", "lxrdh2",
								"lxrsjh2", "jtdhmark", "sjhmark", "gzdwqcmark",
								"gzdwdzmark", "qymark", "ybmark", "lxrmark",
								"gzbmmark", "lxrdhmark", "lxrsjhmark", "xysh",
								"xyshr" },
						new String[] { jtdh2, sjh2, gzdwqc2, gzdwdz2, qy2, yb2,
								lxr2, gzbm2, lxrdh2, lxrsjh2, jtdhmark,
								sjhmark, gzdwqcmark, gzdwdzmark, qymark,
								ybmark, lxrmark, gzbmmark, lxrdhmark,
								lxrsjhmark, "未审核", "" }, pk, pkValue, request);
				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			
		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select a.rowid,a.* from " + tableName + " a where " + pk
					+ "='" + pkValue + "' and xn='" + xn + "'";
			String[] colList = dao.getColumnName("select a.rowid, a.* from "
					+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				request.setAttribute("jtdhmarkcolor", "on".equals(map
						.get("jtdhmark")) ? "B5D4F7" : "");
				request.setAttribute("sjhmarkcolor", "on".equals(map
						.get("sjhmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwqcmarkcolor", "on".equals(map
						.get("gzdwqcmark")) ? "B5D4F7" : "");
				request.setAttribute("gzdwdzmarkcolor", "on".equals(map
						.get("gzdwdzmark")) ? "B5D4F7" : "");
				request.setAttribute("qymarkcolor", "on".equals(map
						.get("qymark")) ? "B5D4F7" : "");
				request.setAttribute("ybmarkcolor", "on".equals(map
						.get("ybmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrmarkcolor", "on".equals(map
						.get("lxrmark")) ? "B5D4F7" : "");
				request.setAttribute("gzbmmarkcolor", "on".equals(map
						.get("gzbmmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrdhmarkcolor", "on".equals(map
						.get("lxrdhmark")) ? "B5D4F7" : "");
				request.setAttribute("lxrsjhmarkcolor", "on".equals(map
						.get("lxrsjhmark")) ? "B5D4F7" : "");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 安置信息申请变更-查询学生信息-填充

	private ActionForward hzjyazupdatequerystuinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "hzjy_xsjbxxb"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String dataType = request.getParameter("act"); // 接收数据类型
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb")); // 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String xn = Base.currXn;// 获得当前学年
		String[] xtydm = null;

		String whichtype = request.getParameter("whichtype");
		HashMap<String, String> whichtypemap = new HashMap<String, String>();
		whichtypemap.put("whichtype", whichtype);
		request.setAttribute("whichtypemap", whichtypemap);

		// String pk = "xh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		if ("xy".equals(userType)) {
			sql = "select xtydm from hzjy_xtyxxb where xtyyhm=? and xn=?";
			String[] xtyinfo = dao.getRs(sql, new String[] { userName,Base.currXn },
					"xtydm");
			if (null != xtyinfo) {
				xtydm = xtyinfo;
			}	
		} 
		if ("xx".equals("userType")) {
			String xymc1 = DealString.toGBK(request.getParameter("xymc1"));
			if (xymc1 != null && !"".equals(xymc1)) {
				xymc = xymc1;
			}
		}

		if ("go".equals(act)) {
			map.put("xh", xh);
			map.put("xb", xb);
			map.put("name", name);
			map.put("xymc", xymc);
		}

		if (xh == null) {
			xh = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (name == null) {
			name = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xb = '" + xb + "' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc ='" + xymc + "'";
		}
		querry += " and xn = '" + xn + "'";

		CommanForm dataSearchForm = (CommanForm) form;

		if ("xy".equals(userType)) {
			if(xtydm != null && xtydm.length>0){
				querry += " and (1 = 2 ";
				for(int i=0;i<xtydm.length;i++){
					querry += " or xtydm = '" + xtydm[i] + "'";
				}
				querry += " ) ";
			}else{
				querry += " and xtydm='!!$!!' ";
			}
			sql = "select count(*) count from " + tableName + " a " + querry;;
		} else {
			sql = "select count(*) count from " + tableName + " a " + querry;
		}
		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		sql = "select * from (select a.* ,rownum r from (select distinct a.xh,a.name,a.xymc,a.xn from "
				+ tableName
				+ " a "
				+ querry
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		colList = new String[] { "r", "xh", "name", "xymc", "xn" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}

		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rs1", map);// 发送查询条件数组
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("xyList", Base.getXyList());// 学院列表
		return mapping.findForward("success");
	}

	// 成绩参数设定

	private ActionForward xtyglscoreset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "hzjy_scoresetb";
		String realTable = "hzjy_realscoresetb";
		String savetype = "";
		String sql = "";
		String[] pages = null;
		String[] titNames = null;
		String[] scoresetinfo = null;
		String titName = request.getParameter("titName");
		// 表头名
		HashMap<String, String> map = new HashMap<String, String>();
		String type = request.getParameter("type");
		String doType = request.getParameter("doType");

		pages = new String[] { "合作单位评分", "协调员评分", "业务报告评分" };
		titNames = new String[] { "hzdw", "xty", "ywbg" };

		List pagesList = dao.arrayToList(titNames, pages); // 把表头信息装入数组
		request.setAttribute("pages", pagesList);
		if (titName == null) {
			titName = titNames[0];
		}
		request.setAttribute("titName", titName);

		if ("hzdw".equals(titName)) {
			savetype = "合作单位评分";
			request.setAttribute("which", "hzdw");
		} else if ("xty".equals(titName)) {
			savetype = "协调员评分";
			request.setAttribute("which", "xty");
		} else if ("ywbg".equals(titName)) {
			savetype = "业务报告评分";
			request.setAttribute("which", "ywbg");
		}

		String level1 = DealString.toGBK(request.getParameter("level1"));
		String level2 = DealString.toGBK(request.getParameter("level2"));
		String level3 = DealString.toGBK(request.getParameter("level3"));
		String level4 = DealString.toGBK(request.getParameter("level4"));
		String level5 = DealString.toGBK(request.getParameter("level5"));
		String level6 = DealString.toGBK(request.getParameter("level6"));
		String level7 = DealString.toGBK(request.getParameter("level7"));

		// 等级封装进map
		HashMap<String, String> levelmap = new HashMap<String, String>();
		levelmap.put("level1", level1);
		levelmap.put("level2", level2);
		levelmap.put("level3", level3);
		levelmap.put("level4", level4);
		levelmap.put("level5", level5);
		levelmap.put("level6", level6);
		levelmap.put("level7", level7);

		String describe1 = DealString.toGBK(request.getParameter("describe1"));
		String describe2 = DealString.toGBK(request.getParameter("describe2"));
		String describe3 = DealString.toGBK(request.getParameter("describe3"));
		String describe4 = DealString.toGBK(request.getParameter("describe4"));
		String describe5 = DealString.toGBK(request.getParameter("describe5"));
		String describe6 = DealString.toGBK(request.getParameter("describe6"));
		String describe7 = DealString.toGBK(request.getParameter("describe7"));

		// 描述封装进map
		HashMap<String, String> describemap = new HashMap<String, String>();
		describemap.put("describe1", describe1);
		describemap.put("describe2", describe2);
		describemap.put("describe3", describe3);
		describemap.put("describe4", describe4);
		describemap.put("describe5", describe5);
		describemap.put("describe6", describe6);
		describemap.put("describe7", describe7);

		boolean judge = false;
		sql = "select * from " + tableName + " where savetype=? ";
		scoresetinfo = dao.getOneRs(sql, new String[] { savetype },
				new String[] { "score1", "score2", "score3", "score4",
						"score5", "score6", "score7", "level1", "level2",
						"level3", "level4", "level5", "level6", "level7",
						"describe1", "describe2", "describe3", "describe4",
						"describe5", "describe6", "describe7" });
		if ("save".equals(doType)) {
			if ("hzdw".equals(type)) {
				if (null == scoresetinfo) {
					judge = StandardOperation.insert(tableName, new String[] {
							"savetype", "score1", "score2", "score3", "score4",
							"score5", "score6", "score7", "level1", "level2",
							"level3", "level4", "level5", "level6", "level7",
							"describe1", "describe2", "describe3", "describe4",
							"describe5", "describe6", "describe7" },
							new String[] { savetype, "5", "10", "15", "20",
									"25", "30", "35", level1, level2, level3,
									level4, level5, level6, level7, describe1,
									describe2, describe3, describe4, describe5,
									describe6, describe7 }, request);

					int score = 5;
					for (int i = 1; i < 8; i++) {
						StandardOperation.insert(realTable, new String[] {
								"savetype", "score", "lev", "describe" },
								new String[] {
										savetype,
										Integer.toString(score),
										levelmap.get("level"
												+ Integer.toString(i)),
										describemap.get("describe"
												+ Integer.toString(i)) },
								request);
						score += 5;
					}
				} else {
					judge = StandardOperation.update(tableName, new String[] {
							"level1", "level2", "level3", "level4", "level5",
							"level6", "level7", "describe1", "describe2",
							"describe3", "describe4", "describe5", "describe6",
							"describe7" }, new String[] { level1, level2,
							level3, level4, level5, level6, level7, describe1,
							describe2, describe3, describe4, describe5,
							describe6, describe7 }, "savetype", savetype,
							request);

					int score = 5;
					for (int i = 1; i < 8; i++) {
						StandardOperation.update(realTable, new String[] {
								"lev", "describe" }, new String[] {
								levelmap.get("level" + Integer.toString(i)),
								describemap.get("describe"
										+ Integer.toString(i)) }, new String[] {
								"savetype", "score" }, new String[] { savetype,
								Integer.toString(score) }, request);
						score += 5;
					}
				}

			} else if ("xty".equals(type)) {
				if (null == scoresetinfo) {
					judge = StandardOperation.insert(tableName, new String[] {
							"savetype", "score1", "score2", "score3", "score4",
							"score5", "score6", "score7", "level1", "level2",
							"level3", "level4", "level5", "level6", "level7",
							"describe1", "describe2", "describe3", "describe4",
							"describe5", "describe6", "describe7" },
							new String[] { savetype, "5", "10", "15", "20",
									"25", "30", "35", level1, level2, level3,
									level4, level5, level6, level7, describe1,
									describe2, describe3, describe4, describe5,
									describe6, describe7 }, request);

					int score = 5;
					for (int i = 1; i < 8; i++) {
						StandardOperation.insert(realTable, new String[] {
								"savetype", "score", "lev", "describe" },
								new String[] {
										savetype,
										Integer.toString(score),
										levelmap.get("level"
												+ Integer.toString(i)),
										describemap.get("describe"
												+ Integer.toString(i)) },
								request);
						score += 5;
					}

				} else {
					judge = StandardOperation.update(tableName, new String[] {
							"level1", "level2", "level3", "level4", "level5",
							"level6", "level7", "describe1", "describe2",
							"describe3", "describe4", "describe5", "describe6",
							"describe7" }, new String[] { level1, level2,
							level3, level4, level5, level6, level7, describe1,
							describe2, describe3, describe4, describe5,
							describe6, describe7 }, "savetype", savetype,
							request);

					int score = 5;
					for (int i = 1; i < 8; i++) {
						StandardOperation.update(realTable, new String[] {
								"lev", "describe" }, new String[] {
								levelmap.get("level" + Integer.toString(i)),
								describemap.get("describe"
										+ Integer.toString(i)) }, new String[] {
								"savetype", "score" }, new String[] { savetype,
								Integer.toString(score) }, request);
						score += 5;
					}

				}
			} else if ("ywbg".equals(type)) {
				if (null == scoresetinfo) {
					judge = StandardOperation.insert(tableName, new String[] {
							"savetype", "score1", "score2", "score3", "score4",
							"score5", "score6", "level1", "level2", "level3",
							"level4", "level5", "level6", "describe1",
							"describe2", "describe3", "describe4", "describe5",
							"describe6" }, new String[] { savetype, "5", "10",
							"15", "20", "25", "30", level1, level2, level3,
							level4, level5, level6, describe1, describe2,
							describe3, describe4, describe5, describe6 },
							request);

					int score = 5;
					for (int i = 1; i < 7; i++) {
						StandardOperation.insert(realTable, new String[] {
								"savetype", "score", "lev", "describe" },
								new String[] {
										savetype,
										Integer.toString(score),
										levelmap.get("level"
												+ Integer.toString(i)),
										describemap.get("describe"
												+ Integer.toString(i)) },
								request);
						score += 5;
					}

				} else {
					judge = StandardOperation
							.update(tableName, new String[] { "level1",
									"level2", "level3", "level4", "level5",
									"level6", "describe1", "describe2",
									"describe3", "describe4", "describe5",
									"describe6" }, new String[] { level1,
									level2, level3, level4, level5, level6,
									describe1, describe2, describe3, describe4,
									describe5, describe6 }, "savetype",
									savetype, request);

					int score = 5;
					for (int i = 1; i < 7; i++) {
						StandardOperation.update(realTable, new String[] {
								"lev", "describe" }, new String[] {
								levelmap.get("level" + Integer.toString(i)),
								describemap.get("describe"
										+ Integer.toString(i)) }, new String[] {
								"savetype", "score" }, new String[] { savetype,
								Integer.toString(score) }, request);
						score += 5;
					}

				}
			}
			scoresetinfo = dao.getOneRs(sql, new String[] { savetype },
					new String[] { "score1", "score2", "score3", "score4",
							"score5", "score6", "score7", "level1", "level2",
							"level3", "level4", "level5", "level6", "level7",
							"describe1", "describe2", "describe3", "describe4",
							"describe5", "describe6", "describe7" });
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		String[] colList = { "score1", "score2", "score3", "score4", "score5",
				"score6", "score7", "level1", "level2", "level3", "level4",
				"level5", "level6", "level7", "describe1", "describe2",
				"describe3", "describe4", "describe5", "describe6", "describe7" };

		if (null != scoresetinfo) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), scoresetinfo[i]); // 将记录循环放入map中
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// 学生成绩录入
	private ActionForward hzjyxtyglscoreinput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		String tableName = "hzjy_xsjbxxb";
		String realTable = "hzjy_scoreb";
		// String pk = "xh";
		String xn = Base.currXn;
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		// HashMap<String, String> map1 = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> hzdwList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> xtyList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> ywbgList = new ArrayList<HashMap<String, String>>();

		// sql = "select score1,score2,score3,score4,score5,score6,score7 from
		// hzjy_scoresetb where savetype=?";
		//		
		// String[] hzdwscoreinfo = dao.getOneRs(sql, new String[]{ "合作单位评分" },
		// new String[]{
		// "score1","score2","score3","score4","score5","score6","score7"});

		if ("save".equals(doType)) {
			String scoretype = DealString.toGBK(request
					.getParameter("scoretype"));
			String xh = request.getParameter("xh");
			String hzdwscore = request.getParameter("hzdwscore");
			String xtyscore = request.getParameter("xtyscore");
			String ywbgscore = request.getParameter("ywbgscore");
			String allscore = request.getParameter("allscore");
			String hzdwlevel = DealString.toGBK(request
					.getParameter("hzdwlevel"));
			String xtylevel = DealString
					.toGBK(request.getParameter("xtylevel"));
			String ywbglevel = DealString.toGBK(request
					.getParameter("ywbglevel"));
			String hzdwdescribe = DealString.toGBK(request
					.getParameter("hzdwdescribe"));
			String xtydescribe = DealString.toGBK(request
					.getParameter("xtydescribe"));
			String ywbgdescribe = DealString.toGBK(request
					.getParameter("ywbgdescribe"));
			String zhpj = DealString.toGBK(request.getParameter("zhpj"));
			String alllevel = DealString
					.toGBK(request.getParameter("alllevel"));
			String kssj = DealString.toGBK(request.getParameter("hzjykssj"));
			String jssj = DealString.toGBK(request.getParameter("hzjyjssj"));

			boolean judge = false;

			judge = StandardOperation.insert(realTable, new String[] { "xh",
					"xn", "hzdwscore", "xtyscore", "ywbgscore", "allscore",
					"hzdwlevel", "xtylevel", "ywbglevel", "hzdwdescribe",
					"xtydescribe", "ywbgdescribe", "zhpj", "alllevel", "kssj",
					"jssj", "scoretype" }, new String[] { xh, xn, hzdwscore,
					xtyscore, ywbgscore, allscore, hzdwlevel, xtylevel,
					ywbglevel, hzdwdescribe, xtydescribe, ywbgdescribe, zhpj,
					alllevel, kssj, jssj, scoretype }, request);
			if (judge) {
				if ("常规成绩".equals(scoretype)) {
					if ("F".equalsIgnoreCase(alllevel)) {
						StandardOperation.update(tableName, new String[] {
								"score", "retest" }, new String[] { alllevel,
								"是" }, new String[] { "xh", "xn" },
								new String[] { xh, xn }, request);
					} else {
						StandardOperation.update(tableName,
								new String[] { "score" },
								new String[] { alllevel }, new String[] { "xh",
										"xn" }, new String[] { xh, xn },
								request);
					}
				} else {
					StandardOperation.update(tableName,
							new String[] { "rescore" },
							new String[] { alllevel }, new String[] { "xh",
									"xn" }, new String[] { xh, xn }, request);
				}

				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		// 查询数据
		sql = "select * from hzjy_xsjbxxb where xh=? and xn=?";
		String[] colList = dao
				.getColumnName("select a.xh,a.name,a.xymc,a.zymc,a.gzdwqc,a.xtyxm,a.xn,a.score,a.retest,a.hzjykssj,a.hzjyjssj from "
						+ tableName + " a where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue, xn },
				colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			String hzjykssj = map.get("hzjykssj");
			String hzjyjssj = map.get("hzjyjssj");
			String countdays = GetTime.getDaysOfTowDate(hzjykssj, hzjyjssj);
			if ("参数出错！".equalsIgnoreCase(countdays)) {
				map.put("countdays", "该生未设置时间");
			} else {
				map.put("countdays", "共计" + countdays + "天");
			}
		}

		sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
		String[] hzdwlevelinfo = dao.getOneRs(sql, new String[] { "合作单位评分" },
				new String[] { "level1", "level2", "level3", "level4",
						"level5", "level6", "level7" });

		sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
		String[] xtylevelinfo = dao.getOneRs(sql, new String[] { "协调员评分" },
				new String[] { "level1", "level2", "level3", "level4",
						"level5", "level6", "level7" });

		sql = "select level1,level2,level3,level4,level5,level6 from hzjy_scoresetb where savetype=?";
		String[] ywbglevelinfo = dao.getOneRs(sql, new String[] { "业务报告评分" },
				new String[] { "level1", "level2", "level3", "level4",
						"level5", "level6" });

		// sql = "select
		// describe1,describe2,describe3,describe4,describe5,describe6,describe7
		// from hzjy_scoresetb where savetype=?";
		// String[] hzdwdescribeinfo = dao.getOneRs(sql, new String[]{ "合作单位评分"
		// }, new String[]{
		// "describe1","describe2","describe3","describe4","describe5","describe6","describe7"});

		// 合作单位评分
		if (null != hzdwlevelinfo) {
			for (int i = 0; i < hzdwlevelinfo.length; i++) {
				HashMap<String, String> hzdwmap = new HashMap<String, String>();
				hzdwmap.put("hzdwlevel", hzdwlevelinfo[i]);
				hzdwList.add(hzdwmap);
			}
		}
		request.setAttribute("hzdwList", hzdwList);

		// 协调员评分
		if (null != xtylevelinfo) {
			for (int i = 0; i < xtylevelinfo.length; i++) {
				HashMap<String, String> xtymap = new HashMap<String, String>();
				xtymap.put("xtylevel", xtylevelinfo[i]);
				xtyList.add(xtymap);
			}
		}
		request.setAttribute("xtyList", xtyList);

		// 业务报告评分
		if (null != ywbglevelinfo) {
			for (int i = 0; i < ywbglevelinfo.length; i++) {
				HashMap<String, String> ywbgmap = new HashMap<String, String>();
				ywbgmap.put("ywbglevel", ywbglevelinfo[i]);
				ywbgList.add(ywbgmap);
			}
		}
		request.setAttribute("ywbgList", ywbgList);

		int intallscore = 0;

		map.put("allscore", Integer.toString(intallscore));
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生成绩查询
	private ActionForward hzjyxtyglscorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		// ArrayList<Object> rs = new ArrayList<Object>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		// String querry = " where 1=1 "; // sql条件
		String tableName = "view_hzjy_scoreb"; // 查询信息源表（多为视图名）
		String realTable = "hzjy_scoreb"; // 学生成绩表视图
		String tableName2 = "hzjy_xsjbxxb";
		String rsNum = "0";// 返回的记录数
		String xn = Base.currXn;// 学年
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String scoretype = DealString.toGBK(request.getParameter("scoretype")); // 接收成绩类型
		String scoretype2 = DealString
				.toGBK(request.getParameter("scoretype2")); // 接收成绩类型
		String xh2 = DealString.toGBK(request.getParameter("xh2")); // 接收学号
		String xydm = "";
		String zydm = "";
		String nj = request.getParameter("nj"); // 接收年级
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));// 协调员姓名
		String xtydm = "";
		String pk = "rowid"; // 主键
		String pkValue = request.getParameter("pkValue");

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		// 判断用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
			sql = "select xydm from view_njxyzybj where xymc=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}

			sql = "select xtydm from hzjy_xtyxxb where xtyyhm=?";
			String xtyinfo = dao.getOneRs(sql, new String[] { userName },
					"xtydm");
			if (null != xtyinfo && !"".equals(xtyinfo)) {
				xtydm = xtyinfo;
			}

			request.setAttribute("who", "xy");
		}

		// sql = "select bmdm from zxbz_xxbmdm where bmmc=?";
		// String[] bminfo = dao.getOneRs(sql, new String[] { xymc },
		// new String[] { "bmdm" });
		// if (null != bminfo) {
		// xydm = bminfo[0];
		// }

		sql = "select zydm from bks_zydm where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete(realTable, pk, whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation
					.delete(realTable, new String[] { "rowid" },
							new String[] { pkValue }, request);
			if (judge) {
				if ("常规成绩".equals(scoretype2)) {
					StandardOperation.update(tableName2, new String[] {
							"score", "retest", "rescore" }, new String[] {
							"未录入", "否", "未录入" }, new String[] { "xh", "xn" },
							new String[] { xh2, xn }, request);
					StandardOperation.delete(realTable, new String[] { "xh",
							"xn", "scoretype" },
							new String[] { xh2, xn, "补考成绩" }, request);
				} else if ("补考成绩".equals(scoretype2)) {
					StandardOperation.update(tableName2,
							new String[] { "rescore" }, new String[] { "未录入" },
							new String[] { "xh", "xn" },
							new String[] { xh2, xn }, request);
				}
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("xtyxm", xtyxm);
			map.put("xtydm", xtydm);
			map.put("scoretype", scoretype);
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");

		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if ((xtyxm != null) && !xtyxm.equals("")) {
			query.append(" and xtyxm like '%");
			query.append(xtyxm);
			query.append("%' ");
		}
		if ((xtydm != null) && !xtydm.equals("")) {
			query.append(" and xtydm='");
			query.append(xtydm);
			query.append("' ");
		}
		if ((scoretype != null) && !scoretype.equals("")) {
			query.append(" and scoretype='");
			query.append(scoretype);
			query.append("' ");
		}

		sql = "select rowid,rownum 行号,a.* from " + tableName + " a "
				+ query.toString() + " and xn='" + xn + "'";
		colList = new String[] { "rowid", "行号", "nj", "name", "xh", "xymc",
				"bjmc", "alllevel", "xtyxm", "scoretype" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs = dao.getArrayList(sql, new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");
	}

	// 学生成绩详细内容查看
	private ActionForward hzjyxtyglscoremorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjy_scoreb";
		String act = request.getParameter("act");

		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String scoretype = map.get("scoretype");
				if ("常规成绩".equals(scoretype)) {
					request.setAttribute("color", "black");
				} else {
					request.setAttribute("color", "red");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生成绩修改

	private ActionForward hzjyxtyglscoreupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;// 获得当前学年
		String sql = "";
		String pk = "rowid";
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
		String tableName = "view_hzjy_scoreb";
		String tableName2 = "hzjy_xsjbxxb";
		String realTable = "hzjy_scoreb";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");

		ArrayList<HashMap<String, String>> hzdwList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> xtyList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> ywbgList = new ArrayList<HashMap<String, String>>();

		if ("update".equals(doType)) {
			pkValue = RowidToPk.rowidToPK(request.getParameter("rowid"));
			if (null != pkValue) {
				pkValue = pkValue.replace(" ", "+");
			}
			String xh = DealString.toGBK(request.getParameter("xh"));
			String scoretype = DealString.toGBK(request
					.getParameter("scoretype"));
			String hzdwscore = request.getParameter("hzdwscore");
			String xtyscore = request.getParameter("xtyscore");
			String ywbgscore = request.getParameter("ywbgscore");
			String allscore = request.getParameter("allscore");
			String hzdwlevel = DealString.toGBK(request
					.getParameter("hzdwlevel"));
			String xtylevel = DealString
					.toGBK(request.getParameter("xtylevel"));
			String ywbglevel = DealString.toGBK(request
					.getParameter("ywbglevel"));
			String hzdwdescribe = DealString.toGBK(request
					.getParameter("hzdwdescribe"));
			String xtydescribe = DealString.toGBK(request
					.getParameter("xtydescribe"));
			String ywbgdescribe = DealString.toGBK(request
					.getParameter("ywbgdescribe"));
			String zhpj = DealString.toGBK(request.getParameter("zhpj"));
			String alllevel = DealString
					.toGBK(request.getParameter("alllevel"));
			String kssj = DealString.toGBK(request.getParameter("kssj"));
			String jssj = DealString.toGBK(request.getParameter("jssj"));

			boolean judge = false;

			judge = StandardOperation.update(realTable, new String[] {
					"hzdwscore", "xtyscore", "ywbgscore", "allscore",
					"hzdwlevel", "xtylevel", "ywbglevel", "hzdwdescribe",
					"xtydescribe", "ywbgdescribe", "zhpj", "alllevel", "kssj",
					"jssj" }, new String[] { hzdwscore, xtyscore, ywbgscore,
					allscore, hzdwlevel, xtylevel, ywbglevel, hzdwdescribe,
					xtydescribe, ywbgdescribe, zhpj, alllevel, kssj, jssj },
					pk, pkValue, request);

			if (judge) {
				if ("常规成绩".equals(scoretype)) {
					if ("F".equalsIgnoreCase(alllevel)) {
						StandardOperation.update(tableName2, new String[] {
								"score", "retest" }, new String[] { alllevel,
								"是" }, new String[] { "xh", "xn" },
								new String[] { xh, xn }, request);
					} else {
						StandardOperation.update(tableName2, new String[] {
								"score", "retest", "rescore" }, new String[] {
								alllevel, "否", "未录入" }, new String[] { "xh",
								"xn" }, new String[] { xh, xn }, request);
						StandardOperation.delete(realTable, new String[] {
								"xh", "xn", "scoretype" }, new String[] { xh,
								xn, "补考成绩" }, request);
					}
				} else {// 补考成绩
					StandardOperation.update(tableName2,
							new String[] { "rescore" },
							new String[] { alllevel }, new String[] { "xh",
									"xn" }, new String[] { xh, xn }, request);
				}
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		if ("view".equalsIgnoreCase(act)) {

			sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
			String[] hzdwlevelinfo = dao.getOneRs(sql,
					new String[] { "合作单位评分" }, new String[] { "level1",
							"level2", "level3", "level4", "level5", "level6",
							"level7" });

			sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
			String[] xtylevelinfo = dao.getOneRs(sql, new String[] { "协调员评分" },
					new String[] { "level1", "level2", "level3", "level4",
							"level5", "level6", "level7" });

			sql = "select level1,level2,level3,level4,level5,level6 from hzjy_scoresetb where savetype=?";
			String[] ywbglevelinfo = dao.getOneRs(sql,
					new String[] { "业务报告评分" }, new String[] { "level1",
							"level2", "level3", "level4", "level5", "level6" });

			// sql = "select
			// describe1,describe2,describe3,describe4,describe5,describe6,describe7
			// from hzjy_scoresetb where savetype=?";
			// String[] hzdwdescribeinfo = dao.getOneRs(sql, new String[]{
			// "合作单位评分"
			// }, new String[]{
			// "describe1","describe2","describe3","describe4","describe5","describe6","describe7"});

			// 合作单位评分
			if (null != hzdwlevelinfo) {
				for (int i = 0; i < hzdwlevelinfo.length; i++) {
					HashMap<String, String> hzdwmap = new HashMap<String, String>();
					hzdwmap.put("hzdwlevel", hzdwlevelinfo[i]);
					hzdwList.add(hzdwmap);
				}
			}
			request.setAttribute("hzdwList", hzdwList);

			// 协调员评分
			if (null != xtylevelinfo) {
				for (int i = 0; i < xtylevelinfo.length; i++) {
					HashMap<String, String> xtymap = new HashMap<String, String>();
					xtymap.put("xtylevel", xtylevelinfo[i]);
					xtyList.add(xtymap);
				}
			}
			request.setAttribute("xtyList", xtyList);

			// 业务报告评分
			if (null != ywbglevelinfo) {
				for (int i = 0; i < ywbglevelinfo.length; i++) {
					HashMap<String, String> ywbgmap = new HashMap<String, String>();
					ywbgmap.put("ywbglevel", ywbglevelinfo[i]);
					ywbgList.add(ywbgmap);
				}
			}
			request.setAttribute("ywbgList", ywbgList);

			// 查询数据
			sql = "select a.rowid,a.* from " + tableName + " a where " + pk
					+ "='" + pkValue + "'";
			String[] colList = dao.getColumnName("select a.rowid,a.* from "
					+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String scoretype = map.get("scoretype");
				if ("常规成绩".equals(scoretype)) {
					request.setAttribute("color", "black");
				} else {
					request.setAttribute("color", "red");
				}
				sql = "select lev from hzjy_realscoresetb where lev=?";
				String hzdwchangeinfo = dao.getOneRs(sql, new String[] { map
						.get("hzdwlevel") }, "lev");
				if (null == hzdwchangeinfo || "".equals(hzdwchangeinfo)) {
					request.setAttribute("hzdwlevelchange", "hzdwlevelchange");
				}
				String xtychangeinfo = dao.getOneRs(sql, new String[] { map
						.get("xtylevel") }, "lev");
				if (null == xtychangeinfo || "".equals(xtychangeinfo)) {
					request.setAttribute("xtylevelchange", "xtylevelchange");
				}
				String ywbgchangeinfo = dao.getOneRs(sql, new String[] { map
						.get("ywbglevel") }, "lev");
				if (null == ywbgchangeinfo || "".equals(ywbgchangeinfo)) {
					request.setAttribute("ywbglevelchange", "ywbglevelchange");
				}
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生查询本人成绩
	private ActionForward hzjyxtyglstuqueryscore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		HttpSession session = request.getSession();
		String useType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "view_hzjy_scoreb";

		if ("stu".equals(useType)) {

			sql = "select rowid,rownum 行号,a.* from " + tableName
					+ " a where a.xh=?";
			colList = new String[] { "rowid", "行号", "name", "xh", "xn", "xymc",
					"bjmc", "alllevel", "xtyxm", "scoretype" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			rs = dao.getArrayList(sql, new String[] { userName }, colList);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("false");
		}

	}

	// 协调员工作记录

	private ActionForward hzjyXtyglGzjlInput(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		// ArrayList<Object> rs2 = new ArrayList<Object>();
		ArrayList<HashMap<String, String>> rs2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs3 = new HashMap<String, String>();// 条件
		ArrayList<HashMap<String, String>> rs4 = new ArrayList<HashMap<String, String>>();
		HzjyServiceDao dao1 = new HzjyServiceDao();
		String[] colList = null;
		String[] colListCN = null;
		boolean judge = false;
		String rsNum = "0";
		String rsNum2 = "0";
		List topTr = null;
		String sql = "";
		DAO dao = DAO.getInstance();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String rowid = request.getParameter("rowid");
		String xn = Base.currXn;
		String tableName = "hzjy_xsjbxxb";
		String tableName2 = "hzjy_xtygzjlb";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		HttpSession session = request.getSession();
		String usertype = session.getAttribute("userType").toString();
		String username = session.getAttribute("userName").toString();
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String nj = DealString.toGBK(request.getParameter("nj"));

		String xh1 = request.getParameter("xh1");
		String xm1 = DealString.toGBK(request.getParameter("xm1"));
		nj = DealString.toGBK(request.getParameter("nj"));
		xydm = DealString.toGBK(request.getParameter("xydm"));
		zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));

		rs3.put("xh1", xh1);
		rs3.put("xm1", xm1);
		rs3.put("nj", nj);
		rs3.put("xydm", xydm);
		rs3.put("zydm", zydm);
		rs3.put("bjdm", bjdm);

		if ("xy".equalsIgnoreCase(usertype)) {
			request.setAttribute("who", "xy");
			sql = "select xymc from hzjy_xtyxxb where xtyyhm=?";
			String xtyxymc = dao.getOneRs(sql, new String[] { username },
					"xymc");
			if (null != xtyxymc) {
				sql = "select xydm from view_njxyzybj where xymc=?";
				String xtyxydm = dao.getOneRs(sql, new String[] { xtyxymc },
						"xydm");
				xydm = xtyxydm;
				rs3.put("xydm", xydm);
			}
		} else if ("xx".equalsIgnoreCase(usertype)
				|| "admin".equalsIgnoreCase(usertype)) {
			request.setAttribute("who", "xx");
		}

		if (null != rowid) {
			rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			judge = StandardOperation.delete(tableName2, "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if ("save".equals(doType)) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xm = DealString.toGBK(request.getParameter("name"));
			String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
			String xtydm = DealString.toGBK(request.getParameter("xtydm"));
			String gzfs = DealString.toGBK(request.getParameter("gzfs1"));
			String sj = DealString.toGBK(request.getParameter("sj1"));
			String js = DealString.toGBK(request.getParameter("js"));

			judge = StandardOperation.insert(tableName2, new String[] { "xh",
					"xm", "xn", "xtyxm", "xtydm", "gzfs", "sj", "js" },
					new String[] { xh, xm, xn, xtyxm, xtydm, gzfs, sj, js },
					request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		// 查询数据
		if ("go".equals(act)) {
			String xh = DealString.toGBK(request.getParameter("xh"));
			if ("save".equalsIgnoreCase(doType3)) {
				pkValue = xh;
			}
			sql = "select * from hzjy_xsjbxxb where xh=? and xn=?";
			colList = dao
					.getColumnName("select a.xh,a.name,a.xymc,a.zymc,a.gzdwqc,a.xtyxm,a.xtydm,a.xn,a.nj from "
							+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue, xn },
					colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		sql = "select rowid,rownum 行号,a.* from " + tableName2
				+ " a  where  xh='" + pkValue + "' order by sj";
		colList = new String[] { "rowid", "行号", "xh", "xm", "xn", "xtyxm",
				"gzfs", "sj" };
		colListCN = dao.getColumnNameCN(colList, tableName2);
		List topTr2 = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs2 = dao1.getQuerryInfoArrayList(sql, new String[] {}, colList,
					rs3);
			if (rs2 == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs2.size());
			}
		}

		if ("query".equalsIgnoreCase(doType2)) {
			String querry = " where 1=1 ";

			String[] xtydm = dao.getRs(
					"select xtydm from hzjy_xtyxxb where xtyyhm=?",
					new String[] { username },"xtydm");

			if ((xh1 == null) || xh1.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh like '%" + xh1 + "%' ";
			}
			if ((xm1 == null) || xm1.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + xm1 + "%' ";
			}
//			if ((xydm == null) || xydm.equals("")) {
//				querry += " and 1=1 ";
//			} else {
//				querry += " and xydm='" + xydm + "' ";
//			}
			if ((zydm == null) || zydm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and zydm='" + zydm + "' ";
			}
			if ((bjdm == null) || bjdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bjdm='" + bjdm + "' ";
			}
			if ((xtydm == null) || xtydm.length==0) {
				querry += " and 1=1 ";
			} else {
				querry += " and ";
				for(int i=0;i<xtydm.length;i++){
					if(xtydm.length==1){
						querry += " xtydm= '" + xtydm[i] + "' ";
					}else if(i==xtydm.length-1){
						querry += " xtydm= '" + xtydm[i] + "') ";
					}else{
						querry += " (xtydm= '" + xtydm[i] + "' or ";
					}
					
				}
				
			}

			sql = "select rownum 行号,a.* from  view_hzjy_xsjbxxb a" + querry;
			colList = new String[] { "行号", "xh", "name", "xymc", "zymc",
					"bjmc", "xtyxm", "jls", "gxsj" };
			colListCN = new String[] { "行号", "学号", "姓名", Base.YXPZXY_KEY, "专业", "班级",
					"协调员", "记录数", "记录更新时间" };
			// colListCN = dao.getColumnNameCN(colList, "hzjy_xsjbxxb");
			rs4 = dao1.getQuerryInfoArrayList(sql, new String[] {},
					new String[] { "行号", "xh", "name", "xymc", "zymc", "bjmc",
							"xtyxm", "rid", "jls", "gxsj" }, rs3);
			topTr = dao.arrayToList(colList, colListCN);

			if (rs4 == null) {
				rsNum2 = "0";
			} else {
				rsNum2 = String.valueOf(rs4.size());
			}

		}

		request.setAttribute("rs", map);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs2", rs2);
		request.setAttribute("rs3", rs3);
		request.setAttribute("rs4", rs4);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("topTr", topTr);
		request.setAttribute("topTr2", topTr2);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rsNum2", rsNum2);
		return mapping.findForward("success");
	}

	// 协调员工作记录修改
	private ActionForward hzjyXtyglGzjlUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String tableName = "hzjy_xtygzjlb";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		if (pkValue != null) {
			pkValue.replaceAll(" ", "+");
		}

		// 修改
		if ("update".equals(doType)) {
			String gzfs = DealString.toGBK(request.getParameter("gzfs"));
			String sj = DealString.toGBK(request.getParameter("sj"));
			String js = DealString.toGBK(request.getParameter("js"));
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "gzfs",
					"sj", "js" }, new String[] { gzfs, sj, js }, "rowid",
					pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		// 查询数据
		if ("view".equals(act)) {
			sql = "select * from " + tableName + " where rowid=?";
			colList = dao
					.getColumnName("select a.xh,a.xm,a.xtyxm,a.gzfs,a.xn,a.sj,a.js from "
							+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("rowid", pkValue);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 协调员工作记录查询
	private ActionForward hzjyXtyglGzjlQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
		String xtydm = DealString.toGBK(request.getParameter("xtydm"));
		String doType = DealString.toGBK(request.getParameter("doType"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String xn = Base.currXn;
		String xydm = "";
		String zydm = "";
		String rsNum = "";
		String querry = " where 1=1 "; // sql条件
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		DAO dao = DAO.getInstance();
		String tableName = "view_hzjy_xtygzjlb";

		// 判断用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
			sql = "select xydm from view_njxyzybj where xymc=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
				map.put("xymc", xymc);
			}

			sql = "select xtydm,xtyxm from hzjy_xtyxxb where xtyyhm=?";
			String[] xtyinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xtydm", "xtyxm" });
			if (null != xtyinfo && !"".equals(xtyinfo)) {
				xtydm = xtyinfo[0];
				xtyxm = xtyinfo[1];
				map.put("xtydm", xtydm);
				map.put("xtyxm", xtyxm);
			}
			request.setAttribute("who", "xy");
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("xtyxm", xtyxm);
			map.put("xtydm", xtydm);
			map.put("xn", xn);
		}

		if (xh == null) {
			xh = "";
		}
		if (xm == null) {
			xm = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (zymc == null) {
			zymc = "";
		}
		if (bjmc == null) {
			bjmc = "";
		}
		if (xtyxm == null) {
			xtyxm = "";
		}
		if (xtydm == null) {
			xtydm = "";
		}
		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((xm == null) || xm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xm like '%" + xm + "%' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((zymc == null) || zymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and zymc='" + zymc + "' ";
		}
		if ((bjmc == null) || bjmc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bjmc='" + bjmc + "' ";
		}
		if ((xtyxm == null) || xtyxm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyxm like '%" + xtyxm + "%' ";
		}
		if ((xtydm == null) || xtydm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtydm= '" + xtydm + "' ";
		}
		if ((xn == null) || xn.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xn= '" + xn + "' ";
		}
		sql = "select rid ,rownum 行号,a.* from " + tableName + " a " + querry;
		colList = new String[] { "rid", "行号", "xh", "xm", "xtyxm", "gzfs", "sj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs = dao.getArrayList(sql, new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));

		return mapping.findForward("success");
	}

	// 协调员工作记录详细情况查看
	private ActionForward hzjyXtyglGzjlViewmore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String act = request.getParameter("act");
		String tableName = "view_hzjy_xtygzjlb";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		if (pkValue != null) {
			pkValue.replaceAll(" ", "+");
		}

		// 查询数据
		if ("view".equals(act)) {
			sql = "select * from " + tableName + " where rid=?";
			colList = dao
					.getColumnName("select a.xh,a.xm,a.xtyxm,a.gzfs,a.xn,a.sj,a.xymc,a.zymc,a.bjmc,a.js from "
							+ tableName + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("rid", pkValue);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 申请时间设置
	@SuppressWarnings("unused")
	private ActionForward hzjyxscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		List<Object> rs = new ArrayList<Object>();
		String xh = userName;
		String sql = "select * from view_hzjysh where xh=?";
		String[] cols = { "xh", "xm", "xn", "xq", "nd", "sqsj" };
		// Arrays2.toLower(cols);
		String[] colCNs = dao.getColumnNameCN(cols, "view_hzjysh");
		rs.addAll(dao.rsToVator(sql, new String[] { xh }, cols));
		request.setAttribute("title", colCNs);
		request.setAttribute("rs", rs);
		return new ActionForward("/shgc/hzjy_xscx.jsp", false);
	}

	// 合作教育申请时间设置
	private ActionForward hzjysjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession(false);
		String userType = session.getAttribute("userType")==null?"":session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep")==null?"":session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String sqkssj = request.getParameter("sqkssj");
		String sqjssj = request.getParameter("sqjssj");
		String xsazsqkssj = request.getParameter("xsazsqkssj");
		String xsazsqjssj = request.getParameter("xsazsqjssj");
		String bgkssj = request.getParameter("bgkssj");
		String bgjssj = request.getParameter("bgjssj");
		String sql = "";
		boolean flag = false;
		String[] cols = { "sqkssj", "sqjssj", "xsazsqkssj", "xsazsqjssj",
				"bgkssj", "bgjssj" };
		String[] values = {sqkssj, sqjssj, xsazsqkssj, xsazsqjssj, bgkssj,bgjssj};
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String,String>> list = null;
		sql = "select sqkssj,sqjssj,xsazsqkssj,xsazsqjssj,bgkssj,bgjssj from hzjysjszb where xn=? and xq=? and fwbz=?";
		list = dao.getList(sql, new String[]{Base.currXn,Base.currXq,"-100"}, cols);
		if(userType.equals("xx") || userType.equals("admin")){
			if(list !=null && list.size()>0){
				request.setAttribute("rs", list.get(0));
			}else{
				request.setAttribute("rs", map);
			}
		}else if(userType.equals("xy")){
			if(list !=null && list.size()>0){
				request.setAttribute("info", list.get(0));
				list = dao.getList(sql, new String[]{Base.currXn,Base.currXq,userDep}, cols);
				if(list !=null && list.size()>0){
					request.setAttribute("rs", list.get(0));
				}else{
					request.setAttribute("rs", map);
				}
			}else{
				request.setAttribute("flag", "no");
			}
		}else{
			request.setAttribute("view", "no");
		}
		if (doType != null && doType.equalsIgnoreCase("save")) {
			if(userType.equals("xx") || userType.equals("admin")){
				sql = "delete from hzjysjszb where xn=? and xq=? and fwbz=?";
				flag = dao.runUpdate(sql, new String[]{Base.currXn,Base.currXq,"-100"});
				sql = "insert into hzjysjszb(xn,xq,sqkssj,sqjssj,xsazsqkssj,xsazsqjssj,bgkssj,bgjssj,fwbz) values (?,?,?,?,?,?,?,?,?)";
				flag = dao.runUpdate(sql, new String[]{Base.currXn,Base.currXq,sqkssj, sqjssj, xsazsqkssj, xsazsqjssj, bgkssj,
						bgjssj,"-100" });
			}else{
				sql = "delete from hzjysjszb where xn=? and xq=? and fwbz=?";
				flag = dao.runUpdate(sql, new String[]{Base.currXn,Base.currXq,userDep});
				sql = "insert into hzjysjszb(xn,xq,sqkssj,sqjssj,xsazsqkssj,xsazsqjssj,bgkssj,bgjssj,fwbz) values (?,?,?,?,?,?,?,?,?)";
				flag = dao.runUpdate(sql, new String[]{Base.currXn,Base.currXq,sqkssj, sqjssj, xsazsqkssj, xsazsqjssj, bgkssj,
						bgjssj,userDep });
			}
			for(int i=0;i<cols.length;i++){
				map.put(cols[i], values[i]);
			}
			request.setAttribute("rs", map);
			request.setAttribute("result", flag);	
		}
		return mapping.findForward("success");
	}

	// 合作教育时间段设置
	private ActionForward hzjysjdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		DAO dao = DAO.getInstance();
		String xn = Base.currXn;
		String sql = "";
		String tableName = "hzjy_xsjbxxb";
		String realTable = "hzjy_xsjbxxb";
		HashMap<String, String> map = new HashMap<String, String>(); // 查询条件
		Vector<Object> rs = new Vector<Object>(); // 获得记录
		String xh = request.getParameter("xh");
		String name = DealString.toGBK(request.getParameter("name"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String hzjykssj = DealString.toGBK(request.getParameter("hzjykssj"));
		String hzjyjssj = DealString.toGBK(request.getParameter("hzjyjssj"));
		String sfpipeisjd = DealString
				.toGBK(request.getParameter("sfpipeisjd"));
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		// String act1 = request.getParameter("act1");
		String rsNum = "0"; // 记录数
		String querry = " where 1=1 ";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		String[] colListCN = null;
		if (null != pkValue) {
			pkValue.replace(" ", "+");
		}
		if (userType.equalsIgnoreCase("xy")) {
			xymc = dao.getOneRs(
					"select distinct xymc from view_njxyzybj where xydm=?",
					new String[] { userDep }, "xymc");
			sql = "select xtydm from hzjy_xtyxxb where xtyyhm=?";
			String xtyinfo = dao.getOneRs(sql, new String[] { userName },
					"xtydm");
			if (null != xtyinfo && !"".equals(xtyinfo)) {
				querry += "and xtydm=" + xtyinfo + " ";
			}
			xydm = userDep;

		} else if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			sql = "select xymc from view_njxyzybj where xydm=?";
			String xymcinfo = dao.getOneRs(sql, new String[] { xydm }, "xymc");
			xymc = xymcinfo;
		}

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equals(userType)) {
			request.setAttribute("who", "xy");
		}

		// 批量设置时间

		if ("setall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if (null != whichxh) {
					whichxh.replace(" ", "+");
				}
				boolean judge = false;
				judge = StandardOperation.update(tableName, new String[] {
						"hzjykssj", "hzjyjssj" }, new String[] { hzjykssj,
						hzjyjssj }, pk, whichxh, request);
				if (judge) {
					request.setAttribute("setall", "ok");
				} else {
					request.setAttribute("setall", "no");
				}
			}
		}

		// 批量取消时间

		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if (null != whichxh) {
					whichxh.replace(" ", "+");
				}
				boolean judge = false;
				judge = StandardOperation.update(tableName, new String[] {
						"hzjykssj", "hzjyjssj" }, new String[] { "", "" }, pk,
						whichxh, request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// 返回查询条件
		// if ("query".equals(doType) || "changed".equals(act1)) {
		map.put("xh", xh);
		map.put("name", name);
		map.put("nj", nj);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("hzjykssj", hzjykssj);
		map.put("hzjyjssj", hzjyjssj);
		map.put("sfpipeisjd", sfpipeisjd);
		// }

		// sql = "select distinct xydm,xymc from view_njxyzybj where xymc=?";
		// String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
		// new String[] { "xydm" });
		// if (null != xyinfo) {
		// xydm = xyinfo[0];
		// }
		// 获取专业名称
		sql = "select distinct zydm,zymc from view_njxyzybj where zydm=?";
		zymc = dao.getOneRs(sql, new String[] { zydm }, "zymc");
		// 获取班级名称
		sql = "select distinct bjmc from view_njxyzybj where bjdm=?";
		bjmc = dao.getOneRs(sql, new String[] { bjdm }, "bjmc");

		if (xh == null) {
			xh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (zymc == null) {
			zymc = "";
		}
		if (bjmc == null) {
			bjmc = "";
		}
		if (sfpipeisjd == null) {
			sfpipeisjd = "";
		}
		if ((xh == null) || xh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh like '%" + xh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xymc = '" + xymc + "'";
		}
		if ((nj == null) || nj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "'";
		}
		if ((zymc == null) || zymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zymc = '" + zymc + "' ";
		}
		if ((bjmc == null) || bjmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjmc = '" + bjmc + "' ";
		}
		if ((sfpipeisjd == null) || sfpipeisjd.equals("")) {
			querry += "and 1=1 ";
		} else {
			if ("已设置".equals(sfpipeisjd)) {
				querry += "and hzjykssj is not null";
			} else {
				querry += "and hzjykssj is null";
			}
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ querry + " and xn='" + xn + "'";
		colList = new String[] { "主键", "行号", "name", "xh", "xymc", "zymc",
				"bjmc", "hzjykssj", "hzjyjssj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		request.setAttribute("userType", userType);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");

	}

	// 合作教育时间段设置学生基本信息详细
	private ActionForward hzjysjdszViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xsjbxxb";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 合作教育学生个人信息查询
	private ActionForward hzjyStuGrxxQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		// HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "hzjy_xsjbxxb"; // 查询信息源表（多为视图名）
		String pk = "xh";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if (!("stu".equals(userType))) {
			return mapping.findForward("false");
		}

		if ("stu".equals(userType)) {
			sql = "select rowid rid,rownum 行号,a.* from " + tableName
					+ " a where a." + pk + " =?";
			colList = new String[] { "rid", "行号", "xh", "name", "xb", "xn",
					"xymc", "zymc", "bjmc", "stutype" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			rs = dao.getArrayList(sql, new String[] { userName }, colList);
			request.setAttribute("topTr", topTr);
		}

		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// 学生基本信息 页面打开 录入
	private ActionForward xsjbxxinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) {

		String tableName = "hzjy_xsjbxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String name = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String sfzh = request.getParameter("sfzh");
		String doType = request.getParameter("doType");
		String xn = Base.currXn;

		if ("save".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation
					.insert(tableName, new String[] { "xh", "name", "xb", "nj",
							"xymc", "zymc", "bjmc", "sfzh", "xn" },
							new String[] { xh, name, xb, nj, xymc, zymc, bjmc,
									sfzh, xn }, request);
			if (judge) {
				request.setAttribute("insert", "ok");
			} else {
				request.setAttribute("insert", "no");
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生基本信息查询 页面打开
	private ActionForward xsjbxxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String tableName = "hzjy_xsjbxxb";
		String realTable = "hzjy_xsjbxxb";
		HashMap<String, String> map = new HashMap<String, String>(); // 查询条件
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String xh = request.getParameter("xh");
		String name = DealString.toGBK(request.getParameter("name"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String stutype = DealString.toGBK(request.getParameter("stutype"));
		String sfdjaz = DealString.toGBK(request.getParameter("sfdjaz"));
		String sfpipeixty = DealString
				.toGBK(request.getParameter("sfpipeixty"));
		// String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String act = request.getParameter("act");
		// String act1 = request.getParameter("act1");
		String rsNum = "0"; // 记录数
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		String[] colListCN = null;
		String xydm = "";
		String zydm = "";
		if(userType.equalsIgnoreCase("xx") || userType.equalsIgnoreCase("admin")){
			request.setAttribute("writeable", "yes");
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xymc == null || xymc.trim().equals(""))) {
			xymc = dao.getOneRs(
					"select distinct xymc from view_njxyzybj where xydm=?",
					new String[] { userDep }, "xymc");
		}
		// 返回查询条件
		// if ("query".equals(doType) || "changed".equals(act1)) {
		map.put("xh", xh);
		map.put("name", name);
		map.put("xb", xb);
		map.put("xn", xn);
		map.put("nj", nj);
		map.put("xymc", xymc);
		map.put("zymc", zymc);
		map.put("bjmc", bjmc);
		map.put("stutype", stutype);
		map.put("sfdjaz", sfdjaz);
		map.put("sfpipeixty", sfpipeixty);
		// }

		sql = "select distinct xydm,xymc from view_njxyzybj where xymc=?";
		String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
				new String[] { "xydm" });
		if (null != xyinfo) {
			xydm = xyinfo[0];
		}
		sql = "select distinct zydm,zymc from view_njxyzybj where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");

		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if ((stutype != null) && !stutype.equals("")) {
			query.append(" and stutype='");
			query.append(stutype);
			query.append("' ");
		}
		if ((sfdjaz != null) && !sfdjaz.equals("")) {
			query.append(" and sfdjaz='");
			query.append(sfdjaz);
			query.append("' ");
		}
		if ((xn != null) && !xn.equals("")) {
			query.append(" and xn='");
			query.append(xn);
			query.append("' ");
		}
		if ((sfpipeixty != null) && !sfpipeixty.equals("")) {
			if ("是".equals(sfpipeixty)) {
				query.append("and xtydm is not null ");
			} else if ("否".equals(sfpipeixty)) {
				query.append("and xtydm is null ");
			}
		}

		//
		// if ((xh == null) || xh.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xh like '%" + xh + "%' ";
		// }
		// if ((name == null) || name.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and name like '%" + name + "%' ";
		// }
		// if ((xb == null) || xb.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xb = '" + xb + "'";
		// }
		// if ((xymc == null) || xymc.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xymc = '" + xymc + "'";
		// }
		// if ((nj == null) || nj.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and nj = '" + nj + "'";
		// }
		// if ((zymc == null) || zymc.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and zymc = '" + zymc + "' ";
		// }
		// if ((bjmc == null) || bjmc.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and bjmc = '" + bjmc + "' ";
		// }
		// if ((stutype == null) || stutype.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and stutype = '" + stutype + "' ";
		// }
		// if ((sfdjaz == null) || sfdjaz.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and sfdjaz = '" + sfdjaz + "' ";
		// }
		// if ((xn == null) || xn.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// querry += "and xn = '" + xn + "' ";
		// }
		// if ((sfpipeixty == null) || sfpipeixty.equals("")) {
		// querry += "and 1=1 ";
		// } else {
		// if ("是".equals(sfpipeixty)) {
		// querry += "and xtydm is not null ";
		// } else if ("否".equals(sfpipeixty)) {
		// querry += "and xtydm is null ";
		// }
		// }

		sql = "select rowid rid,rownum 行号,a.* from " + tableName + " a "
				+ query.toString();
		colList = new String[] { "rid", "行号", "name", "xh", "xb", "nj", "xn",
				"xymc", "zymc", "bjmc", "stutype" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		request.setAttribute("userType", userType);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// 学生基本信息详细 页面打开
	private ActionForward xsjbxxViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xsjbxxb";
		String pk = "rowid";
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));

		String doType = request.getParameter("doType");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生基本信息查询 修改
	private ActionForward xsjbxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String realTable = "hzjy_xsjbxxb";
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		if (null != pkValue) {
			pkValue.replace(" ", "+");
		}

		// 数据更新
		if ("update".equals(doType2)) {
			String name = DealString.toGBK(request.getParameter("name"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = DealString.toGBK(request.getParameter("nj"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));
			String sfzh = request.getParameter("sfzh");

			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "name",
					"xb", "nj", "xymc", "zymc", "bjmc", "sfzh" }, new String[] {
					name, xb, nj, xymc, zymc, bjmc, sfzh }, pk, pkValue,
					request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			// if (pkValue == null) {
			// pkValue = request.getParameter("rid");
			// }
			sql = "select rowid rid, a.* from " + realTable + " a where " + pk
					+ "='" + pkValue + "'";
			String[] colList = dao.getColumnName("select rowid rid,a.* from "
					+ realTable + " a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}

			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 代教学生查询
	private ActionForward hzjyXtyglDjxsQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
//		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String sql = "";
		// 判断用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String name = DealString.toGBK(request.getParameter("name"));
		String xtydm = DealString.toGBK(request.getParameter("xtydm"));
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));

		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
			String xtyinfo[] = dao.getOneRs(
					"select xtydm,xtyxm from  hzjy_xtyxxb where xtyyhm=?",
					new String[] { userName },
					new String[] { "xtydm", "xtyxm" });
			if (null != xtyinfo) {
				xtydm = xtyinfo[0];
				xtyxm = xtyinfo[1];
				xydm = userDep;
			}
		}
		map.put("xh", xh);
		map.put("name", name);
		map.put("xtydm", xtydm);
		map.put("xtyxm", xtyxm);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(name))) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%'");
		}
		if (!("".equals(xtyxm))) {
			query.append(" and xtyxm like '%");
			query.append(xtyxm);
			query.append("%'");
		}
		if (!("".equals(xtydm))) {
			query.append(" and xtydm like '%");
			query.append(xtydm);
			query.append("%'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_hzjy_xsjbxxb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct a.rid, a.xh,a.name,a.xsdh,a.gzdwqc,a.dwlxrdh,a.lxr,a.score,a.hzjyqzsj,a.gxsj from view_hzjy_xsjbxxb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] {"rid","行号", "xh", "name", "xsdh", "gzdwqc", "lxr","dwlxrdh",
				"score", "hzjyqzsj","gxsj" };
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}


		sql = "select count(*) from view_hzjy_xsjbxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_hzjy_xsjbxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		
		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "view_hzjy_xsjbxxb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// 发送专业列表
		return mapping.findForward("success");
	}

	// 数据导出
	private ActionForward hzjyDataExport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String realTable = request.getParameter("realTable");
		String sql = "";
		String zd = "";
		String querry = " where 1=1";
		DAO dao = DAO.getInstance();
		if ("hzjy_xsjbxxb".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh");
			String name = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = DealString.toGBK(request.getParameter("nj"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));

			if (xh == null) {
				xh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nj == null) {
				nj = "";
			}
			if (zymc == null) {
				zymc = "";
			}
			if (bjmc == null) {
				bjmc = "";
			}
			if ((xh == null) || xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh like '%" + xh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xb == null) || xb.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xb = '" + xb + "'";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xymc = '" + xymc + "'";
			}
			if ((nj == null) || nj.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and nj = '" + nj + "'";
			}
			if ((zymc == null) || zymc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and zymc = '" + zymc + "' ";
			}
			if ((bjmc == null) || bjmc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and bjmc = '" + bjmc + "' ";
			}
		}

		if ("view_hzjysh".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh");
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String xydm = DealString.toGBK(request.getParameter("xydm"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String nd = DealString.toGBK(request.getParameter("nd"));
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));

			if (xh == null) {
				xh = "";
			}
			if (xm == null) {
				xm = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xydm == null) {
				xydm = "";
			}
			if (zydm == null) {
				zydm = "";
			}
			if (bjdm == null) {
				bjdm = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (xysh == null) {
				xysh = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if ((xh == null) || xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh like '%" + xh + "%' ";
			}
			if ((xm == null) || xm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xm like '%" + xm + "%' ";
			}
			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xydm == null) || xydm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xydm='" + xydm + "' ";
			}
			if ((zydm == null) || zydm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and zydm='" + zydm + "' ";
			}
			if ((bjdm == null) || bjdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bjdm='" + bjdm + "' ";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((xysh == null) || xysh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xysh='" + xysh + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
		}

		if ("hzjy_xtyxxb".equals(realTable)) {
			zd = " * ";
			String xtydm = DealString.toGBK(request.getParameter("xtydm"));
			String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));
			String xtyxb = DealString.toGBK(request.getParameter("xtyxb"));
			String xtyyb = DealString.toGBK(request.getParameter("xtyyb"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));

			if (xtydm == null) {
				xtydm = "";
			}
			if (xtyxm == null) {
				xtyxm = "";
			}
			if (xtyyb == null) {
				xtyyb = "";
			}
			if (xtyxb == null) {
				xtyxb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (xtyqy == null) {
				xtyqy = "";
			}
			if ((xtydm == null) || xtydm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xtydm like '%" + xtydm + "%' ";
			}
			if ((xtyxm == null) || xtyxm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xtyxm like '%" + xtyxm + "%' ";
			}
			if ((xtyyb == null) || xtyyb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xtyyb like'" + xtyyb + "%' ";
			}
			if ((xtyxb == null) || xtyxb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xtyxb='" + xtyxb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((xtyqy == null) || xtyqy.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xtyqy='" + xtyqy + "' ";
			}
		}

		if ("hzjy_xsjbxxb".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh");
			String name = DealString.toGBK(request.getParameter("name"));
			String nj = DealString.toGBK(request.getParameter("nj"));
			String xymc = DealString.toGBK(request.getParameter("xymc"));
			String xydm = DealString.toGBK(request.getParameter("xydm"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String bjmc = DealString.toGBK(request.getParameter("bjmc"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String sfpipeisjd = DealString.toGBK(request
					.getParameter("sfpipeisjd"));
			if (userType.equalsIgnoreCase("xy")) {
				xymc = dao.getOneRs(
						"select distinct xymc from view_njxyzybj where xydm=?",
						new String[] { userDep }, "xymc");
				sql = "select xtydm from hzjy_xtyxxb where xtyyhm=?";
				String xtyinfo = dao.getOneRs(sql, new String[] { userName },
						"xtydm");
				if (null != xtyinfo && !"".equals(xtyinfo)) {
					querry += "and xtydm=" + xtyinfo + " ";
				}
				xydm = userDep;

			} else if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sql = "select xymc from view_njxyzybj where xydm=?";
				String xymcinfo = dao.getOneRs(sql, new String[] { xydm },
						"xymc");
				xymc = xymcinfo;
			}

			if ("xx".equals(userType) || "admin".equals(userType)) {
				request.setAttribute("who", "xx");
			} else if ("xy".equals(userType)) {
				request.setAttribute("who", "xy");
			}

			// 获取专业名称
			sql = "select distinct zydm,zymc from view_njxyzybj where zydm=?";
			zymc = dao.getOneRs(sql, new String[] { zydm }, "zymc");
			// 获取班级名称
			sql = "select distinct bjmc from view_njxyzybj where bjdm=?";
			bjmc = dao.getOneRs(sql, new String[] { bjdm }, "bjmc");

			if (xh == null) {
				xh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nj == null) {
				nj = "";
			}
			if (zymc == null) {
				zymc = "";
			}
			if (bjmc == null) {
				bjmc = "";
			}
			if (sfpipeisjd == null) {
				sfpipeisjd = "";
			}
			if ((xh == null) || xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh like '%" + xh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xymc = '" + xymc + "'";
			}
			if ((nj == null) || nj.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and nj = '" + nj + "'";
			}
			if ((zymc == null) || zymc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and zymc = '" + zymc + "' ";
			}
			if ((bjmc == null) || bjmc.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and bjmc = '" + bjmc + "' ";
			}
			if ((sfpipeisjd == null) || sfpipeisjd.equals("")) {
				querry += "and 1=1 ";
			} else {
				if ("已设置".equals(sfpipeisjd)) {
					querry += "and hzjykssj is not null";
				} else {
					querry += "and hzjykssj is null";
				}
			}
		}

		if ("view_hzjyaz".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
			String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
			String xydm = "";
			String azxysh = DealString.toGBK(request.getParameter("azxysh"));// 接收学院审核
			String azxxsh = DealString.toGBK(request.getParameter("azxxsh"));// 接收学校审核
			String nj = request.getParameter("nj"); // 接收年级

			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();

			if ("xx".equals(userType) || "admin".equals(userType)) {
				request.setAttribute("who", "xx");
				sql = "select xydm from view_njxyzybj where xymc=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
						new String[] { "xydm" });
				if (null != stuinfo) {
					xydm = stuinfo[0];
					sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
					xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				}
			} else if ("xy".equals(userType)) {
				sql = "select szbm from yhb where yhm =?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm" });
				if (null != stuinfo) {
					xydm = stuinfo[0];
					sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
					xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				}
				request.setAttribute("who", "xy");
			}

			StringBuffer query = new StringBuffer();
			query.append(" ");

			if ((xh != null) && !xh.equals("")) {
				query.append(" and xh like '%");
				query.append(xh);
				query.append("%' ");
			}
			if ((name != null) && !name.equals("")) {
				query.append(" and name like '%");
				query.append(name);
				query.append("%' ");
			}
			if ((xb != null) && !xb.equals("")) {
				query.append(" and xb='");
				query.append(xb);
				query.append("' ");
			}
			if ((xymc != null) && !xymc.equals("")) {
				query.append(" and xymc='");
				query.append(xymc);
				query.append("' ");
			}
			if ((zymc != null) && !zymc.equals("")) {
				query.append(" and zymc='");
				query.append(zymc);
				query.append("' ");
			}
			if ((bjmc != null) && !bjmc.equals("")) {
				query.append(" and bjmc='");
				query.append(bjmc);
				query.append("' ");
			}
			if ((nj != null) && !nj.equals("")) {
				query.append(" and nj='");
				query.append(nj);
				query.append("' ");
			}
			if ((azxysh != null) && !azxysh.equals("")) {
				query.append(" and xysh='");
				query.append(azxysh);
				query.append("' ");
			}
			if ((azxxsh != null) && !azxxsh.equals("")) {
				query.append(" and xxsh='");
				query.append(azxxsh);
				query.append("' ");
			}
			querry += query.toString();
		}

		if ("view_azxxbgb".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
			String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
			String xydm = "";
			String xysh = DealString.toGBK(request.getParameter("xysh"));// 接收学院审核
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 接收学校审核
			String nj = request.getParameter("nj"); // 接收年级
			String pkValue = request.getParameter("pkValue");

			if (null != pkValue) {
				pkValue = pkValue.replace(" ", "+");
			}

			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();

			if ("xx".equals(userType) || "admin".equals(userType)) {
				request.setAttribute("who", "xx");
			} else if ("xy".equals(userType)) {
				sql = "select szbm from yhb where yhm =?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm" });
				if (null != stuinfo) {
					xydm = stuinfo[0];
					sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
					xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				}
			}

			if (xh == null) {
				xh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (zymc == null) {
				zymc = "";
			}
			if (bjmc == null) {
				bjmc = "";
			}
			if (nj == null) {
				nj = "";
			}
			if (xysh == null) {
				xysh = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if ((xh == null) || xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh like '%" + xh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((zymc == null) || zymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and zymc='" + zymc + "' ";
			}
			if ((bjmc == null) || bjmc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bjmc='" + bjmc + "' ";
			}
			if ((nj == null) || nj.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nj='" + nj + "' ";
			}
			if ((xysh == null) || xysh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xysh='" + xysh + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
		}

		if ("view_hzjy_scoreb".equals(realTable)) {
			zd = " * ";
			String xh = request.getParameter("xh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
			String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
			String scoretype = DealString.toGBK(request
					.getParameter("scoretype")); // 接收成绩类型
			String xydm = "";
			String nj = request.getParameter("nj"); // 接收年级
			String xtyxm = DealString.toGBK(request.getParameter("xtyxm"));// 协调员姓名
			String xtydm = "";

			// 判断用户类型
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();

			if ("xx".equals(userType) || "admin".equals(userType)) {
				request.setAttribute("who", "xx");
				sql = "select xydm from view_njxyzybj where xymc=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
						new String[] { "xydm" });
				if (null != stuinfo) {
					xydm = stuinfo[0];
					sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
					xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				}
			} else if ("xy".equals(userType)) {
				sql = "select szbm from yhb where yhm =?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm" });
				if (null != stuinfo) {
					xydm = stuinfo[0];
					sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
					xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				}

				sql = "select xtydm from hzjy_xtyxxb where xtyyhm=?";
				String xtyinfo = dao.getOneRs(sql, new String[] { userName },
						"xtydm");
				if (null != xtyinfo && !"".equals(xtyinfo)) {
					xtydm = xtyinfo;
				}

			}

			StringBuffer query = new StringBuffer();
			query.append(" ");

			if ((xh != null) && !xh.equals("")) {
				query.append(" and xh like '%");
				query.append(xh);
				query.append("%' ");
			}
			if ((name != null) && !name.equals("")) {
				query.append(" and name like '%");
				query.append(name);
				query.append("%' ");
			}
			if ((xb != null) && !xb.equals("")) {
				query.append(" and xb='");
				query.append(xb);
				query.append("' ");
			}
			if ((xymc != null) && !xymc.equals("")) {
				query.append(" and xymc='");
				query.append(xymc);
				query.append("' ");
			}
			if ((nj != null) && !nj.equals("")) {
				query.append(" and nj='");
				query.append(nj);
				query.append("' ");
			}
			if ((zymc != null) && !zymc.equals("")) {
				query.append(" and zymc='");
				query.append(zymc);
				query.append("' ");
			}
			if ((bjmc != null) && !bjmc.equals("")) {
				query.append(" and bjmc='");
				query.append(bjmc);
				query.append("' ");
			}
			if ((xtyxm != null) && !xtyxm.equals("")) {
				query.append(" and xtyxm like '%");
				query.append(xtyxm);
				query.append("%' ");
			}
			if ((xtydm != null) && !xtydm.equals("")) {
				query.append(" and xtydm='");
				query.append(xtydm);
				query.append("' ");
			}
			if ((scoretype != null) && !scoretype.equals("")) {
				query.append(" and scoretype='");
				query.append(scoretype);
				query.append("' ");
			}
			querry += query.toString();
		}
		
		if ("view_hzjy_xsjbxxb".equals(realTable)) {
			
			zd="*";
			
			querry= request.getParameter("querry");
			
		}
		
		

		sql = "select " + zd + " from " + realTable + querry;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (realTable == null) {
			Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
		}
		return mapping.findForward("success");
	}

	// 学生基本信息批量录入
	private ActionForward xsjbxxpllr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
//		String tableName = "hzjy_xsjbxxb";
		String realTable = "hzjy_xsjbxxb";
		HashMap<String, String> map = new HashMap<String, String>(); // 查询条件
		HttpSession session = request.getSession(false);
		String bmdm = session.getAttribute("userDep") == null?"":session.getAttribute("userDep").toString();
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String xh = request.getParameter("xh");
		String name = DealString.toGBK(request.getParameter("name"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String doType = request.getParameter("doType");
		String lrdw = request.getParameter("lrdw");
		String lrqk = request.getParameter("lrqk");
		String act = request.getParameter("act");
		String rsNum = "0"; // 记录数
		String[] colList = null;
		String[] colListCN = null;
		boolean flag = false;
		String xydm = "";
		String zydm = "";
		if (userType.equalsIgnoreCase("xy")
				&& (xymc == null || xymc.trim().equals(""))) {
			xymc = dao.getOneRs(
					"select distinct xymc from view_njxyzybj where xydm=?",
					new String[] { userDep }, "xymc");
		}
		
		map.put("xh", xh);
		map.put("name", name);
		map.put("nj", nj);
		map.put("xymc", xymc);
		map.put("zymc", zymc);
		map.put("bjmc", bjmc);
		map.put("lrdw", lrdw);
		map.put("lrqk", lrqk);
		
		sql = "select distinct xydm,xymc from view_njxyzybj where xymc=?";
		String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
				new String[] { "xydm" });
		if (null != xyinfo) {
			xydm = xyinfo[0];
		}
		sql = "select distinct zydm,zymc from view_njxyzybj where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		if ("save".equals(doType)) {
			String[] pks = request.getParameterValues("pkV");
			String[] sqls = new String[pks.length];
			if(lrdw !=null && lrdw.equals("bj")){
				for(int i=0;i<pks.length;i++){
					sql = "insert into hzjy_xsjbxxb_temp (xh, name, xb, nj, xymc, zymc, bjmc, sfzh) select xh, xm name,"
						+" xb, nj, xymc, zymc, bjmc, sfzh from view_xsjbxx a where bjdm='"+pks[i]+"' and not exists (select "
						+"xh from hzjy_xsjbxxb where xh=a.xh) and not exists (select xh from hzjy_xsjbxxb_temp where xh=a.xh)";
					sqls[i] = sql;
				}
			}else{
				for(int i=0;i<pks.length;i++){
					sql = "insert into hzjy_xsjbxxb_temp (xh, name, xb, nj, xymc, zymc, bjmc, sfzh) select xh, xm name,"
						+" xb, nj, xymc, zymc, bjmc, sfzh from view_xsjbxx a where xh='"+pks[i]+"'";
					sqls[i] = sql;
				}
			}
			int[] nums = dao.runBatch(sqls);
			flag = dao.checkBatch(nums);
			if (flag) {
				request.setAttribute("insert", "ok");
			} else {
				request.setAttribute("insert", "no");
			}
		}
		if("del".equals(doType)){
			String[] pks = request.getParameterValues("pkV");
			String[] sqls = new String[pks.length];
			if(lrdw !=null && lrdw.equals("bj")){
				for(int i=0;i<pks.length;i++){
					sql = "delete from hzjy_xsjbxxb_temp a where exists (select * from bks_bjdm where bjdm='"+pks[i]+"' and bjmc=a.bjmc)";
					sqls[i] = sql;
				}
			}else{
				for(int i=0;i<pks.length;i++){
					sql = "delete from hzjy_xsjbxxb_temp where xh='"+pks[i]+"'";
					sqls[i] = sql;
				}
			}
			int[] nums = dao.runBatch(sqls);
			flag = dao.checkBatch(nums);
			if (flag) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		
		if ("sh".equals(doType)) {
			String[] pks = request.getParameterValues("pkV");
			String[] sqls = new String[pks.length];
			String[] sqlsdel = new String[pks.length];
			if(lrdw !=null && lrdw.equals("bj")){
				for(int i=0;i<pks.length;i++){
					sql = "insert into hzjy_xsjbxxb (xh, name, xb, nj, xymc, zymc, bjmc, sfzh,xn) select xh,name,"
						+" xb, nj, xymc, zymc, bjmc, sfzh,'"+Base.currXn+"' xn from hzjy_xsjbxxb_temp a where exists (select * from bks_bjdm where bjdm='"+pks[i]+"' and bjmc=a.bjmc)";
					sqls[i] = sql;
					sql = "delete from hzjy_xsjbxxb_temp a where exists (select * from bks_bjdm where bjdm='"+pks[i]+"' and bjmc=a.bjmc)";
					sqlsdel[i] = sql;
				}
			}else{
				for(int i=0;i<pks.length;i++){
					sql = "insert into hzjy_xsjbxxb (xh, name, xb, nj, xymc, zymc, bjmc, sfzh,xn) select xh, name,"
						+" xb, nj, xymc, zymc, bjmc, sfzh ,'"+Base.currXn+"' xn from hzjy_xsjbxxb_temp a where xh='"+pks[i]+"'";
					sqls[i] = sql;
					sql = "delete from hzjy_xsjbxxb_temp a where xh='"+pks[i]+"'";
					sqlsdel[i] = sql;
				}
			}
			int[] nums1 = dao.runBatch(sqls);
			int[] nums2 = new int[sqlsdel.length];
			flag = dao.checkBatch(nums1);
			if(flag){
				nums2 = dao.runBatch(sqlsdel);
			}
			boolean flag1 =false;
			flag1 = dao.checkBatch(nums2);
			if (flag && flag1) {
				request.setAttribute("insert", "ok");
			} else {
				request.setAttribute("insert", "no");
			}
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");

		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if(userType.equals("admin") || userType.equals("xx")){
			if(lrdw != null && lrdw.equals("bj")){
				sql = "select a.bjdm pk,rownum 行号,a.*,(select count(*) from view_xsjbxx where bjdm=a.bjdm) bjzrs from (select distinct a.nj,b.bjdm,a.bjmc,xymc,zymc from hzjy_xsjbxxb_temp a,bks_bjdm b where a.bjmc=b.bjmc) a "+ query.toString();
			}else{
				sql = "select xh pk,rownum 行号,a.* from hzjy_xsjbxxb_temp a "+ query.toString();
			}
		} else if(userType.equals("xy")){
			if(lrqk != null && lrqk.equals("xyylr")){
				if(lrdw != null && lrdw.equals("bj")){
					sql = "select distinct a.bjdm pk,rownum 行号,a.*,(select count(*) from view_xsjbxx where bjdm=a.bjdm) bjzrs from (select distinct a.nj,b.bjdm,a.bjmc,xymc,zymc from hzjy_xsjbxxb_temp a,bks_bjdm b where a.bjmc=b.bjmc) a "+ query.toString();
				}else{
					sql = "select distinct xh pk,rownum 行号,a.* from hzjy_xsjbxxb_temp a "+ query.toString();
				}	
			}else if(lrqk != null && lrqk.equals("xxyqr")){
				if(lrdw != null && lrdw.equals("bj")){
					sql = "select distinct bjdm pk,rownum 行号,a.*,(select count(*) from view_xsjbxx where bjdm=a.bjdm) bjzrs from (select distinct a.nj,b.bjdm,a.bjmc,xymc,zymc,xn from hzjy_xsjbxxb a,bks_bjdm b where a.bjmc=b.bjmc) a "+query.toString()+" and xn='"+Base.currXn+"'";							
				}else{
					sql = "select distinct xh pk,rownum 行号,a.* from hzjy_xsjbxxb a "+ query.toString()+" and xn='"+Base.currXn+"'";
				}	
			}else{
				if(lrdw != null && lrdw.equals("bj")){
					sql = "select bjdm pk,rownum 行号,a.*,(select count(*) from view_xsjbxx where bjdm=a.bjdm) bjzrs from (select distinct nj,bjdm,bjmc,xymc,zymc,xydm from view_xsjbxx a  where not exists "
							+"(select distinct bjmc from hzjy_xsjbxxb where bjmc=a.bjmc and xn='"+Base.currXn+"') and not exists (select distinct bjmc from "
							+"hzjy_xsjbxxb_temp where bjmc=a.bjmc)) a "+ query.toString()+" and xydm= '"+bmdm+"'";
				}else{
					sql = "select xh pk,rownum 行号,a.*,a.xm name from view_xsjbxx a "
						+ query.toString()+" and xydm= '"+bmdm
						+"' and not exists (select xh from hzjy_xsjbxxb where xh=a.xh and xn='"+Base.currXn+"') and not "
						+"exists (select distinct bjmc from hzjy_xsjbxxb_temp where xh=a.xh)";		
				}
			}
		} else{
			request.setAttribute("view", "no");
			return mapping.findForward("success");
		}
		
		if(lrdw != null && lrdw.equals("bj")){
			colList = new String[] {"pk","行号", "bjdm","bjmc", "zymc", "xymc","bjzrs"};
		}else{
			colList = new String[] { "pk", "行号", "xh","name", "xb", "nj", "sfzh",
					"bjmc", "zymc", "xymc"};
		}
		List topTr = null;
		if ("go".equals(act)) {
			if(lrdw != null && lrdw.equals("bj")){
				colListCN = new String[]{"主键","行号","班级代码","班级名称","专业名称",Base.YXPZXY_KEY+"名称","班级总人数"};
			}else{
				colListCN = new String[]{"主键","行号","学号","姓名","性别","年级","身份证号","班级名称","专业名称",Base.YXPZXY_KEY+"名称"};
			}
			topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}
		//是否在合作教育时间范围内
		if(userType.equals("xy")){
			sql = "select * from hzjysjszb where xn=? and xq=? and fwbz=?";
			String[] timeVals = dao.getOneRs(sql, new String[] {Base.currXn,Base.currXq,"-100"},
					new String[]{"sqkssj", "sqjssj" });
			String rightTime = dao.getOneRs(
					"select to_char(sysdate,'yyyymmdd') rtime from dual",
					new String[] {}, new String[] { "rtime" })[0];
			if (timeVals == null || (rightTime.compareTo(timeVals[0]) < 0)
					|| (rightTime.compareTo(timeVals[1]) > 0)) {
				request.setAttribute("time", "no");
				return mapping.findForward("success");
			}
		}
		if(userType.equals("xy")){
			if(lrqk ==null){
				request.setAttribute("pltj", "yes");
			}else if(lrqk !=null && lrqk.equals("wlr")){
				request.setAttribute("pltj", "yes");
			}else if(lrqk !=null && lrqk.equals("xyylr") && lrdw != null){
				if(lrdw.equals("bj")){
					request.setAttribute("plsc", "yes");
				}else{
					request.setAttribute("plsc", "yes");
				}
			}else{
				request.setAttribute("plop", "no");
			}
		}else{
			request.setAttribute("plsc", "yes");
		}
		
		request.setAttribute("userType", userType);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("lrqk", lrqk);
		request.setAttribute("lrdw", lrdw);
		return mapping.findForward("success");
	}
	//学生安置信息转历史
	private ActionForward azxx_history(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) count from hzjy_xsazxxb where xn=?";
		String count = dao.getOneRs(sql, new String[]{Base.currXn}, "count");
		boolean flag = false;
		String[] sqls = new String[2];
		if(!count.equals("0")){
			sql = "insert into HZJY_XSAZXXLSB(XH,name,XN,nj,xymc,zymc,bjmc,GZDWQC,GZDWDZ,QY,YB,LXR,GZBM,xtyxm,XXSH,XXSHR,btgyy,xsdh,dwlxdh,hzjyqzsj) " 
					+"select XH,name,XN,nj,xymc,zymc,bjmc,GZDWQC,GZDWDZ,QY,YB,LXR,GZBM,xtyxm,XXSH,XXSHR,btgyy,xsdh,dwlxdh,hzjyqzsj from view_hzjyaz";
			sqls[0] = sql;
			sql = "delete from hzjy_xsazxxb where xn='"+Base.currXn+"'";
			sqls[1] = sql;
			int[] result = dao.runBatch(sqls);
			flag = dao.checkBatch(result);
			request.setAttribute("result", flag);
		}else{
			request.setAttribute("info", "yes");
		}
		hzjyazxxquery(mapping, form, request, response);
		return new ActionForward("/hzjy/hzjy_xsazxx_query.jsp");
	}
	//安置信息历史查询
	private ActionForward hzjyazxxhistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String tableName = "hzjy_xsazxxlsb"; // 查询信息源表（多为视图名）
		String realTable = "hzjy_xsazxxlsb"; // 学生安置信息表
		String rsNum = "0";// 返回的记录数
		String xn = request.getParameter("xn");// 学年
		String xh = request.getParameter("xh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String doType = request.getParameter("doType");
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 接收专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 接收班级名称
		String xydm = "";
		String zydm = "";
		String azxysh = DealString.toGBK(request.getParameter("azxysh"));// 接收学院审核
		String azxxsh = DealString.toGBK(request.getParameter("azxxsh"));// 接收学校审核
		String nj = request.getParameter("nj"); // 接收年级
		String pk = "xn||xh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
			sql = "select xydm from view_njxyzybj where xymc=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
		} else if ("xy".equals(userType)) {
			sql = "select szbm from yhb where yhm =?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm" });
			if (null != stuinfo) {
				xydm = stuinfo[0];
				sql = "select bmmc from zxbz_xxbmdm where bmdm=? ";
				xymc = dao.getOneRs(sql, new String[] { xydm }, "bmmc");
				map.put("xydm", xydm);
			}
			request.setAttribute("who", "xy");
		}
		sql = "select zydm from bks_zydm where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}
//		 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xh", xh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bjmc", bjmc);
			map.put("nj", nj);
			map.put("azxysh", azxysh);
			map.put("azxxsh", azxxsh);
		}
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		if ((xn != null) && !xn.equals("")) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("' ");
		}
		if ((xh != null) && !xh.equals("")) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if ((name != null) && !name.equals("")) {
			query.append(" and name like '%");
			query.append(name);
			query.append("%' ");
		}
		if ((xb != null) && !xb.equals("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		if ((xymc != null) && !xymc.equals("")) {
			query.append(" and xymc='");
			query.append(xymc);
			query.append("' ");
		}
		if ((zymc != null) && !zymc.equals("")) {
			query.append(" and zymc='");
			query.append(zymc);
			query.append("' ");
		}
		if ((bjmc != null) && !bjmc.equals("")) {
			query.append(" and bjmc='");
			query.append(bjmc);
			query.append("' ");
		}
		if ((nj != null) && !nj.equals("")) {
			query.append(" and nj='");
			query.append(nj);
			query.append("' ");
		}
		if ((azxxsh != null) && !azxxsh.equals("")) {
			query.append(" and xxsh='");
			query.append(azxxsh);
			query.append("' ");
		}
		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ query.toString();
		colList = new String[] { "主键", "行号", "xh", "name","xn", "xsdh", "gzdwqc",
				"dwlxdh", "lxr","hzjyqzsj"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xnndList", dao.getXnndList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		return mapping.findForward("success");

	}
//	 协调员信息维护 - 设置当前协调员
	private ActionForward setxty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "view_xtyxxb";
		String realTable = "hzjy_xtyxxb";
		HttpSession session = request.getSession(false);
		String userType = session.getAttribute("userType") == null ? "" : session.getAttribute("userType").toString();
		String xtydm = request.getParameter("xtydm"); // 协调员代码
		String xtyxm = DealString.toGBK(request.getParameter("xtyxm")); // 接收姓名
		String xtyyb = request.getParameter("xtyyb"); // 协调员邮编
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String xtyqy = DealString.toGBK(request.getParameter("xtyqy"));// 协调员区域
		String sql = "";
		String rsNum = "";
		String pkstring = request.getParameter("pkstring");
		String[] pkstringI = new String[] {};
		if (pkstring != null) {
			pkstringI = pkstring.split("!!#!!");
		}
		String[] sqls = new String[pkstringI.length-1];
		for (int i = 1; i < pkstringI.length; i++) {
			sql = "update hzjy_xtyxxb set xn='"+Base.currXn+"' where xtydm='"+pkstringI[i]+"'";
			sqls[i-1] = sql;
		}
		int[] num = dao.runBatch(sqls);
		boolean judge = dao.checkBatch(num);
		if(judge){
			request.setAttribute("setxty", "ok");
		}else{
			request.setAttribute("setxty", "no");
		}
		String querry = " where 1=1 ";
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("xtydm", xtydm);
		map.put("xtyxm", xtyxm);
		map.put("xtyyb", xtyyb);
		map.put("xymc", xymc);
		map.put("xtyqy", xtyqy);

		if (xtydm == null) {
			xtydm = "";
		}
		if (xtyxm == null) {
			xtyxm = "";
		}
		if (xtyyb == null) {
			xtyyb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (xtyqy == null) {
			xtyqy = "";
		}
		if ((xtydm == null) || xtydm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xtydm like '%" + xtydm + "%' ";
		}
		if ((xtyxm == null) || xtyxm.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xtyxm like '%" + xtyxm + "%' ";
		}
		if ((xtyyb == null) || xtyyb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyyb like'" + xtyyb + "%' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((xtyqy == null) || xtyqy.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xtyqy='" + xtyqy + "' ";
		}
		sql = "select a.xtydm ,rownum 行号,a.*,decode(a.xn,'"+Base.currXn+"','是','否') flag from " + realTable + " a "
		+ querry;
		String[] colList = new String[] { "xtydm", "行号", "xtydm", "xtyyhm", "xtyxm",
		"xtyxb", "xymc", "xtyqy", "xtyyb", "xtylxdh", "xtysjh","flag" };
		String[] colListCN = new String[] { "协调员代码", "行号", "协调员代码", "协调员用户名", "协调员姓名",
		"协调员性别", Base.YXPZXY_KEY+"名称", "协调员区域", "协调员邮编", "协调员联系电话", "协调员手机号","是否当前协调员" };
//		sql = "select a.xtydm ,rownum 行号,a.* from " + realTable + " a "
//				+ querry;
//		String[] colList = new String[] { "xtydm", "行号", "xtydm", "xtyyhm","xn", "xtyxm",
//				"xtyxb", "xymc", "xtyqy", "xtyyb", "xtylxdh", "xtysjh" };
//		String[] colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		if (rs == null) {
			rsNum = "0";
		} else {
				rsNum = String.valueOf(rs.size());
		}
		sql = "select dqdm,dqmc from dmk_shdqmc"; // 上海地区名称
		List dqmcList = dao.getList(sql, new String[] {}, new String[] {
				"dqdm", "dqmc" });
		if(userType.equals("admin") || userType.equals("xx")){
			request.setAttribute("who", "xx");
		}else{
			request.setAttribute("who", "xy");
		}
		request.setAttribute("dqmcList", dqmcList);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		return mapping.findForward("success");
	}
	private ActionForward scoreOperate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		CommanForm cform = (CommanForm)form;
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
		String operate = request.getParameter("operate");
		String doType = request.getParameter("doType");
		String cjlx = request.getParameter("cjlx");
		request.setAttribute("cjlx", Base.isNull(cjlx)?"":cjlx);
		if(cjlx != null && cjlx.equals("cgcj")){
			cjlx = "常规成绩";
		}else{
			cjlx = "补考成绩";
		}
		String sql = "select a.rowid from (select rowid,xh,xn,scoretype from view_hzjy_scoreb) a,hzjy_xsjbxxb b where a.xh=b.xh and a.xn=b.xn and b.rowid=? and a.scoretype=?";
		String pkValue1 = dao.getOneRs(sql, new String[]{pkValue,cjlx}, "rowid");
		String xn = Base.currXn;
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> hzdwList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> xtyList = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> ywbgList = new ArrayList<HashMap<String, String>>();
		
		sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
		String[] hzdwlevelinfo = dao.getOneRs(sql,
				new String[] { "合作单位评分" }, new String[] { "level1",
						"level2", "level3", "level4", "level5", "level6",
						"level7" });

		sql = "select level1,level2,level3,level4,level5,level6,level7 from hzjy_scoresetb where savetype=?";
		String[] xtylevelinfo = dao.getOneRs(sql, new String[] { "协调员评分" },
				new String[] { "level1", "level2", "level3", "level4",
						"level5", "level6", "level7" });

		sql = "select level1,level2,level3,level4,level5,level6 from hzjy_scoresetb where savetype=?";
		String[] ywbglevelinfo = dao.getOneRs(sql,
				new String[] { "业务报告评分" }, new String[] { "level1",
						"level2", "level3", "level4", "level5", "level6" });

		if (null != hzdwlevelinfo) {
			for (int i = 0; i < hzdwlevelinfo.length; i++) {
				HashMap<String, String> hzdwmap = new HashMap<String, String>();
				hzdwmap.put("hzdwlevel", hzdwlevelinfo[i]);
				hzdwList.add(hzdwmap);
			}
		}
		request.setAttribute("hzdwList", hzdwList);

		// 协调员评分
		if (null != xtylevelinfo) {
			for (int i = 0; i < xtylevelinfo.length; i++) {
				HashMap<String, String> xtymap = new HashMap<String, String>();
				xtymap.put("xtylevel", xtylevelinfo[i]);
				xtyList.add(xtymap);
			}
		}
		request.setAttribute("xtyList", xtyList);

		// 业务报告评分
		if (null != ywbglevelinfo) {
			for (int i = 0; i < ywbglevelinfo.length; i++) {
				HashMap<String, String> ywbgmap = new HashMap<String, String>();
				ywbgmap.put("ywbglevel", ywbglevelinfo[i]);
				ywbgList.add(ywbgmap);
			}
		}
		request.setAttribute("ywbgList", ywbgList);
		if(operate != null && operate.equals("save")){
			String xh = DealString.toGBK(request.getParameter("xh"));
			String scoretype = DealString.toGBK(request
					.getParameter("scoretype"));
			String hzdwscore = request.getParameter("hzdwscore");
			String xtyscore = request.getParameter("xtyscore");
			String ywbgscore = request.getParameter("ywbgscore");
			String allscore = request.getParameter("allscore");
			String hzdwlevel = DealString.toGBK(request
					.getParameter("hzdwlevel"));
			String xtylevel = DealString
					.toGBK(request.getParameter("xtylevel"));
			String ywbglevel = DealString.toGBK(request
					.getParameter("ywbglevel"));
			String hzdwdescribe = DealString.toGBK(request
					.getParameter("hzdwdescribe"));
			String xtydescribe = DealString.toGBK(request
					.getParameter("xtydescribe"));
			String ywbgdescribe = DealString.toGBK(request
					.getParameter("ywbgdescribe"));
			String zhpj = DealString.toGBK(request.getParameter("zhpj"));
			String alllevel = DealString
					.toGBK(request.getParameter("alllevel"));
			String kssj = DealString.toGBK(request.getParameter("hzjykssj"));
			String jssj = DealString.toGBK(request.getParameter("hzjyjssj"));

			boolean judge = false;

			
			if("update".equals(doType)){
				judge = StandardOperation.update("hzjy_scoreb", new String[] {
						"hzdwscore", "xtyscore", "ywbgscore", "allscore",
						"hzdwlevel", "xtylevel", "ywbglevel", "hzdwdescribe",
						"xtydescribe", "ywbgdescribe", "zhpj", "alllevel", "kssj",
						"jssj" }, new String[] { hzdwscore, xtyscore, ywbgscore,
						allscore, hzdwlevel, xtylevel, ywbglevel, hzdwdescribe,
						xtydescribe, ywbgdescribe, zhpj, alllevel, kssj, jssj },
						"xh||xn||scoretype", xh+xn+scoretype, request);

				if (judge) {
					if ("常规成绩".equals(scoretype)) {
						if ("F".equalsIgnoreCase(alllevel)) {
							StandardOperation.update("hzjy_xsjbxxb", new String[] {
									"score", "retest" }, new String[] { alllevel,
									"是" }, new String[] { "xh", "xn" },
									new String[] { xh, xn }, request);
						} else {
							StandardOperation.update("hzjy_xsjbxxb", new String[] {
									"score", "retest", "rescore" }, new String[] {
									alllevel, "否", "未录入" }, new String[] { "xh",
									"xn" }, new String[] { xh, xn }, request);
							StandardOperation.delete("hzjy_scoreb", new String[] {
									"xh", "xn", "scoretype" }, new String[] { xh,
									xn, "补考成绩" }, request);
						}
					} else {// 补考成绩
						StandardOperation.update("hzjy_xsjbxxb",
								new String[] { "rescore" },
								new String[] { alllevel }, new String[] { "xh",
										"xn" }, new String[] { xh, xn }, request);
					}
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			}else{
				judge = StandardOperation.insert("hzjy_scoreb", new String[] { "xh",
						"xn", "hzdwscore", "xtyscore", "ywbgscore", "allscore",
						"hzdwlevel", "xtylevel", "ywbglevel", "hzdwdescribe",
						"xtydescribe", "ywbgdescribe", "zhpj", "alllevel", "kssj",
						"jssj", "scoretype" }, new String[] { xh, xn, hzdwscore,
						xtyscore, ywbgscore, allscore, hzdwlevel, xtylevel,
						ywbglevel, hzdwdescribe, xtydescribe, ywbgdescribe, zhpj,
						alllevel, kssj, jssj, scoretype }, request);
				if (judge) {
					if ("常规成绩".equals(scoretype)) {
						if ("F".equalsIgnoreCase(alllevel)) {
							StandardOperation.update("hzjy_xsjbxxb", new String[] {
									"score", "retest" }, new String[] { alllevel,
									"是" }, new String[] { "xh", "xn" },
									new String[] { xh, xn }, request);
						} else {
							StandardOperation.update("hzjy_xsjbxxb",
									new String[] { "score" },
									new String[] { alllevel }, new String[] { "xh",
											"xn" }, new String[] { xh, xn },
									request);
						}
					} else {
						StandardOperation.update("hzjy_xsjbxxb",
								new String[] { "rescore" },
								new String[] { alllevel }, new String[] { "xh",
										"xn" }, new String[] { xh, xn }, request);
					}

					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
		}
		if(pkValue1 != null && !pkValue1.equals("")){
			// 查询数据
			sql = "select a.rowid,a.*,a.kssj hzjykssj,a.jssj hzjyjssj from view_hzjy_scoreb a where rowid=?";
			String[] colList = dao.getColumnName("select a.rowid,a.*,'' hzjykssj,'' hzjyjssj from view_hzjy_scoreb a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {pkValue1}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				sql = "select lev from hzjy_realscoresetb where lev=?";
				String hzdwchangeinfo = dao.getOneRs(sql, new String[] { map
						.get("hzdwlevel") }, "lev");
				if (null == hzdwchangeinfo || "".equals(hzdwchangeinfo)) {
					request.setAttribute("hzdwlevelchange", "hzdwlevelchange");
				}
				String xtychangeinfo = dao.getOneRs(sql, new String[] { map
						.get("xtylevel") }, "lev");
				if (null == xtychangeinfo || "".equals(xtychangeinfo)) {
					request.setAttribute("xtylevelchange", "xtylevelchange");
				}
				String ywbgchangeinfo = dao.getOneRs(sql, new String[] { map
						.get("ywbglevel") }, "lev");
				if (null == ywbgchangeinfo || "".equals(ywbgchangeinfo)) {
					request.setAttribute("ywbglevelchange", "ywbglevelchange");
				}
			}
			request.setAttribute("doType", "update");
		}else{
			//查询数据
			sql = "select * from hzjy_xsjbxxb where xh=? and xn=?";
			String[] colList = dao
					.getColumnName("select a.xh,a.name,a.xymc,a.zymc,a.gzdwqc,a.xtyxm,a.xn,a.score,a.retest,a.hzjykssj,a.hzjyjssj from hzjy_xsjbxxb a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] { cform.getXh(), xn },
					colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String hzjykssj = map.get("hzjykssj");
				String hzjyjssj = map.get("hzjyjssj");
				String countdays = GetTime.getDaysOfTowDate(hzjykssj, hzjyjssj);
				if ("参数出错！".equalsIgnoreCase(countdays)) {
					map.put("countdays", "该生未设置时间");
				} else {
					map.put("countdays", "共计" + countdays + "天");
				}
				map.put("scoretype", cjlx);
			}
			request.setAttribute("doType", "add");
		}
		String scoretype = map.get("scoretype");
		if ("常规成绩".equals(scoretype)) {
			request.setAttribute("color", "black");
		} else {
			request.setAttribute("color", "red");
		}
		int intallscore = 0;

		map.put("allscore", Integer.toString(intallscore));
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}


}
