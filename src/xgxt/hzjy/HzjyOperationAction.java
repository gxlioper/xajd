package xgxt.hzjy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HzjyOperationAction {

	public static ActionForward ExcellenceStuApply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
	
	public static ActionForward ExcellenceOperationApply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("success");
	}
}
