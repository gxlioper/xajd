package xgxt.pjpy.zjcm.cpz;

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
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;

public class CpzAction extends DispatchAction {

	/**
	 * 测评组管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward cpzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		CpzService service = new CpzService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		CpzModel model = new CpzModel();

		String tableName = "";
		String realTable = "zjcm_cpz";
		String title = "评奖评优 - 参数设置 - 测评小组维护";

		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String xydm = myForm.getXydm();

		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

			result = service.saveCpz(model);
			request.setAttribute("result", result);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "zjcm_pjpy_cpxz.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("zwList", service.getZwList(Base.isNull(xn) ? ""
						: xn, Base.isNull(xq) ? "" : xq, Base.isNull(xydm) ? ""
						: xydm));
		request.setAttribute("cpZwList", service.getCpZwList(
				Base.isNull(xn) ? "" : xn, Base.isNull(xq) ? "" : xq, Base
						.isNull(xydm) ? "" : xydm));
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		// myForm.setXn(xn);
		// myForm.setXq(xq);

		return mapping.findForward("cpzManage");
	}
}
