package xsgzgl.jcsj.bjdmwh;

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

public class BjdmwhAction extends BasicAction{

	public ActionForward bjdmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		String doType = request.getParameter("doType");
		BjdmwhForm myForm = (BjdmwhForm) form;
		BjdmwhService service = new BjdmwhService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveBjdmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// 修改
				message = service.saveBjdmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteBjdmInfo(myForm);
			} 
			
			if ("checkExcData".equals(doType)){//检测异常数据
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_bjdmb");
				message+="共检测出"+num+"条异常数据！";
			}else{
				service.checkExceptionData();
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getBjdmList(myForm);
//		request.setAttribute("searchTj", myForm.getSearchModel());

//		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("fdykh"));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("bmdmList", service.getBmdmList());
		request.setAttribute("zydmList", service.getZydmList(myForm.getQuery_ssbmdm()));
		
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
		BjdmwhService service = new BjdmwhService();
		List<HashMap<String, String>> list = service.getZydmList(bmdm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setCharacterEncoding("GBK");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
}
