package xgxt.pjpy.comm.pjpy.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyInit;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_基本设置-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyJbszAction extends BasicAction {

	/**
	 * 评奖评优_基本设置_评奖基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJbszForm myForm = (PjpyJbszForm) form;
		PjpyJbszService service = new PjpyJbszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getPjjbszRForm(rForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.savePjpyJbsz(myForm, rForm, user, request);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			
			if(flag){
				PjxtszModel.setPjxtszModel();
				service.updatePjzqSfsz("1");//评奖周期是否设置改为“是”;
			}
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, String> rs = service.getPjpyJbsz();
		rForm.setRs(rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("pjjbsz");
	}
	
	
	
	
	/**
	 * 开始评奖
	 */
	public ActionForward startPjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		boolean result = service.startPjpy();
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result); 
		return null;
	}
	
	
	
	
	/**
	 * 评奖综合设置
	 */
	public ActionForward pjpyZhsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		request.setAttribute("path", "pjpy_zhsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjpyZhsz");
	}
	
	
	
	/**
	 * 评奖周期-是否设置
	 */
	public ActionForward getPjzqSfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJbszService service = new PjpyJbszService();
		
		String flg = service.getPjpyPjzqSfsz();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(flg); 
		
		return null;
	}
	
	
	/**
	 * 评奖评优-综测是否设置
	 */
	public ActionForward getPjzcSfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyJbszForm model = (PjpyJbszForm) form;
		PjpyJbszService service = new PjpyJbszService();
		String zczq = model.getZczq();
		
		String count = service.getZczqSfsz(zczq);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
	
	
	
	/**
	 * 评奖项目是否维护
	 */
	public ActionForward getPjxmSfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		String count = service.getPjxmSfwh();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
	
	
	
	/**
	 * 评奖人员是否设置
	 */
	public ActionForward getPjrySfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		String count = service.getPjrySfsz();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
}