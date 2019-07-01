package xgxt.xszz.ycws;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * Title: 学生工作管理系统
 * Description: 盐城卫生学生资助Action
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-06-09
 */
public class XszzAction extends BaseAction {

//	private boolean isNull(String str) {
//		return ((str == null) || str.equalsIgnoreCase("") || str
//				.equalsIgnoreCase("all"));
//	}
	
	/**
	 * @describe 得到时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select knrdzsg,knrddr,knrddc,zzzsg,zzdr,zzdc from ycws_sjb where rownum=1";
		String[] outString = new String[] { "knrdzsg", "knrddr", "knrddc", "zzzsg", "zzdr", "zzdc" };

		if ("save".equalsIgnoreCase(act)) {
			String knrdzsg = Base.chgNull(request.getParameter("knrdzsg"), "",
					1);
			String knrddr = Base.chgNull(request.getParameter("knrddr"), "", 1);
			String knrddc = Base.chgNull(request.getParameter("knrddc"), "", 1);
			String zzzsg = Base.chgNull(request.getParameter("zzzsg"), "", 1);
			String zzdr = Base.chgNull(request.getParameter("zzdr"), "", 1);
			String zzdc = Base.chgNull(request.getParameter("zzdc"), "", 1);
			boolean b = false;
			
			b = StandardOperation.update("ycws_sjb",
					"update ycws_sjb set knrdzsg='" + knrdzsg + "',knrddr='"
							+ knrddr + "',knrddc='" + knrddc + "',zzzsg='"
							+ zzzsg + "',zzdr='" + zzdr + "',zzdc='"
							+ zzdc + "'", request);
			if(b){
				request.setAttribute("ok", "ok");
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] {}, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
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
		
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		return mapping.findForward("zzsjEdit");
	}

	/**
	 * 困难认定页面
	 * knsrd_data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd_data(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		XszzYcwsService service = new XszzYcwsService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			String[] pk = xszzYcwsActionForm.getPk();
			service.delKnrdxx(pk, request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzYcwsActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzYcwsActionForm);
		List<HashMap<String, String>> topList = service.getKnrdTit();
		List<String[]> resList = service.getKnrdRes(queryModel,request,xszzYcwsActionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzYcwsActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		xszzYcwsActionForm.setKnrd(Base.chgNull(queryModel.getKnrd(), "", 1));
		
		xszzYcwsActionForm.getPages().setMaxRecord(Integer.parseInt(service.getKnrdResNum(queryModel,request)));
		request.setAttribute("kg", service.getKg());
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzYcwsActionForm);
		request.setAttribute("realTable", "ycws_knrdxx");
		request.setAttribute("tableName", "view_ycws_knrdxx");
		return mapping.findForward("knsrd_data");
	}
	
	/**
	 * 困难认定信息导出
	 * knsrdExp ----- 困难认定信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		XszzYcwsService service = new XszzYcwsService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzYcwsActionForm);
		service.getKnsrdExp(queryModel, response,request);
		return mapping.findForward("knsrdExp");
	}
	
	/**
	 * 困难认定详细页面
	 * knsrdOne ----- 困难认定详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzYcwsService service = new XszzYcwsService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrdxx(pkVal);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdOne");
	}
	
	/**
	 * 困难认定编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsService service = new XszzYcwsService();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(pkVal);// 得到学生信息
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdEdit");
	}
	
	/**
	 * 困难认定编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		KnrdModel model = new KnrdModel();
		BeanUtils.copyProperties(model, xszzYcwsActionForm);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		XszzYcwsService service = new XszzYcwsService();
		boolean bJg = service.saveKnrd(model, act, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdEditSave");
	}
	
	/**
	 * 困难资助项目页面
	 * knzzxm_data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knzzxm_data(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		XszzYcwsService service = new XszzYcwsService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnzzxmxx(xszzYcwsActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzYcwsActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzYcwsActionForm);
		List<HashMap<String, String>> topList = service.getKnzzxmTit();
		List<String[]> resList = service.getKnzzxmRes(queryModel,request,xszzYcwsActionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzYcwsActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		xszzYcwsActionForm.setXmmc(Base.chgNull(queryModel.getXmmc(), "", 1));
		xszzYcwsActionForm.setKndj(Base.chgNull(queryModel.getKndj(), "", 1));
		
		xszzYcwsActionForm.getPages().setMaxRecord(Integer.parseInt(service.getKnzzxmResNum(queryModel,request)));
		request.setAttribute("kg", service.getKg());
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzYcwsActionForm);
		request.setAttribute("realTable", "ycws_knzzxm");
		request.setAttribute("tableName", "view_ycws_knzzxm");
		return mapping.findForward("knzzxm_data");
	}
	
	/**
	 * 困难资助项目信息导出
	 * knzzxmExp ----- 困难资助项目信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knzzxmExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		XszzYcwsService service = new XszzYcwsService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzYcwsActionForm);
		service.getKnzzxmExp(queryModel, response,request);
		return mapping.findForward("knzzxmExp");
	}
	
	/**
	 * 困难资助项目详细页面
	 * knzzxmOne ----- 困难资助项目详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knzzxmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzYcwsService service = new XszzYcwsService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnzzxmxx(pkVal);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knzzxmOne");
	}
	
	/**
	 * 困难资助项目编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knzzxmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzYcwsService service = new XszzYcwsService();
		String id = Base.chgNull(request.getParameter("id"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? id : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)||!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnzzxmxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knzzxmEdit");
	}
	
	/**
	 * 困难资助项目编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knzzxmEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzYcwsActionForm xszzYcwsActionForm = (XszzYcwsActionForm)form;
		KnzzxmModel model = new KnzzxmModel();
		BeanUtils.copyProperties(model, xszzYcwsActionForm);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		XszzYcwsService service = new XszzYcwsService();
		boolean bJg = service.saveKnzzxm(model, act, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String id = model.getId();
		String pkVal = id;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnzzxmxx(pkVal);// 得到详细信息
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knzzxmEditSave");
	}
}
