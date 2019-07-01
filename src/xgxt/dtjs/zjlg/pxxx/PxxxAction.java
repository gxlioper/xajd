package xgxt.dtjs.zjlg.pxxx;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.zjlg.ZjlgDtjsForm;
import xgxt.dtjs.zjlg.zbgl.ZbglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class PxxxAction extends DispatchAction {

	/**
	 * 培训信息管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		PxxxService service = new PxxxService();
		ZbglService zbService = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		PxxxModel model = new PxxxModel();

		String title = "党团建设 - 支部管理 - 培训信息";
		String doType = request.getParameter("doType");
		String tableName = "view_xspxxx";
		String realTable = "xspxxxb";
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delPxxx(checkVal);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			// 设置用户类型MAP
			HashMap<String, String> map = service.setUserTypeMap(request);
			
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "zbmc", "pxxmmc", "pxkssj", "pxjssj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPxxxList(tableName, model, colList,map);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZbdm(DealString.toGBK(myForm.getZbdm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "dtjs_pxxx.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("pxxmList", service.getPxxm());
		request.setAttribute("zbList", zbService.getZbList(xydm));
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("pxxxManage");
	}

	/**
	 * 培训信息维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 培训信息";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");

		PxxxService service = new PxxxService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		PxxxModel model = new PxxxModel();
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			if (Base.isNull(xh)) {
				rs.put("xh", " ");
			} else {
				rs = service.getStuInfo(xh);
			}
		}
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			rs = service.getPxxxInfo(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.savePxxx(model, request);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForDzb(request);

		request.setAttribute("pxxmList", service.getPxxm());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("pxxxUpdate");
	}
}
