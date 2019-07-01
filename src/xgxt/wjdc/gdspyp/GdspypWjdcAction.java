package xgxt.wjdc.gdspyp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class GdspypWjdcAction extends BasicExtendAction{
	/**
	 * ����ѧ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgfxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ�û�
		User user = getUser(request);
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		GdspypWjdcService service = new GdspypWjdcService();
	
		String doType = request.getParameter("doType");
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delJgfx(pks) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("rs", service.jgfxQuery(myForm));
		// �꼶ѧԺרҵ�༶List��title�Ͷ�дȨ
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		setWriteAbleAndTitle(request, "wjdc_msxldc_jgfx.do");
		
		request.setAttribute("topTr", service.getTopTr("jgfx"));
		request.setAttribute("user", user);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("jgfxManage");
	}
	
	/**
	 * ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pywhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		
		GdspypWjdcService service = new GdspypWjdcService();
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.savePynr(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("pyList", service.getPy());
		request.setAttribute("rs", service.getJgfxOne(pkValue));
		return mapping.findForward("pywhUpdate");
	}
	
	/**
	 * �ʾ�ش�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjhd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName=request.getSession().getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		GdspypWjdcService service=new GdspypWjdcService();
		
		if("save".equals(doType)){//�����
			String back=service.saveWjhd(request);
			request.setAttribute("back", back);
		}
		
		//�ʾ�����
		request.setAttribute("rs", service.getMslbtmb());
		//�ʾ��
		List<HashMap<String,String>> wjda=service.getWjhdda(userName);
		request.setAttribute("wjda", wjda);
		String userType=request.getSession().getAttribute("userType").toString();
		if("stu".equals(userType)&&(wjda==null||wjda.size()==0)){
			request.setAttribute("sfkhd", "yes");
		}else{
			request.setAttribute("sfkhd", "no");
		}
		return mapping.findForward("wjhd");
	}
	
	/**
	 * ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjhdView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");		
		GdspypWjdcForm myForm = (GdspypWjdcForm)form;
		
		GdspypWjdcService service = new GdspypWjdcService();
		request.setAttribute("hdInfoList", service.getHdInfoList(pkValue));
		request.setAttribute("rs", service.getJgfxOne(pkValue));
		return mapping.findForward("wjhdView");
	}
}
