/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:12:54 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

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
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 奖惩项目维护
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午04:34:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxwJcxmwhAction extends SuperAction<XsxwJcxmwhForm,XsxwJcxmwhService> {
	private static final String url = "xsxwkh_jcxmwh.do";
	
	/**
	 * 
	 * @描述:奖励项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午04:40:31
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
	public ActionForward jlxmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxwJcxmwhForm model = (XsxwJcxmwhForm) form;
		XsxwJcxmwhService service = new XsxwJcxmwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxwkh_jcxmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jlxmManage");
	}
	/**
	 * 增加 奖励项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生行为考核-奖惩项目维护-增加奖励项目MC:{mc}")
	public ActionForward addJlxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addJlxm");
	}
	/**
	 * 
	 * @描述:修改奖励项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午04:44:19
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
	@SystemLog(description="访问学生行为考核-奖惩项目维护-修改奖励项目MC:{mc}")
	public ActionForward updateJlxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		XsxwJcxmwhService service = new XsxwJcxmwhService();
		XsxwJcxmwhForm myForm = (XsxwJcxmwhForm) form;
		XsxwJcxmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateJlxm");
	}
	/**
	 * 处罚项目
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
	public ActionForward cfxmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxwJcxmwhForm model = (XsxwJcxmwhForm) form;
		XsxwJcxmwhService service = new XsxwJcxmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getCfxmPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxwkh_jcxmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cfxmManage");
	}
	
	/**
	 * 
	 * 增加处罚项目
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:12:42
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
	@SystemLog(description="访问学生行为考核-奖惩项目维护-增加处罚项目MC:{mc}")
	public ActionForward addCfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addCfxm");
	}
	
	/**
	 * 
	 * 修改处罚项目
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-2 上午09:13:12
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
	@SystemLog(description="访问学生行为考核-奖惩项目维护-修改处罚项目DM:{dm}")
	public ActionForward updateCfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxwJcxmwhService service = new XsxwJcxmwhService();
		XsxwJcxmwhForm myForm = (XsxwJcxmwhForm) form;
		XsxwJcxmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateCfxm");
	}
	
	
	/**
	 * 
	 * @描述:删除项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午04:55:06
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
	@SystemLog(description="访问日常事务日常行为代码维护-奖惩项目-删除VALUES:{values}")
	public ActionForward delJcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxwJcxmwhService service = new XsxwJcxmwhService();	
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
