package xgxt.gygl.qsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;

import com.zfsoft.basic.BasicAction;
import common.exception.SystemException;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_���ҹ���-action��
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

public class GyglQsglAction extends BasicAction {

	/**
	 * ��Ԣ����_���ҹ���_�����Զ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// δ�����������ģ��Թ�Ԣ���л�������
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			// ������ϵ
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQszdfpRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			// ����������Ϣ������λ��Ϣ
			//service.creatGyglCwxx(myForm, user);
			// ִ���Զ���������
			String message = service.saveQsfpAuto(myForm, user);
			// service.saveQszdfp(myForm, user);

			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			rsArrList = service.getZdfpBmList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());

			// δ����������
			String wfpQsNum = service.getWfpQsNum();
			request.setAttribute("wfpQsNum", wfpQsNum);
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qszdfp");
	}

	/**
	 * ��Ԣ����_���ҹ���_�����Զ�����(���)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszdfpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// δ�����������ģ��Թ�Ԣ���л�������
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			// ������ϵ
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQszdfpjgRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ��ȡ��������� ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm,request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		service.setSearchModel(myForm, request);
		
		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			rsArrList = service.getZdfpjgList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
			
			if(rsArrList!=null && rsArrList.size()>0){
				rsArrList=service.getResultList(rsArrList, myForm.getPages());
			}
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qszdfpjg");
	}

	/**
	 * ��Ԣ����_���ҹ���_�����ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();

		// δ�����������ģ��Թ�Ԣ���л�������
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			// ������ϵ
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		
		init.getQssdfpRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ��ȡ��������� ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm,request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
			myForm.getSearchModel().setSearch_tj_rzzt(null);
			search = true;
		}
		// ================= end =====================
		
		// =============== �ж�ͳ��ҳ������ ===============
		// ͳ��ҳ��Ĺ�ѡ����
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0 
				&& !"del".equalsIgnoreCase(doType)
				&& !"return".equalsIgnoreCase(doType)) {
			service.setSearchModelValue(fpdx, myForm);
			search = true;
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		//if (search) {
			// �����
			rsArrList = service.getSdfpjgList(myForm, user, colList, request);
			
			// ��ס״̬����������
			myForm.getSearchModel().setSearch_tj_rzzt(myForm.getRzzt());
			request.setAttribute("searchTj", myForm.getSearchModel());
			
//			if (rsArrList != null && rsArrList.size() > 0) {
//				rsArrList = service.getResultList(rsArrList, myForm.getPages());
//			}
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfp");
	}
	
	/**
	 * ��Ԣ����_���ҹ���_�����ֶ�����(��ϸ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfpdDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// ������ϵ
		String csgx = jbszModel.getCsgx();

		init.getQssdfpRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();

		// ������
		String[] ssbh = myForm.getPrimarykey_checkVal();
		request.setAttribute("ssbh", ssbh);
		// ================= end =====================

		// =============== �����ֶ����� ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveQssdfp(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== ��÷�������б� ===============
		List<HashMap<String, Object>> fpdxList = service.getFpdxList(myForm,
				jbszModel, user);
		request.setAttribute("fpdxList", fpdxList);
		// ================= end =====================

		// =============== ��ù�Ԣ�ṹ�б� ===============
		List<HashMap<String, Object>> ldInfoList = service.getGyjgList(myForm,
				user);
		request.setAttribute("csgx", csgx);
		request.setAttribute("ldInfoList", ldInfoList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfpdDetail");
	}

	/**
	 * ��Ԣ����_���ҹ���_���ҷ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsfpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		String sfsz = jbszModel.getSfsz();

		// δ�����������ģ��Թ�Ԣ���л�������
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			// ������ϵ
			String csgx = jbszModel.getCsgx();

			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);

		init.getQsfpjgRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================
		
		// =============== �ж�ͳ��ҳ������ ===============
		// ͳ��ҳ��Ĺ�ѡ����
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0 && !"del".equalsIgnoreCase(doType)) {
			service.setSearchModelValue(fpdx, myForm);
			search = true;
		}
		// ================= end =====================
		
		// =============== ִ��ȡ��������� ===============
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.cancelQsfp(myForm, request);
			String message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			rForm.setMessage(message);
			myForm.getSearchModel().setSearch_tj_rzzt(null);
			search = true;
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		//if (search) {
			// �����
			rsArrList = service.getSdfpjgList(myForm, user, colList, request);
			// ��ס״̬����������
			myForm.getSearchModel().setSearch_tj_rzzt(myForm.getRzzt());
			request.setAttribute("searchTj", myForm.getSearchModel());
			
			//if(rsArrList!=null && rsArrList.size()>0){
				//rsArrList=service.getResultList(rsArrList, myForm.getPages());
			//}else{
			//	myForm.getPages().setMaxPage(1);
			//}
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qsfpjg");
	}

	/**
	 * ��Ԣ����_���ҹ���_���ҷ���ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsfptj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		String sfsz = jbszModel.getSfsz();

		// δ�����������ģ��Թ�Ԣ���л�������
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			// ����У��
			String czxq = jbszModel.getCzxq();
			// ����԰��
			String czyq = jbszModel.getCzyq();
			// ������ϵ
			String csgx = jbszModel.getCsgx();
			
			request.setAttribute("czxq", czxq);
			request.setAttribute("czyq", czyq);
			request.setAttribute("csgx", csgx);
		}

		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		// ���䷽ʽ
		String fpfs = jbszModel.getFpfs();
		request.setAttribute("fpfs", fpfs);
		
		// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
		if ("2".equalsIgnoreCase(fpfs)
				&& "xx".equalsIgnoreCase(user.getUserStatus())) {
			fpdx = "xy";
			myForm.setKffp("no");
		}else{
			myForm.setKffp("yes");
		}
		myForm.setFpdx(fpdx);

		init.getQsfpRForm(rForm, myForm, request);
		
		// ��Ԣ����ԱȨ��
		rForm.setGyglyQx(user.getGyglyQx());
		rForm.setUserName(user.getUserName());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================
		
		// =============== �ж��Ƿ����ҳ�淵�� ===============
		// ����
		String mklx = request.getParameter("mklx");
		if ("fh".equalsIgnoreCase(mklx)) {
			SearchModel searchModel = new SearchModel();
			myForm.setSearchModel(searchModel);
		}
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			// ִ���Զ���������
			String message = service.saveQsfpAuto(myForm, user);

			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		//if (search) {
			// �����
			try {
				rsArrList = service.getQsfpTjList(myForm, user, colList, request);
			} catch (SystemException e) {
				// TODO: handle exception
				catchSystemException(request, e);
			}
			
			request.setAttribute("searchTj", myForm.getSearchModel());

		//} else {
		//	myForm.getPages().setMaxPage(1);
		//}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		List<HashMap<String, String>> ldQsList = service.getLdForWfpQssList(user);
		request.setAttribute("ldQsList", ldQsList);
		// =================== end ===================
		
		return mapping.findForward("qsfptj");
	}
	
	/**
	 * ��Ԣ����_���ҹ���_�����ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qssdfpByHand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglQsglForm myForm = (GyglQsglForm) form;
		GyglQsglService service = new GyglQsglService();
		GyglQsglInit init = new GyglQsglInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		RequestForm rForm = new RequestForm();

		// �������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// ������ϵ
		String csgx = jbszModel.getCsgx();

		init.getQssdfpRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();

		// ������
		String[] ssbh = myForm.getPrimarykey_checkVal();
		request.setAttribute("ssbh", ssbh);
		
		// ���䷽ʽ
		String fpfs = jbszModel.getFpfs();
		// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
		if ("2".equalsIgnoreCase(fpfs)
				&& "xx".equalsIgnoreCase(user.getUserStatus())) {
			fpdx = "xy";
			myForm.setKffp("no");
		} else {
			myForm.setKffp("yes");
		}
		myForm.setFpdx(fpdx);
		request.setAttribute("fpfs", fpfs);
		request.setAttribute("fpdx", fpdx);
		// ================= end =====================

		// =============== �����ֶ����� ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveQssdfp(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== ��÷�������б� ===============
		List<HashMap<String, Object>> fpdxList = service.getFpdxByQssdfp(myForm);
		request.setAttribute("fpdxList", fpdxList);
		// ================= end =====================

		// =============== ��ù�Ԣ�ṹ�б� ===============
		myForm.setWfpqs("yes");
		myForm.setPrimarykey_checkVal(null);
		List<HashMap<String, Object>> ldInfoList = service.getGyjgList(myForm,
				user);
		request.setAttribute("csgx", csgx);
		request.setAttribute("ldInfoList", ldInfoList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qssdfpByHand");
	}
}
