package xsgzgl.jxgl.hzsf.tdry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class JxglTdryAjax extends BasicExtendAction{
	/**
	 * 团队荣誉获奖查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxhj_tdry.do");
		// IE版本
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		model.setQuery(otherValue[1]);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.tdryCx(model);
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
	 * 团队荣誉获奖添加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//解决乱码问题
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.tdryBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 团队荣誉获奖修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//解决乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setBz(service.unicode2Gbk(model.getBz()));
		String message = service.tdryXg(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 团队荣誉获奖删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdrySc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//解决乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.tdrySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 初始化团营连列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBzdmList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		List<HashMap<String, String>> list = service.getBzdmList(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	/**
	 * 验证保存信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSaveInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		//解决乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message  = service.checkSaveInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}
