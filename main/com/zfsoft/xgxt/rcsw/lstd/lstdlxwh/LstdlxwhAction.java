/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:37:16 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

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
import com.zfsoft.xgxt.rcsw.lstd.lstdlxwh.LstdlxwhForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdlxwh.LstdlxwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:37:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdlxwhAction extends SuperAction {
	
	private static final String url = "rcsw_lstd_lxwh.do";

	/**
	 * 
	 * @描述:绿色通道类型list
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午08:44:28
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
	public ActionForward lstdlxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdlxwhForm model = (LstdlxwhForm) form;
		LstdlxwhService service = new LstdlxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_lstd_lxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lstdlxwhManage");
	}
	
	
	/**
	 * 
	 * @描述:类型增加
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午08:48:21
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道理由维护-增加LXMC:{lxmc}")
	public ActionForward addLstdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LstdlxwhForm model = (LstdlxwhForm) form;
		LstdlxwhService service = new LstdlxwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveLxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String lstdlxdm = service.changeLxdm();
		model.setLxdm(lstdlxdm);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("addLstdlx");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午08:50:09
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道理由维护-修改LXDM:{lxdm},LXMC:{lxmc}")
	public ActionForward updateLstdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdlxwhService service = new LstdlxwhService();
		LstdlxwhForm myForm = (LstdlxwhForm) form;
		LstdlxwhForm model = service.getLxwhForm(myForm,myForm.getLxdm());
	
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveLxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateLstdlx");
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午08:51:00
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
	@SystemLog(description = "访问日常事务-绿色通道-绿色通道理由维护-删除VALUES:{values}")
	public ActionForward delLstdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdlxwhService service = new LstdlxwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteLxwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

}
