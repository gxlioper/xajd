package xsgzgl.xsxx.xsxxhz;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;
import common.Globals;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xsgzgl.xsxx.gdjs.XsxxGdjsPrint;
import xsgzgl.xsxx.gdjs.XsxxGdjsService;
import xsgzgl.xsxx.xsxxhz.zjkj.XsxxZjkjService;

public class XsxxXxhzbPrint extends BasicAction{
	
	XsxxXxhzbService service= null;
	
	/**
	 * ѧ����Ϣexcel���
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
	
		String modelPath = servlet.getServletContext().getRealPath("/print/xsxx/xsxxhzb/xsxxhzb_"+Base.xxdm+".xls");
		
		File file = new File(modelPath);
		
		if (!file.exists()) {
			modelPath= servlet.getServletContext().getRealPath("/print/xsxx/xsxxhzb/xsxxhzb.xls");
		}
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		printFactory(wwb, xh);
		
		return null;
	}
	
	
	/**
	 * @param wwb
	 * @param xh
	 */
	public void printFactory(WritableWorkbook wwb,String xh){
		
		XsxxXxhzbService service= null;
		
		if (Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)) { // �㶫����

			service=new XsxxGdjsService();
						
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){// �㽭�Ƽ�ѧԺ
			
			service=new XsxxZjkjService();
			
		} else { // ͨ��
			
			service=new XsxxCommService();
	
		}
		
		service.printXsxx(wwb, xh);
		
	}
}
