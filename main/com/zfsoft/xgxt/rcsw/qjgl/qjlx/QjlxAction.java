/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjlxAction extends SuperAction {
	
	private static final String url = "qjlxbase.do";

	/**
	 * 
	 * @描述:请假管理列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		CommService cs = new CommService();
		QjlxForm myForm = (QjlxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("Qjlxwh.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qjlxbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qjlxlb");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假类型维护-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
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

	/**
	 * 
	 * @描述: 请假管理模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假类型维护-修改QJLXID:{qjlxid},QJLXMC:{qjlxmc}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjlxForm model = service.getModel(myForm);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("qjlxxg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假类型维护-增加QJLXMC:{qjlxmc}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("qjlxzj");
	}

	/**
	 * 
	 * @描述:是否可以增加
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:24:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		// 是否可以增加
		boolean result = service.isCanAdd(myForm);
		if (!result) {// 不可以
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 开启状态
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-19 上午09:38:20
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
	public ActionForward openZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjlxForm myForm = (QjlxForm) form;
		QjlxForm model = new QjlxService().getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("openZt");
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-19 上午10:31:19
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
	public ActionForward saveOpenZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjlxForm myForm = (QjlxForm) form;
		boolean rs = new QjlxService().runUpdate(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
