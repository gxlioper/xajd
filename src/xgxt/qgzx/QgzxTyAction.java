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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学页面跳转-action类
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

public class QgzxTyAction extends DispatchAction {

	/**
	 * 勤工助学 - 临时岗位 - 岗位分配
	 * 
	 * @return ActionForward
	 */
	public ActionForward lsgwfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_qgzx_lsgw_fp.do";
		} else {
			forward = "/gzdx_qgzx_lsgw_fp.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 勤工助学 - 临时岗位 - 结果查询
	 * 
	 * @return ActionForward
	 */
	public ActionForward lsgwjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_qgzx_lsgw_jg.do";
		} else {
			forward = "/gzdx_qgzx_lsgw_jg.do";
		}
		return new ActionForward(forward, false);
	}
}
