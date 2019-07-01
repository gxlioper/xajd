package xsgzgl.pjpy.general.pjsz.pjxm;

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
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ŀ_ͨ��_Action��
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

public class PjszPjxmAction extends BasicAction {
	
	/**
	 * ��ѯ�������ã�������Ŀ���ã����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszPjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszPjxmTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getPjszPjxmList(myForm, model,
				user);
		// ���������
		String spHtml = service.createPjszPjxmHTML(rsModel, model, rsArrList,
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
	 * ��ʼ��������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjxmSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//��������
		String step = request.getParameter("step");
		model.setStep(step);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultPjxmSetting(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ��ʼ��������̸�λ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultShlcGwxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmService service = new PjszPjxmService();
		
		// ��������
		String lcid = request.getParameter("lcid");
		model.setLcid(lcid);

		// ��������
		String rssz = request.getParameter("rssz");
		model.setRssz(rssz);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultShlcGwxx(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ����������Ŀ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.savePjxm(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ����������Ŀ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.updatePjxm(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPjxmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);

		// ��Ŀ�������
		String xmdm = request.getParameter("xmdm");
		// =================== end ===================

		// ==================���������Ŀ��Ϣ====================
		HashMap<String, String> map = service.getPjxmMap(xmdm, user);
		// ��������
		String rssz = map.get("rssz");
		// ���Ʒ�Χ
		String kzfw = map.get("kzfw");

		String message = rssz + "!!luojw!!" + kzfw + "!!luojw!!";
		// ==================���������Ŀ��Ϣ end================
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �����Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXmmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= �����Ŀ���� ============
		boolean flag = service.checkXmmc(model);
		String message = flag ? "" : "fail";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjxmUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//��������
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultPjxmUpdate(model, user, response);
		
		
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	

	/**
	 * ��ʼ��������Ŀ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//��������
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		myService.getModelValue(model, request);
		
		String message=service.checkDelete(model, user);
		boolean flag=false;
		
		if(Base.isNull(message)){
			
			flag=service.deletePjxm(model, user);

			message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
			
		}
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
}
