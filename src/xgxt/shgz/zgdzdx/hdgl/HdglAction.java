package xgxt.shgz.zgdzdx.hdgl;

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

public class HdglAction extends DispatchAction {

	/**
	 * 活动申请审核管理
	 * 
	 * @throws Exception
	 */
	public ActionForward hdglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		HdglService service = new HdglService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();
		HdglModel model = new HdglModel();

		String tableName = "view_zgdd_hdshb";
		String realTable = "zgdd_shgz_hdshb";
		String title = "社会工作 - 社团活动管理 - 活动审核";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);

		unit.setNjXyZyBjList(request, myForm);

		boolean result = false;

		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "此功能不对学生用户开放！");
			return new ActionForward("/errMsg.do", false);
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量审核
		if (!Base.isNull(doType)
				&& ("tg".equalsIgnoreCase(doType)
						|| "wtg".equalsIgnoreCase(doType) || "del"
						.equalsIgnoreCase(doType))) {

			String[] checkVal = myForm.getCheckVal();

			if (checkVal != null && checkVal.length > 0) {
				result = service.saveHdsh(checkVal, doType, userType);

				if (result) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = null;

			String pk = myForm.getHddm() + Base.currXn + Base.currXq;

			if ("yes".equalsIgnoreCase(service.needXy(pk, "search"))) {
				if ("xy".equalsIgnoreCase(userType)) {
					colList = new String[] { "pk", "xh", "xb", "xm", "xymc",
							"zymc", "bjmc", "hdmc", "xn", "xqmc", "xyshimg" };
				} else {
					colList = new String[] { "pk", "xh", "xb", "xm", "xymc",
							"zymc", "bjmc", "hdmc", "xn", "xqmc", "xyshimg",
							"xxshimg" };
				}
				request.setAttribute("needXy", "yes");
			} else {
				colList = new String[] { "pk", "xh", "xb", "xm", "xymc",
						"zymc", "bjmc", "hdmc", "xn", "xqmc", "xxshimg" };
				request.setAttribute("needXy", "no");
			}

			topTr = service.getTopTr(tableName, colList);
			rs = service.getHdglList(tableName, model, colList, userType,
					"search");

			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxShgzHdsh.do");

		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("hdList", service.getHdList(Base.currXn,
				Base.currXq));

		return mapping.findForward("hdglManage");
	}

	/**
	 * 活动审核
	 * 
	 * @throws Exception
	 */
	public ActionForward hdglSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		String tableName = "view_zgdd_hdshb";
		String realTable = "zgdd_shgz_hdshb";
		String title = "社会工作 - 社团活动管理 - 活动审核";
		String shlx = request.getParameter("shlx");

		HdglService service = new HdglService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		HdglModel model = new HdglModel();
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();

		String pk = request.getParameter("pk");
		String doType = request.getParameter("doType");
		if (!Base.isNull(shlx)) {

			BeanUtils.copyProperties(model, myForm);

			boolean result = service.saveHdsh(pk, userType, shlx, model,
					request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		String[] colList = new String[] { "xh", "hddm", "hdmc", "xm", "xb",
				"xn","xq", "xqmc", "xymc", "zymc", "bjmc", "bz", "mzmc", "xyyj",
				"xxyj" };
		HashMap<String, String> rs = service.getXsjbxx(tableName, colList,
				"pk", pk);
		model.setHddm(rs.get("hddm"));
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxShgzHdsh.do");
		request.setAttribute("needXy", service.needXy(pk, "view"));
		request.setAttribute("pk", pk);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("doType", doType);

		unit.setNjXyZyBjList(request, myForm);

		return mapping.findForward("hdglHdsh");
	}

	/**
	 * 活动申请
	 * 
	 * @throws Exception
	 */
	public ActionForward hdglHdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		String tableName = "view_zgdd_hdbzd";
		String realTable = "zgdd_shgz_hdbzd";
		String title = "社会工作 - 社团活动管理 - 活动申请";
		String doType = request.getParameter("doType");

		HdglService service = new HdglService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		HdglModel model = new HdglModel();
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();

		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "此功能只对学生开放！");
			return new ActionForward("/errMsg.do", false);
		}

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			BeanUtils.copyProperties(model, myForm);

			String hddm = myForm.getHddm();
			model.setXh(userName);
			model.setXq(Base.currXq);
			model.setXn(Base.currXn);
			String[] zdyZd = new String[0];
			String[] zdyZdz = new String[0];

			if (service.hadSq(model)) {
				String msg = "你已经申请过本次活动，不能重复申请，请确认";
				request.setAttribute("msg", msg);
			} else {
				List<HashMap<String, String>> list = service.getZdyZd(hddm);

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

				boolean result = service
						.saveHdsq(model, zdyZd, zdyZdz, request);

				if (result) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			}
		}
		String[] colList1 = new String[] { "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "bz" };
		HashMap<String, String> rs1 = service.getXsjbxx("view_xsjbxx",
				colList1, "xh", userName);
		String[] colList2 = new String[] { "mzmc" };
		HashMap<String, String> rs2 = service.getXsjbxx("view_xsxxb", colList2,
				"xh", userName);

		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxShgzHdsq.do");

		request.setAttribute("rs1", rs1);
		request.setAttribute("rs2", rs2);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqV", Base.currXq);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("hdList", service.getHdList(Base.currXn,
				Base.currXq));

		unit.setNjXyZyBjList(request, myForm);

		return mapping.findForward("hdglHdsq");
	}

	/**
	 * 活动申请结果
	 * 
	 * @throws Exception
	 */
	public ActionForward hdglHdjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		HdglService service = new HdglService();
		ZgdzdxShgzForm myForm = (ZgdzdxShgzForm) form;
		ZgdzdxShgzUnit unit = new ZgdzdxShgzUnit();
		HdglModel model = new HdglModel();

		String tableName = "view_zgdd_hdshb";
		String realTable = "zgdd_shgz_hdshb";
		String title = "社会工作 - 社团活动管理 - 活动申请结果";
		String doType = "jg";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		unit.setNjXyZyBjList(request, myForm);

		boolean result = false;

		if ("stu".equalsIgnoreCase(userType)) {
			String xh = userName;
			HashMap<String, String> stuMap = service.getStuXxForXh(xh);

			myForm.setXh(stuMap.get("xh"));
		}
		//myForm.setXn(Base.currXn);
		//myForm.setXq(Base.currXq);
		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = null;

			String pk = myForm.getHddm() + myForm.getXn() + myForm.getXq();

			if ("yes".equalsIgnoreCase(service.needXy(pk, "search"))) {
				colList = new String[] { "pk", "xh", "xb", "xm", "xymc",
						"zymc", "bjmc", "hdmc", "xn", "xqmc", "xyshimg",
						"xxshimg" };
				request.setAttribute("needXy", "yes");
			} else {
				colList = new String[] { "pk", "xh", "xb", "xm", "xymc",
						"zymc", "bjmc", "hdmc", "xn", "xqmc", "xxshimg" };
				request.setAttribute("needXy", "no");
			}

			topTr = service.getTopTr(tableName, colList);
			rs = service.getHdglList(tableName, model, colList, "", "search");

			if (!"stu".equalsIgnoreCase(userType)) {
				myForm.setXh(DealString.toGBK(myForm.getXh()));
				myForm.setXm(DealString.toGBK(myForm.getXm()));
			}
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zgdzdxShgzHdjg.do");

		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		List<HashMap<String, String>> xnList = service.getXnList();
		xnList.remove(0);
		request.setAttribute("xnVList", xnList);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("doType", doType);
		request.setAttribute("hdList", service.getHdList());

		return mapping.findForward("hdglHdjg");
	}
}
