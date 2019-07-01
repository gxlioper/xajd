/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-3 ����02:46:01 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

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
import xsgzgl.rwgl.rwtw.RwglRwtwService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.rwdjsqcssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-3 ����02:46:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjsqAction extends SuperAction<RwdjsqForm, RwdjsqService> {
	private static final String RCSWRCXW = "rcswqjgl";
	private List<HashMap<String,String>> jbxxList = null;
	private RwdjsqService service = new RwdjsqService();
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-3 ����03:14:40
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rwdjbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "rwdjsqbase.do");
		FormModleCommon.commonRequestSet(request);
		// Ĭ�ϵ�ǰѧ��ѧ��
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		//���뿪�أ�
		CsszService jcszService = new CsszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		boolean notcfbz = service.checkIsNotExist(user.getUserName());
		request.setAttribute("notcfbz", notcfbz+"");
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ɾ�������¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-3 ����05:39:36
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
	public ActionForward delSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
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
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-3 ����05:41:43
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType()) || "savesubmit".equals(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			/**
			 * ��֤�ظ���
			 */
			if(!service.checkIsNotExist(myForm.getXh())){
				String  message = "��ѧ����������ǼǼ�¼����ȷ�ϣ�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			super.resetToken(request);
			boolean result = service.saveJg(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			if (!SAVE.equalsIgnoreCase(myForm.getType())) {
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		String path = "rwdjsq.do?method=add";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//����;��
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		this.saveToken(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����08:48:59
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		myForm = service.getModel(myForm);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		String path = "rwdjsq.do?method=showView";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data",myForm);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����08:51:10
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
		RwdjsqForm model = (RwdjsqForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
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
	 * 
	 * @����:�ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����09:14:39
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		String value = request.getParameter("values");
		myForm.setRwdjid(value);
		RwdjsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����09:15:11
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqbh = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqbh, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			RwdjsqForm model = new RwdjsqForm();
			model.setRwdjid(sqbh);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqbh)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
    /**
     * �����޸Ľ��
     */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType()) || "savesubmit".equalsIgnoreCase(myForm.getType())) {
			boolean result = service.saveUpdateJg(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			if (!SAVE.equalsIgnoreCase(myForm.getType())) {
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		RwdjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		//ѧ��������Ϣ��ʾ����
		String path = "rwdjsq.do?method=update";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//����;��
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		return mapping.findForward("edit");
	}
}
