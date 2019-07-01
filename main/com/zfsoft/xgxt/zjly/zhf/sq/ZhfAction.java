/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����04:17:48 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.cssz.ZhfCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷ�����(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����04:17:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfAction extends SuperAction<ZhfForm, ZhfService>{
	private ZhfService service = new ZhfService();
	private ZhfCsszService csszService = new ZhfCsszService();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	private static List<HashMap<String, String>> jbxxList = null;
	public static final String ZJLY_ZHF = "zjly_zhf";
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZJLY_ZHF);
	}
	
	private static final String url = "xg_zjly_zhfsq.do";
	
	/** 
	 * @����:��ȡ�ۺϷ��б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-17 ����04:33:39
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
	public ActionForward getZhfsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
//			//���ɸ߼���ѯ����
//			CommService comService = new CommService();
//			SearchModel searchModel = comService.getSearchModel(request);
//			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
	    String sqshkg = csszService.getModel().getSqkg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg);
		String path = "xg_zjly_zhfsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfsqList");
	}
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-17 ����04:29:52
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
	public ActionForward addZhfsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfForm model = (ZhfForm) form;
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
		List<HashMap<String, String>> xmmkList = service.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqr", user.getUserName());
		return mapping.findForward("addZhfsq");
	}
	
	/** 
	 * @����:����ģ�����õ��Ʒ��б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����01:42:39
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
	public ActionForward getJfxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfForm model = (ZhfForm) form;
		// ��ѯ
		if(null != model.getXmmkdm() && !"".equalsIgnoreCase(model.getXmmkdm())){
			List<HashMap<String, String>> resultList = service.getJfxmListByMkid((model.getXmmkdm()));
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
		}	
		return null;
	}
	
	/** 
	 * @�������õ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����03:14:07
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
	public ActionForward getFs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfForm model = (ZhfForm) form;
		// ��ѯ
		if(null != model.getJfxmdm() && !"".equalsIgnoreCase(model.getJfxmdm())){
			String fs = service.getFs(model.getJfxmdm());
			response.getWriter().print(fs);
		}	
		return null;
	}
	
	/** 
	 * @����:��ȡ����Ҫ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����03:19:58
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
	public ActionForward getKhyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfForm model = (ZhfForm) form;
		// ��ѯ
		if(null != model.getJfxmdm() && !"".equalsIgnoreCase(model.getJfxmdm())){
			String khyd = service.getKhyd(model.getJfxmdm());
			response.getWriter().print(khyd);
		}	
		return null;
	}
	
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-20 ����07:50:12
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
	public ActionForward saveZhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfForm model = (ZhfForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		boolean result = service.saveZhf(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.ZJLY_ZHF_SQ_REPEAT;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @����:ɾ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����10:20:52
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		String ids = request.getParameter("ids");
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
	 * @����:�޸�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����11:56:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZhfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		ZhfForm model = (ZhfForm) form;
		ZhfForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		request.setAttribute("model", model);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		List<HashMap<String, String>> xmmkList = service.getXmmkList();
		request.setAttribute("xmmkList", xmmkList);
		request.setAttribute("jbxxList", jbxxList);
		if(null != model.getXmmkdm() && !"".equalsIgnoreCase(model.getXmmkdm())){
			List<HashMap<String, String>> resultList = service.getJfxmListByMkid((model.getXmmkdm()));
			request.setAttribute("jfxmList", resultList);
		}	
		return mapping.findForward("updateZhfsq");
	}
	
	
	/** 
	 * @����:���ظ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����01:59:36
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhfForm myForm = (ZhfForm) form;
		ZhfForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFj())){
			File file = new File(model.getFj());
			if (file.exists()){
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(model.getFjmc(),"utf-8")); 
				FileUtil.outputFile(response, file);
			}
		}		
		return null;
	}
	
	
	/** 
	 * @����:ɾ������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����01:59:54
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
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhfForm myForm = (ZhfForm) form;
		ZhfForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFj())){
			File file = new File(model.getFj());
			if (file.exists()){
				file.delete();
			}
			model.setFj("");
			service.runUpdate(model);
		}
		return null;
	}
	
	/** 
	 * @����:�޸ı���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����04:12:22
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
	public ActionForward saveZhfForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZhfForm myForm = (ZhfForm) form;
		if(service.isExistForUpdate(myForm)){
			String messageKey = MessageKey.ZJLY_ZHF_SQ_REPEAT;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			if(null != myForm.getMultipartRequestHandler()){
				Hashtable files = myForm.getMultipartRequestHandler().getFileElements();
				if(null != files){
					FormFile file = (FormFile) files.get("lbfj");
					if(null!=file&&file.getFileSize() > 1024*1024*5){
						String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,file.getFileName());
						response.getWriter().print(getJsonMessage(message));
						return null;
					}
					if (file != null && !StringUtil.isNull(file.getFileName())){
						String basePath = resource.getString("filesys.local.dir");
						//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
						if(StringUtils.isNull(basePath)){
							basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
						}
						String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));					
						if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
							File srcFile = FileUtil.conversionFormFile(file);
							File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
							FileUtils.copyFile(srcFile, destFile);
							myForm.setFj(destFile.getAbsolutePath());
							myForm.setFjmc(file.getFileName());
						}
					}
				}
			}
			
			//������
								
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	}
	
	/** 
	 * @����:�鿴(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-21 ����06:28:44
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
	public ActionForward viewZhfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		ZhfForm myForm = (ZhfForm) form;
		ZhfForm model = service.getModel(myForm);
		request.setAttribute("model", model);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewZhfsq");
	}
	
}

