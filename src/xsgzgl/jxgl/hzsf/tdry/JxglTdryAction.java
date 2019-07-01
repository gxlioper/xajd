package xsgzgl.jxgl.hzsf.tdry;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.jxgl.hzsf.dmwh.JxglDmwhService;

public class JxglTdryAction extends BasicExtendAction{
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
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jxgl_jxhj_tdry.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_jxgl_hzsf_tdryhjb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_tdryhjb");
		JxglDmwhService dmwhService = new JxglDmwhService();
		HashMap<String,String> rs = new HashMap<String,String>();
		rs.put("xn", Base.currXn);
		model.setXn(Base.currXn);//默认当前学年
		model.setBzdj("1");//团代码
		model.setSjdm("");//团上级代码是空
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("tdryList", dmwhService.getTdrydmList());
		request.setAttribute("zjList", service.getZjList(model));
		
		
		return mapping.findForward("tdryCx");
	}
	/**
	 * 团队荣誉获奖增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JxglTdryForm model = (JxglTdryForm)form;
		JxglTdryService service = new JxglTdryService();
		JxglDmwhService dmwhService = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		HashMap<String,String> rs = new HashMap<String,String>();
		String xn = request.getParameter("xn");
		rs.put("xn", xn);
		User user = getUser(request);
		rForm.setPath("jxgl_jxhj_tdry.do");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("tdryList", dmwhService.getTdrydmList());
		request.setAttribute("zjList", service.getZjList(model));
		return mapping.findForward("tdryZj");
	}
	/**
	 * 团队荣誉获奖修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tdryXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JxglTdryService service = new JxglTdryService();
		JxglTdryForm model = (JxglTdryForm)form;
		JxglDmwhService dmwhService = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		HashMap<String,String> rs = service.getTdryMap(model);
		
		User user = getUser(request);
		rForm.setPath("jxgl_jxhj_tdry.do");
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("tdryList", dmwhService.getTdrydmList());
		return mapping.findForward("tdryXg");
	}
}
