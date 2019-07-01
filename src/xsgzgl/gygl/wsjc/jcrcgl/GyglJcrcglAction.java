package xsgzgl.gygl.wsjc.jcrcgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 下午12:36:30
 * </p>
 */
public class GyglJcrcglAction extends BasicAction {

	// -----------------------公寓管理 检查日程管理 begin---------------------------

	/**
	 * 卫生检查日程查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_jcrcgl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_gygl_new_wsjc_jcrcb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_gygl_new_wsjc_jcrcb");
		return mapping.findForward("jcrcglCx");
	}

	/**
	 * 卫生检查日程管理增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		
		String doType = "add";
		request.setAttribute("doType", doType);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", Base.getDqxqmc());
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		request.setAttribute("xxdm", Base.xxdm);
		myForm.setJclx(GyglNewInit.JFFS);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglWh");
	}

	/**
	 * 卫生检查日程管理修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		String doType = "modi";
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		HashMap<String, String> rs = service.getJcrcglMap(myForm);
		request.setAttribute("doType", doType);
		// 单条记录信息
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		
		if (StringUtil.isNull(myForm.getJclx())){
			myForm.setJclx(GyglNewInit.JFFS);
		}
		
		// 加载学年、学期、年度列表
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglWh");
	}

	/**
	 * 卫生检查日程管理详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// 单条记录信息
		HashMap<String, String> rs = service.getJcrcglMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglCk");
	}
	

	/**
	 * 
	 * @描述:提交检查日程管理
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 下午02:39:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问公寓管理-卫生检查-提交检查日程管理PK:{pkValue}")
	public ActionForward tjJcrcgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//修改检查日程管理为提交状态
		boolean result = service.tjJcrcgl(pkStr);
		if(result && "10279".equals(Base.xxdm)) {
			service.saveSubmit(pkStr);
		}
		//浙江传媒个性化，日程提交完成之后，批量更新日程相关的卫生分记录中的是否毕业寝室字段
		if(result && "11647".equals(Base.xxdm)){
			service.updateByqsForZjcm(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_REFER_SUCCESS : MessageInfo.MESSAGE_REFER_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:取消提交检查日程管理
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 下午02:40:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问公寓管理-卫生检查-取消检查日程管理PK:{pkValue}")
	public ActionForward qxtjJcrcgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//修改检查日程管理为提交状态
		boolean result = service.qxtjJcrcgl(pkStr);
		if(result) {
			service.delCancel(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_CANCEL_SUCCESS : MessageInfo.MESSAGE_CANCEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	/** 
	 * @描述:保存操作(浙江商业技师学院专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-31 上午10:46:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SystemLog(description="访问公寓管理-卫生检查-增加卫生检查日程PK:{pkValue}")
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//修改检查日程管理为提交状态
		boolean result = service.qxtjJcrcgl(pkStr);
		if(result) {
			service.delCancel(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_CANCEL_SUCCESS : MessageInfo.MESSAGE_CANCEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
}