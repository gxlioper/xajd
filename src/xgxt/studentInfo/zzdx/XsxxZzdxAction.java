/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.studentInfo.zzdx;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.ExcelMethods;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中州大学高基报表Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-26</p>
 */
public class XsxxZzdxAction extends DispatchAction {
	
	/** 
	 * Method loftyReport 转到选择报表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward loftyReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XsxxZzdxService service = new XsxxZzdxService();
		
		request.setAttribute("list", service.getReportList());
		return mapping.findForward("success");
	}
	
	/** 
	 * Method loftyReportOne 生成单个Excel报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException
	 */
	public ActionForward loftyReportOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		XsxxZzdxForm zzdxForm = (XsxxZzdxForm)form;
		XsxxZzdxService service = new XsxxZzdxService();
		
		String bbbh = zzdxForm.getBbbh();
		String modelPath = "";
		
		if(bbbh!=null && "gj222".equalsIgnoreCase(bbbh)){
			//招生、在校学生来源情况表 完成
			modelPath = servlet.getServletContext().getRealPath("")+"/print/gj222.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printGjbb222(wwb);
		}
		if(bbbh!=null && "gj24".equalsIgnoreCase(bbbh)){
			//在校学生其他情况 完成
			modelPath = servlet.getServletContext().getRealPath("")+"/print/gj24.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printGjbb24(wwb);
		}
		if(bbbh!=null && "gj221".equalsIgnoreCase(bbbh)){
			//在校学生年龄情况 完成
			modelPath = servlet.getServletContext().getRealPath("")+"/print/gj221.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printGjbb221(wwb);
		}
		if(bbbh!=null && "gj212".equalsIgnoreCase(bbbh)){
			//普通本科、专科分专业学生数
			modelPath = servlet.getServletContext().getRealPath("")+"/print/gj212.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printGjbb212(wwb);
		}
		if(bbbh!=null && "gj231".equalsIgnoreCase(bbbh)){
			//学生变动情况
			modelPath = servlet.getServletContext().getRealPath("")+"/print/gj231.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printGjbb231(wwb);
		}
		return mapping.findForward("");
	}
	
	
}