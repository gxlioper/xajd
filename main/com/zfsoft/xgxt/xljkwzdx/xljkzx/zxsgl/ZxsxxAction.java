/**
 * @部门:学工产品事业部
 * @日期：2014-4-24 上午11:04:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-咨询师管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-24 上午11:04:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxsxxAction extends SuperAction{
	
	private static final String url = "xljk_xljkzx_zxsgl.do";

	/**
	 * 
	 * @描述:查询咨询师（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24 上午11:24:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_xljkzx_zxsgl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryZxsxx");
	}
	
	/**
	 * 
	 * @描述:查询咨询师
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24 上午11:50:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:添加咨询师（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24下午01:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zgh = request.getParameter("zgh");
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		ZxsxxService service = new ZxsxxService();
		if(!StringUtil.isNull(zgh)){                 
			if(!service.zxsxxIsExist(zgh)){              //判断该教师已经是心理咨询师
				zxsInfo = service.getFdyInfo(zgh);		 //不存在     查询出该教师信息供前台显示
			}else{
				request.setAttribute("zxsxxIsExisttip", "true");//存在     放开前台提示弹窗
			}
	 	}
		request.setAttribute("zxsInfo", zxsInfo);
		
		return mapping.findForward("addZxsxx");
	}
	
	/**
	 * 
	 * @描述:添加咨询师
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24下午02:50:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:修改咨询师（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24上午10:20:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		ZxsxxForm dataModel = service.getModel(model.getZgh());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		zxsInfo = service.getFdyInfo(model.getZgh());
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		return mapping.findForward("updateZxsxx");
	}
	
	/**
	 * 
	 * @描述:修改咨询师
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24下午03:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:删除咨询师
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午03:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxService service = new ZxsxxService();
		
		String zghs = request.getParameter("zghs"); //需删除的职工号
		
		int isSuccess = service.runDelete(zghs.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:查看咨询师
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-25 上午10:22:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		ZxsxxService service = new ZxsxxService();
		zxsInfo = service.getFdyInfo(model.getZgh());		 //查询教师信息供前台显示
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		ZxsxxForm dataModel = service.getModel(model.getZgh()); //查询该教师作为咨询师的信息供前台显示
		BeanUtils.copyProperties(model, dataModel);
		model.setZxsjj(StringTools.strOutNull(model.getZxsjj()).replaceAll(" ", "&nbsp"));
		if(model.getStatus().equals("0")){
			model.setStatus("不在岗");
		}else if(model.getStatus().equals("1")){
			model.setStatus("在岗");
		}
		if(model.getKjdrs()!=null&&!model.getKjdrs().trim().equals("")){
		}else{
			model.setKjdrs("无上限");
		}
		return mapping.findForward("viewZxsxx");
	}
	
	/**
	 * 
	 * @描述:设置在岗状态（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-25 上午08:52:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxsxxStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zghs = request.getParameter("zghs");
		request.setAttribute("zghs", zghs);
		return mapping.findForward("setZxsxxStatus");
	}
	
	/**
	 * 
	 * @描述:设置在岗状态
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-25 上午08:52:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxsxxStatusAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zghs = request.getParameter("zghs");
		String status = request.getParameter("status");
		ZxsxxService service = new ZxsxxService();
		zghs = zghs.replace(",", "','");
		zghs = "'"+zghs+"'";
		boolean isSuccess = service.setZxsxxStatus(zghs, status);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:咨询预约说明（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-25 上午09:30:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxyysm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxService service = new ZxsxxService();
		String zxyysm = service.getZxyysm();
		request.setAttribute("zxyysm", zxyysm);
		return mapping.findForward("setZxyysm");
	}
	
	/**
	 * 
	 * @描述:咨询预约说明
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-25 上午09:30:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxyysmAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zxyysm = request.getParameter("zxyysm");
		
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.setZxyysm(zxyysm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;	
	}
}
