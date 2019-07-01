package xsgzgl.qgzx.cjffjg;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffjgAction extends BasicAction {
	/**
	 * 酬金信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String qgzq = QgCommUtilf.getQgzq();
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_cjffjg_cjxxgl.do");
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_cjffjg_cjxxgl.do");
		service.setRequestValue(rForm, user, request);
		if("xq".equals(qgzq)){
			request.setAttribute("path", "qgzx_cjffjg_cjxxgl_xq.do");
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH end-----------------------
		
		HashMap<String,String> rs = service.setCxmrCs(request);
		request.setAttribute("rs", rs);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_jcffb");
		
		//经费提醒
		request.setAttribute("jftx", service.getJftx(user));
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("cjxxCx");
		}
	}
	
	
	/**
	 * 酬金信息发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxFf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		// 2014-04-20 部门列表只显示有岗位的
		//HashMap<String,String> rs = service.setFfmrCs(request,model);
		HashMap<String,String> rs = service.setFfmrCsNew(request,model);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", Base.xxdm);
		service.setRequestValue(rForm, user, request);
		if("10351".equals(Base.xxdm)){
			request.setAttribute("cs",new QgzxCsszService().getCssz());
		}
		/*取参数配置表中的【酬金发放工时显示】，0:不显示,1:显示*/
		String csz = qgzxCjffsqService.getCspzxx("cjffgs");
		request.setAttribute("csz", csz);
		this.saveToken(request);
		return mapping.findForward("cjxxFf");
	}
	
	
	/**
	 * 酬金信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjxxCk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_cjgl_cjxxgl.do");
		HashMap<String,String> rs = service.cjxxCk(model);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		/*取参数配置表中的【酬金发放工时显示】，0:不显示,1:显示*/
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		String csz = qgzxCjffsqService.getCspzxx("cjffgs");
		request.setAttribute("csz", csz);
		service.setRequestValue(rForm, user, request);
		QgCommUtilf.setCssz(request);
		return mapping.findForward("cjxxCk");
	}
	
	/**
	 * @描述：酬金明细页面根据学号姓名模糊查询
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward cjxxCkByxhxm(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		HashMap<String,String> rs = service.cjxxCk(model);
		response.getWriter().print(rs.get("cjmxHtml"));
		return null;
	}
	
	/**
	 * @描述:将查看的一条酬金发放信息导出为Excel文件
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月6日 下午7:59:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws Exception
	 */
	public ActionForward cjxxExportExcel(ActionMapping mapping,ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm)form;
		
		//获得文件临时路径
		String path = servlet.getServletContext().getRealPath("/temp/mb/");
		
		//生成excel文件
		File file = service.getCjxxExcel(path,model);
		
		//响应下载
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(file.getName(),"utf-8")); 
		FileUtil.outputFile(response, file);
		
		//下载完成后删除生成的文件
		if(file.exists()){
			file.delete();
		}
		return null;
		
	}

	/**
	 *
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-21 下午06:02:27
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
	@SystemLog(description = "勤工助学-酬金发放-导出")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCjffjgService service = new QgzxCjffjgService();
		QgzxCjffjgForm model = (QgzxCjffjgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getCjffList(model, user);
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
