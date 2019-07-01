package xgxt.dtjs.zgdzdx.dzb;

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
import xgxt.action.Base;

public class DzbAction extends DispatchAction {

	/**
	 * 党支部管理
	 * 
	 * @throws Exception
	 */
	public ActionForward dzbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		DzbService service = new DzbService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZgdzdxDtjsUnit unit = new ZgdzdxDtjsUnit();
		DzbModel model = new DzbModel();

		String tableName = "view_zgdd_dzbjsb";
		String realTable = "zgdd_dzbjsb";
		String title = "党团建设 - 数据维护 - 党支部管理";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = DealString.toGBK(request.getParameter("pk"));
			result = service.delDzb(pk, request);

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
			String[] colList = new String[] { "pk", "xymc", "dzbmc", "zrq",
					"dzbcys" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getDzbList(tableName, model, colList);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxDzbqk.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("dzbManage");
	}

	/**
	 * 党支部信息修改
	 * 
	 * @throws Exception
	 */
	public ActionForward dzbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DzbService service = new DzbService();
		ZgdzdxDtjsForm myForm = (ZgdzdxDtjsForm) form;
		ZgdzdxDtjsUnit unit = new ZgdzdxDtjsUnit();
		DzbModel model = new DzbModel();

		String tableName = "view_zgdd_dzbjsb";
		String type = request.getParameter("doType");
		String pk = DealString.toGBK(request.getParameter("pk"));
		HashMap<String, String> rs = new HashMap<String, String>();
		boolean result = false;

		if (type != null && type.equalsIgnoreCase("add")) {
			rs.put("bz", "");
		}
		if ((type != null)
				&& (type.equalsIgnoreCase("edit") || type
						.equalsIgnoreCase("view"))) {
			String[] colList = new String[] { "xydm", "dzbmc", "zrq", "dzbcys",
					"dzbjycs", "dzbbz" };
			rs = service.getDzb(tableName, colList, "xydm||dzbmc", pk);
		}
		if (type != null && type.equalsIgnoreCase("save")) {

			BeanUtils.copyProperties(model, myForm);
			
			String xyV=request.getParameter("xyV");
			if(!Base.isNull(xyV)){
				model.setXydm(xyV);
			}
			result = service.saveDzb(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("doType", type);

		return mapping.findForward("dzbUpdate");
	}
}
