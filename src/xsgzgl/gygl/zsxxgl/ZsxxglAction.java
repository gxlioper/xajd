package xsgzgl.gygl.zsxxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import common.Globals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.cwgl.CwglService;

public class ZsxxglAction extends SuperAction {

	private static final String url = "gyglnew_zsxxgl_zsxxgl.do";
	
	/**
	 * 
	 * @����:������У
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-16 ����08:54:22
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
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-ס����Ϣ����-{doType}������УLDDM:{lddm}")
	public ActionForward plLx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();
		User user = getUser(request);
		ZsxxglForm myForm = (ZsxxglForm) form;
		String doType = request.getParameter("doType");

		if ("pllx".equals(doType)) {
			String mess = service.pllx(myForm, user);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", mess);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		if (QUERY.equals(doType)) {
			List<HashMap<String, String>> list = service.getKtsList(myForm,
					user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("hjrs", service.getHjrs(myForm, user));
		// request.setAttribute("data", list);

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());

		return mapping.findForward("pllx");
	}

	/**
	 * ס����Ϣ����
	 */
	@SystemAuth(url = url)
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-ס����Ϣ����-��λ�Ե�PK:{primarykey_checkVal}")
	public ActionForward zsxxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		ZsxxglService service = new ZsxxglService();
		User user = getUser(request);

		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;

		if ("cwdd".equals(doType)) {// ��λ�Ե�
			HttpSession session = request.getSession();
			myForm.setCzr(session.getAttribute("userName").toString());// ���ò�����
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.cwdd(pk, myForm) ? "�Ե��ɹ���" : "�Ե�ʧ�ܣ�";

			request.setAttribute("message", message);
		}

		// ����Ǹ���Ա���жϴ�λ�Ƿ��ڿɲ���ʱ�䷶Χ
		request.setAttribute("rzyylist", service.getRzyyList());
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if (fdyqx || bzrqx) {
			boolean czkz = service.getCzkz();
			request.setAttribute("czkz", czkz);
		} else {
			request.setAttribute("czkz", true);
		}

		// ѧУ�û���ʾ[������У����]
		if (!fdyqx && !bzrqx && ("xx".equals(user.getUserType()) || "admin".equals(user.getUserType()))) {

			request.setAttribute("pllxtx", "true");
		}

		request.setAttribute("rs", service.queryCw(myForm, request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("num", service.getYrzrs(myForm, request));
		request.setAttribute("searchTjstr", service.getSearchTjstr(myForm,
				request));

		// write��titile
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		FormModleCommon.commonRequestSet(request);

		request.setAttribute("topTr", service.getTopTr("zsgl"));
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // ������ͼ

		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		FormModleCommon.commonRequestSet(request);
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl_ntzydx.do");
		}
		if (Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)) {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl_zjgmzy.do");
		} else {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl.do");
		}

		return mapping.findForward("zsxxglManage");
	}

	@SystemAuth(url = url)
	public ActionForward geExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();
		ZsxxglForm myForm = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");//��Ԣ����Ա���ݷ�Χ����
		File file = service.getExportData(myForm, request);
		//Ϊ���жϣ������ȡ��fileΪ�գ�������ض��򣬻ص���ҳ�棬��ֹҳ�汨����߿հ�
		if(file != null){
			FileUtil.outputExcel(response, file);
			return null;
		}else{
		   response.sendRedirect("gyglnew_zsxxgl_zsxxgl.do");
		}
		return null;
	}

	/**
	 * 
	 * @����:��λס�޹���λ�Ե�
	 * @���ߣ�dlq
	 * @���ڣ�2013-8-22 ����05:54:14
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
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-ס����Ϣ����-��λ�Ե�PK:{idList}")
	public ActionForward zsxxCwdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();

		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;

		String idList = request.getParameter("idList");
		idList.substring(0, idList.length() - 1);
		request.setAttribute("idList", idList);
		request.setAttribute("rzyylist", service.getRzyyList());
		if ("cwdd".equals(doType)) {// ��λ�Ե�
			HttpSession session = request.getSession();
			myForm.setCzr(session.getAttribute("userName").toString());// ���ò�����
			String[] pk = idList.split(",");
			String message = service.cwdd(pk, myForm) ? "�Ե��ɹ���" : "�Ե�ʧ�ܣ�";
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
			// request.setAttribute("message", message);
		}
		return mapping.findForward("zsxxCwdd");
	}

	/**
	 *ס����Ϣ�����Զ��嵼��
	 * 
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
	public ActionForward zsxxglExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZsxxglService service = new ZsxxglService();
		ZsxxglForm model = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String, String>> resultList = service.queryExportCw(model,
				request);
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
	 * ������ͷ��
	 */
	@SystemAuth(url = url)
	public ActionForward expCtk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		ZsxxglService service = new ZsxxglService();
		String path = servlet.getServletContext().getRealPath(
				"/print/gygl/gygl_exp_ctk.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expCtk((ZsxxglForm) form, request, response, path);
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rzyyZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if (UPDATE.equalsIgnoreCase(doType)) {
			String jldldm = request.getParameter("rzyydm");
			String jldlmc = request.getParameter("rzyymc");
			request.setAttribute("rzyydm", jldldm);
			request.setAttribute("rzyymc", jldlmc);
		}

		return mapping.findForward("rzyyZj");
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xucy[���ţ�754]
	 * @���ڣ�2013-8-29 ����09:22:45
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
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-ס����Ϣ����-{doType}ά��LDDM:{lddm};���ʹ�Ԣ����-ס�޹���-ס�޹������ά��-ά��{doType}-RZYYDM��{rzyydm},RZYYMC:{rzyymc}")
	public ActionForward zsxxgldmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ����
		request.setAttribute("path", "gyglnew_zsxxgl_zsgldmgl.do");
		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveRzyy(myForm, "add");
				// String message = flag ? "�޸ĳɹ�!":"�޸�ʧ��!";
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveRzyy(myForm, "update");
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.saveRzyy(myForm, "delete");
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getRzyyList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("rzyy"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsxxgldmManage");
	}
	/**
	 * @����: ��У������ѧ����Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-29 ����09:17:05
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
	public ActionForward pllxview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		String nj = request.getParameter("nj");
		request.setAttribute("nj", nj);
		String xydm = request.getParameter("xydm");
		request.setAttribute("xymc", service.getXymc(xydm));
		String zydm = request.getParameter("zydm");
		request.setAttribute("zymc", service.getZymc(zydm));
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//��ѯ
			List<HashMap<String,String>> resultList = service.getLxxsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("pllxview");
	}
	
	/**
	 * 
	 * @����: ѧ��Υ����ϸ��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-2-25 ����10:16:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public ActionForward wjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		String xh = request.getParameter("xh");
		request.setAttribute("xh", xh);
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXswjxx(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("xswjxxView");
	}
	
	/**
	 * @����:�޸ı�ע��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��29�� ����11:59:55
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
	public ActionForward updateBz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//����
		String pkValue = request.getParameter("pkValue");
		CwglService service = new CwglService();
		// ������ϸ��Ϣ
		request.setAttribute("rs", service.getCwForPk(pkValue));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("updateBz");
	}
	
	/**
	 * @����:�޸ı�ע��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��29�� ����12:00:17
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
	public ActionForward updateBzBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValues = request.getParameter("pkValues");
		request.setAttribute("pkValues", pkValues);
		return mapping.findForward("updateBzBatch");
	}
	
	/**
	 * @����:�������汸ע���޸�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��29�� ����1:57:09
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
	public ActionForward saveBzBatchForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValues = request.getParameter("pkValues");
		String bz = request.getParameter("bz");
		ZsxxglService service = new ZsxxglService();
		boolean result = service.saveBzBatchForUpdate(pkValues,bz);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-10-23 ����02:07:12
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
	public ActionForward qshr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		//���һ���
		boolean result = service.qshr(model.getLddm(),model.getQsh(),model.getCwh(),model.getXh());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 *ס����Ϣ�����Զ��嵼��
	 * 
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
	public ActionForward zsxxglExportDataForGsjt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZsxxglService service = new ZsxxglService();
		ZsxxglForm model = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String, String>> resultList = service.queryExportCwForGsjt(model,
				request);
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
