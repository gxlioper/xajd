/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-6 ����03:30:22 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbgl;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-6 ����03:18:09
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglAction extends SuperAction {
	
	private static final String url = "fbglfbbase.do";
	
	/**
	 * 
	 * @����: �ְ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����09:08:10
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
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForFb(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbbase.do");
		request.setAttribute("fbzt", myForm.getFbzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbgllb");
	}

	/**
	 * 
	 * @����: �ѷְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����09:07:52
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
	public ActionForward yfblist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================�߼���ѯ��� end========================
			myForm.setFbzt(FbglService._TBZT_YFB);
			List<HashMap<String, String>> resultList = service
					.getPageListForFb(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglyfblb");
	}

	/**
	 * 
	 * @����: �ְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����02:40:43
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
	public ActionForward fb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		String pk = request.getParameter("pk");
		pk=new String (pk.getBytes("iso8859-1"),"gbk");
		// ���û��ѡ��������н��в���
		if (StringUtil.isNull(pk)) {
			pk = service.getAllPks();
		}
		// �ѷְ�����
		request.setAttribute("yfbts", service.getYfbTs(pk));
		// δ�ְ�����
		request.setAttribute("wfbts", service.getWfbTs(pk));
		// רҵ����
		//request.setAttribute("zysl", service.getSelectZy(pk));
		request.setAttribute("zysl", pk.split(",").length);
		//request.setAttribute("zyids", service.getSelectZyIds(pk));
		// ���ù��򼯺�
		request.setAttribute("pzgzList", fgts
				.getYqyTjnrList(Config._TJGZ_GZDM_FBGZ));
		request.setAttribute("pk",pk);
		return mapping.findForward("fbglfb");
	}

	/**
	 * 
	 * @����: ���ɷְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����02:52:29
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
	public ActionForward createFb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		String pzgzid = request.getParameter("pzgzid");
		String pk = request.getParameter("pk");
		boolean isok = service.saveFb(pzgzid, pk.split(","));
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", String.valueOf(isok));
		response.getWriter().print(JSONObject.fromObject(result));
		return null;
	}

	/**
	 * 
	 * @����: �����ְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-10 ����02:52:42
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�ְ����-�����ְ�VALUES:{values}")
	public ActionForward tzbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String values = request.getParameter("values");
		if (SAVE.equals(myForm.getType())) {
			String bjdm = request.getParameter("bjdm");
			String bjmc = request.getParameter("bjmc");
			boolean isok = service.tzbj(values.split(","), bjdm, bjmc);
			Map<String, String> result = new HashMap<String, String>();
			result.put("message", String.valueOf(isok));
			response.getWriter().print(JSONObject.fromObject(result));
			return null;
		}
		// �༶�б�
		request.setAttribute("bjlist", service.getDqBjList(myForm));
		// ��ѡѧ����Ϣ
		request.setAttribute("data", service.getTbParam(values.split(",")));
		request.setAttribute("ids", values);
		return mapping.findForward("fbgltb");
	}

	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����02:40:57
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-�ְ����-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		String values = request.getParameter("values");
		String ids[]=null;
		if(StringUtils.isNotNull(values)){
			ids=values.split(",");
		}
		String fbzt = request.getParameter("fbzt");
		Map<String, Object> map = service.deleteFb(ids, fbzt);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}