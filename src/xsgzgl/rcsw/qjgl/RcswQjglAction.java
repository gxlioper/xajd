package xsgzgl.rcsw.qjgl;

import java.io.File;
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
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ճ�����_��ٹ���_Action��
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

public class RcswQjglAction extends BasicAction {

	/**
	 * �ճ�����_��ٹ���_��������_������̣�ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjlcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initQjlc(rForm, myForm, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("qjlcManage");
	}

	/**
	 * �ճ�����_��ٹ���_��������_������̣���ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjlcDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initQjlc(rForm, myForm, request);
		String id = myForm.getId();
		// =================== end ===================

		// ============= ������������Ϣ ============
		HashMap<String, String> rs = service.getQjlcInfo(myForm);
		rForm.setRs(rs);

		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		//request.setAttribute("qjlxList", service.getQjlbList());
		// =================== end ===================

		return mapping.findForward("qjlcDetail");
	}
	
	/**
	 * �鿴�����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewDetial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ============= ������������Ϣ ============
		String userType=user.getUserType();
//		HashMap<String, String> rs = new HashMap<String,String>();
//		if("stu".equalsIgnoreCase(userType)){
//			rs.putAll(service.getStuInfo(user.getUserName()));
//		}else{
//			String xh=request.getParameter("xh");
//			rs.putAll(service.getStuInfo(xh));
//		}
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		List<HashMap<String, String>> lctLists = service.getLctList(myForm);
		String qstz="0";
		for(HashMap<String, String> map:lctLists){
			if(map.get("shzt").toString().equals("��ͨ��")){
				qstz="1";
				break;
			}
		}
		request.setAttribute("lctLists", lctLists);
		request.setAttribute("lsszie",lctLists.size());
		request.setAttribute("qstz",qstz);
		
//		request.setAttribute("qjshList", service.getQjshInfo(myForm, user));
		rForm.setRs(rs);
		
		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		
		myForm.setQjlx("sj");
		request.setAttribute("qjlxList", service.getQjlbList());
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs",rs);
		return mapping.findForward("viewdetail");
	}

	/**
	 * ������������Ϣ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQjlcList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��������
		String lxmc = service.unicode2Gbk(otherValue[0]);
		myForm.setLxmc(lxmc);

		// �ɷ��޸�
		String kfxg = otherValue[1];
		myForm.setKfxg(kfxg);

		// ��ʼ��
		init.initQjlc(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getQjlcList(myForm, user);
		String spHtml = service.getQjlcHtml(rsModel, myForm, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ID
		String id = request.getParameter("id");
		myForm.setId(id);

		// ��������
		String lxmc = service.unicode2Gbk(request.getParameter("lxmc"));
		myForm.setLxmc(lxmc);

		// ��С����
		String mints = request.getParameter("mints");
		myForm.setMints(mints);

		// �������
		String maxts = request.getParameter("maxts");
		myForm.setMaxts(maxts);

		// ����ID
		String lcid = request.getParameter("lcid");
		myForm.setLcid(lcid);
		
		// �������
		String qjlx = request.getParameter("qjlx");
		myForm.setQjlx(qjlx);
		
		// ==================ִ�б������========================
		String message = ""; 
		String qjs = service.minDaysIsLegality(myForm);
		if(Integer.valueOf(qjs).intValue()>0){
			message="-999";
		}else{
			boolean flag = service.saveQjlc(myForm, user, request);
			message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		}
		
		
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ɾ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delQjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ID
		String[] checkVal = request.getParameter("id").split("!!@@!!");
		myForm.setCheckVal(checkVal);
		
		// ==================ִ�б������========================
		boolean flag = service.delQjlc(myForm, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	//===================���������������======================================
	
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵ���Ո��ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myqjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ==================��½�û���� ==================
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "��ģ��ֻ����ѧ���û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		  
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		// =================== end ===================

		return mapping.findForward("myqjManage");
	}
	
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵ���٣���ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myqjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ============= ������������Ϣ ============
		String userType=user.getUserType();
		HashMap<String, String> rs = new HashMap<String,String>();
		if("stu".equalsIgnoreCase(userType)){
			rs.putAll(service.getQjStudentInfo(user.getUserName()));
		}else{
			String xh=request.getParameter("xh");
			rs.putAll(service.getQjStudentInfo(xh));
		}
		
		List<HashMap<String, String>> splcList = service.getQjlcList();
		request.setAttribute("splcList", splcList);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		
		myForm.setQjlx("sj");
		request.setAttribute("qjlxList", service.getQjlbList());
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("rs",rs);
		// =================== end ===================

		return mapping.findForward("myqjDetail");
	}
	
	/**
	 * ����ҵ���������б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyqjList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ѧ��
		String xn = otherValue[0];
		myForm.setXn(xn);

		// ѧ��
		String xq = otherValue[1];
		myForm.setXq(xq);

		// ��ʼʱ��
		String kssj = otherValue[2];
		myForm.setKssj(kssj);

		// ����ʱ��
		String jssj = otherValue[3];
		myForm.setJssj(jssj);
		
		// ��ʼ��
		init.initMyqj(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getMyqjList(myForm, user);
		String spHtml = service.getMyqjHtml(rsModel, myForm, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ɾ���ҵ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delMyqj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ID
		String[] checkVal = request.getParameter("id").split("!!@@!!");
		myForm.setCheckVal(checkVal);
		
		// ==================ִ�б������========================
		boolean flag = service.delMyqjSq(myForm, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ���������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		User user = getUser(request);// �û�����

		// ��������
		String sqts = request.getParameter("sqts");
		myForm.setSqts(sqts);
		
		String qjlx = request.getParameter("qjlx");
		myForm.setQjlx(qjlx);

		// ==================��������Ŀ========================
		//String qjxm = service.getQjxm(myForm, user);
//		response.setContentType("text/html;charset=gbk");
//		response.getWriter().print(qjxm);
		// ==================��������Ŀ end========================
		List<HashMap<String, String>> map = service.getQjxm(myForm, user);
		JSONArray qjlcsList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(qjlcsList);
		return null;
	}
	
	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ���ID
		String qjid = request.getParameter("qjid");
		myForm.setQjid(qjid);

		// ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);

		// �������
		String sqts = request.getParameter("sqts");
		myForm.setSqts(sqts);

		// ��ʼʱ��
		String kssj = request.getParameter("kssj");
		myForm.setKssj(kssj);

		// ����ʱ��
		String jssj = request.getParameter("jssj");
		myForm.setJssj(jssj);
		
		String qjlx = request.getParameter("qjlx");
		myForm.setKzzd1(qjlx);

		// ��ϵ�绰
		String lxdh = request.getParameter("lxdh");
		myForm.setLxdh(lxdh);

		// ��ͥ�绰
		String jtdh = request.getParameter("jtdh");
		myForm.setJtdh(jtdh);

		// ��ͥ��ַ
		String jtdz = service.unicode2Gbk(request.getParameter("jtdz"));
		myForm.setJtdz(jtdz);

		// ��������
		String sqly = service.unicode2Gbk(request.getParameter("sqly"));
		myForm.setSqly(sqly);

		// ��ע
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		myForm.setBz(bz);
		
		// ��ٲ���
		String qjcl = service.unicode2Gbk(request.getParameter("qjcl"));
		myForm.setQjcl(qjcl);

		// ==================ִ�б������========================
		boolean flag = service.saveMysq(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	//	===================�����ҵ����======================================
	
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵĹ�������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mygzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
	

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMygz(rForm, myForm, request);
		// =================== end ===================
		  
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
	
		// =================== end ===================
		
		return mapping.findForward("mygzManage");
	}
	
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵĹ�����ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMysh(rForm, myForm, request);
		
		String czxm = request.getParameter("czxm");
		request.setAttribute("czxm", czxm);
		
		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm,user);
		request.setAttribute("cshXmList", cshXmList);
		
		// =================== end ===================
		  
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		// =================== end ===================

		return mapping.findForward("myshManage");
	}
	
	/**
	 * �ճ�����_��ٹ���_�����ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mycxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		String tableName="xg_view_rcsw_qjgl_qjsq";

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMycx(rForm, myForm, request);
		
		// =================== end ===================
		  
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		request.setAttribute("tableName", tableName);
		// =================== end ===================

		return mapping.findForward("mycxManage");
	}
		
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵ���٣���ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initQjlc(rForm, myForm, request);
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);
		// =================== end ===================

		// ============= ����ҵ������Ϣ ============
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		rs.put("shgw", shgw);
		rForm.setRs(rs);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		List<HashMap<String, String>> shList = service.getShList(myForm,user);
		request.setAttribute("shList", shList);

		return mapping.findForward("myshDetail");
	}

	/**
	 * ����ҵĹ���ͳ���б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMygzList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		
		// ��ʼ��
		init.initMygz(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		String spHtml = service.getMygzHtml(rsModel, myForm, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(null);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ����ҵ�����б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyshList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ����
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ������Ŀ
		String czxm = otherValue[0];
		myForm.setCzxm(czxm);

		// ��˸�λ
		String shgw = otherValue[1];
		myForm.setShgw(shgw);
		
		// IE
		String ie = otherValue[2];
		rsModel.setIe(ie);

		// ·��
		String stylePath = otherValue[3];
		rsModel.setStylePath(stylePath);
		
		// ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);

		// ��ʼ��
		init.initMysh(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getMyshList(myForm, user);
		String spHtml = service.getMyshHtml(rsModel, myForm, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��ý����ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMycxList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ����
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);

		// ��ʼ��
		init.initMycx(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getMycxList(myForm, user);
		String spHtml = service.getMycxHtml(rsModel, myForm, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ��ø�λ��Ϣ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShgwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		
		String czxm = request.getParameter("czxm");
		myForm.setCzxm(czxm);

		List<HashMap<String, String>> list = service.getShgwInfoList(myForm, user);
		request.setAttribute("gwList", list);
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return mapping.findForward("shgwInfo");
	}

	/**
	 * ��������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		//����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		//��˸�λ
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);
		// ============= ��ʼ����������ֵ end============

		// ==================����ǰ̨ҳ��========================
		service.createShInfoHtml(myForm,response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * �ճ�����_��ٹ���_�ҵĹ���_�ҵ���٣���ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		String doType=request.getParameter("doType");
		
		String forward="print".equalsIgnoreCase(doType)?"qjglPrint" : "jgcxDetail";
		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initQjlc(rForm, myForm, request);
		
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		// =================== end ===================

		// ============= ����ҵ������Ϣ ============
		HashMap<String, String> rs = service.getMyqjInfo(myForm, user);
		rs.put("sqid", sqid);
		
		List<HashMap<String, String>> qjshList = service.getQjshInfo(myForm, user);
		request.setAttribute("qjshList",qjshList);
		request.setAttribute("qjshlength",qjshList.size());
		
		rForm.setRs(rs);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward(forward);
	}
	
	/**
	 * �������״̬
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		RcswQjglInit init = new RcswQjglInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ����
		String[] pk = null;	
		if (!Base.isNull(request.getParameter("pk"))) {
			pk = request.getParameter("pk").split("!!@@!!");
			myForm.setCheckVal(pk);
		}
		
		// ���״̬
		String shzt = "";
		if (!Base.isNull(request.getParameter("shzt"))) {
			shzt = service.unicode2Gbk(request.getParameter("shzt"));
			myForm.setShzt(shzt);
		}
		
		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// ��˸�λ
		String shgw = request.getParameter("shgw");
		myForm.setShgw(shgw);

		// ������
		String shyj = "";
		if (!Base.isNull(request.getParameter("shyj"))) {
			shyj = service.unicode2Gbk(request.getParameter("shyj"));
			myForm.setShyj(shyj);
		}
		
		// ==================ִ�б������========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʦ��Ƭ�ϴ�
	 */
	public ActionForward uploadQjcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswQjglForm myForm = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		
		String result = service.saveQjcl(myForm);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * �ճ���������ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward jgcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		RcswQjglForm model = (RcswQjglForm) form;
		RcswQjglService service = new RcswQjglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getMycxExportList(model,user);
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
