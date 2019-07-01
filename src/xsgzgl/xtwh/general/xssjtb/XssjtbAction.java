package xsgzgl.xtwh.general.xssjtb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.base.Excel2Oracle;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_学生数据检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbAction extends BasicExtendAction {
	
	/**
	 * 数据同步_日志数据
	 */
	public ActionForward tbrzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbForm model = (XssjtbForm) form;
		XssjtbService service = new XssjtbService();
		
		request.setAttribute("topTr", service.getTopTr(model));
		request.setAttribute("rs", service.getSjjcRzList(model));
		setWriteAbleAndTitle(request, "xtwh_xssjtbManage.do");
		return mapping.findForward("tbrzManage");
	}
	
	/**
	 * 数据同步_查看失败数据
	 */
	public ActionForward ckSbsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbForm model = (XssjtbForm) form;
		String jcb = request.getParameter("jcb");
		String jcsj = request.getParameter("jcsj");
		XssjtbService service = new XssjtbService();
		model.setJcb(jcb);
		model.setJcsj(jcsj);
		request.setAttribute("jcb", jcb);
		request.setAttribute("jcsj", jcsj);
		request.setAttribute("topTr", service.getCkTopTr(model));
		request.setAttribute("rs", service.getYcsjList(model));
		request.setAttribute("jcb", jcb);
		return mapping.findForward("ckSbsj");
	}
	
	/**
	 * 数据同步
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-基础设置-系统基础数据同步-同步")
	public ActionForward xssjTb (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssjtbService service = new XssjtbService();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(service.xssjTb()?"true":"false");
		return null;
	}
	
	/**
	 * 数据同步初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-基础设置-系统基础数据同步-初始化")
	public ActionForward cshsjTb (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssjtbService service = new XssjtbService();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(service.cshsjTb()?"true":"false");
		return null;
	}
	
	/**
	 * 结果导出
	 */
	public ActionForward ycsjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbService service = new XssjtbService();
		String jcb = request.getParameter("jcb");
		String jcsj = request.getParameter("jcsj");
		XssjtbForm myForm = (XssjtbForm) form;
		myForm.setJcb(jcb);
		myForm.setJcsj(jcsj);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = service.getExportTop(myForm);
		List<String[]> rs = service.getYcsjList(myForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
}
