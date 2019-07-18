/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����11:10:02 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-28 ����11:10:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcyhAction extends SuperAction<ZcyhForm, ZcyhService> {

	
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	private static final String PATH = "jjgl_zcyh.do";
	
	private static final String url = "jjgl_zcyh.do";
	
	/**
	 * 
	 * @����:ע���û���ѯҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward zcyhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻�ʻ����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcyhCx");
	}
	
	
	/**
	 * 
	 * @����:��ѯע���û��б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����09:43:49
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
	public ActionForward queryZcyhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcyhForm model = (ZcyhForm) form;
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:������ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward hmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		
		String yhm = model.getYhm();
		
		if(StringUtils.isNotBlank(yhm)){
			ZcyhForm dataModel = getService().getModel(yhm);
			
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
			
		}
		
		return mapping.findForward("hmd");
	}
	
	/**
	 * 
	 * @����:���ú������ύ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward hmdSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().szHmd(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL );
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward hmdCancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().cancelHmd(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL );
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:�鿴�û�����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����06:43:32
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
	public ActionForward viewZcyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		
		String yhm = model.getYhm();
		
		if(StringUtils.isNotBlank(yhm)){
			HashMap<String , String> zcyhxxMap = getService().getZcyhMapById(yhm);
			
			List<HashMap<String, String>> znxxMapList = getService().getZnxxMapByYhm(yhm);
			
			request.setAttribute("zcyhxxMap", xgxt.utils.String.StringUtils.formatData(zcyhxxMap));
			
			request.setAttribute("znxxMapList", znxxMapList);
		}
		
		return mapping.findForward("ck");
	}

	/**
	 * ��ת���ҳ���Ϣ����ҳ��.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-23 17:50
	 */
	@SystemAuth(url = url)
	public ActionForward jzxxAdd(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhService svc = getService();

		//yhm�Զ����� ���+0001
		String maxYhm = svc.getMaxYhm();
		Integer yhm = 0;
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		if(StringUtils.isNotEmpty(maxYhm)&&maxYhm.substring(0,2).equals(yearLast)){
			yhm = Integer.parseInt(maxYhm)+1;
		}else {
			yhm = Integer.parseInt(yearLast+"0001");
		}
		request.setAttribute("yhm",yhm);
		return mapping.findForward("jzxxAdd");
	}


	/**
	 * �ҳ���Ϣ���ӵı���.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:34
	 */
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxSaveForAdd(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm zcyhForm = (ZcyhForm) form;
		ZcyhService svc = getService();
		//�û��� ���֤�� ��ϵ�绰�ѱ�ע��
		boolean isYhmExist = svc.isYhmExist(zcyhForm);
		if (isYhmExist) {
			response.getWriter().print(getJsonMessage("�û����ѱ�ע��"));
			return null;
		}

		boolean isSfzhExist = svc.isSfzhExist(zcyhForm);
		if (isSfzhExist) {
			response.getWriter().print(getJsonMessage("���֤���ѱ�ע��"));
			return null;
		}

		boolean isLxdhExist = svc.isLxdhExist(zcyhForm);
		if (isLxdhExist) {
			response.getWriter().print(getJsonMessage("��ϵ�绰�ѱ�ע��"));
			return null;
		}

		boolean result = getService().jzxxSaveForAdd(zcyhForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * ��ת���ҳ���Ϣ�޸�ҳ��.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-23 17:50
	 */
	@SystemAuth(url = url)
	public ActionForward jzxxEdit(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcyhForm zcyhForm = (ZcyhForm) form;
		ZcyhService zcyhService = getService();
		//��ѯ�ҳ���Ϣ
		ZcyhForm model = zcyhService.getModel(zcyhForm.getYhm());
		org.springframework.beans.BeanUtils.copyProperties(model,zcyhForm);
		//��ѯ��Ů��Ϣ
		List<HashMap<String,String>> znxxList = zcyhService.getZnxxMapByYhm(zcyhForm.getYhm());
		request.setAttribute("znxxList",znxxList);
		return mapping.findForward("jzxxEdit");
	}


	/**
	 * �ҳ���Ϣ�޸ĵı���.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:34
	 */
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxSaveForEdit(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcyhForm zcyhForm = (ZcyhForm) form;
		//���֤�� ��ϵ�绰�ѱ�ע��
		ZcyhService svc = getService();

		boolean isSfzhExist = svc.isSfzhExist(zcyhForm);
		if (isSfzhExist) {
			response.getWriter().print(getJsonMessage("���֤���ѱ�ע��"));
			return null;
		}

		boolean isLxdhExist = svc.isLxdhExist(zcyhForm);
		if (isLxdhExist) {
			response.getWriter().print(getJsonMessage("��ϵ�绰�ѱ�ע��"));
			return null;
		}

		boolean result = getService().jzxxSaveForEdit(zcyhForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @����:�ҳ���Ϣ��ɾ������������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����9:50:05
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
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxDel(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) throws Exception {

		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = getService().jzxxDel(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * @����:�����û���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����9:50:05
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
	public ActionForward getJzxxByYhm(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		String yhm = request.getParameter("yhm");
		List<HashMap<String,String>> znxxList = getService().getZnxxMapByYhm(yhm);

		JSONArray dataList = JSONArray.fromObject(znxxList);
		response.getWriter().print(dataList);
		return null;
	}
}
