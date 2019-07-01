/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:35:31 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-����ѧ������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:35:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpmdAction extends SuperAction {
	
	
	
	/**
	 * 
	 * @����: ����������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-22 ����03:58:12
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
	
	public ActionForward viewCpxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
		//�жϵ�ǰ����������Ա���Ƿ�Ϊ�գ��գ�������У����ʼ��
		boolean sfcz = service.getSfcz();
		if(!sfcz){
			//������Ա��ִ�г�ʼ������
			service.init();
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			request.setAttribute("userName",user.getUserName());
			
			return null;
		}
		
		CsszService csszservice = new CsszService();
		request.setAttribute("zqmc", csszservice.getModel().getZqmc());
		
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_cpmd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewCpxsList");
	}
	
	/**
	 * 
	 * @����: ������¼��ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-23 ����10:44:11
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
	@SystemAuth(url = "pj_tzjl.do")
	public ActionForward viewTzjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();  

		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getTzjlList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszservice = new CsszService();
		request.setAttribute("zqmc", csszservice.getModel().getZqmc());
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_tzjl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewTzjlList");
	}
	
	/**
	 * 
	 * @����: ������������ѧ��״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-24 ����10:34:21
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
	
	public ActionForward tzcpxszt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		CpmdModel model = (CpmdModel) form;
		User user = getUser(request);
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "��û�з��ʸ�ģ���Ȩ�ޣ�");
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (!StringUtil.isNull(model.getXh())){
			
			XsxxService xsxxService = new XsxxService();
			
			//�ж�ѧ���Ƿ����
			boolean isHaveXx = xsxxService.getCheckStuExists(model.getXh()); 
			if(isHaveXx){
				//���ص�����Ϣ
				HashMap<String, String> tjxx = service.getTzxx(model.getXh());
				request.setAttribute("tjxx", StringUtils.formatData(tjxx));
				
				//����ѧ��������Ϣ
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
				
			}else{
				request.setAttribute("xhInfo", "ѧ�Ų����ڣ�������¼��");
			}
			
		}		
		
		request.setAttribute("ids", request.getParameter("ids"));

		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		return mapping.findForward("tzcpxszt");
	}
	
	/**
	 * 
	 * @����: ��������Ա��һ���༶��������һ���༶
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-25 ����07:06:39
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
	
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����-����ѧ��TZHBJDM��{tzhbjdm}")
	public ActionForward updateCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		String tzbjdm = request.getParameter("tzqbjdm");
		
		User user = getUser(request);
		
		 if (!StringUtil.isNull(tzbjdm)){
			 
			//�༶����			 	
			String tzhbjdm = request.getParameter("tzhbjdm");
			String xh = request.getParameter("xh");
			 
			boolean result = service.bjtz(tzhbjdm,user,xh);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		
		return null;

	}
	
	
	/**
	 * 
	 * @����: ȡ��������Ա״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-24 ����10:34:21
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
	
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-ɾ��ѧ��VALUES��{values}")
	public ActionForward delCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		
		String values = request.getParameter("values");
		User user = getUser(request);
		

		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
				: MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @����:������Ա������¼����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-14 ����03:43:11
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
		
		CpmdService service = new CpmdService();
		CpmdModel model = (CpmdModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String,String>> resultList = service.getTzjlList(model,user);//��ѯ�����м�¼������ҳ

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
	 * @����: ѡ�����Ӳ���ѧ��ҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����11:34:12
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
	public ActionForward viewAddCpxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getAddCpxsList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setAllNjXyZyBjList(request);
		request.setAttribute("path", "pj_cpmd_zjcpxs.do");
		return mapping.findForward("viewAddCpxsList");
	}
	
	
	
	/**
	 * 
	 * @����: ����ѧ��ѡ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-17 ����02:03:06
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
	 
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
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
		
		String gotoPath = request.getParameter("goto");
		String path = "pj_cpmd_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	
	
	/**
	 * 
	 * @����: ��������Ա��һ���༶��������һ���༶s
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-25 ����07:06:39
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
	 
	public ActionForward updateCpbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		String bjdm = request.getParameter("bjdm");
		String ids = request.getParameter("ids");
		
		User user = getUser(request);
		
		 if (!StringUtil.isNull(bjdm)){
			 
			boolean result = service.bjtzs(bjdm,user,ids);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		
		return null;

	}
	
	
	
}
