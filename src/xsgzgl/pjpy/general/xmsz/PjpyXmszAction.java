package xsgzgl.pjpy.general.xmsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyXmszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmsyInterface;
import xsgzgl.pjpy.general.xmsz.xmsy.XmszXmsyModel;

import com.zfsoft.basic.BasicAction;

public class PjpyXmszAction extends BasicAction{
	

	/**
	 * ��ѯ�������ã��ҵ�����ͳ�ƣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShxz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		PjpyXmszModel model=new PjpyXmszModel();
		
		User user=getUser(request);
		
		HttpSession session=request.getSession();
		//ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyXmszInterface service = myService.getPjpyXmszService(myForm);
		 
		// ��request�е�ֵ��װ��model
		commService.getModelValue(model, request);
		String message=service.getXmshzg(model, user);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * ��ʾ�ֶ��޸�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXmsyDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommService commService = new CommService();
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		XmszXmsyModel model=new XmszXmsyModel();
		
		HttpSession session=request.getSession();
		//ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmsyInterface service = myService.getXmszXmsyService(myForm);

		
		//��ѧ������Ŀ��װ��model
		commService.getModelValue(model, request);

		// ==================����ǰ̨ҳ��========================
		service.showXmsyDiv(model,response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��ʾ�ֶ��޸�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommService commService = new CommService();
		
		User user=getUser(request);
		
		PjpyGeneralForm myForm=PjpyGeneralForm.getJbszModel();
		
		XmszXmsyModel model=new XmszXmsyModel();
		
		HttpSession session=request.getSession();
		//ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmsyInterface service = myService.getXmszXmsyService(myForm);

		//��ѧ������Ŀ��װ��model
		commService.getModelValue(model, request);
		
		boolean flag=service.saveXmsy(model, user);
		
		String message=flag?"˳�ӳɹ�":"����ʧ�ܣ�����ϵ�����Ա";
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
