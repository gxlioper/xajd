/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:46:17 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

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
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:46:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XslxwhAction extends SuperAction{
	private XslxwhService service = new XslxwhService();
	
	private static final String url = "tsxsgl_xslxwh.do";
	
	/**
	 * 
	 * @描述:学生类型列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午12:58:56
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
	public ActionForward xslxwhManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm model = (XslxwhForm) form;
		if (QUERY.equals(model.getType())) {
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	request.setAttribute("path", "tsxsgl_xslxwh.do");
	FormModleCommon.commonRequestSet(request);
	return mapping.findForward("xslxManage");
	}
	/**
	 * 
	 * @描述:获取学生类型名称
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午09:23:09
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
	public ActionForward getXslxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XslxwhForm myForm = (XslxwhForm) form;
		String xslxdms = myForm.getXslxdm();
		String xslxmc = "";
		if(xslxdms!=null){
			xslxmc =service.getXslxMc(xslxdms);
		}
		response.getWriter().print(xslxmc);
		return null;
	}
	/**
	 * 
	 * @描述:增加学生类型
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午04:30:36
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
	@SystemLog(description="访问学生信息-特殊学生管理-学生类型维护-增加XSLXDM:{xslxdm},XSLXMC:{xslxmc}")
	public ActionForward addXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm model = (XslxwhForm) form;
		
		if (SAVE.equals(model.getType())) {
			boolean result = service.addXslx(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addXslx");
	}

	/**
	 * 
	 * @描述:修改学生类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午03:50:04
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
	@SystemLog(description="访问学生信息-特殊学生管理-学生类型维护-修改XSLXDM:{xslxdm},XSLXMC:{xslxmc}")
	public ActionForward updateXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XslxwhForm myForm = (XslxwhForm) form;
		
		XslxwhForm model = service.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateXslx(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXslx");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午04:03:00
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
	@SystemLog(description="访问学生信息-特殊学生管理-学生类型维护-删除XSLXDM:{values}")
	public ActionForward delXslx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String[] dms=values.split(",");
		if (!StringUtil.isNull(values)) {
			//判断学生类别是否被占用
			if (service.isUsed(values)) {
				String messageKey = "该学生类别已被使用，不允许删除！";
				response.getWriter().print(getJsonMessageResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(dms);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}

}
