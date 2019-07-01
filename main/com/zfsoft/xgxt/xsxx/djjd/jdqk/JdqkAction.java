/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:49:48 
 */  
package com.zfsoft.xgxt.xsxx.djjd.jdqk;

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
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.xsxx.djjd.dmwh.JddjDmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @�๦������:�����ȼ��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:49:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdqkAction extends SuperAction<JdqkModel, JdqkService> {
	
	private static final String url = "ntgm_jjdj_jdqk.do";

	/**�ȼ���������б�*/
	@SystemAuth(url = url)
	public ActionForward jdqkList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "ntgm_jjdj_jdqk.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jdqkList");
	}
	
	/**�ȼ���������б�*/
	@SystemAuth(url = url)
	public ActionForward getJdqkList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdqkService service = getService();
		JdqkModel model = (JdqkModel) form;
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**�ȼ������������*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdqkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdqkModel model = (JdqkModel) form;
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
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
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		request.setAttribute("path", "jddj_jdqk.do?method=jdqkAdd");
		return mapping.findForward("jdqkAdd");
	}
	
	/**�ȼ���������޸�*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdqkEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdqkService service = getService();
		JdqkModel myForm = (JdqkModel) form;
		
		JdqkModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
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
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("jdqkEdit");
	}
	
	@SystemAuth(url = url)
	public ActionForward jdqkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdqkService service = getService();
		JdqkModel myForm = (JdqkModel) form;
		
		JdqkModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
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
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("jdqkView");
	}
	
	
	/**�ȼ��������ɾ��*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-�����ȼ���������ͨ��ó��-�������-ɾ��VALUES:{values}")
	public ActionForward jdqkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JdqkService service = getService();
		
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
		JdqkService service = getService();
		JdqkModel model = (JdqkModel) form;

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
