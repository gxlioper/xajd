package xsgzgl.xsxx.general.zxxs;

import java.io.InputStream;
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
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_ͨ��_Action��
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

public class XsxxZxxsAction extends BasicAction{
	
	/**
	 * ��ѯ��Уѧ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXsxxZxxs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getXsxxZxxsTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getXsxxZxxsList(myForm, model,
				user);
		// ���������
		String spHtml = service.createXsxxZxxsHTML(rsModel, model, rsArrList,
				user);
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
	 * ����ѧ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		boolean flag = service.saveXsxx(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����ҵ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBycl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		boolean flag = service.saveBycl(model, user);
		String message = flag ? "�����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��Ƭ�ϴ�
	 */
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();
		String type=(String) request.getAttribute("type");

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ִ�б������ ============
		String flag = service.saveStuPic(myForm, user);
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	
	/**
	 * ����ʦ����ѧ�߿���Ƭ�ϴ�
	 */
	public ActionForward uploadStuGkPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();
		User user = getUser(request);// �û�����

		// ============= ִ�б������ ============
		String flag = service.saveStuGkPic(myForm, user);
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
}
