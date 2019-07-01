package xgxt.xsh.stgl.cdty;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.xtwh.comm.splc.XtwhShlcForm;
import xgxt.xtwh.comm.splc.XtwhShlcService;

public class CdtyXsstAjax {
	/**
	 * 确认是否存在社团干部信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkStgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh=request.getParameter("xh");
		
		String stdm=request.getParameter("stdm");
		
		CdtyXsstService service=new CdtyXsstService();
		
		CdtyXsstForm model=(CdtyXsstForm)form;
		
		model.setXh(xh);
		model.setStdm(stdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(service.checkStgb(model)));
		
		return null;
	}
}
