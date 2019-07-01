/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午01:55:49 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import java.io.File;
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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjcm.wsjc.wsftj.WsftjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-8 下午01:55:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsflrAction extends SuperAction<WsflrForm, WsflrService>{
	private WsflrService service = new WsflrService();
	private static final String url = "cjWsf_wsflr.do";
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-8 下午01:59:37
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
	public ActionForward getWsflrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsflrForm model = (WsflrForm) form;
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
		String path = "cjWsf_wsflr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getWsflrList");
	}
	
	
	/** 
	 * @描述:卫生分录入
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-9 上午08:57:13
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
	public ActionForward addWsflr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // 查询
				List<HashMap<String, String>> resultList = service.getPageListForLr(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "cjWsflr.do?method=addWsflr";
		request.setAttribute("path", path);
		request.setAttribute("ccny", model.getCcny());
		//其他信息配置
		return mapping.findForward("addWsflrList");
	}
	
	/** 
	 * @描述:保存分数
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-8 下午05:41:25
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
	public ActionForward saveWsf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		User user = getUser(request);
		String host = IPRequest.getIpAddress(request);
		model.setCcrip(host);
		boolean result = service.saveWsf(model, user);		
		if (!result){
			//如果失败，则提示
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-9 上午10:04:38
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
	public ActionForward tj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		User user = getUser(request);
		String host = IPRequest.getIpAddress(request);
		model.setCcrip(host);
		String message = service.tj(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/** 
	 * @描述:是否可提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-9 上午10:17:45
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
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsflrForm model = (WsflrForm) form;
		boolean result = service.checkIsCanSubmit(model);
		response.getWriter().print(result);
		return null;
	}
	
	/** 
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-10 下午02:05:41
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
	public ActionForward viewqs(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		WsflrForm model = (WsflrForm) form;
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
		request.setAttribute("ccny", model.getCcny());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewqs");		
	}
	
	/** 
	 * @描述:卫生分录入导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-11 上午11:04:50
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WsflrForm model = (WsflrForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getWsflrDc(model, user);//查询出所有记录，不分页
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
}
