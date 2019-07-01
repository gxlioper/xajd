package xgxt.dtjs;

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
 * Description: 党团建设页面跳转-action类
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

public class DtjsAction extends DispatchAction {
	
	/**
	 * 支部管理跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbglForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// 浙江理工
			forward = "/zjlg_dtjs_zbgl.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			if ("admin".equalsIgnoreCase(userType)
					|| "xx".equalsIgnoreCase(userType)) {
				forward = "/sjxy_dtjs_zbgl.do";// 党总支
			} else {
				forward = "/sjxy_dtjs_dzb.do";// 党支部
			}
		} else {
			forward = "/zjlg_dtjs_zbgl.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 入党申请跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			forward = "/sjxy_dtjs_rdsq.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_rdsq.do";
		} else {
			forward = "/czxx_dtjs_rdsq.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 入党积极分子跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// 浙江理工
			forward = "/zjlg_dtjs_jjfz.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			forward = "/sjxy_dtjs_jjfz.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_jjfz.do";
		} else {
			forward = "/czxx_dtjs_jjfz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 发展对象跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_fzdx.do";
		} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			//forward = "/dtjs_zjcm.do?method=fzdx";
			forward = "/zjcm_dtjs_fzdx.do";
		} else {
			forward = "/czxx_dtjs_fzdx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 预备党员跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// 浙江理工
			forward = "/zjlg_dtjs_ybdy.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			forward = "/sjxy_dtjs_ybdy.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_ybdy.do";
		} else {
			forward = "/czxx_dtjs_ybdy.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 正式党员跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// 浙江理工
			forward = "/zjlg_dtjs_zsdy.do";
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			forward = "/sjxy_dtjs_zsdy.do";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_zsdy.do";
		} else {
			forward = "/czxx_dtjs_zsdy.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 数据统计跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward sjtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// 三江学院
			forward = "/sjxy_dtjs_sjtj.do";
		} else {
			forward = "/sjxy_dtjs_sjtj.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 培训信息跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_pxxx.do";
		} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
			forward = "/zjcm_dtjs_pxxx.do";
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {// 浙江理工
			forward = "/zjlg_dtjs_pxxx.do";
		} else if (Globals.XXDM_GDBYXY.equalsIgnoreCase(xxdm)) {// 广东白云
			forward = "/gdby_dtjs_pxxx.do";
		}else {
			forward = "/czxx_dtjs_pxxx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 党课名单跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward dkmdForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_dkmd.do";
		}else if(Globals.XXDM_GDBYXY.equalsIgnoreCase(xxdm)){// 广东白云
			forward = "/gdby_dtjs_dkmd.do";
		}else {
			forward = "/czxx_dtjs_dkmd.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 思想汇报跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_sxhb.do";
		} else {
			forward = "/czxx_dtjs_sxhb.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 团员信息跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward tyxxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_tyxx.do";
		} else if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {// 南京技师
			forward = "/njjs_dtjs_tyxx.do";
		} else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(xxdm)) {// 南通职业
			forward = "/czxx_dtjs_tyxx.do";
		} else {
			forward = "/czxx_dtjs_tyxx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 非团员培训信息跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward ftypxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_ftypx.do";
		} else {
			forward = "/czxx_dtjs_ftypx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 团员培训跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward typxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_typx.do";
		} else {
			forward = "/czxx_dtjs_typx.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 团内推优跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward tntyForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {// 常州信息
			forward = "/czxx_dtjs_tnty.do";
		} else {
			forward = "/czxx_dtjs_tnty.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 团员注册跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward tyzcForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)) {// 成都体育
			forward = "/czxx_dtjs_tyzc.do";
		}  else {
			forward = "/czxx_dtjs_tyzc.do";
		}
		return new ActionForward(forward, false);
	}
}
