package xgxt.audit.gnsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

public class ShgnszAction extends BasicAction{

	
	/**
	 * 审批流功能设置管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gnszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ShgnszForm model = (ShgnszForm) form;
		String tableName = "xg_ty_shlcszb";
		String viewName = "xg_view_ty_shlcszb";
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"pkValue","gnmc","mc"};
		String[] topTr = new String[]{"pkValue","功能名称","流程名称"};
		
		//查询
		if (QUERY.equals(doType)){
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		request.setAttribute("path", "xtwhGnsz.do?method=gnszManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("gnszManage");
	}
	
	
	public ActionForward gnszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_ty_shlcszb";
		String viewName = "xg_view_ty_shlcszb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		CommService service = new CommService();
		
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		if (SAVE.equals(doType)){
			updateOperation(request, tableName);
		}
		
		request.setAttribute("lcList", service.getWhList("xg_xtwh_splc", "id", "mc", "", "", ""));//流程列表
		return mapping.findForward("gnszUpdate");
	}
}
