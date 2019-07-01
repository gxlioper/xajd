package xgxt.xsgygl.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.xsgygl.GyglTyForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 通用版本公寓管理-action类
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

public class GyglCommAction extends BasicAction {

	/**
	 * 学生资助_项目维护_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxfpSsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglCommService service = new GyglCommService();
		GyglTyForm myForm = (GyglTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gygl_cxfp_ss";
		// 表名
		String realTable = "ssfpb";
		myForm.setTableName(realTable);
		// 访问路径
		String path = "gygl_cxfp_ss.do";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================执行撤销操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delSsfp(myForm);
			message = result ? "撤销成功" : "撤销失败";
		}
		// =================end ===================

		// ==================执行批量撤销操作 ==================
		if ("plcx".equalsIgnoreCase(doType)) {
			boolean result = service.delSsfpPl(myForm);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "nj", "xymc", "xqmc", "ldmc", "cs",
					"qsh" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message" };
		// 其他字段值
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "cxfp_ss");
		// =================end ===================

		return mapping.findForward("cxfpSsManage");
	}

}