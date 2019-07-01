package com.zfsoft.xgxt.rcsw.xsxwkh.pddj;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.rcsw.xsxwkh.cssz.XsxwCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class PddjAction extends SuperAction<PddjForm, PddjService> {
	private final String RCSW="rcswrcxw";
	private PddjService service = new PddjService();
	private static final String url = "xsxwkh_djpd.do";

	/**
	 * �ȼ������б�
	 */
	@SystemAuth(url = url)
	public ActionForward getPddjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		XsxwCsszService csszService = new XsxwCsszService();
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
		//��������ʾ��ѧ����
		if("stu".equals(user.getUserType())){
			RcxwjgService rcxwjgService = new RcxwjgService();
			boolean flag = rcxwjgService.getCffWarnStudent(user.getUserName());
			request.setAttribute("flag", flag);
		}
		// Ĭ�ϸ߼���ѯ����(��ǰѧ��)
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("csszForm", csszService.getModel());
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPddjList");
	}
	/**
	 * 
	 * @����:�����ȼ���������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-5 ����04:56:32
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
	@SystemLog(description = "����ѧ����Ϊ����-�ȼ�����-�ȼ�����-����XH:{xh}")
	public ActionForward pddjSingle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.savePddj(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> XsxwKhxxMap = service.getXsxwKhxx(model.getJbfid(), model.getXn());
		if (!StringUtil.isNull(XsxwKhxxMap.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(XsxwKhxxMap.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", service.getXsxwKhxx(model.getJbfid(), model.getXn()));
		request.setAttribute("jlfList", service.getJlfList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("cffList", service.getCffList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("fjfList", service.getFjfList(XsxwKhxxMap.get("xh"), model.getXn()));
		return mapping.findForward("pddjSingle");
	}
	public ActionForward pddjView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		HashMap<String,String> XsxwKhxxMap = service.getXsxwKhxx(model.getJbfid(), model.getXn());
		if (!StringUtil.isNull(XsxwKhxxMap.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(XsxwKhxxMap.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", service.getXsxwKhxx(model.getJbfid(), model.getXn()));
		request.setAttribute("jlfList", service.getJlfList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("cffList", service.getCffList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("fjfList", service.getFjfList(XsxwKhxxMap.get("xh"), model.getXn()));
		return mapping.findForward("pddjView");
	}
	/**
	 * 
	 * @����:���������ȼ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-5 ����05:00:40
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
	@SystemLog(description = "����ѧ����Ϊ����-�ȼ�����-�ȼ���������-����XH:{xh}��XN:{xn}")
	public ActionForward pddjPl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePddjPl(model);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("num", request.getParameter("num"));
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("pddjPl");
	}
	
	/**
	 * �ȼ���������
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PddjForm model = (PddjForm) form;
		PddjService service = new PddjService();

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
