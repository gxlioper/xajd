package xsgzgl.xsxx.general.dljc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��¼���_ͨ��_Action��
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

public class XsxxDljcAction extends BasicAction {

	// ===================ҳ����ת begin=============================

	/**
	 * �����Ϣ���á�ҳ����ת��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initDljcSetting(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcSetting.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ===================ҳ����ת end=============================

	// ===================��ѯҳ�� begin=============================

	/**
	 * ��ѯ��¼���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchDljc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

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
		List<HashMap<String, String>> topTr = service.getXsxxDljclTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getXsxxDljcList(myForm, model,
				user);
		// ���������
		String spHtml = "";
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
	 * ������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resetDljc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ִ�����ò��� ============
		boolean flag = service.resetDljc(myForm, model, user);
		String message = flag ? "�����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================��ѯҳ�� end=============================

	// ===================����ҳ�� begin=============================

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createZdszDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSetting(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.createZdszDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		myService.getModelValue(model, request);
		// ============= end ============

		// ==================ִ�б������========================
		boolean flag = service.saveZdsz(model, user);
		String message = flag ? "���óɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================����ҳ�� end=============================

	// ===================��Ϣ���� begin=============================

	/**
	 * ������Ϣ����Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createXxwsDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSetting(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.createXxwsDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXxws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// ============= end ============

		// ==================ִ�б������========================
		boolean flag = service.saveXxws(user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================��Ϣ���� end=============================
}
