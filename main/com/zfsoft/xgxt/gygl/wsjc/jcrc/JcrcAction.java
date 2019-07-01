/**
 * @部门:学工产品事业部
 * @日期：2015-6-1 上午09:03:40 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

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
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmModel;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmService;

/** 
 * @类功能描述: 检查日程
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-1 上午09:03:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcrcAction extends SuperAction<JcrcModel, JcrcService> {

	private static final String url = "gygl_wsjc_jcrc.do";
	
	/**卫生检查-- 检查日程**/
	@SystemAuth(url = url)
	public ActionForward jcrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_jcrc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcList");
	}
	
	
	/**卫生检查--检查日程**/
	@SystemAuth(url = url)
	public ActionForward getJcrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcrcService service = getService();
		JcrcModel model = (JcrcModel) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
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

		JcxmService jcxmService = new JcxmService();
		
		request.setAttribute("jcxmList", jcxmService.getAllList(new JcxmModel()));
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		return mapping.findForward("add");
	}
	
	
	/**卫生检查--修改**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JcrcService service = getService();
		JcrcModel myForm = (JcrcModel) form;
		JcxmService jcxmService = new JcxmService();
		
		JcrcModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		String[] rcxmArr = service.getRcxmArr(model.getId());
		request.setAttribute("rcxmArr", rcxmArr);
		request.setAttribute("jcxmList", jcxmService.getAllList(new JcxmModel()));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("edit");
	}
	
	
	
	/**删除检查日程 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-卫生检查-检查日程-删除ID:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"检查日程");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	/**检查日程--提交**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			boolean result = service.runSubmit(ids.split(","));
			if(result) {
				service.saveSubmit(ids.split(","));
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) 
									: MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	
	/**检查日程--取消提交**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		JcrcService service = getService();
		
		if (!StringUtil.isNull(ids)){
			boolean result = service.runCancel(ids.split(","));
			if(result) {
				service.delCancel(ids.split(","));
			}	
			String message = result ? MessageUtil.getText(MessageKey.SYS_QXCP_SUCCESS) 
									: MessageUtil.getText(MessageKey.SYS_QXCP_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
