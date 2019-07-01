package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszService;

public class ZbsbAction extends SuperAction<ZbsbForm,ZbsbService> {
	private ZbsbService service = new ZbsbService();
	private final String url = "xg_xlzxnew_zbsb.do";
	/**
	 * 
	 * @描述: 周报上报查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 上午09:05:27
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
	public ActionForward getZbsbListCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		User user = getUser(request);
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String xssq = service.xssqCheck(user.getUserName());
		if("N".equals(xssq)){
			String msg = "您没有该面页的访问权限！请联系管理员！";
			request.setAttribute("message",msg);
			return mapping.findForward("error");
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("nowtime",GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//审批流程有无啥子控制增删改按钮
		request.setAttribute("cssz", new XlzxSbService().getModel("zb"));
		request.setAttribute("path", url);
		request.setAttribute("rzflag",service.checkRzrq(user.getUserName())+"");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbsbcx");
	}
	
	/**
	 * 
	 * @描述: 周报上报查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 上午10:18:18
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
	public ActionForward searchZbsbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 周报上报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午01:49:04
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
	public ActionForward addZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZqszForm zbrc = new ZqszService().getModel(myForm.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		User user = getUser(request);
		request.setAttribute("bjxx", service.getBjxx(user.getUserName()));
		return mapping.findForward("zbsb");
	}
	
	/**
	 * 
	 * @描述: 周报上报修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:00:29
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
	public ActionForward editZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZbsbForm model = service.getModel(myForm.getSbsqid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		User user = getUser(request);
		request.setAttribute("bjxx", service.getBjxx(user.getUserName()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbsqid()));
		return mapping.findForward("zbsbedit");
	}
	
	/**
	 * 
	 * @描述: 保存上报数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:33:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public ActionForward saveSbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] zbwtmsArray = request.getParameterValues("zbwtms");
		myForm.setXhArray(xhArray);
		myForm.setZbwtmsArray(zbwtmsArray);
		myForm.setXh(getUser(request).getUserName());
		ZbsbService tranService = TransactionControlProxy.newProxy(new ZbsbService());
		boolean rs = tranService.saveZbsb(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	/**
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午10:10:49
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String value = request.getParameter("sbsqid");
		myForm.setSbsqid(value);
		ZbsbForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午10:28:11
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sbsqid");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			ZbsbForm model = new ZbsbForm();
			model.setSbsqid(sqid);
			model.setSplcid(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看周报上报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午11:12:26
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
	public ActionForward ckZbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		ZbsbForm model = service.getModel(myForm.getSbsqid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		request.setAttribute("bjxx", new ZbjgService().getTeaBjxx(model.getBjdm()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbsqid()));
		return mapping.findForward("zbsbview");
	}
	
	/**
	 * 
	 * @描述: 增加学生跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午11:23:08
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
	public ActionForward addStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setAttribute("xhs",request.getParameter("xhs"));
		request.setAttribute("bjdm",request.getParameter("bjdm"));
		request.setAttribute("path","xg_xlzxnew_addBjStu.do");
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("addstu");
	}
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午11:43:58
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
	public ActionForward searchForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbsbForm myForm = (ZbsbForm)form;
		String xhs = request.getParameter("xhs");
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getStuCx(myForm, user, xhs);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
}
