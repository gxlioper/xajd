package com.zfsoft.xgxt.pjpy.hjsq.sq;

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
import com.zfsoft.xgxt.pjpy.hjsq.cssz.CsszForm;
import com.zfsoft.xgxt.pjpy.hjsq.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
public class HjsqAction extends SuperAction<HjsqForm, HjsqService> {
	private static final String url = "pjpy_jxsq.do";
	private HjsqService service = new HjsqService();
	private static final String JXSQ = "jxsq";
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-8 ����10:33:44
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
	public ActionForward getJxsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		CsszForm kgModel = new CsszService().getModel();
		boolean sqkg = (kgModel == null || StringUtils.isNull(kgModel.getSplc())) ? false :true;
		request.setAttribute("sqkg", sqkg);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ��ѯ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-8 ����03:25:15
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
	public ActionForward searchForHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm hjForm = (HjsqForm)form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		hjForm.setSearchModel(searchModel);  
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(hjForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-8 ����05:42:25
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zpForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=add";
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-8 ����05:42:25
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		HjsqForm zpModel = service.getModel(zpForm.getId());
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm,StringUtils.formatData(zpModel) );
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("xn",zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-8 ����05:42:25
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		boolean rs = true;
		try {
			rs = service.save(zpForm);
		}catch (SystemException e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-9 ����09:54:55
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
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		HjsqForm zpModel = service.getModel(zpForm);
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm, zpModel);
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=view";
		request.setAttribute("xn", zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		request.setAttribute("path", path);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @������ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-9 ����10:01:31
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
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-9 ����10:03:59
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
		String ywid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		boolean rs = service.cancelSq(ywid, lcid);
		if (rs) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			HjsqForm myForm = new HjsqForm();
			myForm.setId(ywid);
			if (Integer.valueOf(shlcdao.getExistTh(ywid)) > 0) {
				myForm.setShzt(Constants.YW_YTH);
			} else {
				myForm.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-9 ����02:24:47
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
		String value = request.getParameter("values");
		HjsqForm model = service.getModel(value);
		User user = getUser(request);
		boolean result = service.submitBusiness(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-9 ����02:31:19
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
		
		HjsqForm model = (HjsqForm) form;

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
