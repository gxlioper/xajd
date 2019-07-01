/**
 * @部门:学工产品事业部
 * @日期：2016-11-7 下午03:13:26 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-7 下午03:13:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JesxAction extends SuperAction<JesxForm,JesxService> {
	JesxService service = new JesxService();
	public ActionForward getJesxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JesxForm model = (JesxForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zxdk_gjdk_jesx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 上午09:46:37
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
	public ActionForward jesxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		JesxForm model = (JesxForm)form;
		HashMap<String,String> rs = service.getJesxMap(model.getXlccdm());
		request.setAttribute("rs",rs);
		return mapping.findForward("ck");
		
	}
	
	/**
	 * 
	 * @描述: 贷款上限维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 上午09:47:37
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
	public ActionForward jesxPlwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String[] xlccdms = request.getParameter("values").split(",");
		List<HashMap<String, String>> rs = service.getJesxList(xlccdms);
		request.setAttribute("xljesxList",rs);
		return mapping.findForward("plwh");
		
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 上午10:18:56
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
	public ActionForward saveRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String[] xlccdms = request.getParameterValues("xlccdm");
		String[] jesxs = request.getParameterValues("jesx");
		boolean rs = service.saveRs(xlccdms, jesxs);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
}
