/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-3 ����03:46:47 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-3 ����03:46:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsqjcxAction extends SuperAction<XsqjcxForm, XsqjcxService> {
	private XsqjcxService service = new  XsqjcxService();
	private static final String url = "zjly_xsqj_jscx.do";
	public ActionForward getQjcxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsqjcxForm model = (XsqjcxForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsqjcxForm model = (XsqjcxForm) form;

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
	
	public ActionForward Qjsqck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsqjcxForm model = (XsqjcxForm) form;
		if(null!=model){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> ckxx = service.Qjsqck(model);
		request.setAttribute("ckxx", ckxx);
		//��������ʷ�б�
		List<HashMap<String, String>> splslist =service.getSplsList(model);
		request.setAttribute("splslist", splslist);
		return mapping.findForward("ck");
	}
	//ѧ�������Ϣ��ѯ
	public ActionForward getQjcxList_tjcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsqjcxForm model = (XsqjcxForm) form;
		model.setFlag("tjcx");
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjcx");
	}
}
