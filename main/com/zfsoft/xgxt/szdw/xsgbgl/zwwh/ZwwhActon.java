/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:27:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务action
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:27:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwwhActon extends SuperAction {

	private ZwwhService service = new ZwwhService();
	
	private ZwlxService zwlxService = new ZwlxService();
	

	/**
	 * @描述:职务列表
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
	
	public ActionForward zwwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwwhForm myForm = (ZwwhForm) form;
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
		request.setAttribute("path", "szdw_xsgb_zwwh.do?method=zwList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:增加学生干部管理职务
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务管理-增加ZWMC:{zwmc},LXDM:{lxdm},ZWZZ:{zwzz}")
	public ActionForward zwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取职务类型
		ZwlxService zwlxService = new ZwlxService();
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("add");
	}
	/**
	 * @描述:增加学生干部管理职务
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务管理-修改ZWID:{zwid},ZWMC:{zwmc},LXDM:{lxdm},ZWZZ:{zwzz}")
	public ActionForward zwwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取职务类型
		
		myForm=service.getModel(myForm);
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("update");
	}
	
	/**
	 * @描述:删除学生干部职务
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
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部职务管理-删除ZWID:{values}")
	public ActionForward zwwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			String[] sqids = values.split(",");
			ZwsqService zwsq = new ZwsqService();
			//验证此职务是否被申请
			int sqcount = zwsq.getSqCountByZwid(sqids);
			if(sqcount==0){
				int num = service.runDelete(sqids);
				boolean result =  num > 0;
				message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zw");
			}
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	/**
	 * @描述:职务信息查看
	 * @作者：zhangjw
	 * @日期：2013-8-13 上午9:27:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	
	public ActionForward zwView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		List<HashMap<String,String>> list = service.getZwList(myForm.getLxdm());
		//获取职务类型
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("zwList",list);
		if(!StringUtil.isNull(myForm.getZwid())){
			myForm = service.getModel(myForm);
		}
		request.setAttribute("model",StringUtils.formatData(myForm));
		return mapping.findForward("view");
	}
	
	/**
	 *
	 * @描述:查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-25 下午07:22:16
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
	public ActionForward zwck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZwwhForm myForm = (ZwwhForm) form;
		ZwwhForm model = service.getModel(myForm.getZwid());
		ZwlxForm zwlx = zwlxService.getModel(model.getLxdm());
		request.setAttribute("zwwh", model);
		request.setAttribute("zwlx", zwlx);
		return mapping.findForward("zwck");
	}
}
