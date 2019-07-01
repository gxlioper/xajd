/**
 * @部门:学工产品事业部
 * @日期：2017-8-9 下午03:40:57 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学风建设维护模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-8-9 下午03:40:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class xfjsAction extends SuperAction{
	private xfjsService service = new xfjsService();
	private static final String url = "szdw_xfjswh.do";
	@SystemAuth(url = url)
	public ActionForward xfjsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xfjsForm model = (xfjsForm) form;
		if(QUERY.equals(model.getType())){
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
		// 默认高级查询条件(当前学年)
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfjsList");
	}
	
	/**
	 * 
	 * @描述:增加
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午09:46:22
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
	public ActionForward  addXfjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		xfjsForm myForm = (xfjsForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setLrr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = service.isExist(myForm);
	    	if (!isExist) {
	    		boolean flag = service.saveDataXf(myForm);
	        	String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SZDW_XFJS_ADD_EXIST));
	    	}
				return null;
		}
		// 取当前学年
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("lrr", user.getRealName());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		//得到登陆用户权限内的楼栋列表
		request.setAttribute("path", url);
		return mapping.findForward("addXfjs");
	}
	/**
	 * 
	 * @描述:修改
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午02:55:41
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
	public ActionForward updateXf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xfjsForm model = (xfjsForm) form;
		if(StringUtils.isNotNull(model.getBz())){
			model.setBz(URLDecoder.decode((URLDecoder.decode(model.getBz(),"UTF-8")),"UTF-8"));
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String messageKey;
			boolean flag = service.updateData(model);
			messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SZDW_XFJS_ADD_EXIST;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		xfjsForm myForm = service.getModel(model);
		BeanUtils.copyProperties(myForm,model);
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("lrr", user.getRealName());
		request.setAttribute("bjdm", myForm.getBjdm());
		request.setAttribute("id", myForm.getId());
		request.setAttribute("path", url);
		return mapping.findForward("updateXf");
		
	}

	/**
	 * 
	 * @描述:查看
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 上午 10:44:07
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
	public ActionForward viewXf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		xfjsForm myForm = (xfjsForm) form;
		xfjsForm model = service.getModel(myForm);;
		BeanUtils.copyProperties(model, myForm);
		request.setAttribute("rs", model);
		return mapping.findForward("viewXf");
	}
	
	
	/**
	 * 
	 * @描述:删除
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 下午02:44:11
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
	public ActionForward delXf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @描述:导出
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11下午02:44:16
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		xfjsForm model = (xfjsForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;	
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 下午03:59:33
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
	public ActionForward bjManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		xfjsForm model = (xfjsForm) form;		
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "szdw_xfjswh.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
}
