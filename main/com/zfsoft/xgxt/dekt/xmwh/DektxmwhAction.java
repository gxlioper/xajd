/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.dekt.xmwh;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;


/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ��ڶ�����-��Ŀά�� ����ģ��
 * @�๦��������TODO(������һ�仰��������������) 
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��4��21��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DektxmwhAction extends SuperAction<DektxmwhForm,DektxmwhService> {
	private DektxmwhService service = new  DektxmwhService();
	private static final String url = "dekt_xmwh_list.do";
	
	public ActionForward xmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhList");
	}
	
	public ActionForward getXmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm model = (DektxmwhForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	
	
	public ActionForward splcEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm model = (DektxmwhForm) form;
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("dekt");
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("splcEdit");
	}
	
	public ActionForward splcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxmwhForm myForm = (DektxmwhForm) form;
		boolean result =service.runUpdate(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
//	public ActionForward exportData(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DektxmwhForm model = (DektxmwhForm) form;
//		// ���ɸ߼���ѯ����
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		model.setSearchModel(searchModel);
//		
//		User user = getUser(request);
//		List<HashMap<String, String>> resultList = service.getAllList(model);// ��ѯ�����м�¼������ҳ
//		 
//		IExportService exportService = new ExportExcelImpl();
//		ExportModel exportModel = model.getExportModel();
//		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
//		exportModel.setDataList(resultList);// ��������
//		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
//		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
//		FileUtil.outputExcel(response, file);
//		return null;
//	}
	
	
}