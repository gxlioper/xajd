package xgxt.pjpy.jhzy.jcf;

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
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;

public class JcfAction extends DispatchAction {

	/**
	 * 奖惩分管理
	 * 
	 * @throws Exception
	 */
	public ActionForward jcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		JcfService service = new JcfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		JcfModel model = new JcfModel();

		String tableName = "view_jhzy_jcf";
		String realTable = "jhzy_jcf";
		String title = "评奖评优 - 信息维护 - 奖惩分维护";

		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("xydm", userDep);
		}
//		String xn = myForm.getXn();
//		String xq = myForm.getXq();
//		xn=Base.isNull(xn)?Base.currXn:xn;
//		xq=Base.isNull(xq)?Base.currXq:xq;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = request.getParameter("pk");
			result = service.delJcf(pk, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))|| result) {
			String[] colList = new String[] { "pk","xh", "xm", "xb", "bjmc", "xn","xqmc",
					"jcf" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getJcList(tableName, model, colList);
			
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// 存放访问路径求取读写权
		request.setAttribute("path", "jhzy_pjpy_jcf.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, "view_jhzy_jcf_ex", realTable, rs, topTr);
		
//		myForm.setXn(xn);
//		myForm.setXq(xq);

		return mapping.findForward("jcfManage");
	}
	
	/**
	 * 奖惩分维护
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward jcfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcfService service = new JcfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		JcfModel model = new JcfModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "评奖评优 - 信息维护 - 奖惩分维护";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		xh = Base.isNull(xh) ? "" : xh;
		String pkValue = request.getParameter("pkValue");
		String pk = "";
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		
		if (!Base.isNull(xn)) {
			String xqdm = service.getXqdm(DealString.toGBK(xq.trim()));
			myForm.setXn(xn.trim());
			myForm.setXq(Base.isNull(xqdm) ? xq : xqdm);
		}
		
		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {
			boolean result = service.saveJcf(model, request);
			if (result) {
				pk = model.getXh() + model.getXn()+ model.getXq();
				xh = model.getXh();
				request.setAttribute("pk", pk);
			}
			
			request.setAttribute("result", result);
		}

		map = service.getStuInfo(xh);

		unit.setNjXyZyBjList(request, myForm);

		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", Base.isNull(pk)?pkValue:pk);
		request.setAttribute("rs", map);
		return mapping.findForward("jcfUpdate");
	}
}
