/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-13 ����01:57:28 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.utils.StringUtil;
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
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq.RysqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_�ҵ�����_�������
 * @���ߣ�  Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-13 ����01:57:28 
 * @�汾��V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgAction extends SuperAction {
	private static final String url = "xpjpy_wdpj_pjjg.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	
	/**
	 * @����: ��������б�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-13 ����03:58:14
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
	public ActionForward getPjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjjgService service = new PjjgService();
		PjjgForm model = (PjjgForm) form;
		if(QUERY.equals(model.getType())){
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			/*��ѯ*/
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*Ĭ�ϸ߼���ѯ����-���������е�ѧ��*/
		XmwhService xmmwService = new XmwhService();
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {xmmwService.getCsszMap().get("xn")});
		request.setAttribute("searchTj", searchModel);
		String path = "xpjpy_wdpj_pjjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listPjjg");
	}
	
	/**
	 * @����: ����ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-13 ����05:26:15
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
	public ActionForward addPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			/*��ȡ��ѧ�����һ��������Ϣ*/
			HashMap<String,String> latestSqxx = service.getLatestSqxx(model.getXh());
			request.setAttribute("latestSqxx",latestSqxx);
		}
		String path = "xpjpy_pjjg.do?method=addPjjg";
		request.setAttribute("path", path);
		/*ѧ��list*/
		request.setAttribute("xnList", Base.getXnndList());
		/*��Ŀ����list*/
		List<HashMap<String, String>> xmlx = service.getXmlx();
		request.setAttribute("xmlxList", xmlx);
		/*��Ŀ����list*/
		List<HashMap<String, String>> xmxz = service.getXmxz();
		request.setAttribute("xmxzList", xmxz);
		/*��ǰѧ��*/
		XmwhService xmmwService = new XmwhService();
		model.setXn(xmmwService.getCsszMap().get("xn"));
		/*��ǰʱ��*/
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		/*ѧ��������Ϣ��ʾ����*/
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("addPjjg");
	}
	
	/**
	 * @����: �����������ӱ���
	 * @���ߣ�  Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����11:27:26
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
	@SystemLog(description="������������-�ҵ�����-�������-����-XMMC:{xmmc},XH:{xh},XN:{xn}LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward saveFormAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		/*Ψһ���жϣ�ѧ�ţ�ѧ�꣬��Ŀ���Ʋ����ظ���*/
		boolean isExist = service.isExistByPjjgAdd(model);
		if (!isExist) {
			/*����ʱ�����û���*/
			model.setLrr(user.getUserName());
			/*����ʱ����������Դ(������Դ   0:���롢1:�������� 2:��̨����)*/
			model.setSjly("2");
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
		}
		return null;
	}
	
	/**
	 * @����: ɾ�����������¼���������ݲ�����ɾ���޸�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2013-8-8 ����10:11:03
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
	@SystemLog(description="������������-�ҵ�����-�������-ɾ��-VALUES:{values}")
	public ActionForward delPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgService service = new PjjgService();
		/*���id*/
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
	 * @����: ���ɡ�����ѧ���������ƺ�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-6 ����11:36:05
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
	public ActionForward scyxxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgService service = new PjjgService();
		boolean result = service.scyxxs();
		String message = result ? "��������ѧ���ɹ���" : "��������ѧ��ʧ�ܣ�";
		getJsonMessage(message);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @����: �޸�ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����01:44:41
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
	public ActionForward updatePjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		PjjgForm updateForm = service.getModel(model);
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "xpjpy_pjjg.do?method=updatePjjg";
		request.setAttribute("path", path);
		/*ѧ��list*/
		request.setAttribute("xnList", Base.getXnndList());
		/*��Ŀ����list*/
		List<HashMap<String, String>> xmlx = service.getXmlx();
		/*��Ŀ����list*/
		List<HashMap<String, String>> xmxz = service.getXmxz();
		request.setAttribute("xmlxList", xmlx);
		request.setAttribute("xmxzList", xmxz);
		/*����ѧ��������Ϣ*/
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("updatePjjg");
	}
	
	/**
	 * @����: �޸ı��淽��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����01:45:15
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
	@SystemLog(description="������������-�ҵ�����-�������-�޸�-XMMC:{xmmc},XH:{xh},XN:{xn}LXDM:{lxdm},XZDM:{xzdm},SQSJ:{sqsj}")
	public ActionForward saveFormUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		/*Ψһ���жϣ�ѧ�ţ�ѧ�꣬��Ŀ���Ʋ����ظ���*/
		boolean isExist = service.isExistByPjjgUpdate(model);
		if (!isExist) {
			/*����ʱ�����û���*/
			model.setLrr(user.getUserName());
			/*����ʱ����������Դ(������Դ   0:���롢1:�������� 2:��̨����)*/
			model.setSjly("2");
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
	
	/**
	 * @����: ���ѧ�ŵ����鿴
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-14 ����03:50:18
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
	public ActionForward viewPjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {
			/*����ѧ��������Ϣ*/
			HashMap<String, String> xsjbxx = service.getPjjgInfo(model.getXh(),model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*��ѯ���*/
			request.setAttribute("rs", StringUtils.formatData(service.getOnePjjgList(model.getId())));
			return mapping.findForward("viewPjjg");
		} else {
			return updatePjjg(mapping, form, request, response);
		}
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2013-8-14 ����03:41:50
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjjgForm model = (PjjgForm) form;
		PjjgService service = new PjjgService();

		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����: ����ѡ��ѧ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-22 ����11:03:44
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
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjjgForm model = (PjjgForm)form;
		PjjgService service = new PjjgService();
		if (QUERY.equals(model.getType())) {
			/*���ɸ߼���ѯ����*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			/*��ѯ*/
			List<HashMap<String, String>> resultList = service.getZjXs(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String sqlx = request.getParameter("sqlx");
		String path = "xpjpy_pjjg.do?method=showStudents";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath+"&sqlx="+sqlx);
		return mapping.findForward("showStudents");
	}
}
