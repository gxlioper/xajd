package xsgzgl.pjpy.szgyyq.mypj.stu;

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
import xgxt.comm.FileManage;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.mypj.PjpyMypjForm;
import xgxt.pjpy.comm.pjpy.mypj.PjpyMypjService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;

import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjInit;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.IvtltService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ShsjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.WthdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.YybdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZznlService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_Action��
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

public class PjpyStuAction extends BasicAction {

	/**
	 * ��������_���ݹ�ҵ԰��_��Ŀ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		XsxxglService stuService = new XsxxglService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMypj(rForm, myForm, request);

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getFssqXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		String xh = user.getUserName();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		request.setAttribute("stuInfo", stuInfo);
		// =================== end ===================

		// ============= ����request��ֵ =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("fssqManage");
	}

	// =====================����===================================

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDshdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// �û�����
		DshdModel dshdModel = new DshdModel();

		// ��������
		String[] dsmc = request.getParameter("dsmc").split("!!@@!!");
		dshdModel.setDsmc(dsmc);

		// ��������
		String[] dsrq = request.getParameter("dsrq").split("!!@@!!");
		dshdModel.setDsrq(dsrq);

		// �����ĵ�
		String[] dsxd = request.getParameter("dsxd").split("!!@@!!");
		dshdModel.setDsxd(dsxd);

		// �Ƿ��
		String[] sfhj = request.getParameter("sfhj").split("!!@@!!");
		dshdModel.setSfhj(sfhj);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		dshdModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.saveDshdSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ�����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delDshdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// �û�����
		DshdModel dshdModel = new DshdModel();

		// ��������
		String dsmc = request.getParameter("dsmc");
		dshdModel.setDsmc(new String[] { dsmc });

		// ��������
		String dsrq = request.getParameter("dsrq");
		dshdModel.setDsrq(new String[] { dsrq });

		// ѧ��
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================ִ��ɾ������========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.delDshdSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * ��ö�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDshdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getDshdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editDshdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		DshdService service = new DshdService();
		User user = getUser(request);// �û�����
		DshdModel dshdModel = new DshdModel();

		// ����ID
		String id = request.getParameter("sqid");
		dshdModel.setId(id);

		// ��������
		String dsmc = request.getParameter("dsmc");
		dshdModel.setDsmc(new String[] { service.unicode2Gbk(dsmc) });

		// ��������
		String dsrq = request.getParameter("dsrq");
		dshdModel.setDsrq(new String[] { dsrq });

		// �����ĵ�
		String dsxd = request.getParameter("dsxd");
		dshdModel.setDsxd(new String[] { service.unicode2Gbk(dsxd) });

		// �Ƿ��
		String sfhj = request.getParameter("sfhj");
		dshdModel.setSfhj(new String[] { service.unicode2Gbk(sfhj) });

		// �����
		String sqf = request.getParameter("sqf");
		dshdModel.setSqf(new String[] { sqf });

		// ѧ��
		String xn = Base.currXn;
		dshdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		dshdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		dshdModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setDshdModel(dshdModel);
		boolean flag = service.editDshdInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================���� end===================================

	// =====================���Ա��===================================

	/**
	 * �������Ա�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveYybdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// �û�����
		YybdModel yybdModel = new YybdModel();

		// ���Ա������
		String[] yybdnr = request.getParameter("yybdnr").split("!!@@!!");
		yybdModel.setYybdnr(yybdnr);

		// ����
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		yybdModel.setXthdrq(xthdrq);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		yybdModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.saveYybdSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ�����Ա�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delYybdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// �û�����
		YybdModel yybdModel = new YybdModel();

		// ���Ա������
		String yybdnr = request.getParameter("yybdnr");
		yybdModel.setYybdnr(new String[] { yybdnr });

		// ����
		String xthdrq = request.getParameter("xthdrq");
		yybdModel.setXthdrq(new String[] { xthdrq });

		// ѧ��
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================ִ��ɾ������========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.delYybdSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * ��ö�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYybdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getYybdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * �������Ա�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editYybdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		YybdService service = new YybdService();
		User user = getUser(request);// �û�����
		YybdModel yybdModel = new YybdModel();

		// ����ID
		String id = request.getParameter("sqid");
		yybdModel.setId(id);

		// ���Ա������
		String yybdnr = request.getParameter("yybdnr");
		yybdModel.setYybdnr(new String[] { service.unicode2Gbk(yybdnr) });

		// ��������
		String xthdrq = request.getParameter("xthdrq");
		yybdModel.setXthdrq(new String[] { xthdrq });

		// �����
		String sqf = request.getParameter("sqf");
		yybdModel.setSqf(new String[] { sqf });

		// ѧ��
		String xn = Base.currXn;
		yybdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		yybdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		yybdModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setYybdModel(yybdModel);
		boolean flag = service.editYybdInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================���Ա�� end ===================================

	// =====================Ivt��̳===================================

	/**
	 * ����Ivt��̳�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveIvtltSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// �û�����
		IvtltModel ivtltModel = new IvtltModel();

		// ������Ŀ
		String[] jztm = request.getParameter("jztm").split("!!@@!!");
		ivtltModel.setJztm(jztm);

		// ����
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		ivtltModel.setXthdrq(xthdrq);

		// �����Ǽ�
		String[] jcdj = request.getParameter("jcdj").split("!!@@!!");
		ivtltModel.setJcdj(jcdj);

		// �����Ǽ�
		String[] ccdj = request.getParameter("ccdj").split("!!@@!!");
		ivtltModel.setCcdj(ccdj);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		ivtltModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		ivtltModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		ivtltModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		ivtltModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.saveIvtltSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ��Ivt��̳�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delIvtltSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// �û�����
		IvtltModel ivtltModel = new IvtltModel();

		// ������Ŀ
		String jztm = request.getParameter("jztm");
		ivtltModel.setJztm(new String[] { jztm });

		// ����
		String xthdrq = request.getParameter("xthdrq");
		ivtltModel.setXthdrq(new String[] { xthdrq });

		// ѧ��
		String xn = Base.currXn;
		ivtltModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		ivtltModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		ivtltModel.setXh(xh);

		// ==================ִ��ɾ������========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.delIvtltSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * ���Ivt��̳��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getIvtltInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getIvtltInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * ����Ivt��̳�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editIvtltInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		IvtltService service = new IvtltService();
		User user = getUser(request);// �û�����
		IvtltModel ivtltModel = new IvtltModel();

		// ����ID
		String id = request.getParameter("sqid");
		ivtltModel.setId(id);

		// ������Ŀ
		String jztm = request.getParameter("jztm");
		ivtltModel.setJztm(new String[] { service.unicode2Gbk(jztm) });

		// ����
		String xthdrq = request.getParameter("xthdrq");
		ivtltModel.setXthdrq(new String[] { xthdrq });

		// �����Ǽ�
		String jcdj = request.getParameter("jcdj");
		if (jcdj != null) {
			ivtltModel.setJcdj(new String[] { service.unicode2Gbk(jcdj) });
		}

		// �����Ǽ�
		String ccdj = request.getParameter("ccdj");
		if (ccdj != null) {
			ivtltModel.setCcdj(new String[] { service.unicode2Gbk(ccdj) });
		}

		// �����
		String sqf = request.getParameter("sqf");
		ivtltModel.setSqf(new String[] { sqf });

		// ==================ִ�б������========================
		myForm.setIvtltModel(ivtltModel);
		boolean flag = service.editIvtltInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================Ivt��̳ end===================================

	// =====================����===================================

	/**
	 * �������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveWthdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// �û�����
		WthdModel wthdModel = new WthdModel();

		// �����
		String[] hdnr = request.getParameter("hdnr").split("!!@@!!");
		wthdModel.setHdnr(hdnr);

		// ����
		String[] xthdrq = request.getParameter("xthdrq").split("!!@@!!");
		wthdModel.setXthdrq(xthdrq);

		// �����ȼ�
		String[] jldj = request.getParameter("jldj").split("!!@@!!");
		wthdModel.setJldj(jldj);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		wthdModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		wthdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		wthdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		wthdModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.saveWthdSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ�����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delWthdSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// �û�����
		WthdModel wthdModel = new WthdModel();

		// ���Ա������
		String hdnr = request.getParameter("hdnr");
		wthdModel.setHdnr(new String[] { hdnr });

		// ����
		String xthdrq = request.getParameter("xthdrq");
		wthdModel.setXthdrq(new String[] { xthdrq });

		// ѧ��
		String xn = Base.currXn;
		wthdModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		wthdModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		wthdModel.setXh(xh);

		// ==================ִ��ɾ������========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.delWthdSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWthdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getWthdInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * �������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editWthdInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		WthdService service = new WthdService();
		User user = getUser(request);// �û�����
		WthdModel wthdModel = new WthdModel();

		// ����ID
		String id = request.getParameter("sqid");
		wthdModel.setId(id);

		// ������Ŀ
		String hdnr = request.getParameter("hdnr");
		wthdModel.setHdnr(new String[] { service.unicode2Gbk(hdnr) });

		// ����
		String xthdrq = request.getParameter("xthdrq");
		wthdModel.setXthdrq(new String[] { xthdrq });

		// �����Ǽ�
		String jldj = request.getParameter("jldj");
		if(jldj != null){
			wthdModel.setJldj(new String[] { service.unicode2Gbk(jldj) });
		}

		// �����
		String sqf = request.getParameter("sqf");
		wthdModel.setSqf(new String[] { sqf });

		// ==================ִ�б������========================
		myForm.setWthdModel(wthdModel);
		boolean flag = service.editWthdInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================���� end ===================================

	// =====================��֯����===================================

	/**
	 * ������֯���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZznlSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// �û�����
		ZznlModel zznlModel = new ZznlModel();

		// �����
		String[] hdzt = request.getParameter("hdzt").split("!!@@!!");
		zznlModel.setHdzt(hdzt);

		// �����
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		zznlModel.setHdrq(hdrq);

		// ��ȼ�
		String[] hddj = request.getParameter("hddj").split("!!@@!!");
		zznlModel.setHddj(hddj);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		zznlModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		zznlModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		zznlModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		zznlModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.saveZznlSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ����֯���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delZznlSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// �û�����
		ZznlModel zznlModel = new ZznlModel();

		// �����
		String[] hdzt = request.getParameter("hdzt").split("!!@@!!");
		zznlModel.setHdzt(hdzt);

		// �����
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		zznlModel.setHdrq(hdrq);

		// ѧ��
		String xn = Base.currXn;
		zznlModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		zznlModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		zznlModel.setXh(xh);
		// ==================ִ��ɾ������========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.delZznlSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * �����֯������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZznlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getZznlInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * ������֯���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editZznlInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ZznlService service = new ZznlService();
		User user = getUser(request);// �û�����
		ZznlModel zznlModel = new ZznlModel();

		// ����ID
		String id = request.getParameter("sqid");
		zznlModel.setId(id);

		// �����
		String hdzt = request.getParameter("hdzt");
		zznlModel.setHdzt(new String[] { service.unicode2Gbk(hdzt) });

		// ����
		String hdrq = request.getParameter("hdrq");
		zznlModel.setHdrq(new String[] { hdrq });

		// ��ȼ�
		String hddj = request.getParameter("hddj");
		if(hddj != null){
			zznlModel.setHddj(new String[] { service.unicode2Gbk(hddj) });
		}

		// �����
		String sqf = request.getParameter("sqf");
		zznlModel.setSqf(new String[] { sqf });

		// ==================ִ�б������========================
		myForm.setZznlModel(zznlModel);
		boolean flag = service.editZznlInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================��֯���� end ===================================

	// =====================���ʵ��===================================

	/**
	 * �������ʵ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShsjSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// �û�����
		ShsjModel shsjModel = new ShsjModel();

		// ��ص�
		String[] hddd = request.getParameter("hddd").split("!!@@!!");
		shsjModel.setHddd(hddd);

		// �����
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		shsjModel.setHdrq(hdrq);

		// �����
		String[] hdnr = request.getParameter("hdnr").split("!!@@!!");
		shsjModel.setHdnr(hdnr);

		// �ʱ��
		String[] hdsj = request.getParameter("hdsj").split("!!@@!!");
		shsjModel.setHdsj(hdsj);

		// �����
		String[] sqf = request.getParameter("sqf").split("!!@@!!");
		shsjModel.setSqf(sqf);

		// ѧ��
		String xn = Base.currXn;
		shsjModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		shsjModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		shsjModel.setXh(xh);

		// ==================ִ�б������========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.saveShsjSqf(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ�����ʵ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delShsjSqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// �û�����
		ShsjModel shsjModel = new ShsjModel();

		// ��ص�
		String[] hddd = request.getParameter("hddd").split("!!@@!!");
		shsjModel.setHddd(hddd);

		// �����
		String[] hdrq = request.getParameter("hdrq").split("!!@@!!");
		shsjModel.setHdrq(hdrq);

		// ѧ��
		String xn = Base.currXn;
		shsjModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		shsjModel.setXq(xq);

		// ѧ��
		String xh = user.getUserName();
		shsjModel.setXh(xh);

		// ==================ִ��ɾ������========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.delShsjSqf(myForm, user);
		// ==================ִ��ɾ������ end========================

		return null;
	}

	/**
	 * ������ʵ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShsjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		List<HashMap<String, String>> list = service.getShsjInfo(myForm, user);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * �������ʵ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editShsjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		ShsjService service = new ShsjService();
		User user = getUser(request);// �û�����
		ShsjModel shsjModel = new ShsjModel();

		// ����ID
		String id = request.getParameter("sqid");
		shsjModel.setId(id);

		// ��ص�
		String hddd = request.getParameter("hddd");
		shsjModel.setHddd(new String[] { service.unicode2Gbk(hddd) });

		// �����
		String hdrq = request.getParameter("hdrq");
		shsjModel.setHdrq(new String[] { hdrq });

		// �����
		String hdnr = request.getParameter("hdnr");
		shsjModel.setHdnr(new String[] { service.unicode2Gbk(hdnr) });

		// �ʱ��
		String hdsj = request.getParameter("hdsj");
		if(hdsj != null){
			shsjModel.setHdsj(new String[] { hdsj });
		}

		// �����
		String sqf = request.getParameter("sqf");
		shsjModel.setSqf(new String[] { sqf });

		// ==================ִ�б������========================
		myForm.setShsjModel(shsjModel);
		boolean flag = service.editShsjInfo(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// =====================���ʵ�� end===================================

	/**
	 * ���������Ŀ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSqxmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// �û�����

		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ѧ��
		String xh = user.getUserName();
		myForm.setXh(xh);

		String sqxnIbfo = service.getSqxmInfo(myForm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sqxnIbfo);

		return null;
	}

	/**
	 * ��������_���ݹ�ҵ԰��_��Ŀ����������ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sqxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqxxDetail(rForm, myForm, request);

		// ������Ϣ
		List<HashMap<String, String>> infoList = service.getSqxxList(myForm);
		request.setAttribute("infoList", infoList);

		String yhlx = myForm.getYhlx();
		if ("bz".equalsIgnoreCase(yhlx) || "stu".equalsIgnoreCase(yhlx)) {
			request.setAttribute("canCz", "yes");
		}
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("sqxxDetail");
	}

	/**
	 * ����ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// �û�����
		XsssModel xsssModel = new XsssModel();

		// ��Ŀ����
		String xmlx = request.getParameter("xmlx");
		xsssModel.setXmlx(xmlx);

		// ��ĿID
		String xmid = request.getParameter("xmid");
		xsssModel.setXmid(xmid);

		// ѧ��
		String xh = request.getParameter("xh");
		xsssModel.setXh(xh);

		// ��������
		String ssnr = service.unicode2Gbk(request.getParameter("ssnr"));
		xsssModel.setSsnr(ssnr);

		// ѧ��
		String xn = Base.currXn;
		xsssModel.setXn(xn);

		// ѧ��
		String xq = Base.currXq;
		xsssModel.setXq(xq);

		// ==================ִ�б������========================
		myForm.setXsssModel(xsssModel);
		boolean flag = service.saveXsss(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
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

		PjpyStuForm myForm = (PjpyStuForm) form;
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

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

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
		String xh = user.getUserName();
		myForm.setXh(xh);

		// ѧ��
		String xn = otherValue[1];

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!")[0];
		} else {
			xn = Base.currXn;
		}

		// ѧ��
		String xq = otherValue[2];

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!")[0];
		} else {
			xq = Base.currXq;
		}

		// ����
		String[] cz = null;
		if (!Base.isNull(otherValue[3].trim())) {
			cz = otherValue[3].split("!!##!!");
		}

		// ��ѯ����
		String mhcx_lx = otherValue[4];

		// ģ����ѯ
		String input_mhcx = service.unicode2Gbk(otherValue[5]);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xn(new String[] { xn });
		searchModel.setSearch_tj_xq(new String[] { xq });
		searchModel.setSearch_tj_cz(cz);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
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
	 * ����ѧ��Ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
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
		String xh = user.getUserName();
		xstsModel.setXh(xh);

		// ��Ͷ����
		String btsr = request.getParameter("btsr");
		xstsModel.setBtsr(btsr);

		// Ͷ������
		String tsnr = service.unicode2Gbk(request.getParameter("tsnr"));
		xstsModel.setTsnr(tsnr);

		// ==================ִ�б������========================
		myForm.setXstsModel(xstsModel);
		boolean flag = service.saveXsts(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��������_���ݹ�ҵ԰��_�ҵ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myssManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMySs(rForm, myForm, request);

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
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

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("myssManage");
	}

	/**
	 * ����ҵ����߲�ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyssInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMySs(rForm, myForm, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��Ŀ����
		String[] xmdm = null;
		if (!Base.isNull(otherValue[0].trim())) {
			xmdm = otherValue[0].split("!!##!!");
		}

		// ѧ��
		String xh = user.getUserName();
		myForm.setXh(xh);

		// ѧ��
		String[] xn = null;

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// ѧ��
		String[] xq = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// ����
		String[] lx = null;
		if (!Base.isNull(otherValue[3].trim())) {
			lx = otherValue[3].split("!!##!!");
		}

		// ��ѯ����
		String mhcx_lx = otherValue[4];

		// ģ����ѯ
		String input_mhcx = service.unicode2Gbk(otherValue[5]);

		// IE�汾
		// String ie = otherValue[1];
		// rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xmdm(xmdm);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		searchModel.setSearch_tj_lx(lx);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getMyssList(myForm, user);
		String spHtml = service.getMyssHtml(rsModel, myForm, rsArrList, user);
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
	 * ��������_���ݹ�ҵ԰��_�ҵ�Ͷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mytsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		SearchModel searchModel = myForm.getSearchModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMyTs(rForm, myForm, request);

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getJgcxXmList(myForm);
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

		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", xqmc);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward("mytsManage");
	}

	/**
	 * ����ҵ�Ͷ�߲�ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMytsInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		PjpyMypjInit init = new PjpyMypjInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initMyTs(rForm, myForm, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ��Ŀ����
		String[] xmdm = null;
		if (!Base.isNull(otherValue[0].trim())) {
			xmdm = otherValue[0].split("!!##!!");
		}

		// ѧ��
		String xh = user.getUserName();
		myForm.setXh(xh);

		// ѧ��
		String[] xn = null;

		if (!Base.isNull(otherValue[1].trim())) {
			xn = otherValue[1].split("!!##!!");
		} else {
			xn = new String[] { Base.currXn };
		}

		// ѧ��
		String[] xq = null;

		if (!Base.isNull(otherValue[2].trim())) {
			xq = otherValue[2].split("!!##!!");
		} else {
			xq = new String[] { Base.currXq };
		}

		// ����
		String[] lx = null;
		if (!Base.isNull(otherValue[3].trim())) {
			lx = otherValue[3].split("!!##!!");
		}

		// ��ѯ����
		String mhcx_lx = otherValue[4];

		// ģ����ѯ
		String input_mhcx = service.unicode2Gbk(otherValue[5]);

		// IE�汾
		// String ie = otherValue[1];
		// rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setSearch_tj_xmdm(xmdm);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		searchModel.setSearch_tj_lx(lx);
		searchModel.setMhcx_lx(mhcx_lx);
		searchModel.setInput_mhcx(input_mhcx);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getMytsList(myForm, user);
		String spHtml = service.getMytsHtml(rsModel, myForm, rsArrList, user);
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
	 * ����ɷ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkKfcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// ��ĿID
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ==================ִ�б������========================
		boolean flag = service.checkKfcl(myForm);
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(flag);

		return null;
	}

	/**
	 * ɾ�������¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyStuForm myForm = (PjpyStuForm) form;
		PjpyStuService service = new PjpyStuService();
		User user = getUser(request);// �û�����

		// ����ID
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);

		// ��ĿID
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ==================ִ�б������========================
		boolean flag = service.deleteSqjl(myForm, user);
		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
