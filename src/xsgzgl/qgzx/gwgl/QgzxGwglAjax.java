package xsgzgl.qgzx.gwgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglAjax extends BasicAction {

	
	/**
	 * 岗位申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsq");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwsqList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 用人单位岗位申请 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getGwsqExportList(model,user);
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
	 * 岗位审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsh");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwshList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByGwsh(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 岗位信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwxxgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwxx");
		// 结果集
		ArrayList<String[]> rsArrList = service.getGwxxList(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 获得学生列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_gwgl_getStu.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		String pkValue= request.getParameter("pkValue");
		model.setPkValue(pkValue);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("stu");
		// 结果集
		ArrayList<String[]> rsArrList = service.getStuList(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 岗位信息管理增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-增加XN:{xn},YRBM:{yrbm},GWMC:{gwmc},GWXZDM:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		//传输乱码问题
		String message = service.gwxxBc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 岗位信息管理修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-增加XN:{xn},YRBM:{yrbm},GWMC:{gwmc},GWXZDM:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		//传输乱码问题
		String message = service.gwxxXg(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 岗位信息管理复制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String type = request.getParameter("doType");
		  if ("fz".equalsIgnoreCase(type)){
			  	String pkValue = request.getParameter("pkValue");
				String xn = request.getParameter("xn");
				myForm.setPkValue(pkValue);
				String message = service.gwxxFz(xn,myForm);
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print(message);
				return null;
			  
		  }else{
			  String num = request.getParameter("num");
			  String len = request.getParameter("len");
			  String str = request.getParameter("str");
			  String idList = request.getParameter("idList");
			  request.setAttribute("num", num);
			  request.setAttribute("len", len);
			  request.setAttribute("str", str);
			  request.setAttribute("idList", idList);
		  }
		  
		  return mapping.findForward("gyxxFzgw");
		
	}
	
	
	/**
	 * 岗位信息管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-岗位人员维护-删除VALUES:{pkValue}")
	public ActionForward gwxxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwxxSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理保存信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理删除信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.checkScInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理复制信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkFzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		String xn = request.getParameter("xn");
		myForm.setXn(xn);
		myForm.setPkValue(pkValue);
		String message = service.checkFzInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 获得学年（复制）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> list = Base.getXnndList();
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	/**
	 * 获得学生信息列表（增加学生）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		//解决乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		List<HashMap<String, String>> list = service.getXsxxList(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	
	/**
	 * 保存增加人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZjryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setXn(service.unicode2Gbk(model.getXn()));
		String message = service.bcZjryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 删除人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcScryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		String message = service.scRyxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 保存退岗人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcTgryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setSgsj(service.unicode2Gbk(model.getSgsj()));
		model.setTgyy(service.unicode2Gbk(model.getTgyy()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setSqbhs(service.unicode2Gbk(model.getSqbhs()));
		String message = service.bcTgryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 人员信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		HashMap<String,String> rs = service.ryxxCk(model,request);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * 验证岗位信息管理复制信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScRyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String message = service.checkScRyxx(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 验证增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkZjGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkZjGwsqInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 保存增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZjGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//传输乱码问题
		model.setUser(service.getUser(request));
		String message = service.bcZjGwsq(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 岗位申请管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwsqSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 保存增加岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcXgGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		//传输乱码问题
		String message = service.bcXgGwsq(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 岗位信息审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxshBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setShyj(service.unicode2Gbk(model.getShyj()));
		model.setUser(service.getUser(request));
		String message = service.gwxxshBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
	
}
