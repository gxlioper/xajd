/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����04:59:43 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

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
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2015-9-14 ����09:30:39 
 * @�汾�� V5.17
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XshjglAction extends SuperAction{
	
	XshjglService service = new XshjglService();
	
	private static final String url = "xshjgl_list.do";
	
	/**
	 * @����:listҳ��
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-9-14 ����09:31:04
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
	@SystemLog(description="�����ճ�����-ѧ������������list")
	public ActionForward Xshjgllist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
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
		request.setAttribute("realTable", "xshjgl_list");
		String path = "xshjgl_list.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("Xshjgllist");
	}
	
/**
 * @����: ����
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-9-14 ����09:31:33
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
	@SystemLog(description="�����ճ�����-ѧ����������-����XH:{xh},QYZT:{qyzt},QYSJ:{qysj}")
	public ActionForward Xshjgladd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglForm myForm = (XshjglForm) form;
		XshjglService service=new XshjglService();
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
		String path = "rcsw_xshjgl.do?method=Xshjgladd";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("Xshjgladd");
	}  

/**
 * @����: ɾ��
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-9-14 ����09:31:52
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
	@SystemLog(description="�����ճ�����-ѧ����������-ɾ��VALUES:{values}")
	public ActionForward Xshjgldel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service = new XshjglService();
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
 * @����:��ѧ����Ϣ���С��޸ġ�
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-10-13 ����10:16:32
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
	@SystemLog(description="�����ճ�����-ѧ����������-�޸�HJGLID:{hjglid},XH:{xh},QYZT:{qyzt},QYSJ:{qysj}")
	public ActionForward Xshjgledit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
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
		XshjglForm myForm = service.getModel(model);
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
		//BeanUtils.copyProperties(model, myForm);   �����д���������ֻ��г���<br/>״̬
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("qyzt", model.getQyzt());
		return mapping.findForward("Xshjgledit");
	}
	
/**
 * @����: �鿴
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-9-14 ����09:32:33
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
	@SystemLog(description="�����ճ�����-ѧ�������������鿴")
	public ActionForward Xshjglview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm model=(XshjglForm) form;
		
		XshjglForm myForm = service.getModel(model);
		myForm.setQyztmc(myForm.getQyzt().equals("0")?"Ǩ��":"Ǩ��");
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));//�����д����������XXX<br/>XXX
		return mapping.findForward("Xshjglview");
		}
	/**
	 * @����: ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-9-14 ����09:32:51
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
	@SystemLog(description="�����ճ�����-ѧ����������������")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XshjglService service=new XshjglService();
		XshjglForm myForm = (XshjglForm) form;
		XshjglForm model=(XshjglForm) form;
		
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
