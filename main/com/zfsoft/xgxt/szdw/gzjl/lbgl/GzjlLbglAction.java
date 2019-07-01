/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午03:51:50 
 */
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

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
 * @时间： 2015-6-25 下午03:51:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GzjlLbglAction extends SuperAction<GzjlLbglForm, GzjlLbglService> {
	
	private static final String url = "gzjl_gzjllbgl.do";
	
	/**
	 * 
	 * @描述:工作记录类别列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-26 下午05:25:48
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
	public ActionForward gzjllbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlLbglForm model = (GzjlLbglForm) form;
		GzjlLbglService service = new GzjlLbglService();
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "gzjl_gzjllbgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lbglList");

	}

	/**
	 * 
	 * @描述:增加工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50
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
	@SystemLog(description = "访问思政队伍-工作记录管理-代码维护-增加LBDM:{lbdm},LBMC:{lbmc},XSSX:{xssx}")
	public ActionForward addGzlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlLbglForm model = (GzjlLbglForm) form;
		GzjlLbglService service = new GzjlLbglService();
		if (SAVE.equals(model.getType())) {
			boolean result = service.addGzjllb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addGzlb");
	}

	/**
	 * 
	 * @描述:修改工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50
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
	@SystemLog(description = "访问思政队伍-工作记录管理-代码维护-修改LBDM:{lbdm},LBMC:{lbmc},XSSX:{xssx}")
	public ActionForward updateGzlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlLbglForm myForm = (GzjlLbglForm) form;
		GzjlLbglService service = new GzjlLbglService();
		GzjlLbglForm model = service.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateGzjllb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateGzlb");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50
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
	@SystemLog(description = "访问思政队伍-工作记录管理-代码维护-删除LBDM:{values}")
	public ActionForward delGzlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlLbglService service = new GzjlLbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 判断工作记录类别是否被占用
			if (service.isUsed(values)) {
				String messageKey = MessageKey.GZJL_LBGL_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	

}
