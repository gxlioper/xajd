package xsgzgl.jxgl.hzsf.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * 军训管理-基础设置-代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhAjax extends BasicExtendAction{
	/**
	 * 个人荣誉维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("grry");
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getGrryList(model);
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
	 * 团队荣誉维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("tdry");
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getTdryList(model);
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
	 * 个人荣誉维护保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String doType = request.getParameter("doType");
		//解决乱码问题
		model.setGrrymc(service.unicode2Gbk(model.getGrrymc()));
		String message = service.grryBc(model,doType);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 团队荣誉维护保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String doType = request.getParameter("doType");
		//解决乱码问题
		model.setTdrymc(service.unicode2Gbk(model.getTdrymc()));
		String message = service.tdryBc(model,doType);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 个人荣誉维护删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.grrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 团队荣誉维护删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdrySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.tdrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 验证个人荣誉维护信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkGrry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkGrry(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 验证团队荣誉维护信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkTdry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglDmwhService service = new JxglDmwhService();
		JxglDmwhForm model = (JxglDmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkTdry(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
