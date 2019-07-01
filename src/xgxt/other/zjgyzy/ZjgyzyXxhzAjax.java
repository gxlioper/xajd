package xgxt.other.zjgyzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.rcsw.zjbb.RcswZjbbForm;

import com.zfsoft.basic.BasicAction;

public class ZjgyzyXxhzAjax extends BasicAction{
	
	ZjgyzyXxhzInit init= new ZjgyzyXxhzInit();
	
	ZjgyzyXxhzService service= new ZjgyzyXxhzService();
	/**
	 * 学生人数信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsrsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initXsrsManage(rForm, model, request, user);

		// 结果集显示字段getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXsrsList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	
	}
	
	/**
	 * 学生住宿信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initXszsManage(rForm, model, request, user);

		// 结果集显示字段getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXszsList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	
	}
	

	/**
	 * 学生档案信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsdaManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initXsdaManage(rForm, model, request, user);

		// 结果集显示字段getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXsdaList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 辅导员信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initFdyManage(rForm, model, request, user);

		// 结果集显示字段getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getFdyList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	
	}
	
	/**
	 * 辅导员班主任聘用一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward prqkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initPyqkManage(rForm, model, request, user);

		// 结果集显示字段getXsrsList

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getPyqkList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	
	}
	
	/**
	 * 违纪处分信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZjgyzyXxhzForm myForm =(ZjgyzyXxhzForm)form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initWjcfManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getWjcfList(myForm,model,"cx");

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	
	}
	
}
