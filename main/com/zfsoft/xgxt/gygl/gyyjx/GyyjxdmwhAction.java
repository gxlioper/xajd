/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午11:34:01 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 上午11:34:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxdmwhAction extends SuperAction {
	
	private static final String url = "gygl_yjxdmwhgl.do";

	@SystemAuth(url = url)
	public ActionForward listDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxdmwhService service  = new GyyjxdmwhService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_yjxdmwhgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listDmwh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward actionNavi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxdmwhService service  = new GyyjxdmwhService();
		String type = model.getType();
		String mappingUri = "gyyjxdmwhAddUpdate";
		request.setAttribute("actionType", type);
		if(StringUtils.equals("update", type)){
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(service.getModel(model.getYjfldm())));
		}
		return mapping.findForward(mappingUri);
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓意见箱-意见分类代码维护-增加YJFLMC:{yjflmc}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxdmwhService service  = new GyyjxdmwhService();

		if(StringUtils.isNotBlank(model.getYjflmc())){
			boolean check = service.checkExist(model.getYjflmc());
			if(check){
				String messageKey = MessageKey.GYGL_YJXFLDM_CHECK_ERROR;
				JSONObject message = getJsonMessageByKey(messageKey);
				message.put("repeat", "Y");
				response.getWriter().print(message);
			}else{
				model.setYjfldm(service.getMaxdm() + "");
				boolean isSuccess = service.runInsert(model);
				String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				JSONObject message = getJsonMessageByKey(messageKey);
				response.getWriter().print(message);
			}
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓意见箱-意见分类代码维护-修改PK:{yjfldm},YJFLMC:{yjflmc}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxdmwhService service  = new GyyjxdmwhService();

		if(StringUtils.isNotBlank(model.getYjflmc()) && StringUtils.isNotBlank(model.getYjfldm())){
			boolean check = service.checkExist(model.getYjflmc() , model.getYjfldm());
			if(check){
				String messageKey = MessageKey.GYGL_YJXFLDM_CHECK_ERROR;
				JSONObject message = getJsonMessageByKey(messageKey);
				message.put("repeat", "Y");
				response.getWriter().print(message);
			}else{
				boolean isSuccess = service.runUpdate(model);
				String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				JSONObject message = getJsonMessageByKey(messageKey);
				response.getWriter().print(message);
			}

		}
		
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓意见箱-意见分类代码维护-删除PK:{pks}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxdmwhService service  = new GyyjxdmwhService();

		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			int isSuccess = service.runDelete(pks.split(","));
			String messageKey = (isSuccess>0) ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
}
