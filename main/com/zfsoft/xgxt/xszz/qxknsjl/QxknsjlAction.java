/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:31:00 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-21 ����08:35:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QxknsjlAction extends SuperAction<QxknsjlForm, QxknsjlService> {
	
	
	//private static final String QXKNSZG = "qxknszg";
	private static final String KNSRD = "knsrd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_qxknsjl_cx.do";
	
	public ActionForward qxKnsjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknsjlForm model = (QxknsjlForm) form;
		QxknsjlService service = new QxknsjlService();		
		if (QUERY.equals(model.getType())){
			
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
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
		String path = "xszz_qxknsjl_cx.do";		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxKnsjlManage");		
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-25 ����03:34:13
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
	public ActionForward viewQxKnszgjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			QxknsjlForm model = (QxknsjlForm) form;
			QxknsjlService service = new QxknsjlService();
			@SuppressWarnings("unused")
			User user = getUser(request);			
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//��ѯ�������������
			request.setAttribute("knsjgrs", service.getOneKnsjgList(model.getJgguid()));
			//��ѯ������Ϊ��Ϣ���
			//request.setAttribute("rs", StringUtils.formatData(service.getHcccqjtxInfo(model)));
			request.setAttribute("knsqxjlrs", service.getOneKnsqxjlList(model.getJgguid()));
			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(KNSRD);
			request.setAttribute("jbxxList", jbxxList);							
			return mapping.findForward("viewQxKnszgjl");
			
	}
	
	/**
	 * 
	 * ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknsjlForm model = (QxknsjlForm) form;
		QxknsjlService service = new QxknsjlService();

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
	
	

}
