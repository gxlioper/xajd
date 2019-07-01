/**
 * @部门:学工产品事业部
 * @日期：2016-3-11 下午02:01:48 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-11 下午02:01:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsftjAction extends SuperAction<WsftjForm, WsftjService>{
	private WsftjService service = new WsftjService();
	private static final String url = "cjWsf_wsftj.do";
	
	@SystemAuth(url = url)
	public ActionForward getWsftjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsftjForm myForm = (WsftjForm) form;
//		String[] otherValue = request.getParameter("otherValue")
//		.split("!!@@!!");
//		RequestForm rForm = new RequestForm();
//		SearchRsModel rsModel = new SearchRsModel();
//		User user = getUser(request);
//		service.commInit(rForm, myForm, request, user);
//		// IE版本
//		String ie = otherValue[0];
//		rsModel.setIe(ie);
//		Pages pages = service.setPages("", request);
//		myForm.setPages(pages);
//		if (QUERY.equalsIgnoreCase(myForm.getType())) {
//			// 生成高级查询对象
//			CommService comService = new CommService();
//			SearchModel searchModel = comService.getSearchModel(request);
//			model.setSearchModel(searchModel);
//			User user = getUser(request);
//			if(!service.sfkcx(model)){
//				String messageKey = MessageKey.ZJCM_WSJC_TJ_WTJ;
//				response.getWriter().print(getJsonMessageByKey(messageKey));
//				return null;
//			}
//			// 查询
//			List<HashMap<String, String>> resultList = service.getPageList(
//					model, user);
//			JSONArray dataList = JSONArray.fromObject(resultList);
//			response.getWriter().print(dataList);
//			return null;
//		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		if(Base.currYf.equals("01")){
			searchModel.setSearch_tj_nd(new String[]{String.valueOf((Integer.valueOf(Base.currNd)-1))});
			searchModel.setSearch_tj_yf(new String[]{"12"});
		}else{
			if(String.valueOf((Integer.valueOf(Base.currYf)-1)).length()<2){
				searchModel.setSearch_tj_yf(new String[]{"0"+String.valueOf((Integer.valueOf(Base.currYf)-1))});
			}else{
				searchModel.setSearch_tj_yf(new String[]{String.valueOf(Integer.valueOf(Base.currYf)-1)});
			}
		}
		request.setAttribute("searchTj", searchModel);
		String path = "cjWsf_wsftj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getWsftjList");
	}
	
	/** 
	 * @描述:是否有未提交的寝室
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-14 下午04:29:00
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
	public ActionForward canSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsftjForm model = (WsftjForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		if(!service.sfkcx(model)){
				String messageKey = MessageKey.ZJCM_WSJC_TJ_WTJ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				WsftjDao wsftjDao = new WsftjDao();
				List<HashMap<String, String>> tjList = wsftjDao.getPageList(model, user);
				if(tjList.size()<1){
					String messageKey = MessageKey.ZJCM_WSJC_TJ_WTJ;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}
			}			
		return null;
		}
	
	/** 
	 * @描述:导出数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-14 下午04:29:56
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
		WsftjForm model = (WsftjForm) form;
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
		
}

