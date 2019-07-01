package xsgzgl.pjpy.general.wdpj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyWdpjAction extends BasicAction {

	/**
	 * ��ѯ�������ã��ҵ�����ͳ�ƣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjpyWdpj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		PjpyWdpjModel model = new PjpyWdpjModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initWdpj(rForm, myForm, user, request);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
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
		List<HashMap<String, String>> topTr = service.getPjpyWdpjTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getPjpyWdpjList(myForm, model,
				user);
		// ���������
		String spHtml = service.createPjpyWdpjHTML(rsModel, myForm, model,
				rsArrList, user);
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
	 * ��ʾѧ������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdpjView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����
		
		String ie =request.getParameter("ie");
		rsModel.setIe(ie);
		
		String stylePath =request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);
		
		// ============= ��ʼ����������ֵ ============
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
		String xmdm = request.getParameter("xmdm");
	
		service.showWdpjView(rsModel,xmdm, user, response);
		
		return null;
	}
	
	/**
	 * ��ʾѧ������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdpjLssb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		String[] otherValue = request.getParameter("otherValue")
		.split("!!@@!!");
		
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		// ============= ��ʼ����������ֵ ============
		//init.initSaveXssq(rForm, myForm, user, request);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		// ��������
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");

		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
			
		service.showWdpjView( rsModel,xmdm, user, response);
		
		return null;
	}
	
	/**
	 * ��ʾѧ������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLnzcInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����
		
		String ie =request.getParameter("ie");
		rsModel.setIe(ie);
		
		String stylePath =request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);
		
		String pkValue=request.getParameter("pkValue");
		
		// ============= ��ʼ����������ֵ ============
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
		String xmdm = request.getParameter("xmdm");
	
		service.showLnzcInfo(rsModel,pkValue,user, response);
		
		return null;
	}
	
	/**
	 * �����ҵ����DIV
	 * 
	 * @date 2013-02-04
	 * @author ΰ�����
	 */
	public ActionForward createWdshDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjModel model = new PjpyWdpjModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createWdshDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
}
