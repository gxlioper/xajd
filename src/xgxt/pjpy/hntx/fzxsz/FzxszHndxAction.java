package xgxt.pjpy.hntx.fzxsz;

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
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统
 * Description:海南大学发展性素质分Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-12
 */
public class FzxszHndxAction extends BasicExtendAction{
	
	/**
	 * 发展性素质分录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String user = getUserType(session);
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		FzxszForm myForm = (FzxszForm)form;
		
		String xn = Base.getJxjsqxn();
		FzxszHndxService service = new FzxszHndxService();
		
		List<HashMap<String, String>> xmList = service.getFzxszxm();

		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			FzxszModel model = new FzxszModel();
			BeanUtils.copyProperties(model, myForm);
		
			String msg = service.saveXsFzxszf(model) ? "保存成功" : "保存失败";
			request.setAttribute("msg", msg);
		}
		
		if("go".equalsIgnoreCase(go)){
			
			String[] outputColumn = new String[]{"pkValue","xh","xm"};
			this.selectPageDataByPagination(request, form, "xsxxb", "view_xsjbxx", outputColumn);
			List<String[]> rs1 = (List<String[]>) request.getAttribute("rs");
			
			if(rs1 != null && rs1.size()>0){
				List<String[]> rs2 = service.getXsFzxszf(rs1, xn);
				List<String[]> rs = service.changeRs(rs1, rs2, xmList);
				request.setAttribute("rs", rs);
			}
		}
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(session.getAttribute("userDep").toString());
		}
		
		setWriteAbleAndTitle(request, "fzxszflr.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("xmList", xmList);
		request.setAttribute("xn", xn);
		request.setAttribute("userType", user);
		return mapping.findForward("fzxszflr");
	}
	
	
	/**
	 * 发展性素质分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		String user = getUserType(session);
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		FzxszForm myForm = (FzxszForm)form;
		
		String xn = StringUtils.isNotNull(request.getParameter("xn")) ? request.getParameter("xn") : Base.getJxjsqxn();
		FzxszHndxService service = new FzxszHndxService();
		
		List<HashMap<String, String>> xmList = service.getFzxszxm();

		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] xhs = request.getParameterValues("primarykey_cbv");
			
			String msg = service.delXsfzxsz(xhs, xn) ? "删除成功" : "删除失败";
			request.setAttribute("msg", msg);
		}
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){
			
			String[] outputColumn = new String[]{"pkValue","xh","xm"};
			this.selectPageDataByPagination(request, form, "xsxxb", "view_xsjbxx", outputColumn);
			List<String[]> rs1 = (List<String[]>) request.getAttribute("rs");
			
			if(rs1 != null && rs1.size()>0){
				List<String[]> rs2 = service.getXsFzxszf(rs1, xn);
				List<String[]> rs = service.changeRs(rs1, rs2, xmList);
				request.setAttribute("rs", rs);
			}
		}
		
		// 学生用户
		if("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_xh(userName);
		}
		
		// 学院用户
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(userDep);
		}
		
		setWriteAbleAndTitle(request, "fzxszfcx.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("tableName", "view_hndx_xsfzxsz");
		request.setAttribute("realTable", "hndx_xsfzxszb");
		request.setAttribute("xmList", xmList);
		request.setAttribute("xn", xn);
		request.setAttribute("userType", user);
		return mapping.findForward("fzxszfcx");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String operation = "modi".equalsIgnoreCase(doType) ? "modi" : "view"; 
		
		String xn = StringUtils.isNotNull(request.getParameter("xn")) ? request.getParameter("xn") : Base.getJxjsqxn();
		String xh = request.getParameter("pkValue");
		
		FzxszHndxService service = new FzxszHndxService();
	
		if("save".equalsIgnoreCase(doType)){
			FzxszForm myForm = (FzxszForm)form;
			FzxszModel model = new FzxszModel();
			BeanUtils.copyProperties(model, myForm);
			
			String msg = service.saveXsFzxszf(model) ? "操作成功" : "操作失败";
			request.setAttribute("msg", msg);
		}
		
		
		List<HashMap<String, String>> list = service.getXsfzxszfOne(xh, xn);
		request.setAttribute("rs", list);
		
		request.setAttribute("operation", operation);
		return mapping.findForward("fzxszfview");
	}
	
	/**
	 * 通用导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xb","nj","xn","xymc","zymc","bjmc","xmmc","xmjf"};
		
		expPageData(request, response, "hndx_xsfzxszb","view_hndx_xsfzxsz", output);
		return mapping.findForward("");
	}
	/**
	 * 海南大学综合素质总分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String go = request.getParameter("go");
		
		String user = getUserType(session);
		FzxszForm myForm = (FzxszForm)form;
		
		if("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_xh(userName);
		}else if("xy".equalsIgnoreCase(user)){
			myForm.setXydm(userDep);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"xh","xm","xn","xymc","zymc","bjmc","fs",
					"fzxzs","zhzf"};
			
			this.selectPageDataByPagination(request, myForm, "", "view_hndx_zhszf", outputColumn);
		}
		
		setWriteAbleAndTitle(request, "zhszfcx.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("tableName", "view_hndx_zhszf");
		return mapping.findForward("zhszfcx");
	}
	
		
	/**
	 * 通用导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xb","xn","xymc","zymc","bjmc","fs","fzxzs",
				"zhzf"};
		
		expPageData(request, response, "","view_hndx_zhszf", output);
		return mapping.findForward("");
	}
	
//	/**
//	 * 发展性素质项目维护
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward fzxszxm(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String doType = request.getParameter("doType");
//		
//		FzxszHndxService service = new FzxszHndxService();
//		
//		if("save".equalsIgnoreCase(doType)){
//			FzxszForm myForm = (FzxszForm)form;
//			FzxszModel model = new FzxszModel();
//			BeanUtils.copyProperties(model, myForm);
//			
//			String msg = service.saveFzxszxm(model) ? "保存成功" : "保存失败";
//			request.setAttribute("msg", msg);
//		}
//		
//		return mapping.findForward("fzxszxm");
//	}
}
