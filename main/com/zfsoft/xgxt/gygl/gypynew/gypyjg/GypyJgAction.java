package com.zfsoft.xgxt.gygl.gypynew.gypyjg;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqForm;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqService;
import common.newp.StringUtil;

public class GypyJgAction extends SuperAction<GypyJgForm,GypyJgService> {
	private GypyJgService service = new GypyJgService();
	private final String url = "gygl_gypynew_gypyjg.do";
	/**
	 * 
	 * @����: ��Ԣ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����11:24:44
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
	public ActionForward getGypyJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypyjgcx");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����11:40:58
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
	public ActionForward searchForJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:��Ԣ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-25 ����04:33:46
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
	public ActionForward addJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		request.setAttribute("month",nowYearMonth);
		return mapping.findForward("addjg");
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:22:04
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
	public ActionForward editJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		GypyJgForm model = service.getModel(myForm);
		GypySqForm sqForm = new GypySqForm();
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			BeanUtils.copyProperties(sqForm, model);
		}
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		request.setAttribute("month",nowYearMonth);
		request.setAttribute("qsxx",new GypySqService().getQshxx(sqForm));
		return mapping.findForward("editjg");
	}
	/**
	 * 
	 * @����: ���湫Ԣ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:28:43
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
	public ActionForward saveJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		User user = getUser(request);
		boolean rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:30:08
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
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
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����02:31:18
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
		GypyJgForm model = (GypyJgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

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
	 * @����: ����鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-28 ����02:35:41
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
	public ActionForward viewJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		GypyJgForm model = service.getModel(myForm);
		GypySqForm sqForm = new GypySqForm();
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatDataView(model) );
			BeanUtils.copyProperties(sqForm, model );
		}
		request.setAttribute("qsxx",new GypySqService().getQshxx(sqForm));
		return mapping.findForward("viewjg");
	}
	
	/**
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-28 ����04:48:26
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
	public ActionForward saveCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		boolean rs = service.saveCx(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �����Ǽ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-1 ����02:35:45
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
	public ActionForward cancelXj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		@SuppressWarnings("unused")
		GypyJgForm myForm = (GypyJgForm)form;
		return mapping.findForward("cx");
	}
}
