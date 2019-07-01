/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-8 ����02:38:03 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-8 ����02:38:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XshdglAction extends SuperAction<XshdglForm, XshdglService> {
	private XshdglService service = new  XshdglService();
	
	private static final String url = "rcsw_xshdgl_xshdgl.do";
	
	/**
	 * 
	 * @����:ѧ�����Ϣ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����09:12:59
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
	public ActionForward getXshdglcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XshdglForm model = (XshdglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_xshdgl_xshdgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����:ѧ�����Ϣ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����09:13:46
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XshdglForm model = (XshdglForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:ѧ�����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����09:55:05
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
	public ActionForward addXshd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XshdglForm model = (XshdglForm) form;
		String path = "xshdgl_xshdgl.do?method=addXshd";
		request.setAttribute("path", path);
		//request.setAttribute("bmdmlist", service.getBmdmList());
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:03:48
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
	public ActionForward editXshd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		XshdglForm model = (XshdglForm) form;
		model = service.getModel(model);
		if("0".equals(model.getHdlx())){
			request.setAttribute("hdlxmc", "У��");
		}else{
			request.setAttribute("hdlxmc", "У��");
		}
		String path = "xshdgl_xshdgl.do?method=editXshd";
		request.setAttribute("path", path);
		//request.setAttribute("bmdmlist", service.getBmdmList());
		request.setAttribute("XshdglForm", model);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:04:15
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
	public ActionForward saveXshd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		XshdglForm model = (XshdglForm) form;
		boolean result = true;
		//ͬһ���쵥λ��ͬһʱ�䲻������������ͬ���Ƶ���Ŀ
		boolean checkresult = service.checkIsExistNotSame(model);
		//�������false,�������أ����豣��
		if(!checkresult){
			String message = "������ͬһʱ���ظ�����ͬһ���";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		model.setHdmc(model.getHdmc().trim());
		if("save".equals(model.getType())){
			result = service.runInsert(model);
		}else{
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴ѧ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:13:45
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
	public ActionForward ckXshd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		XshdglForm model = (XshdglForm) form;
		request.setAttribute("xshdmap", service.getXshdMap(model.getSqid()));
		String path = "xshdgl_xshdgl.do?method=ckXshd";
		request.setAttribute("path", path);
		return mapping.findForward("viewXbzjxs");
		
	}
	
	/**
	 * 
	 * @����:ɾ��ѧ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:13:45
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
	public ActionForward delXshdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
}
