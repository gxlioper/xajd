/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����02:42:18 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

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
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

@SuppressWarnings("unchecked")
public class KqglAction extends SuperAction {
	
	private KqglService service = new KqglService();
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String KQGL = "kqgl";
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "rcsw_kqjg_cx.do";
	
	/**
	 * 
	 * @����:��ѯ���ڽ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:27:20
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
	public ActionForward viewKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_kqjg_cx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKqjgList");
	}
   
	/**
	 * 
	 * @����:�鿴�������ڽ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:27:44
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
	public ActionForward viewKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		KqglForm myForm = service.getModel(model.getId());
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			xsjbxx.remove("xymc");
			xsjbxx.put("xymc", myForm.getXymc());
			xsjbxx.remove("zymc");
			xsjbxx.put("zymc", myForm.getZymc());
			xsjbxx.remove("bjmc");
			xsjbxx.put("bjmc", myForm.getBjmc());
			
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewKqjg");
	}
	
	/**
	 * 
	 * @����:���ӿ��ڽ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:27:56
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
	@SystemLog(description="�����ճ�����-���ڹ���-���ڹ���-���ӿ��ڽ��XH:{xh},XN:{xn},XQ:{xq},ZC:{zc},KQKC:{kqkc},KQSJ:{kqsj},KQLXDM:{kqlxdm}")
	public ActionForward addKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// ��ѧ�Ų�Ϊ�գ�����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// Ψһ���жϣ�ѧ�ţ�����ʱ�䣬�������ͣ�
			boolean isExist = service.isKqjgExists(model);
			if (!isExist) {
				super.resetToken(request);
				model.setZjsj(DateUtils.getCurrTime());
				// ���ѧ�����ڵǼ�
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		request.setAttribute("kqlblist", service.getList("xg_rcsw_kqgl_kqlbdmb"));
		request.setAttribute("qkjblblist", service.getList("xg_rcsw_kqgl_qkjblbdmb"));
		request.setAttribute("ybqkjblist", service.getList("xg_rcsw_kqgl_ybqkjbdmb"));
		request.setAttribute("dqztlist", service.getList("xg_rcsw_kqgl_mqzkdmb"));
		
		String path = "rcsw_kqgl_kqgljg.do?method=addKqjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addKqjg");
	}
	/**
	 * 
	 * @����:���¿��ڽ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:28:10
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
	@SystemLog(description="�����ճ�����-���ڹ���-���ڹ���-�޸Ŀ��ڽ��ID:{id},XH:{xh},XN:{xn},XQ:{xq},ZC:{zc},KQKC:{kqkc},KQSJ:{kqsj},KQLXDM:{kqlxdm}")
	public ActionForward updateKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		KqglForm myForm = service.getModel(model.getId());
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			xsjbxx.remove("xymc");
			xsjbxx.put("xymc", myForm.getXymc());
			xsjbxx.remove("zymc");
			xsjbxx.put("zymc", myForm.getZymc());
			xsjbxx.remove("bjmc");
			xsjbxx.put("bjmc", myForm.getBjmc());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isKqjgExists(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		request.setAttribute("kqlblist", service.getList("xg_rcsw_kqgl_kqlbdmb"));
		request.setAttribute("qkjblblist", service.getList("xg_rcsw_kqgl_qkjblbdmb"));
		request.setAttribute("ybqkjblist", service.getList("xg_rcsw_kqgl_ybqkjbdmb"));
		request.setAttribute("dqztlist", service.getList("xg_rcsw_kqgl_mqzkdmb"));
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);		
		return mapping.findForward("updateKqjg");
	}
	
	/**
	 * 
	 * @����:ɾ�����ڽ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:28:24
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
	@SystemLog(description="�����ճ�����-���ڹ���-���ڹ���-ɾ��VALUES:{values}")
	public ActionForward deleteKqjg(ActionMapping mapping, ActionForm form,
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
	 * 
	 * @����:��������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-1 ����09:27:12
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
}
