package xgxt.pjpy.cqkjxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 重庆科技学院评奖评优Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李涛
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-05-19
 * </p>
 */
public class PjpyCqkjxyAction extends DispatchAction {

	/**
	 * 辅导员审核奖学金
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveFdyShXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCqkjxyActionForm pjpyForm = (PjpyCqkjxyActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkVal"));
		PjpyCqkjxyFdyshModel cqkjxyfdyshModel = new PjpyCqkjxyFdyshModel();
		PjpyCqkjxyService service = new PjpyCqkjxyService();
		BeanUtils.copyProperties(cqkjxyfdyshModel, pjpyForm);
		boolean flag = false;
		flag = service.saveFdyShXs(cqkjxyfdyshModel, "xn||nd||xh||jxjdm",
				pkValue, request);
		if (flag) {
			request.setAttribute("result", "view");// 通过后
		}
		String[] colList = new String[] { "xn||nd||xh||jxjdm", "ND", "XN",
				"XH", "XM", "NJ", "XYMC", "ZYMC", "BJMC", "XB", "JXJMC",
				"yesNo", "TJFLAG", "DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW",
				"cjmc", "zhpfmc", "xq" };
		String[] rs = service
				.getFdyShQry("xn||nd||xh||jxjdm", pkValue, colList);
		for (int i = 0; i < colList.length; i++) {// 存放页面信息
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			request.setAttribute(colList[i], rs[i]);
		}
		pjpyForm.setYesNo(rs[11]);// 设置页面审核状态
		request.setAttribute("pjpyForm", pjpyForm);
		request.setAttribute("chkList", service.getChkList(3));// 审核列表
		return mapping.findForward("jxjfdysh");
	}
}
