/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����11:00:08 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import org.apache.struts.action.ActionForward;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����ѧ���±�
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1186]
 * @ʱ�䣺 2016-3-17 ����11:00:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class XscxqybAction  extends SuperAction{
	XscxqybService service = new XscxqybService();
	private static final String url = "rcsw_xscxqyb.do";
/**
 * @����:TODO(ѧ����ѧ���±���ѯҳ��)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:17:09
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
	@SystemLog(description="�����ճ����񡪡�ѧ����ѧ���±�����List")
	public ActionForward XscxqybList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model = (XscxqybForm) form;
		User user = getUser(request);
		if(QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_xscxqyb.do";
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("XscxqybList");
	}
/**
 * @����:����
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-17 ����06:37:16
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
	public ActionForward XscxqybAdd (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XscxqybForm model = (XscxqybForm) form;
		XscxqybService service = new XscxqybService();
		User user = getUser(request);
		//��ȡ��ǰѧ��ѧ��
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getCurrentXqmc(model));
		//��ȡ��д��
		model.setTxr(user.getUserName());
		request.setAttribute("txr", user.getRealName());	
		if (SAVE.equalsIgnoreCase(model.getType())) {
			// Ψһ���ж�
			boolean isExist = service.isExistYf(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.RCSW_XSCXQYB_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSCXQYB_REPEAT, false));
			}
			return null;
		}
		String path = "rcsw_xscxqyb.do?method=XscxqybAdd";
		request.setAttribute("path", path);
		return mapping.findForward("XscxqybAdd");
	}
/**
 * @����:ɾ��
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-22 ����10:43:41
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
	public ActionForward XscxqybDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
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
 * @����:�޸�
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-22 ����02:49:36
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
	public ActionForward XscxqybUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model=(XscxqybForm) form;
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.isExistYf(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSCXQYB_REPEAT, false));
			}	
			return null;
		}
		User user = getUser(request);
		//��ȡ��ǰѧ��ѧ��
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getCurrentXqmc(model));
		//��ȡ��д��
		model.setTxr(user.getUserName());
		request.setAttribute("txr", user.getRealName());
		XscxqybForm myForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("XscxqybUpdate");
	}	
/**
 * @����:�鿴
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-23 ����02:12:04
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
	public ActionForward XscxqybView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm myForm = (XscxqybForm) form;
		if(!StringUtil.isNull(myForm.getJgid())){
			HashMap<String ,String> xxck = service.getXxck(myForm.getJgid());
			request.setAttribute("xxck", xxck);
		}
		String path = "rcsw_xscxqyb.do?method=XscxqybView";
		request.setAttribute("path", path);
		return mapping.findForward("XscxqybView");
	}
/**
 * @����:����
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-24 ����02:03:02
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscxqybService service = new XscxqybService();
		XscxqybForm model=(XscxqybForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscxqybdcList(model,user);//��ѯ�����м�¼������ҳ
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
