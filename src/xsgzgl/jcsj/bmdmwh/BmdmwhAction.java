package xsgzgl.jcsj.bmdmwh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.basic.BasicAction;

public class BmdmwhAction extends BasicAction{

	public ActionForward bmdmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		String doType = request.getParameter("doType");
		BmdmwhForm myForm = (BmdmwhForm) form;
		BmdmwhService service = new BmdmwhService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveBmdmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// 修改
				message = service.saveBmdmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteBmdmInfo(myForm);
			} 
			
			if ("checkExcData".equals(doType)){//检测异常数据
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_bmdmb");
				message+="共检测出"+num+"条异常数据！";
			}else {
				service.checkExceptionData();
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getBmdmList(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());

//		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("fdykh"));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("bmlbList", service.getBmlbList());
		
		return mapping.findForward("success");
	}
}
