package xgxt.pjpy.hntx.zycp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.pjpy.hntx.dypy.DypyForm;
import xgxt.utils.FormModleCommon;

/**
 * Title: ѧ����������ϵͳ
 * Description:���ϴ�ѧ����������Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-25
 */
public class ZycpHndxAction extends BasicExtendAction{
	
	/**
	 * ���ϴ�ѧ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zycpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ��Ҫͬ����ѧ��
		String tbxn = request.getParameter("tbxn");
		String viewName = "view_hndx_xszycpf";
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");
		
		// �û�����
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(session);

		DypyForm gForm = (DypyForm) form;

		ZycpHndxService service = new ZycpHndxService();
		if("tb".equalsIgnoreCase(doType)){
			String msg = service.tbsj(tbxn) ? "ͬ���ɹ�" : "ͬ��ʧ��";
			request.setAttribute("msg", msg);
		}
		
		// �����ѧԺ��ѧԺ���ž�ȷ��
		if("stu".equalsIgnoreCase(user)){
			gForm.setQuerylike_xh(userName);
			request.setAttribute("xh", userName);
		} else if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}
		
		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"xh","xm","xn","xymc","zymc","bjmc","pjxfjd","zyzf"};
			selectPageDataByPagination(request, gForm, "", viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "hndx_zycp.do?method=zycpwh");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", "");
		return mapping.findForward("zycpwh");
	}
	
	/**
	 * ͨ�õ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zycpfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xn","xymc","zymc","bjmc","pjxfjd","zyzf"};
		
		expPageData(request, response, "","view_hndx_xszycpf", output);
		return mapping.findForward("");
	}
}
