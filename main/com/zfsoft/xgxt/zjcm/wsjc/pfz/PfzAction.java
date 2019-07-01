/**
 * @部门:学工产品事业部
 * @日期：2016-2-26 下午04:59:47 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

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
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 传媒卫生分_评分组
 * @作者： cq [工号:785]
 * @时间： 2016-2-26 下午04:59:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfzAction extends SuperAction<PfzForm, PfzService> {
	
	
	private PfzService service = new PfzService(); 
	private static final String url = "cjWsf_pfz.do";  //传媒卫生分
	
	
	/**
	 * 
	 * @描述:查询评分组List
	 * @作者：cq [工号：785]
	 * @日期：2016-2-26 下午03:22:45
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
	public ActionForward getPfzlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PfzForm model = (PfzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_pfz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPfzlList");
	}
	
	/**
	 * 
	 * @描述:增加评分组
	 * @作者：cq [工号：785]
	 * @日期：2016-2-29 下午06:46:02
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
	public ActionForward addPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("ssxqList", new OptionUtil().getOptions("ssxq"));
		return mapping.findForward("addPfz");
	}
	
	
	/**
	 * 
	 * @描述:增加评分组
	 * @作者：cq [工号：785]
	 * @日期：2016-2-29 下午07:26:35
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
	@SystemLog(description = "访问卫生检查—评分组-增加或修改保存PFZMC:{pfzmc},SSXQ:{ssxq},pfzdm:{pfzdm}")
	public ActionForward savePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm myForm = (PfzForm) form;
		myForm.setPfzmc(StringUtil.trim(myForm.getPfzmc()));
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.ZJCM_WSJC_PFZ_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editPfz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:修改评分组
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午04:16:31
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
	public ActionForward updatePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm myForm = (PfzForm) form;
		PfzForm pfzForm = service.getModel(myForm);
		if (null != pfzForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(pfzForm));
			request.setAttribute("pfzForm", pfzForm);
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("ssxqList", new OptionUtil().getOptions("ssxq"));
		return mapping.findForward("updatePfz");
	}
	
	
	/**
	 * 
	 * @描述:删除评分组
	 * @作者：cq [工号：785]
	 * @日期：2016-2-29 下午07:37:52
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
	@SystemLog(description = "访问卫生检查—评分组-删除VALUES:{values}")
	public ActionForward delPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断评分组是否被使用
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.ZJCM_WSJC_PFZ_DFGZYSY);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			//删除对应的评分成员(暂不考虑返回消息)；
			service.delPfcy(ids);
			
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:评分成员列表
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:19:40
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
	public ActionForward showPfcyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm myForm = (PfzForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPfcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "cjWsfPfz.do?method=showPfcyList");
		request.setAttribute("zxxMap", service.getZxx(myForm.getPfzid()));
		request.setAttribute("pfzid", myForm.getPfzid());
		return mapping.findForward("showPfcyList");
	}
	
	
	/**
	 * 
	 * @描述:保存评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:30:28
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
	public ActionForward savePfcy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm model = (PfzForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfcy(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}

	/**
	 * 
	 * @描述:取消评分成员
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 上午10:31:26
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
	public ActionForward savePfcyQx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm model = (PfzForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfcyQxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:评分对象查看
	 * @作者：cq [工号：785]
	 * @日期：2016-3-1 下午03:04:29
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
	public ActionForward viewPfzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm model = (PfzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPfzList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path =null;
		
		path = "cjWsfPfz.do?method=showPfcyList";
		
		request.setAttribute("path", path);
		request.setAttribute("pfzid", model.getPfzid());
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPfzList");
	}


}
