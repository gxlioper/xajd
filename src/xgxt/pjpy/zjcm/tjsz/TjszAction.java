package xgxt.pjpy.zjcm.tjsz;

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
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;
import xgxt.utils.FormModleCommon;

public class TjszAction extends DispatchAction {

	/**
	 * 条件设置管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String tableName = "";
		String realTable = "zjcm_pjpy_tjsz";
		String title = "";
		String userType = session.getAttribute("userType").toString();
		String szlx = request.getParameter("szlx");
		String doType = request.getParameter("doType");
		szlx = Base.isNull(szlx) ? "jxj" : szlx;

		boolean result = false;

		TjszService service = new TjszService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		TjszModel myModel = new TjszModel();
		PjpyZjcmUnit unit = new PjpyZjcmUnit();

		myForm.setSzlx(szlx);
		BeanUtils.copyProperties(myModel, myForm);

		if ("rych".equalsIgnoreCase(szlx)) {
			title = "评奖评优 - 条件设置 - 荣誉称号";
		} else if ("xjbj".equalsIgnoreCase(szlx)) {
			title = "评奖评优 - 条件设置 - 先进班级";
		} else if ("jxj".equalsIgnoreCase(szlx)) {
			title = "评奖评优 - 条件设置 - 奖学金";
		}
		
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			result = service.saveTjsz(myModel, request);
			request.setAttribute("result", result);
		}
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String pk = request.getParameter("pk");
			result = service.delTj(pk, request);
			request.setAttribute("result", result);
		}

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "szlx", "xn", "jxjdm",
					"tjzd", "szmc", "jxjmc", "tjmc", "tjlx", "tjz" };

			rs = service.getTjList(myModel, colList);
			myForm.setTjz(DealString.toGBK(myForm.getTjz()));
		}

		request.setAttribute("path", "prise_condition.do");
		unit.setNjXyZyBjList(request, myForm);
		// unit.setTj(request, myForm);
		FormModleCommon.commonRequestSet(request, tableName, realTable, null,
				topTr);
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("title", title);
		request.setAttribute("szlx", szlx);
		request.setAttribute("rs", rs);
		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("zdList", service.getZdList(szlx));
		if("rych".equalsIgnoreCase(szlx)){
			request.setAttribute("jxjList", service.getRychList());
		}else{
			request.setAttribute("jxjList", service.getJxjList());
		}
		request.setAttribute("userType", userType);

		return mapping.findForward("tjszManage");
	}
	
	/**
	 * 统计报表打印
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjbbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		TjszService service = new TjszService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		TjszModel model = new TjszModel();

		String title = "评奖评优 - 统计分析 - 统计报表";
		String doType = request.getParameter("doType");

		String bblx = myForm.getBblx();

		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("xydm", userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) &&"print".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			if ("xyzh".equalsIgnoreCase(bblx)) {
				service.printXyzhExcel(model, response.getOutputStream());
			}else if("pjmd".equalsIgnoreCase(bblx)){
				service.printXyPymdExcel(model, response.getOutputStream());
			}
			return null;
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "zjcm_pjpy_tjbb.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		unit.setNjXyZyBjList(request, myForm);


		return mapping.findForward("tjbbPrint");
	}
}
