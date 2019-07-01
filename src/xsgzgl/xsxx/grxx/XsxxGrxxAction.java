package xsgzgl.xsxx.grxx;

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
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.cssz.grxx.XsxxCsszService;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdxgModel;

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

public class XsxxGrxxAction extends BasicAction {
	
	/**
	 * ѧ����Ϣ_������Ϣ_�޸�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();
		  
		// ============= ��ʼ����������ֵ ============
		myForm.setCsszModel(csszModel);
		
		// ��ʼ��
		init.initGrxxSq(rForm, myForm, user, request);
		HashMap<String, String> rs = null;

		if (!Base.isNull(myForm.getXh())) {
			rs = service.getXsxxInfo(myForm, user);
		}
		// =================== end ===================

		// ==================��½�û���� ==================
		String msg = service.checkSqzg(csszModel);
		String sqjg = myForm.getSqjg();

		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "������ѧ����������");
			return new ActionForward("/yhInfo.do", false);
		} 
		
		if (!"�˻�".equalsIgnoreCase(sqjg) && !Base.isNull(msg)) {
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		
		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editSq");
	}
	
	/**
	 * ѧ����Ϣ_������Ϣ_�޸�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ==================��½�û���� ==================
		String msg = service.checkShzg(csszModel);
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "ѧ���û����ܽ�����ˣ���ȷ��");
			return new ActionForward("/yhInfo.do", false);
		} else if (!Base.isNull(msg)) {
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		  
		// ============= ��ʼ����������ֵ ============
		myForm.setCsszModel(csszModel);
		// ��ʼ��
		init.initGrxxSh(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editSh");
	}

	/**
	 * ѧ����Ϣ_������Ϣ_�޸������ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= ��ʼ����������ֵ ============
		myForm.setCsszModel(csszModel);

		// ��ʼ��
		init.initGrxxShDetail(rForm, myForm, user, request);
		HashMap<String, String> rs = service.getXgxxInfo(myForm, user);
		// =================== end ===================

		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("editDetail");
	}
	
	/**
	 * ѧ����Ϣ_������Ϣ_�޸Ľ��
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

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		  
		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initGrxxJg(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxManage");
	}
	
	/**
	 * ѧ����Ϣ_������Ϣ_�޸Ľ����ϸ
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

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxInit init = new XsxxGrxxInit();
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= ��ʼ����������ֵ ============
		myForm.setCsszModel(csszModel);

		String stylePath = request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);

		// ��ʼ��
		init.initGrxxShDetail(rForm, myForm, user, request);
		HashMap<String, String> rs = service.getXgxxInfo(myForm, user);
		List<HashMap<String, String>> shrList = service.getShrList(myForm,
				rsModel);
		// =================== end ===================

		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		
		if(shrList!=null && shrList.size()>0){
			rForm.setRsList(shrList);
		}
		
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxDetail");
	}
	
	// =================���·���=======================
	
	/**
	 * ��ʾ�ֶ��޸�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxgDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// �û�����
		String zd = request.getParameter("zd");
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.showZdxgDiv(zd, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * �����ֶ��޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// �û�����
		ZdxgModel zdxgModel = new ZdxgModel();
		
		// ============= ��ʼ����������ֵ ============
		String xh = request.getParameter("xh");// 'ѧ��';
		zdxgModel.setXh(xh);

		String sqid = request.getParameter("sqid");// '����ID';
		zdxgModel.setSqid(sqid);

		String xgzd = request.getParameter("xgzd");// '�޸��ֶ�';
		zdxgModel.setXgzd(xgzd);

		String xgz = request.getParameter("xgz");// '�޸�ֵ';
		xgz = Base.isNull(xgz) ? xgz : service.unicode2Gbk(xgz);
		zdxgModel.setXgz(xgz);
		
		myForm.setZdxgModel(zdxgModel);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.saveZdxg(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������ڲ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSzbm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		String bjdm = request.getParameter("bjdm");// 'ѧ��';
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		String szbm = service.getSzbm(bjdm);
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(szbm);

		return null;
	}
	
	/**
	 * ���ʡ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSsx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		String ssx = request.getParameter("ssx");// 'ѧ��';
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		String szbm = service.getSsx(ssx);
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(szbm);

		return null;
	}
	
	/**
	 * �����ֶ��޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= ��ʼ����������ֵ ============
		String xh = request.getParameter("xh");// 'ѧ��';
		myForm.setXh(xh);
		
		String sqid = request.getParameter("sqid");// '����ID';
		myForm.setSqid(sqid);
		
		myForm.setCsszModel(csszModel);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		String message = service.saveXgsq(myForm, user);
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �ύ������Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitGrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();

		// ============= ��ʼ����������ֵ ============
		String xh = request.getParameter("xh");// 'ѧ��';
		myForm.setXh(xh);

		myForm.setCsszModel(csszModel);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.submitGrxx(myForm);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ���������
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

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= ��ʼ����������ֵ ============
		List<HashMap<String, String>> shInfoList = null;
			
		//ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		//����id
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		
		//�Ƿ����
		String sfsh = csszModel.getSfsh();
		
		myForm.setCsszModel(csszModel);
		// ============= ��ʼ����������ֵ end============

		// ============= ��ѯĿǰ�������� ============
		if ("��".equalsIgnoreCase(sfsh)) {
			shInfoList = service.getShInfoList(myForm);
		}
		// ============= ��ѯĿǰ�������� end============
		
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(shInfoList));
		
		return null;
	}
	
	/**
	 * ����޸����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXgInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		
		// ============= ��ʼ����������ֵ ============	
		//ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		//����id
		String sqid = request.getParameter("sqid");
		myForm.setSqid(sqid);
		// ============= ��ʼ����������ֵ end============

		// ============= ��ѯĿǰ���ֶ��޸� ============
		List<HashMap<String, String>> xgInfoList = service
				.getXgInfoList(myForm);
		// ============= ��ѯĿǰ���ֶ��޸� end============
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(xgInfoList));
		
		return null;
	}
	
	/**
	 * ����޸�����б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXgshList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		XsxxGrxxInit init = new XsxxGrxxInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		CsszModel csszModel = csszService.getGrxxCssz();
		
		// ============= ��ʼ����������ֵ ============
		// ����
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// ��ʽ��ַ
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// ��˸�λ
		String shgw = otherValue[2];
		myForm.setShgw(shgw);
		
		myForm.setCsszModel(csszModel);
		
		// ��ʼ��
		init.initGrxxSh(rForm, myForm, user, request);
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
		ArrayList<String[]> rsArrList = service.getXgshList(myForm, user);
		String spHtml = service.getXgshHtml(rsModel, myForm, rsArrList, user);
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

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxCsszService csszService = new XsxxCsszService();
		CsszModel csszModel = csszService.getGrxxCssz();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		String xh = request.getParameter("xh");// 'ѧ��';
		myForm.setXh(xh);
		
		String sqid = request.getParameter("sqid");// '����ID';
		myForm.setSqid(sqid);

		String shgw = request.getParameter("shgw");// '��˸�λ';
		myForm.setShgw(shgw);

		String shzt = request.getParameter("shzt");// '���״̬';
		shzt = Base.isNull(shzt) ? shzt : service.unicode2Gbk(shzt);
		myForm.setShzt(shzt);
		
		String shyj = request.getParameter("shyj");// '������';
		shyj = Base.isNull(shyj) ? shyj : service.unicode2Gbk(shyj);
		myForm.setShyj(shyj);
		
		myForm.setCsszModel(csszModel);
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.saveShzt(myForm, user);
		String message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ����޸Ľ���б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJgcxList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGrxxForm myForm = (XsxxGrxxForm) form;
		XsxxGrxxService service = new XsxxGrxxService();
		XsxxGrxxInit init = new XsxxGrxxInit();
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

		// ��ʽ��ַ
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// ��ʼ��
		init.initGrxxJg(rForm, myForm, user, request);
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
		ArrayList<String[]> rsArrList = service.getJgcxList(myForm, user);
		String spHtml = service.getJgcxHtml(rsModel, myForm, rsArrList, user);
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
}