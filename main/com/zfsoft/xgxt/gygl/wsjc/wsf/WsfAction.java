/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-4 ����10:18:29 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.wsjc.jcrc.JcrcModel;
import com.zfsoft.xgxt.gygl.wsjc.jcrc.JcrcService;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmService;

/** 
 * @�๦������: ������¼��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-4 ����10:18:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsfAction extends SuperAction<WsfModel, WsfService> {

	private static final String url = "gygl_wsjc_wsflr.do";
	
	/**�ճ��������¼�� �б�**/
	@SystemAuth(url = url)
	public ActionForward fslrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_wsflr.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fslrList");
	}
	
	
	/**�ճ��������¼�� �б�**/
	@SystemAuth(url = url)
	public ActionForward getFslrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getRclrList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**������¼��**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wsflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfModel model = (WsfModel) form;
		WsfService service = getService();
		
		JcxmService jcxmService = new JcxmService();
		List<HashMap<String, String>>  rcxmList = jcxmService.getRcxmList(model.getRcid(),model.getJcdx());
		request.setAttribute("rcxmList", rcxmList);
		
		JcrcService jcrcService = new JcrcService();
		JcrcModel jcrcInfo = jcrcService.getModel(model.getRcid());
		request.setAttribute("jcrcInfo", StringUtils.formatData(jcrcInfo));
		
		List<String> djList = service.getWsfdjList();
		List<String> xjList = service.getWsfxjList();
		
		request.setAttribute("djList", djList);
		request.setAttribute("xjList", xjList);
		
		request.setAttribute("path", "gygl_wsjc_fslr.do");
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_sfrz(new String[]{"��"});
		request.setAttribute("searchTj", searchModel);
//		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wsflr");
	}
	
	
	
	/**������¼���ѯ�б�**/
	@SystemAuth(url = url)
	public ActionForward getWsflrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getFslrList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	

	/**����������***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		model.setLrr(user.getUserName());
		boolean isSuccess = service.runInsert(model);
		response.getWriter().print(isSuccess);
		
		return null;
	}
	
	
	/**�����ֵ���***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward importWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("jsonStr", request.getParameter("jsonStr"));
		return mapping.findForward("importWsf");
	}
	
	
	
	/***���ص���ģ��**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(model, user);
		FileUtil.outputExcel(response, file);
		
		return null;
	}
	
	
	
	/**�����ֵ��뱣��**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveImportWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		try {
			File file = service.importWsf(model, user);
			
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
		
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		} catch (Exception e) {
			request.setAttribute("result", false);
			request.setAttribute("message", "����ʧ�ܣ�");
		}
		
		return importWsf(mapping, model, request, response);
	}
	
	
	/**�����ֲ�ѯ***/
	@SystemAuth(url = "gygl_wsjc_wsfcx.do")
	public ActionForward fscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		request.setAttribute("path", "gygl_wsjc_wsfcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fscxList");
	}
	
	
	/**�����ֲ�ѯ***/
	@SystemAuth(url = url)
	public ActionForward getFscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/***����������****/
	@SystemAuth(url = "gygl_wsjc_wsfcx.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportFscxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsfService service = getService();
		WsfModel model = (WsfModel) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model,user);// ��ѯ�����м�¼������ҳ

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
