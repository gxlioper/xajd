/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 下午05:31:13 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-考情管理-管理模块
 * @类功能描述: 考情维护service
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 下午05:31:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqwhAction extends SuperAction<KqwhForm, KqwhService> {
	
	private static final String url = "rcsw_zjsy_kqwh.do";
	
	private KqwhService service = new KqwhService();
	
	/**
	 * 
	 * @描述:考情维护跳转
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 上午11:59:12
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
	public ActionForward kqwhKqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setSearch_tj_yf(new String[]{DateUtils.getMonth()});
		String dqzc = service.getDqZc();
		if(!StringUtil.isNull(dqzc)){
			searchModel.setSearch_tj_zjsyzc(new String[]{dqzc});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zjsy_kqwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kqwhKqList");
	}
	
	/**
	 * 
	 * @描述:考情管理list
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 上午11:58:49
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
	public ActionForward getKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqwhForm model = (KqwhForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:考情维护
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:12:57
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
	public ActionForward updateKqwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqwhForm model = (KqwhForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			String objStr = request.getParameter("objStr");
			List<KqwhForm> list = JsonUtil.jsonArrToList(objStr,KqwhForm.class);
			model.setJlr(getUser(request).getUserName());
			boolean result = service.saveKqinfo(model, list);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String,String>> kqinfoList= service.getKqinfo(model.getId());
		KqwhForm myForm = service.getModel(model.getId());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("kqinfoList", kqinfoList);
		this.saveToken(request);
		return mapping.findForward("updateKqwh");
	}
	
	
	/**
	 * 
	 * @描述:查询有多少条记录可以提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午07:37:28
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
	public ActionForward checkSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqwhForm kqwhForm = (KqwhForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		kqwhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		
		List<HashMap<String, String>> isCanSubmit = service.isCanSubmit(values,kqwhForm,user);
		
		response.getWriter().print(isCanSubmit.size());
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午08:29:38
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
		
		KqwhForm kqwhForm = (KqwhForm) form;
		
		String values = request.getParameter("values");
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		kqwhForm.setSearchModel(searchModel);
		
		int[] result = service.submit(values,kqwhForm,user);
		
		String messageKey = result[1]==0 ? MessageKey.SYS_SUBMIT_SUCCESS
				: "提交成功："+result[0]+"条，失败："+result[1]+"条！";
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @描述:考情维护撤销
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 上午10:27:08
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
//	@SystemLog(description="日常事务-考情管理-考情维护-撤销VALUES：{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		KqwhForm model = service.getModel(values);
		String splc = model.getSplc();
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(values, splc);
		
		if (result) {
			// 更新业务状态为'未提交'
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
