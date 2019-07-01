/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午10:18:29 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

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
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.gygl.wsjc.jcrc.JcrcModel;
import com.zfsoft.xgxt.gygl.wsjc.jcrc.JcrcService;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmService;

/** 
 * @类功能描述: 卫生分录入
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-4 上午10:18:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfAction extends SuperAction<WsfModel, WsfService> {

	private static final String url = "gygl_wsjc_wsflr.do";
	
	/**日常卫生检查录入 列表**/
	@SystemAuth(url = url)
	public ActionForward fslrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_wsflr.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fslrList");
	}
	
	
	/**日常卫生检查录入 列表**/
	@SystemAuth(url = url)
	public ActionForward getFslrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// 查询
		List<HashMap<String, String>> resultList = service.getRclrList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**卫生分录入**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wsflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfModel model = (WsfModel) form;
		WsfService service = getService();
		
		JcxmService jcxmService = new JcxmService();
		List<HashMap<String, String>>  rcxmList = jcxmService.getRcxmList(model.getRcid(),model.getJcdx());
		request.setAttribute("rcxmList", rcxmList);
		
		JcrcService jcrcService = new JcrcService();
		JcrcModel jcrcInfo = jcrcService.getModel(model.getRcid());
		request.setAttribute("jcrcInfo", StringUtils.formatData(jcrcInfo));
		
		List<String> djList = service.getWsfdjList();
		List<String> xjList = service.getWsfxjList();
		
		request.setAttribute("djList", djList);
		request.setAttribute("xjList", xjList);
		
		request.setAttribute("path", "gygl_wsjc_fslr.do");
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_sfrz(new String[]{"是"});
		request.setAttribute("searchTj", searchModel);
//		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wsflr");
	}
	
	
	
	/**卫生分录入查询列表**/
	@SystemAuth(url = url)
	public ActionForward getWsflrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// 查询
		List<HashMap<String, String>> resultList = service.getFslrList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	

	/**保存卫生分***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		model.setLrr(user.getUserName());
		boolean isSuccess = service.runInsert(model);
		response.getWriter().print(isSuccess);
		
		return null;
	}
	
	
	/**卫生分导入***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward importWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("jsonStr", request.getParameter("jsonStr"));
		return mapping.findForward("importWsf");
	}
	
	
	
	/***下载导入模版**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(model, user);
		FileUtil.outputExcel(response, file);
		
		return null;
	}
	
	
	
	/**卫生分导入保存**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveImportWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		try {
			File file = service.importWsf(model, user);
			
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
		
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		} catch (Exception e) {
			request.setAttribute("result", false);
			request.setAttribute("message", "导入失败！");
		}
		
		return importWsf(mapping, model, request, response);
	}
	
	
	/**卫生分查询***/
	@SystemAuth(url = "gygl_wsjc_wsfcx.do")
	public ActionForward fscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		request.setAttribute("path", "gygl_wsjc_wsfcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fscxList");
	}
	
	
	/**卫生分查询***/
	@SystemAuth(url = url)
	public ActionForward getFscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/***导出卫生分****/
	@SystemAuth(url = "gygl_wsjc_wsfcx.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportFscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model,user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
