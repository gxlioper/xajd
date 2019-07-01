/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:42:15 
 */
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

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
import xgxt.utils.date.DateUtils;

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

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:42:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZzdyBtffAction extends SuperAction<ZzdyBtffForm, ZzdyBtffService> {
	private static final String url = "xszz_zzdy_zzbtff.do";
	private ZzdyBtffService service = new ZzdyBtffService();
	private static List<HashMap<String, String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	private static final String YFF="1";

	@SystemAuth(url = url)
	public ActionForward getBtffList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyBtffForm model = (ZzdyBtffForm) form;
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
		return mapping.findForward("getBtffList");
	}


	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-������������-����-ffyf:{ffyf}")
	public ActionForward btff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyBtffForm myForm = (ZzdyBtffForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.btff(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("isHave",service.isHave(myForm));
		myForm.setFfyf(DateUtils.getCurrYearAndMonth());
		List<HashMap<String, String>> ffyfList = service.getFfyfList();
		request.setAttribute("ffyfList", ffyfList);
		return mapping.findForward("ayByff");
	}

	public ActionForward checkFfzt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyBtffForm myForm = (ZzdyBtffForm) form;
			boolean flag=service.isHave(myForm);
			response.getWriter().print(getJsonMessage(String.valueOf(flag)));
			return null;
		}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyBtffForm model = (ZzdyBtffForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ
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
	
	public ActionForward viewBtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyBtffForm myForm = (ZzdyBtffForm) form;
		ZzdyBtffForm model = service.getModel(myForm);
		ZzdyBtffService btffService = new ZzdyBtffService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("ffjlList", btffService.getFfjlList(model.getXh(), model.getXmdm()));
		jbxxList = bdpzService.getJbxxpz("zzxm");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewBtff");
	}

	

}
