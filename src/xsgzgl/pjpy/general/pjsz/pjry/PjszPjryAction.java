package xsgzgl.pjpy.general.pjsz.pjry;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ա_ͨ��_Action��
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

public class PjszPjryAction extends BasicAction {

	/**
	 * ��ѯ�������ã�������Ա���ã����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszPjry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszPjryTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getPjszPjryList(myForm, model,
				user);
		// ���������
		String spHtml = service.createPjszPjryHTML(rsModel, model, rsArrList,
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
	 * ����༶����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String bjdm = request.getParameter("bjdm");// �����༶����
		model.setBjdm(bjdm);

		String bjmc = request.getParameter("bjmc");// �����༶����
		if(!Base.isNull(bjmc)){
			model.setBjmc(myService.unicode2Gbk(bjmc));
		}

		String xh = request.getParameter("xh");// ѧ��
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= ���������༶���� ============
		boolean flag = service.saveBjtz(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward disfrockBjtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String xh = request.getParameter("xh");// ѧ��
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= ���������༶���� ============
		boolean flag = service.disfrockPjry(model, user);
		String message = flag ? "�����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String sfcp = request.getParameter("sfcp");// �����༶����
		model.setSfcp(sfcp);

		String xh = request.getParameter("xh");// ѧ��
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= ����������� ============
		boolean flag = service.saveSfcp(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
