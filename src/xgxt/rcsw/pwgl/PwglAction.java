package xgxt.rcsw.pwgl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.rcsw.RcswService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务票务管理类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 屈朋辉
 * @version 1.0
 */
public class PwglAction extends BasicAction {

	
	/**
	 * 车次查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cccx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswService service = new RcswService();
		
		String tableName = "hcccb";
		String viewName = "view_hccc";
		String[] outputColumn = new String[] { "pkValue", "cc", "qdzmc",
				"zdzmc", "kcsj", "ddsj", "yxsj", "pj", "dqzt", "tkz" };
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName,
					outputColumn);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName,
					outputColumn);
			return mapping.findForward("");
		}
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("ccList", service.getCcList());
		request.setAttribute("czList", service.getCzList());
		request.setAttribute("path", "rcsw_cccx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cccx");
	}
	
	
	/**
	 * 车次维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ccUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswService service = new RcswService();
		
		String tableName = "hcccb";
		String viewName = "view_hccc";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("ccList", service.getCcList());
		request.setAttribute("czList", service.getCzList());
		request.setAttribute("path", "rcsw_cccx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ccUpdate");
	}
	
}
