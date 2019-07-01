/**
 * @部门:学工产品事业部
 * @日期：2015-5-29 上午08:58:13 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @类功能描述: 检查项目
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-5-29 上午08:58:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcxmAction extends SuperAction<JcxmModel, JcxmService> {

	private static final String url = "gygl_wsjc_jcxm.do";
	
	/**卫生检查--项目列表**/
	@SystemAuth(url = url)
	public ActionForward jcxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_jcxm.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jcxmList");
	}
	
	
	/**卫生检查--项目列表**/
	@SystemAuth(url = url)
	public ActionForward getJcxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcxmService service = getService();
		JcxmModel model = (JcxmModel) form;
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**卫生检查--增加**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("add");
	}
	
	
	/**卫生检查--修改**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JcxmService service = getService();
		JcxmModel myForm = (JcxmModel) form;
		
		JcxmModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("edit");
	}
	
	
	/**删除检查项目**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-卫生检查-检查项目-删除XMDM:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JcxmService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"检查项目");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	//保存
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcxmModel myForm = (JcxmModel) form;
		JcxmService service = getService();
		if(service.isExist(myForm)){
			JSONObject message = getJsonMessageByKey(MessageKey.GYGL_WSJC_JCXM_REPEAT);
			response.getWriter().print(message);		
			return null;
		}else{			
			boolean isSuccess = service.runInsert(myForm);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
			return null;
		}
	}
	
	//修改保存
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcxmModel myForm = (JcxmModel) form;
		JcxmService service = getService();
		if(service.isExist(myForm)){
			JSONObject message = getJsonMessageByKey(MessageKey.GYGL_WSJC_JCXM_REPEAT);
			response.getWriter().print(message);		
			return null;
		}else{			
			boolean isSuccess = service.runUpdate(myForm);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
			return null;
		}
	}
}
