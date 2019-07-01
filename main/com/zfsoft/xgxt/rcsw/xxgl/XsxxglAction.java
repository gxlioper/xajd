/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����03:25:29 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ѧ����Ѫ����action 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-4-18 ����03:26:39 
 * @�汾�� V5.1.75
 */
public class XsxxglAction extends SuperAction {
	
	private static final String url = "rcsw_xsxxgl.do?method=gjcxXxgl";

	private static final String  XSXXGL = "xsxxgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	/**
	 * @����:ѧ����Ѫ��Ϣ�߼�ģʽ��ѯ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:22:47
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gjcxXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_xsxxgl.do?method=gjcxXxgl";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "XG_RCSW_XSXXGL");
		request.setAttribute("realTable", "XG_RCSW_XSXXGL");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gjcxXxgl");
	}
	/**
	 * @����:����ѧ����Ѫ��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:26:34
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-ѧ����Ѫ����-ѧ����Ѫ��Ϣά��-����XH:{xh},XN:{xn},XXSJ:{xxsj}")
	public ActionForward zjXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		myForm.setXn(Base.currXn);
		String path = "rcsw_xsxxgl.do?method=zjXxgl";
		request.setAttribute("path", path);
		request.setAttribute("model", StringUtils.formatData(myForm));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("zjXxgl");
	}
	/**
	 * @����:����ѧ��������Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-3 ����10:59:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param xh ѧ��
	 * void �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XSXXGL);
		request.setAttribute("jbxxList", jbxxList);
	}
	/**
	 * @����:�޸�ѧ����Ѫ��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:26:01
	 * @�޸ļ�¼:  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-ѧ����Ѫ����-ѧ����Ѫ��Ϣά��-�޸�XXGLDM:{xxgldm},XH:{xh},XN:{xn},XXSJ:{xxsj}")
	public ActionForward xgXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		String path = "rcsw_xsxxgl.do?method=xgXxgl";
		request.setAttribute("path", path);
		request.setAttribute("type", "update");
		XsxxglForm model = (XsxxglForm)StringUtils.formatData(service.getModel(myForm));
		request.setAttribute("model", model);
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("xgXxgl");
	}
	/**
	 * @����:�鿴ѧ����Ѫ��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:26:01
	 * @�޸ļ�¼:  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		String path = "rcsw_xsxxgl.do?method=ckXxgl";
		request.setAttribute("path", path);
		request.setAttribute("type", "update");
		XsxxglForm model = service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("ckXxgl");
	}
	/**
	 * @����:ɾ��ѧ����Ѫ��Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:27:15
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward �������� 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-ѧ����Ѫ����-ѧ����Ѫ��Ϣά��-ɾ��VALUES:{values}")
	public ActionForward scXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	
	/**
	 * ѧ����Ѫ��Ϣά���Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xsxxxxwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XsxxglForm model = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
