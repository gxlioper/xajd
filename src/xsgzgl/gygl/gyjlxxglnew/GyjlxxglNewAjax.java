package xsgzgl.gygl.gyjlxxglnew;

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

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyjlxxglNewAjax extends BasicAction{
	
	/**
	 * 公寓纪律信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxwh");
		// 结果集
		ArrayList<String[]> rsArrList = service.gyjlxxwhCx(myForm,request);
		// 构建结果集
		String spHtml = service.createSearchHTMLgyjlxxwh(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 公寓纪律信息维护 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gyjlxxwhexportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//根据path判断是否需要公寓管理员数据范围
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh.do");
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList  = service.gyjlxxwhExportCx(model,request);
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
	 * 公寓纪律学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyjl_gyjlglnew_gyjlxscx.do");
		request.setAttribute("path", "gyjl_gyjlglnew_gyjlxscx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxscx");
		// 结果集
		ArrayList<String[]> rsArrList = service.gyjlxscx(myForm,request);
		// 构建结果集学生信息
		String spHtml = service.createSearchHTMLXscx(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 学生纪律信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjlxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		myForm.setXh(user.getUserName());
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyjl_gyjlglnew_xsjlxxcx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("xsjlxxcx");
		// 结果集
		ArrayList<String[]> rsArrList = service.xsjlxxcx(myForm);
		// 构建结果集学生信息
		String spHtml = service.createSearchHTMLXsjl(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 获得学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		HashMap<String,String> rs = service.getStuInfo(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * 公寓纪律信息处理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxcl.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxcl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxcl");
		// 结果集
		ArrayList<String[]> rsArrList = service.gyjlxxclCx(myForm,request);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 公寓纪律信息审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxsh.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxsh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxsh");
		// 结果集
		ArrayList<String[]> rsArrList = service.gyjlxxshCx(myForm,request);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 获得学生信息    getWjxxList
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWjxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		ArrayList<String[]> rs = service.getWjxxList(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	
	/**
	 * 公寓纪律信息批量处理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxPlcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setCljg(service.unicode2Gbk(model.getCljg()));
		model.setDcqk(service.unicode2Gbk(model.getDcqk()));
		String message = service.gyjlxxPlcl(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 公寓纪律信息批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		User user = getUser(request);// 用户对象
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setShyj(service.unicode2Gbk(model.getShyj()));
		model.setShzt(model.getShzt());
		model.setShr(user.getUserName());
		model.setShsj(DateUtils.getCurrTime());
		String message = service.gyjlxxPlsh(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:公寓纪律信息审核  自定义导出
	 * @作者：cq [工号：785]
	 * @日期：2013-12-30 下午03:28:05
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
	public ActionForward gyjlxxshexportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//根据path判断是否需要公寓管理员数据范围
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxsh.do");
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList  = service.gyjlxxshExportCx(model,request);
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
