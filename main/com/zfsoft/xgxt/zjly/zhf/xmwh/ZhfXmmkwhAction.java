/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 上午10:01:35 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 项目维护(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 上午10:01:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfXmmkwhAction extends SuperAction<ZhfXmmkwhForm, ZhfXmmkwhService>{
	public ZhfXmmkwhService service = new ZhfXmmkwhService();
	private static final String url = "xg_zjly_zhfxmwh.do"; 
	
	/** 
	 * @描述:列表显示(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 上午10:23:02
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
	public ActionForward getZhfXmmkwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfXmmkwhForm model = (ZhfXmmkwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_zjly_zhfxmwh.do";
		request.setAttribute("path", path);
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfXmmkwhList");
	}
	
	/** 
	 * @描述:跳转项目模块增加页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 上午11:35:15
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
	public ActionForward addZhfXmmk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("addZhfXmmk");
	}
	
	/** 
	 * @描述:保存项目模块(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午01:30:59
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
	public ActionForward saveXmmk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfXmmkwhForm model = (ZhfXmmkwhForm) form;
		if(model.getType().equals("save")){
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_XMMK_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				boolean result = service.runInsert(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}else{
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_XMMK_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				boolean result = service.runUpdate(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}		
	}
	
	/** 
	 * @描述:跳转项目模块修改页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午01:51:35
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
	public ActionForward updateZhfXmmk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfXmmkwhForm model = (ZhfXmmkwhForm) form;
		ZhfXmmkwhForm viewForm = service.getModel(model);
		request.setAttribute("rs", model);
		BeanUtils.copyProperties(model, viewForm);
		return mapping.findForward("updateZhfXmmk");
	}
	
	/** 
	 * @描述:删除项目模块(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午03:32:29
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
	public ActionForward delZhfXmmk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("values");
		if (!StringUtil.isNull(ids)) {
			String[] idss = ids.split(",");
			if(service.isCanDel(idss)){//判断是否有计分项目存在
				int num = service.runDelete(idss);
				boolean result = num > 0;
				String message = result ? MessageUtil.getText(
						MessageKey.SYS_DEL_NUM, num) : MessageUtil
						.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}else{
				String message= MessageKey.ZJLY_ZHF_XMWH_XMMK_EXIST;
				response.getWriter().print(getJsonMessage(message));
			}			
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
