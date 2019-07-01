/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����04:22:10 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

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
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.kqgl.dmwh.KqlxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: ѧ�����ڹ���
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����04:22:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqglAction extends SuperAction {
	
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String KQGL = "kqgl";
	BdpzService bdpzService = new BdpzService();

	private static final String url = "rcsw_kqgl_kqgl.do";
	
	/**
	 * 
	 * @����:ѧ�����ڵǼǲ鿴
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:13:57
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
	public ActionForward viewKqdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();
		

		if (QUERY.equals(model.getType())){
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_kqgl_kqgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKqdjList");
		
	}
	
	
	/**
	 * 
	 * @����:����ѧ�����ڵǼ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:30:42
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
	@SystemLog(description="�����ճ�����-���ڹ���-ѧ�����ڹ���-����")
	public ActionForward addKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// ��ѧ�Ų�Ϊ�գ�����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// Ψһ���жϣ�ѧ�ţ�����ʱ�䣬�������ͣ�
			boolean isExist = service.isExistByKqdj(model);
			if (!isExist) {
				super.resetToken(request);
				model.setZjsj(DateUtils.getCurrTime());
				// ���ѧ�����ڵǼ�
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		// ��ǰѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		// ���������б�
		KqlxService kqlxService = new KqlxService();
		List<HashMap<String, String>> kqlxList = kqlxService.getKqlxList();
		request.setAttribute("kqlxList",kqlxList);
		
		String path = "rcsw_kqgl_xskqgl.do?method=addKqdj";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addKqdj");
	}
	
	
	/**
	 * 
	 * @����:�޸�ѧ�����ڵǼ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:34:37
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
	@SystemLog(description="�����ճ�����-���ڹ���-ѧ�����ڹ���-�޸�KQDJID:{kqdjid}")
	public ActionForward updateKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();
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

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�����ʱ�䣬�������ͣ�
			boolean isExist = service.isExistByKqdj(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		// ���������б�
		KqlxService kqlxService = new KqlxService();
		List<HashMap<String, String>> kqlxList = kqlxService.getKqlxList();
		request.setAttribute("kqlxList",kqlxList);

		String path = "rcsw_kqgl_xskqgl.do?method=updateKqdj";
		request.setAttribute("path", path);
		
		KqglForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("updateKqdj");
	}
	
	
	/**
	 * 
	 * @����:ɾ��ѧ�����ڵǼ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:37:49
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
	@SystemLog(description="�����ճ�����-���ڹ���-ѧ�����ڹ���-ɾ��VALUES:{values}")
	public ActionForward deleteKqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KqglService service = new KqglService();
		
		// ���id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @����:ѧ�����ڵǼǵ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:38:24
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
		
		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();

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
	 * @����:���ڵǼǽ�������鿴
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:39:19
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
	public ActionForward oneKqdjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KqglForm model = (KqglForm) form;
		KqglService service = new KqglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			// ��ѯ���
			request.setAttribute("rs", StringUtils.formatData(service.getOneKqdjList(model.getKqdjid())));

			return mapping.findForward("oneKqdjView");
		} else {
			return updateKqdj(mapping, form, request, response);
		}
	}
}
