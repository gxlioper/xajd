/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-31 ����09:35:55 
 */  
package com.zfsoft.xgxt.xsxx.yrgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class YrglAction extends SuperAction{
	YrglService service = new YrglService();
	private static final String url = "yrgl_list.do";
	/**
	 * @����: �������list
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	@SystemLog(description="����ѧ����Ϣ-���������list")
	public ActionForward Yrgllist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglService service=new YrglService();
		YrglForm model=(YrglForm) form;
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("realTable", "yrgl_list");
		String path = "yrgl_list.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("Yrgllist");
	}
	/**
	 * @����: ����
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:16:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="����ѧ����Ϣ-�������-����")
	public ActionForward Yrgladd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglForm myForm = (YrglForm) form;
		YrglService service=new YrglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("xnList", Base.getXnndList());
		//Ĭ�Ͻ�ѧ������Ϊϵͳ��ǰѧ��
		if(StringUtil.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
        }
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// Ψһ���ж�
			boolean isExist = service.isExistQysj(myForm);
			if (!isExist) {
				super.resetToken(request);
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		String path = "xsxx_yrgl.do?method=Yrgladd";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("Yrgladd");
	}  

	/**
	 * @����: ɾ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:18:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="����ѧ����Ϣ-�������-ɾ��VALUES:{values}")
	public ActionForward Yrgldel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglService service = new YrglService();
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
       } 	
	/**
	 * @����: �޸�
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:25:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="����ѧ����Ϣ-�������-�޸�GUID:{guid},XH:{xh}")
	public ActionForward Yrgledit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglService service=new YrglService();
		YrglForm model=(YrglForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.isExistQysj(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_STUDENT, false));
			}
			return null;
		}
		YrglForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		//���д���
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("Yrgledit");
	}
	/**
	 * @����: �鿴
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:33:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	@SystemLog(description="����ѧ����Ϣ-����������鿴")
	public ActionForward Yrglview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglService service=new YrglService();
		YrglForm model=(YrglForm) form;	
		YrglForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", StringUtils.formatData(service.getXn(model.getXh())));
		//�����д����������<br/>���
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("Yrglview");
		}
	/**
	 * @����: ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����09:36:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="����ѧ����Ϣ-�����������")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YrglService service=new YrglService();
		YrglForm model=(YrglForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXshjgldcList(model,user);//��ѯ�����м�¼������ҳ
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
