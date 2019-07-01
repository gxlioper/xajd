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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理页面跳转-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class JxglTyAction extends DispatchAction {
	
	/**
	 * 军训理论成绩管理跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxllcjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (JxglYw.IsJwTb(xxdm)) {// 判断成绩管理是否需要教务数据同步
			forward = "/zjcm_jxgl_jxcj.do";
		} else {
			forward = "/jxglgt.do?method=ArmyIntoAchievement";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 军训技能成绩管理跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjncjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			forward = "/gzdx_jxgl_jxjncj.do";
		} else {
			forward = "/gzdx_jxgl_jxjncj.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 军训成绩管理跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxcjckForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			forward = "/gzdx_jxgl_jxcjck.do";
		} else {
			forward = "/gzdx_jxgl_jxcjck.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 军训管理_免缓训_申请跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//贵州大学
			forward = "/gzdx_jxgl_mhxsq.do";
		} else {
			forward = "/gzdx_jxgl_mhxsq.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 军训管理_免缓训_审核跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//贵州大学
			forward = "/gzdx_jxgl_mhxsh.do";
		} else {
			forward = "/gzdx_jxgl_mhxsh.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 军训管理_免缓训_结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward mhxjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {//贵州大学
			forward = "/gzdx_jxgl_mhxjg.do";
		} else {
			forward = "/gzdx_jxgl_mhxjg.do";
		}
		return new ActionForward(forward, false);
	}
}
