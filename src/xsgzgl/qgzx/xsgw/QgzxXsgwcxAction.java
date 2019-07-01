package xsgzgl.qgzx.xsgw;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxXsgwcxAction extends BasicAction{
	
	/**
	 * ��ѯ�ҵ��ڹ���λ��ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wdqggwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_xsqg_xsqggw.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("xsgwCx");
		}
	}
	
	/**
	 * �鿴�ҵ��ڹ���λ��ҳ��һ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wdqggwCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		QgzxXsgwcxForm myForm = (QgzxXsgwcxForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// ������¼��Ϣ
		HashMap<String, String> rs = service.getXsgwCkmxMap(myForm,userName);
		ArrayList<String[]> cjrs = (ArrayList<String[]>) service.getXsgwCkcjmxMap(myForm,userName);
		HashMap<String, String> cssz = new QgzxCsszService().getCssz();
		
		request.setAttribute("cjbz", cssz.get("cjbz"));
		request.setAttribute("rs", rs);
		request.setAttribute("cjrs", cjrs);
		request.setAttribute("path", "qgzx_xsqg_xsqggw.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsgwCkmx");
	}
	
	/**
	 * ��ѯ�ҵ��ڹ���λ��ҳ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_xsqg_xsgwcx.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
		String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxxCx");
		}
	}
	
	/**
	 * ��λ��Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		QgzxXsgwcxForm model = (QgzxXsgwcxForm) form;
		RequestForm rForm = new RequestForm(); 
		HashMap<String,String> rs = service.gwxxCk(model);
		
		//982
		//�������
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		HashMap<String, String> map=qgzxCsszService.getCssz();
		String isshow="false";
		String sfsdgwcjsx=map.get("sfsdgwcjsx");
		//��������˳������
		if("yes".equals(sfsdgwcjsx)){
			isshow="true";
			//�����ǰ��λδ���ó�����ޣ�����ʾ�������õĳ������
			if(StringUtils.isNull(rs.get("gwcjsx"))){
				rs.put("gwcjsx",map.get("gwzgcjsx"));
			}
		}
		request.setAttribute("isshow", isshow);
		
		request.setAttribute("rs", rs);
		User user = getUser(request);
		rForm.setPath("qgzx_xsqg_xsgwcx.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwxxCk");
	}
}