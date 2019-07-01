package xgxt.pjpy.ghxy.ryjf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;

/**
 * Title: 学生工作管理系统
 * Description:荣誉查询Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class PjpyRyjfAction extends DispatchAction {
	/**
	 * 根据学院代码选择相应的个人申请路径
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String xxdm = StandardOperation.getXxdm();
		return new ActionForward("/ghxy_ryjf.do?method=grjfsq");
	}
	
	/**
	 * 根据学院代码选择相应的个人审核路径
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String xxdm = StandardOperation.getXxdm();
		return new ActionForward("/ghxy_ryjf.do?method=grjfsh");
	}
	
	/**
	 * 根据学院代码选择相应的个人审核路径
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String xxdm = StandardOperation.getXxdm();
		return new ActionForward("/ghxy_ryjf.do?method=grjfcx");
	}
	
	/**
	 * 根据学院代码选择相应的班级表彰分录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ActionForward("/ghxy_bjbz.do?method=bjbzflr");
	}
	
	/**
	 * 根据学院代码选择相应的班级表彰分录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ActionForward("/ghxy_bjbz.do?method=bjbzfsh");
	}
	
	/**
	 * 根据学院代码选择相应的班级表彰分录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ActionForward("/ghxy_bjbz.do?method=bjbzfcx");
	}
	
	/**
	 * 硅湖学院荣誉减分录入跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";
		
		if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
			forward = "/ghxy_rykf.do?method=ryjflr";
		}else{
			forward = "/ghxy_rykf.do?method=ryjflr";
		}
		return new ActionForward(forward);
	}
	
	/**
	 * 硅湖学院荣誉减分审核跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";
		
		if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
			forward = "/ghxy_rykf.do?method=ryjfsh";
		}else{
			forward = "/ghxy_rykf.do?method=ryjfsh";
		}
		return new ActionForward(forward);
	}
	
	/**
	 * 硅湖学院荣誉减分查询跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";
		
		if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
			forward = "/ghxy_rykf.do?method=ryjfcx";
		}else{
			forward = "/ghxy_rykf.do?method=ryjfcx";
		}
		return new ActionForward(forward);
	}
	
	public ActionForward ryjfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String forward = "";
		
		if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
			forward = "/ghxy_ryjf.do?method=ryjfhz";
		}else{
			forward = "/ghxy_ryjf.do?method=ryjfhz";
		}
		return new ActionForward(forward);
	}
}
