/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��14�� ����3:33:38 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת��-������־ģ��
 * @�๦������: Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��14�� ����3:33:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GprzAction extends SuperAction<GprzForm,GprzService>{
	
	private static final String url = "dtjs_gprz.do?method=gprzList";
	private final String ZCSQ ="zcsq";
	
	/**
	 * @����:��ѯ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����10:39:21
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
	public ActionForward gprzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		
		if (QUERY.equals(gprzForm.getType())){
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			gprzForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = gprzService.getPageList(gprzForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gprzList");
	}
	
	/**
	 * 
	 * @����:������־����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��13�� ����4:53:47
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
	public ActionForward gprzShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		GprzForm gf = gprzService.getModel(gprzForm);
		BeanUtils.copyProperties(gprzForm,gf);
		String xh = request.getParameter("xh");
		
		if (!StringUtil.isNull(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		String path = "dtjs_gprz.do?method=xxjgShow";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("gprzShow");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��14�� ����3:59:24
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		gprzForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = gprzService.getAllList(gprzForm,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = gprzForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
