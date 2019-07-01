/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:36:27 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl.fysb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xsgzgl.qgzx.kycxgl.cssz.KyxmCsszService;
import xsgzgl.qgzx.kycxgl.sjxmgl.SjxmglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;



/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ʵ����Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SjxmFysbAction extends SuperAction<SjxmFysbForm, SjxmFysbService> {
	private SjxmFysbService service = new SjxmFysbService();
	private SjxmglService xmglService = new SjxmglService();
	
	private static final String sburl = "qgzx_kycx_sjgl_sjxmfysb.do";
	
	

	@SystemAuth(url =sburl)
	public ActionForward getSjxmFysbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", sburl);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSjxmFysbList");
	}
	
	@SystemAuth(url ="qgzx_kycx_sjgl_sjxmfycx.do")
	public ActionForward getSjxmFycxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageListOfFyff(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "qgzx_kycx_sjgl_sjxmfycx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSjxmFycxList");
	}
	@SystemAuth(url ="qgzx_kycx_sjgl_sjxmfycx.do")
	public ActionForward ffcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.ffcx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "qgzx_kycx_sjgl_sjxmfycx.do");
		request.setAttribute("xmid", model.getXmid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ffcx");
	}
	@SystemAuth(url = sburl,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�����걨-����-xmid:{xmid}")
	public ActionForward addFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		KyxmCsszService csszService = new KyxmCsszService();
		User user = getUser(request);
		List<HashMap<String, String>> ffnyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> cyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> sbxmList = service.getSbxmList(user);
		if(StringUtil.isNull(model.getXmid())&&sbxmList.size()>0){
			model.setXmid(sbxmList.get(0).get("xmid"));
		}
		if (!StringUtil.isNull(model.getXmid())) {
			// ��Ŀ��Ϣ
			HashMap<String,String> xmMap = xmglService.getSjxm(model.getXmid());
			request.setAttribute("rs", StringUtils.formatData(xmMap));
			//��ȡ��Ա�б�
			cyList = service.getCyList(model.getXmid(),"");
			ffnyList = service.getFfyfList(model.getXmid());
			
		}
		HashMap<String,String> csszMap  = csszService.getCssz("sjgl");
		request.setAttribute("ffnyList", ffnyList);
		request.setAttribute("sbxmList", sbxmList);
		request.setAttribute("cyList", cyList);
		request.setAttribute("csszMap", csszMap);
		request.setAttribute("gotoPath", "qgzx_kycxsjxmfysb.do?method=addFysb");
		this.saveToken(request);
		return mapping.findForward("addFysb");
	}
	@SystemAuth(url = sburl,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�����걨-����-xmid:{xmid}")
	public ActionForward editFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		KyxmCsszService csszService = new KyxmCsszService();
		User user = getUser(request);
		SjxmFysbForm myForm = service.getModel(model);
		List<HashMap<String, String>> ffnyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> cyList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String,String>> sbxmList = service.getSbxmList(user);
		if(StringUtil.isNull(model.getXmid())){
			model.setXmid(sbxmList.get(0).get("xmid"));
		}
		if (!StringUtil.isNull(model.getXmid())) {
			// ��Ŀ��Ϣ
			HashMap<String,String> xmMap = xmglService.getSjxm(model.getXmid());
			request.setAttribute("rs", StringUtils.formatData(xmMap));
			//��ȡ��Ա�б�
			cyList = service.getCyList(model.getXmid(),myForm.getSbyf());
			HashMap<String,String> ffnyMap = new HashMap<String,String>();
			ffnyMap.put("nydm", myForm.getSbyf());
			ffnyMap.put("nymc", myForm.getSbyf());
			ffnyList.add(ffnyMap);
		}
		HashMap<String,String> csszMap  = csszService.getCssz("sjgl");
		request.setAttribute("ffnyList", ffnyList);
		request.setAttribute("sbxmList", sbxmList);
		request.setAttribute("sbid", myForm.getXmid());
		request.setAttribute("cyList", cyList);
		request.setAttribute("csszMap", csszMap);
		request.setAttribute("gotoPath", "qgzx_kycxsjxmfysb.do?method=editFysb");
		return mapping.findForward("editFysb");
	}
	
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�����걨-����xmid:{xmid}")
	public ActionForward saveFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SjxmFysbForm myForm = (SjxmFysbForm) form;
		User user =getUser(request);
		String xsxxStr = request.getParameter("sbxxStr");
		List<SjxmglXsxxForm> sbxxList = JsonUtil.jsonArrToList(xsxxStr,SjxmglXsxxForm.class);
		boolean result = service.editFysb(myForm,sbxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�����걨ɾ��-sbid:{sbid}")
	public ActionForward delFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm myForm = (SjxmFysbForm) form;
		String values = request.getParameter("values");
		boolean result = service.delFysb(myForm,values);
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	@SystemAuth(url = sburl,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ�����걨-�ύ:sbid{sbid}")
	public ActionForward submitFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm myForm = (SjxmFysbForm) form;
		User user = getUser(request);
		String values = request.getParameter("values");
		myForm.setSbid(values);
		boolean result = service.submitFysb(myForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	@SystemLog(description = "�����ڹ���ѧ-ʵ����Ŀ����-�����걨�޸�-����sbid:{sbid}")
	public ActionForward saveEditFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm myForm = (SjxmFysbForm) form;
		User user =getUser(request);
		String xsxxStr = request.getParameter("sbxxStr");
		List<SjxmglXsxxForm> sbxxList = JsonUtil.jsonArrToList(xsxxStr,SjxmglXsxxForm.class);
		boolean result = service.saveEditFysb(myForm,sbxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:08:06
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
	@SystemAuth(url =sburl,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelFysb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;
		String sbid = request.getParameter("values");
		model.setSbid(sbid);
		
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = "qgzx_kycx_sjgl_sjxmfycx.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmFysbForm model = (SjxmFysbForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getPageListOfFyff(model, user);// ��ѯ�����м�¼������ҳ
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
