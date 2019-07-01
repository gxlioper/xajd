package xsgzgl.pjpy.general.xmsz.rssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_Action��
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

public class XmszRsszAction extends BasicAction {

	/**
	 * ��ѯ��Ŀ���ã��������ã����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXmszRssz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
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
		
		// ��Ŀ����
		String xmdm = otherValue[1];
		myForm.setXmdm(xmdm);
		
		// ����
		String arrange = otherValue[2];
		myForm.getSearchModel().setArrange(arrange);
		// ����ʽ
		String fashion = otherValue[3];
		myForm.getSearchModel().setFashion(fashion);
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
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
		List<HashMap<String, String>> topTr = service.getXmszRsszTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getXmszRsszList(myForm, model,
				user);
		// ���������
		String spHtml = service.createXmszRsszHTML(rsModel, model, rsArrList,
				user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setArrange("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * �������ñ�����δ��ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSzblNoChecked(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		// ���ñ���
		String szbl = request.getParameter("szbl");
		model.setSzbl(szbl);
		
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		// �꼶
		String nj = request.getParameter("nj");
		if (!Base.isNull(nj)) {
			searchModel.setSearch_tj_nj(nj.split("!!@@!!"));
		}
		// ѧԺ
		String xy = request.getParameter("xy");
		if (!Base.isNull(xy)) {
			searchModel.setSearch_tj_xy(xy.split("!!@@!!"));
		}
		// רҵ
		String zy = request.getParameter("zy");
		if (!Base.isNull(zy)) {
			searchModel.setSearch_tj_zy(zy.split("!!@@!!"));
		}
		// �༶
		String bj = request.getParameter("bj");
		if (!Base.isNull(bj)) {
			searchModel.setSearch_tj_bj(bj.split("!!@@!!"));
		}
		
		myForm.setSearchModel(searchModel);
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= �����������ã�δ��ѡ�� ============
		boolean flag = service.saveSzbl(myForm,model, user, "no_checked");
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �������ñ�������ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSzblChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ���ñ���
		String szbl = request.getParameter("szbl");
		model.setSzbl(szbl);
		
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		if (!Base.isNull(pkValue)) {
			model.setPkValue(pkValue.split("!!@@!!"));
		}
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= �������ñ�������ѡ�� ============
		boolean flag = service.saveSzbl(myForm, model, user, "checked");
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ����ȷ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ȷ������
		String qdrs = request.getParameter("qdrs");
		if (!Base.isNull(qdrs)) {
			model.setQdrs(qdrs.split("!!@@!!"));
		}
		
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		if (!Base.isNull(pkValue)) {
			model.setPkValue(pkValue.split("!!@@!!"));
		}
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= ����ȷ������ ============
		boolean flag = service.saveQdrs(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
