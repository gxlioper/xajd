package xsgzgl.xtwh.general.commutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_通用方法_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class CommUtilAction extends BasicAction {

	/**
	 * 检测输入值是否存在
	 * 
	 * @throws Exception
	 */
	public ActionForward checkInputIsExisting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommUtilService service = new CommUtilService();

		// 表名
		String table_name = request.getParameter("table_name");
		// 字段
		String column_name = request.getParameter("column_name");
		// 值
		String column_value = request.getParameter("column_value");
		// 条件
		String query = request.getParameter("query");

		boolean isExist = service.checkInputIsExisting(table_name, column_name,
				column_value, query);

		// response.setContentType("text/html;charset=gbk");
		response.getWriter().print(isExist);

		return null;
	}

	/**
	 * 检测表是否存在
	 * 
	 * @throws Exception
	 */
	public ActionForward checkTableIsExisting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommUtilService service = new CommUtilService();

		// 表名
		String table_name = request.getParameter("table_name");

		boolean isExist = service.checkTableIsExisting(table_name);

		// response.setContentType("text/html;charset=gbk");
		response.getWriter().print(isExist);

		return null;
	}
}
