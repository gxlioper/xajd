package xsgzgl.jxgl.hzsf.grry;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.rcjc.qszf.QszfService;
import xsgzgl.jxgl.hzsf.dmwh.JxglDmwhService;
/**
 * ��ѵ����-��ѵ��-��������
 * @author yeyipin
 * @since 2012.7.27
 */
public class JxglGrryAction extends BasicExtendAction{
	/**
	 * ���������񽱲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jxhj_grry.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_jxgl_hzsf_grryhjb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_jxgl_hzsf_grryhjb");
		JxglDmwhService dmwhService = new JxglDmwhService();
		HashMap<String,String> rs = new HashMap<String,String>();
		rs.put("xn", Base.currXn);
		model.setXn(Base.currXn);//Ĭ�ϵ�ǰѧ��
		model.setBzdj("1");//�Ŵ���
		model.setSjdm("");//���ϼ������ǿ�
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("grryList", dmwhService.getGrrydmList());
		request.setAttribute("tuanList", service.getTuanYingLianList(model));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		
		return mapping.findForward("grryCx");
	}
	/**
	 * ��������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JxglGrryService service = new JxglGrryService();
		JxglDmwhService dmwhService = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		HashMap<String,String> rs = new HashMap<String,String>();
		String xn = request.getParameter("xn");
		rs.put("xn", xn);
		User user = getUser(request);
		rForm.setPath("jxgl_jxhj_grry.do");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("rs", rs);
		request.setAttribute("grryList", dmwhService.getGrrydmList());
		return mapping.findForward("grryZj");
	}
	/**
	 * �����������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grryXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		JxglDmwhService dmwhService = new JxglDmwhService();
		RequestForm rForm = new RequestForm();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		HashMap<String,String> rs = service.getGrryMap(model);
		
		User user = getUser(request);
		rForm.setPath("jxgl_jxhj_grry.do");
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("grryList", dmwhService.getGrrydmList());
		return mapping.findForward("grryXg");
	}
	/**
	 * ��þ�ѵѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxxs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JxglGrryService service = new JxglGrryService();
		JxglGrryForm model = (JxglGrryForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jxhj_grry.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String xn = request.getParameter("xn");
		HashMap<String,String> rs = new HashMap<String,String>();
		rs.put("xn", xn);
		model.setXn(xn);
		model.setBzdj("1");//�Ŵ���
		model.setSjdm("");//���ϼ������ǿ�
		request.setAttribute("rs", rs);
		request.setAttribute("tuanList", service.getTuanYingLianList(model));
		return mapping.findForward("getJxxs");
	}
}
