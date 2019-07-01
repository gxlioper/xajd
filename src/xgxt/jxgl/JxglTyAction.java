package xgxt.jxgl;

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
 * Description: ��ѵ����ҳ����ת-action��
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

public class JxglTyAction extends DispatchAction {
	
	/**
	 * ��ѵ���۳ɼ�������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxllcjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (JxglYw.IsJwTb(xxdm)) {// �жϳɼ������Ƿ���Ҫ��������ͬ��
			forward = "/zjcm_jxgl_jxcj.do";
		} else {
			forward = "/jxglgt.do?method=ArmyIntoAchievement";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * ��ѵ���ܳɼ�������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjncjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			forward = "/gzdx_jxgl_jxjncj.do";
		} else {
			forward = "/gzdx_jxgl_jxjncj.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��ѵ�ɼ�������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxcjckForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			forward = "/gzdx_jxgl_jxcjck.do";
		} else {
			forward = "/gzdx_jxgl_jxcjck.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_������ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
			forward = "/gzdx_jxgl_mhxsq.do";
		} else {
			forward = "/gzdx_jxgl_mhxsq.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_�����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
			forward = "/gzdx_jxgl_mhxsh.do";
		} else {
			forward = "/gzdx_jxgl_mhxsh.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_�����ת
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
			forward = "/gzdx_jxgl_mhxjg.do";
		} else {
			forward = "/gzdx_jxgl_mhxjg.do";
		}
		return new ActionForward(forward, false);
	}
}
