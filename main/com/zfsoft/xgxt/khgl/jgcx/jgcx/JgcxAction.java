/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����03:12:17 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
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
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���˹���
 * @�๦������: ���˽��
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-18 ����03:12:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JgcxAction extends SuperAction {
	
	JgcxService service = new JgcxService();
	
	private static final String url = "khgl_jgcx.do";
	
	/**
	 * 
	 * @����:�����ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-18 ����03:25:56
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
	public ActionForward jgcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
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
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxList");
	}
	
	
	/**
	 * ��������Ŀ�����ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward xmjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.xmjgList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("model", model);
		request.setAttribute("xmInfo", StringUtils.formatData(service.getModel(model)));
		
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			//�߼���ѯpath
			request.setAttribute("path", "khgl_jgcx_ckJs.do");
			return mapping.findForward("xmjgJsList");
		}
		//�߼���ѯpath
		request.setAttribute("path", "khgl_jgcx_ckXs.do");
		return mapping.findForward("xmjgXsList");
	}
	
	/**
	 * ��������Ŀ�����ѯ���׶��������Ի��鿴��
	 */
	@SystemAuth(url = url)
	public ActionForward xmjgListOfSdty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.xmjgListOfSdty(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_jgcx.do";
		request.setAttribute("path", path);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("model", model);
		request.setAttribute("xmInfo", StringUtils.formatData(service.getModel(model)));
		request.setAttribute("pfzList", service.getPfzListByXmid(model.getXmid()));
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			//�߼���ѯpath
			request.setAttribute("path", "khgl_jgcx_ckJs.do");
			return mapping.findForward("xmjgJsListOfSdty");
		}
		//�߼���ѯpath
		request.setAttribute("path", "khgl_jgcx_ckXs.do");
		return mapping.findForward("xmjgXsListOfSdty");
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-19 ����04:54:10
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.xmjgList(model, user);// ��ѯ�����м�¼������ҳ
		
		
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
	
	@SystemAuth(url = url)
	public ActionForward exportConfigOfSdty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		String dclb = request.getParameter("dclb");
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.xmjgListOfSdty(model, user);// ��ѯ�����м�¼������ҳ
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if(dclb.equals("xs")){
			service.exportXsOfSdty(model,response.getOutputStream(),resultList);
		}else{
			service.exportJsOfSdty(model,response.getOutputStream(),resultList);
		}
		
		// ============= end ============

		return null;
	}
	
	/**
	 * 
	 * @����:���ֶ��󵼳�
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-6-26 ����04:43:57
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
	public ActionForward exportDfrData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getDfrList(model, user);// ��ѯ�����м�¼������ҳ
		
		
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
	 * ���ͳ��List
	 */
	@SystemAuth(url = url)
	public ActionForward dftjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		User user = getUser(request);
		
		KhpfService khpfService = new KhpfService();
		HashMap<String, String> ryInfo = khpfService.getRyInfo(model.getKhdxr());
		
		List<HashMap<String, String>> dftjList = service.dftjList(model, user);
		
		request.setAttribute("ryInfo", StringUtils.formatData(ryInfo));
		request.setAttribute("xmmc", dftjList.get(0).get("xmmc"));
		request.setAttribute("dftjList", dftjList);
		request.setAttribute("khbid", request.getParameter("khbid"));
		
		return mapping.findForward("dftjList");
	}
	
	
	public ActionForward lscpjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		SearchModel searchModel=new SearchModel();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			searchModel = comService.getSearchModel(request);
			User user = getUser(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.lscpjgList(
					model, user);
		
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
	    searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "khgl_lscpjgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lscpjgList");
	}
	
	/**
	 * ����˲鿴
	 */
	@SystemAuth(url = url)
	public ActionForward dfrList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);

			List<HashMap<String,String>> resultList = service.getDfrList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "khgljgcx.do?method=dfrListJs";
		
		if(KhpfService.YHLX_XS.equals(model.getPflx())||
				(KhpfService.YHLX_BR.equals(model.getPflx())&&KhpfService.YHLX_XS.equals(model.getKhlx()))){
			path = "khgljgcx.do?method=dfrListXs";
		}
		
		request.setAttribute("path", path);
		request.setAttribute("khbid", request.getParameter("khbid"));
		return mapping.findForward("dfrList");
	}
	
	/**
	 * @����:�㽭��ҵ��ʦѧԺ���Ի���ѧ���԰����ο��˷������ܴ�ӡ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��6��5�� ����3:08:20
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
	public ActionForward xsdbzrhzDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm jgcxForm = (JgcxForm) form;
		//��ȡcfids
		String khdxrsStr = request.getParameter("khdxrs");
		String [] khdxrs = null;
		
		if(StringUtils.isNotNull(khdxrsStr)){
			khdxrs = khdxrsStr.split(",");
		}
		//����ְ�����ж��Ƿ��ǰ�����
		List<String> bzrList = new LinkedList<String>();
		boolean isBzr = false;
		for(String khdxr:khdxrs){
			if(Fdypd.isBzr(khdxr, null)){
				bzrList.add(khdxr);
				isBzr = true;
			}
		}
		if(!isBzr){
			//��ʾ��ѡ��Ŀ��˶�������ǰ����Σ�
			String messageKey = MessageKey.KHGL_JGCX_KHDX_NOTBZR;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
			throw new SystemException(messageKey);
//			return null;
		}
		
		//������Ŀid�������Σ�ְ���ţ�List��ѯѧ���԰����εĻ��ܴ�������б�
		/*�������һ�����⣺����һ���༶�ж�������Σ�������������Զ�������εģ���ʾ��ֻ�а༶������ȷ�������Σ�
		 * ��ѧУ������û����ʾ�����Σ��������ݲ����ǡ�
		 */
		List<HashMap<String,String>> xsdbzrhzList = service.getXsdbzrhzList(jgcxForm.getXmid(),bzrList);
		//����word�ļ�����񰴰༶����
		//һ���༶һ����� ����һ��������һ�����
		File file = service.getXsdbzrhzFile(xsdbzrhzList);
		
		FileUtil.outputWord(response,file);
		return null;
	}

}
