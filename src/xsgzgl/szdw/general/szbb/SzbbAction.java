package xsgzgl.szdw.general.szbb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_思政编班_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzbbAction extends BasicAction {

	/**
	 * 查询思政辅导员编班
	 * 
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward searchSzbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getSzbbTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service
				.getSzbbList(myForm, model, user);
		// 构建结果集
		String spHtml = service.createSzbbHTML(rsModel, model, rsArrList, user,true);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 查询思政班主任编班
	 *
	 * @date 2018-09-15
	 * @author wn
	 */
	public ActionForward searchSzBzrbb(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getSzBzrbbTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service
				.getSzBzrbbList(myForm, model, user);
		// 构建结果集
		String spHtml = service.createSzbbHTML(rsModel, model, rsArrList, user,false);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 查询已分配辅导员信息
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward loadYfpFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配辅导员前台显示TBODY
		String html=service.createYfpFdyHTML(bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(html);

		return null;
		
	}
	
	/**
	 * 查询已分配班主任信息
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward loadYfpBzr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配班主任前台显示信息HTML
		String html=service.createYfpBzrHTML(bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(html);

		return null;
	}
	
	/**
	 * 未编班教师数据加载（辅导员 or 班主任）
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward searchSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommService commService=new CommService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// IE版本
		String ie = request.getParameter("ie");
		rsModel.setIe(ie);
		
		String bjdm=request.getParameter("bjdm");
		myForm.setBjdm(bjdm);
		
		String fplx=request.getParameter("hid_fplx");
		
		commService.getModelValue(myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================显示内容========================
		// 标题
		//湖南涉外个性化，修改辅导员编班
		List<HashMap<String, String>> topTr = null;
		if("12303".equals(Base.xxdm)){
			if("fdy".equalsIgnoreCase(fplx)){
				topTr = service.getSzbbSetTop1(model, user);
			}else if("bzr".equalsIgnoreCase(fplx)){
				topTr = service.getSzbbSetTop(model, user);
			}
		}else{
			 topTr = service.getSzbbSetTop(model, user);
		}
		// 结果集
		ArrayList<String[]> rsArrList =new ArrayList<String[]>();
		
		// 编班类型 为辅导员编班
		model.setFplx(fplx);
		
		String spHtml ="";
		if("fdy".equalsIgnoreCase(fplx)){
			
			rsArrList=(ArrayList<String[]>)service.getWfpFdyList(myForm, model, user);
			// 构建结果集
			spHtml = service.createWfpFdyHTML(rsModel, model, rsArrList, user);
		}else if("bzr".equalsIgnoreCase(fplx)){// 编班类型 为班主任编班
			
			rsArrList=(ArrayList<String[]>)service.getWfpBzrList(myForm, model, user);
			// 构建结果集
			spHtml = service.createWfpBzrHTML(rsModel, model, rsArrList, user);
		}
		
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		
		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 取消辅导员编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward cancelFdybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 辅导员职工号
		String zgh=request.getParameter("zgh");
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配班主任前台显示信息HTML
		boolean flag=service.cancelFdybb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * 取消班主任编班
	 * @date 2013-01-09
	 * @author qlj
	 */
	public ActionForward cancelBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 班主任职工号
		String zgh=request.getParameter("zgh");
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配班主任前台显示信息HTML
		boolean flag=service.cancelBzrbb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * 辅导员编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward setFdybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 辅导员职工号
		String zgh=request.getParameter("zgh");
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配班主任前台显示信息HTML
		boolean flag=service.setFdybb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * 班主任编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward setBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= 初始化各变量的值 ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// 班主任职工号
		String zgh=request.getParameter("zgh");
		
		// 班级代码
		String bjdm=request.getParameter("bjdm");
		
		// 构建已分配班主任前台显示信息HTML
		boolean flag=service.setBzrbb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	
	/**
	 * 辅导员信息维护自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward szdwbbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		SzdwGeneralService myService = new SzdwGeneralService();
//		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		SzdwGeneralService szdwGeneralService = new SzdwGeneralService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList;
		String dcclbh = request.getParameter("dcclbh");
		if("general_szdw_bzr.do".equals(dcclbh)){
			resultList = szdwGeneralService.getSzBzrbbList(myForm,user);
		} else {
			resultList = szdwGeneralService.getSzbbList(myForm,user);
		}
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(dcclbh);//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	public ActionForward jslx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String quantity = request.getParameter("quntity");
		request.setAttribute("quntity", quantity);
		return mapping.findForward("jslx");
	} 
	
	public ActionForward jsbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String quantity = request.getParameter("quntity");
		request.setAttribute("quntity", quantity);
		return mapping.findForward("jsbb");
	}
}
