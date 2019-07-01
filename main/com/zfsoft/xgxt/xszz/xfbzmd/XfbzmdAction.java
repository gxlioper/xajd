/**
 * @部门:学工产品事业部
 * @日期：2016-7-18 下午03:45:07 
 */  
package com.zfsoft.xgxt.xszz.xfbzmd;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助模块
 * @类功能描述: 老师可以直接在系统里查看到所有符合学费补助条件的同学名单，信息包括：学号、姓名、学院（大二的还算在学园），
 * 			       同时还能看到每个同学符合学费补助的原因，同时支持导出。
 * 			       导出的EXCLE字段有 序号、学号、学院名称、备注（每个学生符合申请学费补助的条件）
 * @作者： 孟威[工号:1186]
 * @时间： 2016-7-18 下午03:45:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XfbzmdAction extends SuperAction{
	private static final String url = "xszz_zzxmjg_xfbz.do";
	
	@SystemAuth(url = url)
	@SystemLog(description="访问学生资助-学费补助名单查询——list")
	public ActionForward xfbzmdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfbzmdService service=new XfbzmdService();
		XfbzmdForm model=(XfbzmdForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("realTable", "xszz_zzxmjg_xfbz");
		String path = "xszz_zzxmjg_xfbz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfbzmdList");
	}
	/**
	 * @描述: 学费补助名单导出
	 * @作者： MengWei[工号：1186]
	 * @日期：2016-7-18 下午07:06:57
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
	@SystemLog(description="访问学生资助-学费补助名单——导出")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfbzmdService service = new XfbzmdService();
		XfbzmdForm model=(XfbzmdForm) form;
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
