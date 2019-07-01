package xgxt.wjcf.gzdx;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class WjcfGzdxAction extends CommonAction {


	/**
	 * 违纪处分汇总表
	 * 
	 * @param mapping
	 * @param form
	 * @param requset
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/wjcf/gzdxwjcfhz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		WjcfGzdxService service = new WjcfGzdxService();
		WjcfGzdxModel model = getModel(request);
		service.wjcfhzPrint(wwb, model);
		return mapping.findForward("");
	}
	
	/**
	 * 教务处违纪处分汇总表
	 * 
	 * @param mapping
	 * @param form
	 * @param requset
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfhzbByjwc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/wjcf/gzdxwjcfjwchz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		
		WjcfGzdxService service = new WjcfGzdxService();
		WjcfGzdxModel model = getModel(request);
		service.wjcfhzPrintByjwc(wwb, model);
		return mapping.findForward("");
	}
	
	/**
	 * 申诉书打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			WjcfGzdxService service = new WjcfGzdxService();
			rs = service.querySsbprint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("ssprint");
	}
	
	/**
	 * 处分表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfbprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			WjcfGzdxService service = new WjcfGzdxService();
			rs = service.queryCfbprint(pkValue);
			String qrsj = rs.get("qrsj");
			if (!Base.isNull(qrsj)) {
				rs.put("year", qrsj.substring(0, 4));
				rs.put("month", qrsj.substring(4, 6));
				rs.put("date", qrsj.substring(6));
			}
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("cfbprint");
	}

	//从页面获取值
	public WjcfGzdxModel getModel(HttpServletRequest request) {
		WjcfGzdxModel model = new WjcfGzdxModel();
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setNj(request.getParameter("nj"));
		model.setXn(request.getParameter("xn"));
		model.setNd(request.getParameter("nd"));
		model.setXq(request.getParameter("xq"));
		model.setCflb(request.getParameter("cflb"));
		model.setCfyy(request.getParameter("cfyy"));
		model.setSfjw(request.getParameter("sfjw"));
		return model;
	}
}
