package xsgzgl.customForm.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_DEMO_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class DemoFormAction extends BasicAction {

	// ===============������D begin=====================

	/**
	 * �Զ����_����ģ��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward demoFormManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initDemoFormManage(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("demoFormManage");
	}

	/**
	 * �Զ����_����ģ��_�ֶ��O��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszdSetupDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initXszdSetup(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("xszdSetupDetail");
	}

	/**
	 * �Զ����_����ģ��_�ֶ��O��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxSetupDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initJgcxSetup(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxSetupDetail");
	}

	// ===============������D end=======================

	// ===============��ԃ�Y�� begin=====================

	/**
	 * ��ѯ��Ŀ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getCustomFormTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.ggetCustomFormList(myForm,
				model, user);
		// ���������
		String spHtml = service.createCustomFormHTML(rsModel, model, rsArrList,
				user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	// ===============��ԃ�Y�� end=====================

	// ===============��β��� begin=====================
	
	/**
	 * �@ʾ�Զ��x���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCustomForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		DemoFormInit init = new DemoFormInit();

		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ begin =======
		// ��ʼ��
		init.initXszdSetup(rForm, myForm, user, request);
		BeanUtils.copyProperties(myForm, model);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.showCustomForm(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �����x�еČ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createClickedObj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		DemoFormInit init = new DemoFormInit();

		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ begin =======
		String td_id = request.getParameter("td_id");
		if (!Base.isNull(td_id)) {
			model.setTd_id(td_id.split("!!@@!!"));
		}
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createClickedObj(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		boolean flag = service.saveTable(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ����ϲ���Ԫ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalition(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		boolean flag = service.saveTable(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	// ===============��β��� end=====================
}
