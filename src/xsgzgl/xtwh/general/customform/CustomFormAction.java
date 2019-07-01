package xsgzgl.xtwh.general.customform;

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
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_Action��
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

public class CustomFormAction extends BasicAction {

	// ===============������D begin=====================
	/**
	 * �Զ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initManage(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormManage");
	}

	/**
	 * �Զ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initParameter(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormParameter");
	}

	/**
	 * �Զ�����@ʾ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSetting(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormSetting");
	}

	/**
	 * �Զ������ԃ�O��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormSearch");
	}
	
	// ===============������D end=======================

	// ===============������ begin=====================
	/**
	 * ��ѯ�Զ����
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

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

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
		ArrayList<String[]> rsArrList = service.getCustomFormList(myForm,
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

	/**
	 * ɾ���Զ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.deleteCustomForm(model, user);
		String message = flag ? "�h���ɹ�" : "�h��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============������ end=====================

	// ===============������ begin=====================
	/**
	 * ����ѡ������DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createChoosePkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		String source_table = request.getParameter("source_table");
		String source_table_pk = request.getParameter("source_table_pk");
		model.setSource_table(source_table);
		model.setSource_table_pk(source_table_pk);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createChoosePkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ����ѡ�����DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createChooseRelateDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		String source_table = request.getParameter("source_table");
		String assistant_table = request.getParameter("assistant_table");
		String lx = request.getParameter("lx");
		model.setSource_table(source_table);
		if("one".equalsIgnoreCase(lx)){
			model.setAssistant_table_one(assistant_table);
		}else{
			model.setAssistant_table_two(assistant_table);
		}	
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createChooseRelateDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �����Զ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCustomForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveCustomForm(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// ===============������ end=====================

	// ===============����ԃbegin=====================
	/**
	 * �����Զ��x��β�ԃ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCustomSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createCustomSearch(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ������ԃ�����ֶ�TABLE
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSearchCzzdTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createSearchCzzdTable(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �����ԃ�ֶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSearchZd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveSearchZd(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �h����ԃ�ֶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSearchZd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.deleteSearchZd(model, user);
		String message = flag ? "�h���ɹ�" : "�h��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����ѯ�ֶ���ʾ˳��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSearchZdXssx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveSearchZdXssx(model, user);
		String message = flag ? "�h���ɹ�" : "�h��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// ===============����ԃ end=====================
	
	// ===============������ begin=====================
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
	public ActionForward saveTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
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
	 * �����Զ��x���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		String form_id = request.getParameter("form_id");
		model.setForm_id(form_id);
		
		String contextPath = request.getContextPath();
		model.setContextPath(contextPath);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createCustomForm(model, user, response);
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
	public ActionForward createClickedObj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		String td_id = request.getParameter("td_id");
		if (!Base.isNull(td_id)) {
			// model.setTd_id(td_id.split("!!luojw!!"));
		}
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createClickedObj(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ����ϲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalitionCol(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveCoalitionCol(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ȡ���ϲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelCoalitionCol(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.cancelCoalitionCol(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����ϲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalitionRow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveCoalitionRow(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ȡ���ϲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelCoalitionRow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.cancelCoalitionRow(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��úϲ���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCoalitionInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		HashMap<String, String> map = service.getCoalitionInfo(model);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(map));

		return null;
	}

	/**
	 * ���������ֶ�TABLE
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCzzdTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createCzzdTable(model, user, response);
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
	public ActionForward saveZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		boolean flag = service.saveZd(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �������ҵ���DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createAreaDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createAreaDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �����޸��ֶ�����DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createConfigureDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createConfigureDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �޸��ֶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================ִ���޸Ĳ���====================
		boolean flag = service.updateZd(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ==================ִ���޸Ĳ��� end================

		return null;
	}
	// ===============������ end=====================
	
	// ===============�������� begin=====================
	/**
	 * ѧ����Ƭ�ϴ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();

		User user = getUser(request);// �û�����

		// ============= ִ�б������ ============
		boolean flag = service.saveStuPic(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		//request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	// ===============�������� end=====================
}
