/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺2013-10-22 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcpzAction extends SuperAction {
	
	public static String _DTXX_SPLC_NAME="dtjs"; 
	
	private static final String url = "shlcpzbase.do";
	
	/**
	 * 
	 * @����:������Ϣ�����б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-10-22 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		CommService cs = new CommService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("shlcpzbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "shlcpzbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shlcpzlb");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-10-22 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����: ������Ϣ����ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-10-22 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ShlcpzForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//��ȡ�ճ����������
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx(_DTXX_SPLC_NAME);
		request.setAttribute("shlcList", shlc);
		//��ȡ��ʹ�ý׶�����
		List<HashMap<String,String>> jdlist = service.getJdList(myForm.getJddm(),myForm.getJdmc());
		request.setAttribute("jdList", jdlist);
		return mapping.findForward("shlcpzxg");
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:36:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ�ճ����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx(_DTXX_SPLC_NAME);
		request.setAttribute("shlcList", shlc);
		//��ȡ��ʹ�ý׶�����
		List<HashMap<String,String>> jdlist = service.getJdList();
		request.setAttribute("jdList", jdlist);
		return mapping.findForward("shlcpzzj");
	}
	
}
