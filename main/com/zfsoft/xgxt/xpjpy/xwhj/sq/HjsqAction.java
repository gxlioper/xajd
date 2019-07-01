/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-21 ����03:48:43 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
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
import com.zfsoft.xgxt.xpjpy.xwhj.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj.DmwhJxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ������ 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-21 ����03:48:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjsqAction extends SuperAction<HjsqForm, HjsqService> {
	
	private HjsqService service = new HjsqService();
	private CsszService csszService = new CsszService();
	private static final String HJSQ = "hjsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HJSQ);
	}
	private static final String url = "pjpy_hjgl_sq.do";
	
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����04:59:44
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
	public ActionForward hjsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
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
		
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "pjpy_hjgl_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hjsqList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����10:40:42
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
	public ActionForward addHjSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xpj_hjsq.do?method=addHjSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		
		return mapping.findForward("addHjSq");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����03:09:11
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
	public ActionForward editHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm myForm = (HjsqForm) form;
		HjsqForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		request.setAttribute("jxdjdm", model.getJxdjdm());
		request.setAttribute("jxmcdm", model.getJxmcdm());
		request.setAttribute("je", model.getJe());
		String path = "xpj_hjsq.do?method=editHjsq";
		request.setAttribute("path", path);
		
		return mapping.findForward("editHjsq");
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(��������)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����02:03:21
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
	public ActionForward getJxmcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		JSONArray json = JSONArray.fromObject(service.getJxmcList(model.getJxlbdm(), model.getJsfs(), model.getJxdjdm())); 
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(���)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:22:57
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
	public ActionForward getJe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		JSONArray json = JSONArray.fromObject(service.getJe(model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm())); 
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:45:29
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
	public ActionForward saveHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		boolean result = false;
	    User user = getUser(request);
	    String messageKey = "";
 		if(model.getType().equals("save") || model.getType().equals("submit")){
 			boolean isExist = service.isExistByHjsq(model,SAVE);
 			if(!isExist){
 				result = service.saveSqjg(model, user);
 			}else {
 				messageKey = "��ѧ����ǰ����������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
 			}
		}else if(model.getType().equals("update") || model.getType().equals("updatesubmit")){
			boolean isExist = service.isExistByHjsq(model,UPDATE);
			if(!isExist){
				result = service.saveSqjgUpdate(model, user);
			}else {
				messageKey = "��ѧ����ǰ����������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			}
		}
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����05:40:05
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
	public ActionForward cancelHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			HjsqForm model = new HjsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
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
	 * 
	 * @����: �ύ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����05:47:01
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
	public ActionForward submitHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		String values = request.getParameter("values");
		model.setSqid(values);
		boolean result = service.submitHjsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����10:52:14
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
	public ActionForward delHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	 * @����: �鿴
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����03:54:05
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
	public ActionForward viewHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		request.setAttribute("jbxxList", jbxxList);
		HjsqForm viewForm = service.getModel(model.getSqid());
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		
		return mapping.findForward("viewHjsq");		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����03:59:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
	
}
