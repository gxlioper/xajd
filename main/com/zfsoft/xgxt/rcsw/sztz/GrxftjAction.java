/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-2 ����09:20:17 
 */  
package com.zfsoft.xgxt.rcsw.sztz;

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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-2 ����09:20:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GrxftjAction extends SuperAction<SzxfModel, SzxfService>{
	
	private static final String url = "rcsw_sztz_grxftj.do";
	
	
	/** 
	 * @����:����ѧ��ͳ����תҳ�棨���չ������Ի����ƣ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-2 ����10:57:54
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_sfzx(new String[] {"��У"});
		request.setAttribute("searchTj", searchModel);	
		request.setAttribute("path", "rcsw_sztz_grxftj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	
	/** 
	 * @����:��ȡ����ѧ��ͳ���б����չ������Ի����ƣ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-2 ����10:58:27
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
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzxfService service = getService();
		SzxfModel model = (SzxfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageGrxftjList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/** 
	 * @����:��������ѧ��ͳ���б����չ������Ի����ƣ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-2 ����04:18:46
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
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzxfService service = getService();
		SzxfModel model = (SzxfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllGrxftjList(model,user);
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
