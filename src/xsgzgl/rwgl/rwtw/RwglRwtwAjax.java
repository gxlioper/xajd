package xsgzgl.rwgl.rwtw;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class RwglRwtwAjax extends BasicExtendAction{
	/**
	 * 入伍登记查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("rwgl_rwtwgl_rwdjgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("rwdj");
		// 结果集
		ArrayList<String[]> rsArrList = service.rwdjCx(model,request);
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
	 * 入伍登记管理自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward rwdjglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.rwdjExportCx(model,request);
		
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
	
	/**
	 * 在校学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxxsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("rwgl_rwtwgl.do?method=zxxsCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("zxxs");
		// 结果集
		ArrayList<String[]> rsArrList = service.zxxsCx(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTMLByXsxx(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 学生详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsxxCk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		HashMap<String,String> rs = service.xsxxCk(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * 入伍登记保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjBc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		//传输乱码问题
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setRwsj(service.unicode2Gbk(model.getRwsj()));
		model.setRwd(service.unicode2Gbk(model.getRwd()));
		model.setRwdwd(service.unicode2Gbk(model.getRwdwd()));
		String message = service.rwdjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
	/**
	 * 入伍登记删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwdjSc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.rwdjSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 退伍登记查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("rwgl_rwtwgl_twdjgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("twdj");
		// 结果集
		ArrayList<String[]> rsArrList = service.twdjCx(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTMLByTw(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 退伍登记管理自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward twdjglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
	
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.twdjExportCx(model,request);
	
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
	
	/**
	 * 入伍学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwxsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("rwgl_rwtwgl.do?method=rwxsCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("rwxs");
		// 结果集
		ArrayList<String[]> rsArrList = service.rwxsCx(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTMLByXsxx(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 退伍登记保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjBc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		//传输乱码问题
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setRwzh(service.unicode2Gbk(model.getRwzh()));
		model.setTwsj(service.unicode2Gbk(model.getTwsj()));
		model.setYzy(service.unicode2Gbk(model.getYzy()));
		model.setYbj(service.unicode2Gbk(model.getYbj()));
		model.setHjgx(service.unicode2Gbk(model.getHjgx()));
		model.setHkszd(service.unicode2Gbk(model.getHkszd()));
		model.setBz(service.unicode2Gbk(model.getBz()));

		String message = service.twdjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
	/**
	 * 退伍登记删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward twdjSc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.twdjSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 入伍学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwxsCk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RwglRwtwService service  = new RwglRwtwService();
		RwglRwtwForm model = (RwglRwtwForm) form;
		HashMap<String,String> rs = service.getRwxx(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	

}
