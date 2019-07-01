package xgxt.pjpy.zjcm.xwjxj;

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

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;
import xgxt.pjpy.zjcm.xnjxj.XnjxjModel;
import xgxt.pjpy.zjcm.xnjxj.XnjxjService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.zjcm.WjcfZjcmService;

public class XwjxjAction extends DispatchAction {

	/**
	 * 校外奖学金审核管理
	 * 
	 * @throws Exception
	 */
	public ActionForward shManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		XwjxjService service = new XwjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit util = new PjpyZjcmUnit();
		XwjxjModel model = new XwjxjModel();

		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String title = "评奖评优 - 奖学金 - 校外奖学金审核";
		String doType = request.getParameter("go");
		String shzt = request.getParameter("lb");

		String[] checkVal = myForm.getCheckVal();

		boolean canWrite = false;

		//用于判断非学校用户不能进入该模块
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(request.getSession()
				.getAttribute("userType").toString())
				|| "true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(request
						.getSession().getAttribute("userType").toString())) {
			request.setAttribute("errMsg", "当前用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.updateXwjxjshJg(checkVal,shzt);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
//			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
//					"xn", "xqmc", "jxjmc","xysh", "xxsh" };
//			if ("xy".equalsIgnoreCase(userType)) {
//				colList = new String[] { "pk", "xh", "xm", "xb", "bjmc", "xn",
//						"xqmc", "jxjmc", "xysh" };
//			}
//
//			topTr = service.getTopTr(tableName, colList);
//			rs = service.getJxjSqList(tableName, model, colList, userType);
			topTr = service.queryXwjxjshTitle();
			rs = (ArrayList<String[]>)service.queryXwjxjshResult(model);
			if (StringUtils.isNotNull(myForm.getJxjdm())) {

				String sxrs = service.getJxjRs(myForm.getJxjdm());
				request.setAttribute("jxjrs", sxrs);
				request.setAttribute("hdrs", service.getHdJxjRs(myForm.getJxjdm()));
			}
		
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "zjcm_pjpy_xwjxjsh.do");
		request.setAttribute("title", title);
		XnjxjService myService = new XnjxjService();
		request.setAttribute("jxjList", myService.getXnjxjList("校外%"));
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		util.setNjXyZyBjList(request, myForm);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("shManage");
	}

	/**
	 * 校外奖学金审核
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward xwJxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		XwjxjService service = new XwjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		XwjxjModel model = new XwjxjModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "评奖评优 - 奖学金 - 校外奖学金审核";
		String doType = request.getParameter("doType");
		String cjsh = request.getParameter("cjsh");
		String pk = request.getParameter("pk").trim();
		String userType = session.getAttribute("userType").toString();
		//String userName = session.getAttribute("userName").toString();

		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {
			String shzt = request.getParameter("shzt");
			boolean result = service.saveJxjsh(model, pk, shzt, userType,
					request);
			request.setAttribute("result", result);
		}

		if(!Base.isNull(cjsh)){
			request.setAttribute("cjsh", cjsh);
		}
		map = service.getSbJxjXx(pk);

		List<HashMap<String, String>> wjcfList = service.getWjcfList(map
				.get("xh"));
		unit.setNjXyZyBjList(request, myForm);

		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("jsjList", service.getJsjList());
		request.setAttribute("jxjList", service.getJxjList("校"));
		request.setAttribute("wjcfList", wjcfList);
		if (wjcfList != null && wjcfList.size() > 0) {
			request.setAttribute("cfqk", wjcfList.size());
		}
		request.setAttribute("title", title);
		request.setAttribute("pk", pk);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", map);
	
		return mapping.findForward("xwJxjSh");
	}
	
	/**
	 * 校外奖学金单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xwjxjshDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		XwjxjService service = new XwjxjService();
		
//		保存数据操作
		if ("save".equalsIgnoreCase(request.getParameter("go"))) {
			XwjxjModel model = new XwjxjModel();
			BeanUtils.copyProperties(model, myForm);
			request.setAttribute("result", service.updateXwjxjDgxx(model, pkValue));
		} else if ("view".equalsIgnoreCase(request.getParameter("go"))) {
			request.setAttribute("writa", "view");
		}
		
		if (StringUtils.isNotNull(pkValue)) {
			rs = service.queryXwjxjDgxx(pkValue) ;
			WjcfZjcmService myService = new WjcfZjcmService();
			request.setAttribute("cfList", myService.queryStuCfxx(rs.get("xh")));
		}
		myForm.setXxsh(rs.get("xxsh"));
		myForm.setXxyj(rs.get("xxyj"));
		request.setAttribute("rs", rs);
		
		request.setAttribute("pkValue", pkValue);

		return mapping.findForward("xwjxjshDetails");
	}
}
