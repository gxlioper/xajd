package xsgzgl.jxgl.general.jxjzgl.lsjzgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglFrom;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglService;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhForm;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_历史建制管理_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 易江东
 * @version 1.0
 */

public class LsjzglAction extends BasicAction {
	/**
	 * 查询历史军训建制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom lsjzModel=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		//查询军训信息列表
		List<HashMap<String, String>> jxxxList=jxxxwhService.getJxxxList();
		if(jxxxList == null || jxxxList.size() == 0){
			//返回不能建制的页面
			String msg = "当前还未建立过任何军训,无历史军训信息！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//查询统计人数
		lsjzModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(lsjzModel);
		//设置查询人数
		request.setAttribute("rsTj", rsTj);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		request.setAttribute("jxxxList", jxxxList);
		
		// 访问路径
		String path = "jxjzgl_lsjzgl_cxLsjz.do";
		rForm.setPath(path);
		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("cxLsjz");
	}
	
	/**
	 * 查询建制统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzTjbAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		
		String jxjzTjbHtml=service.createJxjzTjxHtml(jxjzList, model);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(jxjzTjbHtml));
		return null;
	}
	
	/**
	 * 查询建制统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjxxxAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		JxglJxxxwhForm jxxxModle=new JxglJxxxwhForm();
		jxxxModle.setJxid(model.getSjid());
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModle);
		//查询统计人数
		HashMap<String, String> rsTj=service.getRszjTjb(model);
		
		String lsjxxxHtml=service.createLsjxjzHtml(jxxxwhModel, rsTj);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(lsjxxxHtml));
		return null;
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcJzTjb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		JxglJxxxwhForm jxxxModel=new JxglJxxxwhForm();
		jxxxModel.setJxid(model.getSjid());
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModel);
		//建制人数统计
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		//查询统计人数
		HashMap<String, String> title=service.getRszjTjb(model);
		title.put("jxmc", jxxxwhModel.get("jxmc"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcJzTjb(response.getOutputStream(),model,title,jxjzList);
		return mapping.findForward("");
	}
	
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		LsjzglFrom model=(LsjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		User user=getUser(request);
		
		JxglJxxxwhForm jxxxModel=new JxglJxxxwhForm();
		jxxxModel.setJxid(model.getSjid());
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhByJxidModel(jxxxModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "jxjzgl_lsjzgl_cxLsjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax");
		// ----------------设置PATH end-----------------------
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		
		//设置导出
		request.setAttribute("tableName", "xg_view_jxgl_jxjzmdb");//一般为查询视图
		request.setAttribute("jxxxwhModel", jxxxwhModel);//查询军训信息
		
		//默认高级查询条件
		String jzid=request.getParameter("jzid");
		String bjdm=request.getParameter("bjdm");
		if(jzid != null && !"".equals(jzid)){
			SearchModel searchModel = service.setDefaultSearchModel(jzid, bjdm);
			request.setAttribute("searchTj", searchModel);
		}
		String sjjz=request.getParameter("sjjz");
		if(sjjz != null && !"".equals(sjjz)){
			SearchModel searchModel = new SearchModel();
			if("1".equals(sjjz)){
				searchModel.setSearch_tj_sfybz(new String[]{"是"});
			}else{
				searchModel.setSearch_tj_sfybz(new String[]{"否"});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		return mapping.findForward("cxLsjzmd");
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxLsjzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//查询路径
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax");
		//model.getSearchModel().setPath("jxjzgl_lsjzgl_cxLsjz.do");
		// IE版本
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		rsModel.setIe(otherValue[0]);
		//其它参数
		model.setSjid(otherValue[1]);
		model.setJxid(otherValue[1]);
		
		//获取当前军训信息
		ArrayList<String[]> rsArrList=service.getJxjzmdList(model);
		//表头
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 导出维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcLsjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();
		
		//建制人数统计
		List<HashMap<String, String>> lsjzmdList=service.getJxjzmdNoPageList(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcLsjzmd(response.getOutputStream(), lsjzmdList);
		return mapping.findForward("");
	}
	
	/**
	 * 历史建制管理自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward lsjzglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		LsjzglFrom model=(LsjzglFrom)form;
		LsjzglService service=new LsjzglService();		
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getJxjzmdExportDataList(model);
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
