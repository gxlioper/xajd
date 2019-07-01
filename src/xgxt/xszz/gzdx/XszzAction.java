package xgxt.xszz.gzdx;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.GetTime;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 广州大学Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-24</p>
 */
public class XszzAction extends BaseAction {
//	页面参数值获取
	public List<HashMap<String, String>> getParams(HttpServletRequest request) {
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while (paramsEnum.hasMoreElements()) {
			String param = (String) paramsEnum.nextElement();
			if (param.startsWith("_")) {
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if (valueListSize > -1) {
			for (int i = 0; i < valueListSize; i++) {
				valueList.add(new HashMap<String, String>());
			}
			Set<String> keySet = map_values.keySet();
			for (String key : keySet) {
				String[] values = map_values.get(key);
				for (int i = 0; i < valueList.size(); i++) {
					HashMap<String, String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
	
	/**
	 * 贷款学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdkxxDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXsdkxx(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (userType.equalsIgnoreCase("stu")
				|| userType.equalsIgnoreCase("student")) {
			actionForm.setXh(userName);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXsdkxxTit();
		List<String[]> resList = service.getXsdkxxRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setHtbh(DealString.toGBK(actionForm.getHtbh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXsdkxxResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "xszz_gzdx_xsdkxxb");
		request.setAttribute("tableName", "view_xszz_gzdx_xsdkxxb");
		return mapping.findForward("xsdkxxDate");
	}
	
	/**
	 * 贷款学生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdkxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXsdkxxExp(queryModel, response, request);
		return mapping.findForward("xsdkxxExp");
	}
	
	/**
	 * 贷款学生信息查看页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdkxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		request.setAttribute("rs", service.getXsdkzxx(pkVal));
		return mapping.findForward("xsdkxxOne");
	}
	
	/**
	 * 贷款学生信息编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdkxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xn = Base.chgNull(request.getParameter("xn"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getXsdkxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xsdkxxEdit");
	}
	
	/**
	 * 保存贷款学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdkxxEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		XsdkxxbModel model = new XsdkxxbModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXsdkxx(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getXsdkxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", "modi");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", xh);
		return mapping.findForward("xsdkxxEditSave");
	}
	
	/**
	 * 就业学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjyxxDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXsjyxx(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (userType.equalsIgnoreCase("stu")
				|| userType.equalsIgnoreCase("student")) {
			actionForm.setXh(userName);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXsjyxxTit();
		List<String[]> resList = service.getXsjyxxRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setHtbh(DealString.toGBK(actionForm.getHtbh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXsjyxxResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "xszz_gzdx_dkxsjyxxb");
		request.setAttribute("tableName", "view_xszz_gzdx_dkxsjyxxb");
		return mapping.findForward("xsjyxxDate");
	}
	
	/**
	 * 就业学生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjyxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXsjyxxExp(queryModel, response, request);
		return mapping.findForward("xsjyxxExp");
	}
	
	/**
	 * 就业学生信息查看页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjyxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		request.setAttribute("rs", service.getXsjyxx(pkVal));
		return mapping.findForward("xsjyxxOne");
	}
	
	/**
	 * 就业学生信息编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjyxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getXsjyxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xsjyxxEdit");
	}
	
	/**
	 * 保存就业学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjyxxEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		XsjyxxbModel model = new XsjyxxbModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXsjyxx(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXsjyxx(xh);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		request.setAttribute("type", "modi");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", xh);
		return mapping.findForward("xsjyxxEditSave");
	}

	/**
	 * 还款学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xshkxxDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXshkxx(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (userType.equalsIgnoreCase("stu")
				|| userType.equalsIgnoreCase("student")) {
			actionForm.setXh(userName);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXshkxxTit();
		List<String[]> resList = service.getXshkxxRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setHtbh(DealString.toGBK(actionForm.getHtbh()));
		actionForm.setSfqbhqdk(DealString.toGBK(actionForm.getSfqbhqdk()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXshkxxResNum(queryModel, request)));
		request.setAttribute("pk", "htbh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "xszz_gzdx_hkxxb");
		request.setAttribute("tableName", "view_xszz_gzdx_hkxxb");
		return mapping.findForward("xshkxxDate");
	}
	
	/**
	 * 还款学生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xshkxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXshkxxExp(queryModel, response, request);
		return mapping.findForward("xshkxxExp");
	}
	
	/**
	 * 还款学生信息查看页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xshkxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		request.setAttribute("rs", service.getXshkxx(pkVal));
		return mapping.findForward("xshkxxOne");
	}
	
	/**
	 * 还款学生信息编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xshkxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String htbh = Base.chgNull(request.getParameter("htbh"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? htbh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getXshkxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getXsdkxxByHtbh(pkVal);
				if (stuMap.size() == 0) {
					request.setAttribute("isNull", "isNull");
				}
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xshkxxEdit");
	}
	
	/**
	 * 保存还款学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xshkxxEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		XshkxxbModel model = new XshkxxbModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXshkxx(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String htbh = model.getHtbh();
		String pkVal =htbh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getXshkxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getXsdkxxByHtbh(pkVal);
				if (stuMap.size() == 0) {
					request.setAttribute("isNull", "isNull");
				}
			}
		}

		request.setAttribute("type", "modi");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", htbh);
		return mapping.findForward("xshkxxEditSave");
	}

	/**
	 * 临时补贴信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbtDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delLsbt(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (userType.equalsIgnoreCase("stu")
				|| userType.equalsIgnoreCase("student")) {
			actionForm.setXh(userName);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getLsbtTit();
		List<String[]> resList = service.getLsbtRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setBtsj(DealString.toGBK(actionForm.getBtsj()));

		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("btsj", actionForm.getBtsj());
		request.setAttribute("rs1", rs1);
		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getLsbtResNum(queryModel, request)));
		request.setAttribute("pk", "btsj||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "xszz_gzdx_lsbt");
		request.setAttribute("tableName", "view_xszz_gzdx_lsbt");
		return mapping.findForward("lsbtDate");
	}
	
	/**
	 * 临时补贴信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbtExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getLsbtExp(queryModel, response, request);
		return mapping.findForward("lsbtExp");
	}
	
	/**
	 * 临时补贴信息编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbtEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGzdxService service = new XszzGzdxService();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xn = Base.chgNull(request.getParameter("xn"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getLsbt(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("btsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbtEdit");
	}
	
	/**
	 * 保存临时补贴信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbtEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzGzdxService service = new XszzGzdxService();
		XszzGzdxActionForm actionForm = (XszzGzdxActionForm) form;
		LsbtModel model = new LsbtModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveLsbt(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String btsj = model.getBtsj();
		String pkVal = btsj + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getLsbt(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("btsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", "modi");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", xh);
		return mapping.findForward("lsbtEditSave");
	}
}
