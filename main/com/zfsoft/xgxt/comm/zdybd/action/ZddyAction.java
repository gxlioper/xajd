package com.zfsoft.xgxt.comm.zdybd.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.zdybd.model.ZddyModel;
import com.zfsoft.xgxt.comm.zdybd.service.ZddyService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 字段定义
 * @作者： ligl
 * @时间： 2013-11-26 下午03:56:07
 * @版本： V1.0
 * @修改记录:
 */
public class ZddyAction extends SuperAction {
	private String messageKey;

	/**
	 * 
	 * @描述:根据功能代码获取所有分类下的字段定义列表
	 * @作者：ligl
	 * @日期：2013-11-26 下午04:07:46
	 * @修改记录:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public ActionForward getZddyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		ZddyService service = new ZddyService();
		String gndm = request.getParameter("gndm");
		String xs = request.getParameter("xs");
		List<HashMap<String, String>> list = null;
		if (xs != null && xs.equals("true")) {// getListByGndmOfXs
			list = service.getListByGndmOfXs(gndm);
		} else {
			if ((user.getUserType() != null && user.getUserType().equals("stu")) || "szdw_fdyxx_update".equals(gndm)) {
 				list = service.getListByGndmOfXszdsz(gndm);
			} else {
				list = service.getListByGndm(gndm);
			}
		}

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

	/**
	 * 
	 * @描述:字段配置功能
	 * @作者：ligl
	 * @日期：2013-12-10 下午04:27:40
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward zdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "zdybd_zddy_zdpz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zdpz");
	}

	/**
	 * 
	 * @描述:获取字段定义，简单显示，包含学生定义
	 * @作者：ligl
	 * @日期：2013-12-10 下午04:27:21
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward getZddySimpleList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ZddyService service = new ZddyService();
		String gndm = request.getParameter("gndm");
		List<HashMap<String, String>> list = null;
		list = service.getListByGndmOfSimple(gndm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

	/**
	 * 
	 * @描述:修改操作，针对为空、可修改情况
	 * @作者：ligl
	 * @日期：2013-12-10 下午06:42:49
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问学生信息-基础设置-信息修改字段设置-LB:{lb}")
	public ActionForward updateSimple(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZddyModel model = (ZddyModel) form;
		ZddyService service = new ZddyService();
		boolean result = service.updateSimple(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
