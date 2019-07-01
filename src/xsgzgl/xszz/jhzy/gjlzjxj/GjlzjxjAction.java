package xsgzgl.xszz.jhzy.gjlzjxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;
import xsgzgl.xszz.jhzy.gjzxj.GjzxjForm;
import xsgzgl.xszz.jhzy.gjzxj.GjzxjService;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService;
import xsgzgl.xszz.jhzy.knsrd.KnsrdService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ������_������־��ѧ��_��ְҵ_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjAction extends BasicAction {

	// ===============������D begin=====================

	/**
	 * ������־��ѧ�����롾��ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqSearch(rForm, myForm, user, request);
		// =================== end ===================
		
		//===============����������===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjlzjxjsqb");
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		request.setAttribute("czqx", csszForm.getGjlzjxjsqzt());
		return mapping.findForward("cxGjlzjxjsq");
	}
	
	/**
	 * ��ѯ����ѧ��������������¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSqSearch(rForm, myForm, user, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
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
		// ����
		List<HashMap<String, String>> topTr = service.getGjlzjxjTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getGjlzjxjsqList(myForm, model,
				user);
		// ���������
		String spHtml = "";
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
	 * ɾ��������־��ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteGjlzjxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ==================����ǰ̨ҳ��====================

		boolean flag = service.deleteGjlzjxjSq(model, user);

		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ������־��ѧ�����롾���ӡ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		myForm.setXn(XszzCsszActionForm.getCsszForm().getXn());
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqInsert(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			boolean flag = service.saveGjlzjxjSq(model, user);
			String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
			request.setAttribute("message", message);
		}
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		if (StringUtils.isNotNull( myForm.getXh())) {
			request.setAttribute("bjzrs", service.cxBjrs(myForm.getXh()));
			XszzCsszActionForm cform = new XszzCsszActionForm();
			request.setAttribute("isKns", cform.getIsKns(myForm.getXh(), myForm.getXn()));
		}
		
		
		request.setAttribute("zzxn", XszzCsszActionForm.getCsszForm().getXn());
		return mapping.findForward("gjlzjxjsqInsert");
	}

	/**
	 * ������־��ѧ�����롾�޸ġ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			boolean flag = service.saveGjlzjxjSq(model, user);
			String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
			request.setAttribute("message", message);
		}
		if (StringUtils.isNotNull( rForm.getRs().get("xh"))) {
			request.setAttribute("bjzrs", service.cxBjrs(rForm.getRs().get("xh")));
			XszzCsszActionForm cform = new XszzCsszActionForm();
			request.setAttribute("isKns", cform.getIsKns(rForm.getRs().get("xh"), rForm.getRs().get("xn")));
		}
		request.setAttribute("pkValue", request.getParameter("pkValue"));
		return mapping.findForward("gjlzjxjsqUpdate");
	}

	/**
	 * �û�Ȩ��ȷ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhqxDecision(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);// �û�����

		if ("jd".equalsIgnoreCase(user.getUserStatus())) {
			HttpSession session = request.getSession();
			Boolean sfjryx = session.getAttribute("sfjryx") != null ? Boolean
					.valueOf(session.getAttribute("sfjryx").toString()) : false;
			String userType = (String) session.getAttribute("userType");
			boolean isFdy = (Boolean) session.getAttribute("isFdy");
			boolean isBzr = (Boolean) session.getAttribute("isBzr");

			request.setAttribute("userType", userType);
			request.setAttribute("isFdy", isFdy);
			request.setAttribute("isBzr", isBzr);
			return mapping.findForward("yhqxDecision");
		} else {
			return cxGjlzjxjsh(mapping, form, request, response);
		}
	}

	/**
	 * ������־��ѧ����ˡ���ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initShSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("gjlzjxjshSearch");
	}
	
	/**
	 * ������־��ѧ����ˡ��޸ġ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initShUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			BeanUtils.copyProperties(model, myForm);
			model.setPkValue(new String[]{myForm.getPkValue()});
			boolean flag = service.saveGjlzjxjSh(model, user);
			String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
			request.setAttribute("message", message);
		}
		request.setAttribute("pkValue", request.getParameter("pkValue"));
		return mapping.findForward("gjlzjxjshUpdate");
	}

	

	// ===============������D end=====================

	// ===============������־��ѧ������begin=====================

	/**
	 * ��ѯ����ѧ��Ĺ�����־��ѧ����˼�¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initShSearch(rForm, myForm, user, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
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
		// ����
		List<HashMap<String, String>> topTr = service.getGjlzjxjTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getGjlzjxjshList(myForm, model,
				user);
		// ���������
		String spHtml = "";
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
	 * ���������־��ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveGjlzjxjSq(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * �鿴������־��ѧ����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  gjlzjxjsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		
		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, myForm);
		
		return mapping.findForward("gjlzjxjsqView");
	}

	/**
	 * �������̸���HTML
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createLcgzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		GjlzjxjModel model = new GjlzjxjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initSqInsert(rForm, myForm, user, request);
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createLcgzHtml(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	// ===============������־��ѧ������end=====================

	// ===============������־��ѧ�����begin=====================

	/**
	 * ���������־��ѧ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjlzjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveGjlzjxjSh(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============������־��ѧ�����end=====================

	// ===============������־��ѧ����begin=====================

	/**
	 * ��ѯ����ѧ��Ĺ�����־��ѧ����˼�¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchGjlzjxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjModel model = new GjlzjxjModel();
		GjlzjxjInit init = new GjlzjxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initjgSearch(rForm, myForm, user, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
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
		// ����
		List<HashMap<String, String>> topTr = service.getGjlzjxjjgTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getGjlzjxjjgList(myForm, model,
				user);
		// ���������
		String spHtml = "";
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
	 * ������־��ѧ�𡾲�ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxGjlzjxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		request.setAttribute("patn", "xszz_jhzy_gjlzjxjCx.do");
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		request.setAttribute("searchTj", searchModel);
		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initjgSearch(rForm, myForm, user, request);
		// =================== end ===================
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjlzjxjb");

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("cxGjlzjxjjg");
	}

	/**
	 * ������־��ѧ��������ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjlzjxjForm myForm = (GjlzjxjForm) form;
		GjlzjxjService service = new GjlzjxjService();
		GjlzjxjInit init = new GjlzjxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initJgDetail(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("gjlzjxjjgDetail");
	}

	/**
	 * ������־��ѧ���ӡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		KnsrdService knsService = new KnsrdService();
		GjlzjxjService service = new GjlzjxjService();
		XszzJtqkdzService jtqkdcSerivce = new XszzJtqkdzService();
		
		GjlzjxjForm myForm = (GjlzjxjForm) form;
		XszzJtqkdzActionForm jtqkdcModel=new XszzJtqkdzActionForm();
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user����
		User user=getUser(request);
		// �û���
 		String xh=request.getParameter("xh");
 		String xn=request.getParameter("xn");
 		jtqkdcModel.setPkStr(xh+xn);
 		model.setXh(xh);
 		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		List<HashMap<String, String>> jtcyList = jtqkdcSerivce.cxJtcyxxList(jtqkdcModel);
		//ѧ��������Ϣ
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		HashMap<String,String>knsInfo=knsService.getKnsrdInfo(xh,xn);
		xsxxInfo.putAll(knsInfo);
		
		HashMap<String, String> jtqkdcInfo = jtqkdcSerivce.ckJtqkdzxx(jtqkdcModel);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		xsxxInfo.putAll(jhzyPrint.getSfzhArr(xsxxInfo.get("sfzh")));
		
		xsxxInfo.putAll(service.getGjlzjxjInfo(xh, xn));
		
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		return mapping.findForward("gjlzjxjb");
	}
	// ===============������־��ѧ�����end=====================
}
