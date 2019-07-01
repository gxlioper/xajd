package xgxt.pjpy.zjkjxy;

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
import xgxt.base.Excel2Oracle;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszModel;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjkjxyAction extends CommonAction{
	
	/**
	 * �������ù���
	 * */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm thisForm = (PjpyZjkjxyActionForm) form;
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String userType = getUserType(request);
		String msg = "";//��ʾ��Ϣ
		//��������
		String pjzq = zjkjService.getPjpySqzq();
		//�����ж�ѧ���û����ܽ����ģ��
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)) {
			msg = "��ǰ�û���Ȩ���ʸ�ҳ�棡";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//�û��жϸù����Ƿ񿪷�
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_RSTZ)
				&& zjkjService.checkKgflag()){
			msg = "�ù�����ʱ�����Ų�����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		setXydm(request, thisForm);
		setPjxnndxq(thisForm);
		
		if (StringUtils.isNull(thisForm.getKey())) {
			thisForm.setKey(GlobalsVariable.PJPY_JXJ);
		}
		
		//��ѯ���ݲ���
		String key = thisForm.getKey();
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(thisForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, thisForm);
			topTr = service.queryJxjrsTitle(key);
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, key);
		}
		
		//�����������������
		request.setAttribute("path", "pjpyrssz.do");
		request.setAttribute("pjzq", pjzq);//��������
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(key,
				thisForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rsszManage");
	}

	/**
	 * �����ƺ�������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychRsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm thisForm = (PjpyZjkjxyActionForm) form;
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String userType = getUserType(request);
		String msg = "";//��ʾ��Ϣ
		//�����ж�ѧ���û����ܽ����ģ��
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)) {
			msg = "��ǰ�û���Ȩ���ʸ�ҳ�棡";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//�û��жϸù����Ƿ񿪷�
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_RSTZ)
				&& zjkjService.checkKgflag()){
			msg = "�ù�����ʱ�����Ų�����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
			
		
		setXydm(request, thisForm);
		setPjxnndxq(thisForm);
		
		if (StringUtils.isNull(thisForm.getKey())) {
			thisForm.setKey(GlobalsVariable.PJPY_RYCH);
		}
		
		//��ѯ���ݲ���
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(thisForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, thisForm);
			topTr = service.queryJxjrsTitle(thisForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, thisForm.getKey());
		}
		
		//�����������������
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(thisForm.getKey(),
				thisForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		request.setAttribute("pjzq", zjkjService.getPjpySqzq());
		return mapping.findForward("rychRsszManage");
	}
	
	/**
	 * �������ݳ�ʼ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward baseDataInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lb = request.getParameter("lb");
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		appendOperResult(request, service.baseDataInit(lb));
		//���ݴ�������������ж���תҳ��
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(lb) 
				|| GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)) {
			return rsszManage(mapping, form, request, response);
		}else {
			return rychRsszManage(mapping, form, request, response);
		}
		
	}
	
	/**
	 * ��ѧ������������ù���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//����Ĭ�ϵ�����ѧ�꣬ѧ�ڣ����
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//�������ݲ���
		String key = GlobalsVariable.PJPY_JXJ;
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, myForm);
			model.setKey(key);
			appendOperResult(request, zjkjService.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		//��ȡ������Χ�б�
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//��ȡ��ѧ������б�
		request.setAttribute("dmList", service.getDmList(key,myForm.getJxjlb()));
		//��ȡ��ѧ���������б�
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjblPlsz");
	}
	
	/**
	 * �����ƺű����������ù���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//����Ĭ�ϵ�����ѧ�꣬ѧ�ڣ����
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, myForm);
			model.setKey(GlobalsVariable.PJPY_RYCH);
			appendOperResult(request, zjkjService.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,myForm.getJxjlb()));
		request.setAttribute("lbList", zjkjService.getJxjlbList(true));
		request.setAttribute("path", "pjpyrssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychblPlsz");
	}
	
	/**
	 * ��ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pk = "xn||bmdm||nd||jxjdm||xqdm||key||nj";
		
		myForm.setPk(pk);		
		myForm.setKey(GlobalsVariable.PJPY_JXJ);
		
		PjpyZjkjxyActionForm model = new PjpyZjkjxyActionForm();
		BeanUtils.copyProperties(model, myForm);
		request.setAttribute("message", service.jxjrstz(model));
		return rsszManage(mapping, form, request, response);
	}
	
	/**
	 * �����ƺ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pk = "xn||bmdm||nd||jxjdm||xqdm||key||nj";
		
		myForm.setPk(pk);		
		myForm.setKey(GlobalsVariable.PJPY_RYCH);
		request.setAttribute("message", service.jxjrstz(myForm));
		return rychRsszManage(mapping, form, request, response);
	}
	
	/**
	 * ��ѧ������ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		String pjzq = service.getPjpySqzq();//��������
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "pjpy_jxjsqxsb";
		String viewName = "view_pjpy_jxjsqxs";//��ͼ����
		String[] zqzd = service.getPjzqzd(pjzq);//���������ֶ�
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue","xh","xn","nd","xq","xmdm","xh"}, zqzd, new String[]{"xm", "xymc",
				"bjmc", "xmmc", "sfksq" });
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		myForm.setKey(GlobalsVariable.PJPY_JXJ);//��ѧ��
		
		//�û��жϸù����Ƿ񿪷�
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSQRSZ)
				&& service.checkKgflag()){
			String msg = "���������ù�����ʱ�����Ų��������޲�ѯ��";
			request.setAttribute("yhInfo", msg);
			//return new ActionForward("/yhInfo.do", false);
		}
		
		//��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {		
			List<String[]> rs = service.queryJxjsqrssz(myForm,outputColumn);//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
			
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr("jxjsqxs",outputColumn));
		}
		//����
		else if (!Base.isNull(doType) && "save".equals(doType)) {
			String message = service.saveJxjsqrssz(myForm);
			request.setAttribute("message", message);
		}		
		//����
		else if (!Base.isNull(doType) && "exp".equals(doType)) {
			outputColumn = StringUtils.joinStrArr(new String[] { "xh"}, 
					                              zqzd, 
					                              new String[]{"xm", "xydm","xymc",
												  "zydm","zymc","bjdm","bjmc","nj",
												  "xmdm", "xmmc", "sfksq" });
			List<String[]> list = service.queryJxjsqrsszNotP(myForm,outputColumn);
			List<HashMap<String, String>> topTr = service.getTopTr("jxjsqxs",outputColumn);
			String[] colListCN = service.getCn(topTr);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//ѧԺרҵ�༶�꼶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				myForm.getQueryequals_xydm(), 
				myForm.getQueryequals_zydm(), 
				myForm.getQueryequals_bjdm(), 
				myForm.getQueryequals_nj());
		//ѧ�����ѧ��
		FormModleCommon.setNdXnXqList(request);
		//�����ƺ��б�
		service.setList(new PjpyTyForm(), request, "rych");
		//��ѧ���б�
		request.setAttribute("jxjList", service.getJxjList());
		//�Ƿ��б�
		request.setAttribute("flagList", service.getChkList(2));
		request.setAttribute("path", "pjpysqrsz.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("sqzqLength", zqzd.length);//���������ֶεĳ���
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxjsqxsManage");
	}
	
	/**
	 * �����ƺ�����ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjkjxyActionForm myForm = (PjpyZjkjxyActionForm)form;
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		String pjzq = service.getPjpySqzq();//��������
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "pjpy_jxjsqxsb";
		String viewName = "view_pjpy_jxjsqxs";
		String[] zqzd = service.getPjzqzd(pjzq);//���������ֶ�
		String[] outputColumn = StringUtils.joinStrArr(new String[] { "pkValue","xh","xn","nd","xq","xmdm","xh"}, zqzd, new String[]{"xm", "xymc",
				"bjmc", "xmmc", "sfksq" });
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		} else if ("stu".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			myForm.setQuerylike_xh(userName);
		}
		
		myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		myForm.setKey(GlobalsVariable.PJPY_RYCH);//�����ƺ�
		
		//�û��жϸù����Ƿ񿪷�
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJSQRSZ)
				&& service.checkKgflag()){
			String msg = "���������ù�����ʱ�����Ų��������޲�ѯ��";
			request.setAttribute("yhInfo", msg);
			//return new ActionForward("/yhInfo.do", false);
		}
		//��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {		
			List<String[]> rs = service.queryJxjsqrssz(myForm,outputColumn);//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getTopTr("jxjsqxs",outputColumn));
		}
		//����
		else if (!Base.isNull(doType) && "save".equals(doType)) {
			String message = service.saveJxjsqrssz(myForm);
			request.setAttribute("message", message);
		}		
		//����
		else if (!Base.isNull(doType) && "exp".equals(doType)) {
			outputColumn = StringUtils.joinStrArr(new String[] { "xh"}, 
						                    zqzd, 
						                    new String[]{"xm", "xydm","xymc",
											  "zydm","zymc","bjdm","bjmc","nj",
											  "xmdm", "xmmc", "sfksq" });
			List<String[]> list = service.queryJxjsqrsszNotP(myForm,outputColumn);
			List<HashMap<String, String>> topTr = service.getTopTr("jxjsqxs",outputColumn);
			String[] colListCN = service.getCn(topTr);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//ѧԺרҵ�༶�꼶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				myForm.getQueryequals_xydm(), 
				myForm.getQueryequals_zydm(), 
				myForm.getQueryequals_bjdm(), 
				myForm.getQueryequals_nj());
		//ѧ�����ѧ��
		FormModleCommon.setNdXnXqList(request);
		//�����ƺ��б�		
		request.setAttribute("rychList", service.getRychList());
		//�Ƿ��б�
		request.setAttribute("flagList", service.getChkList(2));
		request.setAttribute("path", "pjpysqrsz.do");
		request.setAttribute("realTable", tableName);
		request.setAttribute("sqzqLength", zqzd.length);//���������ֶεĳ���
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rychsqxsManage");
	}
	
	
	/**
	 * ѧԺ�û�����Ĭ�ϲ��Ŵ���
	 * @param request
	 * @param form
	 * */
	public void setXydm(HttpServletRequest request, PjpyZjkjxyActionForm form) throws Exception {
		String userType = getUserType(request);
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)) {
			form.setXydm(getUserDep(request));
			form.setQueryequals_xydm(getUserDep(request));
		}
	}
	
	/**
	 * ��������ѧ�꣬��ȣ�ѧ��
	 * */
	public void setPjxnndxq(PjpyZjkjxyActionForm form) {
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//pjzq = service.getZhcpSqzq();
		}
		
		if (StringUtils.isNull(form.getXn())  
				&& ("xn".equalsIgnoreCase(pjzq) 
						|| "xq".equalsIgnoreCase(pjzq))) {
			form.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getNd()) 
				&& "nd".equalsIgnoreCase(pjzq)) {
			form.setNd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getXq())
				&& "xq".equalsIgnoreCase(pjzq)) {
			form.setXq(Base.getJxjsqxq());
		}
		if (StringUtils.isNull(form.getQueryequals_xn())
				&& ("xn".equalsIgnoreCase(pjzq) 
						|| "xq".equalsIgnoreCase(pjzq))) {
			form.setQueryequals_xn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getQueryequals_nd())
				&& "nd".equalsIgnoreCase(pjzq)) {
			form.setQueryequals_nd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getQueryequals_xq())
				&& "xq".equalsIgnoreCase(pjzq)) {
			form.setQueryequals_xq(Base.getJxjsqxq());
		}
	}
	
}
