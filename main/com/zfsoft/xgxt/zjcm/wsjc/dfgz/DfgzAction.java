/**
 * @部门:学工产品事业部
 * @日期：2016-3-2 上午09:00:55 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2016-3-2 上午09:00:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DfgzAction extends SuperAction<DfgzForm, DfgzService> {
	
	private DfgzService service = new DfgzService(); 
	private static final String url = "cjWsf_dfgz.do";  //
	
	/**
	 * 
	 * @描述:打分规则查询
	 * @作者：cq [工号：785]
	 * @日期：2016-3-2 上午11:22:20
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
	public ActionForward getDfgzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm model = (DfgzForm) form;
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
		String path = "cjWsf_dfgz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getDfgzList");
	}
	
	/** 
	 * @描述:设置打分规则
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-7 下午02:40:42
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
	public ActionForward addDfgz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXpmc(Base.currXq));
		request.setAttribute("nyList", service.getYueFenByXn(Base.currXn));
		request.setAttribute("pfzList", service.getPfzList());
		String path = "cjWsfDfgz.do?method=addDfgz";
		request.setAttribute("path", path);
		//其他信息配置
		return mapping.findForward("addDfgz");
	}
	
	
	/**
	 * 
	 * @描述:保存打分规则
	 * @作者：cq [工号：785]
	 * @日期：2016-3-14 上午08:52:41
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
	public ActionForward saveForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DfgzForm model = (DfgzForm) form;
	    
		if ("save".equals(model.getType())&&service.wsfTj(model.getDfszid())) {
			response.getWriter().print(getJsonMessage("抽查年月已存在"));
			return null;
		}
		
		boolean result = service.editGz(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	} 
	
	/**
	 * 
	 * @描述:更新打分规则
	 * @作者：cq [工号：785]
	 * @日期：2016-3-14 上午09:10:20
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
	public ActionForward updateDfgz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DfgzForm model = (DfgzForm) form;
		DfgzForm myForm = new DfgzForm();
		myForm = service.getModel(model);
		
		//获取学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(myForm.getXq())){
				myForm.setXqmc(map.get("xqmc"));
				break;
			}
		}
		
		request.setAttribute("rs", myForm);
		String path = "cjWsfDfgz.do?method=updateDfgz";
		request.setAttribute("path", path);
		//其他信息配置
		return mapping.findForward("updateDfgz");
	}
	
	
	/**
	 * 
	 * @描述:查询所有记录，返回json格式
	 * @作者：cq [工号：785]
	 * @日期：2016-3-10 下午05:15:12
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
	public ActionForward gzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DfgzForm myForm = (DfgzForm) form;
		myForm = service.getAll(myForm);
		JSONObject json = JSONObject.fromBean(myForm);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:修改方法
	 * @作者：cq [工号：785]
	 * @日期：2016-3-11 下午03:29:14
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
	@SystemLog(description="操作卫生分打分规则")
	public ActionForward dfgzXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm myForm = (DfgzForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			//有已提交的卫生分，不允许修改打分组
			if(service.wsfTj(myForm.getDfszid())){
				response.getWriter().print(getJsonMessage("已有卫生分提交，不允许修改！"));
			}
			
			String pfzJson = request.getParameter("pfzJson");
			List<DfgzForm> pfzList = JsonUtil.jsonArrToList(pfzJson,DfgzForm.class);
			boolean result = service.saveDfgzSz(myForm,pfzList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		
		boolean isExist = service.getExistCcsj(myForm);
		
		response.getWriter().print(isExist);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:规则设定
	 * @作者：cq [工号：785]
	 * @日期：2016-3-12 下午02:58:57
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
	public ActionForward gzsd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm myForm = (DfgzForm) form;
		request.setAttribute("xqmc", service.getXpmc(Base.currXq));
		request.setAttribute("rs", service.getModel(myForm));
		
		return mapping.findForward("gzsd");
	}
	
	
	/**
	 * 
	 * @描述:抽查寝室list
	 * @作者：cq [工号：785]
	 * @日期：2016-3-15 下午02:41:10
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
	public ActionForward getCcqsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DfgzForm model = (DfgzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getCcqsList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_ccqs.do";
		request.setAttribute("path", path);
		request.setAttribute("tjzt", model.getTjzt());
		request.setAttribute("dfszid", model.getDfszid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getCcqsList");
	}
	
	
	/**
	 * 
	 * @描述:删除规则设置
	 * @作者：cq [工号：785]
	 * @日期：2016-3-15 下午03:14:16
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
	public ActionForward delGzsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//没删掉影响也不是很大，就没处理了
			service.delPfzQs(ids);
			service.delPfzSz(ids);
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
	
	
	/**
	 * 
	 * @描述:规则查看
	 * @作者：cq [工号：785]
	 * @日期：2016-3-15 下午05:08:07
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
	public ActionForward viewGzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DfgzForm model = (DfgzForm) form;
		DfgzForm myForm = new DfgzForm();
		myForm = service.getModel(model);
		
		//获取学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(myForm.getXq())){
				myForm.setXqmc(map.get("xqmc"));
				break;
			}
		}
		request.setAttribute("rs", myForm);
		request.setAttribute("pfzszList", service.pfzszList(myForm.getDfszid()));
		return mapping.findForward("viewGzsz");
	}

	
}
