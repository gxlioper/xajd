/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:36:27 
 */
package  xsgzgl.qgzx.kycxgl.kyxmgl;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;


/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KyxmsqAction extends SuperAction<KyxmglForm, KyxmglService> {
	private KyxmglService service = new KyxmglService();
	
	private static final String url = "qgzx_kycx_kygl_kyxmsq.do";
	
	
	
	@SystemAuth(url = url)
	public ActionForward getKyxmsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm model = (KyxmglForm) form;
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
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKyxmsqList");
	}

	/**
	 * 
	 * @����:��Ŀά��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����08:57:08
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ����-xmid:{xmid}")
	public ActionForward xmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		KyxmglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		HashMap<String,String> kyxmMap = service.getKyxmgl(myForm);
		//��ȡ��Ա�б�
		List<HashMap<String, String>> cyList = service.getCyList(kyxmMap.get("xmid"));
		//��ȡ��ʦ�б�
		List<HashMap<String, String>> zdlsList = service.getTeaList(kyxmMap.get("xmid"));
		//��ȡ����Ԥ���б�
		List<HashMap<String, String>> ysxxList = service.getYsxxList(kyxmMap.get("xmid"));
		List<HashMap<String, String>> sfsfsList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�ʦ����
		request.setAttribute("sfsfsList", sfsfsList);
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		request.setAttribute("ysxxList", ysxxList);
		request.setAttribute("rs", StringUtils.formatData(kyxmMap));
		this.saveToken(request);
		return mapping.findForward("xmwh");
	}
	/**
	 * 
	 * @����:��Աά��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����09:34:16
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Աά��-xmid:{xmid}")
	public ActionForward cywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		KyxmglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		HashMap<String,String> kyxmMap = service.getKyxmgl(myForm);
		//��ȡ��Ա�б�
		List<HashMap<String, String>> cyList = service.getCyList(kyxmMap.get("xmid"));
		//��ȡ��ʦ�б�
		List<HashMap<String, String>> zdlsList = service.getTeaList(kyxmMap.get("xmid"));
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		List<HashMap<String, String>> sfsfsList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�ʦ����
		request.setAttribute("sfsfsList", sfsfsList);
		request.setAttribute("rs", StringUtils.formatData(kyxmMap));
		this.saveToken(request);
		return mapping.findForward("cywh");
	}
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����08:56:57
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ����-�ύ:{xmid}")
	public ActionForward submitXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmglForm myForm = (KyxmglForm) form;
		User user = getUser(request);
		String values = request.getParameter("values");
		myForm.setXmid(values);
		boolean result = service.submitXmwh(myForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����09:13:22
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ����-����:{xmid}")
	public ActionForward cancelXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			KyxmglForm model = new KyxmglForm();
			model.setXmid(sqid);
			model.setSplcid(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��Ŀά�����뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����08:56:31
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Ŀ����-����xmid:{xmid}")
	public ActionForward saveXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		KyxmglForm myForm = (KyxmglForm) form;
		User user =getUser(request);
		String xsxxStr = request.getParameter("xsxxStr");
		String jsxxStr = request.getParameter("jsxxStr");
		String ysxxStr = request.getParameter("ysxxStr");
		List<KyxmglXsxxForm> xsxxList = JsonUtil.jsonArrToList(xsxxStr,KyxmglXsxxForm.class);
		List<KyxmglJsxxForm> jsxxList = JsonUtil.jsonArrToList(jsxxStr,KyxmglJsxxForm.class);
		List<KyxmglJfysForm> jfysList = JsonUtil.jsonArrToList(ysxxStr,KyxmglJfysForm.class);
		boolean result = service.editXmwh(myForm,user,xsxxList,jsxxList,jfysList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��Աά������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-3 ����09:47:50
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
	@SystemLog(description = "�����ڹ���ѧ-������Ŀ����-��Աά��-����xmid:{xmid}")
	public ActionForward saveCywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		KyxmglForm myForm = (KyxmglForm) form;
		String xsxxStr = request.getParameter("xsxxStr");
		String jsxxStr = request.getParameter("jsxxStr");
		List<KyxmglXsxxForm> xsxxList = JsonUtil.jsonArrToList(xsxxStr,KyxmglXsxxForm.class);
		List<KyxmglJsxxForm> jsxxList = JsonUtil.jsonArrToList(jsxxStr,KyxmglJsxxForm.class);
		
		boolean result = service.editCywh(myForm,xsxxList,jsxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		KyxmglForm myForm = (KyxmglForm) form;
		String xhs= request.getParameter("xhs");
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			String xmid = request.getParameter("xmid");
			myForm.setXmid(xmid);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,xhs);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycxkyxmgl.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("xhs", xhs);
		request.setAttribute("xmid", myForm.getXmid());
		return mapping.findForward("getStu");
	}
	@SystemAuth(url = url)
	public ActionForward getTea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		KyxmglForm myForm = (KyxmglForm) form;
		String zghs= request.getParameter("zghs");
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			String xmid = request.getParameter("xmid");
			myForm.setXmid(xmid);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getTeaList(myForm,zghs);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycxkyxmgl.do?method=getTea";
		request.setAttribute("zghs", zghs);
		request.setAttribute("xmid", myForm.getXmid());
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}
	
	
	
	
}
