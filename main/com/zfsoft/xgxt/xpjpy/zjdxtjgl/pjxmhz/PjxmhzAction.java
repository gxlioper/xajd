/**
 * @部门:学工产品(1)部
 * @日期：2018-1-5 上午10:41:00 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优
 * @类功能描述:  浙江大学新评奖评优-统计查询-评奖项目汇总
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-5 上午10:26:12 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_pjxmhz.do";
	
	/**
	 * @描述: 页面回显数据查询
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-5 下午05:52:18
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
	@SystemLog(description = "访问新评奖评优-统计管理-评奖项目汇总-查询页面")
	public ActionForward getPjxmhzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//得到用户
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "xpjpy_tjgl_pjxmhz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjxmhzList");
	}
	
	/**
	 * @描述: 评奖项目汇总导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-5 下午05:50:58
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
		
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();

		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*导出功能代码*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*当前操作员*/
		exportModel.setZgh(user.getUserName());
		/*设置数据*/
		exportModel.setDataList(resultList);
		/*设置当前导出功能编号*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*生成导出文件*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 点击获奖人数连接查看详细信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-9 上午09:46:10
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
	public ActionForward pjxmhzHjrsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		PjxmhzForm model = (PjxmhzForm)form;
		PjxmhzService service = new PjxmhzService();
		
		if(QUERY.equals(model.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.viewRs(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("pjxmhzHjrsView");
	}
	
	/**
	 * @描述: 上报表下载
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-10 下午05:45:09
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
	public ActionForward reportDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		return null;
	}
}
