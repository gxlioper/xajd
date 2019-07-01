package xsgzgl.xtwh.general.cxjgpz;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ѯ�������
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
public class CxjgpzAction extends BasicExtendAction{

	/**
	 * �ֶβ�ѯ
	 */
	public ActionForward cxjgpzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		model.setGnlj("xsxx_tygl_cxzxs.do");
		request.setAttribute("wpzlist", service.getWpzzdlist(model));
		request.setAttribute("ypzlist", service.getYpzzdlist(model));
		request.setAttribute("xsxxlist", service.getXsxxlist(model));
		request.setAttribute("gnlj", "xsxx_tygl_cxzxs.do");
		setWriteAbleAndTitle(request, "xtwh_cxjgpzManage.do");
		return mapping.findForward("cxjgpzManage");
	}
	
	/**
	 * �����ֶ�����
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-��ѯ�������-�����ֶ�����ZDS��{zds}")
	public ActionForward cxjgPz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		//String cxjgs[] = request.getParameterValues("cxjg_ypzzd");
		String cxjgs[] = null;
		String zds = request.getParameter("zds");
		if(!"".equals(zds)){
			 cxjgs = zds.split("!@!");
		}
		model.setCxjg(cxjgs);
		boolean flag = service.bcCxjgpz(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag?"true":"false");
		return null;
	}
	
	/**
	 * �������ֶ�����
	 */
	public ActionForward xgZdmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
	//	String doType = request.getParameter("doType");
		String zd = request.getParameter("zd");
		String gnlj = request.getParameter("gnlj");
		model.setZd(zd);
		model.setGnlj(gnlj);
		HashMap<String, String> rs = service.getZd(model);
		//request.setAttribute("rs",rs);
//		if("save".equals(doType)){
//			boolean flag = service.xgZdmc(model);
//			request.setAttribute("message",flag?"�޸ĳɹ�!":"�޸�ʧ��!");
//		}
		//JSONArray.fromObject(rs);
		request.setAttribute("zd",zd);
		request.setAttribute("gnlj",gnlj);
		request.setAttribute("rs",rs);
		return mapping.findForward("cxjgZdxg");
	}
	
	/**
	 * �����������ֶ�����
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-��ѯ�������-�����������ֶ�����ZD��{zd}")
	public ActionForward bcxgZdmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxjgpzService service = new CxjgpzService();
		CxjgpzForm model = (CxjgpzForm) form;
		String zd = request.getParameter("zd");
		String gnlj = request.getParameter("gnlj");
		model.setZd(zd);
		model.setGnlj(gnlj);
		HashMap<String, String> rs = service.getZd(model);
		request.setAttribute("rs",rs);
		
		boolean flag = service.xgZdmc(model);
		//request.setAttribute("message",flag?"�޸ĳɹ�!":"�޸�ʧ��!");
		String message = flag ? "�޸ĳɹ�!":"�޸�ʧ��!";
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
		
		//return cxjgpzManage( mapping,form,request,response);
		
	}
	


}
