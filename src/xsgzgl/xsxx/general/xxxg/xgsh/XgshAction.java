package xsgzgl.xsxx.general.xxxg.xgsh;

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
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸Č���_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XgshAction extends BasicAction {

	/**
	 * ��ѯ�޸Č���
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public ActionForward searchXgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		XgshInit init = new XgshInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initXgshSearch(rForm, myForm, user, request);
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// ��ʽ��ַ
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// ��������
		String splc = otherValue[2];
		model.setSplc(splc);
		
		// ��λID
		String gwid = otherValue[3];
		model.setGwid(gwid);
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
		List<HashMap<String, String>> topTr = service.getXgshTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service
				.getXgshList(myForm, model, user);
		// ���������
		String spHtml = service.createXgshHTML(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ���挏�ˠ�B
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ��ɾ������ begin ============
		boolean flag = service.saveShzt(model, user);
		String message = flag ? "ִ�гɹ�" : "ִ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
