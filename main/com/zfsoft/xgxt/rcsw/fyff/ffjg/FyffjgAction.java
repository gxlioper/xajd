/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-10 ����10:19:47 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj.FyfftjService;
import com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm.FyffxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡����÷��š������Ž��  ����ģ��
 * @�๦������: ���Ž��ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-10 ����10:19:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffjgAction extends SuperAction {
	
	private static final String url = "rcsw_fyff_fyffjg.do";
		
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String FYFF = "fyff";
	
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(FYFF);
	}
	
	/**
	 * 
	 * @����:���÷��Ž���鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:23:25
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
	public ActionForward viewFyffjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	
		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		

		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_fyff_fyffjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewFyffjgList");
		
	}
	
	
	
	/**
	 * 
	 * @����: ���ӷ��÷��Ž��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:29:28
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
	@SystemLog(description="�����ճ�����-���÷���-���÷��Ž��-����XH:{xh},FFXMDM:{ffxmdm},SFJE:{sfje},FFFS:{fffs},FFTJDM:{fftjdm}")
	public ActionForward addFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// ��ѧ�Ų�Ϊ�գ�����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�����ʱ�䣬������Ŀ������;����
			boolean isExist = service.isExistByFfjg(model);
			if (!isExist) {
				// ���������Ϣ
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
		
		
		//��𷢷���ĿList
		FyfftjService fyfftjservice = new FyfftjService();
		List<HashMap<String, String>> fftjList = fyfftjservice.getFyfftj();
		request.setAttribute("fftjList",fftjList);
		
		//��𷢷�;��List
		FyffxmService fyffxmservice = new FyffxmService();
		List<HashMap<String, String>> ffxmList = fyffxmservice.getFyffxm();
		request.setAttribute("ffxmList", ffxmList);
		

		String path = "rcsw_fyff_ffjg.do?method=addFyffjg";
		request.setAttribute("path", path);

		return mapping.findForward("addFyffjg");
	}
	
	
	/**
	 * 
	 * @����:�޸ķ��÷��Ž��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-15 ����02:50:16
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
	@SystemLog(description="�����ճ�����-���÷���-���÷��Ž��-�޸�guid:{guid},XH:{xh},FFXMDM:{ffxmdm},SFJE:{sfje},FFFS:{fffs},FFTJDM:{fftjdm}")
	public ActionForward updateFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
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

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�����ʱ�䣬������Ŀ������;����
			boolean isExist = service.isExistByFfjg(model);
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
		
		//��𷢷���ĿList
		FyfftjService fyfftjservice = new FyfftjService();
		List<HashMap<String, String>> fftjList = fyfftjservice.getFyfftj();
		request.setAttribute("fftjList",fftjList);
		
		//��𷢷�;��List
		FyffxmService fyffxmservice = new FyffxmService();
		List<HashMap<String, String>> ffxmList = fyffxmservice.getFyffxm();
		request.setAttribute("ffxmList", ffxmList);
		

		String path = "xpj_pjjg.do?method=updateFyffjg";
		request.setAttribute("path", path);
		
		FyffjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		request.setAttribute("jbxxList", jbxxList);
		
		//������Ŀ�����ȡ���ŷ�ʽ
		
		String fffs = service.getFffs(model.getFfxmdm());
		request.setAttribute("fffs", fffs);
		
		return mapping.findForward("updatePjxmjg");

	}
	
	
	/**
	 * 
	 * @����: ɾ�����÷��Ž��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-15 ����05:40:48
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
	@SystemLog(description="�����ճ�����-���÷���-���÷��Ž��-ɾ��VALUES:{values}")
	public ActionForward deleteFyffjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgService service = new FyffjgService();
		
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
	 * 
	 * @����: ���÷��Ž������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-16 ����02:14:46
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
		
		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		

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
		return null;
	}
	
	/**
	 * 
	 * @����:���÷��Ž�������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-17 ����09:53:34
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
	public ActionForward oneFyffjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FyffjgForm model = (FyffjgForm) form;
		FyffjgService service = new FyffjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			// ��ѯ���
			request.setAttribute("rs", StringUtils.formatData(service.getOneFyffjgList(model.getGuid())));

			return mapping.findForward("oneFyffjgView");
		} else {
			return updateFyffjg(mapping, form, request, response);
		}

	}
	
	
}
