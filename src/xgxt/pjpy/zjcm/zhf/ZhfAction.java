package xgxt.pjpy.zjcm.zhf;

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
import xgxt.pjpy.zjcm.zhf.ZhfService;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;

public class ZhfAction extends DispatchAction {

	/**
	 * 综合素质分管理
	 * 
	 * @throws Exception
	 */
	public ActionForward zhfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZhfService service = new ZhfService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		ZhfModel model = new ZhfModel();

		String tableName = "view_zjcm_zhf";
		String realTable = "zjcm_zhf";
		String title = "评奖评优 - 信息维护 - 综合素质分维护";
		boolean canWrite = false;

		if ("stu".equalsIgnoreCase(userType)) {
			if (!service.isCpzCy(userName, userDep)) {
				String msg = "你不是测评小组成员，无法访问";
				request.setAttribute("msg", msg);
			} else {
				if (!service.inTime(userDep)) {
					String msg = "现在不在所设置的分数填写时间范围内，无法操作";
					request.setAttribute("msg", msg);
				}
			}
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
			request.setAttribute("xydm", userDep);
			myForm.setXydm(userDep);
			myForm.setZydm(service.getStuInfo(userName).get("zydm"));
			myForm.setBjdm(service.getStuInfo(userName).get("bjdm"));
		}

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

			result = service.saveZhf(model);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("js")) {

			String xn = myForm.getXn();
			String xq = myForm.getXq();
			result = service.jsZhf(xn, xq);

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
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xq", "dyf", "zyf", "tyf", "nlf" };
			if (canWrite) {
				colList = new String[] { "xh", "xm", "xb", "bjmc", "xn", "xq",
						"dyf", "zyf", "tyf", "nlf", "zhf" };
			}

			topTr = service.getTopTr(tableName, colList);
			rs = service.getZhfList(tableName, model, colList);

			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zjlgPjpy.do?method=zhszcp");
		request.setAttribute("title", title);
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, "view_zjcm_zhf_ex", realTable, rs, topTr);

		return mapping.findForward("zhfManage");
	}
	
	/**
	 * 综合分维护
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward zhfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfService service = new ZhfService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		ZhfModel model = new ZhfModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "评奖评优 - 信息维护 - 综合素质分比例设置";
		String doType = request.getParameter("doType");

		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {
			boolean result = service.saveZhfBl(model, request);

			request.setAttribute("result", result);
		}

		map=service.getZhfBl();
		
		unit.setNjXyZyBjList(request, myForm);

		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map );
		return mapping.findForward("zhfUpdate");
	}
}
