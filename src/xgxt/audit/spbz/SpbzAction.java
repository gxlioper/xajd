package xgxt.audit.spbz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.audit.splc.SplcForm;
import xgxt.audit.splc.SplcService;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批步骤Action</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpbzAction extends BasicAction {
	
	public ActionForward spbzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SpbzService service = new SpbzService();
		SpbzForm myForm = (SpbzForm) form;
		String doType = request.getParameter("doType");
		String id = request.getParameter("id");
		boolean result = false;
		HashMap<String, String> spbzMap = new HashMap<String, String>();
		if( doType != null && !"".equals(doType) && !"del".equals(doType)){
			if ("save".equalsIgnoreCase(doType)) {
				result = service.addSpbz(myForm);
				request.setAttribute("result", result);
			} else if ("modi".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				result = service.modiSpbz(myForm);
				request.setAttribute("result", result);
			} else if ("update".equalsIgnoreCase(doType)) {
				myForm.setId(id);
				spbzMap = service.getSpbz(myForm);
			}
			request.setAttribute("splc", myForm.getSplc());
			request.setAttribute("spgwList", service.getSpgwList());
			request.setAttribute("doType", doType);
			request.setAttribute("rs", spbzMap);
			return mapping.findForward("spbzUpdate");
		}
		
		if("del".equalsIgnoreCase(doType)){
			result=service.delSpbz(myForm);
			request.setAttribute("result", result);
		}

		request.setAttribute("splc", myForm.getSplc());
		request.setAttribute("rs", service.getSpbzList(myForm));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr(myForm));
		request.setAttribute("title", "审批步骤");
		
		return mapping.findForward("spbzwh");
	}

}
