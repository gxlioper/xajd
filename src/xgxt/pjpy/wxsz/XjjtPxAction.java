package xgxt.pjpy.wxsz;

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
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
/**
 * 先进集体
 * @author 屈朋辉
 * @version 1.0
 */
public class XjjtPxAction extends DispatchAction {
	/**
	 * 先进集体申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		XjjtService service = new XjjtService();
		XjjtForm myForm = (XjjtForm) form;
		XjjtModel model = new XjjtModel();

		String realTable = "xjjtb";
		String pk = "jtmc||xn||xq";
		String tip = "评奖评优 - 申请 - 先进集体评选";
		String doType = request.getParameter("doType");
		String pkValue = myForm.getJtmc() + Base.currXn + Base.currXq;
		String[] onezd = new String[] { "jtmc", "jtrs", "xydm", "fzls", "zysj",
				"yxyj", "xgcyj", "xyyj", "xn", "xq" };
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		BeanUtils.copyProperties(model, myForm);
		boolean result = false;
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			saveForm.setOnezd(onezd);
			result = service.saveXjjt(saveForm, model, request);
			request.setAttribute("result", result);
		}
		pkValue = request.getParameter("pkValue");
		if(!Base.isNull(pkValue)){
			model = service.getOneXjjt( model, pkValue);
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("tip", tip);
		request.setAttribute("result", result);
		request.setAttribute("doType", doType);
		request.setAttribute("pj", myForm);
		request.setAttribute("xyList", Base.getXyList());// 学院列表
		return mapping.findForward("xjjtsq");
	}
	/**
	 * 先进集体查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjjtForm myForm = (XjjtForm) form;
		XjjtService service = new XjjtService();
		XjjtModel model = new XjjtModel();
		String tableName = "view_xjjt";
		String pk = "jtmc||xn||xq";
		String tip = "评奖评优 - 申请 - 先进集体查询";
		String doType = request.getParameter("doType");
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		BeanUtils.copyProperties(model, myForm);
		// 点击查询按钮进行查询
		if ("del".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("pkValue");
			service.delXjjt(pkValue);
		}
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			String[] colList = new String[] { pk, "jtmc", "jtrs", "xymc",
					"fzls", "xysh", "xxsh" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getXjjtList(tableName, model, colList);
		}
		FormModleCommon.commonRequestSet(request, tableName, "", rs, topTr);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("tip", tip);
		request.setAttribute("pj", myForm);
		return mapping.findForward("xjjtList");
	}
	/**
	 * 先进集体审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjjtForm myForm = (XjjtForm) form;
		XjjtService service = new XjjtService();
		XjjtModel model = new XjjtModel();
		String tableName = "view_xjjt";
		String pk = "jtmc||xn||xq";
		String tip = "评奖评优 - 申请 - 先进集体审核";
		String[] colList = new String[] { pk, "jtmc", "jtrs", "xymc", "fzls",
				"xysh" };
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String flag = "";
		/*
		 * 根据用户类型进行审核
		 */
		if ("xy".equals(userType)) {
			flag = "xysh";
			myForm.setXydm(userDep);
		} else if ("xx".equals(userType) || "admin".equalsIgnoreCase(userType)) {
			colList = new String[] { pk, "jtmc", "jtrs", "xymc", "fzls",
					"xysh", "xxsh" };
			flag = "xxsh";
			myForm.setXysh("已通过");
		}

		if ("sh1".equalsIgnoreCase(doType) && !Base.isNull(pkValue)) {
			service.shXjjt(pkValue, "sh1", flag);
		} else if ("sh2".equalsIgnoreCase(doType) && !Base.isNull(pkValue)) {
			service.shXjjt(pkValue, "sh2", flag);
		}

		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		BeanUtils.copyProperties(model, myForm);
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getXjjtList(tableName, model, colList);
		}
		FormModleCommon.commonRequestSet(request, tableName, "", rs, topTr);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("pj", myForm);
		request.setAttribute("tip", tip);
		request.setAttribute("doType", "sh");
		return mapping.findForward("xjjtList");
	}
}
