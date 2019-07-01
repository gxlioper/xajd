package xgxt.qgzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import xgxt.qgzx.QgzxGbmygExcelActionForm;
import xgxt.qgzx.QgzxGbmygExcelService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ-��𷢷�-�������ù���ͳ��-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author HongLin
 * @version 1.0
 */
public class QgzxGbmygExcelAction extends BasicAction {

	public ActionForward gbmygbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGbmygExcelService service = new QgzxGbmygExcelService();
		QgzxGbmygExcelActionForm myForm = (QgzxGbmygExcelActionForm) form;
		//String searchType = "cwtj";		
		MessageResources message = getResources(request);
		//�߼���ѯ
		//String path = myForm.getSearchModel().getPath() + "&searchType="+searchType;
		//myForm.getSearchModel().setPath(path);								
		//����ͳ���������ķ���
		String doType = request.getParameter("doType");
		if("exp".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printGbmyg(myForm,message,request,response);
			return null;
		}
		//���ù��ʷ����������ķ���
		if("gzff".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.printGzff(myForm,message,request,response);
			return null;
		}
		return mapping.findForward("gbmygbtj");
		
	}
	
	// -------------------2012.3.13 QLJ ��ʱ��λͳ��----------------------
	
	/**
	 * �㽭����ѧԺ��ʱ��λ���ͳ��
	 */
	public ActionForward lsgwTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGbmygExcelService service = new QgzxGbmygExcelService();
		QgzxGbmygExcelActionForm myForm = (QgzxGbmygExcelActionForm) form;
		//String searchType = "cwtj";		
		MessageResources message = getResources(request);
	
		response.setContentType("application/vnd.ms-excel");
		service.printLsgwcjInfo(myForm,message,request,response);
		return null;
		
		
	}
}
