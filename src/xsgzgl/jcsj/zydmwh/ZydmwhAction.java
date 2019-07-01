package xsgzgl.jcsj.zydmwh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.basic.BasicAction;

public class ZydmwhAction extends BasicAction{

	public ActionForward zydmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 操作
		String doType = request.getParameter("doType");
		ZydmwhForm myForm = (ZydmwhForm) form;
		ZydmwhService service = new ZydmwhService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveZydmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// 修改
				message = service.saveZydmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteZydmInfo(myForm);
			}

			if ("checkExcData".equals(doType)){//检测异常数据
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_zydmb");
				message+="共检测出"+num+"条异常数据！";
			}else{
				service.checkExceptionData();
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getZydmList(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());

//		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("fdykh"));
//		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("bmdmList", service.getBmdmList());
		
		return mapping.findForward("success");
	}
}
