package xsgzgl.gygl.mrjl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import com.zfsoft.xgxt.base.log.SystemLog;

public class MrjlglAction extends BasicExtendAction{
	
	/**
	 * ÿ�ռ�¼����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mrjlglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		MrjlglForm myForm = (MrjlglForm)form;
		
		String[] topTr = new String[]{"����", "ʱ��", "¥������", "ֵ����Ա", "����"};
		String doType = request.getParameter("doType");
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delMrjl(pks) ? "ɾ���ɹ���": "ɾ��ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		setWriteAbleAndTitle(request, "gyglnew_mrjlgl_mrjlgl.do");
		
		request.setAttribute("searchTj", myForm.getSearchModel());	//�߼���ѯ����
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", service.mrjlQuery(myForm));
		request.setAttribute("ldList", service.getLdList());		// ¥���б�
		request.setAttribute("zbryList", service.getZbryList());	// ֵ����Ա�б�
		request.setAttribute("realTable", "");
		request.setAttribute("tableName", "xg_view_gygl_mrzbjl");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		return mapping.findForward("mrjlglManage");
	}
	
	/**
	 * ÿ�ռ�¼����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ֵ���¼-ÿ�ռ�¼-����")
	public ActionForward mrjlglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		
		String doType = request.getParameter("doType");
		MrjlglForm myForm = (MrjlglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveMrjl(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());		// ¥���б�
		request.setAttribute("zbryList", service.getZbryList());	// ֵ����Ա�б�
		return mapping.findForward("mrjlglUpdate");
	}
	
	/**
	 * ÿ�ռ�¼����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ֵ���¼-ÿ�ռ�¼-�޸�LDDM:{lddm}")
	public ActionForward mrjlglModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		MrjlglForm myForm = (MrjlglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.updateMrjl(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("rs", service.getMrjl(pkValue));
		
		request.setAttribute("doType", doType);
		request.setAttribute("ldList", service.getLdList());		// ¥���б�
		request.setAttribute("zbryList", service.getZbryList());	// ֵ����Ա�б�
		return mapping.findForward("mrjlglModi");
	}
}
