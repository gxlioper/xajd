package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb.YbsbForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh.YbshForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh.YbshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class XjsqshAction extends SuperAction<XjsqshForm,XjsqshService> {
	private XjsqshService service = new XjsqshService();
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	/**
	 * 
	 * @描述: 销假申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-21 下午03:01:59
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
	public ActionForward xjsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		QjjgForm qjjg = new QjjgForm();
		BeanUtils.copyProperties(qjjg, xjsqshForm);
		// 学生信息
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		
		request.setAttribute("jbxxList", jbxxList);
		XjsqshForm xssqshModel = service.getModelOfqjjgid(xjsqshForm.getQjjgid());

		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		QjjgForm dataForm =new QjjgService().getModel(qjjg);
		request.setAttribute("data",StringUtils.formatData(dataForm));
		//请假类型
		QjlxService qjlx=new QjlxService();

		request.setAttribute("qjlxmc",qjlx.getModel(dataForm.getQjlxid()).getQjlxmc());
		if(xssqshModel != null && (!"0".equals(xssqshModel.getShzt()) && !"3".equals(xssqshModel.getShzt()) ) ){
			return mapping.findForward("ckxjsh");
		}
		return mapping.findForward("xjsqAdd");
	}
	
	/**
	 * 
	 * @描述: 保存销假申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-21 下午03:07:47
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
	public ActionForward saveXjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		User user = getUser(request);
		xjsqshForm.setXjr(user.getUserName());
		xjsqshForm.setXh(user.getUserName());
		boolean rs = service.saveForm(xjsqshForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 *
	 * @描述: 单个销假审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午02:03:49
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
	public ActionForward dgXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		QjjgForm qjjg = new QjjgForm();
		XjsqshForm xssqshModel = service.getModel(xjsqshForm);
		qjjg.setQjjgid(xssqshModel.getQjjgid());
		// 学生信息
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		xjsqshForm.setShid(request.getParameter("shid"));
		// 学生信息
		if (!StringUtil.isNull(xssqshModel.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xssqshModel.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		QjjgForm dataForm =new QjjgService().getModel(qjjg);
		request.setAttribute("data",StringUtils.formatData(dataForm));
		//请假类型
		QjlxService qjlx=new QjlxService();

		request.setAttribute("qjlxmc",qjlx.getModel(dataForm.getQjlxid()).getQjlxmc());
		return mapping.findForward("dgsh");
	}
	
	/**
	 * 
	 * @描述: 单个审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午01:54:53
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
	public ActionForward saveDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm model = (XjsqshForm)form;
		User user = getUser(request);
		// 保存单个审核
		boolean result = service.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 批量销假审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午02:05:00
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
	public ActionForward plXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @描述: 保存批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午02:05:40
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm model = (XjsqshForm)form;
		User user = getUser(request);
		String message = service.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 销假审核跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午03:08:33
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
	public ActionForward xjShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_qjgl_xjsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjshlist");
	}
	
	/**
	 * 
	 * @描述: 查询销假审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-22 下午03:10:26
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
	public ActionForward searchXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		xjsqshForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(xjsqshForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:查看销假申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-26 下午04:13:06
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
	public ActionForward ckXjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XjsqshForm xjsqshForm = (XjsqshForm)form;
		// 学生信息
		if (!StringUtil.isNull(xjsqshForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xjsqshForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		XjsqshForm xssqshModel = service.getModel(xjsqshForm);
		if(xssqshModel != null){
			BeanUtils.copyProperties(xjsqshForm, xssqshModel);
		}
		request.setAttribute("data", StringUtils.formatData(new QjjgService().getModel(xjsqshForm.getQjjgid())));
		return mapping.findForward("ckxjsh");
	}
	
	/**
	 * 
	 * @描述: 撤销销假申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-26 下午04:23:06
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qjjgid = request.getParameter("qjjgid");
		XjsqshForm myForm = service.getModelOfqjjgid(qjjgid);
		String message ="";
		if(myForm == null || StringUtil.isNull(myForm.getShzt())){
			message = "无销假申请记录，无法撤销！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(myForm != null && !"5".equals(myForm.getShzt()) ){
			message = "只能撤销已提交并未被审核的记录！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(myForm.getYwid(), myForm.getSplc());
		if (result) {
			// 更新业务状态为'未提交'
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(myForm.getYwid())) > 0) {
				myForm.setShzt(Constants.YW_YTH);
			} else {
				myForm.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(myForm);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 教师端最后一级撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-26 下午04:50:55
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XjsqshForm model = (XjsqshForm) form;
		String sqid = request.getParameter("sqid");
		model.setYwid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 教师端撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-26 下午04:58:19
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjsqshForm model =new XjsqshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除销假申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-27 下午05:10:32
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isCanDelete(ids)){
				String message = "只能删除未提交或已退回的申请！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-27 下午05:21:41
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
		XjsqshForm myForm = (XjsqshForm)form;
		XjsqshForm model = service.getModelOfqjjgid(myForm.getQjjgid());
		String message= "";
		if(model == null || StringUtil.isNull(model.getShzt())){
			message = "您还未对此请假申请销假，不能做提交操作！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(model != null && (!"0".equals(model.getShzt()) && !"3".equals(model.getShzt()))){
			message = "只能提交未提交或已退回的记录！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 流程跟踪重写
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-4 下午04:32:58
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
	public ActionForward lcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		XjsqshForm xssqshModel = service.getModelOfqjjgid(sqid);
		if(xssqshModel == null || StringUtils.isNull(xssqshModel.getYwid())){
			request.setAttribute("message", "该条记录还未申请销假");
			return mapping.findForward("error");
		}
		sqid = xssqshModel.getYwid();
		String splc = xssqshModel.getSplc();
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqid);
		List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(splc);
		request.setAttribute("infoList", infoList);
		request.setAttribute("gwList", gwList);
		HashMap<String,String> dqgw = ShlcUtil.getDqGwbz(splc,sqid);
		request.setAttribute("dqgw", dqgw);
		request.setAttribute("gwListSize", infoList.size()-1);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("lcgz");
	}
}
