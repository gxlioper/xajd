package xgxt.xljk.xlcs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.xlcs.form.xilk_xlcsTkwh_form;
import xgxt.xljk.xlcs.util.*;
//import xgxt.xszz.ynys.XszzYnysActionForm;
//import xgxt.xszz.ynys.XszzYnysService;

import java.util.*;

public class xljk_xlcs_tkwhAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form xilk_xlcsTkwh_form = (xilk_xlcsTkwh_form) form;
		ActionForward myActFwd = null;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				xilk_xlcsTkwh_form.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			String act = request.getParameter("act");
			String doType = request.getParameter("doType");

			if (act.equals("tkwh")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("Modi")) { // 修改试卷
						myActFwd = sj_modi(mapping, form, request, response);
					} else if (doType.equals("Del")) { // 删除试卷
						myActFwd = sj_del(mapping, form, request, response);
					} else if (doType.equals("Insert")) { // 增加试卷
						myActFwd = sj_insert(mapping, form, request, response);
					} else if (doType.equals("search")) { // 显示试卷列表
						myActFwd = sj_search(mapping, form, request, response);
					} else if (doType.equals("View")) {
						myActFwd = sj_view(mapping, form, request, response);
					} else if (doType.equals("st_index")) { // 题库维护首页
						myActFwd = st_index(mapping, form, request, response);
					} else if (doType.equals("st_add")) { // 增加试题
						myActFwd = st_add(mapping, form, request, response);
					} else if (doType.equals("st_search")) {
						myActFwd = st_search(mapping, form, request, response);
					} else if (doType.equals("st_view")) { // 显示试题信息
						myActFwd = st_view(mapping, form, request, response);
					} else if (doType.equals("st_del")) { // 删除试题
						myActFwd = st_del(mapping, form, request, response);
					} else if (doType.equals("st_modi")) { // 修改试题
						myActFwd = st_modi(mapping, form, request, response);
					} else if (doType.equals("st_stsslb_pipei")) { // 试题所属类别 匹配
						myActFwd = st_sslb_pipei(mapping, form, request,
								response);
					} else if (doType.equals("st_xxwh")) { // 试题选项维护
						myActFwd = st_xxwh(mapping, form, request, response);
					} else if (doType.equals("st_xx_view")) { // 试题选项显示
						myActFwd = st_xx_view(mapping, form, request, response);
					} else if (doType.equals("st_xx_add")) { // 试题选项增加
						myActFwd = st_xx_add(mapping, form, request, response);
					} else if (doType.equals("st_xx_modi")) { // 试题选项修改
						myActFwd = st_xx_modi(mapping, form, request, response);
					} else if (doType.equals("st_xx_del")) { // 试题选项删除
						myActFwd = st_xx_del(mapping, form, request, response);
					} else if (doType.equals("zjwh")) { // 组卷维护
						myActFwd = zjwh(mapping, form, request, response);
					} else if (doType.equals("sjst_search")) {
						myActFwd = sjst_search(mapping, form, request, response);
					} else if (doType.equals("sjst_view")) { // 查看试题的详细信息
						myActFwd = sjst_view(mapping, form, request, response);
					} else if (doType.equals("sjst_zj_wh")) { // 试卷试题组卷维护
						myActFwd = sjst_zj_wh(mapping, form, request, response);
					} else if (doType.equals("sjst_st_search")) {// 试卷试题 试题
						// 查询
						myActFwd = sjst_st_search(mapping, form, request,
								response);
					} else if (doType.equals("sjst_st_add")) { // 试卷试题 试题
						// 增加（保存试卷试题信息）
						myActFwd = sjst_st_add(mapping, form, request, response);
					} else if (doType.equals("common_exp")) { // 导出数据
						// 增加（保存试卷试题信息）
						myActFwd = common_exp(mapping, form, request, response);
					} else if (doType.equals("yzfst")) { // 导出数据
						// 给因子分试题
						myActFwd = yzfst(mapping, form, request, response);
					}
				} else {
					myActFwd = index_to_jsp(mapping, form, request, response);
				}
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			return myActFwd;
			// xilk_xlcsTkwh_form.setErrMsg("数据连接中断，请重新登陆！");
			// return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward index_to_jsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		String act = request.getParameter("act");
		List li = myutil.tkwh_getSjlshList();
		String tip = "心理健康-在线测试-试卷试题维护";
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", "sjb");
		request.setAttribute("act", act);
		request.setAttribute("realTable", "sjb");
		request.setAttribute("pk", "SJLSH");
		request.setAttribute("sjList", li);
		request.setAttribute("tips", tip);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward sj_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		List li = myutil.tkwh_findBy_sql(myform);
		List sjlshList = myutil.tkwh_getSjlshList();
		String rsNum = String.valueOf(li.size());
		String[] zdm = { "SJLSH", "SJM", "SJXD", "SJXSBJ", "JRSJ" };
		List toptr = myutil.Get_Table_Title("sjb", zdm);
		String tip = "心理健康-在线测试-试卷试题维护";
		request.setAttribute("rs", li);
		request.setAttribute("topTr", toptr);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", "sjb");
		request.setAttribute("act", myform.getAct());
		request.setAttribute("realTable", "sjb");
		request.setAttribute("pk", "SJLSH");
		request.setAttribute("sjList", sjlshList);
		request.setAttribute("tips", tip);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward sj_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sjlsh = request.getParameter("sjlsh");
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.setSjlsh(sjlsh);
		myform.deal_gbk();
		myform = myutil.tkwh_findBySjlsh(myform);
		myform.setXlcsxmmc(myutil.getXlcsxmmc(myform.getXlcsxmdm()));
		request.setAttribute("rs", myform);
		return mapping.findForward("view_sj");
	}

	private ActionForward sj_insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String add_flag = request.getParameter("add_flag");
		if (!StringUtils.isNull(add_flag) && add_flag.equals("yes")) {
			String message = myutil.tkwh_add_sj(myform);
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ynList", myutil.getCheckList(2));
		request.setAttribute("csxmList", myutil.getCsxmList());
		request.setAttribute("rs", myform);
		return mapping.findForward("add_sj");
	}

	private ActionForward sj_modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xilk_xlcsTkwh_form modiform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		modiform = (xilk_xlcsTkwh_form) form;
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String sjlsh = request.getParameter("sjlsh");
		myform.setSjlsh(sjlsh);
		myform = myutil.tkwh_findBySjlsh(myform);
		String modi_flag = request.getParameter("modi_flag");
		if (!StringUtils.isNull(modi_flag) && modi_flag.equals("yes")) {
			modiform.setSjlsh(sjlsh);
			String message = myutil.tkwh_modi_sj(modiform);
			request.setAttribute("message", message);
			request.setAttribute("rs", modiform);
		} else {
			request.setAttribute("rs", myform);
		}
		request.setAttribute("csxmList", myutil.getCsxmList());
		request.setAttribute("sjlsh", myform.getSjlsh());
		request.setAttribute("ynList", myutil.getCheckList(2));
		return mapping.findForward("modi_sj");
	}

	private ActionForward sj_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xilk_xlcsTkwh_form tryform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String sjlsh = request.getParameter("sjlsh");
		myform.setSjlsh(sjlsh);
		String message = myutil.tkwh_del_sj(myform);
		List li = myutil.tkwh_findBy_sql(tryform);
		List sjlshList = myutil.tkwh_getSjlshList();
		String rsNum = String.valueOf(li.size());
		String[] zdm = { "SJLSH", "SJM", "SJXD", "SJXSBJ", "JRSJ" };
		List toptr = myutil.Get_Table_Title("sjb", zdm);
		String tip = "心理健康-在线测试-试卷试题维护";
		request.setAttribute("rs", li);
		request.setAttribute("topTr", toptr);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", "sjb");
		request.setAttribute("act", myform.getAct());
		request.setAttribute("realTable", "sjb");
		request.setAttribute("pk", "SJLSH");
		request.setAttribute("sjList", sjlshList);
		request.setAttribute("tips", tip);
		request.setAttribute("message", message);
		return mapping.findForward("index_to_jsp");
	}

	private ActionForward st_index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		List stlshList = myutil.tkwh_getStlshList();
		String tip = "心理健康 - 心理测试 - 题库维护";
		String act = request.getParameter("doType");
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("stndjbList", myutil.tkwh_getStndjbbList());
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", "stb");
		request.setAttribute("act", act);
		request.setAttribute("realTable", "stb");
		request.setAttribute("pk", "STLSH");
		request.setAttribute("stlshList", stlshList);
		request.setAttribute("tips", tip);
		return mapping.findForward("st_index");
	}

	private ActionForward st_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String add_flag = request.getParameter("add_flag");
		if (StringUtils.isNotNull(add_flag)) {
			if (add_flag.equals("yes")) {
				String message = myutil.tkwh_add_st(myform);
				request.setAttribute("rs", myform);
				request.setAttribute("message", message);
			}
		} else {
			request.setAttribute("rs", myform);
		}
		List ynList = myutil.getCheckList(2);
		List stndjbList = myutil.tkwh_getStndjbbList();
		List stlxList = myutil.tkwh_getStlxList();
		request.setAttribute("stlxList", stlxList);
		request.setAttribute("stndjbList", stndjbList);
		request.setAttribute("ynList", ynList);
		return mapping.findForward("st_add");
	}

	private ActionForward st_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tip = "心理健康 - 心理测试 - 题库维护";
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		List li = myutil.tkwh_search_st(myform);
		String[] zdm = { "STLSH", "STLXMC", "STNDJBMC", "STNR", "SSLXMC",
				"STJFFS", "STFZ", "STXSBJ" };
		List toptr = myutil.Get_Table_Title("VIEW_XLJK_XLCS_ST", zdm);
		String rsNum = String.valueOf(li.size());
		if (StringUtils.isNotNull(myform.getMessage())) {
			request.setAttribute("message", myform.getMessage());
		}
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("stndjbList", myutil.tkwh_getStndjbbList());
		request.setAttribute("tableName", "VIEW_XLJK_XLCS_ST");
		request.setAttribute("pk", "stlsh");
		request.setAttribute("realTable", "VIEW_XLJK_XLCS_ST");
		request.setAttribute("tips", tip);
		request.setAttribute("rs", li);
		request.setAttribute("topTr", toptr);
		request.setAttribute("act", myform.getAct());
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("st_search");
	}

	private ActionForward st_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tip = "心理健康 - 心理测试 - 题库维护";
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		myform = myutil.tkwh_view_st(myform);
		request.setAttribute("rs", myform);
		request.setAttribute("tips", tip);
		return mapping.findForward("st_view");
	}

	private ActionForward st_modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String modi_flag = request.getParameter("modi_flag");
		if (modi_flag != null && !modi_flag.equalsIgnoreCase("")) {
			if (modi_flag.equals("yes")) {
				String message = myutil.tkwh_modi_st(myform);// myutil.tkwh_add_st(myform);
				request.setAttribute("rs", myform);
				request.setAttribute("message", message);
			}
		} else {
			myform = myutil.tkwh_view_st2(myform);
			request.setAttribute("rs", myform);
		}
		List ynList = myutil.getCheckList(2);
		List stndjbList = myutil.tkwh_getStndjbbList();
		List stlxList = myutil.tkwh_getStlxList();
		request.setAttribute("stlsh", myform.getStlsh());
		request.setAttribute("stlxList", stlxList);
		request.setAttribute("stndjbList", stndjbList);
		request.setAttribute("ynList", ynList);
		return mapping.findForward("st_modi");
	}

	private ActionForward st_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		String stlsh = request.getParameter("stlsh");
		myform.setStlsh(stlsh);
		String message = "";
		message = myutil.tkwh_del_st(myform);
		request.setAttribute("message", message);
		myform.setMessage(message);
		return this.st_search(mapping, myform, request, response);
	}

	private ActionForward st_sslb_pipei(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String pipei_flag = request.getParameter("pipei_flag");
		String message = "";
		if (StringUtils.isNotNull(pipei_flag)) {
			if (pipei_flag.equals("yes")) {
				message = myutil.tkwh_stsslb_pipei(myform);
				request.setAttribute("rs", myform);
				request.setAttribute("message", message);
			}
		} else {
			myform = myutil.tkwh_view_st(myform);
			request.setAttribute("rs", myform);
		}
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		return mapping.findForward("stsslb_pipei");
	}

	private ActionForward st_xxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform.deal_gbk();
		myform.setStlsh(myform.getStlsh());
		String xx_search_flag = request.getParameter("xx_search_flag");
		if (xx_search_flag != null && !xx_search_flag.equalsIgnoreCase("")) {
			if (xx_search_flag.equals("yes")) {
				List li = myutil.tkwh_stb_xxxx(myform);
				String[] zdm = { "XXLSH", "STLSH", "XXXH", "XXNR", "XXFZ",
						"XXXSBJ" };
				List topTr = myutil.Get_Table_Title("xxb", zdm);
				String rsNum = String.valueOf(li.size());
				request.setAttribute("rs", li);
				request.setAttribute("rsNum", rsNum);
				request.setAttribute("topTr", topTr);
			}
		}
		List stlshList = myutil.tkwh_getStlshList();

		if (StringUtils.isNotNull(myform.getMessage())) {
			request.setAttribute("message", myform.getMessage());
		}
		request.setAttribute("stlshList", stlshList);
		request.setAttribute("tableName", "xxb");
		request.setAttribute("realTable", "xxb");
		request.setAttribute("pk", "XN_ID");
		request.setAttribute("act", "st_xx");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("st_xx_index");
	}

	private ActionForward st_xx_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform.deal_gbk();
		String xxlsh = request.getParameter("xxlsh");
		myform.setXxlsh(xxlsh);
		myform = myutil.tkwh_stb_xxxxxx(myform);
		request.setAttribute("rs", myform);
		request.setAttribute("ynList", myutil.getCheckList(2));
		return mapping.findForward("st_xx_view");
	}

	private ActionForward st_xx_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform.deal_gbk();
		String stlsh = request.getParameter("stlsh");
		myform.setStlsh(stlsh);
		String st_xx_add_flag = request.getParameter("st_xx_add_flag");
		if (StringUtils.isNotNull(st_xx_add_flag)) {
			if (st_xx_add_flag.equals("yes")) {
				String message = myutil.tkwh_stb_xx_add(myform);
				request.setAttribute("rs", myform);
				request.setAttribute("message", message);
			}
		} else {
			request.setAttribute("rs", myform);
		}
		List ynList = myutil.getCheckList(2);
		request.setAttribute("ynList", ynList);
		return mapping.findForward("st_xx_add");
	}

	private ActionForward st_xx_modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform.deal_gbk();
		String xxlsh = request.getParameter("xxlsh");
		myform.setXxlsh(xxlsh);
		String st_xx_modi_flag = request.getParameter("st_xx_modi_flag");
		if (StringUtils.isNotNull(st_xx_modi_flag)) {
			if (st_xx_modi_flag.equals("yes")) {
				String message = myutil.tkwh_stb_xx_modi(myform);
				request.setAttribute("rs", myform);
				request.setAttribute("message", message);
			}
		} else {
			myform = myutil.tkwh_stb_xxxxxx(myform);
			request.setAttribute("rs", myform);
		}
		List ynList = myutil.getCheckList(2);
		request.setAttribute("ynList", ynList);
		return mapping.findForward("st_xx_modi");
	}

	private ActionForward st_xx_del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform.deal_gbk();
		String xxlsh = request.getParameter("xxlsh");
		myform.setXxlsh(xxlsh);
		String message = myutil.tkwh_st_xx_del(myform);
		myform.setMessage(message);
		return this.st_xxwh(mapping, myform, request, response);
	}

	private ActionForward zjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String tip = "心理健康 - 心理测试 - 题库维护 - 组卷维护";
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tips", tip);
		request.setAttribute("tableName", "sjstb");
		request.setAttribute("act", "zjwh");
		request.setAttribute("realTable", "sjstb");
		request.setAttribute("pk", "SJLSH");
		request.setAttribute("sjList", myutil.tkwh_getSjlshList());
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("stndjbList", myutil.tkwh_getStndjbbList());
		return mapping.findForward("zjwh");
	}

	private ActionForward sjst_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String tip = "心理健康 - 心理测试 - 题库维护 - 组卷维护";
		String tableName = "VIEW_XLJK_XLCS_SJSTB";
		String[] zdm = { "SJLSH", "SJM", "STXH", "STLXMC", "STNDJBMC", "STNR",
				"STFZ", "STDA", "STLSH", "SSLXMC" };
		List topTr = myutil.Get_Table_Title(tableName, zdm);
		List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
		li = myutil.tkwh_sjst_search(myform);
		String rsNum = String.valueOf(li.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", li);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tips", tip);
		request.setAttribute("tableName", "sjstb");
		request.setAttribute("act", "zjwh");
		request.setAttribute("realTable", "sjstb");
		request.setAttribute("pk", "SJLSH");
		request.setAttribute("sjList", myutil.tkwh_getSjlshList());
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("stndjbList", myutil.tkwh_getStndjbbList());
		return mapping.findForward("sjst_search");
	}

	private ActionForward sjst_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		String tip = "心理健康 - 心理测试 - 题库维护 - 组卷维护 - 试题详细信息";
		String sjst_xnid = request.getParameter("sjst_xnid");
		myform.setSjst_xnid(sjst_xnid);
		myform = myutil.tkwh_sjst_view(myform);
		request.setAttribute("rs", myform);
		request.setAttribute("tips", tip);
		return mapping.findForward("sjst_view");
	}

	private ActionForward sjst_zj_wh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		myform.setSjm(request.getParameter("sjm"));
		myform.deal_gbk();
		String sjlsh = request.getParameter("sjlsh");
		List stList = myutil.tkwh_getstList(sjlsh);
		request.setAttribute("stNum", myutil.getstTotalNumBySjlsh(sjlsh));
		request.setAttribute("stList", stList);
		request.setAttribute("rs", myform);
		return mapping.findForward("sjst_zj_wh");
	}

	private ActionForward sjst_st_search(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tip = "心理健康 - 心理测试 - 题库维护 - 组卷维护 - 增加试题到试卷";
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform = (xilk_xlcsTkwh_form) form;
		myform.deal_gbk();
		List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
		String[] zdm = { "STLSH", "STLXMC", "STNDJBMC", "STNR", "SSLXMC",
				"STJFFS", "STFZ", "STDA", "STXSBJ" };
		List toptr = myutil.Get_Table_Title("VIEW_XLJK_XLCS_ST", zdm);
		request.setAttribute("message",
				StringUtils.isNull(myform.getMessage()) ? "" : myform
						.getMessage());
		if (StringUtils.isNotNull(request.getParameter("search"))) {
			li = myutil.tkwh_search_st2(myform);
		}
		String rsNum = String.valueOf(li.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("stsslbList", myutil.tkwh_getStsslbList());
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("stndjbList", myutil.tkwh_getStndjbbList());
		request.setAttribute("tableName", "VIEW_XLJK_XLCS_ST");
		request.setAttribute("pk", "stlsh");
		request.setAttribute("realTable", "VIEW_XLJK_XLCS_ST");
		request.setAttribute("tips", tip);
		request.setAttribute("rs", li);
		request.setAttribute("topTr", toptr);
		request.setAttribute("act", myform.getAct());
		request.setAttribute("writeAble", "yes");
		//request.setAttribute("myform", myform);
		return mapping.findForward("sjst_st_search");
	}

	/** 数据导出 */
	public ActionForward common_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myForm = (xilk_xlcsTkwh_form)form;
		xljk_XlcsTkwh_util service = new xljk_XlcsTkwh_util();
		String tableName = "VIEW_XLJK_XLCS_ST";
		String[] columns = new String[] { "stlxdm", "stndjbdm", "sslxdm" };
		service.getCommonExp(myForm, response, request, tableName, columns);
		return mapping.findForward("common_exp");
	}
	
	// now no used
	private ActionForward sjst_st_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = new xilk_xlcsTkwh_form();
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myutil.xljk_XlcsTkwh_util1(request);
		myform = (xilk_xlcsTkwh_form) form;
		String yxstStr = request.getParameter("yxstStr");
		myform.setYxstStr(yxstStr);
		myform.deal_gbk();
		myutil.tkwh_sjst_st_add(myform);
//		String message = myutil.tkwh_sjst_st_add(myform);
		return null;
	}
	//因子分试题
	private ActionForward yzfst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xilk_xlcsTkwh_form myform = (xilk_xlcsTkwh_form) form;
		xljk_XlcsTkwh_util myutil = new xljk_XlcsTkwh_util();
		myform.setYzmc(DealString.toGBK(myform.getYzmc()));
		myform.setStxsbj(DealString.toGBK(myform.getStxsbj()));
		String operation = request.getParameter("do");
		String newValue = request.getParameter("newValue");
		String oldValue = request.getParameter("oldValue");
		if(Base.isNull(myform.getYzdm())){
			request.setAttribute("yfyz", "yes");
		}else{
			request.setAttribute("yfyz", "no");
			request.setAttribute("yzmc", myform.getYzmc());
		}
		if(!Base.isNull(operation) && operation.equals("save")){
			String[] array = newValue.split(",");
			boolean flag = myutil.saveYzst(myform,array);
			request.setAttribute("flag", flag);
		}
		List<HashMap<String,String>> list = myutil.getYzstList(myform);
		
		for(int i=0;i<list.size();i++){
			HashMap<String,String> map = (HashMap<String,String>)list.get(i);
			oldValue = map.get("dm")+",";
		}
		request.setAttribute("yzList", myutil.getYzList());
		request.setAttribute("stList", myutil.getStList(myform));
		request.setAttribute("yzstList", list);
		request.setAttribute("stlxList", myutil.tkwh_getStlxList());
		request.setAttribute("oldValue", Base.isNull(oldValue)?"":oldValue);
		return mapping.findForward("yzfst");
	}

}