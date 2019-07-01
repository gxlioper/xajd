package xgxt.xszz.shgc;

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
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * Title: ѧ����������ϵͳ
 * Description: �Ϻ�����ѧ������Action
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-06-10
 */
public class XszzAction extends BaseAction {

	/**
	 * ��������ʷ��ҳ��
	 * knslsk_data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsk_data(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzShgcActionForm xszzShgcActionForm = (XszzShgcActionForm)form;
		XszzShgcService service = new XszzShgcService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnslskxx(xszzShgcActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzShgcActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzShgcActionForm);
		List<HashMap<String, String>> topList = service.getKnslskTit();
		List<String[]> resList = service.getKnslskRes(queryModel,request,xszzShgcActionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzShgcActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzShgcActionForm.setKnrdjg(Base.chgNull(queryModel.getKnrdjg(), "", 1));
		
		xszzShgcActionForm.getPages().setMaxRecord(Integer.parseInt(service.getKnslskResNum(queryModel,request)));
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzShgcActionForm);
		request.setAttribute("realTable", "SHGC_KNSXX_LSK");
		request.setAttribute("tableName", "SHGC_KNSXX_LSK");
		return mapping.findForward("knslsk_data");
	}
	
	/**
	 * ��������ʷ����Ϣ����
	 * knslskExp ----- ��������ʷ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslskExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzShgcActionForm xszzShgcActionForm = (XszzShgcActionForm)form;
		XszzShgcService service = new XszzShgcService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzShgcActionForm);
		service.getKnslskExp(queryModel, response,request);
		return mapping.findForward("knslskExp");
	}
	
	/**
	 * ��������ʷ����ϸҳ��
	 * knslskOne ----- ��������ʷ����ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslskOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzShgcService service = new XszzShgcService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnslskxx(pkVal);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knslskOne");
	}
	
	/**
	 * ��������ʷ��༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslskEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzShgcService service = new XszzShgcService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd+xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnslskxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("sydList", dao.getArrayList("SELECT qxmc syd FROM dmk_qx  WHERE qxdm LIKE '__0000' ORDER BY qxdm", new String[]{}, new String[]{"syd"}));
		return mapping.findForward("knslskEdit");
	}
	
	/**
	 * ��������ʷ��༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslskEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzShgcActionForm xszzYcwsActionForm = (XszzShgcActionForm)form;
		KnslskModel model = new KnslskModel();
		BeanUtils.copyProperties(model, xszzYcwsActionForm);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		XszzShgcService service = new XszzShgcService();
		boolean bJg = service.saveKnslsk(model, act, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd+xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnslskxx(pkVal);// �õ���ϸ��Ϣ
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("sydList", dao.getArrayList("SELECT qxmc syd FROM dmk_qx  WHERE qxdm LIKE '__0000' ORDER BY qxdm", new String[]{}, new String[]{"syd"}));
		return mapping.findForward("knslskEditSave");
	}
}
