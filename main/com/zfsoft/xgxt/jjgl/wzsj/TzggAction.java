/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 下午02:14:06 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号：1036]
 * @时间： 2014-9-11 下午02:14:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TzggAction extends SuperAction<TzggForm, TzggService> {
	/**
	 *  @属性： PATH 路径
	 */
	private static final String PATH = "jjgl_tzgg.do";
	
	private static final String url = "jjgl_tzgg.do";
	
	/**
	 * 
	 * @描述:查询页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward tzggCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tzggCx");
	}
	
	
	/**
	 * 
	 * @描述:列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午09:43:49
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
	public ActionForward queryTzggList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TzggForm model = (TzggForm) form;
		
		//查询
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:新增
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward tzggXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tzggXz");
	}
	/**
	 * 
	 * @描述:新增提交
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		TzggForm model = (TzggForm) form;
		model.setPublishdate(DateUtils.getCurrTime());
		model.setSid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		model.setZgh(user.getUserName());
		boolean isSuccess = getService().runInsert(model);
		JSONObject message = null;
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:修改
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward tzggXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TzggForm model = (TzggForm) form;
		if(StringUtils.isNotBlank(model.getSid())){
			TzggForm dataModel = getService().getModel(model.getSid());
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
		}
		return mapping.findForward("tzggXg");
	}
	/**
	 * 
	 * @描述:修改提交
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward submitXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TzggForm model = (TzggForm) form;
		model.setPublishdate(DateUtils.getCurrTime());
		boolean isSuccess = getService().runUpdate(model);
		JSONObject message = null;
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward tzggCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TzggForm model = (TzggForm) form;
		
		if(StringUtils.isNotBlank(model.getSid())){
			HashMap<String, String> modelMap = getService().getModelMap(model);
			request.setAttribute("modelMap", modelMap);
		}
		
		return mapping.findForward("tzggCk");
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-12 上午10:37:39
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
	public ActionForward tzggSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TzggForm model = (TzggForm) form;
		if(StringUtils.isNotBlank(model.getSid())){
			boolean isSuccess = getService().runDelete(model.getSid().split(",")) > 0;
			JSONObject message = null;
			message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(message);
		}
		return null;
	}
	
}
