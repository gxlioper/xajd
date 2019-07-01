package xsgzgl.xsxx.qtxx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxQtxxAction extends BasicAction{
	
	XsxxQtxxService service =new XsxxQtxxService();
	
	XsxxQtxxInit init =new XsxxQtxxInit();
	
	/**
	 * ��ѯģ�� �Ը߼���ѯΪ��ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqtxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		String xmdm=request.getParameter("xmdm");
		
		//��formֵ������model��
		/** �˲������û����ڲ�ѯʱֻ���� BasicModel �ڲ�ѯʱ��Ҫ��ҳ���Խ�
		 * Pages���󿽱���BasicModel
		 * ��demo�Ĳ�ѯ���Ը߼���ѯΪ��������Ҳ��Ҫ���� SearchModel
		 * ����BasicModel �� SearchModel �⻹�ṩ�� USER����Ŀ��� ����Ȩ�޿���
		 */
		BeanUtils.copyProperties(model, myForm);
		
		//����ģ��
		model.setGnmk("xsxx");
		//ģ��·��
		model.setPath("xsxx_qtxx.do?method=xsqtxxManage&xmdm=001");/** �߼���ѯ�õ������� �Լ�jspҳ���ϵ�title��writeAble*/

		init.initXsqtxx(rForm, myForm,user, request);
		
		//�߼���ѯ
		SearchModel searchModel=model.getSearchModel();
//		searchModel.setPath(model.getPath());
		request.setAttribute("searchTj", searchModel);/** �߼���ѯֵ���� */
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("path", model.getPath());
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("xmdm", xmdm);
		
		request.setAttribute("tableName", "xg_view_xsxx_xsqtxx");
		
		request.setAttribute("realTable", "xg_xsxx_qtxxb");
		// =================== end ===================
	
		return mapping.findForward("xsqtxxManage");
	}
	
	/**
	 * ��ѯģ�� �Ը߼���ѯΪ��ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqtxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		String doType=request.getParameter("doType");
		
		String xh=request.getParameter("xh");
		
		String xmdm=request.getParameter("xmdm");
		
		if(Base.isNull(xmdm)){
			
			xmdm=request.getParameter("va");
		}
		
		myForm.setXh(xh);
		
		myForm.setXmdm(xmdm);
		
		HashMap<String,String>rs=service.getQtxxDetail(myForm);
		
		//��formֵ������model��
		/** �˲������û����ڲ�ѯʱֻ���� BasicModel �ڲ�ѯʱ��Ҫ��ҳ���Խ�
		 * Pages���󿽱���BasicModel
		 * ��demo�Ĳ�ѯ���Ը߼���ѯΪ��������Ҳ��Ҫ���� SearchModel
		 * ����BasicModel �� SearchModel �⻹�ṩ�� USER����Ŀ��� ����Ȩ�޿���
		 */
		BeanUtils.copyProperties(model, myForm);
		
		//����ģ��
		model.setGnmk("xsxx");
		//ģ��·��
		model.setPath("xsxx_qtxx.do?method=xsqtxxManage&xmdm=001");

		//init.initBzrpy(rForm, model, request);
		
		//�߼���ѯ
		request.setAttribute("searchTj", model.getSearchModel());/** �߼���ѯֵ���� */
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("xmdm", xmdm);
		
		request.setAttribute("doType", doType);
		
		request.setAttribute("path", model.getPath());
		
		FormModleCommon.commonRequestSet(request);
		// =================== end ===================
	
		return mapping.findForward("xsqtxxDetail");
	}
	
	
}
