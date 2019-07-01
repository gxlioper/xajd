/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:49:48 
 */  
package com.zfsoft.xgxt.rcsw.sybx.hsd;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @�๦������:��ʦ��-��ҵ����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:49:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdSybxAction extends SuperAction<HsdSybxModel, HsdSybxService> {
	
	private static final String url = "rcsw_hsd_sybx.do";

	/**��ҵ�����б�*/
	@SystemAuth(url = url)
	public ActionForward sybxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "rcsw_hsd_sybx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sybxList");
	}
	
	/**��ҵ��������б�*/
	@SystemAuth(url = url)
	public ActionForward getSybxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HsdSybxService service = getService();
		HsdSybxModel model = (HsdSybxModel) form;
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**��ҵ��������*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sybxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HsdSybxModel model = (HsdSybxModel) form;
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("sybx");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		request.setAttribute("path", "hsd_sybx.do?method=sybxAdd");
		return mapping.findForward("sybxAdd");
	}
	
	/**��ҵ�����޸�*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sybxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HsdSybxService service = getService();
		HsdSybxModel myForm = (HsdSybxModel) form;
		
		HsdSybxModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("sybx");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("sybxEdit");
	}
	
	
	/**��ҵ�������ɾ��*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ҵ����-��ҵ����ά������ʦ��-ɾ��VALUES:{values}")
	public ActionForward sybxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		HsdSybxService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HsdSybxService service = getService();
		HsdSybxModel model = (HsdSybxModel) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

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
