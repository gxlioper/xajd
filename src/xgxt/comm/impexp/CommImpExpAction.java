package xgxt.comm.impexp;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 通用-action类
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

public class CommImpExpAction extends BasicAction {

	/**
	 * 导入导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward commImpExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommImpExpService service = new CommImpExpService();
		CommForm myForm = (CommForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 表名
		String realTable = "";
		// 提示信息
		String message = "";
		// 访问路径
		String path = "xgxt_data_imp.do";
		// =================end==================

		// ==================执行导入操作 ==================
		if ("imp".equalsIgnoreCase(doType)) {

			String filePath = service.upLoadFile(request, myForm
					.getUploadFile(), "comm");

			message = (String) request.getAttribute("message");
			
			if (Base.isNull(message)) {
				if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
					message = service.impInfoForNtzy(myForm, filePath);
				}else {
					message = service.impInfo(myForm, filePath);
				}
				message = Base.isNull(message) ? "导入数据成功" : message;
			}	
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {

			HashMap<String, String> map = new HashMap<String, String>();

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expInfo(myForm, map, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMessage(message);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "");
		// =================end ===================

		return mapping.findForward("commImp");
	}
}