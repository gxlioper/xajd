package xgxt.pjpy.portallet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PjpyPortalletAction extends CommonAction {

	private PjpyPortalletService service = PjpyPortalletService.getInstance();
	
	/**
	 * 学生奖学金查询信息只显示后5条
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyPortalLet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PjpyPortalletActionForm pletForm = (PjpyPortalletActionForm) form;
		HttpSession session = request.getSession();
		String xh = "";  
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		if ("stu".equalsIgnoreCase(userType)
				|| "student".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName") == null ? "" : session
					.getAttribute("userName").toString();
		}
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (!StringUtils.isNull(xh)) {
			topTr = service.titList();
			rs = service.rsList(xh);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("protalletpage");
	}

	
}
