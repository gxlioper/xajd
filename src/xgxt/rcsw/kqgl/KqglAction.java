package xgxt.rcsw.kqgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

public class KqglAction extends BasicAction {

	private final static String SAVE = "save";
	private final static String EXP = "expData";
	private final static String QUERY = "query";
	private final static String DEL = "del";
	
	
	/**
	 * 学生考勤查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xskqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		User user = getUser(request);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		
		String tableName = "xg_rcsw_kqgl";
		String viewName = "xg_view_rcsw_kqgl";
		String[] colList = new String[] { "pkValue", "xh", "xm", "xn", "xqmc",
							"nj", "xymc","bjmc","kqmc", "kqsj" };
		String doType = request.getParameter("doType");
		
		KqglForm myForm = (KqglForm) form;
		KqglService service = new KqglService();
		BasicService basicService = new BasicService();
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		boolean isxy= false;
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		//查询
		if (QUERY.equals(doType)) {
			//selectPageDataByPagination(request, form, tableName, viewName, colList);
			request.setAttribute("rs", service.getKqglList(myForm,user,colList));
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		}
		
		//导出
		if (EXP.equals(doType)) {
			expPageData(request, response, tableName, viewName, basicService.getTableColumn(viewName));
			
			return mapping.findForward("");
		}
		
		
		request.setAttribute("kqlxList", service.getList("kqlx"));//考勤类型
		request.setAttribute("kqlxdmList", service.getList("kqlxdm"));//考勤类型
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("topTr", basicService.getColumnComment(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "xskqManage.do");
		request.setAttribute("user", user);
		request.setAttribute("userType", userType);
		request.setAttribute("isFdy", fdyQx);
		request.setAttribute("isBzr", bzrQx);
		request.setAttribute("isxy", isxy);
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xskqQuery");
	}

	
	/**
	 * 学生考勤增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xskqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqglService service = new KqglService();
		
		String tableName = "xg_rcsw_kqgl";
		String doType = request.getParameter("doType");
		
		//保存
		if (SAVE.equals(doType)) {
			insertOperation(request, tableName);
		}
		
		request.setAttribute("kqlxList", service.getList("kqlx"));//考勤类型
		request.setAttribute("kqlxdmList", service.getList("kqlxdm"));//考勤类型
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("path", "xskqManage.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xskqAdd");
	}
	
	
	
	/**
	 * 学生考勤修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xskqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_rcsw_kqgl";
		String viewName = "xg_view_rcsw_kqgl";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		KqglService service = new KqglService();
		
		//加载单条考勤记录
		if (StringUtils.isNotNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		//修改
		if (SAVE.equals(doType)) {
			updateOperation(request, tableName);
		}
		
		request.setAttribute("kqlxList", service.getList("kqlx"));//考勤类型
		request.setAttribute("kqlxdmList", service.getList("kqlxdm"));//考勤类型
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xskqManage.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xskqUpdate");
	}
	
	
	/**
	 * 学生考勤删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xskqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_rcsw_kqgl";
		String doType = request.getParameter("doType");
		
		if (DEL.equals(doType)) {
			deleteOperation(request, tableName);
		}
		
		return xskqQuery(mapping, form, request, response);
	}


	/**
	 * 学生考勤统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xskqTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();
		
		String[] topTr = null;
		String tjlb = request.getParameter("tjlb");
		String doType = request.getParameter("doType");
		
		List<String[]> rs = service.getXskqTj(tjlb, model);
		String[] kqlxTop = service.getKqlxTopTr();
		
		if ("xy".equals(userType)){
			model.setQueryequals_xydm(userDep);
		}
		
		if (StringUtils.isNull(tjlb)){
			model.setTjlb("stu");
			tjlb = "stu";
		}
		
		//按类别统计表头
		if ("xy".equals(tjlb)) {
			topTr = new String[] {Base.YXPZXY_KEY,"迟到次数","旷课学时","旷课次数","旷寝次数"};
		} else if ("zy".equals(tjlb)) {
			topTr = new String[] {Base.YXPZXY_KEY,"专业","迟到次数","旷课学时","旷课次数","旷寝次数"};
		} else if ("bj".equals(tjlb)) {
			topTr = new String[] {Base.YXPZXY_KEY,"专业","班级","迟到次数","旷课学时","旷课次数","旷寝次数"};
		} else if ("all".equals(tjlb)){
			topTr = new String[] {"迟到次数","旷课学时","旷课次数","旷寝次数"};
		} else {
			topTr = new String[] {"学号","姓名","年级",Base.YXPZXY_KEY,"班级","迟到次数","旷课学时","旷课次数","旷寝次数"};
		}
		topTr = StringUtils.joinStrArr(topTr,kqlxTop);
		
		if (EXP.equals(doType)){
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,new String[topTr.length],topTr, response.getOutputStream());
			return mapping.findForward("");
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tjlb", tjlb);
		request.setAttribute("path", "xskqTj.do");
		request.setAttribute("maxNum", model.getPages().getPageSize());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xskqTj");
	}

	
	
}
