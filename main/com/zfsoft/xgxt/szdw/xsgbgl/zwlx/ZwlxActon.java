/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:27:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务类型action
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:27:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwlxActon extends SuperAction {
	
	private static final String url = "szdw_xsgb_zwlx.do?method=zwlxList";

	/**
	 * @描述:职务类型列表
	 * @作者：zhangjw
	 * @日期：2013-8-7 上午11:17:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward zwlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_xsgb_zwlx.do?method=zwlxList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:增加学生干部管理职务类型
	 * @作者：zhangjw
	 * @日期：2013-8-7 上午11:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-干部职务类型维护-增加LXMC:{lxmc}")
	public ActionForward zwlxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean yzLxName = service.yzLxName(myForm.getLxmc());
			if(yzLxName){
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonResult(messageKey,result));
			}else{
				response.getWriter().print(getJsonResult("szdw_xsgbgl_zwlxyz",yzLxName));
			}
			return null;
		}
		request.setAttribute("model", StringUtils.formatData(myForm));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("szdw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("add");
	}
	/**
	 * @描述:增加学生干部管理职务类型
	 * @作者：zhangjw
	 * @日期：2013-8-7 上午11:24:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-干部职务类型维护-修改LXDM:{lxdm},LXMC:{lxmc}")
	public ActionForward zwlxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		myForm=service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(myForm));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("szdw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("update");
	}
	
	/**
	 * @描述:删除学生干部职务类型
	 * @作者：zhangjw
	 * @日期：2013-8-7 上午11:24:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-干部职务类型维护-删除LXDM:{values}")
	public ActionForward zwlxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxService service = new ZwlxService();
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			ZwwhService zwwhService = new ZwwhService();
			int sqcount = zwwhService.yzZwCount(values.split(","));
			if(sqcount<=0){
				int num = service.runDelete(values.split(","));
				boolean result =  num > 0;
				message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zwlx");
			}
			
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 职务类型查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-26 上午10:09:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward zwlxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxForm zwlx =   new ZwlxService().getModel(myForm.getLxdm());
		HashMap<String, String> lcxx = new ZwlxService().getSplcMc(zwlx.getSplc());
		request.setAttribute("zwlx", zwlx);
		request.setAttribute("splc", lcxx.get("lcxx"));
		return mapping.findForward("zwlxck");
	}
	
	
}
