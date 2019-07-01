package xsgzgl.gyjc.ccjgcx;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gyjc.jcjglr.JcjglrService;
import xsgzgl.gyjc.jcsd.PfbzForm;
import xsgzgl.gyjc.jcsd.PfbzService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

public class CcjgAction extends SuperAction<CcjgForm, CcjgService> {
	private CcjgService service = new  CcjgService();
	private PfbzService pfbzServie = new PfbzService();
	private static final String url = "gyjc_ccjgcx.do";
	private static final String JCLX_XX = "xx";//������ͣ�ѧУ
	
	/**
	 * 
	 * @����:�������ѯ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-17 ����10:42:14
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
	public ActionForward ccjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm model = (CcjgForm) form;
		User user = getUser(request);
		request.getRequestURL();
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
		request.setAttribute("searchTj", searchModel);
		if("cx".equals(model.getFlag())){
			request.setAttribute("path", "xg_gyjc_ccjgcx.do?flag=cx");
		}else{
			request.setAttribute("path", "xg_gyjc_ccjgcx.do?flag=cc");
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", url);
		request.setAttribute("flag", model.getFlag());
		return mapping.findForward("ccjgList");
	}
	/**
	 * 
	 * @����:�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-17 ����05:31:27
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
	public ActionForward addCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception { 
		this.saveToken(request);
		return mapping.findForward("addCcjg");
	}
	
	/**
	 * 
	 * @����:�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-17 ����06:08:25
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
	public ActionForward showQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcjgForm model = (CcjgForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getQsxxPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "gyjc_ccjgcx.do?method=showQsxx";
		request.setAttribute("path", path);
        return mapping.findForward("showQsxx");	
	}
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-18 ����04:21:12
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
	public ActionForward getJclxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcjgForm model = (CcjgForm) form;
		PfbzForm bzForm = new PfbzForm();
		bzForm.setJjlx(model.getJjlx());
		bzForm.setJs(model.getJs());
		bzForm.setXydm(model.getXydm());
		List<HashMap<String,String>> jclxList = pfbzServie.getXmSelectList(bzForm);
		JSONArray dataList = JSONArray.fromObject(jclxList);
		response.getWriter().print(dataList);
		return null;
	}
	@SystemLog(description = "��������-�����-���������-:ѧԺ{xydm},lddm:{lddm},qsh:{qsh}")
	public ActionForward saveCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm model = (CcjgForm) form;
		if (service.isHaveCcjg(model)) {
			String messageKey = MessageUtil.getText(MessageKey.XG_GYJC_CCJG_REPEAT, new String[] {});
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		User user =getUser(request);
		model.setTjr(user.getUserName());
		model.setFids(request.getParameterValues("fid"));
		model.setIndexs(request.getParameterValues("index"));
		boolean result = service.editCcjg(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�޸ĳ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-20 ����04:59:28
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
	public ActionForward editCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		CcjgForm model = service.getModel(myForm);
		request.setAttribute("model", model);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//��ѯ�������Ϣ
		List<HashMap<String,String>> ccjgList = service.getCcjgList(model);
		request.setAttribute("ccjgList", ccjgList);
		request.setAttribute("yscwjList", new JcjglrService().getYscfjxx(model.getFjid()));
		getJcxxList(model,request);//���ü�����б���Ϣ
		return mapping.findForward("editCcjg");
	}
	/**
	 * 
	 * @����:������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-21 ����10:12:58
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
	public ActionForward viewCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		CcjgForm model = service.getModel(myForm);
		request.setAttribute("model", model);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//��ѯ�������Ϣ
		List<HashMap<String,String>> ccjgList = service.getCcjgList(model);
		request.setAttribute("ccjgList", ccjgList);
		request.setAttribute("djList", new JcjglrService().getFxdjcxForView(null, model.getXydm(), model.getQsh(), model.getLddm(), "cc", model.getJcrq()));
		getJcxxList(model,request);//���ü�����б���Ϣ
		return mapping.findForward("viewCcjg");
	}
	private void getJcxxList(CcjgForm model,HttpServletRequest request){
		PfbzForm t = new PfbzForm();
		t.setJs(JCLX_XX);
		t.setJjlx(OptionUtil.JCLX_WSJC);
		t.setXydm(model.getXydm());
		List<HashMap<String,String>> wsjcList = pfbzServie.getXmSelectList(t);//����������
		t.setJjlx(OptionUtil.JCLX_AQJC);
		List<HashMap<String,String>> aqjcList = pfbzServie.getXmSelectList(t);//��ȫ������
		t.setJjlx(OptionUtil.JCLX_JLJC);
		List<HashMap<String,String>> jljcList = pfbzServie.getXmSelectList(t);//���ɼ�����
		request.setAttribute("wsjcList", wsjcList);
		request.setAttribute("aqjcList", aqjcList);
		request.setAttribute("jljcList", jljcList);
		
	}
	/**
	 * 
	 * @����:ɾ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-20 ����04:44:57
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
	@SystemLog(description = "��������-�����-���������-ɾ��VALUES:{values}")
	public ActionForward delCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			service.delCcmxbz(ids);
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	public ActionForward exportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		exportData(myForm, request, response);
		return null;
	}
	/*
	 * �Զ��嵼������ʵ��
	 */
	private void exportData(CcjgForm model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				CcjgForm fmtModel = (CcjgForm)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				return service.getPageList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}
	
	
}
