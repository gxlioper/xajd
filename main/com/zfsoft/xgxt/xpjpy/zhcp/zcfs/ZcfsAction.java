/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-23 ����01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cpxz.CpxzService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲����
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-23 ����01:36:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcfsAction extends SuperAction {
	
	private static final String url = "pj_jgcx.do";
	
	/**
	 * 
	 * @����: �Ƿ��а༶�ύ���۲����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����01:50:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward isHaveSubmitClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		boolean isHaveSubmitClass = service.isHaveSubmitClass(csszModel.getXn(), csszModel.getXq());
		response.getWriter().print(isHaveSubmitClass);
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: �༶�۲�����б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����10:29:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward viewBjzcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZcbjList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		/*xg_pjpy_new_cspzb szyf = 1 �����ύ�ˣ��ύ״̬*/
	    request.setAttribute("szyf", csszService.getCsz("szyf"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjzcList");
	}
	
	
	
	/**
	 * 
	 * @����: �۲����¼��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:22:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@Deprecated
	public ActionForward editZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//��Ĭ����Ŀ�ͽӿ�ͬ����Ŀ�������������¼��
			service.initDefaultZcfs(csszModel.getXn(), csszModel.getXq(), zcfsForm.getId());
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		
		//�༶��Ϣ
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		
		//����û������Ŀ���۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap",bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("editZcfs");
	}
	

	/**
	 * 
	 * @����: �۲��������¼��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:22:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZcfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			//��Ĭ����Ŀ�ͽӿ�ͬ����Ŀ�������������¼��
			service.initDefaultZcfs(zcfsForm, user);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�༶��Ϣ
		List<HashMap<String, String>> bjList = service.getBjxxByIds(zcfsForm, user);
		
		//����û������Ŀ���۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdms(bjList,zcfsForm, user);
		
		//��һ��߼���ѯ����
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		List<HashMap<String, String>> djList = service.getDj();// �ȼ�
		
		if("10264".equalsIgnoreCase(Base.xxdm)) {
			service.getIntefaceDataAll(zcfsForm, null, user);
		}
		
		request.setAttribute("djList", JSONArray.fromObject(djList));
		request.setAttribute("bjList",bjList);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		request.setAttribute("path", "pj_zcflr.do");
		FormModleCommon.commonRequestSet(request);
		//����¼���۲��
		if("1".equals(szyf)){
			request.setAttribute("szyf", szyf);
			request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
			// �����ύ��ʱֻ֧�ֵ����༶�ύ
			request.setAttribute("yftjztlist", service.getYfTjzt(bjList.get(0).get("bjdm"), csszModel.getXn(), csszModel.getXq()));
			request.setAttribute("bjdm", bjList.get(0).get("bjdm"));
			return mapping.findForward("editZcfsOfYf");
		}
		
		return mapping.findForward("editZcfs");
	}
	
	/**
	 * 
	 * @����: �����۲����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����03:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����ѧ��XH��{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveZcfs(zcfsForm, user);
		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ����Ƿ���ύ�۲��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����06:26:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		boolean isCanSubmit = service.isCanSubmit(zcfsForm);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	/**
	 * 
	 * @����: ѭ�����õ������ ,�Դ��������������пտ��Կ����Ż�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����06:26:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward plCheckIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		if(StringUtils.isBlank(zcfsForm.getId())){
			
			System.out.println("id Ϊ��");
			throw new SystemException(MessageKey.SYS_SUBMIT_FAIL);
			
		}else{
			
			String[] mess = service.isNotCanSubmit(zcfsForm, user);
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: �ύ�༶�۲��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����06:53:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-�ύ�۲��ID��{id}")
	public ActionForward submitBjzcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.submitZcfs(zcfsForm, user);
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	

	/**
	 * 
	 * @����: �鿴�۲����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-25 ����09:29:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward viewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//��������
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		//�༶��Ϣ
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		//�۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap", bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		if("1".equals(szyf)){
		request.setAttribute("szyf", szyf);
		request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
		return mapping.findForward("viewZcfsOfYf");
		}
		return mapping.findForward("viewZcfs");
	}
	
	
	/**
	 * 
	 * @����: �۲�ֲ鿴-����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-6 ����09:14:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		File file = service.getBjZcfFile(zcfsForm,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	
	
	
	
	
	

	/**
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-29 ����08:54:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(zcfsForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * �������ѧ�����ص���ģ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadTemplateForAll(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);

		File file = service.createImportTemplateForAll(zcfsForm);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @����: �۲�ֵ���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-29 ����10:35:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward toImportZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		request.setAttribute("zcyf", request.getParameter("zcyf"));
		return mapping.findForward("toImportZcfs");
	}

	
	
	/**
	 * 
	 * @����: �۲��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-29 ����10:36:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-����ID��{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		try {
			File file = service.importZcfs(zcfsForm,user);
			
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
		
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		}
		
		return toImportZcfs(mapping, zcfsForm, request, response);
		
	}


	
	/**
	 * 
	 * @����: ͬ���ӿ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-29 ����06:55:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-ͬ��ID��{id}")
	public ActionForward getIntefaceData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		boolean result = false;
		if("10279".equals(Base.xxdm) || "13779".equals(Base.xxdm) || "10868".equals(Base.xxdm) || "13011".equals(Base.xxdm)) {
			result = service.getIntefaceDataS(zcfsForm, zcfsForm.getXmdms(), user);
		}else if("13022".equals(Base.xxdm)) {
			if("1".equals(request.getParameter("nzcs"))){
				result = service.getIntefaceDataXysp(zcfsForm, zcfsForm.getXmdms(), user);
			}else if("2".equals(request.getParameter("nzcs"))) {			
				result = service.getIntefaceDataSxdd(zcfsForm, zcfsForm.getXmdms(), user);
			}
		}
//		else if("13011".equals(Base.xxdm)){
//			result = service.getIntefaceDatazcf(zcfsForm, user);
//		}
		else if("12673".equals(Base.xxdm)){
			result = service.getIntefaceDataNmgdz(zcfsForm, user);
		}else if("12942".equals(Base.xxdm)){
			result = service.getIntefaceDataNmgzc(zcfsForm, user);
		}else if("13431".equals(Base.xxdm)){
			result = service.getIntefaceData_13431(zcfsForm, user);
			//result = service.getIntefaceDataNmgzc(zcfsForm, user);
		}else {
			result = service.getIntefaceData(zcfsForm, zcfsForm.getXmdm(), user);
		}
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL));
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:ȡ���۲�ֲ�ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����11:20:16
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
	public ActionForward viewZcfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZcfqxList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcfqx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfqxList");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ȡ���۲�ּ�¼��ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-1 ����02:56:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewQxjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ�۲��ȡ����¼
			List<HashMap<String,String>> resultList = service.getZcfqxjlList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_qxjl.do");
		request.setAttribute("szyf", csszService.getCsz("szyf"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewQxjlList");
	}
	
			
	/**
	 * 
	 * @����:�ı�ȡ���ύ״̬��ȡ�����ύ�ļ�¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����11:22:30
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
	@SystemLog(description="������������-�ۺϲ���-ȡ���ύ�۲��-ȡ���ύID��{id}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (UPDATE.equals(zcfsForm.getDoType())){
			ZcfsService service = new ZcfsService();
			
			User user = getUser(request);
			
			boolean result = service.cancelTj(user,zcfsForm);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
					: MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		
		return mapping.findForward("cancelTj");
		
	}
	
	
	/**
	 * 
	 * @����:�۲�����ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����04:02:46
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
	public ActionForward viewZcfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZcfjgList(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String zczq = csszService.getCsz("zczq");	//�۲�����
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		if(!StringUtil.isNull(zczq)&&CsszService.PJFS_XQ.equals(zczq)){
			searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		}
		request.setAttribute("searchTj", searchModel);
		zcfsForm.setSearchModel(searchModel);
		ZcxmService zcxmService = new ZcxmService();
		//�۲���Ŀ�б�
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(zcfsForm);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_jgcx.do");
		request.setAttribute("zczq", zczq);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfjgList");
	}
	
	
	public ActionForward initZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		ZcxmService zcxmService = new ZcxmService();
		ZcfsModel zcfsForm = new ZcfsModel();
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		String zczq= request.getParameter("zczq");
		if(null==xn||"".equals(xn)){
			xn=csszModel.getXn();
		}
			//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setSearch_tj_xn(xn.split("!!@@!!"));
		searchModel.setSearch_tj_xq(xq.split("!!@@!!"));
		zcfsForm.setSearchModel(searchModel);
		zcfsForm.setZczq(zczq);
		//�۲���Ŀ�б�
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(zcfsForm);
		JSONArray dataList = JSONArray.fromObject(zcxmList);
		response.getWriter().print(dataList);
		
		
		
		
		
		return null;
	}
	
	/**
	 * �յ�list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}
	
	/**
	 * ��ӡWord�ǼǱ���ѧ�ţ�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDjbWordStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsModel model = (ZcfsModel) form;
		File wordFile = getWordStu(model.getXn(), model.getXq(), model.getXh());
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * �ǼǱ�ZIP����ѧ�ţ�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDjbZipStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xns = request.getParameter("xns");
		String xqs = request.getParameter("xqs");
		String xhs =request.getParameter("xhs");
		if (StringUtils.isNotBlank(xhs)){
			String[] xnArr = xns.split(",");
			String[] xqArr = xqs.split(",");
			String[] xhArr = xhs.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = xhArr.length ; i < n ; i++){
				File file = getWordStu(xnArr[i],xqArr[i],xhArr[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	// ���ģ����������word�ļ�����ѧ�ţ�
	private File getWordStu(String xn,String xq,String xh) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		ZcfsService service = new ZcfsService();
		PjjgService pjjgService = new PjjgService();
		//��ȡѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xq)){
				xqmc = map.get("xqmc");
				break;
			}
		}
		// ������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		// ��ѧ�����гɼ�
		List<HashMap<String,String>> cjb_sxqBxList = service.getCjListByXhXnXq(xh, xn, "01", "");
		// ��ѧ�����гɼ�
		List<HashMap<String,String>> cjb_xxqBxList = service.getCjListByXhXnXq(xh, xn, "02", "");
		//��ȡ������ɼ�����
		String bjgcjs = pjjgService.getBjgcjNum(xh, xn, "");
		// ������Ϣְҵ����ѧԺ
		if("13108".equals(Base.xxdm)){
			addBlankList(cjb_sxqBxList, 9 - cjb_sxqBxList.size());
			cjb_sxqBxList = cjb_sxqBxList.subList(0, 9);
			addBlankList(cjb_xxqBxList, 9 - cjb_xxqBxList.size());
			cjb_xxqBxList = cjb_xxqBxList.subList(0, 9);
			// ��ѧ�ꡢѧ�ڡ�ѧ�Ų�ѯ�����۲���Ŀ��
			List<HashMap<String,String>> zcfListAllXh = service.getZcfListAllByBjdm_13108("", xh, xn, xq);
			if(zcfListAllXh.size() > 0){
				data.putAll(zcfListAllXh.get(0));
			}
		}
		
		data.put("xsxx", xsjbxx);
		data.put("bjgcjs", bjgcjs);
		data.put("xn", xn);
		data.put("xqmc", xqmc);
		data.put("cjb_sxqBxList", cjb_sxqBxList);
		data.put("cjb_xxqBxList", cjb_xxqBxList);
		
		String fileName = xh +"["+xsjbxx.get("xm")+"]" + "-";
		String mbmc = Base.xxdm + ".xml";
		// ������Ϣְҵ����ѧԺ
		if("13108".equals(Base.xxdm)){
			fileName = fileName + xn + xqmc + "ѧ���ۺ����ʲ������ܱ�";
			mbmc = "xszhszcphzb_" + mbmc;
		}
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", mbmc, fileName);
		return wordFile;
	}
	
	/**
	 * ��ӡWord�ǼǱ����༶��
	 */
	public ActionForward getDjbWordBjdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsModel model = (ZcfsModel) form;
		File wordFile = getWordBjdm(model.getXn(), model.getXq(), model.getBjdm());
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	// ���ģ����������word�ļ������༶��
	private File getWordBjdm(String xn,String xq,String bjdm) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		ZcfsService service = new ZcfsService();
		//��ȡѧ������
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = "";
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xq)){
				xqmc = map.get("xqmc");
				break;
			}
		}
		// ��ѧ�ꡢѧ�ڡ��༶�����ѯ�༶��Ϣ
		HashMap<String,String> bjxx = service.getBjxx(bjdm, xn, xq);
		// ������Ϣְҵ����ѧԺ
		if("13108".equals(Base.xxdm)){
			// ��ѧ�ꡢѧ�ڡ��༶�����ѯ�����۲���Ŀ��
			List<HashMap<String,String>> zcfListAllBjdm = service.getZcfListAllByBjdm_13108(bjdm, "", xn, xq);
			data.put("zcfListAllBjdm", zcfListAllBjdm);
			addBlankList(zcfListAllBjdm, 30 - zcfListAllBjdm.size());
			// ��ѧ�ꡢѧ�ڡ��༶�����ѯ�۲���Ŀƽ����
			HashMap<String,String> zcfAvgByBjdm = service.getZcfAvgByBjdm_13108(bjdm, xn, xq);
			data.put("zcfAvgByBjdm", zcfAvgByBjdm);
		}
		
		data.put("xn", xn);
		data.put("xqmc", xqmc);
		data.put("bjxx", bjxx);
		
		String fileName = bjxx.get("bjmc") + "-";
		String mbmc = Base.xxdm + ".xml";
		// ������Ϣְҵ����ѧԺ
		if("13108".equals(Base.xxdm)){
			fileName = fileName + xn + xqmc + "ѧ���ۺ����ʲ���������ܱ�";
			mbmc = "xszhszcpjghzb_" + mbmc;
		}
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", mbmc, fileName);
		return wordFile;
	}
	
	/**
	 * 
	 * @����:�۲�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-17 ����11:43:51
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
	public ActionForward exportViewZcfsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			
			ZcfsService service = new ZcfsService();
			ZcfsModel zcfsForm = (ZcfsModel) form;
			
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			File file = service.getZcfjgFile(zcfsForm,user);
			//�����ļ�
			FileUtil.outputExcel(response, file);
			return null;
			
	}
	/**
	 * �㽭����ְҵ����ѧԺ �����۲����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewZcfsjg_12861(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZcfjgFile_12861(zcfsForm,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 *  ѧ���ɼ����ܱ�.
	 *  <p>����ʡ����ҽҩ�ߵ�ְҵѧУ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-06 15:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportCjhzb(ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		//��ѯ�����м�¼������ҳ
		List<HashMap<String,String>> resultList = service.getXscjList(zcfsForm, user);

		BjcjhzModel bjcjhzModel = new BjcjhzModel().addAll(resultList);
		File file = service.getCjhzbFile(bjcjhzModel);
		if(file == null){
			request.setAttribute("message","���޵������ݣ�" );
			return new ActionForward("/prompt.do",false);
		}
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ȡ���۲⵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-15 ����09:18:00
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
		
		ZcfsService service = new ZcfsService();
		ZcfsModel model = (ZcfsModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String,String>> resultList = service.getZcfqxjlList(model,user);//��ѯ�����м�¼������ҳ

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
	 * @����:�鿴ȡ���ύ״̬��¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-15 ����09:27:32
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
	public ActionForward qxjlView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel model = (ZcfsModel) form;
		CsszService csszService = new CsszService();
		HashMap<String, String>qxjlInfor = service.getQxjl(model.getId());
		
		request.setAttribute("qxjlInfor", xgxt.utils.String.StringUtils.formatDataView(qxjlInfor));
		request.setAttribute("szyf", csszService.getCsz("szyf"));
		
		return mapping.findForward("qxjlView");
	}
	

	/**
	 * 
	 * @����:�鿴�Ƿ���δ�ύ��¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-2-9 ����03:31:38
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
	
	public ActionForward checkSubmitInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
	
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		User user = getUser(request);
		boolean isCanSubmit = service.isSubmitInfo(zcfsForm,user );
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:��֤��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-4 ����11:03:07
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
	public ActionForward checkDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		String num = request.getParameter("num");
		String zcyf = request.getParameter("zcyf");
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		
		
		boolean isCanDownload = service.isCanDownload(zcfsForm, user, num);
		
		response.getWriter().print(isCanDownload);
		
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-24 ����01:46:36
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
	public ActionForward exportDataNeW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		String id = request.getParameter("id");
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		ZcxmDao zcxmDao = new ZcxmDao();
		//����IDȡ�ð༶���������
		List<HashMap<String, String>> bjxxMap = service.getBjxxByIds(zcfsForm, user);
		
		CsszDao csszDao = new CsszDao();
		List<HashMap<String,String>> zcxmList = null;
		
		if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
			//�۲���Ŀ����Ϊ �꼶
			zcxmList = zcxmDao.getEditZcxmByNj(bjxxMap,zcfsForm,user);
		} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
			//�۲���Ŀ������Ϊ Ժϵ
			zcxmList = zcxmDao.getEditZcxmByXy(bjxxMap,zcfsForm,user);
		} else {
			zcxmList = zcxmDao.getNoChildZcfxm();
		}
		// ��ѯ
		ZcfsDao dao = new ZcfsDao();
		zcfsForm.setCxlx("dr");
		zcfsForm.setEditType("editType");
		List<HashMap<String, String>> resultList = dao.getPageList(zcfsForm, zcxmList, user);
		
		// �������ܴ���
		File file = service.createImportTemplateDc(resultList, zcxmList,user);
		FileUtil.outputExcel(response, file);
		return null;
	} 
	
	
	/**
	 * 
	 * @����: �ɶ��м�ʦѧԺ�����ύ���Ի�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-6 ����10:31:46
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
	public ActionForward plCheckIsCanSubmityf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		if(StringUtils.isBlank(zcfsForm.getId())){
			
			System.out.println("id Ϊ��");
			throw new SystemException(MessageKey.SYS_SUBMIT_FAIL);
			
		}else{
			
			String[] mess = service.isNotCanSubmitYf(zcfsForm, user);
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:�ɶ��м�ʦѧԺ�۲��ȡ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-6 ����01:44:42
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
	public ActionForward viewZcfqxListYf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getZcfqxListYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcfqxyf.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZcfqxListYf");
	}
	
	/**
	 * 
	 * @����: ȡ�������ύ�۲�ֲ鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-6 ����03:08:41
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
	public ActionForward viewZcfsOfyf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (QUERY.equals(zcfsForm.getDoType())){
			
			User user = getUser(request);
			//��ѯ
			zcfsForm.setDoType("aytjzcf");
			List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//��������
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		//�༶��Ϣ
		HashMap<String,String> bjMap = service.getBjxxById(zcfsForm.getId());
		//�۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getEditZcxmByBjdm(bjMap.get("bjdm"));
		
		request.setAttribute("bjxxMap", bjMap);
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		String szyf = csszService.getCsz("szyf");
		request.setAttribute("szyf", szyf);
		request.setAttribute("zcyfList", zcxmService.getZcYf(csszModel.getXn(), csszModel.getXq()));
		request.setAttribute("flag", "aytjzcf");
		return mapping.findForward("viewZcfsOfYf");
	}
	
	/**
	 * 
	 * @����:ȡ�������ύ�۲��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-6 ����03:38:34
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
	public ActionForward cancelTjofYf(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		if (UPDATE.equals(zcfsForm.getDoType())){
			ZcfsService service = new ZcfsService();
			
			User user = getUser(request);
			
			boolean result = service.cancelTjOfYf(user,zcfsForm);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
					: MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		//��������
		CsszService csszService = new CsszService();
		request.setAttribute("szyf",csszService.getCsz("szyf"));
		return mapping.findForward("cancelTj");
		
	}
	
	/**
	    * 
	    * @����: �ɶ��м�ʦѧԺ�����ύͳ�Ʋ�ѯ
	    * @���ߣ�yxy[���ţ�1206]
	    * @���ڣ�2016-9-7 ����11:33:19
	    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	    * @param xn
	    * @param xq
	    * @return
	    * List<HashMap<String,String>> �������� 
	    * @throws
	    */
	public ActionForward getZcfAyTjTjcxList (ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CsszService csszService = new CsszService();
		CsszModel csszmodel = csszService.getModel();
		List<HashMap<String, String>> zcaytjlist = new ZcfsService().getZcfAyTjTjcxList(csszmodel.getXn(), csszmodel.getXq());
		request.setAttribute("zcaytjlist", zcaytjlist);
		request.setAttribute("xn", csszmodel.getXn()+"ѧ��");
		request.setAttribute("xqmc", new DAO().getXqmcForXqdm(csszmodel.getXq()));
		return mapping.findForward("zcfaytjtjcx");
	}
	
	/**
	 * 
	 * @����:�ύͳ�Ʋ鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-7 ����03:51:52
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
	public ActionForward viewBjzcListCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		String yf = request.getParameter("yf");
		String tjzt = request.getParameter("tjzt");
		if (QUERY.equals(zcfsForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcfsForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			
			List<HashMap<String,String>> resultList = service.getZcbjListCk(zcfsForm,user,tjzt,yf);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "pj_zcflr.do");
		/*xg_pjpy_new_cspzb szyf = 1 �����ύ�ˣ��ύ״̬*/
	    request.setAttribute("szyf", csszService.getCsz("szyf"));
	    request.setAttribute("tjzt", tjzt);
	    request.setAttribute("yf", yf);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjzcListck");
	}
	
	/**
	 * 
	 * @����: �ɶ��м�ʦѧԺ�ύ�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-7 ����05:39:03
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
	public ActionForward exportDataDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsModel model = (ZcfsModel) form;
		String yf = request.getParameter("yf");
		String tjzt = request.getParameter("tjzt");

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = new ZcfsService().getZcbjListCk(model, user, tjzt, yf);
		
		//��Ϊѧ�����ƺ������סԭ��ֻ�ܻ�ȡ����ֵ���������ѭ������setֵ

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
	 * @���������������������ʼ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��27�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="��������-�ۺϲ���-�۲�ά��-���������������ʼ��")
	public ActionForward initCpzCpmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		String messageKey;
		if("zf01".equals(user.getUserName())){
			CpxzService cpxzService = new CpxzService();
			boolean result = cpxzService.initCpzCpmd(user);
			messageKey = result ? MessageKey.SYS_INIT_SUCCESS:MessageKey.SYS_INIT_FAIL;
		}else{
			messageKey = "�ǹ���Ա�û����������";
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-ͬ��ID��{id}")
	public ActionForward getIntefaceData_tbf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		boolean result = false;
		String type = request.getParameter("type");
		result = service.getIntefaceData_12688(type, zcfsForm, zcfsForm.getXmdms(), user);
		

		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL));
		
		return null;
	}

	/**
	 * ͬ��ѧ�ֳɼ�
	 */
	public ActionForward tbXfcj(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcfsService service = new ZcfsService();
		if(service.tbXfcj(getUser(request))){
			response.getWriter().print(getJsonMessage(MESSAGE_SUCCESS));
		}else {
			response.getWriter().print(getJsonMessage(MESSAGE_FAIL));
		}
		return null;
	}
	
}
