package xgxt.pjpy.hntx.dypy;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ
 * Description:�������ŵ�������Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-17
 */
public class DypyAction extends BasicExtendAction{
	
	/**
	 * ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dypywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String tableName = "hndx_dypyxxb";
		String viewName = "view_hndx_dypyxxb";
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");
		
		// �û�����
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(session);

		DypyForm gForm = (DypyForm) form;

		// �����ѧԺ��ѧԺ���ž�ȷ��
		if("stu".equalsIgnoreCase(user)){
			gForm.setQuerylike_xh(userName);
			request.setAttribute("xh", userName);
		} else if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}

		if("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
		}
		
		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","xh","xm","xn","xymc","zymc","bjmc","xmmc","xmjf"};
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "hndx_dypy.do?method=dypywh");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("dypywh");
	}
	
	/**
	 * ��������ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dypyadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();
		DypyForm dForm = (DypyForm)form;
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		DypyService service = new DypyService();
		
		if(StringUtils.isNotNull(xh)){
			String[] output = new String[]{"xh","xm","xymc","zymc","xb",
					"bjmc","nj","xz"};
			stuInfo = service.getStuInfo(xh, output);
		}
		
		if("add".equalsIgnoreCase(doType)){
			DypyModel model = new DypyModel();
			BeanUtils.copyProperties(model, dForm);
			
			String msg = service.saveDypy(model) ? "����ɹ�" : "����ʧ��";
			
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xn", xn);
		return mapping.findForward("dypyadd");
	}
	
	/**
	 * ��������鿴���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dypyViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String operation = "modi".equalsIgnoreCase(doType) ? "modi" : "view";
		String pkValue = request.getParameter("pkValue");
		
		DypyService service = new DypyService();
		
		
		
		if("save".equalsIgnoreCase(doType)){
			DypyForm myForm = (DypyForm)form;
			
			String msg = service.updateDypy(pkValue, myForm.getXmjf()[0]) ? "����ɹ�" : "����ʧ��";
			request.setAttribute("msg", msg);
		}
		
		Map<String, String> map = service.getDypyInfo(pkValue);
		
		request.setAttribute("rs", map);
		request.setAttribute("operation", operation);
		return mapping.findForward("dypyview");
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
	public ActionForward dypyExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[]{"xh","xm","xn","xymc","zymc","bjmc","xmmc","xmjf"};
		
		expPageData(request, response, "hndx_dypyxxb","view_hndx_dypyxxb", output);
		return mapping.findForward("");
	}
}
