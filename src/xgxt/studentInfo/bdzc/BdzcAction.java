package xgxt.studentInfo.bdzc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ����Ϣ������ע��Action</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-13</p>
 */
public class BdzcAction extends BasicAction {
	
	
	/**
	 * ����ע�ᣭʱ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		BdzcService service = new BdzcService();
		BdzcActionForm model = (BdzcActionForm) form;
		String tableName = "xsxx_bdzc_sjsz";
		String viewName = "view_xsxx_bdzc_sjsz";
		String[] colList = new String[] { "pkValue", "xn", "xqmc", "xh", "xm",
				"nj", "xymc","bjmc", "zckssj", "zcjssj", "hzcjssj" };
		String doType = request.getParameter("doType");
		boolean result = false;
		User user = getUser(request);
		
		if ("xy".equalsIgnoreCase(user.getUserType()) && !Boolean.valueOf(user.getIsFdy())){
			model.setQueryequals_xydm(user.getUserDep());
			request.setAttribute("annexTerm", " and xydm='"+user.getUserDep()+"'");
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			
			String[] pkValue = request.getParameterValues("pkValue");
			String[] zckssj = request.getParameterValues("zckssj");
			String[] zcjssj = request.getParameterValues("zckjsj");
			String[] hzcjssj = request.getParameterValues("hzcjssj");
			
			if (null != pkValue && pkValue.length > 0) {
				//ʱ��������������
				result = service.saveSjsz(pkValue, zckssj, zcjssj, hzcjssj);
				request.setAttribute("result", result);
			}
			
		}
		
		//ͬ��
		if (!Base.isNull(doType) && "tb".equals(doType)) {
			request.setAttribute("result", service.zctb());
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, colList);
			return mapping.findForward("");
		}
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("path", "bdzc_sjsz.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sjsz");
	}
	
	
	/**
	 * ��ע������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		BdzcService service = new BdzcService();
		BdzcActionForm myForm = (BdzcActionForm) form;
		
		String tableName = "bdzc_hzc";
		String viewName = "view_bdzc_hzc";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		HashMap<String, String> rs = new HashMap<String, String>();
		
		
		//����ѧ��������Ϣ
		if ("stu".equals(userType)) {
			rs = service.getStuInfo(userName);
			xh=userName;
		} else {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		
		//����
		if ("save".equals(doType)) {
			this.insertOperation(request, tableName);
			this.selectPageDataByOne(request, tableName, viewName, myForm.getSave_xh()
					+ myForm.getSave_xn() + myForm.getSave_xq());
		}
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("isHzcsj", service.isHzcsj(xh));
		request.setAttribute("path", "bdzc_hzcsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hzcsq");
	}
	
	
	/**
	 * ��ע�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		BdzcActionForm myForm = (BdzcActionForm) form;
		BdzcService service = new BdzcService();
		
		String tableName = "bdzc_hzc";
		String viewName = "view_bdzc_hzc";
		
		String doType = request.getParameter("doType");
		String[] outputColumn = null;
		
		/*Ȩ�޿���*/
		if ("xy".equals(userType)) {
			outputColumn = new String[] {"xn","xq" ,"pkValue", "xh", "xm", "nj", "xymc",
					"zymc", "bjmc", "xn", "xqmc", "sqyy", "xysh" };
			request.setAttribute("shzd", "xysh");
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			outputColumn = new String[] { "xn","xq" ,"pkValue", "xh", "xm", "nj", "xymc",
					"zymc", "bjmc", "xn", "xqmc", "sqyy", "xxsh" };
			request.setAttribute("shzd", "xxsh");
			request.setAttribute("annexTerm", " and xysh='ͨ��' ");
		} else {
			request.setAttribute("errMsg","�Բ�������Ȩ���ʴ�ҳ��" );
			return new ActionForward("/errMsg.do",false);
		}
		
		//��ѯ
		if ("query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//�������
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		
		service.setList(request, "sh");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "bdzc_hzcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hzcsh");
	}
	
	
	/**
	 * ��ע������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		BdzcActionForm myForm = (BdzcActionForm) form;
		BdzcService service = new BdzcService();
		
		String tableName = "bdzc_hzc";
		String viewName = "view_bdzc_hzc";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "xn", "xqmc", "sqyy", "xysh","xxsh" };
		
		//ѧԺ�û�����
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("stu".equals(userType)) {
			myForm.setQuerylike_xh(userName);
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		//��ѯ
		if ("query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//	ɾ��
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		//����
		if (!Base.isNull(doType) && "expPageData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, outputColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "sh");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "bdzc_hzcjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hzcjg");
	}
	
	
	/**
	 * ��ע�ᵥ��ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzcOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BdzcService service = new BdzcService();
		
		String tableName = "bdzc_hzc";
		String viewName = "view_bdzc_hzc";
		
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		//��������
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		if ("modify".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		service.setList(request, "sh");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("hzcOne");
	}


	/**
	 * ѧ���ɷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		BdzcActionForm myForm = (BdzcActionForm) form;
		BdzcService service = new BdzcService();
		
		String tableName = "xsxxb";
		String viewName = "view_cw_bks_xssfb";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] { "r", "xh", "xm", "nj", "xymc",
				"bjmc", "sfqjdm", "ysje", "ssje", "sfqf" };
		
		if ("xy".equals(userType) && myForm.getQueryequals_xydm() == null) {
			myForm.setQueryequals_xydm(userDep);
			//request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, outputColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "zczt");
		request.setAttribute("realTable", "cw_bks_xssfb");
		request.setAttribute("path", "bdzc_xsjf.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsjf");
	}
	
	
	
	/**
	 * ѧ���ɷѵ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjfOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "cw_bks_xssfb";
		String viewName = "view_cw_bks_xssfb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "bdzc_xsjf.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xsjfOne");
	}
	
	
	
	/**
	 * ��ɫͨ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lstd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		
		BdzcActionForm myForm = (BdzcActionForm) form;
		
		String tableName = "bdzc_lstd";
		String viewName = "view_bdzc_lstd";
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "xn", "xqmc", "je" };
		String doType = request.getParameter("doType");
		
		//	�û�����
		if ("xy".equals(userType) && !Boolean.parseBoolean(isFdy)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else 
			if ("stu".equals(userType)) {
			myForm.setQuerylike_xh(userName);
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, outputColumn);
			return mapping.findForward("");
		}
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "bdzc_lstd.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("lstd");
	}
	
	
	
	/**
	 * ��ɫͨ������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward lstdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BdzcService service  = new BdzcService();
		
		String tableName = "bdzc_lstd";
		String viewName = "view_bdzc_lstd";
		
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		HashMap<String , String> rs = new HashMap<String, String>();
		
		if (!Base.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		
		if (!Base.isNull(pkValue)) {
			 this.selectPageDataByOne(request, tableName, viewName, pkValue);
			 rs = (HashMap<String, String>) request.getAttribute("rs");
			 rs.put("xz", service.getStuInfo(rs.get("xh")).get("xz"));
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "update".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		if (!Base.isNull(xh) || !Base.isNull(rs.get("xh"))) {
			xh = Base.isNull(xh) ? rs.get("xh") : xh;
			String xn = Base.isNull(rs.get("xn")) ? Base.currXn : rs.get("xn");
			String xq = Base.isNull(rs.get("xq")) ? Base.currXq : rs.get("xq");
			request.setAttribute("zxdk", service.getZxdk(xh));
			request.setAttribute("qfqk", service.getSfqf(xh, xn,xq));
		}
		
		if (null != rs && Base.isNull(doType)) {
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("currXq", Base.currXq);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "bdzc_lstd.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(request, "xq");
		return mapping.findForward("lstdOne");
	}
	
	
	
	/**
	 * ע��Ǽ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		BdzcService service = new BdzcService();
		BdzcActionForm myForm = (BdzcActionForm) form;
		
		String tableName = "bdzc_zcdj";
		String viewName = "view_bdzc_zcdj";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] { "xn","xq","pkValue","xh",  "xm", "xn","xqmc",
				"ysje", "ssje", "je","sfqf", "ishzc","isLstd","zczt" };
		
		String[] pkValue = request.getParameterValues("primarykey_cbv");
		String[] xn = request.getParameterValues("xn");
		String[] xq = request.getParameterValues("xq");
		
		if (Base.isNull(myForm.getQueryequals_xn())) {
			myForm.setQueryequals_xn(Base.currXn);
		}
		
		if (Base.isNull(myForm.getQueryequals_xq())) {
			myForm.setQueryequals_xq(Base.currXq);
		}
		
		//��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		
		//	����
		if (!Base.isNull(doType) && "expPageData".equals(doType)) {
			String[] colList = new String[] { "xh", "xm", "xn", "xqmc", "ysje",
					"ssje", "je", "sfqf", "ishzc", "isLstd", "zczt" };
			this.expPageData(request, response, tableName, viewName, colList);
			return mapping.findForward("");
		}
		
		
		//����ע��״̬
		if (!Base.isNull(doType) && "save".equals(doType)) {
			if (null != pkValue && pkValue.length > 0) {
				HashMap<String, String> map = service.saveZczt(pkValue,xn,xq,request.getParameter("zczt"));
				request.setAttribute("result", map.get("result"));
				request.setAttribute("count", map.get("count"));
			}
		}
		
		//ע��״̬ͬ��
		if (!Base.isNull(doType) && "zczttb".equals(doType)) {
			
				boolean blog = service.tbZczt();
				String message = blog ? "ͬ��ע��״̬�ɹ�!" :"ͬ��ע��״̬ʧ��!";
				request.setAttribute("message", message);
			
		}
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		service.setList(request, "zczt");
		request.setAttribute("path", "bdzc_zcdj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcdj");
	}
	
	
	/**
	 * ͳ�ƽ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		User user = getUser(request);
		BdzcActionForm model = (BdzcActionForm) form;
		BdzcService service = new BdzcService();
		
		String viewName = "";
		String[] outputColumn = null;
		String doType = request.getParameter("doType");
		String tjfs = request.getParameter("tjfs");
		
		/*ͳ�Ʒ�ʽ*/
		if ("xy".equals(tjfs)) {
			viewName = "view_bdzc_xytjjg";
			outputColumn = new String[] { "xymc", "xn","xqmc","wrs", "yrs", "ysje", "ssje", "je" };
			
			if ("xy".equalsIgnoreCase(user.getUserType())){
				model.setQueryequals_xydm(user.getUserDep());
				request.setAttribute("annexTerm", " and xydm= '"+user.getUserDep()+"'");
			}
			
		} else if ("zy".equals(tjfs)) {
			viewName = "view_bdzc_zytjjg";
			outputColumn = new String[] { "xymc", "zymc","xn","xqmc","wrs", "yrs", "ysje", "ssje", "je" };
			
			if ("xy".equalsIgnoreCase(user.getUserType())){
				model.setQueryequals_xydm(user.getUserDep());
				request.setAttribute("annexTerm", " and xydm= '"+user.getUserDep()+"'");
			}
		} else {
			 viewName = "view_bdzc_tjjg";
			 outputColumn = new String[] { "nj", "xymc", "zymc", "bjmc", "xn",
					"xqmc", "wrs", "yrs", "ysje", "ssje", "je" };
			 
			 if (Boolean.valueOf(user.getIsFdy())){
					request.setAttribute("annexTerm", " and exists (select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+user.getUserName()+"')");
			 } else if ("xy".equalsIgnoreCase(user.getUserType())){
				 model.setQueryequals_xydm(user.getUserDep());
				 request.setAttribute("annexTerm", " and xydm= '"+user.getUserDep()+"'");
			 }
		}
		
		//��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, "", viewName, outputColumn);
		}
		
		//	����
		if (!Base.isNull(doType) && "expPageData".equals(doType)) {
			this.expPageData(request, response, "", viewName, outputColumn);
			return mapping.findForward("");
		}
		
		service.setList(request, "tjjg");
		request.setAttribute("path", "bdzc_tjjg.do");
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjjg");
	}
	
	
	
	/**
	 * ����ע���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		BdzcService service = new BdzcService();
		BdzcActionForm myForm = (BdzcActionForm) form;
		
		String tableName = "bdzc_zcdj";
		String viewName = "view_bdzc_zcdj";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] {"jfbz", "xn", "xq", "pkValue", "xh",
				"xm", "xn", "xqmc", "sfqf", "isLstd","sfzxdk","zczt" };
		
		
		if (Base.isNull(myForm.getQueryequals_xn())) {
			myForm.setQueryequals_xn(Base.currXn);
		}
		
		if (Base.isNull(myForm.getQueryequals_xq())) {
			myForm.setQueryequals_xq(Base.currXq);
		}
		
		//��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		service.setList(request, "zczt");
		request.setAttribute("path", "bdzc_bdzc.do");
		FormModleCommon.commonRequestSet(request);
		return  mapping.findForward("bdzc");
	}
	
	
	
	
	/**
	 * ����ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bdzcOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BdzcService service = new BdzcService();
		
		String tableName = "bdzc_zcdj";
		String viewName = "view_bdzc_zcdj";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			request.setAttribute("result", service.updateZczt(pkValue));
		}
		
		
		/*     ������2010-8-27�����޸� begin       */
		//�жϸ����ڵ�ǰѧ���Ƿ��нɷѼ�¼
		if (!rs.isEmpty()) {
			//����true���԰��� 
			request.setAttribute("pdbz", service.checkStusfJf(rs.get("xh"), rs.get("nd")));
		}
		/*    end         */
		
		
		//�Ƿ�ע��ʱ���
		request.setAttribute("iszcsj", service.getZcsj(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "bdzc_bdzc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bdzcOne");
	}
}



























