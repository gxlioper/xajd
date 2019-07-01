/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-9 ����10:44:26 
 */
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysq;


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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.hjjygl.cssz.HjjyCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-5-9 ����10:44:26
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class HjjySqAction extends SuperAction<HjjySqForm, HjjySqService> {
	private HjjySqService service = new HjjySqService();
	private final String RCSW="rcswrcxw";
	private HjjyCsszService csszService = new HjjyCsszService();
	private static final String url = "rcsw_hjjy_jysq.do";
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:06:53
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
	public ActionForward getHjjySqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HjjySqForm model = (HjjySqForm) form;
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
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getHjjySqList");
	}
	/**
	 * 
	 * @����:���ӻ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:07:14
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
	public ActionForward addHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm model = (HjjySqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(Base.getXqmcForXqdm(Base.currXq));
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> jyyyList = csszService.getJyyyList();//����ԭ���б�
		request.setAttribute("jyyyList", jyyyList);
		String path = "hjjyJysq.do?method=addHjjySq";
		request.setAttribute("path", path);
		request.setAttribute("model", model);
		this.saveToken(request);
		return mapping.findForward("addHjjySq");
	}
	/**
	 * 
	 * @����:�޸Ļ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:07:49
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
	public ActionForward editHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm myForm = (HjjySqForm) form;
		User user = getUser(request);
		HjjySqForm model = service.getModel(myForm);
		model.setXqmc(Base.getXqmcForXqdm(model.getXq()));
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> jyyyList = csszService.getJyyyList();//����ԭ���б�
		request.setAttribute("jyyyList", jyyyList);
		String path = "hjjyJysq.do?method=editHjjySq";
		request.setAttribute("path", path);
		request.setAttribute("model", model);
		return mapping.findForward("editHjjySq");
	}
	/**
	 * 
	 * @�����鿴������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:08:01
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
	public ActionForward viewHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm model = (HjjySqForm) form;
		HashMap<String, String> jyxxMap = service.getJyxx(model.getSqid());
		if (!StringUtil.isNull(jyxxMap.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jyxxMap.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", StringUtils.formatData(jyxxMap));
		return mapping.findForward("viewHjjySq");

	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:08:25
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
	@SystemLog(description = "�����ճ�����-�������ù���-��������-����sqid:{sqid},xh:{xh}")
	public ActionForward saveHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		HjjySqForm model = (HjjySqForm) form;
		if (service.isHaveGhJl(model)) {// ������
			String message = MessageUtil.getText(MessageKey.RCSW_HJJY_WGH);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		super.resetToken(request);
		boolean result = service.saveHjjySq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�޸ı��滧����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:08:55
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
	@SuppressWarnings("unchecked")
	@SystemLog(description = "�����ճ�����-�������ù���-��������-�޸�sqid:{sqid},XQ:{xn},XQ:{xh}")
	public ActionForward saveEditHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm model = (HjjySqForm) form;
		String message = null;
		if (service.isHaveGhJl(model)) {// ������
			message = MessageUtil.getText(MessageKey.RCSW_HJJY_WGH);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.saveEditHjjySq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ��������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:09:11
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
	@SystemLog(description = "�����ճ�����-�������ù���-��������-ɾ��VALUES:{values}")
	public ActionForward delHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @����:����������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:09:23
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
	public ActionForward cancelHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			HjjySqForm model = new HjjySqForm();
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
	 * @����:�ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����02:09:39
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
	@SystemLog(description = "�����ճ�����-�������ù���-��������-�ύ:{values}")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitHjjySq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm model = (HjjySqForm) form;
		String values = request.getParameter("values");
		model.setSqid(values);
		boolean result = service.submitHjjySq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjjySqForm model = (HjjySqForm) form;

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
