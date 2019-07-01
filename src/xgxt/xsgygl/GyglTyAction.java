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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理页面跳转-action类
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

public class GyglTyAction extends DispatchAction {

	/**
	 * 公寓管理_房源库维护_跳转
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

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
			forward = "/ssxx_search.do?act=dormInfo";
		} else {
			forward = "/ssxx_search.do?act=dormInfo";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 公寓管理_撤销分配(宿舍)_跳转
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

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
			forward = "/tybb_gygl_cxfp_ss.do";
		} else {
			forward = "/tybb_gygl_cxfp_ss.do";
		}
		return new ActionForward(forward, false);
	}

	// ======================公寓报修================================
	/**
	 * 报修申请_跳转
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

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			forward = "/zjcm_gygl_bxsq.do";
		} else {
			forward = "/zjcm_gygl_bxsq.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * 报修审核_跳转
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

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			forward = "/zjcm_gygl_bxsh.do";
		} else {
			forward = "/zjcm_gygl_bxsh.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * 报修结果_跳转
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

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			forward = "/zjcm_gygl_bxjg.do";
		} else {
			forward = "/zjcm_gygl_bxjg.do";
		}

		return new ActionForward(forward, false);
	}

	/**
	 * 报修统计_跳转
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

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			forward = "/zjcm_gygl_bxtj.do";
		} else {
			forward = "/zjcm_gygl_bxtj.do";
		}

		return new ActionForward(forward, false);
	}

	// ======================公寓报修 end================================

	// ======================卫生检查================================
	/**
	 * 参数设置
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
	 * 空白报表
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
	 * 卫生分录入
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
	 * 卫生分查询
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
	 * 特殊寝室
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcTsForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String forward = "/ty_gygl_wsjc_tsqs.do";
		//
		// return new ActionForward(forward, false);

		String msg = "本模块将在二期进行开发，请确认！";
		request.setAttribute("yhInfo", msg);
		return new ActionForward("/yhInfo.do", false);
	}

	/**
	 * 卫生分统计
	 * 
	 * @return ActionForward
	 */
	public ActionForward wsjcTjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/ty_gygl_wsjc_fstj.do";

		return new ActionForward(forward, false);
	}
	// ======================卫生检查 end================================
	
	/**
	 * 卫生检查学生分数录入
	 * 
	 */
	public ActionForward wsjcXsfslrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/ty_gygl_wsjc_xsfslr.do";
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 卫生检查学生分数查看
	 * 
	 */
	public ActionForward wsjcXsfsckForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/ty_gygl_wsjc_xsfsck.do";
		
		return new ActionForward(forward, false);
	}
}
