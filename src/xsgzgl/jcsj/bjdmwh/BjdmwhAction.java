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
		
		// ����
		String doType = request.getParameter("doType");
		BjdmwhForm myForm = (BjdmwhForm) form;
		BjdmwhService service = new BjdmwhService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveBjdmInfo(myForm, "add");
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveBjdmInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteBjdmInfo(myForm);
			} 
			
			if ("checkExcData".equals(doType)){//����쳣����
				message = service.checkExceptionData();
				String num=service.getYcsjCount("xg_jcsj_bjdmb");
				message+="������"+num+"���쳣���ݣ�";
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
	 * ��ȡרҵ�������
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
