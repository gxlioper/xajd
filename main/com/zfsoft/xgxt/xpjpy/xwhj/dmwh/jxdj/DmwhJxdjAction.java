/**
 * @部门:学工产品事业部
 * @日期：2016-7-13 下午05:30:19 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj;

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
 * @类功能描述: 代码维护-奖项等级
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-13 下午05:30:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhJxdjAction extends SuperAction {
	
	private static final String url = "pjpy_hjgl_dmwh.do";
	
	/**
	 * 
	 * @描述: 查看结果集LIST
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-20 上午10:19:45
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
	public ActionForward jxdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxdjForm model = (DmwhJxdjForm) form;
		DmwhJxdjService service = new DmwhJxdjService();
		
		if(QUERY.equals(model.getType())) {
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pjpy_hjgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jxdjList");
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-20 下午02:40:08
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
	public ActionForward addJxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxdjForm model = (DmwhJxdjForm) form;
		DmwhJxdjService service = new DmwhJxdjService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//重复判断
			boolean isExist=service.isExist(model, SAVE);
			if(!isExist){
				boolean result = service.save(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{		
				String messageKey = "该奖项等级已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;			
			}	
		}
		
		request.setAttribute("jxlbList", service.getJxlbList());
		return mapping.findForward("addJxdj");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-20 下午03:04:08
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
	public ActionForward updateJxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxdjForm model = (DmwhJxdjForm) form;
		DmwhJxdjService service = new DmwhJxdjService();
		DmwhJxdjForm myForm = service.getModel(model);
		if(UPDATE.equalsIgnoreCase(model.getType())) {
			if(model.getJxdjmc().trim().equals(myForm.getJxdjmc().trim()) && model.getJxlbdm().trim().equals(myForm.getJxlbdm().trim()) && model.getJsfs().trim().equals(myForm.getJsfs().trim())) {
				model.setJxdjmc(myForm.getJxdjmc().trim());
				model.setJxlbdm(myForm.getJxlbdm().trim());
				model.setJsfs(myForm.getJsfs().trim());
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
				String messageKey = "该奖项等级已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			  
			}			
		}
		request.setAttribute("jxlbList", service.getJxlbList());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateJxdj");
		
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-20 下午03:41:18
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
	public ActionForward delJxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxdjService service = new DmwhJxdjService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
			String checkSq = service.checkSq(values);
			String checkJg = service.checkJg(values);
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
			
			if(!checkJxmc.trim().equals("")){
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
