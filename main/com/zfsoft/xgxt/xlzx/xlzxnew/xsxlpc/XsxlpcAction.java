package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class XsxlpcAction extends SuperAction<XsxlpcForm,XsxlpcService> {
	private final static String XSPCJG ="xspcjg";
	private XsxlpcService service = new  XsxlpcService();
	private static final String url = "xg_xlzxnew_xsxlpc.do";
	// ѧ��������Ϣ��ʾ����
	private final static BdpzService bdpzService = new BdpzService();
	private static List<HashMap<String, String>>  jbxxList = null;
	static{
		 jbxxList = bdpzService.getJbxxpz(XSPCJG);
	}
	/**
	 * 
	 * @���������������ղ���ҳ��תAction
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����02:27:06
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
	public ActionForward getXsxlpcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ��ѯҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����02:29:23
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
	public ActionForward searchForXsxlpcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
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
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����04:08:19
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
	@SystemLog(description = "������������ѯ�����������ղ⡪���������ղ����-����")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(model.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����04:10:04
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
	@SystemLog(description = "������������ѯ�����������ղ⡪���������ղ����-�޸�")
	public ActionForward updateJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		XsxlpcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(myForm.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=updateJg";
		request.setAttribute("path", path);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����: ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����04:25:18
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
	public ActionForward saveJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		boolean rs = true;
		User user = getUser(request);
		model.setJlr(user.getUserName());
		if(StringUtils.isNotNull(model.getId())){
			rs = service.runUpdate(model);
		}else{
			if(!service.checkIsNotExists(model.getXh())){
				String message = "��ѧ�������ղ����У������ظ���д��";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			rs = service.runInsert(model);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :  MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����07:22:16
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
		
		XsxlpcForm model = (XsxlpcForm) form;

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
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-9 ����10:24:24
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		XsxlpcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(myForm.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=ck";
		request.setAttribute("path", path);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @����: ɾ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-9 ����04:27:23
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
	@SystemLog(description = "������������ѯ�����������ղ⡪���������ղ����-ɾ��id:{values}")
	public ActionForward delJg(ActionMapping mapping, ActionForm form,
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
	 * @����:��ע��ȡ����ע��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-9 ����04:50:55
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
	@SystemLog(description = "������������ѯ�����������ղ⡪���������ղ����-�Ƿ��ע����id:{values}")
	public ActionForward sz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("ids");
		String sfgz = request.getParameter("sfgz");
		String message = "��ע�ɹ���";
		if("0".equals(sfgz)){
			message = "ȡ����ע�ɹ���";
		}
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean rs = service.sz(ids, sfgz);
			message = rs ? message : "ȡ��ʧ�ܣ�";
			response.getWriter().print(getJsonMessage(message));
		}else{
			message = "����ѡ���¼���ٽ��в�����";
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
}
