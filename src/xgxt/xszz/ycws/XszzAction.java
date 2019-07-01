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
 * Title: ѧ����������ϵͳ
 * Description: �γ�����ѧ������Action
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-06-09
 */
public class XszzAction extends BaseAction {

//	private boolean isNull(String str) {
//		return ((str == null) || str.equalsIgnoreCase("") || str
//				.equalsIgnoreCase("all"));
//	}
	
	/**
	 * @describe �õ�ʱ��������ϸ��Ϣ�ͱ�����Ϣ
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
	 * �����϶�ҳ��
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
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzYcwsActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
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
	 * �����϶���Ϣ����
	 * knsrdExp ----- �����϶���Ϣ����
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
	 * �����϶���ϸҳ��
	 * knsrdOne ----- �����϶���ϸҳ��
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
	 * �����϶��༭ҳ��
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
			stuMap = service.getKnsrdxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(pkVal);// �õ�ѧ����Ϣ
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdEdit");
	}
	
	/**
	 * �����϶��༭ҳ��
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
		boolean bJg = service.saveKnrd(model, act, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdEditSave");
	}
	
	/**
	 * ����������Ŀҳ��
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
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzYcwsActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
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
	 * ����������Ŀ��Ϣ����
	 * knzzxmExp ----- ����������Ŀ��Ϣ����
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
	 * ����������Ŀ��ϸҳ��
	 * knzzxmOne ----- ����������Ŀ��ϸҳ��
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
	 * ����������Ŀ�༭ҳ��
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
			stuMap = service.getKnzzxmxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knzzxmEdit");
	}
	
	/**
	 * ����������Ŀ�༭ҳ��
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
		boolean bJg = service.saveKnzzxm(model, act, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String id = model.getId();
		String pkVal = id;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnzzxmxx(pkVal);// �õ���ϸ��Ϣ
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knzzxmEditSave");
	}
}
