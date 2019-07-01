package xsgzgl.dtjs.general.tyjf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.DtjsGeneralService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���_��Ա�ɷ�_ͨ��_Action��
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

public class DtjsTyjfAction extends BasicAction {

	/**
	 * ��ѯ��Ա�ɷѽ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchTyjfResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		
		

		// ============= ��ʼ����������ֵ ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String edit =  otherValue[1];//�Ƿ����༭����ֵ
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
		List<HashMap<String, String>> topTr = service.getDtjsTyjfTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getDtjsTyjfList(myForm, model,
				user);
		// ���������
		String spHtml = "";
		if(null!=edit&&"yes".equals(edit)){
			spHtml= service.createDtjsTyjfHTMLofEdit(rsModel, model, rsArrList,
					user);
		}else{
			spHtml = service.createDtjsTyjfHTML(rsModel, model, rsArrList,
					user);
		}
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
	 * ������Ա�ɷ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveTyjf(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������Ա�ɷ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveBjTyjf(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * �������
	 */
	public ActionForward expTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		User user = getUser(request);// �û�����
		DtjsTyjfModel model = new DtjsTyjfModel();
		model.setXn(Base.currXn);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = new String[]{"PK","ѧ��","ѧ��","����","�꼶","�༶","Ӧ���ŷ�","Ӧ���ŷ�","Ƿ��"};
		List<String[]> rs = service.getDtjsTyjfList(myForm, model,
				user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
}
