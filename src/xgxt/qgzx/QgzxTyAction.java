package xgxt.qgzx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧҳ����ת-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class QgzxTyAction extends DispatchAction {

	/**
	 * �ڹ���ѧ - ��ʱ��λ - ��λ����
	 * 
	 * @return ActionForward
	 */
	public ActionForward lsgwfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			forward = "/gzdx_qgzx_lsgw_fp.do";
		} else {
			forward = "/gzdx_qgzx_lsgw_fp.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �ڹ���ѧ - ��ʱ��λ - �����ѯ
	 * 
	 * @return ActionForward
	 */
	public ActionForward lsgwjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			forward = "/gzdx_qgzx_lsgw_jg.do";
		} else {
			forward = "/gzdx_qgzx_lsgw_jg.do";
		}
		return new ActionForward(forward, false);
	}
}
