package xgxt.xsxx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.form.XsxxcshForm;
import xgxt.xsxx.service.XsxxXjydglService;
import xgxt.xsxx.service.XxcshglService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/** 
 * Creation date: 02-23-2011
 * author lr 
 */
public class XxcshglAction extends BasicAction {
	
	/**
	 * ѧУ������Ϣ��ʼ��
	 * */
	public ActionForward xxbmcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxglService xsxxglService = new XsxxglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//��������
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_xxbmxxlsb";
		String viewName = "xg_view_xsxx_xxbmxxlsb";
		//����ֶ�
		String[] outputColumn = {"bmdm","bmmc","bmpyjc","bmfdm","bmjb","bmlbmc"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//���ӽӿ�
			message = service.xxbmLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//���Ϊ��ȷ����
			message = service.xxbmBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//ȫ�����Ϊ��ȷ����
			message = service.xxbmQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//ɾ������
			deleteOperation(request, tableName);
		}
		
		//��ѯ����
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//Ҫ��ʼ�����ݵ�ģ��
		request.setAttribute("mkList", service.getInitDataModule());
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//��������б�
		request.setAttribute("xxbmlbList", xsxxglService.getList(GlobalsVariable.XTWH_XXBMLB_LIST));	
		//�Ƿ�ɳ�ʼ�����ݱ�־
		request.setAttribute("cshFlag", service.getCshFlag("xxbm"));
		return mapping.findForward("success");
	}
	
	/**
	 * ѧУ������Ϣ�޸ġ��鿴��ϸ
	 * */
	public ActionForward xxbmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxglService xsxxglService = new XsxxglService();
		String tableName = "xg_xsxx_xxbmxxlsb";
		//����ֵ
		String pkValue = request.getParameter("pkValue");
		//��������
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//�޸�����
			updateOperation(request, tableName);
		}
		//����������ѯ��¼
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		//��������б�
		request.setAttribute("xxbmlbList", xsxxglService.getList(GlobalsVariable.XTWH_XXBMLB_LIST));
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxbmUpdate");
	}
	
	
	
	/**
	 * רҵ��Ϣ��ʼ��
	 * */
	public ActionForward zyxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//��������
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_zyxxlsb";
		String viewName = "xg_view_xsxx_zyxxlsb";
		//����ֶ�
		String[] outputColumn = {"zydm","zymc","zyjc","bmdm","bmmc","xkmldm","zyywmc","gjzydm"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//���ӽӿ�
			message = service.zyxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//���Ϊ��ȷ����
			message = service.zyxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//ȫ�����Ϊ��ȷ����
			message = service.zyxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//ɾ������
			deleteOperation(request, tableName);
		}
		
		//��ѯ����
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//Ҫ��ʼ�����ݵ�ģ��
		request.setAttribute("mkList", service.getInitDataModule());
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//�Ƿ�ɳ�ʼ�����ݱ�־
		request.setAttribute("cshFlag", service.getCshFlag("zyxx"));
		return mapping.findForward("zyxxcsh");
	}
	
	/**
	 * רҵ��Ϣ�޸ġ��鿴��ϸ
	 * */
	public ActionForward zyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableName = "xg_xsxx_zyxxlsb";
		//����ֵ
		String pkValue = request.getParameter("pkValue");
		//��������
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//�޸�����
			updateOperation(request, tableName);
		}
		//����������ѯ��¼
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyxxUpdate");
	}
	
	/**
	 * �༶��Ϣ��ʼ��
	 * */
	public ActionForward bjxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//��������
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_bjxxlsb";
		String viewName = "xg_view_xsxx_bjxxlsb";
		//����ֶ�
		String[] outputColumn = {"bjdm","bjmc","bjjc","bmdm","bmmc","zydm","zymc","nj"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//���ӽӿ�
			message = service.bjxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//���Ϊ��ȷ����
			message = service.bjxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//ȫ�����Ϊ��ȷ����
			message = service.bjxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//ɾ������
			deleteOperation(request, tableName);
		}
		
		//��ѯ����
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//Ҫ��ʼ�����ݵ�ģ��
		request.setAttribute("mkList", service.getInitDataModule());
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//�Ƿ�ɳ�ʼ�����ݱ�־
		request.setAttribute("cshFlag", service.getCshFlag("bjxx"));
		return mapping.findForward("bjxxcsh");
	}
	
	
	/**
	 * �༶��Ϣ�޸ġ��鿴��ϸ
	 * */
	public ActionForward bjxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String tableName = "xg_xsxx_bjxxlsb";
		//����ֵ
		String pkValue = request.getParameter("pkValue");
		//��������
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//�޸�����
			updateOperation(request, tableName);
		}
		//����������ѯ��¼
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxxUpdate");
	}
	
	/**
	 * ѧ����Ϣ��ʼ��
	 * */
	public ActionForward xsxxcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XxcshglService service = new XxcshglService();
		XsxxcshForm model = (XsxxcshForm)form; 
		//��������
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_xsxxlsb";
		String viewName = "xg_view_xsxx_xsxxlsb";
		//����ֶ�
		String[] outputColumn = {"xh","xm","xb","xbmc","nj","xz","xydm","xymc","zydm","zymc","xjztm","sfzx"};
		String message = "";
		if ("ljjk".equalsIgnoreCase(doType)){
			//���ӽӿ�
			message = service.xsxxLjjk(model);
		}
		if ("bjwzqsj".equalsIgnoreCase(doType)){
			//���Ϊ��ȷ����
			message = service.xsxxBjwzqsj(model);
		}
		if ("qbbjwzqsj".equalsIgnoreCase(doType)){
			//ȫ�����Ϊ��ȷ����
			message = service.xsxxQbbjwzqsj(model);			
		}		
		request.setAttribute("message", message);
		if ("del".equalsIgnoreCase(doType)){
			//ɾ������
			deleteOperation(request, tableName);
		}
		
		//��ѯ����
		this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		//Ҫ��ʼ�����ݵ�ģ��
		request.setAttribute("mkList", service.getInitDataModule());
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("pageSize", model.getPages().getPageSize());
		//�Ƿ�ɳ�ʼ�����ݱ�־
		request.setAttribute("cshFlag", service.getCshFlag("xsxx"));
		return mapping.findForward("xsxxcsh");
	}
	
	/**
	 * ѧ����Ϣ�޸ġ��鿴��ϸ
	 * */
	public ActionForward xsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxglService xsxxglService = new XsxxglService();
		String tableName = "xg_xsxx_xsxxlsb";
		//����ֵ		
		String pkValue = request.getParameter("pkValue");
		//��������
		String doType = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			//�޸�����
			updateOperation(request, tableName);
		}
		//����������ѯ��¼
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		//·���Ͷ�дȨ��Ϣ
		request.setAttribute("path", "xsxxjcsjcsh.do");
		FormModleCommon.commonRequestSet(request);
		//�б�����
		request.setAttribute("mzList", xsxxglService.getList(GlobalsVariable.XTWH_MZ_LIST));
		request.setAttribute("zzmmList", xsxxglService.getList(GlobalsVariable.XTWH_ZZMM_LIST));
		request.setAttribute("xsccList", xsxxglService.getList(GlobalsVariable.XTWH_PYCC_LIST));
		request.setAttribute("ssList", xsxxglService.getSsList());
		request.setAttribute("xjztList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		request.setAttribute("yhList", xsxxglService.getList(GlobalsVariable.XTWH_YH_LIST));
		FormModleCommon.setNjXyZyBjList(request);
		
		return mapping.findForward("xsxxUpdate");
	}
}