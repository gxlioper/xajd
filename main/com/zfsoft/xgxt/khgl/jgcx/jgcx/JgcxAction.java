/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午03:12:17 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
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
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: 考核结果
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 下午03:12:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JgcxAction extends SuperAction {
	
	JgcxService service = new JgcxService();
	
	private static final String url = "khgl_jgcx.do";
	
	/**
	 * 
	 * @描述:结果查询
	 * @作者：cq [工号：785]
	 * @日期：2015-8-18 下午03:25:56
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
	public ActionForward jgcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxList");
	}
	
	
	/**
	 * 按单个项目结果查询
	 */
	@SystemAuth(url = url)
	public ActionForward xmjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.xmjgList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("model", model);
		request.setAttribute("xmInfo", StringUtils.formatData(service.getModel(model)));
		
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			//高级查询path
			request.setAttribute("path", "khgl_jgcx_ckJs.do");
			return mapping.findForward("xmjgJsList");
		}
		//高级查询path
		request.setAttribute("path", "khgl_jgcx_ckXs.do");
		return mapping.findForward("xmjgXsList");
	}
	
	/**
	 * 按单个项目结果查询（首都体育个性化查看）
	 */
	@SystemAuth(url = url)
	public ActionForward xmjgListOfSdty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.xmjgListOfSdty(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("model", model);
		request.setAttribute("xmInfo", StringUtils.formatData(service.getModel(model)));
		request.setAttribute("pfzList", service.getPfzListByXmid(model.getXmid()));
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			//高级查询path
			request.setAttribute("path", "khgl_jgcx_ckJs.do");
			return mapping.findForward("xmjgJsListOfSdty");
		}
		//高级查询path
		request.setAttribute("path", "khgl_jgcx_ckXs.do");
		return mapping.findForward("xmjgXsListOfSdty");
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：cq [工号：785]
	 * @日期：2015-8-19 下午04:54:10
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
		
		JgcxForm model = (JgcxForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.xmjgList(model, user);// 查询出所有记录，不分页
		
		
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
	
	@SystemAuth(url = url)
	public ActionForward exportConfigOfSdty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		String dclb = request.getParameter("dclb");
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.xmjgListOfSdty(model, user);// 查询出所有记录，不分页
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if(dclb.equals("xs")){
			service.exportXsOfSdty(model,response.getOutputStream(),resultList);
		}else{
			service.exportJsOfSdty(model,response.getOutputStream(),resultList);
		}
		
		// ============= end ============

		return null;
	}
	
	/**
	 * 
	 * @描述:评分对象导出
	 * @作者：tgj[工号：1075]
	 * @日期：2017-6-26 下午04:43:57
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
	public ActionForward exportDfrData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getDfrList(model, user);// 查询出所有记录，不分页
		
		
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
	 * 打分统计List
	 */
	@SystemAuth(url = url)
	public ActionForward dftjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		User user = getUser(request);
		
		KhpfService khpfService = new KhpfService();
		HashMap<String, String> ryInfo = khpfService.getRyInfo(model.getKhdxr());
		
		List<HashMap<String, String>> dftjList = service.dftjList(model, user);
		
		request.setAttribute("ryInfo", StringUtils.formatData(ryInfo));
		request.setAttribute("xmmc", dftjList.get(0).get("xmmc"));
		request.setAttribute("dftjList", dftjList);
		request.setAttribute("khbid", request.getParameter("khbid"));
		
		return mapping.findForward("dftjList");
	}
	
	
	public ActionForward lscpjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		SearchModel searchModel=new SearchModel();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			User user = getUser(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.lscpjgList(
					model, user);
		
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
	    searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "khgl_lscpjgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lscpjgList");
	}
	
	/**
	 * 打分人查看
	 */
	@SystemAuth(url = url)
	public ActionForward dfrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);

			List<HashMap<String,String>> resultList = service.getDfrList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "khgljgcx.do?method=dfrListJs";
		
		if(KhpfService.YHLX_XS.equals(model.getPflx())||
				(KhpfService.YHLX_BR.equals(model.getPflx())&&KhpfService.YHLX_XS.equals(model.getKhlx()))){
			path = "khgljgcx.do?method=dfrListXs";
		}
		
		request.setAttribute("path", path);
		request.setAttribute("khbid", request.getParameter("khbid"));
		return mapping.findForward("dfrList");
	}
	
	/**
	 * @描述:浙江商业技师学院个性化：学生对班主任考核分数汇总打印
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月5日 下午3:08:20
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
	public ActionForward xsdbzrhzDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm jgcxForm = (JgcxForm) form;
		//获取cfids
		String khdxrsStr = request.getParameter("khdxrs");
		String [] khdxrs = null;
		
		if(StringUtils.isNotNull(khdxrsStr)){
			khdxrs = khdxrsStr.split(",");
		}
		//根据职工号判断是否是班主任
		List<String> bzrList = new LinkedList<String>();
		boolean isBzr = false;
		for(String khdxr:khdxrs){
			if(Fdypd.isBzr(khdxr, null)){
				bzrList.add(khdxr);
				isBzr = true;
			}
		}
		if(!isBzr){
			//提示：选择的考核对象均不是班主任！
			String messageKey = MessageKey.KHGL_JGCX_KHDX_NOTBZR;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
			throw new SystemException(messageKey);
//			return null;
		}
		
		//根据项目id，班主任（职工号）List查询学生对班主任的汇总打分数据列表
		/*这里存在一个问题：假如一个班级有多个班主任，则打分数据是针对多个班主任的，显示上只有班级不能明确到班主任，
		 * 但学校需求上没有显示班主任，此问题暂不考虑。
		 */
		List<HashMap<String,String>> xsdbzrhzList = service.getXsdbzrhzList(jgcxForm.getXmid(),bzrList);
		//生成word文件，表格按班级遍历
		//一个班级一个表格 不是一个班主任一个表格
		File file = service.getXsdbzrhzFile(xsdbzrhzList);
		
		FileUtil.outputWord(response,file);
		return null;
	}

}
