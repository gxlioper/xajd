package xgxt.dtjs.zjlg.zzgx;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class ZzgxAction extends DispatchAction {

	/**
	 * 组织关系转接管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward zzgxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZzgxService service = new ZzgxService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZzgxModel model = new ZzgxModel();

		String title = "党团建设 - 支部管理 - 组织关系转接";
		String doType = request.getParameter("doType");
		String tableName = "view_zjlg_zzgx";
		String realTable = "zjlg_zzgx";
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delZzgx(checkVal);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
//			 设置用户类型MAP
			HashMap<String, String> map = service.setUserTypeMap(request);
			
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"lxmc","zzmmmc", "zjsj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getZzgxList(tableName, model, colList,map);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZbdm(DealString.toGBK(myForm.getZbdm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zjlg_dtjs_zzgx.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zzgxManage");
	}

	/**
	 * 组织关系转接维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward zzgxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 数据维护 - 组织关系转接";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		
		ZzgxService service = new ZzgxService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZzgxModel model = new ZzgxModel();
		HashMap<String, String> rs = new HashMap<String, String>();

		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		
		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			if (Base.isNull(xh)) {
				rs.put("xh", " ");
			} else {
				rs = service.getStuInfo(xh);
				rs.put("lxdh","");
			}
		}
		if ("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getZzgxInfo(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveZzgx(model, request);
			request.setAttribute("result", result);
		}
		
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zzgxUpdate");
	}
}
