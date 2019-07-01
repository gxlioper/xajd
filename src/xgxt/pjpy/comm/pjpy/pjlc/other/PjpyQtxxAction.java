package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-21 ����08:50:58</p>
 */
public class PjpyQtxxAction extends BasicAction{
	
	PjpyQtxxService service = new PjpyQtxxService();

	// -----------------------��Ԣ���� ����ճ̹��� begin---------------------------
	
	/**
	 * ��ѧ�ɲ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("pjpy_qtxx_qtjl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_view_pjpy_qtjl");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_pjpy_qtjlb");
		
		return mapping.findForward("qtxxCx");
	
	}

	/**
	 * ��ѧ�ɲ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		User user=getUser(request);
		
		String doType="add";
		
		String xh=request.getParameter("xh");
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			xh=user.getUserName();
		}
		
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		stuInfo.putAll(stuService.selectStuinfo(xh));
		
		request.setAttribute("doType", doType);
		
		request.setAttribute("path", "pjpy_qtxx_qtjl.do");

		request.setAttribute("rs", stuInfo);
		request.setAttribute("xn", Base.currXn);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qtxxWh");
	}
	
	/**
	 * ��ѧ�ɲ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		PjpyQtxxForm myForm = (PjpyQtxxForm) form;
		
		String doType="modi";
		
		String pkValue=request.getParameter("pkValue");
		
		String xh=pkValue.split("!!@@!!")[1];
		
		String xn=pkValue.split("!!@@!!")[0];
		
		HashMap<String,String>rs=stuService.selectStuinfo(xh);
		
		request.setAttribute("doType", doType);
		// ������¼��Ϣ
		request.setAttribute("rs", rs);
		
		request.setAttribute("pkValue", pkValue);
		
		request.setAttribute("xn", xn);
		// ����ѧ�ꡢѧ�ڡ�����б�
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("path", "pjpy_qtxx_qtjl.do");
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("qtxxWh");
	}
	
	/**
	 * ��ѧ�ɲ���ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		PjpyQtxxForm myForm = (PjpyQtxxForm) form;
		
		String doType="modi";
		
		String pkValue=request.getParameter("pkValue");
		
		String xh=pkValue.split("!!@@!!")[1];
		
		String xn=pkValue.split("!!@@!!")[0];
		
		HashMap<String,String>rs=stuService.selectStuinfo(xh);
		
		request.setAttribute("doType", doType);
		// ������¼��Ϣ
		request.setAttribute("rs", rs);
		
		request.setAttribute("pkValue", pkValue);
		
		request.setAttribute("xn", xn);
		// ����ѧ�ꡢѧ�ڡ�����б�
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("path", "pjpy_qtxx_qtjl.do");
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("qtxxCk");
	}

	// -----------------------��Ԣ���� ����ճ̹��� end---------------------------
}
