/**
 * @部门:学工产品事业部
 * @日期：2014-5-7 下午01:51:54 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-5-7 下午01:51:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxglAction extends SuperAction {
	
	private static final String url = "xljk_xljkzx_xlzxgl.do";
	
	/**
	 * 
	 * @描述:查询心理咨询管理（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-7 下午02:01:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_xljkzx_xlzxgl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryXlzxgl");
	}
	
	/**
	 * 
	 * @描述:查询心理咨询管理
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-7 下午02:01:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlzxglAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlzxglForm model = (XlzxglForm) form;
		XlzxglService service = new XlzxglService();
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-7 下午17:31:39 
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
		XlzxglForm model  = (XlzxglForm) form;
		XlzxglService service = new XlzxglService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllXlzxglList(model, user);
		resultList = resultList == null?new ArrayList<HashMap<String,String>>():resultList;
		HashMap<String,String> mapTemp = null;
		for(int i = 0 ; i<resultList.size();i++){
			mapTemp = resultList.get(i);
			mapTemp.put("yyzxzt", StringTools.TextToString1(StringTools.strOutNull(mapTemp.get("yyzxzt"))));
		}
		
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
