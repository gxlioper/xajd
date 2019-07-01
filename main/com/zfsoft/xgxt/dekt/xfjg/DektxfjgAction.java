/**
 * @���ţ�ѧ����Ʒ��ҵ��
 * @���ڣ�2016��11��17��
 */  
package com.zfsoft.xgxt.dekt.xfjg;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dekt.qnzyry.QnzyryForm;
import com.zfsoft.xgxt.dekt.qnzyry.QnzyryService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/**
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ��ڹ���ѧ-�������� ����ģ��
 * @�๦��������TODO(������һ�仰��������������) 
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��4��21��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DektxfjgAction extends SuperAction<DektxfjgForm,DektxfjgService> {
	private DektxfjgService service = new  DektxfjgService();
	private static final String url = "dekt_xfjg_list.do";
	
	/**
	 * @������listҳ����ת
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward xfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		User user = getUser(request);
		//ѧ���û���¼������ѧ��
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			String fs = service.getTotalXf(user.getUserName());
			request.setAttribute("xf", fs);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfjgList");
	}
	
	/**
	 * @������list����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward getXfjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward xfjgView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		XsxxService xsxxService = new XsxxService();
		//ѧ����Ϣ
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//ҵ����Ϣ
		request.setAttribute("model", service.getView(model));
		return mapping.findForward("xfjgView");
	}
	
	/**
	 * @����������ҳ����ת //TODO ��ʱ����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward xfjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//ѧ����Ϣѡ������¼���
		request.setAttribute("path", "qgzx_jtff.do?method=jtffAdd");
		return mapping.findForward("xfjgAdd");
	}
	
	/**
	 * @�������޸�ҳ����ת //TODO ��ʱ����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward xfjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		DektxfjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xfjgEdit");
	}
	
	/**
	 * @���������� //TODO ��ʱ����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward xfjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm myForm = (DektxfjgForm) form;
		// Ψһ���ж�
		if (!service.checkExist(myForm)) {
			boolean result =StringUtils.isNull(myForm.getSqid())?service.runInsert(myForm):service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonResult(MessageKey.SYS_AUD_DOUBLE, false));
		}
		return null;
	}
	
	/**
	 * @������ɾ�� //TODO ��ʱ����
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward xfjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����������
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��7��28��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
		 
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
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2017-12-18 ����10:27:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		DektxfjgService service = new DektxfjgService();
		DektxfjgForm copyModel = service.getModel(model);
		if(null != copyModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(copyModel));
		}
		return mapping.findForward("pfzhhd");
	} 
	
	/**
	 * @description	�� ��������
	 * @author 		�� ������1282��
	 * @date 		��2017-12-18 ����10:30:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfjgForm model = (DektxfjgForm) form;
		boolean result = service.runUpdate(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	
}