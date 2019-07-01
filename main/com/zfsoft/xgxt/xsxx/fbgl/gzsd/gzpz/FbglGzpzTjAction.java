/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:09:28 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

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
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglGzdmService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:09:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjAction extends SuperAction {
	
	private static final String url = "fbglgzsd.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", "fbglgzsd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglgzpztjlb");
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�����趨-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		Map<String, Object> data = service.getGzpz(myForm.getPzgzid());
		request.setAttribute("data", data);
		return mapping.findForward("fbglgzpztjck");
	}

	/**
	 * 
	 * @����: ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����02:39:34
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�����趨-����PZGZMC:{pzgzmc},GZDM:{gzdm},SYZT:{syzt}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		if (QUERY.equals(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// ��������
		FbglGzdmService fgs = new FbglGzdmService();
		request.setAttribute("gzlist", fgs.getGzList());
		this.saveToken(request);
		return mapping.findForward("fbglgzpztjzj");
	}

	/**
	 * 
	 * @����: �޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����02:39:47
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�����趨-�޸�PZGZID:{pzgzid},PZGZMC:{pzgzmc},GZDM:{gzdm},SYZT:{syzt}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		FbglGzpzTjForm model = service.getModel(request.getParameter("pzgzid"));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		FbglGzdmService fgs = new FbglGzdmService();
		Map<String, Object> data = service.getGzpzForUpdate(myForm.getPzgzid());
		request.setAttribute("data", data);
		// ��������
		request.setAttribute("gzlist", fgs.getGzList());
		return mapping.findForward("fbglgzpztjxg");
	}

	/**
	 * 
	 * @����: ��������״̬
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����02:40:11
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�����趨-��������״̬VALUES:{values}")
	public ActionForward updateQyzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String[] ids = values.split(",");
		boolean result = false;
		for (String pzgzid : ids) {
			FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
			String qyzt = request.getParameter("qyzt");
			myForm.setQyzt(qyzt);
			myForm.setPzgzid(pzgzid);
			FbglGzpzTjServer service = new FbglGzpzTjServer();
			result = service.runUpdate(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����: ���ƹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����08:59:13
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�����趨-���ƹ���PZGZID:{pzgzid}")
	public ActionForward copy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		boolean result = service.copy(pzgzid);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����: �����͹����Ƿ��������õ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����09:09:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward sfQy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		String gzdm = request.getParameter("gzdm");
		boolean sfqy = service.sfQy(gzdm);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", sfqy);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}

	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-26 ����10:55:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getGzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		response.getWriter().print(
				JSONArray.fromObject(fgtxs.getGzpzTjxxLx(pzgzid)));
		return null;
	}

	/**
	 * 
	 * @����: ��ȡԤ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-26 ����10:55:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getPreview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		String tjgzid = request.getParameter("tjgzid");
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		response.getWriter().print(
				JSONArray.fromObject(service.getGzStr(pzgzid, tjgzid)));
		return null;
	}

	/**
	 * 
	 * @����: ��ȡ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����02:09:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getGzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		String gzdm = request.getParameter("gzdm");
		response.getWriter().print(
				JSONArray.fromObject(fgts.getYqyTjnrList(gzdm)));
		return null;
	}
}
