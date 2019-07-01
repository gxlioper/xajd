package xgxt.xsxx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xsxx.form.XsxxRstjForm;
import xgxt.xsxx.service.XsxxRstjService;

import com.zfsoft.basic.BasicAction;

/**
 * 
* 
* �����ƣ�XsxxRstjAction 
* �������� ѧ����Ϣ����ͳ��Action  
* �����ˣ�yijd 
* ����ʱ�䣺2012-7-05 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class XsxxRstjAction  extends BasicAction {
	/**
	 * ����ͳ�Ʋ鿴
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rstjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xsxx_rstjcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rstjCx");
	}
	
	
	/**
	 * ѧ����������
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rstjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxRstjService service=new XsxxRstjService();
		String doType = request.getParameter("doType");
		XsxxRstjForm model=(XsxxRstjForm)form;
		if("xqxynj".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.xqxynj(model, response.getOutputStream());
			
		}else if("ssccxbmc".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.ssccxbmc(model, response.getOutputStream());
			
		}else if("xyzynjxb".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.xyzynjxb(model, response.getOutputStream());
			
		}else if("xyzynj".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.xyzynj(model, response.getOutputStream());
			
		}
		return mapping.findForward("");
	}
}
