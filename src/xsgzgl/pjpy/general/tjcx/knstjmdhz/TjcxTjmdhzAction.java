package xsgzgl.pjpy.general.tjcx.knstjmdhz;

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
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxTjmdhzInterface;
import xsgzgl.pjpy.general.tjcx.tjmdhz.TjcxTjmdhzModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_�Ƽ���������_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class TjcxTjmdhzAction extends BasicAction {

	/**
	 * ��ѯ�񽱽�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createTjmdhzHTML(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxTjmdhzInit init = new TjcxTjmdhzInit();
		TjcxTjmdhzModel model = new TjcxTjmdhzModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.init(rForm, myForm, user, request);
		TjcxTjmdhzInterface service = myService.getTjcxKnsTjmdhzService(myForm);

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
		List<HashMap<String, String>> topTr = service.getTjmdhzTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getTjmdhzList(myForm, model,
				user);
		// ���������
		String spHtml = service.createTjmdhzHTML(rsModel, model, rsArrList,
				user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setCheckBoxRs("no");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����񽱽�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expTjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxTjmdhzInit init = new TjcxTjmdhzInit();
		TjcxTjmdhzModel model = new TjcxTjmdhzModel();


		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.init(rForm, myForm, user, request);
		TjcxTjmdhzInterface service = myService.getTjcxKnsTjmdhzService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expTjmdhz(myForm,model,user, response.getOutputStream());
		// ============= end ============

		return null;
	}
}
