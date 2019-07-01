package xsgzgl.pjpy.general.bjry;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.pjpy.general.PjpyGeneralForm;

public class BjryglAction extends BasicAction {
	
	/**
	 * �༶������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_pjpy_bjryb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_pjpy_bjryb");
		return mapping.findForward("bjryglCx");
	}
	
	/**
	 * �༶��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		RequestForm rForm = new RequestForm();
		// ��ȡ���������б��
		request.setAttribute("hdryList", service.getHdryList(request));
		HashMap<String, String> bjryglxx = new HashMap<String, String>();
		bjryglxx.put("xn", jbszModel.getPjxn());
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ��ȡ�W��W����������б��
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglZj");
	}
	
	/**
	 * ��ȡ�༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("pjpy_bjry_bjrygl.do?searchType=getBjmc");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjmcInfo");
	}
	
	/**
	 * �༶�����鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		myForm.setGuid(pkValue);
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglCk");
	}
	
	/**
	 * �༶����ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// ��ȡ������������б��
		request.setAttribute("hdryList", service.getHdryList(request));
		HashMap<String, String> bjryglxx = service.getBjryglMap(myForm);
		request.setAttribute("rs", bjryglxx);
		User user = getUser(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("pjpy_bjry_bjrygl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjryglWh");
	}
	
	/**
	 * �༶����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjryglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjryglService service = new BjryglService();
		BjryglForm myForm = (BjryglForm) form;
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.bjryglSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}