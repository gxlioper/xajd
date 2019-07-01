package xgxt.qgzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.qgzx.QgzxGbmygExcelActionForm;
import xgxt.qgzx.QgzxGbmygExcelService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学-酬金发放-各部门用工表统计-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author HongLin
 * @version 1.0
 */
public class QgzxGbmygExcelAction extends BasicAction {

	public ActionForward gbmygbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGbmygExcelService service = new QgzxGbmygExcelService();
		QgzxGbmygExcelActionForm myForm = (QgzxGbmygExcelActionForm) form;
		//String searchType = "cwtj";		
		MessageResources message = getResources(request);
		//高级查询
		//String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		//myForm.getSearchModel().setPath(path);								
		//调用统计制作表格的方法
		String doType = request.getParameter("doType");
		if("exp".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printGbmyg(myForm,message,request,response);
			return null;
		}
		//调用工资发放制作表格的方法
		if("gzff".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printGzff(myForm,message,request,response);
			return null;
		}
		return mapping.findForward("gbmygbtj");
		
	}
	
	// -------------------2012.3.13 QLJ 临时岗位统计----------------------
	
	/**
	 * 浙江建设学院临时岗位酬金统计
	 */
	public ActionForward lsgwTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGbmygExcelService service = new QgzxGbmygExcelService();
		QgzxGbmygExcelActionForm myForm = (QgzxGbmygExcelActionForm) form;
		//String searchType = "cwtj";		
		MessageResources message = getResources(request);
	
		response.setContentType("application/vnd.ms-excel");
		service.printLsgwcjInfo(myForm,message,request,response);
		return null;
		
		
	}
}
