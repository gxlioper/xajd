package xsgzgl.qgzx.glygl;

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
import com.zfsoft.xgxt.base.log.SystemLog;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxGlyglAjax extends BasicAction {
	/**
	 * 管理员维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		QgzxGlyglForm model = (QgzxGlyglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gly");
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getGlyList(model);
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
	 * 管理员增加用户查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		QgzxGlyglForm model = (QgzxGlyglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_glygl_glywh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("yh");
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getYhList(model);
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
	 * 管理员增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-基础设置-勤工管理员维护-增加YHM:{pkValue}")
	public ActionForward glyZjbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		QgzxGlyglForm model = (QgzxGlyglForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.glyZjbc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 管理员删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "勤工助学-基础设置-勤工管理员维护-删除YHM:{pkValue}")
	public ActionForward glySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGlyglService service = new QgzxGlyglService();
		QgzxGlyglForm model = (QgzxGlyglForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.glySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
}
