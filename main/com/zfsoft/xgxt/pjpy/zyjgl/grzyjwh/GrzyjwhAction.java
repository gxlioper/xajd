/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-22 ����10:17:36 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����רҵ��ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2015-12-22 ����10:17:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GrzyjwhAction extends SuperAction<GrzyjwhForm, GrzyjwhService>{
	private final static String url = "pjpy_zyjgl_zyjwh.do";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private final static String ZYJWH = "zyjwh";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(ZYJWH);
	}
	
	//��ѯ���
	@SystemAuth(url = url)
	public ActionForward grzyjwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GrzyjwhForm model = (GrzyjwhForm) form;
		GrzyjwhService service = new GrzyjwhService();
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ��ȡ�������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "pjpy_zyjgl_zyjwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("grzyjwhManage");

	}
	
	//���Ӹ���רҵ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addGrzyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		GrzyjwhForm model = (GrzyjwhForm) form;
		GrzyjwhService service = new GrzyjwhService();
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
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.saveGrzyj(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}		
		String path = "pjpy_zyjwhwh.do?method=addGrzyj";
		request.setAttribute("path", path);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("grzyjwhAdd");
	}
	
	//�޸Ľ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateGrzyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GrzyjwhForm model = (GrzyjwhForm) form;
		GrzyjwhService service = new GrzyjwhService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
					boolean result = service.updateGrzyj(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
		}
				request.setAttribute("jbxxList", jbxxList);
		GrzyjwhForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("grzyjwhUpdate");
	}
	
	//ɾ�����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delGrzyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GrzyjwhService service = new GrzyjwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delGrzyj(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",String.valueOf(num));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	//��������
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GrzyjwhForm model = (GrzyjwhForm) form;
		GrzyjwhService service = new GrzyjwhService();
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
	
	//�϶�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rendingGrzyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GrzyjwhForm model = (GrzyjwhForm) form;
		GrzyjwhService service = new GrzyjwhService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if ("rending".equalsIgnoreCase(model.getType())) {
					boolean result = service.updateGrzyj(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
		}
		
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> rddjList = service.getRddjList();
		request.setAttribute("rddjList", rddjList);
		GrzyjwhForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("grzyjwhRending");
	}
	
	//�鿴
	public ActionForward grzyjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			GrzyjwhForm model = (GrzyjwhForm) form;
			GrzyjwhService service = new GrzyjwhService();
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
						.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);
			GrzyjwhForm viewForm = service.getModel(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
			request.setAttribute("xxdm", Base.xxdm);
			request.setAttribute("rs", StringUtils.formatData(model));
			return mapping.findForward("grzyjwhView");
	}
}
