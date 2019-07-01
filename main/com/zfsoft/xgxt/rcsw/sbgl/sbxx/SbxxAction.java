/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:49:48 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbxx;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflModel;
import com.zfsoft.xgxt.rcsw.sbgl.sbfl.SbflService;

/** 
 * @类功能描述:代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:49:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SbxxAction extends SuperAction<SbxxModel, SbxxService> {
	
	private static final String url = "rcsw_sbgl_sbfl.do";

	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		
		request.setAttribute("sbflList", sbflList);
		request.setAttribute("path", "rcsw_sbgl_sbfl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SbxxService service = getService();
		SbxxModel model = (SbxxModel) form;
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**按分类代码加载设备信息*/
	@SystemAuth(url = url)
	public ActionForward getSbxxByFldm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SbxxService service = getService();
		SbxxModel model = (SbxxModel) form;
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		
		request.setAttribute("sbflList", sbflList);
		return mapping.findForward("add");
	}
	
	/**代码维护修改*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SbxxService service = getService();
		SbxxModel myForm = (SbxxModel) form;
		
		SbxxModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		SbflService sbflService = new SbflService();
		List<HashMap<String,String>> sbflList = sbflService.getAllList(new SbflModel());
		
		request.setAttribute("sbflList", sbflList);
		return mapping.findForward("edit");
	}
	
	
	/**代码维护删除*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-场地管理-设备维护-设备信息-删除IDS:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		SbxxService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"设备信息");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	
	/**校验设备编号是否存在**/
	public ActionForward checkSbbh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SbxxService service = getService();
		SbxxModel myForm = (SbxxModel) form;
		
		int count = service.getSbslByBh(myForm.getBh());
		response.getWriter().print(count);
		
		return null;
	}
	
}
