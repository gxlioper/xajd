package common.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


public class SystemExceptionHandler extends ExceptionHandler {

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		ActionMessage error;
		ActionForward forward;
		
		if (null != ae.getPath()){
			forward = new ActionForward(ae.getPath());
		} else {
			forward = mapping.getInputForward();
		}
		
		if (ex instanceof SystemException){
			SystemException se = (SystemException) ex;
			
			String key = se.getKey();
			if (null == key || "".equals(key.trim())){
				error = new ActionMessage(ae.getKey(), ex.getMessage());
			} else {
				if (null != se.getValues()){
					error = new ActionMessage(key,se.getValues());
				} else {
					error = new ActionMessage(key);
				}
			}
			
			storeException(request, key, error, forward, ae.getScope());
			return forward;
		}
		
		
		return super.execute(ex, ae, mapping, formInstance, request, response);
	}

}
