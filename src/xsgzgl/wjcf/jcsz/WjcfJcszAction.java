package xsgzgl.wjcf.jcsz;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* 类名称：WjcfJcszAction 
* 类描述：违纪处分基础设置Action
* 创建人：yijd 
* 创建时间：2012-6-19 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class WjcfJcszAction extends BasicAction {

	private WjcfJcszServices service = new WjcfJcszServices();
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 违纪处分  类别代码查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		List<String[]> rsList = service.cflbdmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// 以下为公共配置项
		request.setAttribute("topTr", service.getTopTr(model,"wjcflb"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcflb"));
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		// 导入导出表配置
		request.setAttribute("tableName", "view_cflbdm");
		request.setAttribute("realTable", "xg_wjcf_cflbdmb");
		return mapping.findForward("success");
	}
	/**
	 * 自定义导出处分类别代码维护数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cflbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.cflbdmExportCx(model);
		
		//List<String[]> rsList = service.cflbdmCx(model);
		
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 违纪处分  类别代码增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;

		if ("save".equals(doType)) {
			boolean result=service.cflbdmZj(model);
			request.setAttribute("result", result);//保存增加结果
		}
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("doType", doType);
		
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 违纪处分   基础类别代码修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		if ("update".equals(doType)) {
			boolean result=service.cflbdmXg(model);
			request.setAttribute("result", result);//保存增加结果
		}
		HashMap<String, String> rs=service.cflbdmCk(pkValue);
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		
		//处分上报中存在未审核完成的数据则不能进行修改审核流
		request.setAttribute("iskxg", service.cxCfsbBycflbdm(rs.get("cflbdm")));
		return mapping.findForward("update");
	}
	
	/**
	 * 检查处分类别代码是否可删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmKfsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(service.cxCflbdmsfksc(pkValue));
		return null;
	}
	
	/**
	 * 违纪处分   基础类别代码删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		//执行删除
		boolean result=service.cflbdmSc(pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("result", result);
		cflbdmCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分  基础类别代码查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		
		HashMap<String, String> rs=service.cflbdmCk(pkValue);
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("rs", rs);
		if(rs.get("spl").equals("wxsh")){
			request.setAttribute("wxsh", "yes");
		}else{
			request.setAttribute("wxsh", "no");
		}
		// 获取用户（是否可写）权限 和title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("view");
	}
	
	
	/**
	 * 违纪处分 原因代码查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		List<String[]> rsList = service.cfyydmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// 以下为公共配置项
		request.setAttribute("topTr", service.getTopTr(model,"wjcfyy"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcfyy"));
		// 获取用户（是否可写）权限 title
		request.setAttribute("path", "wjcfJcsz_cfyydm.do?method=cfyydmCx");
		FormModleCommon.commonRequestSet(request);
		// 导入导出表配置
		request.setAttribute("tableName", "xg_wjcf_cfyydmb");
		request.setAttribute("realTable", "xg_wjcf_cfyydmb");
		return mapping.findForward("success");
	}
	
	/**
	 * 自定义导出违纪处分 原因代码数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cfyydmExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.cfyydmExportCx(model);
		
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 违纪处分  原因代码增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		if ("save".equals(doType)) {
			boolean result=service.cfyydmZj(model);
			request.setAttribute("result", result);//保存增加结果
		}
		// 以下为公共配置项
		request.setAttribute("doType", doType);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 保存处分原因
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCfyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		String lx = request.getParameter("lx");
		boolean flag = false;
		if("zj".equals(lx)){
			flag = service.cfyydmZj(model);
		}else{
			flag = service.cfyydmXg(model);
		}
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		List<String[]> rsList = service.cfyydmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// 以下为公共配置项
		request.setAttribute("topTr", service.getTopTr(model,"wjcfyy"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcfyy"));
		// 获取用户（是否可写）权限 title
		request.setAttribute("path", "wjcfJcsz_cfyydm.do?method=cfyydmCx");
		FormModleCommon.commonRequestSet(request);
		// 导入导出表配置
		request.setAttribute("tableName", "xg_wjcf_cfyydmb");
		request.setAttribute("realTable", "xg_wjcf_cfyydmb");
		return mapping.findForward("success");
	}
	
	
	/**
	 * 违纪处分  处分原因代码解除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgCfyydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(rs));
		return null;
	}
	
	/**
	 * 违纪处分   基础原因代码修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		if ("update".equals(doType)) {
			boolean result=service.cfyydmXg(model);
			request.setAttribute("result", result);//保存增加结果
		}
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		// 以下为公共配置项
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("update");
	}
	
	/**
	 * 违纪处分   基础原因代码删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		//执行删除
		boolean result=service.cfyydmSc(pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("result", result);
		cfyydmCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分  基础原因代码查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		// 以下为公共配置项
		request.setAttribute("rs", rs);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("view");
	}
	
	/**
	 * 申诉解除审批流 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssjcsplCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		HashMap<String, String> rs=service.ssjcsplCx(model);
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		//以下为公共配置项
		List<HashMap<String, String>> shlcList = shlcService.getShlcByDjlx("wjcf");
		request.setAttribute("shlcList", shlcList);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "ssjcsplCx.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("success");
	}
	
	/**
	 * 申诉解除审批流 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssjcsplBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		boolean result=service.ssjcsplBc(model);
		//以下为公共配置项
		request.setAttribute("result", result);
		ssjcsplCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
}
