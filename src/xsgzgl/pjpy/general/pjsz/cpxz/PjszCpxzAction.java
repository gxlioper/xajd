package xsgzgl.pjpy.general.pjsz.cpxz;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_����С��_ͨ��_Action��
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

public class PjszCpxzAction extends BasicAction {
	
	/**
	 * ��ѯ�������ã�����С�����ã����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszCpxz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszCpxzTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getPjszCpxzList(myForm, model,
				user);
		// ���������
		String spHtml = service.createPjszCpxzHTML(rsModel, model, rsArrList,
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
	 * ������������ã�δ��ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzNoChecked(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);

		// ����������
		String cpzmc = request.getParameter("cpzmc");
		if (!Base.isNull(cpzmc)) {
			model.setCpzmc(myService.unicode2Gbk(cpzmc));
		}
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
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		boolean flag = service.saveCpxz(myForm,model, user, "no_checked");
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������������ã���ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);

		// ����������
		String cpzmc = request.getParameter("cpzmc");
		if (!Base.isNull(cpzmc)) {
			model.setCpzmc(myService.unicode2Gbk(cpzmc));
		}
		
		// �༶����
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= �������С�����ã���ѡ���µģ� ============
		boolean flag = service.saveCpxz(myForm, model, user, "checked");
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ɾ�����������ã�δ��ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCpxzNoChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
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
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		boolean flag = service.deleteCpxz(myForm, model, user, "no_checked");
		String message = flag ? "ȡ���ɹ�" : "ȡ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ɾ�����������ã���ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCpxzChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// �༶����
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= �������С�����ã���ѡ�� ============
		boolean flag = service.deleteCpxz(myForm, model, user, "checked");
		String message = flag ? "ȡ���ɹ�" : "ȡ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �������С���Զ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzZdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// ���������
		String cpzgz = request.getParameter("cpzgz");
		model.setCpzgz(cpzgz);
		// ============= end ============

		// ============= �������С���Զ����� ============
		boolean flag = service.saveCpxzZdsz(model, user);
		String message = flag ? "���óɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������С���ύ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCpxzSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// ���������
		String cpzgz = request.getParameter("cpzgz");
		model.setCpzgz(cpzgz);
		// ============= end ============

		// ============= �������С���Զ����� ============
		String message = service.checkCpxzSubmit(model, user);
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
