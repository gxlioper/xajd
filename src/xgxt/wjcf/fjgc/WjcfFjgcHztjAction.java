/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.wjcf.fjgc;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.form.ShgcForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

/** 
 * MyEclipse Struts
 * Creation date: 12-11-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class WjcfFjgcHztjAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * 违纪处分汇总统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ActionForward wjcfHztj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfFjgcService service=new WjcfFjgcService();
		ShgcForm shgcForm =(ShgcForm)form;

		String pkValue=request.getParameter("pkValue");
		shgcForm.setXh(pkValue);
		shgcForm.setXn(Base.currXn);
		shgcForm.setXq(Base.currXq);
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/pjpy/pjpy_cqdzgc.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.wjcfHztj(shgcForm, request, wwb);
		return null;
	}
	
	/**
	 * 打开年度选择窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ActionForward showWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("wjcfHztj");
	}
}