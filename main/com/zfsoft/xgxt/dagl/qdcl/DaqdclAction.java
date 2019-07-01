/**
 * @部门:学工产品事业部
 * @日期：2014-2-10 下午05:24:44 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
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
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: 档案材料维护
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-10 下午05:24:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdclAction extends SuperAction {

	private static final String url = "daqdcl.do?method=daqdclList";
	
	/**
	 * 
	 * @描述: 档案材料维护列表
	 * @日期：2014-4-23 下午02:45:08
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
	public ActionForward daqdclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "daqdcl.do?method=daqdclList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("daqdclList");
	}

	/**
	 * 
	 * @描述: 增加档案材料
	 * @日期：2014-4-23 下午02:45:08
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
	@SystemLog(description="访问学生信息-档案管理-档案清单材料维护-增加DAQDCL_MC:{daqdcl_mc}")
	public ActionForward addDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		if (SAVE.equalsIgnoreCase(myForm .getType())){
			String guid = UniqID.getInstance().getUniqIDHash();
			myForm.setDaqdcl_id(guid);
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		return mapping.findForward("addDaqdcl");
	}
	/**
	 * 
	 * @描述: 修改档案材料
	 * @日期：2014-4-23 下午02:45:08
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
	@SystemLog(description="访问学生信息-档案管理-档案清单材料维护-修改DAQDCL_ID:{daqdcl_id},DAQDCL_MC:{daqdcl_mc}")
	public ActionForward updateDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		DaqdclForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateDaqdcl");
	}
	
	/**
	 * 删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-档案管理-档案清单材料维护-删除DAQDCLIDS:{daqdclIds}")
	public ActionForward delDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		
		String daqdclIds = request.getParameter("daqdclIds");
		
		if (!StringUtil.isNull(daqdclIds)){
			int num = service.runDelete(daqdclIds.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}

	/**
	 * 判断档案清单材料名称是否重复
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumeFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		String flag = "0";

		HashMap<String, String> daqdclInfo = service.getDaqdclByName(myForm);
		if(daqdclInfo!=null && daqdclInfo.size()>0){
			flag = "1";
		}
		
		response.getWriter().print(flag);
		return null;
	}
	
}
