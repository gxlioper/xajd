/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午06:08:49 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行等级维护) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午06:08:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxdjAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_cxcxdj.do?method=cxdjwhList";
	
	@SystemAuth(url = url)
	public ActionForward cxdjwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxdjForm model = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xsxx_gygl_cxcxdj.do?method=cxdjwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxdjwhList");
	}
	/**
	 * 增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生操行评定管理-操行等级维护-增加CXDJDM:{cxdjdm}")
	public ActionForward addCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxdjForm model = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//等级代码是否存在
			boolean isExist=service.isExist(model);
			boolean isExists = service.checkSameNameIsNotExists(null, model.getCxdjmc());
			if(isExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
				return null;
			}else if(!isExists){
				response.getWriter().print(getJsonMessage("名称已存在！"));
				return null;
			}else{
				
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}
		
		return mapping.findForward("addCxdj");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：cmj
	 * @日期：2013-7-31 上午10:01:23
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
	@SystemLog(description="访问学生信息-学生操行评定管理-操行等级维护-修改CXDJDM:{cxdjdm}")
	public ActionForward updateCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxdjForm myForm = (CxdjForm) form;
		CxdjService service = new CxdjService();
		CxdjForm model=service.getModel(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			boolean isExists = service.checkSameNameIsNotExists(myForm.getCxdjdm(), myForm.getCxdjmc());
			if(!isExists){
				response.getWriter().print(getJsonMessage("名称已存在！"));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateCxdj");
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：cmj
	 * @日期：2013-7-31 下午04:17:51
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
	@SystemLog(description="访问学生信息-学生操行评定管理-操行等级维护-删除VALUES:{values}")
	public ActionForward delCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxdjForm myForm = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		String values = request.getParameter("values");
		if(Base.xxdm.equals("12684")){
			if(service.isNowUsing(values.split(","))){
				String message = "代码正在使用中，不允许删除！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
		}
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}
