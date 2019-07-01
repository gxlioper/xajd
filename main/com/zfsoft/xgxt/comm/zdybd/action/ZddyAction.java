package com.zfsoft.xgxt.comm.zdybd.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.zdybd.model.ZddyModel;
import com.zfsoft.xgxt.comm.zdybd.service.ZddyService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: �ֶζ���
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ZddyAction extends SuperAction {
	private String messageKey;

	/**
	 * 
	 * @����:���ݹ��ܴ����ȡ���з����µ��ֶζ����б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:07:46
	 * @�޸ļ�¼:
	 * @param gndm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public ActionForward getZddyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		ZddyService service = new ZddyService();
		String gndm = request.getParameter("gndm");
		String xs = request.getParameter("xs");
		List<HashMap<String, String>> list = null;
		if (xs != null && xs.equals("true")) {// getListByGndmOfXs
			list = service.getListByGndmOfXs(gndm);
		} else {
			if ((user.getUserType() != null && user.getUserType().equals("stu")) || "szdw_fdyxx_update".equals(gndm)) {
 				list = service.getListByGndmOfXszdsz(gndm);
			} else {
				list = service.getListByGndm(gndm);
			}
		}

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

	/**
	 * 
	 * @����:�ֶ����ù���
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-10 ����04:27:40
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward zdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "zdybd_zddy_zdpz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zdpz");
	}

	/**
	 * 
	 * @����:��ȡ�ֶζ��壬����ʾ������ѧ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-10 ����04:27:21
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward getZddySimpleList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ZddyService service = new ZddyService();
		String gndm = request.getParameter("gndm");
		List<HashMap<String, String>> list = null;
		list = service.getListByGndmOfSimple(gndm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}

	/**
	 * 
	 * @����:�޸Ĳ��������Ϊ�ա����޸����
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-10 ����06:42:49
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-��Ϣ�޸��ֶ�����-LB:{lb}")
	public ActionForward updateSimple(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZddyModel model = (ZddyModel) form;
		ZddyService service = new ZddyService();
		boolean result = service.updateSimple(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
