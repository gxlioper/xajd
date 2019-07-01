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
		
		// ����
		String doType = request.getParameter("doType");
		ZydmwhForm myForm = (ZydmwhForm) form;
		ZydmwhService service = new ZydmwhService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveZydmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveZydmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteZydmInfo(myForm);
			}

			if ("checkExcData".equals(doType)){//����쳣����
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_zydmb");
				message+="������"+num+"���쳣���ݣ�";
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
