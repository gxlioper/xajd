/**
 * @部门:学工产品事业部
 * @日期：2016-12-2 下午05:37:20 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zgk;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-2 下午05:37:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zgkAction extends SuperAction<zgkForm,zgkService> {
	private  zgkService service = new zgkService();
	/**
	 * 
	 * @描述:删除
	 * @备注: 如果被删除的数据中存在已有被使用的数据，不允许删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 上午10:21:50
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
	public ActionForward delJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			String message = "";
			if(!service.checkIsNotUsed(ids)){
				message = "资格库数据已被使用，不能被删除！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			message = result ? MessageUtil.getText(
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
	 * @描述: 德育资格库学生添加列表查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午02:10:39
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
	public ActionForward getDyzgkCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_dyxj_dyzgk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 添加学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午02:42:00
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
	public ActionForward addZgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if(StringUtils.isNotNull(values)){
			String[] xhs = values.split(",");
			boolean result = service.saveAddXsIntoZgk(xhs);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 选择学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午05:54:29
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
	public ActionForward showStudent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getZgkStuList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "dyxj_dyzgk.do?method=showStudent";
		request.setAttribute("path", path);
		request.setAttribute("xn",request.getParameter("xn") );
		request.setAttribute("xq",request.getParameter("xq") );
		request.setAttribute("gotoPath", request.getParameter("goto"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showdyStudents");
	}
	
	/**
	 * 
	 * @描述: 1.自评申请增加时学号输入情况下，验证学号是否存在资格库中
	 *    	  2.评议结果增加时学号输入情况下，验证学号是否存在资格库中
	 *        3.学号不为空，学年，学期切换情况下，验证学号是否存在资格库中
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-6 上午09:20:42
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
	public ActionForward checkXsIsInZgk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		zgkForm model = (zgkForm) form;
		boolean rs = service.checkIsInZgk(model.getXh(), model.getXn(), model.getXq());
		String message = rs ? "true" : "false";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 第二种保存方式，根据高级查询条件批量保持资格库学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-7 下午02:58:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveZgkXsbySeniorTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		zgkForm model = (zgkForm) form;
	    zgkService  service = new zgkService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model, user);
		if(null != resultList && resultList.size() > 0 && StringUtils.isNotNull(resultList.get(0).get("xh")) ){
			boolean result = service.saveAddXsIntoZgkBytj(resultList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			String messageKey = "高级查询条件没有搜索到学生数据！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
	}
}
