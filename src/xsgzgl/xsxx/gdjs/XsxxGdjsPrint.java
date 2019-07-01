package xsgzgl.xsxx.gdjs;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.ExcelMethods;
import xsgzgl.comm.BasicService;

public class XsxxGdjsPrint extends DispatchAction {

	
	XsxxGdjsService service= new XsxxGdjsService();
	/**
	 * 广东建设学生信息excel输出
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printXsxxExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		String xh=request.getParameter("xh");
		String modelPath = servlet.getServletContext().getRealPath("/print/xsxx/gdjs/xsxxk.xls");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		service.printXsxx(wwb,xh);
		
		return null;
	}
}
