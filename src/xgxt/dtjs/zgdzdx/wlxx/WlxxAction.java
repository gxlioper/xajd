package xgxt.dtjs.zgdzdx.wlxx;

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

import xgxt.base.DealString;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsForm;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsUnit;

public class WlxxAction extends DispatchAction {

	/**
	 * 网络信息管理
	 * 
	 * @throws Exception
	 */
	public ActionForward wlxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		WlxxService service = new WlxxService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZgdzdxDtjsUnit unit = new ZgdzdxDtjsUnit();
		WlxxModel model = new WlxxModel();

		String tableName = "view_zgdd_wlxxb";
		String realTable = "zgdd_wlxxb";
		String title = "党团建设 - 数据维护 - 网络信息管理";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = request.getParameter("pk");
			result = service.delWlxx(pk, request);

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
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj",
					"xymc", "bjmc", "zbid", "gwmc", "kssj","jssj" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getWlxxList(tableName, model, colList);

			myForm.setXh(DealString.toGBK(model.getXh()));
			myForm.setXm(DealString.toGBK(model.getXm()));

		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxWlxx.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("wlxxManage");
	}

	/**
	 * 网络信息修改
	 * 
	 * @throws Exception
	 */
	public ActionForward wlxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WlxxService service = new WlxxService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		WlxxModel model = new WlxxModel();

		String tableName = "view_zgdd_wlxxb";
		String type = request.getParameter("doType");
		String pk = request.getParameter("pk");
		HashMap<String, String> rs = new HashMap<String, String>();
		boolean result = false;

		if (type != null && type.equalsIgnoreCase("add")) {
			rs.put("bz", "");
		}
		if (type != null && type.equalsIgnoreCase("stuInfo")) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "zbid", "gwmc", "dh", "yhzh" };
			String xh = request.getParameter("xh");
			rs = service.getWlxx("view_zgdd_zbrb", colList, "xh", xh);
			type = "add";
		}
		if ((type != null)
				&& (type.equalsIgnoreCase("edit") || type
						.equalsIgnoreCase("view"))) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "zbid", "gwmc", "dh", "yhzh", "kssj","jssj",
					"zbqk", "wlbz" };
			rs = service.getWlxx(tableName, colList, "xh", pk);
		}
		if (type != null && type.equalsIgnoreCase("save")) {

			BeanUtils.copyProperties(model, myForm);

			result = service.saveWlxx(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("doType", type);

		return mapping.findForward("wlxxUpdate");
	}
}
