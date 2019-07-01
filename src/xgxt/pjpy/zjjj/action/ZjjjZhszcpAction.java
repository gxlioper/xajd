package xgxt.pjpy.zjjj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.pjpy.zjjj.form.ZjjjZhszcpActionForm;
import xgxt.pjpy.zjjj.service.ZjjjZhszcpService;
import xgxt.wjcf.wjcfutils.CommonAction;

public class ZjjjZhszcpAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	private ZjjjZhszcpService zhcpService = ZjjjZhszcpService.getInstance();// 综合测评SERVICE
	
	/**
	 * 德育成绩维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZjjjZhszcpActionForm zhcpForm = (ZjjjZhszcpActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zhcpForm.setXydm(xydm);
		}
		zhcpForm.setXm(DealString.toGBK(zhcpForm.getXm()));
		zhcpForm.setDycj(DealString.toGBK(zhcpForm.getDycj()));
		request.setAttribute("dycjList", zhcpService.dycjList());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("dycjpage");
	}

	
}
