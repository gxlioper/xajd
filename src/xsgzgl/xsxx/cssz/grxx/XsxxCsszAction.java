package xsgzgl.xsxx.cssz.grxx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.cssz.XsxxCsszInit;
import xsgzgl.xsxx.cssz.XsxxCsszInterface;
import xsgzgl.xsxx.model.ZdqxModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_������Ϣ_Action��
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

public class XsxxCsszAction extends BasicAction {

	// =================����������D=======================

	/**
	 * ѧ����Ϣ_��������_������Ϣ_ѧ���޸��ֶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszService service = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initGrxx(rForm, myForm, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("xszdManage");
	}

	/**
	 * ѧ����Ϣ_��������_������Ϣ_��ʦ�޸��ֶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lszdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszInterface service = init.getCsszService("grxx");// ������Ϣ
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initGrxx(rForm, myForm, request);
		HashMap<String, String> rs = service.getCsszInfo(myForm);
		// =================== end ===================

		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("lszdManage");
	}

	// =================���·���=======================

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
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszInterface service = init.getCsszService("grxx");// ������Ϣ
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String sfsh = request.getParameter("sfsh");// '�Ƿ����';
		myForm.setSfsh(sfsh);

		String lcid = request.getParameter("lcid");// '����ID';
		myForm.setLcid(lcid);

		String sqkssj = request.getParameter("sqkssj");// '���뿪ʼʱ��';
		myForm.setSqkssj(sqkssj);

		String sqjssj = request.getParameter("sqjssj");// '�������ʱ��';
		myForm.setSqjssj(sqjssj);

		String shkssj = request.getParameter("shkssj");// '��˿�ʼʱ��';
		myForm.setShkssj(shkssj);

		String shjssj = request.getParameter("shjssj");// '��˽���ʱ��';
		myForm.setShjssj(shjssj);
		
		String over = "no";// '���ý���';
		myForm.setOver(over);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.saveCssz(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszService service = new XsxxCsszService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// �û�����
		String yhlx = request.getParameter("yhlx");
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.showZdxzDiv(yhlx, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����ֶ�Ȩ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszService service = new XsxxCsszService();
		User user = getUser(request);// �û�����
		ZdqxModel zdqxModel = new ZdqxModel();

		// ============= ��ʼ����������ֵ ============
		String[] zd = request.getParameter("zd").split("!!@@!!");// '�ֶ�';
		zdqxModel.setZd(zd);
		
		String yhlx = request.getParameter("yhlx");//�û�����
		myForm.setYhlx(yhlx);
		
		String over = request.getParameter("over");//�û�����
		myForm.setOver(over);
		
		myForm.setZdqxModel(zdqxModel);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.saveZdqx(myForm, user);
		String message = flag ? "���óɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
