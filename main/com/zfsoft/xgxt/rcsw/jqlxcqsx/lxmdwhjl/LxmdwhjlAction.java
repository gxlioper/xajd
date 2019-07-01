/**
 * @部门:学工产品事业部
 * @日期：2017年3月27日 下午1:49:56 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年3月27日 下午1:49:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhjlAction extends SuperAction<LxmdwhjlForm,LxmdwhjlService>{
	
	private static final String url = "jqlx_lxmdwhjl.do?method=lxmdwhjlList";
	private LxmdwhjlService service = new LxmdwhjlService();
	
	/**
	 * @描述:跳转到留校名单维护记录列表页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月27日 下午2:40:35
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
	public ActionForward lxmdwhjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String path = "jqlx_lxmdwhjl.do?method=lxmdwhjlList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxmdwhjlList");
		
	}
	
	/**
	 * @描述:查询离校名单维护列表（JSON）数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月27日 下午2:43:08
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
	public ActionForward getLxmdwhjlData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LxmdwhjlForm lxmdwhjlForm = (LxmdwhjlForm)form;

		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		lxmdwhjlForm.setSearchModel(searchModel);
		
		List<HashMap<String, String>> resultList = service.getPageList(lxmdwhjlForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:离校名单维护记录查看
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月27日 下午2:44:38
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
	public ActionForward lxmdwhjlShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jlid = request.getParameter("jlid");
		HashMap<String,String> lxmdwhjl = service.getLxmdwhjlById(jlid);
		request.setAttribute("lxmdwhjl", lxmdwhjl);
		return mapping.findForward("lxmdwhjlShow");
	}
	
	/**
	 * @描述:导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月28日 上午8:49:32
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LxmdwhjlForm lxmdwhjlForm = (LxmdwhjlForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		lxmdwhjlForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(lxmdwhjlForm,user);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = lxmdwhjlForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
