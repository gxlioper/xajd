/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

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
import xgxt.xtwh.comm.splc.XtwhShlcDAO;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjgzAction extends SuperAction {

	private static final String url = "qjgzbase.do";
	
	/**
	 * 
	 * @����:��ٹ����б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		CommService cs = new CommService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("Qjlxwh.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String userXy = "";
		String userXymc = "";
		if("xx".equals(user.getUserStatus())){
			userXy = "qx";
			userXymc = "ȫУ";
		}else{
			userXy = user.getUserDep();
			userXymc = user.getUserDepName();
		}
		request.setAttribute("userXy",userXy);
		request.setAttribute("userXymc",userXymc);
		String path = "qjgzbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qjgzlb");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-��ٹ���ά��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
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
	 * @����: ��ٹ���ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-��ٹ���ά��-ɾ��QJGZID:{qjgzid},KSSJ:{kssj},JSSJ:{jssj},SPLCID:{splcid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String, String> map = service.getInfo(myForm);
		request.setAttribute("ssxymc",map.get("ssxymc"));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(map));
		//��ȡ�ճ����������
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("qjlxList", service.getQjlxList());
		return mapping.findForward("qjgzxg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-��ٹ���ά��-����KSSJ:{kssj},JSSJ:{jssj},SPLCID:{splcid}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String ssxy = "xx".equals(user.getUserStatus()) ? "qx":user.getUserDep();
			myForm.setSsxydm(ssxy);
			boolean result = service.save(myForm);

			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String userXy = "";
		String userXymc = "";
		if("xx".equals(user.getUserStatus())){
			userXy = "qx";
			userXymc = "ȫУ";
		}else{
			userXy = user.getUserDep();
			userXymc = user.getUserDepName();
		}
		request.setAttribute("ssxydm",userXy);
		request.setAttribute("ssxymc",userXymc);
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ�ճ����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("qjlxList", service.getQjlxList());
		return mapping.findForward("qjgzzj");
	}
	/**
	 * 
	 * @����:�Ƿ��������
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:24:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		Map<String, String> map = new HashMap<String, String>();
		
		// �Ƿ�����������ͻ
		String result = service.getClash(myForm);
		if (StringUtils.isNotNull(result)) {//�г�ͻ
			map.put("success", "false");
			map.put("message", result);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�鿴��ٹ��򣬷� 
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-30 ����04:46:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ckQjgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QjgzForm myForm = (QjgzForm) form;
		HashMap<String,String> map = new QjgzService().getInfo(myForm);
		String qjlxmc = new QjlxService().getModel(map.get("qjlxid")).getQjlxmc();
		String shlcmc = new XtwhShlcDAO().getShlcMap(map.get("splcid")).get("lcxx");
		request.setAttribute("rs", map);
		request.setAttribute("qjlxmc", qjlxmc);
		request.setAttribute("shlcmc", shlcmc);
		return mapping.findForward("qjgzck");
		
			
	}
	
	/**
	 * 
	 * @����: ����״̬
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-19 ����09:38:20
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
	public ActionForward openZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjgzForm myForm = (QjgzForm) form;
		QjgzForm model = new QjgzService().getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		String qjlxmc;
		try {
			qjlxmc = new QjlxService().getModel(myForm.getQjlxid()).getQjlxmc();
			request.setAttribute("qjlxmc", qjlxmc);
		} catch (NullPointerException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		String shlcmc = new XtwhShlcDAO().getShlcMap(myForm.getSplcid()).get("lcxx");
		request.setAttribute("shlcmc", shlcmc);
		return mapping.findForward("openZt");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-19 ����10:31:19
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
	public ActionForward saveOpenZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjgzForm myForm = (QjgzForm) form;
		boolean rs = new QjgzService().runUpdate(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
