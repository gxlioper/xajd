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
 * Title: ѧ����������ϵͳ
 * Description:������ѯAction
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class PjpyRyjfAction extends DispatchAction {
	/**
	 * ����ѧԺ����ѡ����Ӧ�ĸ�������·��
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
	 * ����ѧԺ����ѡ����Ӧ�ĸ������·��
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
	 * ����ѧԺ����ѡ����Ӧ�ĸ������·��
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
	 * ����ѧԺ����ѡ����Ӧ�İ༶���÷�¼��
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
	 * ����ѧԺ����ѡ����Ӧ�İ༶���÷�¼��
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
	 * ����ѧԺ����ѡ����Ӧ�İ༶���÷�¼��
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
	 * ���ѧԺ��������¼����ת
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
	 * ���ѧԺ�������������ת
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
	 * ���ѧԺ�������ֲ�ѯ��ת
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
