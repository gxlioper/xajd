/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-22 ����04:59:52 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-ͳ�ƹ���-������Ŀ����
 * @�๦������: ͳ��ÿѧ�ꡢÿѧ�ڡ�ÿ����Ŀ�Ļ�����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-2-22 ����04:59:52 
 * @�汾�� Ver 5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzAction extends SuperAction {
	private static final String url = "xg_pjpy_tjgl_pjxmhz.do";
	
	/**
	 * @����: ��ѯ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-2-22 ����05:35:09
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
	public ActionForward viewPjxmhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//�õ��û�
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "xg_pjpy_tjgl_pjxmhz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPjxmhz");
	}
	
	/**
	 * @����: ������Ŀ���ܲ�ѯ�б��Զ��嵼��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-2-23 ����11:18:23
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
	public ActionForward pjxmhzExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		exportData(model, request, response);
		return null;
	}
	
	/**
	 * @����: ʵ���Զ��嵼��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-2-23 ����11:19:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void exportData(PjxmhzForm model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				PjxmhzForm fmtModel = (PjxmhzForm)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				PjxmhzService service = new PjxmhzService();
				return service.getPageList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}
	
	/**
	 * @����: ����������ĳ����ӣ���ת��ÿ�������õľ���������ѧ����ϸ��Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-2-23 ����02:14:30
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
	public ActionForward viewRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		if (QUERY.equals(model.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.viewRs(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("viewRs");
	}
	
	/**
	 * @����: ��������ӽ�����ϸ������ѯ�Զ��嵼��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-2-23 ����11:18:23
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
	public ActionForward pjxmhzRsExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.viewRs(model, user, false);// ��ѯ�����м�¼������ҳ

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
}
