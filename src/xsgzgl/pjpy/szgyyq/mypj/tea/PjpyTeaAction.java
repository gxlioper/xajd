package xsgzgl.pjpy.szgyyq.mypj.tea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.FileManage;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjInit;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(��ʦ)_Action��
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

public class PjpyTeaAction extends BasicAction {

	/**
	 * ��������_���ݹ�ҵ԰��_5S��ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fivesManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFiveS(rForm, myForm, request);

		String[] xn = new String[] { Base.currXn };
		String[] xq = new String[] { Base.currXq };

		myForm.getSearchModel().setPath("pjpy_szgyyq_fives.do");
		myForm.getSearchModel().setSearch_tj_xn(xn);
		myForm.getSearchModel().setSearch_tj_xq(xq);
		// =================== end ===================

		// ============= ����request��ֵ =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("fivesManage");
	}

	/**
	 * ���5S���б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFivesInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFiveS(rForm, myForm, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ѧ��
		String xh = user.getUserName();
		myForm.setXh(xh);

		// ��ѯ����
		String mhcx_lx = otherValue[0];

		// ģ����ѯ
		String input_mhcx = service.unicode2Gbk(otherValue[1]);

		// ѧ��
		String[] xn = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xn = otherValue[2].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// ѧ��
		String[] xq = null;

		if (!Base.isNull(otherValue[3].trim())) {
			xq = otherValue[3].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// ����
		// String[] cz = null;
		// if(!Base.isNull(otherValue[3].trim())){
		// cz = otherValue[3].split("!!##!!");
		// }
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		if("bz".equalsIgnoreCase(myForm.getYhlx())){
			searchModel.setMhcx_lx(mhcx_lx);
			searchModel.setInput_mhcx(input_mhcx);
		}
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getFivesInfoList(myForm, user);
		String spHtml = service.getFivesHtml(rsModel, myForm, rsArrList, user);
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
	 * ��������_���ݹ�ҵ԰��_5S����ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fivesDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFiveSDetail(rForm, myForm, request);

		String xh = myForm.getXh();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);

		// ������Ϣ
		List<HashMap<String, String>> infoList = service.getFivesList(myForm);
		request.setAttribute("infoList", infoList);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fivesDetail");
	}

	/**
	 * ����5S��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveFives(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����
		FivesModel fivesModel = new FivesModel();

		// ѧ��
		String xh = request.getParameter("xh");
		fivesModel.setXh(xh);

		// ѧ��
		String xn = Base.currXn;
		fivesModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		fivesModel.setXq(xq);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		fivesModel.setId(id);

		// ��ֵ��Ŀ
		String[] fzxm = request.getParameter("fzxm").split("!!@@!!");
		fivesModel.setFzxm(fzxm);

		// �Ӽ���
		String[] jjf = request.getParameter("jjf").split("!!@@!!");
		fivesModel.setJjf(jjf);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		fivesModel.setSqf(sqf);

		// �ӷ�����
		String[] jfrq = request.getParameter("jfrq").split("!!@@!!");
		fivesModel.setJfrq(jfrq);

		// ԭ��
		String[] yy = request.getParameter("yy").split("!!@@!!");
		fivesModel.setYy(yy);

		// �ӷ�ԭ��
		String[] jfyy = request.getParameter("jfyy").split("!!@@!!");
		fivesModel.setJfyy(jfyy);

		// ==================ִ�б������========================
		myForm.setFivesModel(fivesModel);
		boolean flag = service.saveFives(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ��5S��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delFives(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����
		FivesModel fivesModel = new FivesModel();

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		fivesModel.setId(id);

		// ==================ִ�б������========================
		myForm.setFivesModel(fivesModel);
		boolean flag = service.delFives(myForm, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��������_���ݹ�ҵ԰��_�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFssh(rForm, myForm, request);

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getFsshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		// ѧ��
		searchModel.setPath("pjpy_szgyyq_fssh.do");
		String[] xn = new String[] { Base.currXn };
		searchModel.setSearch_tj_xn(xn);

		// ѧ��
		String[] xq = new String[] { Base.currXq };
		searchModel.setSearch_tj_xq(xq);
		
		//�������
		String shtj = request.getParameter("shtj");
		
		if ("xsh".equalsIgnoreCase(shtj)) {// �����
			// ���״̬����
			String[] shztlx = new String[] { "����δ���", "����������" };
			searchModel.setSearch_tj_shztlx(shztlx);
		} else if ("ss".equalsIgnoreCase(shtj)) {
			// ���״̬����
			String[] ss = new String[] { "��" };
			searchModel.setSearch_tj_sf(ss);
		} else if ("ts".equalsIgnoreCase(shtj)) {
			// ���״̬����
			String[] ts = new String[] { "��" };
			searchModel.setSearch_tj_sftj(ts);
		}
		// =================== end ===================

		// ============= ����request��ֵ =============
		request.setAttribute("searchTj", searchModel);
		service.setRequestValue(rForm, request);
		
		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fsshManage");
	}

	/**
	 * ��ø�����Ŀ�ķ�������б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFsshInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��ѯ����
		String mhcx_lx = otherValue[0];

		// ģ����ѯ
		String input_mhcx = service.unicode2Gbk(otherValue[1]);

		// ��Ŀ����
		String xmdm = otherValue[2];
		myForm.setXmdm(xmdm);

		// ѧ��
		String xh = user.getUserName();
		myForm.setXh(xh);

		// ѧ��
		String xn = otherValue[3];

		if (!Base.isNull(otherValue[3].trim())) {
			xn = otherValue[3].split("!!##!!")[0];
		} else {
			xn = Base.currXn;
		}

		// ѧ��
		String xq = otherValue[4];

		if (!Base.isNull(otherValue[4].trim())) {
			xq = otherValue[4].split("!!##!!")[0];
		} else {
			xq = Base.currXq;
		}

		// ��ʼ��
		init.initFssh(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(new String[] { xn });
		searchModel.setSearch_tj_xq(new String[] { xq });

		if ("bz".equalsIgnoreCase(myForm.getYhlx())) {
			searchModel.setMhcx_lx(mhcx_lx);
			searchModel.setInput_mhcx(input_mhcx);
		}
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getFsshList(myForm, user);
		request.setAttribute("searchTj", searchModel);
		String spHtml = service.getFsshHtml(rsModel, myForm, rsArrList, user);
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
	 * ��������_���ݹ�ҵ԰��_���������ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFsshDetail(rForm, myForm, request);

		// ѧ��
		String xh = myForm.getXh();

		// ѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);

		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);

		// ������Ϣ
		List<HashMap<String, String>> infoList = service.getFsshInfoList(
				myForm, user);
		request.setAttribute("infoList", infoList);
		request.setAttribute("zhcpsd", service.getZhcpsdInfo(myForm));
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("fsshDetail");
	}

	/**
	 * ������˷�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// �û�����
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		myForm.setId(id);

		// ��������˷�
		String[] bzrshf = null;
		if (!Base.isNull(request.getParameter("bzrshf"))) {
			bzrshf = request.getParameter("bzrshf").split("!!@@!!");
		}
		myForm.setBzrshf(bzrshf);

		// ѧԺ��˷�
		String[] xyshf = null;
		if (!Base.isNull(request.getParameter("xyshf"))) {
			xyshf = request.getParameter("xyshf").split("!!@@!!");
		}
		myForm.setXyshf(xyshf);

		// ѧУ��˷�
		String[] xxshf = null;
		if (!Base.isNull(request.getParameter("xxshf"))) {
			xxshf = request.getParameter("xxshf").split("!!@@!!");
		}
		myForm.setXxshf(xxshf);

		// ==================ִ�б������========================
		boolean flag = service.saveShf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
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
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// �û�����
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		myForm.setId(id);

		// ��������˷�
		String[] bzrshf = null;
		if (!Base.isNull(request.getParameter("bzrshf"))) {
			bzrshf = request.getParameter("bzrshf").split("!!@@!!");
		}
		myForm.setBzrshf(bzrshf);

		// ѧԺ��˷�
		String[] xyshf = null;
		if (!Base.isNull(request.getParameter("xyshf"))) {
			xyshf = request.getParameter("xyshf").split("!!@@!!");
		}
		myForm.setXyshf(xyshf);

		// ѧУ��˷�
		String[] xxshf = null;
		if (!Base.isNull(request.getParameter("xxshf"))) {
			xxshf = request.getParameter("xxshf").split("!!@@!!");
		}
		myForm.setXxshf(xxshf);

		// ���״̬
		String shzt = request.getParameter("shzt");
		myForm.setShzt(shzt);
		// ==================ִ����˲���========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ����˲��� end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �����������״̬
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePlShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// �û�����
		String yhlx = request.getParameter("yhlx");
		myForm.setYhlx(yhlx);

		// ���ѧ��
		String[] shxh = request.getParameter("xh").split("!!@@!!");
		myForm.setShxh(shxh);

		// ���״̬
		String shzt = request.getParameter("shzt");
		myForm.setShzt(shzt);
		// ==================ִ����˲���========================
		boolean flag = service.savePlShzt(myForm, user);
		String message = flag ? "��˳ɹ�<br/>ע���������һ����<font color='blue'>δ���</font>�ļ�¼��" : "���ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ����˲��� end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �������ߴ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSscl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����
		XsssModel xsssModel = new XsssModel();

		// ѧ��
		String xn = request.getParameter("xn");
		xsssModel.setXn(xn);

		// ѧ��
		String xq = request.getParameter("xq");
		xsssModel.setXq(xq);

		// ��Ŀ����
		String xmlx = request.getParameter("xmlx");
		xsssModel.setXmlx(xmlx);

		// ѧ��
		String xh = request.getParameter("xh");
		xsssModel.setXh(xh);

		// ��ĿID
		String xmid = request.getParameter("xmid");
		xsssModel.setXmid(xmid);

		// �������
		String clyj = service.unicode2Gbk(request.getParameter("clyj"));
		xsssModel.setClyj(clyj);

		// ==================ִ�б������========================
		myForm.setXsssModel(xsssModel);
		boolean flag = service.saveSscl(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����Ͷ�ߴ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTscl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTeaForm myForm = (PjpyTeaForm) form;
		PjpyTeaService service = new PjpyTeaService();
		User user = getUser(request);// �û�����
		XstsModel xstsModel = new XstsModel();

		// ѧ��
		String xn = request.getParameter("xn");
		xstsModel.setXn(xn);

		// ѧ��
		String xq = request.getParameter("xq");
		xstsModel.setXq(xq);

		// ��Ŀ����
		String xmlx = request.getParameter("xmlx");
		xstsModel.setXmlx(xmlx);

		// ѧ��
		String xh = request.getParameter("tsr");
		xstsModel.setXh(xh);

		// ��Ͷ����
		String btsr = request.getParameter("btsr");
		xstsModel.setBtsr(btsr);

		// �������
		String clyj = service.unicode2Gbk(request.getParameter("clyj"));
		xstsModel.setClyj(clyj);

		// ==================ִ�б������========================
		myForm.setXstsModel(xstsModel);
		boolean flag = service.saveTscl(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ==================����Made By ΰ�����======================

	/**
	 * �ۺϲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward zhcpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		PjpyTeaService service = new PjpyTeaService();
		PjpyTeaForm myForm = (PjpyTeaForm) form;
		User user=getUser(request);

		String yhlx=(String)session.getAttribute("yhlx");
		
		myForm.setYhlx(yhlx);
		SearchModel searchModel = myForm.getSearchModel();
		searchModel.setPath("pjpy_szgyyq_zhcp.do");
		// ѧ��
		if (searchModel.getSearch_tj_xn() == null
				|| searchModel.getSearch_tj_xn().length == 0) {
			String[] xn = new String[] { Base.currXn };
			searchModel.setSearch_tj_xn(xn);
		}
		// ѧ��
		if (searchModel.getSearch_tj_xq() == null
				|| searchModel.getSearch_tj_xq().length == 0) {
			String[] xq = new String[] { Base.currXq };
			searchModel.setSearch_tj_xq(xq);
		}
		String doType = request.getParameter("doType");
		if ("jsfspm".equals(doType)) {// �����������
		// Long s=System.currentTimeMillis();
			boolean b = service.jsfspm(myForm, request);
			if (b) {
				request.setAttribute("message", "����ɹ���");
			} else {
				request.setAttribute("message", "����ʧ�ܣ�");
			}
			// Long e=System.currentTimeMillis();
			// System.out.println((e-s)/1000);
		} else if ("export".equals(doType)) {// �����ۺϲ����༶���ܱ�����
			String[] bjdm = searchModel.getSearch_tj_bj();
			if (bjdm != null && bjdm.length == 1) {
				myForm.setBjdm(bjdm[0]);
			}
			boolean b = service.exportZhcpBjhzb(myForm, response);
			if(b){
				return null;
			}else{
				request.setAttribute("yhInfo", "��ѡ��༶���ٽ��е�����");
				return new ActionForward("/yhInfo.do");
			}
		}

		// =================== end ===================

		// ============= ����request��ֵ =============
		request.setAttribute("rs", service.getZhcpList(myForm, user));

		request.setAttribute("searchTj", searchModel);

		// write��titile

		request.setAttribute("topTr", service.getTopTr("zhcpManage"));
		request.setAttribute("realTable", "szgy_zhszcphzlsb"); // �����
		request.setAttribute("tableName", "xg_view_szgy_zhszcphzlsb"); // ������ͼ

		request.setAttribute("path", "pjpy_szgyyq_zhcp.do");
		FormModleCommon.setNdXnXqList(request);

		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		
		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("yhlx", yhlx);

		return mapping.findForward("zhcpManage");
	}

	/**
	 * ��������_���ݹ�ҵ԰��_�����ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = new PjpyStuForm();
		BeanUtils.copyProperties(myForm, form);
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initJgcx(rForm, myForm, request);

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
		// cshXmList.remove(cshXmList.size()-1);
		// cshXmList.remove(cshXmList.size()-1);//�Ƴ�5S
		// 5S��
		// HashMap<String,String> map = new HashMap<String, String>();
		// map.put("xmdm", "szyc_5sb");
		// map.put("xmmc", "5S");
		// cshXmList.remove(map);//�Ƴ�5S
		//
		// // �ۺ����ʲ��
		// map = new HashMap<String, String>();
		// map.put("xmdm", "szgy_zhszcphzlsb");
		// map.put("xmmc", "�ۺ����ʲ��");
		// cshXmList.remove(map);//�Ƴ��۲�

		request.setAttribute("cshXmList", cshXmList);

		// ѧ��
		String[] xn = new String[] { Base.currXn };
		searchModel.setSearch_tj_xn(xn);

		// ѧ��
		String[] xq = new String[] { Base.currXq };
		searchModel.setSearch_tj_xq(xq);
		// =================== end ===================

		// ============= ����request��ֵ =============
		request.setAttribute("searchTj", searchModel);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		request.setAttribute("realTable", "szyq_ivtltb");
		request.setAttribute("tableName", "szyq_ivtltb");
		request.setAttribute("path", "pjpy_szgyyq_teajgcx.do");

		return mapping.findForward("jgcxManage");
	}

	/**
	 * ��ø�����Ŀ�Ľ����ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJgcxInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = new PjpyStuForm();
		BeanUtils.copyProperties(myForm, form);
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		String path = "pjpy_szgyyq_teajgcx.do";
		searchModel.setPath(path);
		// ==================�߼���ѯ��� end========================

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��Ŀ����
		String xmdm = otherValue[0];
		myForm.setXmdm(xmdm);

		// ��ʼ��
		init.initJgcx(rForm, myForm, request);

		// ѧ��
		String[] xn = searchModel.getSearch_tj_xn();

		if (xn != null && xn.length > 0) {

		} else {
			xn = new String[] { Base.currXn };
		}
		searchModel.setSearch_tj_xn(xn);

		// ѧ��
		String[] xq = searchModel.getSearch_tj_xq();

		if (xq != null && xq.length > 0) {

		} else {
			xq = new String[] { Base.currXq };
		}
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================��ʾ����========================
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getJgcxList(myForm, user);
		String spHtml = service.getJgcxHtml(rsModel, myForm, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ����Ivt��̳
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward exportIvtlt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTeaForm model = (PjpyTeaForm)form;
		SearchModel searchModel=model.getSearchModel();
		searchModel.setSearch_tj_xn(new String[]{});
		searchModel.setSearch_tj_xq(new String[]{});
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(searchModel);
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
//		String sql="select xh,xn,xq,jztm,xthdrq,jcdj,ccdj,'1' pf from " +
//				"(select a.*,b.nj,b.xydm,b.zydm,b.bjdm from szyq_ivtltb a,view_xsjbxx b where a.xh=b.xh) where 1=1 "+searchTj;
//		String[] outputValue={"xn","xq","xh","jztm","xthdrq","jcdj","ccdj","pf"};
//		String[] topTr=new String[]{"ѧ��","ѧ��","ѧ��","������Ŀ","����","�����Ǽ�","�����Ǽ�","����"};
		
		String sql="select '"+Base.currXn+"' xn,'"+Base.currXq+"' xq,xh,xm,xymc,zymc,bjmc,'' jztm,'' xthdrq,'' jcdj,'' ccdj,'1' pf from view_xsjbxx where 1=1 "+searchTj;
		String[] outputValue={"xn","xq","xh","xm","xymc","zymc","bjmc","jztm","xthdrq","jcdj","ccdj","pf"};
		String[] topTr=new String[]{"ѧ��","ѧ��","ѧ��","����","ѧԺ","רҵ","�༶","������Ŀ","����","�����Ǽ�","�����Ǽ�","����"};
		
		new Excel2Oracle().exportData(sql, inputV, outputValue, topTr, response.getOutputStream(), "export.xls");
		
		return null;
	}

	/**
	 * ����Ivt��̳
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public ActionForward importIvtlt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");// ��ͼ��
		String realTable = request.getParameter("realTable");// ����

		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);

		String act = request.getParameter("act");
		// ��������
		if ("import".equals(act)) {
			uploadFile(mapping, form, request, response);
			String filepath = (String) request.getAttribute("filepath");

			PjpyTeaService service = new PjpyTeaService();
			String back = service.importIvtlt(filepath, getUser(request));
			request.setAttribute("back", back);

		}
		return mapping.findForward("importIvtlt");
	}

	/**
	 * �ļ��ϴ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// �ô���Ҫ��֤��������ԱȨ��
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":",
				"");
		PjpyTeaForm hff = (PjpyTeaForm) form;
		FormFile file = hff.getImpFilePath();
		// if (file == null || (file.getFileName() == null ||
		// file.getFileName().trim().equals(""))) {
		// file = hff.getCheckFilePath();
		if (file == null) {
			return mapping.findForward("false");
		}
		// }
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}

}
