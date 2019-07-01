package xsgzgl.xszz.jhzy.jtqkdz;

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
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.xszz.XszzTyForm;
import xsgzgl.qgzx.cxtj.QgzxCxtjForm;
import xsgzgl.qgzx.cxtj.QgzxCxtjService;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;
import xsgzgl.xszz.jhzy.XszzJhzyPrint;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description:ѧ������-��ͥ�������
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
public class XszzJtqkdzAction extends BasicAction {

	/**
	 * ��ѯ��ͥ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJtqkdzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		XszzCsszActionForm csszForm = XszzCsszActionForm.getCsszForm();
		searchModel.setSearch_tj_xn(new String[]{csszForm.getXn()});
		searchModel.setSearch_tj_xq(new String[]{Base.currXn});
		searchModel.setPath("xszz_jhzy_jtqkdz.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("xszz_jhzy_jtqkdz.do");
		// ----------------����PATH end-----------------------
		serivce.setRequestValue(rForm, user, request);
		
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_xszz_jhzy_jtqkdzb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_xszz_jhzy_jtqkdzb");
		
		request.setAttribute("czqx", csszForm.getJtqktzzt());
		
		return mapping.findForward("cxJtqkdzxx");
		
	}
	
	/**
	 * ��ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxjtdzqkByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		serivce.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("xszz_jhzy_jtqkdz.do");
		// IE�汾
		String ie = request.getParameter("otherValue") != null ? request.getParameter("otherValue").split("!!@@!!")[0] : "";
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = serivce.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = serivce.getJtqkdzList(model);
		// ���������
		String spHtml = serivce.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		serivce.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scJtqkdz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		model.setPkStr(serivce.unicode2Gbk(request.getParameter("pkValue")));
		String message = serivce.scJtqkdzxx(model) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ���Ӽ�ͥ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjJtqkdz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		
		User user = getUser(request);
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}
		
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> rs = stuService.selectStuinfo(xh);

		XsxxYnysService ynysService = new XsxxYnysService();
		CommanForm dataSearchForm =new CommanForm();
		request.setAttribute("ssList", ynysService.getSsList());
		request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
		request.setAttribute("xianList", ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
		
		model.setXn(XszzCsszActionForm.getCsszForm().getXn());
		model.setCyxmArr(request.getParameterValues("cyxm"));
		model.setCynlArr(request.getParameterValues("cynl"));
		model.setCygxArr(request.getParameterValues("cygx"));
		model.setCygzxxdwArr(request.getParameterValues("cygzxxdw"));
		model.setCyzyArr(request.getParameterValues("cyzy"));
		model.setCylxdhArr(request.getParameterValues("cylxdh"));
		model.setCynsrArr(request.getParameterValues("cynsr"));
		model.setCyjkzkArr(request.getParameterValues("cyjkzk"));
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			model.setSzssx(model.getSyshen()+"--"+model.getSyshi()+"--"+model.getSyxian());
			boolean result = serivce.bcJtqkdzxx(model);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		request.setAttribute("zzxn", model.getXn());
		
		if (rs != null && !rs.isEmpty()) {
			model.setXh(xh);
			rs.putAll(serivce.ckJtqkdzxx(model));
			List<HashMap<String, String>> jtxxList = serivce.cxJtcyxxList(model);
			request.setAttribute("rs", rs);
			request.setAttribute("jtxxList", jtxxList);
			request.setAttribute("jtxxList1", jtxxList != null && jtxxList.size() > 0 ? jtxxList.get(0) : new HashMap<String, String>());
			request.setAttribute("jtxxList2", jtxxList != null && jtxxList.size() > 1 ? jtxxList.get(1) : new HashMap<String, String>());
			request.setAttribute("jtxxList3", jtxxList != null && jtxxList.size() > 2 ? jtxxList.get(2) : new HashMap<String, String>());
			
		}
		
		return mapping.findForward("zjJtqkdz");
	}
	

	/**
	 * �޸ļ�ͥ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgJtqkdz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		XsxxYnysService ynysService = new XsxxYnysService();
		CommanForm dataSearchForm =new CommanForm();
		request.setAttribute("ssList", ynysService.getSsList());
		request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
		request.setAttribute("xianList", ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
		model.setXn(XszzCsszActionForm.getCsszForm().getXn());
		model.setCyxmArr(request.getParameterValues("cyxm"));
		model.setCynlArr(request.getParameterValues("cynl"));
		model.setCygxArr(request.getParameterValues("cygx"));
		model.setCygzxxdwArr(request.getParameterValues("cygzxxdw"));
		model.setCyzyArr(request.getParameterValues("cyzy"));
		model.setCylxdhArr(request.getParameterValues("cylxdh"));
		model.setCynsrArr(request.getParameterValues("cynsr"));
		model.setCyjkzkArr(request.getParameterValues("cyjkzk"));
		
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			model.setSzssx(model.getSyshen()+"--"+model.getSyshi()+"--"+model.getSyxian());
			boolean result = serivce.xgJtqkdzxx(model);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		HashMap<String, String> rs = serivce.ckJtqkdzxx(model);
		model.setXh(rs.get("xh"));
		List<HashMap<String, String>> jtxxList = serivce.cxJtcyxxList(model);
		request.setAttribute("rs", rs);
		request.setAttribute("jtxxList", jtxxList);
		request.setAttribute("jtxxList1", jtxxList != null && jtxxList.size() > 0 ? jtxxList.get(0) : new HashMap<String, String>());
		request.setAttribute("jtxxList2", jtxxList != null && jtxxList.size() > 1 ? jtxxList.get(1) : new HashMap<String, String>());
		request.setAttribute("jtxxList3", jtxxList != null && jtxxList.size() > 2 ? jtxxList.get(2) : new HashMap<String, String>());
		return mapping.findForward("xgJtqkdz");
	}
	
	/**
	 * �鿴��ͥ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckJtqkdz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		
		HashMap<String, String> rs = serivce.ckJtqkdzxx(model);
		model.setXh(rs.get("xh"));
		List<HashMap<String, String>> jtxxList = serivce.cxJtcyxxList(model);
		request.setAttribute("rs", rs);
		request.setAttribute("jtxxList", jtxxList);
		CommanForm dataSearchForm =new CommanForm();
		XsxxYnysService ynysService = new XsxxYnysService();
		request.setAttribute("ssList", ynysService.getSsList());
		request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
		request.setAttribute("xianList", ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
		return mapping.findForward("ckJtqkdz");
	}
	
	/**
	 * �鿴��ͥ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckXsjtknxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		XszzJtqkdzActionForm model = (XszzJtqkdzActionForm) form;
		
		HashMap<String, String> rs = serivce.ckJtqkdzxx(model);
		model.setXh(rs.get("xh"));
		List<HashMap<String, String>> jtxxList = serivce.cxJtcyxxList(model);
		request.setAttribute("rs", rs);
		request.setAttribute("jtxxList", jtxxList);
		CommanForm dataSearchForm =new CommanForm();
		XsxxYnysService ynysService = new XsxxYnysService();
		request.setAttribute("ssList", ynysService.getSsList());
		request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
		request.setAttribute("xianList", ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
		return mapping.findForward("ckXsjtknxx");
	}
	
	/**
	 * ��ͥ���������Ϣ��ӡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XszzJtqkdzService serivce = new XszzJtqkdzService();
		
		XszzJtqkdzActionForm myForm = (XszzJtqkdzActionForm) form;
		
		XszzTyForm model = new XszzTyForm();
		
		XszzJhzyPrint jhzyPrint=new XszzJhzyPrint();
		// user����
		User user=getUser(request);
		// �û���
 		String xh=request.getParameter("xh");
		model.setXh(xh);
		
		HashMap<String,String>xsxxInfo=new HashMap<String,String>();
		
		//��ͥ��Ա��Ϣ
		List<HashMap<String, String>> jtcyList = serivce.cxJtcyxxList(myForm);
		//ѧ��������Ϣ
		jhzyPrint.getStuInfo(model,xsxxInfo);
		
		HashMap<String, String> jtqkdcInfo = serivce.ckJtqkdzxx(myForm);
		
		xsxxInfo.putAll(jtqkdcInfo);
		
		request.setAttribute("rs", xsxxInfo);
		request.setAttribute("cyList", jtcyList);
		return mapping.findForward("jtqkdcb");
	}
}
