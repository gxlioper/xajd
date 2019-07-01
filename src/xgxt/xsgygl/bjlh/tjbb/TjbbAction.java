package xgxt.xsgygl.bjlh.tjbb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.bjlh.ssfp.SsfpService;

public class TjbbAction extends DispatchAction {
	
	/**
	 * 统计报表打印
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjbbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		TjbbService service = new TjbbService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		TjbbModel model = new TjbbModel();

		String title = "公寓管理 - 统计分析 - 基本信息统计";
		String doType = request.getParameter("doType");

		BeanUtils.copyProperties(model, myForm);
		String[] xydmArr = request.getParameterValues("chkonexy");
		model.setXydmArr(xydmArr);
		SsfpService fpservice = new SsfpService();
		List<HashMap<String, String>> xyList = fpservice.getXyList();
		xyList = fpservice.appendXyList(xyList);
		
		if (!Base.isNull(doType) &&"print".equalsIgnoreCase(doType)) {
			
			String bblx = myForm.getBblx();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			if ("ssqk".equalsIgnoreCase(bblx)) {
				service.printSsqkExcel(model, response.getOutputStream());
				//service.expSsjbqkxx(model, response.getOutputStream());
			}else if("cwfp".equalsIgnoreCase(bblx)){
				service.printCwfpExcel(model, response.getOutputStream());
			}
			return null;
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_tjbb.do");
		request.setAttribute("title", title);
		request.setAttribute("ldList", service.getGyLdList());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("byndList", service.getByndList());
		
		request.setAttribute("xyList", xyList);
//		FormModleCommon.setNjXyZyBjList(request);
//		FormModleCommon.setNdXnXqList(request);


		return mapping.findForward("tjbbPrint");
	}
}
