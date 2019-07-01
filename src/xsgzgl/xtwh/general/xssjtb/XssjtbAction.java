package xsgzgl.xtwh.general.xssjtb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.base.Excel2Oracle;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_ѧ�����ݼ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbAction extends BasicExtendAction {
	
	/**
	 * ����ͬ��_��־����
	 */
	public ActionForward tbrzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbForm model = (XssjtbForm) form;
		XssjtbService service = new XssjtbService();
		
		request.setAttribute("topTr", service.getTopTr(model));
		request.setAttribute("rs", service.getSjjcRzList(model));
		setWriteAbleAndTitle(request, "xtwh_xssjtbManage.do");
		return mapping.findForward("tbrzManage");
	}
	
	/**
	 * ����ͬ��_�鿴ʧ������
	 */
	public ActionForward ckSbsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbForm model = (XssjtbForm) form;
		String jcb = request.getParameter("jcb");
		String jcsj = request.getParameter("jcsj");
		XssjtbService service = new XssjtbService();
		model.setJcb(jcb);
		model.setJcsj(jcsj);
		request.setAttribute("jcb", jcb);
		request.setAttribute("jcsj", jcsj);
		request.setAttribute("topTr", service.getCkTopTr(model));
		request.setAttribute("rs", service.getYcsjList(model));
		request.setAttribute("jcb", jcb);
		return mapping.findForward("ckSbsj");
	}
	
	/**
	 * ����ͬ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-ϵͳ��������ͬ��-ͬ��")
	public ActionForward xssjTb (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssjtbService service = new XssjtbService();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(service.xssjTb()?"true":"false");
		return null;
	}
	
	/**
	 * ����ͬ����ʼ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-ϵͳ��������ͬ��-��ʼ��")
	public ActionForward cshsjTb (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssjtbService service = new XssjtbService();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(service.cshsjTb()?"true":"false");
		return null;
	}
	
	/**
	 * �������
	 */
	public ActionForward ycsjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssjtbService service = new XssjtbService();
		String jcb = request.getParameter("jcb");
		String jcsj = request.getParameter("jcsj");
		XssjtbForm myForm = (XssjtbForm) form;
		myForm.setJcb(jcb);
		myForm.setJcsj(jcsj);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = service.getExportTop(myForm);
		List<String[]> rs = service.getYcsjList(myForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
}
