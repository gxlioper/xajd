/**
 * @部门:学工产品事业部
 * @日期：2014年6月9日 上午9:27:09 
 */  
package com.zfsoft.xgxt.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import common.newp.StringUtil;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 申请审核
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月9日 上午9:27:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public abstract class SuperAuditAction<T extends SuperAuditModel,S extends SuperAuditService<T,?>> extends SuperAction<T,S>{

	private String gnid;
	private String squrl;
	private String shurl;
	
	protected SuperAuditAction(String gnid ,String squrl,String shurl){
		this.gnid = gnid;
		this.squrl = squrl;
		this.shurl = shurl;
	}
	
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午4:10:14
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
	@SuppressWarnings("unchecked")
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		
		JSONObject message = submit(gnid , model.getId(), squrl, shurl);
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 新增和提交
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午4:53:42
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
	@SuppressWarnings("unchecked")
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		S service = getService();
		boolean isSuccess = false;
		//设置申请记录对应的审核状态“审核中”
		model.setShzt(Constants.YW_SHZ);
		
		//保证申请ID和审核ID的一致性，由系统生成唯一ID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//保存申请记录
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//提交申请到审核流程
			message = submit(gnid , model.getId(), squrl, shurl);
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	

	/**
	 * 
	 * @描述: 修改和提交
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午5:09:21
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
	@SuppressWarnings("unchecked")
	public ActionForward updateAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		S service = getService();
		
		//先执行修改操作
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = null;
		
		if (isSuccess){
			//提交申请到审核流程
			message = submit(gnid , model.getId(), squrl, shurl);
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	
	
	/*
	 * @描述: 提交申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午4:04:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid 对应参数设置中的ID
	 * @param id 申请记录ID
	 * @param squrl 申请待办跳转路径
	 * @param shurl 审核待办跳转路径 
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
			throws Exception {
		
		ShlcInterface shlc = new CommShlcImpl();
		
		S service = getService();
		//查询申请记录
		T model = service.getModel(id);
		String splcid = model.getSplcid();
		//提交申请流程
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//更新申请记录状态
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);

		return getJsonMessage(message);
	}
	
	
	/**
	 * 
	 * @描述: 取消提交
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月10日 上午8:52:28
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
	@SuppressWarnings("unchecked")
	public ActionForward cancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t = (T) form;
		S service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		
		T model = service.getModel(t.getId());
		
		boolean isSuccess = shlc.firstStepCancle(model.getId(), model.getSplcid());
		
		if(isSuccess){
			//更新业务状态为'未提交'
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = isSuccess ? 
				MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 审核操作
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月10日 上午9:08:55
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
	@SuppressWarnings("unchecked")
	public ActionForward submitAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t  = (T) form;
		S service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		User user = getUser(request);
		
		ShxxModel shxxModel = new ShxxModel();
		BeanUtils.copyProperties(shxxModel, t);
		
		T model = service.getModel(t.getId());
				
		shxxModel.setYwid(t.getId());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShzt(t.getShjg());
		shxxModel.setSqrid(model.getXh());

//		shxxModel.setShyj(t.getShyj());
//		shxxModel.setThgw(t.getThgw());
//		shxxModel.setGwid(t.getGwid());
		shxxModel.setTzlj(shurl);
		shxxModel.setTzljsq(squrl);
		
		try {
			//审核操作{插入一条数据到审核表中}
			String zhzt  = shlc.runAuditing(shxxModel); 
			model.setShzt(zhzt);//获取审核状态标志，并更新Model
			
			boolean result = service.runUpdate(model);//更新申请表{变更申请表中审核状态信息}
			
			//如果审核通过 插入一条数据到结果库
			if(result && Constants.SH_TG.equals(zhzt)){ 
				BeanUtils.copyProperties(model, shxxModel);
				result = service.afterLastAudit(model);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 取消审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月10日 下午2:19:20
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
	@SuppressWarnings("unchecked")
	public ActionForward cancelAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t  = (T) form;
		S service = getService();
		User user = getUser(request);
		
		//更新 申请记录对应审核状态为“审核中”
		t.setShzt(Constants.YW_SHZ);
		T model = service.getModel(t);
		
		boolean isSuccess = new CommShlcImpl().runCancel(user.getUserName(), t.getId(), model.getSplcid(), t.getGwid());
		
		if(isSuccess){
			isSuccess = service.runUpdate(t); 
			service.deleteResult(t); 
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
	
}
