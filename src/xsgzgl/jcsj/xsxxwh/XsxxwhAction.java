package xsgzgl.jcsj.xsxxwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.basic.BasicAction;

public class XsxxwhAction extends BasicAction{

	
	public ActionForward xsxxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxwhForm myForm=(XsxxwhForm)form;
		String doType=request.getParameter("doType");
		XsxxwhService service=new XsxxwhService();
		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if("del".equals(doType)){//删除
				message=service.deleteXsxxInfo(myForm);
			}
			
			if ("checkExcData".equals(doType)){//检测异常数据
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xsxxb");
				message+="共检测出"+num+"条异常数据！";
			}else{
				service.checkExceptionData();
			}
			request.setAttribute("message", message);
		}
		String refurbish=request.getParameter("refurbish");
		if("yes".equals(refurbish)){
			service.checkExceptionData();
		}
		
		List<String[]> rs = service.getXsxxList(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());

//		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr());
		
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("bmdmList", service.getBmdmList());
		request.setAttribute("zydmList", service.getZydmList(myForm.getBmdm()));
		request.setAttribute("bjdmList", service.getBjdmList(myForm.getZydm()));
		
		return mapping.findForward("success");
	}
	
	/**
	 * 获取专业代码类别
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZydmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bmdm=request.getParameter("bmdm");
		XsxxwhService service = new XsxxwhService();
		List<HashMap<String, String>> list = service.getZydmList(bmdm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setCharacterEncoding("GBK");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * 获取专业代码类别
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zydm=request.getParameter("zydm");
		XsxxwhService service = new XsxxwhService();
		List<HashMap<String, String>> list = service.getBjdmList(zydm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setCharacterEncoding("GBK");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
}
