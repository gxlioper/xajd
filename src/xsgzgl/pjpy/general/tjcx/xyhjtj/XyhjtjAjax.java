package xsgzgl.pjpy.general.tjcx.xyhjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class XyhjtjAjax extends BasicAction {
	
	/**
	 * ѧԺ��ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyhjtjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyhjtjService service = new XyhjtjService();
		XyhjtjForm myForm = (XyhjtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("pjpy_tjcx_xyhjtj.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> xmjhAll = (ArrayList<String[]>) service.getXmjhAll(myForm);
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getXyhjtjCx(myForm,xmjhAll);
		// ���������
		String spHtml = service.createSearchHTML(myForm,rsModel, rsArrList, xmjhAll, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ѧԺ��ͳ����ҳ���ݵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expXyhjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyhjtjService service = new XyhjtjService();
		XyhjtjForm myForm = (XyhjtjForm) form;
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expXyhjtj(response.getOutputStream(),myForm);
		return null;
	}
}