package xgxt.pjpy.jhzy.dyf;

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
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;

public class DyfAction extends DispatchAction {

	/**
	 * 德育分管理
	 * 
	 * @throws Exception
	 */
	public ActionForward dyfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		DyfService service = new DyfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		DyfModel model = new DyfModel();

		String tableName = "view_jhzy_dyf";
		String realTable = "jhzy_dyf";
		String title = "评奖评优 - 信息维护 - 德育分维护";

		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

				result = service.saveDyf(model);
			
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xq", "dyf" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getDyfList(tableName, model, colList);
		}
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzy_pjpy_dyf.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, "view_jhzy_dyf_ex", realTable, rs, topTr);

		return mapping.findForward("dyfManage");
	}
	
	/**
	 * 体育分管理
	 * 
	 * @throws Exception
	 */
	public ActionForward tyfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		DyfService service = new DyfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		DyfModel model = new DyfModel();

		String tableName = "view_jhzy_tyf";
		String realTable = "jhzy_tyf";
		String title = "评奖评优 - 信息维护 - 体育分维护";

		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

				result = service.saveTyf(model);
			
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xq", "tyf" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getTyfList(tableName, model, colList);
			
		}
		
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzy_pjpy_dyf.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, "view_jhzy_tyf_ex", realTable, rs, topTr);
		
//		myForm.setXn(xn);
//		myForm.setXq(xq);

		return mapping.findForward("tyfManage");
	}
	
	/**
	 * 技能分管理
	 * 
	 * @throws Exception
	 */
	public ActionForward jnfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		DyfService service = new DyfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		DyfModel model = new DyfModel();

		String tableName = "view_jhzy_jnf";
		String realTable = "jhzy_jnf";
		String title = "评奖评优 - 信息维护 - 技能分维护";

		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

				result = service.saveJnf(model);
			
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xq", "jnf" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getJnfList(tableName, model, colList);
		}
		
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzy_pjpy_dyf.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, "view_jhzy_jnf_ex", realTable, rs, topTr);
		
		return mapping.findForward("jnfManage");
	}
}
