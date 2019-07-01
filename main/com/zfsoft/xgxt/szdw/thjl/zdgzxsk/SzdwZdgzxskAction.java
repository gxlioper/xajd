package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * 重点关注学生库维护 
 */
public class SzdwZdgzxskAction extends SuperAction {
	
	private static SzdwZdgzxskService service = new SzdwZdgzxskService();
	
	private static final String url = "szdw_thjl_zdgzxsk.do";
	
	/** 
	 * 重点关注学生库查询列表
	 */
	@SystemAuth(url = url)
	public ActionForward zdgzxskManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwZdgzxskForm myForm = (SzdwZdgzxskForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> zdgzxskInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(zdgzxskInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("userType", user.getUserType());
		request.setAttribute("path", "szdw_thjl_zdgzxsk.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("zdgzxskManage");
	}
	
	/**
	 * 重点关注学生库导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZdgzxskData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwZdgzxskForm myForm=(SzdwZdgzxskForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

}
