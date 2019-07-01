package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.action.init.InitGygl;
import xgxt.comm.MessageInfo;
import xgxt.form.User;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_��Ԣά��-action��
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

public class GyglGywhAction extends BasicAction {

	/**
	 * ��Ԣ����_��Ԣά��_��������ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService = new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user = getUser(request);// �û�����
		
		// �ı�����
		MessageResources message = getResources(request);
		
		String doType=request.getParameter("doType");
		myForm.setCzxq(jbszModel.getCzxq());
		myForm.setCzyq(jbszModel.getCzyq());

		//ģ������
		String mklx = request.getParameter("mklx");
		if(!"��".equalsIgnoreCase(jbszModel.getCzxq())
				){
			mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "yq" : mklx;
		}
		if(!"��".equalsIgnoreCase(jbszModel.getCzxq())
				&&!"��".equalsIgnoreCase(jbszModel.getCzyq())){
			mklx = "yq".equalsIgnoreCase(mklx)? "ld" : mklx;
		}
		mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "xq" : mklx;
		myForm.setMklx(mklx);
		
		//ɾ������
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delJcsj(myForm,request);
			request.setAttribute("result", result);
		}
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (!"��".equalsIgnoreCase(jbszModel.getCzxq())
				&& !"��".equalsIgnoreCase(jbszModel.getCzyq())) {
			
			String msg = "��ԢУ����԰��������,����ά����Ϣ,��ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//ɾ������������
		if("scjc".equalsIgnoreCase(doType)){
			service.setSearchModel(myForm, request);
		}
		//��ʼ�����뵼����
		service.JcmkxxInit(myForm);
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("tableName", myForm.getTableName());
		// �߼���ѯ
		request.setAttribute("searchType", mklx);
		// ҳǩ���ݼ���
		request.setAttribute("pageCard", service.getJcsjCard(jbszModel));
		// �߼���ѯҳ�淵��ֵ
		// �����
		List<String[]> rs = service.getJcsjList(myForm, user, request);
		String rsNum = (rs != null && rs.size() > 0) ? String
				.valueOf(rs.get(0).length - 1) : "0";

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("mklx", mklx);

		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("path", "gygl_gywh_jcsj.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jcsjwh");
	}
	
	/**
	 * ��Ԣ����_��Ԣά��_ҵ������ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ywsjwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		String mklx = request.getParameter("mklx");
		mklx = ("".equalsIgnoreCase(mklx) || Base.isNull(mklx)) ? "qsfp" : mklx;
		myForm.setMklx(mklx);
		
		myForm.setFpdx(jbszModel.getFpdx());
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delYwsj(myForm,request);
			request.setAttribute("result", result);
		}
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		//ɾ������������
		if("scjc".equalsIgnoreCase(doType)){
			service.setSearchModel(myForm, request);
		}
		
		service.YwmkxxInit(myForm);
		// �����
		List<String[]> rs = service.getYwsjList(myForm, request);
		String rsNum = (rs != null && rs.size() > 0) ? String
				.valueOf(rs.get(0).length-1) : "0";

		request.setAttribute("searchType", mklx);
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("pageCard", service.getYwsjCard());
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", service.getYwsjTopTr(myForm));
		request.setAttribute("mklx", mklx);
		request.setAttribute("path", "gygl_gywh_ywsj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ywsjwh");
	}

	/**
	 * ��Ԣ����_��Ԣά��_У����Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType = request.getParameter("doType");
		String xqdm = request.getParameter("xqdm");
		boolean result = false;
		HashMap<String, String> xqxxMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addXqxx(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setXqdm(xqdm);
			result = service.modiXqxx(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setXqdm(xqdm);
			xqxxMap = service.getXqxx(myForm);
		}

		request.setAttribute("rs", xqxxMap);
		request.setAttribute("doType", doType);
		return mapping.findForward("xqxxwh");
	}

	/**
	 * ��Ԣ����_��Ԣά��_԰����Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yqxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType = request.getParameter("doType");
		String yqdm = request.getParameter("yqdm");
		boolean result = false;
		HashMap<String, String> yqxxMap = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addYqxx(myForm);
			request.setAttribute("result", result);
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setYqdm(yqdm);
			result = service.modiYqxx(myForm);
			request.setAttribute("result", result);
		} else if ("update".equalsIgnoreCase(doType)) {
			myForm.setYqdm(yqdm);
			yqxxMap = service.getYqxx(myForm);
		}
		
		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("rs", yqxxMap);
		request.setAttribute("doType", doType);
		return mapping.findForward("yqxxwh");
	}

	/**
	 * ��Ԣ����_��Ԣά��_¥����Ϣά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		User user = getUser(request);// �û�����
		
		//�ı�����
		MessageResources message = getResources(request);
		
		//ģ������
		String mklx = "ldwh";
		myForm.setMklx(mklx);
		
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delJcsj(myForm, request);
			request.setAttribute("result", result);
		}

		// �ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
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
		
		service.JcmkxxInit(myForm);
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("realTable", myForm.getRealTable());
		request.setAttribute("rs", service.getJcsjList(myForm,user,request));
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("mklx", mklx);
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldwhManage");
	}
	
	/**
	 * ��Ԣ����_��Ԣά��_¥����Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String,String>ldxxMap=new HashMap<String,String>();
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		
		String lddm = request.getParameter("lddm");
		String doType=request.getParameter("doType");
		// Ĭ���Ա�
		String xbxd = myForm.getXbxd();
		
		if(Base.isNull(xbxd)){
			ldxxMap.put("xbxd", "���");
		}
		
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		boolean result=false;
		//����
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addLdxx(myForm);
			request.setAttribute("result", result);
		//�޸�
		} else if ("modi".equalsIgnoreCase(doType)) {
			myForm.setLddm(lddm);
			result = service.modiLdxx(myForm);
			request.setAttribute("result", result);
		//��ȡѡ�м�¼��Ϣ
		}else if ("update".equalsIgnoreCase(doType)) {
			myForm.setLddm(lddm);
			ldxxMap = service.getLdxx(myForm);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		request.setAttribute("xqList",service.getXqList());
		
		if(!Base.isNull(ldxxMap.get("xqdm"))){
			myForm.setXqdm(ldxxMap.get("xqdm"));
		}else{
			List<HashMap<String,String>> xqList = service.getXqList();
			if(xqList!=null && xqList.size()>0){
				myForm.setXqdm(service.getXqList().get(0).get("dm"));
			}
		}
		
		request.setAttribute("yqList",service.getYqList(ldxxMap.get("xqdm")));
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("rs", ldxxMap);
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldxxwh");
	}
	
	/**
	 * ��Ԣ����_��Ԣά��_��ʾ¥����ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldxxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String,String>ldxxMap=new HashMap<String,String>();
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user= getUser(request);// �û�����
		
		String pkValue=request.getParameter("pkValue");
		
		//¥����Ϣ
		myForm.setLddm(pkValue);
		ldxxMap = service.getLdxx(myForm);
		
		//����¥����Ϣ
		myForm.setCs(null);
		//������Ϊ����
		myForm.setFpbj("����");
		myForm.setFpdx(jbszModel.getFpdx());
		
		user.setUserStatus("xx");
		myForm.setUser(user);
		List<HashMap<String,String>>qsxxList=service.getQsfpList(myForm);
		//¥����ϸ��Ϣ
		List<HashMap<String,String>>ldlcxxList=service.getLdcsList(myForm);
	
		request.setAttribute("lcsz",myForm.getLcsz());
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("qsxxList", qsxxList);
		request.setAttribute("ldlcxxList", ldlcxxList);
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("ldxx", ldxxMap);
		request.setAttribute("path", "gygl_gywh_ldwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ldxxxx");
	}
	
	/**
	 * ��Ԣ����_��Ԣά��_������Ϣά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglGywhDAO dao=new GyglGywhDAO();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		String doType=request.getParameter("doType");
		String mklx = request.getParameter("mklx");
		User user = getUser(request);// �û�����
		
		//�ı�����
		MessageResources message = getResources(request);
		mklx = "qswh";
		myForm.setMklx(mklx);
		
		//ɾ������
		if("del".equalsIgnoreCase(doType)){
			boolean result=service.delJcsj(myForm,request);
			String messages = result ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", messages);
		}
		
		//�ж����ݿ����Ƿ���������
		String sfsz = jbszModel.getSfsz();
		if ("��".equalsIgnoreCase(sfsz)) {
			String msg = "��Ԣ����������δ��ɣ��޷����н���ȥ�Ĳ���������ϵ����Ա�������Ա����������ã���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 
		
		//��λ����
		if("cwsc".equalsIgnoreCase(doType)){
			GyglGywhModel model=new GyglGywhModel();
			model.setSfplcz(true);
			service.creatGyglCwxx(model,user);
			boolean flag =true;
			String messages = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			request.setAttribute("message", messages);
		}
		
		//������Ϣ��������
		if("plsz".equalsIgnoreCase(doType)){
			boolean flag =service.updateQsxx(myForm,user);
			String messages = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", messages);
		}
		
		service.JcmkxxInit(myForm);
		request.setAttribute("tableName", myForm.getTableName());
		request.setAttribute("realTable", myForm.getRealTable());
		service.getJcColList(myForm);
		String path = "gygl_gywh_qswh.do";
		myForm.getSearchModel().setPath(path);
		
		//��������
		myForm.setXb("���޸�");
		myForm.setKfhz("���޸�");
		myForm.setFpbj("���޸�");
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		
		//�����е�¥������������ǿ��Զ���䵽�߼���ѯ
		if(!Base.isNull(myForm.getLdHid())){
			myForm.getSearchModel().setSearch_tj_lddm(new String[]{myForm.getLdHid()});
		}
		request.setAttribute("rs", dao.getQssjList(myForm, user, request));
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("mklx", mklx);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qswhManage");
	}
	
	/**
	 * ������Ϣά��(���ӡ��޸�)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qsxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HashMap<String,String>qsxxMap=new HashMap<String,String>();
		List<HashMap<String,String>>cwxxList=new ArrayList<HashMap<String,String>>();
		
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form;
		User user = getUser(request);// �û�����
		String pkValue= request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		String page="qsxxwh";
		
		boolean result=false;
		//����
		if ("save".equalsIgnoreCase(doType)) {
			result = service.addQsxx(myForm,user);
			request.setAttribute("result", result);
		} 
		

		//�޸�
		if ("modi".equalsIgnoreCase(doType)) {
			result = service.modiQsxx(myForm,user);
			request.setAttribute("result", result);
			page="updateQsxx";	
		}
		
		
		String cws=InitGygl.initGygl.getCws();
		myForm.setCws(cws);
		//��ȡѡ���м�¼��Ϣ
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			
			myForm.setPkValue(pkValue);
			//��ȡ������ϸ��Ϣ
			qsxxMap = service.getQsxx(myForm);
			//��ȡ���Ҵ�λ��Ϣ
			cwxxList = service.getCwxx(myForm);
			
			page="updateQsxx";
			
			//��������ס
			String yyrrz="false";
			if(!"0".equalsIgnoreCase(qsxxMap.get("yzrcws"))){
				yyrrz="true";
			}
			request.setAttribute("yyrrz",yyrrz);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("yqList",service.getYqxxList());
		request.setAttribute("ldList",service.getLdList(myForm));
		
		if("add".equalsIgnoreCase(doType)){
			//Ĭ��ֵ
			qsxxMap.put("fpbj", "һ��");
			qsxxMap.put("xb", "��");
			qsxxMap.put("kfhz", "����");
			
		}
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cwxxList", cwxxList);
		request.setAttribute("cwsList", service.getCwsList(myForm));
		request.setAttribute("rs", qsxxMap);
		request.setAttribute("path", "gygl_gywh_qswh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward(page);
	}
	
	public ActionForward zsxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources message = getResources(request);
		GyglGywhService service=new GyglGywhService();
		GyglGywhForm myForm=(GyglGywhForm)form;
		
		String pkValue=request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		String xh=request.getParameter("xh");
		myForm.setXh(xh);
		String[]colList={"xh","xm","xb","xymc","zymc","bjmc","nj","ldmc","cs","qsh","cwh"};
		HashMap<String, String> stuInfo =service.getRsInfo("xg_view_gygl_xszsxx", "xh", xh, colList);
		request.setAttribute("xsxx", stuInfo);
		ArrayList<String[]>rs=(ArrayList<String[]>)service.getXszsxxInfo(myForm);
		request.setAttribute("rs",rs);
		myForm.setMklx("zsxx");
		request.setAttribute("topTr", service.getJcsjTopTr(myForm,message ));
		//	-------------------�Զ������й�����������----------------------
		// ���������
		String rsName = "rs";
		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		// ��ʾ����ʼ��
		String startNum = "0";
		// ��ʾ����
		String showNum =String.valueOf(4);
		
		
		if(rs!=null){
			if(rs.size()>7){
				myForm.getPages().setPageSize(rs.size());
			}else{
				
				myForm.getPages().setPageSize(7);
			}
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rsName", rsName);
		request.setAttribute("isCheckBox", isCheckBox);
		request.setAttribute("startNum", startNum);
		request.setAttribute("showNum", showNum);
		request.setAttribute("pObj",myForm.getPages());
		
		// -------------------�Զ������й�����������end----------------------
		return mapping.findForward("zsxxInfo");
	}
	
	/**
	 * �����Զ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zdscManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HashMap<String,String>qsxxMap=new HashMap<String,String>();
		User user = getUser(request);// �û�����
		GyglGywhService service = new GyglGywhService();
		GyglJbszService jbszService=new GyglJbszService();
		GyglJbszForm jbszModel = jbszService.getGyjbszModel();
		GyglGywhForm myForm = (GyglGywhForm) form; 
		String tzPath=request.getParameter("tzPath");
		String doType=request.getParameter("doType");
		
		String[] pkValue=myForm.getCheckVal();
		
		if(pkValue!=null && pkValue.length>0){
			qsxxMap.put("lddm", pkValue[0]);
		}
		
		if(!Base.isNull(tzPath)){
			if("qswh".equalsIgnoreCase(tzPath)){
				tzPath="gygl_gywh_qswh.do";
			}else if("ldwh".equalsIgnoreCase(tzPath)){
				tzPath="gygl_gywh_ldwh.do";
			}
			request.setAttribute("tzPath", tzPath);
		}
		
		if("zdsc".equalsIgnoreCase(doType)){
			
			boolean blog=service.zdscQs(myForm,user);
			request.setAttribute("ldHid", myForm.getLddm());
			myForm.setLddm(null);
			myForm.setYqdm(null);
			myForm.setXqdm(null);
			request.setAttribute("result", blog);
		}
		
		// =================�Զ��������ҵı�Ź���========================
		String bhgzxz=InitGygl.initGygl.getBhgz();
		String[]bhgzxzArr=bhgzxz.split("!!@@!!");
		//����Ĭ�ϱ�Ź���
		service.setQsBhgz(myForm);
		request.setAttribute("bhgzxzArr", bhgzxzArr);
		//  ===================end ======================
		
		request.setAttribute("doType", doType);
		request.setAttribute("lcsz",myForm.getLcsz());
		
		request.setAttribute("xqList",service.getXqList());
		request.setAttribute("yqList",service.getYqxxList());
		request.setAttribute("ldList",service.getLdList(myForm));
		
		request.setAttribute("message", myForm.getQss());
		request.setAttribute("czxq", jbszModel.getCzxq());
		request.setAttribute("czyq", jbszModel.getCzyq());
		request.setAttribute("rs", qsxxMap);
		request.setAttribute("path", "gygl_gywh_zdsc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zdscManage");
	}
}
