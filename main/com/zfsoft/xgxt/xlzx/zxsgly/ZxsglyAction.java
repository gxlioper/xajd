package com.zfsoft.xgxt.xlzx.zxsgly;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglForm;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglService;
import common.newp.StringUtil;

public class ZxsglyAction extends SuperAction<ZxsglForm, ZxsglService> {
	private static final String URL = "xg_xlzx_zxsgly.do";
	private static final String URL_ADDGLY = "xlzx_zxsgly.do?method=addZxsgly";
	private ZxsglyService service = new  ZxsglyService();
	/**
	 * 
	 * @描述: 查询跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 上午10:22:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = URL)
	public ActionForward searchZxsgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URL);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 上午10:40:28
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
	@SystemAuth(url = URL)
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加咨询师管理员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午03:25:42
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
	@SystemAuth(url = URL,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问心理健康咨询-咨询师管理-咨询师管理员-增加")
	public ActionForward addZxsgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URL_ADDGLY);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 查询咨询师管理员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午03:43:01
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
	@SystemAuth(url = URL)
	public ActionForward searchRsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午04:26:22
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
	@SystemAuth(url = URL,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问心理健康咨询-咨询师管理-咨询师管理员-增加保存-zghs:{zghs}")
	public ActionForward saveAddGly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		//为了保持事务一致性
		ZxsglyService transService = TransactionControlProxy.newProxy(new ZxsglyService());
		boolean rs = transService.saveForm(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除管理员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午05:30:03
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
	public ActionForward delgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
