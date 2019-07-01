/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-10 ����11:36:27 
 */
package com.zfsoft.xgxt.khgl.khdxgl.khdxgl;

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
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ���˶������ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-10 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhdxglAction extends SuperAction<KhdxglForm, KhdxglService> {
	private KhdxglService service = new KhdxglService();
	
	private static final String url = "khgl_khdxgl_khdxgl.do";

	/**
	 * 
	 * @����:��ѯ���˶����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����01:54:11
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
	public ActionForward getKhdxglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khdxgl_khdxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKhdxglList");
	}
	/**
	 * 
	 * @����:���˶���鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-17 ����08:48:09
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
	public ActionForward viewKhdxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhdxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path =null;
		
		if("1".equals(model.getKhlx())){
			path = "khglKhdxgl.do?method=viewKhdxOfStuList";//���˶���Ϊѧ��
		}else{
			path = "khglKhdxgl.do?method=viewKhdxOfTeaList";//���˶���Ϊ��ʦ
		}
		
		request.setAttribute("path", path);
		request.setAttribute("khlx", model.getKhlx());
		request.setAttribute("pflx", model.getPflx());
		request.setAttribute("sfnz", model.getSfnz());
		request.setAttribute("fpzt", model.getFpzt());
		request.setAttribute("khdxid", model.getKhdxid());
		request.setAttribute("pfzid", model.getPfzid());
		request.setAttribute("khdxmc", model.getKhdxmc());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKhdxList");
	}

	/**
	 * 
	 * @����:���˶�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����05:27:51
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
	public ActionForward addKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		return mapping.findForward("addKhdx");
	}

	/**
	 * 
	 * @����:�޸Ŀ��˶���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����01:55:20
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
	public ActionForward updateKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		KhdxglForm KhdxglForm = service.getModel(myForm);
		if (null != KhdxglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhdxglForm));
			request.setAttribute("KhdxglForm", KhdxglForm);
		}
		request.setAttribute("sfqy", request.getParameter("sfqy"));
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		return mapping.findForward("updateKhdx");
	}

	
	/**
	 * 
	 * @����:���˶��󱣴�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����05:28:12
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
	@SystemLog(description = "���ʿ��˹���-���˶������-���˶������-���ӻ��޸ı���KHDXMC:{khdxmc},KHLX:{khlx},KHDXID:{khdxid}")
	public ActionForward saveKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHDXGL_KHDX_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhdx(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ�����˶���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����04:08:06
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
	@SystemLog(description = "���ʿ��˹���-���˶������-���˶������-ɾ��VALUES:{values}")
	public ActionForward delKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�жϿ��˶����Ƿ�ʹ��
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.KHGL_KHDXGL_KHDX_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}


	/**
	 * 
	 * @����:���˶���ѡ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����04:43:08
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
	public ActionForward showKhdx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		String forWardUrl=null;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhdxFpList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		if("1".equals(myForm.getKhlx())){
			request.setAttribute("path", "khglKhdxgl.do?method=viewKhdxOfStuList");//����·����ֻ�����ڸ߼���ѯ
			forWardUrl="showStu";
		}else{
			request.setAttribute("path", "khglKhdxgl.do?method=viewKhdxOfTeaList");//����·����ֻ�����ڸ߼���ѯ
			forWardUrl="showTea";
		}
		
		request.setAttribute("khdxid", myForm.getKhdxid());
		return mapping.findForward(forWardUrl);
	}
	/**
	 * 
	 * @����:���˶�����䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
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
	public ActionForward saveKhdxFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.saveKhdxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	/**
	 * 
	 * @����:���˶�����䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
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
	public ActionForward saveKhdxQxFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.saveKhdxQxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
}
