/**
 * @部门:学工产品事业部
 * @日期：2016-7-6 上午11:23:08 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 代码维护-奖项类别  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-6 上午11:23:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxlbAction extends SuperAction {
	
	private static final String url = "pjpy_hjgl_dmwh.do";
	
	/**
	 * 
	 * @描述: 查看结果集LIST
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-7 下午04:21:00
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
	public ActionForward jxlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		
		if(QUERY.equals(model.getType())) {
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pjpy_hjgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jxlbList");
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午02:29:36
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
	public ActionForward addJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//重复判断
			boolean isExist=service.isExist(model, SAVE);
			if(!isExist){
				boolean result = service.save(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String messageKey = "该奖项类别名称已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;			
			}	
		}
		return mapping.findForward("addJxlb");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-11 下午06:11:46
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
	public ActionForward updateJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		DmwhJxlbForm myForm	= service.getModel(model);
		
		if(UPDATE.equalsIgnoreCase(model.getType())) {
			if(model.getJxlbmc().trim().equals(myForm.getJxlbmc().trim())){
				model.setJxlbmc(myForm.getJxlbmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExist(model, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String messageKey = "该奖项类别名称已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			  
			}	
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateJxlb");
		
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-13 下午04:38:03
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
	public ActionForward delJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbService service = new DmwhJxlbService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
			String checkSq = service.checkSq(values);
			String checkJg = service.checkJg(values);
			String checkJxdj = service.checkJxdj(values);
			String checkJxmc = service.checkJxmc(values);
			if(!checkSq.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_SQ,checkSq);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JG,checkJg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJxdj.trim().equals("")) {
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JXDJ,checkJxdj);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJxmc.trim().equals("")) {
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JXMC,checkJxmc);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
			
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
}
