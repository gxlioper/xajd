/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-19 ����09:42:28 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.gyglry.GyglryService;
import xsgzgl.gygl.qsgl.QsglService;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ���ҵ�ʦά��
 * @���ߣ� ��С��[����:1036]
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-19 ����09:42:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdswhAction extends SuperAction {
	
	private static final String url = "gygl_qsdswhgl.do";
	
	/**
	 * 
	 * @����:��Ԣ�����ѯ�б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����10:41:15
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
	public ActionForward qsdswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_qsdswhgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qsdswhManage");
	}
	
	/**
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����04:15:05
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
	public ActionForward qsdswhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService qsglService = new QsglService();
		//������Ϣ
		request.setAttribute("ldList", qsglService.getLdList());
		return mapping.findForward("qsdswhAdd");
	}
	
	/**
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����04:15:05
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
	public ActionForward qsdswhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh)){

			HashMap<String ,String> data = service.getQsdsxx(model);
			request.setAttribute("qsdsxx", data);
			GyglryService gyglryService = new GyglryService();
			HashMap<String ,String> qszInfo = gyglryService.getQszInfo(lddm, qsh);
			request.setAttribute("qszInfo", xgxt.utils.String.StringUtils.formatData(qszInfo));
		}
		return mapping.findForward("qsdswhUpdate");
	}
	
	
	/**
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����04:15:05
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
	@SystemLog(description="���ʹ�Ԣ����-���ҵ�ʦ����-���ҵ�ʦά��-����LDDM:{lddm},QSH:{qsh},ZGH:{zgh}")
	public ActionForward qsdswhAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		String zgh = model.getZgh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh) && StringUtils.isNotBlank(zgh)){
			boolean isSuccess = service.saveQsdsxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
		
	
	/**
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����04:15:05
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
	public ActionForward qsdsIsExist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		boolean isExist = service.isExistLddm(model);
		if(isExist) {
			String messageKey = MessageKey.GYGL_QSDSWH_FALL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
	
	/**
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-19 ����04:15:05
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
	@SystemLog(description="���ʹ�Ԣ����-���ҵ�ʦ����-���ҵ�ʦά��-�޸�LDDM:{lddm},QSH:{qsh},ZGH:{zgh}")
	public ActionForward qsdswhUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		String zgh = model.getZgh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh) && StringUtils.isNotBlank(zgh)){
			boolean isSuccess = service.saveQsdsxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����05:08:29
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
	@SystemLog(description="���ʹ�Ԣ����-���ҵ�ʦ����-���ҵ�ʦά��-ɾ��VALUES:{pks}")
	public ActionForward qsdswhDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhService service  = new QsdswhService();
		
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split("@@"));
			}
			service.deleteDsdsxxPl(pkList);
			String messageKey =MessageKey.SYS_DEL_SUCCESS;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	/** 
	 * ��ѯ������Ϣ
	 */
	@SystemAuth(url = url)
	public ActionForward qsdswhQsxxView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		QsdswhService service  = new QsdswhService();
		if(Globals.XXDM_WZDX.equalsIgnoreCase(Base.xxdm)){
			List<HashMap<String, String>> rsList = service.getxsxx(lddm, qsh);
			request.setAttribute("rsList", rsList);
			request.setAttribute("qsh", qsh);
			return mapping.findForward("qsdswhQsxxViewForWzdx");
		}else {
			List<String[]> rsList=service.getQsxxList(lddm, qsh);
			request.setAttribute("rsList", rsList);
			request.setAttribute("topTr", service.getQsxxTopTr());
			
			FormModleCommon.commonRequestSet(request);
					
			return mapping.findForward("qsdswhQsxxView");
		}
		
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-21 ����05:13:15
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
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
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
	
	/**
	 * @����:��ʦ���˵���
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
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
	public ActionForward exportDskh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("��ʦ����".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.exportDskh(model, user, response.getOutputStream());
		return null;
	}
	
}
