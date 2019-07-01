/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-19 ����05:15:41 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxjg;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ͽ��������ģ��
 * @�๦������: ���������ѯ
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-8-25 ����06:50:56 
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class SxDaxxjgAction extends SuperAction<SxDaxxjgForm, SxDaxxjgService>{
	private final String RCSW="rcswrcxw";
	private SxDaxxjgService service = new SxDaxxjgService();
	private static final String url = "sxdaxxgljg.do";
	
	@SystemAuth(url = url)
	public ActionForward getjglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SxDaxxjgForm model = (SxDaxxjgForm) form;
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
			// Ĭ�ϸ߼���ѯ����(��ǰѧ��)
			SearchModel searchModel = new SearchModel();
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			request.setAttribute("searchTj", searchModel);
			request.setAttribute("path", url);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("getjglist");
	}
	
	
	/**
	 * @����:����
	 * @���ڣ�2016-8-25 ����06:51:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxjgService service=new SxDaxxjgService();
		SxDaxxjgForm model=(SxDaxxjgForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
		
	

	/**
	 * @����: ���ѧ�Ų鿴

	 */
	@SystemAuth(url = url)
	public ActionForward dajgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxjgService service = new SxDaxxjgService();
		SxDaxxjgForm model = (SxDaxxjgForm) form;

		SxDaxxjgForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("dajgView");
	}
		
	
	
	
}
