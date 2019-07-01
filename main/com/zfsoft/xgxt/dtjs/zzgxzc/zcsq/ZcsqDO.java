/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-8 ����10:08:12 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

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
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
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
import com.zfsoft.xgxt.dtjs.zzgxzc.jcsz.JcszDao;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-8 ����10:08:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcsqDO extends SuperAction<ZcsqForm, ZcsqSERVICE> {
	ZcsqSERVICE service = new ZcsqSERVICE();
	private final String ZCSQ ="zcsq";
	
	private static final String url_js = "dtjs_dzzgxsq_js.do";//dtjs_dzzgxsq_js.do ��ʦ�����б�
	private static final String url_xs = "dtjs_dzzgxsq_xs.do";//dtjs_dzzgxsq_xs.do ѧ�������б�

	/**
	 * 
	 * @����: �����ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����11:23:18
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
	@SystemAuth(url = url_js)
	public ActionForward dzzGxJsSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcsqForm model = (ZcsqForm)form;
		User user = getUser(request);
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
		String path = "dtjs_dzzgxsq_js.do";
		request.setAttribute("path", path);
		JcszDao jcsz = new JcszDao();
		String sqkg = StringUtils.isNotNull(jcsz.getSqKg()) ? jcsz.getSqKg():"0";
		request.setAttribute("sqkg", sqkg);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jscx");
	}
	
	/**
	 * 
	 * @����: ����֯��ϵת������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����04:37:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward zcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm model = (ZcsqForm)form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",service.getDzbList());
		String path = "dzzgxsq.do?method=zcSq";
		request.setAttribute("path", path);
		return mapping.findForward("sq");
	}
	
	/**
	 * ����[����(�ύ)���޸�(�ύ)]
	 * @���ߣ�yxy[���ţ�1206]
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		String type = myForm.getType();
		boolean rs = true;
		User user = getUser(request);
		/**
		 * ��������ӣ���Ҫ�ж��ظ���
		 */
		if("save".equals(type) || "savesubmit".equals(type)){
			rs = service.IsNotExist(myForm);
			if(!rs){
				String message = "��ѧ��������д��¼����ȷ�ϣ�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (type.indexOf("submit") != -1) {
			messageKey = rs ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:24:02
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
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		ZcsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> rs = service.ckZcsq(myForm.getXh());
		request.setAttribute("rs", rs);
		return mapping.findForward("cksq");
	}
	
	/**
	 * 
	 * @����: �޸�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:26:35
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
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		ZcsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",service.getDzbList());
		String path = "dzzgxsq.do?method=xgsq";
		request.setAttribute("path", path);
		return mapping.findForward("xgsq");
	}
	
	/**
	 * 
	 * @����: ѧ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:28:52
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
	@SystemAuth(url = url_xs)
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = getUser(request);
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("message", "��ҳ��ֻ��ѧ����Ա�û����ʡ�");
			return mapping.findForward("error");
		}
		String xh = user.getUserName();
		boolean isdy = service.IsDy(xh);
		if(!isdy){
			request.setAttribute("message", "��ҳ��ֻ��ѧ����Ա�û����ʡ�");
			return mapping.findForward("error");
		}else{
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			// ѧ��������Ϣ��ʾ����gzkhKhjgXx
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
			request.setAttribute("jbxxList", jbxxList);
			JcszDao jcsz = new JcszDao();
			String sqkg = StringUtils.isNotNull(jcsz.getSqKg()) ? jcsz.getSqKg():"0";
			request.setAttribute("sqkg", sqkg);
			HashMap<String, String> rs = service.ckZcsq(xh);
			request.setAttribute("rs", rs);
			request.setAttribute("xh", xh);
			request.setAttribute("dzbList",service.getDzbList());
			if(rs == null || rs.isEmpty()){
				request.setAttribute("saveType", "save");
				request.setAttribute("submitType", "savesubmit");
			}else{
				request.setAttribute("saveType", "update");
				request.setAttribute("submitType", "updatesubmit");
			}
			
		}
		return mapping.findForward("xssq");
	}
	
	/**
	 * 
	 * @����: ɾ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:31:02
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ�Ǽ�-ɾ��VALUES:{values}")
	public ActionForward delSq(ActionMapping mapping, ActionForm form,
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
	 * @����:�ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:42:55
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ�Ǽ�-�ύVALUES:{values}")
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcsqForm myForm = (ZcsqForm)form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		ZcsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:48:57
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ�Ǽ�-����VALUES:{values}")
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			ZcsqForm model = new ZcsqForm();
			model.setSqid(sqid);
			model.setSplcid(lcid);
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
	 * @����: ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:01:20
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
		ZcsqForm myForm = (ZcsqForm)form;
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// ��ѯ�����м�¼������ҳ
		
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
