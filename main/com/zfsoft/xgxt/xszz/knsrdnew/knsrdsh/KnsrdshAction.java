/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-1-27 ����10:13:33 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: TODO(�������϶����) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-27 ����10:13:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdshAction extends SuperAction {
	//����ѧ��֤������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String KNSRD = "knsrd";
	
	private static final String url = "xg_xszz_knsrd_knsh.do";
	
	/**
	 * 
	 * @����:TODO(�������϶�����б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����10:28:40
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
	public ActionForward knsrdshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdshForm model = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ��ȡѧ��֤�����������
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_xszz_knsrd_knsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsrdshManage");
	}
	
	/**
	 * 
	 * @����:TODO(�������϶��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-10 ����10:21:59
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
	@SuppressWarnings("deprecation")
	@SystemLog(description="����ѧ������-�������϶�-�������϶����-���-NRIDS:{nrids}")
	public ActionForward knsrdDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdshForm model = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//���������Ϣ
			//HashMap<String,String> infoList = service.getXszbbshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//���浥�����
			String jsonStr = request.getParameter("json");
			JSONArray jsonArray = new JSONArray(jsonStr);
			String nrids = request.getParameter("nrids");
			boolean result = service.saveSh(model,user,nrids,jsonArray);
			//boolean result = false;
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		//knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//��ȡ�������϶�ָ�����Լ���
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//��ȡ�������϶�ָ�����ݼ���
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//�������϶�ָ�����ݼ��������������϶�ָ�����Լ��Ϸŵ�map ������ ��ǰ̨ҳ�����
		for (HashMap<String, String> hm : knsrdzbsxList) {
			hm=(HashMap<String, String>) StringUtils.formatDataView(hm);
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = service.getKnsrdzbnrsqList(sxid,model.getSqid());
			object.put(hm,knsrdzbnrList);
		}
		String nrids = service.getKnsrdzbsqnrids(model);
		//���������϶���¼
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(model.getXh()));
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		KnsdcService knsdcSerivce = new KnsdcService();
		//�����������б�
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		return mapping.findForward("knsrdzbDgsh");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-8 ����03:30:12
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
	public ActionForward knsrdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcSerivce = new KnsdcService();
		//�����������б�
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		
		return mapping.findForward("knsrdPlsh");
	}
	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-8 ����05:01:19
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
	@SystemLog(description="����ѧ������-�������϶�-�������϶����-������˱���-SQIDS:{sqids}")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm myForm = (KnsrdshForm) form;
		KnsrdshService service = new KnsrdshService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @����:TODO(�����������϶����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-10 ����10:21:17
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
	@SystemLog(description="����ѧ������-�������϶�-�������϶����-����-SQID:{sqid}")
	public ActionForward cancelKnsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm model = (KnsrdshForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		KnsrdshService service = new KnsrdshService();
		//�����������϶���ˣ����һ����
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(�鿴�������϶����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����02:30:26
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
	public ActionForward viewKnsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdshForm model = (KnsrdshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
	
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//���������Ϣ
			//HashMap<String,String> infoList = service.getXszbbshInfo(model);
			//request.setAttribute("rs", infoList);
		}
		KnsrdshService service = new KnsrdshService();
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("shzt", model.getShzt());
		
		model=service.getModel(model);
		/*KnsrdzbForm knsrdzbForm = new KnsrdzbForm();*/
		//KnsrdzbService knsrdzbService = new KnsrdzbService();
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		//knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//��ȡ�������϶�ָ�����Լ���
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//��ȡ�������϶�ָ�����ݼ���
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//�������϶�ָ�����ݼ��������������϶�ָ�����Լ��Ϸŵ�map ������ ��ǰ̨ҳ�����
		for (HashMap<String, String> hm : knsrdzbsxList) {
			hm=(HashMap<String, String>) StringUtils.formatDataView(hm);
			String sxid = hm.get("sxid"); 
			//knsrdzbnrList = knsrdzbService.getKnsrdzbnrList(sxid);
			knsrdzbnrList = service.getKnsrdzbnrsqList(sxid,model.getSqid());
			object.put(hm,knsrdzbnrList);
		}
		
		String nrids = service.getKnsrdzbsqnrids(model);
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", model);
		return mapping.findForward("viewKnsrdsh");
		
	}
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:27:45
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
		KnsrdshForm model = (KnsrdshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		KnsrdshService service = new KnsrdshService();
		model.setShzt(shlx);
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
