/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:32:32 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

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

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:32:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglShAction extends SuperAction<XsxxKzxxglShForm, XsxxKzxxglShService> {

	private XsxxKzxxglShService service = new XsxxKzxxglShService();
	
	private ExtendService extendService = new ExtendService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	
	private static final String DATA_EXTEND_MODULE = "XSXX";
	
	private static final String url = "xsxx_kzxxgl_shgl.do";
	
	/**
	 * 
	 * @描述:申请审核列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 下午04:44:38
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getActionType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xsxx_kzxxgl_shgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shManage");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午05:47:33
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("ck");
	} 
	
	/**
	 * 
	 * @描述:审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午01:38:49
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
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel);
			model.setShid(shid);
			model.setXtgwid(xtgwid);
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("sh");
	}
	
	
	/**
	 * 
	 * @描述:提交审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午02:14:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward shAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;
		User user = getUser(request);
		//单个保存审核
		boolean result = service.saveSh(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:撤销审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午03:22:24
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
	public ActionForward cancelShAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglShForm model  = (XsxxKzxxglShForm) form;

		XsxxKzxxglShForm dataModel = service.getModel(model.getSqid());
		
		boolean isSuccess = false;
		
		if(dataModel != null){
			dataModel.setShzt(Constants.YW_SHZ);
			isSuccess = service.runUpdate(dataModel); //更新 Model
			if(isSuccess){
				//删除结果记录
				XsxxKzxxglJgForm jgModel = jgService.getModelBySqid(dataModel.getSqid());
				if(jgModel != null){
					isSuccess = jgService.deleteJgBySqid(jgModel.getSqid());
					//删除数据
					String jgid = jgModel.getJgid();
					isSuccess = extendService.deleteData(jgid, DATA_EXTEND_MODULE);
				}
			}
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
}
