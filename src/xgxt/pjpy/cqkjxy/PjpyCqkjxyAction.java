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
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: ����Ƽ�ѧԺ��������Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
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
	 * ����Ա��˽�ѧ��
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
			request.setAttribute("result", "view");// ͨ����
		}
		String[] colList = new String[] { "xn||nd||xh||jxjdm", "ND", "XN",
				"XH", "XM", "NJ", "XYMC", "ZYMC", "BJMC", "XB", "JXJMC",
				"yesNo", "TJFLAG", "DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW",
				"cjmc", "zhpfmc", "xq" };
		String[] rs = service
				.getFdyShQry("xn||nd||xh||jxjdm", pkValue, colList);
		for (int i = 0; i < colList.length; i++) {// ���ҳ����Ϣ
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			request.setAttribute(colList[i], rs[i]);
		}
		pjpyForm.setYesNo(rs[11]);// ����ҳ�����״̬
		request.setAttribute("pjpyForm", pjpyForm);
		request.setAttribute("chkList", service.getChkList(3));// ����б�
		return mapping.findForward("jxjfdysh");
	}
}
