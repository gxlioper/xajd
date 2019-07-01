/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.dekt.xmwh;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;


/**
 * @系统名称：学生工作管理系统
 * @模块名称：第二课堂-项目维护 管理模块
 * @类功能描述：TODO(这里用一句话描述这个类的作用) 
 * @作者：卓耐[工号:1391]
 * @时间：2017年4月21日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DektxmwhAction extends SuperAction<DektxmwhForm,DektxmwhService> {
	private DektxmwhService service = new  DektxmwhService();
	private static final String url = "dekt_xmwh_list.do";
	
	public ActionForward xmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhList");
	}
	
	public ActionForward getXmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm model = (DektxmwhForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	
	
	public ActionForward splcEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm model = (DektxmwhForm) form;
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("dekt");
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("splcEdit");
	}
	
	public ActionForward splcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm myForm = (DektxmwhForm) form;
		boolean result =service.runUpdate(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
//	public ActionForward exportData(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DektxmwhForm model = (DektxmwhForm) form;
//		// 生成高级查询对象
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		model.setSearchModel(searchModel);
//		
//		User user = getUser(request);
//		List<HashMap<String, String>> resultList = service.getAllList(model);// 查询出所有记录，不分页
//		 
//		IExportService exportService = new ExportExcelImpl();
//		ExportModel exportModel = model.getExportModel();
//		exportModel.setZgh(user.getUserName());// 当前操作员
//		exportModel.setDataList(resultList);// 设置数据
//		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
//		File file = exportService.getExportFile(exportModel);// 生成导出文件
//		FileUtil.outputExcel(response, file);
//		return null;
//	}
	
	
}