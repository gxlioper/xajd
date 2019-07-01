package xsgzgl.gygl.hzsf;

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

import com.zfsoft.xgxt.base.log.SystemLog;
/**
 * 公寓管理-湖州师范-楼栋考核管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglAjax extends BasicExtendAction{

	/**
	 * 楼栋考核管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ldkhgl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypygl_gypywh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("ldkhgl");
		// 结果集
		ArrayList<String[]> rsArrList = service.ldkhgl(model);
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
	 * 考核成绩维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward khcjwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_ldkhgl.do?method=khcjwh");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// 结果集
		String nd = model.getSearchModel().getSearch_tj_nd()[0];
		String yf = model.getSearchModel().getSearch_tj_yf()[0];
		model.setPkValue(nd+"-"+yf);
		ArrayList<String[]> rsArrList = service.khcjwh(model);
		// 构建结果集
		String spHtml = service.createSearchHTMLByKhcj(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exp(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * 数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expCj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expCj(response.getOutputStream(),model);
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
	@SystemLog(description="访问公寓管理-楼栋考核管理-楼栋考核管理 -保存PK:{pkValue}")
	public ActionForward khcjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LdkhglService service = new LdkhglService();
		LdkhglForm model = (LdkhglForm)form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.khcjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
