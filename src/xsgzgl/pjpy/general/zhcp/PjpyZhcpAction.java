package xsgzgl.pjpy.general.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_Action��
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

public class PjpyZhcpAction extends BasicAction {
	
	// ---------------------�۲���Ϣά�� begin--------------------------
	/**
	 * ��ѯ�ۺϲ������۲���Ϣ��ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZhcpZcxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcp(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// ��Ŀ����
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		
		// ��Ŀ����
		String xmmc = otherValue[2];
		
		// ��Դ��
		String lyb =otherValue[3];
		model.setLyb(lyb);
		
		//��Ŀ����
		String xmjb =otherValue[4];
		model.setXmjb(xmjb);
		
		if(!Base.isNull(xmmc)){
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		List<HashMap<String, String>> topTr = service.getZhcpZcxxTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getZhcpZcxxList(myForm, model,
				user);
		// ���������
		String spHtml = service.createZhcpZcxxHTML(rsModel, model, rsArrList,
				user);
		
		// ����ѧУ���Ի���Ϣ������
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	
	/**
	 * �����ۺϲ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZhcpInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);

		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		boolean flag = service.saveZhcpInfo(myForm, request, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * ��ʾ�ֶ��޸�Div(��չ�ֶ��޸�)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxgDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		
		PjpyGeneralService myService = new PjpyGeneralService();
		
		PjpyZhcpInit init = new PjpyZhcpInit();
		
		RequestForm rForm = new RequestForm();
		
		User user = getUser(request);// �û�����
		
		init.initZhcpMaintain(rForm, myForm, user, request);
		
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		// ============= ��ʼ����������ֵ ============
		// �û�����
		String zd = request.getParameter("zd");

		// �������ֶ�ID
		String zdid = request.getParameter("zdid");
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.showZdxgDiv(zd,zdid, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ͬ����Դ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateLybInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
		
		//ͬ����Դ������
		boolean flag=false;
		String message="";
		
		BeanUtils.copyProperties(myForm, model);
		
		flag=service.updateLybInfo(myForm, user);
			
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
	
		response.getWriter().print(message);
		
		return null;
	}
	
	// ---------------------�۲���Ϣά�� end--------------------------	
	
	// ---------------------�۲�����ѯ begin-------------------------
	
	/**
	 * ��ѯ�ۺϲ������(��������)	  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZhcpResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		
		// ����
		List<HashMap<String, String>> topTr = service.getZhcpResultTop(
				myForm,user);
		// �����
		ArrayList<String[]> rsArrList = service.getZhcpResultList(myForm, model,
				user);
		// ���������
		String spHtml = service.createZhcpResultHTML(rsModel, model, rsArrList,
				user);
		
		// ����ѧУ���Ի���Ϣ������
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setArrange("no");
		
		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * �ۺϲ���_��������_�۲��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kindChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
//		 ����
		boolean flag= service.saveKindChoose(model, user);
		
		String message="";
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
	
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * �ۺϲ���_�۲��ּܷ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
		
		//�۲��ּܷ���
		boolean flag=false;
		String message="";
		
		BeanUtils.copyProperties(myForm, model);
		
		flag = service.account(myForm, user);

		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	
	// ---------------------�۲�����ѯ end-------------------------
	
}
