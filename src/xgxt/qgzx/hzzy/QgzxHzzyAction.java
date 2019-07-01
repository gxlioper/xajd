package xgxt.qgzx.hzzy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;


public class QgzxHzzyAction extends DispatchAction {

	/**
	 * 勤工助学申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qgzxsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		QgzxHzzyActionForm hzzyForm = (QgzxHzzyActionForm) form;
		QgzxHzzyService service = new QgzxHzzyService();
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();
		HashMap<String, String> resMap = service.getXnnd();
		String xq = resMap.get("xq");
		xq = StringUtils.isNull(xq) ? "" : (StringUtils.isEqual(xq, "01") ? "第二学期" : "第一学期");
		resMap.put("xq", xq);
		request.setAttribute("rs", resMap);
		request.setAttribute("userType", sUserType);
		return mapping.findForward("qgzxsqb");
	}

}
