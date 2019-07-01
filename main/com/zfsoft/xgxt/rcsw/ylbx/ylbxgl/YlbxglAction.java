package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ҽ�Ʊ��չ���
 */
public class YlbxglAction extends SuperAction {
	
	//�����ճ�������ҽ�Ʊ��ճ������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	YlbxglService service = new YlbxglService();
	
	private static final String url = "rcsw_ylbx_ylbxgl.do";

	/**
	 * ��ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward ylbxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxglForm model = (YlbxglForm) form;
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤����������
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_ylbx_ylbxgl.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxglManage");
	}

	/**
	 * ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.insertYlbxgl(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}

		String path = "rcsw_ylbx_ylbxglgl.do?method=addYlbxgl";
		request.setAttribute("path", path);
		
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		//��һ��ѧ��
		request.setAttribute("dqxn", Base.afterXn);
		return mapping.findForward("addYlbxgl");
	}
	
	/**
	 * ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYlbxglPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if(StringUtil.isNull(model.getXh())){
				// ���ݲ�ѯ���������������
				//���ɸ߼���ѯ����		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("rcsw_ylbx_ylbxgl.do");
				model.setSearchModel(searchModel);
				List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
				
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				boolean result = true;
				for (int i = 0; i < resultList.size(); i++) {
					model.setXh(resultList.get(i).get("xh"));
					result = service.insertYlbxgl(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}else{
				// ���ݹ�ѡ��¼������������
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				String[] arr = model.getXh().split(",");
				boolean result = true;
				for (int i = 0; i < arr.length; i++) {
					model.setXh(arr[i]);
					result = service.insertYlbxgl(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
		}
		
		String path = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl";
		request.setAttribute("path", path);
		//��һ��ѧ��
		request.setAttribute("dqxn", Base.afterXn);
		return mapping.findForward("addYlbxglPl");
	}
	
	/**
	 * �޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateYlbxgl(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		// �޸�ʱ���ų���ǰ��¼����ѯӦ������
		model.setYjnum(service.viewYjnum(model.getXh(), model.getJgid()));
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("updateYlbxgl");
	}

	/**
	 * ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * �鿴�轻
	 */
	@SystemAuth(url = url)
	public ActionForward viewOneYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		
		//��ѯ��ʷ����
		List<HashMap<String, String>> lsList = service.viewLsList(model.getXh());
		request.setAttribute("lsList", lsList);

		return mapping.findForward("viewYlbxgl");
		
	}
	/**
	 * �鿴�ѽ�����
	 */
	@SystemAuth(url = url)
	public ActionForward viewYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		
		//��ѯ��ʷ����
		List<HashMap<String, String>> lsList = service.viewOneList(model.getXh(),model.getJgid());
		request.setAttribute("lsList", lsList);
		
		return mapping.findForward("viewlsYlbxgl");
		
	}
	
	/**
	 * ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		String ylbxzt = request.getParameter("ylbxzt");
		model.setYlbxzt(ylbxzt);
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
	
	/**
	 * ��ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward ylbxXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		String jgid = request.getParameter("jgid");
		Map<String, String> ylbxXxMap = service.viewOneYlbxglList(xh, jgid);//
		ylbxXxMap = (Map<String, String>) StringUtils.formatData(ylbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(ylbxXxMap));
		return null;
	}

}
