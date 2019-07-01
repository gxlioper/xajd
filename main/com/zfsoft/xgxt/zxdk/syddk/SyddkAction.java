/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-29 ����09:34:58 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:��ѧ����-��Դ�ش���
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-29 ����09:34:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SyddkAction extends SuperAction<SyddkModel, SyddkService> {

	private static final String ZXDK = "gjzxdk";
	
	private static final String url = "zxdk_syddm_dkwh.do";
	
	/**
	 * 
	 * @����: ������Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����09:38:47
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
	public ActionForward dkxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_syddm_dkwh.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dkxxList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:38:22
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
	public ActionForward getDkxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel model = (SyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @����: ���Ӵ�����Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����10:27:48
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkModel model = (SyddkModel) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		// �����б�
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("yhList", xsxxglService.getYhList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "zxdkSyddk.do?method=addDkxx";
		request.setAttribute("path", path);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("addDkxx_zjdx");
		}
		this.saveToken(request);
		return mapping.findForward("addDkxx");
	}
	
	
	/**
	 * 
	 * @����: ��Դ�ش���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����10:27:28
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel myForm = (SyddkModel) form;
		
		SyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		// �����б�
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("yhList", xsxxglService.getYhList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		List<HashMap<String,String>> dkxxList = service.getDkxxList(myForm.getId());
		request.setAttribute("dkxxList", dkxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("editDkxx_zjdx");
		}
		
		return mapping.findForward("editDkxx");
	}
	
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����04:04:41
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
	public ActionForward ckDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel myForm = (SyddkModel) form;
		
		SyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>> dkxxList = service.getDkxxList(myForm.getId());
		request.setAttribute("dkxxList", dkxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDkxx_zjdx");
		}
		
		return mapping.findForward("ckDkxx");
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.action.SuperAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-��Դ�ش���-������-ɾ��values:{ids}")
	public ActionForward delDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyddkService service = getService();
		String ids = request.getParameter("ids");
		
		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	@SystemAuth(url = url)
	public ActionForward dcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyddkService service = getService();
		SyddkModel model = (SyddkModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

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
	 * @����:save������д
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-13 ����09:04:11
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
	public ActionForward savePd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SyddkModel model = (SyddkModel) form;
		SyddkService service = new SyddkService();
		boolean isExist = service.getExists(model.getXh(), model.getXn());
		if(isExist) {
			response.getWriter().print(
					getJsonResult(MessageKey.ZXDK_SYDDK, false));		
		}else {		
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
}
