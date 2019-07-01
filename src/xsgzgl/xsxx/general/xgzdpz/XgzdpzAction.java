package xsgzgl.xsxx.general.xgzdpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;


import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class XgzdpzAction extends BasicAction  {
	
	/**
	 * ��ѯ�޸��ֶ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxXgzdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XgzdpzForm myForm = (XgzdpzForm) form;
		if(myForm.getLb()==null){
			myForm.setLb("stu");
		}
		XgzdpzService service = new XgzdpzService();
		List<HashMap<String, String>> zdflList = service.getXgzdflList();//�鿴һ��
		List<HashMap<String, String>> zdList = service.getXgzdList(myForm);//�鿴����
		String btzds = service.getBtzd(myForm);
		String zdzds = service.getZdzd(myForm);
		request.setAttribute("zdflList", zdflList);
		request.setAttribute("zdList", zdList);
		request.setAttribute("zdzds", zdzds);
		request.setAttribute("btzds", btzds);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "general_xsxx_xgzdpz.do");
		FormModleCommon.commonRequestSet(request);
		if("stu".equals(myForm.getLb())){
			return mapping.findForward("xsXgzdxx");
		}else{
			return mapping.findForward("jsXgzdxx");
		}
		
	}
	
	/**
	 * �����޸��ֶ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-��Ϣ�޸��ֶ�����LB:{lb}")
	public ActionForward bcXgzdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XgzdpzForm myForm = (XgzdpzForm) form;
		XgzdpzService service = new XgzdpzService();
		String lb = request.getParameter("lb");
		myForm.setLb(lb);
		boolean flag = service.bcXgzdsz(myForm);
		String message =flag?"�����ɹ���":"����ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

}
