/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:02:12 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.cssz.CsszService;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:02:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmsbAction extends SuperAction<XmsbForm, XmsbService> {
	private XmsbService service = new XmsbService();
	private CsszService csszService = new CsszService();
	
	private static final String url = "sztz_xmsbgl_xmsb.do";

	/**
	 * 
	 * @����:��Ŀ�걨�б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-10 ����04:23:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getXmsbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
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
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "sztz_xmsbgl_xmsb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmsbList");
	}

	/**
	 * 
	 * @����:��Ŀ�걨����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����04:06:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		User user = getUser(request);
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setSfsljx("0");
		String path = "xmsbXmsb.do?method=addXmsb";
		request.setAttribute("path", path);
		request.setAttribute("sbr", user.getUserName());
		service.initParam(request, user);
		return mapping.findForward("addXmsb");
	}

	/**
	 * 
	 * @����:��Ŀ�걨�޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����04:07:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-20 ����04:27:40
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
	public ActionForward editXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm myForm = (XmsbForm) form;
		User user = getUser(request);
		XmsbForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		// ������Ŀ������Ϣ
		List<HashMap<String, String>> xmjxList = service.getXmjxList(myForm.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		String path = "xmsbXmsb.do?method=editXmsb";
		request.setAttribute("path", path);
		request.setAttribute("csms", model.getCsms());
		service.initParam(request, user);
		return mapping.findForward("editXmsb");
	}

	/**
	 * 
	 * @����:��Ŀ�걨�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����11:32:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward viewXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		HashMap<String, String> xmxxMap = service.getXmxx(model.getXmdm());
		request.setAttribute("rs", xmxxMap);
		// ������Ŀ������Ϣ
		List<HashMap<String, String>> xmjxList = service.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		// ������Ŀ����ѧԺ��Ϣ
		List<HashMap<String, String>> xyList = service.getCyxyListForView(model.getXmdm());
		request.setAttribute("xyList", xyList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("viewXmsb");

	}

	/**
	 * 
	 * @����:��Ŀ�걨����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����10:18:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	@SystemLog(description = "����������չ-��Ŀ�걨����-��Ŀ�걨-����XQ:{xn},XQ:{xq},XMMC:{xmmc},XMJBDM:{xmjbdm},SBBMDM:{sbbmdm},SSKMDM:{sskmdm},KCYRS:{kcyrs},XMKSSJ:{xmkssj},SBR:{sbr},LXDH:{lxdh},JCXF:{jcxf}")
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String objStr = request.getParameter("objStr");
		String[] cyxyArr = request.getParameterValues("cyxy");
		if (service.isHaveSbJl(model, "add")) {// ������
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<XmjxForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr, XmjxForm.class);
		}
		boolean result = service.saveXmsb(model, jxxxList,cyxyArr);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:��Ŀ�걨�޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����02:33:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	@SystemLog(description = "����������չ-��Ŀ�걨����-��Ŀ�걨-�޸�XMDM:{xmdm},XQ:{xn},XQ:{xq},XMMC:{xmmc},XMJBDM:{xmjbdm},SBBMDM:{sbbmdm},SSKMDM:{sskmdm},KCYRS:{kcyrs},XMKSSJ:{xmkssj},SBR:{sbr},LXDH:{lxdh},JCXF:{jcxf}")
	public ActionForward saveEditXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String objStr = request.getParameter("objStr");
		String[] cyxyArr = request.getParameterValues("cyxy");
		String message = null;
		if (service.isHaveSbJl(model, "update")) {// ������
			message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<XmjxForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr, XmjxForm.class);
		}
		boolean result = service.saveEditXmsb(model, jxxxList,cyxyArr);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����02:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����������չ-��Ŀ�걨����-��Ŀ�걨-ɾ��VALUES:{values}")
	public ActionForward delXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delPlXmjx(ids);
			}
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
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-10 ����03:03:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XmsbForm model = new XmsbForm();
			model.setXmdm(sqid);
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
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-13 ����10:26:05
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
	public ActionForward submitXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String values = request.getParameter("values");
		model.setXmdm(values);
		boolean result = service.submitXmsb(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���ݵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-9 ����11:18:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;

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

}
