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
		
		// ����
		String doType = request.getParameter("doType");
		BmdmwhForm myForm = (BmdmwhForm) form;
		BmdmwhService service = new BmdmwhService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveBmdmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveBmdmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteBmdmInfo(myForm);
			} 
			
			if ("checkExcData".equals(doType)){//����쳣����
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_bmdmb");
				message+="������"+num+"���쳣���ݣ�";
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
