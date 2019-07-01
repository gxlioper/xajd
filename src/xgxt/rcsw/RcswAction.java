package xgxt.rcsw;

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
 * Description: 日常事务页面跳转-action类
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

public class RcswAction extends DispatchAction {

	/**
	 * 日常事务_留言板_学生留言跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward xslyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_rcsw_xsly.do";
		} else {
			forward = "/gzdx_rcsw_xsly.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 日常事务_留言板_发布通知跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward fbtzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_rcsw_fbtz.do";
		} else {
			forward = "/gzdx_rcsw_fbtz.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_留言板_回复留言跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward hflyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_rcsw_hfly.do";
		} else {
			forward = "/gzdx_rcsw_hfly.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_留言板_回复统计跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward hftjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_rcsw_hftj.do";
		} else {
			forward = "/gzdx_rcsw_hftj.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放项目维护跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffXmwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_xmwh.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_学生发放跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffXsffForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_xsff.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_老师发放跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffLsffForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_lsff.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffFfjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_ffjg.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放查询跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffJgcxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_jgcx.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放评价跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffFfpjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_ffpj.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放评价统计跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffPjTjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_pjtj.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放人员确定
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffFfryForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_ffry.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放人员确定
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffFfryqrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "";

		forward = "/zjxy_rcsw_swff_ffryqr.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_发放人员确定
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffXmffForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_xmff.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 日常事务_实物发放_短信通知
	 * 
	 * @return ActionForward
	 */
	public ActionForward swffDxtzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		forward = "/zjxy_rcsw_swff_dxtz.do";
		
		return new ActionForward(forward, false);
	}
}
