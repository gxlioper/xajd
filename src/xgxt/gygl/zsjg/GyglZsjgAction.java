package xgxt.gygl.zsjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.cwgl.GyglCwglInit;
import xgxt.gygl.cwgl.GyglCwglService;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.gygl.gywh.GyglGywhService;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.gygl.qsgl.GyglQsglInit;
import xgxt.gygl.qsgl.GyglQsglService;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.gygl.tjfx.GyglTjfxService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_ס�޽��-action��
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

public class GyglZsjgAction extends BasicAction {

	/**
	 * ��Ԣ����_ס�޽��_��ʷ��Ϣά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getLsxxRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ��ɾ������ ===========
		if("del".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.deleteLsxx(myForm));
			search=true;
		}
		
		// =============== ִ�в�ѯ���� ===========
		if (search) {
			rsArrList = (ArrayList<String[]>)service.getZslsxxList(myForm, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}
		
		
		request.setAttribute("path", "gygl_zsjg_lsxx.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("lsxxManage");
	}
		
	/**
	 * ��Ԣ����_ס�޽��_ס����Ϣͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zsxxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// �û�����
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//�������ʾ�ֶ�
		init.iniZstjRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// ����б�
		ArrayList<String[]> rsArrList = null;
		//�Ƿ��ѯ����
		
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		
		
		//if (search) {
			// �����
			myForm.getSearchModel().setPath("gygl_zsjg_zstj.do");
			rsArrList = (ArrayList<String[]>)service.getZdfpBmTjList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsxxTj");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_ס����Ϣά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getZsxxRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		
		// ����У��
		String czxq = jbszModel.getCzxq();
		// ����԰��
		String czyq = jbszModel.getCzyq();
		// ������ϵ
		String csgx = jbszModel.getCsgx();

		request.setAttribute("czxq", czxq);
		request.setAttribute("czyq", czyq);
		request.setAttribute("csgx", csgx);
		// ================= end =====================
		String[] colList = rForm.getColList();
		
		// =============== ִ�й�ѡ���޲��� ===========
		if("xsts".equalsIgnoreCase(doType)){
			boolean result=service.xsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		

		// =============== ִ���������޲��� ===========
		if("plxsts".equalsIgnoreCase(doType)){
			boolean result=service.plxsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		
		
		//ͳ��ҳ��Ĺ�ѡ����
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
			search = true;
		}
		
		
		// =============== ִ�в�ѯ���� ===========
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_zsxx.do");
			rsArrList = (ArrayList<String[]>)service.getZsxxList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
			
		request.setAttribute("path", "gygl_zsjg_zsxx.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zsxxManage");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_�����춯����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getQsydRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ��ɾ������ ===========
		if("del".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.deleteLsxx(myForm));
			search=true;
		}
		
		//ѧ������
		if("xsts".equalsIgnoreCase(doType)){
			boolean result=service.xsts(myForm);
			request.setAttribute("result", result);
			search=true;
		}
		
		//ͳ��ҳ��Ĺ�ѡ����
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
			search = true;
		}
		// =============== ִ�в�ѯ���� ===========
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_ydgl.do");
			rsArrList = (ArrayList<String[]>)service.getZsxxList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		request.setAttribute("path", "gygl_zsjg_ydgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ydglManage");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_ѧ����λ�춯
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscwyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglCwglForm cwglForm=new GyglCwglForm();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglCwglService cwglService=new GyglCwglService();
		GyglGywhService gywhService=new GyglGywhService();
		GyglZsjgInit init = new GyglZsjgInit();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getQsydRForm(rForm, myForm, request);
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		if("save".equalsIgnoreCase(rForm.getDoType())){
			
			boolean result=service.saveCwyd(myForm);
			request.setAttribute("result", result);
			rForm.setDoType("cwyd");
		}
		
		// =============== ִ�д�λ�춯 ===========
		//��Ҫ���䴲λ��ѧ��
		if ("cwyd".equalsIgnoreCase(rForm.getDoType())){
			//��Ҫ���䴲λ��ѧ��
			
			
			if(myForm.getCheckVal()==null || myForm.getCheckVal().length==0){
				myForm.setCheckVal(myForm.getXsxhArr());
			}
			
			cwglForm.setPkvArr(myForm.getCheckVal());
			List<HashMap<String,String>>fpcwxsList=cwglService.getFpcwXs(cwglForm);
			//¥����Ϣ�б�
			List<HashMap<String,Object>>ldxxxxList=cwglService.getSsxxList(cwglForm);
			//����¥����Ϣ
			GyglGywhForm gywhForm=new GyglGywhForm();
			gywhForm.setCs(null);
			gywhForm.setFpbj("һ��");
			gywhForm.setFpbm("δ����");
			gywhForm.setFpdx(fpdx);
			gywhForm.setUser(user);
			List<HashMap<String,String>>qsxxList=gywhService.getQsfpList(gywhForm);
			request.setAttribute("qsxxList", qsxxList);
			request.setAttribute("ldxxxxList", ldxxxxList);
			request.setAttribute("fpcwxsList", fpcwxsList);
		}
		
		request.setAttribute("path", "gygl_zsjg_ydgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================
		
		request.setAttribute("fpdx", jbszModel.getFpdx());
		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xscwyd");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_�����춯ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssydTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// �û�����
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//�������ʾ�ֶ�
		init.iniSsydTjRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// ����б�
		ArrayList<String[]> rsArrList = null;
			
		// �����
		myForm.getSearchModel().setPath("gygl_zsjg_ydtj.do");
		rsArrList = (ArrayList<String[]>) service.getSsydList(myForm, user,
				colList, request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// } else {
		// myForm.getPages().setMaxPage(1);
		//		}
		
		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ssydTj");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_�����춯��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssydCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		RequestForm rForm = new RequestForm();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgInit init=new GyglZsjgInit();
		User user = getUser(request);// �û�����
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		//�������ʾ�ֶ�
		init.iniSsydCxRForm(rForm, myForm, request);
		String[] colList = rForm.getColList();
		
		// ����б�
		ArrayList<String[]> rsArrList = null;
		//�Ƿ��ѯ����
		
		
		//ͳ��ҳ��Ĺ�ѡ����
		String[] pk = myForm.getPrimarykey_checkVal();
		if (pk != null && pk.length > 0) {
			service.setSearchModelValue(jbszModel.getFpdx(), myForm);
		}
		
		// if (search) {
		// �����
		myForm.getSearchModel().setPath("gygl_zsjg_ydcx.do");
		rsArrList = (ArrayList<String[]>) service.getSsydcxList(myForm, user,
				colList, request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// } else {
		// myForm.getPages().setMaxPage(1);
		//		}
		
		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rsArrList", rsArrList);
		
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ssydCx");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_�����춯����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxssTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MessageResources message = getResources(request);
		
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgService service=new GyglZsjgService();
		//��Ԣ��������
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		
		RequestForm rForm = new RequestForm();
		
		GyglZsjgInit init=new GyglZsjgInit();
		//�û�����
		User user = getUser(request);
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());
		//�������ʾ�ֶ�
		init.iniKxssTjForm(rForm, myForm, request,message);
		
		// ����б�
		ArrayList<String[]> rs = null;
		String[]colList=rForm.getColList();
		
		//�Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		
		//��ѯ����
		//if (search) {
			myForm.getSearchModel().setPath("gygl_zsjg_kxss.do");
			rs = (ArrayList<String[]>)service.getKxqsList(myForm, user, colList, request,"cx");
			request.setAttribute("searchTj", myForm.getSearchModel());
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}
		
		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		request.setAttribute("rs", rs);
		request.setAttribute("czxq", myForm.getCzxq());
		request.setAttribute("czyq", myForm.getCzyq());
		request.setAttribute("path", "gygl_zsjg_kxss.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kxssTj");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgService service=new GyglZsjgService();
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgInit init=new GyglZsjgInit();
		RequestForm rForm = new RequestForm();
		User user=new User();
		
		
		String lddm=request.getParameter("lddm");
		myForm.setLddm(lddm);
		//�������ʾ�ֶ�
		init.iniKqsxxForm(rForm, myForm, request,message);
		
		myForm.setFpdx(jbszModel.getFpdx());
		
		List<String[]>rs=new ArrayList<String[]>();
		rs=service.getKqsxx(myForm, user, rForm.getColList(), request);
		
		request.setAttribute("lddm", lddm);
		request.setAttribute("ldMap", service.getLdMap(myForm));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", rForm.getTopTr());
		return mapping.findForward("kqsxx");
	}
	
	/**
	 * ��Ԣ����_ס�޽��_��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgService service=new GyglZsjgService();
		GyglZsjgForm myForm=(GyglZsjgForm)form;
		GyglZsjgInit init=new GyglZsjgInit();
		RequestForm rForm = new RequestForm();
		User user=new User();
		
		String lddm=request.getParameter("lddm");
		myForm.setLddm(lddm);
		//�������ʾ�ֶ�
		init.iniXqsxxForm(rForm, myForm, request,message);
		
		myForm.setFpdx(jbszModel.getFpdx());
		
		List<String[]>rs=new ArrayList<String[]>();
		rs=service.getXqsxx(myForm, user, rForm.getColList(), request);
		
		request.setAttribute("lddm", lddm);
		request.setAttribute("ldMap", service.getLdMap(myForm));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", rForm.getTopTr());
		return mapping.findForward("xqsxx");
	}
	
	/** 
	 * Method printNjfbtj
	 * �����꼶�ֲ����ͳ�Ƶ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward printKxss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageResources message = getResources(request);
		GyglZsjgService service=new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglZsjgForm myForm = (GyglZsjgForm) form;
		
		//�������
		String fpdx = jbszModel.getFpdx();
		myForm.setFpdx(fpdx);
		
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_ssnjfbtj.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		RequestForm rForm = new RequestForm();
		myForm.getSearchModel().setPath("gygl_zsjg_kxss.do");
		GyglZsjgInit init=new GyglZsjgInit();
		
		String dclx=request.getParameter("dclx");
		myForm.setDclx(dclx);
		if("kxqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniKxssTjForm(rForm, myForm, request,message);
		}else if("kqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniKqsxxForm(rForm, myForm, request,message);
		}else if("xqs".equalsIgnoreCase(myForm.getDclx())){
			init.iniXqsxxForm(rForm, myForm, request,message);
		}
		
		service.printKxss(myForm, request, wwb,rForm);
		return null;
	}
		
	// ================���� Made By ΰ�����=====================
	/**
	 * ��Ԣ����_ס�޽��_������ס���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsrzqkCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglZsjgForm myForm = (GyglZsjgForm) form;
		GyglZsjgService service = new GyglZsjgService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglZsjgInit init = new GyglZsjgInit();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		String sfsz = jbszModel.getSfsz();
		
		myForm.setFpdx(jbszModel.getFpdx());
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
		init.getQsrzqkRForm(rForm, myForm, request);

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

		// =============== ִ�е������� ===============
		if ("exp".equalsIgnoreCase(doType)) {
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expQsrzqkToExcel(myForm, user, request, response.getOutputStream());

			return null;
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========

		// ��λ��Ϣ�б�
		List<HashMap<String, String>> cwInfoList = service
				.getQsrzCwList(service.getMaxCws());
		request.setAttribute("cwInfoList", cwInfoList);

		// �����
		rsArrList = service.getQsInfoList(myForm, user, colList, request);
		List<HashMap<String, Object>> qsrzInfoList = service
				.getQsrzInfoList(myForm,rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("qsrzInfoList", qsrzInfoList);

		rForm.setRsArrList(rsArrList);

		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("qsrzqkCx");
	}

	// =================���� Made By ΰ�����==================
}
