package xgxt.wjcf.hhgxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;

public class WjcfHhgxyAction extends DispatchAction {

	WjcfHhgxyService service =WjcfHhgxyService.getInstance();
	
	/**
	 * 预先告知书打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfbPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		@SuppressWarnings("unused")
		String cflb = request.getParameter("cflb");
		@SuppressWarnings("unused")
		String cfyy = request.getParameter("cfyy");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {//申报时打印
			/*rs = service.wjcfysbPrint(xh, cflb, cfyy,
					pkValue); */
			
		}
		if (!StringUtils.isNull(pkValue)) {//审核时打印
			
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("cfbprint");
	}
	
	/**
	 * 决定书打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		request.setAttribute("rs", rs);
		return mapping.findForward("jdprint");
	}
	
	/**
	 * 申诉受理情况告知书
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		String xh = request.getParameter("xh");
		@SuppressWarnings("unused")
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		request.setAttribute("rs", rs);
		return mapping.findForward("ssPrint");
	}
	
	/**
	 * 申诉决定通知书
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssjdPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		request.setAttribute("rs", rs);
		return mapping.findForward("ssjdPrint");
	}
	
	
}
