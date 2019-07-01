package com.zfsoft.xgxt.jyglnew.jygl.byqx;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class ByqxAction extends SuperAction {
	
	private static final String url = "jyglnew_jygl_byqx.do";
	
	private static final String JYGLNEWBYQX = "jyglnewbyqx";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;

	/**
	 * ��ѯ��ҵȥ��
	 */
	@SystemAuth(url = url)
	public ActionForward byqxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "��ģ�鲻����ѧ�����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jyglnew_jygl_byqx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("byqxManage");
	}
	/**
	 * ���ӱ�ҵȥ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-����")
	public ActionForward addByqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.insertByqx(model, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = MessageUtil.getText(MessageKey.JYGLNEW_JYGL_BYQX_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			model.setSjhm(xsjbxx.get("sjhm"));
			model.setQqhm(xsjbxx.get("qqhm"));
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWBYQX);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> jydwxzList = service.getJydwxzList();
		request.setAttribute("jydwxzList", jydwxzList);
		List<HashMap<String, String>> jyxsList = service.getJyxsList();
		request.setAttribute("jyxsList", jyxsList);
		//����ҽҩ�ߵ�ְҵѧУ
		if("70002".equals(Base.xxdm)){			
			List<HashMap<String, String>> byqxList = service.getByqxList();
			request.setAttribute("byqxList", byqxList);
			List<HashMap<String, String>> jylbList = service.getJylbList();
			request.setAttribute("jylbList", jylbList);
			List<HashMap<String, String>> jyzkList = service.getJyzkList();
			request.setAttribute("jyzkList", jyzkList);
		}
		String path = "jyglnew_jygl_byqxgl.do?method=addByqx";
		request.setAttribute("path", path);
		return mapping.findForward("addByqx");
	}
	/**
	 * �޸ı�ҵȥ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-�޸�XH:{xh}")
	public ActionForward updateByqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.updateByqx(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> updateForm = service.getModelMap(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWBYQX);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> jydwxzList = service.getJydwxzList();
		request.setAttribute("jydwxzList", jydwxzList);
		List<HashMap<String, String>> jyxsList = service.getJyxsList();
		request.setAttribute("jyxsList", jyxsList);
		request.setAttribute("rs",StringUtils.formatData(model));
		//����ҽҩ�ߵ�ְҵѧУ
		if("70002".equals(Base.xxdm)){			
			List<HashMap<String, String>> byqxList = service.getByqxList();
			request.setAttribute("byqxList", byqxList);
			List<HashMap<String, String>> jylbList = service.getJylbList();
			request.setAttribute("jylbList", jylbList);
			List<HashMap<String, String>> jyzkList = service.getJyzkList();
			request.setAttribute("jyzkList", jyzkList);
			if(!StringUtil.isNull(model.getXh())){
				ByqxForm editModel = service.getModel(model);
				BeanUtils.copyProperties(model, StringUtils.formatData(editModel));
			}
		}		
		return mapping.findForward("updateByqx");
	}
	/**
	 * ɾ����ҵȥ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-ɾ��VALUES:{values}")
	public ActionForward delByqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * �鿴��ҵȥ��
	 */
	@SystemAuth(url = url)
	public ActionForward viewByqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWBYQX);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> rs = service.getModelMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		//request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewByqx");
	}
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByqxForm model = (ByqxForm) form;
		ByqxService service = new ByqxService();
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
