/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����05:21:24 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: �Ʒ���Ŀ(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����05:21:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfJfxmwhAction extends SuperAction<ZhfJfxmwhForm, ZhfJfxmwhService>{
	private static final String url = "xg_zjly_zhfxmwh.do";
	private ZhfJfxmwhService service = new ZhfJfxmwhService();
	private ZhfService zhfService = new ZhfService();
	
	@SystemAuth(url = url)
	public ActionForward getZhfJfxmwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "xg_zjly_zhfxmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfXmmkwhList.jsp");
	}
	
	/** 
	 * @����:�Ʒ���Ŀ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����11:29:56
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
	public ActionForward addZhfJfxm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> xmmkList = zhfService.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		return mapping.findForward("addZhfJfxm");
	}
	
	/** 
	 * @����:����Ʒ���Ŀ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����02:00:04
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
	public ActionForward saveJfxm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		if(model.getType().equals("save")){
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_JFXM_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
//				String jfxmdm = UniqID.getInstance().getUniqIDHash();
				String jfxmdm = service.getnewjfxmdm();
				model.setJfxmdm(jfxmdm);
				boolean result = service.runInsert(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}else{
			if(service.isExist(model)){
				String messageKey = MessageKey.ZJLY_ZHF_XMWH_JFXM_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				boolean result = service.runUpdate(model);
				String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}
	}
	
	/** 
	 * @����:�Ʒ���Ŀ�޸���תҳ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����02:31:24
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
	public ActionForward updateZhfJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		ZhfJfxmwhForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if(model.getXdfs()!= null){
			request.setAttribute("xdfs", model.getXdfs());
		}
		List<HashMap<String, String>> xmmkList = zhfService.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		return mapping.findForward("updateZhfJfxm");
	}
	 
	/** 
	 * @����:ɾ���Ʒ���Ŀ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����02:52:47
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
	public ActionForward delZhfJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("values");
		if (!StringUtil.isNull(ids)) {
			String[] idss = ids.split(",");
				int num = service.runDelete(idss);
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
	 * @����:�õ���Ȩ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����03:45:06
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
	public ActionForward getBmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
//			CommService comService = new CommService();
//			SearchModel searchModel = comService.getSearchModel(request);
//			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageListForSq(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "zhf_xmsq.do");
		String jfxms = request.getParameter("jfxms");
		request.setAttribute("jfxms", jfxms);
		return mapping.findForward("getBmList");
	}
	
	/** 
	 * @����:������Ȩ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����06:56:11
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
	public ActionForward saveSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jfs= request.getParameter("jfxms");
		String bms = request.getParameter("bmdms");
		String[] jfxms = jfs.split(",");
		String[] bmdms = bms.split(",");
		boolean result = service.jfxmSq(bmdms, jfxms);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/** 
	 * @����:ȡ����Ȩ(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-16 ����02:25:11
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jfs= request.getParameter("jfxms");
		String bms = request.getParameter("bmdms");
		String[] jfxms = jfs.split(",");
		String[] bmdms = bms.split(",");
		boolean result = service.jfxmQx(bmdms, jfxms);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/** 
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����08:48:14
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
	public ActionForward jdsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("jdsz");
	}
	
	/** 
	 * @����:����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����09:31:45
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
	public ActionForward saveJdsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		String jdszContent = model.getJdszContent();
		String[] jfxmmcs = jdszContent.split(",");
		if(jfxmmcs.length<2){
			response.getWriter().print(getJsonMessageByKey(MessageKey.ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR));
		}else{
			boolean result = service.jdsz(jfxmmcs);
			String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}
	
	//
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����10:38:09
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
		ZhfJfxmwhForm model = (ZhfJfxmwhForm) form;
		//���ɸ߼���ѯ����		
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
