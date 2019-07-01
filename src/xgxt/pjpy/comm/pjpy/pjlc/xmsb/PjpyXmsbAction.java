package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;
import xgxt.utils.Pages;
import xgxt.xsxx.comm.ajax.XsxxAjaxModel;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ�ϱ�-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyXmsbAction extends BasicAction {

	/**
	 * ��������_��������_��Ŀ�ϱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmsbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("xmsbManage");
	}	
	
	/**
	 * �����Ŀ�ϱ�ѧ���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXmsbXsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= ��ʼ����������ֵ ============		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��Ŀ����
		String xmdm = otherValue[0];
		myForm.setXmdm(xmdm);
		// �༶����
		String bjdm = otherValue[1];
		myForm.setBjdm(bjdm);
		// ����
		String arrange = otherValue[2];
		myForm.setArrange(arrange);
		// ����ʽ
		String fashion = otherValue[3];
		myForm.setFashion(fashion);
		// ��ѯ����
		String search_condition = otherValue[4];
		myForm.setSearch_condition(search_condition);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("",request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		String spHtml = service.getLssbHtml(rsModel, myForm, user);
		// ==================��ʾ���� end========================
		
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");
		rsModel.setLeft("no");
		rsModel.setNoSpace("no");
		rsModel.setArrange("yes");
		
		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
	
	/**
	 * �ϱ���Χȷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sbfwChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		init.getPjxmsbRForm(rForm, myForm, request);
		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String showMessage = "";// ��ʾ��Ϣ
		// =================== end ===================		

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		List<HashMap<String, String>> xmdmList = service.getXmOption(myForm);
		request.setAttribute("xmdmList", xmdmList);
		// ��ʼ���б�
		CommForm commForm = new CommForm();
		service.setList(commForm, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("sbfwChoose");
	}
	
	/**
	 * ������Ŀ�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		User user = getUser(request);// �û�����
		
		//��Ŀ����
		String xmmc = request.getParameter("xmdm");
		
		if (!Base.isNull(xmmc)){
			xmmc = URLDecoder.decode(xmmc, "UTF-8");
		}
			
		myForm.setXmmc(xmmc);
		
		List<HashMap<String, String>> map = service.getXmOption(myForm);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * ������Ŀ�ϱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		User user = getUser(request);// �û�����

		// ����ѧ��
		String[] pjxh = request.getParameter("xh").split("!!@@!!");
		myForm.setPjxh(pjxh);
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ============= ������Ŀ�ϱ� ============
		// ��ʾ��Ϣ
		String message = service.judgeSbzg(myForm);

		if (Base.isNull(message)) {
			boolean flag = service.saveXmsb(myForm, user);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
		}
		// ============= ������Ŀ�ϱ� end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʼ��ѧ���ɼ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultXscj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= ��ʼ����������ֵ ============
		// ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ==================����ǰ̨ҳ��========================		
		service.createXscjHTML(myForm, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
}
