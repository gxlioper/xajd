package xgxt.pjpy.ycsf;

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

import xgxt.action.Base;
import xgxt.pjpy.zjlg.ZjlgPjpyForm;
import xgxt.pjpy.zjlg.ZjlgPjpyModel;
import xgxt.pjpy.zjlg.ZjlgPjpyService;
import xgxt.pjpy.zjlg.ZjlgPjpyUnit;

public class YcsfPjpyAction {
	
	/**
	 * @describe 综合素质测评分维护
	 * @author L
	 * @throws Exception
	 */
	public ActionForward dycpfPsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();

		if (service.isBzrBj(userName)) {
			if (service.isDySz(userName, Base.currXn)) {
				return new ActionForward("/zjlgPjpy.do?method=dycpfPsfSz",
						false);
			}
		}

		String tableName = "view_zjlg_dypsf";
		String realTable = "zjlg_dypsf";
		String title = "评奖评优 - 德育测评 - 平时分维护";

		// 班主任所在班级
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		boolean result = false;

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

			if ("teacher".equalsIgnoreCase(userType)) {
				result = service.savePsf(myModel,userType);
			} else if ("xy".equalsIgnoreCase(userType)) {
				result = service.savePsfXyfjf(myModel);
			} else if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				result = service.savePsf(myModel,userType);
			}
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
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "dqxn",
					"zwpyf", "bjpyf", "xyfjf", "xysh" };

			if (userType.equalsIgnoreCase("teacher")) {
				colList = new String[] { "xh", "xm", "xb", "bjmc", "dqxn",
						"zwpyf", "bjpyf", "xyfjf" };
			}
			if ("xy".equalsIgnoreCase(userType)) {
				myModel.setXydm(userDep);
			}
			topTr = service.getTopTr(tableName, colList);
			rs = service.getPsfList(myModel, myForm, userName, colList,
					userType);

		}

		request.setAttribute("path", "zjlgPjpy.do?method=dycpfPsf");
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfPsf");
	}
}
