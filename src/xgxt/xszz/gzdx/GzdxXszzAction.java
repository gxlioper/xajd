package xgxt.xszz.gzdx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.xszz.XszzTyForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 广州大学学生资助-action类
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

public class GzdxXszzAction extends BasicAction {



	/**
	 * 学生资助_条件设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GzdxXszzService service = new GzdxXszzService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xszz_tjsz";
		// 表名
		String realTable = "xszz_zztjb";
		// 访问路径
		String path = "xszz_tjsz.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// 项目代码
		myForm.setXmdm(myForm.getQueryequals_xmdm());
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;

		// =================end==================

		 // ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveTjsz(myForm, request);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xmmc", "tjsm" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "tjsz");
		// =================end ===================

		return mapping.findForward("tjszManage");
	}
}