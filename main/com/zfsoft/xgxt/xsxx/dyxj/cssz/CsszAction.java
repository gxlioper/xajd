/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-23 ����08:52:56 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.cssz;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import common.newp.StringUtil;

/** 
 * @�๦������: ����С��-��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-23 ����08:52:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<CsszModel, CsszService> {

	
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "xsxx_dyxj_cssz.do";
	
	
	/***����С��-��������***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		CsszModel csszForm = (CsszModel) form;
		CsszService service = getService();
		if (QUERY.equalsIgnoreCase(csszForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			csszForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(csszForm, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszModel model = service.getModel();
	
		if (model != null){
			
			if (StringUtil.isNull(csszForm.getSqkg())){
				csszForm.setSqkg(KGZT_CLOSE);
			}
			
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "xsxx_dyxj_cssz.do");
		request.setAttribute("sqxnList", Base.getXnndList());
		request.setAttribute("sqxqList", Base.getXqList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����11:25:07
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
		
		CsszModel model = (CsszModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		CsszService service = getService();

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
