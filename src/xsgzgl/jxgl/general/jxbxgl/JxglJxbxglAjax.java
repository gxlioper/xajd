package xsgzgl.jxgl.general.jxbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

public class JxglJxbxglAjax extends BasicAction{

	/**
	 * 军训表现查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		JxglJxbxglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxkhgl_jxbxgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = JxglJxbxglService.getTopTr("jxbx");
		// 结果集
		ArrayList<String[]> rsArrList = JxglJxbxglService.jxbxCx(model);
		// 构建结果集
		String spHtml = JxglJxbxglService.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		JxglJxbxglService.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
		
	}
	

	/**
	 * 表现名单查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		// ============= 初始化各变量的值 ============
		JxglJxbxglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxbxgl.do?method=bxmdCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = JxglJxbxglService.getTopTr("bxmd");
		// 结果集
		ArrayList<String[]> rsArrList = JxglJxbxglService.bxmdCx(model,request);
		// 构建结果集
		String spHtml = JxglJxbxglService.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		JxglJxbxglService.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
		
	}
	
	
	/**
	 * 表现名单操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		//传输乱码问题
		model.setPkValue(JxglJxbxglService.unicode2Gbk(model.getPkValue()));
		model.setXh(JxglJxbxglService.unicode2Gbk(model.getXh()));
		String message = JxglJxbxglService.bxmdCz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	
}
