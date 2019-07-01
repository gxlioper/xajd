/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-6 ����04:35:38 
 */
package com.zfsoft.xgxt.xszy.xsqshf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����֮�ѹ���
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-6 ����04:35:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszyQshfAction extends SuperAction<XszyQshfForm, XszyQshfService> {
	private XszyQshfService service = new XszyQshfService();

	private static final String url = "xszy_xsqshf.do";
	
	/**
	 * 
	 * @����:���һ�����Ϣ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-6 ����05:21:05
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
	public ActionForward getQshfxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
			String msg = "��ģ�������Ժϵ��У���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
	}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			if (null == model.getNj()) {
				model.setNj(Base.currNd);
			}
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xszy_xsqshf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qshfxxList");
	}

	/**
	 * 
	 * @����:����������ֶ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-9 ����02:57:20
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
	public ActionForward fpcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if (UPDATE.equals(myForm.getType())) {
			XszyQshfForm model = service.getModel(myForm.getId());
			if (null != model) {
				BeanUtils.copyProperties(myForm, model);
				request.setAttribute("model", model);
			}
		}
		List<HashMap<String, String>> xyList = service.getXyList();
		request.setAttribute("xyList", xyList);
		return mapping.findForward("fpcz");
	}

	/**
	 * 
	 * @����:���ҷ��䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-9 ����04:30:22
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
	public ActionForward editFpcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==model.getNj()){
			model.setNj(Base.currNd);
		}
		boolean result = service.editFpcz(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����02:39:04
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
	public ActionForward plfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		HashMap<String,String> qssMap = service.getQss(myForm);
		request.setAttribute("qssMap", qssMap);
		List<HashMap<String, String>> xyList = service.getXyList();
		request.setAttribute("xyList", xyList);
		return mapping.findForward("plfp");
	}
	/**
	 * 
	 * @����:�������䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����02:20:28
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
	public ActionForward savePlfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		boolean result = service.savePlfp(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:���ҷ���ȡ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:04:34
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
	public ActionForward qxFp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_CANCEL_SUCCESS_NUM, new Object[]{num,ids.length-num}) : MessageUtil
					.getText(MessageKey.SYS_CANCEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����:������Ϣ�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:04:19
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
	public ActionForward qsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if(null==myForm.getNj()||"null".equals(myForm.getNj())){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxMap = service.getQsxx(myForm);
		/*���ǵ�����֮����Ϣ��������ƥ�����ҡ�����undefind���������ֱ����SQL�����ѯ����*/
		/*qsxxMap.put("sfhhqs", myForm.getSfhhqs());*/
		request.setAttribute("qsxxMap", StringUtils.formatData(qsxxMap));
		List<HashMap<String, String>> rzxsList = service.getRzxsList(myForm);
	    request.setAttribute("rzxsList", rzxsList);
		return mapping.findForward("qsxxView");
	}
	/**
	 * 
	 * @����:��סѧ����Ϣ�鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����08:48:35
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
	public ActionForward rzxsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxMap = service.getQsxx(myForm);
		request.setAttribute("qsxxMap", StringUtils.formatData(qsxxMap));
		List<HashMap<String, String>> rzxsList = service.getRzxsList(myForm);
	    request.setAttribute("rzxsList", rzxsList);
		return mapping.findForward("rzxsView");
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����09:53:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward fpxqCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszyQshfForm myForm = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxAllMap = service.getQsxxAll(myForm);
		List<HashMap<String, String>> fpXqList = service.getFpxq(myForm,user);
		request.setAttribute("qsxxAllMap", StringUtils.formatData(qsxxAllMap));
		request.setAttribute("nj", Base.currNd);
		request.setAttribute("fpXqList", fpXqList);
		return mapping.findForward("fpxqCk");
		
	}
	/**
	 * 
	 * @����:ѧ԰����ͳ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-22 ����01:50:31
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
	public ActionForward fptjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszyQshfForm myForm = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxAllMap = service.getQsxxAll(myForm);
		List<HashMap<String, String>> fpTjList = service.getFptj(myForm,user);
		request.setAttribute("qsxxAllMap", StringUtils.formatData(qsxxAllMap));
		request.setAttribute("nj", Base.currNd);
		request.setAttribute("fpTjList", fpTjList);
		return mapping.findForward("fptjCk");
		
	}

}
