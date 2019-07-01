/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:24:28 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl;

import java.net.URLDecoder;
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
import xgxt.utils.GetTime;
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
 * @时间： 2015-07-31 下午02:24:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StlbglAction extends SuperAction {
	
	private static final String url = "stgl_jcsz_dmwh.do";
	
	/**
	 * 
	 * @描述:社团类别列表
	 * @作者：xiaixa [工号：1104]
	 * @日期：2015-07-31 下午02:38:34
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
	public ActionForward getStlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StlbglForm model = (StlbglForm) form;
		StlbglService StlbService = new StlbglService();
		if (QUERY.equals(model.getType())) {
			String stlbmc = request.getParameter("stlbmc"); 
			stlbmc = URLDecoder.decode(URLDecoder.decode(stlbmc,"UTF-8"),"UTF-8");
			model.setStlbmc(stlbmc);
			List<HashMap<String, String>> resultList = StlbService.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "stgl_jcsz_dmwh.do");
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStlbList");

	}

	/**
	 * 
	 * @描述:增加社团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:44:04
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
	public ActionForward addStlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StlbglForm model = (StlbglForm) form;
		StlbglService StlbService = new StlbglService();
		if (SAVE.equals(model.getType())) {
			boolean result = StlbService.addStlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addStlb");
	}

	/**
	 * 
	 * @描述:修改社团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:50:04
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
	public ActionForward updateStlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StlbglForm myForm = (StlbglForm) form;
		StlbglService StlbService = new StlbglService();
		StlbglForm model = StlbService.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = StlbService.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateStlb");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:03:00
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
	public ActionForward delStlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StlbglService StlbService = new StlbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断社团类别是否被占用
			if (StlbService.isUsed(values)) {
				String messageKey = MessageKey.STGL_JCSZ_STLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = StlbService.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
}
