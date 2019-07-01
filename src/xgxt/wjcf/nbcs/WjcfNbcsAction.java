package xgxt.wjcf.nbcs;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.wjcf.wjcfutils.WjcfActionUtils;

public class WjcfNbcsAction extends WjcfActionUtils {
	
	/**
	 * 违纪处分拟通知填写申请表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] keys = request.getParameterValues("cbv");
		WjcfNbcsService service = new WjcfNbcsService();
		//下发拟处分通知
		boolean result = service.updateXfcftz(keys);
		return new ActionForward("/stuPunishAudit.do?res=" + result);
	}

	/**
	 * 填写拟处分通知信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward writeNcftz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		//如果不是学生用户则没有读写权
		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("jswrite", "no");
		} else {
			
			WjcfNbcsService service = new WjcfNbcsService();
			String xh = request.getSession().getAttribute("userName").toString();
			List<String[]> rs = service.queryNcftzBystu(xh);
			List<HashMap<String, String>> topTr = service.getNcfxxTitle();
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			//拟处分通知记录数
			request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		}
		return mapping.findForward("writeNcftz");
	}
	
	/**
	 * 填写处分通知书
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txcftzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfNbcsActionForm nbcsForm = (WjcfNbcsActionForm) form;
		WjcfNbcsModel model = new WjcfNbcsModel();
		WjcfNbcsService service = new WjcfNbcsService();
		String pkValue = request.getParameter("pkValue");
		//保存数据操作
		if ("save".equalsIgnoreCase(request.getParameter("operType"))) {
			BeanUtils.copyProperties(model, nbcsForm);
			boolean result = service.saveNcftzxx(model);
			appendResult(result, request);
		}
		//双击查看详细信息
		HashMap<String, String> rs = service.viewNcftzxx(pkValue);
		if (rs != null) {
			nbcsForm.setSfsb(rs.get("sfsb"));
			nbcsForm.setSbsy(rs.get("sbsy"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("txcftzs");
	}
	
	/**
	 * 处理返回到页面的操作如果提示信息
	 * @param result
	 * @param request
	 */
	public void appendResult(boolean result, HttpServletRequest request) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	}
	
	/**
	 * 通知书打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		WjcfNbcsService service = new WjcfNbcsService();
		HashMap<String, String> rs = service.tzsPrint(pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("tzsPrint");
	}
	
	/**
	 * 处分呈报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cbbprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String cfpk = request.getParameter("cfpk");
		String pkValue = request.getParameter("pkValue");
		WjcfNbcsService service = new WjcfNbcsService();
		HashMap<String, String> rs = service.cbbPrint(pkValue, cfpk);
		request.setAttribute("rs", rs);
		return mapping.findForward("cbbprint");
	}
}
