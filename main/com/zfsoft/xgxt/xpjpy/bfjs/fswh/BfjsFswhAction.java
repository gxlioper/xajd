/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import java.io.File;
import java.util.HashMap;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszService;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

/** 
 * @ϵͳ����: �༶��������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������
 * @���ߣ�xiaxia [���ţ�1104]
 * @ʱ�䣺 2016-4-19 ����01:36:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsFswhAction extends SuperAction {
	
	private static final String url = "xpjpy_bfjs_fswh.do";
	private BfjsCsszService csszService = new BfjsCsszService();
	private BfjsJsxmService jsxmService = new BfjsJsxmService();
	/**
	 * 
	 * @����: �༶��������б�
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����10:29:06
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
	public ActionForward viewBjjsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getJsbjList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		//����û������Ŀ���۲���Ŀ
		List<HashMap<String,String>> jsxmList = jsxmService.getEditBfjsJsxmByBjdm();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_fswh.do");
		request.setAttribute("jsxmList", jsxmList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBjjsList");
	}
	
	/**
	 * 
	 * @����: ���澺������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����03:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-��羺��-����ά��-¼��-���°༶Bjdm��{Bjdm}")
	public ActionForward saveBfjsFswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveBfjsFswh(BfjsFswhForm, user);
		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ����Ƿ���ύ������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����06:26:25
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
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel bfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		bfjsFswhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("id");
		
		boolean isCanSubmit = service.isCanSubmit(values,bfjsFswhForm,user);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	
	public ActionForward isHaveSubmitClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsFswhService service = new BfjsFswhService();
		BfjsCsszModel csszModel = csszService.getModel();
		
		boolean isHaveSubmitClass = service.isHaveSubmitClass(csszModel.getXn(), csszModel.getXq());
		response.getWriter().print(isHaveSubmitClass);
		return null;
	}
	
	/**
	 * 
	 * @����: �ύ�༶������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����06:53:22
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
	@SystemLog(description="������������-��羺��-����ά��-�ύ������ID��{id}")
	public ActionForward submitBjJsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel bfjsFswhForm = (BfjsFswhModel) form;
		String values = request.getParameter("id");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		bfjsFswhForm.setSearchModel(searchModel);
		
		boolean result = service.submitBfjsFswh(values,tjzt,bfjsFswhForm,user);
		
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	
	/**
	 * 
	 * @����: �����ֲ鿴-����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����09:14:47
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
	public ActionForward exportViewJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		File file = service.getBjJsfFile(BfjsFswhForm,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����08:54:42
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
	
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		File file = service.createImportTemplate(BfjsFswhForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @����: �����ֵ���
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����10:35:27
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
	public ActionForward toImportJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		return mapping.findForward("toImportJsfs");
	}

	/**
	 * 
	 * @����: ������������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����10:36:17
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
	@SystemLog(description="������������-��羺��-����ά��-¼��-����ID��{id}")
	public ActionForward importJsfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		
		try {
			File file = service.importBfjsFswh(BfjsFswhForm,user);
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
		
		return toImportJsfs(mapping, BfjsFswhForm, request, response);
		
	}

	/**
	 * 
	 * @����:ȡ�������ֲ�ѯ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����11:20:16
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
	public ActionForward viewJsfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getJsfqxList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_qxtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJsfqxList");
	}
	
	
			
	/**
	 * 
	 * @����:�ı�ȡ���ύ״̬��ȡ�����ύ�ļ�¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����11:22:30
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
	@SystemLog(description="������������-��羺��-ȡ���ύ������-ȡ���ύID��{values}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (UPDATE.equals(BfjsFswhForm.getDoType())){
			BfjsFswhService service = new BfjsFswhService();
			
			User user = getUser(request);
			boolean result = service.cancelTj(user,BfjsFswhForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		
		return mapping.findForward("cancelTj");
		
	}
	
	
	/**
	 * 
	 * @����:���������ѯ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����04:02:46
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
	@SystemAuth(url = "xpjpy_bfjs_pfjg.do")
	public ActionForward viewJsfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
		
		if (QUERY.equals(BfjsFswhForm.getDoType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getJsfjgList(BfjsFswhForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		BfjsCsszModel csszModel = csszService.getModel();
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		request.setAttribute("searchTj", searchModel);
		BfjsFswhForm.setSearchModel(searchModel);
		//������Ŀ�б�
		List<HashMap<String,String>> jsxmList = jsxmService.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		request.setAttribute("jsxmList", jsxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "xpjpy_bfjs_pfjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJsfjgList");
	}
	
	
	public ActionForward initJsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BfjsFswhModel BfjsFswhForm = new BfjsFswhModel();
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		BfjsCsszModel csszModel = csszService.getModel();
		String Jszq= request.getParameter("jszq");
		if(null==xn||"".equals(xn)){
			xn=csszModel.getXn();
		}
			//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setSearch_tj_xn(xn.split("!!@@!!"));
		searchModel.setSearch_tj_xq(xq.split("!!@@!!"));
		BfjsFswhForm.setSearchModel(searchModel);
		BfjsFswhForm.setJszq(Jszq);
		//������Ŀ�б�
		List<HashMap<String,String>> JsxmList = jsxmService.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		JSONArray dataList = JSONArray.fromObject(JsxmList);
		response.getWriter().print(dataList);
		
		
		
		
		
		return null;
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19 ����11:43:51
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
	@SystemAuth(url = "xpjpy_bfjs_pfjg.do")
	public ActionForward exportJsfjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			
			BfjsFswhService service = new BfjsFswhService();
			BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
			
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			BfjsFswhForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			File file = service.getJsfjgFile(BfjsFswhForm,user);
			//�����ļ�
			FileUtil.outputExcel(response, file);
			return null;
			
	}


	/**
	 * 
	 * @����:�鿴�Ƿ���δ�ύ��¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-19����03:31:38
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
		
		BfjsFswhService service = new BfjsFswhService();
		BfjsFswhModel BfjsFswhForm = (BfjsFswhModel) form;
	
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		BfjsFswhForm.setSearchModel(searchModel);
		User user = getUser(request);
		boolean isCanSubmit = service.isSubmitInfo(BfjsFswhForm,user );
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}
	

	
}
