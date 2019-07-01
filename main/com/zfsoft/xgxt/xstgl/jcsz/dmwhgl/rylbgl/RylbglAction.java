/**
 * @部门:学工产品事业部
 * @日期：2015-9-9 下午04:37:05 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 社团管理-代码维护-人员类别维护 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-9-9 下午04:37:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RylbglAction extends SuperAction<RylbglForm, RylbglService> {
	private RylbglService service = new RylbglService();
	private static final String url = "stgl_jcsz_dmwh.do";
	//查询
	@SystemAuth(url = url)
	public ActionForward getRylblist(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			String rylbmc = request.getParameter("rylbmc"); 
			rylbmc = URLDecoder.decode(URLDecoder.decode(rylbmc,"UTF-8"),"UTF-8");
			model.setRylbmc(rylbmc);
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return null;
	}
	
	//增加
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return map.findForward("addRylb");
	}
	
	//增加保存
	public ActionForward saveNewRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		//验证增加的名称是否存在在数据库中
		model.setRylbmc(model.getRylbmc().trim());
		boolean isExist = service.isExistsSameRylbmc(model.getRylbmc(), null);
		//如果存在直接返回错误信息
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.STGL_JCSZ_RYLB_REPEAT));
			return null;
		}
		boolean result = service.save(model.getRylbmc());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//修改
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		RylbglForm myform = service.getModel(model);
		request.setAttribute("rylbdm", model.getRylbdm());
		request.setAttribute("rylbmc", myform.getRylbmc());
		return map.findForward("updateRylb");
	}
	
	//修改保存
	public ActionForward saveRylb_update(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		//验证修改的名称是否存在在数据库中
		model.setRylbmc(model.getRylbmc().trim());
		boolean isExist = service.isExistsSameRylbmc(model.getRylbmc(), model.getRylbdm());
		//如果存在直接返回错误信息
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.STGL_JCSZ_RYLB_REPEAT));
			return null;
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//删除
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String[] rylbdms = request.getParameter("values").split(",");
		boolean isUsing = false;
		if (!StringUtil.isNull(request.getParameter("values"))) {
			for(int i=0;i<rylbdms.length;i++){
				//判断当前要删除的人员类别是否在业务表中被使用
				isUsing = service.isExistsRylbmc_user(rylbdms[i]);
				//如果是在用状态，直接输出错误信息
				if(isUsing){
					String rylbmc = service.getRylbmc(rylbdms[i]);
					String message = MessageUtil.getText(
							MessageKey.STGL_JCSZ_RYLB_USED, rylbmc);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}
			int num = service.runDelete(rylbdms);
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
}
