package xsgzgl.qgzx.tjcx;

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
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxTjcxAjax extends BasicAction{
	
	/**
	 * �¶ȳ�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=ydcjfftjCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByYdcjfftjCx();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getYdcjfftjCx(myForm);
		// ���������
		String spHtml = service.createSearchHTMLByYdcjfftjCx(myForm,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��ȳ�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ndcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=ndcjfftjCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByNdcjfftjCx();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getNdcjfftjCx(myForm);
		ArrayList<String[]> rsArrListAll = (ArrayList<String[]>) service.getNdcjfftjCxAll(myForm);
		// ���������
		String spHtml = service.createSearchHTMLByNdcjfftjCx(myForm, rsModel, rsArrList, rsArrListAll, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���ų�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=bmcjfftjCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByBmcjfftjCx();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBmcjfftjCx(myForm);
		// ���������
		String spHtml = service.createSearchHTMLByBmcjfftjCx(myForm,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���˳�𷢷�ͳ����ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=grcjfftjCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByGrcjfftjCx();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getGrcjfftjCx(myForm);
		ArrayList<String[]> rsArrListAll = (ArrayList<String[]>) service.getGrcjfftjCxAll(myForm);
		// ���������
		String spHtml = service.createSearchHTMLByGrcjfftjCx(myForm,rsModel, rsArrList,rsArrListAll, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * �����¶ȳ�𷢷�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expYdcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expYdcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * �������ų�𷢷�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expBmcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expBmcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * �������˳�𷢷�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expGrcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expGrcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * ������ȳ�𷢷�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expNdcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expNdcjfftj(response.getOutputStream(),myForm);
		return null;
	}
}