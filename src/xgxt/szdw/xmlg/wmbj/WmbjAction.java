package xgxt.szdw.xmlg.wmbj;

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
import xgxt.szdw.xmlg.XmlgSzdwForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class WmbjAction extends DispatchAction {

	/**
	 * 自定义字段管理
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

		WmbjService service = new WmbjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		WmbjModel model = new WmbjModel();

		String tableName = "view_xmlg_wmbj_hdbzd";
		String realTable = "xmlg_wmbj_hdbzd";
		String title = "思政队伍 - 文明班级 - 申报内容设置";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;

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
			String[] colList = new String[] { "pk", "xn", "xqmc", "zd", "zdm",
					"lxmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getCsszList(tableName, model, colList);
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "xmlg_wmbj_sz.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("csszManage");
	}

	/**
	 * 自定义字段维护
	 * 
	 * @throws Exception
	 */
	public ActionForward csszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WmbjService service = new WmbjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		WmbjModel model = new WmbjModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "思政队伍 - 文明班级 - 申报内容设置";
		String doType = request.getParameter("doType");
		String tableName = "view_xmlg_wmbj_hdbzd";
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
					"zdlx" };
			rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			result = service.saveCssz(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (myForm.getXn() == null) {
			rs.put("xn",Base.currXn);
		}
		if (myForm.getXq() == null) {
			rs.put("xq",Base.currXq);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("csszUpdate");
	}

	/**
	 * 文明班级申报
	 * 
	 * @throws Exception
	 */
	public ActionForward wmbjSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		String tableName = "";
		String realTable = "";
		String title = "思政队伍 - 文明班级 - 申报";
		String doType = request.getParameter("doType");

		WmbjService service = new WmbjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		WmbjModel model = new WmbjModel();

		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "此功能只对学生开放！");
			return new ActionForward("/errMsg.do", false);
		}
		if (!service.isBgb(userName)) {
			request.setAttribute("errMsg", "申报只能由班干部操作，请确认！");
			return new ActionForward("/errMsg.do", false);
		}

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] zdyZd = new String[0];
			String[] zdyZdz = new String[0];

			List<HashMap<String, String>> list = service.getZdyZd(myForm
					.getXn(), myForm.getXq());

			if (list != null && list.size() > 0) {

				zdyZd = new String[list.size()];
				zdyZdz = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					String zd = map.get("zd");
					String zdz = DealString.toGBK(request.getParameter(zd));
					zdyZd[i] = zd;
					zdyZdz[i] = zdz;
				}
			}

			myForm.setArrZd(zdyZd);
			myForm.setArrZdz(zdyZdz);
			// FormModleCommon.formToGBK(myForm);

			BeanUtils.copyProperties(model, myForm);

			boolean result = service.saveWmbjsb(model, zdyZd, zdyZdz, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		HashMap<String, String> rs = service.getSbrXx(userName);
		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);

		// 存放访问路径求取读写权
		request.setAttribute("path", "xmlg_wmbj_sb.do");

		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);

		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("wmbjSb");
	}

	/**
	 * 申报查看管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward sbckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		WmbjService service = new WmbjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		WmbjModel model = new WmbjModel();

		String title = "思政队伍 - 文明班级 - 查看";
		String doType = request.getParameter("doType");
		String tableName = "view_xmlg_wmbjsh";
		String realTable = "xmlg_szdw_wmbjsh";
		String sbsj = myForm.getSbsj();
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if (!Base.isNull(sbsj) && sbsj.length() == 8) {
			sbsj = sbsj.substring(0, 4) + "-" + sbsj.substring(4, 6) + "-"
					+ sbsj.substring(6, 8);
			myForm.setSbsj(sbsj);
		}

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delWmbj(checkVal);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "bjmc",
					"sbr", "sbrxm", "sbsj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getWmbjList(tableName, model, colList);
			myForm.setSbr(DealString.toGBK(myForm.getSbr()));
			myForm.setSbrxm(DealString.toGBK(myForm.getSbrxm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "xmlg_wmbj_ck.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sbckManage");
	}

	/**
	 * 文明班级老师查看
	 * 
	 * @return ActionForward
	 */
	public ActionForward wmbjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "思政队伍 - 文明班级 - 查看";
		String pk = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		WmbjService service = new WmbjService();
		WmbjModel model = new WmbjModel();

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] zdyZd = new String[0];
			String[] zdyZdz = new String[0];

			List<HashMap<String, String>> list = service.getZdyZd(myForm
					.getXn(), myForm.getXq());

			if (list != null && list.size() > 0) {

				zdyZd = new String[list.size()];
				zdyZdz = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					String zd = map.get("zd");
					String zdz = DealString.toGBK(request.getParameter(zd));
					zdyZd[i] = zd;
					zdyZdz[i] = zdz;
				}
			}

			myForm.setArrZd(zdyZd);
			myForm.setArrZdz(zdyZdz);
			// FormModleCommon.formToGBK(myForm);

			BeanUtils.copyProperties(model, myForm);

			boolean result = service.saveWmbjsb(model, zdyZd, zdyZdz, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (!Base.isNull(pk)) {
			String[] pkValue = pk.split("!!@@!!");
			if (pkValue != null && pkValue.length > 3) {
				String xn = pkValue[0];
				String xq = pkValue[1];
				// String sbbj = pkValue[2];
				String xh = pkValue[3];

				HashMap<String, String> rs = service.getSbrXx(xh);
				rs.put("xn", xn);
				rs.put("xq", xq);

				request.setAttribute("rs", rs);
			}
		}

		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pk);
		
		return mapping.findForward("wmbjCk");
	}
}
