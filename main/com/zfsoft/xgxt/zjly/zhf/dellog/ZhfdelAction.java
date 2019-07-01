/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-21 ����09:29:38 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ɾ����־��ѯ
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-3-21 ����09:29:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfdelAction extends SuperAction<ZhfdelForm, ZhfdelService>{
	private ZhfdelService service = new ZhfdelService();
	private static final String url = "xg_zjly_dellog.do";
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�CP[���ţ�982]
	 * @���ڣ�2017-3-21 ����10:45:49
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
	public ActionForward getZhfDelList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfdelForm model = (ZhfdelForm) form;
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
			request.setAttribute("searchTj", searchModel);
			request.setAttribute("path", url);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("zhfdelCx");
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-21 ����01:43:40
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfdelForm model = (ZhfdelForm) form;
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				List<HashMap<String, String>> resultList = service.getAllList(model, user);
			// Ĭ�ϸ߼���ѯ����
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
