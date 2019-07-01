package com.zfsoft.xgxt.xszz.xszzbjpy.jgcx;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jglr.JglrService;

public class JgcxAction extends SuperAction {
	
	private static final String ZZXM = "zzxm";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_xszzbjpy_jgcx.do";
	
	/**
	 * ��ѯ�����༶��������ѯ
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
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
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
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		if(!StringUtil.isNull(Base.currXq)){
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_xszzbjpy_jgcx.do";
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
		String path = "xszz_xszzbjpy_jgcx.do?method=jgcxView";
		request.setAttribute("path", path);
		request.setAttribute("rs", StringUtils.formatData(model));
		JglrService jglrService = new JglrService();
		HashMap<String,String> xsxxMap = jglrService.queryXsxx(model.getSqr());
		request.setAttribute("xsxxMap", xsxxMap);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxView");
	}
	/**
	 * �ύ�����༶������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ������-�����༶�������-�༶��������ѯ-�ύVALUES:{values}")
	public ActionForward jgcxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// ������Ϣ
		XszzSqshForm xszzbjpyForm = new XszzSqshForm();
		xszzbjpyForm.setGuid(myForm.getSqid());
		XszzSqshService xszzbjpyService = new XszzSqshService();
		XszzSqshForm xszzbjpyModel = xszzbjpyService.getModel(xszzbjpyForm);
		if(SAVE.equals(myForm.getType())){
			boolean result = service.jgcxTj(myForm, xszzbjpyModel, user);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xszzbjpyModel.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//�����༶���������ʾ����
		mkxxList = bdpzService.getBdpz(ZZXM);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZZXM);
		//���������϶���¼
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(xszzbjpyModel.getXh()));
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(xszzbjpyModel));
		List<HashMap<String,String>> pyjgList = new OptionUtil().getOptions(OptionUtil.PYJG);
		request.setAttribute("pyjgList", pyjgList);
		
		JgcxForm model = service.getModel(myForm);
		if(model == null){
			model = new JgcxForm();
		}
		request.setAttribute("jgcxForm", StringUtils.formatData(model));
		HashMap<String,String> bjpyxzdbMap = service.queryBjpyxzdb(user.getUserName());
		request.setAttribute("bjpyxzdbMap", StringUtils.formatData(bjpyxzdbMap));
		return mapping.findForward("jgcxTj");
	}
	
	/**
	 * ���������༶������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ������-�����༶�������-�༶��������ѯ-����VALUES:{values}")
	public ActionForward jgcxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// ������Ϣ
		XszzSqshForm xszzbjpyForm = new XszzSqshForm();
		xszzbjpyForm.setGuid(myForm.getSqid());
		XszzSqshService xszzbjpyService = new XszzSqshService();
		XszzSqshForm xszzbjpyModel = xszzbjpyService.getModel(xszzbjpyForm);
		boolean result = service.jgcxCx(xszzbjpyModel);
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
