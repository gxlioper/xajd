package xgxt.studentInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxGxhService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_���Ի�_action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class XsxxGxhAction extends BasicExtendAction{
	/**
	 * �û�ά������
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwbhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGxhService service = new XsxxGxhService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		User user = getUser(request);
		
		StudentInfoForm myForm = (StudentInfoForm)form;
		
		String userStatus = user.getUserStatus(); 
		
		if("stu".equalsIgnoreCase(userStatus)){
			myForm.setXh(user.getUserName());
		}else if("xy".equalsIgnoreCase(userStatus)){
			myForm.setXydm(user.getUserDep());
		}
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String[] pkValus = request.getParameterValues("primarykey_cbv");
			String message = service.delRs(pkValus) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.getRs(myForm));
		}
		
		String[] topTr = new String[]{"ѧ��","����",Base.YXPZXY_KEY+"����","רҵ����","�༶����","������"};
		// ��ͷ
		request.setAttribute("topTr", topTr);
		// ��ͷ�ͷ���Ȩ��
		setWriteAbleAndTitle(request, "stuCwbh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("realTable", "xg_xsxx_xscwbhb");
		request.setAttribute("user", user);
		
		return mapping.findForward("cwbhManage");
	}
	
	/**
	 * �û�ά������
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwbhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// �û�
		User user = getUser(request);
		// ����
		String doType = request.getParameter("doType");
		
		String xh = user.getUserStatus().equalsIgnoreCase("stu") ? user.getUserName() : request.getParameter("xh");
		
		XsxxGxhService service = new XsxxGxhService();
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			// ��ʾ��
			String message = service.saveCwbh((StudentInfoForm)form) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}else if("del".equalsIgnoreCase(doType)){
			
		}
		
		// ��ȡѧ����Ϣ
		if(StringUtils.isNotNull(xh)){
			request.setAttribute("rs",service.getInfo(xh));
		}
		
		request.setAttribute("doType", doType);
		return mapping.findForward("cwbhUpdate");
	}
}
