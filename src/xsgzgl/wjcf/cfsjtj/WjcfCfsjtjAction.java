package xsgzgl.wjcf.cfsjtj;

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
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.basic.BasicAction;

public class WjcfCfsjtjAction extends BasicAction {
	private WjcfJcszServices wjcfjcszServices=new WjcfJcszServices();
	private WjcfCfsjtjService wjcfCfsjtjService=new WjcfCfsjtjService();
	
	public ActionForward xywjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("wjcfCfsjtj.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "wjcfCfsjtj.do");
		RequestForm rForm =new RequestForm();
		User user=getUser(request);
		wjcfCfsjtjService.setRequestValue(rForm, user, request);
		//WjcfCfsjtjActionForm myForm=(WjcfCfsjtjActionForm)form;
		//String xn=myForm.getXn();
		//String cftjlx=request.getParameter("cftjlx");
		List<HashMap<String, String>> cflbsList=wjcfjcszServices.cflbmcCx();
		request.setAttribute("cflbsList", cflbsList);
		List<String[]> tjList=new ArrayList<String[]>();
		request.setAttribute("rsList", tjList);
//		if("1".equalsIgnoreCase(cftjlx)){
//			request.setAttribute("cftjlx", cftjlx);
//			String ksxn=request.getParameter("ksxn");
//			String jsxn=request.getParameter("jsxn");
//		}
//		
//		if("2".equalsIgnoreCase(cftjlx)){
//			request.setAttribute("cftjlx", cftjlx);
//			tjList = wjcfCfsjtjService.xycftj(myForm,cflbsList);
//			request.setAttribute("tjList", tjList);
//		}
//		if("3".equalsIgnoreCase(cftjlx)){
//			request.setAttribute("cftjlx", cftjlx);
//			tjList=wjcfCfsjtjService.yltj(myForm);
//			request.setAttribute("tjList", tjList);
//		}
//		String doType = request.getParameter("doType");
//		if("xytj".equalsIgnoreCase(doType)){
//			response.reset();
//			response.setContentType("application/vnd.ms-excel");
//			wjcfCfsjtjService.exportData(response,cftjlx,tjList,cflbsList,xn,response.getOutputStream());
//			return null;
//		}
//		if("yltj".equalsIgnoreCase(doType)){
//			response.reset();
//			response.setContentType("application/vnd.ms-excel");
//			wjcfCfsjtjService.exportData(response,cftjlx,tjList,xn,response.getOutputStream());
//			return null;
//		}		
		//request.setAttribute("cftjlx", cftjlx);
		return mapping.findForward("xywjtj");
	}
	public ActionForward xnyl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("xnyl.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xnyl.do");
		RequestForm rForm =new RequestForm();
		User user=getUser(request);
		wjcfCfsjtjService.setRequestValue(rForm, user, request);
		List<HashMap<String, String>> cflbsList=wjcfjcszServices.cflbmcCx();
		request.setAttribute("cflbsList", cflbsList);
		List<String[]> tjList=new ArrayList<String[]>();
		request.setAttribute("rsList", tjList);
		return mapping.findForward("xnyl");
	}
	
	public ActionForward xnylAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ArrayList<String[]> tjList=new ArrayList<String[]>();
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		//request.setAttribute("path", "wjcfCfsjtj.do");
		rForm.setPath("xnyl.do");
		WjcfCfsjtjActionForm myForm=(WjcfCfsjtjActionForm)form;
		
		SearchModel searchModel = wjcfjcszServices.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		tjList = (ArrayList<String[]>) wjcfCfsjtjService.yltj(myForm);
		
		
		Pages pages = wjcfjcszServices.setPages("", request);
		myForm.setPages(pages);

		// 构建结果集
		String spHtml =wjcfCfsjtjService.createHTML2(rsModel, tjList);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(tjList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		wjcfjcszServices.createRs(rsModel, pages, response);
		//request.setAttribute("rsList", tjList);
		return null;
	}
	
	public ActionForward xywjtjAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ArrayList<String[]> tjList=new ArrayList<String[]>();
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		//request.setAttribute("path", "wjcfCfsjtj.do");
		rForm.setPath("wjcfCfsjtj.do");
		WjcfCfsjtjActionForm myForm=(WjcfCfsjtjActionForm)form;
		
		SearchModel searchModel = wjcfjcszServices.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		
		List<HashMap<String, String>> cflbsList=wjcfjcszServices.cflbmcCx();
		tjList = (ArrayList<String[]>) wjcfCfsjtjService.xycftj(myForm,cflbsList);
		
		
		Pages pages = wjcfjcszServices.setPages("", request);
		myForm.setPages(pages);

		// 构建结果集
		String spHtml =wjcfCfsjtjService.createHTML(rsModel, tjList);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(tjList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		wjcfjcszServices.createRs(rsModel, pages, response);
		//request.setAttribute("rsList", tjList);
		return null;
	}
	public ActionForward xyExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjtjActionForm myForm=(WjcfCfsjtjActionForm)form;
		ArrayList<String[]> tjList=new ArrayList<String[]>();
		List<HashMap<String, String>> cflbsList=wjcfjcszServices.cflbmcCx();
		tjList = (ArrayList<String[]>) wjcfCfsjtjService.xycftj(myForm,cflbsList);
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		wjcfCfsjtjService.exportData(response,tjList,cflbsList,input,response.getOutputStream());
		return null;
	}
	public ActionForward ylExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjtjActionForm myForm=(WjcfCfsjtjActionForm)form;
		ArrayList<String[]> tjList=new ArrayList<String[]>();
		tjList = (ArrayList<String[]>) wjcfCfsjtjService.yltj(myForm);
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		wjcfCfsjtjService.exportData(response,tjList,input,response.getOutputStream());
		return null;
	}
}
