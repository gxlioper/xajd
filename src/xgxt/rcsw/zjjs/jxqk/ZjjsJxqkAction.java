package xgxt.rcsw.zjjs.jxqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @version 1.0
 */

public class ZjjsJxqkAction extends BasicAction {

	public ActionForward jxqkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";
		String doType = request.getParameter("doType");

		if (SAVE.equals(doType)) {
			insertOperation(request, tableName);
		}

		return mapping.findForward("jxqkAdd");
	}

	public ActionForward jxqkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";

		updateOperation(request, tableName);

		return jxqkView(mapping, form, request, response);
	}

	public ActionForward jxqkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";
		String pkValue = request.getParameter("pkValue");

		if (StringUtils.isNotNull(pkValue)) {
			selectPageDataByOne(request, tableName, tableName, pkValue);
		}

		return mapping.findForward("jxqkUpdate");
	}

	public ActionForward jxqkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		 ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";
		String[] colList = new String[] { "pkValue", "bmmc", "xm", "xb","csrq", "xl" };

		selectPageDataByPagination(request, form, tableName, tableName, colList);

		request.setAttribute("path", "rcsw_zjjs_jxqk.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("jxqkManage");
	}

	public ActionForward jxqkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";
		String[] colList = new String[] {"bmmc", "xm", "xb","csrq",
					"xl","byyx","zy","zyjszw","xzy","sqjxzy","jxyx",
					"rdsj","bysj","xfje","hjqk","bzqk","rzgysj" };

		expPageData(request, response, tableName, tableName, colList);

		return mapping.findForward("");
	}

	public ActionForward jxqkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ZjjsJxqkForm model = (ZjjsJxqkForm) form;

		String tableName = "xg_zjjs_rcsw_fdyjxqkb";

		deleteOperation(request, tableName);

		return jxqkManage(mapping, form, request, response);
	}

}
