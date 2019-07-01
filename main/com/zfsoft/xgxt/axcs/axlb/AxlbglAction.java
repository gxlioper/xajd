/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午02:24:28 
 */
package com.zfsoft.xgxt.axcs.axlb;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午02:24:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class AxlbglAction extends SuperAction {
	
	private static final String url = "axcs_axcslbgl.do";
	
	/**
	 * 
	 * @描述:爱心类别列表
	 * @作者：xiaixa [工号：1104]
	 * @日期：2014-12-2 下午02:38:34
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
	public ActionForward axlbglList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AxlbglForm model = (AxlbglForm) form;
		AxlbglService axlbService = new AxlbglService();
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = axlbService.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "axcs_axcslbgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("axlbglList");

	}

	/**
	 * 
	 * @描述:增加爱心类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午03:44:04
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
	public ActionForward addAxlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AxlbglForm model = (AxlbglForm) form;
		AxlbglService axlbService = new AxlbglService();
		if (SAVE.equals(model.getType())) {
			boolean result = axlbService.addAxlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addAxlb");
	}

	/**
	 * 
	 * @描述:修改爱心类别
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
	public ActionForward updateAxlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AxlbglForm myForm = (AxlbglForm) form;
		AxlbglService axlbService = new AxlbglService();
		AxlbglForm model = axlbService.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = axlbService.updateAxlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateAxlb");
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
	public ActionForward delAxlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AxlbglService axlbService = new AxlbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断爱心类别是否被占用
			if (axlbService.isUsed(values)) {
				String messageKey = MessageKey.AXCS_AXLB_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = axlbService.delAxlb(values);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
}
