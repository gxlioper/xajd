/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-27 ����09:08:18 
 */
package com.zfsoft.xgxt.xtwh.bjdl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �༶��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-27 ����09:08:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BjdlAction extends SuperAction {

	private final static String ACTION_SCDL_ALL = "szdl_all";
	
	private static final String url = "xg_bjdl.do";

	/**
	 * 
	 * @����: �༶��Ϣ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-27 ����10:20:11
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
	public ActionForward viewBjxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();

		if (QUERY.equals(model.getType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xg_bjdl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("viewBjxxList");
	}

	/**
	 * 
	 * @����: ���ð༶����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����03:04:19
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
	public ActionForward szBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
		if (ACTION_SCDL_ALL.equals(model.getSzType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);

			List<HashMap<String, String>> resultList = service.getAllList(
					model, user); // ȫ����ѯ

			StringBuilder ids = new StringBuilder();

			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				HashMap<String, String> hashMap = (HashMap<String, String>) iterator
						.next();
				String bjdm = hashMap.get("bjdm");
				ids.append(bjdm);
				if (iterator.hasNext())
					ids.append(",");

			}

			model.setBjdm(ids.toString());

		}

		List<HashMap<String, String>> bjxxList = service.getBjxxList(model);

		request.setAttribute("bjxxList", bjxxList);
		return mapping.findForward("szBjdl");
	}

	/**
	 * 
	 * @����: ����༶����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����04:06:52
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
	@SystemLog(description="������������-��������-�༶��������-����BJDM��{bjdm}")
	public ActionForward saveBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();

		String[] bjdm = request.getParameterValues("bjdm");

		boolean result = service.szBjdl(model, bjdm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * 
	 * @����:ȡ���༶��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-19 ����09:53:19
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
	@SystemLog(description="������������-��������-�༶��������ȡ��-VALUES��{values}")
	public ActionForward delBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		String bjdm = request.getParameter("values");
		String bjdms[] = StringUtils.isNotNull(bjdm)?bjdms = bjdm.split(","):null;
		boolean result = service.qxBjdl(bjdms,model);

		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}
	
	/**
	 * @����: ���ӵ�������
	 * @���ߣ�MengWei[���ţ�1186]
	 * @���ڣ�2016-8-18 ����04:43:12
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
		BjdlModel model = (BjdlModel) form;
		BjdlService service = new BjdlService();
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
