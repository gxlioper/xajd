package com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx;

import java.io.File;
import java.util.HashMap;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyService;

public class JgcxAction extends SuperAction {
	
	private static final String KNSRD = "knsrd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_knsrdbjpy_jgcx.do";
	
	/**
	 * ��ѯ�༶��������ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		String xh = user.getUserName();
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
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "��������δ��ʼ��������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		HashMap<String,String> bjpyxzdbMap = service.queryBjpyxzdb(xh);
		String userType = user.getUserType();
		boolean isStuFlag = "stu".equalsIgnoreCase(userType) && bjpyxzdbMap.get("xh") == null;
		if(isStuFlag){
			String msg = "��ģ��ֻ�����ʦ���༶����С�������ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if("stu".equalsIgnoreCase(userType) && "0".equals(bjpyxzdbMap.get("tjzt"))){
			String msg = "�༶����С����δ�ύ����ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		JglrService jglrService = new JglrService();
		HashMap<String,String> xsxxMap = jglrService.queryXsxx(xh);
		request.setAttribute("xsxxMap", xsxxMap);
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
		if(!StringUtil.isNull(jcszModel.getXq())){
			searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_knsrdbjpy_jgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxManage");
	}
	/**
	 * �鿴�༶������
	 */
	@SystemAuth(url = url)
	public ActionForward jgcxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.jgcxView(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xszz_knsrdbjpy_jgcx.do?method=jgcxView";
		request.setAttribute("path", path);
		request.setAttribute("rs", StringUtils.formatData(model));
		JglrService jglrService = new JglrService();
		HashMap<String,String> xsxxMap = jglrService.queryXsxx(model.getSqr());
		request.setAttribute("xsxxMap", xsxxMap);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxView");
	}
	/**
	 * �ύ�༶������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ������-�������϶�-�༶��������ѯ-�ύVALUES:{values}")
	public ActionForward jgcxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// ������Ϣ
		KnsrdbjpyForm knsrdbjpyForm = new KnsrdbjpyForm();
		knsrdbjpyForm.setGuid(myForm.getSqid());
		KnsrdbjpyService knsrdbjpyService = new KnsrdbjpyService();
		KnsrdbjpyForm knsrdbjpyModel = knsrdbjpyService.getModel(knsrdbjpyForm);
		if(SAVE.equals(myForm.getType())){
			boolean result = service.jgcxTj(myForm, knsrdbjpyModel, user);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(knsrdbjpyModel.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		// ��������
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszModel.getSqsftxdc());
		
		//�������϶���ʾ����
		mkxxList = bdpzService.getBdpz(KNSRD);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		//���������϶���¼
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(knsrdbjpyModel.getXh()));
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", knsrdbjpyModel);
		List<HashMap<String,String>> pyjgList = new OptionUtil().getOptions(OptionUtil.PYJG);
		request.setAttribute("pyjgList", pyjgList);
		
		JgcxForm model = service.getModel(myForm);
		if(model == null){
			model = new JgcxForm();
		}
		request.setAttribute("jgcxForm", model);
		HashMap<String,String> bjpyxzdbMap = service.queryBjpyxzdb(user.getUserName());
		request.setAttribute("bjpyxzdbMap", StringUtils.formatData(bjpyxzdbMap));
		return mapping.findForward("jgcxTj");
	}
	
	/**
	 * �����༶������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ������-�������϶�-�༶��������ѯ-����VALUES:{values}")
	public ActionForward jgcxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// ������Ϣ
		KnsrdbjpyForm knsrdbjpyForm = new KnsrdbjpyForm();
		knsrdbjpyForm.setGuid(myForm.getSqid());
		KnsrdbjpyService knsrdbjpyService = new KnsrdbjpyService();
		KnsrdbjpyForm knsrdbjpyModel = knsrdbjpyService.getModel(knsrdbjpyForm);
		boolean result = service.jgcxCx(knsrdbjpyModel);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();

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
