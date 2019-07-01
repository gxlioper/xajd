package xgxt.shgz.zgdzdx.cssz;

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
import xgxt.shgz.zgdzdx.ZgdzdxShgzForm;
import xgxt.shgz.zgdzdx.ZgdzdxShgzUnit;

public class CsszAction extends DispatchAction {

	/**
	 * 参数设置管理
	 * 
	 * @throws Exception
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		CsszService service = new CsszService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();
		CsszModel model = new CsszModel();

		String tableName = "view_zgdd_hdbzd";
		String realTable = "zgdd_shgz_hdbzd";
		String title = "社会工作 - 参数设置";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;

		// if (!"admin".equalsIgnoreCase(userType)
		// && !"xx".equalsIgnoreCase(userType)) {
		// request.setAttribute("errMsg", "此功能只对管理员或学校用户开放！");
		// return new ActionForward("/errMsg.do", false);
		// }

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = DealString.toGBK(request.getParameter("pk"));
			result = service.delCssz(pk, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "hdmc", "xn","zd", "zdm", "lxmc" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getCsszList(tableName, model, colList);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxShgzCssz.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("hdList", service.getHdList());
		
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("csszManage");
	}

	/**
	 * 参数设置
	 * 
	 * @throws Exception
	 */
	public ActionForward csszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CsszService service = new CsszService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		CsszModel model = new CsszModel();
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();
		
		HashMap<String, String> rs = new HashMap<String, String>();

		String doType = request.getParameter("doType");
		String tableName = "view_zgdd_hdbzd";
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		if (!Base.isNull(doType) && "add".equalsIgnoreCase(doType)) {
			rs.put("bz", "");
		}
		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
			String[] colList = new String[] { "hddm", "xn","zd", "zdm", "zdlx" };
			rs = service.getCssz(tableName, colList, "hddm||xn||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String hdV = request.getParameter("hdV");
			String zdV = request.getParameter("zdV");
			String xnV = request.getParameter("xnV");
			if (!Base.isNull(hdV)) {
				model.setHddm(hdV);
			}
			if (!Base.isNull(zdV)) {
				model.setZd(zdV);
			}
			if (!Base.isNull(xnV)) {
				model.setXn(xnV);
			}

			result = service.saveCssz(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("hdList", service.getHdList());
		unit.setNjXyZyBjList(request, myForm);

		return mapping.findForward("csszUpdate");
	}
}
