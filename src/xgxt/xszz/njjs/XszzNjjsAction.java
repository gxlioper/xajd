package xgxt.xszz.njjs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 南京技师学生资助-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class XszzNjjsAction extends BasicAction {

	/**
	 * 学生资助——学费信息维护（无修改功能，数据从财务同步）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzNjjsService service = new XszzNjjsService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================== 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType =  request.getParameter("doType");
		// 视图名
		String tableName = "view_njjs_xfxx";
		// 表名
		String realTable = "njjs_xfxxb";
		// 访问路径
		String path = "xszz_xfxx.do";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================
		
		// ==================判断模块,权限======================

		// 登陆用户为学院
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		// =================end ===================
		
		// ==================同步数据 ==================
		if ("tb".equalsIgnoreCase(doType)) {
			boolean flag = service.tbXfxx();
			message = flag ? "操作成功" : "操作失败";
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "xh", "xm", "xb", "xn", "nd", "nj",
					"xymc", "zymc", "bjmc", "xfsfxm", "xfyjje", "xfsjje", "xfsfqf", };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			StudentInfoService siService = new StudentInfoService();
			String expColumn = request.getParameter("mappingItems");
			String[] expColumns = {};
			if(StringUtils.isNotNull(expColumn)){
				expColumns = siService.getDczd(expColumn).split(",");
			}
			expPageData(request, response, null, tableName, expColumns);
			return mapping.findForward("");
		}
		// =================end ===================
		

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "xfxx");
		// =================end ===================

		return mapping.findForward("xfxxManage");
	}
}
