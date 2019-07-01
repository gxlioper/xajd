/**
 * @部门:学工产品事业部
 * @日期：2013-6-19 下午03:40:14 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

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
import xsgzgl.comm.BasicInit;
import xsgzgl.dtjs.dtxxgl.DtxxglForm;

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
 * @类功能描述: TODO(财务数据查询) 
 * @作者： cmj [工号：913]
 * @时间： 2013-6-19 下午03:40:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CwsjAction extends SuperAction {
	
	private static final String url = "rcsw_cwsj_qfsjcx.do";
	
	/**
	 * 查询学生财务数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getCwsjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CwsjForm cwsjForm=(CwsjForm)form;
		CwsjService service=new CwsjService();
		if (QUERY.equals(cwsjForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			cwsjForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(cwsjForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_cwsj_qfsjcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		//个性化页面跳转
		String xxpymc=getXxpymc(request);
		String returnType = "/xsgzgl/rcsw/cwsj/"+xxpymc+"/cwsjList.jsp";
		if(validateUrlIsExists(request,returnType)){
			return new ActionForward(returnType,false);
		}
		return mapping.findForward("cwsjlist");
	}
	/**
	 * 
	 * @描述:TODO(获取学校名称拼音)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-19 下午04:36:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	private String getXxpymc(HttpServletRequest request){
		// 学校代码
		String xxdm = (String) request.getSession().getAttribute("xxdm");
		
		// 学校拼音名称
		String xxpymc = new BasicInit().getXxmc(xxdm, null);
		
		return xxpymc;
	}
	
	public boolean validateUrlIsExists(HttpServletRequest request, String jspUrl) {
		File tempFilePath  = new File(request.getRealPath(jspUrl));
		if (!tempFilePath.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CwsjForm model=(CwsjForm)form;
		CwsjService service=new CwsjService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		
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
