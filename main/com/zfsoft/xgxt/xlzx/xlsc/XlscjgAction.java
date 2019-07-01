/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-21 ����11:51:34 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

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
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ɸ�鷽����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-21 ����11:51:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlscjgAction extends SuperAction<XlscjgForm,XlscjgService>{
	private static final String url = "xg_xlzx_xlscjg.do";
	private XlscjgService service = new XlscjgService();
	private static final String XLZXXLSCJG = "xlzxxlscjg";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLZXXLSCJG);
	}
	
	/**
	 * @����: ��ѯ�б�ҳ����ת
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����03:21:33
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
	public ActionForward xlscjgManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
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
		String path = "xg_xlzx_xlscjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlscjgList");
	}
	
	/**
	 * @����: ���ӡ����淽��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-21 ����03:22:02
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
	@SystemLog(description="����������ѯ-����ɸ����-����XH:{xh},SCRQ:{scrq},SCL:{scl},SDS:{sds},SAS:{sas},BKYY:{bkyy},BKJL:{bkjl},SFXYYT:{sfxyyt},SFYYT:{sfyyt}")
	public ActionForward xlscjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
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
			// Ψһ���ж�
			boolean isExist = service.uniqueness(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.XLZX_XLSCJG_EXIST, false));
			}
			return null;
		}
		//����Path
		String path = "xlzx_xlscjg.do?method=xlscjgAdd";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("jbxxList", jbxxList);
		//�����д���������ֻ��г���<br/>
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("xlscjgAdd");
	}
	
	/**
	 * @����: �޸ķ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����05:34:15
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
	@SystemLog(description="����������ѯ-����ɸ����-�޸�id:{id},XH:{xh},SCRQ:{scrq}")
	public ActionForward xlscjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
		if (UPDATE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.uniqueness(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.XLZX_XLSCJG_EXIST, false));
			}
			return null;
		}
		XlscjgForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sfxyyt", myForm.getSfxyyt());
		request.setAttribute("sfyyt", myForm.getSfyyt());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xlscjgUpdate");
	}
	
	/**
	 * @����: ɾ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����05:21:10
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
	@SystemLog(description="����������ѯ-����ɸ����-ɾ��VALUES:{values}")
	public ActionForward xlscjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
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
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����08:11:42
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
	public ActionForward xlscjgView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
		XlscjgForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("model", model);
		//�Ƿ���Ҫ�μ�Լ̸
		String sfxyyt = "";
		if("0".equals(model.getSfxyyt())){
			 sfxyyt = "��";
		}else{
			sfxyyt = "��";
		}
		request.setAttribute("sfxyyt", sfxyyt);
		//�Ƿ��Ѳμ�Լ̸
		String sfyyt = "";
		if("0".equals(model.getSfyyt())){
			sfyyt = "��";
		}else{
			sfyyt = "��";
		}
		request.setAttribute("sfyyt", sfyyt);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xlscjgView");
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����08:16:23
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
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