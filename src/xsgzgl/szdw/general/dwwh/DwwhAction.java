package xsgzgl.szdw.general.dwwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.szdw.teainfo.TeaInfoService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_����ά��_ͨ��_Action��
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

public class DwwhAction extends BasicAction {

	/**
	 * ��ѯ˼�����S�o
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public ActionForward searchDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		DwwhInit init = new DwwhInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		String path = request.getParameter("path");
		myForm.setPath(path);

		// ============= ��ʼ����������ֵ ============
		init.initDwwhSearch(rForm, myForm, user, request);
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");

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
		List<HashMap<String, String>> topTr = service.getDwwhTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getDwwhList(myForm, model, user);
		// ���������An internal error has occurred.

		String spHtml = service.createDwwhHTML(rsModel, model, rsArrList, user);
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
	 * ��������ά��DIV
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public ActionForward createDwwhDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		// service.createDwwhDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================
		String html = service.createDwwhDivStr(model, user, response);
		request.setAttribute("html", html);
		return mapping.findForward("dwwhzj");
	}

	/**
	 * ����˼������ά��
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	@SystemLog(description = "����˼������-˼������-����Ա��Ϣά��-����ZGH:{zgh},XM:{xm},XB:{xb},BMDM:{bmdm}")
	public ActionForward saveDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�б������ begin ============
		boolean flag = service.saveDwwh(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ��˼������ά��
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	@SystemLog(description = "����˼������-˼������-����Ա��Ϣά��-ɾ��values:{pkValue}")
	public ActionForward deleteDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
//		myService.getModelValue(model, request);
		model.setPkValue(myForm.getZghs().split(","));
		// =================== end ===================

		// ============= ִ��ɾ������ begin ============
		boolean flag = service.deleteDwwh(model, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �����û���DIV
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward createYhkDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createYhkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * �����û����û���
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward saveYhk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�б������ begin ============
		boolean flag = service.saveYhk(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����Ժϵ����DIV
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward createYxjrDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		// service.createYxjrDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================
		request.setAttribute("html", service.createYxjrDiv(model, user, response));
		return mapping.findForward("yxjr");
	}

	/**
	 * �����û����û���
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward saveYxjr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�б������ begin ============
		boolean flag = service.saveYxjr(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �����༶��ϢDIV
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward createBjxxDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createBjxxDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * 
	 * @����:�޸ĵ�����ҳ��
	 * @���ߣ�dlq [���ţ�445]
	 * @���ڣ�2013-8-21 ����12:21:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward createBjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================
		// ==================�޸�ǰ̨ҳ��====================
		String lx = request.getParameter("lx");
		String zgh = request.getParameter("zgh");
		model.setLx(lx);
		model.setZgh(zgh);
		HashMap<String, String> map = service.getDwwh(model, user);

		// ��������
		String lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		request.setAttribute("map", map);
		request.setAttribute("lxmc", lxmc);
		request.setAttribute("rsArrList", service.createBjxx(model, user));
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("lx", lx);
		request.setAttribute("zgh", zgh);
		// ==================�޸�ǰ̨ҳ�� end================

		return mapping.findForward("viewDbs");
	}

	/**
	 * �����꼶Div
	 * 
	 * @date 2013-01-11
	 * @author ΰ�����
	 */
	public ActionForward createNjLvDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ����Html ============
		service.createNjLvDiv(model, user, response);
		// ============= ����Html end ============

		return null;
	}

	/**
	 * ������������Div
	 * 
	 * @date 2013-01-11
	 * @author ΰ�����
	 */
	public ActionForward createOtherLvDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ����Html ============
		service.createOtherLvDiv(model, user, response);
		// ============= ����Html end ============

		return null;
	}

	/**
	 * �������༶
	 * 
	 * @date 2013-01-14
	 * @author ΰ�����
	 */
	public ActionForward saveFpbj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�б������ begin ============
		boolean flag = service.saveFpbj(model, user);
		String message = flag ? "ִ�гɹ�" : "ִ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �������༶
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public ActionForward disfrockFpbj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ִ�б������ begin ============
		boolean flag = service.disfrockFpbj(model, user);
		String message = flag ? "ִ�гɹ�" : "ִ��ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ������ְ��Option
	 * 
	 * @date 2013-01-18
	 * @author ΰ�����
	 */
	public ActionForward createJzgOption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
 		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= ��ȡoption begin ============
		String option = service.createJzgOption(model);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(option);

		return null;
	}

	/**
	 * ��ѯ�༶��Ϣ
	 * 
	 * @date 2013-01-18
	 * @author ΰ�����
	 */
	public ActionForward searchSetup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		DwwhInit init = new DwwhInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initDwwhSearch(rForm, myForm, user, request);
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================��ʾ����========================

		// ����
		List<HashMap<String, String>> topTr = service.getSetupTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getSetupList(myForm, model, user);
		// ���������
		String spHtml = service.createSetupHTML(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������ְ����ϢDIV
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public ActionForward createJzgxxDiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhModel model = new DwwhModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createJzgxxDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ��ѯ��ְ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzgxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		DwwhJzgModle model = new DwwhJzgModle();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		myService.getModelValue(model, request);
		String zgh = request.getParameter("zgh");

		User user = getUser(request);// �û�����
		if (zgh == null || "".equals(zgh)) {
			zgh = user.getUserName();
			// ����Ա������Ϣ�޸ĺ͹���Ա�޸������ʾ
			request.setAttribute("flag", "true");
		}
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("xy", "true");
		}
		// ְ���б�
		request.setAttribute("zwList", new TeaInfoService().getZwList());
		// ���ظ�λ����б�
		request.setAttribute("gwlbList", new TeaInfoService().getGwlbList());
		//����ְ���б�
		request.setAttribute("zcList", new TeaInfoService().getZcList());
		model.setZgh(zgh);
		model = service.cxJzgxx(model, user,request);
		request.setAttribute("model", model);

		String path1 = "szdw_dwwh.do?method=cxJzgxx&zgh=";
		request.setAttribute("path", path1);
		request.setAttribute("bmList", new DwwhService().getBmList());
		
		// ����ʦ����ѧ����Ա��Ϣ���Ի���ʾ
		if ("11318".equals(Base.xxdm)) {
			// ���ر�������
			request.setAttribute("bzlxList", new TeaInfoService().getBzlxList());
			// ����ְ������
			request.setAttribute("zcList", new TeaInfoService().getZcList());
			// ������ݾ�������
			request.setAttribute("sfjtlxList", new TeaInfoService().getZzFdyList());
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx_11318");
		} else if ("13023".equals(Base.xxdm)) {
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx_13023");
		} else {
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("szdwwhjzgxx");
		}

	}

	/**
	 * �鿴��ְ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckJzgxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		String zgh = request.getParameter("zgh");

		if ("11318".equals(Base.xxdm)) {
			HashMap<String, String> map = service.ckJzgxxJxsf(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx_11318");
		} else if ("13023".equals(Base.xxdm)) {
			HashMap<String, String> map = service.ckJzgxx_13023(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx_13023");
		} else {
			HashMap<String, String> map = service.ckJzgxx(zgh);
			request.setAttribute("map", map);
			return mapping.findForward("ckJzgxx");
		}
	}

	/**
	 * �޸Ľ�ְ������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDwwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		boolean flag = service.updateJzgxx(request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().println(message);
		return null;
	}

	/**
	 * ����Ա��Ϣά���Զ��嵼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward fdyxxwhExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SzdwGeneralForm model = (SzdwGeneralForm) form;

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(model);

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		List<HashMap<String, String>> resultList = service.getDwwhExportList(model, user);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		insertLog(mapping, form, request, response);//���뵼��������־
		return null;
	}

	/**
	 * 
	 * @����:�����Ƽ�ʦ����ѧ˼�����鸨��Ա��Ϣ�������Ի�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:20:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward fdyxxwhExportJxsf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.fdyxxwhExportJxsf(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}

	/**
	 * 
	 * @����:�����Ƽ�ʦ����ѧ˼�����鸨��Ա�䱸�������Ի�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:20:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward pbbExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.pbbExportData(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}

	/**
	 * 
	 * @����:�����Ƽ�ʦ����ѧ˼�����鸨��Ա��������������Ի�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-8 ����02:20:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward dbqkbData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DwwhService service = new DwwhService();
		SzdwGeneralForm exporModel = (SzdwGeneralForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("szdw_dwwh.do?method=searchDwwh");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.dbqkbData(exporModel, response.getOutputStream(), user);

		// ============= end ============

		return null;
	}
	
	/**
	 * @����: ˼����������
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-10 ����02:39:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward szdwSz_10352(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute("ids", request.getParameter("ids"));
		return mapping.findForward("szdwSz_10352");
	}
	
	/**
	 * @����: ˼���������ñ���
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-10 ����02:39:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward szdwSzSave_10352(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String sf = request.getParameter("sf"); // �Ƿ�˼������
		DwwhService service = new DwwhService();
		
		boolean flag = service.szdwSzSave(ids, sf);
		String message = flag ? "����ɹ���":"����ʧ�ܣ�";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������ѧ����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-10-12 ����01:59:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportDbStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String lx = request.getParameter("lx");
		String zgh = request.getParameter("zgh");
		User user = getUser(request);
		List<HashMap<String, String>> resultList = new DwwhService().exportDbStu(zgh, lx);
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = new ExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
