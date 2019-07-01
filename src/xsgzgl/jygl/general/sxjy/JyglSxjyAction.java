package xsgzgl.jygl.general.sxjy;

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
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.JyglGeneralService;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ҵ����_ʵϰ��ҵ_ͨ��_Action��
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

public class JyglSxjyAction extends BasicAction {
	
	
	/**
	 * ��ѯ��ʷ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSxjyManage(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getSxjyTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getSxjyList(myForm, model,
				user);
		// ���������
		String spHtml = service.createSxjyHTML(rsModel, model, rsArrList,
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
	 * ����������ʷ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSxjyUpdate(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.saveSxjy(model, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}	

	/**
	 * ɾ��������ʷ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSxjyUpdate(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.deleteSxjy(model, user, request);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}	
}
