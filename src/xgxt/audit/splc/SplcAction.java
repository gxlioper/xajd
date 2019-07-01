package xgxt.audit.splc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import com.zfsoft.basic.BasicAction;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批流程Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SplcAction extends BasicExtendAction {
	
	
	public ActionForward splcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SplcService service = new SplcService();
		SplcForm myForm = (SplcForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		HashMap<String, String> splcMap = new HashMap<String, String>();
		if( doType != null && !"".equals(doType) && !"del".equals(doType)){
			if ("save".equalsIgnoreCase(doType)) {
				result = service.addSplc(myForm);
				request.setAttribute("result", result);
			} else if ("modi".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				result = service.modiSplc(myForm);
				request.setAttribute("result", result);
			} else if ("update".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				splcMap = service.getSplc(myForm);
			}
			request.setAttribute("doType", doType);
			request.setAttribute("rs", splcMap);
			return mapping.findForward("splcUpdate");
		}
		
		if("del".equalsIgnoreCase(doType)){
			result=service.delSplc(myForm);
			request.setAttribute("result", result);
		}

		request.setAttribute("rs", service.getSplcList(myForm));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr(myForm));
		// write和titile
		setWriteAbleAndTitle(request, "xtwhSplc.do?method=splcManage");
		
		return mapping.findForward("splcwh");
	}
	

}
