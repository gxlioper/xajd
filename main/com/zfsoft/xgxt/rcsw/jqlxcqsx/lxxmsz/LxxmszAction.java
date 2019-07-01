/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:21:50 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
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
 * @�๦������:��У��Ŀ����
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:21:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxxmszAction extends SuperAction<LxxmszForm, LxxmszService> {
	LxxmszService service = new LxxmszService();
	/**
	 * 
	 * @����: ��У��Ŀ���ò�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����02:08:04
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
	public ActionForward getXmszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxxmszForm model = (LxxmszForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_rcsw_cqsx_jqlx_lxxmsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ��У��Ŀ���ò鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����02:08:24
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
	public ActionForward ckLxxmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxxmszForm myForm = (LxxmszForm)form;
		LxxmszForm rs = service.getModel(myForm.getXmid());
		request.setAttribute("rs",rs);
		return mapping.findForward("ck");
		
	}
	
	/**
	 * 
	 * @����:��У��Ŀ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����02:17:30
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
	public ActionForward addLxxmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		return mapping.findForward("add");
		
	}
	
	/**
	 * 
	 * @����: ��У��Ŀ�����޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:07:31
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
	public ActionForward updateLxxmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxxmszForm myForm = (LxxmszForm)form;
		LxxmszForm model = service.getModel(myForm.getXmid());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("update");
		
	}
	
	/**
	 * 
	 * @����: ��Ŀ���ý������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:15:02
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
	public ActionForward saveSzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxxmszForm myForm = (LxxmszForm)form;
		if(!service.checkIsNotExist(myForm.getXmid(), myForm.getXmmc())){
			String message = "��Ŀ�����Ѵ��ڣ��������Ŀ���ƣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean rs = service.saveSzjg(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����: ������Ŀ���ü�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:30:15
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
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxxmszForm myForm = (LxxmszForm)form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		myForm.getPages().setMaxRecord(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ����Ŀ���ý��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:32:04
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
	public ActionForward delSzjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.ifCanDel(ids)){
				String message = "�����ѱ�ʹ�õ���Ŀ���ƣ�������ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
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
