package xgxt.studentInfo.zjlg.dwjl;

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
import xgxt.studentInfo.zjlg.ZjlgXsxxForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class DwjlAction extends DispatchAction {

	/**
	 * 对外交流管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward dwjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		DwjlService service = new DwjlService();
		ZjlgXsxxForm myForm = (ZjlgXsxxForm) form;
		// BjlhGyglUnit unit = new BjlhGyglUnit();
		DwjlModel model = new DwjlModel();

		String title = "学生信息 - 学生信息维护 - 对外交流";
		String doType = request.getParameter("doType");
		String tableName = "view_zjlg_dwjl";
		String realTable = "zjlg_dwjl";
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delDwgx(checkVal);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
				"xn", "xqmc", "xmmc", "cgsj", "hgsj" };

		topTr = SearchUtils.getTopTr(tableName, colList, null);
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {			
			rs = service.getDwjlList(tableName, model, colList);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//无记录时，初始化总页数和总记录数
			myForm.getPages().setMaxPage(0);
			myForm.getPages().setMaxRecord(0);
		}
		//=========end=======
		
		// 存放访问路径求取读写权
		request.setAttribute("path", "zjlg_xsxx_dwjl.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("jlxmList", service.getJlxm());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("dwjlManage");
	}

	/**
	 * 对外交流维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward dwjlUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "学生信息 - 学生信息维护 - 对外交流";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		
		DwjlService service = new DwjlService();
		ZjlgXsxxForm myForm = (ZjlgXsxxForm) form;
		DwjlModel model = new DwjlModel();
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			if (Base.isNull(xh)) {
				rs.put("xh", " ");
			} else {
				rs = service.getStuInfo(xh);
			}
		}
		if ("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getDwjlInfo(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveDwjl(model, request);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("jlxmList", service.getJlxm());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("dwjlUpdate");
	}
}
