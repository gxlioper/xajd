/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

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
import xgxt.utils.String.StringUtils;

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

import common.newp.StringUtil;

/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ���������ѵά�� ����ģ��
 * @�๦����������������ѵά��Action
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2016��11��17��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class PxwhAction extends SuperAction<PxwhForm,PxwhService> {
	private PxwhService service = new  PxwhService();
	private static final String url = "xg_xlzx_pxwh.do";
	private static final String urlstu = "xg_xlzx_pxbm.do";
	
	/**
	 * @���������｡����ѵά���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward pxwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pxwhList");
	}
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��17��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-��������ѵ-��������ѵά��-����PXID:{pxid}")
	public ActionForward pxwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myForm = (PxwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// Ψһ���ж�
			if (service.checkExist(myForm)) {
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(getJsonResult(MessageKey.XLZX_PXWH_REPEAT, false));
			}
			return null;
		}
		return mapping.findForward("pxwhAdd");
	}
	
	/**
	 * @�������༭
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��18�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-��������ѵ-��������ѵά��-�༭PXID:{pxid}")
	public ActionForward pxwhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myForm = (PxwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// Ψһ���ж�
			if (service.checkExist(myForm)) {
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(getJsonResult(MessageKey.XLZX_PXWH_REPEAT, false));
			}
			return null;
		}
		PxwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		return mapping.findForward("pxwhEdit");
	}
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��17��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward pxwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myform = (PxwhForm) form;
		PxwhForm model = service.getModel(myform);
		BeanUtils.copyProperties(myform,model);
		return mapping.findForward("pxwhView");
	}
	
	/**
	 * @����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��17��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PxwhForm model = (PxwhForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getBmxsdcList(model);// ��ѯ�����м�¼������ҳ
		
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
	 * @������ɾ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��18�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-��������ѵ-��������ѵά��-ɾ��:PXID{values}")
	public ActionForward pxwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @������δ����/�ѱ����б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = urlstu)
	public ActionForward pxbmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPxbmList(model,user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xg_xlzx_pxbm.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pxbmList");
	}
	
	/**
	 * @����������/ȡ������ ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = urlstu,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������ѯ-��������ѵ-��ѵά��-����:PXID{pxid},XH{xh}")
	public ActionForward bmcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh=request.getParameter("xh");
		String bmtype=request.getParameter("bmtype");
		String pxid=request.getParameter("pxid");
		if(StringUtils.isNull(xh)||StringUtils.isNull(bmtype)||StringUtils.isNull(pxid)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XLZX_PXWH_CZFAIL));
			return null;
		}
		boolean result = service.bmcz(pxid, xh, bmtype);
		String messageKey = result ? MessageKey.XLZX_PXWH_CZSUCCESS:MessageKey.XLZX_PXWH_CZFAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @�������ѱ���ѧ���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward ybmxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getYbmxsList(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xg_xlzx_ybmxslist.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybmxsList");
	}
	
}
