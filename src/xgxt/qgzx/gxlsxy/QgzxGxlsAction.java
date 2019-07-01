package xgxt.qgzx.gxlsxy;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.CommanForm;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.gygl.tjfx.GyglTjfxService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class QgzxGxlsAction extends BasicAction{
	
	
	QgzxGxlsService service = new QgzxGxlsService();
	
	public ActionForward printQgzxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		QgzxGxlsService service=new QgzxGxlsService();
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String,String>qgzxInfo=service.getQgzxInfo(pkValue);
		
		//学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(qgzxInfo.get("xh"));
		
		request.setAttribute("rs", stuInfo);
		
		return mapping.findForward("printQgzxsq");
	}
	
	/**
	 * 公寓管理_统计分析_精简统计
	 * 宿舍年级分布情况统计
	 *  Method tjfxNjfbtj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward printQggwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommanForm myForm=(CommanForm)form;
		
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_ssnjfbtj.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		service.printQggwInfo(myForm, request, wwb);
		return null;
	}
	
}