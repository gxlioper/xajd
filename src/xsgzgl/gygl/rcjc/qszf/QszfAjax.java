package xsgzgl.gygl.rcjc.qszf;

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
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;

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
 * Time:2012-7-9 ����14:19:22
 * </p>
 */

public class QszfAjax extends BasicAction {
	/**
	 * �����߷���ҳ��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		QszfForm myForm = (QszfForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_rcjc_qszf.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getQszfCx(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	/**
	 * ��ȡ�����߷���ʦ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZfls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		QszfForm myForm = (QszfForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_rcjc_qszf.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr2();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getZfls(myForm);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	/**
	 * ������ѯ-���߷���ʦְ���Ż�ȡ����༶
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFzbjForZflsgh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String zflsgh = request.getParameter("zflsgh");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getFzbjForZflsgh(zflsgh);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * ������ѯ-���߷���ʦְ���Ż�ȡ�߷���ʦ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZflsxmForZflsgh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		QszfForm qszfForm = (QszfForm) form;
		String zflsgh = request.getParameter("zflsgh");
		QszfService service = new QszfService();
		HashMap<String, String> zflsxmRs = service.getZflsxmForZflsgh(qszfForm,zflsgh);
		String zflsxm = zflsxmRs.get("xm");
		if (zflsxm == null || zflsxm == "") {
			zflsxm = "";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(zflsxm);
		return null;
	}

	/**
	 * ������ѯ-���߷���ʦ����༶��ȡ¥��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLdForFzbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String fzbj = request.getParameter("fzbj");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getLdForFzbj(fzbj);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * ������ѯ-��¥���Ż�ȡ���Һ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getQshForLd(fzbj, xsszld);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * �����߷���Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QszfService service = new QszfService();
		String message = "";
		boolean flag = false;
		QszfForm myForm = (QszfForm) form;
		User user = getUser(request);
		String zflsgh = request.getParameter("zflsgh");
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		String xsszqsh = request.getParameter("xsszqsh");
		String xqsj = request.getParameter("xqsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setZflsgh(zflsgh);
		myForm.setFzbj(fzbj);
		myForm.setXsszld(xsszld);
		myForm.setXsszqsh(xsszqsh);
		myForm.setXqsj(xqsj);
		myForm.setBz(bz);
		flag = service.qszfBc(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * �����߷���Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QszfService service = new QszfService();
		String message = "";
		boolean flag = false;
		QszfForm myForm = (QszfForm) form;
		User user = getUser(request);
		String zflsgh = request.getParameter("zflsgh");
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		String xsszqsh = request.getParameter("xsszqsh");
		String fzbjBefore = request.getParameter("fzbjxgq");
		String xsszldBefore = request.getParameter("xsszldxgq");
		String xsszqshBefore = request.getParameter("xsszqshxgq");
		String xqsj = request.getParameter("xqsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setZflsgh(zflsgh);
		myForm.setFzbj(fzbj);
		myForm.setXsszld(xsszld);
		myForm.setXsszqsh(xsszqsh);
		myForm.setBjBefore(fzbjBefore);
		myForm.setXsszldBefore(xsszldBefore);
		myForm.setXsszqsBefore(xsszqshBefore);
		myForm.setXqsj(xqsj);
		myForm.setBz(bz);

		flag = service.qszfXg(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * ���޴���ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqcsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QszfForm myForm = (QszfForm) form;
		QszfService service = new QszfService();
		User user = getUser(request);// �û�����
		myForm.setUserName(user.getUserName());
		String xn = request.getParameter("xn");
		if(!StringUtil.isNull(xn)){
			xn = xn.substring(0, 4);
		}
		String yf = request.getParameter("yf");
		String xymc = request.getParameter("xymc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expXqtjb(myForm, response.getOutputStream(), xn, yf, xymc);
		return null;
	}
}