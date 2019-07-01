package xgxt.xszz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助页面跳转-action类
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

public class XszzAction extends DispatchAction {
	
	/**
	 * 学生资助_信息_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhInfoForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("yhInfo");
	}
	
	/**
	 * 学生资助_条件设置
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			forward = "/gzdx_xszz_tjsz.do";
		} else {
			forward = "/gzdx_xszz_tjsz.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_副食补助_分配专业跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward fsbzfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			forward = "/guizdx_jxszz_fsbzfp.do";
		} else {
			forward = "/guizdx_jxszz_fsbzfp.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_副食补助_发放跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward fsbzffForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			forward = "/guizdx_jxszz_fsbzff.do";
		} else {
			forward = "/guizdx_jxszz_fsbzff.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_副食补助_结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward fsbzjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			forward = "/guizdx_jxszz_fsbzjg.do";
		} else {
			forward = "/guizdx_jxszz_fsbzjg.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_经济困难生_申请
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjknssqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
			forward = "/hndx_xszz_jjknssq.do";
		} else {
			forward = "/n05_xszz.do?method=knsrd3sq";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_经济困难生_审核
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjknsshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
			forward = "/hndx_xszz_jjknssh.do";
		} else {
			forward = "/n05_xszz.do?method=knsrd3sh";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_经济困难生_结果查询
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjknsjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
			forward = "/hndx_xszz_jjknsjg.do";
		} else {
			forward = "/n05_xszz.do?method=knsrd3sh";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_参数设置
	 * 
	 * @return ActionForward
	 */
	public ActionForward csszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
			forward = "/hndx_xszz_cssz.do";
		} else {
			forward = "/hndx_xszz_cssz.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_学费信息
	 * 
	 * @return ActionForward
	 */
	public ActionForward xfxxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {// 南京技师
			forward = "/njjs_xszz_xfxx.do";
		} else {
			forward = "/njjs_xszz_xfxx.do";
		}
		
		return new ActionForward(forward, false);
	}
	
	
	//=====================以下通用版版本========================
	
	/**
	 * @param request
	 */
	private void setXszzMklx(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String mklx = request.getParameter("mklx");
		if(!Base.isNull(mklx)){
			session.setAttribute("mklx", mklx);
		}
	}
	
	/**
	 * 学生资助_项目维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward xmwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_xmwh.do";
		
		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_人数设置
	 * 
	 * @return ActionForward
	 */
	public ActionForward rsszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_rssz.do";
		
		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_字段设置
	 * 
	 * @return ActionForward
	 */
	public ActionForward zdszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_zdsz.do";
		
		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_项目申请
	 * 
	 * @return ActionForward
	 */
	public ActionForward xmsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_xmsq.do";
		
		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_项目审核
	 * 
	 * @return ActionForward
	 */
	public ActionForward xmshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//String forward = "/comm_xszz_xmsh.do";
		String forward = "/comm_xszz_msg.do";
		
		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_结果查询
	 * 
	 * @return ActionForward
	 */
	public ActionForward jgcxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_jgcx.do";

		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_统计报表
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjbbForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_tjbb.do";

		setXszzMklx(request);
		
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_资助汇总
	 * 
	 * @return ActionForward
	 */
	public ActionForward zzhzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_zzhz.do";

		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生资助_打印统计报表
	 * 
	 * @return ActionForward
	 */
	public ActionForward printTjbbForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "/comm_xszz_printTjbb.do";

		return new ActionForward(forward, false);
	}
	
	
}
