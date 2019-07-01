package xgxt.qgzx.xcxy.action;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.xcxy.service.QgzxXcxyService;
import xgxt.qgzx.zzsf.service.QgzxZzsfService;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ѧԺ�ڹ���ѧAction</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-07</p>
 */
public class QgzxXcxyAction extends DispatchAction {
	
	/**
	 * ��ʾѧ�������λҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward xssqgw(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response){	
		HttpSession session = request.getSession();
		QgzxService service = new QgzxService();
		QgzxZzsfService zzsfService = new QgzxZzsfService();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		if(userOnLine != null && "student".equalsIgnoreCase(userOnLine)){
			xh = userName;
		}
		
		map.putAll(service.getSqsjInfo());//����ʱ����Ϣ
		map.putAll(zzsfService.getBaseInfo(xh));//ѧ�����˻�����Ϣ
		
		request.setAttribute("gwList", zzsfService.getGwList());//��λ�б�
		request.setAttribute("rs", map);
		request.setAttribute("isKns", service.isKns(xh));//�Ƿ�����������־
		request.setAttribute("do", "no");//���������־
		return mapping.findForward("success");
	}
	
	/** 
	 * ��ʾ����ѧ����λ��Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request0
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward modiXsgwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QgzxZzsfService zzsfService = new QgzxZzsfService();
		String pkV = DealString.toGBK(request.getParameter("xmdm"));
		String type = DealString.toGBK(request.getParameter("type"));
		HashMap<String, String> map = new HashMap<String, String>();
		
		map = zzsfService.getXsgwxx(pkV);
		request.setAttribute("gwList", zzsfService.getGwList());
		request.setAttribute("rs", map);
		request.setAttribute("doType", type);
		request.setAttribute("do", "yes");
		return mapping.findForward("success");
	}
	
	/**
	 * ����ѧ�������λ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward saveXsgwxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		QgzxForm model = (QgzxForm) form;
		String doType = request.getParameter("do");
		QgzxXcxyService service = new QgzxXcxyService();
		QgzxZzsfService zzsfService = new QgzxZzsfService();
		String pkV = model.getXh()+ DealString.toGBK(model.getXmdm());
		
		request.setAttribute("result", service.saveXsgwxx(model,request));
		request.setAttribute("gwList", zzsfService.getGwList());//��λ�б�
		request.setAttribute("do", doType);//���������־
		request.setAttribute("rs", service.getXsgwxx(pkV));
		return mapping.findForward("success");
	}
	
	/**
	 * ��ӡ���ͨ��ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printPassStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		QgzxXcxyService service = new QgzxXcxyService();		
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/qgzx/xcxy_auditingStu.xls";
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String nj = request.getParameter("nj");
		String yrdwdm = request.getParameter("yrdwdm");
		String xydm = request.getParameter("xydm");
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		QgzxForm model = new QgzxForm();
		
		model.setXn(xn);
		model.setNd(nd);
		model.setNj(nj);
		model.setXydm(xydm);
		model.setYrdwdm(yrdwdm);
		model.setXmdm(xmdm);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		String userName = request.getSession().getAttribute("userName").toString();
		
		service.printPassStu(wwb,userName,model);
		return mapping.findForward("");
	}
	
}
