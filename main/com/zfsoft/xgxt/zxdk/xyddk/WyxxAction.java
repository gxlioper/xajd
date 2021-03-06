/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:58:45 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.io.File;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约信息
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-10-10 下午02:58:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyxxAction extends SuperAction<WyxxModel, WyxxService> {

	private static final String url = "zxdk_gjdk_wygl.do";
	
	@SystemAuth(url = url)
	public ActionForward wyxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_gjdk_wygl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wyxxList");
	}
	
	@SystemAuth(url = url)
	public ActionForward getWyxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyxxService service = getService();
		WyxxModel model = (WyxxModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward dcwy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WyxxService service = getService();
		WyxxModel model = (WyxxModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	
	
	/**
	 * 
	 * @描述: 违约备注
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 上午09:24:31
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
	public ActionForward wybz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WyxxService service = getService();
		WyxxModel wyxxForm = (WyxxModel) form;
		
		WyxxModel model = service.getModel(wyxxForm);
		
		if (model != null){
			BeanUtils.copyProperties(wyxxForm, model);
		}
		
		return mapping.findForward("wybz");
	}
}
