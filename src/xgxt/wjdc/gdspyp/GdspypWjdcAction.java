package xgxt.wjdc.gdspyp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class GdspypWjdcAction extends BasicExtendAction{
	/**
	 * 马氏学生结果分析
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgfxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取用户
		User user = getUser(request);
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		GdspypWjdcService service = new GdspypWjdcService();
	
		String doType = request.getParameter("doType");
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delJgfx(pks) ? "删除成功！" : "删除失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("rs", service.jgfxQuery(myForm));
		// 年级学院专业班级List与title和读写权
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		setWriteAbleAndTitle(request, "wjdc_msxldc_jgfx.do");
		
		request.setAttribute("topTr", service.getTopTr("jgfx"));
		request.setAttribute("user", user);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("jgfxManage");
	}
	
	/**
	 * 评议内容维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pywhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		
		GdspypWjdcService service = new GdspypWjdcService();
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.savePynr(myForm) ? "保存成功！" : "保存失败！";
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("pyList", service.getPy());
		request.setAttribute("rs", service.getJgfxOne(pkValue));
		return mapping.findForward("pywhUpdate");
	}
	
	/**
	 * 问卷回答
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjhd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName=request.getSession().getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		GdspypWjdcService service=new GdspypWjdcService();
		
		if("save".equals(doType)){//保存答案
			String back=service.saveWjhd(request);
			request.setAttribute("back", back);
		}
		
		//问卷试题
		request.setAttribute("rs", service.getMslbtmb());
		//问卷答案
		List<HashMap<String,String>> wjda=service.getWjhdda(userName);
		request.setAttribute("wjda", wjda);
		String userType=request.getSession().getAttribute("userType").toString();
		if("stu".equals(userType)&&(wjda==null||wjda.size()==0)){
			request.setAttribute("sfkhd", "yes");
		}else{
			request.setAttribute("sfkhd", "no");
		}
		return mapping.findForward("wjhd");
	}
	
	/**
	 * 评议内容维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjhdView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");		
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		
		GdspypWjdcService service = new GdspypWjdcService();
		request.setAttribute("hdInfoList", service.getHdInfoList(pkValue));
		request.setAttribute("rs", service.getJgfxOne(pkValue));
		return mapping.findForward("wjhdView");
	}
}
