package xgxt.studentInfo.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.StuInfoReportService;
import xgxt.utils.FormModleCommon;

public class StuInfoReportAction extends DispatchAction {

	public ActionForward stuInfoReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		
		request.setAttribute("path", "stuInfoReport.do?method=stuInfoReport");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("stuInfoReport");
	}
	
	
	public ActionForward getStuInfoReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		StudentInfoForm myForm = (StudentInfoForm) form;
		StuInfoReportService service = new StuInfoReportService();
		String flg = request.getParameter("flg");
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		OutputStream os = response.getOutputStream();
		
		if ("001".equals(flg)) {
			service.printStuInfoRepordOfZy(myForm, os);
		} else if ("002".equals(flg)) {
			service.printStuInfoRepordOfBj(myForm,os);
		} else {
			service.printStuInfoRepordOfXy(myForm, os);
		}
		
		return mapping.findForward("");
	}
}
