package xgxt.yxgl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.ExcelMethods;

public class YxtjAction extends DispatchAction{
	
	public ActionForward printXsbdhzb(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String modelPath = servlet.getServletContext().getRealPath("") + "/print/yxgl/hngy_xsbdqkhzb.xls";
		YxglService service = new YxglService();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printXsbdhzb(wwb);		
		return mapping.findForward("");		
	}
}
