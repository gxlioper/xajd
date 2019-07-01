/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:03:35 
 */  
package com.zfsoft.xgxt.rcsw.sxjy;

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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	�� DwjlAction
 * @description	�� ���⽻��ģ��
 * @author 		��CP��1352��
 * @date		�� 2017-11-20 ����09:00:46
 * @version 	V1.0
 */

public class SxjyAction extends SuperAction {
	
	//�����ճ�������ҽ�Ʊ��ճ������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	SxjyService service = new SxjyService();
	
	private static final String url = "xg_sxjy_sxjyjg.do";

	/**
	 * @description	����ѯ
	 * @author 		�� CP��1352��
	 * @date 		��2017-11-20 ����09:03:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@SystemAuth(url = url)
	public ActionForward sxjycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxjyForm model = (SxjyForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤����������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxjycx");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2017-11-20 ����09:48:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward addSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SxjyForm model = (SxjyForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			// ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWYLBX);	
			request.setAttribute("jbxxList", jbxxList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
	    	}
			return null;
		}
		String path = "xg_sxjy_sxjy.do?method=addSxjy";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}

/**
 * 
 * @description	�� �޸�
 * @author 		�� CP��1352��
 * @date 		��2017-11-20 ����01:49:05
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@SystemAuth(url = url)
	public ActionForward updateSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxjyForm model = (SxjyForm) form;
		SxjyForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("id", myForm.getId());
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_TOKEN_VALID, false));
			}

			return null;
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xg");
	}
/**
 * @description	�� ɾ��
 * @author 		�� CP��1352��
 * @date 		��2017-11-20 ����02:43:23
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward sxjyDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
 * @description	�� �鿴
 * @author 		�� CP��1352��
 * @date 		��2017-11-20 ����03:38:40
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
		
	@SystemAuth(url = url)
	public ActionForward viewSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SxjyForm myForm = (SxjyForm) form;
		SxjyForm model = service.getModel(myForm);
		
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("view");
	}
	
	
/**
 * @description	�� ����
 * @author 		�� CP��1352��
 * @date 		��2017-11-20 ����04:10:15
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxjyForm model=(SxjyForm) form;
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
	
	
	
	

}
