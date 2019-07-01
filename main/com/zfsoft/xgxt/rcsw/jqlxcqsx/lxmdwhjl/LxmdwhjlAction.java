/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��3��27�� ����1:49:56 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��3��27�� ����1:49:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhjlAction extends SuperAction<LxmdwhjlForm,LxmdwhjlService>{
	
	private static final String url = "jqlx_lxmdwhjl.do?method=lxmdwhjlList";
	private LxmdwhjlService service = new LxmdwhjlService();
	
	/**
	 * @����:��ת����У����ά����¼�б�ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��27�� ����2:40:35
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
	public ActionForward lxmdwhjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String path = "jqlx_lxmdwhjl.do?method=lxmdwhjlList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxmdwhjlList");
		
	}
	
	/**
	 * @����:��ѯ��У����ά���б�JSON������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��27�� ����2:43:08
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
	public ActionForward getLxmdwhjlData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LxmdwhjlForm lxmdwhjlForm = (LxmdwhjlForm)form;

		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		lxmdwhjlForm.setSearchModel(searchModel);
		
		List<HashMap<String, String>> resultList = service.getPageList(lxmdwhjlForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:��У����ά����¼�鿴
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��27�� ����2:44:38
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
	public ActionForward lxmdwhjlShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jlid = request.getParameter("jlid");
		HashMap<String,String> lxmdwhjl = service.getLxmdwhjlById(jlid);
		request.setAttribute("lxmdwhjl", lxmdwhjl);
		return mapping.findForward("lxmdwhjlShow");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����8:49:32
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
		
		LxmdwhjlForm lxmdwhjlForm = (LxmdwhjlForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		lxmdwhjlForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(lxmdwhjlForm,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = lxmdwhjlForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
