/**
 * @部门:学工产品事业部
 * @日期：2016-7-4 上午10:34:08 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-4 上午10:34:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XgzdAction extends SuperAction<XgzdForm, XgzdService> {
	//建立service对象，方便调用方法
	XgzdService service = new XgzdService();
	/**
	 * 
	 * @描述: 修改字段查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-4 下午02:00:56
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
	public ActionForward xgzdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XgzdForm model = (XgzdForm)form;
		//查询
		if("search".equals(model.getType())){
			JSONObject JsonObj = service.getJsonData();
			response.getWriter().print(JsonObj);
			return null;
		}
		String path = "xszz_jtqkdc_xgzdsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgzdCx");
	}
	
	/**
	 * 
	 * @描述: 保存字段必填非必填信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-5 下午01:47:01
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
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XgzdForm model = (XgzdForm)form;
		//保存
	    boolean result = service.saveData(model);
	    String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
