package xsgzgl.xtwh.general.commutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_ͨ�÷���_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class CommUtilAction extends BasicAction {

	/**
	 * �������ֵ�Ƿ����
	 * 
	 * @throws Exception
	 */
	public ActionForward checkInputIsExisting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommUtilService service = new CommUtilService();

		// ����
		String table_name = request.getParameter("table_name");
		// �ֶ�
		String column_name = request.getParameter("column_name");
		// ֵ
		String column_value = request.getParameter("column_value");
		// ����
		String query = request.getParameter("query");

		boolean isExist = service.checkInputIsExisting(table_name, column_name,
				column_value, query);

		// response.setContentType("text/html;charset=gbk");
		response.getWriter().print(isExist);

		return null;
	}

	/**
	 * �����Ƿ����
	 * 
	 * @throws Exception
	 */
	public ActionForward checkTableIsExisting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommUtilService service = new CommUtilService();

		// ����
		String table_name = request.getParameter("table_name");

		boolean isExist = service.checkTableIsExisting(table_name);

		// response.setContentType("text/html;charset=gbk");
		response.getWriter().print(isExist);

		return null;
	}
}
