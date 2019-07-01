/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:24:40 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;
import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:24:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhAction extends SuperAction<LxmdwhForm, LxmdwhService> {
	LxmdwhService service = new LxmdwhService();
	private String CQSXJQLX = "cqsxjqlx";
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����02:09:17
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
	public ActionForward getMdwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxmdwhForm model = (LxmdwhForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_rcsw_cqsx_jqlx_lxmdwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ɾ������ά��������˴������κο���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:04:35
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
	public ActionForward delWhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			String czr = getUser(request).getUserName();
			int num = service.deleteLxmd(ids,czr);
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
	 * @����: ��У����ά���鿴����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:05:48
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
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxmdwhForm myForm = (LxmdwhForm)form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm, user);
		

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
	
	/**
	 * 
	 * @����:�鿴ά�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:20:35
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
	public ActionForward ckMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxmdwhForm myForm = (LxmdwhForm) form;
		LxmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CQSXJQLX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", service.getXmmcMap(model.getXmid()));
		request.setAttribute("rs", model);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����:�޸�ά�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:20:35
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
	public ActionForward xgMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxmdwhForm myForm = (LxmdwhForm) form;
		if("save".equals(myForm.getType())){
			String czr = getUser(request).getUserName();
			boolean rs = service.updateLxmd(myForm,czr);
			String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		LxmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CQSXJQLX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", service.getXmmcMap(myForm.getXmid()));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����: ��������ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:23:25
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
	public ActionForward plMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		String xmid = request.getParameter("xmid");
		//�ж�xmid�Ƿ�Ϊ��
		List<HashMap<String, String>> xmmcList = service.getXmmcList();
		HashMap<String, String> xmmcMap = null;
		if(StringUtils.isNotNull(xmid)){
			xmmcMap = service.getXmmcMap(xmid);
		}
		request.setAttribute("xmmcMap", xmmcMap);
		request.setAttribute("xmmcList", xmmcList);
		request.setAttribute("xmid", xmid);
		return mapping.findForward("plwh");
	}
	
	/**
	 * 
	 * @����: ������������ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����11:08:30
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
	public ActionForward savePlMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] xhs = request.getParameterValues("xh");
		LxmdwhForm myForm = (LxmdwhForm) form;
		String message = "";
		if(null == xhs || xhs.length == 0 || StringUtils.isNull(xhs[0])){
			message = "�޿�ά����ѧ������ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//��֤�����Ƿ����ظ�
		boolean rs = service.checkIfCanSave(xhs, myForm.getXmid());
		if(!rs){
			message = "����Ŀ������ά����ѧ������ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String czr = getUser(request).getUserName();
		rs = service.savePlwh(myForm, xhs,czr); 
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ�����ѧ��List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-28 ����01:42:35
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
	public ActionForward getCanAddUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxmdwhForm model = (LxmdwhForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			String xhs = request.getParameter("xhs");
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCanAddStuList(model, user, xhs);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jqlx_lxmdwh.do?method=getCanAddUserList";
		request.setAttribute("path", path);
		request.setAttribute("xhs", request.getParameter("xhs"));
		request.setAttribute("xmid", request.getParameter("xmid"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getstu");
	}
}
