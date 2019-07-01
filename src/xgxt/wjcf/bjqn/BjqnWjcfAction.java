package xgxt.wjcf.bjqn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class BjqnWjcfAction extends BasicExtendAction{
	
	/**
	 * 显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfjj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		BjqnWjcfService service = new BjqnWjcfService();
		Map<String, String> map = service.getCfInfo(pkValue);
		
		String cflbdm = map.get("cflb");
		List<HashMap<String, String>> cflbList = service.getJjcflbList(cflbdm);
		
		if(cflbList != null && cflbList.size() == 0){
			request.setAttribute("isCf", "no");
		}
		
		request.setAttribute("rs", map);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cflbList", cflbList);
		return mapping.findForward("wjcfjj");
	}
	
	/**
	 * 违纪处分降级保存,将历史记录保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfjjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		BjqnWjcfForm myForm = (BjqnWjcfForm)form;
		WjcfModel model = new WjcfModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		BjqnWjcfService service = new BjqnWjcfService();
		Map<String, String> map = service.getCfInfo(pkValue);
		
		String cflbdm = map.get("cflb");
		List<HashMap<String, String>> cflbList = service.getJjcflbList(cflbdm);
		
		String message = service.Cfjj(pkValue, model) ? "操作成功！" : "操作失败！";
		
		request.setAttribute("rs", map);
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("message", message);
		return mapping.findForward("wjcfjj");
	}
	
	public ActionForward lsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userDep = (String)session.getAttribute("userDep");
		String userName = (String)session.getAttribute("userName");
		String user = getUserType(session);
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String viewName = "view_wjcf_bjqnzzxy_cfjj";
		String tableName = "wjcf_bjqnzzxy_cfjjb";
	
		
		BjqnWjcfForm myForm = (BjqnWjcfForm)form;
		
		if("xy".equalsIgnoreCase(user)){
			myForm.setQueryequals_xydm(userDep);
		}else if ("stu".equalsIgnoreCase(user)){
			myForm.setQuerylike_xh(userName);
		}
		
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","xh","xm","bjmc", "xn", "xqmc",
					"cflbmc", "cfyymc","cfsj", "cfwh", "jjsj"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		setWriteAbleAndTitle(request, "wjcf_bjqn_jjlsxx.do");		
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("userType", user);
		return mapping.findForward("lsxxManage");
	}
	
	public ActionForward lsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String viewName = "view_wjcf_bjqnzzxy_cfjj";
		String tableName = "wjcf_bjqnzzxy_cfjjb";
		
		if(StringUtils.isNotNull(pkValue)){
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		return mapping.findForward("lsxxUpdate");
	}
	
	public ActionForward dataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] output = new String[]{"xh","xm","bjmc", "xn", "xqmc",
				"cflbmc", "cfyymc","cfsj", "cfwh", "jjsj"};
		
		String viewName = "view_wjcf_bjqnzzxy_cfjj";
		String tableName = "wjcf_bjqnzzxy_cfjjb";
		
		expPageData(request, response, tableName, viewName, output);
		return mapping.findForward("");
	}
}
