/**
 * @部门:学工产品事业部
 * @日期：2015-11-10 下午04:56:15 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl.RylbglForm;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-10 下午04:56:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction<CsszForm, CsszService> {
	private CsszService service = new CsszService();
	private static final String url = "rcsw_xsgzzb_cssz.do";
	/**
	 * 
	 * @描述:查询
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-11 上午09:55:03
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
	@SystemAuth(url = url)
	public ActionForward getCsszList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			String wjlxmc = request.getParameter("wjlxmc"); 
			wjlxmc = URLDecoder.decode(URLDecoder.decode(wjlxmc,"UTF-8"),"UTF-8");
			model.setWjlxmc(wjlxmc);
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "rcsw_xsgzzb_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszlist");
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-11 上午09:58:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-11 上午09:54:07
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		CsszForm myform = service.getModel(model);
		request.setAttribute("wjlxdm", model.getWjlxdm());
		request.setAttribute("wjlxmc", myform.getWjlxmc());
		return mapping.findForward("edit");
	}
	
	//删除
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问日常事务-学生工作周报表-参数设置-删除VALUES:{values}")
	public ActionForward delWjlx(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String[] wjlxdms = request.getParameter("values").split(",");
		boolean isUsing = false;
		if (!StringUtil.isNull(request.getParameter("values"))) {
			for(int i=0;i<wjlxdms.length;i++){
				//判断当前要删除的人员类别是否在业务表中被使用
				isUsing = service.isExistsWjlxmc_user(wjlxdms[i]);
				//如果是在用状态，直接输出错误信息
				if(isUsing){
					String rylbmc = service.getWjlxmc(wjlxdms[i]);
					String message = MessageUtil.getText(
							MessageKey.RCSW_XSGZZB_WJLX_USED, rylbmc);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}
			int num = service.runDelete(wjlxdms);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	//增加保存
	@SystemLog(description = "访问日常事务-学生工作周报表-参数设置-增加WJLXMC:{wjlxmc}")
	public ActionForward saveNewWjlx(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		//验证增加的名称是否存在在数据库中
		model.setWjlxmc(model.getWjlxmc().trim());
		boolean isExist = service.isExistsSameWjlxmc(model.getWjlxmc(), null);
		//如果存在直接返回错误信息
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSGZZB_WJLX_REPEAT));
			return null;
		}
		boolean result = service.saveWjlx(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//修改保存
	@SystemLog(description = "访问日常事务-学生工作周报表-参数设置-修改WJLXDM:{wjlxdm},WJLXMC:{wjlxmc}")
	public ActionForward saveWjlx_update(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		//验证修改的名称是否存在在数据库中
		model.setWjlxmc(model.getWjlxmc().trim());
		boolean isExist = service.isExistsSameWjlxmc(model.getWjlxmc(), model.getWjlxdm());
		//如果存在直接返回错误信息
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSGZZB_WJLX_REPEAT));
			return null;
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
