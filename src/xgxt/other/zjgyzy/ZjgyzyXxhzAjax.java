package xgxt.other.zjgyzy;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.rcsw.zjbb.RcswZjbbForm;

import com.zfsoft.basic.BasicAction;

public class ZjgyzyXxhzAjax extends BasicAction{
	
	ZjgyzyXxhzInit init= new ZjgyzyXxhzInit();
	
	ZjgyzyXxhzService service= new ZjgyzyXxhzService();
	/**
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsrsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initXsrsManage(rForm, model, request, user);

		// �������ʾ�ֶ�getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXsrsList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * ѧ��ס����Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initXszsManage(rForm, model, request, user);

		// �������ʾ�ֶ�getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXszsList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdaManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initXsdaManage(rForm, model, request, user);

		// �������ʾ�ֶ�getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXsdaList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * ����Ա��Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initFdyManage(rForm, model, request, user);

		// �������ʾ�ֶ�getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getFdyList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * ����Ա������Ƹ��һ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward prqkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initPyqkManage(rForm, model, request, user);

		// �������ʾ�ֶ�getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getPyqkList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * Υ�ʹ�����Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initWjcfManage(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getWjcfList(myForm,model,"cx");

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	
}
