package com.zfsoft.xgxt.gygl.gyjlxxwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyjlxxwhAction extends SuperAction{
	private GyjlxxwhService service = new GyjlxxwhService();
	private static final String url = "gygl_gyjlglnew_gyjlxxwh_new.do";
	/**
	 * ��Ԣ������Ϣά��
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxwhForm model = (GyjlxxwhForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh_new.do");
		FormModleCommon.commonRequestSet(request);		
		return mapping.findForward("gyjlxxwhList");
		
	}
	

	
	/**
	 * ���ӹ�Ԣ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣά��-����PK:{xh},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		GyjlxxglService jlxxService = new GyjlxxglService();
		List<HashMap<String, String>> jldlList= service.getJldlList(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", Base.getDqxqmc());
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("jldlList", jldlList);
		request.setAttribute("jllbList", jlxxService.getJllbList(jldlList.get(0).get("jldldm"), request));
		request.setAttribute("username", user.getRealName());
		return mapping.findForward("gyjlxxwhZj");
	}
	/**
	 * �޸Ĺ�Ԣ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣά��-�޸�PK:{xh},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		if("update".equals(myForm.getType())){
			if(service.isExists(myForm)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_WJXX_EXIST));
				return null;
			}
			myForm.setGyjllbdldm(myForm.getJldldm());//model�ֶκ����ݿ���ֶβ�һ�£��ܿ�~~~~~
			myForm.setGyjllbdm(myForm.getJllbdm());
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> wjxxMap = service.getOneRsWjxx(myForm);
		request.setAttribute("rs", wjxxMap);
		
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		//����ѧ��ѧ���б�
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlxxwhXg");
	}
	
	/**
	 * ɾ����Ԣ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣά��-ɾ��PK:{values}")
	public ActionForward gyjlSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����:�鿴��Ԣ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-3 ����04:06:10
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
	public ActionForward gyjlxxwhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		HashMap<String,String> wjxxMap = service.getOneRsWjxx(myForm);
		request.setAttribute("rs", wjxxMap);
		request.setAttribute("rsArrList", service.getWjxxList(myForm));//��ʷΥ����Ϣ
		return mapping.findForward("gyjlxxwhCk");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣά��:XH:{xh},XN:{xn},XQ:{xq},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward saveWjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		User user =getUser(request);
		myForm.setCzr(user.getUserName());
		String objStr = request.getParameter("objStr");
		List<GyjlxxwhForm> wjxxList = JsonUtil.jsonArrToList(objStr,	GyjlxxwhForm.class);
		boolean result = service.saveWjxx(myForm,wjxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��ѯΥ��ѧ��List
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-12-3 ����11:48:38
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
	public ActionForward getWjxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxwhForm model = (GyjlxxwhForm) form;
		String cxzd = request.getParameter("cxzd");//��ѯ�ֶ�
		String sftq = request.getParameter("sftq");//�Ƿ�ͬ����
		String qsxx = request.getParameter("qsxx");
		String xhArr = request.getParameter("xhArr");//����ӵ�ѧ��
		List<HashMap<String,String>> wjxsList = service.getWjxsList(model,cxzd,qsxx,sftq,xhArr);
		JSONArray dataList = JSONArray.fromObject(wjxsList);
		response.getWriter().print(dataList);
		return null;
	}
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyjlxxwhForm model = (GyjlxxwhForm) form;
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

	
	
}
