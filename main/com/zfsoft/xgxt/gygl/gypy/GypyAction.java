package com.zfsoft.xgxt.gygl.gypy;

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-20 ����04:49:21 
 */

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.qsgl.QsglService;

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
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�· [���ţ�982]
 * @ʱ�䣺 2013-8-20 ����04:49:21
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GypyAction extends SuperAction {
	
	
	
	/**
	 * 
	 * @����:��Ԣ�����б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		BeanUtils.copyProperties(myForm, request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path",service.getGjcxPath(myForm));//��Ӧ�߼���ѯ��Ӧpath
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypylb");
	}
	
	
	public ActionForward yxfdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		myForm.setPylx(GypyService._YXFDY);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path",service.getGjcxPath(myForm));//��Ӧ�߼���ѯ��Ӧpath
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxfdylb");
	}
	
	
	public ActionForward yxxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		myForm.setPylx(GypyService._YXXS);
		request.setAttribute("path",service.getGjcxPath(myForm));//��Ӧ�߼���ѯ��Ӧpath
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա���ݷ�Χ��������
			myForm.setOtherFilter(searchTjByGyfdy);
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxxslb");
	}
	/**
	 * 
	 * @����:����ɾ����Ԣ����
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��������-ɾ��VALUES:{values};" +
			"���ʹ�Ԣ����-��Ԣ����-���㸨��Ա-ɾ��VALUES:{values};" +
			"���ʹ�Ԣ����-��Ԣ����-����ѧ��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delete(values.split(","));
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
	 * @����:�޸���������
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��������-�޸�PK:{gypyid},PYLY:{pyly};" +
			"���ʹ�Ԣ����-��Ԣ����-���㸨��Ա-�޸�{gypyid},PYLY:{pyly};" +
			"���ʹ�Ԣ����-��Ԣ����-����ѧ��-�޸�{gypyid},PYLY:{pyly}")		
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm myForm = (GypyForm) form;
		String gypyid=request.getParameter("gypyid");
		myForm.setGypyid(gypyid);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("data", service.query(myForm));
		String gotopath=service.getForwordPath(myForm,3);
		//������Ϣ
		//QsglService lds = new QsglService();
		//request.setAttribute("ldList", lds.getLdList());
		//ѧԺ ѧ��
		//FormModleCommon.setNdXnXqList(request);
		return mapping.findForward(gotopath);
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��������-����LDDM:{lddm},QSH:{qsh},XN:{xn},XQDM:{xqdm},PYLY:{pyly};" +
			"���ʹ�Ԣ����-��Ԣ����-���㸨��Ա-����GLDM:{gldm},XN:{xn},XQDM:{xqdm},PYLY:{pyly};" +
			"���ʹ�Ԣ����-��Ԣ����-����ѧ��-����XH:{xh},XN:{xn},XQDM:{xqdm},PYLY:{pyly}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm myForm = (GypyForm) form;
		if(StringUtils.isNull(myForm.getXn())&&StringUtils.isNull(myForm.getXqdm())){
			myForm.setXn(Base.currXn);
			myForm.setXqdm(Base.currXq);
		}
		myForm.setPylx(request.getParameter("pylx"));
		//��ȡ�Ƿ�ѡ������ʦ
		String zgh=request.getParameter("zgh");
		if (!StringUtil.isNull(zgh)){
			myForm.setGldm(zgh);
			//����f����Ա������Ϣ
			FdyjtffService fdyservice = new FdyjtffService();
			HashMap<String,String> jbxx = fdyservice.getFdyjbxx(zgh);
			request.setAttribute("jbxx", jbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѡ�񸨵�Ա ��ת��ַ
		String path = "gypy.do?method=add";
		request.setAttribute("path", path);
		//������Ϣ
		QsglService lds = new QsglService();
		request.setAttribute("ldList", lds.getLdList());
		//ѧԺ ѧ��
		FormModleCommon.setNdXnXqList(request);
		String gotopath=service.getForwordPath(myForm, 2);
		return mapping.findForward(gotopath);
	}
	/**
	 * 
	 * @����:�鿴��ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-21 ����05:44:54
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
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		String gypyid=request.getParameter("gypyid");
		GypyForm myForm = (GypyForm) form;
		myForm.setGypyid(gypyid);	
		request.setAttribute("data", service.query(myForm));
		String gotopath=service.getForwordPath(myForm,5);

		return mapping.findForward(gotopath);
	}
	
	
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm model = (GypyForm) form;
		model.setPylx(request.getParameter("pylx"));
		
		//��ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//��ȡ��������
		List<HashMap<String,String>> resultList=service.exportData(model);
		
		//��������
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * ajax����������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		GypyService service = new GypyService();
		Map<String, String> map = service.getMaxQsh(lddm, ch);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @����:�������ҵ绰
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����08:53:11
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
	public ActionForward loadQsxxdh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		String qsh = request.getParameter("qsh");

		GypyService service = new GypyService();
		Map<String, String> map = service.getQsxx(lddm, ch, qsh);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @����:����ѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����08:52:56
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
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		GypyService service = new GypyService();
		Map<String, String> map = service.getXsxx(xh);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-8-23 ����08:52:28
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
	public ActionForward checkData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm model = (GypyForm) form;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message",service.checkSoleData(model));
		String json = JSONObject.fromObject(map).toString();
		response.getWriter().write(json);
		return null;
	}
}
