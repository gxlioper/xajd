/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-1-5 ����10:41:00 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������
 * @�๦������:  �㽭��ѧ����������-ͳ�Ʋ�ѯ-������Ŀ����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-5 ����10:26:12 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_pjxmhz.do";
	
	/**
	 * @����: ҳ��������ݲ�ѯ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-5 ����05:52:18
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
	@SystemLog(description = "��������������-ͳ�ƹ���-������Ŀ����-��ѯҳ��")
	public ActionForward getPjxmhzList(ActionMapping mapping, ActionForm form,
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
		String path = "xpjpy_tjgl_pjxmhz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjxmhzList");
	}
	
	/**
	 * @����: ������Ŀ���ܵ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-5 ����05:50:58
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();

		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: ������������Ӳ鿴��ϸ��Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-9 ����09:46:10
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
	public ActionForward pjxmhzHjrsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		PjxmhzForm model = (PjxmhzForm)form;
		PjxmhzService service = new PjxmhzService();
		
		if(QUERY.equals(model.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.viewRs(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("pjxmhzHjrsView");
	}
	
	/**
	 * @����: �ϱ�������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-10 ����05:45:09
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
	public ActionForward reportDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		return null;
	}
}
