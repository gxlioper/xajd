package xgxt.xljk.xlcs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.xljk.xlcs.form.xilk_zxpc_form;
import xgxt.xljk.xlcs.util.*;
import xgxt.xljk.lrh_Util.model.*;
import xgxt.xljk.xlkhxs.form.*;

/**
 * MyEclipse Struts Creation date: 07-27-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/xljk_xlcs_pcjgcx" name="xilk_zxpc_form" scope="request"
 *                validate="true"
 */
public class xljk_xlcs_pcjgcxAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String act = request.getParameter("act");
			String doType = request.getParameter("doType");

			if (act.equals("pcjgcx")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("stu_info")) {
						myActFwd = stu_info(mapping, form, request, response);
					} else if (doType.equals("cjb_search")) { // 查询学生成绩列表
						myActFwd = cjb_search(mapping, form, request, response);
					} else if (doType.equals("cjb_view")) { // 查看单个学生的成绩信息
						myActFwd = cjb_view(mapping, form, request, response);
					} else if (doType.equals("dtjlb_search")) { // 答题记录查询
						myActFwd = dtjlb_search(mapping, form, request,
								response);
					} else if (doType.equals("dtjlb_view")) {
						myActFwd = dtjlb_view(mapping, form, request, response);
					} else if (doType.equals("dtjlb_tj_search")) {
						myActFwd = dtjlb_tj_search(mapping, form, request,
								response);
					} else if (doType.equals("dtjgfxb_search")) {
						myActFwd = dtjgfxb_search(mapping, form, request,
								response);
					} else if (doType.equals("xlkhxs_pcjg_view")) {
						myActFwd = xlkhxs_pcjg_view(mapping, form, request,
								response);
					} else if (doType.equalsIgnoreCase("cjb_search_xjlk")) {
						myActFwd = cjb_search_xljk(mapping, form, request,
								response);
					} else if (doType.equalsIgnoreCase("stuMoreInfo")) {
						myActFwd = stuMoreInfo(mapping, form, request, response);
					} else if (doType.equalsIgnoreCase("cjb_search_gxcs")) {
						myActFwd = cjb_search_gxcs(mapping, form, request,
								response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		List xztjList = pcjgcx_util.pcjgcx_getXztjList();
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		request.setAttribute("sjList", sjList);
		request.setAttribute("xztjList", xztjList);
		request.setAttribute("tips", "心理健康－在线测试－普测结果查询");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward stu_info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		List xztjList = pcjgcx_util.pcjgcx_getXztjList();
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		String url = "/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=stu_info";
		String xh = request.getParameter("stu_id");
		if (StringUtils.isNotNull(xh)) {
			myform.setXh(xh);
			xztjList = pcjgcx_util.pcjgcx_getXztjList();
			sjList = pcjgcx_util.pcjgcx_getSjList();
			request.setAttribute("sjList", sjList);
			request.setAttribute("xztjList", xztjList);
			request.setAttribute("tips", "心理健康－在线测试－普测结果查询");
			request.setAttribute("writeAble", "yes");
			return mapping.findForward("index_to_jsp");
		} else {
			request.setAttribute("url", url);
			return mapping.findForward("stu_info");
		}
	}

	private ActionForward cjb_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		List xztjList = pcjgcx_util.pcjgcx_getXztjList();
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		List rs = pcjgcx_util.pcjgcx_cjjlb_search(myform);
		String tableName = "cjjlb";
		String[] zdm = { "XH", "DTSJ", "SJLSH", "CJ", "DTYS", "BZ" };
		List topTr = pcjgcx_util.Get_Table_Title(tableName, zdm);
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("sjList", sjList);
		request.setAttribute("xztjList", xztjList);
		request.setAttribute("tips", "心理健康－在线测试－普测结果查询");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward cjb_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String cjjlb_xnid = request.getParameter("cjjlb_xnid");
		myform.setCjjlb_xnid(cjjlb_xnid);
		myform = pcjgcx_util.pcjgcx_cjjlb_view(myform);
		String xh = myform.getXh();
		stu_info_model stinfo = new stu_info_model();
		stu_info_util st_util = new stu_info_util();
		stinfo = st_util.stu_find_byxh(xh);
		myform.setBjmc(stinfo.getBJMC());
		myform.setXymc(stinfo.getXYMC());
		myform.setXm(stinfo.getXM());
		myform.setXh(stinfo.getXH());
		request.setAttribute("rs", myform);
		return mapping.findForward("cjb_view");
	}

	private ActionForward dtjlb_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String cjjlb_xnid = request.getParameter("cjjlb_xnid");
		myform.setCjjlb_xnid(cjjlb_xnid);
		myform = pcjgcx_util.pcjgcx_cjjlb_view(myform);
		List stList = pcjgcx_util.pcjgcx_stb_search(myform);
		List sslbList = pcjgcx_util.pcjgcx_get_sslbList();
		List dtjlList = pcjgcx_util.pcjgcx_dtjlb_search(myform);
		String[] zdm = { "STXH", "SJLSH", "STLSH", "DTDA", "DTFZ", "SSLXMC" };
		List topTr = pcjgcx_util.Get_Table_Title("VIEW_XLJK_XLCS_DTLJB", zdm);
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		List stxhList = pcjgcx_util.pcjgcx_stxh_search(myform);
		String rsNum = String.valueOf(dtjlList.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", dtjlList);
		request.setAttribute("stxhList", stxhList);
		request.setAttribute("stList", stList);
		request.setAttribute("sjList", sjList);
		request.setAttribute("sslbList", sslbList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", "心理健康 － 在线测试 － 普测结果查询 － 答题记录查询");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("dtjlb_search");
	}

	private ActionForward dtjlb_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// xilk_zxpc_form myform = (xilk_zxpc_form) form;
		// xljk_xlcs_pcjgcx_util pcjgcx_util= new xljk_xlcs_pcjgcx_util();

		return mapping.findForward("dtjlb_view");
	}

	private ActionForward dtjlb_tj_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		List dtjlList = pcjgcx_util.pcjgcx_dtjl_tjsearch(myform);
		List stList = pcjgcx_util.pcjgcx_stb_search(myform);
		List sslbList = pcjgcx_util.pcjgcx_get_sslbList();
		String[] zdm = { "STXH", "SJLSH", "STLSH", "DTDA", "DTFZ", "SSLXMC" };
		List topTr = pcjgcx_util.Get_Table_Title("VIEW_XLJK_XLCS_DTLJB", zdm);
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		List stxhList = pcjgcx_util.pcjgcx_stxh_search(myform);
		String rsNum = String.valueOf(dtjlList.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", dtjlList);
		request.setAttribute("stxhList", stxhList);
		request.setAttribute("stList", stList);
		request.setAttribute("sjList", sjList);
		request.setAttribute("sslbList", sslbList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", "心理健康 － 在线测试 － 普测结果查询 － 答题记录查询");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("dtjlb_search");
	}

	private ActionForward dtjgfxb_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String cjjlb_xnid = request.getParameter("cjjlb_xnid");
		myform.setCjjlb_xnid(cjjlb_xnid);
		myform = pcjgcx_util.pcjgcx_cjjlb_view(myform);
		List stList = pcjgcx_util.pcjgcx_stb_search(myform);
		List sslbList = pcjgcx_util.pcjgcx_get_sslbList();

		List csfxList = pcjgcx_util.pcjgcx_csfx2(myform);
		String rsNum = String.valueOf(csfxList.size());
		String[] zdm = { "SSLXMC", "DF" };
		List topTr = pcjgcx_util.Get_Table_Title("VIEW_XLJK_CSFX", zdm);
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		List stxhList = pcjgcx_util.pcjgcx_stxh_search(myform);
		request.setAttribute("rs", csfxList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("stxhList", stxhList);
		request.setAttribute("stList", stList);
		request.setAttribute("sjList", sjList);
		request.setAttribute("sslbList", sslbList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", "心理健康－在线测试－答题记录分析");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("dtjgfxb_search");
	}

	private ActionForward xlkhxs_pcjg_view(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xilk_zxpc_form myform = (xilk_zxpc_form) form;
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		pcjgcx_util.xljk_xlcs_pcjgcx_util1(request);
		String cjjlb_xnid = request.getParameter("cjjlb_xnid");
		myform.setCjjlb_xnid(cjjlb_xnid);
		myform = pcjgcx_util.pcjgcx_cjjlb_view(myform);
		// List stList=pcjgcx_util.pcjgcx_stb_search(myform);
		// List sslbList=pcjgcx_util.pcjgcx_get_sslbList();
		try {
			xljk_xlkhxs_form xlkhxs_form = new xljk_xlkhxs_form();
			Object ob = request.getAttribute("xljk_xlkhxs_form");
			xlkhxs_form = (xljk_xlkhxs_form) ob;
			myform.setXh(xlkhxs_form.getXh());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		List rs = pcjgcx_util.xlkhxsxx_pcjgcx(myform);
		List xztjList = pcjgcx_util.pcjgcx_getXztjList();
		List sjList = pcjgcx_util.pcjgcx_getSjList();
		String tableName = "cjjlb";
		String[] zdm = { "XH", "DTSJ", "SJLSH", "CJ", "DTYS", "BZ" };
		List topTr = pcjgcx_util.Get_Table_Title(tableName, zdm);
		String rsNum = String.valueOf(rs.size());
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("sjList", sjList);
		request.setAttribute("xztjList", xztjList);
		request.setAttribute("tips", "心理健康－在线测试－普测结果查询");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward cjb_search_xljk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String search = request.getParameter("search");
		List<HashMap<String, String>> stuList = new ArrayList<HashMap<String, String>>();
		;
		request.setAttribute("tips", "心理健康－在线测试－心理健康自评试卷结果查询");
		request.setAttribute("tableName", "view_xljkzptj");
		request.setAttribute("realTable", "xljkzptjb");
		if (search == null || search.equalsIgnoreCase("")) {
			return mapping.findForward("cjb_search_xljk");
		} else {
			Integer rowIndex = Integer.parseInt(request
					.getParameter("rowIndex"));// 取的行数
			// 取得第一行数据
			String yz = request.getParameter("yz");// 因子
			String tj = request.getParameter("tj");// 条件
			String df = request.getParameter("yzdf");// 得分
			if (rowIndex == 1) {
				stuList = pcjgcx_util.getOkStuList(yz, tj, df);
				String sql = pcjgcx_util.getSqlByCondi(yz, tj, df);
				request.setAttribute("sql", sql);
				request.setAttribute("yz", yz);
				request.setAttribute("tj", tj);
				request.setAttribute("yzdf", df);
			} else if (rowIndex > 1) {
				// 表明有多行数据,循环取得数据
				String tempSql = pcjgcx_util.getSqlByCondi(yz, tj, df);
				for (int i = 2; i < rowIndex + 1; i++) {
					yz = request.getParameter("yz" + i);// 因子
					tj = request.getParameter("tj" + i);// 条件
					df = request.getParameter("yzdf" + i);// 得分
					tempSql += pcjgcx_util.getOtherSql(yz, tj, df);
				}
				request.setAttribute("sql", tempSql);
				stuList = pcjgcx_util.getConGtOneStuList(tempSql);
			}
		}

		String[] zdm = new String[] { "xh", "xb", "nl", "zf", "zjf", "yxzztksp" };
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String[] top = new String[] { "学号", "性别", "年龄", "总分", "总均分", "阳性症状痛苦水平" };
		// pcjgcx_util.Get_Table_Title("xljkzptjb", zdm);
		for (int i = 0; i < top.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", zdm[i]);
			map.put("cn", top[i]);
			topTr.add(map);
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", stuList);
		request.setAttribute("rsNum", stuList.size());
		return mapping.findForward("cjb_search_xljk");
	}

	@SuppressWarnings("unchecked")
	private ActionForward stuMoreInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String xh = request.getParameter("xh").trim();
		String[] output = new String[] { "qthyz", "qpzzyz", "rjgxmgyz", "yyyz",
				"jlyz", "ddyz", "kbyz", "pzyz", "jsbxyz", "fjyz" };
		// 根据学号得到学生的分数信息
		String[] stuMoreInfo = pcjgcx_util.getMoreStuInfo(xh, output);
		// 得到中文
		List<HashMap<String, String>> topTr = pcjgcx_util.Get_Table_Title(
				"xljkzptjb", output);
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < output.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("zw", topTr.get(i + 1).get("cn"));
			map.put("df", stuMoreInfo[i] + "分");
			result.add(map);
		}
		request.setAttribute("tips", "学生做题详细分数");
		request.setAttribute("result", result);
		return mapping.findForward("stumoreinfo");
	}

	private ActionForward cjb_search_gxcs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		xljk_xlcs_pcjgcx_util pcjgcx_util = new xljk_xlcs_pcjgcx_util();
		String search = request.getParameter("search");
		request.setAttribute("tips", "心理健康－在线测试－个性自评试卷结果查询");
		if (search == null || search.equalsIgnoreCase("")) {
			return mapping.findForward("cjb_search_gxcs");
		} else {
			List topTr = new ArrayList();
			List<HashMap<String, String>> stuList = new ArrayList<HashMap<String, String>>();
			Integer rowIndex = Integer.parseInt(request
					.getParameter("rowIndex"));// 取得行数
			// 判断是因子还是得分
			String currCondi = request.getParameter("currCondi");
			String tempSql = " where 1=1  ";
			if (currCondi.equalsIgnoreCase("0")) {
				// 因子
				String yz = null;
				String tj = null;
				String df = null;
				for (int i = 1; i < rowIndex + 1; i++) {
					yz = request.getParameter("yz" + i);// 因子
					tj = request.getParameter("tj" + i);// 条件
					df = request.getParameter("yzdf" + i);// 得分
					tempSql += pcjgcx_util.getAllSqlByYz(yz, tj, df);
				}
				// System.out.println(tempSql);
				// get ok students
				String[] zdm = new String[] { "XH", "A", "B", "C", "E", "F",
						"G", "H", "I", "L", "M", "N", "O", "Q1", "Q2", "Q3",
						"Q4" };
				topTr = pcjgcx_util.Get_Table_Title("zcyscjzbfb", zdm);
				stuList = pcjgcx_util.getOkStuByYz(tempSql);
				request.setAttribute("tableName", "view_zcyscjzbf");
				request.setAttribute("realTable", "zcyscjzbfb");
			} else if (currCondi.equalsIgnoreCase("1")) {
				// 试题
				String th = null;
				String tj = null;
				String thdf = null;
				for (int i = 1; i < rowIndex + 1; i++) {
					th = request.getParameter("th" + i);// 题号
					tj = request.getParameter("tj" + i);// 条件
					thdf = request.getParameter("thdf" + i);// 得分
					tempSql += pcjgcx_util.getAllSqlByExam(th, tj, thdf);
				}
				// System.out.println(tempSql);
				String[] zdm = new String[] { "xh", "th", "yx", "df" };
				topTr = pcjgcx_util.Get_Table_Title("zcystmdfb", zdm);
				stuList = pcjgcx_util.getOkStuByExam(tempSql);
				request.setAttribute("tableName", "view_zcystmdf");
				request.setAttribute("realTable", "zcystmdfb");
			}
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsNum", stuList.size());
			request.setAttribute("rs", stuList);
			request.setAttribute("sql", tempSql);
			request.setAttribute("currCondi", currCondi);
		}
		return mapping.findForward("cjb_search_gxcs");
	}
}