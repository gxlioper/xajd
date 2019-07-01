package xgxt.dtjs.zgdzdx.zbr;

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

public class ZbrAction extends DispatchAction {

	/**
	 * 值班人管理
	 * 
	 * @throws Exception
	 */
	public ActionForward zbrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZbrService service = new ZbrService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZgdzdxDtjsUnit unit = new ZgdzdxDtjsUnit();
		ZbrModel model = new ZbrModel();

		String tableName = "view_zgdd_zbrb";
		String realTable = "zgdd_zbrb";
		String title = "党团建设 - 数据维护 - 值班人管理";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		if (!"admin".equalsIgnoreCase(userType)
				&& !"xx".equalsIgnoreCase(userType)) {
			// request.setAttribute("errMsg", "此功能只对管理员或学校用户开放！");

			String msg = "此功能只对管理员或学校用户开放！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);

		}

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = request.getParameter("pk");
			result = service.delZbr(pk, request);

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
					"xymc", "bjmc", "zbid", "gwmc", "dh" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getZbrList(tableName, model, colList);

			myForm.setXh(DealString.toGBK(model.getXh()));
			myForm.setXm(DealString.toGBK(model.getXm()));

		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxZbr.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("zbrManage");
	}

	/**
	 * 值班人修改
	 * 
	 * @throws Exception
	 */
	public ActionForward zbrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZbrService service = new ZbrService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZbrModel model = new ZbrModel();

		String tableName = "view_zgdd_zbrb";
		String type = request.getParameter("doType");
		String pk = request.getParameter("pk");
		HashMap<String, String> rs = new HashMap<String, String>();
		boolean result = false;

		if (type != null && type.equalsIgnoreCase("add")) {
			rs.put("bz", "");
		}
		if (type != null && type.equalsIgnoreCase("stuInfo")) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			String xh = request.getParameter("xh");
			rs = service.getZbr("view_xsjbxx", colList, "xh", xh);
			type = "add";
		}
		if ((type != null)
				&& (type.equalsIgnoreCase("edit") || type
						.equalsIgnoreCase("view"))) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "zbid", "gwmc", "dh", "yhzh" };
			rs = service.getZbr(tableName, colList, "xh", pk);
		}
		if ((type != null) && type.equalsIgnoreCase("save")) {

			BeanUtils.copyProperties(model, myForm);

			result = service.saveZbr(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("doType", type);

		return mapping.findForward("zbrUpdate");
	}
}
