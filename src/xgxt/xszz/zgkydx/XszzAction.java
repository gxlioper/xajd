package xgxt.xszz.zgkydx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * Title: 学生工作管理系统
 * Description: 中国矿业大学学生资助Action
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2008-08-13
 */
public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
	
	/**
	 * 困难生申请页面 knssq ----- 困难生申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzZgkdService service = new XszzZgkdService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		request.setAttribute("sfksq", service.getKnsSqQx(sUserType, userDep,
				xh));
		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}

	/**
	 * 保存困难生申请信息 knssqSave ---- 保存困难生申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		XszzZgkdService service = new XszzZgkdService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// 保存信息
		if (!"is".equals(request.getAttribute("isPASS"))) {
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * 困难生申请表页面 knssqb ----- 困难生申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		stuMap = service.getKnsSqb(knsModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * 困难生审核页面 knssh ----- 困难生审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("ad".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "A档", request);
			queryModel.setGo("go");
		}
		if ("bd".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "B档", request);
			queryModel.setGo("go");
		}
		if ("cd".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "C档", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不困难", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzZgkydxActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel, request, form);
		String rnum = service.getKnsResNum(queryModel, request);
		xszzZgkydxActionForm.getPages().setMaxRecord(Integer.parseInt(rnum));
		String xh = DealString.toGBK(xszzZgkydxActionForm.getXh());
		xszzZgkydxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		xszzZgkydxActionForm.setXbshjg(DealString.toGBK(xszzZgkydxActionForm.getXbshjg()));
		xszzZgkydxActionForm.setXxshjg(DealString.toGBK(xszzZgkydxActionForm.getXxshjg()));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgkydx_knsxx");
		request.setAttribute("tableName", "view_zgkydx_knsxx");
		return mapping.findForward("knssh");
	}

	/**
	 * 困难生信息导出 knsExp ----- 困难生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		service.getKnsExp(queryModel, response, request);
		return mapping.findForward("knsExp");
	}

	/**
	 * 困难生审核详细页面 knsshOne ----- 困难生审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzZgkydxActionForm.setBjpyjg(stuMap.get("bjpyjg"));
		xszzZgkydxActionForm.setXbshjg(stuMap.get("xbshjg"));
		xszzZgkydxActionForm.setXxshjg(stuMap.get("xxshjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(21));
		request.setAttribute("dcfyqkList", dao.getChkList(22));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * 保存困难生审核信息 knsshSave ---- 保存困难生审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		XszzZgkdService service = new XszzZgkdService();
		boolean bJg = service.saveKnsShxx(knsModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzZgkydxActionForm.setBjpyjg(stuMap.get("bjpyjg"));
		xszzZgkydxActionForm.setXbshjg(stuMap.get("xbshjg"));
		xszzZgkydxActionForm.setXxshjg(stuMap.get("xxshjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(21));
		request.setAttribute("dcfyqkList", dao.getChkList(22));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}

	/**
	 * 特殊人群设置困难生查询页面
	 * knscx ----- 特殊人群设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqszTit();
		List<String[]> resList = service.getTsrqKnsRes(queryModel, request);
		String xh = DealString.toGBK(xszzZgkydxActionForm.getXh());
		xszzZgkydxActionForm.setXh(xh);
		xszzZgkydxActionForm.setXxshjg(queryModel.getXxshjg());
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("knpdList", dao.getChkList(23));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "zgkydx_knsxx");
		request.setAttribute("tableName", "view_zgkydx_knsxx");
		return mapping.findForward("knscx");
	}

	/**
	 * 特殊人群设置页面
	 * tsrqSet
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String pkVal = Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1);
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("tsrqSet");
	}

	/**
	 * 特殊人群设置保存
	 * tsrqSave
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String tsrqdm = xszzZgkydxActionForm.getTsrqdm();
		String pkVal = Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1);
		service.tsrqSave(Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1), tsrqdm, request);
		request.setAttribute("ok", "ok");
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("tsrqSave");
	}

	/**
	 * 特殊人群查询页面
	 * tsrqcx ----- 特殊人群查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delTsrqxx(Base.chgNull(request.getParameter("pkVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqcxTit();
		List<String[]> resList = service.getTsrqRes(queryModel, request);
		xszzZgkydxActionForm.setKnpd(queryModel.getKnpd());
		xszzZgkydxActionForm.setTsrqdm(queryModel.getTsrqdm());
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("knpdList", dao.getChkList(23));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "xszz_zgkd_tsrq");
		request.setAttribute("tableName", "view_xszz_zgkd_tsrq");
		return mapping.findForward("tsrqcx");
	}

	/**
	 * 特殊人群信息导出
	 * tsrqExp ----- 特殊人群信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		service.getTsrqExp(queryModel, response, request);
		return mapping.findForward("tsrqExp");
	}

	/**
	 * 特殊人群与资助项目维护页面
	 * tsrqxmcx ----- 特殊人群与资助项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delTsrqxmcxx(Base.chgNull(request.getParameter("pkVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqxmTit();
		List<String[]> resList = service.getTsrqxmRes(queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("pk", "tsrqdm||zzxmdm");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "xszz_zgktdx_tsrqxmwh");
		request.setAttribute("tableName", "view_xszz_zgktdx_tsrqxmwh");
		return mapping.findForward("tsrqxmcx");
	}

	/**
	 * 特殊人群与资助项目设置页面
	 * tsrqxmSet
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("tsrqxmSet");
	}

	/**
	 * 特殊人群与资助项目设置保存
	 * tsrqxmSave
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String tsrqdm = xszzZgkydxActionForm.getTsrqdm();
		String zzxmdm = xszzZgkydxActionForm.getZzxmdm();
		boolean b = service.tsrqxmSave(zzxmdm, tsrqdm, request);
		if (b) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		xszzZgkydxActionForm.setTsrqdm(tsrqdm);
		xszzZgkydxActionForm.setZzxmdm(zzxmdm);
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("tsrqxmSave");
	}
	
	/**
	 * @describe 资助审核列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		tableName = "view_xszz_common_new_zzbbxssqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		String sqlt = "";
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 资助项目审核";
		
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "当前所在位置：学生资助 - 申请 - 申请结果查询 - 资助项目";
			colList = new String[] { "bgcolor", "主键", "pk2", "r", "nd", "xmmc", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "sfkns" };
			if (userType.equalsIgnoreCase("xx")) {
				StringBuffer sb = new StringBuffer("select * from (select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from(select ");
				sb.append(pk);
				sb.append(" 主键,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" order by xxsh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString(); 
				request.setAttribute("isXX", "is");
			} else {
				StringBuffer sb = new StringBuffer("select * from (select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from(select ");
				sb.append(pk);
				sb.append(" 主键,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" order by xysh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString(); 
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				StringBuffer sb = new StringBuffer("select * from (select (case when (select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='通过' and x.xh=a.xh)+(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh)<='10000' then case when (select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh)<='8000' then '#000000' else '#FF0000' end else '#FF0000' end) bgcolor, a.* from(select ");
				sb.append(pk);
				sb.append(" 主键,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" and xysh='通过' order by xxsh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xysh='通过'"; 
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				StringBuffer sb = new StringBuffer("select * from (select (case when (select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='通过' and x.xh=a.xh)+(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh)<='10000' then case when (select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh)<='8000' then '#000000' else '#FF0000' end else '#FF0000' end) bgcolor, a.* from(select ");
				sb.append(pk);
				sb.append(" 主键,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" and xydm='");
				sb.append(userDep);
				sb.append("' order by xysh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xydm='"+userDep+"'"; 
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
		map.put("xmdm", xmdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		xszzZgkydxActionForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sqlt, new String[]{}, "num")));
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
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
		return mapping.findForward("xszzsh");
	}
	
	/**
	 * @describe 资助过盛
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzgs(ActionMapping mapping, ActionForm form,
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		tableName = "view_xszz_common_new_zzbbxssqb";
		
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		querry.append(querry1.toString());
		tips = "当前所在位置：学生资助 - 审核 - 资助过盛汇总";

		colList = new String[] { "主键", "pk2", "nd", "xh", "xm", "xb",
				"xymc", "zymc", "bjmc", "nj", "sfkns", "xmmc", "本次批准金额",
				"奖学金总金额", "资助总金额" };
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		if (userType.equalsIgnoreCase("xx")) {
			StringBuffer sb = new StringBuffer("select a.* from (select ");
			sb.append(pk);
			sb.append(" 主键,a.xmdm pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje 本次批准金额,");
			sb.append("(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='通过' and x.xh=a.xh) 奖学金总金额,");
			sb.append("(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh) 资助总金额 from ");
			sb.append(tableName);
			sb.append(" a");
			sb.append(querry.toString());
			sb.append(" order by xh) a where (NVL(奖学金总金额,0)+nvl(资助总金额,0))>'10000' or nvl(资助总金额,0)>'8000'");
			sql = sb.toString();
			request.setAttribute("isXX", "is");
		} else {
			StringBuffer sb = new StringBuffer("select a.* from (select ");
			sb.append(pk);
			sb.append(" 主键,a.xmdm pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje 本次批准金额,");
			sb.append("(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='通过' and x.xh=a.xh) 奖学金总金额,");
			sb.append("(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh) 资助总金额 from ");
			sb.append(tableName);
			sb.append(" a");
			sb.append(querry.toString());
			sb.append(" and xydm='");
			sb.append(userDep);
			sb.append("' order by xh) a where (NVL(奖学金总金额,0)+nvl(资助总金额,0))>'10000' or nvl(资助总金额,0)>'8000'");
			sql = sb.toString();
			request.setAttribute("isXX", "no");
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
		map.put("xmdm", xmdm);
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
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
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
		return mapping.findForward("xszzgs");
	}
	
	/**
	 * @describe 资助过盛数据导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzgsExp(ActionMapping mapping, ActionForm form,
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
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		sql = "select a.* from (select a.nd,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje,"
			+ "(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='通过' and x.xh=a.xh) jlje,"
			+ "(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='通过' and x.xh=a.xh) zzzje"
			+ " from view_xszz_common_new_zzbbxssqb a"
			+ querry.toString()
			+ " order by xh) a where (NVL(jlje,0)+nvl(zzzje,0))>'10000' or nvl(zzzje,0)>'8000'";
		
		String[] colListT = dao.getColumnName("select nd,xh,xm,sfzh,xymc,zymc,bjmc,nj,sfkns,xmmc from view_xszz_common_new_zzbbxssqb where 1=2");// 获得列名数组
		String[] colListCNT = dao.getColumnNameCN(colListT, "view_xszz_common_new_zzbbxssqb");
		String[] colList = new String[colListT.length+3];
		String[] colListCN = new String[colListCNT.length+3];
		int i = 0;
		for (; i < colListT.length; i++){
			colList[i] = colListT[i];
			colListCN[i] = colListCNT[i];
		}
		colList[i] = "xxpzje";
		colListCN[i] = "本次资助金额";
		i++;
		colList[i] = "jlje";
		colListCN[i] = "奖学金总金额";
		i++;
		colList[i] = "zzzje";
		colListCN[i] = "资助总金额";
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzgsExp");
	}
	
	// 学生 还款管理  hupeng
	public ActionForward hkgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "";
		String pk ="xh";
		XszzZgkdService dao2 = new XszzZgkdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String act = DealString.toGBK(request.getParameter("act"));
		String doType = DealString.toGBK(request.getParameter("doType"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String hth = DealString.toGBK(request.getParameter("hth"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));

		map.put("xh", xh);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("hth", hth);
		map.put("nj", nj);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);

		// 单个删除
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = dao2.hkxxDel(pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		//批量删除
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			String[] sqlT = new String[pkstringI.length];
			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				sqlT[i] = "delete xszz_zgkd_xshkxxb where '"+pk+"'='"+whichrid+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("delall", "ok");
		}

		//清空合同信息表
		if ("delallhtinfo".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = dao2.hkxxDelall("xszz_zgkd_htxxb"); 
			if (judge) {
				request.setAttribute("delallhtinfo", "ok");
			} else {
				request.setAttribute("delallhtinfo", "no");
			}
		}
		//清空还款信息表
		if ("delallhkinfo".equalsIgnoreCase(doType)) {

			boolean judge = false;

			judge = dao2.hkxxDelall("xszz_zgkd_xshkxxb");
            
			if (judge) {
				request.setAttribute("delallhkinfo", "ok");
			} else {
				request.setAttribute("delallhkinfo", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
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
		if (!("".equals(hth))) {
			query.append(" and hth like '%");
			query.append(hth);
			query.append("%'");
		}
		
		String query1 = query.toString();
		XszzZgkdDAO dao1 = new XszzZgkdDAO();
		if ("go".equalsIgnoreCase(act)) {
			rs = dao1.getHkxsxx(query, form, request);
		}
		sql = "select count(*) from view_xszz_zgkd_xshkxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		String[] colList = new String[] { "xh", "xm", "xb", "hth", "xymc",
				"zymc", "bjmc" };
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_xszz_zgkd_xshkxxb");
		List topTr = dao.arrayToList(colList, colListCN);
		
		
		

		String querry = query.toString();
		request.setAttribute("querry", querry);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", "xszz_zgkd_htxxb");
		request.setAttribute("realTable2", "xszz_zgkd_xshkxxb");
		request.setAttribute("topTr", topTr);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		return mapping.findForward("hkgl");
	}

	//	 学生 还款管理--详细信息查看 hupeng
	public ActionForward hkglviewmore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
	
		XszzZgkdService dao = new XszzZgkdService();
		map=dao.getHkxxmoreinfo(pkValue, request);
		request.setAttribute("rs", map);	
		return mapping.findForward("hkglviewmore");

	}
	
	 //学生 还款管理--修改
	public ActionForward hkglupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");
		XszzZgkdService dao = new XszzZgkdService();

		String jtdz = DealString.toGBK(request.getParameter("jtdz")); // 家庭地址
		String jtyzbm = DealString.toGBK(request.getParameter("jtyzbm")); // 家庭邮政编码
		String jtlxdh = DealString.toGBK(request.getParameter("jtlxdh")); // 家庭联系电话
		String gzdw = DealString.toGBK(request.getParameter("gzdw")); // 工作单位
		String gzdwdz = DealString.toGBK(request.getParameter("gzdwdz")); // 工作单位地址
		String gzdwlxdh = DealString.toGBK(request.getParameter("gzdwlxdh")); // 工作单位联系电话
		String sjh = DealString.toGBK(request.getParameter("sjh")); // 手机号
		String zjdh = DealString.toGBK(request.getParameter("zjdh")); // 座机电话
		String email = DealString.toGBK(request.getParameter("email")); //电子邮箱
		String qq = DealString.toGBK(request.getParameter("qq")); // QQ号码

		if ("update".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String[] columns = { "jtdz", "jtyzbm", "jtlxdh", "gzdw", "gzdwdz",
					"gzdwlxdh", "sjh", "zjdh", "email", "qq" };
			String[] values = { jtdz, jtyzbm, jtlxdh, gzdw, gzdwdz, gzdwlxdh,
					sjh, zjdh, email, qq };
			judge = dao.hkxxUpdate(columns, values, "xh", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		map = dao.getHkxxmoreinfo(pkValue, request);
		request.setAttribute("rs", map);
		return mapping.findForward("hkglupdate");
	}

	//还款信息数据导出
	public ActionForward hkglexpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		String sql = "";

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (tableName == null) {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}
		return mapping.findForward("expdata");

	}
	
	/**
	 * @describe 资助金额汇总列表
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);

		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and a.xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and a.nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and a.nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and a.xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and a.zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and a.bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and a.xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and a.xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and a.xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		String je = "0";
		tips = "当前所在位置：学生资助 - 审核 - 资助汇总";
		String sqlT = "";
		if (userType.equalsIgnoreCase("xx")) {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc",
					"sqsj", "xysh", "xxsh", "xxpzje", "pkdj" };
			colListCN = new String[] { "年度", "资助名称", "学号", "姓名", "班级名称",
					"申请时间", Base.YXPZXY_KEY+"审核", "学校审核", "学校批准金额", "困难等级" };
		} else {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc",
					"sqsj", "xysh", "xxsh", "xypzje", "pkdj" };
			colListCN = new String[] { "年度", "资助名称", "学号", "姓名", "班级名称",
					"申请时间", Base.YXPZXY_KEY+"审核", "学校审核", Base.YXPZXY_KEY+"批准金额", "困难等级" };
		}
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select nvl(sum(ROUND(xxpzje)),0) num from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString();
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc,' ' sqsj,'合计' xysh, ' ' xxsh, '"
					+ je + "' xxpzje,' ' pkdj from dual";
			sql = "select a.nd,a.xmmc,a.xh,a.xm,a.bjmc,a.sqsj,a.xysh,a.xxsh,a.xxpzje,NVL((select (case b.xxshjg when '未审核' then '' when '不困难' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString() + " order by a.nd desc";
		} else {
			sql = "select nvl(sum(ROUND(xypzje)),0) num from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString() + " and xydm='" + userDep;
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc,' ' sqsj,'合计' xysh, ' ' xxsh, '"
					+ je + "' xypzje,' ' pkdj from dual";
			sql = "select a.nd,a.xmmc,a.xh,a.xm,a.bjmc,a.sqsj,a.xysh,a.xxsh,a.xypzje,NVL((select (case b.xxshjg when '未审核' then '' when '不困难' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString()
					+ " and a.xydm='"
					+ userDep
					+ "' order by a.nd desc";
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rs.addAll(dao.rsToVator(sqlT, new String[] {}, colList));
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("ndList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("xyshList", xszzDao.xyshList());
		request.setAttribute("xxshList", xszzDao.xxshList());
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		return mapping.findForward("xszzJehz");
	}
	
	/**
	 * @describe 资助金额汇总导出
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehzExp(ActionMapping mapping, ActionForm form,
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		
		sql = "select a.*,NVL((select (case b.xxshjg when '未审核' then '' when '不困难' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from (select * from view_xszz_common_new_zzbbxssqb " + querry.toString() + ") a";
		
		String[] colListT = dao.getColumnName("select * from view_xszz_common_new_zzbbxssqb where 1=2");// 获得列名数组
		String[] colListCNT = dao.getColumnNameCN(colListT, "view_xszz_common_new_zzbbxssqb");
		String[] colList = new String[colListT.length+1];
		String[] colListCN = new String[colListCNT.length+1];
		int i = 0;
		for (; i < colListT.length; i++){
			colList[i] = colListT[i];
			colListCN[i] = colListCNT[i];
		}
		colList[i] = "pkdj";
		colListCN[i] = "贫困等级";
		
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		sql = "select nvl(sum(ROUND(xypzje)),0) xyzje,nvl(sum(ROUND(xxpzje)),0) xxzje from view_xszz_common_new_zzbbxssqb " + querry.toString();
		String[] je = dao.getOneRs(sql, new String[]{}, new String[]{"xyzje","xxzje"});
		
		StringBuffer sqlBf = new StringBuffer("select '合计' nd");
		for(i = 1; i< colList.length; i++){
			if("xypzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[0]+"' xypzje");
			} else if("xxpzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[1]+"' xxpzje");
			} else {
				sqlBf.append(",' ' "+colList[i].toLowerCase());
			}
		}
		sqlBf.append(" from dual ");
		rs.addAll(dao.rsToVator(sqlBf.toString(), new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzJehzExp");
	}

}
