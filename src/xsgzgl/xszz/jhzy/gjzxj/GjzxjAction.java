package xsgzgl.xszz.jhzy.gjzxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xszz.XszzTyForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm;
import xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService;
import xsgzgl.xszz.jhzy.knsrd.KnsrdService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ������_������ѧ��_��ְҵ_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class GjzxjAction extends BasicAction {

	// ===============������D begin=====================

	/**
	 * ������ѧ�����롾��ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqSearch(rForm, myForm, user, request);
		// =================== end ===================

		//===============����������===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjzxjsqb");
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqSearch");
	}

	/**
	 * ������ѧ�����롾���ӡ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqInsert(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqInsert");
	}

	/**
	 * ������ѧ�����롾�޸ġ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSqUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjsqUpdate");
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
			return zxjshSearch(mapping, form, request, response);
		}
	}

	/**
	 * ������ѧ����ˡ���ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initShSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjshSearch");
	}

	/**
	 * ������ѧ����ˡ��޸ġ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initShUpdate(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjshUpdate");
	}

	/**
	 * ������ѧ��������ѯ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		request.setAttribute("searchTj", searchModel);
		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initJgSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		//===============����������===================
		request.setAttribute("tableName", "view_xg_xszz_jhzy_gjzxjb");

		return mapping.findForward("zxjjgSearch");
	}

	/**
	 * ������ѧ��������ϸ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initJgDetail(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zxjjgDetail");
	}

	// ===============������D end=====================

	// ===============������ѧ������begin=====================

	/**
	 * ��ѯ����ѧ�������ѧ�������¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

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
		List<HashMap<String, String>> topTr = service.getZxjsqTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getZxjsqList(myForm, model,
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
	 * ���������ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjzxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ִ�б������ ============
		boolean flag = service.saveGjzxjSq(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ɾ��������ѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteGjzxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ==================ִ��ɾ������====================

		boolean flag = service.deleteGjzxjSq(model, user);

		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================ִ��ɾ������ end================

		return null;
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

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjInit init = new GjzxjInit();
		GjzxjModel model = new GjzxjModel();

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

	// ===============������ѧ������end=====================

	// ===============������ѧ�����begin=====================

	/**
	 * ��ѯ����ѧ��Ĺ�����ѧ����˼�¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

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
		List<HashMap<String, String>> topTr = service.getZxjshTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getZxjshList(myForm, model,
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
	 * ���������ѧ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGjzxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= ����������Ŀ ============
		boolean flag = service.saveGjzxjSh(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============������ѧ�����end=====================

	// ===============������ѧ����begin=====================

	/**
	 * ��ѯ����ѧ��Ĺ�����ѧ������¼
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZxjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GjzxjForm myForm = (GjzxjForm) form;
		GjzxjService service = new GjzxjService();
		GjzxjModel model = new GjzxjModel();
		GjzxjInit init = new GjzxjInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initJgSearch(rForm, myForm, user, request);

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
		List<HashMap<String, String>> topTr = service.getZxjjgTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service.getZxjjgList(myForm, model,
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
	// ===============������ѧ����end=====================
	
	/**
	 * ��ͥ���������Ϣ��ӡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		KnsrdService knsService = new KnsrdService();
		GjzxjService service = new GjzxjService();
		XszzJtqkdzService jtqkdcSerivce = new XszzJtqkdzService();
		
		GjzxjForm myForm = (GjzxjForm) form;
		XszzJtqkdzActionForm jtqkdcModel=new XszzJtqkdzActionForm();
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user����
		User user=getUser(request);
		// �û���
 		String xh=request.getParameter("xh");
 		String xn=request.getParameter("xn");
 		jtqkdcModel.setPkStr(xh+xn);
 		jtqkdcModel.setXh(xh);
 		model.setXh(xh);
 		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		List<HashMap<String, String>> jtcyList = jtqkdcSerivce.cxJtcyxxList(jtqkdcModel);
		//ѧ��������Ϣ
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		HashMap<String, String> jtqkdcInfo = jtqkdcSerivce.ckJtqkdzxx(jtqkdcModel);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		HashMap<String,String>knsInfo=knsService.getKnsrdInfo(xh,xn);
		xsxxInfo.putAll(knsInfo);
		// ������ѧ��
		xsxxInfo.putAll(service.getGjzxjInfo(xh, xn));

		xsxxInfo.putAll(jhzyPrint.getSfzhArr(xsxxInfo.get("sfzh")));
		
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		return mapping.findForward("gjzxjb");
	}
}
