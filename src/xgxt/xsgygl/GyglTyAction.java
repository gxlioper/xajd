package xgxt.xsgygl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����ҳ����ת-action��
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

public class GyglTyAction extends DispatchAction {

	/**
	 * ��Ԣ����_��Դ��ά��_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward fykForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
			forward = "/ssxx_search.do?act=dormInfo";
		} else {
			forward = "/ssxx_search.do?act=dormInfo";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��Ԣ����_��������(����)_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfpSsForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = Base.xxdm;
		String forward = "";

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
			forward = "/tybb_gygl_cxfp_ss.do";
		} else {
			forward = "/tybb_gygl_cxfp_ss.do";
		}
		return new ActionForward(forward, false);
	}

	// ======================��Ԣ����================================
	/**
	 * ��������_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward bxsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			forward = "/zjcm_gygl_bxsq.do";
		} else {
			forward = "/zjcm_gygl_bxsq.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * �������_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward bxshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			forward = "/zjcm_gygl_bxsh.do";
		} else {
			forward = "/zjcm_gygl_bxsh.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * ���޽��_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward bxjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			forward = "/zjcm_gygl_bxjg.do";
		} else {
			forward = "/zjcm_gygl_bxjg.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * ����ͳ��_��ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward bxtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
			forward = "/zjcm_gygl_bxtj.do";
		} else {
			forward = "/zjcm_gygl_bxtj.do";
		}

		return new ActionForward(forward, false);
	}

	// ======================��Ԣ���� end================================

	// ======================�������================================
	/**
	 * ��������
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcSzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_cssz.do";

		return new ActionForward(forward, false);
	}

	/**
	 * �հױ���
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcBbForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_kbbb.do";

		return new ActionForward(forward, false);
	}

	/**
	 * ������¼��
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcLrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_fslr.do";

		return new ActionForward(forward, false);
	}

	/**
	 * �����ֲ�ѯ
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcCxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_fscx.do";

		return new ActionForward(forward, false);
	}

	/**
	 * ��������
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcTsForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String forward = "/ty_gygl_wsjc_tsqs.do";
		//
		// return new ActionForward(forward, false);

		String msg = "��ģ�齫�ڶ��ڽ��п�������ȷ�ϣ�";
		request.setAttribute("yhInfo", msg);
		return new ActionForward("/yhInfo.do", false);
	}

	/**
	 * ������ͳ��
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcTjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_fstj.do";

		return new ActionForward(forward, false);
	}
	// ======================������� end================================
	
	/**
	 * �������ѧ������¼��
	 * 
	 */
	public ActionForward wsjcXsfslrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/ty_gygl_wsjc_xsfslr.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * �������ѧ�������鿴
	 * 
	 */
	public ActionForward wsjcXsfsckForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/ty_gygl_wsjc_xsfsck.do";
		
		return new ActionForward(forward, false);
	}
}
