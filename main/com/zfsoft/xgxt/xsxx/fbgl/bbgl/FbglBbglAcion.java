/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-17 ����09:43:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-17 ����09:43:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBbglAcion extends SuperAction {
	
	private static final String url = "fbglbbglbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
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
		request.setAttribute("path", "fbglbbglbase.do");
		//���״̬
		request.setAttribute("bbzt", myForm.getBbzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglbbgllb");
	}

	/**
	 * 
	 * @����: ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:28:42
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-������-����")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getBbxx(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//������ù���id
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BBGZ));
		request.setAttribute("pk", myForm.getPk());
		return mapping.findForward("fbglbbglzj");
	}

	/**
	 * 
	 * @����: �ְ�����б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-4 ����09:24:52
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
	public ActionForward fbglbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		//���ù���id
		String pzgzid = request.getParameter("pzgzid");
		request.setAttribute("pzgzid", pzgzid);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getBbxx(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		} else {
			//���޸���Ϣ
			String kxgxx = service.getKxgxx(pzgzid);
			request.setAttribute("autoGrid", service.getAutoGrid(kxgxx));
		}
		return mapping.findForward("fbglbbglbb");

	}

	/**
	 * 
	 * @����: ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-26 ����03:32:49
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-������-������PZGZID:{pzgzid}")
	public ActionForward saveBb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		String pzgzid = request.getParameter("pzgzid");
		String json = request.getParameter("json");
		String all = request.getParameter("all");
		JSONArray array = JSONArray.fromString(json);
		String messageKey = service.save(array, pzgzid, all);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����: ���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-5 ����03:43:40
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-������-�������PZGZID:{pzgzid}")
	public ActionForward saveTbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		String pzgzid = request.getParameter("pzgzid");
		String json = request.getParameter("json");
		String pk = request.getParameter("pk");

		JSONArray array = JSONArray.fromString(json);
		boolean isok = service.saveTbxx(array, pzgzid, pk);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����: ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:28:58
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
	public ActionForward fbgltb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		List<HashMap<String, String>> bjxx = service.getTbJtBj(pk);
		if (QUERY.equals(myForm.getType())) {
			JSONArray dataList = JSONArray.fromObject(bjxx);
			response.getWriter().print(dataList);
			return null;
		} else {
			HashMap<String, String> data = service.getTbxx(pk);
			String pzgzid = bjxx.get(0).get("pzgzid");
			request.setAttribute("data", StringUtils.formatData(data));
			request.setAttribute("pzgzid", pzgzid);
			request.setAttribute("bjgs", bjxx.size());
			//���ù�������
			FbglGzpzTjServer fgtxs = new FbglGzpzTjServer();
			FbglGzpzTjForm fgtxf = fgtxs.getModel(pzgzid);
			request.setAttribute("pzgzmc", fgtxf.getPzgzmc());
			//���޸���Ϣ
			String kxgxx = service.getKxgxx(pzgzid);
			request.setAttribute("autoGrid", service.getAutoGrid(kxgxx));
		}
		request.setAttribute("pk", pk);
		return mapping.findForward("fbglbbgltb");

	}

	/**
	 * @����: ������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:29:29
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
	public ActionForward fbgltbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> bjxx = service.getTbJtBj(pk);
			JSONArray dataList = JSONArray.fromObject(bjxx);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("fbglbbgltbxx");
	}

	/**
	 * 
	 * @����: ��ȡ��һ��code
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:29:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getNextCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		String pk = request.getParameter("pk");
		String index = request.getParameter("index");
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm fbf = service.fbglBbglNextCodexx(pk, pzgzid, index);
		response.getWriter().print(JSONObject.fromObject(fbf));
		return null;
	}

	/**
	 * 
	 * @����: ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-1 ����10:30:02
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-������-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
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
}
