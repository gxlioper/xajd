/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-15 ����03:08:15 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-15 ����03:08:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdxdAction extends SuperAction<HsdxdForm, HsdxdService> {
	HsdxdService service = new HsdxdService();
	private static final String FDWW = "fdwh";
	public ActionForward xdCsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
        User user = getUser(request);
        /**
         * �������ѧ���û�,ֱ��ת������ҳ��
         */
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "��ҳ��ֻ��ѧ���û����ʡ�");
			return mapping.findForward("error");
		}
		/**
		 * ��֤���������Ƿ񿪷�
		 */
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getXdKg())){
			request.setAttribute("message", "��������δ���š�");
			return mapping.findForward("error");
		}
		String num = service.getDkjgNum(user.getUserName());
		if (("0").equals(num)){
			request.setAttribute("message", "��δ�����������ѧ���");
			return mapping.findForward("error");
		}
		List<HashMap<String, String>> xdxxList = service.getXdxxMx(user.getUserName());
		HashMap<String, String> dkxMap = service.getDkxxMap(user.getUserName());
		request.setAttribute("xdxxList", xdxxList);
		request.setAttribute("dkxx", dkxMap);
		
		/**
		 * ���������¼
		 */
		request.setAttribute("xdsqList",service.getXdsqList(user.getUserName()));
		request.setAttribute("fkzh", service.getFdjeZh(user.getUserName()));
		String path = "zxdk_gjdk_xdsqnew.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdcsh");
	}
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-18 ����10:02:13
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
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String jgid = request.getParameter("jgid");
		boolean result = service.qxsq(jgid);
		String message = "";
		if(result){
			message = "ȡ���ɹ���";
		}else{
			message = "ȡ��ʧ�ܣ�";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����: �Ŵ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-21 ����11:06:42
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
	public ActionForward fdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		    HsdxdForm model = (HsdxdForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// ��ѯ
				HsdxdDao dao = new HsdxdDao();
				List<HashMap<String, String>> resultList = dao.getFdcxList(user, model);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// Ĭ�ϸ߼���ѯ����
			SearchModel searchModel = new SearchModel();
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			searchModel.setSearch_tj_xq(new String[] { Base.currXq });
			request.setAttribute("searchTj", searchModel);
			String path = "zxdk_gjdk_hsdfd.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("hsdfd");
	}
	
	/**
	 * 
	 * @����: �Ŵ�ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-21 ����01:53:07
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
	public ActionForward fdWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HsdxdForm model = service.getFdbModel(myForm.getId());
		BeanUtils.copyProperties(myForm, model);
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(FDWW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(myForm.getXq()));
		request.setAttribute("dkzhs", service.getDkzh(model.getHtbh()));
		request.setAttribute("xdjes", service.getFdjes(model.getId()));
		request.setAttribute("fkjes", model.getFkje());
		request.setAttribute("rs", model);
		return mapping.findForward("fdwh");
	}
	
	/**
	 * 
	 * @����: �Ŵ�ά������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-21 ����01:57:49
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
	public ActionForward saveFdwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		boolean rs = service.updateFdb(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �Ŵ�ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-21 ����01:53:07
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
	public ActionForward fdCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HsdxdForm model = service.getFdbModel(myForm.getId());
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(FDWW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		request.setAttribute("rs", model);
		return mapping.findForward("fdck");
	}
	
	/**
	 * 
	 * @����:�����Ŵ���¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:30:15
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
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HsdxdForm myForm = (HsdxdForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		myForm.getPages().setMaxRecord(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = new HsdxdDao().getFdcxList(user,myForm);
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
